package controller;

import dao.AcenteDAO;
import dao.BolumDAO;
import dao.GrupDAO;
import dao.PersonelDAO;
import dao.TurDAO;
import entity.Acente;
import entity.Bolum;
import entity.Grup;
import entity.Personel;
import entity.Tur;
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

public class PersonelController implements Serializable {

    private Personel personel;
    private PersonelDAO personelDAO;
    private List<Personel> personelList;
    private List<Acente> acenteList;
    private AcenteDAO acenteDAO;
    private List<Bolum> bolumList;
    private BolumDAO bolumDAO;
    private List<Grup> grupList;
    private GrupDAO grupDAO;
    private List<Tur> turList;
    private TurDAO turDAO;

    private int page = 1;
    private int pageSize = 2;
    private int pageCount;
    private String aramaTerimi;

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
        this.pageCount = (int) Math.ceil(this.getPersonelDAO().count(this.aramaTerimi) / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void ara() {

        this.setPage(1);
    }

    public void aramaTemizle() {
        this.aramaTerimi = null;
    }

    public void create() {
        int kontrol = 0;
        for (Personel person : this.getPersonelDAO().findAll(1, this.getPersonelDAO().count(null), null)) {
            if (this.personel.getPersonel_kullanici().equals(person.getPersonel_kullanici())) {
                kontrol = 1;

            }
        }
        if (kontrol == 0) {
            this.getPersonelDAO().create(this.personel);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kullanici adi bulunmaktadır lütfen farkli bir kullanici adi giriniz."));
        }

        clearForm();
    }

    public void delete() {
        this.getPersonelDAO().delete(this.personel);
        if (this.getPageCount() < this.getPage()) {
            this.page--;
        }
        clearForm();
    }

    public void update() {
        this.getPersonelDAO().update(personel);
        clearForm();
    }

    public void deleteConfirm(Personel personel) {
        this.personel = personel;

    }

    public void updateForm(Personel personel) {
        this.personel = personel;
    }

    public void clearForm() {
        this.personel = new Personel();
        this.personelDAO = new PersonelDAO();

    }

    public PersonelController() {
    }

    public Personel getPersonel() {
        if (this.personel == null) {
            this.personel = new Personel();
        }
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public String getAramaTerimi() {
        return aramaTerimi;
    }

    public void setAramaTerimi(String aramaTerimi) {
        this.aramaTerimi = aramaTerimi;
    }

    public PersonelDAO getPersonelDAO() {
        if (this.personelDAO == null) {
            this.personelDAO = new PersonelDAO();
        }
        return personelDAO;
    }

    public void setPersonelDAO(PersonelDAO personelDAO) {
        this.personelDAO = personelDAO;
    }

    public List<Personel> getPersonelList() {
        this.personelList = this.getPersonelDAO().findAll(page, pageSize, aramaTerimi);

        return personelList;
    }

    public void setPersonelList(List<Personel> personelList) {
        this.personelList = personelList;
    }

    public List<Acente> getAcenteList() {
        this.acenteList = this.getAcenteDAO().findAll(1, getAcenteDAO().count());
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

    public void setAcenteDAO(AcenteDAO acenteDAO) {
        this.acenteDAO = acenteDAO;
    }

    public List<Bolum> getBolumList() {
        this.bolumList = this.getBolumDAO().findAll(1, getBolumDAO().count());
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

    public List<Grup> getGrupList() {

        this.grupList = this.getGrupDAO().findAll(1, getGrupDAO().count());
        grupList.remove(1);
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

    public TurDAO getTurDAO() {
        if (this.turDAO == null) {
            this.turDAO = new TurDAO();
        }
        return turDAO;
    }

    public void setTurDAO(TurDAO turDAO) {
        this.turDAO = turDAO;
    }

    public List<Tur> getTurList() {
        this.turList = this.getTurDAO().findAll(1, getTurDAO().count(null), null);
        return turList;
    }

    public void setTurList(List<Tur> turList) {
        this.turList = turList;
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
        } else if (value.length() < 3 || value.length() > 40) {
            FacesMessage message = new FacesMessage("Lütfen 3 ile 40 karakter arasi giriniz.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public void validateSifre(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidationException {
        String value = (String) arg2;
        String duzen = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
        Pattern pattern = Pattern.compile(duzen);//Pattern:string duzendeki kuralı alıyor
        Matcher matcher = pattern.matcher(value);//matcher:gelen stringteki alfabe teyit 
        Boolean duzenKontrol = matcher.matches();

        if (value.equals("")) {
            FacesMessage message = new FacesMessage("Lütfen eksik alan bırakmayınız.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else if (!duzenKontrol) {
            FacesMessage message = new FacesMessage("En az bir büyük harf ve bir harf giriniz!");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else if (value.length() < 3 || value.length() > 40) {
            FacesMessage message = new FacesMessage("Lütfen 3 ile 40 karakter arasi giriniz.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public void telValidate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
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

    public void validateSecim(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidationException {
        List<String> s = (List<String>) arg2;
        if (s.size() == 0) {
            FacesMessage message = new FacesMessage("Lütfen en az bir seçim yapınız.");
            message.setSeverity(message.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}
