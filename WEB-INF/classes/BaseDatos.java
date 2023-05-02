import java.sql.*;
import java.util.logging.*;

public class BaseDatos {

  private Connection connection;
  private ResultSet rs;
  private PreparedStatement ps;
  private String query;
	
	
	


  public void iniciarConexion() {
    try {
      if (connection == null) {
        String conector = "org.postgresql.Driver";
        Class.forName(conector).newInstance();

        //String URL = "jdbc:postgresql://127.0.0.1:5432/tienda";
        String URL = "jdbc:postgresql://127.0.0.1:5432/tiendaDAW";
        //String username = "Alex";
        String username = "alex";
        String password = "12345";

        connection = DriverManager.getConnection(URL, username, password);

		//Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, "cONEXION CON LA BD EXITOSA");

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
    if (ps != null) {
      try {
        ps.close();
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
    ps = null;

    //query = "INSERT INTO pedidos (nombre,correo,tipotarjeta,numerotarjeta,preciototal) VALUES(?,?,?,?,?)";
    query = "INSERT INTO pedidos (identificador,nombreuser,tipotarjeta,numerotarjetauser,preciototal) VALUES(?,?,?,?,?)";

    try {
      ps = connection.prepareStatement(query);

      
      //ps.setString(2, p.getCorreo());
      ps.setString(1, p.getId());
      ps.setString(2, p.getNombre());
      ps.setString(3, p.getTipoTarjeta());
      ps.setString(4, p.getNumTarjeta());
      ps.setString(5, p.getPrecioTotal());
      //ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

      ps.executeUpdate();

      return true;

    } catch (SQLException e) {
      return false;
    }
  }

  // comprobar si existe un usuario con el mismo correo en la base de datos
  public boolean existeUsuario(String correo) {
    boolean resultado = true;

    query = "SELECT * FROM usuarios WHERE direccioncorreo =?";

    try {
      ps = connection.prepareStatement(query);
      ps.setString(1, correo);

      rs = ps.executeQuery();

      resultado = rs.next();
    } catch (SQLException e) {
		return false;
    }

    return resultado;
  }

  // función para introducir nuevos usuarios en la base de datos
  public boolean insertarUsuario(Users u) {
    ps = null;

    query = "INSERT INTO usuarios (nombre,direccioncorreo,numerotarjeta,password) VALUES(?,?,?,?)";

    try {
      ps = connection.prepareStatement(query);

      ps.setString(1, u.getNombreUser());
      ps.setString(2, u.getCorreoUser());
      //poner el numero de tarjeta, lo de abajo es el tipo
      ps.setString(3, u.getNumeroTarjetaUser());
	    ps.setString(4, u.getPasswordUser());

      ps.executeUpdate();

      return true;
    } catch (SQLException e) {
	  Logger.getLogger(query).log(Level.SEVERE, "Error al insertarUsuario en la BD", e);
      return false;
    }
  }

  public Users iniciarSesion(Users u) {
    Users resultado = null;

    query = "SELECT * FROM usuarios WHERE direccioncorreo=? and password=?";

    

    try {
      ps = connection.prepareStatement(query);
      ps.setString(1, u.getCorreoUser());
      ps.setString(2, u.getPasswordUser());

      rs = ps.executeQuery();

      if (rs.next()) {
        resultado = new Users(rs.getString("nombre"), rs.getString("direccioncorreo"), rs.getString("numerotarjeta"), rs.getString("password"));
        
      }
    } catch (SQLException e) {
	  Logger.getLogger(query).log(Level.SEVERE, "Error al iniciarSesion en la BD", e);
    }

    // devuelve el usuario si los datos son correctos
    return resultado;
  }

}
