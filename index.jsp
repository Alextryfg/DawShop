<%-- Minitienda --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
    <head>
      <title>Musica para DAA</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<!-- La hoja de estilo a utilizar -->
	<link rel="stylesheet" type="text/css" href="css/style.css">

</head>




<body>
      
	<table align="center" border="0">
		<!--User: admin Password: 1234 TOMCAT-->
		<tr> 
		<th><img src="" ALIGN="center"></th>
		<th><font face="Times New Roman,Times" size="+3">Musica para DAA</font></th>
		<th><img src="" ALIGN="center"></th>
		</tr>
    </table>
    <hr>
	<center>
		
		<form action="Controlador" method = "post">
		<b>CD:</b> 
		<select name="disco">
		<option>Yuan | The Guo Brothers | China | $14.95</option>
		<option>Drums of Passion | Babatunde Olatunji | Nigeria | $16.95</option>
		<option>Kaira | Tounami Diabate| Mali | $16.95</option>
		<option>The Lion is Loose | Eliades Ochoa | Cuba | $13.95</option>
		<option>Dance the Devil Away | Outback | Australia | $14.95</option>
		<option>Record of Changes | Samulnori | Korea | $12.95</option>
		<option>Djelika | Tounami Diabate | Mali | $14.95</option>
		<option>Rapture | Nusrat Fateh Ali Khan | Pakistan | $12.95</option>
		<option>Cesaria Evora | Cesaria Evora | Cape Verde | $16.95</option>
		<option>DAA | GSTIC | Spain | $50.00</option>
		</select>
		<b>Cantidad:</b>
		<input type="text" name="cantidad" value="1"><p>
		  
		<input type="submit" name ="seleccion" id="seleccion" class="btn btn-success" value="Producto a carrito" style="margin-top: 10px;margin-bottom: 10px;">
		<input type="submit" name="view" class="btn btn-success" value="Ver carrito" style="margin-top: 10px;margin-bottom: 10px;">		

	    </form>

		<hr><p>

            <!--Muestro el mensaje de inicio de sesion en caso de que si sea null-->

        <c:if test="${usuario eq null}">
            <form action="formularioUsers" method="post">
            <th><font face="Times New Roman,Times" size="+3">Inicia Sesion o Registrate!</font></th><p></p>
            <input type="submit" name="inicioSesion" class="btn btn-outline-primary" value="Iniciar Sesion" style="margin-top: 10px;margin-bottom: 10px;">
            <input type="submit" name="registro" class="btn btn-outline-primary" value="Registrarse" style="margin-top: 10px;margin-bottom: 10px;">
            </form>
        </c:if>

        <!--En caso contrario, muestro el contenido de la variable-->
        <c:if test="${not empty usuario}">
			<p>Sesion iniciada con el usuario <strong><c:out value="${username}"/></strong></p>
			<form id="formulariologout" action="formularioUsers" method="post">
			<input type="submit" name="verPedidos" class="btn btn-success" value="Ver Pedidos" style="margin-top: 10px;margin-bottom: 10px;">
			<input type="submit" name="cerrarSesion" class="btn btn-outline-primary" value="Cerrar Sesion" style="margin-top: 10px;margin-bottom: 10px;">
				
			</form>
        </c:if>



	</center>
	  

	
</html>
