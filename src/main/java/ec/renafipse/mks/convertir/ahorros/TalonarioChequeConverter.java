package ec.renafipse.mks.convertir.ahorros;


import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.ahorros.TalonarioCheque;
import ec.renafipse.mks.modelo.ahorros.TalonarioChequePK;
import ec.renafipse.mks.negocio.ahorros.TalonarioChequeFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class TalonarioChequeConverter implements Converter {

    @EJB
    private TalonarioChequeFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    TalonarioChequePK getKey(String value) {
        TalonarioChequePK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new TalonarioChequePK();
        key.setCodigo(Long.parseLong(values[0]));
        key.setCodigoCuentaEntFin(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(TalonarioChequePK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigo());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoCuentaEntFin());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof TalonarioCheque) {
            TalonarioCheque o = (TalonarioCheque) object;
            return getStringKey(o.getTalonarioChequePK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TalonarioCheque.class.getName()});
            return null;
        }
    }

}
