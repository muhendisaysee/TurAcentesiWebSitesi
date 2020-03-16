/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Sehir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class SehirDAO {
    
    private DBConnection connector;
    private Connection connection;
    private AcenteDAO acenteDao;
    
     public Sehir find(Long id){
            Sehir sehir = null;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from sehir where sehir_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            sehir = new Sehir();
            sehir.setSehir_id(rs.getLong("sehir_id"));
            sehir.setSehir_adi(rs.getString("sehir_adi"));
            sehir.setSehir_adres(rs.getString("sehir_adres"));
            sehir.setSehir_tel(rs.getString("sehir_tel"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return sehir;
    }
      public int count() {
        int count=0;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select count(sehir_id) as sehir_count from sehir");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("sehir_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

     public List<Sehir> findAll(int page, int pageSize) {

        List<Sehir> sehirList = new ArrayList<>();
         int start = (page-1)*pageSize;
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from sehir order by sehir_id asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Sehir tmp = new Sehir();
                tmp.setSehir_id(rs.getLong("sehir_id"));
                tmp.setSehir_adi(rs.getString("sehir_adi"));
                tmp.setSehir_adres(rs.getString("sehir_adres"));
                tmp.setSehir_tel(rs.getString("sehir_tel"));

                tmp.setAcente(this.getAcenteDao().find(rs.getLong("acente_id")));

                sehirList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return sehirList;
    }
     public void create(Sehir sehir) {
        
         try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into sehir(sehir_adi,sehir_adres ,sehir_tel ,acente_id) values(?,?,?,?)");
            pst.setString(1, sehir.getSehir_adi());
            pst.setString(2, sehir.getSehir_adres());
            pst.setString(3, sehir.getSehir_tel());
            pst.setLong(4, sehir.getAcente().getAcente_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        
    }
    
    public void delete(Sehir sehir) {
        
   try {

            PreparedStatement pst = this.getConnection().prepareStatement("delete from sehir where sehir_id=?");
            pst.setLong(1, sehir.getSehir_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        
    }
    
    public void update(Sehir sehir) {
        
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("update sehir set sehir_adi=?,sehir_adres=?,acente_id=? where sehir_id=?");
            pst.setString(1, sehir.getSehir_adi());
            pst.setString(2, sehir.getSehir_adres());
            pst.setLong(3, sehir.getAcente().getAcente_id());
            pst.setLong(4, sehir.getSehir_id());
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
