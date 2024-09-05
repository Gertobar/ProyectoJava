package ec.renafipse.mks.convertir.creditos;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.creditos.TipoGarantiaCredito;
import ec.renafipse.mks.negocio.creditos.TipoGarantiaCreditoFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class TipoGarantiaCreditoConverter implements Converter {

    @EJB
    private TipoGarantiaCreditoFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.creditos.TipoGarantiaCreditoPK getKey(String value) {
        ec.renafipse.mks.modelo.creditos.TipoGarantiaCreditoPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.creditos.TipoGarantiaCreditoPK();
        key.setNumeroCredito(Long.parseLong(values[0]));
        key.setCodigoIfip(Long.parseLong(values[1]));
        key.setCodigoTipoGarantia(Long.parseLong(values[2]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.creditos.TipoGarantiaCreditoPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getNumeroCredito());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoIfip());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoTipoGarantia());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof TipoGarantiaCredito) {
            TipoGarantiaCredito o = (TipoGarantiaCredito) object;
            return getStringKey(o.getTipoGarantiaCreditoPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoGarantiaCredito.class.getName()});
            return null;
        }
    }

}
