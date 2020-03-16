/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AcenteDAO;
import dao.OtelDAO;
import entity.Acente;
import entity.Otel;
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
public class OtelController implements Serializable {

    private Otel otel;
    private OtelDAO otelDAO;
    private List<Otel> otelList;
    private AcenteDAO acenteDAO;
    private List<Acente> acenteList;
    private int page = 1;
    private int pageSize = 2;
    private int pageCount;

    public void next() {
        if(this.getPageCount()!=0){
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }
    }
    public void previous() {
        if(this.page >0){
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
        this.pageCount = (int) Math.ceil(this.getOtelDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    @Inject
    private AcenteController acenteController;

    public void create() {
        this.getOtelDAO().create(this.otel);
        clearForm();
    }

    public void update() {
        this.getOtelDAO().update(this.otel);
        clearForm();
    }

    public void deleteConfirm(Otel otel) {
        this.otel = otel;
    }

    public void delete() {
        this.getOtelDAO().delete(this.otel);
        if(this.getPageCount() < this.getPage()){
            this.page--;
        }
        clearForm();
    }

    public void updateForm(Otel otel) {
        this.otel = otel;
    }

    public void clearForm() {
        this.otel = new Otel();
        this.acenteDAO = new AcenteDAO();
    }

    public OtelController() {
    }

    public Otel getOtel() {
        if (this.otel == null) {
            this.otel = new Otel();
        }
        return otel;
    }

    public void setOtel(Otel otel) {
        this.otel = otel;
    }

    public OtelDAO getOtelDAO() {
        if (this.otelDAO == null) {
            this.otelDAO = new OtelDAO();
        }
        return otelDAO;
    }

    public void setOtelDAO(OtelDAO otelDAO) {
        this.otelDAO = otelDAO;
    }

    public List<Otel> getOtelList() {
        this.otelList = this.getOtelDAO().findAll(page,pageSize);
        return otelList;
    }

    public void setOtelList(List<Otel> otelList) {
        this.otelList = otelList;
    }

    public AcenteController getAcenteController() {
        return acenteController;
    }

    public void setAcenteController(AcenteController acenteController) {
        this.acenteController = acenteController;
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

    public void validateYildizKontrol(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidationException {
        String value = String.valueOf(arg2);
        String duzen = "([0-9]*)";
        Pattern pattern = Pattern.compile(duzen);
        Matcher matcher = pattern.matcher(value);
        Boolean duzenKontrol = matcher.matches();

        if (!duzenKontrol || value.length() != 1 || 0 >= (Integer) arg2 || (Integer) arg2 > 7) {
            FacesMessage message = new FacesMessage("Lütfen 1-7 arasi bir rakam giriniz");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

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
        } else if (value.length() < 3 || value.length() > 35) {
            FacesMessage message = new FacesMessage("Lütfen 3 ile 35 karakter arasi giriniz.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
