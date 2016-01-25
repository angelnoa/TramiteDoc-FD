package tramitedoc.concytec.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BDatosFirmante implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2303400583493053444L;
	private String firmado_por;
	private Integer tipo_firma;
	private String cargo;
	private String oficina;
	private String nombre_corto;
	private String nombre_sello;
	private String ruta_sello;
	private String nombre_pfx;
	private String ruta_pfx;
	private String nombre_sello_visto;
	private String abreviatura;
	private String dni;
	private boolean esoficinamaxima=false;
	private boolean esresponsable=false;
	private boolean essecretaria=false;
	private Integer oficinaPermisoFirma;
	private String usuario;
	
	public void setFirmado_por(String firmado_por) {
		this.firmado_por = firmado_por;
	}
	public String getFirmado_por() {
		return firmado_por;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCargo() {
		return cargo;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getOficina() {
		return oficina;
	}
	
	public void setNombre_pfx(String nombre_pfx) {
		this.nombre_pfx = nombre_pfx;
	}
	public String getNombre_pfx() {
		return nombre_pfx;
	}
	
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDni() {
		return dni;
	}
	public void setTipo_firma(Integer tipo_firma) {
		this.tipo_firma = tipo_firma;
	}
	public Integer getTipo_firma() {
		return tipo_firma;
	}
	public void setNombre_sello(String nombre_sello) {
		this.nombre_sello = nombre_sello;
	}
	public String getNombre_sello() {
		return nombre_sello;
	}
	public void setRuta_sello(String ruta_sello) {
		this.ruta_sello = ruta_sello;
	}
	public String getRuta_sello() {
		return ruta_sello;
	}
	public void setRuta_pfx(String ruta_pfx) {
		this.ruta_pfx = ruta_pfx;
	}
	public String getRuta_pfx() {
		return ruta_pfx;
	}
	public void setNombre_sello_visto(String nombre_sello_visto) {
		this.nombre_sello_visto = nombre_sello_visto;
	}
	public String getNombre_sello_visto() {
		return nombre_sello_visto;
	}
	public void setEsoficinamaxima(boolean esoficinamaxima) {
		this.esoficinamaxima = esoficinamaxima;
	}
	public boolean isEsoficinamaxima() {
		return esoficinamaxima;
	}
	public void setEsresponsable(boolean esresponsable) {
		this.esresponsable = esresponsable;
	}
	public boolean isEsresponsable() {
		return esresponsable;
	}
	public void setEssecretaria(boolean essecretaria) {
		this.essecretaria = essecretaria;
	}
	public boolean isEssecretaria() {
		return essecretaria;
	}
	public Integer getOficinaPermisoFirma() {
		return oficinaPermisoFirma;
	}
	public void setOficinaPermisoFirma(Integer oficinaPermisoFirma) {
		this.oficinaPermisoFirma = oficinaPermisoFirma;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setNombre_corto(String nombre_corto) {
		this.nombre_corto = nombre_corto;
	}
	public String getNombre_corto() {
		return nombre_corto;
	}
		
	
}
