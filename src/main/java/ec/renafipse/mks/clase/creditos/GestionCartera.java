/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.clase.creditos;

import ec.renafipse.mks.modelo.creditos.CarteraGestionada;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Santiago Araujo
 */
@Entity
@Table(name="GestionCartera")
@NamedNativeQueries({
    @NamedNativeQuery(name="CarteraGestionada.findByRecuperadoToGestion", 
            query= "SELECT s.numero codigo,\n" +
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
"ORDER BY 1,2,5 DESC" , resultClass=GestionCartera.class)
})
public class GestionCartera implements Serializable {
    public static String findByRecuperadoToGestion = "CarteraGestionada.findByRecuperadoToGestion";
    @Id
    private Long codigo;
    private String agencia;
    private String responsable;
    private Long numeroCredito;
    private String socio;
    private long diasMora;
    private BigDecimal valorAPagar;
    private Date fechaGestion;
    private String gestion;
    private String satisfactorio;
    private Long codigoCarteraAsignada;
    private Long codigoRecuperador;
    @Transient
    private List<CarteraGestionada> detalleGestion;

    public GestionCartera() {
    }
    public GestionCartera(Long codigo, String agencia, String responsable, Long numeroCredito, String socio, long diasMora, BigDecimal valorAPagar, Date fechaGestion, String gestion, String satisfactorio, Long codigoCarteraAsignada, Long codigoRecuperador){
        this.codigo = codigo;
        this.agencia = agencia;
        this.responsable = responsable;
        this.numeroCredito = numeroCredito;
        this.socio = socio;
        this.diasMora = diasMora;
        this.valorAPagar = valorAPagar;
        this.fechaGestion = fechaGestion;
        this.gestion = gestion;
        this.satisfactorio = satisfactorio;
        this.codigoCarteraAsignada = codigoCarteraAsignada;
        this.codigoRecuperador = codigoRecuperador;
    }

    /**
     * @return the agencia
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * @param agencia the agencia to set
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    /**
     * @return the numeroCredito
     */
    public Long getNumeroCredito() {
        return numeroCredito;
    }

    /**
     * @param numeroCredito the numeroCredito to set
     */
    public void setNumeroCredito(Long numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    /**
     * @return the socio
     */
    public String getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(String socio) {
        this.socio = socio;
    }

    /**
     * @return the diasMora
     */
    public long getDiasMora() {
        return diasMora;
    }

    /**
     * @param diasMora the diasMora to set
     */
    public void setDiasMora(long diasMora) {
        this.diasMora = diasMora;
    }

    /**
     * @return the valorAPagar
     */
    public BigDecimal getValorAPagar() {
        return valorAPagar;
    }

    /**
     * @param valorAPagar the valorAPagar to set
     */
    public void setValorAPagar(BigDecimal valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    /**
     * @return the gestion
     */
    public String getGestion() {
        return gestion;
    }

    /**
     * @param gestion the gestion to set
     */
    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    /**
     * @return the satisfactorio
     */
    public String getSatisfactorio() {
        return satisfactorio;
    }

    /**
     * @param satisfactorio the satisfactorio to set
     */
    public void setSatisfactorio(String satisfactorio) {
        this.satisfactorio = satisfactorio;
    }

    /**
     * @return the responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * @return the codigoCarteraAsignada
     */
    public Long getCodigoCarteraAsignada() {
        return codigoCarteraAsignada;
    }

    /**
     * @param codigoCarteraAsignada the codigoCarteraAsignada to set
     */
    public void setCodigoCarteraAsignada(Long codigoCarteraAsignada) {
        this.codigoCarteraAsignada = codigoCarteraAsignada;
    }

    /**
     * @return the codigoRecuperador
     */
    public Long getCodigoRecuperador() {
        return codigoRecuperador;
    }

    /**
     * @param codigoRecuperador the codigoRecuperador to set
     */
    public void setCodigoRecuperador(Long codigoRecuperador) {
        this.codigoRecuperador = codigoRecuperador;
    }

    /**
     * @return the fechaGestion
     */
    public Date getFechaGestion() {
        return fechaGestion;
    }

    /**
     * @param fechaGestion the fechaGestion to set
     */
    public void setFechaGestion(Date fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    /**
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the detalleGestion
     */
    public List<CarteraGestionada> getDetalleGestion() {
        return detalleGestion;
    }

    /**
     * @param detalleGestion the detalleGestion to set
     */
    public void setDetalleGestion(List<CarteraGestionada> detalleGestion) {
        this.detalleGestion = detalleGestion;
    }
}
