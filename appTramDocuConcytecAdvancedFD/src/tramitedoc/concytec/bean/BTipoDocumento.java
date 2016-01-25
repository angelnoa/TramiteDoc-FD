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
public class BTipoDocumento extends Bean{
	
	private String codigo_tipo;
	private String descripcion_tipo;
	private String es_multiple;
	private String es_entrada;
	private String es_salida;
	
	private String es_interno;
	private String dias_respuesta;
	
	public String getCodigo_tipo() {
		return codigo_tipo;
	}
	public void setCodigo_tipo(String codigo_tipo) {
		this.codigo_tipo = codigo_tipo;
	}
	public String getDescripcion_tipo() {
		return descripcion_tipo;
	}
	public void setDescripcion_tipo(String descripcion_tipo) {
		this.descripcion_tipo = descripcion_tipo;
	}
	public String getDias_respuesta() {
		return dias_respuesta;
	}
	public void setDias_respuesta(String dias_respuesta) {
		this.dias_respuesta = dias_respuesta;
	}
	public String getEs_entrada() {
		return es_entrada;
	}
	public void setEs_entrada(String es_entrada) {
		this.es_entrada = es_entrada;
	}
	public String getEs_interno() {
		return es_interno;
	}
	public void setEs_interno(String es_interno) {
		this.es_interno = es_interno;
	}
	public String getEs_multiple() {
		return es_multiple;
	}
	public void setEs_multiple(String es_multiple) {
		this.es_multiple = es_multiple;
	}
	public String getEs_salida() {
		return es_salida;
	}
	public void setEs_salida(String es_salida) {
		this.es_salida = es_salida;
	}
	
		
		}
