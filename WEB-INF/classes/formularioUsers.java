import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

import org.omg.CORBA.SystemException;
import org.omg.PortableInterceptor.ForwardRequest;

import java.io.*;

public class formularioUsers extends HttpServlet {

  
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

    }

  

  }

}
