/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.FabricaPerfilEstado;
import ec.renafipse.mks.modelo.creditos.FabricaUsuarioPerfil;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Santiago Araujo
 */
@Stateless
public class FabricaUsuarioPerfilFacade extends AbstractFacade<FabricaUsuarioPerfil> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FabricaUsuarioPerfilFacade() {
        super(FabricaUsuarioPerfil.class);
    }
    /***
     * Metodo para obtener el Perfil de un usuario en una Agencia
     * @param codigoUsuario
     * @param codigoIfipAgencia
     * @param codigoEstadoCredito
     * @return 
     */
    public List<FabricaUsuarioPerfil> getPerfilUsuarioFabrica(Long codigoUsuario, Long codigoIfipAgencia){
        Query query = em.createNamedQuery(FabricaUsuarioPerfil.findPerfilByUsuarioAgencia);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("codigIfipAgencia", codigoIfipAgencia);
        return query.getResultList();
    }
    
    /***
     * Metodo para validar el acceso a las pantallas para los procesos de concesion de credito
     * @param codigoUsuario
     * @param codigoEstadoCredito
     * @param codigoAgencia
     * @return 
     */
    public boolean validaAccesoProcesoCredito(Long codigoUsuario, Long codigoIfipAgencia, Long codigoEstadoCredito)
    {
       boolean esValido = false;
       List<FabricaUsuarioPerfil> perfiles = getPerfilUsuarioFabrica(codigoUsuario, codigoIfipAgencia);
       for(int i=0; i < perfiles.size(); i++){
           FabricaUsuarioPerfil usuarioPerfil = (FabricaUsuarioPerfil)perfiles.get(i);
           List<FabricaPerfilEstado> perfilEstados = usuarioPerfil.getCodigoPerfil().getFabricaPerfilEstadoList();
           for(int j=0; j< perfilEstados.size();j++){
               FabricaPerfilEstado perfilEstado = perfilEstados.get(j);
               if(perfilEstado.getEstadoCredito().getCodigo() == codigoEstadoCredito && perfilEstado.getTipoEstadoCredito().compareTo("A") == 0){
                   esValido = true;
                   break;
               }
           }
       }
       return esValido;
    }
    
}
