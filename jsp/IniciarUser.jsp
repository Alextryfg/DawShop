<%-- Minitienda --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesion</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <!-- La hoja de estilo a utilizar -->
    <link rel="stylesheet" type="text/css" href="../css/style.css">

</head>
<body>

<center> 

<!--Indico que el carrito no tiene ningun objeto-->

<H1>Formulario de Inicio de sesion!</H1>
<h4>Introduzca sus credenciales para acceder a su cuenta!</h4>

<!--Formulario de inicio de sesion-->

<form action="../Controlador" method = "post">

  <p><strong><font color="#F24638"><c:out value="${error}"></c:out></font></strong></p>

  <center>
      <div class="d-flex justify-content-center"></div>
      <div class="mb-3">
        <input type="email" name="correo" class="form-control"placeholder="Correo" title="Debe ser un correo valido" required/>
      </div>
      <div class="mb-3">
        <input type="password" name="password" class="form-control" placeholder="Password" pattern="(?=.*\d)(?=.*[A-Z]).{8,}" title="Debe tener al menos 8 caracteres, una mayuscula y un numero" required/>
      </div>
    </div>
  </center>
      
    <h4> Si todavia no tiene una cuenta, registrese pulsando el boton!</h4>

    <!--Botones de vuelta a index o submit de inicio de sesion para logearse-->

    <button type="submit" name="confirmarInicioSesion" class="btn btn-primary">Iniciar Sesion</button>
    <a href="../index.jsp"><button type="button" class="btn btn-success">Seguir Comprando</button></a>
    
  </form>

<!--Pruebas-->
</center> 
</body>
</html>


