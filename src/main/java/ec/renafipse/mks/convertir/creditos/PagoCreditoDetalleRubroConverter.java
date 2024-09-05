package ec.renafipse.mks.convertir.creditos;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleRubro;
import ec.renafipse.mks.negocio.creditos.PagoCreditoDetalleRubroFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class PagoCreditoDetalleRubroConverter implements Converter {

    @EJB
    private PagoCreditoDetalleRubroFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleRubroPK getKey(String value) {
        ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleRubroPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleRubroPK();
        key.setCodigoPagoCredito(Long.parseLong(values[0]));
        key.setNumeroCredito(Long.parseLong(values[1]));
        key.setCodigoIfip(Long.parseLong(values[2]));
        key.setCuota(Long.parseLong(values[3]));
        key.setCodigoRubro(Long.parseLong(values[4]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleRubroPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoPagoCredito());
        sb.append(SEPARATOR);
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
        if (object instanceof PagoCreditoDetalleRubro) {
            PagoCreditoDetalleRubro o = (PagoCreditoDetalleRubro) object;
            return getStringKey(o.getPagoCreditoDetalleRubroPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PagoCreditoDetalleRubro.class.getName()});
            return null;
        }
    }

}
