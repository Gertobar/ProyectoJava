//package ec.renafipse.mks.convertir.creditos;
//
//import ec.renafipse.mks.modelo.creditos.DiasEnvioNotificacionIfip;
//import ec.renafipse.mks.negocio.creditos.DiasEnvioNotificacionIfipFacade;
//import ec.renafipse.mks.negocio.creditos.util.JsfUtil;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.inject.Named;
//import javax.inject.Inject;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//
//@Named
//public class DiasEnvioNotificacionIfipConverter implements Converter {
//
//    @Inject
//    private DiasEnvioNotificacionIfipFacade ejbFacade;
//
//    private static final String SEPARATOR = "#";
//    private static final String SEPARATOR_ESCAPED = "\\#";
//
//    @Override
//    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
//            return null;
//        }
//        return this.ejbFacade.find(getKey(value));
//    }
//
//    ec.renafipse.mks.modelo.creditos.DiasEnvioNotificacionIfipPK getKey(String value) {
//        ec.renafipse.mks.modelo.creditos.DiasEnvioNotificacionIfipPK key;
//        String values[] = value.split(SEPARATOR_ESCAPED);
//        key = new ec.renafipse.mks.modelo.creditos.DiasEnvioNotificacionIfipPK();
//        key.setCodigoTipoNotificacion(Long.parseLong(values[0]));
//        key.setCodigoIfip(Long.parseLong(values[1]));
//        return key;
//    }
//
//    String getStringKey(ec.renafipse.mks.modelo.creditos.DiasEnvioNotificacionIfipPK value) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(value.getCodigoTipoNotificacion());
//        sb.append(SEPARATOR);
//        sb.append(value.getCodigoIfip());
//        return sb.toString();
//    }
//
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//        if (object == null
//                || (object instanceof String && ((String) object).length() == 0)) {
//            return null;
//        }
//        if (object instanceof DiasEnvioNotificacionIfip) {
//            DiasEnvioNotificacionIfip o = (DiasEnvioNotificacionIfip) object;
//            return getStringKey(o.getDiasEnvioNotificacionIfipPK());
//        } else {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DiasEnvioNotificacionIfip.class.getName()});
//            return null;
//        }
//    }

//}
