package tramitedoc.concytec.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tramitedoc.concytec.dao.interfaces.IControlBandejasService;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.ObtieneConexion;

public class IControlBandejasServiceImplement implements
				IControlBandejasService {
protected  Log log = LogFactory.getLog(IControlBandejasServiceImplement.class);

	public Collection lista_recepcion_documentos_normal_bandejas(String oficina, int tipo) throws Exception{
		Collection resultado = new ArrayList();
		Funciones funciones = new Funciones();
		String ls_codigo_documento="";
		String ls_secuencia_movimiento="";
		String ls_secuencia_origen="";
		
		String ls_codigo_expediente_cont="";
		String ls_codigo_documento_cont="";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		
		StringBuilder ls_sql = new StringBuilder("select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,");
		ls_sql.append("md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.secuencia_origen, ");
		ls_sql.append("md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,");
		ls_sql.append("md.codigo_expediente,md.oficina_origen,md.oficina_deriva,md.correlativo_deriva,md.observa_registro,md.codigo_recepcion,");
		ls_sql.append("md.flag,tp.descripcion_tipo,md.desc_origen,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,");
		ls_sql.append("dc.numero_documento_ant,dc.observa_documento,dc.fecha_registro,dc.hora_registro,dc.dirigido,de.origen_documento,de.destino_documento,de.desc_origen as nombre_procedencia,");
		ls_sql.append("de.tipo,de.remitente,md.destinatario,de.firmadopor,de.medio,de.codigo_solicitud,of.codigo_oficina,of.descripcion_oficina,");
		ls_sql.append("of_a.descripcion_oficina as descripcion_destino,pr.codigo_personal,pr.nombre_personal, md.doc_resp");
		
		ls_sql.append(",(select tofi.descripcion_oficina from tramite.oficinas tofi where tofi.codigo_oficina=md.oficina_origen) as nombre_oficina_origen,");
		ls_sql.append("(select tofi.descripcion_oficina from tramite.oficinas tofi where tofi.codigo_oficina=md.oficina_deriva) as nombre_oficina_deriva,");
		ls_sql.append("tedoc.estado_expediente as estado_expediente, (select count(tdetu.codigo_documento) from tramite.tbl_det_upload  tdetu  where tdetu.codigo_documento = md.codigo_documento) as numupload, ");
		ls_sql.append("(select tpers.nombre_personal from tramite.personal tpers where tpers.codigo_personal= md.destinatario) as nombre_destinatario ");
		
		ls_sql.append(" from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,");
		ls_sql.append("tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr");
		ls_sql.append(",tramite.estados_doc tedoc ");
		
		ls_sql.append(" where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento ");
		ls_sql.append(" and dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento");
		ls_sql.append(" and de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina ");
		ls_sql.append(" and de.destino_documento=of_a.codigo_oficina  and md.estado_movimiento=tedoc.codigo_estado ");
		
		ls_sql.append(" and md.estado_movimiento ='2' and md.codigo_fondo like '01' and md.codigo_oficina = ?  ");
		
		if(tipo==1){
			ls_sql.append(" and  md.fecha_rpta = '' and  md.codigo_motivo=2 ");
		}
		if(tipo==2){
			ls_sql.append(" and  md.fecha_rpta <> '' and current_date  <  to_date(md.fecha_rpta,'dd/mm/yyyy') ");
		}
		if(tipo==3){
			ls_sql.append(" and  md.fecha_rpta <> '' and current_date  =  to_date(md.fecha_rpta,'dd/mm/yyyy') ");
		}
		if(tipo==4){
			ls_sql.append(" and  md.fecha_rpta <> '' and current_date  >  to_date(md.fecha_rpta,'dd/mm/yyyy') ");
		}

		ls_sql.append(" and md.secuencia_movimiento = (select max(secuencia_movimiento) from tramite.movimientos_doc where codigo_documento=md.codigo_documento and codigo_oficina=? ) ");
		ls_sql.append(" order by md.codigo_documento desc");
		
		conn = obt.getConnection();
		pstmt = conn.prepareStatement(ls_sql.toString());
		pstmt.setInt(1, Integer.parseInt(oficina));
		pstmt.setInt(2, Integer.parseInt(oficina));
		log.info(pstmt);
		rs = pstmt.executeQuery();
		
		
		while ( rs.next() ) { 

			Hashtable objListaDoc = new Hashtable();
			
			ls_codigo_documento=rs.getString("codigo_documento").trim();
			ls_secuencia_movimiento=rs.getString("secuencia_movimiento").trim();
			ls_secuencia_origen=rs.getString("secuencia_origen").trim();
			
			ls_secuencia_origen= (ls_secuencia_origen==null)? "0":ls_secuencia_origen;
			
			//ls_codigo_solicitud=funciones.of_solicitud_documento(cnx, rs.getString("codigo_solicitud")).trim();
			//ls_medio=funciones.of_medio_documento(cnx, rs.getString("medio")).trim();
			
			String ls_doc_respuesta = (rs.getString("doc_resp")==null)?"":rs.getString("doc_resp");
			String ls_observa_movimiento = rs.getString("observa_movimiento");
			ls_observa_movimiento = ls_observa_movimiento==null?"":ls_observa_movimiento;

			String asunto_documento = funciones.convertir_comillas(rs.getString("asunto_documento"));
			
			/*String seleccionar = "<label style='cursor: hand;' align='center' " 
			+ "onclick=\"fn_editar_cop('"+ls_codigo_documento+"','"+ls_secuencia_movimiento+"');\" " + ">Seleccionar</label>";*/

			 ls_codigo_expediente_cont = "<label style='cursor: hand;' align='center' " 
				+ "onclick=\"window.parent.fn_editar_cop('"+ls_codigo_documento+"','"+ls_secuencia_movimiento+"');\" " + ">"+ rs.getString("codigo_expediente").trim()+"</label>";

			 ls_codigo_documento_cont = "<label style='cursor: hand;' align='center' " 
				+ "onclick=\"window.parent.fn_editar_cop('"+ls_codigo_documento+"','"+ls_secuencia_movimiento+"');\" " + ">"+ rs.getString("codigo_documento")+"</label>";

			String ls_numero_documento = "<label style='cursor: hand;' align='center' " 
				+ "onclick=\"window.parent.fn_editar_cop('"+ls_codigo_documento+"','"+ls_secuencia_movimiento+"');\" " + ">"+ rs.getString("numero_documento")+"</label>";

			String ls_oficina_origen = "<label style='cursor: hand;' align='center' " 
				+ "onclick=\"window.parent.fn_editar_cop('"+ls_codigo_documento+"','"+ls_secuencia_movimiento+"');\" " + ">"+ rs.getString("nombre_oficina_origen").trim()+"</label>";

			//objListaDoc.put("ACTION", seleccionar);
			objListaDoc.put("codigo_documento", ls_codigo_documento_cont);
			objListaDoc.put("codigo_expediente", ls_codigo_expediente_cont);
			objListaDoc.put("numero_documento", ls_numero_documento);
			objListaDoc.put("oficina_origen", ls_oficina_origen);
			objListaDoc.put("oficina_deriva", rs.getString("nombre_oficina_deriva").trim());
			objListaDoc.put("estado_movimiento", "<FONT color='#FF0000 '>"+rs.getString("estado_expediente").trim()+"</FONT>");	
			
			
			/*objListaDoc.put("fecha_movimiento", rs.getString("fecha_movimiento"));
			objListaDoc.put("hora", rs.getString("hora_movimiento"));
			objListaDoc.put("asunto_documento", rs.getString("asunto_documento"));
			*/
			objListaDoc.put("codigo_recepcion", rs.getString("codigo_recepcion").trim());
			
			if(tipo==1){
				objListaDoc.put("fecha_registro", rs.getString("fecha_registro").trim());
			}else{
				objListaDoc.put("fecha_registro", rs.getString("fecha_rpta").trim());
			}
			
			objListaDoc.put("tipo_doc", rs.getString("descripcion_tipo").trim());
			

			if(rs.getInt("numupload")==0){
				
				objListaDoc.put("view_archivo", "<font color='#0000FF'>--------</font>");
				
			}else {
				
				objListaDoc.put("view_archivo", "<A id='A1' href='javascript:VerArchivos("+rs.getString("codigo_documento").trim()+","+rs.getString("codigo_expediente").trim()+","+rs.getString("codigo_recepcion").trim()+");' >"+
				"<img src='img/copy_f2.png' height='20' alt='Ver Documento' border='0'></A>");

			}
			objListaDoc.put("destinatario", rs.getString("nombre_destinatario"));
			
			if(tipo==1 || tipo==4){
				objListaDoc.put("muestra_alerta", "<img src='img/atencion.gif' height='20' title='"+rs.getString("fecha_rpta")+"' border='0'>");
			}
			if(tipo==2){
				objListaDoc.put("muestra_alerta", "<img src='img/verde.gif' height='20' title='"+rs.getString("fecha_rpta")+"' border='0'>");
			}
			if(tipo==3){
				objListaDoc.put("muestra_alerta", "<img src='img/amarillo.gif' height='20' title='"+rs.getString("fecha_rpta")+"' border='0'>");
			}
			
			objListaDoc.put("observar", "<A id='A1' href='javascript:Observar("+rs.getString("codigo_documento").trim()+","+rs.getString("codigo_expediente").trim()+","+rs.getString("codigo_recepcion").trim()+","+ls_secuencia_movimiento+","+ls_secuencia_origen+");' >"+
			"<img src='img/07observar.png' height='20' title='Observar' border='0'></A>");
			
			resultado.add(objListaDoc);
				

		}
		
		obt.cerrar_conexion(conn, rs);
		pstmt.close();
		
		return resultado;
	}


}
