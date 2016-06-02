<!Doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible' />
<title>Shop Medical S.A.C.</title>
	<link rel="shortcut icon" href="images/icono.ico" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.maximage.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/screen.css" type="text/css" media="screen"  />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css" type="text/css"  />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Bootstrap/css/jquery-ui.min.css">
	
	<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	
	<!--[if IE 6]>
	<style type="text/css" media="screen">
			/*I don't feel like messing with pngs for this browser... sorry*/
			#gradient {display:none;}
	</style>
	<![endif]-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/login-imagenes/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.cycle.all.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.maximage.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/extra.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/validCampo.js"></script>
	<script src="<%=request.getContextPath()%>/js/valid.js"></script>
	<%
	String mensaje = (String) request.getAttribute("mensaje");
	String mensaje2 = (String) request.getAttribute("mensaje2");
	%>
</head>
<body>
		<div id="maximage">
			<img src="images/01.png" width="1400" height="1050" />
			<img src="images/02.png" width="1400" height="1050" />
			<img src="images/03.png" width="1400" height="1050" />
            <img src="images/04.png" width="1400" height="1050" />
            <img src="images/05.png" width="1400" height="1050" />
		</div>
        <div id="cuerpo">
       	      		 <div id="headerLogin"> </div>
                    		<div id="logofondo">
             					<img src="images/logoLM2.png" id="logo">
            				</div>
              			      
              		
            <div class="register-container container">
            <div class="row">
                <div id="form">
                    <form action="<%=request.getContextPath()%>/Login" method="post" name="formLogin">
                        <div id="iniciologin"><h2>INICIAR SESION</h2></div>
                        <br><br>
                        <label style="width:100px;">Usuario</label>
                        <input type="text" id="login1" class="textbox" name="user" placeholder="Ingrese su usuario" required="required">
                        <br><br>
                        <label style="width:100px;">Contraseña</label>
                        <input type="password" id="login2" class="textbox" name="pass" placeholder="Ingrese su contraseña" required="required">
                        <br><br>
                       <button  class="btn-large clickSubmit btn btn-primary" type="submit" id="btnIniciarSesion">Iniciar Sesi&oacute;n</button>
                    	</br>
                    	</br>
                    	<%if(mensaje!=null){ %>
                    	<center>
                    	<div class="alert alert-danger"><strong><%=mensaje %></strong></div>
                    	</center>
                    	<%}else if(mensaje2!=null){ %>
                    	<center>
                    	<div class="alert alert-success"><strong><%=mensaje2 %></strong></div>
                    	</center>
                    	<%} %>
                    
                    </form>
                </div>
            </div>
            </div>
        </div>
		<footer id="contenedor-pie">
            <p id="piepagina">Shop Medical S.A.C 2015 Todos los derechos reservados</p>
        </footer>
</body>
</html>
