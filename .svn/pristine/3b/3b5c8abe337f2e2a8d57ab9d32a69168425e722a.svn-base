/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.clase.creditos.GestionCartera;
import ec.renafipse.mks.modelo.creditos.CarteraGestionada;
import ec.renafipse.mks.modelo.creditos.asignacionCartera.CarteraAsignada;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.negocio.AbstractFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Santiago Araujo
 */
@Stateless
public class CarteraGestionadaFacade extends AbstractFacade<CarteraGestionada> {
    
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;
    
    @EJB
    private IfipAgenciaFacade ejbIfipAgenciaFacade;
    @EJB
    private CalculoCuotaPagarFacade ejbCalculoCuotaPagarFacade;
    @EJB
    private UsuarioFacade ejbUsuarioFacade;
    @EJB
    private ApoyoCarteraAsignadaFacade ejbApoyoCarteraAsignadaFacade;
    @EJB
    private PersonaFacade ejbPersonaFacade;

    @Override
        protected EntityManager getEntityManager() {
        return em;
    }

    public CarteraGestionadaFacade() {
        super(CarteraGestionada.class);
    }
    
    /***
     * Metodo para obtener los creditos a gestionar por una persona
     * @param codigoUsuario
     * @return 
     */
    public List<GestionCartera> getGestionCarteraSql(Long codigoUsuario){
        String querySQL= "SELECT s.numero codigo,\n" +
"       a.nombre agencia,\n" +
"       p_rec.nombre_completo responsable,\n" +
"       s.numero numeroCredito,\n" +
"       p.nombre_completo socio,\n" +
"       MAX( ccp.dias_mora ) diasMora,\n" +
"       ( SUM( CASE WHEN ta.fecha_pago <= TRUNC( SYSDATE ) THEN\n" +
"                     ( ccp.total_pago )\n" +
"                   ELSE\n" +
"                     ( 0 )\n" +
"              END ) + sd.total_costo_judicial ) valor_a_pagar,\n" +
"       cg.fecha fechaGestion,\n" +
"       cg.gestion,\n" +
"       cg.gestion_satisfactoria satisfactorio,\n" +
"       ca.codigo codigo_carteraAsignada,\n" +
"       r.codigo_persona codigoRecuperador\n" +
"FROM   mks_creditos.cartera_asignada ca,\n" +
"       mks_creditos.recuperador r,\n" +
"       mks_socios.persona p_rec,\n" +
"       mks_creditos.solicitud s,\n" +
"       mks_socios.socio so,\n" +
"       mks_socios.persona p,\n" +
"       mks_creditos.tabla_amortizacion ta,\n" +
"       mks_creditos.calculo_cuota_pagar ccp,\n" +
"       mks_ifips.ifip_agencia a,\n" +
"       mks_creditos.solicitud_detalle sd,\n" +
"       mks_creditos.cartera_gestionada cg\n" +
"WHERE  ca.codigo_recuperador = r.codigo_persona\n" +
"AND    r.codigo_persona = p_rec.codigo\n" +
"AND    ca.numero_credito = s.numero\n" +
"AND    ca.codigo_ifip = s.codigo_ifip\n" +
"AND    s.codigo_socio = so.codigo\n" +
"AND    so.codigo_persona = p.codigo\n" +
"AND    ta.numero_credito = s.numero\n" +
"AND    ta.codigo_ifip = s.codigo_ifip\n" +
"AND    ta.numero_credito = ccp.numero_credito\n" +
"AND    ta.codigo_ifip = ccp.codigo_ifip\n" +
"AND    ta.cuota = ccp.cuota\n" +
"AND    s.codigo_ifip_agencia = a.codigo\n" +
"AND    s.numero = sd.numero_credito\n" +
"AND    s.codigo_ifip = sd.codigo_ifip\n" +
"AND    ca.codigo = cg.codigo_cartera_asignada( + )\n" +
"AND    r.codigo_persona = :codigoPersona\n" +
"AND    ca.vigente = 'S'\n" +
"AND    ta.estado = 'P'\n" +
"AND    cg.vigente( + ) = 'S'\n" +
"HAVING MAX( ccp.dias_mora ) > 0\n" +
"GROUP BY a.nombre,\n" +
"         p_rec.nombre_completo,\n" +
"         s.numero,\n" +
"         p.nombre_completo,\n" +
"         sd.total_costo_judicial,\n" +
"         cg.fecha,\n" +
"         cg.gestion,\n" +
"         cg.gestion_satisfactoria,\n" +
"         ca.codigo,\n" +
"         r.codigo_persona\n" +
"UNION\n" +
"SELECT x.numeroCredito codigo,\n" +
"       x.agencia,\n" +
"       x.responsable,\n" +
"       x.numeroCredito,\n" +
"       x.socio,\n" +
"       x.diasMora,\n" +
"       x.valorAPagar,\n" +
"       x.fechaGestion,\n" +
"       x.gestion,\n" +
"       x.satisfactorio,\n" +
"       x.codigoCarteraAsignada,\n" +
"       x.codigoRecuperador\n" +
"FROM   ( SELECT a.nombre agencia,\n" +
"                p_rec.nombre_completo responsable,\n" +
"                s.numero numeroCredito,\n" +
"                p.nombre_completo socio,\n" +
"                MAX( ccp.dias_mora ) diasMora,\n" +
"                ( SUM( CASE WHEN ta.fecha_pago <= TRUNC( SYSDATE ) THEN\n" +
"                              ( ccp.total_pago )\n" +
"                            ELSE\n" +
"                              ( 0 )\n" +
"                       END ) + sd.total_costo_judicial ) valorAPagar,\n" +
"                cg.fecha fechaGestion,\n" +
"                cg.gestion,\n" +
"                cg.gestion_satisfactoria satisfactorio,\n" +
"                ca.codigo codigoCarteraAsignada,\n" +
"                r.codigo_persona codigoRecuperador,\n" +
"                aca.codigo_calificacion_asignacion\n" +
"         FROM   mks_creditos.cartera_asignada ca,\n" +
"                mks_creditos.recuperador r,\n" +
"                mks_socios.persona p_rec,\n" +
"                mks_creditos.solicitud s,\n" +
"                mks_socios.socio so,\n" +
"                mks_socios.persona p,\n" +
"                mks_creditos.tabla_amortizacion ta,\n" +
"                mks_creditos.calculo_cuota_pagar ccp,\n" +
"                mks_ifips.ifip_agencia a,\n" +
"                mks_creditos.solicitud_detalle sd,\n" +
"                mks_creditos.cartera_gestionada cg,\n" +
"                mks_creditos.apoyo_cartera_asignada aca\n" +
"         WHERE  ca.codigo_recuperador = r.codigo_persona\n" +
"         AND    r.codigo_persona = p_rec.codigo\n" +
"         AND    ca.numero_credito = s.numero\n" +
"         AND    ca.codigo_ifip = s.codigo_ifip\n" +
"         AND    s.codigo_socio = so.codigo\n" +
"         AND    so.codigo_persona = p.codigo\n" +
"         AND    ta.numero_credito = s.numero\n" +
"         AND    ta.codigo_ifip = s.codigo_ifip\n" +
"         AND    ta.numero_credito = ccp.numero_credito\n" +
"         AND    ta.codigo_ifip = ccp.codigo_ifip\n" +
"         AND    ta.cuota = ccp.cuota\n" +
"         AND    s.codigo_ifip_agencia = a.codigo\n" +
"         AND    s.numero = sd.numero_credito\n" +
"         AND    s.codigo_ifip = sd.codigo_ifip\n" +
"         AND    ca.codigo = cg.codigo_cartera_asignada( + )\n" +
"         AND    s.codigo_ifip_agencia = aca.codigo_ifip_agencia\n" +
"         AND    aca.codigo_persona = :codigoPersona\n" +
"         AND    ca.vigente = 'S'\n" +
"         AND    ta.estado = 'P'\n" +
"         AND    cg.vigente( + ) = 'S'\n" +
"         HAVING MAX( ccp.dias_mora ) > 0\n" +
"         GROUP BY a.nombre,\n" +
"                  p_rec.nombre_completo,\n" +
"                  s.numero,\n" +
"                  p.nombre_completo,\n" +
"                  sd.total_costo_judicial,\n" +
"                  cg.fecha,\n" +
"                  cg.gestion,\n" +
"                  cg.gestion_satisfactoria,\n" +
"                  ca.codigo,\n" +
"                  r.codigo_persona,\n" +
"                  aca.codigo_calificacion_asignacion ) x,\n" +
"       mks_creditos.calificacion_asignacion ca\n" +
"WHERE  x.codigo_calificacion_asignacion = ca.codigo\n" +
"AND    x.diasMora BETWEEN ca.dia_inicial AND NVL(ca.dia_final,x.diasMora)\n" +
"AND    TRUNC( SYSDATE ) BETWEEN ca.fecha_inicio AND NVL( ca.fecha_fin,TRUNC( SYSDATE ) )\n" +
"ORDER BY 1,2,5 DESC";
        //Inicializar la varible de retorno
        List<GestionCartera> lista = new ArrayList();
        //Obtener la persona del usuario
        Usuario usuario = ejbUsuarioFacade.getUsuariobyCodigo(codigoUsuario).get(0);
        //Otener la cartera asignada para la gestion
        Query query = this.em.createNativeQuery(querySQL);
        query.setParameter("codigoPersona",usuario.getCodigoPersona().getCodigo());
        if(!query.getResultList().isEmpty()){
            List listaObjetos=query.getResultList();
            for (Iterator iterator = listaObjetos.iterator(); iterator.hasNext();){
                System.out.println("-1");
                Object object[] = (Object[]) iterator.next();
                System.out.println("-2");
                Date fechaGestion = null;
                System.out.println("-3");
                String gestion = null;
                System.out.println("-4");
                String satisfactorio = null;
                System.out.println("-5");
                if(object[7]!=null)
                    fechaGestion = new Date(object[7].toString());
                System.out.println("-6");
                if(object[8]!=null)
                    gestion = object[8].toString();
                System.out.println("-7");
                if(object[9]!=null)
                    satisfactorio = object[9].toString();
                System.out.println("-8");
                GestionCartera gestionCartera = new GestionCartera();                
                System.out.println("1");
                gestionCartera.setCodigo(Long.parseLong(object[0].toString()));
                System.out.println("2");
                gestionCartera.setAgencia(object[1].toString());
                System.out.println("3");
                gestionCartera.setResponsable(object[2].toString());
                System.out.println("4");
                gestionCartera.setNumeroCredito(Long.parseLong(object[3].toString()));
                System.out.println("5");
                gestionCartera.setSocio(object[4].toString());
                System.out.println("6");
                gestionCartera.setDiasMora(Long.parseLong(object[5].toString()));
                System.out.println("7");
                gestionCartera.setValorAPagar(new BigDecimal(object[6].toString()));
                System.out.println("8");
                gestionCartera.setFechaGestion(fechaGestion);
                System.out.println("9");
                gestionCartera.setGestion(gestion);
                System.out.println("10");
                gestionCartera.setSatisfactorio(satisfactorio);
                System.out.println("11");
                gestionCartera.setCodigoCarteraAsignada(Long.valueOf(object[10].toString()));
                System.out.println("12");
                gestionCartera.setCodigoRecuperador(Long.valueOf(object[11].toString()));
                System.out.println("13");
                lista.add(gestionCartera);         
                System.out.println("14");
            }       
        }
        return lista;
    }
    public List<GestionCartera> getGestionCartera(Long codigoUsuario, Long codigoIfipAgencia){
        //return this.getGestionCarteraSql(codigoUsuario);
        String namedQuery = null;
        List<GestionCartera> listaCarteraAGestionar = new ArrayList();
        //Obtener la persona del usuario
        Usuario usuario = ejbUsuarioFacade.getUsuariobyCodigo(codigoUsuario).get(0);
        //Otener la cartera asignada
        Query query = null;
        //Verificar si es una persona que apoyoa a la oficina
        if(ejbApoyoCarteraAsignadaFacade.esApoyoEnGestion(usuario.getCodigoPersona().getCodigo(), codigoIfipAgencia)){
            System.out.println("ENTRA EN APOYO");
            namedQuery = CarteraAsignada.findByAgenciaAndResponsable;
            //namedQuery = "CarteraAsignada.findByResponsable";
            query = this.em.createNamedQuery(namedQuery,CarteraAsignada.class);
            query.setParameter("codigoPersona",usuario.getCodigoPersona().getCodigo());
            query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        }
        else{
            namedQuery = CarteraAsignada.findByCodigoPersonaVigente;
            query = this.em.createNamedQuery(namedQuery);
            query.setParameter("codigoPersona", usuario.getCodigoPersona().getCodigo());
        }
        List<CarteraAsignada> listaCarteraAsignada = query.getResultList();
        if(!listaCarteraAsignada.isEmpty()){
            for(int a=0; a<listaCarteraAsignada.size(); a++){
                //Inicializar variables
                String gestion = null;
                String gestionSatisfactoria = null;
                Date fechaGestion = null;
                //Obtener la persona responsable de la cartera
                Persona responsable = ejbPersonaFacade.getPersonaByCodigo(listaCarteraAsignada.get(a).getCodigoRecuperador().getCodigoPersona());
                
                //Obtener la gestion realizada
                query = this.em.createNamedQuery(CarteraGestionada.findByCodigoCarteraAsignadaVigente);
                query.setParameter("codigoCarteraAsignada", listaCarteraAsignada.get(a).getCodigo());
                List<CarteraGestionada> listaCarteraGestionada = query.getResultList();
                IfipAgencia agencia = ejbIfipAgenciaFacade.getAgenciaPorCodigo(listaCarteraAsignada.get(a).getSolicitud().getCodigoIfipAgencia());
                List<CarteraGestionada> detalleGestion = null;
                if(!listaCarteraGestionada.isEmpty()){
                    CarteraGestionada carteraGestionada = listaCarteraGestionada.get(0);
                    gestion = carteraGestionada.getGestion();
                    gestionSatisfactoria = carteraGestionada.getGestionSatisfactoria();
                    fechaGestion = carteraGestionada.getFecha();
                    Query queryDetalle = this.em.createNamedQuery(CarteraGestionada.findByNumeroCredito);
                    queryDetalle.setParameter("numeroCredito",listaCarteraAsignada.get(a).getSolicitud().getSolicitudPK().getNumero());
                    queryDetalle.setParameter("codigoIfip",listaCarteraAsignada.get(a).getSolicitud().getSolicitudPK().getCodigoIfip());
                    detalleGestion = queryDetalle.getResultList();
                }
                GestionCartera gestionCartera = new GestionCartera(listaCarteraAsignada.get(a).getSolicitud().getSolicitudPK().getNumero(),
                                                                   agencia.getNombre(),
                                                                   responsable.getNombreCompleto(),
                                                                   listaCarteraAsignada.get(a).getSolicitud().getSolicitudPK().getNumero(),
                                                                   listaCarteraAsignada.get(a).getSolicitud().getSocio().getCodigoPersona().getNombreCompleto(),
                                                                   ejbCalculoCuotaPagarFacade.getDiasMoraNumeroIfip(listaCarteraAsignada.get(a).getSolicitud().getSolicitudPK().getNumero(),listaCarteraAsignada.get(a).getSolicitud().getSolicitudPK().getCodigoIfip(),'P'),
                                                                   ejbCalculoCuotaPagarFacade.getValorVencido(listaCarteraAsignada.get(a).getSolicitud().getSolicitudPK().getNumero(),listaCarteraAsignada.get(a).getSolicitud().getSolicitudPK().getCodigoIfip(),new java.util.Date(),'P'),
                                                                   fechaGestion,
                                                                   gestion,
                                                                   gestionSatisfactoria,
                                                                   listaCarteraAsignada.get(a).getCodigo(),
                                                                   listaCarteraAsignada.get(a).getCodigoRecuperador().getCodigoPersona());
                gestionCartera.setDetalleGestion(detalleGestion);
                if(gestionCartera.getDiasMora()>0)
                    listaCarteraAGestionar.add(gestionCartera);
            }
        }
        return listaCarteraAGestionar;
    }
    
    /***
     * Metodo para colocar en vigente No las gestions antes de insertar una nueva
     * @param codigoCarteraAsignada 
     */
    public void actualizaVigenciaCarteraGestionada(Long codigoCarteraAsignada) {
        Query query = em.createQuery("UPDATE CarteraGestionada c\n"
                + "SET c.vigente = 'N'\n"
                + "WHERE c.codigoCarteraAsignada = :codigoCarteraAsignada");
        query.setParameter("codigoCarteraAsignada", codigoCarteraAsignada);
        query.executeUpdate();
    }
}
