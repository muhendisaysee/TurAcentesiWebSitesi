/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GrupDAO;
import dao.KampanyaDAO;
import dao.MusteriDAO;
import dao.TurDAO;
import entity.Kampanya;
import entity.Musteri;
import entity.Tur;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

@Named
@SessionScoped
public class MusteriController implements Serializable {

    private Musteri musteri;
    private MusteriDAO musteriDAO;
    private List<Musteri> musteriList;
    private List<Kampanya> kampanyaList;
    private List<Tur> turList;
    private KampanyaDAO kampanyaDAO;
    private GrupDAO grupDAO;
    private TurDAO turDAO;
    private String aramaTerimi;

    private int page = 1;
    private int pageSize = 2;
    private int pageCount;

    public void next() {
        if (getPageCount() != 0) {

            if (this.page == this.getPageCount()) {
                this.page = 1;
            } else {
                this.page++;
            }
        }
    }

    public void previous() {
        if (this.page > 0) {
            if (this.page == 1) {
                this.page = this.getPageCount();
            } else {
                this.page--;
            }
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getMusteriDAO().count(this.aramaTerimi) / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getAramaTerimi() {
        return aramaTerimi;
    }

    public void setAramaTerimi(String aramaTerimi) {
        this.aramaTerimi = aramaTerimi;
    }

    @Inject
    private TurController turController;

    public void create() {
  
        
        this.getMusteriDAO().create(this.musteri);
        clearForm();
    }

    public void delete() {
        this.getMusteriDAO().delete(musteri);
        if (this.page > this.getPageCount()) {
            this.page--;
        }
        clearForm();

    }

    public void update() {
        this.getMusteriDAO().update(this.musteri);
        clearForm();

    }

    public void deleteConfirm(Musteri musteri) {
        this.musteri = musteri;
    }

    public void updateForm(Musteri musteri) {
        this.musteri = musteri;
    }

    public void clearForm() {
        this.musteri = new Musteri();
    }

    public void ara() {

        this.setPage(1);
    }

    public void aramaTemizle() {
        this.aramaTerimi = null;
    }

    public MusteriController() {
    }

    public TurController getTurController() {
        if (this.turController == null) {
            this.turController = new TurController();
        }
        return turController;
    }

    public void setTurController(TurController turController) {
        this.turController = turController;
    }

    public List<Kampanya> getKampanyaList() {
        this.kampanyaList = this.getKampanyaDAO().findAll(1, getKampanyaDAO().count());
        return kampanyaList;
    }

    public void setKampanyaList(List<Kampanya> kampanyaList) {
        this.kampanyaList = kampanyaList;
    }

    public KampanyaDAO getKampanyaDAO() {
        if (this.kampanyaDAO == null) {
            this.kampanyaDAO = new KampanyaDAO();
        }
        return kampanyaDAO;
    }

    public void setKampanyaDAO(KampanyaDAO kampanyaDAO) {
        this.kampanyaDAO = kampanyaDAO;
    }

    public GrupDAO getGrupDAO() {
        if (this.grupDAO == null) {
            this.grupDAO = new GrupDAO();
        }
        return grupDAO;
    }

    public void setGrupDAO(GrupDAO grupDAO) {
        this.grupDAO = grupDAO;
    }

    public Musteri getMusteri() {
        if (this.musteri == null) {
            this.musteri = new Musteri();
        }
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
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

    public List<Musteri> getMusteriList() {
        this.musteriList = this.getMusteriDAO().findAll(page, pageSize,aramaTerimi);
        return musteriList;
    }

    public void setMusteriList(List<Musteri> musteriList) {
        this.musteriList = musteriList;
    }

    public List<Tur> getTurList() {
        this.turList = this.getTurDAO().findAll(1, getTurDAO().count(null), null);
        return turList;
    }

    public TurDAO getTurDAO() {
        if (this.turDAO == null) {
            this.turDAO = new TurDAO();
        }
        return turDAO;
    }

    public void setTurDAO(TurDAO turDAO) {
        this.turDAO = turDAO;
    }

    public void setTurList(List<Tur> turList) {
        this.turList = turList;
    }

    public void validateSecim(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidationException {
        List<String> s = (List<String>) arg2;
        if (s.size() == 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "sumary", "Lütfen en az bir seçim yapınız."));
        }
    }

    public void validateDuzen(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidationException {
        String value = (String) arg2;
        String duzen = "([a-zA-ZıİĞğÜüÖöÇçŞş ]*)";
        Pattern pattern = Pattern.compile(duzen);
        Matcher matcher = pattern.matcher(value);
        Boolean duzenKontrol = matcher.matches();

        if (value.equals("")) {
            FacesMessage message = new FacesMessage("Lütfen eksik alan bırakmayınız.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else if (!duzenKontrol) {
            FacesMessage message = new FacesMessage("Lütfen rakam girmeyiniz");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else if (value.length() < 3 || value.length() > 40) {
            FacesMessage message = new FacesMessage("Lütfen 3 ile 40 karakter arasi giriniz.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
    
     public void validateSifre(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidationException {
        String value = (String) arg2;
        String duzen = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
        Pattern pattern = Pattern.compile(duzen);
        Matcher matcher = pattern.matcher(value);
        Boolean duzenKontrol = matcher.matches();

        if (value.equals("")) {
            FacesMessage message = new FacesMessage("Lütfen eksik alan bırakmayınız.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else if (!duzenKontrol) {
            FacesMessage message = new FacesMessage("En az bir büyük bir küçük harf ve bir sayı giriniz!");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else if (value.length() < 3 || value.length() > 40) {
            FacesMessage message = new FacesMessage("Lütfen 3 ile 40 karakter arasi giriniz.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}
