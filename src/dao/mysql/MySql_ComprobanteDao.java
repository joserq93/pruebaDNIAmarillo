package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import beans.ComprobanteBean;
import beans.PedidoBean;
import beans.SunatBean;
import dao.interfaces.I_Comprobante;
import daofactory.MySQLDaoFactory;

public class MySql_ComprobanteDao extends MySQLDaoFactory implements I_Comprobante{
	@Override
	public boolean generarComprobante(PedidoBean pedido) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
			String query;
			if(pedido.getTipoComprobante().equals("Boleta")){
				query="insert into t_boleta (codPedido,fechaEmision) values ('"+pedido.getCodPedido()+"',CURRENT_TIMESTAMP)";
			}else{
				query="insert into t_factura (codPedido,fechaEmision,rucSolicitante) values ('"+pedido.getCodPedido()+"',CURRENT_TIMESTAMP,'"+pedido.getRuc()+"')";
			}
			int filas=stmt.executeUpdate(query);
			if(filas>0){
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
	public ComprobanteBean obtenerComprobante(String codPedido, String tipo)
			throws Exception {
		ComprobanteBean obj=null;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			
			Statement stmt=con.createStatement();
			
			String query;
			if(tipo.equals("Boleta")){
				query="select * from t_boleta where codPedido='"+codPedido+"'";
			}else{
				query="select * from t_factura where codPedido='"+codPedido+"'";
			}
			ResultSet rs= stmt.executeQuery(query);
			if( rs.next() ){
				obj = new ComprobanteBean();
				obj.setNroComprobante(rs.getInt("nroComprobante"));
				obj.setCodPedido(rs.getString("codPedido"));
				obj.setFechaEmision(rs.getString("fechaEmision"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.print(e.getMessage());
		}
		return obj;
	}

	@Override
	public SunatBean obtenerDatosRuc(String ruc) throws Exception {
		SunatBean obj=null;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			
			Statement stmt=con.createStatement();
			
			String query="select * from t_sunat where ruc='"+ruc+"'";
			
			ResultSet rs= stmt.executeQuery(query);
			if( rs.next() ){
				obj = new SunatBean();
				obj.setRuc(rs.getString("ruc"));
				obj.setRazonSocial(rs.getString("razonSocial"));
				obj.setNombreComercial(rs.getString("nombreComercial"));
				obj.setDireccion(rs.getString("direccion"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.print(e.getMessage());
		}
		return obj;
	}
}
