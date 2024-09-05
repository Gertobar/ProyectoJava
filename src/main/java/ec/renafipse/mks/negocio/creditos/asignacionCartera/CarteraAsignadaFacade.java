/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos.asignacionCartera;

import ec.renafipse.mks.modelo.comunes.EntidadDataModel;
import ec.renafipse.mks.modelo.creditos.asignacionCartera.CarteraAsignada;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author willan
 */
@Stateless
public class CarteraAsignadaFacade extends AbstractFacade<CarteraAsignada> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    private EntidadDataModel entidadDataModel;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarteraAsignadaFacade() {
        super(CarteraAsignada.class);

    }

    public EntidadDataModel getEntidadDataModel() {
        if (entidadDataModel == null) {
            entidadDataModel = new EntidadDataModel(em, CarteraAsignada.class);
        }
        return entidadDataModel;
    }

    public void setEntidadDataModel(EntidadDataModel entidadDataModel) {
        this.entidadDataModel = entidadDataModel;
    }
   
}
