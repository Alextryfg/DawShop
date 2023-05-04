public class Pedidos {

    //Esta clase almacenará los pedidos con un id y el resto de datos del comprador, mas un importe final de la compra
    
    private String id;
    private String correoUser;
    private String precioTotal;

    //El constructor para crear el pedido actual

    public Pedidos(String id, String correoUser, String precioTotal) {
        this.id = id;
        this.correoUser = correoUser;
        this.precioTotal = precioTotal;
    }

    //Los setters y getter por si son necesarios a la hora de inyectar los datos en la BD.

    public String getId() {
        return this.id;
    }

    public String getCorreoUser() {
        return this.correoUser;
    }

    public String getPrecioTotal() {
        return this.precioTotal;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCorreoUser(String nombre) {
        this.correoUser= nombre;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

}
