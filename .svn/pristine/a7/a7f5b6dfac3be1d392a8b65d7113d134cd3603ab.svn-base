/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.clases.personas.PersonaListaNegra;
import ec.renafipse.mks.modelo.comunes.ConsultasCriterioConstructor;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.OficialCumplimiento;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.TipoIdentificacion;
import ec.renafipse.mks.modelo.socios.TipoPersona;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TemporalType;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vicastoc
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     *
     * @param identificacion
     * @return
     */
    public List<Persona> getItemsPersonaIdentificacion(String identificacion) {
        Query query = this.em.createNamedQuery(Persona.findByIdentificacion);
        query.setParameter("identificacion", identificacion);
        return query.getResultList();
    }

    /**
     *
     * @param criterio
     * @param buscarPor
     * @return
     */
    public List<Persona> getItemsPersona(String criterio, String buscarPor, char esParaInstitucion) {
        List<Persona> listPersonas = null;

        if (buscarPor.equals("N")) {
            Query query = this.em.createNamedQuery(Persona.findByNombreCompletoEsParaInstitucion);
            query.setParameter("nombreCompleto", criterio.toUpperCase());
            query.setParameter("esParaInstitucion", esParaInstitucion);
            listPersonas = query.getResultList();
        }

        if (buscarPor.equals("I")) {
            Query query = this.em.createNamedQuery(Persona.findByIdentificacion);
            query.setParameter("identificacion", criterio);
            listPersonas = query.getResultList();
        }

        return listPersonas;
    }

    public Persona getPersona(String identificacion) {
        List<Persona> listaPersona = em.createNamedQuery(Persona.findByIdentificacion).setParameter("identificacion", identificacion).getResultList();
        if (listaPersona.size() == 1) {
            return (Persona) listaPersona.get(0);
        }
        return null;
    }

    /**
     *
     * @param identificacion
     * @return
     */
    public List<Persona> getItemsIdenPersona(String identificacion) {
        Query query = this.em.createNamedQuery(Persona.findByIdentificacion);
        query.setParameter("identificacion", identificacion);
        return query.getResultList();
    }

    public List<Persona> getItemsByCodigo(Long codigo) {
        Query query = this.em.createNamedQuery(Persona.findByCodigo);
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }

    public Persona getPersonaByCodigo(Long codigo) {
        Query query = this.em.createNamedQuery(Persona.findByCodigo);
        query.setMaxResults(1);
        query.setParameter("codigo", codigo);
        List results = query.getResultList();
        if (!results.isEmpty()) {
            return (Persona) results.get(0);
        } else {
            return null;
        }
    }

    /**
     * Actualiza datos de la persona
     *
     * @param codigoTipoIdentificacion Codigo de Tipo de Identificacion
     * @param codigoTipoPersona Codigo del Tipo de Persona
     * @param identificacion Numero de Identificacion
     * @param nombreCompleto Nombre Completo de la Persona
     * @param fechaActualizacion Fecha de Actualizacion
     * @param correoElectronico Coreo Electrónico
     * @param fechaCaducidadIdentificacion Fecha de Caducidad de la CEdula
     * @param codigo Codigo de la Persona
     */
    @Transactional
    public void actualiza(TipoIdentificacion codigoTipoIdentificacion, TipoPersona codigoTipoPersona, String identificacion, String nombreCompleto, Date fechaActualizacion, String correoElectronico, Date fechaCaducidadIdentificacion, long codigo) {
        System.out.println(codigoTipoIdentificacion + " " + codigoTipoPersona + " " + identificacion + " " + nombreCompleto + " " + fechaActualizacion + " " + correoElectronico + " " + fechaCaducidadIdentificacion + " " + codigo);
        Query query = em.createQuery("UPDATE Persona c "
                + "SET codigoTipoIdentificacion = :codigoTipoIdentificacion, \n"
                + "codigoTipoPersona = :codigoTipoPersona, \n"
                + "identificacion = :identificacion, \n"
                + "nombreCompleto = :nombreCompleto, \n"
                + "fechaActualizacion = :fechaActualizacion, \n"
                + "correoEletronico = :correoElectronico, \n"
                + "fechaCaducidadIdentificacion = :fechaCaducidadIdentificacion \n"
                + "WHERE codigo = :codigo");
        query.setParameter("codigoTipoIdentificacion", codigoTipoIdentificacion);
        query.setParameter("codigoTipoPersona", codigoTipoPersona);
        query.setParameter("identificacion", identificacion);
        query.setParameter("nombreCompleto", nombreCompleto);
        query.setParameter("fechaActualizacion", fechaActualizacion, TemporalType.TIMESTAMP);
        query.setParameter("correoElectronico", correoElectronico);
        query.setParameter("fechaCaducidadIdentificacion", fechaCaducidadIdentificacion, TemporalType.DATE);
        query.setParameter("codigo", codigo);
        query.executeUpdate();
    }

    /**
     * Procedimiento que permite consultar a la base de datos y luego mapear a
     * una clase de objectos con la información encontrada
     *
     * @param nombresOIdentificacion parametro de entrada que puede ser los
     * nombres o la identificación
     * @return devuelve un objeto con los datos
     */
    public PersonaListaNegra consultaPersonaListasNegras(String nombresOIdentificacion) {
        PersonaListaNegra personaListaNegra = new PersonaListaNegra();
        if (nombresOIdentificacion == null) {
            return personaListaNegra;
        }
        if (nombresOIdentificacion.isEmpty()) {
            return personaListaNegra;
        }
        if (nombresOIdentificacion.trim().isEmpty()) {
            return personaListaNegra;
        }
        Object[] datoObtenido = new Object[3];
        try {
            StoredProcedureQuery procedimientoEnviaCorreo = em.createStoredProcedureQuery("mks_historicos.pkm_uaf_carga.p_busca_listas_uaf");
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_identificacion_nom", String.class, ParameterMode.IN);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pv_identificacion", String.class, ParameterMode.OUT);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pv_nombres", String.class, ParameterMode.OUT);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pn_tipo_uaf", String.class, ParameterMode.OUT);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pv_error_sql", String.class, ParameterMode.OUT);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pv_error_code", String.class, ParameterMode.OUT);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pv_error", String.class, ParameterMode.OUT);
            procedimientoEnviaCorreo.setParameter("pt_identificacion_nom", nombresOIdentificacion);
            procedimientoEnviaCorreo.execute();

            datoObtenido[0] = (String) procedimientoEnviaCorreo.getOutputParameterValue("pv_identificacion");
            datoObtenido[1] = (String) procedimientoEnviaCorreo.getOutputParameterValue("pv_nombres");
            datoObtenido[2] = procedimientoEnviaCorreo.getOutputParameterValue("pn_tipo_uaf").toString();
        } catch (Exception localException) {            
            return personaListaNegra;
        }
        try {
            if (datoObtenido == null) {
                return personaListaNegra;
            }
            personaListaNegra.setIdentificacion(datoObtenido[0].toString());
            personaListaNegra.setNombres(datoObtenido[1].toString());
            if (Integer.parseInt(datoObtenido[2].toString()) == 1) {
                personaListaNegra.setTipoPersona(1);
            } else if (Integer.parseInt(datoObtenido[2].toString()) == 2) {
                personaListaNegra.setTipoPersona(2);
            } else if (Integer.parseInt(datoObtenido[2].toString()) == 3) {
                personaListaNegra.setTipoPersona(3);
            }
        } catch (Exception localException1) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Estado de evio...." + localException1);
        }
        return personaListaNegra;
    }

    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
    public List<Persona> getItemsPersonaIden() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param identificacion
     * @return
     */
    public List<Persona> getItemsPersonaIdentificacionPersonaNatural(String identificacion) {
        Query query = this.em.createNamedQuery("Persona.findByIdentificacionPersonaNatural", Persona.class);
        query.setParameter("identificacion", identificacion);
        return query.getResultList();
    }

    /**
     *
     * @param nombreCompleto
     * @return
     */
    public List<Persona> getItemsPersonaNombreCompleto(String nombreCompleto) {
        Query query = this.em.createNamedQuery("Persona.findByNombreCompleto", Persona.class);
        query.setParameter("nombreCompleto", nombreCompleto);
        return query.getResultList();
    }

    /**
     *
     * @param nombreCompleto
     * @return
     */
    public List<Persona> getItemsPersonaNombreCompletoPersonaNatural(String nombreCompleto) {
        Query query = this.em.createNamedQuery("Persona.findByNombreCompletoPersonaNatural", Persona.class);
        query.setParameter("nombreCompleto", nombreCompleto);
        return query.getResultList();
    }

    public void enviarEmail() {
        /*mks_historicos.pkm_uaf_email.envia_email(pv_nombres => :pv_nombres,
                                           pv_identificacion => :pv_identificacion,
                                           pv_listas_uaf => :pv_listas_uaf,
                                           pv_codigo_usuario => :pv_codigo_usuario,
                                           pv_observacion => :pv_observacion,
                                           pv_asunto => :pv_asunto,
                                           pv_error_sql => :pv_error_sql,
                                           pv_error_code => :pv_error_code,
                                           pv_error => :pv_error);*/
    }

    /**
     *
     * @param codigoServicio
     * @param codigoUsuario
     * @param codigoEstadoMensaje
     * @param destino
     * @param origen
     * @param mensaje
     * @param fecha
     * @param eliminado
     * @param observacion
     * @param asunto
     * @return
     */
    public boolean enviarEmail(
            long codigoServicio,
            long codigoUsuario,
            long codigoEstadoMensaje,
            String destino,
            String origen,
            String mensaje,
            Date fecha,
            String eliminado,
            String observacion,
            String asunto
    ) {
        boolean enviado = false;
        try {
            Query consultaNativa = this.em.createNativeQuery("SELECT mks_mensajeria.pkm_mensajeria.f_crear_html_mensaje_correo('<br>" + mensaje + "') FROM  DUAL");
            String mensajeFormateado = clobToString((Clob) consultaNativa.getSingleResult());
            StoredProcedureQuery procedimientoEnviaCorreo = em.createStoredProcedureQuery("mks_mensajeria.pkm_mensajeria.p_inserta_solicitud_mensaje");
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_codigo_servicio_men_can", Long.class, ParameterMode.IN);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_codigo_usuario", Long.class, ParameterMode.IN);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_codigo_estado_mensaje", Long.class, ParameterMode.IN);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_destino", String.class, ParameterMode.IN);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_origen", String.class, ParameterMode.IN);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_mensaje", String.class, ParameterMode.IN);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_fecha", Date.class, ParameterMode.IN);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_eliminado", String.class, ParameterMode.IN);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_observacion", String.class, ParameterMode.IN);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_asunto", String.class, ParameterMode.IN);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pt_codigo_solicitud_mensaje", Long.class, ParameterMode.OUT);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pv_error_sql", String.class, ParameterMode.OUT);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pv_error_code", String.class, ParameterMode.OUT);
            procedimientoEnviaCorreo.registerStoredProcedureParameter("pv_error", String.class, ParameterMode.OUT);
            procedimientoEnviaCorreo.setParameter("pt_codigo_servicio_men_can", codigoServicio);
            procedimientoEnviaCorreo.setParameter("pt_codigo_usuario", codigoUsuario);
            procedimientoEnviaCorreo.setParameter("pt_codigo_estado_mensaje", codigoEstadoMensaje);
            procedimientoEnviaCorreo.setParameter("pt_destino", destino);
            procedimientoEnviaCorreo.setParameter("pt_origen", origen);
            procedimientoEnviaCorreo.setParameter("pt_mensaje", mensajeFormateado);
            procedimientoEnviaCorreo.setParameter("pt_fecha", fecha);
            procedimientoEnviaCorreo.setParameter("pt_eliminado", eliminado);
            procedimientoEnviaCorreo.setParameter("pt_observacion", observacion);
            procedimientoEnviaCorreo.setParameter("pt_asunto", asunto);
            procedimientoEnviaCorreo.execute();
            String error1 = (String) procedimientoEnviaCorreo.getOutputParameterValue("pv_error_sql");
            String error2 = (String) procedimientoEnviaCorreo.getOutputParameterValue("pv_error_code");
            String error3 = (String) procedimientoEnviaCorreo.getOutputParameterValue("pv_error");
            if (error1 != null) {
                enviado = false;
            }
            if (error2 != null) {
                enviado = false;
            }
            if (error3 != null) {
                enviado = false;
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Estado de evio...." + enviado);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error " + e.getMessage());
        }
        return enviado;
    }

    /**
     * Clob to string.
     *
     * @param data the data
     * @return the string
     * @throws SQLException the SQL exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String clobToString(Clob data) throws SQLException, IOException {
        StringBuilder sb = new StringBuilder();
        Reader reader = data.getCharacterStream();
        BufferedReader br = new BufferedReader(reader);
        String line;
        while (null != (line = br.readLine())) {
            sb.append(line);
        }
        br.close();

        return sb.toString();
    }

    /**
     * Permite obtener los datos del oficial de cumplimiento
     *
     * @return devuelve los datos del oficial de cumplimiento como nombres y
     * correo
     */
    public OficialCumplimiento obtenerOficialCumplimiento() {
        OficialCumplimiento datosOficialCumplimiento = new OficialCumplimiento();
        try {
            ConsultasCriterioConstructor<OficialCumplimiento> consulta = new ConsultasCriterioConstructor<OficialCumplimiento>(OficialCumplimiento.class, em);
            datosOficialCumplimiento = consulta.consultaYVariasColumnas(Arrays.asList("fechaSalida"), Arrays.asList(""));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"PersonaFacade", "obtenerOficialCumplimiento", CapturaError.getErrorException(e)});
        }
        return datosOficialCumplimiento;
    }
    
    /**
     * Metodo que permite obtener la agencia a partir del codigo
     * @param codigoAgencia determina el codigo de agencia
     * @return devuelve la agencia encontrada
     */
    public IfipAgencia obtenerAgencia(long codigoAgencia){
        IfipAgencia agencia=new IfipAgencia();
        try {
            ConsultasCriterioConstructor<IfipAgencia> consulta = new ConsultasCriterioConstructor<IfipAgencia>(IfipAgencia.class, em);
            agencia = consulta.consultaYVariasColumnas(Arrays.asList("codigo"), Arrays.asList(codigoAgencia));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"IfipAgencia", "obtenerAgencia", CapturaError.getErrorException(e)});
        }
        return agencia;
    }
}
