/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.SubTipoParte;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.TipoParte;

/**
 *
 * @author jonahdz
 */
@Stateless
public class SubTipoParteFacade extends AbstractFacade<SubTipoParte> implements SubTipoParteFacadeLocal {

    @PersistenceContext(unitName = "flota_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubTipoParteFacade() {
        super(SubTipoParte.class);
    }

    @Override
    public List<TipoParte> listaTParte() {
        List<TipoParte> lstTparte = new ArrayList<TipoParte>();
        try{
            Query query = em.createQuery("SELECT u FROM TipoParte u");
            lstTparte = query.getResultList();
            return lstTparte;
        }catch(Exception ex){
            return null;
        }
    }
    
    
    
}
