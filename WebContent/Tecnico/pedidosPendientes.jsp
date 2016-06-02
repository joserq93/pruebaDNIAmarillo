<%@page import="beans.PedidoBean"%>
<%@page import="java.util.Vector"%>
<%@include file="menuPrincipal.jsp" %>
<%Vector<PedidoBean> pedidosPendientes=(Vector<PedidoBean>)request.getAttribute("pedidosPendientes"); %>
<div id="header"><h1>PEDIDOS PENDIENTES</h1></div>
	<div class="table-responsive" style="width: auto;">
  	<div id="tablapedido">
  		<table class="table table-hover table-bordered">
 			<thead>
 			<tr class="btn-info">
 			<th style="width:150px; text-align: center;" valign="middle">N° Pedido</th>
 			<th style="width:470px; text-align: center;" valign="middle">Cliente</th>
 			<th style="width:150px; text-align: center;" valign="middle">Fecha</th>
 			<th style="width:150px; text-align: center;" valign="middle">Hora</th>
 			<th style="width:150px; text-align: center;" valign="middle">Detalle</th>
 			<th style="width:200px; text-align: center;" valign="middle">Comprobante</th>
 			</tr>
 			</thead>
 			<tbody>
 			<% for(int i=0;i<pedidosPendientes.size();i++){%>
	 			<tr>
		 			<td valign="bottom" style="width:150px; text-align: center; vertical-align:middle;"><%=pedidosPendientes.get(i).getCodPedido()%></td>
		 			<td style="width:470px; text-align: center; vertical-align:middle;"><%=pedidosPendientes.get(i).getPersona().getNombres()+" "+pedidosPendientes.get(i).getPersona().getApellidoPaterno()+" "+pedidosPendientes.get(i).getPersona().getApellidoMaterno()%></td>
		 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedidosPendientes.get(i).getFechaPedido()%></td>
		 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedidosPendientes.get(i).getHoraPedido()%></td>
		 			<td style="width:150px; text-align: center; vertical-align:middle;">
		 				<form action="Pedido" method="post">
		 					<button type="button" style="background-color:transparent; border-color:transparent; outline:transparent;" onclick="window.open('Pedido?accion=DetallePedido&&id=<%=pedidosPendientes.get(i).getCodPedido()%>','Detalle Pedido','toolbar=no,location=no,menubar=no,scrollbars=NO,height=660,width=1100')">
		 					<img alt="" src="<%=request.getContextPath()%>/images/detalle.png"></button>
		 				</form>
		 			</td>
		 			<td style="width:200px; text-align: center; vertical-align:middle;">
			 			<form action="Pedido" method="post">
			 				<input type="hidden" name="idpedido" value="<%=pedidosPendientes.get(i).getCodPedido()%>">
			 				<input type="hidden" name="tipocomprobante" value="<%=pedidosPendientes.get(i).getTipoComprobante()%>">
			 				<%if(pedidosPendientes.get(i).getTipoComprobante().equals("Factura")){%>
			 					<input type="hidden" name="ruc" value="<%=pedidosPendientes.get(i).getRuc()%>">
			 				<%}%>
			 				<input type="hidden" name="accion" value="generarComprobante">
			 				<button  class="btn-large clickSubmit btn btn-primary" type="submit">Generar Comprobante</button>
			 			</form>
		 			</td>
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