/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Tur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class TurDAO {

    private DBConnection connector;
    private Connection connection;
    private AcenteDAO acenteDao;
    private DosyaDAO dosyaDao;

    public Tur find(Long id) {
        Tur tur = null;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from tur where tur_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            tur = new Tur();
            tur.setTur_id(rs.getLong("tur_id"));
            tur.setTur_adi(rs.getString("tur_adi"));
            tur.setTur_turu(rs.getString("tur_turu"));
            tur.setTur_tarihi(rs.getDate("tur_tarihi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return tur;
    }

    public int count(String aramaTerimi) {
        int count=0;

        try {
            String query="select count(tur_id) as tur_count from tur";
            if(aramaTerimi!=null){
                query += " where tur_adi like ?";
            }
            PreparedStatement pst = this.getConnection().prepareStatement(query);
            if(aramaTerimi!=null){
                pst.setString(1,"%"+aramaTerimi+"%");
            }
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("tur_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public List<Tur> findAll(int page, int pageSize,String aramaTerimi) {
        List<Tur> turList = new ArrayList<>();
        int start = (page-1)*pageSize;
        
        
        try {
            String query="select * from tur";
            
            if(aramaTerimi!=null){
                query+=" where tur_adi like ?";
            }
            
            query+=" order by tur_id asc limit ? offset ?";
            
            
            PreparedStatement pst = this.getConnection().prepareStatement(query);
            if(aramaTerimi!=null){
                pst.setString(1, "%"+aramaTerimi+"%");
                pst.setInt(2, pageSize);
                pst.setInt(3, start);
            }
            else{
                pst.setInt(1, pageSize);
                pst.setInt(2, start);
            }
            
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Tur tmp = new Tur();

                tmp.setTur_id(rs.getLong("tur_id"));
                tmp.setTur_adi(rs.getString("tur_adi"));
                tmp.setTur_turu(rs.getString("tur_turu"));
                tmp.setTur_tarihi(rs.getDate("tur_tarihi"));
                tmp.setAcente(this.getAcenteDao().find(rs.getLong("acente_id")));
                tmp.setDosya(this.getDosyaDao().find(rs.getLong("dosya_id")));
                turList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return turList;
    }

    public List<Tur> getTurMusteri(Long musteri_id) {
        List<Tur> TurMusteriList = new ArrayList<>();

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from tur_musteri where musteri_id=?");
            pst.setLong(1, musteri_id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                TurMusteriList.add(this.find(rs.getLong("tur_id")));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return TurMusteriList;
    }

    public List<Tur> getTurPersonel(Long personel_id) {
        List<Tur> TurPersonelList = new ArrayList<>();

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from tur_personel where personel_id=?");
            pst.setLong(1, personel_id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                TurPersonelList.add(this.find(rs.getLong("tur_id")));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return TurPersonelList;
    }

    public void create(Tur tur) {

        try {

            SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdt.format(tur.getTur_tarihi());

            PreparedStatement pst = this.getConnection().prepareStatement("insert into tur(tur_adi,tur_turu,tur_tarihi,acente_id,dosya_id) values(?,?,?,?,?)");
            pst.setString(1, tur.getTur_adi());
            pst.setString(2, tur.getTur_turu());
            pst.setString(3, s);
            pst.setLong(4, tur.getAcente().getAcente_id());
            pst.setLong(5, tur.getDosya().getDosya_id());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Tur tur) {

        try {

            SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdt.format(tur.getTur_tarihi());

            PreparedStatement pst = this.getConnection().prepareStatement("update tur set tur_adi=?,tur_turu=?,tur_tarihi=?,acente_id=?,dosya_id=? where tur_id=?");
            pst.setString(1, tur.getTur_adi());
            pst.setString(2, tur.getTur_turu());
            pst.setString(3, s);
            pst.setLong(4, tur.getAcente().getAcente_id());
            pst.setLong(5, tur.getDosya().getDosya_id());
            pst.setLong(6, tur.getTur_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Tur tur) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("delete from tur where tur_id=?");
            pst.setLong(1, tur.getTur_id());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DBConnection getConnector() {
        if (this.connector == null) {
            this.connector = new DBConnection();
        }
        return connector;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }

    public AcenteDAO getAcenteDao() {
        if (this.acenteDao == null) {
            this.acenteDao = new AcenteDAO();
        }
        return acenteDao;
    }

    public DosyaDAO getDosyaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDAO();
        }
        return dosyaDao;
    }
}
