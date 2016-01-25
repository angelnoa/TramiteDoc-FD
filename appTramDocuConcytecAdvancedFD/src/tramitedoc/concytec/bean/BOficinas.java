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
public class BOficinas extends Bean{
	
	private String codigo_oficina;
	private String codigo_fondo;
	private String nombre_corto;
	private String fondo;
	private String oficina;
	
	private String descripcion_oficina;
	private String siglas_oficina;
	
	private String doc_entrada;
	private String doc_salida;
	private String doc_interno;
	private String es_activo;
	private String modalidad_salida;
	private String nivel;
	private String cod_dpto;
	private String cod_prov;
	private String cod_dist;
	
	private int grupo_oficina=0;
	private int padre=0;
	private int grupo_oficina_combo=0;
	
	private int sede;
	
	public String getCod_dist() {
		return cod_dist;
	}
	public void setCod_dist(String cod_dist) {
		this.cod_dist = cod_dist;
	}
	public String getCod_dpto() {
		return cod_dpto;
	}
	public void setCod_dpto(String cod_dpto) {
		this.cod_dpto = cod_dpto;
	}
	public String getCod_prov() {
		return cod_prov;
	}
	public void setCod_prov(String cod_prov) {
		this.cod_prov = cod_prov;
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
	public String getDescripcion_oficina() {
		return descripcion_oficina;
	}
	public void setDescripcion_oficina(String descripcion_oficina) {
		this.descripcion_oficina = descripcion_oficina;
	}
	public String getDoc_entrada() {
		return doc_entrada;
	}
	public void setDoc_entrada(String doc_entrada) {
		this.doc_entrada = doc_entrada;
	}
	public String getDoc_interno() {
		return doc_interno;
	}
	public void setDoc_interno(String doc_interno) {
		this.doc_interno = doc_interno;
	}
	public String getDoc_salida() {
		return doc_salida;
	}
	public void setDoc_salida(String doc_salida) {
		this.doc_salida = doc_salida;
	}
	public String getEs_activo() {
		return es_activo;
	}
	public void setEs_activo(String es_activo) {
		this.es_activo = es_activo;
	}
	public String getFondo() {
		return fondo;
	}
	public void setFondo(String fondo) {
		this.fondo = fondo;
	}
	public String getModalidad_salida() {
		return modalidad_salida;
	}
	public void setModalidad_salida(String modalidad_salida) {
		this.modalidad_salida = modalidad_salida;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getNombre_corto() {
		return nombre_corto;
	}
	public void setNombre_corto(String nombre_corto) {
		this.nombre_corto = nombre_corto;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getSiglas_oficina() {
		return siglas_oficina;
	}
	public void setSiglas_oficina(String siglas_oficina) {
		this.siglas_oficina = siglas_oficina;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre_corto;
	}
	public int getGrupo_oficina() {
		return grupo_oficina;
	}
	public void setGrupo_oficina(int grupo_oficina) {
		this.grupo_oficina = grupo_oficina;
	}
	public int getPadre() {
		return padre;
	}
	public void setPadre(int padre) {
		this.padre = padre;
	}
	public int getGrupo_oficina_combo() {
		return grupo_oficina_combo;
	}
	public void setGrupo_oficina_combo(int grupo_oficina_combo) {
		this.grupo_oficina_combo = grupo_oficina_combo;
	}
	public int getSede() {
		return sede;
	}
	public void setSede(int sede) {
		this.sede = sede;
	}
	
			}
