package tramitedoc.concytec.dao.interfaces;

import java.sql.Connection;
import java.util.Collection;
import java.util.Date;

import tramitedoc.concytec.bean.BUsuario;

public interface IReportesDAO {

	Collection consolidado_transacciones(Connection cnx,
			Date fecha_inicio, Date fecha_fin);

	Collection documentos_pendientes_oficina(Connection cnx, Date fecha_inicio,
			Date fecha_fin, String codigo_oficina);

	Collection of_lista_oficinas(Connection cnx, BUsuario usuario);
	
	Collection consolidado_transaccionesn(Connection cnx,
			Date fecha_inicio, Date fecha_fin, String ls_fecha_inicio, String ls_fecha_fin, String ls_codigo_motivo, String grupo_oficina);
	
	Collection consolidado_transaccionesn(Connection cnx,
			Date fecha_inicio, Date fecha_fin, String ls_fecha_inicio, String ls_fecha_fin, String ls_codigo_motivo);
	
	Collection porrecibirreporte(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina, String ls_codigo_motivo);
	
	Collection ntramitereporte(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina, String ls_codigo_motivo);

}
