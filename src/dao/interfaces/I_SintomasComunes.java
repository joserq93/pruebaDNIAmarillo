package dao.interfaces;

import java.util.List;

import beans.android.BeanSintomasComunes;

public interface I_SintomasComunes {

	public List<BeanSintomasComunes> listarSintomas();
	public List<BeanSintomasComunes> listarSintomasxSintoma(String sintoma);
	public List<BeanSintomasComunes> listarSintomasxMedicamento(String medicamento);
	
}
