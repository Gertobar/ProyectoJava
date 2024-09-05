/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.comunes;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author willan
 */
public class EntidadDataModel<W> extends LazyDataModel<W> {

    private final ConsultasCriterioConstructor criterioConstructor;
    private Class<W> clase;

    /**
     * Constructor del modelo
     *
     * @param em Unidad de persistencia
     * @param clase
     */
    public EntidadDataModel(EntityManager em, Class<W> clase) {
        this.clase = clase;
        criterioConstructor = new ConsultasCriterioConstructor(clase, em);
        this.setRowCount(Integer.valueOf(String.valueOf(criterioConstructor.cantidadRegistros())));
    }

    /**
     *      
     * @return devuelve la clase seteada
     */
    public Class<W> getClase() {
        return clase;
    }

    /**
     * Metodo para setear la clase
     * @param clase
     */
    public void setClase(Class<W> clase) {
        this.clase = clase;
    }

    /**
     * Permite la paginacion     
     * @param first
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param filters
     * @return
     */
    @Override
    public List<W> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<W> list = criterioConstructor.getListaPaginacion(first, pageSize, filters);
        if (filters != null && filters.size() > 0) {
            this.setRowCount(criterioConstructor.getNumeroRegistrosFiltro(filters));
        }
        return list;
    }
}
