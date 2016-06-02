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
import beans.android.BeanSintomasComunes;
import dao.interfaces.I_SintomasComunes;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletListarSintomas
 */
@WebServlet("/listarSintomas")
public class ServletListarSintomas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarSintomas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		I_SintomasComunes sintomasDao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getSintomasComunesDao();
		List<BeanSintomasComunes> lista = sintomasDao.listarSintomas();
		
		ResponseObject responseObj = new ResponseObject();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(!lista.isEmpty()){
			responseObj.setLista(lista);
			responseObj.setSuccess(true);
		}else{
			responseObj.setSuccess(false);
			responseObj.setMensaje("Error al listar todos los sintomas");
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
