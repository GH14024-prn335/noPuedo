package acceso;

import java.util.List;
import javax.ejb.Local;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.SubTipoParte;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.TipoParte;

/**
 *
 * @author jonahdz
 */
@Local
public interface SubTipoParteFacadeLocal extends AbstractInterface<SubTipoParte> {
    
    List<TipoParte> listaTParte();
    
}
