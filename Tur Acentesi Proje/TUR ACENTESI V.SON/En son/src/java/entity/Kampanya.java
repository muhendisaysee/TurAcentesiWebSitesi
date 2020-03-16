/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;
 
public class Kampanya {

    private Long kampanya_id;
    private String kampanya_turu;
    private String kampanya_detayi;
    private Acente acente;

    public Kampanya() {
    }

    public Kampanya(Long kampanya_id, String kampanya_turu, String kampanya_detayi, Acente acente) {
        this.kampanya_id = kampanya_id;
        this.kampanya_turu = kampanya_turu;
        this.kampanya_detayi = kampanya_detayi;
        this.acente = acente;
    }

    public Long getKampanya_id() {
        return kampanya_id;
    }

    public void setKampanya_id(Long kampanya_id) {
        this.kampanya_id = kampanya_id;
    }

    public String getKampanya_turu() {
        return kampanya_turu;
    }

    public void setKampanya_turu(String kampanya_turu) {
        this.kampanya_turu = kampanya_turu;
    }

    public String getKampanya_detayi() {
        return kampanya_detayi;
    }

    public void setKampanya_detayi(String kampanya_detayi) {
        this.kampanya_detayi = kampanya_detayi;
    }

    public Acente getAcente() {
        return acente;
    }

    public void setAcente(Acente acente) {
        this.acente = acente;
    }

    @Override
    public String toString() {
        return "Kampanya{" + "kampanya_id=" + kampanya_id + ", kampanya_turu=" + kampanya_turu + ", kampanya_detayi=" + kampanya_detayi + ", acente=" + acente + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.kampanya_id);
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
        final Kampanya other = (Kampanya) obj;
        if (!Objects.equals(this.kampanya_id, other.kampanya_id)) {
            return false;
        }
        return true;
    }

}
