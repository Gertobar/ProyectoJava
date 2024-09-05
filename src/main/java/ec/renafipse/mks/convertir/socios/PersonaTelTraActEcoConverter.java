package ec.renafipse.mks.convertir.socios;

import ec.renafipse.mks.modelo.socios.PersonaTelTraActEco;
import ec.renafipse.mks.negocio.socios.PersonaTelTraActEcoFacade;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named
public class PersonaTelTraActEcoConverter implements Converter {

    @Inject
    private PersonaTelTraActEcoFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.socios.PersonaTelTraActEcoPK getKey(String value) {
        ec.renafipse.mks.modelo.socios.PersonaTelTraActEcoPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.socios.PersonaTelTraActEcoPK();
        key.setCodigoPerTraActEco(Long.parseLong(values[0]));
        key.setNumero(values[1]);
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.socios.PersonaTelTraActEcoPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoPerTraActEco());
        sb.append(SEPARATOR);
        sb.append(value.getNumero());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof PersonaTelTraActEco) {
            PersonaTelTraActEco o = (PersonaTelTraActEco) object;
            return getStringKey(o.getPersonaTelTraActEcoPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonaTelTraActEco.class.getName()});
            return null;
        }
    }

}
