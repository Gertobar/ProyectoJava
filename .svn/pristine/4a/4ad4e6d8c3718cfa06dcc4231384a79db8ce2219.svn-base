/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.validadores.socios;

import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author vicastoc
 */
@FacesValidator("identificacionSocioValidador")
public class IdentificacionSocioValidador implements Validator {


    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String msg = null;
        String identificacion = value.toString();

        String siglasTipoIdentificacion = (component.getAttributes().get("siglasIdentificacion") != null) ? component.getAttributes().get("siglasIdentificacion").toString() : "C";

        if (siglasTipoIdentificacion.trim().toUpperCase().equals("C")) {
            try {
                Long.parseLong(identificacion);
            } catch (Exception e) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AceptaSoloNumericos");
            }
            if (!Validaciones.validaCedula(identificacion)) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionIncorrecta");
            }
        }
        ////System.out.println("Siglas "+siglasTipoIdentificacion);
        if (siglasTipoIdentificacion.trim().toUpperCase().equals("R")) {
            if (identificacion.length() != 13) {
                try {
                    Long.parseLong(identificacion);
                } catch (Exception e) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AceptaSoloNumericos");
                }
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionIncorrecta");
            }
        }

        if (msg != null) {
            throw new ValidatorException(MuestraMensaje.getFaceError(msg));
        }

    }

}
