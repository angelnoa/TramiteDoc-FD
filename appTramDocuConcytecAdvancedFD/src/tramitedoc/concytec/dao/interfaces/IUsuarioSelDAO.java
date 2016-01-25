package tramitedoc.concytec.dao.interfaces;

import java.util.List;

import tramitedoc.concytec.bean.BUsuarioSel;


public interface IUsuarioSelDAO {
	
	public List<BUsuarioSel> getUsersByEmail(String email);

}
