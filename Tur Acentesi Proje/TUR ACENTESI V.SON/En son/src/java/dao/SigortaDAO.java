/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Sigorta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class SigortaDAO {

    private DBConnection connector;
    private Connection connection;
    private AcenteDAO acenteDao;
    

    public int count() {
        int count = 0;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select count(sigorta_id) as sigorta_count from sigorta");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("sigorta_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public List<Sigorta> findAll(int page, int pageSize) {
        List<Sigorta> sigortaList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from sigorta order by sigorta_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Sigorta tmp = new Sigorta();

                tmp.setSigorta_id(rs.getLong("sigorta_id"));
                tmp.setSigorta_suresi(rs.getInt("sigorta_suresi"));
                tmp.setSigorta_adi(rs.getString("sigorta_adi"));
                tmp.setAcente(this.getAcenteDao().find(rs.getLong("acente_id")));

                sigortaList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return sigortaList;
    }
     public List<Sigorta> findByAcenteId(Long id) {

        List<Sigorta> SigortaList = new ArrayList<>();
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from sigorta where acente_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Sigorta tmp = new Sigorta();
                tmp.setSigorta_id(rs.getLong("sigorta_id"));
                tmp.setSigorta_adi(rs.getString("sigorta_adi"));
                tmp.setSigorta_suresi(rs.getInt("sigorta_suresi"));
                tmp.setAcente(this.getAcenteDao().find(rs.getLong("acente_id")));
                SigortaList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return SigortaList;
    }

    public Sigorta find(Long id) {
        Sigorta sigorta = null;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * form sigorta where sigorta_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();

            sigorta = new Sigorta();
            sigorta.setSigorta_id(rs.getLong("sigorta_id"));
            sigorta.setSigorta_adi(rs.getString("sigorta_adi"));
            sigorta.setSigorta_suresi(rs.getInt("sigorta_suresi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return sigorta;
    }

    public void create(Sigorta sigorta) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into sigorta(sigorta_suresi,sigorta_adi,acente_id) values(?,?,?)");
            pst.setInt(1, sigorta.getSigorta_suresi());
            pst.setString(2, sigorta.getSigorta_adi());
            pst.setLong(3, sigorta.getAcente().getAcente_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Sigorta sigorta) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("delete from sigorta where sigorta_id=?");
            pst.setLong(1, sigorta.getSigorta_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(Sigorta sigorta) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("update sigorta set sigorta_suresi=?,sigorta_adi=?,acente_id=? where sigorta_id=?");
            pst.setInt(1, sigorta.getSigorta_suresi());
            pst.setString(2, sigorta.getSigorta_adi());
            pst.setLong(3, sigorta.getAcente().getAcente_id());
            pst.setLong(4, sigorta.getSigorta_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public AcenteDAO getAcenteDao() {
        if (this.acenteDao == null) {
            this.acenteDao = new AcenteDAO();
        }
        return acenteDao;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = getConnector().connect();
        }
        return connection;
    }

    public DBConnection getConnector() {
        if (this.connector == null) {
            this.connector = new DBConnection();
        }
        return connector;
    }

   

}
