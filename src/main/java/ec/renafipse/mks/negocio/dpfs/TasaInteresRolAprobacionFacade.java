/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.comunes.ConsultasCriterioConstructor;
import ec.renafipse.mks.modelo.dpfs.TasaInteresRolAprobacion;
import ec.renafipse.mks.modelo.seguridades.UsuarioIfipAgencia;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author willan
 */
@Stateless
public class TasaInteresRolAprobacionFacade extends AbstractFacade<TasaInteresRolAprobacion> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TasaInteresRolAprobacionFacade() {
        super(TasaInteresRolAprobacion.class);
    }

    public TasaInteresRolAprobacion buscarTasaInteresRolAprobacion(List<String> columnas, List<Object> datos) {
        return new ConsultasCriterioConstructor<TasaInteresRolAprobacion>(TasaInteresRolAprobacion.class, em).consultaYVariasColumnas(columnas, datos);
    }

    public UsuarioIfipAgencia buscarUsuarioIfipAgencia(List<String> columnas, List<Object> datos) {
        return new ConsultasCriterioConstructor<UsuarioIfipAgencia>(UsuarioIfipAgencia.class, em).consultaYVariasColumnas(columnas, datos);
    }

}
