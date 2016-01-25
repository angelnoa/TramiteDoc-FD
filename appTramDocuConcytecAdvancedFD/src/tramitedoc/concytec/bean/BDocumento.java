/*
 * Created on 23/07/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tramitedoc.concytec.bean;
import java.util.Collection;

import tramitedoc.concytec.util.Bean;
/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BDocumento extends Bean{
	
	private String codigo_documento;
	private String descripcion_oficina;
	private String descripcion_tipo;
	private String codigo_expediente;
	private String numero_documento;
	private String naturaleza_documento;
	private String fecha_documento;
	private String codigo_oficina;
	private String codigo_institucion;
	private String descripcion_institucion;
	private String siglas_institucion;
	private String siglas_oficina;
	private String fecha_movimiento;
	private String hora_movimiento;
	private String estado_movimiento;
	private String descripcion_motivo;
	private String asunto_documento;
	private String folios_documento;
	private String observa_documento;
	private String descripcion_serie;
	private String descripcion_expediente;
	private String fecha_registro;
	private String hora_registro;
	private String ultima_modificacion;
	private String codigo_inicia;
	private String secuencia_movimiento;
	private String ultimo_usuario;
	private String es_multiple;
	private String nombre_contacto;
	private String nombre_personal;
	private String observa_movimiento;
	private String doc_deriva;
	private String codigo_motivo;
	private String nombre_archivo;
	private int contador=0;
	private int id_det_upload=0;
	private boolean seleccionado;
	private Collection archivos;
	private String procedencia;
	private String remitente;
	private String ruta_archivo;
	
	
	
	/**
	 * @return the remitente
	 */
	public String getRemitente() {
		return remitente;
	}
	/**
	 * @param remitente the remitente to set
	 */
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getProcedencia() {
		return procedencia;
	}
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	public String getCodigo_expediente() {
		return codigo_expediente;
	}
	public void setCodigo_expediente(String codigo_expediente) {
		this.codigo_expediente = codigo_expediente;
	}
	/**
	 * @return the archivos
	 */
	public Collection getArchivos() {
		return archivos;
	}
	/**
	 * @param archivos the archivos to set
	 */
	public void setArchivos(Collection archivos) {
		this.archivos = archivos;
	}
	/**
	 * @return the seleccionado
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}
	/**
	 * @param seleccionado the seleccionado to set
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	public int getId_det_upload() {
		return id_det_upload;
	}
	public void setId_det_upload(int id_det_upload) {
		this.id_det_upload = id_det_upload;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	public String getNombre_archivo() {
		return nombre_archivo;
	}
	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}
	public String getAsunto_documento() {
		return asunto_documento;
	}
	public void setAsunto_documento(String asunto_documento) {
		this.asunto_documento = asunto_documento;
	}
	public String getCodigo_documento() {
		return codigo_documento;
	}
	public void setCodigo_documento(String codigo_documento) {
		this.codigo_documento = codigo_documento;
	}
	public String getCodigo_inicia() {
		return codigo_inicia;
	}
	public void setCodigo_inicia(String codigo_inicia) {
		this.codigo_inicia = codigo_inicia;
	}
	public String getCodigo_institucion() {
		return codigo_institucion;
	}
	public void setCodigo_institucion(String codigo_institucion) {
		this.codigo_institucion = codigo_institucion;
	}
	public String getCodigo_motivo() {
		return codigo_motivo;
	}
	public void setCodigo_motivo(String codigo_motivo) {
		this.codigo_motivo = codigo_motivo;
	}
	public String getCodigo_oficina() {
		return codigo_oficina;
	}
	public void setCodigo_oficina(String codigo_oficina) {
		this.codigo_oficina = codigo_oficina;
	}
	public String getDescripcion_expediente() {
		return descripcion_expediente;
	}
	public void setDescripcion_expediente(String descripcion_expediente) {
		this.descripcion_expediente = descripcion_expediente;
	}
	public String getDescripcion_institucion() {
		return descripcion_institucion;
	}
	public void setDescripcion_institucion(String descripcion_institucion) {
		this.descripcion_institucion = descripcion_institucion;
	}
	public String getDescripcion_motivo() {
		return descripcion_motivo;
	}
	public void setDescripcion_motivo(String descripcion_motivo) {
		this.descripcion_motivo = descripcion_motivo;
	}
	public String getDescripcion_oficina() {
		return descripcion_oficina;
	}
	public void setDescripcion_oficina(String descripcion_oficina) {
		this.descripcion_oficina = descripcion_oficina;
	}
	public String getDescripcion_serie() {
		return descripcion_serie;
	}
	public void setDescripcion_serie(String descripcion_serie) {
		this.descripcion_serie = descripcion_serie;
	}
	public String getDescripcion_tipo() {
		return descripcion_tipo;
	}
	public void setDescripcion_tipo(String descripcion_tipo) {
		this.descripcion_tipo = descripcion_tipo;
	}
	public String getDoc_deriva() {
		return doc_deriva;
	}
	public void setDoc_deriva(String doc_deriva) {
		this.doc_deriva = doc_deriva;
	}
	public String getEs_multiple() {
		return es_multiple;
	}
	public void setEs_multiple(String es_multiple) {
		this.es_multiple = es_multiple;
	}
	public String getEstado_movimiento() {
		return estado_movimiento;
	}
	public void setEstado_movimiento(String estado_movimiento) {
		this.estado_movimiento = estado_movimiento;
	}
	public String getFecha_documento() {
		return fecha_documento;
	}
	public void setFecha_documento(String fecha_documento) {
		this.fecha_documento = fecha_documento;
	}
	public String getFecha_movimiento() {
		return fecha_movimiento;
	}
	public void setFecha_movimiento(String fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getFolios_documento() {
		return folios_documento;
	}
	public void setFolios_documento(String folios_documento) {
		this.folios_documento = folios_documento;
	}
	public String getHora_movimiento() {
		return hora_movimiento;
	}
	public void setHora_movimiento(String hora_movimiento) {
		this.hora_movimiento = hora_movimiento;
	}
	public String getHora_registro() {
		return hora_registro;
	}
	public void setHora_registro(String hora_registro) {
		this.hora_registro = hora_registro;
	}
	public String getNaturaleza_documento() {
		return naturaleza_documento;
	}
	public void setNaturaleza_documento(String naturaleza_documento) {
		this.naturaleza_documento = naturaleza_documento;
	}
	public String getNombre_contacto() {
		return nombre_contacto;
	}
	public void setNombre_contacto(String nombre_contacto) {
		this.nombre_contacto = nombre_contacto;
	}
	public String getNombre_personal() {
		return nombre_personal;
	}
	public void setNombre_personal(String nombre_personal) {
		this.nombre_personal = nombre_personal;
	}
	public String getNumero_documento() {
		return numero_documento;
	}
	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}
	public String getObserva_documento() {
		return observa_documento;
	}
	public void setObserva_documento(String observa_documento) {
		this.observa_documento = observa_documento;
	}
	public String getObserva_movimiento() {
		return observa_movimiento;
	}
	public void setObserva_movimiento(String observa_movimiento) {
		this.observa_movimiento = observa_movimiento;
	}
	public String getSecuencia_movimiento() {
		return secuencia_movimiento;
	}
	public void setSecuencia_movimiento(String secuencia_movimiento) {
		this.secuencia_movimiento = secuencia_movimiento;
	}
	public String getSiglas_institucion() {
		return siglas_institucion;
	}
	public void setSiglas_institucion(String siglas_institucion) {
		this.siglas_institucion = siglas_institucion;
	}
	public String getSiglas_oficina() {
		return siglas_oficina;
	}
	public void setSiglas_oficina(String siglas_oficina) {
		this.siglas_oficina = siglas_oficina;
	}
	public String getUltima_modificacion() {
		return ultima_modificacion;
	}
	public void setUltima_modificacion(String ultima_modificacion) {
		this.ultima_modificacion = ultima_modificacion;
	}
	public String getUltimo_usuario() {
		return ultimo_usuario;
	}
	public void setUltimo_usuario(String ultimo_usuario) {
		this.ultimo_usuario = ultimo_usuario;
	}
	
	public void setRuta_archivo(String ruta_archivo) {
		this.ruta_archivo = ruta_archivo;
	}
	public String getRuta_archivo() {
		return ruta_archivo;
	}
	
			}
