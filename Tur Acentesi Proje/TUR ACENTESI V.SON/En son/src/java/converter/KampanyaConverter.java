/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.KampanyaDAO;
import entity.Kampanya;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="kampanyaConverter")
public class KampanyaConverter implements Converter {
    
    private KampanyaDAO kampanyaDAO;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getKampanyaDAO().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Kampanya kampanya =(Kampanya) value;
        return kampanya.getKampanya_id().toString();
    }

    public KampanyaDAO getKampanyaDAO() {
        if(this.kampanyaDAO == null) {
            this.kampanyaDAO = new KampanyaDAO();
        }
        return kampanyaDAO;
    }
    
    
    
}
