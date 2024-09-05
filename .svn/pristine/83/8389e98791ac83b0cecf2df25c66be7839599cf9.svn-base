package ec.renafipse.mks.convertir.cajas;

import ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDet;
import ec.renafipse.mks.negocio.cajas.TransferenciaChequeDepDetFacade;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class TransferenciaChequeDepDetConverter implements Converter {

    @EJB
    private TransferenciaChequeDepDetFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDetPK getKey(String value) {
        ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDetPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDetPK();
        key.setCodigoMoneda(Long.parseLong(values[0]));
        key.setCodigoTransferencia(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDetPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoMoneda());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoTransferencia());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof TransferenciaChequeDepDet) {
            TransferenciaChequeDepDet o = (TransferenciaChequeDepDet) object;
            return getStringKey(o.getTransferenciaChequeDepDetPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TransferenciaChequeDepDet.class.getName()});
            return null;
        }
    }

}
