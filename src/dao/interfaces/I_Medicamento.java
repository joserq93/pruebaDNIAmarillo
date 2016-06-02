package dao.interfaces;

import java.util.List;

import beans.PresentacionxMedicamentoBean;
import beans.android.BeanMedSucursalAndroid;

public interface I_Medicamento {

	public List<PresentacionxMedicamentoBean> buscarxnombre(String medicamento);
	public List<BeanMedSucursalAndroid>buscarMedicamentoxSucursal(String medicamento, int codSucursal);
	

}
