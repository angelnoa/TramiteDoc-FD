package tramitedoc.concytec.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.dao.interfaces.IReiterativoDAO;
import tramitedoc.concytec.dao.sql.SqlReiterativoDAO;

public class ReiterativoService {
	
	IReiterativoDAO rDao = new SqlReiterativoDAO();

	public String listarDocs(String codigo_oficina,
			String codigo_expediente, String codigo_documento,Collection listaDocs, int valor) {
		// TODO Auto-generated method stub
		String mensaje = "";
		Collection temp = null;
		try {
			
			//if(perteneceOficina(codigo_oficina,codigo_expediente, codigo_documento)){
				temp = rDao.listarDocs(codigo_oficina, codigo_expediente, codigo_documento, valor);
				listaDocs.addAll(temp);
			//}else{
			//	mensaje = "La Oficina del usuario no ha trabajado dicho expediente";
			//}
			
			
			return mensaje;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private boolean perteneceOficina(String codigo_oficina,
			String codigo_expediente, String codigo_documento) throws Exception{
		// TODO Auto-generated method stub
				
		try {
			return rDao.perteneceOficina(codigo_oficina,codigo_expediente, codigo_documento);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Collection listarSecuencias(String codigo_documento) {
		// TODO Auto-generated method stub
		try {
			return rDao.listarSecuencias(codigo_documento);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection listarSecuenciasMantenimiento(String codigo_documento) {
		// TODO Auto-generated method stub
		try {
			Collection listado =  rDao.listarSecuencias(codigo_documento);
			
			if(listado!=null){
				Collection nuevoListado=new ArrayList();
				Iterator it=listado.iterator();
				int cont=0;
				while(it.hasNext()){
					BMesaPartes mesa=(BMesaPartes)it.next();
					Hashtable tabla=new Hashtable();
					cont ++;
					tabla.put("orden", cont);
					tabla.put("nombre_oficina_origen", mesa.getNombre_oficina_origen());
					tabla.put("nombre_oficina_destino", mesa.getNombre_oficina_destino());
					tabla.put("nombre_personal", mesa.getNombre_personal());
					tabla.put("estado_movimiento", mesa.getEstado_movimiento());
					tabla.put("observa_movimiento", mesa.getObserva_movimiento());
					tabla.put("anular","<IMG onClick='javascript:anular("+mesa.getCodigo_documento()+","+mesa.getSecuencia_movimiento()+");' height='15' width='15' SRC='img/trash.png' alt='Anular'>");
					tabla.put("editar","<IMG onClick='javascript:editar("+mesa.getCodigo_documento()+","+mesa.getSecuencia_movimiento()+");' height='15' width='15' SRC='img/modifica.gif' alt='Editar'>");
					nuevoListado.add(tabla);
				}
				
				return nuevoListado;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int ObtieneCantidadArchivos(String codigo_documento) throws Exception{
		// TODO Auto-generated method stub
		return rDao.ObtieneCantidadArchivos(codigo_documento);
	}

	public void actualizarEstadoArchivos(String codigo_oficina,int correlativo,int codigo_documento,int codigo_expediente ,int secuencia_movimiento,String usuario,int codigo_recepcion) throws Exception{
		rDao.actualizarEstadoArchivos(codigo_oficina,correlativo,codigo_documento,codigo_expediente,secuencia_movimiento,usuario,codigo_recepcion);
				
	}
	

}
