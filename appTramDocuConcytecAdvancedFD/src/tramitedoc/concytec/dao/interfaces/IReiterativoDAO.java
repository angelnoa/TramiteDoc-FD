package tramitedoc.concytec.dao.interfaces;

import java.sql.Connection;
import java.util.Collection;

public interface IReiterativoDAO {
	
	Collection listarEstados() throws Exception;
	
	Collection listarDocs(String codigo_oficina,String codigo_expediente,String codigo_documento, int valor) throws Exception;

	int registrar()throws Exception;

	Collection listarSecuencias(String codigo_documento)throws Exception;

	String obtenerMaxSecuencia(String codigo_documento)throws Exception;

	Collection lista_upload_documentos_detalle(Connection cnx, int parseInt)throws Exception;

	Collection lista_upload_documentos_detalle_firma_manual(Connection cnx, int parseInt)throws Exception;
	
	void anularMovimiento(String codigo_documento, String secuencia_movimiento)throws Exception;

	boolean perteneceOficina(String codigo_oficina, String codigo_expediente,
			String codigo_documento)throws Exception;

	int ObtieneCantidadArchivos(String codigo_documento)throws Exception;

	void actualizarEstadoArchivos(String codigo_oficina,int correlativo,int codigo_documento,int codigo_expediente ,int secuencia_movimiento,String usuario,int codigo_recepcion)throws Exception;

	boolean esDocumentoReiterado(String codigo_documento,String secuencia_movimiento)throws Exception;

	void recibirDocumentoReiterado(String codigo_oficina, String codigo_documento,
			String usuario, String fecha, String hora,
			String observa_documento, String estado_movimiento,
			String codigo_recepcion, String secuencia_movimiento,
			String asunto_documento, String ls_cargo_personal)throws Exception;	
}
