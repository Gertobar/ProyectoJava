package ec.renafipse.mks.convertir.socios;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgencia;
import ec.renafipse.mks.negocio.socios.GrupoInstitucionIfipAgenciaFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class GrupoInstitucionIfipAgenciaConverter implements Converter {

    @EJB
    private GrupoInstitucionIfipAgenciaFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgenciaPK getKey(String value) {
        ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgenciaPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgenciaPK();
        key.setCodigoGrupo(Long.parseLong(values[0]));
        key.setCodigoIfipAgencia(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgenciaPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoGrupo());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoIfipAgencia());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof GrupoInstitucionIfipAgencia) {
            GrupoInstitucionIfipAgencia o = (GrupoInstitucionIfipAgencia) object;
            return getStringKey(o.getGrupoInstitucionIfipAgenciaPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), GrupoInstitucionIfipAgencia.class.getName()});
            return null;
        }
    }

}
