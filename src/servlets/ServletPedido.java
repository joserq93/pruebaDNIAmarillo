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

import beans.DetallePedidoBean;
import beans.PedidoBean;
import beans.UsuarioBean;
import dao.interfaces.I_Comprobante;
import dao.interfaces.I_Pedido;
import daofactory.DAOFactory;

@WebServlet("/Pedido")
public class ServletPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletPedido() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String accion=request.getParameter("accion");
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");	
		if(user!=null){
			if(accion.equals("PedidosPendientes")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
					if(pedidosPendientes.size()>0){
						request.setAttribute("notificacion", "true");
					}else{
						request.setAttribute("notificacion", "false");
					}
					request.setAttribute("pedidosPendientes", pedidosPendientes);
					request.getRequestDispatcher("/Tecnico/pedidosPendientes.jsp").forward(request, response);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}else if(accion.equals("PedidosEnProceso")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<PedidoBean> pedidosProceso=pedidodao.obtenerPedidosProceso(user.getPersona().getCodDistrito());
					Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
					if(pedidosPendientes.size()>0){
						request.setAttribute("notificacion", "true");
					}else{
						request.setAttribute("notificacion", "false");
					}
					request.setAttribute("pedidosProceso", pedidosProceso);
					request.getRequestDispatcher("/Tecnico/pedidosProceso.jsp").forward(request, response);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}else if(accion.equals("HistorialDePedidos")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<PedidoBean> historialpedidos=pedidodao.obtenerHistorialPedidos(user.getPersona().getCodDistrito());
					request.setAttribute("historialpedidos", historialpedidos);
					if(user.getRol().getDescripcion().equals("Tecnico")){
						Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
						if(pedidosPendientes.size()>0){
							request.setAttribute("notificacion", "true");
						}else{
							request.setAttribute("notificacion", "false");
						}
						request.getRequestDispatcher("/Tecnico/historialPedidos.jsp").forward(request, response);
					
					}else if(user.getRol().getDescripcion().equals("Administrador")){
						request.getRequestDispatcher("/Administrador/historialPedidos.jsp").forward(request, response);
					}
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}else if(accion.equals("DetallePedido")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<DetallePedidoBean> detallePedidos=pedidodao.obtenerDetalle(request.getParameter("id"));
					request.setAttribute("detallePedidos", detallePedidos);
					if(user.getRol().getDescripcion().equals("Tecnico")){
						request.getRequestDispatcher("/Tecnico/detallePedido.jsp").forward(request, response);
					}else if(user.getRol().getDescripcion().equals("Administrador")){
						request.getRequestDispatcher("/Administrador/detallePedido.jsp").forward(request, response);
					}
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}			
			}else if(accion.equals("empleados")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<DetallePedidoBean> detallePedidos=pedidodao.obtenerDetalle(request.getParameter("id"));
					request.setAttribute("detallePedidos", detallePedidos);
					request.getRequestDispatcher("/Tecnico/detallePedido.jsp").forward(request, response);
					
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}			
			}
		}else{
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String accion=request.getParameter("accion");
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");	
		if(user!=null){
			if(accion.equals("generarComprobante")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					PedidoBean pedido=new PedidoBean();
					pedido.setCodPedido(request.getParameter("idpedido"));
					pedido.setCodEstadoPedido(2);
					pedidodao.actualizarPedidoPendiente(pedido);
					pedido.setTipoComprobante(request.getParameter("tipocomprobante"));
					Vector<DetallePedidoBean>detallepedido=pedidodao.obtenerDetalle(request.getParameter("idpedido"));
					if(request.getParameter("tipocomprobante").equals("Factura")){
						pedido.setRuc(request.getParameter("ruc"));
					}
					
					I_Comprobante comprobantedao=dao.getComprobanteDAO();
					comprobantedao.generarComprobante(pedido);
					Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
					if(pedidosPendientes.size()>0){
						request.setAttribute("notificacion", "true");
					}else{
						request.setAttribute("notificacion", "false");
					}
					for(int i=0;i<detallepedido.size();i++){
						pedidodao.confirmarPedido(detallepedido.get(i).getCodSucursal(), detallepedido.get(i).getCodMedicamento(), detallepedido.get(i).getCodCantidadxPresentacion(), detallepedido.get(i).getCantidad());
					}
					request.setAttribute("pedidosPendientes", pedidosPendientes);
					request.getRequestDispatcher("/Tecnico/pedidosPendientes.jsp").forward(request, response);
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}			
			}else if(accion.equals("finalizarPedido")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					PedidoBean pedido=new PedidoBean();
					pedido.setCodPedido(request.getParameter("idpedido"));
					pedido.setCodEstadoPedido(Integer.parseInt(request.getParameter("slt_estado")));
					pedidodao.actualizarPedidoProceso(pedido);
					Vector<DetallePedidoBean>detallepedido=pedidodao.obtenerDetalle(request.getParameter("idpedido"));
					if(pedido.getCodEstadoPedido()==4){
						for(int i=0;i<detallepedido.size();i++){
							pedidodao.actualizarStock2(detallepedido.get(i).getCodSucursal(), detallepedido.get(i).getCodMedicamento(), detallepedido.get(i).getCodCantidadxPresentacion());
						}
					}else{
						for(int i=0;i<detallepedido.size();i++){
							pedidodao.reingresarStock1(detallepedido.get(i).getCodSucursal(), detallepedido.get(i).getCodMedicamento(), detallepedido.get(i).getCodCantidadxPresentacion(), detallepedido.get(i).getCantidad());
						}
					}
					Vector<PedidoBean> pedidosProceso=pedidodao.obtenerPedidosProceso(user.getPersona().getCodDistrito());
					Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
					if(pedidosPendientes.size()>0){
						request.setAttribute("notificacion", "true");
					}else{
						request.setAttribute("notificacion", "false");
					}
					request.setAttribute("pedidosProceso", pedidosProceso);
					request.getRequestDispatcher("/Tecnico/pedidosProceso.jsp").forward(request, response);
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}
			}else if(accion.equals("buscarPedido")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					PedidoBean pedido=pedidodao.buscarPedido(request.getParameter("txt_busqueda"),user.getPersona().getCodDistrito());
					
					if(pedido!=null){
						request.setAttribute("titulo", "Pedido Encontrado");
					}else{
						request.setAttribute("titulo", "No se encontro el pedido");
					}
					request.setAttribute("pedido", pedido);
					if(user.getRol().getDescripcion().equals("Tecnico")){
						Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
						if(pedidosPendientes.size()>0){
							request.setAttribute("notificacion", "true");
						}else{
							request.setAttribute("notificacion", "false");
						}
						request.getRequestDispatcher("/Tecnico/buscarPedido.jsp").forward(request, response);
					}
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}
			}else if(accion.equals("corregirEstado")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					PedidoBean pedido=new PedidoBean();
					pedido.setCodPedido(request.getParameter("idpedido"));
					pedido.setCodEstadoPedido(2);
					Vector<DetallePedidoBean>detallepedido=pedidodao.obtenerDetalle(request.getParameter("idpedido"));
					if(detallepedido.get(0).getPedido().getCodEstadoPedido()==4){
						for(int i=0;i<detallepedido.size();i++){
							pedidodao.reingresarStock2(detallepedido.get(i).getCodSucursal(), detallepedido.get(i).getCodMedicamento(), detallepedido.get(i).getCodCantidadxPresentacion(), detallepedido.get(i).getCantidad());
						}
					}
					pedidodao.actualizarPedidoPendiente(pedido);
					Vector<PedidoBean> historialpedidos=pedidodao.obtenerHistorialPedidos(user.getPersona().getCodDistrito());
					request.setAttribute("historialpedidos", historialpedidos);
					request.getRequestDispatcher("/Administrador/historialPedidos.jsp").forward(request, response);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}else if(accion.equals("HistorialDePedidosC")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<PedidoBean> historialpedidos=pedidodao.obtenerHistorialPedidosC(request.getParameter("fechaIn"),request.getParameter("fechaFi"),user.getPersona().getCodDistrito());
					request.setAttribute("fechaIn", request.getParameter("fechaIn"));
					request.setAttribute("fechaFi", request.getParameter("fechaFi"));
					request.setAttribute("historialpedidos", historialpedidos);
					if(user.getRol().getDescripcion().equals("Tecnico")){
						Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
						if(pedidosPendientes.size()>0){
							request.setAttribute("notificacion", "true");
						}else{
							request.setAttribute("notificacion", "false");
						}
						request.getRequestDispatcher("/Tecnico/historialPedidos.jsp").forward(request, response);
					
					}else if(user.getRol().getDescripcion().equals("Administrador")){
						request.getRequestDispatcher("/Administrador/historialPedidos.jsp").forward(request, response);
					}
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}
		}else{
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
