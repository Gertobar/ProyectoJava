/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.NotificacionCredito;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class NotificacionCreditoFacade extends AbstractFacade<NotificacionCredito> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;
    private List<Object[]> lista;
    private List<NotificacionCredito> listado;
    private NotificacionCredito notificacion;
    @EJB
    private TipoNotificacionIfipFacade ejbTipoNotificacionIfipFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificacionCreditoFacade() {
        super(NotificacionCredito.class);
    }

    public List<NotificacionCredito> getItemsPagoCreditoSocio(long numeroCredito, long codigoIfip, char estado, char eliminado) {
        Query query = this.em.createNativeQuery("SELECT n.codigo, "
                                                + "n.numero_credito, "
                                                + "n.codigo_ifip, "
                                                + "n.codigo_tipo_notificacion, "
                                                + "n.cuota, "
                                                + "n.es_para_garante, "
                                                + "n.estado, "
                                                + "n.valor, "
                                                + "n.abono, "
                                                + "n.saldo, "
                                                + "n.registrado_por, "
                                                + "n.fecha_registro, "
                                                + "n.eliminado, "
                                                + "n.cancelada, "
                                                + "n.saldo_capital_cuota, "
                                                + "n.dias_mora, "
                                                + "n.observaciones "
                                                + "FROM mks_creditos.notificacion_credito n,  "
                                                     + "mks_creditos.tabla_amortizacion t "
                                                + "WHERE n.numero_credito = t.numero_credito "
                                                      + "AND n.codigo_ifip = t.codigo_ifip "
                                                      + "AND n.cuota = t.cuota "
                                                      + "AND t.codigo_ifip = :codigoIfip "
                                                      + "AND t.numero_credito = :numeroCredito "
                                                      + "AND n.estado = :estado "
                                                      + "AND n.eliminado = :eliminado "
                                                + "ORDER BY n.cuota, n.codigo");
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estado", estado);
        query.setParameter("eliminado", eliminado);
        lista = query.getResultList();
        if (lista != null) {
            if (lista.size() > 0) {
                listado = new ArrayList<NotificacionCredito>();
                for (int i = 0; i < lista.size(); i++) {
                    notificacion = new NotificacionCredito();
                    notificacion.setCodigo(Long.valueOf(lista.get(i)[0].toString()));
                    notificacion.setNumeroCredito(Long.valueOf(lista.get(i)[1].toString()));
                    notificacion.setCodigoIfip(Long.valueOf(lista.get(i)[2].toString()));
                    notificacion.setCodigoTipoNotificacion(Long.valueOf(lista.get(i)[3].toString()));
                    notificacion.setCuota(Long.valueOf(lista.get(i)[4].toString()));
                    notificacion.setEsParaGarante((Character) lista.get(i)[5]);
                    notificacion.setEstado((Character) lista.get(i)[6]);
                    notificacion.setValor((BigDecimal) lista.get(i)[7]);
                    notificacion.setAbono((BigDecimal) lista.get(i)[8]);
                    notificacion.setSaldo((BigDecimal) lista.get(i)[9]);
                    notificacion.setUsuarioRegistradoPor(Long.valueOf(lista.get(i)[10].toString()));
                    notificacion.setFechaRegistro((Date) lista.get(i)[11]);
                    notificacion.setEliminado((Character) lista.get(i)[12]);
                    notificacion.setCancelada((Character) lista.get(i)[13]);
                    notificacion.setSaldoCapitalCuota((BigDecimal) lista.get(i)[14]);
                    notificacion.setDiasMora(Long.valueOf(lista.get(i)[15].toString()));
                    notificacion.setObservaciones(lista.get(i)[16].toString());
                    notificacion.setTipoNotificacionIfip(ejbTipoNotificacionIfipFacade.getTipoNotificacionCodigo(Long.valueOf(lista.get(i)[3].toString())));
                    listado.add(notificacion);
                }
            } else {
                listado = null;
            }
        }
        return listado;
    }
}