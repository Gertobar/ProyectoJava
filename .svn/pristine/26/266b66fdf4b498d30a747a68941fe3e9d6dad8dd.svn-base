/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.comunes.ConsultasCriterioConstructor;
import ec.renafipse.mks.modelo.dpfs.ContratoDpf;
import ec.renafipse.mks.modelo.dpfs.RegistroAgenciaSocioDPF;
import ec.renafipse.mks.modelo.dpfs.TasaIntenresProductoDPFMontoDetalle;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author willan
 */
@Stateless
public class RegistroAgenciaSocioDPFFacade extends AbstractFacade<RegistroAgenciaSocioDPF> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroAgenciaSocioDPFFacade() {
        super(RegistroAgenciaSocioDPF.class);
    }

    public RegistroAgenciaSocioDPFFacade(Class<RegistroAgenciaSocioDPF> entityClass) {
        super(entityClass);
    }

    /**
     * Permite guardar o actualizar la información
     *
     * @param registroAgenciaSocioDPF
     */
    public void guardaOActualiza(RegistroAgenciaSocioDPF registroAgenciaSocioDPF) {
        ConsultasCriterioConstructor criterioConstructor = new ConsultasCriterioConstructor<TasaIntenresProductoDPFMontoDetalle>(TasaIntenresProductoDPFMontoDetalle.class, em);
        criterioConstructor.guardarOActualizar(registroAgenciaSocioDPF);
    }

    /**
     * Permite guardar o actualizar la información
     *
     * @param contratoDpf
     * @return devuel una entidad en el caso de encontrarse o nulo cuando no encuentra regsitros
     */
    public RegistroAgenciaSocioDPF buscarPorDPF(ContratoDpf contratoDpf) {
        RegistroAgenciaSocioDPF registroAgenciaSocioDPF = null;
        try {
            registroAgenciaSocioDPF = new ConsultasCriterioConstructor<RegistroAgenciaSocioDPF>(RegistroAgenciaSocioDPF.class, em).consultaPrimerDato("contratoDpf", contratoDpf);
        } catch (Exception e) {
        }
        return registroAgenciaSocioDPF;
    }
    
    
    
     /**
     * Permite buscar la oficina del socio en la que se registra o cuaquier oficina a través del código
     *
     * @param codigoAgencia
     * @return devuelve la oficina encontrada
     */
    public IfipAgencia buscarPorAgencia(long codigoAgencia) {
        IfipAgencia ifipAgencia=null;
        try {
            ifipAgencia = new ConsultasCriterioConstructor<IfipAgencia>(IfipAgencia.class, em).consultaPrimerDato("codigo", codigoAgencia);
        } catch (Exception e) {
        }
        return ifipAgencia;
    }

}
