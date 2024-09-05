/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.TalonarioLibretaAhorroDet;
import ec.renafipse.mks.modelo.ahorros.TipoFirma;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vicastoc
 */
@Stateless
public class TalonarioLibretaAhorroDetFacade extends AbstractFacade<TalonarioLibretaAhorroDet> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TalonarioLibretaAhorroDetFacade() {
        super(TalonarioLibretaAhorroDet.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE EL OBJETO DEL NUMERO DE LIBRETA DE ACUERDO AL NUMERO Y ESTADO
     *
     * @param numeroLibreta
     * @param estado
     * @return
     */
    public TalonarioLibretaAhorroDet getNumeroLibreta(String numeroLibreta, char estado) {
        List<TalonarioLibretaAhorroDet> listTalonarioLibretaAhorroDet;

        Query query = this.em.createNamedQuery(TalonarioLibretaAhorroDet.findByNumeroLibretaEstado);
        query.setParameter("numeroLibreta", numeroLibreta);
        query.setParameter("estado", estado);
        listTalonarioLibretaAhorroDet = query.getResultList();

        return listTalonarioLibretaAhorroDet.size() == 1 ? listTalonarioLibretaAhorroDet.get(0) : null;
    }

    /**
     * BUSCA LA LIBRETA DE ACUERDO A SU NUMERO
     *
     * @param numeroLibreta
     * @return
     */
    public TalonarioLibretaAhorroDet getNumeroLibreta(String numeroLibreta) {
        List<TalonarioLibretaAhorroDet> listTalonarioLibretaAhorroDet;

        Query query = this.em.createNamedQuery(TalonarioLibretaAhorroDet.findByNumeroLibreta);
        query.setParameter("numeroLibreta", numeroLibreta);
        listTalonarioLibretaAhorroDet = query.getResultList();

        return listTalonarioLibretaAhorroDet.size() == 1 ? listTalonarioLibretaAhorroDet.get(0) : null;
    }

    /**
     * BUSCA LA LIBRETA DE ACUERDO A SU NUMERO Y AL PRODUCTO
     *
     * @param codigoTipoProducto Codigo del Tipo de Producto
     * @param codigoMoneda Codigo de la Moneda
     * @param codigoIfip Codigo de la IFIP
     * @param numeroLibreta Numero de Librea
     * @return NUmero de Libreta
     */
    public TalonarioLibretaAhorroDet getNumeroLibretaProducto(Long codigoTipoProducto, Long codigoMoneda, Long codigoIfip, String numeroLibreta) {
        List<TalonarioLibretaAhorroDet> listTalonarioLibretaAhorroDet;

        Query query = this.em.createNamedQuery(TalonarioLibretaAhorroDet.findByNumeroLibretaProducto);
        query.setParameter("numeroLibreta", numeroLibreta);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);

        listTalonarioLibretaAhorroDet = query.getResultList();

        return listTalonarioLibretaAhorroDet.size() == 1 ? listTalonarioLibretaAhorroDet.get(0) : null;
    }

    /**
     *
     * @param codigoTipoProducto
     * @param codigoMoneda
     * @param codigoIfip
     * @param estado
     * @return
     */
    public List<TalonarioLibretaAhorroDet> getLibretaIfipProductoMonedaEstado(Long codigoTipoProducto, Long codigoMoneda, Long codigoIfip, char estado) {
        Query query = this.em.createNamedQuery(TalonarioLibretaAhorroDet.findByLibretaIfipProductoMonedaEstado);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estado", estado);
        return query.getResultList();

    }

    public TalonarioLibretaAhorroDet getLibretaIfipProductoMonedaSerie(Long codigoTipoProducto, Long codigoMoneda, Long codigoIfip, long serie) {
        Query query = this.em.createNamedQuery(TalonarioLibretaAhorroDet.findByLibretaIfipProductoMonedaSerie);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("serie", serie);
        List<TalonarioLibretaAhorroDet> listTalonarioLibretaAhorroDet = query.getResultList();
        return listTalonarioLibretaAhorroDet.size() == 1 ? listTalonarioLibretaAhorroDet.get(0) : null;

    }
    
    /***
     * 
     * @param codigoTipoProducto
     * @param codigoMoneda
     * @param codigoIfip
     * @param serie
     * @return
     */
    public List<TalonarioLibretaAhorroDet> getLibretaIfipProductoMonedaSerieEliminado(Long codigoTipoProducto, Long codigoMoneda, Long codigoIfip, long serie) {
        Query query = this.em.createNamedQuery(TalonarioLibretaAhorroDet.findByLibretaIfipProductoMonedaSerieEliminado);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("serie", serie);
        List<TalonarioLibretaAhorroDet> listTalonarioLibretaAhorroDet = query.getResultList();
        return listTalonarioLibretaAhorroDet;
    }
    
    @Transactional
     public void actualizaEstado(String numeroLibreta, char estado)
     {
         TalonarioLibretaAhorroDet libreta = super.find(numeroLibreta);
         libreta.setEstado(estado);
     }
}
