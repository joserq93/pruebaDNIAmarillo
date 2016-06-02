package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ComprobanteBean;
import beans.DetallePedidoBean;
import beans.SunatBean;
import beans.UsuarioBean;
import dao.interfaces.I_Comprobante;
import dao.interfaces.I_Pedido;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletComprobante
 */
@WebServlet("/Comprobante")
public class ServletComprobante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletComprobante() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");	
		if(user!=null && (user.getRol().getDescripcion().equals("Tecnico")||user.getRol().getDescripcion().equals("Administrador"))){
			try{
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Pedido pedidodao=dao.getPedidoDAO();
				I_Comprobante comprobantedao=dao.getComprobanteDAO();
				Vector<DetallePedidoBean> detallePedidos=pedidodao.obtenerDetalle(request.getParameter("id"));
				ComprobanteBean comprobante=comprobantedao.obtenerComprobante(request.getParameter("id"), detallePedidos.get(0).getPedido().getTipoComprobante());
				double sigv=Math.round((detallePedidos.get(0).getPedido().getMontoTotal()*0.82)*100)/100.00;
				double igv=Math.round((detallePedidos.get(0).getPedido().getMontoTotal()*0.18)*100)/100.00;
				double vuelto=Math.round((detallePedidos.get(0).getPedido().getMontoCancelar()-detallePedidos.get(0).getPedido().getMontoTotal())*100)/100.00;
				request.setAttribute("sigv", sigv);
				request.setAttribute("vuelto", vuelto);
				request.setAttribute("igv", igv);
				request.setAttribute("detallePedidos", detallePedidos);
				request.setAttribute("comprobante", comprobante);
				String com=""+detallePedidos.get(0).getCodSucursal();
				String cod=""+comprobante.getNroComprobante();
				for(int i=cod.length();i<8;i++){
					cod="0"+cod;
				}
				for(int i=com.length();i<3;i++){
					com="0"+com;
				}
				request.setAttribute("cod", cod);
				request.setAttribute("com", com);
				if(detallePedidos.get(0).getPedido().getTipoComprobante().equals("Boleta")){
					if(user.getRol().getDescripcion().equals("Tecnico")){
						request.getRequestDispatcher("/Tecnico/boleta.jsp").forward(request, response);
					}else{
						request.getRequestDispatcher("/Administrador/boleta.jsp").forward(request, response);
					}
				}else{
					SunatBean sunat=comprobantedao.obtenerDatosRuc(detallePedidos.get(0).getPedido().getRuc());
					request.setAttribute("sunat", sunat);
					if(user.getRol().getDescripcion().equals("Tecnico")){
						request.getRequestDispatcher("/Tecnico/factura.jsp").forward(request, response);
					}else{
						request.getRequestDispatcher("/Administrador/factura.jsp").forward(request, response);
					}
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				out.print(e.getMessage());
			}
		}else{
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
