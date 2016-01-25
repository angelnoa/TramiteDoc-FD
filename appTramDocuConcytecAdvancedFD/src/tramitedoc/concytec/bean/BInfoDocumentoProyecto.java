package tramitedoc.concytec.bean;

import tramitedoc.concytec.util.Bean;

public class BInfoDocumentoProyecto extends Bean{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6745727078145928760L;
	/**
	 * 
	 */
	
	private String codigo_documento="0";
	private String codigo_expediente="0";
	private String codigo_recepcion="0";
	private String secuencia_movimiento="0";
	private String oficina_origen="0";
	private String codigo_proyecto="0";
	
	public void setCodigo_documento(String codigo_documento) {
		this.codigo_documento = codigo_documento;
	}
	public String getCodigo_documento() {
		return codigo_documento;
	}
	public void setCodigo_expediente(String codigo_expediente) {
		this.codigo_expediente = codigo_expediente;
	}
	public String getCodigo_expediente() {
		return codigo_expediente;
	}
	public void setCodigo_recepcion(String codigo_recepcion) {
		this.codigo_recepcion = codigo_recepcion;
	}
	public String getCodigo_recepcion() {
		return codigo_recepcion;
	}
	public void setSecuencia_movimiento(String secuencia_movimiento) {
		this.secuencia_movimiento = secuencia_movimiento;
	}
	public String getSecuencia_movimiento() {
		return secuencia_movimiento;
	}
	public void setOficina_origen(String oficina_origen) {
		this.oficina_origen = oficina_origen;
	}
	public String getOficina_origen() {
		return oficina_origen;
	}
	public void setCodigo_proyecto(String codigo_proyecto) {
		this.codigo_proyecto = codigo_proyecto;
	}
	public String getCodigo_proyecto() {
		return codigo_proyecto;
	}
	
	

}