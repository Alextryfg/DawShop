<%-- Minitienda --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<head>
    <title>Carrito de Compra</title>

</head>
<BODY BGCOLOR="#FDF5E6">

<center> 
<H1>Carrito</H1>
<tr> 
<td><b>Numero</b></td>
<td><b>Titulo del Disco</b></td> 
<td><b>Cantidad</b></td> 
<td><b>Importe</b></td> 
</tr>

<c:set var="lista" value="${carro.compra}"/>

<c:if test="${empty lista}">
  La lista esta vacia.
</c:if>

<p>Esto es: <c:out value="${lista[0].id}"/></p>

<c:forEach var="producto" items="${lista}">
<tr>
      <form action="Controlador">
      <td><b><input type="text" name="disco" value="${producto.num}" readonly></b></td>
      <td><b><c:out value="${producto.id}"/></b></td>
      <td><b><c:out value="${producto.cantidad}"/></b></td>
      <td><b><c:out value="${producto.precioMult}"/></b></td>
      <!--<td><bd><input type="submit" name="Eliminar" value="Eliminar"></b></td>-->      
      </form>    
</tr>
</c:forEach>


<input type="submit" name="Seguir Comprando" value="Seguir Comprando"> 
<input type="submit" name="Pagar" value="Pagar"> 
</form>
</center> 
</BODY></HTML>


