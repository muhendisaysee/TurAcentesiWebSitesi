/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;
 
public class Dosya {

    private Long dosya_id;
    private String dosya_adi;
    private String dosya_yolu;
    private String dosya_tipi;

    public Dosya() {
    }

    public Dosya(Long dosya_id, String dosya_adi, String dosya_yolu, String dosya_tipi) {
        this.dosya_id = dosya_id;
        this.dosya_adi = dosya_adi;
        this.dosya_yolu = dosya_yolu;
        this.dosya_tipi = dosya_tipi;
    }

   

    public String getDosya_tipi() {
        return dosya_tipi;
    }

    public void setDosya_tipi(String dosya_tipi) {
        this.dosya_tipi = dosya_tipi;
    }

    
    
    public Long getDosya_id() {
        return dosya_id;
    }

    public void setDosya_id(Long dosya_id) {
        this.dosya_id = dosya_id;
    }

    public String getDosya_adi() {
        return dosya_adi;
    }

    public void setDosya_adi(String dosya_adi) {
        this.dosya_adi = dosya_adi;
    }

    public String getDosya_yolu() {
        return dosya_yolu;
    }

    public void setDosya_yolu(String dosya_yolu) {
        this.dosya_yolu = dosya_yolu;
    }

    @Override
    public String toString() {
        return "Dosya{" + "dosya_id=" + dosya_id + ", dosya_adi=" + dosya_adi + ", dosya_yolu=" + dosya_yolu + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.dosya_id);
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
        final Dosya other = (Dosya) obj;
        if (!Objects.equals(this.dosya_id, other.dosya_id)) {
            return false;
        }
        return true;
    }

}
