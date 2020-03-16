/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AcenteDAO;
import entity.Acente;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.validation.ValidationException;

@Named
@SessionScoped
public class AcenteController implements Serializable {

    private Acente acente;
    private AcenteDAO acenteDAO;
    private List<Acente> acenteList;

    private int page = 1;   //Hangi sayfada bulunduğumuzu tutan 
    private int pageSize = 2;   //Her sayfada gösterdiğimiz nesne sayısı
     //Kaç tane sayfamızın olduğunu tutan değişken, veri tabanındaki kayıt sayısının pageSize a bölünmesiyle elde edilen değer
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
        this.pageCount = (int) Math.ceil(this.getacenteDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public AcenteDAO getAcenteDAO() {
        return acenteDAO;
    }

    public void setAcenteDAO(AcenteDAO acenteDAO) {
        this.acenteDAO = acenteDAO;
    }

    public AcenteController() {
    }

    public AcenteController(Acente acente, AcenteDAO acenteDAO, List<Acente> acenteList) {
        this.acente = acente;
        this.acenteDAO = acenteDAO;
        this.acenteList = acenteList;
    }

    public void create() {
        this.getacenteDAO().create(this.acente);
        clearForm();
    }

    public void delete() {
        this.getacenteDAO().delete(this.acente);
        if (this.page > this.getPageCount()) {
            this.page--;
        }      
        clearForm();
    }

    public void deleteConfirm(Acente acente) {
        this.acente = acente;
    }

    public void updateForm(Acente acente) {
        this.acente = acente;
    }

    public void clearForm() {
        this.acente = new Acente();
        this.acenteDAO=new AcenteDAO();
    }

    public void update() {
        this.getacenteDAO().update(this.acente);
        this.acente = new Acente();
    }

    public Acente getAcente() {
        if (this.acente == null) {
            this.acente = new Acente();
        }
        return acente;
    }

    public void setAcente(Acente acente) {
        this.acente = acente;
    }

    public AcenteDAO getacenteDAO() {
        if (this.acenteDAO == null) {
            this.acenteDAO = new AcenteDAO();
        }
        return acenteDAO;
    }

    public void setacenteDAO(AcenteDAO acenteDAO) {
        this.acenteDAO = acenteDAO;
    }

    public List<Acente> getAcenteList() {
        this.acenteList = this.getacenteDAO().findAll(page, pageSize);
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
