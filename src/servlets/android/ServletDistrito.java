package servlets.android;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import utility.ResponseObject;

import beans.android.BeanDistrito;

import dao.interfaces.I_Distrito;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletDistrito
 */
@WebServlet("/distrito")
public class ServletDistrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDistrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		I_Distrito distritoDao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getDistritoDao();
		List<BeanDistrito> lista=distritoDao.listar();
		
		ResponseObject responseObj = new ResponseObject();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(!lista.isEmpty()){
			responseObj.setLista(lista);
			responseObj.setSuccess(true);
		}else{
			responseObj.setSuccess(false);
			responseObj.setMensaje("Error al listar los distritos");
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
