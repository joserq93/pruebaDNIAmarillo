package beans.android;

public class BeanDetalleCarritoAndroid {

	/*Bean usado para registrar una compra en android*/
	
	private int codPedido;
	private int codSucursal;
	private int codMedicamento;
	private int codCantidadxPresentacion;
	private int codUnidad;
	
	private int cantidad;
	private double precioTotal;
	/**
	 * @return the codPedido
	 */
	public int getCodPedido() {
		return codPedido;
	}
	/**
	 * @param codPedido the codPedido to set
	 */
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}
	/**
	 * @return the codSucursal
	 */
	public int getCodSucursal() {
		return codSucursal;
	}
	/**
	 * @param codSucursal the codSucursal to set
	 */
	public void setCodSucursal(int codSucursal) {
		this.codSucursal = codSucursal;
	}
	/**
	 * @return the codMedicamento
	 */
	public int getCodMedicamento() {
		return codMedicamento;
	}
	/**
	 * @param codMedicamento the codMedicamento to set
	 */
	public void setCodMedicamento(int codMedicamento) {
		this.codMedicamento = codMedicamento;
	}
	/**
	 * @return the codCantidadxPresentacion
	 */
	public int getCodCantidadxPresentacion() {
		return codCantidadxPresentacion;
	}
	/**
	 * @param codCantidadxPresentacion the codCantidadxPresentacion to set
	 */
	public void setCodCantidadxPresentacion(int codCantidadxPresentacion) {
		this.codCantidadxPresentacion = codCantidadxPresentacion;
	}
	/**
	 * @return the codUnidad
	 */
	public int getCodUnidad() {
		return codUnidad;
	}
	/**
	 * @param codUnidad the codUnidad to set
	 */
	public void setCodUnidad(int codUnidad) {
		this.codUnidad = codUnidad;
	}
	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the precioTotal
	 */
	public double getPrecioTotal() {
		return precioTotal;
	}
	/**
	 * @param precioTotal the precioTotal to set
	 */
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}	
	
	
	
}
