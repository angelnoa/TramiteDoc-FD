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
public class BPersona extends Bean{
	
	private String codigo_persona;
	private String nombre_persona;
	private String direccion_persona;
	private String grado_persona;
	private String ruc;
	private String tipo;
	private String oficina_pertenece;
	private int contador=0;
	
	public BPersona() {
		// TODO Auto-generated constructor stub
	}
	public BPersona(String nombre_persona) {

		this.nombre_persona =nombre_persona;
	}
	
	
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	public String getCodigo_persona() {
		return codigo_persona;
	}
	public void setCodigo_persona(String codigo_persona) {
		this.codigo_persona = codigo_persona;
	}
	public String getDireccion_persona() {
		return direccion_persona;
	}
	public void setDireccion_persona(String direccion_persona) {
		this.direccion_persona = direccion_persona;
	}
	public String getGrado_persona() {
		return grado_persona;
	}
	public void setGrado_persona(String grado_persona) {
		this.grado_persona = grado_persona;
	}
	public String getNombre_persona() {
		return nombre_persona;
	}
	public void setNombre_persona(String nombre_persona) {
		this.nombre_persona = nombre_persona;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
		
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre_persona;
	}
	public void setOficina_pertenece(String oficina_pertenece) {
		this.oficina_pertenece = oficina_pertenece;
	}
	public String getOficina_pertenece() {
		return oficina_pertenece;
	}
		}
