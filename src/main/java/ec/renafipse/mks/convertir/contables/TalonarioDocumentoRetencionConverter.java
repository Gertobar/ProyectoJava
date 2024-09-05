package ec.renafipse.mks.convertir.contables;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetencion;
import ec.renafipse.mks.negocio.contables.TalonarioDocumentoRetencionFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class TalonarioDocumentoRetencionConverter implements Converter {

    @EJB
    private TalonarioDocumentoRetencionFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetencionPK getKey(String value) {
        ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetencionPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetencionPK();
        key.setCodigoIfip(Long.parseLong(values[0]));
        key.setCodigo(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetencionPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoIfip());
        sb.append(SEPARATOR);
        sb.append(value.getCodigo());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof TalonarioDocumentoRetencion) {
            TalonarioDocumentoRetencion o = (TalonarioDocumentoRetencion) object;
            return getStringKey(o.getTalonarioDocumentoRetencionPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TalonarioDocumentoRetencion.class.getName()});
            return null;
        }
    }

}
