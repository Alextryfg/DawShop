import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

import org.omg.CORBA.SystemException;
import org.omg.PortableInterceptor.ForwardRequest;

import java.io.*;
import java.net.URLEncoder;

public class Controlador extends HttpServlet {

  // Init para Base de datos
  BaseDatos bd;

  public void init() throws ServletException {
    // Inicializamos la base de datos
    bd = new BaseDatos();
    bd.iniciarConexion();
  }

  // Metodo convencional goToPage para redireccionar a una pagina JSP o HTML y
  // pasarle la request y la response
  private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Creamos objeto RequestDispatcher
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
    // Redireccionamos a la pagina
    dispatcher.forward(request, response);

  }

  // Manejar solicitudes Post
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);

  }

  // Manejar solicitudes Get
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Generamos un objeto sesion
    HttpSession session = request.getSession(true);

    // Generamos un objeto para el contexto de la aplicacion
    ServletContext context = getServletContext();

    // Ejecutamos en funcion de la accion del usuario
    if (request.getParameter("seleccion") != null) {

      // Obtenemos el id de disco a partir de la peticion
      String id = request.getParameter("disco");

      // Obtenemos la cantidad a partir de la peticion
      String cantidad = request.getParameter("cantidad");

      // Recuperamos el carrito anterior
      Carro temp = (Carro) session.getAttribute("carro");

      // Si el carrito no existe, lo creamos
      if (temp == null) {
        temp = new Carro();
      }

      // Creamos un producto con el id y la cantidad
      Producto p = new Producto(id, cantidad);

      // Añadimos el producto al carrito
      temp.addProducto(p);
      // Actualizamos el numero de productos
      temp.setNum();

      // Actualizamos el carrito por lo que la info se conservara mientras el user
      // navegue por la app en esta sesion

      session.setAttribute("compra", temp.getCompra());

      session.setAttribute("carro", temp);

      // Redireccionamos a la pagina de carrito
      String url = "http://localhost:8080/Tienda_Daw/jsp/Carrito.jsp";
      request.setAttribute("url", url);
      gotoPage("/jsp/intermedia.jsp", request, response);

    } else if (request.getParameter("delete") != null) {

      String idRemove = request.getParameter("itemId");

      // Obtenemos el carrito de la sesion
      Carro temp = null;
      if (session.getAttribute("carro") != null) {
        temp = (Carro) session.getAttribute("carro");
      }

      // Obtenemos el numero de disco a partir de la peticion

      // Eliminamos el disco correspondiente
      if (temp != null) {
        for (Producto p : temp.getCompra()) {
          if (p.getId().equals(idRemove)) {
            temp.getCompra().remove(p);
            break;
          }
        }

        // Comprobamos si el carrito esta vacio

        if (temp.getCompra().size() == 0) {
          // almacenar el carrito actualizado
          session.setAttribute("carro", null);
        } else {
          // almacenar el carrito actualizado
          session.setAttribute("carro", temp);

        }

      }

      //De nuevo redireccionamos y en caso de estar vacio se va a la página de vacio
      String url = "http://localhost:8080/Tienda_Daw/jsp/Carrito.jsp";
      request.setAttribute("url", url);
      gotoPage("/jsp/intermedia.jsp", request, response);

    } else if (request.getParameter("view") != null) {

      // Obtenemos el carrito de la sesion
      Carro temp = (Carro) session.getAttribute("carro");

      // Si el carrito no existe, lo creamos
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
      // Volvemos a la página en la que estabamos
      String url = "http://localhost:8080/Tienda_Daw/jsp/Carrito.jsp";
      request.setAttribute("url", url);
      gotoPage("/jsp/intermedia.jsp", request, response);

    }

    else if (request.getParameter("registro") != null) {

      // Vamos a la página donde se comenzará el registro del usuario
      String url = "http://localhost:8080/Tienda_Daw/jsp/RegistrarUser.jsp";
      request.setAttribute("url", url);
      gotoPage("/jsp/intermedia.jsp", request, response);

    } else if (request.getParameter("inicioSesion") != null) {
      String url = "http://localhost:8080/Tienda_Daw/jsp/IniciarUser.jsp";
      request.setAttribute("url", url);
      gotoPage("/jsp/intermedia.jsp", request, response);

    } else if (request.getParameter("confirmarRegistro") != null) {

      // Obtenemos los datos del formulario y los guardamos en variables
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      String correo = request.getParameter("correo");
      String tipoTarjeta = request.getParameter("tipoTarjeta");
      String numeroTarjeta = request.getParameter("numeroTarjeta");

      // Creamos el usuario a insertar en la Base de Datos
      Users user = new Users(username, correo, tipoTarjeta, numeroTarjeta, password);

      if (bd.existeUsuario(correo) == true) {
        request.setAttribute("error", "Ya existe un usuario con ese correo!!!");
        gotoPage("/jsp/RegistrarUser.jsp", request, response);
      } else {
        // A continuacion, mediante las funciones especificadas en BaseDatos, se inyecta en la BD
        bd.insertarUsuario(user);

        // Creamos el atributo sesion para mantenerlo iniciado durante toda la sesion
        session.setAttribute("usuario", user.getCorreo());
        session.setAttribute("username", user.getNombre());

        Carro temp = (Carro) session.getAttribute("carro");
        if (temp == null) {
          temp = new Carro();
        }

        //Dependiendo del contenido de carro se va a una página o a otra

        if (temp.getCompra().size() != 0) {
          String url = "http://localhost:8080/Tienda_Daw/jsp/Carrito.jsp";
          request.setAttribute("url", url);
          gotoPage("/jsp/intermedia.jsp", request, response);
        } else {

          String url = "http://localhost:8080/Tienda_Daw/index.jsp";
          request.setAttribute("url", url);
          gotoPage("/jsp/intermedia.jsp", request, response);
        }
      }

    } else if (request.getParameter("confirmarInicioSesion") != null) {

      // Obtenemos los datos del formulario
      String password = request.getParameter("password");
      String correo = request.getParameter("correo");

      // Comprobamos si existe el usuario en la BD
      if (bd.existeUsuario(correo) == false) {
        request.setAttribute("error", "No existe un usuario con ese correo!!!");
        String url = "http://localhost:8080/Tienda_Daw/jsp/IniciarUser.jsp";
        request.setAttribute("url", url);
        gotoPage("/jsp/intermedia.jsp", request, response);
      } else {
        Users a = bd.recuperarDatosUsuario(correo);
        if (a.getPassword().equals(password)) {
          // Creamos el atributo sesion para mantenerlo iniciado durante toda la sesion
          session.setAttribute("usuario", correo);
          session.setAttribute("username", a.getNombre());
          // Vamos a la pagina final donde se confirmará la compra y se muestra la informacion
          Carro temp = (Carro) session.getAttribute("carro");
          if (temp == null) {
            temp = new Carro();
          }
          // if(!temp.getCompra().isEmpty()){
          if (temp.getCompra().size() != 0) {
            String url = "http://localhost:8080/Tienda_Daw/jsp/Carrito.jsp";
            request.setAttribute("url", url);
            gotoPage("/jsp/intermedia.jsp", request, response);
          } else {
            String url = "http://localhost:8080/Tienda_Daw/index.jsp";
            request.setAttribute("url", url);
            gotoPage("/jsp/intermedia.jsp", request, response);
          }
        } else {
          request.setAttribute("error", "Password incorrecto!!!");
          String url = "http://localhost:8080/Tienda_Daw/jsp/IniciarUser.jsp";
          request.setAttribute("url", url);
          gotoPage("/jsp/intermedia.jsp", request, response);
        }
      }

    } else if (request.getParameter("finalCompra") != null) {

      // Seteo el carro y la compra a null ya que la compra ya fue realizada
      Carro c = new Carro();

      // Se vacia el carrito

      session.setAttribute("carro", c);
      session.setAttribute("compra", c.getCompra());

      String url = "http://localhost:8080/Tienda_Daw/index.jsp";
      request.setAttribute("url", url);
      gotoPage("/jsp/intermedia.jsp", request, response);

    } else if (request.getParameter("pagar") != null) {

      // Si ya tenemos la sesion iniciada, vamos a la pagina de compra directamente
      if (session.getAttribute("usuario") != null) {

        String user = (String) session.getAttribute("usuario");
        Carro carro = (Carro) session.getAttribute("carro");

        /// acceder bdd para los datos
        Users usuario = bd.recuperarDatosUsuario(user);
        // comprobar pedidos pa la mierda esta del codigo
        Pedidos ped = new Pedidos(Integer.toString(bd.calcularIdentificador()), usuario.getCorreo(),
          Float.toString(carro.getPrecioTotal()));
        bd.insertarPedido(ped);
        String url = "http://localhost:8080/Tienda_Daw/jsp/CompraRealizada.jsp";
        request.setAttribute("url", url);
        gotoPage("/jsp/intermedia.jsp", request, response);

      } else { // En caso de que todavia no hayamos iniciado sesion, vamos a la pagina de inicio de sesion
        String url = "http://localhost:8080/Tienda_Daw/jsp/IniciarUser.jsp";
        request.setAttribute("url", url);
        gotoPage("/jsp/intermedia.jsp", request, response);
      }

    } else if (request.getParameter("cerrarSesion") != null) {

      //En caso de querer cerrar sesion borramos el valor de las variables de la sesion que nos guardan el nombre de usuario

      session.setAttribute("usuario", null);
      session.setAttribute("username", null);

      //Creamos un carro vacio y redireccionamos

      Carro c = new Carro();
      session.setAttribute("carro", c);

      String url = "http://localhost:8080/Tienda_Daw/index.jsp";
      request.setAttribute("url", url);
      gotoPage("/jsp/intermedia.jsp", request, response);

    }

    else if (request.getParameter("verPedidos") != null) {

      //Accedemos a los pedidos del usuario y redireccionamos a una página donde se mostraran (Pedidos.jsp)

      String usuario = (String) session.getAttribute("usuario");
      ArrayList<Pedidos> list = new ArrayList<Pedidos>();
      list = bd.pedidosUsuario(usuario);

      request.setAttribute("lista", list);
      String url = "http://localhost:8080/Tienda_Daw/jsp/Pedidos.jsp";
      request.setAttribute("url", url);
      gotoPage("/jsp/intermedia.jsp", request, response);
    }

  }

}
