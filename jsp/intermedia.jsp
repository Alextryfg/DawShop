<%
String url = (String)request.getAttribute("url");
%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="refresh" content="0; URL=<%=url%>">
</head>
<body>
  <h1>Procesando...</h1>
  <p>Por favor espera...</p>
</body>
</html>