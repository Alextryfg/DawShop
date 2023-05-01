import java.util.ArrayList;

public class Carro {
    
  private ArrayList<Producto> compra = new ArrayList<>();
  private Float precio_Total = new Float(0);

    public void setNum() {

        for(int i = 0; i < compra.size(); i++) {
            compra.get(i).setNum(i);
        }
    }

    public void addProducto(Producto p) {
        if(p!=null){
            compra.add(p);
            this.precio_Total += p.getPrecioMult();
        }
            
    }

}
