<%-- Minitienda --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <title>Carrito Vacio</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <!-- La hoja de estilo a utilizar -->
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>

<center> 

<!--Indico que el carrito no tiene ningun objeto-->

<H1>El carrito se encuentra vacio!</H1>

<!-- El precio Total será de 0 -->
<c:set var="total" value="0" />

<img src="https://letrasrecortadas.com/carritoVacio.png" height="450" width="450">

<p><strong>Precio Total :  <c:out value="${total}"/></strong></p>
    
<a href="index.html"><button type="button" class="btn btn-success">Seguir Comprando</button></a>

<!--Pruebas-->
</center> 
</body>
</html>


