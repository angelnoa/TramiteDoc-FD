package tramitedoc.concytec.dao.interfaces;

import java.util.Collection;

import tramitedoc.concytec.bean.BPaquete;
import tramitedoc.concytec.form.FFormMantCerrar;
import tramitedoc.concytec.form.FFormMantPrecerrar;

public interface ICierreDAO {

	Collection lista_tipo_servicios()throws Exception;
	Collection lista_couriers()throws Exception;
	int registrar_preCierre(FFormMantPrecerrar oForm)throws Exception;
	Collection pendientesCierre() throws Exception;
	BPaquete buscar_paquete(int i)throws Exception;
	int cerrar_documentos(FFormMantCerrar oForm)throws Exception;
	int actualizarEstadoDocumentos(int codigo_cierre, int estado) throws Exception;
}
