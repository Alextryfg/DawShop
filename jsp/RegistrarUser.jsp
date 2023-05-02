<%-- Minitienda --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <title>Registrar Usuario</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <!-- La hoja de estilo a utilizar -->
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>

<center> 

<!--Indico que el carrito no tiene ningun objeto-->

<H1>Introduzca los Datos para registrarse y Confirmar su compra</H1>

<form  action="formularioUsers" method = "post">

    <p><strong><font color="#F24638"><c:out value="${error}"></c:out></font></strong></p>

    <div class="mb-3">
        <label for="formGroupExampleInput" class="form-label"></label>
        <input type="text" name="username" class="form-control"placeholder="Nombre de Usuario">
    </div>
    <div class="mb-3">
        <label for="formGroupExampleInput2" class="form-label"></label>
        <input type="text" name="correo" class="form-control"placeholder="Correo">
    </div>
    <select class="form-select" name="tipoTarjeta" aria-label="Default select example">
        <label for="form-select" class="form-label">Tipo de Tarjeta</label>
        <option value="Visa">Visa</option>
        <option value="MasterCard">MasterCard</option>
    </select>
    <div class="mb-3">
        <label for="formGroupExampleInput2" class="form-label"></label>
        <input type="text" name="password" class="form-control"placeholder="Password">
    </div>


    <input type="submit" name="confirmarRegistro" class="btn btn-outline-primary" value="Registrase" style="margin-top: 10px;margin-bottom: 10px;">
    <button type="submit" name="inicioSesion" class="btn btn-primary">Volver a Inicio de Sesion</button><p>
        
    <a href="index.jsp"><button type="button" class="btn btn-success">Seguir Comprando</button></a>

</form>

<!--Pruebas-->
</center> 
</body>
</html>


