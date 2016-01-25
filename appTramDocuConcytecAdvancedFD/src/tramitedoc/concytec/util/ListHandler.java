package tramitedoc.concytec.util;

import java.util.*;

/**
 * AUTOR: Machuca Ovalle
 * FECHA: 31-03-2006
 * VERSION: 1.0
 * DESCRIPCIÓN: Clase List Handler (listado y paginación)
 */

public class ListHandler {

	private Object[] data;
	private int pageSize;

	public ListHandler(Collection data, int pageSize) {
		// --- Recibimos el Collection y el numero de registros a mostrar por pagina
		this.data = data.toArray();
		this.pageSize = pageSize;
	}

	public int getSize() {
		return data.length / pageSize + ((data.length % pageSize == 0) ? 0 : 1);
	}

	public Collection getPage(int pageNumber) {
		
		ArrayList page = null;

		if (getSize() == 0) {
			page = new ArrayList();
			return page;
		}

		if (pageNumber >= 0 && pageNumber < getSize()) {
			
			page = new ArrayList(pageSize);
			
			int index = pageNumber * pageSize;
			
			int truePageSize =
				(data.length - index < pageSize)
					? data.length - index
					: pageSize;
			
			for (int i = 0; i < truePageSize; i++) {
				page.add(data[index++]);
			}
		
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}

		return page;
	
	}
}
