package dao.interfaces;

import java.util.List;
import java.util.Vector;

import beans.DetallePedidoBean;
import beans.IngresosDeliveryBean;
import beans.PedidoBean;
import beans.PresentacionxMedicamentoBean;
import beans.android.BeanCarritoAndroid;
import beans.android.BeanDetallePedidoAndroid;
import beans.android.BeanPedidoAndroid;

public interface I_Pedido {
	
	public Vector<PedidoBean> obtenerPedidosPendientes(int codDistrito) throws Exception ;
	public Vector<PedidoBean> obtenerPedidosProceso(int codDistrito) throws Exception ;
	public Vector<PedidoBean> obtenerHistorialPedidos(int codDistrito) throws Exception ;
	public boolean actualizarPedidoPendiente(PedidoBean pedido) throws Exception ;
	public boolean actualizarPedidoProceso(PedidoBean pedido) throws Exception ;
	public Vector<DetallePedidoBean> obtenerDetalle (String codPedido) throws Exception ;
	public PedidoBean buscarPedido (String codPedido, int codDistrito) throws Exception ;
	public boolean confirmarPedido (int codSucursal,int codMedicamento, int codCantxPresentacion, int stc) throws Exception;
	public boolean reingresarStock1 (int codSucursal,int codMedicamento, int codCantxPresentacion, int stc) throws Exception;
	public boolean reingresarStock2 (int codSucursal,int codMedicamento, int codCantxPresentacion, int stc) throws Exception;
	public boolean actualizarStock2 (int codSucursal,int codMedicamento, int codCantxPresentacion) throws Exception;
	public Vector<PresentacionxMedicamentoBean> rankingMedicamento (int codDistrito) throws Exception;
	public Vector<PresentacionxMedicamentoBean> rankingMedicamentoC (String fecIn, String fecFi,int codDistrito) throws Exception;
	public Vector<PedidoBean> obtenerHistorialPedidosC(String fecIn, String fecFi,int codDistrito) throws Exception ;
	public Vector<IngresosDeliveryBean> obtenerIngresos(int codDistrito) throws Exception ;
	public Vector<IngresosDeliveryBean> obtenerIngresosC(String anio,int codDistrito) throws Exception ;
	
	/*Android*/
	
	public List<BeanPedidoAndroid> buscarPedidosProceso (int codUsuario);
	public List<BeanDetallePedidoAndroid> buscarDetallePedido(int codPedido);
	public List<BeanPedidoAndroid> historialPedidos(int codUsuario);
	public boolean ingresarPedidoAndroid(String codPedido,BeanCarritoAndroid carrito);
	public String obtenerUltimoCodigo();
	public List<String> obtenerMesyAnio();
}
