package entity;

import java.util.Objects;

public class Bolum {

    private Long bolum_id;
    private String bolum_adi;

    public Bolum() {
    }

    public Bolum(Long bolum_id, String bolum_adi) {
        this.bolum_id = bolum_id;
        this.bolum_adi = bolum_adi;
    }

    public Long getBolum_id() {
        return bolum_id;
    }

    public void setBolum_id(Long bolum_id) {
        this.bolum_id = bolum_id;
    }

    public String getBolum_adi() {
        return bolum_adi;
    }

    public void setBolum_adi(String bolum_adi) {
        this.bolum_adi = bolum_adi;
    }

    @Override
    public String toString() {
        return "Bolum{" + "bolum_id=" + bolum_id + ", bolum_adi=" + bolum_adi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.bolum_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bolum other = (Bolum) obj;
        if (!Objects.equals(this.bolum_id, other.bolum_id)) {
            return false;
        }
        return true;
    }

    
    
}
