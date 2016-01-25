package tramitedoc.concytec.form;

import java.util.Collection;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import tramitedoc.concytec.bean.BDocumento;

public class FFormMantPrecerrar extends ActionForm{

	
	private static final long serialVersionUID = 1L;
	private String fecha_envio = null;
	private String nombre_destinatario = null;
	private String nombre_remitente= null;
	private int destinatario;
	private String remitente; //Oficina
	private int nro_documentos = 0;
	private String objeto_envio = null;
	private int tipo_servicio;
	private int codigo_courier;
	private String codigo_guia_courier=null;
	private String tipo=null;
	private Collection paquete;
	
	private String codigo_guia_courier_recep=null;
	private String fecha_recep=null;
	
	
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
					if(doc.isSeleccionado()){
						i++;
						if(i==1){
							paqueteDocs = "("+doc.getCodigo_documento();
						}else{
							paqueteDocs += ","+doc.getCodigo_documento();
						}					
					}				
				}
				paqueteDocs += ") "+(paquete.size()==1?"1 Documento":paquete.size()+" Documentos");
			}
		}else{
			System.out.println("No existe paquete");
		}
		
		return paqueteDocs;
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
	 * @return the fecha_recep
	 */
	public String getFecha_recep() {
		return fecha_recep;
	}
	/**
	 * @param fecha_recep the fecha_recep to set
	 */
	public void setFecha_recep(String fecha_recep) {
		this.fecha_recep = fecha_recep;
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
	public String getFecha_envio() {
		return fecha_envio;
	}
	public void setFecha_envio(String fecha_envio) {
		this.fecha_envio = fecha_envio;
	}
	public int getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(int destinatario) {
		this.destinatario = destinatario;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public int getNro_documentos() {
		return nro_documentos;
	}
	public void setNro_documentos(int nro_documentos) {
		this.nro_documentos = nro_documentos;
	}
	public String getObjeto_envio() {
		return objeto_envio;
	}
	public void setObjeto_envio(String objeto_envio) {
		this.objeto_envio = objeto_envio;
	}
	public int getTipo_servicio() {
		return tipo_servicio;
	}
	public void setTipo_servicio(int tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
	}
	public int getCodigo_courier() {
		return codigo_courier;
	}
	public void setCodigo_courier(int codigo_courier) {
		this.codigo_courier = codigo_courier;
	}
	public Collection getPaquete() {
		return paquete;
	}
	public void setPaquete(Collection paquete) {
		this.paquete = paquete;
	}
	
}
