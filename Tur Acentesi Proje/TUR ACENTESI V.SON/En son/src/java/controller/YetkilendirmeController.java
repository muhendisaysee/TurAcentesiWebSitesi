/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GrupDAO;
import dao.YetkilendirmeDAO;
import entity.Grup;
import entity.Personel;
import entity.Yetkilendirme;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import javax.inject.Named;
import javax.validation.ValidationException;
import util.LoginController;

@Named
@SessionScoped
public class YetkilendirmeController implements Serializable {

    private Yetkilendirme yetkilendirme;
    private YetkilendirmeDAO yetkilendirmeDao;
    private List<Yetkilendirme> yetkilendirmeList;
    private List<String> yetkilendirmeAdlari;
    private GrupDAO grupDao;
    private List<Grup> grupList;
    private int page = 1;
    private int pageSize = 2;
    private int pageCount;
    
    private Boolean createKontrol;
    private Boolean updateKontrol;
    private Boolean deleteKontrol;
    private Boolean readKontrol;
    private Boolean accessKontrol;
    private Personel personel;

    @Inject
    LoginController loginController;

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
        this.pageCount = (int) Math.ceil(this.getYetkilendirmeDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void create() {
        this.getYetkilendirmeDao().create(this.yetkilendirme);

        clearForm();
    }

    public void update() {
        this.getYetkilendirmeDao().update(this.yetkilendirme);
        clearForm();
    }

    public void deleteConfirm(Yetkilendirme yetkilendirme) {
        this.yetkilendirme = yetkilendirme;
    }

    public void delete() {

        this.getYetkilendirmeDao().delete(this.yetkilendirme);
        if (this.getPageCount() < this.getPage()) {
            this.page--;
        }
        clearForm();

    }

    public void clearForm() {
        this.yetkilendirme = new Yetkilendirme();
        this.yetkilendirmeDao = new YetkilendirmeDAO();
    }

    public void updateForm(Yetkilendirme yetkilendirme) {
        this.yetkilendirme = yetkilendirme;
    }

    public boolean SuperAdmin(Yetkilendirme yetkilendirme) { ////delete ve update işlemkerine super admin ise izin verilmemesi.
        if (yetkilendirme.getGrup().getGrup_adi().equals("Super Admin ")) {
            return false;
        } else {
            return true;
        }
    }


    public YetkilendirmeController() {
        this.yetkilendirmeAdlari = new ArrayList<>();
        this.yetkilendirmeAdlari.add("Create");
        this.yetkilendirmeAdlari.add("Update");
        this.yetkilendirmeAdlari.add("Delete");
        this.yetkilendirmeAdlari.add("Read");
        this.yetkilendirmeAdlari.add("Access");
    }

    public void yetkilendir() {

        this.createKontrol = false;
        this.updateKontrol = false;
        this.deleteKontrol = false;
        this.readKontrol = false;
        this.accessKontrol = false;

        for (Yetkilendirme y : this.getYetkilendirmeDao().findAll(1, getYetkilendirmeDao().count())) {
            if (y.getGrup().getGrup_adi().equals(this.getLoginController().getPersonel().getGrup().getGrup_adi())) {
                if (y.getY_bolum_adi().equals("Create")) {
                    this.createKontrol = true;
                } else if (y.getY_bolum_adi().equals("Update")) {
                    this.updateKontrol = true;
                } else if (y.getY_bolum_adi().equals("Delete")) {
                    this.deleteKontrol = true;
                } else if (y.getY_bolum_adi().equals("Read")) {
                    this.readKontrol = true;
                } else if (y.getY_bolum_adi().equals("Access")) {
                    this.accessKontrol = true;
                }
            }
        }
    }

    public Yetkilendirme getYetkilendirme() {
        if (this.yetkilendirme == null) {
            this.yetkilendirme = new Yetkilendirme();
        }
        return yetkilendirme;
    }

    public void setYetkilendirme(Yetkilendirme yetkilendirme) {
        this.yetkilendirme = yetkilendirme;
    }

    public YetkilendirmeDAO getYetkilendirmeDao() {
        if (this.yetkilendirmeDao == null) {
            this.yetkilendirmeDao = new YetkilendirmeDAO();
        }
        return yetkilendirmeDao;
    }

    public void setYetkilendirmeDao(YetkilendirmeDAO yetkilendirmeDao) {
        this.yetkilendirmeDao = yetkilendirmeDao;
    }

    public List<Yetkilendirme> getYetkilendirmeList() {
        this.yetkilendirmeList = this.getYetkilendirmeDao().findAll(page, pageSize);
        return yetkilendirmeList;
    }

    public void setYetkilendirmeList(List<Yetkilendirme> yetkilendirmeList) {
        this.yetkilendirmeList = yetkilendirmeList;
    }

    public GrupDAO getGrupDao() {
        if (this.grupDao == null) {
            this.grupDao = new GrupDAO();
        }
        return grupDao;
    }

    public List<Grup> getGrupList() {
        this.grupList = this.getGrupDao().findAll(1, getGrupDao().count());
        this.grupList.remove(this.grupList.get(0));
        this.grupList.remove(this.grupList.get(0));
        return grupList;
    }

    public void setGrupList(List<Grup> grupList) {
        this.grupList = grupList;
    }

    public LoginController getLoginController() {
        if (this.loginController == null) {
            this.loginController = new LoginController();
        }
        return loginController;
    }

    public List<String> getYetkilendirmeAdlari() {
        return yetkilendirmeAdlari;
    }

    public void setYetkilendirmeAdlari(List<String> yetkilendirmeAdlari) {
        this.yetkilendirmeAdlari = yetkilendirmeAdlari;
    }

    public Boolean getCreateKontrol() {
        yetkilendir();
        return createKontrol;
    }

    public Boolean getUpdateKontrol() {
        yetkilendir();
        return updateKontrol;
    }

    public Boolean getDeleteKontrol() {
        yetkilendir();
        return deleteKontrol;
    }

    public Boolean getReadKontrol() {
        yetkilendir();
        return readKontrol;
    }

    public Boolean getAccessKontrol() {
        yetkilendir();
        return accessKontrol;
    }

}
