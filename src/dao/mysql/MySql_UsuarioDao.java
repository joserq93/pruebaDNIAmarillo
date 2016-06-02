package dao.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import beans.PersonaBean;
import beans.RolBean;
import beans.UsuarioBean;
import beans.android.BeanSunat;
import beans.android.BeanUsuarioAndroid;
import dao.interfaces.I_Usuario;
import daofactory.MySQLDaoFactory;

public class MySql_UsuarioDao extends MySQLDaoFactory implements I_Usuario {

	@Override
	public boolean registrarUsuario(BeanUsuarioAndroid user)  {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			String sql="INSERT into t_persona " +
					"(nombres,apellidoPaterno,apellidoMaterno,dni,fechaNacimiento,celular,direccion,correo,codDistrito,latlong)" +
					"VALUES('"+user.getNombres()+"','"+user.getApellidoPaterno()+"','"+user.getApellidoMaterno()+"'" +
							",'"+user.getDni()+"','"+user.getFechaNacimiento()+"','"+user.getCelular()+"'" +
							",'"+user.getDireccion()+"','"+user.getCorreo()+"','"+user.getCodDistrito()+"'" +
							",'"+user.getLatlong()+"')";
			Statement stmt=con.createStatement();
			//Id en persona autogenerado -> Validar el id
			int id=stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			if(id!=0){
				//Captar el id
				ResultSet rs = null;
				rs = stmt.getGeneratedKeys();
				rs.next();
				id = rs.getInt(1);
				System.out.println("ID generado para persona: "+id);
				//Fin captar
				//Query para usuario
				String query = "INSERT into t_usuario" +
							"(codUsuario,usuario,clave,codRol)" +
							"VALUES('"+id+"','"+user.getUsuario()+"','"+user.getClave()+"','"+user.getCodRol()+"')";
				int filas = stmt.executeUpdate(query);
					if(filas>0){
						flag=true;
					}else{
						flag=false;
					}
			}else{
			 flag = false;
			}
			
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Error al ingresar usuario/Android: "+e);
		}
		
		return flag;
	}

	//Validacion de login
	@Override
	public UsuarioBean validar(String usuario, String clave) throws Exception {
		// TODO Auto-generated method stub
		UsuarioBean objusuario=null;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			
			Statement stmt=con.createStatement();
			String query="select * from t_usuario a INNER JOIN t_rol b ON a.codRol=b.codRol INNER JOIN t_persona c ON a.codUsuario=c.codPersona where a.usuario='"+usuario+"' and a.clave='"+clave+"' and a.estado='1'";
			ResultSet rs= stmt.executeQuery(query);
			if( rs.next() ){
				objusuario= new UsuarioBean();
				objusuario.setCodUsuario(rs.getInt("a.codUsuario"));
				objusuario.setUsuario(rs.getString("a.usuario"));
				objusuario.setClave(rs.getString("a.clave"));
				objusuario.setCodRol(rs.getInt("a.codRol"));
				
				RolBean objrol=new RolBean();
				objrol.setCodRol(rs.getInt("b.codRol"));
				objrol.setDescripcion(rs.getString("b.descripcion"));
				objusuario.setRol(objrol);
				
				PersonaBean objpersona=new PersonaBean();
				objpersona.setCodPersona(rs.getInt("c.codPersona"));
				objpersona.setNombres(rs.getString("c.nombres"));
				objpersona.setApellidoPaterno(rs.getString("c.apellidoPaterno"));
				objpersona.setApellidoMaterno(rs.getString("c.apellidoMaterno"));
				objpersona.setDni(rs.getString("c.dni"));
				objpersona.setFechaNacimiento(rs.getString("c.fechaNacimiento"));
				objpersona.setCelular(rs.getInt("c.celular"));
				objpersona.setDireccion(rs.getString("c.direccion"));
				objpersona.setCorreo(rs.getString("c.correo"));
				objpersona.setCodDistrito(rs.getInt("c.codDistrito"));
				objpersona.setReferencia(rs.getString("c.referencia"));
				objpersona.setLatlong(rs.getString("c.latlong"));
				objusuario.setPersona(objpersona);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print(e.getMessage());
		}
		return objusuario;
	}

	@Override
	public BeanUsuarioAndroid validarDniReniec(String dni) {
		// TODO Auto-generated method stub
		BeanUsuarioAndroid user = null;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
		
			String sql="SELECT * FROM t_reniec a INNER JOIN t_distrito b ON a.distrito=b.nombre WHERE a.dni='"+dni+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				
				
				user=new BeanUsuarioAndroid();
				user.setDni(rs.getString("dni"));
				user.setNombres(rs.getString("nombres"));
				user.setApellidoPaterno(rs.getString("apellidoPaterno"));
				user.setApellidoMaterno(rs.getString("apellidoMaterno"));
				user.setFechaNacimiento(rs.getString("fechaNacimiento"));
				user.setDireccion(rs.getString("direccion"));
				user.setCodDistrito(rs.getInt("codDistrito"));
				user.setDistrito(rs.getString("nombre"));
				
			}
			con.close();
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO: handle exception
			System.out.println("Error at validarDni: "+e+" Message: "+e.getMessage());
		}
		
		return user;
	}

	@Override
	public boolean validarDniRegistrado(String dni) {
		// TODO Auto-generated method stub
		/*Devuelve true si es que no existe alguien con el mismo DNI en la BD, caso contrario devuelve false*/
		boolean flag = true;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
		
			String sql="SELECT * FROM t_persona WHERE dni = '"+dni+"' ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					flag=false;
				}

			con.close();
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO: handle exception
		}

		return flag;
	}

	@Override
	public BeanUsuarioAndroid login(String user, String pass) {
		// TODO Auto-generated method stub
		BeanUsuarioAndroid userAndroid=null;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			
			Statement stmt=con.createStatement();
			String query="select * from t_usuario a INNER JOIN t_rol b ON a.codRol=b.codRol INNER JOIN t_persona c ON a.codUsuario=c.codPersona where a.usuario='"+user+"' and a.clave='"+pass+"' and a.estado='1'";
			ResultSet rs= stmt.executeQuery(query);
			if( rs.next() ){
				
				userAndroid = new BeanUsuarioAndroid();
				
				userAndroid.setCodPersona(rs.getInt("c.codPersona"));
				userAndroid.setNombres(rs.getString("c.nombres"));
				userAndroid.setApellidoPaterno(rs.getString("c.apellidoPaterno"));
				userAndroid.setApellidoMaterno(rs.getString("c.apellidoMaterno"));
				userAndroid.setDni(rs.getString("c.dni"));
				userAndroid.setFechaNacimiento(rs.getString("c.fechaNacimiento"));
				
				int celular = rs.getInt("c.celular");
				userAndroid.setCelular(""+celular);
				
				userAndroid.setDireccion(rs.getString("c.direccion"));
				userAndroid.setCorreo(rs.getString("c.correo"));
				userAndroid.setCodDistrito(rs.getInt("c.codDistrito"));
				userAndroid.setLatlong(rs.getString("c.latlong"));
				userAndroid.setCodRol(rs.getInt("b.codRol"));
				userAndroid.setCodUsuario(rs.getInt("a.codUsuario"));
				userAndroid.setEstado(rs.getInt("a.estado"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print(e.getMessage());
		}
		return userAndroid;
	}

	@Override
	public BeanUsuarioAndroid buscarxid(int id) {
		// TODO Auto-generated method stub
		BeanUsuarioAndroid userAndroid=null;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
		
			Statement stmt=con.createStatement();
			String sql = "select * from t_usuario a INNER JOIN t_rol b ON a.codRol=b.codRol INNER JOIN t_persona c ON a.codUsuario=c.codPersona INNER JOIN t_distrito d ON c.codDistrito = d.codDistrito where a.codUsuario='"+id+"' and a.estado='1'";
			ResultSet rs= stmt.executeQuery(sql);
			
			if(rs.next()){
				userAndroid = new BeanUsuarioAndroid();
				
				/*c : para Persona
				 * b: para Rol
				 * a: para Usuaro
				 * d: para Distrito*/
				
				//Comenzamos con Persona
				userAndroid.setCodPersona(rs.getInt("c.codPersona"));
				userAndroid.setNombres(rs.getString("c.nombres"));
				userAndroid.setApellidoPaterno(rs.getString("c.apellidoPaterno"));
				userAndroid.setApellidoMaterno(rs.getString("c.apellidoMaterno"));
				userAndroid.setDni(rs.getString("c.dni"));
				userAndroid.setFechaNacimiento(rs.getString("c.fechaNacimiento"));
				//Parsear el celular a String
				int celular = rs.getInt("c.celular");
				userAndroid.setCelular(""+celular);
				//Fin Parse
				userAndroid.setDireccion(rs.getString("c.direccion"));
				userAndroid.setCorreo(rs.getString("c.correo"));
				userAndroid.setLatlong(rs.getString("c.latlong"));
				userAndroid.setReferencia(rs.getString("c.referencia"));
				//Seguimos con Usuario
				userAndroid.setUsuario(rs.getString("a.usuario"));
				userAndroid.setClave(rs.getString("a.clave"));
				userAndroid.setCodUsuario(rs.getInt("a.codUsuario"));
				userAndroid.setEstado(rs.getInt("a.estado"));
				
				//Seguimos con Rol
				userAndroid.setCodRol(rs.getInt("b.codRol"));
				userAndroid.setDescripcion(rs.getString("b.descripcion"));
				
				//Finalizamos con Distrito
				userAndroid.setCodDistrito(rs.getInt("d.codDistrito"));
				userAndroid.setDistrito(rs.getString("d.nombre"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print(e.getMessage());
		}
		return userAndroid;
	}

	@Override
	public boolean editarUser(BeanUsuarioAndroid user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt = con.createStatement();
			String sql = "UPDATE t_persona SET nombres = '"+user.getNombres()+"', apellidoPaterno='"+user.getApellidoPaterno()+"', apellidoMaterno='"+user.getApellidoMaterno()+"', dni='"+user.getDni()+"',fechaNacimiento='"+user.getFechaNacimiento()+"', direccion='"+user.getDireccion()+"', correo='"+user.getCorreo()+"', codDistrito='"+user.getCodDistrito()+"',latlong='"+user.getLatlong()+"', referencia='"+user.getReferencia()+"',celular='"+user.getCelular()+"'"
					+ "WHERE codPersona = '"+user.getCodPersona()+"' ";
			int fila = stmt.executeUpdate(sql);
			
			if(fila>0){
				//Si se cambia algo del usuario va a acá
				String query = "UPDATE t_usuario SET usuario='"+user.getUsuario()+"', clave='"+user.getClave()+"'"
						+ "WHERE codUsuario = '"+user.getCodPersona()+"'";
				int respuesta = stmt.executeUpdate(query);
				if(respuesta>0){
					flag=true;
				}else{
					flag=false;
				}//Fin if respuesta
			}else{
				flag=false;
			}
			con.close();
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO: handle exception
		}
		return flag;
	}

	@Override
	/*Recupera datos de la tabla t_sunat*/
	public BeanSunat validarRUCSunat(String ruc) {
		// TODO Auto-generated method stub
		BeanSunat sunat = null;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			String sql="select * from t_sunat where ruc='"+ruc+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				
				sunat= new BeanSunat();
				sunat.setRuc(rs.getString("ruc"));
				sunat.setRazonSocial(rs.getString("razonSocial"));
				sunat.setDireccion(rs.getString("direccion"));
				sunat.setNombreComercial(rs.getString("nombreComercial"));
				
			}
			con.close();
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO: handle exception
		}
		
		return sunat;
	}

	@Override
	public BeanUsuarioAndroid recuperarContraseña(String correo) {
		// TODO Auto-generated method stub
		BeanUsuarioAndroid user = null;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			String sql="Select * from t_usuario a INNER JOIN t_persona b ON b.codPersona=a.codUsuario WHERE b.correo='"+correo+"'or a.usuario='"+correo+"' and a.estado=1";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(sql);
			
			if(rs.next()){
				user = new BeanUsuarioAndroid();
				user.setClave(rs.getString("a.clave"));
				user.setCodUsuario(rs.getInt("a.codUsuario"));
				user.setNombres(rs.getString("b.nombres"));
				user.setApellidoPaterno(rs.getString("b.apellidoPaterno"));
				user.setApellidoMaterno(rs.getString("b.apellidoMaterno"));
				user.setDni(rs.getString("b.dni"));
				user.setCodPersona(rs.getInt("b.codPersona"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Error al recuperar clave de la BD: "+e.getMessage());
		}
		return user;
	}

	@Override
	public boolean actualizarClave(String clave, int id) {
		// TODO Auto-generated method stub
		boolean flag = true;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			String sql = "update t_usuario set clave='"+clave+"' where codUsuario='"+id+"'";
			Statement stmt = con.createStatement();
			int fila = stmt.executeUpdate(sql);
			if(fila>0){
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocurrió un error al actualizar la clave: "+e.getMessage());
		}

		return flag;
	}

	@Override
	public Vector<UsuarioBean> listarUsuarios(int codDistrito) throws Exception {
		// TODO Auto-generated method stub
		Vector<UsuarioBean> empleados = new Vector<UsuarioBean>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			String sql="select * from t_persona a INNER JOIN t_usuario b ON a.codPersona=b.codUsuario INNER JOIN t_rol c on b.codRol=c.codRol WHERE c.descripcion<>'cliente' and a.codDistrito="+codDistrito+" and b.estado='1' order by a.codPersona asc";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(sql);
			
			while( rs.next() ){
				UsuarioBean objusuario= new UsuarioBean();
				objusuario.setCodUsuario(rs.getInt("b.codUsuario"));
				objusuario.setUsuario(rs.getString("b.usuario"));
				objusuario.setClave(rs.getString("b.clave"));
				objusuario.setCodRol(rs.getInt("b.codRol"));
				
				RolBean objrol=new RolBean();
				objrol.setCodRol(rs.getInt("c.codRol"));
				objrol.setDescripcion(rs.getString("c.descripcion"));
				objusuario.setRol(objrol);
				
				PersonaBean objpersona=new PersonaBean();
				objpersona.setCodPersona(rs.getInt("a.codPersona"));
				objpersona.setNombres(rs.getString("a.nombres"));
				objpersona.setApellidoPaterno(rs.getString("a.apellidoPaterno"));
				objpersona.setApellidoMaterno(rs.getString("a.apellidoMaterno"));
				objpersona.setDni(rs.getString("a.dni"));
				objpersona.setFechaNacimiento(rs.getString("a.fechaNacimiento"));
				objpersona.setCelular(rs.getInt("a.celular"));
				objpersona.setDireccion(rs.getString("a.direccion"));
				objpersona.setCorreo(rs.getString("a.correo"));
				objpersona.setCodDistrito(rs.getInt("a.codDistrito"));
				objpersona.setReferencia(rs.getString("a.referencia"));
				objpersona.setLatlong(rs.getString("a.latlong"));
				objusuario.setPersona(objpersona);
				
				empleados.add(objusuario);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				con.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			System.out.println(e.getMessage());
		}
		return empleados;
	}

	@Override
	public UsuarioBean obtenerEmpleado(int codUsuario) throws Exception {
		// TODO Auto-generated method stub
		UsuarioBean objusuario = null;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			String sql="select * from t_persona a INNER JOIN t_usuario b ON a.codPersona=b.codUsuario INNER JOIN t_rol c on b.codRol=c.codRol WHERE a.codPersona="+codUsuario;
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(sql);
			
			if(rs.next()){
				objusuario= new UsuarioBean();
				objusuario.setCodUsuario(rs.getInt("b.codUsuario"));
				objusuario.setUsuario(rs.getString("b.usuario"));
				objusuario.setClave(rs.getString("b.clave"));
				objusuario.setCodRol(rs.getInt("b.codRol"));
				
				RolBean objrol=new RolBean();
				objrol.setCodRol(rs.getInt("c.codRol"));
				objrol.setDescripcion(rs.getString("c.descripcion"));
				objusuario.setRol(objrol);
				
				PersonaBean objpersona=new PersonaBean();
				objpersona.setCodPersona(rs.getInt("a.codPersona"));
				objpersona.setNombres(rs.getString("a.nombres"));
				objpersona.setApellidoPaterno(rs.getString("a.apellidoPaterno"));
				objpersona.setApellidoMaterno(rs.getString("a.apellidoMaterno"));
				objpersona.setDni(rs.getString("a.dni"));
				objpersona.setFechaNacimiento(rs.getString("a.fechaNacimiento"));
				objpersona.setCelular(rs.getInt("a.celular"));
				objpersona.setDireccion(rs.getString("a.direccion"));
				objpersona.setCorreo(rs.getString("a.correo"));
				objpersona.setCodDistrito(rs.getInt("a.codDistrito"));
				objpersona.setReferencia(rs.getString("a.referencia"));
				objpersona.setLatlong(rs.getString("a.latlong"));
				objusuario.setPersona(objpersona);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				con.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			System.out.println(e.getMessage());
		}
		return objusuario;
	}
	
	@Override
	public boolean modificarUsuario(UsuarioBean empleado) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = true;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt = con.createStatement();
			String query = "update t_persona set nombres='"+empleado.getPersona().getNombres()+"' ,apellidoPaterno='"+empleado.getPersona().getApellidoPaterno()+"' ,apellidoMaterno='"+empleado.getPersona().getApellidoMaterno()+"' ,dni='"+empleado.getPersona().getDni()+"' ,fechaNacimiento='"+empleado.getPersona().getFechaNacimiento()+"' ,celular='"+empleado.getPersona().getCelular()+"' ,direccion='"+empleado.getPersona().getDireccion()+"' ,correo='"+empleado.getPersona().getCorreo()+"' ,codDistrito='"+empleado.getPersona().getCodDistrito()+"' where codPersona='"+empleado.getCodUsuario()+"'";
			int fila =stmt.executeUpdate(query);
			if(fila==1){
				PreparedStatement pstmt = con.prepareStatement("update t_usuario set usuario='"+empleado.getUsuario()+"' ,clave='"+empleado.getClave()+"' ,codRol='"+empleado.getCodRol()+"' where codUsuario='"+empleado.getCodUsuario()+"'");
				pstmt.executeUpdate();
				flag=true;
			}else{
				flag=false;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			flag=false;
			con.close();
			System.out.println(e.getMessage());
		}
		
		return flag;
	}

	@Override
	public boolean agregarUsuario(UsuarioBean empleado) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = true;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt = con.createStatement();
			String query = "insert into t_persona (nombres,apellidoPaterno,apellidoMaterno,dni,fechaNacimiento,celular,direccion,correo,codDistrito) values('"+empleado.getPersona().getNombres()+"','"+empleado.getPersona().getApellidoPaterno()+"','"+empleado.getPersona().getApellidoMaterno()+"','"+empleado.getPersona().getDni()+"','"+empleado.getPersona().getFechaNacimiento()+"','"+empleado.getPersona().getCelular()+"','"+empleado.getPersona().getDireccion()+"','"+empleado.getPersona().getCorreo()+"','"+empleado.getPersona().getCodDistrito()+"')";
			System.out.println(query);
			int codPersona = 0;
			int fila =stmt.executeUpdate(query, stmt.RETURN_GENERATED_KEYS);
			if(fila==1){
				ResultSet rs = stmt.getGeneratedKeys();
				if(rs.next()){
					codPersona = rs.getInt(1);
					PreparedStatement pstmt = con.prepareStatement("insert into t_usuario(codUsuario,usuario,clave,codRol,estado) values('"+codPersona+"','"+empleado.getUsuario()+"','"+empleado.getClave()+"','"+empleado.getCodRol()+"','1')");
					pstmt.executeUpdate();
					flag=true;
				}
			}else{
				flag=false;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			flag=false;
			con.close();
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean eliminarUsuario(int cod) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
			String query="update t_usuario set estado='0' where codUsuario='"+cod+"';";
			int filas=stmt.executeUpdate(query);
			if(filas==1){
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.print(e.getMessage());
		}
		return flag;
	}

	@Override
	public Vector<PersonaBean> backlist(int codDistrito) throws Exception {
		// TODO Auto-generated method stub
		Vector<PersonaBean> backlist = new Vector<PersonaBean>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {

			String sql="select *,count(*) backlist from t_pedido a inner join t_persona b on a.codPersona=b.codPersona inner join t_detallepedido c on a.codPedido=c.codPedido inner join t_sucursal d on c.codSucursal=d.codSucursal where a.codEstadoPedido=3 and d.codDistrito='"+codDistrito+"' group by a.codPersona HAVING COUNT(*) >=6 order by backlist desc";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(sql);
			
			while( rs.next() ){
				PersonaBean objpersona=new PersonaBean();
				objpersona.setCodPersona(rs.getInt("b.codPersona"));
				objpersona.setNombres(rs.getString("b.nombres"));
				objpersona.setApellidoPaterno(rs.getString("b.apellidoPaterno"));
				objpersona.setApellidoMaterno(rs.getString("b.apellidoMaterno"));
				objpersona.setCount(rs.getInt("backlist"));
				backlist.add(objpersona);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.println(e.getMessage());
		}
		return backlist;
	}
	
}
