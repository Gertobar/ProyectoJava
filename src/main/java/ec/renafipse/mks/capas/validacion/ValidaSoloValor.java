/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.validacion;

import java.math.BigDecimal;
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
@FacesValidator(value = "validaSoloValor")
public class ValidaSoloValor implements Validator {
    
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
                try{
                    BigDecimal bigDecimal = new BigDecimal(cadena);
                }catch(NumberFormatException e){
                    valido = false;
                    mensaje = "Ingrese un valor valido #0.00#";
                } catch (Exception ex) {
                    valido = false;
                    mensaje = "Ingrese un valor valido #0.00#";
                }
                
            }
        }
        
        if (!valido) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensaje);
            throw new ValidatorException(message);
        }
    }
}
