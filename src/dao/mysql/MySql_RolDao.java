package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import beans.RolBean;
import dao.interfaces.I_Rol;
import daofactory.MySQLDaoFactory;

public class MySql_RolDao extends MySQLDaoFactory implements I_Rol{

	@Override
	public Vector<RolBean> roles() throws Exception {
		// TODO Auto-generated method stub
		Vector<RolBean> roless = new Vector<RolBean>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		RolBean rol = null;
		try {
			
			String sql="select * from t_rol ORDER BY codRol";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				rol = new RolBean();
				rol.setCodRol(rs.getInt("codRol"));
				rol.setDescripcion(rs.getString("descripcion"));
				
				roless.add(rol);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
		}
		
		return roless;
	}

}
