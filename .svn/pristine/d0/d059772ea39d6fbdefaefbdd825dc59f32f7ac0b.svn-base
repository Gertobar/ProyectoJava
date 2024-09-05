package ec.renafipse.mks.convertir.creditos;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.creditos.RubroTablaAmortizacion;
import ec.renafipse.mks.negocio.creditos.RubroTablaAmortizacionFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class RubroTablaAmortizacionConverter implements Converter {

    @EJB
    private RubroTablaAmortizacionFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.creditos.RubroTablaAmortizacionPK getKey(String value) {
        ec.renafipse.mks.modelo.creditos.RubroTablaAmortizacionPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.creditos.RubroTablaAmortizacionPK();
        key.setNumeroCredito(Long.parseLong(values[0]));
        key.setCodigoIfip(Long.parseLong(values[1]));
        key.setCuota(Long.parseLong(values[2]));
        key.setCodigoRubro(Long.parseLong(values[3]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.creditos.RubroTablaAmortizacionPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getNumeroCredito());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoIfip());
        sb.append(SEPARATOR);
        sb.append(value.getCuota());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoRubro());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof RubroTablaAmortizacion) {
            RubroTablaAmortizacion o = (RubroTablaAmortizacion) object;
            return getStringKey(o.getRubroTablaAmortizacionPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RubroTablaAmortizacion.class.getName()});
            return null;
        }
    }

}
