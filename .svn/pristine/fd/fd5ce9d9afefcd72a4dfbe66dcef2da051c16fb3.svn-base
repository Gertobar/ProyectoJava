/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.comunes;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaQuery;

import java.math.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.engine.spi.TypedValue;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author willan
 * @param <T>
 */
public class ConsultasCriterioConstructor<T> {

    private T entidad;
    private Root<T> entidadRoot;
    private CriteriaQuery<T> criterioConsulta;
    private Class<T> clase;
    private CriteriaBuilder criterioConstructor;
    private final EntityManager em;
    java.math.BigDecimal ZERO = java.math.BigDecimal.ZERO;
    private Session session;

    private LazyDataModel<T> model;

    public ConsultasCriterioConstructor(Class<T> clase, EntityManager em) {
        super();
        this.clase = clase;
        this.em = em;
        armarConsulta();
    }

    public ConsultasCriterioConstructor(Class<T> clase, EntityManager em, boolean esParaConsulta) {
        super();
        this.clase = clase;
        this.em = em;
        session = em.unwrap(Session.class);
    }

    private void armarConsulta() {
        criterioConstructor = em.getCriteriaBuilder();
        criterioConsulta = criterioConstructor.createQuery(clase);
        entidadRoot = criterioConsulta.from(clase);
        criterioConsulta.select(entidadRoot);
        session = em.unwrap(Session.class);
    }

    /**
     * Metodo que devuelve la entidad parametrizada
     *
     * @return devuelve la entidad parametrizada
     */
    public T getEntidad() {
        return entidad;
    }

    /**
     * Metodo para setear la entidad parametrizada
     *
     * @param entidad entidad a setear
     */
    public void setEntidad(T entidad) {
        this.entidad = entidad;
    }

    /**
     * Metodo que permite obtener la entidad nucleo para armar mas consultas
     *
     * @return devuelve la entidad nucleo
     */
    public Root<T> getEntidadRoot() {
        return entidadRoot;
    }

    /**
     * Metodo para setear la entidad nucleo
     *
     * @param entidadRoot parametro de entidad nucleo
     */
    public void setEntidadRoot(Root<T> entidadRoot) {
        this.entidadRoot = entidadRoot;
    }

    /**
     * Metodo que permite obtener la clase parametrizada
     *
     * @return devuelve la clase parametrizada
     */
    public Class<T> getClase() {
        return clase;
    }

    /**
     * Metodo que permite establecer la clase para las respectivas consultas
     *
     * @param clase clase a utilizarse en las consultas
     */
    public void setClase(Class<T> clase) {
        this.clase = clase;
    }

    /**
     * Metodo para obtener el criterio del constructo necesario para las
     * consultas
     *
     * @return devuelve el criterio de constructor
     */
    public CriteriaBuilder getCriterioConstructor() {
        return criterioConstructor;
    }

    /**
     * Metodo para setear el criterio del constructor para una consulta
     * personalizada
     *
     * @param criterioConstructor parametro de criterio de constructor
     */
    public void setCriterioConstructor(CriteriaBuilder criterioConstructor) {
        this.criterioConstructor = criterioConstructor;
    }

    /**
     * Metodo que permite obtener la contidad de registros de una consulta
     *
     * @return devuelve un entero largo "Long" como resultado
     */
    public Long cantidadRegistros() {
        try {
            criterioConstructor = em.getCriteriaBuilder();
            CriteriaQuery<Long> criterioConsultaCantidad = criterioConstructor.createQuery(Long.class);
            criterioConsultaCantidad.select(criterioConstructor.count(criterioConsultaCantidad.from(clase)));
            return em.createQuery(criterioConsultaCantidad).getSingleResult();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            return 0L;
        }

    }

    /**
     * Metodo que permite obtener la contidad de registros de una consulta
     *
     * @return devuelve un entero como resultado
     */
    public Integer cantidadRegistrosEntero() {
        try {
            criterioConstructor = em.getCriteriaBuilder();
            CriteriaQuery<Integer> criterioConsultaCantidad = criterioConstructor.createQuery(Integer.class);
            entidadRoot = criterioConsultaCantidad.from(clase);
            criterioConsultaCantidad
                    .select(criterioConstructor.count(criterioConsultaCantidad.from(clase)).as(Integer.class));
            return em.createQuery(criterioConsultaCantidad).getSingleResult();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Metodo para obtener el maximo numero de registros
     *
     * @param columna columna a buscar
     * @return devuelve un enterio como valor de resultado
     */
    public Integer maximoRegistro(String columna) {
        try {
            criterioConstructor = em.getCriteriaBuilder();
            CriteriaQuery<Integer> criterioConsultaMax = criterioConstructor.createQuery(Integer.class);
            entidadRoot = criterioConsultaMax.from(clase);
            criterioConsultaMax.select(criterioConstructor.max(entidadRoot.get(columna).as(Integer.class)));
            return em.createQuery(criterioConsultaMax).getSingleResult();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Metodo para obtener el maximo numero de registros
     *
     * @param columna columna a buscar
     * @return devuelve un enterio como valor de resultado
     */
    public Long maximoRegistroLong(String columna) {
        try {
            criterioConstructor = em.getCriteriaBuilder();
            CriteriaQuery<Long> criterioConsultaMax = criterioConstructor.createQuery(Long.class);
            entidadRoot = criterioConsultaMax.from(clase);
            criterioConsultaMax.select(criterioConstructor.max(entidadRoot.get(columna).as(Long.class)));
            return em.createQuery(criterioConsultaMax).getSingleResult();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            return 0l;
        }
    }

    /**
     * Metodo para obtener el maximo numero de registros
     *
     * @param columna columna a buscar
     * @return devuelve un valor de tipo BigDecimal
     */
    public BigDecimal maximoRegistroValor(String columna) {
        try {
            criterioConstructor = em.getCriteriaBuilder();
            CriteriaQuery<Double> criterioConsultaMaxValor = criterioConstructor.createQuery(Double.class);
            entidadRoot = criterioConsultaMaxValor.from(clase);
            criterioConsultaMaxValor.select(criterioConstructor.max(entidadRoot.get(columna).as(Double.class)));
            return new BigDecimal(em.createQuery(criterioConsultaMaxValor).getSingleResult());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            return ZERO;
        }

    }

    /**
     * Metodo que permite obtener el primer dato encontrado
     *
     * @param columna columna de la clase a buscar
     * @param dato objeto instanciado de tipo clase para buscar
     * @return devuelve el primer dato obtenido o nulo si no lo encontro
     */
    public T consultaPrimerDato(String columna, Object dato) {
        criterioConsulta.where(criterioConstructor.equal(entidadRoot.get(columna), dato));
        criterioConsulta.orderBy(criterioConstructor.desc(entidadRoot.get(columna)));
        List<T> lista = em.createQuery(criterioConsulta).getResultList();
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    /**
     * Metodo que permite obtener el primer dato encontrado
     *
     * @param columna columna de la clase a buscar
     * @param dato objeto instanciado de tipo clase para buscar
     * @return devuelve una lisata de registros filtrado por columna
     */
    public List<T> consultaTodosDatos(String columna, Object dato) {
        criterioConsulta.where(criterioConstructor.equal(entidadRoot.get(columna), dato));
        List<T> lista = em.createQuery(criterioConsulta).getResultList();
        return lista;
    }

    /**
     * Metodo que permite obtener el primer dato encontrado
     *
     *
     * @return devuelve una lisata de registros
     */
    public List<T> consultaTodosDatos() {
        TypedQuery findAllBooks = em.createQuery(criterioConsulta);

        String consulta = findAllBooks.unwrap(org.hibernate.Query.class).getQueryString();
        List<T> lista = em.createQuery(criterioConsulta).getResultList();
        return lista;
    }

    /**
     * Metodo que permite obtener el primer dato encontrado
     *
     * @param columna columna de la clase a buscar
     * @param dato objeto instanciado de tipo clase para buscar
     * @return devuelve el primer dato obtenido o nulo si no lo encontro
     */
    public T consultaPrimerDatoLike(String columna, String dato) {
        criterioConsulta.where(criterioConstructor.like(entidadRoot.get(columna).as(String.class), dato));
        List<T> lista = em.createQuery(criterioConsulta).getResultList();
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }

    }

    /**
     * Metodo para obtener los valores por defecto de los parametros generales
     *
     * @return devuelte el primer registro de la entidad encontrada
     */
    public T parametros() {
        return em.createQuery(criterioConsulta).getSingleResult();
        // return
        // em.createQuery(criterioConsulta).getResultList().get(0);
    }

    /**
     * Metodo que permite realizar consultas de varios campos con el modificador
     * Y
     *
     * @param columnas columnas que se desea buscar
     * @param datos datos de las columnas buscadas
     * @return devuelve el primer dato encontrado
     */
    public T consultaYVariasColumnas(List<String> columnas, List<Object> datos) {
        Predicate[] predicadoList = new Predicate[columnas.size()];
        int i = 0;
        try {           
            for (String columna : columnas) {               
                Path<Tuple> tuple = entidadRoot.<Tuple>get(columna);
                if (tuple.getJavaType().isAssignableFrom(Date.class)) {
                    if (datos.get(i).toString().isEmpty()) {
                        Predicate predicado = criterioConstructor.isNull(entidadRoot.get(columna));
                        predicadoList[i] = predicado;
                    } else {
                        //Correcion de busqueda por fecha
                        ParameterExpression<java.util.Date> parameter = criterioConstructor.parameter(java.util.Date.class);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        java.util.Date startDate = dateFormat.parse(getFechaTruncada((Date) datos.get(i)));                        
                        Predicate predicado =  criterioConstructor.greaterThan(entidadRoot.get(columna).as(java.sql.Date.class), startDate);                        
                        predicadoList[i] = predicado;
                    }
                } else {
                    if (datos.get(i) == null) {
                        Predicate predicado = criterioConstructor.isNull(entidadRoot.get(columna));
                        predicadoList[i] = predicado;

                    } else if (datos.get(i).toString().isEmpty()) {
                        Predicate predicado = criterioConstructor.isNull(entidadRoot.get(columna));
                        predicadoList[i] = predicado;

                    } else {
                        Predicate predicado = criterioConstructor.equal(entidadRoot.get(columna), datos.get(i));
                        predicadoList[i] = predicado;

                    }
                }

            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, " error " + e);
        }       
        criterioConsulta.where(criterioConstructor.and(predicadoList));        
        TypedQuery findAllBooks = em.createQuery(criterioConsulta);
        String consulta = findAllBooks.unwrap(org.hibernate.Query.class).getQueryString();       
        if (em.createQuery(criterioConsulta).getResultList().isEmpty()) {
            return null;
        }
        return em.createQuery(criterioConsulta).getResultList().get(0);
    }

    /**
     * Metodo que permite realizar consultas de varios campos con el modificador
     * O
     *
     * @param columnas columnas que se desea buscar
     * @param datos datos de las columnas buscadas
     * @return devuelve el primer dato encontrado
     */
    public T consultaOVariasColumnas(List<String> columnas, List<Object> datos) {
        Predicate[] predicadoList = new Predicate[columnas.size()];
        int i = 0;
        for (String columna : columnas) {
            Predicate predicado = criterioConstructor.equal(entidadRoot.get(columna), datos.get(i));
            predicadoList[i] = predicado;
            i++;
        }
        criterioConsulta.where(criterioConstructor.or(predicadoList));
        return em.createQuery(criterioConsulta).getResultList().get(0);
    }

    /**
     * Metodo que permite actualizar los parametros generales
     *
     * @param entidadActualizar establece que entidad se requiere actualizar
     *
     */
    public void actualizaNumeroEntidad(Object entidadActualizar) {
        em.merge(entidadActualizar);
    }

    /**
     *
     * @param columna
     * @param columnas
     * @param datos
     * @return devuelve el maximo registro a traves de un where
     */
    public Long maximoRegistroValorWhere(String columna, List<String> columnas, List<Object> datos) {
        try {
            criterioConstructor = em.getCriteriaBuilder();
            Predicate[] predicadoList = new Predicate[columnas.size()];

            CriteriaQuery<Long> criterioConsultaMaxValor = criterioConstructor.createQuery(Long.class);
            entidadRoot = criterioConsultaMaxValor.from(clase);
            int i = 0;
            for (String columnaLinea : columnas) {
                Predicate predicado = criterioConstructor.equal(entidadRoot.get(columnaLinea), datos.get(i));
                predicadoList[i] = predicado;
                i++;
            }
            criterioConsultaMaxValor.select(criterioConstructor.coalesce(
                    criterioConstructor.max(entidadRoot.get(columna).as(Long.class)), criterioConstructor.literal(0l)));
            criterioConsultaMaxValor.where(criterioConstructor.and(predicadoList));

            return new Long(em.createQuery(criterioConsultaMaxValor).getSingleResult());

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            return 0L;
        }
    }

    /**
     *
     * @return devuelve la entidad creada o actualizada
     */
    public T guardarOActualizar(T entidadActualizar) {
        session.saveOrUpdate(entidadActualizar);
        return entidadActualizar;
    }

    /**
     * Metodo para formatear una fecha y truncar cuando tiene hora minuto y
     * segundo
     *
     * @param date
     * @return devuelve la fecha truncada
     */
    public String getFechaTruncada(java.util.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }

    /**
     * Metodo que permite mostra un listado de datos de una consulta nativa
     *
     * @param sql
     * @return devuelve un listado
     */
    public List obtenerDatosPersonalizadosConsultaNativa(String sql, String parametro) {
        List listaDatos = new ArrayList();
        try {
            listaDatos = session.createSQLQuery(sql)
                    .setParameter("parametro", parametro).list();
        } catch (Exception e) {
        }
        return listaDatos;
    }

    /**
     *
     * @return devuelve la sesion instanciada
     */
    public Session getSession() {
        return session;
    }

    /**
     *
     * @param session
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Permite paginar un listado de datos
     *
     * @param inicio
     * @param tamanio
     * @param filtros
     * @return
     */
    public List<T> getListaPaginacion(int inicio, int tamanio,
            Map<String, String> filtros) {

        if (filtros != null && filtros.size() > 0) {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> entry : filtros.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    continue;
                }

                Expression<String> expr = entidadRoot.get(field).as(String.class);
                Predicate p = criterioConstructor.like(criterioConstructor.lower(expr),
                        "%" + value.toString().toLowerCase() + "%");
                predicates.add(p);
            }
            if (predicates.size() > 0) {
                criterioConsulta.where(criterioConstructor.and(predicates.toArray(new Predicate[predicates.size()])));
            }
        }

        TypedQuery<T> query = em.createQuery(criterioConsulta);
        query.setFirstResult(inicio);
        query.setMaxResults(tamanio);
        List<T> list = query.getResultList();
        return list;
    }

    /**
     * Obtiene la cantidad de registros por filtro
     *
     * @param filtros
     * @return
     */
    public int getNumeroRegistrosFiltro(Map<String, String> filtros) {
        CriteriaQuery<Long> criteriaQuery = criterioConstructor.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(clase);
        CriteriaQuery<Long> select = criteriaQuery.select(criterioConstructor.count(root));

        if (filtros != null && filtros.size() > 0) {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> entry : filtros.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    continue;
                }
                Expression<String> expr = root.get(field).as(String.class);
                Predicate p = criterioConstructor.like(criterioConstructor.lower(expr),
                        "%" + value.toString().toLowerCase() + "%");
                predicates.add(p);
            }
            if (predicates.size() > 0) {
                criteriaQuery.where(criterioConstructor.and(predicates.toArray(new Predicate[predicates.size()])));
            }
        }
        Long count = em.createQuery(select).getSingleResult();
        return count.intValue();
    }

    class DateLikeExpression implements Criterion {

        private static final long serialVersionUID = 1L;
        private String propertyName;
        private String value;

        public DateLikeExpression(String propertyName, String value) {
            this.propertyName = propertyName;
            this.value = value;
        }

        @Override
        public String toSqlString(Criteria criteria, org.hibernate.criterion.CriteriaQuery criteriaQuery) throws HibernateException {
            String[] columns = criteriaQuery.getColumnsUsingProjection(criteria, propertyName);
            if (columns.length != 1) {
                throw new HibernateException("Like may only be used with single-column properties");
            }
            return "to_char(" + columns[0] + ", 'DD/MM/YYYY HH24:MI') like ?";
        }

        @Override
        public TypedValue[] getTypedValues(Criteria crtr, org.hibernate.criterion.CriteriaQuery cq) throws HibernateException {
            return new TypedValue[]{new TypedValue(new org.hibernate.type.StringType(),
                MatchMode.START.toMatchString(value.toLowerCase()), EntityMode.POJO)};
        }

    }
}
