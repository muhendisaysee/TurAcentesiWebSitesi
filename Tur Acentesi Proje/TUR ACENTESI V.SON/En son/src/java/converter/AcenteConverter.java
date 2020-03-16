/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.AcenteDAO;
import entity.Acente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "acenteConverter")
public class AcenteConverter implements Converter {

    private AcenteDAO acenteDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getAcenteDAO().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Acente acente = (Acente) value;
        return acente.getAcente_id().toString();
    }

    public AcenteDAO getAcenteDAO() {
        if (this.acenteDAO == null) {
            this.acenteDAO = new AcenteDAO();
        }
        return acenteDAO;
    }

}
