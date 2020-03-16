/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GrupDAO;
import entity.Grup;
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
public class GrupController implements Serializable {

    private List<Grup> grupList;
    private GrupDAO grupDAO;
    private Grup grup;

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
        this.pageCount = (int) Math.ceil(this.getGrupDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public Boolean SuperAdminControl(Grup grup)
    {
        if (grup.getGrup_adi().equals("Super Admin ") || grup.getGrup_adi().equals("Ziyaretçi")) {
            return false;
        } else {
            return true;
        }
    }
    public void update() {
      
        this.getGrupDAO().update(this.grup);
        clearForm();
    }

    public void clearForm() {
        this.grupDAO = new GrupDAO();
        this.grup = new Grup();
    }

    public void updateForm(Grup grup) {
        this.grup = grup;
    }

    public void deleteForm(Grup grup) {
        this.grup = grup;
    }

    public void delete() {
        
        this.getGrupDAO().delete(this.grup);
         if (this.page > this.getPageCount()) {
            this.page--;
        }  
        clearForm();
    }

    public void create() {
        this.getGrupDAO().create(this.grup);
        clearForm();
    }

    public GrupController() {
    }

    public List<Grup> getGrupList() {
        grupList = getGrupDAO().findAll(page, pageSize);
        grup = new Grup();
        return grupList;
    }

    public void setGrupList(List<Grup> grupList) {
        this.grupList = grupList;
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

    public Grup getGrup() {
        if (this.grup == null) {
            this.grup = new Grup();
        }
        return grup;
    }

    public void setGrup(Grup grup) {
        this.grup = grup;
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
        } else if(value.equalsIgnoreCase("Super Admin") || value.equalsIgnoreCase("SuperAdmin")){
            FacesMessage message = new FacesMessage("Super admin eklenilemez.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }else if (!duzenKontrol) {
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
