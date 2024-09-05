/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.comunes.ConsultasCriterioConstructor;
import ec.renafipse.mks.modelo.dpfs.TasaIntenresProductoDPFMontoDetalle;
import ec.renafipse.mks.modelo.dpfs.TasaInteresProDpfMon;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author willan
 */
@Stateless
public class TasaIntenresProductoDPFMontoDetalleFacade extends AbstractFacade<TasaIntenresProductoDPFMontoDetalle> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    private ConsultasCriterioConstructor criterioConstructor;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TasaIntenresProductoDPFMontoDetalleFacade() {
        super(TasaIntenresProductoDPFMontoDetalle.class);

    }

    /**
     *
     * @return devuelve un listado de datos de DPF aprobados o no aprobados
     */
    public List<TasaIntenresProductoDPFMontoDetalle> getListaTasaIntenresProductoDPFMontoDetalle() {
        //if (criterioConstructor == null) {
        criterioConstructor = new ConsultasCriterioConstructor<TasaIntenresProductoDPFMontoDetalle>(TasaIntenresProductoDPFMontoDetalle.class, em);
        //}
        //em.close();
        return criterioConstructor.consultaTodosDatos();
    }

    /**
     * Permite guardar o actualizar la información
     *
     * @param tasaIntenresProductoDPFMontoDetalle
     */
    public void guardaOActualiza(TasaIntenresProductoDPFMontoDetalle tasaIntenresProductoDPFMontoDetalle) {
        //if (criterioConstructor == null) {
        criterioConstructor = new ConsultasCriterioConstructor<TasaIntenresProductoDPFMontoDetalle>(TasaIntenresProductoDPFMontoDetalle.class, em);
        //}
        criterioConstructor.guardarOActualizar(tasaIntenresProductoDPFMontoDetalle);
        //em.close();
    }
    
    
      /**
     * Permite realizar una busqueda por dados en la creacion de contrato de DPF
     *
     * @param persona
     * @param montoCapital
     * @param fechaRegistro
     * @param plasoDias
     * @return devuelve la nueva tasa aprobada
     */
    public TasaIntenresProductoDPFMontoDetalle buscarPorPersonaMontoFecha(Persona persona, BigDecimal montoCapital, Date fechaRegistro,long plasoDias) {
        TasaIntenresProductoDPFMontoDetalle tasaIntenresProductoDPFMontoDetalle = new TasaIntenresProductoDPFMontoDetalle();
        //if (criterioConstructor == null) {
            criterioConstructor = new ConsultasCriterioConstructor<TasaIntenresProductoDPFMontoDetalle>(TasaIntenresProductoDPFMontoDetalle.class, em);
        //}
        List columnas = Arrays.asList("persona", "montoCapital", "fechaRegistro","plasoDias","contratoDpf");
        List datos = Arrays.asList(persona, montoCapital, fechaRegistro,plasoDias,null);
        try {
            tasaIntenresProductoDPFMontoDetalle = (TasaIntenresProductoDPFMontoDetalle) criterioConstructor.consultaYVariasColumnas(columnas, datos);
        } catch (Exception e) {
        }
        //em.close();
        return tasaIntenresProductoDPFMontoDetalle;
    }  
}
