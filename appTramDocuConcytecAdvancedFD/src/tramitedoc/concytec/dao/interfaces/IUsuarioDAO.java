/*
 * Created on 27/11/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tramitedoc.concytec.dao.interfaces;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;

import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.form.FFormMantArchivar;
import tramitedoc.concytec.form.FFormMantDerivar;
import tramitedoc.concytec.form.FFormMantRecepcion;
/**
 * @author Christian MAchuca
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface  IUsuarioDAO {
    
	public abstract Collection of_lista_oficinas_usuario(Connection cnx,String usuario) throws Exception;
	public abstract Collection of_lista_series(Connection cnx,String oficina) throws Exception;
	public abstract Collection of_lista_documentos(Connection cnx,String oficina) throws Exception;
	public abstract Collection of_lista_tipdoc_todos(Connection cnx) throws Exception;
	public abstract BOficinas of_nombre_oficina(Connection cnx,String oficina) throws Exception;
	public abstract BPersonal of_datos_usuario(Connection cnx,String codpersonal,String codoficina) throws Exception;
	public abstract Collection of_lista_personas_oficinas(String oficina) throws Exception;
	public abstract String of_abreviatura(String codigo_personal) throws Exception;
	public abstract Collection of_lista_oficinas(Connection cnx) throws Exception;
	public abstract Collection of_lista_oficinas(Connection cnx,String codigo_oficina) throws Exception;
	
	public abstract Collection of_lista_oficinas_institucion(String pcodigo_institucion, boolean valor) throws Exception;
	public abstract Collection of_lista_oficinas_institucion(String pcodigo_institucion,String grupo_oficina, boolean valor) throws Exception;
	public abstract Collection of_lista_oficinas_institucion(String pcodigo_institucion, String nombre_usuario) throws Exception;
	public abstract boolean validaUsuarioDirigido(String nombre_usuario) throws Exception;
	
	public abstract Collection of_lista_rutas(Connection cnx) throws Exception;
	public abstract Collection of_lista_modalidad_rutas(String cod_ruta) throws Exception;
	public abstract Collection of_lista_motivos(Connection cnx) throws Exception;
	public abstract Collection of_lista_motivos() throws Exception;
	public abstract Collection of_lista_correlativos(Connection cnx,String oficina) throws Exception;
	public int  of_lista_mesa_partes(Connection cnx,String oficina,String fecha_inicio,String fecha_fin,String naturaleza,String estado,String tipodoc,String filtro,String valorfiltro) throws Exception;
	public abstract Collection mesa_partes_normal(Connection cnx,String oficina,String fecha_inicio,String fecha_fin,String naturaleza,
			 String estado,String tipodoc,String filtro,String valorfiltro,String numero_registro,String numero_expediente) throws Exception;
	public abstract Collection of_procedencia_listar(Connection cnx,String Tipo,String Cadena) throws Exception;
	public abstract Collection of_procedencia_listar(String Tipo,String Cadena) throws Exception;
	public abstract Collection of_procedencia_interno_listar(String Cadena) throws Exception;
	public abstract BPersona of_busca_persona(int codigo) throws Exception;
	public abstract JSONObject of_procedencia_listar(String Cadena) throws Exception;
	public abstract JSONObject of_dirigido_listar(String Cadena) throws Exception;
	public abstract JSONObject of_dirigido_listar(String Cadena, String institucion) throws Exception;
	public abstract JSONObject of_firmadox_listar(String Cadena) throws Exception;
	public abstract Collection of_lista_solicitud(Connection cnx) throws Exception;
	public abstract Collection of_listar_personas(Connection cnx) throws Exception;
	public abstract Collection of_listar_medios(Connection cnx) throws Exception;
	public abstract void of_ingresar_procedencia(Connection cnx,String descripcion,String tipopersona)throws Exception;
	public abstract void of_ingresar_procedencia(String descripcion,String tipopersona)throws Exception;
	public abstract String of_ingresar_documento(Connection cnx,String codigo_oficina,String medio,String codigo_tipo,String naturaleza_documento,String destino_documento,
			String contacto,String destinatario,String codigo_solicitud,String numero_documento,
			String fecha_registro,String hora,String folios_documento,String asunto_documento,
			String observa_documento,String usuario_actual,String firmadopor,String origen_documento,
			String desc_origen,String tipo,String persona,String fecha_rpta,String correlativorecepcion,
			String numero_referencia,String dirigido,String claveExterior, String codigo_intitucion)throws Exception;
	public abstract void of_recibir_doc(Connection cnx,String oficina,String documento,String usuario,String fecha,
			String hora,String observacion,String ls_estado_doc,String ls_correlativorecepcion,
			String secuencia_movimiento,String asunto_documento,String cargo_personal)throws Exception;
	public abstract BMesaPartes of_obtener_padre(Connection cnx,String documento) throws Exception;
	public abstract void of_registrar_derivacion_mismo(Connection cnx,String ls_oficina,String codigo_oficina,String codigo_motivo,String observacion
   			,String destinatario,String ls_documento,String ls_secuencia,String ls_usuario,String ls_codigoPadre,
   			int correla,String fecha_rpta,int codigo_expediente,String ls_descripcion_oficina,
   			int codigo_recepcion,String doc_resp,String fecha_rpta_rq,Character tipo_envio)throws Exception;
	public abstract void of_registrar_recepcion_mismo(Connection cnx,String ls_oficina,String codigo_oficina,String codigo_motivo,String observacion
   			,String destinatario,String ls_documento,String ls_secuencia,String ls_usuario,String ls_codigoPadre,
   			int correla,String fecha_rpta,String codigo_expediente,String ls_descripcion_oficina,String codigo_recepcion,String fecha_rpta_rq)throws Exception;
	public abstract Collection of_lista_derivacion_documento(Connection cnx,int correla) throws Exception;
	public abstract BPersonal  of_datos_personal(Connection cnx,String codpersonal) throws Exception;
	public abstract Collection of_lista_derivacion(Connection cnx,String codigo_oficina,
			String descripcion_oficina,String codigo_motivo,String descripcion_motivo,
			String destinatario,String nom_persona,String observacion) throws Exception;
	public abstract int parametro(Connection cnx) throws Exception;
	public abstract BMesaPartes  of_detalle_documento(Connection cnx,String oficina,String documento,String secuencia) throws Exception;
	public abstract void of_archivar(Connection cnx,String ls_documento,String ls_oficina,
			String ls_secuencia,String ls_observacion,String estado_movimiento)throws Exception;
	public abstract BCodPadre  of_obtener_padre(Connection cnx,String documento,String secuencia) throws Exception;
	public abstract Collection of_recorrido(Connection cnx,String docinicia) throws Exception;
	//JAZANERO
	public abstract String of_ingresar_documento_interno(Connection cnx,String codigo_oficina,String medio,String codigo_tipo,String naturaleza_documento,String destino_documento,
			String contacto,String destinatario,String codigo_solicitud,String numero_documento,
			String fecha_registro,String hora,String folios_documento,String asunto_documento,
			String observa_documento,String usuario_actual,String firmadopor,String origen_documento,
			String desc_origen,String tipo,String persona,String fecha_rpta,String correlativorecepcion,String numero_referencia,String dirigido,String codigo_intitucion)throws Exception;
	public abstract void of_ingresar_documento_interno_entrada(Connection cnx,String codigo_oficina,String codigo_tipo,
			String origen_documento,String numero_documento,String fecha_registro,String asunto_documento,
			String observa_documento,String usuario_actual,String ls_persona,String ls_codigoPadre,
			String ls_documento,String ls_secuencia_doc,String desc_origen,String ls_seleccion,String correlativo_deriva)throws Exception;
	public abstract void of_registrar_derivacion_intent(Connection cnx,String ls_oficina,String codigo_oficina,String codigo_motivo,String observacion
   			,String destinatario,String ls_documento,String ls_secuencia,String ls_usuario,String ls_codigoPadre,
   			int correla,String fecha_rpta,String ls_correlativo_deriva,String ls_descripcion_oficina)throws Exception;
	public abstract void of_recibir_doc_mesapartes(Connection cnx,String oficina,String documento,String usuario,String fecha,
			String hora,String observacion,String ls_estado_doc,String ls_correlativorecepcion)throws Exception;
	public abstract void of_modificar_procedencia(Connection cnx,String codigo,String descripcion,String tipopersona)throws Exception;
	public abstract void of_modificar_procedencia(String codigo,String descripcion,String tipopersona)throws Exception;
	public abstract void of_eliminar_procedencia(Connection cnx,String codigo,String tipopersona)throws Exception;
	public abstract void of_eliminar_procedencia(String codigo)throws Exception;
	public abstract Collection of_lista_rango_codigos(Connection cnx,String oficina,String naturaleza,String fecha) throws Exception;
	public abstract void of_registrar_derivacion_interno(Connection cnx,String ls_oficina,String codigo_oficina,String codigo_motivo,String observacion
   			,String destinatario,String ls_documento,String ls_secuencia,String ls_usuario,String ls_codigoPadre,
   			int correla,String fecha_rpta,int codigo_expediente,String ls_descripcion_oficina,
   			int codigo_recepcion,String doc_resp,String fecha_rpta_rq)throws Exception;
	
	public abstract Collection mesa_partes_naturaleza_buscar(Connection cnx,String naturaleza,String oficina) throws Exception;
	public abstract Collection mesa_partes_naturaleza_tipodoc_buscar(Connection cnx,String ls_naturaleza,
			 String tipodoc,String oficina) throws Exception;
	public abstract Collection mesa_partes_numeroexp_buscar(Connection cnx,String fecha_inicio,String fecha_fin,
			 String expediente,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_naturaleza_numeroreg_buscar(Connection cnx,String ls_naturaleza,
			 String numero_registro,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_naturaleza_fechaini_fechafin_buscar(Connection cnx,String ls_naturaleza,
			 String ls_fecha_inicio,String ls_fecha_fin,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_naturaleza_estado_buscar(Connection cnx,String ls_naturaleza,
			 String ls_estado,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_naturaleza_expediente_buscar(Connection cnx,String ls_naturaleza,
			 String ls_numero_expediente,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_tipodoc_buscar(Connection cnx,String ls_tipodoc,
			String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_tipodoc_numreg_buscar(Connection cnx,String ls_tipodoc,String ls_numero_registro,
			String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_tipodoc_fecini_fecfin_buscar(Connection cnx,String ls_tipodoc,String ls_fecha_inicio,String ls_fecha_fin,
			String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_tipodoc_estado_buscar(Connection cnx,String ls_tipodoc,String ls_estado,
			String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_tipodoc_numexp_buscar(Connection cnx,String ls_tipodoc,String ls_numero_expediente,
			String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_numreg_buscar(Connection cnx,String ls_numero_registro,
			String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_numreg_fechaini_fechafin_buscar(Connection cnx,String ls_numero_registro,
			String ls_fecha_inicio,String ls_fecha_fin,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_numreg_estado_buscar(Connection cnx,String ls_numero_registro,
			String ls_estado,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_numreg_numexp_buscar(Connection cnx,String ls_numero_registro,
			String ls_numero_expediente,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_estado_buscar(Connection cnx,String ls_estado,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_estado_numexp_buscar(Connection cnx,String ls_estado,String ls_numero_expediente,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_numexp_buscar(Connection cnx,String ls_numero_expediente,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_procedencia_buscar(Connection cnx,String ls_procedencia,String codigo_oficina) throws Exception;
	public abstract Collection mesa_partes_fecha_buscar(Connection cnx,String ls_sle_fecha,String codigo_oficina) throws Exception;
	
	
	public abstract Collection of_lista_reportes(Connection cnx,String oficina,String naturaleza,String fecha,String cbodesde,String cbohasta) throws Exception;
	public abstract void of_modificar_fecharespuesta(Connection cnx,String oficina,String documento)throws Exception;
	
	
	public abstract Collection lista_recepcion_documentos(Connection cnx,String oficina) throws Exception;
	public abstract int total_recepcion_documentos(String oficina) throws Exception;
	public abstract int[] total_control_recepcion_documentos(String oficina,String codigo_persona,String accesooficinas) throws Exception;
	public abstract Collection lista_recepcion_documentos_buscar(Connection cnx,String oficina,String accesoaoficinas,String codigo_documento,String codigo_expediente) throws Exception;
	public abstract Collection lista_preliquidar_documentos(Connection cnx,String oficina) throws Exception;
	
	 public abstract void of_recibir_doc_edit(Connection cnx,String oficina,String documento,String usuario,String fecha,
			 String hora,String observacion,String ls_estado_doc,String ls_correlativorecepcion,String secuencia_moviemiento) ;
		 
	 public abstract Collection lista_derivacion_documentos(Connection cnx) throws Exception;
	 public abstract Collection of_lista_personal(Connection cnx) throws Exception;
	 public abstract Collection lista_derivacion_documentos(Connection cnx,String oficina,String accesoaoficinas,String inicia,int valor) throws Exception;
	 public abstract Collection lista_derivacion_documentos(Connection cnx,String oficina,String persona,String accesoaoficinas, String inicia,int valor) throws Exception;
	 public abstract Collection lista_observacion_documentos(Connection cnx,String oficina,String inicia) throws Exception;
	 
	 public abstract Collection lista_derivacion_documentos_buscar(Connection cnx,String oficina,String accesoaoficinas,String codigo_documento,String codigo_expediente) throws Exception;
	 public abstract void of_derivar_doc_edit(Connection cnx,String oficina,String documento,String usuario,String fecha,
			 String hora,String observacion,String ls_estado_doc,String ls_correlativorecepcion) ;
	 public abstract Collection lista_documentos_todos(Connection cnx,String oficina) throws Exception;
	 
	 public abstract Collection of_busca_documentos(Connection cnx,String oficina,String procedencia,
			 String sle_fecha,String medio,String codigo_tipo,String codigo_oficina,String codigo_solicitud,
			 String codigo_documento,String codigo_expediente,String firmadopor,
			 String dirigido,String estado,String numero_documento,String doc_resp,String asunto_documento,
			 String sle_fecha_recepcion,String sle_fecha_derivacion,String codigo_motivo,String flag, String observacion) throws Exception;
	 
	 public abstract Collection of_busca_documentos_tramite(Connection cnx,String oficina,String procedencia,
			 String sle_fecha,String medio,String codigo_tipo,String codigo_oficina,String codigo_solicitud,
			 String codigo_documento,String codigo_expediente,String firmadopor,
			 String dirigido,String estado,String numero_documento,String doc_resp,String asunto_documento,
			 String sle_fecha_recepcion,String sle_fecha_derivacion,String codigo_motivo,String flag, String observacion) throws Exception;
	 
	 public abstract Collection lista_documentos_archivar(Connection cnx,String oficina,String codigo_doc) throws Exception;
	 public abstract Collection lista_documentos_anular(Connection cnx,String oficina,String codigo_doc,String document) throws Exception;
	 public abstract void of_anular(Connection cnx,String ls_documento,String ls_oficina,
			 String ls_secuencia,String ls_observacion,String estado_movimiento)throws Exception;
	
	 public abstract void of_modificar_doc(Connection cnx,String codigo_oficina,String codigo_documento,String usuario,
			 String fecha,String hora,String observacion,String estado_movimiento,String fecha_registro,
			 String procedencia,String hora_registro,String firmadopor,String dirigido,String medio
			 ,String codigo_solicitud,String numero_documento,String asunto_documento)throws Exception;
	 
	 public abstract Collection lista_documentos_modificar(Connection cnx,String oficina) throws Exception;
	 public abstract void of_preliquidar_doc(Connection cnx,String codigo_oficina,String codigo_documento,
			 String usuario,String fecha,String hora,String observacion,String estado_movimiento,
			 String secuencia_movimiento,String asunto_documento,String codigo_tipo,
			 String codigooficina,String cod_ruta,String cod_modalidad,
			 String descripcion)throws Exception;
	 public abstract Collection lista_liquidar_documentos(Connection cnx,String oficina) throws Exception;
	 public abstract void of_liquidar_doc(Connection cnx,String codigo_oficina,String codigo_documento,String usuario,
			 String fecha,String hora,String observacion,String estado_movimiento,String secuencia_movimiento)throws Exception;
	 public abstract Collection of_busca_documentos_pendientes(Connection cnx,String numero_documento,String codigo_oficina) throws Exception;
	 public abstract Collection of_lista_busqueda_linea(Connection cnx,String anio,String num_exp) throws Exception;
	 
	 public abstract Collection lista_derivacion_documentos(Connection cnx,String oficina,String persona,String inicia) throws Exception;
	 
	 
	 public abstract void of_registrar_derivacion_mismo(Connection cnx,String ls_oficina,String codigo_oficina,String codigo_motivo,String observacion
	   			,String destinatario,String ls_documento,String ls_secuencia,String ls_usuario,String ls_codigoPadre,
	   			int correla,String fecha_rpta,int codigo_expediente,String ls_descripcion_oficina,int codigo_recepcion,String doc_resp,String fecha_rpta_rq,String flag)throws Exception;
	 
	 public abstract void of_registrar_derivacion_mismo(Connection cnx,String ls_oficina,String codigo_oficina,String codigo_motivo,String observacion
	   			,String destinatario,String ls_documento,String ls_secuencia,String ls_usuario,String ls_codigoPadre,
	   			int correla,String fecha_rpta,int codigo_expediente,String ls_descripcion_oficina,
	   			int codigo_recepcion,String doc_resp,String fecha_rpta_rq,Character tipo_envio, int secuencia_origen)throws Exception;
	 
	 public abstract void of_registrar_derivacion_mismo(Connection cnx,String ls_oficina,String codigo_oficina,String codigo_motivo,String observacion
	   			,String destinatario,String ls_documento,String ls_secuencia,String ls_usuario,String ls_codigoPadre,
	   			int correla,String fecha_rpta,int codigo_expediente,String ls_descripcion_oficina,int codigo_recepcion,String doc_resp,String fecha_rpta_rq,String flag,int secuencia_origen, int value)throws Exception;
	 
	/******************Reportes***************/
	public abstract Collection reportes_todos_buscar(Connection cnx) throws Exception;
	public abstract Collection reportes_naturaleza_buscar(Connection cnx,String naturaleza) throws Exception;
	public abstract Collection reportes_estado_buscar(Connection cnx,String estado) throws Exception;
	public abstract Collection reportes_tipodoc_buscar(Connection cnx,String tipodoc) throws Exception;
	public abstract Collection reportes_tipodoc_estado_buscar(Connection cnx,String tipodoc,String estado) throws Exception;
	public abstract Collection reportes_naturaleza_estado_buscar(Connection cnx,String naturaleza,String estado) throws Exception;
	public abstract Collection reportes_naturaleza_tipodoc_buscar(Connection cnx,String naturaleza,String tipodoc) throws Exception;
	public abstract Collection reportes_naturaleza_estado_codigotipo_buscar(Connection cnx,String naturaleza,String estado,String codigo_tipo) throws Exception;
	
	public abstract Collection reportes_usuario_buscar(Connection cnx,String usuario) throws Exception;
	public abstract Collection reportes_usuario_fecha_buscar(Connection cnx,String usuario,String fecha) throws Exception;
	public abstract Collection reportes_usuario_oficina_buscar(Connection cnx,String usuario,String oficina) throws Exception;
	public abstract Collection reportes_fecha_buscar(Connection cnx,String fecha) throws Exception;
	public abstract Collection reportes_fecha_oficina_buscar(Connection cnx,String fecha,String oficina) throws Exception;
	public abstract Collection reportes_oficina_buscar(Connection cnx,String oficina) throws Exception;
	public abstract Collection reportes_oficina_usuario_fecha_buscar(Connection cnx,String oficina,String usuario,String fecha) throws Exception;
	public abstract Collection reportes_solicitud_buscar(Connection cnx,String solicitud,String ls_oficina) throws Exception;
	public abstract Collection reportes_numexpediente_buscar(Connection cnx,String numexpediente,String ls_oficina) throws Exception;
	public abstract Collection reportes_medio_buscar(Connection cnx,String medio,String ls_oficina) throws Exception;
	public abstract Collection reportes_codtipo_buscar(Connection cnx,String ls_codigo_tipo,String ls_oficina) throws Exception;
	public abstract Collection reportes_numregistro_buscar(Connection cnx,String ls_numero_registro,String ls_oficina) throws Exception;
	
	public abstract Collection reportes_codtipo_buscar(Connection cnx,String ls_codigo_tipo) throws Exception;
	public abstract Collection reportes_codtipo_procedencia_buscar(Connection cnx,String ls_codigo_tipo,String ls_procedencia,String ls_oficina) throws Exception;
	public abstract Collection reportes_codtipo_firmadopor_buscar(Connection cnx,String ls_codigo_tipo,String ls_firmadopor,String ls_oficina) throws Exception;
	public abstract Collection reportes_procedencia_buscar(Connection cnx,String ls_procedencia,String ls_oficina) throws Exception;
	public abstract Collection reportes_procedencia_firmadopor_buscar(Connection cnx,String ls_procedencia,String ls_firmadopor,String ls_oficina) throws Exception;
	public abstract Collection reportes_firmadopor_buscar(Connection cnx,String ls_firmadopor,String ls_oficina) throws Exception;
	public abstract Collection reportes_codtipo_procedencia_firmadopor_buscar(Connection cnx,String ls_codigo_tipo,String ls_procedencia,String ls_firmadopor,String ls_oficina) throws Exception;
	
	public abstract Collection reportes_criteriousuario_buscar(Connection cnx,String ls_codigo_oficina,String usuario,String fecha,String codigo_oficina,String estado,String codigo_tipo,String procedencia,String firmadopor,String fecha_rpta,String numero_referencia
)  throws Exception;
	/********************************Fin Reportes*************************/
	
	public abstract void of_modificar_documento(Connection cnx,String codigo_documento,int codigo_expediente,
			String secuencia_movimiento,String codigo_oficina,String medio,String codigo_tipo,
			String ls_seleccion,String destino_documento,String origen_documento,String destinatario,
			String codigo_solicitud,String numero_documento,String fecha_registro,
			String hora,String folios_documento,String asunto_documento,String observa_documento,
			String usuario_actual,String firmadopor,String desc_origen,
			String tipo_persona,String persona,String fecha_rpta,int correlativorecepcion,
			String numero_referencia,String dirigido)throws Exception;
	
	public abstract void of_eliminar_documento(Connection cnx,String codigo_documento,String oficina,String secuencia)throws Exception;
	public abstract Collection of_lista_frame_documentos(Connection cnx,String oficina) throws Exception;
	public abstract Collection of_lista_frame_historial(Connection cnx,String docinicia,String secuencia,String numero_referencia) throws Exception;
	public abstract void of_modificar_usuarioclave(Connection cnx,String usuario,String clave,String clavenueva,String confirmarclavenueva)throws Exception;
	public abstract Collection lista_recepcion_documentos_normal(Connection cnx,String oficina,String accesoaoficinas,String inicia) throws Exception;

	
	public abstract Collection lista_upload_documentos(Connection cnx,int codigo_documento) throws Exception;
	public abstract Collection lista_upload_documentos_detalle(Connection cnx,int codigo_documento) throws Exception;
	
	public abstract Collection lista_conexion(Connection cnx,String fecha_in,String fecha_fin ) throws Exception;
	
	
	public abstract void DerivarDocumentoDetUploadIng(int codigo_documento,int ls_codigo_expediente ,int ls_secuencia_movimiento, String nombre_archivo, String ruta_archivo,
			 String flag,String tipo,String usuario_creacion,String vOf) throws Exception;
	public abstract void DerivarDocumentoDetUploadIng(int codigo_documento,int codigo_expediente,int secuencia_movimiento,String nombre_archivo, String ruta_archivo,
			 String flag,String tipo,String estado);
	public abstract void DerivarDocumentoCabUploadIng(String codigo_oficina,int correlativo,int codigo_documento,int codigo_expediente ,int secuencia_movimiento ,String usuario_creacion,int codigo_recepcion);
	public abstract void EliminarDocumentoDetUpload(Connection cnx,int id_det_upload) throws Exception;
	public abstract Collection lista_upload_documentos_doc_entrada(Connection cnx,int id_cab_upload) throws Exception;

	public abstract void CrearCabDocEntradaUploadIng(String codigo_oficina, String codigo_documento,String numero_expediente,
			String numero_secuencia,String correlativorecepcion,String usuario_creacion) throws Exception;
	public abstract void CrearDocumentoDocEntradaDetUploadIng(int codigo_documento,String nombre_archivo, String ruta_archivo,
			 String flag,String tipo,String usuario_creacion,String vOf) throws Exception;
	public abstract void CrearDocumentoDocEntradaDetUploadIng(int codigo_documento,BArchivo archivo,int codigo_expediente,int secuencia_movimiento,
			 String flag,String tipo, String vOf) throws Exception;
	public abstract void CrearDocumentoDocEntradaDetUploadIngFirmaManual(int codigo_documento,String nombre_archivo, String ruta_archivo,
			 String flag,String tipo,String usuario_creacion,String vOf) throws Exception;
	public abstract void ActualizaDocumentoDocEntradaDetUploadIng(int codigo_documento,
			 String usuario_modificacion,int id_detalle_upload)throws Exception;
	
	public abstract String ValidaListaDocEntradaDetUpload(String codigo_documento) throws Exception;
	public abstract int ValidaListaDocEntradaDetUpload(String codigo_documento, String secuencia_movimiento) throws Exception;
	
	 public BMesaPartes  of_detalle_documento_hoja_envio(Connection cnx,String codigo_documento,String codigo_recepcion,String secuencia_documento) throws Exception;
	 
	 public abstract Collection of_busca_documentos_registrados(Connection cnx,String oficina,String codigo_documento,
			 String codigo_expediente,String ls_codigo_oficina) throws Exception;
	 public BMesaPartes  of_consulta_documento(Connection cnx,String codigo_documento,String secuencia_documento) throws Exception;
	 public abstract Collection lista_upload_documentos_doc_entrada(Connection cnx,int codigo_documento,int secuencia_movimiento) throws Exception;
	 public abstract void of_eliminar_documento_upload(Connection cnx,String id_det_upload,String codigo_documento) throws Exception;
	 public BMesaPartes  of_detalle_documento_hoja_ruta(Connection cnx,String codigo_documento) throws Exception;
	 
	 public abstract Collection lista_recepcion_documentos_normal(Connection cnx,String oficina,String accesoaoficinas,String persona,String inicia) throws Exception;
	 
	 public abstract Collection of_busca_documentos_report(Connection cnx,String oficina,String procedencia,
			 String sle_fecha,String medio,String codigo_tipo,String codigo_oficina,String codigo_solicitud,
			 String codigo_documento,String codigo_expediente,String firmadopor,String dirigido,String estado,String numero_documento,
			 String doc_resp,String asunto_documento,String sle_fecha_inicio,String sle_fecha_fin,String codigo_motivo) throws Exception;
	 
	 public abstract Collection of_busca_documentos_report_general(Connection cnx,String oficina,String procedencia,
			 String sle_fecha,String medio,String codigo_tipo,String codigo_oficina,String codigo_solicitud,
			 String codigo_documento,String codigo_expediente,String firmadopor,String dirigido,String estado,
			 String numero_documento,String doc_resp,String asunto_documento
			 ,String sle_fecha_inicio,String sle_fecha_fin,String codigo_motivo) throws Exception;	 
	 
	 public abstract Collection lista_documentos_modificar_deriva(Connection cnx,String oficina, String codigo_doc) throws Exception;
	 public abstract String of_modificar_doc_deriva(Connection cnx,String codigo_oficina,String codigo_documento,String usuario,
			 String fecha,String hora,String observacion,String estado_movimiento,String secuencia_movimiento) throws Exception;
	 
	 public abstract String of_observar_doc_deriva(Connection cnx,String codigo_oficina,String codigo_documento,String usuario,
			 String fecha,String hora,String observacion,String estado_movimiento,String secuencia_movimiento) throws Exception;
	 
	 public abstract String of_ingresar_doc_observardo(Connection cnx,String codigo_documento,String secuencia_movimiento,String secuencia_movimiento_destino,
			 String observacion,String codigo_motivo,String doc_resp,String fecha_rpta_rq, int codigo_recepcion_destino,int id_cab_upload)throws Exception;
	 
	 public abstract String of_ingresar_documento_asociado_cod_documento(Connection cnx,String codigo_oficina,String medio,String codigo_tipo,String naturaleza_documento,String destino_documento,
				String contacto,String destinatario,String codigo_solicitud,String numero_documento,
				String fecha_registro,String hora,String folios_documento,String asunto_documento,
				String observa_documento,String usuario_actual,String firmadopor,String origen_documento,
				String desc_origen,String tipo,String persona,String fecha_rpta,String correlativorecepcion,String numero_referencia,String dirigido)throws Exception;
	 
	 
	 public abstract Collection of_lista_oficinas_copia(Connection cnx,String codigo_documento) throws Exception;
	 public BPersona  of_detalle_codigo_persona_oficina(Connection cnx,String codigo_documento) throws Exception;
	 public abstract Collection lista_documentos_modificar_deriva_pendientes(Connection cnx,String codigo_documento
			 ,String secuencia_documento) throws Exception;
	 
	 public abstract void of_eliminar_documento_copia(Connection cnx,String codigo_documento,String secuencia) throws Exception;
	 public abstract Collection  reportes_criterio_buscar(Connection cnx,String naturaleza,String tipodoc
			 ,String estado,String usuario,String sle_fecha,String codigo_oficina,
			 String estado_criterio_usuario,String codigo_tipo,String procedencia,String firmadopor,
			 String estado_criterio,String fecha_rpta,String numero_referencia) throws Exception;
	public abstract FFormMantRecepcion obtenerDetalleBandejaRecepcion(Connection cnx,
			String codigo_documento, String secuencia_movimiento);
	public abstract FFormMantDerivar obtenerDetalleBandejaDerivacion(Connection cnx,
			String codigo_documento, String secuencia_movimiento, String secuencia_origen, String tipo_busqueda);
	public abstract FFormMantArchivar obtenerDetalleBandejaArchivo(
			Connection cnx, String codigo_documento, String secuencia_movimiento);
	public abstract Collection of_lista_oficinas_jefes(Connection cnx,
			String codigoOficinaDelUSuario)throws Exception;
	
	 public abstract Collection of_busca_documentos_especial_xls(Connection cnx,String oficina,String procedencia,
			 String sle_fecha,String medio,String codigo_tipo,String codigo_oficina,String codigo_solicitud,
			 String codigo_documento,String codigo_expediente,String firmadopor,
			 String dirigido,String estado,String numero_documento,String doc_resp,String asunto_documento,
			 String sle_fecha_recepcion,String sle_fecha_derivacion,String codigo_motivo,String flag, String observacion) throws Exception;

	public abstract String obtenerNombreArchivo(Connection cnx,
			String ls_id_det_upload, String ls_codigo_documento);
	public abstract HashMap<String, String> obtenerDatosUsuario(String destinatario);
	public abstract ArrayList<String> obtenerDatosUsuario(
			ArrayList<String> myListaCodigoPersonal);
	
	public abstract String of_es_padre(String ls_codigo_oficina) throws Exception;
	
	public abstract String codigo_oficina(String ls_codigo_oficina) throws Exception;
//	public abstract Collection of_lista_oficinas();
	//public abstract int obtener_sede(String )  throws Exception;
	//////AUDITORIA
	public abstract void registrar_conexion(String usuario) throws Exception;
	
	public abstract void actualizar_detalle_documento(String id_detalle) throws Exception; 
}
