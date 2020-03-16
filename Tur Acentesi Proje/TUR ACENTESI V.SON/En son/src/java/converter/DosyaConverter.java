/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.DosyaDAO;
import entity.Dosya;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="dosyaConverter")
public class DosyaConverter implements Converter{

    private DosyaDAO dosyaDAO;
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getDosyaDAO().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        Dosya dosya = (Dosya) value;
        
        return dosya.getDosya_id().toString();
    }

    public DosyaDAO getDosyaDAO() {
        if(this.dosyaDAO == null) 
            this.dosyaDAO = new DosyaDAO();
        return dosyaDAO;
    }
    
    
    
}
