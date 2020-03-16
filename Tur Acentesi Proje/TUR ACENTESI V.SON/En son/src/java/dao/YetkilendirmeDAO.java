package dao;

import entity.Yetkilendirme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class YetkilendirmeDAO {

    private DBConnection connector;
    private Connection connection;
    private GrupDAO grupDao;

    public List<Yetkilendirme> findAll(int page, int pageSize) {
        List<Yetkilendirme> yetkilendirmeList = new ArrayList();
        int start = (page - 1) * pageSize;
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from yetkilendirme order by yetkilendirme_id asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Yetkilendirme tmp = new Yetkilendirme();
                tmp.setYetkilendirme_id(rs.getLong("yetkilendirme_id"));
                tmp.setY_bolum_adi(rs.getString("y_bolum_adi"));
                tmp.setGrup(this.getGrupDao().find(rs.getLong("grup_id")));
                yetkilendirmeList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return yetkilendirmeList;
    }

    public int count() {
        int count = 0;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select count(yetkilendirme_id) as yetkilendirme_count from yetkilendirme");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("yetkilendirme_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public Yetkilendirme find(Long id) {
        Yetkilendirme yetkilendirme = null;

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("select * from yetkilendirme where yetkilendirme_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            yetkilendirme = new Yetkilendirme();
            yetkilendirme.setYetkilendirme_id(rs.getLong("yetkilendirme_id"));
            yetkilendirme.setY_bolum_adi(rs.getString("y_bolum_adi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return yetkilendirme;
    }

    public void create(Yetkilendirme yetkilendirme) {
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into yetkilendirme (y_bolum_adi,grup_id) values(?,?)");
            pst.setString(1, yetkilendirme.getY_bolum_adi());
            pst.setLong(2, yetkilendirme.getGrup().getGrup_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Yetkilendirme yetkilendirme) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("delete from yetkilendirme where yetkilendirme_id=?");
            pst.setLong(1, yetkilendirme.getYetkilendirme_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(Yetkilendirme yetkilendirme) {

        try {

            PreparedStatement pst = this.getConnection().prepareStatement("update yetkilendirme set y_bolum_adi=?,grup_id=? where yetkilendirme_id=?");
            pst.setString(1, yetkilendirme.getY_bolum_adi());
            pst.setLong(2, yetkilendirme.getGrup().getGrup_id());
            pst.setLong(3, yetkilendirme.getYetkilendirme_id());
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

    public GrupDAO getGrupDao() {
        if (this.grupDao == null) {
            this.grupDao = new GrupDAO();
        }
        return grupDao;
    }

}
