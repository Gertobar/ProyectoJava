/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.dpfs;


import ec.renafipse.mks.modelo.dpfs.ParametroGeneralDpf;
import ec.renafipse.mks.modelo.dpfs.ParametroGeneralDpfIfip;
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
public class ParametroGeneralDpfIfipFacade extends AbstractFacade<ParametroGeneralDpfIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroGeneralDpfIfipFacade() {
        super(ParametroGeneralDpfIfip.class);
    }

    //------------------------------------------------------------------------
    //PERSONALIZADOS
    

}
