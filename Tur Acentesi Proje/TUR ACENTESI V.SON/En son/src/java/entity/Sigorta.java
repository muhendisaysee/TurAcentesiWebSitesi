/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
 
public class Sigorta {

    private Long sigorta_id;
    private String sigorta_adi;
    private int sigorta_suresi;
    private Acente acente;

    public Sigorta() {
    }

    public Sigorta(Long sigorta_id, String sigorta_adi, int sigorta_suresi, Acente acente) {
        this.sigorta_id = sigorta_id;
        this.sigorta_adi = sigorta_adi;
        this.sigorta_suresi = sigorta_suresi;
        this.acente = acente;
    }

    public Long getSigorta_id() {
        return sigorta_id;
    }

    public void setSigorta_id(Long sigorta_id) {
        this.sigorta_id = sigorta_id;
    }

    public String getSigorta_adi() {
        return sigorta_adi;
    }

    public void setSigorta_adi(String sigorta_adi) {
        this.sigorta_adi = sigorta_adi;
    }

    public int getSigorta_suresi() {
        return sigorta_suresi;
    }

    public void setSigorta_suresi(int sigorta_suresi) {
        this.sigorta_suresi = sigorta_suresi;
    }

    public Acente getAcente() {
        return acente;
    }

    public void setAcente(Acente acente) {
        this.acente = acente;
    }

    @Override
    public String toString() {
        return "sigorta{" + "sigorta_id=" + sigorta_id + ", sigorta_adi=" + sigorta_adi + ", sigorta_suresi=" + sigorta_suresi + ", acente=" + acente + '}';
    }

}
