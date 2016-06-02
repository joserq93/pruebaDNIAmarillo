package servlets.android;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.ResponseObject;


import beans.android.BeanUsuarioAndroid;

import com.google.gson.Gson;

import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletRegistrarUserAndroid
 */
@WebServlet("/regUserA")
public class ServletRegistrarUserAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistrarUserAndroid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//StringBuilder		
		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    try {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line).append('\n');
	        }
	    } finally {
	        reader.close();
	    }
		
		//Bean
	    
	    BeanUsuarioAndroid user = new Gson().fromJson(sb.toString(),BeanUsuarioAndroid.class);
	    
	    ResponseObject responseObj = new ResponseObject();
	    response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
	    
	    if(user!=null){
	    	//Dao
	    	DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	    	I_Usuario userdao = dao.getUsuarioDao();
	    	//Validar DNI existente
	    	boolean flag = userdao.validarDniRegistrado(user.getDni());
	    	//Si flag da true, signfica que todo es OK y no hay usuario registrado con el DNI ingresado
	    	if(flag){
	    	//Respuesta del dao al registrar
	    		boolean respuesta = userdao.registrarUsuario(user);
	    		//Objeto respuesta del registro
	    		if(respuesta){
	    			responseObj.setSuccess(respuesta);
	    		}else{
	    			responseObj.setSuccess(false);
	    			responseObj.setMensaje("Ocurrio un error al registrar");
	    		}
	    	}else{
	    		responseObj.setSuccess(false);
	    		responseObj.setMensaje("Ya se encuentra un usuario registrado con el mismo DNI");
	    	}
	    }else{
	    	responseObj.setSuccess(false);
	    	responseObj.setMensaje("Datos invalidos");
	
	    }
	    response.getWriter().write(new Gson().toJson(responseObj));
	}

}
