package ec.renafipse.mks.convertir.ifips;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfe;
import ec.renafipse.mks.negocio.ifips.MovimientoBovedaDesEfeFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class MovimientoBovedaDesEfeConverter implements Converter {

    @EJB
    private MovimientoBovedaDesEfeFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfePK getKey(String value) {
        ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfePK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfePK();
        key.setCodigoTipoFraccion(Long.parseLong(values[0]));
        key.setCodigoMoneda(Long.parseLong(values[1]));
        key.setCodigoMovimientoBoveda(Long.parseLong(values[2]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfePK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoTipoFraccion());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoMoneda());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoMovimientoBoveda());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof MovimientoBovedaDesEfe) {
            MovimientoBovedaDesEfe o = (MovimientoBovedaDesEfe) object;
            return getStringKey(o.getMovimientoBovedaDesEfePK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovimientoBovedaDesEfe.class.getName()});
            return null;
        }
    }

}
