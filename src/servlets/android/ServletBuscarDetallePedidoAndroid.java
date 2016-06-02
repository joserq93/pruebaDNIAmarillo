package servlets.android;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.android.BeanDetallePedidoAndroid;
import dao.interfaces.I_Pedido;
import daofactory.DAOFactory;
import utility.ResponseObject;

/**
 * Servlet implementation class ServletBuscarDetallePedidoAndroid
 */
@WebServlet("/buscarDetPedA")
public class ServletBuscarDetallePedidoAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarDetallePedidoAndroid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int codPedido = Integer.parseInt(request.getParameter("codPedido"));
		
		ResponseObject responseObj = new ResponseObject();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(codPedido>0){
			
			I_Pedido pedidoDao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getPedidoDAO();
			List<BeanDetallePedidoAndroid>lista = pedidoDao.buscarDetallePedido(codPedido);
			
			if(!lista.isEmpty()){
				responseObj.setSuccess(true);
				responseObj.setLista(lista);
			}else{
				responseObj.setSuccess(false);
				responseObj.setMensaje("Error el pedido no contiene ningun detalle o no existe");
			}
			
		}else{
			responseObj.setSuccess(false);
			responseObj.setMensaje("Ocurrió un error al recuperar el detalle de su pedido");
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
