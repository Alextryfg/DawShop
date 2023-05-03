import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

import org.omg.CORBA.SystemException;
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

  // Manejar solicitudes Post
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      doGet(request,response);

  // Código para manejar la solicitud GET aquí

  }

// Manejar solicitudes Get
  public void doGet(HttpServletRequest request, HttpServletResponse response)    
    throws ServletException, IOException {

    

  // Generamos un objeto sesion
  HttpSession session = request.getSession(true);
  /*HttpSession session = request.getSession(false);
  if(session == null) {
      session = request.getSession(true);
      session.setAttribute("usuario", null);
      session.setAttribute("username", null);
  }*/


  // Generamos un objeto para el contexto de la aplicacion
  ServletContext context = getServletContext();

      // Ejecutamos en funcion de la accion del usuario
      if (request.getParameter("seleccion") != null ) {

          // Obtenemos el id de disco a partir de la peticion
          String id = request.getParameter("disco");

          // Obtenemos la cantidad a partir de la peticion
          String cantidad = request.getParameter("cantidad");
          
          //Recuperamos el carrito anterior
          Carro temp = (Carro) session.getAttribute("carro");

          //Si el carrito no existe, lo creamos
          if (temp == null) {
              temp = new Carro();
          }

          //Creamos un producto con el id y la cantidad
          Producto p = new Producto(id, cantidad);

          //Añadimos el producto al carrito
          temp.addProducto(p);
          //Actualizamos el numero de productos
          temp.setNum();

          //Actualizamos el carrito por lo que la info se conservara mientras el user navegue por la app en esta sesion

          session.setAttribute("compra", temp.getCompra());

          session.setAttribute("carro", temp);



          //Redireccionamos a la pagina de carrito
          gotoPage("/jsp/Carrito.jsp", request, response);

      }else if (request.getParameter("delete") != null ) {

        
        String idRemove = request.getParameter("itemId");
        
        // Obtenemos el carrito de la sesion
        Carro temp = (Carro)session.getAttribute("carro");

        // Obtenemos el numero de disco a partir de la peticion

        // Eliminamos el disco correspondiente
        for(Producto p: temp.getCompra()){
          if(p.getId().equals(idRemove)){
            temp.getCompra().remove(p);
            break;
          }
        }
          
        //Comprobamos si el carrito esta vacio para redireccionar a una pagina donde indiquemos que el carrito esta vacio

        if (temp.getCompra().size() == 0) {
          // almacenar el carrito actualizado
          session.setAttribute("carro", null);

          //Vamos a una pagina donde indiquemos que el contenido del carrito esta vacio
          gotoPage("/jsp/Vacio.jsp", request, response);

        } else {
          // almacenar el carrito actualizado
          session.setAttribute("carro", temp);

          //Volvemos a la página en la que estabamos
          gotoPage("/jsp/Carrito.jsp", request, response);
        }

        
        
      }else if (request.getParameter("view") != null){

        // Obtenemos el carrito de la sesion
        Carro temp = (Carro)session.getAttribute("carro");

        //Si el carrito no existe, lo creamos
        if (temp == null) {
          temp = new Carro();
      }

        if (temp.getCompra().size() == 0) {
          // almacenar el carrito actualizado
          session.setAttribute("carro", null);

          //Vamos a una pagina donde indiquemos que el contenido del carrito esta vacio
          gotoPage("/jsp/Vacio.jsp", request, response);

        } else {
          // almacenar el carrito actualizado
          session.setAttribute("carro", temp);

          //Volvemos a la página en la que estabamos
          gotoPage("/jsp/Carrito.jsp", request, response);
        }


      }

  

  }

}
