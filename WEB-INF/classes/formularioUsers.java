import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

import org.omg.CORBA.SystemException;
import org.omg.PortableInterceptor.ForwardRequest;

import java.io.*;

public class formularioUsers extends HttpServlet {

  //Init para Base de datos
  BaseDatos bd;

  public void init() throws ServletException {
    // Inicializamos la base de datos
    bd = new BaseDatos();
    bd.iniciarConexion();
  }

  
  private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Creamos objeto RequestDispatcher
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
    dispatcher.forward(request, response);
  }

  // Manejar solicitudes Post
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      doGet(request,response);

  }

// Manejar solicitudes Get
  public void doGet(HttpServletRequest request, HttpServletResponse response)    
    throws ServletException, IOException {

    // Generamos un objeto sesion
    HttpSession session = request.getSession(true);

    // Generamos un objeto para el contexto de la aplicacion
    ServletContext context = getServletContext();

    // Ejecutamos en funcion de la accion del usuario
    if (request.getParameter("registro") != null ) {

        //Vamos a la página donde se comenzará el registro del usuario
        gotoPage("/jsp/RegistrarUser.jsp", request, response);

        


    }else if (request.getParameter("inicioSesion") != null){

        //Vamos a la página donde se comenzará el inicio de sesion
        gotoPage("/jsp/IniciarUser.jsp", request, response);

    }else if (request.getParameter("confirmarRegistro") != null){

      //Obtenemos los datos del formulario y los guardamos en variables
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      String correo = request.getParameter("correo");
      String tipoTarjeta = request.getParameter("tipoTarjeta");

      //Creamos el usuario a insertar en la Base de Datos
      Users user = new Users(username, correo, tipoTarjeta,password);

      //A continuacion, mediante las funciones especificadas en BaseDatos, se inyecta en la BD
      bd.insertarUsuario(user);

      //Vamos a la página donde se comenzará el inicio de sesion
      gotoPage("/jsp/IniciarUser.jsp", request, response);

    }

  

  }

}
