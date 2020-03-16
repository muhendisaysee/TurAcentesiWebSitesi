package entity;

public class Yetkilendirme {

    private Long yetkilendirme_id;
    private String y_bolum_adi;
    private Grup grup;

    public Yetkilendirme(Long yetkilendirme_id, String y_bolum_adi, Grup grup) {
        this.yetkilendirme_id = yetkilendirme_id;
        this.y_bolum_adi = y_bolum_adi;
        this.grup = grup;
    }

    public Yetkilendirme() {
    }

    public String getY_bolum_adi() {
        return y_bolum_adi;
    }

    public void setY_bolum_adi(String y_bolum_adi) {
        this.y_bolum_adi = y_bolum_adi;
    }

    public Grup getGrup() {
        return grup;
    }

    public void setGrup(Grup grup) {
        this.grup = grup;
    }

    public Long getYetkilendirme_id() {
        return yetkilendirme_id;
    }

    public void setYetkilendirme_id(Long yetkilendirme_id) {
        this.yetkilendirme_id = yetkilendirme_id;
    }

    @Override
    public String toString() {
        return "Yetkilendirme{" + "yetkilendirme_id=" + yetkilendirme_id + ", y_bolum_adi=" + y_bolum_adi + ", grup=" + grup + '}';
    }

    
    
}
