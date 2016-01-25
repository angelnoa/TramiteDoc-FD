package tramitedoc.concytec.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import tramitedoc.concytec.bean.BCorrelativos;
import tramitedoc.concytec.bean.BFondo;
import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.bean.BOficinas;
import tramitedoc.concytec.bean.BReporteConsolidadoTransacciones;
import tramitedoc.concytec.bean.BTipoDocumento;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.dao.interfaces.IReportesDAO;
import tramitedoc.concytec.util.Funciones;

public class SqlReportesDAO implements IReportesDAO {
	protected  Log log = LogFactory.getLog(SqlReportesDAO.class);
	
	public Collection of_lista_oficinas(Connection cnx, BUsuario usuario) {
		// TODO Auto-generated method stub

			
	        //log.info("ENTRO A  Lista de oficinas");
			Collection ListaOficinas = new ArrayList();
			
			BOficinas BOficinasVO = null;
			Statement us  = null;
			ResultSet rs = null;
	       
			
	        try {	
	        	String ls_sql ="";
	        	if(usuario.getFlag().equals("N")){
	        		ls_sql = "select codigo_oficina,descripcion_oficina,nombre_corto,siglas_oficina " +
        			"from tramite.oficinas where codigo_oficina in (select codigo_oficina from tramite.personal where c_usuario = '"+usuario.getUsuario().trim()+"') " +
        					" ";
				}else {
					ls_sql = "select codigo_oficina,descripcion_oficina,nombre_corto,siglas_oficina " +
        			"from tramite.oficinas order by orden ";
				}
	        	       	
	        	
		    	us = cnx.createStatement();
	    		rs = us.executeQuery(ls_sql);
	    		
				while ( rs.next() ) { 
					
					BOficinasVO = new BOficinas();
					
					BOficinasVO.setCodigo_oficina(rs.getString("codigo_oficina"));
					BOficinasVO.setDescripcion_oficina(rs.getString("descripcion_oficina"));
					BOficinasVO.setNombre_corto(rs.getString("nombre_corto"));
					BOficinasVO.setSiglas_oficina(rs.getString("siglas_oficina"));
					
					
					ListaOficinas.add(BOficinasVO);

				}	
	            
				rs.close();
				
	            
	        } catch (SQLException e) {
	             e.printStackTrace();
	        }
	        return ListaOficinas;
		                
		}
	
	public Collection documentos_pendientes_oficina(Connection cnx,
			Date fecha_inicio, Date fecha_fin, String codigo_oficina) {
		log.info("Entry documentos_pendientes_oficina...");
        Collection listado = null;
 		
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			StringBuffer ls_sql = new StringBuffer("select md.codigo_documento as codigo_documento," +
	        			"md.codigo_oficina,md.fecha_movimiento as fecha_movimiento,md.hora_movimiento as" +
	        			" hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento as" +
	        			" secuencia_movimiento,md.ultimo_usuario," +
 				"md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta," +
 				"md.codigo_expediente,md.numero_referencia,md.oficina_origen,md.oficina_deriva,md.codigo_recepcion,md.destinatario as ofidestinatario,tp.descripcion_tipo," +
 				"dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant," +
 				"de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente," +
 				"de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as" +
 				" descripcion_destino,pr.codigo_personal,pr.nombre_personal " +
 				",(select to_date(fecha_movimiento,'dd/MM/YYYY')+ hora_movimiento from " +
		 		" tramite.recorrido_doc where codigo_documento = md.codigo_documento and secuencia_movimiento = md.secuencia_movimiento order by 1 limit 1) as fecha_envio,"+
		 		" (select to_date(fecha_movimiento,'dd/MM/YYYY')+ hora_movimiento from "+
		 		" tramite.recorrido_doc where codigo_documento = md.codigo_documento and secuencia_movimiento = md.secuencia_movimiento and estado_movimiento = 3 limit 1) as fecha_recepcion, "+
		 		" (select to_date(fecha_movimiento,'dd/MM/YYYY')+ hora_movimiento from "+
				" tramite.recorrido_doc where codigo_documento = md.codigo_documento and secuencia_movimiento = md.secuencia_movimiento and estado_movimiento = 5 limit 1) as fecha_derivacion," +
				" md.tipo_envio "+
 				" from tramite.movimientos_doc" +
 				" md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de," +
 				"tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr where" +
 				" tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and" +
 				" dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento" +
 				" and de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and" +
 				" de.destino_documento=of_a.codigo_oficina " +
 				" and md.estado_movimiento = 3");
			if(codigo_oficina!=null && !codigo_oficina.equals("0")){
				ls_sql.append(" and md.codigo_oficina = "+codigo_oficina.trim());
			}
			if(fecha_inicio!=null){
				ls_sql.append(" and to_date(md.fecha_movimiento,'dd/MM/yyyy')>'"+fecha_inicio.toString()+"'::date");	
			}
			if(fecha_fin!=null){
				ls_sql.append(" and to_date(md.fecha_movimiento,'dd/MM/yyyy')<'"+fecha_fin.toString()+"'::date");
			}
			
 					
			ls_sql.append(" order by codigo_documento asc,secuencia_movimiento asc");
			
			log.info(ls_sql);
			String query = ls_sql.toString();
			us = cnx.prepareStatement(query);
			rs = us.executeQuery();

			listado = new ArrayList();
			Funciones funciones=new Funciones();
			
			while (rs.next()) {
				BMesaPartes BMesaPartesVO = new BMesaPartes();				
				
				BMesaPartesVO.setTipo_envio(rs.getString("tipo_envio"));
				BMesaPartesVO.setFecha_envio(rs.getTimestamp("fecha_envio"));
				BMesaPartesVO.setFecha_recepcion(rs.getTimestamp("fecha_recepcion"));
				BMesaPartesVO.setFecha_derivacion(rs.getTimestamp("fecha_derivacion"));
				BMesaPartesVO.setTiempo_bandeja_automatico();
				
				BMesaPartesVO.setCodigo_documento(rs.getString("codigo_documento"));
				BMesaPartesVO.setFecha_movimiento(rs.getString("fecha_movimiento"));
				BMesaPartesVO.setHora_movimiento(rs.getString("hora_movimiento"));
				BMesaPartesVO.setEstado_movimiento(rs.getString("estado_movimiento"));
				BMesaPartesVO.setSecuencia_movimiento(rs.getString("secuencia_movimiento"));
				
				BMesaPartesVO.setUltimo_usuario(rs.getString("ultimo_usuario"));
				BMesaPartesVO.setDoc_deriva(rs.getString("doc_deriva"));
				
				BMesaPartesVO.setObserva_movimiento(rs.getString("observa_movimiento"));
				BMesaPartesVO.setCodigo_motivo(rs.getString("codigo_motivo"));
				BMesaPartesVO.setFecha_rpta(rs.getString("fecha_rpta"));
				BMesaPartesVO.setCodigo_expediente(rs.getString("codigo_expediente"));
				BMesaPartesVO.setOficina_origen(rs.getString("oficina_origen"));
				BMesaPartesVO.setOficina_deriva(rs.getString("oficina_deriva"));
				BMesaPartesVO.setCodigo_inicia(rs.getString("codigo_inicia"));
				BMesaPartesVO.setDescripcion_tipo(rs.getString("descripcion_tipo"));
				BMesaPartesVO.setNumero_documento(rs.getString("numero_documento"));
				BMesaPartesVO.setNumero_documento_ant(rs.getString("numero_documento_ant"));
				BMesaPartesVO.setNaturaleza_documento(rs.getString("naturaleza_documento"));
				BMesaPartesVO.setAsunto_documento(rs.getString("asunto_documento"));
				BMesaPartesVO.setOrigen_documento(rs.getString("origen_documento"));
				BMesaPartesVO.setDestino_documento(rs.getString("destino_documento"));
				BMesaPartesVO.setDesc_origen(rs.getString("desc_origen"));
				BMesaPartesVO.setTipo(rs.getString("tipo"));
				BMesaPartesVO.setRemitente(rs.getString("remitente"));
				
				BMesaPartesVO.setDestinatario(rs.getString("destinatario"));
				BMesaPartesVO.setCodigo_oficina(rs.getString("codigo_oficina"));
				BMesaPartesVO.setDescripcion_oficina(rs.getString("descripcion_oficina"));
				BMesaPartesVO.setDescripcion_destino(rs.getString("descripcion_destino"));
				BMesaPartesVO.setCodigo_personal(rs.getString("codigo_personal"));
				BMesaPartesVO.setNombre_personal(funciones.of_nombre_persona(cnx, rs.getString("ofidestinatario")));
				//BMesaPartesVO.setCorrelativo_deriva(rs.getString("correlativo_deriva"));
				BMesaPartesVO.setCodigo_recepcion(rs.getString("codigo_recepcion"));
				BMesaPartesVO.setNumero_referencia(rs.getString("numero_referencia"));
				BMesaPartesVO.setNombre_oficina_origen(funciones.of_nombre_corto_oficina(cnx, rs.getString("oficina_origen")));
				BMesaPartesVO.setNombre_oficina_destino(funciones.of_nombre_corto_oficina(cnx, rs.getString("oficina_deriva")));
	
				listado.add(BMesaPartesVO);
			}
			

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("Finally documentos_pendientes_oficina...");
		return listado;

	}
	
	public Collection  consolidado_transacciones(
			Connection cnx, Date fecha_inicio, Date fecha_fin) {
		// TODO Auto-generated method stub
		Collection listado = null;
		

		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			StringBuffer ls_sql = new StringBuffer("select codigo_oficina, nombre_corto,descripcion_oficina from tramite.oficinas where es_activo like 'A' order by orden;");
			
			us = cnx.prepareStatement(ls_sql.toString());
			rs = us.executeQuery();

			listado = new ArrayList();
			
			while (rs.next()) {
				BReporteConsolidadoTransacciones rCons = new BReporteConsolidadoTransacciones();
				BOficinas oficina = new BOficinas();
				oficina.setCodigo_oficina(rs.getString("codigo_oficina"));
				oficina.setNombre_corto(rs.getString("nombre_corto"));
				oficina.setDescripcion_oficina(rs
						.getString("descripcion_oficina"));
				rCons.setOficina(oficina);
				rCons.setCreados(creados(cnx, fecha_inicio, fecha_fin,oficina.getCodigo_oficina()));
			
				rCons.setDerivados(derivados(cnx, fecha_inicio, fecha_fin,oficina.getCodigo_oficina()));
				
				rCons.setArchivados(archivados(cnx, fecha_inicio, fecha_fin,oficina.getCodigo_oficina()));
				rCons.setAnulados(anulados(cnx, fecha_inicio, fecha_fin, oficina.getCodigo_oficina()));
					
				listado.add(rCons);
			}
			

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listado;
	}

	public Collection  consolidado_transaccionesn(
			Connection cnx, Date fecha_inicio, Date fecha_fin, String ls_fecha_inicio, String ls_fecha_fin, String ls_codigo_motivo, String grupo_oficina) {
		// TODO Auto-generated method stub
		Collection listado = null;
		log.info("consolidado_transaccionesn........");
		int tempa;
		int tempb;
		String ls_codigo_oficina;
		int tipoa=1;
		int tipob=0;
		
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			
						
			StringBuffer ls_sql = new StringBuffer("select codigo_oficina, nombre_corto,descripcion_oficina from tramite.oficinas ");
			
			if(!grupo_oficina.equals("") && !grupo_oficina.equals("0")){
				ls_sql.append("where es_activo like 'A' and grupo_oficina="+grupo_oficina);
			}
			
			ls_sql.append(" order by orden");
			
			log.info("Query-> consolidado_transaccionesn :"+ls_sql.toString());
			String action;
			us = cnx.prepareStatement(ls_sql.toString());
			rs = us.executeQuery();

			listado = new ArrayList();
			
			while (rs.next()) {
				Hashtable objListaDoc = new Hashtable();
				BOficinas oficina = new BOficinas();
				oficina.setCodigo_oficina(rs.getString("codigo_oficina"));
				oficina.setNombre_corto(rs.getString("nombre_corto"));
				oficina.setDescripcion_oficina(rs.getString("descripcion_oficina"));
				ls_codigo_oficina = oficina.getNombre_corto();
				tempa = porrecibir(cnx, fecha_inicio, fecha_fin, oficina.getCodigo_oficina(),ls_codigo_motivo);
				tempb = ntramite(cnx, fecha_inicio, fecha_fin, oficina.getCodigo_oficina(),ls_codigo_motivo);
				
				objListaDoc.put("oficina",ls_codigo_oficina);
				objListaDoc.put("porrecibir", tempa);
				objListaDoc.put("ntramite", tempb);
				
				action = "<A href='javascript:ayuda_reportes("+oficina.getCodigo_oficina()+","+tipoa+");'>"+tempa+"</A>";
				objListaDoc.put("actionporrecibir", action);
				action = "<A href='javascript:ayuda_reportes("+oficina.getCodigo_oficina()+","+tipob+");'>"+tempb+"</A>";
				objListaDoc.put("actionntramite", action);
				listado.add(objListaDoc);
			}
			

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listado;
	}
	
	public Collection  consolidado_transaccionesn(
			Connection cnx, Date fecha_inicio, Date fecha_fin, String ls_fecha_inicio, String ls_fecha_fin, String ls_codigo_motivo) {
		// TODO Auto-generated method stub
		Collection listado = null;
		log.info("consolidado_transaccionesn........");
		int tempa;
		int tempb;
		String ls_codigo_oficina;
		int tipoa=1;
		int tipob=0;
		
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			
						
			StringBuffer ls_sql = new StringBuffer("select codigo_oficina, nombre_corto,descripcion_oficina from tramite.oficinas where es_activo like 'A' order by orden");
			
			String action;
			us = cnx.prepareStatement(ls_sql.toString());
			rs = us.executeQuery();

			listado = new ArrayList();
			
			while (rs.next()) {
				Hashtable objListaDoc = new Hashtable();
				BOficinas oficina = new BOficinas();
				oficina.setCodigo_oficina(rs.getString("codigo_oficina"));
				oficina.setNombre_corto(rs.getString("nombre_corto"));
				oficina.setDescripcion_oficina(rs.getString("descripcion_oficina"));
				ls_codigo_oficina = oficina.getNombre_corto();
				tempa = porrecibir(cnx, fecha_inicio, fecha_fin, oficina.getCodigo_oficina(),ls_codigo_motivo);
				tempb = ntramite(cnx, fecha_inicio, fecha_fin, oficina.getCodigo_oficina(),ls_codigo_motivo);
				
				objListaDoc.put("oficina",ls_codigo_oficina);
				objListaDoc.put("porrecibir", tempa);
				objListaDoc.put("ntramite", tempb);
				
				action = "<A href='javascript:ayuda_reportes("+oficina.getCodigo_oficina()+","+tipoa+");'>"+tempa+"</A>";
				objListaDoc.put("actionporrecibir", action);
				action = "<A href='javascript:ayuda_reportes("+oficina.getCodigo_oficina()+","+tipob+");'>"+tempb+"</A>";
				objListaDoc.put("actionntramite", action);
				listado.add(objListaDoc);
			}
			

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listado;
	}
	
	private int porrecibir(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina, String ls_codigo_motivo) {
		int cantidad=0;
		
		Collection ListaMesaPartes = new ArrayList();
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			StringBuffer ls_sql = new StringBuffer("select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento," +
	 		"md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento," +
	 		"md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta," +
	 		"md.codigo_expediente,md.oficina_origen,md.oficina_deriva,md.correlativo_deriva,md.observa_registro,md.codigo_recepcion," +
	 		"md.flag,tp.descripcion_tipo,md.desc_origen,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento," +
	 		"dc.numero_documento_ant,dc.observa_documento,dc.fecha_registro,dc.hora_registro,dc.dirigido,de.origen_documento,de.destino_documento,de.desc_origen as nombre_procedencia," +
	 		"de.tipo,de.remitente,md.destinatario,de.firmadopor,de.medio,de.codigo_solicitud,of.codigo_oficina,of.descripcion_oficina," +
	 		"of_a.descripcion_oficina as descripcion_destino,pr.codigo_personal,pr.nombre_personal, md.doc_resp" +
	 		" from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc," +
	 		"tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr" +
	 		" where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and" +
	 		" dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento" +
	 		" and de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and" +
	 		" de.destino_documento=of_a.codigo_oficina and md.estado_movimiento ='2' and ");
			
			ls_sql.append(" md.codigo_fondo like '01' and md.codigo_oficina like '"+ codigo_oficina+"'");
			
			if(ls_codigo_motivo!=null && !(ls_codigo_motivo.equals("0"))){
				ls_sql.append(" and md.codigo_motivo ="+ls_codigo_motivo);	
			}
	 		if(fecha_inicio!=null){
				ls_sql.append(" and to_date(dc.fecha_registro,'dd/MM/yyyy')>='"+fecha_inicio.toString()+"'");	
			}
			if(fecha_fin!=null){
				ls_sql.append(" and to_date(dc.fecha_registro,'dd/MM/yyyy')<='"+fecha_fin.toString()+"'");
			}
			ls_sql.append(" order by md.codigo_documento desc");
					
	
			
			us = cnx.prepareStatement(ls_sql.toString());
			rs = us.executeQuery();
			
			/*******CIRUGIA*******/
	     	 String ls_ant_codigo_documento="";
	    	 String ls_codigo_documento="";
	    	 String ls_codigo_documento_cont="";
	    	 int contador=0;
	    	
			
	    	 while ( rs.next() ) { 
					
	 			
					contador=contador+1;
					Hashtable objListaDoc = new Hashtable();
					
					
					ls_codigo_documento=rs.getString("codigo_documento").trim();
					
					if(contador==1){						
                       
						objListaDoc.put("codigo_documento", ls_codigo_documento_cont);
												
						ListaMesaPartes.add(objListaDoc);
				
						ls_ant_codigo_documento=ls_codigo_documento;
					
                 }
					else if(contador>1){
						
						if(ls_ant_codigo_documento.equals(ls_codigo_documento)){
							
						}else {
								objListaDoc.put("codigo_documento", ls_codigo_documento_cont);
								
								ListaMesaPartes.add(objListaDoc);

						}

						ls_ant_codigo_documento=ls_codigo_documento;

					}
				}
			
			
			/*************TERMINA CIRUGIA****************/
	    	rs.close();
			
			cantidad = ListaMesaPartes.size();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;

	}
	
	public Collection porrecibirreporte(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina,String ls_codigo_motivo) {
				
		Collection ListaMesaPartes = new ArrayList();
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			StringBuilder ls_sql = new StringBuilder("select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento," +
	 		"md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento," +
	 		"md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta," +
	 		"md.codigo_expediente,md.oficina_origen,md.oficina_deriva,md.correlativo_deriva,md.observa_registro,md.codigo_recepcion," +
	 		"md.flag,tp.descripcion_tipo,md.desc_origen,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento," +
	 		"dc.numero_documento_ant,dc.observa_documento,dc.fecha_registro,dc.hora_registro,dc.dirigido,de.origen_documento,de.destino_documento,de.desc_origen as nombre_procedencia," +
	 		"de.tipo,de.remitente,md.destinatario,de.firmadopor,de.medio,de.codigo_solicitud,of.codigo_oficina,of.descripcion_oficina," +
	 		"of_a.descripcion_oficina as descripcion_destino,pr.codigo_personal,pr.nombre_personal, md.doc_resp" +
	 		
	 		", (select per.nombre_personal from tramite.personal per where per.codigo_personal=md.destinatario) as persona_destino "+
	 		
	 		" from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc," +
	 		"tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr" +
	 		" where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and" +
	 		" dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento" +
	 		" and de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and" +
	 		" de.destino_documento=of_a.codigo_oficina and md.estado_movimiento ='2' and ");
			
			ls_sql.append(" md.codigo_fondo like '01' and md.codigo_oficina like '"+codigo_oficina+"'");
			if(ls_codigo_motivo!=null && !(ls_codigo_motivo.equals("0"))){
				ls_sql.append(" and md.codigo_motivo ="+ls_codigo_motivo);	
			}
	 		if(fecha_inicio!=null){
	 			ls_sql.append(" and to_date(dc.fecha_registro,'dd/MM/yyyy')>='"+fecha_inicio.toString()+"'");	
			}
			if(fecha_fin!=null){
				ls_sql.append(" and to_date(dc.fecha_registro,'dd/MM/yyyy')<='"+fecha_fin.toString()+"'");
			}
			ls_sql.append(" order by md.codigo_documento desc");
					
	
			log.info("sql-> "+ls_sql.toString());
			us = cnx.prepareStatement(ls_sql.toString());
			rs = us.executeQuery();
			
			/*******CIRUGIA*******/
			String ls_codigo_documento="";
	    	String ls_ant_codigo_documento="";
			String ls_num_upload="";
	    	 String ls_naturaleza_documento="";
	    	 String ls_estado_mov="";
	    	 int contador=0;
	    	 Funciones funciones=new Funciones();

			/* String ls_nomb_upload="";
	    	 String ls_codigo_recepcion="";
	    	 String ls_codigo_expediente="";
	    	 String ls_secuencia_movimiento="";
	    	 String ls_ant_codigo_recepcion="";
	    	 String ls_ant_codigo_expediente="";
	    	 String ls_ant_secuencia_movimiento="";
	    	 String ls_numero_referencia="";
	    	 String ls_oficina_origen="";
	    	 String ls_oficina_deriva="";
	    	 String ls_estado_movimiento="";*/

	    	// log.info("Antes del While....");
	    	 
	    	 while ( rs.next() ) { 
	    		 
						contador=contador+1;
						Hashtable objListaDoc = new Hashtable();
						ls_codigo_documento=rs.getString("codigo_documento").trim();
						
						/*ls_codigo_recepcion=rs.getString("codigo_recepcion").trim();
						ls_codigo_expediente=rs.getString("codigo_expediente").trim();
						ls_secuencia_movimiento=rs.getString("secuencia_movimiento").trim();*/
						
						if(contador==1){
							
							objListaDoc.put("codigo_documento", rs.getString("codigo_documento").trim());
							objListaDoc.put("codigo_expediente",rs.getString("codigo_expediente").trim());
							objListaDoc.put("numero_documento", rs.getString("numero_documento").trim());
							objListaDoc.put("codigo_oficina", funciones.of_nombre_corto_oficina(cnx, rs.getString("codigo_oficina").trim()));
							objListaDoc.put("oficina_origen", funciones.of_nombre_corto_oficina(cnx, rs.getString("oficina_origen").trim()));

							objListaDoc.put("oficina_deriva", funciones.of_nombre_corto_oficina(cnx, rs.getString("oficina_deriva").trim()));

							ls_estado_mov=rs.getString("estado_movimiento").trim();
							if(ls_estado_mov.equals("5")){
								objListaDoc.put("estado_movimiento", "<FONT color='#996633'>"+funciones.of_nombre_estado(cnx,ls_estado_mov )+"</FONT>");	
							}
							else if(ls_estado_mov.equals("2")){
								objListaDoc.put("estado_movimiento", "<FONT color='#FF0000 '>"+funciones.of_nombre_estado(cnx,ls_estado_mov)+"</FONT>");	
							}
							else{
								objListaDoc.put("estado_movimiento", "<FONT color='#00CC00'>"+funciones.of_nombre_estado(cnx, ls_estado_mov)+"</FONT>");
							}
													
							objListaDoc.put("fecha_registro", rs.getString("fecha_registro"));
							
							//objListaDoc.put("fecha_registro",rs.getString("fecha_registro"));
							
							objListaDoc.put("fecha_movimiento", rs.getString("fecha_movimiento"));
							objListaDoc.put("hora_registro", rs.getString("hora_registro"));
							objListaDoc.put("hora", rs.getString("hora_movimiento"));
							objListaDoc.put("asunto_documento", rs.getString("asunto_documento"));
							objListaDoc.put("codigo_recepcion", rs.getString("codigo_recepcion"));
							
							objListaDoc.put("secuencia_movimiento", rs.getString("secuencia_movimiento"));
							objListaDoc.put("codigo_inicia", rs.getString("codigo_inicia"));
							//objListaDoc.put("codigo_oficina", funciones.of_nombre_corto_oficina(cnx, rs.getString("codigo_oficina").trim()));
							//objListaDoc.put("codigo_expediente", rs.getString("codigo_expediente"));
							ls_naturaleza_documento=rs.getString("naturaleza_documento");
							if(ls_naturaleza_documento.equals("I")){
								ls_naturaleza_documento="Interno";
							}else{
								ls_naturaleza_documento="Externo";
							}
							objListaDoc.put("naturaleza_documento", ls_naturaleza_documento);
							
							 ls_num_upload=funciones.of_numero_doc_upload(cnx,rs.getString("codigo_documento").trim());
							
							if(ls_num_upload.equals("0")){
								objListaDoc.put("view_archivo", "<font color='#0000FF'>--------</font>");
							}else {
								objListaDoc.put("view_archivo", "<A id='A1' href='javascript:VerArchivos("+rs.getString("codigo_documento").trim()+","+rs.getString("codigo_expediente").trim()+","+rs.getString("codigo_recepcion").trim()+");' >"+
								"<img src='img/copy_f2.png' height='20' alt='Ver Documento' border='0'></A>");
							}
							objListaDoc.put("firmadopor",rs.getString("nombre_procedencia")+" - " +rs.getString("firmadopor"));
							
							objListaDoc.put("persona_destino",rs.getString("persona_destino"));
							
							ListaMesaPartes.add(objListaDoc);
						
							ls_ant_codigo_documento=ls_codigo_documento;
							/*ls_ant_codigo_recepcion=ls_codigo_recepcion;
							ls_ant_codigo_expediente=ls_codigo_expediente;
							ls_ant_secuencia_movimiento=ls_secuencia_movimiento;*/
							
						}
						else if(contador>1){
							////log.info("Si el contador es mayor a cero es....: ");
							
							if(ls_ant_codigo_documento.equals(ls_codigo_documento)){
								////log.info("Los codigos son iguales me imprime una lista ji ji....: ");
								
								
								
								////log.info("--------------------------------------------------- ");
							}else {
								
									
								//objListaDoc.put("codigo_documento", rs.getString("codigo_documento"));
								objListaDoc.put("codigo_documento", rs.getString("codigo_documento").trim());
								objListaDoc.put("codigo_expediente", rs.getString("codigo_expediente").trim());
								objListaDoc.put("numero_documento", rs.getString("numero_documento").trim());
								objListaDoc.put("codigo_oficina", funciones.of_nombre_corto_oficina(cnx, rs.getString("codigo_oficina").trim()));
								objListaDoc.put("oficina_origen", funciones.of_nombre_corto_oficina(cnx, rs.getString("oficina_origen").trim()));

								objListaDoc.put("oficina_deriva", funciones.of_nombre_corto_oficina(cnx, rs.getString("oficina_deriva").trim()));

								
								//objListaDoc.put("oficina_deriva", funciones.of_nombre_oficina(cnx, rs.getString("oficina_deriva").trim()) );
								ls_estado_mov=rs.getString("estado_movimiento").trim();
								if(ls_estado_mov.equals("5")){
									objListaDoc.put("estado_movimiento", "<FONT color='#996633'>"+funciones.of_nombre_estado(cnx,ls_estado_mov )+"</FONT>");	
								}
								else if(ls_estado_mov.equals("2")){
									objListaDoc.put("estado_movimiento", "<FONT color='#FF0000 '>"+funciones.of_nombre_estado(cnx,ls_estado_mov)+"</FONT>");	
								}
								else{
									objListaDoc.put("estado_movimiento", "<FONT color='#00CC00'>"+funciones.of_nombre_estado(cnx, ls_estado_mov)+"</FONT>");
								}
								//objListaDoc.put("estado_movimiento", rs.getString("estado_movimiento"));
								
								objListaDoc.put("fecha_registro", rs.getString("fecha_registro"));
								
								//objListaDoc.put("fecha_registro",rs.getString("fecha_registro"));
								
								objListaDoc.put("fecha_movimiento", rs.getString("fecha_movimiento"));
								objListaDoc.put("hora_registro", rs.getString("hora_registro"));
								objListaDoc.put("hora", rs.getString("hora_movimiento"));
								objListaDoc.put("asunto_documento", rs.getString("asunto_documento"));
								objListaDoc.put("codigo_recepcion", rs.getString("codigo_recepcion"));
								
								objListaDoc.put("secuencia_movimiento", rs.getString("secuencia_movimiento"));
								objListaDoc.put("codigo_inicia", rs.getString("codigo_inicia"));
								//objListaDoc.put("codigo_oficina", funciones.of_nombre_corto_oficina(cnx, rs.getString("codigo_oficina").trim()));
								//objListaDoc.put("codigo_expediente", rs.getString("codigo_expediente"));
								ls_naturaleza_documento=rs.getString("naturaleza_documento");
								if(ls_naturaleza_documento.equals("I")){
									ls_naturaleza_documento="Interno";
								}else{
									ls_naturaleza_documento="Externo";
								}
								objListaDoc.put("naturaleza_documento", ls_naturaleza_documento);
								
								 ls_num_upload=funciones.of_numero_doc_upload(cnx,rs.getString("codigo_documento").trim());
								
								if(ls_num_upload.equals("0")){
									objListaDoc.put("view_archivo", "<font color='#0000FF'>--------</font>");
								}else {
									objListaDoc.put("view_archivo", "<A id='A1' href='javascript:VerArchivos("+rs.getString("codigo_documento").trim()+","+rs.getString("codigo_expediente").trim()+","+rs.getString("codigo_recepcion").trim()+");' >"+
									"<img src='img/copy_f2.png' height='20' alt='Ver Documento' border='0'></A>");
								}
								objListaDoc.put("firmadopor",rs.getString("nombre_procedencia")+" - " +rs.getString("firmadopor"));
			
								objListaDoc.put("persona_destino",rs.getString("persona_destino"));
								
								ListaMesaPartes.add(objListaDoc);

							}
							ls_ant_codigo_documento=ls_codigo_documento;
							/*ls_ant_codigo_recepcion=ls_codigo_recepcion;
							ls_ant_codigo_expediente=ls_codigo_expediente;
							ls_ant_secuencia_movimiento=ls_secuencia_movimiento;*/
						
							
				}	
	    	 }
		
			
			/*************TERMINA CIRUGIA****************/
	    	rs.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ListaMesaPartes;

	}
	
	private int ntramite(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina, String ls_codigo_motivo) {
		int cantidad=0;
		Collection ListaMesaPartes = new ArrayList();
		/**************/
		Statement us  = null;
		ResultSet rs = null;
		
	
		try {
			
			
			StringBuffer ls_sql = new StringBuffer("select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento," +
			"md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento," +
			"md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo," +
			"md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva," +
			"md.correlativo_deriva,md.observa_registro,md.codigo_recepcion,md.flag," +
			"tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento," +
			"dc.asunto_documento,dc.numero_documento_ant,de.origen_documento," +
			"de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario," +
			"of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as" +
			" descripcion_destino,pr.codigo_personal,pr.nombre_personal from" +
			" tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc," +
			"tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a," +
			"tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and " +
			"dc.codigo_documento=md.codigo_documento and " +
			"dc.codigo_documento=de.codigo_documento and " +
			"md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal" +
			" and md.codigo_oficina=of.codigo_oficina and " +
			"de.destino_documento=of_a.codigo_oficina and md.estado_movimiento = '3'");
			
			ls_sql.append(" and md.codigo_fondo like '01' and md.codigo_oficina like '"+codigo_oficina+"'"+ 
			" and md.codigo_documento not in (select codigo_documento from " +
			"tramite.movimientos_doc where estado_movimiento = '3' and");
			ls_sql.append(" codigo_fondo like '01' and codigo_oficina like '"+codigo_oficina+"' and flag ='I')");
						
			if(ls_codigo_motivo!=null && !(ls_codigo_motivo.equals("0"))){
				ls_sql.append(" and md.codigo_motivo ="+ls_codigo_motivo);	
			}
			if(fecha_inicio!=null){
	 			ls_sql.append(" and to_date(dc.fecha_registro,'dd/MM/yyyy')>='"+fecha_inicio.toString()+"'");	
			}
			if(fecha_fin!=null){
				ls_sql.append(" and to_date(dc.fecha_registro,'dd/MM/yyyy')<='"+fecha_fin.toString()+"'");
			}
			ls_sql.append("order by md.codigo_documento desc");
								
					
	 		us = cnx.createStatement();
	    	rs = us.executeQuery(ls_sql.toString());
			
			/*******CIRUGIA*******/
    		 
	    	 String ls_ant_codigo_documento="";
	    	 String ls_codigo_documento="";
	    	 String ls_codigo_documento_cont="";
	    	     	 
	    	 int contador=0;

	    	 while ( rs.next() ) { 
				
				contador=contador+1;
				Hashtable objListaDoc = new Hashtable();
				ls_codigo_documento=rs.getString("codigo_documento").trim();
				
					if(contador==1){
	
				objListaDoc.put("codigo_documento", ls_codigo_documento_cont);
								
				ListaMesaPartes.add(objListaDoc);

				ls_ant_codigo_documento=ls_codigo_documento;

				}
				else if(contador>1){
											
						if(ls_ant_codigo_documento.equals(ls_codigo_documento)){
							////log.info("Los codigos son iguales me imprime una lista ji ji....: ");
							
							////log.info("--------------------------------------------------- ");
						}else {
							
								objListaDoc.put("codigo_documento", ls_codigo_documento_cont);
								ListaMesaPartes.add(objListaDoc);
								
						}
						
						ls_ant_codigo_documento=ls_codigo_documento;
												
						}
			}	
            			
			/*************TERMINA CIRUGIA****************/
	    	rs.close();
			
			cantidad = ListaMesaPartes.size();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;

	}
	
	public Collection ntramitereporte(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina, String ls_codigo_motivo) {
		
		Collection ListaMesaPartes = new ArrayList();
		/**************/
		BCorrelativos BCorrelativosVO = null;
		BTipoDocumento BTipoDocumentoVO = null;
		BOficinas BOficinasVO = null;
		BFondo BFondoVO = null;
		BMesaPartes BMesaPartesVO = null;
		Statement us  = null;
		ResultSet rs = null;
		
       String ls_desde;
       String ls_hasta;
       
       	SimpleDateFormat fechaActual  = new SimpleDateFormat ("dd/MM/yyyy");
       	SimpleDateFormat fechaActualFormat  = new SimpleDateFormat ("yyyy-dd-MM");
    	SimpleDateFormat fechaConsulta = new SimpleDateFormat ("yyyyMMdd");
    	SimpleDateFormat year = new SimpleDateFormat ("yyyy");
    	
    	Date d= new Date(); 
    	String fecha_documento  = fechaActual.format(d);
    	String fechaconsulta = fechaConsulta.format(d);
    	String fechaformat = fechaActualFormat.format(d);
    	String yearactual = year.format(d);
    	String estado_documento = "1";
    	String fondo = "01";
    	String ls_correla= "";
    	String ls_num_upload="";
    	String ls_valor  = "";
    	
    	int li_retorno=0;
        	  	
    	Date fecha_recep = new Date(); // fecha y hora locales 
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
		    
		Funciones funcion=new Funciones();
		
		try {
			
			
			StringBuffer ls_sql = new StringBuffer("select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento," +
			"md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento," +
			"md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,dc.fecha_registro," +
			"md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva," +
			"md.correlativo_deriva,md.observa_registro,md.codigo_recepcion,md.flag," +
			"tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento," +
			"dc.asunto_documento,dc.numero_documento_ant,de.origen_documento," +
			"de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario,de.firmadopor," +
			"of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as" +
			" descripcion_destino,pr.codigo_personal,pr.nombre_personal " +
			
			" ,(select per.nombre_personal from tramite.personal per where per.c_usuario=md.ultimo_usuario and per.validacion=1) as persona_destino "+ 
			
			" from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc," +
			"tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr " +
			" where tp.codigo_tipo=dc.codigo_tipo and " +
			"dc.codigo_documento=md.codigo_documento and " +
			"dc.codigo_documento=de.codigo_documento and " +
			"md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal" +
			" and md.codigo_oficina=of.codigo_oficina and " +
			"de.destino_documento=of_a.codigo_oficina and md.estado_movimiento = '3'" +
			" and md.codigo_fondo like '01' and md.codigo_oficina like '"+codigo_oficina+"'"+ 
			" and md.codigo_documento not in (select codigo_documento from " +
			"tramite.movimientos_doc where estado_movimiento = '3' and" +
			" codigo_fondo like '01' and codigo_oficina like '"+codigo_oficina+"' and flag ='I')");
			
			if(ls_codigo_motivo!=null && !(ls_codigo_motivo.equals("0"))){
				ls_sql.append(" and md.codigo_motivo ="+ls_codigo_motivo);	
			}
	 		if(fecha_inicio!=null){
	 			ls_sql.append(" and to_date(dc.fecha_registro,'dd/MM/yyyy')>='"+fecha_inicio.toString()+"'");	
			}
			if(fecha_fin!=null){
				ls_sql.append(" and to_date(dc.fecha_registro,'dd/MM/yyyy')<='"+fecha_fin.toString()+"'");
			}
			ls_sql.append("order by md.codigo_documento desc");
								
			log.info("sql-> "+ls_sql.toString());		
	 		us = cnx.createStatement();
	    	rs = us.executeQuery(ls_sql.toString());
			
			/*******CIRUGIA*******/

    		
			String ls_nomb_upload="";
	    	// Funciones funciones=new Funciones();
	    	 String ls_codigo_recepcion="";
	    	 String ls_codigo_documento="";
	    	 String ls_codigo_expediente="";
	    	 String ls_secuencia_movimiento="";
	    	 
	    	 String ls_ant_codigo_recepcion="";
	    	 String ls_ant_codigo_documento="";
	    	 String ls_ant_codigo_expediente="";
	    	 String ls_ant_secuencia_movimiento="";
	    	 String ls_naturaleza_documento="";
	    	 String ls_estado_mov="";
	    	 String ls_numero_referencia="";
	    	 String ls_oficina_origen="";
	    	 String ls_oficina_deriva="";
	    	 String ls_estado_movimiento="";
	    	 int contador=0;
	    	 Funciones funciones=new Funciones();
	    	// log.info("Antes del While....");
	    	 
	    	 while ( rs.next() ) { 
	    		 
					contador=contador+1;
					 Hashtable objListaDoc = new Hashtable();
					 ////log.info("Imprime listas XXX...." + contador);
						////log.info("Imprime listas...." + contador);
					 //log.info("Dentro del While....");
						ls_codigo_recepcion=rs.getString("codigo_recepcion").trim();
						ls_codigo_documento=rs.getString("codigo_documento").trim();
						ls_codigo_expediente=rs.getString("codigo_expediente").trim();
						ls_secuencia_movimiento=rs.getString("secuencia_movimiento").trim();
						
						if(contador==1){
							
							objListaDoc.put("codigo_documento",rs.getString("codigo_documento").trim());
							objListaDoc.put("codigo_expediente", rs.getString("codigo_expediente").trim());
							objListaDoc.put("numero_documento", rs.getString("numero_documento").trim());
							objListaDoc.put("codigo_oficina", funciones.of_nombre_corto_oficina(cnx, rs.getString("codigo_oficina").trim()));
							objListaDoc.put("oficina_origen", funciones.of_nombre_corto_oficina(cnx, rs.getString("oficina_origen").trim()));

							objListaDoc.put("oficina_deriva", funciones.of_nombre_corto_oficina(cnx, rs.getString("oficina_deriva").trim()));

							
							//objListaDoc.put("oficina_deriva", funciones.of_nombre_oficina(cnx, rs.getString("oficina_deriva").trim()) );
							ls_estado_mov=rs.getString("estado_movimiento").trim();
							if(ls_estado_mov.equals("5")){
								objListaDoc.put("estado_movimiento", "<FONT color='#996633'>"+funciones.of_nombre_estado(cnx,ls_estado_mov )+"</FONT>");	
							}
							else if(ls_estado_mov.equals("2")){
								objListaDoc.put("estado_movimiento", "<FONT color='#FF0000 '>"+funciones.of_nombre_estado(cnx,ls_estado_mov)+"</FONT>");	
							}
							else{
								objListaDoc.put("estado_movimiento", "<FONT color='#00CC00'>"+funciones.of_nombre_estado(cnx, ls_estado_mov)+"</FONT>");
							}
							//objListaDoc.put("estado_movimiento", rs.getString("estado_movimiento"));
							
							objListaDoc.put("fecha_registro", rs.getString("fecha_registro"));
							
							//objListaDoc.put("fecha_registro",rs.getString("fecha_registro"));
							
							objListaDoc.put("fecha_movimiento", rs.getString("fecha_movimiento"));
							//objListaDoc.put("hora_registro", rs.getString("hora_registro"));
							objListaDoc.put("hora", rs.getString("hora_movimiento"));
							objListaDoc.put("asunto_documento", rs.getString("asunto_documento"));
							objListaDoc.put("codigo_recepcion", rs.getString("codigo_recepcion"));
							
							objListaDoc.put("secuencia_movimiento", ls_secuencia_movimiento);
							objListaDoc.put("codigo_inicia", rs.getString("codigo_inicia"));
							//objListaDoc.put("codigo_oficina", funciones.of_nombre_corto_oficina(cnx, rs.getString("codigo_oficina").trim()));
							//objListaDoc.put("codigo_expediente", rs.getString("codigo_expediente"));
							ls_naturaleza_documento=rs.getString("naturaleza_documento");
							if(ls_naturaleza_documento.equals("I")){
								ls_naturaleza_documento="Interno";
							}else{
								ls_naturaleza_documento="Externo";
							}
							objListaDoc.put("naturaleza_documento", ls_naturaleza_documento);
							
							 ls_num_upload=funciones.of_numero_doc_upload(cnx,rs.getString("codigo_documento").trim());
							
							if(ls_num_upload.equals("0")){
								objListaDoc.put("view_archivo", "<font color='#0000FF'>--------</font>");
							}else {
								objListaDoc.put("view_archivo", "<A id='A1' href='javascript:VerArchivos("+rs.getString("codigo_documento").trim()+","+rs.getString("codigo_expediente").trim()+","+rs.getString("codigo_recepcion").trim()+");' >"+
								"<img src='img/copy_f2.png' height='20' alt='Ver Documento' border='0'></A>");
							}
							objListaDoc.put("firmadopor",rs.getString("desc_origen").trim()+" - " +rs.getString("firmadopor").trim());
							
							objListaDoc.put("persona_destino",rs.getString("persona_destino"));
							
							ListaMesaPartes.add(objListaDoc);
						
							ls_ant_codigo_recepcion=ls_codigo_recepcion;
							ls_ant_codigo_documento=ls_codigo_documento;
							ls_ant_codigo_expediente=ls_codigo_expediente;
							ls_ant_secuencia_movimiento=ls_secuencia_movimiento;
							
						}
						else if(contador>1){
							////log.info("Si el contador es mayor a cero es....: ");
							
							if(ls_ant_codigo_documento.equals(ls_codigo_documento)){
								////log.info("Los codigos son iguales me imprime una lista ji ji....: ");
								
								
								
								////log.info("--------------------------------------------------- ");
							}else {
								
								
								objListaDoc.put("codigo_documento", rs.getString("codigo_documento").trim());
								objListaDoc.put("codigo_expediente",rs.getString("codigo_expediente").trim());
								objListaDoc.put("numero_documento", rs.getString("numero_documento").trim());
								objListaDoc.put("codigo_oficina", funciones.of_nombre_corto_oficina(cnx, rs.getString("codigo_oficina").trim()));
								objListaDoc.put("oficina_origen", funciones.of_nombre_corto_oficina(cnx, rs.getString("oficina_origen").trim()));

								objListaDoc.put("oficina_deriva", funciones.of_nombre_corto_oficina(cnx, rs.getString("oficina_deriva").trim()));

								
								//objListaDoc.put("oficina_deriva", funciones.of_nombre_oficina(cnx, rs.getString("oficina_deriva").trim()) );
								ls_estado_mov=rs.getString("estado_movimiento").trim();
								if(ls_estado_mov.equals("5")){
									objListaDoc.put("estado_movimiento", "<FONT color='#996633'>"+funciones.of_nombre_estado(cnx,ls_estado_mov )+"</FONT>");	
								}
								else if(ls_estado_mov.equals("2")){
									objListaDoc.put("estado_movimiento", "<FONT color='#FF0000 '>"+funciones.of_nombre_estado(cnx,ls_estado_mov)+"</FONT>");	
								}
								else{
									objListaDoc.put("estado_movimiento", "<FONT color='#00CC00'>"+funciones.of_nombre_estado(cnx, ls_estado_mov)+"</FONT>");
								}
								//objListaDoc.put("estado_movimiento", rs.getString("estado_movimiento"));
								
								objListaDoc.put("fecha_registro", rs.getString("fecha_registro"));
								
								//objListaDoc.put("fecha_registro",rs.getString("fecha_registro"));
								
								objListaDoc.put("fecha_movimiento", rs.getString("fecha_movimiento"));
								//objListaDoc.put("hora_registro", rs.getString("hora_registro"));
								objListaDoc.put("hora", rs.getString("hora_movimiento"));
								objListaDoc.put("asunto_documento", rs.getString("asunto_documento"));
								objListaDoc.put("codigo_recepcion", rs.getString("codigo_recepcion"));
								
								objListaDoc.put("secuencia_movimiento", ls_secuencia_movimiento);
								objListaDoc.put("codigo_inicia", rs.getString("codigo_inicia"));
								//objListaDoc.put("codigo_oficina", funciones.of_nombre_corto_oficina(cnx, rs.getString("codigo_oficina").trim()));
								//objListaDoc.put("codigo_expediente", rs.getString("codigo_expediente"));
								ls_naturaleza_documento=rs.getString("naturaleza_documento");
								if(ls_naturaleza_documento.equals("I")){
									ls_naturaleza_documento="Interno";
								}else{
									ls_naturaleza_documento="Externo";
								}
								objListaDoc.put("naturaleza_documento", ls_naturaleza_documento);
								
								 ls_num_upload=funciones.of_numero_doc_upload(cnx,rs.getString("codigo_documento").trim());
								
								if(ls_num_upload.equals("0")){
									objListaDoc.put("view_archivo", "<font color='#0000FF'>--------</font>");
								}else {
									objListaDoc.put("view_archivo", "<A id='A1' href='javascript:VerArchivos("+rs.getString("codigo_documento").trim()+","+rs.getString("codigo_expediente").trim()+","+rs.getString("codigo_recepcion").trim()+");' >"+
									"<img src='img/copy_f2.png' height='20' alt='Ver Documento' border='0'></A>");
								}
								objListaDoc.put("firmadopor",rs.getString("desc_origen").trim()+" - " +rs.getString("firmadopor").trim());
								
								objListaDoc.put("persona_destino",rs.getString("persona_destino"));
								
			
						ListaMesaPartes.add(objListaDoc);

							}
							ls_ant_codigo_recepcion=ls_codigo_recepcion;
							ls_ant_codigo_documento=ls_codigo_documento;
							ls_ant_codigo_expediente=ls_codigo_expediente;
							ls_ant_secuencia_movimiento=ls_secuencia_movimiento;
						
							
				}	
	    	 }
		
    		
			/*************TERMINA CIRUGIA****************/
	    	rs.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ListaMesaPartes;

	}
	
	private int anulados(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina) {
		int cantidad=0;
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			StringBuffer ls_sql = new StringBuffer("select count(codigo_documento) as cantidad from tramite.recorrido_doc " +
					"where codigo_oficina = '"+codigo_oficina+"' " +
					" and estado_movimiento = 9 ");
					if(fecha_inicio!=null){
						ls_sql.append("and to_date(fecha_movimiento,'dd/MM/yyyy')>='"+fecha_inicio.toString()+"'");	
					}
					if(fecha_fin!=null){
						ls_sql.append("and to_date(fecha_movimiento,'dd/MM/yyyy')<='"+fecha_fin.toString()+"'");
					}
					
			String query = ls_sql.toString();
			us = cnx.prepareStatement(query);
			rs = us.executeQuery();
			
			if (rs.next()) {
				cantidad=rs.getInt("cantidad");
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;

	}
	
	private int cerrados(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina) {
		int cantidad=0;
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			String ls_sql = "select count(codigo_documento) as cantidad from tramite.recorrido_doc " +
					"where codigo_oficina = '"+codigo_oficina+"' " +
					" and estado_movimiento = 7 ";
					if(fecha_inicio!=null){
						ls_sql=ls_sql+"and to_date(fecha_movimiento,'dd/MM/yyyy')>='"+fecha_inicio.toString()+"'";	
					}
					if(fecha_fin!=null){
						ls_sql=ls_sql+"and to_date(fecha_movimiento,'dd/MM/yyyy')<='"+fecha_fin.toString()+"'";
					}
					
			String query = ls_sql.toString();
			us = cnx.prepareStatement(query);
			rs = us.executeQuery();
			
			if (rs.next()) {
				cantidad=rs.getInt("cantidad");
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;

	}
	
	private int preCerrados(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina) {
		int cantidad=0;
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			String ls_sql = "select count(codigo_documento) as cantidad from tramite.recorrido_doc " +
					"where codigo_oficina = '"+codigo_oficina+"' " +
					" and estado_movimiento = 6 ";
					if(fecha_inicio!=null){
						ls_sql=ls_sql+"and to_date(fecha_movimiento,'dd/MM/yyyy')>='"+fecha_inicio.toString()+"'";	
					}
					if(fecha_fin!=null){
						ls_sql=ls_sql+"and to_date(fecha_movimiento,'dd/MM/yyyy')<='"+fecha_fin.toString()+"'";
					}
					
			String query = ls_sql.toString();
			us = cnx.prepareStatement(query);
			rs = us.executeQuery();
			
			if (rs.next()) {
				cantidad=rs.getInt("cantidad");
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;

	}

	
	private int archivados(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina) {
		int cantidad=0;
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			StringBuffer ls_sql = new StringBuffer("select count(codigo_documento) as cantidad from tramite.recorrido_doc " +
					"where codigo_oficina = '"+codigo_oficina+"' " +
					" and estado_movimiento = 4 ");
					if(fecha_inicio!=null){
						ls_sql.append("and to_date(fecha_movimiento,'dd/MM/yyyy')>='"+fecha_inicio.toString()+"'");	
					}
					if(fecha_fin!=null){
						ls_sql.append("and to_date(fecha_movimiento,'dd/MM/yyyy')<='"+fecha_fin.toString()+"'");
					}
					
			String query = ls_sql.toString();
			us = cnx.prepareStatement(query);
			rs = us.executeQuery();
			
			if (rs.next()) {
				cantidad=rs.getInt("cantidad");
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;

	}

	private int derivados(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina) {
		int cantidad=0;
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			StringBuffer ls_sql = new StringBuffer("select count(codigo_documento) as cantidad from tramite.recorrido_doc " +
					"where codigo_oficina = '"+codigo_oficina+"' " +
					" and estado_movimiento = 5 ");
					if(fecha_inicio!=null){
						ls_sql.append("and to_date(fecha_movimiento,'dd/MM/yyyy')>='"+fecha_inicio.toString()+"'");	
					}
					if(fecha_fin!=null){
						ls_sql.append("and to_date(fecha_movimiento,'dd/MM/yyyy')<='"+fecha_fin.toString()+"'");
					}
					
			String query = ls_sql.toString();
			//log.info("Query de Derivados >> "+query);
			us = cnx.prepareStatement(query);
			rs = us.executeQuery();
			
			if (rs.next()) {
				cantidad=rs.getInt("cantidad");
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;

	}

	private int tramite(Connection cnx, Date fecha_inicio, Date fecha_fin,
			String codigo_oficina) {
		int cantidad=0;
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			StringBuffer ls_sql = new StringBuffer("select count(codigo_documento) as cantidad from tramite.recorrido_doc " +
					"where codigo_oficina = '"+codigo_oficina+"' " +
					" and estado_movimiento = 3 ");
					if(fecha_inicio!=null){
						ls_sql.append("and to_date(fecha_movimiento,'dd/MM/yyyy')>='"+fecha_inicio.toString()+"'");	
					}
					if(fecha_fin!=null){
						ls_sql.append("and to_date(fecha_movimiento,'dd/MM/yyyy')<='"+fecha_fin.toString()+"'");
					}
					
			String query = ls_sql.toString();
			//log.info("Query de Derivados >> "+query);
			us = cnx.prepareStatement(query);
			rs = us.executeQuery();
			
			if (rs.next()) {
				cantidad=rs.getInt("cantidad");
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;

	}
	
	
	private int creados(Connection cnx, Date fecha_inicio, Date fecha_fin,String codigo_oficina) {
		// TODO Auto-generated method stub
		int cantidad=0;
		try {

			ResultSet rs = null;
			PreparedStatement us = null;
			StringBuffer ls_sql = new StringBuffer("select count(distinct codigo_documento)  from tramite.recorrido_doc " +
					" where secuencia_movimiento = 1 and codigo_oficina = '"+codigo_oficina+"' ");
					
					if(fecha_inicio!=null){
						ls_sql.append("and to_date(fecha_movimiento,'dd/MM/yyyy')>='"+fecha_inicio.toString()+"'");	
					}
					if(fecha_fin!=null){
						ls_sql.append("and to_date(fecha_movimiento,'dd/MM/yyyy')<='"+fecha_fin.toString()+"'");
					}
		
			String query = ls_sql.toString();
			us = cnx.prepareStatement(query);
			rs = us.executeQuery();
			
			if (rs.next()) {
				cantidad=rs.getInt(1);
			}
			
			
			

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;
	}

	
}
