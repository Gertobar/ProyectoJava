/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.OficialCredito;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class OficialCreditoFacade extends AbstractFacade<OficialCredito> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OficialCreditoFacade() {
        super(OficialCredito.class);
    }
    /***
     * Metodo para obtener el oficial de credito a traves de la persona y la agencia
     * @param codigoPersona
     * @param codigoIfipAgencia
     * @return 
     */
    public OficialCredito getOficialCredito(Long codigoUsuario, Long codigoIfipAgencia) {
        List<OficialCredito> oficialCreditoLista;
        List<Usuario> usuarioLista;
        Query query = this.em.createNamedQuery("Usuario.findByCodigo",Usuario.class);
        query.setParameter("codigo", codigoUsuario);
        usuarioLista = query.getResultList();
        if(usuarioLista.isEmpty())
            return null;
        if(usuarioLista.size()>1)
            return null;
        query = this.em.createNamedQuery("OficialCredito.findByCodigoPersonaAndCodigoIfipAgencia",OficialCredito.class);
        query.setParameter("codigoPersona", usuarioLista.get(0).getCodigoPersona().getCodigo());
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        oficialCreditoLista = query.getResultList();
        if(oficialCreditoLista.size() > 0){
            if(oficialCreditoLista.size() == 1)
                return oficialCreditoLista.get(0);
            else
                return null;
        }else
            return null;

    }
    
}
