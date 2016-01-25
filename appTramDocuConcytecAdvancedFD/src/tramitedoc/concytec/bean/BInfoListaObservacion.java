package tramitedoc.concytec.bean;

import tramitedoc.concytec.util.Bean;

public class BInfoListaObservacion extends Bean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3700204587354942199L;
	private int estado_codigo = 0;
	private int secuencia_inicial = 0;
	private boolean si_observa = true;
	private String ultimo_usuario="";
	private String tipo_envio="";
	private String fecha_movimiento="";
	private String hora_movimiento="";
	private int codigo_oficina_origen=0;
	private int codigo_oficina=0;
	
	public void setEstado_codigo(int estado_codigo) {
		this.estado_codigo = estado_codigo;
	}
	public int getEstado_codigo() {
		return estado_codigo;
	}
	public void setSecuencia_inicial(int secuencia_inicial) {
		this.secuencia_inicial = secuencia_inicial;
	}
	public int getSecuencia_inicial() {
		return secuencia_inicial;
	}
	public void setSi_observa(boolean si_observa) {
		this.si_observa = si_observa;
	}
	public boolean isSi_observa() {
		return si_observa;
	}
	public void setUltimo_usuario(String ultimo_usuario) {
		this.ultimo_usuario = ultimo_usuario;
	}
	public String getUltimo_usuario() {
		return ultimo_usuario;
	}
	public void setTipo_envio(String tipo_envio) {
		this.tipo_envio = tipo_envio;
	}
	public String getTipo_envio() {
		return tipo_envio;
	}
	public void setFecha_movimiento(String fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}
	public String getFecha_movimiento() {
		return fecha_movimiento;
	}
	public void setHora_movimiento(String hora_movimiento) {
		this.hora_movimiento = hora_movimiento;
	}
	public String getHora_movimiento() {
		return hora_movimiento;
	}
	public void setCodigo_oficina_origen(int codigo_oficina_origen) {
		this.codigo_oficina_origen = codigo_oficina_origen;
	}
	public int getCodigo_oficina_origen() {
		return codigo_oficina_origen;
	}
	public void setCodigo_oficina(int codigo_oficina) {
		this.codigo_oficina = codigo_oficina;
	}
	public int getCodigo_oficina() {
		return codigo_oficina;
	}
	
}
