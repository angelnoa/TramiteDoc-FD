package tramitedoc.concytec.dao.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BDatosFirmante;
import tramitedoc.concytec.bean.BInfo;
import tramitedoc.concytec.bean.BInfoDocumento;
import tramitedoc.concytec.bean.BInfoInv;
import tramitedoc.concytec.bean.BInfoListaReiterativo;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.bean.BInfoDocumentoProyecto;


public interface DocumentosInternosService {
	public ArrayList<EstandarBean> getTipoDocumentosInternosLista() throws Exception;
	
	public ArrayList<EstandarBean> getTipoDocumentosInternosListaTemp(int tipo) throws Exception;

	public ArrayList<EstandarBean> getEstadoDocumentoInternoLista()throws Exception;
	
	public ArrayList<EstandarBean> getEstadoDocumentoInternoListaSecretarias()throws Exception;

	public void saveDocumentoInterno(DocumentoInternoBean documentoInterno, BInfoDocumento bInfoDocuEliminado) throws Exception;
	
	public void saveDocumentosAdjuntos(int codigoDelDocumentoNuevo,BArchivo archivoenlista)throws Exception;
	
	public void deleteDocumentoInterno(int codigo_documento_interno) throws Exception; 

	public Collection getLista_oficinas()throws Exception;

	public List lista_Personas() throws Exception;

	public int of_codigo_oficina(String usuario_actual) throws Exception;
	
	public BInfoDocumento esRegistroDocumentoInterno(String codigo_documento, String codigo_oficina_user, String secuencia, String ls_tipenv) throws Exception;
	
	public boolean esRegistroDocumentoInterno(String codigo_documento, String codigo_oficina_user, String secuencia) throws Exception;
	
	public boolean esRegistroDocumentoInternoObservado(String codigo_documento, String codigo_oficina_user, String secuencia_destino) throws Exception;
	
	public int getEstadoDocumentoInterno(int codigoDocumentoInterno)throws Exception;

	public String of_nombre_director_oficina(int codigo_oficina)throws Exception;

	public ArrayList<DocumentoInternoBean> getlistaDocumentosInternos(
			Integer codigo_oficina_pertenece) throws Exception;

	public String getNombreSelloOficina(Integer codigo_oficina_pertenece)throws Exception;

	public int getSiguienteNumeroDocumento(Integer codigo_oficina_pertenece,
			Integer codigo_tipo_documento_interno)throws Exception;
	
	public int getSiguienteNumeroDocumento(Integer codigo_oficina_pertenece,
			Integer codigo_tipo_documento_interno, boolean esproyecto)throws Exception;
	
	public BInfoDocumento getNumeroDocumentoEliminado(Integer codigo_oficina_pertenece,
			Integer codigo_tipo_documento_interno) throws Exception;
	
	public BInfoDocumento getNumeroDocumentoEliminado(Integer codigo_oficina_pertenece,
			Integer codigo_tipo_documento_interno, boolean esproyecto)throws Exception;
	
	public int getSiguienteNumeroDocumentoBorrador(Integer codigo_oficina_pertenece,
			Integer codigo_tipo_documento_interno)throws Exception;
		
	public int of_tipo_documento_interno(int codigo_documento_interno)throws Exception;
	
	public String getNombreOficialAnio(int parseInt)throws Exception;

	public String getSiglasOficina(Integer codigo_oficina_pertenece)throws Exception;
	
	public String getNombreOficina(Integer codigo_oficina_pertenece)throws Exception;
	
	public String getCargo(Integer personas)throws Exception;

	public String getNombre_Personal(Integer personas)throws Exception;

	public DocumentoInternoBean getDocumentoInterno(int codigo_documento_interno, String tipo_documento)throws Exception;

	public void updateDocumentoInterno(DocumentoInternoBean documentoInterno)throws Exception;
	
	public void updateDocumentoInternoObservado(DocumentoInternoBean documentoInterno, String es_observado)throws Exception;
	
	public void modificaTablaDocumentosInternos(int codigo_documento_interno, int parseInt, int secuencia_movimiento_destino, String tipo_envio) throws Exception;

	public DocumentoInternoBean getDocumentoInterno(String parameter,
			int parseInt)throws Exception;

	public BUsuario obtenerResponsabilidad(BUsuario userSystem)throws Exception;

	public ArrayList<DocumentoInternoBean> getlistaDocumentosInternos(
			int bandeja, int parseInt, int fIRMADO)throws Exception;
	
	public ArrayList<DocumentoInternoBean> getlistaDocumentosInternos(
			int bandeja, int parseInt, int fIRMADO, int firmaprevia)throws Exception;
	
	public ArrayList<DocumentoInternoBean> getlistaDocumentosInternos(
			int bandeja, int parseInt, int fIRMADO, int codigo_tipo_documento_interno, int codigo_oficina, String nom_documento, int nrodoc)throws Exception;
	
	public Collection getPersonal(Integer codigo_oficina)throws Exception;

	public int getCodigoDocumentoInterno(String nombreFile,int codigo_oficina_pertenece)throws Exception;
	
	public int getCodigoDocumentoInterno(String codigo_documento, String oficina, String secuencia)throws Exception;
	
	public ArrayList<BArchivo> getListaArchivosAdjuntos(int codigo_documento_interno) throws Exception;
	
	public boolean siHayArchivosAdjuntosxCambiarNombres(int codigo_documento_interno) throws Exception;
	
	public void updateDocumentosAdjuntos(int codigo_documento,BArchivo archivoenlista)throws Exception;
	
	public void deleteDocumentosAdjuntos(int codigo_documento,BArchivo archivoenlista)throws Exception;
	
	public void updateEstadoRegistro(int codigo_documento_interno, int numero_doc_pdf, String nombreArchivo)throws Exception;
	
	public void updateRutaDocumentosAdjuntos(BArchivo archivoenlista)throws Exception;
	
	public BDatosFirmante getDatosFirmante(String usuario_actual) throws Exception;
	
	public BDatosFirmante getDatosFirmantexCodigo(String codigo_persona_noborrar) throws Exception;
	
	public BInfoListaReiterativo getCodigoDocumentoInternoReiterativo(String codigo_documento, String oficina, String secuencia)throws Exception;
	
	public BInfoListaReiterativo getCodigoDocumentoInternoDerivado(String codigo_documento, String oficina, String secuencia, String oficina_origen)throws Exception;
	
	public BInfoListaReiterativo getCodigoDocumentoInternoDerivadoObservado(String codigo_documento, String oficina, String secuencia)throws Exception;
	
	public ArrayList<DocumentoInternoBean> getlistaDocumentosInternosFirmados(int codigo_tipo_documento_interno, int codigo_documento, int codigo_documento_interno_busqueda,Date fecha_inicio, Date fecha_fin, String dirigido_a, int codigo_oficina_pertenece) throws Exception;
	
	public String of_nombre_archivo_sello_oficina(String codigo_oficina) throws Exception;
	
	public void delete_of_nombre_archivo_sello_oficina(String codigo_oficina) throws Exception;
	
	public ArrayList<BInfoDocumentoProyecto> lista_derivacion_documentos_buscar(int oficina,String codigo_documento) throws Exception;
	
	public BInfoDocumento getDatosAdicionales(String codigo_documento, String secuencia) throws Exception;
	
	public void updateEstadoRegistroObservado(int codigo_documento_interno, int codigo_estado_documento)throws Exception;
	
	public int getcodigo_movimiento_destino(int codigo_documento_interno) throws Exception;

	public int getcodigo_recepcion(int codigo_documento_interno, int secuencia_movimiento_destino) throws Exception;
			
	public String of_ingresar_doc_observardo(int codigo_documento_interno, int secuencia_movimiento, int secuencia_movimiento_destino, String observacion, String codigo_motivo, int codigo_recepcion_destino)throws Exception;
	
	public ArrayList<DocumentoInternoBean> getListaProyectosRecibidosCodigo(String codigo_documento, String oficina, String secuencia, String oficina_origen)throws Exception;
	
	public ArrayList<EstandarBean> getTipoRequerimientoInternoLista()throws Exception;
	
	public String getCargo_of_CodigoCargo(String cargo_personal)throws Exception;
	
	public void updateNombresDocumentosAdjuntos(BArchivo archivoenlista)throws Exception;
	
	public BInfo getInfo(String pcodigo_documento, String psecuencia_movimiento)throws Exception;
	
	public ArrayList<BInfoInv> getlistaDocumentosInternosFirmados() throws Exception;
	
	public BInfo getInfoSobreDocumento(int codigo_documento_interno)throws Exception;
	
	public BInfo getInfoSobreDocumentoFirmadoManualmente(int codigo_documento_interno)throws Exception;
	
	public void deleteFirmadoManual(int id_detalle_documento)throws Exception;
	
	public void updateEstadoUsuariosTblDetUpinternos(String codigo_documento_proyecto_editar)throws Exception;
	
	public ArrayList<DocumentoInternoBean> getlistaArchivosBusqueda(int codigo_oficina, int codigo_oficina_pertenece, String nombre_archivo,Date fecha_inicio, Date fecha_fin) throws Exception;
	
	public void insertLogDocumentoInternoFirma(DocumentoInternoBean documentoenlista)throws Exception;
	
	public void saveDocumentoPrincipalFirmantes(int codigoDelDocumentoNuevo,BArchivo archivoenlista,String[] firmantes_destino)throws Exception;
	
	public void updateEstadoDocumento(String cod_doc_interno, int codigo_estado_documento)throws Exception;
	
	public void saveDocumentoAdjuntoVistoBueno(int codigoDelDocumentoNuevo,BArchivo archivoenlista, String[] visto_bueno_destino)throws Exception;
	
	public ArrayList<BArchivo> of_mostrar_estados_documento(int parseInt, String tipo_lista) throws Exception;
	
	public ArrayList<BArchivo> getArchivoPrincipal(int codigo_documento_interno,int ciclo) throws Exception;
	
	public void updateEstadoDocumentoIndividual(String cod_doc_interno, String estado_input, int codigo_oficina, String observacion, String tipo_adjunto)throws Exception;
	
	public void actualizaCiclo(int codigo_documento_interno,String estado) throws Exception;
	
	public int getInstitucion(int codigo_oficina) throws Exception;
	
	public String getAccesosaOficinas(String codigo_personal) throws Exception;
	
	public String getAccesosaPersonas(String tempaccesosoficina, boolean ve_todos_usuarios, String usuario) throws Exception ;
	
	public String getCodigosPersonalesResponsables(String accesoaoficinas) throws Exception ;
	
	public String getRutaDetalleUpload(String codigo_oficina) throws Exception ;
	
	public BArchivo getDetalleFile(String id_detalle_upload)throws Exception;
	
	public int getGrupoOficina(int codigo_oficina_nuevo)throws Exception;
	
	public String getRutaDocumentoUpload(int id_det) throws Exception ;
}
