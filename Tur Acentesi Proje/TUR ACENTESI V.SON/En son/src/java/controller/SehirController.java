/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AcenteDAO;
import dao.SehirDAO;
import entity.Acente;
import entity.Sehir;
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
public class SehirController implements Serializable {

    private Sehir sehir;
    private SehirDAO sehirDAO;
    private List<Sehir> sehirList;
    private AcenteDAO acenteDAO;
    private List<Acente> acenteList;

    private int page = 1;
    private int pageSize = 2;
    private int pageCount;

    public void next() {
        
        if(this.getPageCount() != 0)
            if (this.page == this.getPageCount()) {
                this.page = 1;
            } else {
                this.page++;
            }
       
    }

    public void previous() {
       if (0<page) {
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
        this.pageCount = (int) Math.ceil(this.getSehirDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    

    public void create() {

        this.getSehirDAO().create(this.sehir);
        
        clearForm();
    }

    public void update() {
        this.getSehirDAO().update(this.sehir);
       clearForm();
    }

    public void deleteConfirm(Sehir sehir) {
        this.sehir = sehir;
    }

    public void delete() {
        this.getSehirDAO().delete(this.sehir);
          if(this.getPageCount() < this.getPage()){
            this.page--;
        }
        clearForm();
    }

    public void updateForm(Sehir sehir) {
        this.sehir = sehir;
    }

    public void clearForm() {
        this.sehir = new Sehir();
        this.acenteDAO = new AcenteDAO();
    }

    public SehirController() {
    }

    public Sehir getSehir() {
        if (this.sehir == null) {
            this.sehir = new Sehir();
        }
        return sehir;
    }

    public void setSehir(Sehir sehir) {
        this.sehir = sehir;
    }

    public SehirDAO getSehirDAO() {
        if (this.sehirDAO == null) {
            this.sehirDAO = new SehirDAO();
        }
        return sehirDAO;
    }

    public void setSehirDAO(SehirDAO sehirDAO) {
        this.sehirDAO = sehirDAO;
    }

    public List<Sehir> getSehirList() {
        this.sehirList = this.getSehirDAO().findAll(page, pageSize);
        return sehirList;
    }

    public void setSehirList(List<Sehir> sehirList) {
        this.sehirList = sehirList;
    }

    public AcenteDAO getAcenteDAO() {
        if (this.acenteDAO == null) {
            this.acenteDAO = new AcenteDAO();
        }
        return acenteDAO;
    }

    public void setAcenteDAO(AcenteDAO acenteDAO) {

        this.acenteDAO = acenteDAO;
    }

    public List<Acente> getAcenteList() {
        this.acenteList = this.getAcenteDAO().findAll(1, getAcenteDAO().count());
        return acenteList;
    }

    public void setAcenteList(List<Acente> acenteList) {
        this.acenteList = acenteList;
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
            FacesMessage message = new FacesMessage("Lütfen geçersiz karakter girmeyiniz");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else if (value.length() < 3 || value.length() > 40) {
            FacesMessage message = new FacesMessage("Lütfen 3 ile 40 karakter arasi giriniz.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public void telValidate(FacesContext arg0, UIComponent arg1, Object arg2) throws javax.validation.ValidationException {
        String value = (String) arg2;
        String duzen = "^[+]?[0-9]{10,11}+$";
        Pattern pattern = Pattern.compile(duzen);
        Matcher matcher = pattern.matcher(value);
        Boolean duzenKontrol = matcher.matches();

        if (value.equals("")) {
            FacesMessage message = new FacesMessage("Lütfen eksik alan bırakmayınız.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else if (!duzenKontrol) {
            FacesMessage message = new FacesMessage("Formata uygun telefon numarası giriniz(5123456789)");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}
