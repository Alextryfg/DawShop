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
        request.setAttribute("error", "Ya existe el usuario en la Base de Datos!!!");
        gotoPage("/jsp/RegistrarUser.jsp", request, response);
      }else{
        //A continuacion, mediante las funciones especificadas en BaseDatos, se inyecta en la BD
        bd.insertarUsuario(user);

        //Vamos a la página donde se comenzará el inicio de sesion
        gotoPage("/jsp/IniciarUser.jsp", request, response);

      }

      

    }else if(request.getParameter("confirmarInicioSesion") != null){

        //Obtenemos los datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String correo = request.getParameter("correo");

        //Creamos el usuario a insertar en la Base de Datos
        Users user = new Users(username, password,correo);

        //Comprobamos si existe el usuario en la BD
        if(bd.existeUsuario(user.getCorreo()) == false){
            request.setAttribute("error", "No existe el usuario en la Base de Datos!!!");
            gotoPage("/jsp/IniciarUser.jsp", request, response);
        }else{

            //Creamos el atributo sesion para mantenerlo iniciado durante toda la sesion //Esto TIENE QUE SER LA CLAVE PRINCIPAL DE LA TABLA
            session.setAttribute("usuario", correo);
            session.setAttribute("username", username);
            //Vamos a la pagina final donde se confirmará la compra y se muestra la informacion
            Carro temp = (Carro)session.getAttribute("carro");
            if(temp==null){
              temp = new Carro();
            }
            //if(!temp.getCompra().isEmpty()){
            if (temp.getCompra().size() != 0) {
              gotoPage("/jsp/Carrito.jsp", request, response);
            }else{
              gotoPage("/index.jsp", request, response);
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
        
        String username = (String) session.getAttribute("username");
        Carro carro = (Carro) session.getAttribute("carro");

        ///acceder bdd para los datos
        Users usuario = bd.recuperarDatosUsuario(username);
        //comprobar pedidos pa la mierda esta del codigo
        Pedidos ped = new Pedidos(Integer.toString(bd.calcularIdentificador(usuario.getCorreo())), usuario.getCorreo(),"VISA", usuario.getNumeroTarjeta(), Float.toString(carro.getPrecioTotal()));
        bd.insertarPedido(ped);
        gotoPage("/jsp/CompraRealizada.jsp", request, response);

        ArrayList<Pedidos> lista = new ArrayList<Pedidos>();
        lista = bd.pedidosUsuario(username);

        request.setAttribute("lista", lista);

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
  }
}
