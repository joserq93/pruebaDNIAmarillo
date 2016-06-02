package beans.android;

import java.io.Serializable;
import java.util.List;

public class BeanCarritoAndroid implements Serializable {

	public BeanCarritoAndroid(){
		
	}
	
	private BeanPedidoAndroid pedido;
	private List<BeanDetalleCarritoAndroid> detalle;

	public BeanPedidoAndroid getPedido() {
		return pedido;
	}

	public void setPedido(BeanPedidoAndroid pedido) {
		this.pedido = pedido;
	}

	public List<BeanDetalleCarritoAndroid> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<BeanDetalleCarritoAndroid> detalle) {
		this.detalle = detalle;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BeanCarritoAndroid [pedido=" + pedido + ", detalle=" + detalle
				+ "]";
	}
	
	
	
}
