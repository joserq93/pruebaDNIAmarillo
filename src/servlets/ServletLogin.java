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

import beans.DistritoBean;
import beans.PedidoBean;
import beans.RolBean;
import beans.UsuarioBean;
import dao.interfaces.I_Distrito;
import dao.interfaces.I_Pedido;
import dao.interfaces.I_Rol;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;


@WebServlet("/Login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try{
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");	
		if(user!=null){
			if(user.getRol().getDescripcion().equals("Tecnico")){
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Pedido userdao=dao.getPedidoDAO();
				Vector<PedidoBean> pedidosPendientes=userdao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
				if(pedidosPendientes.size()>0){
					request.setAttribute("notificacion", "true");
				}else{
					request.setAttribute("notificacion", "false");
				}
				request.setAttribute("pedidosPendientes", pedidosPendientes);
				request.getRequestDispatcher("/Tecnico/pedidosPendientes.jsp").forward(request, response);
			
			}else if(user.getRol().getDescripcion().equals("Administrador")){
				
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Usuario usuarioget=dao.getUsuarioDao();
				Vector<UsuarioBean> listausuarios=usuarioget.listarUsuarios(user.getPersona().getCodDistrito());
				request.setAttribute("listausuarios", listausuarios);
				request.getRequestDispatcher("/Administrador/empleados.jsp").forward(request, response);
	
			}
		
		}else{
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		
		}
		}catch (Exception e) {
			out.print(e.getMessage());
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try{
			
			String uss=request.getParameter("user").replaceAll("'or'1'='1", "");
			String pass= request.getParameter("pass").replaceAll("'or'1'='1", "");
			if(uss.equals("")||pass.equals("")){
				request.setAttribute("mensaje", "Ingrese usuario y contraseña");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else{
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Usuario usuariodao = dao.getUsuarioDao();
				I_Pedido pedidodao = dao.getPedidoDAO();
				UsuarioBean ses=usuariodao.validar(uss,pass);
				if(ses!=null){
					
					HttpSession session= request.getSession();
					session.setMaxInactiveInterval(-1);
					session.setAttribute("usuario", ses);
					if(ses.getRol().getDescripcion().equals("Tecnico")){
						
						Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(ses.getPersona().getCodDistrito());
						if(pedidosPendientes.size()>0){
							request.setAttribute("notificacion", "true");
						}else{
							request.setAttribute("notificacion", "false");
						}
						request.setAttribute("pedidosPendientes", pedidosPendientes);
						request.getRequestDispatcher("/Tecnico/pedidosPendientes.jsp").forward(request, response);
					}else if(ses.getRol().getDescripcion().equals("Administrador")){
						I_Usuario usuarioget=dao.getUsuarioDao();
						Vector<UsuarioBean> listausuarios=usuarioget.listarUsuarios(ses.getPersona().getCodDistrito());
						I_Distrito distritodao=dao.getDistritoDao();
						I_Rol roldao=dao.getRolDao();
						Vector<DistritoBean> distrito=distritodao.distritos();
						Vector<RolBean> cargo=roldao.roles();
						request.setAttribute("cargo", cargo);
						request.setAttribute("distrito", distrito);
						request.setAttribute("listausuarios", listausuarios);
						request.getRequestDispatcher("/Administrador/empleados.jsp").forward(request, response);
					}
				
				}else{
						
					request.setAttribute("mensaje", "Usuario y/o Password Incorrectos.");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				
				}
			}
		}catch (Exception e) {
			out.print(e.getMessage());
		}	
	}
}
