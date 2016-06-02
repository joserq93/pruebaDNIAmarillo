package dao.interfaces;

import beans.ComprobanteBean;
import beans.PedidoBean;
import beans.SunatBean;

public interface I_Comprobante {
	public boolean generarComprobante (PedidoBean pedido) throws Exception ;
	public ComprobanteBean obtenerComprobante (String codPedido, String tipo) throws Exception;
	public SunatBean obtenerDatosRuc (String ruc) throws Exception;
}
