package ec.renafipse.mks.convertir.ahorros;

import ec.renafipse.mks.modelo.ahorros.ImpresionLibretaCuenta;
import ec.renafipse.mks.negocio.ahorros.ImpresionLibretaCuentaFacade;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class ImpresionLibretaCuentaConverter implements Converter {

    @EJB
    private ImpresionLibretaCuentaFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.ahorros.ImpresionLibretaCuentaPK getKey(String value) {
        ec.renafipse.mks.modelo.ahorros.ImpresionLibretaCuentaPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.ahorros.ImpresionLibretaCuentaPK();
        key.setCodigoMovimiento(Long.parseLong(values[0]));
        key.setNumeroLibreta(values[1]);
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.ahorros.ImpresionLibretaCuentaPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoMovimiento());
        sb.append(SEPARATOR);
        sb.append(value.getNumeroLibreta());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof ImpresionLibretaCuenta) {
            ImpresionLibretaCuenta o = (ImpresionLibretaCuenta) object;
            return getStringKey(o.getImpresionLibretaCuentaPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ImpresionLibretaCuenta.class.getName()});
            return null;
        }
    }

}
