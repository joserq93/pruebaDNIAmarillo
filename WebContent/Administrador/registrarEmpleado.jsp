<%@page import="beans.DistritoBean"%>
<%@page import="beans.RolBean"%>
<%@page import="java.util.Vector"%>
<%@include file="menuPrincipal.jsp" %>
<%Vector<RolBean> cargo=(Vector<RolBean>)request.getAttribute("cargo");
Vector<DistritoBean> distrito=(Vector<DistritoBean>)request.getAttribute("distrito");%>
<script src="<%=request.getContextPath()%>/js/validCampo.js"></script>
<script src="<%=request.getContextPath()%>/js/valid.js"></script>
<link href="<%=request.getContextPath()%>/css/calendario.css" type="text/css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/calendar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/calendar-es.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/calendar-setup.js" type="text/javascript"></script>
<script>
	function validarexistente(){
		var dnivar = $('#dni').val();
		$.post('<%=request.getContextPath()%>/Usuario', {
			accion : 'verificarUsuarioExistente',
			dni: dnivar,
		}, function(responseText) {
			
			$('#resultado').html("");
			var respuesta=responseText['object'];
			console.log(respuesta);
			if(respuesta===false){
				document.getElementById('divrespuesta').style.display = 'block';
				$('#divrespuesta').html("<div class='alert alert-danger' role='alert'>Ya existe una persona registrado con ese dni</div><input type='hidden' id='dnicomp' value='existente'>");
			}
			if(respuesta==true){
				document.getElementById('divrespuesta').style.display = 'none';
				$('#resultado').html("<input type='hidden' id='dnicomp' value='disponible'>");
			}
			
		});
	}
	function validarform(){
		var x=document.getElementById("dnicomp").value;
		if(form10.nombres.value==""||form10.distrito.value==""||form10.apellidoPaterno.value==""||form10.apellidoMaterno.value==""||form10.usuario.value==""||form10.clave.value==""||form10.dni.value==""||form10.fecha.value==""||form10.celular.value==""||form10.correo.value==""||form10.direccion.value==""){
		}else{
			if(x=='existente'){
				return false;
			}else{
				return true;
			}
		}
	}
	
	
	function verificarDatos(){
		var nombre=$("#letras2").val().trim().length;
		var apePat=$("#letras3").val().trim().length;
		var apeMat=$("#letras5").val().trim().length;
		var direccion=$("#letras1").val().trim().length;
		var tlf=$("#letras8").val().trim().length;
		var pass=$("#letras9").val().trim().length;
		var dni =$("#dni").val().trim().length;
		var usuario=$("#letras4").val().trim().length;
		var email=$("#correo").val().trim();
		
		var fecha =$("#fecha").val().trim().length;
		var distrito=$("#distrito option:selected").text();
		console.log(fecha+" "+distrito);

	    var expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    if(nombre>3 && apePat>3 && apeMat>3 && direccion>3 && pass>3 && tlf==9 && dni==8 && usuario>3 && distrito!="Seleccionar" && fecha==10 && expr.test(email)){
			document.getElementById('divnombre').style.display = 'none';
			document.getElementById('divapePat').style.display = 'none';
			document.getElementById('divapeMat').style.display = 'none';
			document.getElementById('divtlf').style.display = 'none';
			document.getElementById('divdireccion').style.display = 'none';
			document.getElementById('divpass').style.display = 'none';
			document.getElementById('divcorreo').style.display = 'none';
			document.forms['loginForm'].submit();	
			
		}else{
			if(nombre>3){
				document.getElementById('divnombre').style.display = 'none';
			}else{
				document.getElementById('divnombre').style.display = 'block';
			}
			
			if(apePat>3){
				document.getElementById('divapePat').style.display = 'none';
			}else{
				document.getElementById('divapePat').style.display = 'block';
			}
			
			if(apeMat>3){
				document.getElementById('divapeMat').style.display = 'none';
			}else{
				document.getElementById('divapeMat').style.display = 'block';
			}
			
			if(direccion>3){
				document.getElementById('divdireccion').style.display = 'none';
			}else{
				document.getElementById('divdireccion').style.display = 'block';
			}
			if(usuario>3){
				document.getElementById('divusuario').style.display = 'none';
			}else{
				document.getElementById('divusuario').style.display = 'block';
			}
			
			if(pass>3){
				document.getElementById('divpass').style.display = 'none';
			}else{
				document.getElementById('divpass').style.display = 'block';
			}
			if(tlf==9){
				document.getElementById('divtlf').style.display = 'none';
			}else{
				document.getElementById('divtlf').style.display = 'block';
			}
			if(dni==8){
				document.getElementById('divdni').style.display = 'none';
			}else{
				document.getElementById('divdni').style.display = 'block';
			}
			if(fecha==10){
				document.getElementById('divfecha').style.display = 'none';
			}else{
				document.getElementById('divfecha').style.display = 'block';
			}
		    if ( expr.test(email) ){
		    	document.getElementById('divcorreo').style.display = 'none';
		    }else{
		    	document.getElementById('divcorreo').style.display = 'block';
		    }
			if(distrito!="Seleccionar"){
				document.getElementById('divdistrito').style.display = 'none';
			}else{
				document.getElementById('divdistrito').style.display = 'block';
			}
		}
		//
		//return true;
	}
	
	</script>
<div id="header"><h1>NUEVO EMPLEADO</h1></div>
	<div class="table-responsive" style="width: auto;">
  	<div id="tablapedido">
  		<form id="form10"  name="loginForm" action="<%=request.getContextPath()%>/Usuario" method="post">
  		<table class="table table-hover">
 			<thead>
 			<tr class="btn-info">
 			<th style="width:100%; text-align: left;" valign="middle" colspan="4">GENERAL</th>
 			</tr>
 			</thead>
 			<tbody>
 			<tr>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>Cargo</b></td>
 				<td style="width:30%; text-align: left; vertical-align:middle;">
 					<select size="1" class="form-control" name="cargo" style="text-align: center;" required="required">
 					<%for(int i=0;i<cargo.size();i++){%>
 						<option value="<%=cargo.get(i).getCodRol()%>" <%if(cargo.get(i).getDescripcion().equals("Tecnico")){%>selected=selected<%}%> <%if(cargo.get(i).getDescripcion().equals("Cliente")){%>style="display: none;"<%}%>><%=cargo.get(i).getDescripcion()%></option>
 					<%}%>
 					</select>
 				</td>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>Direcci&oacute;n</b></td>
 				<td style="width:30%; text-align: left; vertical-align:middle;"><input type="text" id="letras1" style="text-align:center;" name="direccion" class="form-control" value="" required="required"></td>
 			</tr>
 			<tr>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>Nombres</b></td>
 				<td style="width:30%; text-align: left; vertical-align:middle;"><input type="text" name="nombres" id="letras2" class="form-control" style="text-align:center;" value="" required="required"></td>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>Distrito</b></td>
 				<td style="width:30%; text-align: left; vertical-align:middle;">
 					<select size="1" class="form-control" style="text-align: center;" id="distrito" name="distrito" required="required">
 					<option style="display: none;" value="">Seleccionar</option>
 					<%for(int i=0;i<distrito.size();i++){%>
 						<option value="<%=distrito.get(i).getCodDistrito()%>"><%=distrito.get(i).getNombre()%></option>
 					<%}%>
 					</select>
 				</td>
 			</tr>
 			<tr>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>Apellido Paterno</b></td>
 				<td style="width:30%; text-align: left; vertical-align:middle;"><input type="text" class="form-control" id="letras3" name="apellidoPaterno" style="text-align:center;" value="" required="required"></td>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>Usuario</b></td>
 				<td style="width:30%; text-align: left; vertical-align:middle;"><input type="text" style="text-align:center;" id="letras4" name="usuario" class="form-control" value="" required="required"></td>
 			</tr>
 			<tr>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>Apellido Materno</b></td>
 				<td style="width:30%; text-align: left; vertical-align:middle;"><input type="text" class="form-control" id="letras5" style="text-align:center;" name="apellidoMaterno" value="" required="required"></td>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>Password</b></td>
 				<td style="width:30%; text-align: left; vertical-align:middle;"><input type="password" style="text-align:center;" id="letras9" class="form-control" value="" name="clave" required="required"></td>
 			</tr>
 			<tr>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>DNI</b></td>
 				<td style="width:30%; text-align: left; vertical-align:middle;" ><input type="text" maxlength="8"  id="dni" class="form-control dnival" onblur="validarexistente();" style="text-align:center;" name="dni" value="" required="required"></td>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>Fecha de Nacimiento</b></td>
 				<td style="width:30%; text-align: left; vertical-align:middle;">
 				<input type="text" class="form-control" style="text-align:center; cursor: pointer;" name="fecha" value="" id="fecha" required="required" readonly="readonly">
 				<script src="<%=request.getContextPath()%>/js/calendar-config.js" type="text/javascript"></script>
 				</td>
 			</tr>
 			</tbody>
 			<thead>
 			<tr class="btn-info">
 			<th style="width:100%; text-align: left;" valign="middle" colspan="4">COMUNICACI&Oacute;N</th>
 			</tr>
 			</thead>
 			<tbody>
 			<tr>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>Celular</b></td>
 				<td style="width:30%; text-align: left;  vertical-align:middle;" ><input type="text" maxlength="9" id="letras8" class="form-control" name="celular" style="text-align:center;" value="" required="required"></td>
 				<td style="width:20%; text-align: right; vertical-align:middle;"><b>Correo Electronico</b></td>
 				<td style="width:30%; text-align: left; vertical-align:middle;" ><input type="email" class="form-control" id="correo" name="correo" style="text-align:center;" value="" required="required"></td>
 			</tr>
 			<tr>
 			<td style="width:100%; text-align: right; vertical-align:middle;" colspan="4">
 				<input type="hidden" name="accion" value="registrarEmpleado">
 				<button class="btn-large btn btn-info" type="button" onclick="verificarDatos();" type="submit"><b>Guardar</b></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				<button class="btn btn-info" type="button" onclick="location.href='Usuario?accion=empleados'"><b>Cancelar</b></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				<br><br>

 			</td>
 			</tr>
 			</tbody>
  		</table>
  		</form>
  		<div id="divrespuesta" style='display:none;'>

 		</div>
  		<div class="alert alert-danger" id="divnombre" style='display:none;'>
  			<strong>Ingrese correctamente un Nombre!</strong> 
		</div>
		<div class="alert alert-danger" id="divapePat" style='display:none;'>
  			<strong>Ingrese correctamente un Apellido Paterno!</strong> 
		</div>
		<div class="alert alert-danger" id="divapeMat" style='display:none;'>
  			<strong>Ingrese correctamente un Apellido Materno!</strong> 
		</div>
		<div class="alert alert-danger" id="divtlf" style='display:none;'>
  			<strong>Ingrese correctamente un Numero Celular!</strong> 
		</div>
		<div class="alert alert-danger" id="divdireccion" style='display:none;'>
  			<strong>Ingrese correctamente un Direccion!</strong> 
		</div>
		<div class="alert alert-danger" id="divpass" style='display:none;'>
  			<strong>Ingrese correctamente un Password!</strong> 
		</div>
		<div class="alert alert-danger" id="divcorreo" style='display:none;'>
  			<strong>Ingrese correctamente un Correo Electronico!</strong> 
		</div>
		<div class="alert alert-danger" id="divusuario" style='display:none;'>
  			<strong>Ingrese correctamente un Usuario!</strong> 
		</div>
		<div class="alert alert-danger" id="divdni" style='display:none;'>
  			<strong>Ingrese correctamente un DNI!</strong> 
		</div>
		<div class="alert alert-danger" id="divdistrito" style='display:none;'>
  			<strong>Ingrese correctamente un Distrito!</strong> 
		</div>	
		<div class="alert alert-danger" id="divfecha" style='display:none;'>
  			<strong>Ingrese correctamente una Fecha!</strong> 
		</div>	

	</div>
</div>
</div>
</body>
</html>
<%}%>