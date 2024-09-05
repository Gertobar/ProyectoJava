/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.validadores.socios;

import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import java.util.Date;
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
@FacesValidator("fechaMenorHoyValidador")
public class FechaMenorHoyValidador implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!Validaciones.validaFechaMenorHoy((Date) value)) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FechaMenorHoy");
            throw new ValidatorException(MuestraMensaje.getFaceError(msg));
        }
    }
}
