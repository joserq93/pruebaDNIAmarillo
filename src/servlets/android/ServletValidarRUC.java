package servlets.android;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.android.BeanSunat;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;
import utility.ResponseObject;

/**
 * Servlet implementation class ServletValidarRuc
 */
@WebServlet("/validaRUC")
public class ServletValidarRUC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletValidarRUC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String ruc = request.getParameter("ruc");
		ResponseObject responseobj = new ResponseObject();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(ruc!=null&&ruc.length()==11){
			I_Usuario userDao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getUsuarioDao();
			BeanSunat respuesta = userDao.validarRUCSunat(ruc);
			
			if(respuesta!=null){
				responseobj.setObject(respuesta);
				responseobj.setSuccess(true);
			}else{
				responseobj.setSuccess(false);
				responseobj.setMensaje("No se encontró el ruc");
			}
			
		}else{
			responseobj.setSuccess(false);
			responseobj.setMensaje("Error al capturar los datos del Ruc/datos invalidos");
		}
		response.getWriter().write(new Gson().toJson(responseobj));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
