package servlets.android;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.ResponseObject;

import com.google.gson.Gson;

import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;
import beans.android.BeanUsuarioAndroid;

/**
 * Servlet implementation class ServletValidarDNI
 */
@WebServlet("/validaDNI")
public class ServletValidarDNI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletValidarDNI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dni = request.getParameter("dni");
		ResponseObject responseobj = new ResponseObject();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(dni!=null&&dni.length()==8){
			I_Usuario userDao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getUsuarioDao();
			BeanUsuarioAndroid userAndroid = userDao.validarDniReniec(dni);
			if(userAndroid!=null){
				responseobj.setSuccess(true);
				responseobj.setObject(userAndroid);
			}else{
				responseobj.setSuccess(false);
				responseobj.setMensaje("Su DNI no se encuentra registrado en la RENIEC");
			}
		}else{
			responseobj.setSuccess(false);
			responseobj.setMensaje("Valor equivocado para DNI");
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
