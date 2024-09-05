/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.ContrasenaUsuario;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author vicastoc
 */
@Stateless
public class ContrasenaUsuarioFacade extends AbstractFacade<ContrasenaUsuario> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContrasenaUsuarioFacade() {
        super(ContrasenaUsuario.class);
    }
    

   public List<ContrasenaUsuario> getItemsContrasenaUsuario(Long codigoSistema, String contrasena, Date fechaInicial, Date fechaFinal){    
        Query query=em.createNamedQuery(ContrasenaUsuario.findByContrasenaFecha);
        query.setParameter("contrasena", contrasena);
        query.setParameter("codigoSistema", codigoSistema);
        query.setParameter("fechaInicial",fechaInicial, TemporalType.TIMESTAMP);
        query.setParameter("fechaFinal",fechaFinal, TemporalType.TIMESTAMP);        
        return query.getResultList();        
    }
}
