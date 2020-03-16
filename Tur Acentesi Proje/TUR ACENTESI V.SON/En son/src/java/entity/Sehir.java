/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Sehir {

    private Long sehir_id;
    private String sehir_adi;
    private String sehir_adres;
    private String sehir_tel;
    private Acente acente;

    public Sehir() {
    }

    public Sehir(Long sehir_id, String sehir_adi, String sehir_adres, String sehir_tel, Acente acente) {
        this.sehir_id = sehir_id;
        this.sehir_adi = sehir_adi;
        this.sehir_adres = sehir_adres;
        this.sehir_tel = sehir_tel;
        this.acente = acente;
    }

    @Override
    public String toString() {
        return "sehir{" + "sehir_id=" + sehir_id + ", sehir_adi=" + sehir_adi + ", sehir_adres=" + sehir_adres + ", sehir_tel=" + sehir_tel + ", acente=" + acente + '}';
    }

    public Long getSehir_id() {
        return sehir_id;
    }

    public void setSehir_id(Long sehir_id) {
        this.sehir_id = sehir_id;
    }

    public String getSehir_adi() {
        return sehir_adi;
    }

    public void setSehir_adi(String sehir_adi) {
        this.sehir_adi = sehir_adi;
    }

    public String getSehir_adres() {
        return sehir_adres;
    }

    public void setSehir_adres(String sehir_adres) {
        this.sehir_adres = sehir_adres;
    }

    public String getSehir_tel() {
        return sehir_tel;
    }

    public void setSehir_tel(String sehir_tel) {
        this.sehir_tel = sehir_tel;
    }

    public Acente getAcente() {
        return acente;
    }

    public void setAcente(Acente acente) {
        this.acente = acente;
    }

}
