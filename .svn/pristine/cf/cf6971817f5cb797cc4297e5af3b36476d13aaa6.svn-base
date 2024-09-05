package ec.renafipse.mks.convertir.creditos;


import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.creditos.InformeTecnico;
import ec.renafipse.mks.modelo.creditos.InformeTecnicoPK;
import ec.renafipse.mks.negocio.creditos.InformeTecnicoFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class InformeTecnicoConverter implements Converter {

    @EJB
    private InformeTecnicoFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

   InformeTecnicoPK getKey(String value) {
       InformeTecnicoPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new InformeTecnicoPK();
        key.setNumeroSolicitud(Long.parseLong(values[0]));
        key.setCodigoIfip(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(InformeTecnicoPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getNumeroSolicitud());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoIfip());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof InformeTecnico) {
            InformeTecnico o = (InformeTecnico) object;
            return getStringKey(o.getInformeTecnicoPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), InformeTecnico.class.getName()});
            return null;
        }
    }

}
