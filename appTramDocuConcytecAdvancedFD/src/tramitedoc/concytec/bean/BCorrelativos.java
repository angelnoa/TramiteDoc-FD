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
public class BCorrelativos extends Bean{
	
	private String codigo_oficina;
	private String codigo_fondo;
	private String codigo_tipo;
	private String Ultimo;
	private String UsuMod;
	
	private String FecMod;
	private String modificado;
	
	public String getCodigo_fondo() {
		return codigo_fondo;
	}
	public void setCodigo_fondo(String codigo_fondo) {
		this.codigo_fondo = codigo_fondo;
	}
	public String getCodigo_oficina() {
		return codigo_oficina;
	}
	public void setCodigo_oficina(String codigo_oficina) {
		this.codigo_oficina = codigo_oficina;
	}
	public String getCodigo_tipo() {
		return codigo_tipo;
	}
	public void setCodigo_tipo(String codigo_tipo) {
		this.codigo_tipo = codigo_tipo;
	}
	public String getFecMod() {
		return FecMod;
	}
	public void setFecMod(String fecMod) {
		FecMod = fecMod;
	}
	public String getModificado() {
		return modificado;
	}
	public void setModificado(String modificado) {
		this.modificado = modificado;
	}
	public String getUltimo() {
		return Ultimo;
	}
	public void setUltimo(String ultimo) {
		Ultimo = ultimo;
	}
	public String getUsuMod() {
		return UsuMod;
	}
	public void setUsuMod(String usuMod) {
		UsuMod = usuMod;
	}
	
	
		}
