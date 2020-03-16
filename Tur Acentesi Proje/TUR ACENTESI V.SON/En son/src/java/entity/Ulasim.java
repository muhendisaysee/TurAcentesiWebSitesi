/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Ulasim {

    private Long ulasim_id;
    private String ulasim_kalkis_yeri;
    private String ulasim_varis_yeri;
    private String ulasim_turu;
    private Acente acente; 

    public Ulasim() {
    }

    public Ulasim(Long ulasim_id, String ulasim_kalkis_yeri, String ulasim_varis_yeri, Acente acente) {
        this.ulasim_id = ulasim_id;
        this.ulasim_kalkis_yeri = ulasim_kalkis_yeri;
        this.ulasim_varis_yeri = ulasim_varis_yeri;
        this.acente = acente;
    }

    public Long getUlasim_id() {
        return ulasim_id;
    }

    public void setUlasim_id(Long ulasim_id) {
        this.ulasim_id = ulasim_id;
    }

    public String getUlasim_kalkis_yeri() {
        return ulasim_kalkis_yeri;
    }

    public void setUlasim_kalkis_yeri(String ulasim_kalkis_yeri) {
        this.ulasim_kalkis_yeri = ulasim_kalkis_yeri;
    }

    public String getUlasim_varis_yeri() {
        return ulasim_varis_yeri;
    }

    public void setUlasim_varis_yeri(String ulasim_varis_yeri) {
        this.ulasim_varis_yeri = ulasim_varis_yeri;
    }

    public Acente getAcente() {
        return acente;
    }

    public void setAcente(Acente acente) {
        this.acente = acente;
    }

    public String getUlasim_turu() {
        return ulasim_turu;
    }

    public void setUlasim_turu(String ulasim_turu) {
        this.ulasim_turu = ulasim_turu;
    }

    @Override
    public String toString() {
        return "Ulasim{" + "ulasim_id=" + ulasim_id + ", ulasim_kalkis_yeri=" + ulasim_kalkis_yeri + ", ulasim_varis_yeri=" + ulasim_varis_yeri + ", ulasim_turu=" + ulasim_turu + ", acente=" + acente + '}';
    }

}
