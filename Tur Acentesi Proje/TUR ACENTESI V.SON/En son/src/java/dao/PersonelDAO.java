package dao;

import entity.Personel;
import entity.Tur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class PersonelDAO {

    private DBConnection connector;
    private Connection connection;
    private AcenteDAO acenteDAO;
    private BolumDAO bolumDAO;
    private GrupDAO grupDAO;
    private TurDAO turDAO;   
    
    
    public Personel find(Long id) {
        Personel personel = null;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from personel where personel_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            personel = new Personel();
            personel.setPersonel_id(rs.getLong("personel_id"));
            personel.setPersonel_adi_soyadi(rs.getString("personel_adi_soyadi"));
            personel.setPersonel_tel(rs.getString("personel_tel"));
            personel.setPersonel_sifre("personel_sifre");
            personel.setBolum(this.getBolumDAO().find(rs.getLong("bolum_id")));
            personel.setPersonel_kullanici("personel_kullanici");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personel;
    }

    public int count(String aramaTerimi) {
        int count = 0;

        try {
            String query="select count(personel_id) as personel_count from personel";
            if(aramaTerimi!=null){
                query+=" where personel_adi_soyadi like ?";
            }
            PreparedStatement pst = this.getConnection().prepareStatement(query);
            if(aramaTerimi!=null){
                pst.setString(1, "%"+aramaTerimi+"%");
            }
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("personel_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
    
    public List<Personel> findAll(int page, int pageSize,String aramaTerimi) {
        List<Personel> personelList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            String query="select * from personel";
            
            if(aramaTerimi!=null){
                query+=" where personel_adi_soyadi like ?";
            }
            query+=" order by personel_id asc limit ? offset ?" ;
            
            PreparedStatement pst = this.getConnection().prepareStatement(query);
            if(aramaTerimi!=null){
                pst.setString(1, "%"+aramaTerimi+"%");
                pst.setInt(2, pageSize);
                pst.setInt(3, start);
            }
            else {
                pst.setInt(1, pageSize);
                pst.setInt(2, start);
            }
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Personel tmp = new Personel();

                tmp.setPersonel_id(rs.getLong("personel_id"));
                tmp.setPersonel_adi_soyadi(rs.getString("personel_adi_soyadi"));
                tmp.setPersonel_tel(rs.getString("personel_tel"));
                tmp.setAcente(this.getAcenteDAO().find(rs.getLong("acente_id")));
                tmp.setBolum(this.getBolumDAO().find(rs.getLong("bolum_id")));
                tmp.setGrup(this.getGrupDAO().find(rs.getLong("grup_id")));
                tmp.setPersonel_sifre(rs.getString("personel_sifre"));
                tmp.setPersonel_kullanici(rs.getString("personel_kullanici"));
                
                tmp.setTurList(this.getTurDAO().getTurPersonel(tmp.getPersonel_id()));
                personelList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return personelList;

    }   

    public TurDAO getTurDAO() {
        if (this.turDAO == null) {
            this.turDAO = new TurDAO();
        }
        return turDAO;
    }

    public DBConnection getConnector() {
        if (this.connector == null) {
            this.connector = new DBConnection();
        }

        return connector;
    }

    private Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }

    public void create(Personel personel) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into personel (personel_adi_soyadi,personel_tel,acente_id,grup_id,bolum_id,personel_sifre,personel_kullanici) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, personel.getPersonel_adi_soyadi());
            pst.setString(2, personel.getPersonel_tel());
            pst.setLong(3, personel.getAcente().getAcente_id());
            pst.setLong(4, personel.getGrup().getGrup_id());
            pst.setLong(5, personel.getBolum().getBolum_id());
            pst.setString(6, personel.getPersonel_sifre());
            pst.setString(7, personel.getPersonel_kullanici());
            pst.executeUpdate();
            
            ResultSet gk = pst.getGeneratedKeys();

            Long personel_id = null;
            if (gk.next()) {
                personel_id = gk.getLong(1);
            }
            for (Tur tur : personel.getTurList()) {
                pst = this.getConnection().prepareStatement("insert into tur_personel(tur_id,personel_id) values(?,?)");
                pst.setLong(1, tur.getTur_id());
                pst.setLong(2, personel_id);
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Personel personel) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update personel set personel_adi_soyadi=?,personel_tel=?,acente_id=?,grup_id=?,bolum_id=?,personel_sifre=?,personel_kullanici=? where personel_id=?");
           
            pst.setString(1, personel.getPersonel_adi_soyadi());
            pst.setString(2, personel.getPersonel_tel());
            pst.setLong(3, personel.getAcente().getAcente_id());
            pst.setLong(4, personel.getGrup().getGrup_id());
            pst.setLong(5, personel.getBolum().getBolum_id());
            pst.setString(6, personel.getPersonel_sifre());
            pst.setString(7, personel.getPersonel_kullanici());
            pst.setLong(8,personel.getPersonel_id());
            pst.executeUpdate();

            pst = this.getConnection().prepareStatement("delete from tur_personel where personel_id=?");
            pst.setLong(1, personel.getPersonel_id());
            pst.executeUpdate();

            for (Tur tur : personel.getTurList()) {
                PreparedStatement st2 = this.getConnection().prepareStatement("insert into tur_personel(tur_id,personel_id) values(?,?)");
                st2.setLong(1, tur.getTur_id());
                st2.setLong(2, personel.getPersonel_id());
                st2.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Personel personel) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from tur_personel where personel_id=?");
            pst.setLong(1, personel.getPersonel_id());
            pst.executeUpdate();
            pst = this.getConnection().prepareStatement("delete from personel where personel_id=?");
            pst.setLong(1, personel.getPersonel_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public AcenteDAO getAcenteDAO() {

        if (this.acenteDAO == null) {
            this.acenteDAO = new AcenteDAO();
        }
        return acenteDAO;
    }

    public BolumDAO getBolumDAO() {

        if (this.bolumDAO == null) {
            this.bolumDAO = new BolumDAO();
        }
        return bolumDAO;
    }

    public GrupDAO getGrupDAO() {

        if (this.grupDAO == null) {
            this.grupDAO = new GrupDAO();
        }
        return grupDAO;
    }

}
