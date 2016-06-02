package servlets.android;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.android.BeanUsuarioAndroid;
import utility.Correo;
import utility.ResponseObject;
import utility.Utility;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletRecuperarClave
 */
@WebServlet("/recuperaClave")
public class ServletRecuperarClave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRecuperarClave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String correo= request.getParameter("correo");
		
		I_Usuario userDao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getUsuarioDao();
		BeanUsuarioAndroid user = userDao.recuperarContraseña(correo);
		
		ResponseObject responseObj = new ResponseObject();
	    response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
		
		if(user!=null){
			Utility utils  = new Utility();
			String nuevaClave = utils.generarClave(user.getDni(), user.getNombres(), user.getApellidoPaterno());
			//Enviar Correo con la nueva contraseña
			Correo enviarCorreo = new Correo();
			String mensaje="Estimado: "+user.getNombres()+" "+user.getApellidoPaterno()+" "+user.getApellidoMaterno()+"\n\n"
					+ "Hemos recibido su solicitud de recuperación de contraseña.\n"
					+ "Su nombre de usuario registrado es : "+user.getUsuario()+"\n"
					+ "Su nueva contraseña es la siguiente : "+nuevaClave+"\n\n"+""
							+ "De no haber envíado la solicitud de recuperar contraseña haga caso omiso a este correo\n"
							+ "\nAtentamente.\n\n"
							+ "---------------------------------------------------------\n"
							+ "\nShop Medical - Servicio de Atención al Cliente";
			//Actualizamos la BD antes de mandar el correo
			boolean flag = userDao.actualizarClave(nuevaClave, user.getCodUsuario());
			
			if(flag){
			String resultado = enviarCorreo.enviarCorreo(correo, mensaje);
			
				if(resultado.equals("enviado")){
					responseObj.setSuccess(true);
					responseObj.setMensaje("Se ha envíado un mensaje a su correo electronico con su contraseña");
				}else{
					responseObj.setSuccess(false);
					responseObj.setMensaje("Ocurrió un error al enviar su contraseña");
				}
			}else{
				responseObj.setSuccess(false);
				responseObj.setMensaje("Ocurrio un mensaje al actualizar la clave en la BD");
			}
		}else{
			responseObj.setSuccess(false);
			responseObj.setMensaje("No se encontró una clave para el correo proporcionado");
		}
		response.getWriter().write(new Gson().toJson(responseObj));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
