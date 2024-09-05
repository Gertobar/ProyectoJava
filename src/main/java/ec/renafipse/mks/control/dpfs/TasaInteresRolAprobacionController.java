/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.dpfs;

import ec.renafipse.mks.modelo.dpfs.ContratoDpf;
import ec.renafipse.mks.modelo.dpfs.ContratoDpfPK;
import ec.renafipse.mks.modelo.dpfs.TasaInteresProDpfMon;
import ec.renafipse.mks.modelo.dpfs.TasaInteresRolAprobacion;
import ec.renafipse.mks.negocio.dpfs.ContratoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.TasaIntenresProductoDPFMontoDetalleFacade;
import ec.renafipse.mks.negocio.dpfs.TasaInteresProDpfMonFacade;
import ec.renafipse.mks.negocio.dpfs.TasaInteresRolAprobacionFacade;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author willan
 */
@Singleton
@TransactionManagement(value = TransactionManagementType.BEAN)
public class TasaInteresRolAprobacionController {
    
    @EJB
    private 
    TasaInteresRolAprobacionFacade tasaInteresRolAprobacionFacade;
    
    @EJB
    private TasaIntenresProductoDPFMontoDetalleFacade ejbFacade;
    
    @EJB
    private TasaInteresProDpfMonFacade tasaInteresProDpfMonFacade;    
    
    @EJB
    private ContratoDpfFacade contratoDpfFacade;
    
    
    private ContratoDpf contratoDpf;            
    private TasaInteresRolAprobacion tasaInteresRolAprobacion;    
    private TasaInteresProDpfMon tasaInteresProDpfMon;
    
    public void actualizaDatosDPFNuevaTasa(long codigoTasaInteresProDpfMon, long codigoRolAprobacion, ContratoDpfPK contratoDpfPK){
        getTasaInteresRolAprobacion(codigoRolAprobacion);
        getTasaInteresProDpfMon(codigoTasaInteresProDpfMon);
        getContratoDpf(contratoDpfPK);
        /*TasaIntenresProductoDPFMontoDetalle tasaIntenresProductoDPFMontoDetalle=new TasaIntenresProductoDPFMontoDetalle(
                null, 
                contratoDpf.get, 
                BigDecimal.ONE, 
                BigDecimal.ONE, 
                BigDecimal.ZERO, 
                0l, 
                tasaInteresRolAprobacion, 
                tasaInteresProDpfMon);
        ejbFacade.create(tasaIntenresProductoDPFMontoDetalle);*/
    }

    public TasaInteresRolAprobacion getTasaInteresRolAprobacion(long codigo) {
        tasaInteresRolAprobacion=tasaInteresRolAprobacionFacade.find(codigo);
        return tasaInteresRolAprobacion;
    }
    
    public TasaInteresProDpfMon getTasaInteresProDpfMon(long codigo) {
        tasaInteresProDpfMon=tasaInteresProDpfMonFacade.find(codigo);
        return tasaInteresProDpfMon;
    }           

    public ContratoDpf getContratoDpf(ContratoDpfPK contratoDpfPK) {
        contratoDpf=contratoDpfFacade.find(contratoDpfPK);
        return contratoDpf;
    }
         
}
