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
    <link rel="stylesheet" type="text/css" href="../css/style.css">

</head>
<body>

<center> 

<!--Indico que el carrito no tiene ningun objeto-->

<H1>Compra Realizada Correctamente!</H1>
<h3>A continuacion se mostrara la informacion de su compra</h3>

<!--Creamos la variable que indicará el precio Final pagado-->
<c:set var="total" value="0" />

<!--Aqui deberia aparecer la informacion de la compra-->
<table class="table table-dark">
    <thead>
        <tr>
        <th scope="col">#</th>
        <th scope="col">Titulo del Disco y Autor</th>
        <th scope="col">Cantidad</th>
        <th scope="col">Importe</th>
        </tr>
    </thead>
    <tbody>
        <form action="../Controlador" method="post">
        <c:forEach items="${compra}" var="item" varStatus="loop">
        <tr>
            <th scope="row"><c:out value="${item.num}"/></th>
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.cantidad}"/></td>
            <td><c:out value="${item.precioMult}"/></td>
        </tr>
        <c:set var="total" value="${total + item.precioMult}"/>
        </c:forEach>
        </form>
    </tbody>
</table>

<!--Campos de indicacion del precio-->

<c:set var="formattedTotal" value="${String.format('%.2f', total)}" />
<p><strong>Precio Pagado : <c:out value="${formattedTotal}"/></strong></p>

<!--Boton para volver a la página inicial-->
<form action="../Controlador" method = "post">
    <input type="submit" name="finalCompra" class="btn btn-outline-primary" value="Volver al inicio" style="margin-top: 10px;margin-bottom: 10px;"><p>
</form>


</center> 
</body>
</html>


