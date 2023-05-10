<%-- Minitienda --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<HTML>
<head>
    <title>Carrito de Compra</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <!-- La hoja de estilo a utilizar -->
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<center> 
<!--Si el carrito no esta vacio, se muestra lo siguiente-->

<c:if test="${not empty carro}">

<H1>Carrito</H1>

<!--Variable que almacenara el precio total-->

<c:set var="total" value="0" />

<!--Tabla que indicará los elementos de nuestro carrito, con indice , cantidad, id e importe, ademas de un boton para eliminar-->

<table class="table table-dark">
    <thead>
        <tr>
        <th scope="col">#</th>
        <th scope="col">Titulo del Disco y Autor</th>
        <th scope="col">Cantidad</th>
        <th scope="col">Importe</th>
        <th scope="col" class="text-center align-middle"></th>
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
            <!--Utilizo un campo oculto, aunque se podria hacer de varias otras formas-->
            <input type="hidden" name="itemId" value="${item.id}"/> <!-- o value="${item.num}" si corresponde-->
            <td><input type="submit" name="delete" class="btn btn-danger" value="-"><input type="submit" name="delete" class="btn btn-danger" value="+"></td> 
        </tr>
        <c:set var="total" value="${total + item.precioMult}"/>
        </c:forEach>
        </form>
    </tbody>
</table>

    <!--Muestro el importe total del carrito-->

    <c:set var="formattedTotal" value="${String.format('%.2f', total)}" />
    <p><strong>Precio Total :  <c:out value="${formattedTotal}"/></strong></p>

    <!--Botones de vuelta al index-->

    <form action="../Controlador" method="post"> 
        <a href="../index.jsp"><button type="button" class="btn btn-success">Seguir Comprando</button></a>
        <input type="submit" name ="pagar" class="btn btn-outline-primary" value="Pagar">
    </form>
</c:if>


<!--En caso de estar vacio, se le indicará al cliente-->

<c:if test="${empty carro}">
    <H1>El carrito se encuentra vacio!</H1>

    <!-- El precio Total será de 0 -->
    <c:set var="total" value="0" />

    <img src="../img/carritovacio.png" height="450" width="450">

    <p><strong>Precio Total :  <c:out value="${total}"/></strong></p>
        
    <a href="../index.jsp"><button type="button" class="btn btn-success">Seguir Comprando</button></a>
</c:if>

</center> 
</BODY>
</HTML>


