/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;
 
public class Grup {

    private Long grup_id;
    private String grup_adi;

    public Grup() {
    }

    public Grup(Long grup_id, String grup_adi) {
        this.grup_id = grup_id;
        this.grup_adi = grup_adi;
    }

    public Long getGrup_id() {
        return grup_id;
    }

    public void setGrup_id(Long grup_id) {
        this.grup_id = grup_id;
    }

    public String getGrup_adi() {
        return grup_adi;
    }

    public void setGrup_adi(String grup_adi) {
        this.grup_adi = grup_adi;
    }

    @Override
    public String toString() {
        return "Grup{" + "grup_id=" + grup_id + ", grup_adi=" + grup_adi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.grup_id);
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
        final Grup other = (Grup) obj;
        if (!Objects.equals(this.grup_id, other.grup_id)) {
            return false;
        }
        return true;
    }

}
