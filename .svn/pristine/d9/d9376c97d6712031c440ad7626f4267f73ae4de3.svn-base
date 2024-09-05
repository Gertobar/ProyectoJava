/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.convertir.socios;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.socios.PersonaVinculado;
import ec.renafipse.mks.negocio.socios.PersonaVinculadoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mvks
 */
@Named
public class PersonaVinculadoConverter implements Converter {
    @Inject
    private PersonaVinculadoFacade ejbFacade;
    private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
        }

        ec.renafipse.mks.modelo.socios.PersonaVinculadoPK getKey(String value) {
            ec.renafipse.mks.modelo.socios.PersonaVinculadoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ec.renafipse.mks.modelo.socios.PersonaVinculadoPK();
            key.setCodigoPersona(Long.parseLong(values[0]));
            key.setCodigoPersonaVinculado(Long.parseLong(values[1]));
            return key;
        }

        String getStringKey(ec.renafipse.mks.modelo.socios.PersonaVinculadoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCodigoPersona());
            sb.append(SEPARATOR);
            sb.append(value.getCodigoPersonaVinculado());
            return sb.toString();
        }

    @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PersonaVinculado) {
                PersonaVinculado o = (PersonaVinculado) object;
                return getStringKey(o.getPersonaVinculadoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PersonaVinculado.class.getName());
            }
        }
}
