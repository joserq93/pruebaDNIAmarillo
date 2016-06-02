package servlets.android;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.*;
import beans.android.BeanUsuarioAndroid;
import beans.android.LoginBean;

import com.google.gson.Gson;

import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletLoginAndroid
 */
@WebServlet("/loginAndroid")
public class ServletLoginAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoginAndroid() {
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
		
	  
	    try {
	    	
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
			
		    //Bean Usuario
		    LoginBean login = new Gson().fromJson(sb.toString(),LoginBean.class);
		    
		  //Response
			ResponseObject responseObj = new ResponseObject();
			//Content Type
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
	    	if(login!=null){
	    		//Dao
	    		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Usuario usuariodao = dao.getUsuarioDao();
				//Retorna Objeto del Usuario
				BeanUsuarioAndroid user = usuariodao.login(login.getUser(), login.getPass());
				
					//Validar dependiendo del resultado
				if(user!=null){	
					responseObj.setSuccess(true);
					responseObj.setObject(user);
				}else{
					responseObj.setSuccess(false);
					responseObj.setMensaje("Usuario o Contraseña equivocadas");
				}//Fin validacion metodo BD
				
	    }else{
	    	responseObj.setSuccess(false);
	    	responseObj.setMensaje("Error al recibir datos; datos invalidos");
	    }//FIN else validacion datos ingresados
	    	response.getWriter().write(new Gson().toJson(responseObj));
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	}

}
