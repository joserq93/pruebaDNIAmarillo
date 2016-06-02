package beans.android;

public class BeanPedidoAndroid {

	
	private int codPersona;
	private int codUsuario;
	private int codPedido;
	private String fechaPedido;
	private String horaPedido;
	private double montoTotal;
	private double montoCancelar;
	private String tipoComprobante;
	private long ruc;
	private int codEstadoPedido;
	/*Descripcion del estado del pedido*/
	private String descripcion;
	
	public int getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(int codPersona) {
		this.codPersona = codPersona;
	}
	public int getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	public int getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}
	public String getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public String getHoraPedido() {
		return horaPedido;
	}
	public void setHoraPedido(String horaPedido) {
		this.horaPedido = horaPedido;
	}
	public double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public double getMontoCancelar() {
		return montoCancelar;
	}
	public void setMontoCancelar(double montoCancelar) {
		this.montoCancelar = montoCancelar;
	}
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public long getRuc() {
		return ruc;
	}
	public void setRuc(long ruc) {
		this.ruc = ruc;
	}
	public int getCodEstadoPedido() {
		return codEstadoPedido;
	}
	public void setCodEstadoPedido(int codEstadoPedido) {
		this.codEstadoPedido = codEstadoPedido;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
