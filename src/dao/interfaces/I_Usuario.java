package dao.interfaces;

import java.util.Vector;

import beans.PersonaBean;
import beans.UsuarioBean;
import beans.android.BeanSunat;
import beans.android.BeanUsuarioAndroid;

public interface I_Usuario {

	public boolean registrarUsuario(BeanUsuarioAndroid user);
	public Vector<PersonaBean> backlist(int codDistrito) throws Exception;
	
	/**Android:**/
	public BeanUsuarioAndroid buscarxid(int id);
	public UsuarioBean validar(String usuario, String clave) throws Exception;
	public Vector<UsuarioBean> listarUsuarios(int codDistrito) throws Exception;
	public boolean modificarUsuario(UsuarioBean empleado) throws Exception;
	public boolean agregarUsuario(UsuarioBean empleado) throws Exception; 
	public boolean eliminarUsuario(int cod) throws Exception;
	public UsuarioBean obtenerEmpleado(int codUsuario) throws Exception;
	public BeanUsuarioAndroid validarDniReniec(String dni);
	public BeanSunat validarRUCSunat(String ruc);
	public boolean validarDniRegistrado(String dni);
	public BeanUsuarioAndroid login(String user, String pass);
	public boolean editarUser(BeanUsuarioAndroid user);
	public BeanUsuarioAndroid recuperarContraseña(String correo);
	public boolean actualizarClave(String clave, int id);
}
