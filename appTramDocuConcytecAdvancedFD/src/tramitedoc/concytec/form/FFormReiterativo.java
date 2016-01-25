package tramitedoc.concytec.form;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class FFormReiterativo extends ActionForm {

	private String codigo_oficina;
	private String codigo_expediente;
	private String codigo_documento;
	
	private String operacion;
	private int indice; 
	private Collection listaDocs;
	

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	
	
	

	public Collection getListaDocs() {
		return listaDocs;
	}

	public void setListaDocs(Collection listaDocs) {
		this.listaDocs = listaDocs;
	}

	public String getCodigo_oficina() {
		return codigo_oficina;
	}

	public void setCodigo_oficina(String codigo_oficina) {
		this.codigo_oficina = codigo_oficina;
	}

	public String getCodigo_expediente() {
		return codigo_expediente;
	}

	public void setCodigo_expediente(String codigo_expediente) {
		this.codigo_expediente = codigo_expediente;
	}

	public String getCodigo_documento() {
		return codigo_documento;
	}

	public void setCodigo_documento(String codigo_documento) {
		this.codigo_documento = codigo_documento;
	}

}
