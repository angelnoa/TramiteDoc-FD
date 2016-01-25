package tramitedoc.concytec.form;

import java.util.Collection;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import tramitedoc.concytec.bean.BDocumento;

public class FFormMantCerrar extends ActionForm{

	
	private static final long serialVersionUID = 1L;
	private int codigo_cierre;
	private String fecha_envio = null;
	private String nombre_destinatario = null;
	private String nombre_remitente= null;
	private int nro_documentos = 0;
	private String objeto_envio = null;
	private String tipo_servicio;
	private String nombre_courier;
	private String codigo_guia_courier=null;
	private String tipo=null;
	private String codigo_guia_courier_recep=null;
	private String fecha_recepcion=null;
	private Collection paquete;
	
	
	
	/**
	 * @return the paquete
	 */
	
	
	public Collection getPaquete() {
		return paquete;
	}

	/**
	 * @param paquete the paquete to set
	 */
	public void setPaquete(Collection paquete) {
		this.paquete = paquete;
	}

	public String getPaqueteDocs(){
		String paqueteDocs="";

		if(paquete!=null){
			
			if(paquete.size()==0){
				paqueteDocs = "(No existe Documentos asociados)";
			}else{
				Iterator it=paquete.iterator();
				int i=0;
				while(it.hasNext()){
					
					BDocumento doc = (BDocumento) it.next();
					
						i++;
						if(i==1){
							paqueteDocs = doc.getCodigo_documento();
						}else{
							paqueteDocs += ","+doc.getCodigo_documento();
						}					
									
				}
				paqueteDocs += " "+(paquete.size()==1?"(1 Documento)":"("+paquete.size()+" Documentos )");
			}
		}else{
			System.out.println("No existe paquete");
		}
		
		return paqueteDocs;
	}
	
	/**
	 * @return the fecha_envio
	 */
	/**
	 * @return the codigo_cierre
	 */
	public int getCodigo_cierre() {
		return codigo_cierre;
	}
	/**
	 * @param codigo_cierre the codigo_cierre to set
	 */
	public void setCodigo_cierre(int codigo_cierre) {
		this.codigo_cierre = codigo_cierre;
	}
	/**
	 * @return the fecha_envio
	 */
	public String getFecha_envio() {
		return fecha_envio;
	}
	/**
	 * @param fecha_envio the fecha_envio to set
	 */
	public void setFecha_envio(String fecha_envio) {
		this.fecha_envio = fecha_envio;
	}
	/**
	 * @return the nombre_destinatario
	 */
	public String getNombre_destinatario() {
		return nombre_destinatario;
	}
	/**
	 * @param nombre_destinatario the nombre_destinatario to set
	 */
	public void setNombre_destinatario(String nombre_destinatario) {
		this.nombre_destinatario = nombre_destinatario;
	}
	/**
	 * @return the nombre_remitente
	 */
	public String getNombre_remitente() {
		return nombre_remitente;
	}
	/**
	 * @param nombre_remitente the nombre_remitente to set
	 */
	public void setNombre_remitente(String nombre_remitente) {
		this.nombre_remitente = nombre_remitente;
	}
	/**
	 * @return the nro_documentos
	 */
	public int getNro_documentos() {
		return nro_documentos;
	}
	/**
	 * @param nro_documentos the nro_documentos to set
	 */
	public void setNro_documentos(int nro_documentos) {
		this.nro_documentos = nro_documentos;
	}
	/**
	 * @return the objeto_envio
	 */
	public String getObjeto_envio() {
		return objeto_envio;
	}
	/**
	 * @param objeto_envio the objeto_envio to set
	 */
	public void setObjeto_envio(String objeto_envio) {
		this.objeto_envio = objeto_envio;
	}
	/**
	 * @return the tipo_servicio
	 */
	public String getTipo_servicio() {
		return tipo_servicio;
	}
	/**
	 * @param tipo_servicio the tipo_servicio to set
	 */
	public void setTipo_servicio(String tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
	}
	/**
	 * @return the nombre_courier
	 */
	public String getNombre_courier() {
		return nombre_courier;
	}
	/**
	 * @param nombre_courier the nombre_courier to set
	 */
	public void setNombre_courier(String nombre_courier) {
		this.nombre_courier = nombre_courier;
	}
	/**
	 * @return the codigo_guia_courier
	 */
	public String getCodigo_guia_courier() {
		return codigo_guia_courier;
	}
	/**
	 * @param codigo_guia_courier the codigo_guia_courier to set
	 */
	public void setCodigo_guia_courier(String codigo_guia_courier) {
		this.codigo_guia_courier = codigo_guia_courier;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the codigo_guia_courier_recep
	 */
	public String getCodigo_guia_courier_recep() {
		return codigo_guia_courier_recep;
	}
	/**
	 * @param codigo_guia_courier_recep the codigo_guia_courier_recep to set
	 */
	public void setCodigo_guia_courier_recep(String codigo_guia_courier_recep) {
		this.codigo_guia_courier_recep = codigo_guia_courier_recep;
	}
	/**
	 * @return the fecha_recepcion
	 */
	public String getFecha_recepcion() {
		return fecha_recepcion;
	}
	/**
	 * @param fecha_recepcion the fecha_recepcion to set
	 */
	public void setFecha_recepcion(String fecha_recepcion) {
		this.fecha_recepcion = fecha_recepcion;
	}
	
			
}
