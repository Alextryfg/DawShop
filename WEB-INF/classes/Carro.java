import java.util.ArrayList;

public class Carro {

  private ArrayList<Producto> compra = new ArrayList<>();
  private Float precio_Total = new Float(0);
  private ArrayList<String> ids = new ArrayList<>();

    public void setNum() {

        for(int i = 0; i < compra.size(); i++) {
            this.compra.get(i).setNum(i);
        }
    }

    public void addProducto(Producto p) {
        if(p!=null){
            this.compra.add(p);
            this.ids.add(p.getId());
            this.precio_Total += p.getPrecioMult();
        }
            
    }

    public ArrayList<Producto> getCompra() {
        return this.compra;
    }

    public ArrayList<String> getIds() {
        return this.ids;
    }

}
