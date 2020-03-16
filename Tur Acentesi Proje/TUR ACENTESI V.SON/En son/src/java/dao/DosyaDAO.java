/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Dosya;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class DosyaDAO {

    private DBConnection connector;
    private Connection connection;

    public Dosya find(Long id) {
        Dosya dosya = null;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from dosya where dosya_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            dosya = new Dosya();
            dosya.setDosya_id(rs.getLong("dosya_id"));
            dosya.setDosya_adi(rs.getString("dosya_adi"));
            dosya.setDosya_yolu(rs.getString("dosya_yolu"));
            dosya.setDosya_tipi(rs.getString("dosya_tipi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return dosya;
    }

    public int count() {
        int count = 0;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select count(dosya_id) as dosya_count from dosya");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("dosya_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public List<Dosya> findAll(int page, int pageSize) {
        List<Dosya> dosyaList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from dosya order by dosya_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Dosya tmp = new Dosya(rs.getLong("dosya_id"), rs.getString("dosya_adi"), rs.getString("dosya_yolu"), rs.getString("dosya_tipi"));
                dosyaList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return dosyaList;
    }

    public void create(Dosya dosya) {
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into dosya(dosya_adi,dosya_yolu,dosya_tipi) values(?,?,?)");
            pst.setString(1, dosya.getDosya_adi());
            pst.setString(2, dosya.getDosya_yolu());
            pst.setString(3, dosya.getDosya_tipi());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void delete(Dosya dosya) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("delete from dosya where dosya_id=?");
            pst.setLong(1, dosya.getDosya_id());
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

}
