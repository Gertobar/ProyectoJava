package ec.renafipse.mks.convertir.adquisicones;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.adquisiciones.DocumentosProveedor;
import ec.renafipse.mks.modelo.adquisiciones.DocumentosProveedorPK;
import ec.renafipse.mks.negocio.adquisiciones.DocumentosProveedorFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class DocumentosProveedorConverter implements Converter {

    @EJB
    private DocumentosProveedorFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    DocumentosProveedorPK getKey(String value) {
        DocumentosProveedorPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new DocumentosProveedorPK();
        key.setCodigoProveedor(Long.parseLong(values[0]));
        key.setCodigoTipoComprobante(Long.parseLong(values[1]));
        key.setFechaEmision(java.sql.Date.valueOf(values[2]));
        return key;
    }

    String getStringKey(DocumentosProveedorPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoProveedor());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoTipoComprobante());
        sb.append(SEPARATOR);
        sb.append(value.getFechaEmision());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof DocumentosProveedor) {
            DocumentosProveedor o = (DocumentosProveedor) object;
            return getStringKey(o.getDocumentosProveedorPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DocumentosProveedor.class.getName()});
            return null;
        }
    }

}
