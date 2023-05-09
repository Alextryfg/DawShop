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

<H1>Introduzca los Datos para registrarse</H1>

<form  action="formularioUsers" method = "post">

    <p><strong><font color="#F24638"><c:out value="${error}"></c:out></font></strong></p>

    <div class="mb-3">
        <input type="text" name="username" class="form-control"placeholder="Nombre de Usuario" pattern="[a-zA-Z ,.'-]{3,48}" title="Debe tener entre 3 y 48 letras" required/>
    </div>
    <div class="mb-3">
        <input type="email" name="correo" class="form-control"placeholder="Correo" title="Debe ser un correo valido" required/>
    </div>
    <div class="mb-3">
        <select class="form-select" name="tipoTarjeta" aria-label="Default select example">
            <option value="Visa">Visa</option>
            <option value="MasterCard">MasterCard</option>
        </select>
    </div>
    <div class="mb-3">
        <input type="text" name="numeroTarjeta" class="form-control" placeholder="Numero de Tarjeta" pattern="[0-9]{10,20}" title="Debe tener entre 10 y 20 digitos" required/>
    </div>
    <div class="mb-3">
        <input type="password" name="password" class="form-control" placeholder="Password" pattern="(?=.*\d)(?=.*[A-Z]).{8,}" title="Debe tener al menos 8 caracteres, una mayuscula y un numero" required/>
    </div>


    <input type="submit" name="confirmarRegistro" class="btn btn-outline-primary" value="Registrarse" style="margin-top: 10px;margin-bottom: 10px;"/>
    
    

</form>

<form action="formularioUsers" method = "post">
    <button type="submit" class="btn btn-primary" name="inicioSesion">Dirigirse a Inicio de Sesion</button>
    <a href="index.jsp"><button type="button" class="btn btn-success">Seguir Comprando</button></a>
</form>


<!--Pruebas-->
</center> 
</body>
</html>


