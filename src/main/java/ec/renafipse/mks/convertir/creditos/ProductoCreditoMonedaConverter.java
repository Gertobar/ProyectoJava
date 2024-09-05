package ec.renafipse.mks.convertir.creditos;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMoneda;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoMonedaFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class ProductoCreditoMonedaConverter implements Converter {

    @EJB
    private ProductoCreditoMonedaFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaPK getKey(String value) {
        ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaPK();
        key.setCodigoProducto(Long.parseLong(values[0]));
        key.setCodigoMoneda(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoProducto());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoMoneda());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof ProductoCreditoMoneda) {
            ProductoCreditoMoneda o = (ProductoCreditoMoneda) object;
            return getStringKey(o.getProductoCreditoMonedaPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProductoCreditoMoneda.class.getName()});
            return null;
        }
    }

}
