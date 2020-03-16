/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
 
public class Otel {

    private Long otel_id;
    private String otel_adi;
    private int otel_yildizi;
    private Acente acente;

    public Otel() {
    }

    public Otel(Long otel_id, String otel_adi, int otel_yildizi, Acente acente) {
        this.otel_id = otel_id;
        this.otel_adi = otel_adi;
        this.otel_yildizi = otel_yildizi;
        this.acente = acente;
    }

    public Long getOtel_id() {
        return otel_id;
    }

    public void setOtel_id(Long otel_id) {
        this.otel_id = otel_id;
    }

    public String getOtel_adi() {
        return otel_adi;
    }

    public void setOtel_adi(String otel_adi) {
        this.otel_adi = otel_adi;
    }

    public int getOtel_yildizi() {
        return otel_yildizi;
    }

    public void setOtel_yildizi(int otel_yildizi) {
        this.otel_yildizi = otel_yildizi;
    }

    public Acente getAcente() {
        return acente;
    }

    public void setAcente(Acente acente) {
        this.acente = acente;
    }

    @Override
    public String toString() {
        return "Otel{" + "otel_id=" + otel_id + ", otel_adi=" + otel_adi + ", otel_yildizi=" + otel_yildizi + ", acente=" + acente + '}';
    }

}
