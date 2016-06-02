package dao.mysql;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.android.BeanSintomasComunes;
import dao.interfaces.I_SintomasComunes;
import daofactory.MySQLDaoFactory;

public class MySql_SintomasComunesDao extends MySQLDaoFactory implements I_SintomasComunes {

	@Override
	public List<BeanSintomasComunes> listarSintomas() {
		// TODO Auto-generated method stub
		List<BeanSintomasComunes> lista = new ArrayList<BeanSintomasComunes>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
		
			String sql = "SELECT * FROM t_sintomas_comunes a INNER JOIN t_medicamento b ON a.codMedicamento=b.codMedicamento WHERE b.estado=1 ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				BeanSintomasComunes sintomas = new BeanSintomasComunes();
				sintomas.setCodMedicamento(rs.getInt("a.codMedicamento"));
				sintomas.setCodSintomasComunes(rs.getInt("a.codSintomasComunes"));
				sintomas.setNombreMedicamento(rs.getString("b.nombre"));
				sintomas.setSintoma(rs.getString("a.sintoma"));
				
				lista.add(sintomas);
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
			System.out.println("Error al listar todos los sintomas + medicamentos: "+e.getMessage());
		}
		
		return lista;
	}

	@Override
	public List<BeanSintomasComunes> listarSintomasxSintoma(String sintoma) {
		// TODO Auto-generated method stub
		List<BeanSintomasComunes> lista = new ArrayList<BeanSintomasComunes>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			String sql="SELECT * FROM t_sintomas_comunes a INNER JOIN t_medicamento b ON a.codMedicamento=b.codMedicamento WHERE a.sintoma='"+sintoma+"' AND b.estado=1";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				BeanSintomasComunes sintomas = new BeanSintomasComunes();
				sintomas.setCodMedicamento(rs.getInt("a.codMedicamento"));
				sintomas.setCodSintomasComunes(rs.getInt("a.codSintomasComunes"));
				sintomas.setNombreMedicamento(rs.getString("b.nombre"));
				sintomas.setSintoma(rs.getString("a.sintoma"));
				
				lista.add(sintomas);
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
	public List<BeanSintomasComunes> listarSintomasxMedicamento(String medicamento) {
		// TODO Auto-generated method stub
		List<BeanSintomasComunes> lista = new ArrayList<BeanSintomasComunes>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
	
			String sql="SELECT * FROM t_sintomas_comunes a INNER JOIN t_medicamento b ON a.codMedicamento=b.codMedicamento WHERE b.nombre='"+medicamento+"' AND b.estado=1";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				BeanSintomasComunes sintomas = new BeanSintomasComunes();
				sintomas.setCodMedicamento(rs.getInt("a.codMedicamento"));
				sintomas.setCodSintomasComunes(rs.getInt("a.codSintomasComunes"));
				sintomas.setNombreMedicamento(rs.getString("b.nombre"));
				sintomas.setSintoma(rs.getString("a.sintoma"));
				
				lista.add(sintomas);
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

}
