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

import beans.IngresosDeliveryBean;
import beans.PersonaBean;
import beans.PresentacionxMedicamentoBean;
import beans.UsuarioBean;
import dao.interfaces.I_Pedido;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletReportes
 */
@WebServlet(name = "ServletReporte", urlPatterns = { "/Reporte" })
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String accion=request.getParameter("accion");
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");	
		if(user!=null && user.getRol().getDescripcion().equals("Administrador")){
			if(accion.equals("BlackList")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Usuario personasdao=dao.getUsuarioDao();
					Vector<PersonaBean> blacklist=personasdao.backlist(user.getPersona().getCodDistrito());
					request.setAttribute("blacklist", blacklist);
					request.getRequestDispatcher("/Administrador/blackList.jsp").forward(request, response);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}else if(accion.equals("RankingMedicamentos")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<PresentacionxMedicamentoBean> rankmed=pedidodao.rankingMedicamento(user.getPersona().getCodDistrito());
					
					request.setAttribute("rankmed", rankmed);
					request.getRequestDispatcher("/Administrador/rankingMedicamentos.jsp").forward(request, response);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}else if(accion.equals("IngresosDelivery")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido ingresodao=dao.getPedidoDAO();
					Vector<IngresosDeliveryBean> ingresos=ingresodao.obtenerIngresos(user.getPersona().getCodDistrito());
					request.setAttribute("ingresos", ingresos);
					request.getRequestDispatcher("/Administrador/ingresosDelivery.jsp").forward(request, response);
				}catch(Exception e){
					out.print(e.getMessage());
				}
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
		PrintWriter out=response.getWriter();
		String accion=request.getParameter("accion");
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");	
		if(user!=null){
			if(accion.equals("RankingMedicamentosC")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<PresentacionxMedicamentoBean> rankmed=pedidodao.rankingMedicamentoC(request.getParameter("fechaIn"),request.getParameter("fechaFi"),user.getPersona().getCodDistrito());
					request.setAttribute("fechaIn", request.getParameter("fechaIn"));
					request.setAttribute("fechaFi", request.getParameter("fechaFi"));
					request.setAttribute("rankmed", rankmed);
					request.getRequestDispatcher("/Administrador/rankingMedicamentos.jsp").forward(request, response);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}else if(accion.equals("IngresosDeliveryC")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido ingresodao=dao.getPedidoDAO();
					Vector<IngresosDeliveryBean> ingresos=ingresodao.obtenerIngresosC(request.getParameter("anio"),user.getPersona().getCodDistrito());
					request.setAttribute("anio", request.getParameter("anio"));
					request.setAttribute("ingresos", ingresos);
					request.getRequestDispatcher("/Administrador/ingresosDelivery.jsp").forward(request, response);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}		
		}else{
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		
		}
	}

}
