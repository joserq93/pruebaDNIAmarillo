package dao.mysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import beans.DetallePedidoBean;
import beans.DistritoBean;
import beans.EstadoPedidoBean;
import beans.IngresosDeliveryBean;
import beans.PedidoBean;
import beans.PersonaBean;
import beans.PresentacionxMedicamentoBean;
import beans.SucursalBean;
import beans.android.BeanCarritoAndroid;
import beans.android.BeanDetallePedidoAndroid;
import beans.android.BeanPedidoAndroid;
import dao.interfaces.I_Pedido;
import daofactory.MySQLDaoFactory;

public class MySql_PedidoDao extends MySQLDaoFactory implements I_Pedido{

	@Override
	public Vector<PedidoBean> obtenerPedidosPendientes(int codDistrito)
			throws Exception {
		// TODO Auto-generated method stub
				Vector<PedidoBean> pedidos = new Vector<PedidoBean>();
				Connection con=MySQLDaoFactory.obtenerConexion();
				try {
					
					Statement stmt=con.createStatement();
				  //String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido where b.codDistrito='"+codDistrito+"' and c.descripcion='Pendiente' ORDER BY a.fechaPedido,a.horaPedido";
					String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido inner join t_detallepedido d on a.codPedido=d.codPedido inner join t_sucursal e on d.codSucursal=e.codSucursal  where e.codDistrito="+codDistrito+" and c.descripcion='Pendiente' group by a.codPedido  ORDER BY a.fechaPedido, a.horaPedido";
					ResultSet rs= stmt.executeQuery(query);
					while( rs.next() ){
						PedidoBean obj = new PedidoBean();
						
						obj.setCodPedido(rs.getString("a.codPedido"));
						obj.setFechaPedido(rs.getString("a.fechaPedido"));
						obj.setHoraPedido(rs.getString("a.horaPedido"));
						obj.setMontoTotal(rs.getDouble("a.montoTotal"));
						obj.setMontoCancelar(rs.getDouble("a.montoCancelar"));
						obj.setCodPersona(rs.getInt("a.codPersona"));
						obj.setCodEstadoPedido(rs.getInt("a.codEstadoPedido"));
						obj.setTipoComprobante(rs.getString("a.tipoComprobante"));
						obj.setRuc(rs.getString("a.ruc"));
					//	obj.setHoraFinPedido(rs.getString("a.horaFinPedido"));
						
						PersonaBean persona=new PersonaBean();
						persona.setCodPersona(rs.getInt("b.codPersona"));
						persona.setNombres(rs.getString("b.nombres"));
						persona.setApellidoPaterno(rs.getString("b.apellidoPaterno"));
						persona.setApellidoMaterno(rs.getString("b.apellidoMaterno"));
						persona.setCodDistrito(rs.getInt("b.codDistrito"));
						obj.setPersona(persona);
						
						EstadoPedidoBean estadoPedido=new EstadoPedidoBean();
						estadoPedido.setCodEstadoPedido(rs.getInt("c.codEstadoPedido"));
						estadoPedido.setDescripcion(rs.getString("c.descripcion"));
						obj.setEstadoPedido(estadoPedido);
						
						pedidos.add(obj);
					}
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					con.close();
					System.out.print(e.getMessage());
				}
				return pedidos;
	}

	@Override
	public Vector<PedidoBean> obtenerPedidosProceso(int codDistrito)
			throws Exception {
		// TODO Auto-generated method stub
		Vector<PedidoBean> pedidos = new Vector<PedidoBean>();
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
	
			Statement stmt=con.createStatement();
		  //String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido where b.codDistrito='"+codDistrito+"' and c.descripcion='En Proceso' ORDER BY a.fechaPedido,a.horaPedido";
			String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido inner join t_detallepedido d on a.codPedido=d.codPedido inner join t_sucursal e on d.codSucursal=e.codSucursal  where e.codDistrito="+codDistrito+" and c.descripcion='En Proceso' group by a.codPedido  ORDER BY a.fechaPedido, a.horaPedido";
			ResultSet rs= stmt.executeQuery(query);
			while( rs.next() ){
				PedidoBean obj = new PedidoBean();
				
				obj.setCodPedido(rs.getString("a.codPedido"));
				obj.setFechaPedido(rs.getString("a.fechaPedido"));
				obj.setHoraPedido(rs.getString("a.horaPedido"));
				obj.setMontoTotal(rs.getDouble("a.montoTotal"));
				obj.setMontoCancelar(rs.getDouble("a.montoCancelar"));
				obj.setCodPersona(rs.getInt("a.codPersona"));
				obj.setCodEstadoPedido(rs.getInt("a.codEstadoPedido"));
			//	obj.setHoraFinPedido(rs.getString("a.horaFinPedido"));
				
				PersonaBean persona=new PersonaBean();
				persona.setCodPersona(rs.getInt("b.codPersona"));
				persona.setNombres(rs.getString("b.nombres"));
				persona.setApellidoPaterno(rs.getString("b.apellidoPaterno"));
				persona.setApellidoMaterno(rs.getString("b.apellidoMaterno"));
				persona.setCodDistrito(rs.getInt("b.codDistrito"));
				obj.setPersona(persona);
				
				EstadoPedidoBean estadoPedido=new EstadoPedidoBean();
				estadoPedido.setCodEstadoPedido(rs.getInt("c.codEstadoPedido"));
				estadoPedido.setDescripcion(rs.getString("c.descripcion"));
				obj.setEstadoPedido(estadoPedido);
				
				pedidos.add(obj);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.print(e.getMessage());
		}
		return pedidos;
	}

	@Override
	public Vector<PedidoBean> obtenerHistorialPedidos(int codDistrito)
			throws Exception {
		// TODO Auto-generated method stub
		Vector<PedidoBean> pedidos = new Vector<PedidoBean>();
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
		  //String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido where date_format(a.fechaPedido,'%m')=date_format(sysdate(),'%m') and b.codDistrito='"+codDistrito+"' and (c.descripcion='Terminado' or c.descripcion='Cancelado') ORDER BY a.fechaPedido desc, a.horaPedido desc";
			String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido inner join t_detallepedido d on a.codPedido=d.codPedido inner join t_sucursal e on d.codSucursal=e.codSucursal  where date_format(a.fechaPedido,'%m')=date_format(sysdate(),'%m') and e.codDistrito="+codDistrito+" and (c.descripcion='Terminado' or c.descripcion='Cancelado') group by a.codPedido  ORDER BY a.fechaPedido desc, a.horaPedido desc";
			ResultSet rs= stmt.executeQuery(query);
			while( rs.next() ){
				PedidoBean obj = new PedidoBean();
				obj.setCodPedido(rs.getString("a.codPedido"));
				obj.setFechaPedido(rs.getString("a.fechaPedido"));
				obj.setHoraPedido(rs.getString("a.horaPedido"));
				obj.setMontoTotal(rs.getDouble("a.montoTotal"));
				obj.setMontoCancelar(rs.getDouble("a.montoCancelar"));
				obj.setCodPersona(rs.getInt("a.codPersona"));
				obj.setCodEstadoPedido(rs.getInt("a.codEstadoPedido"));
			//	obj.setHoraFinPedido(rs.getString("a.horaFinPedido"));
				
				PersonaBean persona=new PersonaBean();
				persona.setCodPersona(rs.getInt("b.codPersona"));
				persona.setNombres(rs.getString("b.nombres"));
				persona.setApellidoPaterno(rs.getString("b.apellidoPaterno"));
				persona.setApellidoMaterno(rs.getString("b.apellidoMaterno"));
				persona.setCodDistrito(rs.getInt("b.codDistrito"));
				obj.setPersona(persona);
				
				EstadoPedidoBean estadoPedido=new EstadoPedidoBean();
				estadoPedido.setCodEstadoPedido(rs.getInt("c.codEstadoPedido"));
				estadoPedido.setDescripcion(rs.getString("c.descripcion"));
				obj.setEstadoPedido(estadoPedido);
				
				pedidos.add(obj);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.print(e.getMessage());
		}
		return pedidos;
	}

	@Override
	public boolean actualizarPedidoPendiente(PedidoBean pedido) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
			String query="update  t_pedido set codEstadoPedido='"+pedido.getCodEstadoPedido()+"' where codPedido='"+pedido.getCodPedido()+"'";
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
	public boolean actualizarPedidoProceso(PedidoBean pedido) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
			//String query="update  t_pedido set codEstadoPedido='"+pedido.getCodEstadoPedido()+"', horaFinPedido=curTime() where codPedido='"+pedido.getCodPedido()+"'";
			String query="update  t_pedido set codEstadoPedido='"+pedido.getCodEstadoPedido()+"' where codPedido='"+pedido.getCodPedido()+"'";
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
	public Vector<DetallePedidoBean> obtenerDetalle(String codPedido) throws Exception {
		// TODO Auto-generated method stub
		Vector<DetallePedidoBean> detallespedido = new Vector<DetallePedidoBean>();
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			
			Statement stmt=con.createStatement();
		  //String query="Select * from t_detallepedido a INNER JOIN t_pedido b ON a.codPedido=b.codPedido INNER JOIN t_presentacionxmedicamento c ON a.codMedicamento=c.codMedicamento and a.codCantidadxPresentacion=c.codCantidadxPresentacion INNER JOIN t_persona d ON b.codPersona=d.codPersona INNER JOIN t_distrito e ON d.codDistrito=e.codDistrito INNER JOIN t_estadopedido f ON b.codEstadoPedido=f.codEstadoPedido INNER JOIN t_sucursal_presentacionxmedicamentos g ON a.codSucursal=g.codSucursal and a.codMedicamento=g.codMedicamento and a.codCantidadxPresentacion=g.codCantidadxPresentacion  where a.codPedido='"+codPedido+"'";
		    String query="Select * from t_detallepedido a INNER JOIN t_pedido b ON a.codPedido=b.codPedido INNER JOIN t_presentacionxmedicamento c ON a.codMedicamento=c.codMedicamento and a.codCantidadxPresentacion=c.codCantidadxPresentacion and a.codUnidad=c.codUnidad INNER JOIN t_persona d ON b.codPersona=d.codPersona INNER JOIN t_distrito e ON d.codDistrito=e.codDistrito INNER JOIN t_estadopedido f ON b.codEstadoPedido=f.codEstadoPedido INNER JOIN t_sucursal g on a.codSucursal =g.codSucursal INNER JOIN t_distrito ds on g.codDistrito=ds.codDistrito where a.codPedido='"+codPedido+"'";
			ResultSet rs= stmt.executeQuery(query);
			while( rs.next() ){
				DetallePedidoBean obj= new DetallePedidoBean();
				obj.setCodPedido(rs.getString("a.codPedido"));
				obj.setCodMedicamento(rs.getInt("a.codMedicamento"));
				obj.setCodCantidadxPresentacion(rs.getInt("a.codCantidadxPresentacion"));
				obj.setCodUnidad(rs.getInt("a.codUnidad"));
				obj.setCodSucursal(rs.getInt("a.codSucursal"));
				obj.setCantidad(rs.getInt("a.cantidad"));
				obj.setPrecioTotal(rs.getDouble("a.precioTotal"));
				
				PedidoBean pedido=new PedidoBean();
				pedido.setCodPedido(rs.getString("b.codPedido"));
				pedido.setFechaPedido(rs.getString("b.fechaPedido"));
				pedido.setHoraPedido(rs.getString("b.horaPedido"));
			//	pedido.setHoraFinPedido(rs.getString("b.horaFinPedido"));
				pedido.setCodEstadoPedido(rs.getInt("b.codEstadoPedido"));
				pedido.setMontoTotal(rs.getDouble("b.montoTotal"));
				pedido.setMontoCancelar(rs.getDouble("b.montoCancelar"));
				pedido.setTipoComprobante(rs.getString("b.tipoComprobante"));
				pedido.setRuc(rs.getString("b.ruc"));
				
				EstadoPedidoBean estadoPedido=new EstadoPedidoBean();
				estadoPedido.setCodEstadoPedido(rs.getInt("f.codEstadoPedido"));
				estadoPedido.setDescripcion(rs.getString("f.descripcion"));
				pedido.setEstadoPedido(estadoPedido);
				
				PersonaBean persona=new PersonaBean();
				persona.setCodPersona(rs.getInt("d.codPersona"));
				persona.setNombres(rs.getString("d.nombres"));
				persona.setApellidoPaterno(rs.getString("d.ApellidoPaterno"));
				persona.setApellidoMaterno(rs.getString("d.ApellidoMaterno"));
				persona.setDireccion(rs.getString("d.direccion"));
				persona.setCodDistrito(rs.getInt("d.codDistrito"));
				persona.setDni(rs.getString("d.dni"));
				
				DistritoBean distrito=new DistritoBean();
				distrito.setCodDistrito(rs.getInt("e.codDistrito"));
				distrito.setNombre(rs.getString("e.nombre"));
				
				persona.setDistrito(distrito);
				
				pedido.setPersona(persona);
				
				obj.setPedido(pedido);
				
				PresentacionxMedicamentoBean presentacionxmedicamento=new PresentacionxMedicamentoBean();
				presentacionxmedicamento.setAbreviatura(rs.getString("c.abreviatura"));
				presentacionxmedicamento.setPrecio(rs.getDouble("c.precio"));
				presentacionxmedicamento.setCodUnidad(rs.getInt("c.codUnidad"));
				presentacionxmedicamento.setCodMedicamento(rs.getInt("c.codMedicamento"));
				
				obj.setPresentacionxmedicamento(presentacionxmedicamento);
				
				SucursalBean sucursal =new SucursalBean();
				sucursal.setCodSucursal(rs.getInt("g.codSucursal"));
				sucursal.setDireccion(rs.getString("g.direccion"));
				sucursal.setCodDistrito(rs.getInt("g.codDistrito"));
				sucursal.setLatlong(rs.getString("g.latlong"));
				
				DistritoBean distritosucursal=new DistritoBean();
				distritosucursal.setCodDistrito(rs.getInt("ds.codDistrito"));
				distritosucursal.setNombre(rs.getString("ds.nombre"));
				sucursal.setDistrito(distritosucursal);
				
				obj.setSucursal(sucursal);
				//SucursalPresentacionxMedicamentoBean sucpresmed=new SucursalPresentacionxMedicamentoBean();
				//sucpresmed.setStock1(rs.getInt("g.stock1"));
				//sucpresmed.setStock2(rs.getInt("g.stock2"));
				
				//obj.setSucursalxpresentacionxmedicamento(sucpresmed);
				
				detallespedido.add(obj);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.print(e.getMessage());
		}
		return detallespedido;
	}

	@Override
	public PedidoBean buscarPedido(String codPedido, int codDistrito) throws Exception {
		// TODO Auto-generated method stub
		PedidoBean obj=null;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			
			Statement stmt=con.createStatement();
		  //String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido where b.codDistrito='"+codDistrito+"' and a.codPedido='"+codPedido+"'";
			String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido inner join t_detallepedido d on a.codPedido=d.codPedido inner join t_sucursal e on d.codSucursal=e.codSucursal where e.codDistrito='"+codDistrito+"' and a.codPedido='"+codPedido+"' group by a.codPedido";
			ResultSet rs= stmt.executeQuery(query);
			if( rs.next() ){
				obj = new PedidoBean();
				obj.setCodPedido(rs.getString("a.codPedido"));
				obj.setFechaPedido(rs.getString("a.fechaPedido"));
				obj.setHoraPedido(rs.getString("a.horaPedido"));
				obj.setMontoTotal(rs.getDouble("a.montoTotal"));
				obj.setMontoCancelar(rs.getDouble("a.montoCancelar"));
				obj.setCodPersona(rs.getInt("a.codPersona"));
				obj.setCodEstadoPedido(rs.getInt("a.codEstadoPedido"));
			//	obj.setHoraFinPedido(rs.getString("a.horaFinPedido"));
				
				PersonaBean persona=new PersonaBean();
				persona.setCodPersona(rs.getInt("b.codPersona"));
				persona.setNombres(rs.getString("b.nombres"));
				persona.setApellidoPaterno(rs.getString("b.apellidoPaterno"));
				persona.setApellidoMaterno(rs.getString("b.apellidoMaterno"));
				persona.setCodDistrito(rs.getInt("b.codDistrito"));
				obj.setPersona(persona);
				
				EstadoPedidoBean estadoPedido=new EstadoPedidoBean();
				estadoPedido.setCodEstadoPedido(rs.getInt("c.codEstadoPedido"));
				estadoPedido.setDescripcion(rs.getString("c.descripcion"));
				obj.setEstadoPedido(estadoPedido);
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
	public boolean confirmarPedido(int codSucursal, int codMedicamento,int codCantxPresentacion,int stc) throws Exception {
		boolean flag=false;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
			String query="update  t_sucursal_presentacionxmedicamentos set stock1=stock1-"+stc+" where codSucursal='"+codSucursal+"' and codMedicamento='"+codMedicamento+"' and codCantxPresentacion='"+codCantxPresentacion+"';";
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
	public boolean actualizarStock2(int codSucursal, int codMedicamento,int codCantxPresentacion) throws Exception {
		boolean flag=false;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
			String query="update  t_sucursal_presentacionxmedicamentos set stock2=stock1 where codSucursal='"+codSucursal+"' and codMedicamento='"+codMedicamento+"' and codCantxPresentacion='"+codCantxPresentacion+"';";
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
	public boolean reingresarStock1(int codSucursal, int codMedicamento,
			int codCantxPresentacion, int stc) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
		
			Statement stmt=con.createStatement();
			String query="update  t_sucursal_presentacionxmedicamentos set stock1=stock1+"+stc+" where codSucursal='"+codSucursal+"' and codMedicamento='"+codMedicamento+"' and codCantxPresentacion='"+codCantxPresentacion+"';";
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
	public boolean reingresarStock2(int codSucursal, int codMedicamento,
			int codCantxPresentacion, int stc) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
			String query="update  t_sucursal_presentacionxmedicamentos set stock2=stock2+"+stc+" where codSucursal='"+codSucursal+"' and codMedicamento='"+codMedicamento+"' and codCantxPresentacion='"+codCantxPresentacion+"';";
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
	public Vector<PresentacionxMedicamentoBean> rankingMedicamento(
			int codDistrito) throws Exception {
		// TODO Auto-generated method stub
		Vector<PresentacionxMedicamentoBean> rankmed = new Vector<PresentacionxMedicamentoBean>();
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
		    String query="select *,sum(a.cantidad) ranking from t_detallepedido a inner join t_presentacionxmedicamento b on a.codMedicamento=b.codMedicamento and a.codCantidadxPresentacion=b.codCantidadxPresentacion and a.codUnidad=b.codUnidad inner join t_sucursal c on c.codSucursal=a.codSucursal inner join t_pedido d on a.codPedido=d.codPedido where c.codDistrito='"+codDistrito+"' and date_format(d.fechaPedido,'%m')=date_format(sysdate(),'%m') group by a.codMedicamento,a.codCantidadxPresentacion,a.codUnidad order by ranking desc";
			ResultSet rs= stmt.executeQuery(query);
			while( rs.next() ){
				PresentacionxMedicamentoBean obj = new PresentacionxMedicamentoBean();
				obj.setAbreviatura(rs.getString("b.abreviatura"));
				obj.setCount(rs.getInt("ranking"));
				rankmed.add(obj);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.print(e.getMessage());
		}
		return rankmed;
	}

	@Override
	public Vector<PresentacionxMedicamentoBean> rankingMedicamentoC(
			String fecIn, String fecFi, int codDistrito) throws Exception {
		// TODO Auto-generated method stub
		Vector<PresentacionxMedicamentoBean> rankmed = new Vector<PresentacionxMedicamentoBean>();
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
			String query="select *,sum(a.cantidad) ranking from t_detallepedido a inner join t_presentacionxmedicamento b on a.codMedicamento=b.codMedicamento and a.codCantidadxPresentacion=b.codCantidadxPresentacion and a.codUnidad=b.codUnidad inner join t_sucursal c on c.codSucursal=a.codSucursal inner join t_pedido d on a.codPedido=d.codPedido where c.codDistrito='"+codDistrito+"' and d.fechaPedido>='"+fecIn+"' and d.fechaPedido<='"+fecFi+"' group by a.codMedicamento,a.codCantidadxPresentacion,a.codUnidad order by ranking desc";
			ResultSet rs= stmt.executeQuery(query);
			while( rs.next() ){
				PresentacionxMedicamentoBean obj = new PresentacionxMedicamentoBean();
				obj.setAbreviatura(rs.getString("b.abreviatura"));
				obj.setCount(rs.getInt("ranking"));
				rankmed.add(obj);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.print(e.getMessage());
		}
		return rankmed;
	}

	@Override
	public Vector<PedidoBean> obtenerHistorialPedidosC(String fecIn,
			String fecFi, int codDistrito) throws Exception {
		// TODO Auto-generated method stub
		Vector<PedidoBean> pedidos = new Vector<PedidoBean>();
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
		  //String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido where a.fechaPedido>='"+fecIn+"' and a.fechaPedido<='"+fecFi+"' and b.codDistrito='"+codDistrito+"' and (c.descripcion='Terminado' or c.descripcion='Cancelado') ORDER BY a.fechaPedido desc, a.horaPedido desc";
			String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido inner join t_detallepedido d on a.codPedido=d.codPedido inner join t_sucursal e on d.codSucursal=e.codSucursal where a.fechaPedido>='"+fecIn+"' and a.fechaPedido<='"+fecFi+"' and e.codDistrito='"+codDistrito+"' and (c.descripcion='Terminado' or c.descripcion='Cancelado') group by a.codPedido ORDER BY a.fechaPedido desc, a.horaPedido desc";
			ResultSet rs= stmt.executeQuery(query);
			while( rs.next() ){
				PedidoBean obj = new PedidoBean();
				obj.setCodPedido(rs.getString("a.codPedido"));
				obj.setFechaPedido(rs.getString("a.fechaPedido"));
				obj.setHoraPedido(rs.getString("a.horaPedido"));
				obj.setMontoTotal(rs.getDouble("a.montoTotal"));
				obj.setMontoCancelar(rs.getDouble("a.montoCancelar"));
				obj.setCodPersona(rs.getInt("a.codPersona"));
				obj.setCodEstadoPedido(rs.getInt("a.codEstadoPedido"));
			//	obj.setHoraFinPedido(rs.getString("a.horaFinPedido"));
				
				PersonaBean persona=new PersonaBean();
				persona.setCodPersona(rs.getInt("b.codPersona"));
				persona.setNombres(rs.getString("b.nombres"));
				persona.setApellidoPaterno(rs.getString("b.apellidoPaterno"));
				persona.setApellidoMaterno(rs.getString("b.apellidoMaterno"));
				persona.setCodDistrito(rs.getInt("b.codDistrito"));
				obj.setPersona(persona);
				
				EstadoPedidoBean estadoPedido=new EstadoPedidoBean();
				estadoPedido.setCodEstadoPedido(rs.getInt("c.codEstadoPedido"));
				estadoPedido.setDescripcion(rs.getString("c.descripcion"));
				obj.setEstadoPedido(estadoPedido);
				
				pedidos.add(obj);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.print(e.getMessage());
		}
		return pedidos;
	}

	@Override
	public Vector<IngresosDeliveryBean> obtenerIngresos(int codDistrito) throws Exception {
		// TODO Auto-generated method stub
		Vector<IngresosDeliveryBean> ingresos = new Vector<IngresosDeliveryBean>();
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
		  //String query="select date_format(a.fechaPedido,'%m') mes, date_format(a.fechaPedido,'%m-%Y') fecha, sum(a.montoTotal) monto from t_pedido a inner join t_persona p on a.codPersona=p.codPersona where date_format(a.fechaPedido,'%Y')=date_format(sysdate(),'%Y') and p.codDistrito='"+codDistrito+"' and a.codEstadoPedido='4' group by fecha order by mes asc";
			String query="select date_format(a.fechaPedido,'%m') mes, date_format(a.fechaPedido,'%m-%Y') fecha, sum(b.precioTotal) monto from t_pedido a inner join t_persona p on a.codPersona=p.codPersona inner join t_detallepedido b on a.codPedido=b.codPedido inner join t_sucursal c on b.codSucursal=c.codSucursal where date_format(a.fechaPedido,'%Y')=date_format(sysdate(),'%Y') and c.codDistrito="+codDistrito+" and a.codEstadoPedido=4 group by fecha order by mes asc";
			ResultSet rs= stmt.executeQuery(query);
			while( rs.next() ){
				IngresosDeliveryBean obj = new IngresosDeliveryBean();
				obj.setMes(rs.getString("mes"));
				obj.setFecha(rs.getString("fecha"));
				obj.setMontoTotal(rs.getDouble("monto"));
				ingresos.add(obj);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.print(e.getMessage());
		}
		return ingresos;
	}

	@Override
	public Vector<IngresosDeliveryBean> obtenerIngresosC(String anio, int codDistrito)
			throws Exception {
		// TODO Auto-generated method stub
		Vector<IngresosDeliveryBean> ingresos = new Vector<IngresosDeliveryBean>();
		Connection con=MySQLDaoFactory.obtenerConexion();
		try {
			
			Statement stmt=con.createStatement();
		  //String query="select date_format(a.fechaPedido,'%m') mes, date_format(a.fechaPedido,'%m-%Y') fecha, sum(a.montoTotal) monto from t_pedido a inner join t_persona p on a.codPersona=p.codPersona where date_format(a.fechaPedido,'%Y')='"+anio+"' and p.codDistrito='"+codDistrito+"' and a.codEstadoPedido='4' group by fecha order by mes asc";
			String query="select date_format(a.fechaPedido,'%m') mes, date_format(a.fechaPedido,'%m-%Y') fecha, sum(b.precioTotal) monto from t_pedido a inner join t_persona p on a.codPersona=p.codPersona inner join t_detallepedido b on a.codPedido=b.codPedido inner join t_sucursal c on b.codSucursal=c.codSucursal where date_format(a.fechaPedido,'%Y')='"+anio+"' and c.codDistrito='"+codDistrito+"' and a.codEstadoPedido='4' group by fecha order by mes asc";
			ResultSet rs= stmt.executeQuery(query);
			while( rs.next() ){
				IngresosDeliveryBean obj = new IngresosDeliveryBean();
				obj.setMes(rs.getString("mes"));
				obj.setFecha(rs.getString("fecha"));
				obj.setMontoTotal(rs.getDouble("monto"));
				ingresos.add(obj);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
			System.out.print(e.getMessage());
		}
		return ingresos;
	}
	
	/*Android*/
	@Override
	public List<BeanPedidoAndroid> buscarPedidosProceso(int codUsuario) {
		// TODO Auto-generated method stub
		List<BeanPedidoAndroid>lista = new ArrayList<BeanPedidoAndroid>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			
			String sql="SELECT * FROM t_pedido a INNER JOIN t_usuario b ON a.codPersona=b.codUsuario INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido WHERE b.codUsuario='"+codUsuario+"' AND c.codEstadoPedido IN (1,2)";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				BeanPedidoAndroid pedido = new BeanPedidoAndroid();
				pedido.setCodPedido(rs.getInt(1));
				pedido.setFechaPedido(rs.getString(2));
				pedido.setHoraPedido(rs.getString(3));
				pedido.setMontoTotal(rs.getDouble(4));
				pedido.setMontoCancelar(rs.getDouble(5));
				pedido.setTipoComprobante(rs.getString(6));
				pedido.setRuc(rs.getLong(7));
				pedido.setCodPersona(rs.getInt(8));
				pedido.setCodEstadoPedido(rs.getInt(9));
				pedido.setCodUsuario(rs.getInt(10));
				
				lista.add(pedido);
			}
			con.close();
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Error al buscar pedido/Android: "+e.getMessage());
			// TODO: handle exception
		}
		return lista;
	}

	@Override
	public List<BeanDetallePedidoAndroid> buscarDetallePedido(int codPedido) {
		// TODO Auto-generated method stub
		List<BeanDetallePedidoAndroid> lista = new ArrayList<BeanDetallePedidoAndroid>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			

			String sql="Select * from t_detallepedido a  INNER JOIN t_medicamento b ON a.codMedicamento=b.codMedicamento INNER JOIN t_cantidadxpresentacion c ON a.codCantidadxPresentacion=c.codCantidadxPresentacion INNER JOIN t_presentacion d ON c.codPresentacion=d.codPresentacion INNER JOIN t_unidad e ON a.codUnidad=e.codUnidad INNER JOIN t_tipomedida f ON e.codTipoMedida=f.codTipoMedida INNER JOIN t_presentacionxmedicamento p ON a.codMedicamento=p.codMedicamento AND a.codCantidadxPresentacion=p.codCantidadxPresentacion AND a.codUnidad=p.codUnidad where a.codPedido='"+codPedido+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				/*Creación de variables locales que serán concatenadas a fin de mandar dos cadenas al app movil*/
				String med="";
				String cant="";
				String pres="";
				String uni="";
				String tmed="";
				
				/*Fin variables*/
				
				BeanDetallePedidoAndroid detalle = new BeanDetallePedidoAndroid();
				detalle.setCodPedido(rs.getInt(1));
				detalle.setCodSucursal(rs.getInt(2));
				detalle.setCodMedicamento(rs.getInt(3));
				detalle.setCodCantidadxPresentacion(rs.getInt(4));
				detalle.setCodUnidad(rs.getInt(5));
				detalle.setCantidad(rs.getInt(6));
				detalle.setPrecioTotal(rs.getDouble(7));
				detalle.setPrecioUnitario(rs.getDouble(7)/rs.getInt(6));
				
				/*Coger y parsear valores*/
				med=rs.getString("b.nombre");
				cant=Integer.toString(rs.getInt("c.cantidad"));
				pres=rs.getString("d.descripcion");
				uni=Integer.toString(rs.getInt("e.descripcion"));
				tmed=rs.getString("f.abreviatura");
				
				/*Concatenar para enviar a android*/
				
				detalle.setMedicamentoxUnidad(med+" "+uni+tmed);
				if(rs.getInt("d.codPresentacion")==1||rs.getInt("d.codPresentacion")==4||rs.getInt("d.codPresentacion")==5){
				detalle.setMedicamentoxPresentacion(pres+" x "+cant+" unid.");
				}else{
				detalle.setMedicamentoxPresentacion(pres+" x "+cant);
				}
				
				if(rs.getString("b.nombre").equals("Apronax")){
					detalle.setDescripcionMedicamento("El apronax es un agente inflamatorio no estoroideo (AINE) con propiedades analgesicas no nrcóticas y marcada en accón"
							+ "antiinflamatoria. Tabletas contiene 275 mg de apronx, suspensión 125mg/5ml contiene 25mg");
				}else{
					detalle.setDescripcionMedicamento("Fabricado por Laboratorio: "+rs.getString("p.laboratorio"));
				}
				
				detalle.setTipoMedicamento(rs.getString("p.tipo"));
				detalle.setMedicamentoReceta(rs.getString("p.recetaMedica"));
				lista.add(detalle);
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
			System.out.println("Error al listar el detalle del pedido: "+e.getMessage());
		}
		
		return lista;
	}

	@Override
	public List<BeanPedidoAndroid> historialPedidos(int codUsuario) {
		// TODO Auto-generated method stub
		
		List<BeanPedidoAndroid> lista = new ArrayList<BeanPedidoAndroid>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			
			String sql="SELECT * FROM t_pedido a INNER JOIN t_usuario b ON a.codPersona =b.codUsuario INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido WHERE  c.codEstadoPedido  NOT IN(1,2) AND b.codUsuario='"+codUsuario+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				BeanPedidoAndroid pedido = new BeanPedidoAndroid();
				pedido.setCodPedido(rs.getInt(1));
				pedido.setFechaPedido(rs.getString(2));
				pedido.setHoraPedido(rs.getString(3));
				pedido.setMontoTotal(rs.getDouble(4));
				pedido.setMontoCancelar(rs.getDouble(5));
				pedido.setTipoComprobante(rs.getString(6));
				pedido.setRuc(rs.getLong(7));
				pedido.setCodPersona(rs.getInt(8));
				pedido.setCodEstadoPedido(rs.getInt(9));
				pedido.setCodUsuario(rs.getInt("b.codUsuario"));
				pedido.setDescripcion(rs.getString("c.descripcion"));
				lista.add(pedido);
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
		
		return lista;
	}

	@Override
	public boolean ingresarPedidoAndroid(String codPedido,BeanCarritoAndroid carrito) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			
			String sql="insert into t_pedido (codPedido,fechaPedido,horaPedido,montoTotal,montoCancelar,tipoComprobante,ruc,codPersona,codEstadoPedido) "
					+ "values('"+codPedido+"',(select current_date()),(select current_time()),'"+carrito.getPedido().getMontoTotal()+"','"+carrito.getPedido().getMontoCancelar()+"','"+carrito.getPedido().getTipoComprobante()+"','"+carrito.getPedido().getRuc()+"','"+carrito.getPedido().getCodPersona()+"','1')";
			Statement stmt = con.createStatement();
			
			int rpta = stmt.executeUpdate(sql);
			
			if(rpta>0){
				int fila = 0;
				for(int i=0;i<carrito.getDetalle().size();i++){
				String query = "insert into t_detallepedido (codPedido,codSucursal,codMedicamento,codCantidadxPresentacion,codUnidad,cantidad,precioTotal) "
						+ "values( '"+codPedido+"','"+carrito.getDetalle().get(i).getCodSucursal()+"','"+carrito.getDetalle().get(i).getCodMedicamento()+"','"+carrito.getDetalle().get(i).getCodCantidadxPresentacion()+"','"+carrito.getDetalle().get(i).getCodUnidad()+"','"+carrito.getDetalle().get(i).getCantidad()+"','"+carrito.getDetalle().get(i).getPrecioTotal()+"')";
				fila=stmt.executeUpdate(query);
				}
				if(fila>0){
					flag = true;
				}else{
					flag=false;
				}
			}else{
				flag=false;
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
			System.out.println("Error al agregar carrito: "+e.getMessage());
		}
		
		return flag;
	}
	
	@Override
	public String obtenerUltimoCodigo() {
		// TODO Auto-generated method stub
		String codigo = "error";
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			
			String sql="select max(codPedido) from t_pedido";
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				
				codigo=rs.getString(1);
				
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception}
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Error al capturar el mayor codigo: "+e.getMessage());
		}
		
		return codigo;
	}
	
	@Override
	public List<String> obtenerMesyAnio() {
		// TODO Auto-generated method stub
		List<String> lista = new ArrayList<String>();
		Connection con = MySQLDaoFactory.obtenerConexion();
		try {
			
			
			String sql="select substr( year(sysdate()),3)  as year, month(sysdate())as month";
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				String year = rs.getString(1);
				String month = rs.getString(2);
				lista.add(year);
				lista.add(month);
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
