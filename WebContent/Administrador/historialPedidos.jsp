<%@page import="beans.PedidoBean"%>
<%@page import="java.util.Vector"%>
<%@include file="menuPrincipal.jsp" %>
<%Vector<PedidoBean> historialpedidos=(Vector<PedidoBean>)request.getAttribute("historialpedidos"); 
String fechaIn=(String)request.getAttribute("fechaIn");
String fechaFi=(String)request.getAttribute("fechaFi");%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.dataTables.css">
<script src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/table.js"></script>
<link href="<%=request.getContextPath()%>/css/calendario.css" type="text/css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/calendar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/calendar-es.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/calendar-setup.js" type="text/javascript"></script>
<script lang="JavaScript" type="text/JavaScript">
function validar(){
	
	if(form1.fechaIn.value=="" || form1.fechaFi.value==""){
		alert("Complete rango de fechas");
		return false;
	}else{
		var fechaIni =$("#fechaIn").val().trim();
		var fechaFin =$("#fechaFi").val().trim();
		
		if(Date.parse(fechaIni)<=Date.parse(fechaFin)){
			document.getElementById('divrangofechas').style.display = 'none';
			document.forms['form1'].submit();
		}else{
			document.getElementById('divrangofechas').style.display = 'block';
		}
	}
	//document.forms['form1'].submit();
}
</script>
<div id="header"><h1>HISTORIAL DE PEDIDOS</h1></div>
	<div class="table-responsive" style="width: auto;">
  	<div id="tablapedido">
  	<div style="text-align: right; width:auto; vertical-align: middle; padding-left: 42%;">
	<form id="form1" name="form1" action="<%=request.getContextPath()%>/Pedido" method="post">
		<table style="width: 700px;  text-align: right;"><tr><td style="padding-left: 5px; padding-right: 5px;">
		Fecha Inicio</td><td><input type="text" readonly="readonly" class="form-control" style="text-align:center; cursor: pointer;width:200px;" name="fechaIn" value="<%if(fechaIn!=null){%><%=fechaIn%><%}%>" id="fechaIn" required="required">
		<script src="<%=request.getContextPath()%>/js/calendar-config1.js" type="text/javascript"></script></td>
		<td style="padding-left: 5px; padding-right: 5px;">Fecha Fin</td><td><input type="text" readonly="readonly" class="form-control" style="text-align:center; cursor: pointer;width:200px;" name="fechaFi" value="<%if(fechaFi!=null){%><%=fechaFi%><%}%>" id="fechaFi" required="required">
 		<script src="<%=request.getContextPath()%>/js/calendar-config2.js" type="text/javascript"></script></td>
 		<td style="padding-left: 5px; padding-right: 5px;">
 			<input type="hidden" name="accion" value="HistorialDePedidosC">
 			<button  class="btn-large btn btn-info" type="button" onclick="return validar(this.form);"><b>Consultar</b></button>
 		</td></tr>
 		</table>
	<br>
	</form>
	</div>
  		<table id="tabla" class="table table-hover table-bordered ">
 			<thead>
 			<tr class="btn-info">
 			<th style="width:150px; text-align: center;" valign="middle">N° Pedido</th>
 			<th style="width:430px; text-align: center;" valign="middle">Cliente</th>
 			<th style="width:150px; text-align: center;" valign="middle">Fecha</th>
 			<th style="width:150px; text-align: center;" valign="middle">Hora</th>
 			<th style="width:150px; text-align: center;" valign="middle">Estado</th>
 			<th style="width:110px; text-align: center;" valign="middle">Detalle</th>
 			<th style="width:130px; text-align: center;" valign="middle">Comprobante</th>
 			</tr>
 			</thead>
 			
 			<tbody>

	 		<% for(int i=0;i<historialpedidos.size();i++){%>	
	 			<tr>
	 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=historialpedidos.get(i).getCodPedido()%></td>
	 			<td style="width:430px; text-align: center; vertical-align:middle;"><%=historialpedidos.get(i).getPersona().getNombres()+" "+historialpedidos.get(i).getPersona().getApellidoPaterno()+" "+historialpedidos.get(i).getPersona().getApellidoMaterno()%></td>
	 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=historialpedidos.get(i).getFechaPedido()%></td>
	 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=historialpedidos.get(i).getHoraPedido()%></td>
	 			<td style="width:150px; text-align: center; vertical-align:middle;">
	 				<form action="Pedido" method="post">
		 				<input name="accion" type="hidden" value="corregirEstado">
		 				<input type="hidden" name="idpedido" value="<%=historialpedidos.get(i).getCodPedido()%>">
		 				<select class="selectpicker" size="1" name="slt_estado" onchange="this.form.submit()">
						    <option style="display: none;"><%=historialpedidos.get(i).getEstadoPedido().getDescripcion()%></option>
						    <option value="2">En Proceso</option>
						</select>
						</form>
	 			</td>
	 			<td style="width:110px; text-align: center; vertical-align:middle;">
	 					<button type="button" style="background-color:transparent; border-color:transparent; outline:transparent;" onclick="window.open('Pedido?accion=DetallePedido&&id=<%=historialpedidos.get(i).getCodPedido()%>','Detalle Pedido','toolbar=no,location=no,menubar=no,scrollbars=NO,height=660,width=1100')">
		 				<img alt="" src="<%=request.getContextPath()%>/images/detalle.png"></button>
		 		</td>
	 			<td style="width:130px; text-align: center; vertical-align:middle;">
	 					<button type="button" style="background-color:transparent; border-color:transparent; outline:transparent;" onclick="window.open('Comprobante?id=<%=historialpedidos.get(i).getCodPedido()%>')">
		 				<img alt="" src="<%=request.getContextPath()%>/images/comprobante.png"></button>
		 		</td>
	 			</tr>
	 		<%}%>

 			</tbody>
  		</table>
  		 <% if(historialpedidos.size()<0){ %>
  		 <div class="alert alert-danger" id="divnohayDatos" >
  			<strong>No hay datos!</strong> 
		</div>
  		<%} %>
  		 <div class="alert alert-danger" id="divrangofechas" style='display:none;'>
  			<strong>Ingrese correctamente un Rango de fechas!</strong> 
		</div>
  	</div>
	</div>
</div>
</body>
</html>
<%}%>