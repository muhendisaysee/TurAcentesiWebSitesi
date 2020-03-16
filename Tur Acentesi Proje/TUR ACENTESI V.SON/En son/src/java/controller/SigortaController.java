/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AcenteDAO;
import dao.SigortaDAO;
import entity.Acente;
import entity.Sigorta;
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
public class SigortaController implements Serializable {

    private Sigorta sigorta;
    private SigortaDAO sigortaDAO;
    private List<Sigorta> sigortaList;
    private List<Acente> acenteList;
    private AcenteDAO acenteDAO;

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
        this.pageCount = (int) Math.ceil(this.getSigortaDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public void create() {
        this.getSigortaDAO().create(this.sigorta);
        clearForm();
    }

    public void update() {
        this.getSigortaDAO().update(this.sigorta);
        clearForm();
    }

    public void deleteConfirm(Sigorta sigorta) {
        this.sigorta = sigorta;
    }

    public void delete() {
        this.getSigortaDAO().delete(this.sigorta);
         if(this.getPageCount() < this.getPage()){
            this.page--;
        }
        clearForm();
    }

    public void updateForm(Sigorta sigorta) {
        this.sigorta = sigorta;
    }

    public void clearForm() {
        this.sigorta = new Sigorta();
        this.sigortaDAO = new SigortaDAO();
    }

    public SigortaController() {
    }
    
    public Sigorta getSigorta() {
        if (this.sigorta == null) {
            this.sigorta = new Sigorta();
        }
        return sigorta;
    }

    public void setSigorta(Sigorta sigorta) {
        this.sigorta = sigorta;
    }

    public SigortaDAO getSigortaDAO() {
        if (this.sigortaDAO == null) {
            this.sigortaDAO = new SigortaDAO();
        }
        return sigortaDAO;
    }

    public void setSigortaDAO(SigortaDAO sigortaDAO) {
        this.sigortaDAO = sigortaDAO;
    }

    public List<Sigorta> getSigortaList() {
        this.sigortaList = this.getSigortaDAO().findAll(page,pageSize);
        return sigortaList;
    }

    public void setSigortaList(List<Sigorta> sigortaList) {
        this.sigortaList = sigortaList;
    }

    public List<Acente> getAcenteList() {
        this.acenteList = this.getAcenteDAO().findAll(1,getAcenteDAO().count());
        return acenteList;
    }

    public void setAcenteList(List<Acente> acenteList) {
        this.acenteList = acenteList;
    }

    public AcenteDAO getAcenteDAO() {
        if (this.acenteDAO == null) {
            this.acenteDAO = new AcenteDAO();
        }
        return acenteDAO;
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

    public void validateSigortaSure(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidationException {
        String value = String.valueOf(arg2);
        String duzen = "([0-9]*)";
        Pattern pattern = Pattern.compile(duzen);
        Matcher matcher = pattern.matcher(value);
        Boolean duzenKontrol = matcher.matches();

        if (!duzenKontrol || 0 >= (Integer) arg2 || (Integer) arg2 > 30) {
            FacesMessage message = new FacesMessage("Lütfen 1-30 arasi bir sayi giriniz");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

    }
}
