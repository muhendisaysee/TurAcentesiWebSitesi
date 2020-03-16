/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Grup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class GrupDAO {

    private Connection connection;
    private DBConnection connector;

    public List<Grup> findAll(int page, int pageSize) {
        List<Grup> grupList = new ArrayList<>();
         int start = (page-1)*pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from grup order by grup_id asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Grup tmp = new Grup(rs.getLong("grup_id"), rs.getString("grup_adi"));
                grupList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return grupList;
    }

    public int count() {
        int count = 0;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select count(grup_id) as grup_count from grup");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("grup_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
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

    public void update(Grup grup) {
        try {
            PreparedStatement pst = getConnection().prepareStatement("update grup set grup_adi=? where grup_id=?");
            pst.setString(1, grup.getGrup_adi());
            pst.setLong(2, grup.getGrup_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Grup grup) {
        try {
            PreparedStatement pst = getConnection().prepareStatement("insert into grup(grup_adi) values(?)");
            pst.setString(1, grup.getGrup_adi());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Grup grup) {
        try {
            PreparedStatement pst = getConnection().prepareStatement("delete from grup where grup_id=?");
            pst.setLong(1, grup.getGrup_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Grup find(Long id) {
        Grup grup = null;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from grup where grup_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            grup = new Grup();
            grup.setGrup_id(rs.getLong("grup_id"));
            grup.setGrup_adi(rs.getString("grup_adi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return grup;
    }

}
