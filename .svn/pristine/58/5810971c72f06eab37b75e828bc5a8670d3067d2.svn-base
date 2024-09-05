package ec.renafipse.mks.convertir.dpfs;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.dpfs.PagoDpf;
import ec.renafipse.mks.negocio.dpfs.PagoDpfFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class PagoDpfConverter implements Converter {

    @EJB
    private PagoDpfFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.dpfs.PagoDpfPK getKey(String value) {
        ec.renafipse.mks.modelo.dpfs.PagoDpfPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.dpfs.PagoDpfPK();
        key.setNumeroPago(Long.parseLong(values[0]));
        key.setCodigoContrato(Long.parseLong(values[1]));
        key.setCodigoIfip(Long.parseLong(values[2]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.dpfs.PagoDpfPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getNumeroPago());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoContrato());
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
        if (object instanceof PagoDpf) {
            PagoDpf o = (PagoDpf) object;
            return getStringKey(o.getPagoDpfPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PagoDpf.class.getName()});
            return null;
        }
    }

}
