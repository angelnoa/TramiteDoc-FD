package tramitedoc.concytec.util;

/**
 * AUTOR: Christian Machuca
 * FECHA: 31-03-2006
 * VERSION: 1.0
 * DESCRIPCIÓN: Para implementacion de beans
 */

public abstract class Bean  implements java.io.Serializable{

public String getReferencia() {
	return getClass().getName();
}
}
    