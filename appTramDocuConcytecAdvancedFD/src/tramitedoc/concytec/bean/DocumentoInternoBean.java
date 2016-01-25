package tramitedoc.concytec.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class DocumentoInternoBean implements Serializable{
	private static final long serialVersionUID = 4721367472243796626L;
	private Integer codigo_tipo_documento_interno;
	private Integer codigo_documento_interno ;	
	private Integer codigo_documento;
	private Integer codigo_oficina;
	private Integer codigo_estado_documento;
	private String  asunto;
	private String  descripcion;
	private String  dirigido_a;
	private String  referencia;
	private String  firmado_por;
	private Date  fecha_creacion;
	private Date  fecha_modificacion;
	private Integer personas;
	private String nombre_doc_adjuntos;
	private Integer codigo_documento_interno_busqueda;
	//private Integer id_area_pertenece;
	private Integer codigo_oficina_pertenece;//el documento
	private Integer numero_doc ; //numero de documento MEMO Nro 121	
	private Integer anio_doc;  
	private String nombreSelloOficina;
	private String abreviatura_grado_profesion;
	private boolean checked;
	private String fecha_inicio;
	private String fecha_fin;
	
	private String motivo;
	private String actividad;
	
	private Integer numerofilas; //creado para el ccp
	private String meta; 
	private String especifica;
	private String valor;
	private String observacion;
		
	private String nota;
	
	private String objetivo;
	private String justificacion;
	private String finalidad;
	private String entregable;
	private String plazo;
	private String financiamiento;
	private String modalidad;
	private String responsable_coordinacion;
	private String responsable_conformidad;
	
	private String nombre_arhivo;
	private String nombre_oficial_anio;
	private String cargo_persona_destinatario;	
	
	private String link;
	private String link2;
	private String link3;
	private String verPDF;
	
	
	private String[] copia_oficinas_destino={};
	private String codigos_oficinas_destinos_copias;
	private String radiobuttons = "1";
	
	private Integer codigo_expediente;
	private Integer codigo_recepcion;
	private Integer secuencia_movimiento;
	private String tipo_envio = "N";
	private Integer codigo_tipo_requerimiento;
	private boolean para_firma_previa;
	
	private String[] firmantes_destino={};
	private String codigos_firmantes_destino;
	
	
	private String[] visto_bueno_destino={};
	private String codigos_visto_bueno_destino;
	
		
	public Integer getCodigo_tipo_documento_interno() {
		return codigo_tipo_documento_interno;
	}
	public void setCodigo_tipo_documento_interno(
			Integer codigo_tipo_documento_interno) {
		this.codigo_tipo_documento_interno = codigo_tipo_documento_interno;
	}
	
	
	public Integer getCodigo_documento() {
		return codigo_documento;
	}
	public void setCodigo_documento(Integer codigo_documento) {
		this.codigo_documento = codigo_documento;
	}
	public Integer getCodigo_estado_documento() {
		return codigo_estado_documento;
	}
	public void setCodigo_estado_documento(Integer codigo_estado_documento) {
		this.codigo_estado_documento = codigo_estado_documento;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDirigido_a() {
		return dirigido_a;
	}
	public void setDirigido_a(String dirigido_a) {
		this.dirigido_a = dirigido_a;
	}
	public String getFirmado_por() {
		return firmado_por;
	}
	public void setFirmado_por(String firmado_por) {
		this.firmado_por = firmado_por;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	/**
	 * 
	 */
	public Integer getCodigo_oficina() {
		return codigo_oficina;
	}
	public void setCodigo_oficina(Integer codigo_oficina) {
		this.codigo_oficina = codigo_oficina;
	}
	public Integer getPersonas() {
		return personas;
	}
	public void setPersonas(Integer personas) {
		this.personas = personas;
	}
	public Integer getCodigo_oficina_pertenece() {
		return codigo_oficina_pertenece;
	}
	public void setCodigo_oficina_pertenece(Integer codigo_oficina_pertenece) {
		this.codigo_oficina_pertenece = codigo_oficina_pertenece;
	}
	
	public Integer getNumero_doc() {
		return numero_doc;
	}
	public void setNumero_doc(Integer numero_doc) {
		this.numero_doc = numero_doc;
	}
	public Integer getAnio_doc() {
		return anio_doc;
	}
	public void setAnio_doc(Integer anio_doc) {
		this.anio_doc = anio_doc;
	}
	public void setNombreSelloOficina(String nombreSelloOficina) {
		this.nombreSelloOficina = nombreSelloOficina;
	}
	public String getNombreSelloOficina() {
		return nombreSelloOficina;
	}
	public void setNombre_oficial_anio(String nombre_oficial_anio) {
		this.nombre_oficial_anio = nombre_oficial_anio;
	}
	public String getNombre_oficial_anio() {
		return nombre_oficial_anio;
	}
	public void setCopia_oficinas_destino(String stringMultibox[]) {
		this.copia_oficinas_destino = stringMultibox;
	}
	public String[] getCopia_oficinas_destino() {
		return this.copia_oficinas_destino;
	}
	public void setCargo_persona_destinatario(String cargo_persona_destinatario) {
		this.cargo_persona_destinatario = cargo_persona_destinatario;
		
	}
	public String getCargo_persona_destinatario() {
		return cargo_persona_destinatario;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setNombre_doc_adjuntos(String nombre_doc_adjuntos) {
		this.nombre_doc_adjuntos = nombre_doc_adjuntos;
	}
	public String getNombre_doc_adjuntos() {
		return nombre_doc_adjuntos;
	}
	public void setCodigo_documento_interno(Integer codigo_documento_interno) {
		this.codigo_documento_interno = codigo_documento_interno;
	}
	public Integer getCodigo_documento_interno() {
		return codigo_documento_interno;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLink() {
		return link;
	}
	/*public String getCodigos_oficinas_destino_copias() {
		// TODO Auto-generated method stub
		return null;
	}*/
	public void setCodigos_oficinas_destinos_copias(
			String codigos_oficinas_destinos_copias) {
		this.codigos_oficinas_destinos_copias = codigos_oficinas_destinos_copias;
	}
	public String getCodigos_oficinas_destinos_copias() {
		return codigos_oficinas_destinos_copias;
	}
	public void setAbreviatura_grado_profesion(
			String abreviatura_grado_profesion) {
		this.abreviatura_grado_profesion = abreviatura_grado_profesion;
	}
	public String getAbreviatura_grado_profesion() {
		return abreviatura_grado_profesion;
	}
	public void setVerPDF(String verPDF) {
		this.verPDF = verPDF;
	}
	public String getVerPDF() {
		return verPDF;
	}
	public void setNombre_arhivo(String nombre_arhivo) {
		this.nombre_arhivo = nombre_arhivo;
	}
	public String getNombre_arhivo() {
		return nombre_arhivo;
	}
	public void setCodigo_documento_interno_busqueda(
			Integer codigo_documento_interno_busqueda) {
		this.codigo_documento_interno_busqueda = codigo_documento_interno_busqueda;
	}
	public Integer getCodigo_documento_interno_busqueda() {
		return codigo_documento_interno_busqueda;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setRadiobuttons(String radiobuttons) {
		this.radiobuttons = radiobuttons;
	}
	public String getRadiobuttons() {
		return radiobuttons;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getActividad() {
		return actividad;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public String getMeta() {
		return meta;
	}
	
	public void setEspecifica(String especifica) {
		this.especifica = especifica;
	}
	public String getEspecifica() {
		return especifica;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getValor() {
		return valor;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getNota() {
		return nota;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	public String getJustificacion() {
		return justificacion;
	}
	public void setFinalidad(String finalidad) {
		this.finalidad = finalidad;
	}
	public String getFinalidad() {
		return finalidad;
	}
	public void setEntregable(String entregable) {
		this.entregable = entregable;
	}
	public String getEntregable() {
		return entregable;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setFinanciamiento(String financiamiento) {
		this.financiamiento = financiamiento;
	}
	public String getFinanciamiento() {
		return financiamiento;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setResponsable_coordinacion(String responsable_coordinacion) {
		this.responsable_coordinacion = responsable_coordinacion;
	}
	public String getResponsable_coordinacion() {
		return responsable_coordinacion;
	}
	public void setResponsable_conformidad(String responsable_conformidad) {
		this.responsable_conformidad = responsable_conformidad;
	}
	public String getResponsable_conformidad() {
		return responsable_conformidad;
	}
	
	public void setNumerofilas(Integer numerofilas) {
		this.numerofilas = numerofilas;
	}
	public Integer getNumerofilas() {
		return numerofilas;
	}
	
	public Integer getCodigo_expediente() {
		return codigo_expediente;
	}
	public void setCodigo_expediente(
			Integer codigo_expediente) {
		this.codigo_expediente = codigo_expediente;
	}
	
	public Integer getCodigo_recepcion() {
		return codigo_recepcion;
	}
	public void setCodigo_recepcion(
			Integer codigo_recepcion) {
		this.codigo_recepcion = codigo_recepcion;
	}
	
	public Integer getSecuencia_movimiento() {
		return secuencia_movimiento;
	}
	public void setSecuencia_movimiento(
			Integer secuencia_movimiento) {
		this.secuencia_movimiento = secuencia_movimiento;
	}
	public void setTipo_envio(String tipo_envio) {
		this.tipo_envio = tipo_envio;
	}
	public String getTipo_envio() {
		return tipo_envio;
	}
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}
	public void setLink2(String link2) {
		this.link2 = link2;
	}
	public String getLink2() {
		return link2;
	}
	public void setCodigo_tipo_requerimiento(Integer codigo_tipo_requerimiento) {
		this.codigo_tipo_requerimiento = codigo_tipo_requerimiento;
	}
	public Integer getCodigo_tipo_requerimiento() {
		return codigo_tipo_requerimiento;
	}
	public String getLink3() {
		return link3;
	}
	public void setLink3(String link3) {
		this.link3 = link3;
	}
	public void setPara_firma_previa(boolean para_firma_previa) {
		this.para_firma_previa = para_firma_previa;
	}
	public boolean isPara_firma_previa() {
		return para_firma_previa;
	}
	
	
	public void setFirmantes_destino(String[] firmantes_destino) {
		this.firmantes_destino = firmantes_destino;
	}
	public String[] getFirmantes_destino() {
		return firmantes_destino;
	}
	public void setVisto_bueno_destino(String stringMultiboxdos[]) {
		this.visto_bueno_destino = stringMultiboxdos;
	}
	public String[] getVisto_bueno_destino() {
		return visto_bueno_destino;
	}
	public void setCodigos_firmantes_destino(String codigos_firmantes_destino) {
		this.codigos_firmantes_destino = codigos_firmantes_destino;
	}
	public String getCodigos_firmantes_destino() {
		return codigos_firmantes_destino;
	}
	public void setCodigos_visto_bueno_destino(
			String codigos_visto_bueno_destino) {
		this.codigos_visto_bueno_destino = codigos_visto_bueno_destino;
	}
	public String getCodigos_visto_bueno_destino() {
		return codigos_visto_bueno_destino;
	}
	
	
	
}
