

import javax.servlet.*;
import javax.servlet.http.*;

import org.omg.PortableInterceptor.ForwardRequest;

import java.io.*;

public class Controlador extends HttpServlet {

  //Metodo convencional goToPage para redireccionar a una pagina JSP o HTML y pasarle la request y la response
  //Request lo que le pides a la pagina, response es la respuesta. dispatcher permite cambiar de pagina sin perder la request y la response
  //es decir, es como un mecanismo de redireccionamiento que te lleva a la página que quieras.
  private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Creamos objeto RequestDispatcher
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
    dispatcher.forward(request, response);
  }

  // Aquí van los métodos doPost y doGet para procesar las peticiones.

  // Manejar solicitudes GET
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

  // Código para manejar la solicitud GET aquí

  }

// Manejar solicitudes POST
  public void doPost(HttpServletRequest request, HttpServletResponse response)    
    throws ServletException, IOException {

  // Generamos un objeto sesion
  HttpSession session = request.getSession(true);

  // Generamos un objeto para el contexto de la aplicacion
  ServletContext context = getServletContext();

      // Ejecutamos en funcion de la accion del usuario
      if (request.getParameter("seleccion") != null ) {
          // Acciones

          // Obtenemos el id de disco a partir de la peticion
          String id = request.getParameter("disco");
          // Obtenemos la cantidad a partir de la peticion
          String cantidad = request.getParameter("cantidad");

          //Aqui lo meteriamos dentro del carrito, sin embargo, aun no esta codificado.
          //System.out.println("Disco: " + id + " Cantidad: " + cantidad);
          
          //Recorremos el carrito dentro de session  y metemos lo que sea

          //Redireccionamos a la pagina de carrito

          gotoPage("/jsp/Carrito.jsp", request, response);

      }

  

  }

}