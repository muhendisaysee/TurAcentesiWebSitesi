/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Iletisim;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class IletisimDAO {
    
    private DBConnection connector;
    private Connection connection;
    private MusteriDAO musteriDao;
    
    public Iletisim find(Long id){
         Iletisim iletisim = null;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from iletisim where iletisim_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            iletisim = new Iletisim();
            iletisim.setIletisim_id(rs.getLong("iletisim_id"));
            iletisim.setTel(rs.getString("tel"));
            iletisim.setEposta(rs.getString("e_posta"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return iletisim;
       
    }
     public int count() {
        int count=0;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select count(iletisim_id) as iletisim_count from iletisim");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("iletisim_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

     public List<Iletisim> findAll(int page, int pageSize) {
          List<Iletisim> iletisimList = new ArrayList<>();
     int start = (page-1)*pageSize;
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from iletisim order by iletisim_id asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Iletisim tmp = new Iletisim();
                tmp.setIletisim_id(rs.getLong("iletisim_id"));
                tmp.setTel(rs.getString("tel"));
                tmp.setEposta(rs.getString("e_posta"));
                tmp.setMusteri(this.getMusteriDao().find(rs.getLong("musteri_id")));
                iletisimList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return iletisimList;
    }
     
     public void create(Iletisim iletisim) {

         try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into iletisim(tel,e_posta,musteri_id) values(?,?,?)");
            pst.setString(1, iletisim.getTel());
            pst.setString(2, iletisim.getEposta());
            pst.setLong(3, iletisim.getMusteri().getMusteri_id());
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Iletisim iletisim) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update iletisim set tel=?,e_posta=?,musteri_id=? where iletisim_id=?");
            pst.setString(1,iletisim.getTel());
            pst.setString(2, iletisim.getEposta());
            pst.setLong(3, iletisim.getMusteri().getMusteri_id());
            pst.setLong(4, iletisim.getIletisim_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Iletisim iletisim) {

            try {

            PreparedStatement pst = this.getConnection().prepareStatement("delete from iletisim where iletisim_id=?");
            pst.setLong(1, iletisim.getIletisim_id());
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

    public MusteriDAO getMusteriDao() {
        if (this.musteriDao == null) {
            this.musteriDao = new MusteriDAO();
        }
        return musteriDao;
    }

}
