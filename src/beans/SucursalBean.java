package beans;

public class SucursalBean {
	int codSucursal;
	String direccion;
	int codDistrito;
	String latlong;
	DistritoBean distrito;
	public int getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(int codSucursal) {
		this.codSucursal = codSucursal;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getCodDistrito() {
		return codDistrito;
	}
	public void setCodDistrito(int codDistrito) {
		this.codDistrito = codDistrito;
	}
	
	public DistritoBean getDistrito() {
		return distrito;
	}
	public void setDistrito(DistritoBean distrito) {
		this.distrito = distrito;
	}
	public String getLatlong() {
		return latlong;
	}
	public void setLatlong(String latlong) {
		this.latlong = latlong;
	}
	
}
