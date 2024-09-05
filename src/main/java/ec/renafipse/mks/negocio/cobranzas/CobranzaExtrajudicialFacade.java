package ec.renafipse.mks.negocio.cobranzas;

import ec.renafipse.mks.modelo.cobranzas.CobranzaExtrajudicial;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CobranzaExtrajudicialFacade
  extends AbstractFacade<CobranzaExtrajudicial>
{
  @PersistenceContext(unitName="ec.renafipse.mksPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return em;
  }
  
  public CobranzaExtrajudicialFacade() {
    super(CobranzaExtrajudicial.class);
  }
  
  public List<CobranzaExtrajudicial> getListaCobranzaExtrajudicialPorIfip(Long codigoIfip)
  {
    List<CobranzaExtrajudicial> lista = null;
    Query query = em.createNamedQuery("CobranzaExtrajudicial.findByCodigoIfip", CobranzaExtrajudicial.class);
    query.setParameter("codigoIfip", codigoIfip);
    if (!query.getResultList().isEmpty()) {
      List<CobranzaExtrajudicial> resultado = query.getResultList();
      if (resultado.size() > 0)
        lista = resultado;
    }
    return lista;
  }
  
  public CobranzaExtrajudicial getCobranzaExtrajudicialPorNombre(Long codigoIfip, String nombre) {
    List<CobranzaExtrajudicial> lista = null;
    CobranzaExtrajudicial cobranzaExtrajudicial = null;
    Query query = em.createNamedQuery("CobranzaExtrajudicial.findByCodigoIfipYNombre", CobranzaExtrajudicial.class);
    query.setParameter("codigoIfip", codigoIfip);
    query.setParameter("nombre", nombre);
    if (!query.getResultList().isEmpty()) {
      List<CobranzaExtrajudicial> resultado = query.getResultList();
      if (resultado.size() > 0)
        lista = resultado;
    }
    if (lista != null) {
      cobranzaExtrajudicial = (CobranzaExtrajudicial)lista.get(0);
    }
    return cobranzaExtrajudicial;
  }
}