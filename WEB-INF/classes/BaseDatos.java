import java.sql.*;

public class BaseDatos {
	
	public BaseDatos () throws Exception {
		this.testDriver();
	}
	
	public void testDriver () throws Exception {
		try{
			Class.forName ("org.postgresql.Driver");
			System.out.println("Encontrado el driver de PostgreSQL");
		} catch (java.lang.ClassNotFoundException e){
			System.out.println("Postgres JDBC Driver no encontrado ... ");
			throw (e);
			
		} 
	}
	
	protected Connection obtenerConexion () throws Exception {
    	String url = "";
		try {
			url = "jdbc:postgresql://localhost:5432/minitienda";
			Connection con = DriverManager.getConnection(url, "postgres", "basededatos");
			System.out.println("Conexion establecida con " + url + "...");	    
			return con;
		} catch (java.sql.SQLException e)  {
			System.out.println("Conexion NO establecida con " + url);
			throw (e);
		}
    }
}