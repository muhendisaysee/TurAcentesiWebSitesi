/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.MusteriDAO;
import entity.Musteri;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="musteriConverter")
public class MusteriConverter implements Converter{

    private MusteriDAO musteriDAO;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getMusteriDAO().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        Musteri musteri = (Musteri) value;
        
        return musteri.getMusteri_id().toString();
    }

    public MusteriDAO getMusteriDAO() {
        if(this.musteriDAO == null)
            this.musteriDAO = new MusteriDAO();
        return musteriDAO;
    }
    
    
    
}
