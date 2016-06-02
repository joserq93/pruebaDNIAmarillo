package utility;

import java.util.List;

import beans.PresentacionxMedicamentoBean;
import beans.android.BeanDetallePedidoAndroid;
import beans.android.BeanDistrito;
import beans.android.BeanPedidoAndroid;

public class ResponseObject  {

	private boolean success;
	private Object object;
	private String mensaje;
	private List<BeanDistrito> listaD;
	private List<PresentacionxMedicamentoBean> listaSMed;
	private List<BeanPedidoAndroid>listaPedidoA;
	private List<BeanDetallePedidoAndroid>listaDetallePedidoA;
	private List<?> lista;
 	
	public List<BeanDetallePedidoAndroid> getListaDetallePedidoA() {
		return listaDetallePedidoA;
	}
	public void setListaDetallePedidoA(
			List<BeanDetallePedidoAndroid> listaDetallePedidoA) {
		this.listaDetallePedidoA = listaDetallePedidoA;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}
	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseObject [success=" + success + ", object=" + object
				+ "]";
	}

	public List<BeanDistrito> getListaD() {
		return listaD;
	}
	public void setListaD(List<BeanDistrito> listaD) {
		this.listaD = listaD;
	}
	public List<PresentacionxMedicamentoBean> getListaSMed() {
		return listaSMed;
	}
	public void setListaSMed(List<PresentacionxMedicamentoBean> listaSMed) {
		this.listaSMed = listaSMed;
	}
	public List<BeanPedidoAndroid> getListaPedidoA() {
		return listaPedidoA;
	}
	public void setListaPedidoA(List<BeanPedidoAndroid> listaPedidoA) {
		this.listaPedidoA = listaPedidoA;
	}
	public List<?> getLista() {
		return lista;
	}
	public void setLista(List<?> lista) {
		this.lista = lista;
	}

	
	
	
}
