package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import beans.DistritoBean;
import beans.android.BeanDistrito;
import dao.interfaces.I_Distrito;
import daofactory.MySQLDaoFactory;

public class MySql_DistritoDao extends MySQLDaoFactory implements I_Distrito {

	@Override
	public List<BeanDistrito> listar() {
		// TODO Auto-generated method stub
		List<BeanDistrito> lista = new ArrayList<BeanDistrito>();
		BeanDistrito distrito = null;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
		
			String sql="SELECT * FROM t_distrito ORDER BY codDistrito";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				distrito = new BeanDistrito();
				distrito.setCodDistrito(rs.getInt(1));
				distrito.setNombre(rs.getString(2));
				
				lista.add(distrito);
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
		}
		
		return lista;
	}

	@Override
	public Vector<DistritoBean> distritos() throws Exception {
		// TODO Auto-generated method stub
		Vector<DistritoBean> distritos = new Vector<DistritoBean>();
		DistritoBean distrito = null;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			String sql="select * from t_distrito ORDER BY codDistrito";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				distrito = new DistritoBean();
				distrito.setCodDistrito(rs.getInt("codDistrito"));
				distrito.setNombre(rs.getString("nombre"));
				
				distritos.add(distrito);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
		}
		
		return distritos;
	}

}
