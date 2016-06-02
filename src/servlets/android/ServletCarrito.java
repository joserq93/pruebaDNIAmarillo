package servlets.android;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.ResponseObject;
import utility.Utility;

import com.google.gson.Gson;

import dao.interfaces.I_Pedido;
import daofactory.DAOFactory;
import beans.android.BeanCarritoAndroid;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/carrito")
public class ServletCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCarrito() {
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
	    
	    
	    BeanCarritoAndroid carrito = new Gson().fromJson(sb.toString(),BeanCarritoAndroid.class);
	    
	    ResponseObject responseObj = new ResponseObject();
	    response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	
    	if(carrito!=null){
    		
    		Utility utils = new Utility();
    		String codPedido = utils.generarCodigoPedido();
    		System.out.println("codigo pedido generado: "+codPedido);
    		I_Pedido pedidoDao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getPedidoDAO();

    		if(!codPedido.equals("Se sobrepaso el limite de Pedido por mes")){
    			boolean flag = pedidoDao.ingresarPedidoAndroid(codPedido,carrito);
    		
    			if(flag){
    				responseObj.setSuccess(true);
    			}else{
    				responseObj.setSuccess(false);
	    			responseObj.setMensaje("No se pudo agregar el carrito");
    			}
    		}else{
    			responseObj.setSuccess(false);
    			responseObj.setMensaje("No se pudo generar el codigo del pedido");
    		}
    	}else{
    		responseObj.setSuccess(false);
    		responseObj.setMensaje("Ocurrio un error al recuperar los datos");
    	}
    	
    	 response.getWriter().write(new Gson().toJson(responseObj));
 	    
	}

}
