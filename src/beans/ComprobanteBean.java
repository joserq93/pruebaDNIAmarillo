package beans;

public class ComprobanteBean {
	int nroComprobante;
	String fechaEmision;
	String codPedido;
	public int getNroComprobante() {
		return nroComprobante;
	}
	public void setNroComprobante(int nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(String codPedido) {
		this.codPedido = codPedido;
	}
}
