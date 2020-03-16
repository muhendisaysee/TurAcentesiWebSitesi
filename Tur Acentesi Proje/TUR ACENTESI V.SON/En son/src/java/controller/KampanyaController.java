/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AcenteDAO;
import dao.KampanyaDAO;
import entity.Acente;
import entity.Kampanya;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.validation.ValidationException;

@Named
@SessionScoped
public class KampanyaController implements Serializable {

    private Kampanya kampanya;
    private KampanyaDAO kampanyaDAO;
    private List<Kampanya> kampanyaList;
    private AcenteDAO acenteDAO;
    private List<Acente> acenteList;
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
        this.pageCount = (int) Math.ceil(this.getKampanyaDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void create() {
        this.getKampanyaDAO().create(this.kampanya);

        clearForm();
    }

    public void update() {
        this.getKampanyaDAO().update(this.kampanya);
        clearForm();
    }

    public void deleteConfirm(Kampanya kampanya) {
        this.kampanya = kampanya;
    }

    public void delete() {
        this.getKampanyaDAO().delete(this.kampanya);
        if (this.page > this.getPageCount()) {
            this.page--;
        }
        clearForm();
    }

    public void updateForm(Kampanya kampanya) {
        this.kampanya = kampanya;
    }

    public void clearForm() {
        this.kampanyaDAO = new KampanyaDAO();
        this.kampanya = new Kampanya();
    }

    public KampanyaController() {
    }

    public Kampanya getKampanya() {
        if (this.kampanya == null) {
            this.kampanya = new Kampanya();
        }
        return kampanya;
    }

    public void setKampanya(Kampanya kampanya) {
        this.kampanya = kampanya;
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

    public List<Kampanya> getKampanyaList() {
        this.kampanyaList = this.getKampanyaDAO().findAll(page, pageSize);
        return kampanyaList;
    }

    public void setKampanyaList(List<Kampanya> kampanyaList) {
        this.kampanyaList = kampanyaList;
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

        if (value.equals("")) {
            FacesMessage message = new FacesMessage("Lütfen eksik alan bırakmayınız.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else if (value.length() < 3 || value.length() > 30) {
            FacesMessage message = new FacesMessage("Lütfen 3 ile 30 karakter arasi giriniz.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
