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
import daofactory.DAOFactory;
import beans.android.BeanMedSucursalAndroid;

/**
 * Servlet implementation class ServletBuscarMedicamentoxNombre
 */
@WebServlet("/searchMedxNom")
public class ServletBuscarMedicamentoxNombre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarMedicamentoxNombre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String medicamento = request.getParameter("medicamento");
		int codSucursal = Integer.parseInt(request.getParameter("codSucursal"));

		ResponseObject responseObj = new ResponseObject();
	    response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	
		if(medicamento!=null){
			List<BeanMedSucursalAndroid> lista = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getMedicamentoDao().buscarMedicamentoxSucursal(medicamento, codSucursal);
			if(!lista.isEmpty()){
				responseObj.setLista(lista);
				responseObj.setSuccess(true);
			}else{
				responseObj.setSuccess(false);
				responseObj.setMensaje("No se hallo ningún medicamento");
			}
		}else{
			responseObj.setSuccess(false);
			responseObj.setMensaje("Ha ingresado datos incorrectos para medicamento");
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
