package tramitedoc.concytec.admin.form;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Mapeo de datos del logeo recibidos del jsp  
 */

public class FFormMantConexion extends ActionForm{

	private String usuario = null;
	private String fecha_conexion = null;
	private String fecha_conexion_fin = null;
	private String host = null;
	private String ip = null;
	private String tipo = null;
	
	/**
	 * @return
	 */
	
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
		 	this.usuario = null;
		 	this.fecha_conexion = null;
		 	this.fecha_conexion_fin = null; 
		 	this.host = null;
		 	this.ip = null;
		 	this.tipo = null;
		    }


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	

	public String getFecha_conexion() {
		return fecha_conexion;
	}


	public void setFecha_conexion(String fecha_conexion) {
		this.fecha_conexion = fecha_conexion;
	}


	public String getFecha_conexion_fin() {
		return fecha_conexion_fin;
	}


	public void setFecha_conexion_fin(String fecha_conexion_fin) {
		this.fecha_conexion_fin = fecha_conexion_fin;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}
	 
	
	 
	
	

}