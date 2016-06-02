package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.PresentacionxMedicamentoBean;
import beans.android.BeanMedSucursalAndroid;
import dao.interfaces.I_Medicamento;
import daofactory.MySQLDaoFactory;

public class MySql_MedicamentoDao extends MySQLDaoFactory implements I_Medicamento {

	@Override
	public List<PresentacionxMedicamentoBean> buscarxnombre(String medicamento) {
		// TODO Auto-generated method stub
		List<PresentacionxMedicamentoBean> lista =new ArrayList<PresentacionxMedicamentoBean>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			String sql="SELECT * FROM t_presentacionxmedicamento a INNER JOIN t_medicamento b ON a.codMedicamento=b.codMedicamento WHERE b.nombre='"+medicamento+"' AND a.estado=1 limit 10";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				PresentacionxMedicamentoBean obj = new PresentacionxMedicamentoBean();
				obj.setCodMedicamento(rs.getInt("codMedicamento"));
				obj.setCodCantidadxPresentacion(rs.getInt("codCantidadxPresentacion"));
				obj.setCodUnidad(rs.getInt("codUnidad"));
				obj.setAbreviatura(rs.getString("abreviatura"));
				obj.setPrecio(rs.getDouble("precio"));
				obj.setLaboratorio(rs.getString("laboratorio"));
				obj.setFechaVencimiento(rs.getString("fechaVencimiento"));
				obj.setRecetaMedica(rs.getString("recetaMedica"));
				obj.setTipo(rs.getString("tipo"));
				
				lista.add(obj);
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
	public List<BeanMedSucursalAndroid> buscarMedicamentoxSucursal(
			String medicamento, int codSucursal) {
		// TODO Auto-generated method stub
		List<BeanMedSucursalAndroid> lista =new ArrayList<BeanMedSucursalAndroid>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			String cadena = "'%"+medicamento+"%'";
			System.out.println(cadena);
			String sql="SELECT * FROM t_sucursal_presentacionxmedicamentos a INNER JOIN t_presentacionxmedicamento b ON a.codCantxPresentacion=b.codCantidadxPresentacion AND a.codMedicamento=b.codMedicamento AND a.codUnidad=b.codUnidad INNER JOIN t_medicamento c ON a.codMedicamento=c.codMedicamento INNER JOIN t_cantidadxpresentacion d ON a.codCantxPresentacion=d.codCantidadxPresentacion INNER JOIN t_presentacion e ON d.codPresentacion=e.codPresentacion INNER JOIN t_unidad f ON a.codUnidad=f.codUnidad INNER JOIN t_tipomedida g ON f.codTipoMedida=g.codTipoMedida WHERE c.nombre like  "+cadena+" AND a.estado=1 AND a.codSucursal='"+codSucursal+"' limit 10";
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		
			while(rs.next()){
				
				/*Creación de variables locales que serán concatenadas a fin de mandar dos cadenas al app movil*/
				String med="";
				String cant="";
				String pres="";
				String uni="";
				String tmed="";
				
				/*Fin variables*/

				BeanMedSucursalAndroid medxsus = new BeanMedSucursalAndroid();
				medxsus.setCodSucursal(rs.getInt("a.codSucursal"));
				medxsus.setCodMedicamento(rs.getInt("a.codMedicamento"));
				medxsus.setCodUnidad(rs.getInt("a.codUnidad"));
				medxsus.setCodCantxPresentacion(rs.getInt("a.codCantxPresentacion"));
				medxsus.setStock1(rs.getInt("a.stock1"));
				medxsus.setStock2(rs.getInt("a.stock2"));
				medxsus.setPrecio(rs.getDouble("b.precio"));
				
				med=rs.getString("c.nombre");
				cant=Integer.toString(rs.getInt("d.cantidad"));
				pres=rs.getString("e.descripcion");
				uni=Integer.toString(rs.getInt("f.descripcion"));
				tmed=rs.getString("g.abreviatura");
				
				System.out.println(med+uni+tmed);
				System.out.println(cant+pres);
				
				medxsus.setMedicamentoxUnidad(med+" "+uni+tmed);
				if(rs.getInt("e.codPresentacion")==1||rs.getInt("e.codPresentacion")==4||rs.getInt("e.codPresentacion")==5)
				medxsus.setMedicamentoxPresentacion(pres+" x "+cant+" unid.");
				else
				medxsus.setMedicamentoxPresentacion(pres+" x "+cant);
				/*SUPER ID*/
				
				medxsus.setSuperId(""+rs.getInt("a.codSucursal")+rs.getInt("a.codMedicamento")+rs.getInt("a.codUnidad")+rs.getInt("a.codCantxPresentacion"));
				
				medxsus.setDescripcionMedicamento("Fabricado por Laboratorio: "+rs.getString("b.laboratorio"));
				medxsus.setTipoMedicamento(rs.getString("b.tipo"));
				medxsus.setMedicamentoReceta(rs.getString("b.recetaMedica"));
				lista.add(medxsus);
				
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
			System.out.println("Error al buscar medicamentos x sucursal: "+e.getMessage());
			
		}

		return lista;
	}

}
