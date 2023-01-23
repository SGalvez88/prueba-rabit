
package pruebarabit;

import java.util.ArrayList;


/**
 *
 * @author Sergio
 */
public class Pedido {
    private String idPedido;
    private ArrayList<String> listaProductos;

    public Pedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public ArrayList<String> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<String> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    @Override
    public String toString() {
        return this.idPedido + " - " + this.listaProductos;
    }
    
}
