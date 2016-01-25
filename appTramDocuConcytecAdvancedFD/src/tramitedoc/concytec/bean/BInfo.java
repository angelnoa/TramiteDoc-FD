package tramitedoc.concytec.bean;

import tramitedoc.concytec.util.Bean;

public class BInfo extends Bean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String doc_rpta="";
	private String nombrearchivo="";
	private int tipo_documento_interno=0;
	private int id_det_upload=0;
	
	public void setDoc_rpta(String doc_rpta) {
		this.doc_rpta = doc_rpta;
	}
	public String getDoc_rpta() {
		return doc_rpta;
	}
	public void setNombrearchivo(String nombrearchivo) {
		this.nombrearchivo = nombrearchivo;
	}
	public String getNombrearchivo() {
		return nombrearchivo;
	}
	public int getTipo_documento_interno() {
		return tipo_documento_interno;
	}
	public void setTipo_documento_interno(int tipo_documento_interno) {
		this.tipo_documento_interno = tipo_documento_interno;
	}
	public int getId_det_upload() {
		return id_det_upload;
	}
	public void setId_det_upload(int id_det_upload) {
		this.id_det_upload = id_det_upload;
	}
	
}

	
	
	