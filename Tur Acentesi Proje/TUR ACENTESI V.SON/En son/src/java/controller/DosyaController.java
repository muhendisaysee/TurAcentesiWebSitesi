/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DosyaDAO;
import entity.Dosya;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.validation.ValidationException;

@Named
@SessionScoped
public class DosyaController implements Serializable {

    private Dosya dosya;
    private DosyaDAO dosyaDAO;
    private List<Dosya> dosyaList;
    private int page = 1;
    private int pageSize = 2;
    private int pageCount;

    private Part Doc;

    private final String yol = "C:\\Users\\Volkan\\Desktop\\Fotograflar\\";

    public void Upload() {

        try {

            InputStream giris = Doc.getInputStream();
            CopyOption cpy = StandardCopyOption.REPLACE_EXISTING;
            File file = new File(yol + Doc.getSubmittedFileName());
            Files.copy(giris, file.toPath(), cpy);

            dosya = this.getDosya();
            dosya.setDosya_yolu(file.getParent());
            dosya.setDosya_adi(file.getName());
            dosya.setDosya_tipi(Doc.getContentType());

            this.getdosyaDAO().create(dosya);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public DosyaDAO getDosyaDAO() {
        return dosyaDAO;
    }

    public void setDosyaDAO(DosyaDAO dosyaDAO) {
        this.dosyaDAO = dosyaDAO;
    }

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
        this.pageCount = (int) Math.ceil(this.getdosyaDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public DosyaController() {
    }

    public void create() {
        this.getdosyaDAO().create(this.dosya);
        clearForm();
    }

    public void delete() {
        this.getdosyaDAO().delete(this.dosya);
        if (this.page > this.getPageCount()) {
            this.page--;
        }
        clearForm();
    }

    public void deleteConfirm(Dosya dosya) {
        this.dosya = dosya;
    }

    public void clearForm() {
        this.dosya = new Dosya();
        this.dosyaDAO = new DosyaDAO();
    }

    public Dosya getDosya() {
        if (this.dosya == null) {
            this.dosya = new Dosya();
        }
        return dosya;
    }

    public void setDosya(Dosya dosya) {
        this.dosya = dosya;
    }

    public Part getDoc() {
        return Doc;
    }

    public void setDoc(Part Doc) {
        this.Doc = Doc;
    }

    public DosyaDAO getdosyaDAO() {
        if (this.dosyaDAO == null) {
            this.dosyaDAO = new DosyaDAO();
        }
        return dosyaDAO;
    }

    public List<Dosya> getDosyaList() {
        this.dosyaList = this.getdosyaDAO().findAll(page, pageSize);
        return dosyaList;
    }

    public String getYol() {
        return yol;
    }

    public void setDosyaList(List<Dosya> dosyaList) {
        this.dosyaList = dosyaList;
    }

}
