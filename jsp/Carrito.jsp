<%-- Minitienda --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<head>
    <title>Carrito de Compra</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <!-- La hoja de estilo a utilizar -->
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>

<center> 

    <H1>Carrito</H1>

    <table class="table table-dark" style="" >
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Titulo del Disco</th>
            <th scope="col">Cantidad</th>
            <th scope="col">Importe</th>
            <th scope="col">Opcion</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${compra}" var="item" varStatus="loop">
            <tr>
              <th scope="row"><c:out value="${item.num}"/></th>
              <td><c:out value="${item.id}"/></td>
              <td><c:out value="${item.cantidad}"/></td>
              <td><c:out value="${item.precioMult}"/></td>
              <td><input type="submit" name="out" value="Eliminar"></td> 
            </tr>
          </c:forEach>
        </tbody>
      </table>
    
<a href="index.html"><button type="button" class="btn btn-success">Seguir Comprando</button></a>
<button type="button" class="btn btn-outline-primary">Pagar</button>

<!--Pruebas-->

<p>La ciudad se llama : ${atr}</p>
<p> El id es ${lista[0]}</p>
<p> La compra es ${compra[0].precio}</p>

</form>
</center> 
</BODY></HTML>


