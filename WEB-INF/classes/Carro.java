import java.util.ArrayList;

public class Carro {

  private ArrayList<Producto> compra = new ArrayList<>();
  private Float precioTotal = new Float(0);

    public void setNum() {

        for(int i = 0; i < compra.size(); i++) {
            this.compra.get(i).setNum(i);
        }
    }


    //Añadir un producto a nuestro carrito

    public void addProducto(Producto p) {

        //Si no es null
        if(p!=null){

            //Si el producto ya esta en el carrito, se suma la cantidad

            for(Producto producto: compra){
                if(producto.getId().equals(p.getId())){
                    p.setCantidad(producto.getCantidad()+p.getCantidad());

                    //Se elimina el producto anterior del carrito

                    compra.remove(producto);
                    break;
                }
            }

            //Se añade el producto al carrito
            this.compra.add(p);
            actualizarPrecioTotal();
        }
            
    }

    //Actualizacion del precio total

    public void actualizarPrecioTotal() {
        float total=(float)0.0;

        //Se actualiza el precio total del carrito
        this.precioTotal = new Float(0);
        for(Producto producto: compra){
            total += producto.getPrecioMult();
        }
        setPrecioTotal(total);
    }

    //Getters y Setters

    public ArrayList<Producto> getCompra() {
        return this.compra;
    }

    public void setCompra(ArrayList<Producto> compra) {
        this.compra = compra;
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public float getPrecioTotal() {
        return this.precioTotal;
    }

}
