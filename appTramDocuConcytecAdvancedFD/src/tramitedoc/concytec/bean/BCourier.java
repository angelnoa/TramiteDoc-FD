package tramitedoc.concytec.bean;

public class BCourier {

	int id_courier;
	String codigo_courier;
	String nombre_courier;
	
	public BCourier() {
		// TODO Auto-generated constructor stub
	}
	
	public BCourier(int id_courier, String codigo_courier, String nombre_courier) {
		super();
		this.id_courier = id_courier;
		this.codigo_courier = codigo_courier;
		this.nombre_courier = nombre_courier;
	}
	/**
	 * @return the id_courier
	 */
	public int getId_courier() {
		return id_courier;
	}
	/**
	 * @param id_courier the id_courier to set
	 */
	public void setId_courier(int id_courier) {
		this.id_courier = id_courier;
	}
	/**
	 * @return the codigo_courier
	 */
	public String getCodigo_courier() {
		return codigo_courier;
	}
	/**
	 * @param codigo_courier the codigo_courier to set
	 */
	public void setCodigo_courier(String codigo_courier) {
		this.codigo_courier = codigo_courier;
	}
	/**
	 * @return the nombre_courier
	 */
	public String getNombre_courier() {
		return nombre_courier;
	}
	/**
	 * @param nombre_courier the nombre_courier to set
	 */
	public void setNombre_courier(String nombre_courier) {
		this.nombre_courier = nombre_courier;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return codigo_courier+"-"+nombre_courier;
	}
}
