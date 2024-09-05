package ec.renafipse.mks.negocio.cobranzas;

import ec.renafipse.mks.modelo.cobranzas.CobranzaExtrajudicialDet;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CobranzaExtrajudicialDetFacade
  extends AbstractFacade<CobranzaExtrajudicialDet>
{
  @PersistenceContext(unitName="ec.renafipse.mksPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return em;
  }
  
  public CobranzaExtrajudicialDetFacade() {
    super(CobranzaExtrajudicialDet.class);
  }
  
  public List<CobranzaExtrajudicialDet> getListaCobranzaExtrajudicialDetPorCobranza(Long codigoCobranza) {
    List<CobranzaExtrajudicialDet> lista = null;
    Query query = em.createNamedQuery("CobranzaExtrajudicialDet.findByCodigoCobranza", CobranzaExtrajudicialDet.class);
    query.setParameter("codigoCobranza", codigoCobranza);
    if (!query.getResultList().isEmpty()) {
      List<CobranzaExtrajudicialDet> resultado = query.getResultList();
      if (resultado.size() > 0)
        lista = resultado;
    }
    return lista;
  }
}