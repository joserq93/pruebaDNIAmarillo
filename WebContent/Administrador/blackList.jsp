<%@page import="beans.PersonaBean"%>
<%@page import="java.util.Vector"%>
<%@include file="menuPrincipal.jsp" %>
<%Vector<PersonaBean> blacklist = (Vector<PersonaBean>) request.getAttribute("blacklist");%>
<div id="header"><h1>BLACK LIST</h1></div>
	<div class="table-responsive" style="width: auto;">
  	<div id="tablapedido">
  		<table class="table table-hover table-bordered">
 			<thead>
 			<tr class="btn-info">
 			<th style="width:170px; text-align: center;" valign="middle">Nro</th>
 			<th style="width:800px; text-align: center;" valign="middle">Nombre de Cliente</th>
 			<th style="width:300px; text-align: center;" valign="middle">Nro Pedidos Cancelados</th>
 			</tr>
 			</thead>
 			<tbody>
 			<% for(int i=0;i<blacklist.size();i++){%>
	 			<tr>
		 			<td style="width:170px; text-align: center; vertical-align:middle;"><%=i+1%></td>
		 			<td style="width:800px; text-align: center; vertical-align:middle;"><%=blacklist.get(i).getNombres()+" "+blacklist.get(i).getApellidoPaterno()+" "+blacklist.get(i).getApellidoMaterno()%></td>
		 			<td style="width:300px; text-align: center; vertical-align:middle;"><%=blacklist.get(i).getCount()%></td>
		 		</tr>
 			<%}%>
 			</tbody>
  		</table>
  		
	</div>
</div>
</div>
</body>
</html>
<%}%>