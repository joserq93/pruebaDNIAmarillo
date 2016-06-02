<%@page import="beans.PresentacionxMedicamentoBean"%>
<%@page import="java.util.Vector"%>
<%@include file="menuPrincipal.jsp" %>
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
}
</script>
<%Vector<PresentacionxMedicamentoBean> rankmed = (Vector<PresentacionxMedicamentoBean>) request.getAttribute("rankmed");
String fechaIn=(String)request.getAttribute("fechaIn");
String fechaFi=(String)request.getAttribute("fechaFi");
int cont=0;
if(rankmed.size()<10){cont=rankmed.size();}else{ cont=10;}%>
<div id="header"><h1>LISTA DE MEDICAMENTOS M&Aacute;S VENDIDOS</h1></div>
	<div class="table-responsive" style="width: auto;">
  	<div id="tablapedido">
  	<div style="text-align: right; width:auto; vertical-align: middle; padding-left: 42%;">
	<form id="form1" name="form1" action="<%=request.getContextPath()%>/Reporte" method="post">
		<table style="width: 700px;  text-align: right;"><tr><td style="padding-left: 5px; padding-right: 5px;">
		Fecha Inicio</td><td><input type="text" readonly="readonly" class="form-control" style="text-align:center; cursor: pointer;width:200px;" name="fechaIn" value="<%if(fechaIn!=null){%><%=fechaIn%><%}%>" id="fechaIn" required="required">
		<script src="<%=request.getContextPath()%>/js/calendar-config1.js" type="text/javascript"></script></td>
		<td style="padding-left: 5px; padding-right: 5px;">Fecha Fin</td><td><input type="text" readonly="readonly" class="form-control" style="text-align:center; cursor: pointer;width:200px;" name="fechaFi" value="<%if(fechaFi!=null){%><%=fechaFi%><%}%>" id="fechaFi" required="required">
 		<script src="<%=request.getContextPath()%>/js/calendar-config2.js" type="text/javascript"></script></td>
 		<td style="padding-left: 5px; padding-right: 5px;">
 			<input type="hidden" name="accion" value="RankingMedicamentosC">
 			<button  class="btn-large btn btn-info" type="button" onclick="return validar(this.form);"><b>Consultar</b></button>
 		</td></tr>
 		</table>
	<br>
	</form>
	</div>
  		<table class="table table-hover table-bordered">
 			<thead>
 			<tr class="btn-info">
 			<th style="width:170px; text-align: center;" valign="middle">Nro</th>
 			<th style="width:800px; text-align: center;" valign="middle">Medicamentos</th>
 			<th style="width:300px; text-align: center;" valign="middle">Cantidades Vendidas</th>
 			</tr>
 			</thead>
 			<tbody>
 			<% for(int i=0;i<cont;i++){%>
	 			<tr>
		 			<td style="width:170px; text-align: center; vertical-align:middle;"><%=i+1%></td>
		 			<td style="width:800px; text-align: center; vertical-align:middle;"><%=rankmed.get(i).getAbreviatura()%></td>
		 			<td style="width:300px; text-align: center; vertical-align:middle;"><%=rankmed.get(i).getCount()%></td>
		 		</tr>
 			<%}%>
 			</tbody>
  		</table>
  		<div class="alert alert-danger" id="divrangofechas" style='display:none;'>
  			<strong>Ingrese correctamente un Rango de fechas!</strong> 
		</div>
	</div>
</div>
</div>
</body>
</html>
<%}%>