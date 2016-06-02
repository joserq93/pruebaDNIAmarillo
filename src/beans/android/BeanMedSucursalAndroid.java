package beans.android;

import java.io.Serializable;

public class BeanMedSucursalAndroid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeanMedSucursalAndroid(){
		
	}
	
	private int codSucursal;
	private int codMedicamento;
	private int codCantxPresentacion;
	private int codUnidad;
	private int stock1;
	private int stock2;
	/*PresentacionxMedicamento*/
	private String medicamentoxUnidad;
	private String medicamentoxPresentacion;
	private double precio;
	private String tipoMedicamento;
	private String medicamentoReceta;
	
	
	private String descripcionMedicamento;
	
	private String superId;
	
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
	public int getCodCantxPresentacion() {
		return codCantxPresentacion;
	}
	public void setCodCantxPresentacion(int codCantxPresentacion) {
		this.codCantxPresentacion = codCantxPresentacion;
	}
	public int getCodUnidad() {
		return codUnidad;
	}
	public void setCodUnidad(int codUnidad) {
		this.codUnidad = codUnidad;
	}
	public int getStock1() {
		return stock1;
	}
	public void setStock1(int stock1) {
		this.stock1 = stock1;
	}
	public int getStock2() {
		return stock2;
	}
	public void setStock2(int stock2) {
		this.stock2 = stock2;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getMedicamentoxUnidad() {
		return medicamentoxUnidad;
	}
	public void setMedicamentoxUnidad(String medicamentoxUnidad) {
		this.medicamentoxUnidad = medicamentoxUnidad;
	}
	public String getMedicamentoxPresentacion() {
		return medicamentoxPresentacion;
	}
	public void setMedicamentoxPresentacion(String medicamentoxPresentacion) {
		this.medicamentoxPresentacion = medicamentoxPresentacion;
	}
	
	public String getSuperId() {
		return superId;
	}
	public void setSuperId(String superId) {
		this.superId = superId;
	}
	/**
	 * @return the descripcionMedicamento
	 */
	public String getDescripcionMedicamento() {
		return descripcionMedicamento;
	}
	/**
	 * @param descripcionMedicamento the descripcionMedicamento to set
	 */
	public void setDescripcionMedicamento(String descripcionMedicamento) {
		this.descripcionMedicamento = descripcionMedicamento;
	}
	public String getTipoMedicamento() {
		return tipoMedicamento;
	}
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
	@Override
	public String toString() {
		return "BeanMedSucursalAndroid [codSucursal=" + codSucursal
				+ ", codMedicamento=" + codMedicamento
				+ ", codCantxPresentacion=" + codCantxPresentacion
				+ ", codUnidad=" + codUnidad + ", stock1=" + stock1
				+ ", stock2=" + stock2 + ", precio=" + precio
				+ ", medicamentoxUnidad=" + medicamentoxUnidad
				+ ", medicamentoxPresentacion=" + medicamentoxPresentacion
				+ ", superId=" + superId + "]";
	}

	
	
	
	
	
}
