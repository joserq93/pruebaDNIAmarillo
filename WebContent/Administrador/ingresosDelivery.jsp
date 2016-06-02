<%@page import="beans.IngresosDeliveryBean"%>
<%@page import="java.util.Vector"%>
<%@include file="menuPrincipal.jsp" %>
<%Vector<IngresosDeliveryBean> ingresos = (Vector<IngresosDeliveryBean>) request.getAttribute("ingresos");
String anio=(String)request.getAttribute("anio");%>
<div id="header"><h1>Ingresos Obtenidos por Delivery</h1></div>
	<div class="table-responsive" style="width: auto;">
  	<div id="tablapedido" style="text-align: center;">
  		<div style="text-align: right; width:auto; vertical-align: middle; padding-left: 60%;">
  		<form id="formRep" name="formRep" method="post" action="<%=request.getContextPath()%>/Reporte">
		<table style="width: 700px;  text-align: right;"><tr>
		<td style="padding-left: 5px; padding-right: 5px;"><label style="width:70px; font-size:18px;"><b>A&Ntilde;O:</b></label></td>
		<td style="padding-left: 5px; padding-right: 5px;">
		<input type="hidden" name="accion" value="IngresosDeliveryC">
		<select id="anio" name="anio" size="1" onchange="this.form.submit()" class="form-control" style="width:200px; text-align: center; font-size:13px;">
			<option value="" style="display: none;">Seleccionar A&ntilde;o</option>
			<%for(int i=2013; i<2016; i++){ %>
				<option value="<%=i%>"
				<%if(anio!=null){if(i==Integer.parseInt(anio)){%>
					selected="selected"<%}}%>>
				<%=i%></option>
			<%}%>
		</select>
		</td>
		</tr></table>
		</form>
		</div>
		<br>
  		<table class="table table-hover table-bordered">
 			<thead>
 			<tr class="btn-info">
 			<th style="width:635px; text-align: center;" valign="middle">Mes</th>
 			<th style="width:635px; text-align: center;" valign="middle">Monto Ingresado</th>
 			</tr>
 			</thead>
 			<tbody>
 			<% for(int i=0;i<ingresos.size();i++){%>
	 			<tr>
		 			<td style="width:635px; text-align: center; vertical-align:middle;"><%if(ingresos.get(i).getMes().equals("01")){%>Enero<%}else if(ingresos.get(i).getMes().equals("02")){%>Febrero<%}else if(ingresos.get(i).getMes().equals("03")){%>Marzo<%}else if(ingresos.get(i).getMes().equals("04")){%>Abril<%}else if(ingresos.get(i).getMes().equals("05")){%>Mayo<%}else if(ingresos.get(i).getMes().equals("06")){%>Junio<%}else if(ingresos.get(i).getMes().equals("07")){%>Julio<%}else if(ingresos.get(i).getMes().equals("08")){%>Agosto<%}else if(ingresos.get(i).getMes().equals("09")){%>Septiembre<%}else if(ingresos.get(i).getMes().equals("10")){%>Octubre<%}else if(ingresos.get(i).getMes().equals("11")){%>Noviembre<%}else if(ingresos.get(i).getMes().equals("12")){%>Diciembre<%}%></td>
		 			<td style="width:635px; text-align: center; vertical-align:middle;"><%=ingresos.get(i).getMontoTotal()%></td>
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