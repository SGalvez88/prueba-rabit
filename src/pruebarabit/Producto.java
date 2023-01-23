package pruebarabit;

/**
 *
 * @author Sergio
 */
public class Producto {
    private String nombre;
  

    public Producto(String nombre) {
        this.nombre = nombre;
      
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

      
       
    @Override
    public String toString() {
        return "nombre: " + this.nombre ;
    }

}
