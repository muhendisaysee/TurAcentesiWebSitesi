/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.IletisimDAO;
import dao.MusteriDAO;
import dao.SehirDAO;
import entity.Iletisim;
import entity.Sehir;
import entity.Tur;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Volkan
 */
@Named
@SessionScoped
public class FrontendController implements Serializable {

    private MusteriDAO musteriDAO;
    private Iletisim iletisim;
    private Iletisim kayitIletisim;
    private IletisimDAO iletisimDAO;
    private List<Sehir> sehirList;
    private SehirDAO sehirDAO;
    private Tur tur;

    public void update() {
        this.getIletisimDAO().update(iletisim);
        this.getMusteriDAO().update(iletisim.getMusteri());
    }

    public void satınAl() {
        this.iletisim.getMusteri().getTurMusteriList().add(this.tur);
        this.getMusteriDAO().update(iletisim.getMusteri());
        this.tur = null;
    }

    public void delete(Tur tur) {
        this.iletisim.getMusteri().getTurMusteriList().remove(tur);
        this.getMusteriDAO().deleteByTur(this.iletisim.getMusteri().getTurMusteriList(), iletisim.getMusteri().getMusteri_id());
    }

    public void turSil() {
        this.tur = null;
    }

    public void create() {
        int kontrol = 0;
        for (Iletisim iletisim : this.getIletisimDAO().findAll(1, this.getIletisimDAO().count())) {
            if (this.kayitIletisim.getEposta().equals(iletisim.getEposta())) {
                kontrol = 1;
            }
        }

        if (kontrol == 0) {
        this.getMusteriDAO().create(this.kayitIletisim.getMusteri());
        this.getIletisimDAO().create(this.kayitIletisim);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kayıt başarılı şekilde gerçekleştirildi."));
            this.kayitIletisim = null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("E-posta bulunmaktadır lütfen farkli bir eposta giriniz."));
        }
    }

    public String sayfaYonlendirmeSatınAl(Tur tur) {
        this.tur = tur;
        return "/Frontend/profil?faces-redirect=true";
    }

    public MusteriDAO getMusteriDAO() {
        if (this.musteriDAO == null) {
            this.musteriDAO = new MusteriDAO();
        }
        return musteriDAO;
    }

    public void setMusteriDAO(MusteriDAO musteriDAO) {
        this.musteriDAO = musteriDAO;
    }

    public Iletisim getIletisim() {
        return iletisim;
    }

    public void setIletisim(Iletisim iletisim) {
        this.iletisim = iletisim;
    }

    public IletisimDAO getIletisimDAO() {
        if (this.iletisimDAO == null) {
            this.iletisimDAO = new IletisimDAO();
        }
        return iletisimDAO;
    }

    public void setIletisimDAO(IletisimDAO iletisimDAO) {
        this.iletisimDAO = iletisimDAO;
    }

    public Tur getTur() {
        return tur;
    }

    public void setTur(Tur tur) {
        this.tur = tur;
    }

    public Iletisim getKayitIletisim() {
        if (this.kayitIletisim == null) {
            this.kayitIletisim = new Iletisim();
        }
        return kayitIletisim;
    }

    public void setKayitIletisim(Iletisim kayitIletisim) {
        this.kayitIletisim = kayitIletisim;
    }

    public List<Sehir> getSehirList() {
        this.sehirList = this.getSehirDAO().findAll(1, this.getSehirDAO().count());
        return sehirList;
    }

    public void setSehirList(List<Sehir> sehirList) {
        this.sehirList = sehirList;
    }

    public SehirDAO getSehirDAO() {
        if(this.sehirDAO == null) {
            this.sehirDAO = new SehirDAO();
        }
        return sehirDAO;
    }

    public void setSehirDAO(SehirDAO sehirDAO) {
        this.sehirDAO = sehirDAO;
    }
    
    
       
        
}
