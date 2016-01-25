package tramitedoc.concytec.bean;

import java.io.Serializable;

public class BParametro implements Serializable{
	
		private Integer codigo_parametro;
	    private String simbolo;
	    private String descripcion;
	    private String valor;

	    public Integer getCodigo_parametro() {
	        return codigo_parametro;
	    }

	    public void setCodigo_parametro(Integer codigo_parametro) {
	        this.codigo_parametro = codigo_parametro;
	    }

	    public String getSimbolo() {
	        return simbolo;
	    }

	    public void setSimbolo(String simbolo) {
	        this.simbolo = simbolo;
	    }

	    public String getDescripcion() {
	        return descripcion;
	    }

	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }

	    public String getValor() {
	        return valor;
	    }

	    public void setValor(String valor) {
	        this.valor = valor;
	    }
}
