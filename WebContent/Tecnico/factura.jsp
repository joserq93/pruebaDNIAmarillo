<%@page import="beans.UsuarioBean"%>
<%@page import="beans.SunatBean"%>
<%@page import="beans.ComprobanteBean"%>
<%@page import="beans.DetallePedidoBean"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
   	 UsuarioBean usuario=(UsuarioBean) session.getAttribute("usuario");
    %>
<% if(usuario==null){
	request.setAttribute("mensaje", "No inicio sesión");
	request.getRequestDispatcher("/login.jsp").forward(request, response);
}else{%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comprobante de Pago</title>
<%Vector<DetallePedidoBean> detallePedidos=(Vector<DetallePedidoBean>)request.getAttribute("detallePedidos"); 
ComprobanteBean comprobante=(ComprobanteBean)request.getAttribute("comprobante");
String cod=(String)request.getAttribute("cod");
String com=(String)request.getAttribute("com");
Double sigv=(Double)request.getAttribute("sigv");
Double igv=(Double)request.getAttribute("igv");
Double vuelto=(Double)request.getAttribute("vuelto");
SunatBean sunat=(SunatBean)request.getAttribute("sunat");%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-latest.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsExport/tableExport.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/FileSaver/FileSaver.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/html2canvas/html2canvas.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsPDF/jspdf.min.js"></script>
 <style type="text/css">
  <!--
  .table-striped {
      background-color: white;
  }
  .table-striped > thead > tr > th {
      background-color: white;
	  color: black;
  }
  .table-striped > tbody > tr:nth-child(2n+1) > td {
      background-color: white;
  }
  -->
  </style>
  <script type="text/javaScript">
  function doExport (params){
    var options = {tableName: 'Countries',
	               worksheetName: 'Countries by population'};
	
	$.extend(true, options, params);
	$('#customers').tableExport(options);
  }
  </script>
</head>
<body>
	 <div>
            <a href="#" onClick ="doExport({type:'png'});" style="font-size: 26px; color: blue;"> <img src='<%=request.getContextPath()%>/images/png.png' width='24px'> PNG</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#" onClick ="doExport({type:'pdf'});" style="font-size: 26px; color: blue;"> <img src='<%=request.getContextPath()%>/images/pdf.png' width='24px'> PDF</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	<a href="javascript:void(0)" id="imprime" style="font-size: 26px; color: blue;"><img src='<%=request.getContextPath()%>/images/print.png' width='26px'>IMPRIMIR</a>
          <!-- Factura -->
          <div id="PrintArea">
          <table id="customers" class="table-striped" border="1" cellspacing="0" cellpadding="0" bordercolor="black" align="center" ><tr><td>
          <table  style="padding: 15px;">
            <thead>
              <tr>
                <th style="text-align: center; vertical-align: middle;"><img alt="" src="<%=request.getContextPath()%>/images/logo.png"></th>
                <th style="text-align: center; vertical-align: middle;">
                <h2><b>SHOP MEDICAL</b></h2>
                JR. 3 DE FEBRERO NRO 1279 URB. APOLO
               	<br>LA VICTORIA - LIMA
               	</th>
               	<th colspan="3" style="padding-left: 70px; vertical-align: middle;">
               		<table border="1" cellpadding="0" cellspacing="0" bordercolor="darkblue"><thead><tr>
               		<th style="padding: 5px;">
               			<b>FACTURA ELECTR&Oacute;NICA
               			<br>RUC: 20508681331
               			<br>F<%=com%>-<%=cod%></b>
               		</th>
               		</tr></thead></table>
               	</th>
              </tr>
              <tr>
              <th colspan="5" style="text-align: left;">
              <hr>
	              <table>
		              <tr>
			              <td>FECHA EMISION</td>
			              <td>: <%=comprobante.getFechaEmision().substring(0,10)%></td>
		              </tr>
		              <tr>
		              	  <td>RAZ&Oacute;N SOCIAL</td>
		              	  <td>: <%=sunat.getRazonSocial()%></td>
		              </tr>
		              <tr>
		              	  <td>RUC</td>
		              	  <td>: <%=sunat.getRuc()%></td>
		              </tr>
		               <tr>
			              <td>DIRECCI&Oacute;N</td>
			              <td>: <%=sunat.getDireccion()%></td>
		              </tr>
		               <tr>
			              <td>TIPO DE MONEDA</td>
			              <td>: NUEVOS SOLES</td>
		              </tr>
	              </table>
             </th>
              </tr>
              <tr>
              <th style="padding-bottom: 5px; padding-left: 10px; padding-right: 10px;"><br>C&Oacute;DIGO</th>
              <th style="padding-bottom: 5px; padding-left: 10px; padding-right: 10px;"><br>DESCRIPCI&Oacute;N</th>
              <th style="padding-bottom: 5px; padding-left: 10px; padding-right: 10px;"><br>CANT.</th>
              <th style="padding-bottom: 5px; padding-left: 10px; padding-right: 10px;"><br>PRECIO UNIT.</th>
              <th style="padding-bottom: 5px; padding-left: 10px; padding-right: 10px;"><br>SUBTOTAL</th>
              </tr>
            </thead>
            <tbody>
             <% for(int i=0;i<detallePedidos.size();i++){%>
	 			<tr>
	 			<td style="text-align: center; vertical-align:middle;"><%=detallePedidos.get(i).getCodMedicamento()+""+detallePedidos.get(i).getCodCantidadxPresentacion()+""+detallePedidos.get(i).getCodUnidad()%></td>
	 			<td style="text-align: center; vertical-align:middle;"><%=detallePedidos.get(i).getPresentacionxmedicamento().getAbreviatura()%></td>
	 			<td style="text-align: center; vertical-align:middle;"><%=detallePedidos.get(i).getCantidad()%></td>
	 			<td style="text-align: center; vertical-align:middle;"><%=detallePedidos.get(i).getPresentacionxmedicamento().getPrecio()%></td>
	 			<td style="text-align: center; vertical-align:middle;"><%=detallePedidos.get(i).getPrecioTotal()%></td>
	 			</tr>
 			 <%}%>
              
              <tr>
              <td colspan="2"><br></td>
              <td colspan="2" style="padding-left: 40px; vertical-align: top;"><br><b>TOTAL A PAGAR:</b></td>
              <td style="padding-left: 20px;"><br><b>S/. <%=detallePedidos.get(0).getPedido().getMontoTotal()%></b><hr></td>
              </tr>
              
              <tr>
              <td colspan="2"></td>
              <td colspan="2" style="padding-left: 40px; vertical-align: top;"><b>SUBTOTAL: <br> IGV (18%):</b></td>
              <td style="padding-left: 20px;"><b>S/. <%=sigv%><br>S/. <%=igv%></b><hr></td>
              </tr>
              
              <tr>
              <td colspan="2"></td>
              <td colspan="2" style="padding-left: 40px; vertical-align: top;"><b>IMPORTE TOTAL:</b></td>
              <td style="padding-left: 20px;"><b>S/. <%=detallePedidos.get(0).getPedido().getMontoTotal()%></b><hr></td>
              </tr>
   
            </tbody>
          </table>
          
          <br><div style="page-break-before:always; display: block;"></div>
          <table align="center">
           			<thead>
           				<tr bgcolor="gray">
			                <th style="text-align: center; vertical-align: middle; color: white;" >**TICKET PARCIAL DE VENTA**</th>
			            </tr>
			            <tr>
			            	<th>
			            		<h2>SHOP MEDICAL</h2>
			            		SHOP MEDICAL S.A.C. - RUC: 20508681331
			            		<br><%=detallePedidos.get(0).getSucursal().getDireccion()%>
			            		<br><%=detallePedidos.get(0).getSucursal().getDistrito().getNombre()%> - LIMA
			            		<br><br>TICKET PARCIAL DE VENTA
			            	<th>
			            </tr>
           			</thead>
           			<tbody><tr>
            			<td><br>
            				<table>
					              <tr>
						              <td><b>Nro FACTURA</b></td>
						              <td><b>: F<%=com%>-<%=cod%></b></td>
					              </tr>
					              <tr>
						              <td><b>DIRECCI&Oacute;N</b></td>
						              <td><b>: <%=detallePedidos.get(0).getPedido().getPersona().getDireccion()%></b></td>
					              </tr>
					              <tr>
					              	  <td><b>TOTAL A PAGAR</b></td>
					              	  <td><b>: S/. <%=detallePedidos.get(0).getPedido().getMontoTotal()%></b></td>
					              </tr>
					              <tr>
					              	  <td><b>EFECTIVO</b></td>
					              	  <td><b>: S/. <%=detallePedidos.get(0).getPedido().getMontoCancelar()%></b></td>
					              </tr>
					               <tr>
						              <td><b>VUELTO</b></td>
						              <td><b>: S/. <%=vuelto%></b></td>
					              </tr>
				              </table>
            			</td>
            		</tr>
            	</tbody>
            </table>
          
          </td></tr>
          
          </table>
          </div>
        </div>
        
        <script src='<%=request.getContextPath()%>/js/impresion/jquery.PrintArea.js'></script>
    	<script type="text/javascript">
		$(document).ready(function() {
		    $("#imprime").click(function () {
		        $("div#PrintArea").printArea();
		    })
		});
		</script>
</body>
</html>
<%}%>