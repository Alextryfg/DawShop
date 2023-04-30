<%-- Minitienda --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<head>
    <title>CARRITO</title>
</head>
<BODY BGCOLOR="#FDF5E6">
<center> 
<H1>CARRITO</H1>
<tr> 
<td><b>TITULO DEL CD</b></td> 
<td><b>Cantidad</b></td> 
<td><b>Importe</b></td> 
</tr>
</center>
<!--
<c:set var="lista" value="${carrito.listaD}" />
<c:forEach var="discoActual" items="${lista}" >
<tr>
      <form action="/proyectoII_PerezGalan_PargaMartinez/Controlador">
      <td><bd><input type="text" name="cd" value="${discoActual.index}" readonly></b></td>
      <td><bd><c:out value="${discoActual.id}"/></b></td>
      <td><bd><c:out value="${discoActual.cantidad}"/></b></td>
      <td><bd><c:out value="${discoActual.precioT}"/></b></td>
      <td><bd><input type="submit" name="Eliminar" value="Eliminar"></b></td>      
      </form>    
</tr>
</c:forEach>
<tr> 
<td><b></b></td> 
<td><b></b></td> 
<form action="/proyectoII_PerezGalan_PargaMartinez/Controlador"> 
<td><b>IMPORTE TOTAL</b></td> 
<td><c:out value="${carrito.importeT}"/></td> 
</tr> 
</table> 
<p> 
<input type="submit" name="Seguir Comprando" value="Seguir Comprando"> 
<input type="submit" name="Pagar" value="Pagar"> 
</form>
</center> -->
</BODY></HTML>


