import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

public class BaseDatos {

  private Connection connection;
  private ResultSet rs;
  private PreparedStatement consulta;
  private String query;

  //Metodo de inicio de conexion

  public void iniciarConexion() {
    try {
      if (connection == null) {
        String conector = "org.postgresql.Driver";
        Class.forName(conector).newInstance();

        String URL = "jdbc:postgresql://127.0.0.1:5432/tiendaDAW";
        String username = "alexclfg";
        String password = "12345";

        connection = DriverManager.getConnection(URL, username, password);

      }
    } catch (Exception e) {
      Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, "Fallo al iniciar la conexion con la BD", e);
    }
	
  }

  public void cerrarConexion() {
    // terminar conexión con la base de datos
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        
      }
    }
    if (consulta != null) {
      try {
        consulta.close();
      } catch (SQLException e) {
        
      }
    }
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        
      }
    }
  }

  // función para insertar un nuevo pedido en la base de datos
  public boolean insertarPedido(Pedidos p) {

    // alamcena pedido en DB
    consulta = null;

    query = "INSERT INTO pedidos (correouser,identificador,preciototal) VALUES(?,?,?)";
    try {
      consulta = connection.prepareStatement(query);

      consulta.setString(1, p.getCorreoUser());
      consulta.setInt(2, Integer.parseInt(p.getId()));
      consulta.setString(3, p.getPrecioTotal());

      consulta.executeUpdate();

      return true;

    } catch (SQLException e) {
      Logger.getLogger(query).log(Level.SEVERE, "Error al insertar pedido", e);
      return false;
    }
  }

  // comprobar si existe un usuario con el mismo correo en la base de datos
  public boolean existeUsuario(String correo) {
    boolean resultado = true;

    query = "SELECT * FROM usuarios WHERE direccioncorreo =?";

    try {
      consulta = connection.prepareStatement(query);
      consulta.setString(1, correo);

      rs = consulta.executeQuery();

      resultado = rs.next();
    } catch (SQLException e) {
		return false;
    }

    return resultado;
  }

  public Users recuperarDatosUsuario(String correo){
    Users resultado = null;
    query = "SELECT * FROM usuarios WHERE direccioncorreo =?";

    try {
      consulta = connection.prepareStatement(query);
      consulta.setString(1, correo);

      rs = consulta.executeQuery();

      if (rs.next()) {
        resultado = new Users(rs.getString("nombre"), rs.getString("direccioncorreo"), rs.getString("tipoTarjeta"), rs.getString("numerotarjeta"), rs.getString("password"));
        
      }
    } catch (SQLException e) {
      Logger.getLogger(query).log(Level.SEVERE, "Error al recuperar los datos del usuario", e);
    }

    return resultado;

  }

  public ArrayList<Pedidos> pedidosUsuario(String correo){

    ArrayList<Pedidos> resultado = new ArrayList<Pedidos>();
    query = "SELECT * FROM pedidos WHERE correouser =?";

    try {
      consulta = connection.prepareStatement(query);
      consulta.setString(1, correo);

      rs = consulta.executeQuery();

      while (rs.next()) {
        resultado.add(new Pedidos(rs.getString("identificador"), rs.getString("correouser"), rs.getString("preciototal")));
        
      }
    } catch (SQLException e) {
      Logger.getLogger(query).log(Level.SEVERE, "Error al recuperar los pedidos del usuario", e);
    }

    return resultado;

  }

  public int calcularIdentificador(){
    int resultado = 1;
    query = "SELECT * FROM pedidos WHERE identificador = (SELECT MAX(identificador) FROM pedidos)";
    try {
      consulta = connection.prepareStatement(query);

      rs = consulta.executeQuery();

      if (rs.next()) {
        resultado += rs.getInt("identificador");
      }
    } catch (SQLException e) {
      Logger.getLogger(query).log(Level.SEVERE, "Error al recuperar los pedidos del usuario", e);
    }

    return resultado;
  }

  // función para introducir nuevos usuarios en la base de datos
  public boolean insertarUsuario(Users u) {
    consulta = null;

    query = "INSERT INTO usuarios (nombre,direccioncorreo,numerotarjeta,password,tipotarjeta) VALUES(?,?,?,?,?)";

    try {
      consulta = connection.prepareStatement(query);

      consulta.setString(1, u.getNombre());
      consulta.setString(2, u.getCorreo());
      consulta.setString(3, u.getNumeroTarjeta());
	    consulta.setString(4, u.getPassword());
      consulta.setString(5, u.getTipoTarjeta());

      consulta.executeUpdate();

      return true;
    } catch (SQLException e) {
	  Logger.getLogger(query).log(Level.SEVERE, "Error al insertarUsuario en la BD", e);
      return false;
    }
  }

}
