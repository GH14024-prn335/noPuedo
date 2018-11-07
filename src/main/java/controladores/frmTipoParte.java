
package controladores;

import acceso.TipoParteFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.TipoParte;

/**
 *
 * @author jonahdz
 */
@Named(value="frmTipoParte")
@ViewScoped
public class frmTipoParte implements Serializable {
    
    public void frmTipoParte(){}
    
    @EJB
    private TipoParteFacadeLocal facadeLocal;
    private LazyDataModel<TipoParte> lazymodel;
    private TipoParte registroEntity;
    private boolean botonAgregar = true;
    private boolean botones = false;
    private boolean seleccion = false;

    @PostConstruct
    private void inicio() {

        registroEntity = new TipoParte();

        try {
            this.lazymodel = new LazyDataModel<TipoParte>() {
                
                @Override
                public Object getRowKey(TipoParte object) {
                    if (object != null) {
                        return object.getIdTipoParte();
                    }
                    return null;
                }
                
                @Override
                public TipoParte getRowData(String rowKey) {
                    if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                        try {
                            Integer buscado = new Integer(rowKey);
                            for (TipoParte reg : (List<TipoParte>) getWrappedData()) {
                                if (reg.getIdTipoParte().compareTo(buscado) == 0) {
                                    return reg;
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
                    }
                    return null;
                }
                
                @Override
                public List<TipoParte> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<TipoParte> salida = new ArrayList();
                    try {
                        if (facadeLocal != null) {
                            this.setRowCount(facadeLocal.count());
                            salida = facadeLocal.findRange(first,pageSize);
                        }
                    } catch (Exception e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                    }
                    return salida;
                }
            };
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    /**
     *  Este metodo Domina la Funcionalidad del boton guardar mostrado en la pagina web index.xhtml 
     *  verifica que los registros que han sido ingresados en el formulario no sean nulos para asi no 
     *  generar ninguna excepcion en caso de excepcion no guardara el registro  es capturado por try-catch 
     *  y enviado para notificar el error al cliente.
     */
    public void guardar() {
        try {
            if (this.facadeLocal != null && this.registroEntity != null ) {
               if(this.facadeLocal.create(registroEntity)) {
                    inicio();
                }
            }
        } catch (Exception exp) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, exp.getMessage(),exp);
        }
    }

    /**
     *  Este metodo Domina la Funcionalidad del boton eliminar mostrado en la pagina web index.xhtml 
     *  verifica que los registros que han sido ingresados nos sean nulos para asi no generar excepcion
     *  en caso de excepcion es capturado por try-catch y enviado para notificar el error al cliente.
     */
    public void Eliminar() {
        try {
            if (this.registroEntity != null && this.facadeLocal != null) {
                if (this.facadeLocal.remove(registroEntity)) {
                    this.registroEntity = new TipoParte();
                    this.botones = false;
                    this.botonAgregar = true;
                    inicio();
                }
            }
        } catch (Exception exp) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, exp.getMessage(),exp);
        }
    }

    /**
     *  Este metodo Domina la Funcionalidad del boton modificar mostrado en la pagina web index.xhtml 
     *  verifica que los registros que han sido ingresados y que son requisitos no sean nulos para asi 
     *  no generar excepcion en caso de no cumplir con esto se genera una excepcion es capturado por 
     *  try-catch y enviado para notificar el error al cliente.
     */
    public void Modificar() {
        try {
            if (this.registroEntity != null && this.facadeLocal != null) {
                if (this.facadeLocal.edit(registroEntity)) {
                    this.botones = false;
                    this.botonAgregar = true;
                    inicio();  
                }
            }
        } catch (Exception exp) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, exp.getMessage(),exp);
        }
    }

    /**
     *  Este metodo Domina la Funcionalidad de los botones agregar y cancelar mostrado en la pagina web 
     *  index.xhtml cuando esta en inicio normal lo cual es dinamico mientras se necesite hacer diferentes
     *  operaciones, transacciones o mantenimiento de la entidad.
     */
    public void cambiarSeleccion() {
        this.botones = true;
        this.botonAgregar = false;    
    }
    
    /**
     *  El Metodo cancelar elimina los posibles registros que hayan sido ingresados en el formulario y muestra
     *  los botones editar y cancelar como se muestra al inicio de la pagina para empezar un nuevo registro o 
     *  realizar otra operacion con la pagina de mantenimiento de la entidad.
     */
    public void cancelar() {
        this.registroEntity = new TipoParte();
        this.botones=false;
        this.botonAgregar=true;
    }

    public TipoParteFacadeLocal getFacadeLocal() {
        return facadeLocal;
    }

    public void setFacadeLocal(TipoParteFacadeLocal facadeLocal) {
        this.facadeLocal = facadeLocal;
    }

    public LazyDataModel<TipoParte> getLazymodel() {
        return lazymodel;
    }

    public void setLazymodel(LazyDataModel<TipoParte> lazymodel) {
        this.lazymodel = lazymodel;
    }

    public TipoParte getRegistroEntity() {
        return registroEntity;
    }

    public void setRegistroEntity(TipoParte registroEntity) {
        this.registroEntity = registroEntity;
    }

    public boolean isBotonAgregar() {
        return botonAgregar;
    }

    public void setBotonAgregar(boolean botonAgregar) {
        this.botonAgregar = botonAgregar;
    }

    public boolean isBotones() {
        return botones;
    }

    public void setBotones(boolean botones) {
        this.botones = botones;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    
    
    
    
}
