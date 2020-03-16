/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AcenteDAO;
import dao.DosyaDAO;
import dao.TurDAO;
import entity.Acente;
import entity.Dosya;
import entity.Tur;
import java.io.Serializable;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.validation.ValidationException;

@Named
@SessionScoped
public class TurController implements Serializable {

    private Tur tur;
    private TurDAO turDAO;
    private List<Tur> turList;
    private List<Acente> acenteList;
    private AcenteDAO acenteDao;
    private DosyaDAO dosyaDao;
    private List<Dosya> dosyaList;
    private String AramaTerimi;

    private Date tarih = new Date();

    private List<String> turTuruList;

    private int page = 1;
    private int pageSize = 2;
    private int pageCount;

    public void next() {
        if (this.getPageCount() != 0) {
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
        this.pageCount = (int) Math.ceil(this.getTurDAO().count(this.AramaTerimi) / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void ara(){        
        this.setPage(1);
    }
    
    public void  aramaTemizle() {
        this.AramaTerimi = null;   
    }
    
    @PostConstruct
    public void listele() {
        turTuruList = Arrays.asList("Yurtiçi", "Yurtdışı");
    }

    public void create() {
        this.getTurDAO().create(this.tur);
        clearForm();
    }

    public void delete() {
        this.getTurDAO().delete(this.tur);
        if (this.getPageCount() < this.getPage()) {
            this.page--;
        }
        clearForm();
    }

    public void deleteConfirm(Tur tur) {
        this.tur = tur;
    }

    public void updateForm(Tur tur) {
        this.tur = tur;
    }

    public void clearForm() {
        this.turDAO = new TurDAO();
        this.tur = new Tur();
    }

    public void update() {
        this.getTurDAO().update(this.tur);
        clearForm();
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public DosyaDAO getDosyaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDAO();
        }
        return dosyaDao;
    }

    public List<Dosya> getDosyaList() {
        this.dosyaList = this.getDosyaDao().findAll(1, getDosyaDao().count());
        return dosyaList;
    }

    public void setDosyaList(List<Dosya> dosyaList) {
        this.dosyaList = dosyaList;
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

    public AcenteDAO getAcenteDao() {
        if (this.acenteDao == null) {
            this.acenteDao = new AcenteDAO();
        }
        return acenteDao;
    }

    public void setAcenteDao(AcenteDAO acenteDao) {
        this.acenteDao = acenteDao;
    }

    public List<Acente> getAcenteList() {
        this.acenteList = this.getAcenteDao().findAll(1, acenteDao.count());
        return acenteList;
    }

    public void setAcenteList(List<Acente> acenteList) {
        this.acenteList = acenteList;
    }

    public Tur getTur() {
        if (this.tur == null) {
            this.tur = new Tur();
        }
        return tur;
    }

    public void setTur(Tur tur) {
        this.tur = tur;
    }

    public List<Tur> getTurList() {
        this.turList = this.getTurDAO().findAll(page, pageSize,AramaTerimi);
        return turList;
    }

    public void setTurList(List<Tur> turList) {
        this.turList = turList;
    }

    public List<String> getTurTuruList() {
        return turTuruList;
    }

    public String getAramaTerimi() {
        return AramaTerimi;
    }

    public void setAramaTerimi(String AramaTerimi) {
        this.AramaTerimi = AramaTerimi;
    }
    

    public void validateDuzen(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidationException {
        String value = (String) arg2;
        String duzen = "([a-zA-ZıĞğÜüÖöÇçŞş ]*)";
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
        } else if (value.length() < 3 || value.length() > 15) {
            FacesMessage message = new FacesMessage("Lütfen 3 ile 15 karakter arasi giriniz.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
