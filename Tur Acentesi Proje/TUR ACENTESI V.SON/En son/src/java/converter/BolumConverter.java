/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.BolumDAO;
import entity.Bolum;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "bolumConverter")
public class BolumConverter implements Converter {

    private BolumDAO bolumDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getBolumDAO().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        Bolum bolum = (Bolum) value;
        return bolum.getBolum_id().toString();

    }

    public BolumDAO getBolumDAO() {
        if (this.bolumDAO == null) {
            this.bolumDAO = new BolumDAO();
        }
        return bolumDAO;
    }

}
