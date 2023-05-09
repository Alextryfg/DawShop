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
          gotoPage("/jsp/intermedia.html", request, response);
          
          //gotoPage("/jsp/Carrito.jsp", request, response);

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
          
        //Comprobamos si el carrito esta vacio

        if (temp.getCompra().size() == 0) {
          // almacenar el carrito actualizado
          session.setAttribute("carro", null);
        } else {
          // almacenar el carrito actualizado
          session.setAttribute("carro", temp);

        }//Volvemos a la página en la que estabamos
        
        gotoPage("/jsp/intermedia.html", request, response);
        

        
        
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
        } else {
          // almacenar el carrito actualizado
          session.setAttribute("carro", temp);
        }
        //Volvemos a la página en la que estabamos
        gotoPage("/jsp/intermedia.html", request, response);

      }

      else if (request.getParameter("registro") != null ) {

        //Vamos a la página donde se comenzará el registro del usuario
        gotoPage("/jsp/RegistrarUser.jsp", request, response);


    }else if (request.getParameter("inicioSesion") != null){

        gotoPage("/jsp/IniciarUser.jsp", request, response);

    }else if (request.getParameter("confirmarRegistro") != null){

      //Obtenemos los datos del formulario y los guardamos en variables
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      String correo = request.getParameter("correo");
      String tipoTarjeta = request.getParameter("tipoTarjeta");
      String numeroTarjeta = request.getParameter("numeroTarjeta");

      //Creamos el usuario a insertar en la Base de Datos
      Users user = new Users(username, correo, tipoTarjeta, numeroTarjeta, password);

      if(bd.existeUsuario(correo) == true){
        request.setAttribute("error", "Ya existe un usuario con ese correo!!!");
        gotoPage("/jsp/RegistrarUser.jsp", request, response);
      }else{
        //A continuacion, mediante las funciones especificadas en BaseDatos, se inyecta en la BD
        bd.insertarUsuario(user);

        //Creamos el atributo sesion para mantenerlo iniciado durante toda la sesion //Esto TIENE QUE SER LA CLAVE PRINCIPAL DE LA TABLA
        session.setAttribute("usuario", user.getCorreo());
        session.setAttribute("username", user.getNombre());
        
        Carro temp = (Carro)session.getAttribute("carro");
        if(temp==null){
          temp = new Carro();
        }

        if (temp.getCompra().size() != 0) {
          gotoPage("/jsp/intermedia.html", request, response);
        }else{
          gotoPage("/index.jsp", request, response);
        }
      }

      

    }else if(request.getParameter("confirmarInicioSesion") != null){

        //Obtenemos los datos del formulario
        //String username = request.getParameter("username");
        String password = request.getParameter("password");
        String correo = request.getParameter("correo");

        //Comprobamos si existe el usuario en la BD
        if(bd.existeUsuario(correo) == false){
            request.setAttribute("error", "No existe un usuario con ese correo!!!");
            gotoPage("/jsp/IniciarUser.jsp", request, response);
        }else{
          Users a = bd.recuperarDatosUsuario(correo);
          if(a.getPassword().equals(password)){
            //Creamos el atributo sesion para mantenerlo iniciado durante toda la sesion
            session.setAttribute("usuario", correo);
            session.setAttribute("username", a.getNombre());
            //Vamos a la pagina final donde se confirmará la compra y se muestra la informacion
            Carro temp = (Carro)session.getAttribute("carro");
            if(temp==null){
              temp = new Carro();
            }
            //if(!temp.getCompra().isEmpty()){
            if (temp.getCompra().size() != 0) {
              gotoPage("/jsp/intermedia.html", request, response);
            }else{
              gotoPage("/index.jsp", request, response);
            }
          }
          else{
            request.setAttribute("error", "Password incorrecto!!!");
            gotoPage("/jsp/IniciarUser.jsp", request, response);
          }
        }


    }else if(request.getParameter("finalCompra") != null){

          //Seteo el carro y la compra a null ya que la compra ya fue realizada
          Carro c = new Carro();

          //Se vacia el carrito

          session.setAttribute("carro", c);
          session.setAttribute("compra", c.getCompra());

          gotoPage("/index.jsp", request, response);


    }else if (request.getParameter("pagar") != null){

      //Si ya tenemos la sesion iniciada, vamos a la pagina de compra directamente
      if(session.getAttribute("usuario") != null){
        
        String user = (String) session.getAttribute("usuario");
        Carro carro = (Carro) session.getAttribute("carro");

        ///acceder bdd para los datos
        Users usuario = bd.recuperarDatosUsuario(user);
        //comprobar pedidos pa la mierda esta del codigo
        Pedidos ped = new Pedidos(Integer.toString(bd.calcularIdentificador()), usuario.getCorreo(), Float.toString(carro.getPrecioTotal()));
        bd.insertarPedido(ped);
        gotoPage("/jsp/CompraRealizada.jsp", request, response);

      }else{ //En caso de que todavia no hayamos iniciado sesion, vamos a la pagina de inicio de sesion
        gotoPage("/jsp/IniciarUser.jsp", request, response);
      }

    }else if (request.getParameter("cerrarSesion") != null){

      session.setAttribute("usuario", null);
      session.setAttribute("username", null);
      Carro c = new Carro();
      session.setAttribute("carro", c);
      
      gotoPage("/index.jsp", request, response);

    }  

    else if(request.getParameter("verPedidos") != null){
      String usuario = (String) session.getAttribute("usuario");
      ArrayList<Pedidos> list = new ArrayList<Pedidos>();
      list = bd.pedidosUsuario(usuario);
      
      request.setAttribute("lista", list);
      gotoPage("/jsp/Pedidos.jsp", request, response);
    }
  

  }

}
