/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Musteri;
import entity.Tur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class MusteriDAO {

    private DBConnection connector;
    private Connection connection;

    private GrupDAO grupDAO;
    private KampanyaDAO kampanyaDAO;
    private TurDAO turDAO;

    public Musteri find(Long id) {
        Musteri musteri = null;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from musteri where musteri_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            musteri = new Musteri();
            musteri.setMusteri_id(rs.getLong("musteri_id"));
            musteri.setMusteri_adi_soyadi(rs.getString("musteri_adi_soyadi"));
            musteri.setMusteri_sifre(rs.getString("musteri_sifre"));
            musteri.setKampanya(this.getKampanyaDAO().find(rs.getLong("kampanya_id")));
            musteri.setTurMusteriList(this.getTurDAO().getTurMusteri(musteri.getMusteri_id()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return musteri;
    }

    public int count(String aramaTerimi) {
        int count = 0;
        try {

           String query="select count(musteri_id) as musteri_count from musteri";
            if(aramaTerimi!=null){
                query+=" where musteri_adi_soyadi like ?";
            }
            PreparedStatement pst = this.getConnection().prepareStatement(query);
            if(aramaTerimi!=null){
                pst.setString(1, "%"+aramaTerimi+"%");
            }
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("musteri_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public List<Musteri> findAll(int page, int pageSize, String aramaTerimi) {
        List<Musteri> musteriList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            String query="select * from musteri";
            
            if(aramaTerimi!=null){
                query+=" where musteri_adi_soyadi like ?";
            }
            query+=" order by musteri_id asc limit ? offset ?" ;
            
            PreparedStatement pst = this.getConnection().prepareStatement(query);
            if(aramaTerimi!=null){
                pst.setString(1, "%"+aramaTerimi+"%");
                pst.setInt(2, pageSize);
                pst.setInt(3, start);
            }
            else {
                pst.setInt(1, pageSize);
                pst.setInt(2, start);
            }
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Musteri tmp = new Musteri();

                tmp.setMusteri_id(rs.getLong("musteri_id"));
                tmp.setMusteri_adi_soyadi(rs.getString("musteri_adi_soyadi"));
                tmp.setKampanya(this.getKampanyaDAO().find(rs.getLong("kampanya_id")));
                tmp.setGrup(this.getGrupDAO().find(rs.getLong("grup_id")));
                tmp.setTurMusteriList(this.getTurDAO().getTurMusteri(tmp.getMusteri_id()));
                tmp.setMusteri_sifre(rs.getString("musteri_sifre"));
                musteriList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return musteriList;
    }

    public void create(Musteri musteri) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into musteri (musteri_adi_soyadi,kampanya_id,musteri_sifre) values(?,?,?) ", Statement.RETURN_GENERATED_KEYS);
            Long musteri_id = null;
            pst.setString(1, musteri.getMusteri_adi_soyadi());
            if (musteri.getKampanya().getKampanya_id() != null) {
                pst.setLong(2, musteri.getKampanya().getKampanya_id());
            } else {
                pst.setObject(2, null);
            }
            pst.setString(3, musteri.getMusteri_sifre());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();

            if (gk.next()) {
                musteri_id = gk.getLong(1);
                musteri.setMusteri_id(musteri_id);///kayıt yaparken iletisim boşken musteri id'sini cekmek icin

            }
            if (musteri.getTurMusteriList() != null) {
                for (Tur tur : musteri.getTurMusteriList()) {
                    pst = this.getConnection().prepareStatement("insert into tur_musteri(tur_id,musteri_id) values(?,?)");
                    pst.setLong(1, tur.getTur_id());
                    pst.setLong(2, musteri_id);
                    pst.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteByTur(List<Tur> turList, Long musteri_id) {
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from tur_musteri where musteri_id=? ");
            pst.setLong(1, musteri_id);
            pst.executeUpdate();

            for (Tur tur : turList) {
                pst = this.getConnection().prepareStatement("insert into tur_musteri(tur_id,musteri_id) values(?,?)");
                pst.setLong(1, tur.getTur_id());
                pst.setLong(2, musteri_id);
                pst.executeUpdate();
            }

        } catch (Exception e) {
        }
    }

    public void update(Musteri musteri) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("update musteri set musteri_adi_soyadi=?,kampanya_id=?,musteri_sifre=? where musteri_id=? ");
            pst.setString(1, musteri.getMusteri_adi_soyadi());
            pst.setLong(2, musteri.getKampanya().getKampanya_id());
            pst.setString(3, musteri.getMusteri_sifre());
            pst.setLong(4, musteri.getMusteri_id());

            pst.executeUpdate();

            pst = this.getConnection().prepareStatement("delete from tur_musteri where musteri_id=? ");
            pst.setLong(1, musteri.getMusteri_id());
            pst.executeUpdate();

            for (Tur tur : musteri.getTurMusteriList()) {
                PreparedStatement st2 = this.getConnection().prepareStatement("insert into tur_musteri(tur_id,musteri_id) values(?,?)");
                st2.setLong(1, tur.getTur_id());
                st2.setLong(2, musteri.getMusteri_id());
                st2.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Musteri musteri) {
        try {

            PreparedStatement pst = this.getConnection().prepareStatement(" delete from tur_musteri where musteri_id=? ");
            pst.setLong(1, musteri.getMusteri_id());
            pst.executeUpdate();
            pst = this.getConnection().prepareStatement(" delete from musteri where musteri_id=? ");
            pst.setLong(1, musteri.getMusteri_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public GrupDAO getGrupDAO() {

        if (this.grupDAO == null) {
            this.grupDAO = new GrupDAO();
        }
        return grupDAO;
    }

    public KampanyaDAO getKampanyaDAO() {
        if (this.kampanyaDAO == null) {
            this.kampanyaDAO = new KampanyaDAO();
        }
        return kampanyaDAO;
    }

    public DBConnection getConnector() {
        if (this.connector == null) {
            this.connector = new DBConnection();
        }

        return connector;
    }

    private Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }

    public TurDAO getTurDAO() {
        if (this.turDAO == null) {
            this.turDAO = new TurDAO();
        }
        return turDAO;
    }

}
