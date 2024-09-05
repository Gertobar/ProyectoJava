/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.modelo.ahorros.LibretaAsignadaCuenta;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author vicastoc
 */
@Stateless
public class LibretaAsignadaCuentaFacade extends AbstractFacade<LibretaAsignadaCuenta> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LibretaAsignadaCuentaFacade() {
        super(LibretaAsignadaCuenta.class);
    }
    
    @Transactional
    public void crear(LibretaAsignadaCuenta libretaAsignadaCuenta) {        
        System.out.println("Insertando la libreta Asignada");
        this.em.persist(libretaAsignadaCuenta);
        System.out.println("Fin de Insertar la libreta Asignada");

    }
}
