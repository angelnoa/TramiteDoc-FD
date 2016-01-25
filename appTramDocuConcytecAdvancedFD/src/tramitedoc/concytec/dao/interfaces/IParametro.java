package tramitedoc.concytec.dao.interfaces;

import java.util.List;
import tramitedoc.concytec.bean.*;

public interface IParametro {
	
	public List<BParametro> listar();
	public int modificar(BParametro parametro);
	
}
