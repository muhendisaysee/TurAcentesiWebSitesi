/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.GrupDAO;
import entity.Grup;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="grupConverter")
public class GrupConverter implements Converter {

    private GrupDAO grupDAO;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
     return this.getGrupDAO().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    
        Grup grup = (Grup) value;
        return grup.getGrup_id().toString();
        
    }

    public GrupDAO getGrupDAO() {
        if(this.grupDAO == null)
            this.grupDAO = new GrupDAO();
        return grupDAO;
    }
    
    
    
    
}
