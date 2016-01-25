package tramitedoc.concytec.util;
/**
 * Autor: Moises Pelaez Sarmiento
 * Descripcion contiene ruta de Carpeta donde se guardan los archivos adjuntos
 * correoTramiteDocumentario:correo electronico que aparecerá como  remitente en los documentos.
 * */
import tramitedoc.concytec.util.Constantes;

public interface IConstante {
	final String filePath = Constantes.CarpetaArchivosDesarrollo.getNombre();
	final String filePathFirmadosBackup = Constantes.CarpetaArchivosDesarrolloenTramiteFirmadosBackup.getNombre();
	final String filePathVisadosBackup = Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre();
	
	final String filePathFirmadosTemporalBackup = Constantes.CarpetaArchivosDesarrolloenTramiteFirmadosTemporalBackup.getNombre();
	final String filePathVisadosTemporalBackup = Constantes.CarpetaArchivosDesarrolloenTramiteVisadosTemporalBackup.getNombre();
	
	final String filePathFirmados = Constantes.CarpetaArchivosDesarrolloenTramiteFirmados.getNombre();
	final String filePathVisados = Constantes.CarpetaArchivosDesarrolloenTramiteVisados.getNombre();
	
	final String filePathMembretes = Constantes.CarpetaArchivosMembretesDesarrollo.getNombre();
	final String correoTramiteDocumentario = Constantes.CorreoTramiteDocumentario.getNombre();
	final String correoTO_Principal = Constantes.CorreoTO.getNombre();
	final String correoCC_Copias = Constantes.CorreoCC.getNombre();
	
	final String documentoCreadoExito = Constantes.DocumentoCreadoExito.getNombre();
	final String documentoModificadoExito = Constantes.DocumentoModificadoExito.getNombre();
	final String documentoFirmadoExito = Constantes.DocumentoFirmadoExito.getNombre();
	
	final int numeroCaracters_Clave = Constantes.NumeroCaracteresPassword.getNumeroCaractes();
	
	final String filePathTemporal = Constantes.CarpetaArchivosDesarrolloTemporal.getNombre();
	
	final String TIPO_SEDE = Constantes.TipoDeSede.getNombre();
	
}