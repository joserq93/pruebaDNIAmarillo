<%@page import="beans.DistritoBean"%>
<%@page import="beans.RolBean"%>
<%@page import="java.util.Vector"%>
<%@include file="menuPrincipal.jsp" %>
<%Vector<UsuarioBean> listausuarios = (Vector<UsuarioBean>) request.getAttribute("listausuarios"); 
Vector<RolBean> cargo=(Vector<RolBean>)request.getAttribute("cargo");
Vector<DistritoBean> distrito=(Vector<DistritoBean>)request.getAttribute("distrito");
String mensaje3 =(String) request.getAttribute("mensaje3");
String mensaje4 =(String) request.getAttribute("mensaje4");
String mensaje5 =(String) request.getAttribute("mensaje5");
String mensaje6 =(String) request.getAttribute("mensaje6");
%>
<div id="header"><h1>LISTA DE EMPLEADOS</h1></div>
	<div class="table-responsive" style="width: auto;">
  	<div id="tablapedido">
  		<div style="float: right;"><button class="btn-large btn btn-info" type="button" onclick="location.href='Usuario?accion=RegistrarEmpleado'"><b>Registrar Empleado</b></button></div>
  		<br><br>
  		<table class="table table-hover table-bordered">
 			<thead>
 			<tr class="btn-info">
 			<th style="width:150px; text-align: center;" valign="middle">Cod. Empleado</th>
 			<th style="width:470px; text-align: center;" valign="middle">Nombre de Empleado</th>
 			<th style="width:150px; text-align: center;" valign="middle">Usuario</th>
 			<th style="width:150px; text-align: center;" valign="middle">Detalle</th>
 			<th style="width:200px; text-align: center;" valign="middle">Eliminar</th>
 			</tr>
 			</thead>
 			<tbody>
 			<% for(int i=0;i<listausuarios.size();i++){%>
	 			<tr>
		 			<td valign="bottom" style="width:150px; text-align: center; vertical-align:middle;"><%=listausuarios.get(i).getPersona().getCodPersona()%></td>
		 			<td style="width:470px; text-align: center; vertical-align:middle;"><%=listausuarios.get(i).getPersona().getNombres()+" "+listausuarios.get(i).getPersona().getApellidoPaterno()+" "+listausuarios.get(i).getPersona().getApellidoMaterno()%></td>
		 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=listausuarios.get(i).getUsuario()%></td>
		 			<td style="width:150px; text-align: center; vertical-align:middle;">
		 				<button type="button" style="background-color:transparent; border-color:transparent; outline:transparent;" onclick="location.href='Usuario?accion=DetalleEmpleado&&id=<%=listausuarios.get(i).getCodUsuario()%>'">
		 				<img alt="" src="<%=request.getContextPath()%>/images/detalle.png"></button>
		 				
		 			</td>
		 			<td style="width:200px; text-align: center; vertical-align:middle;">
			 			<a type="button" style="background-color:transparent; border-color:transparent; outline:transparent;" onclick="return confirm('¿Deseas eliminar al empleado?');" href="Usuario?accion=EliminarEmpleado&&id=<%=listausuarios.get(i).getCodUsuario()%>">
		 				<img alt="" src="<%=request.getContextPath()%>/images/eliminar.png"></a>
		 			</td>
	 			</tr>
 			<%}%>
 			</tbody>
  		</table>
  		<%if(mensaje3!=null){ %>
                    	<center>
                    	<div class="alert alert-danger"><strong><%=mensaje3 %></strong></div>
                    	</center>
         <%} %>    
        <%if(mensaje4!=null){ %>
                    	<center>
                    	<div class="alert alert-success"><strong><%=mensaje4 %></strong></div>
                    	</center>
         <%} %>    
          <%if(mensaje6!=null){ %>
                    	<center>
                    	<div class="alert alert-danger"><strong><%=mensaje6 %></strong></div>
                    	</center>
         <%} %>    
        <%if(mensaje5!=null){ %>
                    	<center>
                    	<div class="alert alert-success"><strong><%=mensaje5 %></strong></div>
                    	</center>
         <%} %>        	
	</div>
</div>
</div>
</body>
</html>
<%}%>