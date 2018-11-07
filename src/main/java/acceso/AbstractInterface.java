package acceso;

import java.util.List;


/**
 *
 * @author jonahdz
 */
public interface AbstractInterface<T>  {
    
    boolean create(T Entity);

    boolean edit(T Entity);

    boolean remove(T Entity);

    T find(Object id);

    List<T> findAll();

    List<T> findRange(int desde, int hasta);

    int count();
    
}
