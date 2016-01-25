/*
 * Created on 23/07/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tramitedoc.concytec.bean;
import tramitedoc.concytec.util.Bean;
/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BCodPadre extends Bean{
	
	private String codigo_inicia;
	private String codigo_documento;
	private String secuencia_movimiento;
	
	private String fecha_movimiento;
	private String fecha_rpta;
	private String fecha_sistema;
	
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
	public String getSecuencia_movimiento() {
		return secuencia_movimiento;
	}
	public void setSecuencia_movimiento(String secuencia_movimiento) {
		this.secuencia_movimiento = secuencia_movimiento;
	}
	public String getFecha_movimiento() {
		return fecha_movimiento;
	}
	public void setFecha_movimiento(String fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
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
	
		
		}
