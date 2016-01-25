package tramitedoc.concytec.dao.interfaces;

import java.util.Collection;

import tramitedoc.concytec.bean.BCourier;
import tramitedoc.concytec.bean.BPersona;
import tramitedoc.concytec.bean.BTipoServicio;
import tramitedoc.concytec.bean.BUsuario;

public interface IBusquedasDAO {

	Collection buscar_instituciones(String codigo,String nombre)throws Exception;

	Collection buscar_usuarios(String codigo, String nombre)throws Exception;

	Collection documentos_pend_cerrar()throws Exception;

	BCourier buscar_courier(int id_courier)throws Exception;

	BPersona buscar_persona(int id_persona);

	BUsuario buscar_usuario(String usuario);

	BTipoServicio buscar_tipo_servicio(int tipo_servicio);
}
