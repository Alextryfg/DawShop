<%-- Minitienda --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
  <title>Carrito Vacio</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>
  <!-- La hoja de estilo a utilizar -->
  <link rel="stylesheet" type="text/css" href="../css/style.css">

</head>

<body>

  <center>

    <!--Indico los pedidos que ha realizado el usuario logeado-->

    <h3 style="margin-top: 2em; margin-bottom: 1em;">Listado de Pedidos del usuario</h3>
    <c:forEach items="${lista}" var="item">
      <div style="border: 1px solid #ccc; padding: 10px; border-radius: 5px;">
        <ul style="list-style: none; margin: 0; padding: 0;">
          <li style="font-size: 18px; margin-bottom: 10px; font-weight: bold;">Pedido ${item.getId()}</li>
          <li style="margin-bottom: 5px;">Precio Total: ${item.getPrecioTotal()}</li>
        </ul>
      </div>
    </c:forEach>

    <form action="../Controlador" method="post" style="margin-top: 1em;">
      <a href="../index.jsp"><button type="button" class="btn btn-success">Volver atras</button></a>
    </form>

  </center>
</body>

</html>