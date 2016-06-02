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

import com.google.gson.Gson;

import utility.ResponseObject;
import beans.DistritoBean;
import beans.PersonaBean;
import beans.RolBean;
import beans.UsuarioBean;
import dao.interfaces.I_Distrito;
import dao.interfaces.I_Rol;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/Usuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuario() {
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
		if(user!=null){
			if(accion.equals("empleados")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Usuario usuarioget=dao.getUsuarioDao();
					Vector<UsuarioBean> listausuarios=usuarioget.listarUsuarios(user.getPersona().getCodDistrito());
					request.setAttribute("listausuarios", listausuarios);
					request.getRequestDispatcher("/Administrador/empleados.jsp").forward(request, response);
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}			
			}else if(accion.equals("DetalleEmpleado")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Usuario usuarioget=dao.getUsuarioDao();
					I_Distrito distritodao=dao.getDistritoDao();
					I_Rol roldao=dao.getRolDao();
					UsuarioBean empleado=usuarioget.obtenerEmpleado(Integer.parseInt(request.getParameter("id")));
					Vector<DistritoBean> distrito=distritodao.distritos();
					Vector<RolBean> cargo=roldao.roles();
					request.setAttribute("cargo", cargo);
					request.setAttribute("distrito", distrito);
					request.setAttribute("empleado", empleado);
					request.getRequestDispatcher("/Administrador/detalleEmpleado.jsp").forward(request, response);
					
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}			
			}else if(accion.equals("RegistrarEmpleado")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Distrito distritodao=dao.getDistritoDao();
					I_Rol roldao=dao.getRolDao();
					Vector<DistritoBean> distrito=distritodao.distritos();
					Vector<RolBean> cargo=roldao.roles();
					request.setAttribute("cargo", cargo);
					request.setAttribute("distrito", distrito);
					request.getRequestDispatcher("/Administrador/registrarEmpleado.jsp").forward(request, response);
					
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}			
			}else if(accion.equals("EliminarEmpleado")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Usuario usuarioget=dao.getUsuarioDao();
					usuarioget.eliminarUsuario(Integer.parseInt(request.getParameter("id")));
					Vector<UsuarioBean> listausuarios=usuarioget.listarUsuarios(user.getPersona().getCodDistrito());
					request.setAttribute("listausuarios", listausuarios);
					request.getRequestDispatcher("/Administrador/empleados.jsp").forward(request, response);
				}catch (Exception e) {
					// TODO: handle exception
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
			if(accion.equals("registrarEmpleado")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Usuario usuariodao=dao.getUsuarioDao();
					
					UsuarioBean usuarioper=new UsuarioBean();
					usuarioper.setUsuario(request.getParameter("usuario"));
					usuarioper.setClave(request.getParameter("clave"));
					usuarioper.setCodRol(Integer.parseInt(request.getParameter("cargo")));
					
					PersonaBean empleado = new PersonaBean();
					empleado.setNombres(request.getParameter("nombres"));
					empleado.setApellidoPaterno(request.getParameter("apellidoPaterno"));
					empleado.setApellidoMaterno(request.getParameter("apellidoMaterno"));
					empleado.setCodDistrito(Integer.parseInt(request.getParameter("distrito")));
					empleado.setCorreo(request.getParameter("correo"));
					empleado.setCelular(Integer.parseInt(request.getParameter("celular")));
					empleado.setDireccion(request.getParameter("direccion"));
					empleado.setDni(request.getParameter("dni"));
					empleado.setFechaNacimiento(request.getParameter("fecha"));
					
					usuarioper.setPersona(empleado);
					
					boolean validar=usuariodao.agregarUsuario(usuarioper);
					System.out.println(validar);
					if(validar!=false){
						request.setAttribute("mensaje4", "Se registro Satisfactoriamente");
					}else{
						request.setAttribute("mensaje3", "No se encuentra registrado en la RENIEC");
					}
					Vector<UsuarioBean> listausuarios=usuariodao.listarUsuarios(user.getPersona().getCodDistrito());
					request.setAttribute("listausuarios", listausuarios);
					request.getRequestDispatcher("/Administrador/empleados.jsp").forward(request, response);
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}			
			}else if(accion.equals("modificarUsuario")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Usuario usuariodao=dao.getUsuarioDao();
					
					UsuarioBean usuarioper=new UsuarioBean();
					usuarioper.setCodUsuario(Integer.parseInt(request.getParameter("id")));
					usuarioper.setUsuario(request.getParameter("usuario"));
					usuarioper.setClave(request.getParameter("clave"));
					usuarioper.setCodRol(Integer.parseInt(request.getParameter("cargo")));
					
					PersonaBean empleado = new PersonaBean();
					empleado.setNombres(request.getParameter("nombres"));
					empleado.setApellidoPaterno(request.getParameter("apellidoPaterno"));
					empleado.setApellidoMaterno(request.getParameter("apellidoMaterno"));
					empleado.setCodDistrito(Integer.parseInt(request.getParameter("distrito")));
					empleado.setCorreo(request.getParameter("correo"));
					empleado.setCelular(Integer.parseInt(request.getParameter("celular")));
					empleado.setDireccion(request.getParameter("direccion"));
					empleado.setDni(request.getParameter("dni"));
					empleado.setFechaNacimiento(request.getParameter("fecha"));
					usuarioper.setPersona(empleado);
					
					boolean validar=usuariodao.modificarUsuario(usuarioper);
					System.out.println(validar);
					if(validar!=false){
						request.setAttribute("mensaje5", "Se modifico Satisfactoriamente");
					}else{
						request.setAttribute("mensaje6", "No se pudo modificar");
					}
					Vector<UsuarioBean> listausuarios=usuariodao.listarUsuarios(user.getPersona().getCodDistrito());
					request.setAttribute("listausuarios", listausuarios);
					request.getRequestDispatcher("/Administrador/empleados.jsp").forward(request, response);
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}			
			}else if(accion.equals("verificarUsuarioExistente")){
				try{
					response.setContentType( "text/html; charset=iso-8859-1" );
					PrintWriter ou = response.getWriter();
					
					
					String dni=request.getParameter("dni");
					boolean flag;
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Usuario usuariodao=dao.getUsuarioDao();
					flag=usuariodao.validarDniRegistrado(dni);
					ResponseObject responseobj=null;
					if(flag==false){
						responseobj=new ResponseObject();
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						responseobj.setSuccess(true);
						responseobj.setObject(flag);
						//ou.println("<div class='alert alert-danger' role='alert'>Ya existe una persona registrado con ese dni</div><input type='hidden' id='dnicomp' value='existente'>");
					}else{
						responseobj=new ResponseObject();
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						responseobj.setSuccess(true);
						responseobj.setObject(flag);
						//ou.println("<input type='hidden' id='dnicomp' value='disponible'>");
					}
					response.getWriter().write(new Gson().toJson(responseobj));
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}			
			}
			
		}else{
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		
		}
	}

}
