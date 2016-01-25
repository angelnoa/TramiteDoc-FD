/*
 * Created on 27/11/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tramitedoc.concytec.dao.interfaces;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import tramitedoc.concytec.bean.*;
/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface  ILoginDAO {
    
	public BUsuario fjauthenticate(Connection cnx,String usuario, String clave);

	public int cambiarPassword(String usuario, String new_password);
    
}
