/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
 
public class Iletisim {

    private Long iletisim_id;
    private String tel;
    private String eposta;
    private Musteri musteri;

    public Iletisim(Long iletisim_id, String tel, String eposta, Musteri musteri) {
        this.iletisim_id = iletisim_id;
        this.tel = tel;
        this.eposta = eposta;
        this.musteri = musteri;
    }

    public Iletisim() {
    }

    public Long getIletisim_id() {
        return iletisim_id;
    }

    public void setIletisim_id(Long iletisim_id) {
        this.iletisim_id = iletisim_id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public Musteri getMusteri() {
        if(this.musteri == null) {
            this.musteri = new Musteri();
        }
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    @Override
    public String toString() {
        return "Iletisim{" + "iletisim_id=" + iletisim_id + ", tel=" + tel + ", eposta=" + eposta + ", musteri=" + musteri + '}';
    }

}
