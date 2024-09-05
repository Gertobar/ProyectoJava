/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.capas.validacion;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author andres
 */
@FacesValidator(value = "validadorSoloNumero")
public class ValidaSoloNumero implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean valido = true;
        String mensaje = "";
        String cadena = value.toString().trim();
        
         if (cadena == null) {
            valido = true;
        }
        else {
            if (cadena.length() == 0) {
                valido = true;
            }
            else {
                Boolean soloNumeros = true;
                for (int i = 0; i < (cadena.length()); i++) {
                    String caracter =  cadena.substring(i,i + 1);
                    try {
                        Integer.parseInt(caracter);
                    }
                    catch (NumberFormatException e) {
                        soloNumeros = false;
                        valido = false;
                        mensaje = "Ingrese solo numeros";
                        break;
                    } catch (Exception ex) {
                        soloNumeros = false;
                        valido = false;
                        mensaje = "Ingrese solo numeros";
                        break;
                    }
                }
                if (soloNumeros)
                    valido = true;
            }
        }
        
        if (!valido) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensaje);
            throw new ValidatorException(message);
        }
    }
}