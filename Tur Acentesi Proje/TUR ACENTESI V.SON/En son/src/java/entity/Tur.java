/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.Objects;

public class Tur {

    private Long tur_id;
    private String tur_adi;
    private String tur_turu;
    private Date tur_tarihi;
    private Acente acente;
    private Dosya dosya;

    public Tur() {
    }

    public Tur(Long tur_id, String tur_adi, String tur_turu, Date tur_tarihi, Acente acente, Dosya dosya) {
        this.tur_id = tur_id;
        this.tur_adi = tur_adi;
        this.tur_turu = tur_turu;
        this.tur_tarihi = tur_tarihi;
        this.acente = acente;
        this.dosya = dosya;
    }

    public Date getTur_tarihi() {
        return tur_tarihi;
    }

    public void setTur_tarihi(Date tur_tarihi) {
        this.tur_tarihi = tur_tarihi;
    }

    public Long getTur_id() {
        return tur_id;
    }

    public void setTur_id(Long tur_id) {
        this.tur_id = tur_id;
    }

    public String getTur_adi() {
        return tur_adi;
    }

    public void setTur_adi(String tur_adi) {
        this.tur_adi = tur_adi;
    }

    public String getTur_turu() {
        return tur_turu;
    }

    public void setTur_turu(String tur_turu) {
        this.tur_turu = tur_turu;
    }

    public Acente getAcente() {
        return acente;
    }

    public void setAcente(Acente acente) {
        this.acente = acente;
    }

    public Dosya getDosya() {
        return dosya;
    }

    public void setDosya(Dosya dosya) {
        this.dosya = dosya;
    }

    @Override
    public String toString() {
        return "Tur{" + "tur_id=" + tur_id + ", tur_adi=" + tur_adi + ", tur_turu=" + tur_turu + ", tur_tarihi=" + tur_tarihi + ", acente=" + acente + ", dosya=" + dosya + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.tur_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tur other = (Tur) obj;
        if (!Objects.equals(this.tur_id, other.tur_id)) {
            return false;
        }
        return true;
    }

    
    
}
