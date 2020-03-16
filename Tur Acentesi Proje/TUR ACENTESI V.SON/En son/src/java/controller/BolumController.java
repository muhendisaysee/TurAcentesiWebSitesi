package controller;

import dao.BolumDAO;
import entity.Bolum;
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
public class BolumController implements Serializable {

    private Bolum bolum;
    private List<Bolum> bolumList;
    private BolumDAO bolumDAO;

    private int page = 1;
    private int pageSize = 2;
    private int pageCount;

    public void next() {
        if (this.pageCount != 0) {
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
        this.pageCount = (int) Math.ceil(this.getBolumDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void update() {
        this.getBolumDAO().update(this.bolum);
        clearForm();
    }

    public void updateForm(Bolum bolum) {
        this.bolum = bolum;
    }

    public void delete() {
        this.getBolumDAO().delete(this.bolum);
        if (this.page > this.getPageCount()) {
            this.page--;
        }
        clearForm();
    }

    public void deleteConfirm(Bolum bolum) {
        this.bolum = bolum;
    }

    public void create() {
        this.getBolumDAO().create(this.bolum);
        clearForm();
    }

    public void clearForm() {
        this.bolum = new Bolum();
        this.bolumDAO = new BolumDAO();
    }

    public BolumController() {
    }

    public BolumController(Bolum bolum, List<Bolum> bolumList, BolumDAO bolumDAO) {
        this.bolum = bolum;
        this.bolumList = bolumList;
        this.bolumDAO = bolumDAO;
    }

    public Bolum getBolum() {
        if (this.bolum == null) {
            this.bolum = new Bolum();
        }
        return bolum;
    }

    public void setAcente(Bolum bolum) {
        this.bolum = bolum;
    }

    public List<Bolum> getBolumList() {
        this.bolumList = this.getBolumDAO().findAll(page, pageSize);
        return bolumList;
    }

    public void setBolumList(List<Bolum> bolumList) {
        this.bolumList = bolumList;
    }

    public BolumDAO getBolumDAO() {
        if (this.bolumDAO == null) {
            this.bolumDAO = new BolumDAO();
        }
        return bolumDAO;
    }

    public void setBolumDAO(BolumDAO bolumDAO) {
        this.bolumDAO = bolumDAO;
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
        } else if (value.length() < 3 || value.length() > 25) {
            FacesMessage message = new FacesMessage("Lütfen 3 ile 25 karakter arasi giriniz.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
