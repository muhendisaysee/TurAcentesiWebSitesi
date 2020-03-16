/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Acente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class AcenteDAO {

    private DBConnection connector;
    private Connection connection;

    public Acente find(Long id) {
        Acente acente = null;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from acente where acente_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            acente = new Acente();
            acente.setAcente_id(rs.getLong("acente_id"));
            acente.setAcente_adi(rs.getString("acente_adi"));
            acente.setSube_adi(rs.getString("sube_adi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return acente;
    }
    //Sayfa sayısını bulan metot.
    public int count() {
        int count = 0;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select count(acente_id) as acente_count from acente");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("acente_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public List<Acente> findAll(int page, int pageSize) {
        List<Acente> acenteList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from acente order by acente_id asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Acente tmp = new Acente();
                tmp.setAcente_id(rs.getLong("acente_id"));
                tmp.setAcente_adi(rs.getString("acente_adi"));
                tmp.setSube_adi(rs.getString("sube_adi"));
                acenteList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return acenteList;
    }

    public void create(Acente acente) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into acente(sube_adi) values(?)");
            pst.setString(1, acente.getSube_adi());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Acente acente) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update acente set sube_adi=? where acente_id=?");
            pst.setString(1, acente.getSube_adi());
            pst.setLong(2, acente.getAcente_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Acente acente) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("delete from acente where acente_id=?");
            pst.setLong(1, acente.getAcente_id());
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
