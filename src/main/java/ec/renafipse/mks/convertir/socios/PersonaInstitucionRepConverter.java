package ec.renafipse.mks.convertir.socios;

import ec.renafipse.mks.modelo.socios.PersonaInstitucionRep;
import ec.renafipse.mks.negocio.socios.PersonaInstitucionRepFacade;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named
public class PersonaInstitucionRepConverter implements Converter {

    @Inject
    private PersonaInstitucionRepFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.socios.PersonaInstitucionRepPK getKey(String value) {
        ec.renafipse.mks.modelo.socios.PersonaInstitucionRepPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.socios.PersonaInstitucionRepPK();
        key.setCodigoPersona(Long.parseLong(values[0]));
        key.setCodigoPersonaRep(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.socios.PersonaInstitucionRepPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoPersona());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoPersonaRep());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof PersonaInstitucionRep) {
            PersonaInstitucionRep o = (PersonaInstitucionRep) object;
            return getStringKey(o.getPersonaInstitucionRepPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonaInstitucionRep.class.getName()});
            return null;
        }
    }

}
