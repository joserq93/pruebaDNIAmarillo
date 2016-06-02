package servlets.android;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.android.BeanUsuarioAndroid;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;
import utility.ResponseObject;

/**
 * Servlet implementation class ServletEditarUserAndroid
 */
@WebServlet("/editUserA")
public class ServletEditarUserAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditarUserAndroid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		ResponseObject responseobj = new ResponseObject();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		//Dao
		I_Usuario usuarioDao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getUsuarioDao();
		BeanUsuarioAndroid user = usuarioDao.buscarxid(id);
		//Validar
		if(user!=null){
			responseobj.setSuccess(true);
			responseobj.setObject(user);
		}else{
			responseobj.setSuccess(false);
			responseobj.setMensaje("Error al mostrar los datos del cliente");
		}
		
		response.getWriter().write(new Gson().toJson(responseobj));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	    
	    BeanUsuarioAndroid user = new Gson().fromJson(sb.toString(),BeanUsuarioAndroid.class);
	    ResponseObject responseObj = new ResponseObject();
	    response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
	    if(user!=null){
	    	
	    	DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	    	I_Usuario userdao = dao.getUsuarioDao();
	    	
	    	boolean flag = userdao.editarUser(user);
	    	
	    	if(flag){
	    		responseObj.setSuccess(true);
	    	}else{
	    		responseObj.setSuccess(false);
	    		responseObj.setMensaje("Ocurrio un error al actualizar los datos del usuario");
	    	}
	    	
	    }else{
	    	responseObj.setSuccess(false);
	    	responseObj.setMensaje("Se han enviado datos invalidos o nulos");
	    }
	    
	    response.getWriter().write(new Gson().toJson(responseObj));
	    
	}

}
