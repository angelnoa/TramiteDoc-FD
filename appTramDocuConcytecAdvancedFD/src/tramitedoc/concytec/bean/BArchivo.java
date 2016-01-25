package tramitedoc.concytec.bean;

import tramitedoc.concytec.util.Bean;

public class BArchivo extends Bean{
	
	private String nombre_archivo;
	private String nombre_archivo_cifrado;
	private String ruta;
	private byte[] data;
	private String tipo_archivo;
	private String usuario;
	private boolean ischecked = false;
	private boolean isvisado = false;
	private boolean isfirmado = false;
	private boolean isfirmadomanual = false;
	private String nombre_firmante;
	private int id_archivo_proyecto = 0; //id_darchivoproyecto
	private int id_archivo = 0; //id_tbl_cabupload
	private int id_detalle_archivo = 0;
	private String estado;
	private String estado_usuario_origen;
	private String estado_usuario_fin;
	private String rutaDetalleUpload;
	private String rutaTemporal;
	private boolean isNoFirmado = false;
	private String fecha_hora_actual;
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the nombre_archivo
	 */
	public String getNombre_archivo() {
		return nombre_archivo;
	}
	/**
	 * @param nombre_archivo the nombre_archivo to set
	 */
	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}
	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
	/**
	 * @return the tipo_archivo
	 */
	public String getTipo_archivo() {
		return tipo_archivo;
	}
	/**
	 * @param tipo_archivo the tipo_archivo to set
	 */
	public void setTipo_archivo(String tipo_archivo) {
		this.tipo_archivo = tipo_archivo;
	}
	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}
	public boolean isIschecked() {
		return ischecked;
	}
	public void setIsvisado(boolean isvisado) {
		this.isvisado = isvisado;
	}
	public boolean isIsvisado() {
		return isvisado;
	}
	public void setIsfirmado(boolean isfirmado) {
		this.isfirmado = isfirmado;
	}
	public boolean isIsfirmado() {
		return isfirmado;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getRuta() {
		return ruta;
	}
	public void setNombre_archivo_cifrado(String nombre_archivo_cifrado) {
		this.nombre_archivo_cifrado = nombre_archivo_cifrado;
	}
	public String getNombre_archivo_cifrado() {
		return nombre_archivo_cifrado;
	}
	public void setNombre_firmante(String nombre_firmante) {
		this.nombre_firmante = nombre_firmante;
	}
	public String getNombre_firmante() {
		return nombre_firmante;
	}
	public void setId_archivo(int id_archivo) {
		this.id_archivo = id_archivo;
	}
	public int getId_archivo() {
		return id_archivo;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstado() {
		return estado;
	}
	public void setId_detalle_archivo(int id_detalle_archivo) {
		this.id_detalle_archivo = id_detalle_archivo;
	}
	public int getId_detalle_archivo() {
		return id_detalle_archivo;
	}
	public void setId_archivo_proyecto(int id_archivo_proyecto) {
		this.id_archivo_proyecto = id_archivo_proyecto;
	}
	public int getId_archivo_proyecto() {
		return id_archivo_proyecto;
	}
	public void setEstado_usuario_origen(String estado_usuario_origen) {
		this.estado_usuario_origen = estado_usuario_origen;
	}
	public String getEstado_usuario_origen() {
		return estado_usuario_origen;
	}
	public void setEstado_usuario_fin(String estado_usuario_fin) {
		this.estado_usuario_fin = estado_usuario_fin;
	}
	public String getEstado_usuario_fin() {
		return estado_usuario_fin;
	}
	public void setIsfirmadomanual(boolean isfirmadomanual) {
		this.isfirmadomanual = isfirmadomanual;
	}
	public boolean isIsfirmadomanual() {
		return isfirmadomanual;
	}
	public String getRutaDetalleUpload() {
		return rutaDetalleUpload;
	}
	public void setRutaDetalleUpload(String rutaDetalleUpload) {
		this.rutaDetalleUpload = rutaDetalleUpload;
	}
	public boolean isNoFirmado() {
		return isNoFirmado;
	}
	public void setNoFirmado(boolean isNoFirmado) {
		this.isNoFirmado = isNoFirmado;
	}
	public String getRutaTemporal() {
		return rutaTemporal;
	}
	public void setRutaTemporal(String rutaTemporal) {
		this.rutaTemporal = rutaTemporal;
	}
	public String getFecha_hora_actual() {
		return fecha_hora_actual;
	}
	public void setFecha_hora_actual(String fecha_hora_actual) {
		this.fecha_hora_actual = fecha_hora_actual;
	}
	
	

}
