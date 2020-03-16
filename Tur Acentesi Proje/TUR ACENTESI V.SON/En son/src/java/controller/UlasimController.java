/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AcenteDAO;
import dao.UlasimDAO;
import entity.Acente;
import entity.Ulasim;
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
public class UlasimController implements Serializable {

    private Ulasim ulasim;
    private UlasimDAO ulasimDAO;
    private AcenteDAO AcenteDAO;
    private List<Acente> acenteList;
    private List<Ulasim> ulasimList;
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
        this.pageCount = (int) Math.ceil(this.getUlasimDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void create() {
        this.getUlasimDAO().create(this.ulasim);
        clearForm();
    }

    public void clearForm() {
        this.ulasimDAO = new UlasimDAO();
        this.ulasim = new Ulasim();
    }

    public void updateForm(Ulasim ulasim) {
        this.ulasim = ulasim;
    }

    public void update() {
        this.getUlasimDAO().update(this.ulasim);
        clearForm();
    }

    public void deleteForm(Ulasim ulasim) {
        this.ulasim = ulasim;
    }

    public void delete() {
        this.getUlasimDAO().delete(ulasim);
        if(this.getPageCount() < this.getPage()){
            this.page--;
        }
        clearForm();
    }

    public UlasimController() {
    }

    public Ulasim getUlasim() {
        if (this.ulasim == null) {
            this.ulasim = new Ulasim();
        }
        return ulasim;
    }

    public void setUlasim(Ulasim ulasim) {
        this.ulasim = ulasim;
    }

    public UlasimDAO getUlasimDAO() {
        if (this.ulasimDAO == null) {
            this.ulasimDAO = new UlasimDAO();
        }
        return ulasimDAO;
    }

    public void setUlasimDAO(UlasimDAO ulasimDAO) {
        this.ulasimDAO = ulasimDAO;
    }

    public AcenteDAO getAcenteDAO() {
        if (this.AcenteDAO == null) {
            this.AcenteDAO = new AcenteDAO();
        }
        return AcenteDAO;
    }

    public void setAcenteDAO(AcenteDAO AcenteDAO) {
        this.AcenteDAO = AcenteDAO;
    }

    public List<Acente> getAcenteList() {
        this.acenteList = this.getAcenteDAO().findAll(1, getAcenteDAO().count());
        return acenteList;
    }

    public void setAcenteList(List<Acente> acenteList) {
        this.acenteList = acenteList;
    }

    public List<Ulasim> getUlasimList() {
        this.ulasimList = this.getUlasimDAO().findAll(page, pageSize);
        return ulasimList;
    }

    public void setUlasimList(List<Ulasim> ulasimList) {
        this.ulasimList = ulasimList;
    }

    public void validateDuzen(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidationException {
        String value = (String) arg2;
        String duzen = "([a-zA-Zİ"
                + "ıĞğÜüÖöÇçŞş ]*)";
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
