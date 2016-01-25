package tramitedoc.concytec.form;

import java.util.Collection;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FFormReiterativoMantenimiento extends ActionForm{

	private String opcion = "";
	private String procedencia;
	private String codigo_documento = null;
	private String secuencia_movimiento = null;
	private int codigo_expediente = 0;
	private String numero_documento = null;	
	private Collection listado;
	private String observa_documento;
	
	
	
	
	
	public String getSecuencia_movimiento() {
		return secuencia_movimiento;
	}
	public void setSecuencia_movimiento(String secuencia_movimiento) {
		this.secuencia_movimiento = secuencia_movimiento;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	public String getNumero_documento() {
		return numero_documento;
	}
	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}
	public String getProcedencia() {
		return procedencia;
	}
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	public String getCodigo_documento() {
		return codigo_documento;
	}
	public void setCodigo_documento(String codigo_documento) {
		this.codigo_documento = codigo_documento;
	}
	public int getCodigo_expediente() {
		return codigo_expediente;
	}
	public void setCodigo_expediente(int codigo_expediente) {
		this.codigo_expediente = codigo_expediente;
	}
	public Collection getListado() {
		return listado;
	}
	public void setListado(Collection listado) {
		this.listado = listado;
	}
	public void setObserva_documento(String observa_documento) {
		// TODO Auto-generated method stub
		this.observa_documento = observa_documento;
	}
	/**
	 * @return the observa_documento
	 */
	public String getObserva_documento() {
		return observa_documento;
	}
	
	
	
		
}
