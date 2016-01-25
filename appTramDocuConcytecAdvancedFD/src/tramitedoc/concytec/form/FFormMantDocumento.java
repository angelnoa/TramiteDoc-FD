package tramitedoc.concytec.form;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Mapeo de datos del logeo recibidos del jsp  
 */

public class FFormMantDocumento extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String medio = null;
	private String codigo_tipo = null;
	private String codigo_oficina = null;
	private String destinatario = null;
	private String codigo_solicitud = null;
	private String numero_documento = null;
	private String fecha_registro = null;
	private String hora = null;
	private String folios_documento = null;
	private String asunto_documento = null;
	private String observa_documento = null;
	private String firmadopor = null;
	private String origen_documento = null;
	private String tipo = null;
	private FormFile theFile;
	private String chk_copia = null;
	private String[] cbo_copia = null;
	private String[] selectedItems;
	private Collection archivos;

	private String chk_copia_other = null;
	private String[] cbo_copia_other = null;
	private String[] selectedItems_other;
	
	
	public int getNumeroArchivos(){
		return archivos==null?0:archivos.size();
	}
	/**
	 * @return the archivos
	 */
	public Collection getArchivos() {
		return archivos;
	}
	/**
	 * @param archivos the archivos to set
	 */
	public void setArchivos(Collection archivos) {
		this.archivos = archivos;
	}
	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}
	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	public String getChk_copia() {
		return chk_copia;
	}
	public void setChk_copia(String chk_copia) {
		this.chk_copia = chk_copia;
	}
	public String[] getCbo_copia() {
		return cbo_copia;
	}
	public void setCbo_copia(String[] cbo_copia) {
		this.cbo_copia = cbo_copia;
	}
	
	public String getChk_copia_other() {
		return chk_copia_other;
	}
	public void setChk_copia_other(String chk_copia_other) {
		this.chk_copia_other = chk_copia_other;
	}
	public String[] getCbo_copia_other() {
		return cbo_copia_other;
	}
	public void setCbo_copia_other(String[] cbo_copia_other) {
		this.cbo_copia_other = cbo_copia_other;
	}
	public String[] getSelectedItems_other() {
		return selectedItems_other;
	}
	public void setSelectedItems_other(String[] selectedItems_other) {
		this.selectedItems_other = selectedItems_other;
	}
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
		
	public FormFile getTheFile() {
		return theFile;
	}
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}
	public String getAsunto_documento() {
		return asunto_documento;
	}
	public void setAsunto_documento(String asunto_documento) {
		this.asunto_documento = asunto_documento;
	}
	public String getCodigo_oficina() {
		return codigo_oficina;
	}
	public void setCodigo_oficina(String codigo_oficina) {
		this.codigo_oficina = codigo_oficina;
	}
	public String getCodigo_solicitud() {
		return codigo_solicitud;
	}
	public void setCodigo_solicitud(String codigo_solicitud) {
		this.codigo_solicitud = codigo_solicitud;
	}
	public String getCodigo_tipo() {
		return codigo_tipo;
	}
	public void setCodigo_tipo(String codigo_tipo) {
		this.codigo_tipo = codigo_tipo;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getFirmadopor() {
		return firmadopor;
	}
	public void setFirmadopor(String firmadopor) {
		this.firmadopor = firmadopor;
	}
	public String getFolios_documento() {
		return folios_documento;
	}
	public void setFolios_documento(String folios_documento) {
		this.folios_documento = folios_documento;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getMedio() {
		return medio;
	}
	public void setMedio(String medio) {
		this.medio = medio;
	}
	public String getNumero_documento() {
		return numero_documento;
	}
	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}
	public String getObserva_documento() {
		return observa_documento;
	}
	public void setObserva_documento(String observa_documento) {
		this.observa_documento = observa_documento;
	}
	public String getOrigen_documento() {
		return origen_documento;
	}
	public void setOrigen_documento(String origen_documento) {
		this.origen_documento = origen_documento;
	}

	

}
