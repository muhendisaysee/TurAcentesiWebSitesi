package util;

import controller.FrontendController;
import controller.YetkilendirmeController;
import dao.IletisimDAO;
import dao.PersonelDAO;
import entity.Iletisim;
import entity.Personel;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private PersonelDAO personelDAO;
    private List<Personel> personelList;
    private Personel personel;
    private IletisimDAO iletisimDAO;
    private List<Iletisim> iletisimList;
    private String kullanici_adi;
    private String sifre;

    @Inject
    FrontendController frontendController;

    public String login() {
        int control = 0;
        for (Personel personel2 : this.getPersonelList()) {

            if (this.kullanici_adi.equals(personel2.getPersonel_kullanici()) && this.sifre.equals(personel2.getPersonel_sifre())) {
                this.personel = personel2;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_personel", personel2);
                control = 1;

                return "/index?faces-redirect=true";
            }
        }

        for (Iletisim iletisim2 : this.getIletisimList()) {
            if (this.kullanici_adi.equals(iletisim2.getEposta())
                    && (this.sifre.equals(iletisim2.getMusteri().getMusteri_sifre()))) {
                this.getFrontendController().setIletisim(iletisim2);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_iletisim", iletisim2);
                control = 1;
                return "/Frontend/profil?faces-redirect=true";
            }
        }

        if (control == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hatalı kullanıcı adı veya şifre girdiniz."));
            return "/Frontend/login";
        }
        return "";
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

    public List<Iletisim> getIletisimList() {
        this.iletisimList = this.getIletisimDAO().findAll(1, getIletisimDAO().count());
        return iletisimList;
    }

    public void setIletisimList(List<Iletisim> iletisimList) {
        this.iletisimList = iletisimList;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
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
        this.personelList = this.getPersonelDAO().findAll(1, 4, null);
        return personelList;
    }

    public void setPersonelList(List<Personel> personelList) {
        this.personelList = personelList;
    }

    public FrontendController getFrontendController() {
        if (this.frontendController == null) {
            this.frontendController = new FrontendController();
        }
        return frontendController;
    }

}
