/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.IletisimDAO;
import dao.MusteriDAO;
import entity.Iletisim;
import entity.Musteri;
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

@Named
@SessionScoped
public class IletisimController implements Serializable {

    private Iletisim iletisim;
    private IletisimDAO iletisimDAO;
    private List<Iletisim> iletisimlist;
    private MusteriDAO musteriDAO;
    private List<Musteri> musteriList;

    private int page = 1;
    private int pageSize = 2;
    private int pageCount;

    @Inject
    private MusteriController musteriController;
    
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
        this.pageCount = (int) Math.ceil(this.getIletisimDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    

    public void create() {
        this.getIletisimDAO().create(this.iletisim);

        clearForm();
    }

    public void delete() {
        this.getIletisimDAO().delete(this.iletisim);
        if (this.page > this.getPageCount()) {
            this.page--;
        }
        clearForm();
    }

    public void deleteConfirm(Iletisim iletisim) {
        this.iletisim = iletisim;
    }

    public void updateForm(Iletisim iletisim) {
        this.iletisim = iletisim;
    }

    public void clearForm() {
        this.iletisim = new Iletisim();
        this.iletisimDAO = new IletisimDAO();
    }

    public void update() {
        this.getIletisimDAO().update(this.iletisim);

        clearForm();
    }

    public IletisimController() {
    }

    public List<Iletisim> getIletisimlist() {
        this.iletisimlist = this.getiletisimDAO().findAll(page, pageSize);
        return iletisimlist;
    }

    public void setIletisimlist(List<Iletisim> iletisimlist) {
        this.iletisimlist = iletisimlist;
    }

    public IletisimDAO getIletisimDAO() {
        if (this.iletisimDAO == null) {
            this.iletisimDAO = new IletisimDAO();
        }
        return iletisimDAO;
    }

    public void setIletisimDAO(IletisimDAO iletisimDAO) {
        this.iletisimDAO = iletisimDAO;
    }

    public MusteriDAO getMusteriDao() {
        if (this.musteriDAO == null) {
            this.musteriDAO = new MusteriDAO();
        }
        return musteriDAO;
    }

    public void setMusteriDao(MusteriDAO musteriDAO) {
        this.musteriDAO = musteriDAO;
    }

    public List<Musteri> getmusteriList() {
        this.musteriList = this.getMusteriDao().findAll(1, getMusteriDao().count(null),null);
        return musteriList;
    }

    public void setMusteriList(List<Musteri> musteriList) {
        this.musteriList = musteriList;
    }

    public MusteriController getMusteriController() {
        return musteriController;
    }

    public void setMusteriController(MusteriController musteriController) {
        this.musteriController = musteriController;
    }

    public Iletisim getIletisim() {
        if (this.iletisim == null) {
            this.iletisim = new Iletisim();
        }
        return iletisim;
    }

    public void setIletisim(Iletisim iletisim) {
        this.iletisim = iletisim;
    }

    public IletisimDAO getiletisimDAO() {
        if (this.iletisimDAO == null) {
            this.iletisimDAO = new IletisimDAO();
        }
        return iletisimDAO;
    }

    public void setiletisimDAO(IletisimDAO iletisimDAO) {
        this.iletisimDAO = iletisimDAO;
    }

    public void setIletisimList(List<Iletisim> iletisimlist) {//iletisimList
        this.iletisimlist = iletisimlist;
    }

    public void emailValidate(FacesContext arg0, UIComponent arg1, Object arg2) throws javax.validation.ValidationException {
        String value = (String) arg2;
        String duzen = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(duzen);
        Matcher matcher = pattern.matcher(value);
        Boolean duzenKontrol = matcher.matches();

        if (value.equals("")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "sumary", "Lütfen eksik alan bırakmayınız."));
        } else if (!duzenKontrol) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "sumary", "Formata uygun e-posta giriniz(ornek@gmail.com)"));
        } else if (value.length() < 3 || value.length() > 90) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "sumary", "Girilen 3 ile 15 karakter arasi olmasi gerekir"));
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
