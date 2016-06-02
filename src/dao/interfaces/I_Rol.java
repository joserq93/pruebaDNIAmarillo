package dao.interfaces;

import java.util.Vector;
import beans.RolBean;

public interface I_Rol {
	public Vector<RolBean> roles() throws Exception;
}
