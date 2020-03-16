package dao;

import entity.Bolum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class BolumDAO {

    private DBConnection connector;
    private Connection connection;

    public Bolum find(Long id) {
        Bolum bolum = null;
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from bolum where bolum_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            bolum = new Bolum();
            bolum.setBolum_id(rs.getLong("bolum_id"));
            bolum.setBolum_adi(rs.getString("bolum_adi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bolum;
    }

    public int count() {
        int count = 0;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select count(bolum_id) as bolum_count from bolum");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("bolum_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public void update(Bolum bolum) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update bolum set bolum_adi=? where bolum_id=?");
            pst.setString(1, bolum.getBolum_adi());
            pst.setLong(2, bolum.getBolum_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void delete(Bolum bolum) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from bolum where bolum_id=?");
            pst.setLong(1, bolum.getBolum_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Bolum bolum) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into bolum(bolum_adi) values(?)");
            pst.setString(1, bolum.getBolum_adi());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Bolum> findAll(int page, int pageSize) {
        List<Bolum> bolumList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from bolum order by bolum_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Bolum tmp = new Bolum();
                tmp.setBolum_id(rs.getLong("bolum_id"));
                tmp.setBolum_adi(rs.getString("bolum_adi"));

                bolumList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bolumList;
    }

    public DBConnection getConnector() {

        if (this.connector == null) {
            this.connector = new DBConnection();
        }
        return connector;
    }

    public Connection getConnection() {

        if (connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }

}
