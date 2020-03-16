/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ulasim;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class UlasimDAO {

    private DBConnection connector;
    private Connection connection;
    private AcenteDAO acenteDAO;

    public void create(Ulasim ulasim) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into ulasim (ulasim_kalkis_yeri,ulasim_varis_yeri,ulasim_turu,acente_id) values(?,?,?,?)");
            pst.setString(1, ulasim.getUlasim_kalkis_yeri());
            pst.setString(2, ulasim.getUlasim_varis_yeri());
            pst.setString(3, ulasim.getUlasim_turu());
            pst.setLong(4, ulasim.getAcente().getAcente_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Ulasim ulasim) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update ulasim set ulasim_kalkis_yeri=?,ulasim_varis_yeri=?,ulasim_turu=?,acente_id=? where ulasim_id=?");
            pst.setString(1, ulasim.getUlasim_kalkis_yeri());
            pst.setString(2, ulasim.getUlasim_varis_yeri());
            pst.setString(3, ulasim.getUlasim_turu());
            pst.setLong(4, ulasim.getAcente().getAcente_id());
            pst.setLong(5, ulasim.getUlasim_id());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Ulasim ulasim) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from ulasim where ulasim_id=?");
            pst.setLong(1, ulasim.getUlasim_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Ulasim> findByAcenteId(Long id) {

        List<Ulasim> ulasimList = new ArrayList<>();
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from ulasim where acente_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Ulasim tmp = new Ulasim();
                tmp.setUlasim_id(rs.getLong("ulasim_id"));
                tmp.setUlasim_kalkis_yeri(rs.getString("ulasim_kalkis_yeri"));
                tmp.setUlasim_varis_yeri(rs.getString("ulasim_varis_yeri"));
                tmp.setUlasim_turu(rs.getString("ulasim_turu"));
                tmp.setAcente(this.getAcenteDAO().find(rs.getLong("acente_id")));
                ulasimList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ulasimList;
    }

    public Ulasim find(Long id) {
        Ulasim ulasim = new Ulasim();

        try {
            PreparedStatement pst = getConnection().prepareStatement("select * from ulasim where ulasim_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            ulasim.setUlasim_id(rs.getLong("ulasim_id"));
            ulasim.setUlasim_kalkis_yeri(rs.getString("ulasim_kalkis_yeri"));
            ulasim.setUlasim_varis_yeri(rs.getString("ulasim_varis_yeri"));
            ulasim.setUlasim_turu(rs.getString("ulasim_turu"));
            ulasim.setAcente(this.getAcenteDAO().find(rs.getLong("acente_id")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ulasim;
    }

    public List<Ulasim> findAll(int page, int pageSize) {
        List<Ulasim> ulasimList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from ulasim order by ulasim_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Ulasim ulasim = new Ulasim();
                ulasim.setUlasim_id(rs.getLong("ulasim_id"));
                ulasim.setUlasim_kalkis_yeri(rs.getString("ulasim_kalkis_yeri"));
                ulasim.setUlasim_varis_yeri(rs.getString("ulasim_varis_yeri"));
                ulasim.setUlasim_turu(rs.getString("ulasim_turu"));
                ulasim.setAcente(this.getAcenteDAO().find(rs.getLong("acente_id")));
                ulasimList.add(ulasim);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ulasimList;
    }

    public int count() {
        int count = 0;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select count(ulasim_id) as ulasim_count from ulasim");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("ulasim_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
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

    public AcenteDAO getAcenteDAO() {
        if (this.acenteDAO == null) {
            this.acenteDAO = new AcenteDAO();
        }
        return acenteDAO;
    }

}
