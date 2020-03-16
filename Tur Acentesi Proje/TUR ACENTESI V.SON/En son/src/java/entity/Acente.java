/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;


public class Acente {

    private Long acente_id;
    private String acente_adi;
    private String sube_adi;

    public Acente() {
    }

    public Acente(Long acente_id, String acente_adi, String sube_adi) {
        this.acente_id = acente_id;
        this.acente_adi = acente_adi;
        this.sube_adi = sube_adi;
    }

    public Long getAcente_id() {
        return acente_id;
    }

    public void setAcente_id(Long acente_id) {
        this.acente_id = acente_id;
    }

    public String getAcente_adi() {
        return acente_adi;
    }

    public void setAcente_adi(String acente_adi) {
        this.acente_adi = acente_adi;
    }

    public String getSube_adi() {
        return sube_adi;
    }

    public void setSube_adi(String sube_adi) {
        this.sube_adi = sube_adi;
    }

    @Override
    public String toString() {
        return "Acente{" + "acente_id=" + acente_id + ", acente_adi=" + acente_adi + ", sube_adi=" + sube_adi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.acente_id);
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
        final Acente other = (Acente) obj;
        if (!Objects.equals(this.acente_id, other.acente_id)) {
            return false;
        }
        return true;
    }
}
