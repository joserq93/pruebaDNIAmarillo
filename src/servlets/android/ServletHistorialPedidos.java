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
import beans.android.BeanPedidoAndroid;
import dao.interfaces.I_Pedido;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletHistorialPedidos
 */
@WebServlet("/historialPedA")
public class ServletHistorialPedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletHistorialPedidos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int codUsuario = Integer.parseInt(request.getParameter("codUsuario"));
	
		ResponseObject responseObj = new ResponseObject();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(codUsuario>0){
			
			I_Pedido pedidoDao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getPedidoDAO();
			List<BeanPedidoAndroid> lista = pedidoDao.historialPedidos(codUsuario);
			
			if(!lista.isEmpty()){
				responseObj.setSuccess(true);
				responseObj.setLista(lista);
			}else{
				responseObj.setSuccess(false);
				responseObj.setMensaje("Usted no posee pedidos");
			}
			
		}else{
			responseObj.setSuccess(false);
			responseObj.setMensaje("Error al procesar su historial de pedidos");
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
