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
public class BSerie extends Bean{
	
	private String codigo_oficina;
	private String codigo_fondo;
	private String codigo_serie;
	private String oficina;
	private String descripcion_serie;
	
	private String fondo;
	private String serie;
	private String orden;
	private String valor;
	
	private String ag_reten;
	private String ac_reten;
	private String nivel;
	private String ap_reten;
	
	public String getAc_reten() {
		return ac_reten;
	}
	public void setAc_reten(String ac_reten) {
		this.ac_reten = ac_reten;
	}
	public String getAg_reten() {
		return ag_reten;
	}
	public void setAg_reten(String ag_reten) {
		this.ag_reten = ag_reten;
	}
	public String getAp_reten() {
		return ap_reten;
	}
	public void setAp_reten(String ap_reten) {
		this.ap_reten = ap_reten;
	}
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
	public String getCodigo_serie() {
		return codigo_serie;
	}
	public void setCodigo_serie(String codigo_serie) {
		this.codigo_serie = codigo_serie;
	}
	public String getDescripcion_serie() {
		return descripcion_serie;
	}
	public void setDescripcion_serie(String descripcion_serie) {
		this.descripcion_serie = descripcion_serie;
	}
	public String getFondo() {
		return fondo;
	}
	public void setFondo(String fondo) {
		this.fondo = fondo;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	}
