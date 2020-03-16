package entity;

import java.util.List;

public class Personel {

    private Long personel_id;
    private String personel_adi_soyadi;
    private String personel_tel;
    private Acente acente;
    private Bolum bolum;
    private Grup grup;
    private String personel_sifre;
    private String personel_kullanici;
    private List<Tur> turList;

    public Personel(Long personel_id, String personel_adi_soyadi, String personel_tel, Acente acente, Bolum bolum, Grup grup, String personel_sifre, List<Tur> turList,String personel_kullanici) {
        this.personel_id = personel_id;
        this.personel_adi_soyadi = personel_adi_soyadi;
        this.personel_tel = personel_tel;
        this.acente = acente;
        this.bolum = bolum;
        this.grup = grup;
        this.personel_sifre = personel_sifre;
        this.turList = turList;
        this.personel_kullanici=personel_kullanici;
    }

    public String getPersonel_kullanici() {
        return personel_kullanici;
    }

    public void setPersonel_kullanici(String personel_kullanici) {
        this.personel_kullanici = personel_kullanici;
    }

    

    public String getPersonel_sifre() {
        return personel_sifre;
    }

    public void setPersonel_sifre(String personel_sifre) {
        this.personel_sifre = personel_sifre;
    }

    @Override
    public String toString() {
        return "Personel{" + "personel_id=" + personel_id + ", personel_adi_soyadi=" + personel_adi_soyadi + ", personel_tel=" + personel_tel + ", acente=" + acente + ", bolum=" + bolum + ", grup=" + grup + ", personel_sifre=" + personel_sifre + ", turList=" + turList + '}';
    }

  
    
    
    public Personel() {
    }
    
    public Acente getAcente() {
        return acente;
    }

    public void setAcente(Acente acente) {
        this.acente = acente;
    }

    public Bolum getBolum() {
        return bolum;
    }

    public void setBolum(Bolum bolum) {
        this.bolum = bolum;
    }

    public Grup getGrup() {
        return grup;
    }

    public void setGrup(Grup grup) {
        this.grup = grup;
    }    

    public Long getPersonel_id() {
        return personel_id;
    }

    public void setPersonel_id(Long personel_id) {
        this.personel_id = personel_id;
    }

    public String getPersonel_adi_soyadi() {
        return personel_adi_soyadi;
    }

    public void setPersonel_adi_soyadi(String personel_adi_soyadi) {
        this.personel_adi_soyadi = personel_adi_soyadi;
    }

    public String getPersonel_tel() {
        return personel_tel;
    }

    public void setPersonel_tel(String personel_tel) {
        this.personel_tel = personel_tel;
    }

    public List<Tur> getTurList() {
        return turList;
    }

    public void setTurList(List<Tur> turList) {
        this.turList = turList;
    }

    

}
