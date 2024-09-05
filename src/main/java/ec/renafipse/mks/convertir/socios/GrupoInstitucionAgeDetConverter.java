package ec.renafipse.mks.convertir.socios;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDet;
import ec.renafipse.mks.negocio.socios.GrupoInstitucionAgeDetFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class GrupoInstitucionAgeDetConverter implements Converter {

    @EJB
    private GrupoInstitucionAgeDetFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDetPK getKey(String value) {
        ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDetPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDetPK();
        key.setCodigoGrupo(Long.parseLong(values[0]));
        key.setCodigoSocio(Long.parseLong(values[1]));
        key.setCodigoIfip(Long.parseLong(values[2]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDetPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoGrupo());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoSocio());
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
        if (object instanceof GrupoInstitucionAgeDet) {
            GrupoInstitucionAgeDet o = (GrupoInstitucionAgeDet) object;
            return getStringKey(o.getGrupoInstitucionAgeDetPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), GrupoInstitucionAgeDet.class.getName()});
            return null;
        }
    }

}
