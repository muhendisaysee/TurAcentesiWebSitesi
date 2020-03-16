/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Kampanya;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class KampanyaDAO {

    private DBConnection connector;
    private Connection connection;
    private AcenteDAO acenteDao;

    public List<Kampanya> findAll(int page, int pageSize) {

        List<Kampanya> kampanyaList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from kampanya order by kampanya_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Kampanya tmp = new Kampanya();

                tmp.setKampanya_id(rs.getLong("kampanya_id"));
                tmp.setKampanya_turu(rs.getString("kampanya_turu"));
                tmp.setKampanya_detayi(rs.getString("kampanya_detayi"));
                tmp.setAcente(this.getAcenteDao().find(rs.getLong("acente_id")));

                kampanyaList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return kampanyaList;
    }

    public Kampanya find(Long id) {
        Kampanya kampanya = null;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from kampanya where kampanya_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();

            kampanya = new Kampanya();
            kampanya.setKampanya_id(rs.getLong("kampanya_id"));
            kampanya.setKampanya_turu(rs.getString("kampanya_turu"));
            kampanya.setKampanya_detayi(rs.getString("kampanya_detayi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return kampanya;
    }

    public int count() {
        int count = 0;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select count(kampanya_id) as kampanya_count from kampanya");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("kampanya_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public void create(Kampanya kampanya) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into kampanya(kampanya_turu,kampanya_detayi,acente_id) values(?,?,?)");
            pst.setString(1, kampanya.getKampanya_turu());
            pst.setString(2, kampanya.getKampanya_detayi());
            pst.setLong(3, kampanya.getAcente().getAcente_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Kampanya kampanya) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("delete from kampanya where kampanya_id=?");
            pst.setLong(1, kampanya.getKampanya_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(Kampanya kampanya) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("update kampanya set kampanya_turu=?,kampanya_detayi=?,acente_id=? where kampanya_id=?");
            pst.setString(1, kampanya.getKampanya_turu());
            pst.setString(2, kampanya.getKampanya_detayi());
            pst.setLong(3, kampanya.getAcente().getAcente_id());
            pst.setLong(4, kampanya.getKampanya_id());
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

}
