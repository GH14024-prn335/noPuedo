/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.EstadoVehiculo;

/**
 *
 * @author jonahdz
 */
@Stateless
public class EstadoVehiculoFacade extends AbstractFacade<EstadoVehiculo> implements EstadoVehiculoFacadeLocal {

    @PersistenceContext(unitName = "flota_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoVehiculoFacade() {
        super(EstadoVehiculo.class);
    }
    
}
