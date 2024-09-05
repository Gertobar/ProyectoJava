/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.TalonarioLibretaAhorroCab;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class TalonarioLibretaAhorroCabFacade extends AbstractFacade<TalonarioLibretaAhorroCab> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TalonarioLibretaAhorroCabFacade() {
        super(TalonarioLibretaAhorroCab.class);
    }
    
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE LAS CABECERAS DE LOS TALONARIOS INGRESADOS POR LA IFIP.
     *  
     * @param codigoIfip Codigo de la IFIP
     * @return
     */
    public List<TalonarioLibretaAhorroCab> getItemsTalonarioLibAhoCabIfi(Long codigoIfip){
        List<TalonarioLibretaAhorroCab> listTalonarioLibretaAhorroCab;

        Query query = this.em.createNamedQuery(TalonarioLibretaAhorroCab.findByCodigoIfip);
        query.setParameter("codigoIfip", codigoIfip);
        listTalonarioLibretaAhorroCab = query.getResultList();

        return listTalonarioLibretaAhorroCab;
    }
    
    
    /**
     * OBTIENE LAS CABECERAS DE LOS TALONARIOS INGRESADOS POR LA IFIP Y X TIPO PRODUCTO
     * Y QUE EL INICIO Y FIN DE LA SERIE ESTE ENTER EL NUMERO DE SERIE INGRESADO.     *  
     * @param codigoIfip Codigo de la IFIP
     * @param inicioSerie
     * @param coditoTipoProducto Codigo del tipo de producto
     * @return
     */
    public List<TalonarioLibretaAhorroCab> getItemsTalonarioLibAhoCabIfiTipoProducto(Long codigoIfip, Long inicioSerie, Long codigoTipoProducto){
        List<TalonarioLibretaAhorroCab> listTalonarioLibretaAhorroCab;

        Query query = this.em.createNamedQuery(TalonarioLibretaAhorroCab.findByCodigoIfipInicioSerieEntreInicioFinSerieTipoProducto);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("inicioSerie", inicioSerie);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        listTalonarioLibretaAhorroCab = query.getResultList();

        return listTalonarioLibretaAhorroCab;
    }
    /**
     * OBTIENE LAS CABECERAS DE LOS TALONARIOS QUE TIENEN LA SERIE CONSULTADA POR LA IFIP 
     * @param codigoIfip Codigo de la IFIP
     * @param inicioSerie
     * @param finSerie
     * @return
     */
    public boolean getCabeceraPorSerie(Long codigoIfip, Long inicioSerie, Long finSerie){
        List<TalonarioLibretaAhorroCab> listTalonarioLibretaAhorroCab;
        boolean existe;
        Query query = this.em.createNamedQuery(TalonarioLibretaAhorroCab.findByCodigoIfipSerie);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("serieInicio", inicioSerie);
        query.setParameter("serieFin", finSerie);
        query.setParameter("eliminado", 'N');
        listTalonarioLibretaAhorroCab = query.getResultList();
        if ( listTalonarioLibretaAhorroCab.isEmpty() )
            existe = false;
        else
            existe = true;
        return existe;
    }
    public boolean getCabeceraPorSerieTipoProducto(Long codigoIfip, Long inicioSerie, Long finSerie, Long codigoTipoProducto){
        List<TalonarioLibretaAhorroCab> listTalonarioLibretaAhorroCab;
        boolean existe;
        Query query = this.em.createNamedQuery(TalonarioLibretaAhorroCab.findByCodigoIfipSerieTipoProducto);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("serieInicio", inicioSerie);
        query.setParameter("serieFin", finSerie);
        query.setParameter("eliminado", 'N');
        listTalonarioLibretaAhorroCab = query.getResultList();
        if ( listTalonarioLibretaAhorroCab.isEmpty() )
            existe = false;
        else
            existe = true;
        return existe;
    }
}
