public class Pedidos {

    //Esta clase almacenará los pedidos con un id y el resto de datos del comprador, mas un importe final de la compra
    
    private String id;
    private String nombre;
    private String tipoTarjeta;
    private String numTarjeta;
    private String precioTotal;

    //El constructor para crear el pedido actual

    public Pedidos(String id, String nombre, String tipoTarjeta, String numTarjeta, String precioTotal) {
        this.id = id;
        this.nombre = nombre;
        this.tipoTarjeta = tipoTarjeta;
        this.numTarjeta = numTarjeta;
        this.precioTotal = precioTotal;
    }

    //Los setters y getter por si son necesarios a la hora de inyectar los datos en la BD.

    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTipoTarjeta() {
        return this.tipoTarjeta;
    }

    public String getNumTarjeta() {
        return this.numTarjeta;
    }

    public String getPrecioTotal() {
        return this.precioTotal;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre= nombre;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

}
