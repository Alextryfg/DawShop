<%-- Minitienda --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <title>Iniciar Sesion</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <!-- La hoja de estilo a utilizar -->
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>

<center> 

<!--Indico que el carrito no tiene ningun objeto-->

<H1>Formulario de Inicio de sesion!</H1>
<h4>Introduzca sus credenciales para acceder a su cuenta!</h4>

<form action="formularioUsers" method = "post">
    <div class="mb-3">
        <label for="formGroupExampleInput" class="form-label"></label>
        <input type="text" class="form-control"placeholder="Nombre de Usuario">
      </div>
      <div class="mb-3">
        <label for="formGroupExampleInput2" class="form-label"></label>
        <input type="text" class="form-control"placeholder="Password">
      </div>

      <h4> Si todavia no tiene una cuenta, registrese pulsando el boton!</h4>

    <button type="submit" name="confirmarInicioSesion" class="btn btn-primary">Iniciar Sesion</button>
    <input type="submit" name="registro" class="btn btn-outline-primary" value="Registrase" style="margin-top: 10px;margin-bottom: 10px;"><p>
    
    <a href="index.html"><button type="button" class="btn btn-success">Seguir Comprando</button></a>
    
  </form>

<!--Pruebas-->
</center> 
</body>
</html>

