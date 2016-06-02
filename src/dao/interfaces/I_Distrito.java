package dao.interfaces;

import java.util.List;
import java.util.Vector;

import beans.DistritoBean;
import beans.android.BeanDistrito;

public interface I_Distrito {

	public List<BeanDistrito> listar();
	public Vector<DistritoBean> distritos() throws Exception;
}
