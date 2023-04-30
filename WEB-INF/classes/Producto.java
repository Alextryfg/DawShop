import java.util.StringTokenizer;

public class Producto {
    
    private String id;
    private Float precio;
    private Integer cantidad;
    private Float precioMult;
    private Integer num; //indice del producto en el carrito

    public Producto(String id, String cantidad) {

        // Recuperamos informacion del precio del CD

        StringTokenizer t = new StringTokenizer(id,"|");
        t.nextToken();
        t.nextToken();
        t.nextToken();
        String precioString = t.nextToken();
        precioString = precioString.replace('$',' ').trim();

        //Insertamos la informacion en cada Var del producto

        this.id = id;
        this.precio = Float.parseFloat(precioString);
        this.cantidad = Integer.parseInt(cantidad);
        this.precioMult = this.precio*this.cantidad;
    }

    public String getId() {
        return this.id;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public Float getPrecioMult() {
        return this.precioMult;
    }

    public void setNum(Integer i) {
        this.num = i;
    }

    public Integer getNum() {
        return this.num;
    }

}
 