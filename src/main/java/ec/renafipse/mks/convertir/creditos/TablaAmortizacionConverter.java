package ec.renafipse.mks.convertir.creditos;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacion;
import ec.renafipse.mks.negocio.creditos.TablaAmortizacionFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class TablaAmortizacionConverter implements Converter {

    @EJB
    private TablaAmortizacionFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.creditos.TablaAmortizacionPK getKey(String value) {
        ec.renafipse.mks.modelo.creditos.TablaAmortizacionPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.creditos.TablaAmortizacionPK();
        key.setNumeroCredito(Long.parseLong(values[0]));
        key.setCodigoIfip(Long.parseLong(values[1]));
        key.setCuota(Long.parseLong(values[2]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.creditos.TablaAmortizacionPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getNumeroCredito());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoIfip());
        sb.append(SEPARATOR);
        sb.append(value.getCuota());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof TablaAmortizacion) {
            TablaAmortizacion o = (TablaAmortizacion) object;
            return getStringKey(o.getTablaAmortizacionPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TablaAmortizacion.class.getName()});
            return null;
        }
    }

}
