/*
 * Created on 23/07/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tramitedoc.concytec.bean;
import java.sql.Timestamp;
import java.util.Date;

import tramitedoc.concytec.util.Bean;
/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BMesaPartes extends Bean{
	
	int contador=0;
	private String codigo_documento;
	private String fecha_movimiento;
	private String hora_movimiento;
	private String estado_movimiento;
	private String codigo_inicia;
	
	private String secuencia_movimiento;
	private String ultimo_usuario;
	
	private String doc_deriva;
	private String observa_movimiento;
	private String codigo_motivo;
	private String descripcion_tipo;
	private String numero_documento;
	private String naturaleza_documento;
	private String asunto_documento;
	private String origen_documento;
	private String destino_documento;
	
	private String desc_origen;
	private String tipo;
	private String remitente;
	private String destinatario;
	private String codigo_oficina;
	private String descripcion_oficina;
	private String codigo_personal;
	private String nombre_personal;
	
	private String oficina_deriva;
	private String descripcion_motivo;
	
	private String fecha_registro;
	private String hora_registro;
	private String folios_documento;
	private String observa_documento;
	private String descripcion_destino;
	private String numero_documento_ant;
	private String fecha_rpta;
	private String fecha_sistema;
	private String codigo_expediente;
	private String oficina_origen;
	private String correlativo_deriva;
	private String codigo_recepcion;
	private String numero_referencia;
	private String nombre_motivo;
	private String nombre_oficina_origen;
	private String nombre_oficina_destino;
	private String codigo_tipo;
	
	private String medio;
	private String codigo_solicitud;
	private String dirigido;
	private String firmadopor;
	private String nombre_solicitud;
	
	private Date fecha_envio;
	private Date fecha_recepcion;
	private Date fecha_derivacion;
	
	private String tiempo_bandeja;
	private String tipo_envio;
	
	private int anio;
	
	
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	/**
	 * @return the fecha_derivacion
	 */
	public Date getFecha_derivacion() {
		return fecha_derivacion;
	}
	/**
	 * @param fecha_derivacion the fecha_derivacion to set
	 */
	public void setFecha_derivacion(Date fecha_derivacion) {
		this.fecha_derivacion = fecha_derivacion;
	}
	/**
	 * @return the fecha_recepcion
	 */
	public Date getFecha_recepcion() {
		return fecha_recepcion;
	}
	/**
	 * @param fecha_recepcion the fecha_recepcion to set
	 */
	public void setFecha_recepcion(Date fecha_recepcion) {
		this.fecha_recepcion = fecha_recepcion;
	}
	/**
	 * @return the tiempo_bandeja
	 */
	public String getTiempo_bandeja() {
		return tiempo_bandeja;
	}
	/**
	 * @param tiempo_bandeja the tiempo_bandeja to set
	 */
	public void setTiempo_bandeja(String tiempo_bandeja) {
		this.tiempo_bandeja = tiempo_bandeja;
	}
	public String getNombre_solicitud() {
		return nombre_solicitud;
	}
	public void setNombre_solicitud(String nombre_solicitud) {
		this.nombre_solicitud = nombre_solicitud;
	}
	public String getFirmadopor() {
		return firmadopor;
	}
	public void setFirmadopor(String firmadopor) {
		this.firmadopor = firmadopor;
	}
	public String getDirigido() {
		return dirigido;
	}
	public void setDirigido(String dirigido) {
		this.dirigido = dirigido;
	}
	public String getCodigo_solicitud() {
		return codigo_solicitud;
	}
	public void setCodigo_solicitud(String codigo_solicitud) {
		this.codigo_solicitud = codigo_solicitud;
	}
	public String getMedio() {
		return medio;
	}
	public void setMedio(String medio) {
		this.medio = medio;
	}
	public String getCodigo_tipo() {
		return codigo_tipo;
	}
	public void setCodigo_tipo(String codigo_tipo) {
		this.codigo_tipo = codigo_tipo;
	}
	public String getNombre_oficina_destino() {
		return nombre_oficina_destino;
	}
	public void setNombre_oficina_destino(String nombre_oficina_destino) {
		this.nombre_oficina_destino = nombre_oficina_destino;
	}
	public String getNombre_oficina_origen() {
		return nombre_oficina_origen;
	}
	public void setNombre_oficina_origen(String nombre_oficina_origen) {
		this.nombre_oficina_origen = nombre_oficina_origen;
	}
	public String getNombre_motivo() {
		return nombre_motivo;
	}
	public void setNombre_motivo(String nombre_motivo) {
		this.nombre_motivo = nombre_motivo;
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
	public String getCodigo_personal() {
		return codigo_personal;
	}
	public void setCodigo_personal(String codigo_personal) {
		this.codigo_personal = codigo_personal;
	}
	public String getDesc_origen() {
		return desc_origen;
	}
	public void setDesc_origen(String desc_origen) {
		this.desc_origen = desc_origen;
	}
	public String getDescripcion_oficina() {
		return descripcion_oficina;
	}
	public void setDescripcion_oficina(String descripcion_oficina) {
		this.descripcion_oficina = descripcion_oficina;
	}
	public String getDescripcion_tipo() {
		return descripcion_tipo;
	}
	public void setDescripcion_tipo(String descripcion_tipo) {
		this.descripcion_tipo = descripcion_tipo;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getDestino_documento() {
		return destino_documento;
	}
	public void setDestino_documento(String destino_documento) {
		this.destino_documento = destino_documento;
	}
	public String getDoc_deriva() {
		return doc_deriva;
	}
	public void setDoc_deriva(String doc_deriva) {
		this.doc_deriva = doc_deriva;
	}
	public String getEstado_movimiento() {
		return estado_movimiento;
	}
	public void setEstado_movimiento(String estado_movimiento) {
		this.estado_movimiento = estado_movimiento;
	}
	public String getFecha_movimiento() {
		return fecha_movimiento;
	}
	public void setFecha_movimiento(String fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}
	public String getHora_movimiento() {
		return hora_movimiento;
	}
	public void setHora_movimiento(String hora_movimiento) {
		this.hora_movimiento = hora_movimiento;
	}
	public String getNaturaleza_documento() {
		return naturaleza_documento;
	}
	public void setNaturaleza_documento(String naturaleza_documento) {
		this.naturaleza_documento = naturaleza_documento;
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
	public String getObserva_movimiento() {
		return observa_movimiento;
	}
	public void setObserva_movimiento(String observa_movimiento) {
		this.observa_movimiento = observa_movimiento;
	}
	public String getOrigen_documento() {
		return origen_documento;
	}
	public void setOrigen_documento(String origen_documento) {
		this.origen_documento = origen_documento;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getSecuencia_movimiento() {
		return secuencia_movimiento;
	}
	public void setSecuencia_movimiento(String secuencia_movimiento) {
		this.secuencia_movimiento = secuencia_movimiento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getUltimo_usuario() {
		return ultimo_usuario;
	}
	public void setUltimo_usuario(String ultimo_usuario) {
		this.ultimo_usuario = ultimo_usuario;
	}
	public String getDescripcion_motivo() {
		return descripcion_motivo;
	}
	public void setDescripcion_motivo(String descripcion_motivo) {
		this.descripcion_motivo = descripcion_motivo;
	}
	public String getOficina_deriva() {
		return oficina_deriva;
	}
	public void setOficina_deriva(String oficina_deriva) {
		this.oficina_deriva = oficina_deriva;
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
	public String getHora_registro() {
		return hora_registro;
	}
	public void setHora_registro(String hora_registro) {
		this.hora_registro = hora_registro;
	}
	public String getObserva_documento() {
		return observa_documento;
	}
	public void setObserva_documento(String observa_documento) {
		this.observa_documento = observa_documento;
	}
	public String getDescripcion_destino() {
		return descripcion_destino;
	}
	public void setDescripcion_destino(String descripcion_destino) {
		this.descripcion_destino = descripcion_destino;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	public String getNumero_documento_ant() {
		return numero_documento_ant;
	}
	public void setNumero_documento_ant(String numero_documento_ant) {
		this.numero_documento_ant = numero_documento_ant;
	}
	public String getFecha_rpta() {
		return fecha_rpta;
	}
	public void setFecha_rpta(String fecha_rpta) {
		this.fecha_rpta = fecha_rpta;
	}
	public String getFecha_sistema() {
		return fecha_sistema;
	}
	public void setFecha_sistema(String fecha_sistema) {
		this.fecha_sistema = fecha_sistema;
	}
	public String getCodigo_expediente() {
		return codigo_expediente;
	}
	public void setCodigo_expediente(String codigo_expediente) {
		this.codigo_expediente = codigo_expediente;
	}
	public String getOficina_origen() {
		return oficina_origen;
	}
	public void setOficina_origen(String oficina_origen) {
		this.oficina_origen = oficina_origen;
	}
	public String getCorrelativo_deriva() {
		return correlativo_deriva;
	}
	public void setCorrelativo_deriva(String correlativo_deriva) {
		this.correlativo_deriva = correlativo_deriva;
	}
	public String getCodigo_recepcion() {
		return codigo_recepcion;
	}
	public void setCodigo_recepcion(String codigo_recepcion) {
		this.codigo_recepcion = codigo_recepcion;
	}
	public String getNumero_referencia() {
		return numero_referencia;
	}
	public void setNumero_referencia(String numero_referencia) {
		this.numero_referencia = numero_referencia;
	}
	public void setTiempo_bandeja_automatico() {
		// TODO Auto-generated method stub
		
		Date fecha_recepcion_;
		Date fecha_derivacion_;
		if(fecha_recepcion!=null){
			if(fecha_derivacion==null){
				fecha_derivacion_=new Date();
			}else{
				fecha_derivacion_	=fecha_derivacion;
			}
			
			long fd=fecha_recepcion.getTime();		
			long fr=fecha_derivacion_.getTime();
			
			long tiempo =fr-fd;
			int dias = (int)Math.floor(tiempo / (1000 * 60 * 60 * 24));
			long dif= tiempo%(1000 * 60 * 60 * 24);
			int horas = (int)Math.floor(dif/(1000 * 60 * 60) );
			long dif2 = dif%(1000 * 60 * 60);
			int minutos = (int)Math.floor(dif2/(1000 * 60));
			
			
			if(dias==0){
				if(horas==0){
					setTiempo_bandeja(""+minutos+" minutos");
				}else{
					setTiempo_bandeja(""+horas+" horas, "+minutos+ " minutos");	
				}
			}else{			
				setTiempo_bandeja(""+dias+" dias, "+horas+ " horas");				
			}
			
		}else{
			setTiempo_bandeja("---------");
		}			
		
	}
	
	
	/**
	 * @return the fecha_envio
	 */
	public Date getFecha_envio() {
		return fecha_envio;
	}
	public void setFecha_envio(Date fecha_envio) {
          this.fecha_envio = fecha_envio;		
	}
	public void setTipo_envio(String tipo_envio) {

		this.tipo_envio=tipo_envio;
	}
	/**
	 * @return the tipo_envio
	 */
	public String getTipo_envio() {
		return tipo_envio;
	}
	
	
	}
