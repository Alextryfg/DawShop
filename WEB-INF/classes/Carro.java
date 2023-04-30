import java.util.ArrayList;

public class Carro {
  private ArrayList<Producto> discos = new ArrayList<>();
  private Float precio_Total = new Float(0);

    public void setNum() {

        for(int i = 0; i < discos.size(); i++) {
            discos.get(i).setNum(i);
        }
    }

}
