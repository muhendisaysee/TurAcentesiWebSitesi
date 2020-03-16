/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Otel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class OtelDAO {

    private DBConnection connector;
    private Connection connection;
    private AcenteDAO acenteDao;

    public List<Otel> findByAcenteId(Long id) {

       
        List<Otel> otelList = new ArrayList<>();
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from otel where acente_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Otel tmp = new Otel();
                tmp.setOtel_id(rs.getLong("otel_id"));
                tmp.setOtel_adi(rs.getString("otel_adi"));
                tmp.setOtel_yildizi(rs.getInt("otel_yildizi"));
                tmp.setAcente(this.getAcenteDao().find(rs.getLong("acente_id")));
                otelList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return otelList;
    }
    
    
    public Otel find(Long id){
        Otel otel = null;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from otel where otel_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            otel = new Otel();
            otel.setOtel_id(rs.getLong("otel_id"));
            System.out.println(rs.getLong("otel_id"));
            otel.setOtel_adi(rs.getString("otel_adi"));
            System.out.println(rs.getLong("otel_adi"));
            otel.setOtel_yildizi(rs.getInt("otel_yildizi"));
            System.out.println(rs.getLong("otel_yildizi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return otel;
    }
     public int count() {
        int count=0;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select count(otel_id) as otel_count from otel");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("otel_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    
    public List<Otel> findAll(int page, int pageSize) {

       
        List<Otel> otelList = new ArrayList<>();
        int start = (page-1)*pageSize;
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from otel order by otel_id asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Otel tmp = new Otel();

                tmp.setOtel_id(rs.getLong("otel_id"));
                tmp.setOtel_adi(rs.getString("otel_adi"));
                tmp.setOtel_yildizi(rs.getInt("otel_yildizi"));
                tmp.setAcente(this.getAcenteDao().find(rs.getLong("acente_id")));

                otelList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return otelList;
    }

    public void create(Otel otel) {
        
      try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into otel(otel_adi,otel_yildizi,acente_id) values(?,?,?)");
            pst.setString(1, otel.getOtel_adi());
            pst.setInt(2, otel.getOtel_yildizi());
            pst.setLong(3, otel.getAcente().getAcente_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void delete(Otel otel) {
        
             try {

            PreparedStatement pst = this.getConnection().prepareStatement("delete from otel where otel_id=?");
            pst.setLong(1, otel.getOtel_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        
    }
    
    public void update(Otel otel) {
        
         try {

            PreparedStatement pst = this.getConnection().prepareStatement("update otel set otel_adi=?,otel_yildizi=?,acente_id=? where otel_id=?");
            pst.setString(1, otel.getOtel_adi());
            pst.setInt(2, otel.getOtel_yildizi());
            pst.setLong(3, otel.getAcente().getAcente_id());
            pst.setLong(4, otel.getOtel_id());
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
