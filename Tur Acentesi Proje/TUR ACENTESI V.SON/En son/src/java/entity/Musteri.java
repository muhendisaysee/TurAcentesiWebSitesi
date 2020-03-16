/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import java.util.Objects;

public class Musteri {

    private Long musteri_id;
    private String musteri_adi_soyadi;
    private Kampanya kampanya;
    private Grup grup;
    private List<Tur> turMusteriList;
    private String musteri_sifre;

    public Musteri() {
    }

    public Musteri(Long musteri_id, String musteri_adi_soyadi, Kampanya kampanya, Grup grup, List<Tur> turMusteriList, String musteri_sifre) {
        this.musteri_id = musteri_id;
        this.musteri_adi_soyadi = musteri_adi_soyadi;
        this.kampanya = kampanya;
        this.grup = grup;
        this.turMusteriList = turMusteriList;
        this.musteri_sifre = musteri_sifre;
    }

    public Long getMusteri_id() {
        return musteri_id;
    }

    public void setMusteri_id(Long musteri_id) {
        this.musteri_id = musteri_id;
    }

    public String getMusteri_sifre() {
        return musteri_sifre;
    }

    public void setMusteri_sifre(String musteri_sifre) {
        this.musteri_sifre = musteri_sifre;
    }

    public String getMusteri_adi_soyadi() {
        return musteri_adi_soyadi;
    }

    public void setMusteri_adi_soyadi(String musteri_adi_soyadi) {
        this.musteri_adi_soyadi = musteri_adi_soyadi;
    }

    public Kampanya getKampanya() {
        if (this.kampanya == null) {
            this.kampanya = new Kampanya();
        }
        return kampanya;
    }

    public void setKampanya(Kampanya kampanya) {
        this.kampanya = kampanya;
    }

    public Grup getGrup() {
        return grup;
    }

    public void setGrup(Grup grup) {
        this.grup = grup;
    }

    public List<Tur> getTurMusteriList() {
        return turMusteriList;
    }

    public void setTurMusteriList(List<Tur> turMusteriList) {
        this.turMusteriList = turMusteriList;
    }

    @Override
    public String toString() {
        return "Musteri{" + "musteri_id=" + musteri_id + ", musteri_adi_soyadi=" + musteri_adi_soyadi + ", kampanya=" + kampanya + ", grup=" + grup + ", turMusteriList=" + turMusteriList + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.musteri_id);
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
        final Musteri other = (Musteri) obj;
        if (!Objects.equals(this.musteri_id, other.musteri_id)) {
            return false;
        }
        return true;
    }

}
