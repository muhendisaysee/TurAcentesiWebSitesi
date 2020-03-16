/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.TurDAO;
import entity.Tur;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "turConverter")
public class TurConverter implements Converter {

    TurDAO turDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getTurDAO().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Tur tur = (Tur) value;
        return tur.getTur_id().toString();
    }

    public TurDAO getTurDAO() {
        if (this.turDAO == null) {
            this.turDAO = new TurDAO();
        }
        return turDAO;
    }

}
