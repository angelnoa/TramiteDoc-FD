package tramitedoc.concytec.bean;

import java.io.Serializable;

public class BReporteConsolidadoTransacciones implements Serializable{
	
	private BOficinas oficina;
	private int creados;
	private int derivados;
	private int archivados;
	private int anulados;
	private int tramite;
	/**
	 * @return the oficina
	 */
	public BOficinas getOficina() {
		return oficina;
	}
	/**
	 * @param oficina the oficina to set
	 */
	public void setOficina(BOficinas oficina) {
		this.oficina = oficina;
	}
	/**
	 * @return the creados
	 */
	public int getCreados() {
		return creados;
	}
	/**
	 * @param creados the creados to set
	 */
	public void setCreados(int creados) {
		this.creados = creados;
	}
	/**
	 * @return the derivados
	 */
	public int getDerivados() {
		return derivados;
	}
	/**
	 * @param derivados the derivados to set
	 */
	public void setDerivados(int derivados) {
		this.derivados = derivados;
	}
	/**
	 * @return the archivados
	 */
	public int getArchivados() {
		return archivados;
	}
	/**
	 * @param archivados the archivados to set
	 */
	public void setArchivados(int archivados) {
		this.archivados = archivados;
	}
	/**
	 * @return the anulados
	 */
	public int getAnulados() {
		return anulados;
	}
	/**
	 * @param anulados the anulados to set
	 */
	public void setAnulados(int anulados) {
		this.anulados = anulados;
	}
	public void setTramite(int tramite) {
		this.tramite = tramite;
	}
	public int getTramite() {
		return tramite;
	}
	
	
	

}
