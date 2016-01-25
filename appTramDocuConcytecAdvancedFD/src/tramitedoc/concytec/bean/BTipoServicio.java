package tramitedoc.concytec.bean;

public class BTipoServicio {

	private int codigo_tipo_servicio;
	private String descripcion;
	
	public BTipoServicio() {
		// TODO Auto-generated constructor stub
	}
	
	
	public BTipoServicio(int codigo_tipo_servicio, String descripcion) {
		super();
		this.codigo_tipo_servicio = codigo_tipo_servicio;
		this.descripcion = descripcion;
	}


	/**
	 * @return the tipo_servicio
	 */
	public int getCodigo_tipo_servicio() {
		return codigo_tipo_servicio;
	}
	/**
	 * @param tipo_servicio the tipo_servicio to set
	 */
	public void setCodigo_tipo_servicio(int tipo_servicio) {
		this.codigo_tipo_servicio = tipo_servicio;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return descripcion;
	}
}
