package ec.renafipse.mks.convertir.seguridades;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.seguridades.RolConceptoTransaccionIfip;
import ec.renafipse.mks.negocio.seguridades.RolConceptoTransaccionIfipFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class RolConceptoTransaccionIfipConverter implements Converter {

    @EJB
    private RolConceptoTransaccionIfipFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.seguridades.RolConceptoTransaccionIfipPK getKey(String value) {
        ec.renafipse.mks.modelo.seguridades.RolConceptoTransaccionIfipPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.seguridades.RolConceptoTransaccionIfipPK();
        key.setCodigoTipoProducto(Long.parseLong(values[0]));
        key.setCodigoMoneda(Long.parseLong(values[1]));
        key.setCodigoConcepto(Long.parseLong(values[2]));
        key.setCodigoRol(values[3]);
        key.setCodigoIfip(Long.parseLong(values[4]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.seguridades.RolConceptoTransaccionIfipPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoTipoProducto());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoMoneda());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoConcepto());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoRol());
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
        if (object instanceof RolConceptoTransaccionIfip) {
            RolConceptoTransaccionIfip o = (RolConceptoTransaccionIfip) object;
            return getStringKey(o.getRolConceptoTransaccionIfipPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RolConceptoTransaccionIfip.class.getName()});
            return null;
        }
    }

}
