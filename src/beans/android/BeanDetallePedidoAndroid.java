package beans.android;

public class BeanDetallePedidoAndroid {

	/*Bean usado para visualizar detalles de pedido en Android*/
	
	private int codPedido;
	private int codSucursal;
	private int codMedicamento;
	private int codCantidadxPresentacion;
	private int codUnidad;
	
	/*Datos para la presentación en Android*/
	private String medicamentoxUnidad;
	private String medicamentoxPresentacion;
	private String descripcionMedicamento;
	private String tipoMedicamento;
	private String medicamentoReceta;
	
	/*Precios*/
	
	private double precioUnitario;
	private int cantidad;
	private double precioTotal;	
	
	public int getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}
	public int getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(int codSucursal) {
		this.codSucursal = codSucursal;
	}
	public int getCodMedicamento() {
		return codMedicamento;
	}
	public void setCodMedicamento(int codMedicamento) {
		this.codMedicamento = codMedicamento;
	}
	public int getCodCantidadxPresentacion() {
		return codCantidadxPresentacion;
	}
	public void setCodCantidadxPresentacion(int codCantidadxPresentacion) {
		this.codCantidadxPresentacion = codCantidadxPresentacion;
	}
	public int getCodUnidad() {
		return codUnidad;
	}
	public void setCodUnidad(int codUnidad) {
		this.codUnidad = codUnidad;
	}
	public int getCantidad() {
		return cantidad;
	}
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
	/**
	 * @return the medicamentoxUnidad
	 */
	public String getMedicamentoxUnidad() {
		return medicamentoxUnidad;
	}
	/**
	 * @param medicamentoxUnidad the medicamentoxUnidad to set
	 */
	public void setMedicamentoxUnidad(String medicamentoxUnidad) {
		this.medicamentoxUnidad = medicamentoxUnidad;
	}
	/**
	 * @return the medicamentoxPresentacion
	 */
	public String getMedicamentoxPresentacion() {
		return medicamentoxPresentacion;
	}
	/**
	 * @param medicamentoxPresentacion the medicamentoxPresentacion to set
	 */
	public void setMedicamentoxPresentacion(String medicamentoxPresentacion) {
		this.medicamentoxPresentacion = medicamentoxPresentacion;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getDescripcionMedicamento() {
		return descripcionMedicamento;
	}
	public void setDescripcionMedicamento(String descripcionMedicamento) {
		this.descripcionMedicamento = descripcionMedicamento;
	}
	/**
	 * @return the tipoMedicamento
	 */
	public String getTipoMedicamento() {
		return tipoMedicamento;
	}
	/**
	 * @param tipoMedicamento the tipoMedicamento to set
	 */
	public void setTipoMedicamento(String tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}
	/**
	 * @return the medicamentoReceta
	 */
	public String getMedicamentoReceta() {
		return medicamentoReceta;
	}
	/**
	 * @param medicamentoReceta the medicamentoReceta to set
	 */
	public void setMedicamentoReceta(String medicamentoReceta) {
		this.medicamentoReceta = medicamentoReceta;
	}

	
	
}
