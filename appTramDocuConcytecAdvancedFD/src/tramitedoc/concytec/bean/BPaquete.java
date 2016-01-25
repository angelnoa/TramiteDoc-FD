package tramitedoc.concytec.bean;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import tramitedoc.concytec.util.Bean;

public class BPaquete extends Bean {
	
	private Date fecha_recepcion;
	private String fecha_envio;
	private BCourier courier;
	private String codigo_guia_courier;
	private BPersona destinatario;
	private BUsuario remitente;
	private BTipoServicio tipo_servicio;
	private String objeto_envio;
	private Collection documentos;
	
	
	
	
	
	public String getDetallePaquete(){
		String paqueteDocs = "";
		
		if(documentos!=null){
			
			if(documentos.size()==0){
				paqueteDocs = "(No existe Documentos asociados)";
			}else{
				Iterator it=documentos.iterator();
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
				
			}
		}else{
			System.out.println("No existe paquete");
		}
		return paqueteDocs;
	}
	/**
	 * @return the documentos
	 */
	public Collection getDocumentos() {
		return documentos;
	}
	/**
	 * @param documentos the documentos to set
	 */
	public void setDocumentos(Collection documentos) {
		this.documentos = documentos;
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
	public BTipoServicio getTipo_servicio() {
		return tipo_servicio;
	}
	/**
	 * @param tipo_servicio the tipo_servicio to set
	 */
	public void setTipo_servicio(BTipoServicio tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
	}
	/**
	 * @return the fecha_recepcion
	 */
	public Date getFecha_recepcion() {
		return fecha_recepcion;
	}
	/**
	 * @param fecha_recepcion the fecha_recepcion to set
	 */
	public void setFecha_recepcion(Date fecha_recepcion) {
		this.fecha_recepcion = fecha_recepcion;
	}
	/**
	 * @return the fecha_envio
	 */
	
	/**
	 * @return the courier
	 */
	public BCourier getCourier() {
		return courier;
	}
	public String getFecha_envio() {
		return fecha_envio;
	}
	public void setFecha_envio(String fecha_envio) {
		this.fecha_envio = fecha_envio;
	}
	/**
	 * @param courier the courier to set
	 */
	public void setCourier(BCourier courier) {
		this.courier = courier;
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
	 * @return the destinatario
	 */
	public BPersona getDestinatario() {
		return destinatario;
	}
	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(BPersona destinatario) {
		this.destinatario = destinatario;
	}
	/**
	 * @return the remitente
	 */
	public BUsuario getRemitente() {
		return remitente;
	}
	/**
	 * @param remitente the remitente to set
	 */
	public void setRemitente(BUsuario remitente) {
		this.remitente = remitente;
	}
	
	
}
