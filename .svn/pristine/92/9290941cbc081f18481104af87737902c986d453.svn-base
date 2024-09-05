/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.convertir.cobranzas;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.ifips.ServicioFinancieroTipoCanal;
import ec.renafipse.mks.negocio.ifips.ServicioFinancieroTipoCanalFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author andres
 */
@Named
public class ServicioFinancieroTipoCanalConverter implements Converter {
    @EJB
    private ServicioFinancieroTipoCanalFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        ServicioFinancieroTipoCanal servicioFinancieroTipoCanal = this.ejbFacade.find(getKey(value));
        return servicioFinancieroTipoCanal;
    }

    java.lang.Long getKey(String value) {
        java.lang.Long key;
        key = Long.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Long value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof ServicioFinancieroTipoCanal) {
            ServicioFinancieroTipoCanal o = (ServicioFinancieroTipoCanal) object;
            return getStringKey(o.getCodigo());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ServicioFinancieroTipoCanal.class.getName()});
            return null;
        }
    }
}
