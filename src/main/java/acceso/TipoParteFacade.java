package acceso;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.TipoParte;

/**
 *
 * @author jonahdz
 */
@Stateless
public class TipoParteFacade extends AbstractFacade<TipoParte> implements TipoParteFacadeLocal {

    @PersistenceContext(unitName = "flota_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoParteFacade() {
        super(TipoParte.class);
    }
    
}
