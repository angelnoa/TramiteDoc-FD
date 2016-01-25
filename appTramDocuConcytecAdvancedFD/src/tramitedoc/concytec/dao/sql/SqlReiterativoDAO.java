package tramitedoc.concytec.dao.sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tramitedoc.concytec.bean.BDocumento;
import tramitedoc.concytec.bean.BInfoListaReiterativo;
import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IReiterativoDAO;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.ObtieneConexion;

public class SqlReiterativoDAO implements IReiterativoDAO {
	protected  Log log = LogFactory.getLog(SqlReiterativoDAO.class);
	
	public boolean esDocumentoReiterado(String codigo_documento,
			String secuencia_movimiento) throws Exception {
		// TODO Auto-generated method stub
		boolean resultado=false;
		
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		Connection con = obt.getConnection();
		
		
        try {	
        		
			String ls_sql = "select codigo_documento from tramite.movimientos_doc where codigo_documento = ?  and secuencia_movimiento = ?  and tipo_envio = 'R'";
			
			
			pstmt = con.prepareStatement(ls_sql);
	    	pstmt.setInt(1, Integer.parseInt(codigo_documento));
		    pstmt.setInt(2, Integer.parseInt(secuencia_movimiento));
		    rs=pstmt.executeQuery();
			
			System.out.println("sql :"+ls_sql);
    		if(rs.next()){
    			resultado=true;
    		}
            obt.cerrar_conexion(con,rs);
            pstmt.close();
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(con);
			sqle.printStackTrace();
        }
        
		return resultado;
	}
	
	public void recibirDocumentoReiterado(String codigo_oficina,
			String codigo_documento, String usuario, String fecha, String hora,
			String observa_documento, String estado_movimiento,
			String codigo_recepcion, String secuencia_movimiento,
			String asunto_documento, String ls_cargo_personal) throws Exception {
		
		Calendar calendario = new GregorianCalendar();
    	String fecha_consulta=null;
    	String ls_estado_derivado="3"; //3 ES TRAMITE ANTES ESTABA 5 DERIVACION, MODIFICADO POR MOISES PELAEZ 18-08-2011
    	String codigo_fondo="01";
    	
    	String ls_sql = "";

	 	int horadia = calendario.get(Calendar.HOUR_OF_DAY);
	 	int minutos = calendario.get(Calendar.MINUTE);
	 	int segundos = calendario.get(Calendar.SECOND);
    	
    	ObtieneConexion  x= new ObtieneConexion();
		Connection	con= x.getConnection();
		Statement us  = null;
		PreparedStatement pstmt = null;
		
    	try{
    		
        	fecha_consulta = fecha.substring(6,10)+fecha.substring(3,5)+fecha.substring(0,2);
        	
	    
        	if(estado_movimiento.equals("2") || estado_movimiento.equals("8")){
        	
        			
        				/***ls_sql = "UPDATE tramite.movimientos_doc SET estado_movimiento = " +"'"+ls_estado_derivado+
		        		"'"+ ","+"fecha_movimiento="+"'"+fecha+"'"+","+"fecha_consulta="+"'"+fecha_consulta+"'"+
		        		","+" hora_movimiento="+"'"+hora+"'"+"," + " ultimo_usuario=" + "'" +usuario+ "'"+"," +
		        		" flag=" + "''" + " where codigo_fondo="+ "'" + codigo_fondo +
		        		"'" + " and codigo_oficina= "+ "'" +codigo_oficina + "'" + " and codigo_documento="+"'"+codigo_documento+"'"+
		        		" and codigo_recepcion="+"'"+codigo_recepcion+"'"+ " and secuencia_movimiento="+"'"+secuencia_movimiento+"'"+"";
        				
        				us = con.createStatement();
   			    	    us.executeUpdate(ls_sql);****/	
   			    	 
        		/***
        		 * temporal
        		 */
        		ls_sql = "UPDATE tramite.movimientos_doc SET  codigo_oficina= ?  " +
        		" where codigo_documento=?  and secuencia_movimiento=? ";
        		pstmt = con.prepareStatement(ls_sql);

        		pstmt.setInt(1, Integer.parseInt(codigo_oficina));
				pstmt.setInt(2, Integer.parseInt(codigo_documento));
				pstmt.setInt(3, Integer.parseInt(secuencia_movimiento));
				//log.info("q0: "+pstmt);
		    	 
		    	pstmt.executeUpdate();
		    	
		    	/**
		    	 * fin temporal
		    	 */

        		        		        		
        		ls_sql = "UPDATE tramite.movimientos_doc SET estado_movimiento = ? , fecha_movimiento= ?, fecha_consulta= ? "+
        		", hora_movimiento= ? , ultimo_usuario= ?, flag = ?  where codigo_fondo= ? "+
        		"  and codigo_oficina= ?  and codigo_documento=?  and codigo_recepcion=?  and secuencia_movimiento=? "; 
        		
				pstmt = con.prepareStatement(ls_sql);
				pstmt.setString(1, ls_estado_derivado);
				pstmt.setString(2, fecha);
				pstmt.setString(3, fecha_consulta);
				pstmt.setTime(4, Time.valueOf(String.valueOf(horadia)+":"+String.valueOf(minutos)+":"+String.valueOf(segundos)));
				pstmt.setString(5, usuario);
				pstmt.setString(6, "");
				pstmt.setString(7, codigo_fondo);
				pstmt.setInt(8, Integer.parseInt(codigo_oficina));
				pstmt.setInt(9, Integer.parseInt(codigo_documento));
				pstmt.setInt(10, Integer.parseInt(codigo_recepcion));
				pstmt.setInt(11, Integer.parseInt(secuencia_movimiento));
				
				pstmt.executeUpdate();	
        		
        		
        		
		    	 ls_sql = "insert into tramite.recorrido_doc(select * from tramite.movimientos_doc where" +
	    	 		" codigo_fondo= ?  and codigo_oficina=?   and  codigo_documento=?  and  secuencia_movimiento = ? )";
		    	 
		    	 	pstmt = con.prepareStatement(ls_sql);
					pstmt.setString(1, codigo_fondo);
					pstmt.setInt(2, Integer.parseInt(codigo_oficina));
					pstmt.setInt(3, Integer.parseInt(codigo_documento));
					pstmt.setInt(4, Integer.parseInt(secuencia_movimiento));
		    	 
					pstmt.executeUpdate();	
		    	 
					/*us = con.createStatement();
		    	 us.executeUpdate(ls_sql);*/	
					
					x.cerrar_conexion(con);
					pstmt.close();
			    	 
        	}
    		
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		x.cerrar_conexion(con);
    	}
	}
	
	public void actualizarEstadoArchivos(String codigo_oficina,int correlativo,int codigo_documento,int codigo_expediente ,int secuencia_movimiento,String usuario,int codigo_recepcion)
			throws Exception {
		
		StringBuffer ls_sql = new StringBuffer();
		StringBuffer ls_sql2 = new StringBuffer();
		StringBuffer ls_sql3 = new StringBuffer();
    	int  li_retorno = 1;
    	
    	Statement us  = null;
		ResultSet rs = null;
		ObtieneConexion 	x= new ObtieneConexion();
		Connection	con= x.getConnection();
		
        try {	
        		
			/*String ls_sql = "update tramite.tbl_det_upload set flag = 'I',usuario_creacion = '"+usuario+"'  where codigo_documento= "+codigo_documento+" and flag = 'A';";
			cnx = obt.getConnection();
			us = cnx.createStatement();
    		us.executeUpdate(ls_sql);    		
			obt.cerrar_conexion(cnx);*/
        	
			ls_sql.append("insert into tramite.tbl_cab_upload(id_cab_upload,codigo_documento,codigo_expediente,codigo_oficina,secuencia_movimiento,usuario_creacion,fecha_creacion,codigo_recepcion) ");
			ls_sql.append("values(nextval('tramite.seq_tbl_cab_upload'),");
			ls_sql.append(codigo_documento);
			ls_sql.append(",");
			ls_sql.append(codigo_expediente);
			ls_sql.append(",");
			ls_sql.append(codigo_oficina);
			ls_sql.append(",");
			ls_sql.append(secuencia_movimiento);
			ls_sql.append(",'");
			ls_sql.append(usuario);
			ls_sql.append("',current_timestamp,");
			ls_sql.append(codigo_recepcion);
			ls_sql.append(")");;
			//log.info("El ls_sql es XXX.."+ ls_sql);	
		    	 us = con.createStatement();
		    	 us.executeUpdate(ls_sql.toString());
		    	 
		    ls_sql2.append("SELECT last_value as valor FROM tramite.seq_tbl_cab_upload; ");
			us = con.createStatement();
	    	rs = us.executeQuery(ls_sql2.toString());
	    	while (rs.next()){
	    		li_retorno = rs.getInt("valor");	
	    	}	 
		    	 
			    	 
	    	ls_sql3.append("UPDATE tramite.tbl_det_upload SET id_cab_upload=");
	    	ls_sql3.append(li_retorno);
	    	ls_sql3.append(", codigo_oficina=");
	    	ls_sql3.append(codigo_oficina);
	    	ls_sql3.append(", usuario_modificacion='");
	    	ls_sql3.append(usuario);
	    	ls_sql3.append("', flag='I', fecha_modificacion=current_timestamp, codigo_recepcion=");
	    	ls_sql3.append(codigo_recepcion);
	    	ls_sql3.append("  WHERE codigo_documento=");
	    	ls_sql3.append(codigo_documento);
	    	ls_sql3.append(" and secuencia_movimiento=");
	    	ls_sql3.append(secuencia_movimiento);
	    	ls_sql3.append("  and flag='A'");
	 
	 			//log.info("El ls_sql modificar Cabecera Upload Proy Investigacion .." + ls_sql);
	 	 		us = con.createStatement();
	 	 		us.executeUpdate(ls_sql3.toString());   
	 	 		
	 	 		 x.cerrar_conexion(con);
	 	 		 us.close();
            
        } catch (SQLException sqle) {
        	log.info("Error en el metodo Ingresar actualizarEstadoArchivos");
			sqle.printStackTrace();
			 x.cerrar_conexion(con);
        }
       
		
	}
	public int ObtieneCantidadArchivos(String codigo_documento)
			throws Exception {
		
		Statement us  = null;
		ResultSet rs = null;
		Connection cnx=null;
		ObtieneConexion obt=new ObtieneConexion();
		int contador=0;
        try {	
        		
			String ls_sql = "select count(id_det_upload) as contador from tramite.tbl_det_upload where codigo_documento= "+codigo_documento +" " +
					" and flag = 'A'";
			
			cnx = obt.getConnection();
			us = cnx.createStatement();
    		rs = us.executeQuery(ls_sql);
    		////System.out.println("El rst"+ rst);
    		Funciones funciones=new Funciones();
    		//System.out.println("El ls_sql...." + ls_sql);
    		
    		
			if( rs.next() ) { 
					contador=rs.getInt("contador");			
			}	
            obt.cerrar_conexion(cnx, rs);
			
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(cnx, rs);
			sqle.printStackTrace();
        }
        return contador;
	}
	public boolean perteneceOficina(String codigo_oficina,
			String codigo_expediente, String codigo_documento) throws Exception {
		// TODO Auto-generated method stub
		Statement us = null;
		ResultSet rs = null;
		ObtieneConexion obt = new ObtieneConexion();
		Connection cnx = null;
		Funciones f=new Funciones();
		boolean resultado = false;
		try {

			cnx = obt.getConnection();
			StringBuffer ls_sql = new StringBuffer(
					"select codigo_documento from tramite.movimientos_doc where codigo_oficina = "+codigo_oficina);
			
			if(!f.esNulo(codigo_documento)){
				ls_sql.append(" and codigo_documento = "+codigo_documento);
			}
			
			if(!f.esNulo(codigo_expediente)){
				ls_sql.append(" and codigo_expediente = "+codigo_expediente);
			}			

			us = cnx.createStatement();
			String query = ls_sql.toString();
			System.out.println("query: " + query);
			rs = us.executeQuery(query);

			if (rs.next()) {
				resultado = true;
			}

			rs.close();

			obt.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {
			e.printStackTrace();
			obt.cerrar_conexion(cnx, rs);
		}
		return resultado;
	}
	
	public void anularMovimiento(String codigo_documento,
			String secuencia_movimiento) throws Exception {
		
		Statement us = null;		
		ObtieneConexion obt = new ObtieneConexion();
		Connection cnx = null;
		
		try {

			cnx = obt.getConnection();
			StringBuffer ls_sql = new StringBuffer("delete from tramite.movimientos_doc where codigo_documento = "+codigo_documento+" and " +
					"secuencia_movimiento = "+secuencia_movimiento);

			us = cnx.createStatement();
			String query = ls_sql.toString();
			int resul = us.executeUpdate(query);
			
			obt.cerrar_conexion(cnx);

		} catch (SQLException e) {
			e.printStackTrace();
			obt.cerrar_conexion(cnx);
		}
	}
	public Collection lista_upload_documentos_detalle(Connection cnx,
			int codigo_documento) throws Exception{
		// TODO Auto-generated method stub
		Collection lista = new ArrayList();
		
		BDocumento BDocumentoVO = null;
		Statement us  = null;
		ResultSet rs = null;

        try {	
        		
			String ls_sql = "select * from tramite.tbl_det_upload  " +
			"where codigo_documento=" + "'" + codigo_documento +"'"+
			"  and flag = 'A' order by id_det_upload asc";
        	
			////System.out.println("El ls_sql..."+ ls_sql);
			us = cnx.createStatement();
    		rs = us.executeQuery(ls_sql);
    		////System.out.println("El rst"+ rst);
    		Funciones funciones=new Funciones();
    		//System.out.println("El ls_sql...." + ls_sql);
    		int contador=0;
    		
			while ( rs.next() ) { 
				
				contador=contador+1;
				BDocumentoVO = new BDocumento();
				BDocumentoVO.setContador(contador);
				BDocumentoVO.setId_det_upload(rs.getInt("id_det_upload"));
				BDocumentoVO.setNombre_archivo(rs.getString("nombre_archivo"));
				
				lista.add(BDocumentoVO);
				
			}	
            
			rs.close();
			//conn.close();

            
        } catch (SQLException sqle) {
        	System.out.println("Error en el metodo Lista Proyecto Investigacion Upload..");
			//mensaje=sqle.toString();  
			sqle.printStackTrace();
        }
        return lista;
	}
	
	public Collection lista_upload_documentos_detalle(int codigo_documento) throws Exception{
		// TODO Auto-generated method stub
		Collection lista = new ArrayList();
		
		BDocumento BDocumentoVO = null;
		Statement us  = null;
		ResultSet rs = null;

        try {	
        		
			String ls_sql = "select * from tramite.tbl_det_upload  " +
			"where codigo_documento=" + "'" + codigo_documento +"'"+
			"  and flag = 'A' order by id_det_upload asc";

    		int contador=0;
    		
			while ( rs.next() ) { 
				
				contador=contador+1;
				BDocumentoVO = new BDocumento();
				BDocumentoVO.setContador(contador);
				BDocumentoVO.setId_det_upload(rs.getInt("id_det_upload"));
				BDocumentoVO.setNombre_archivo(rs.getString("nombre_archivo"));
				
				lista.add(BDocumentoVO);
				
			}	
            
			rs.close();
			//conn.close();

            
        } catch (SQLException sqle) {
        	System.out.println("Error en el metodo Lista Proyecto Investigacion Upload..");
			//mensaje=sqle.toString();  
			sqle.printStackTrace();
        }
        return lista;
	}
	
	public Collection lista_upload_documentos_detalle_firma_manual(Connection cnx,
			int codigo_documento) throws Exception{
		// TODO Auto-generated method stub
		Collection lista = new ArrayList();
		
		BDocumento BDocumentoVO = null;
		Statement us  = null;
		ResultSet rs = null;

        try {	
        		
			String ls_sql = "select * from tramite.tbl_det_upload  " +
			"where id_codigo_documento_interno=" + "'" + codigo_documento +"'"+
			"  and flag = 'A' order by id_det_upload asc";
        	
			////System.out.println("El ls_sql..."+ ls_sql);
			us = cnx.createStatement();
    		rs = us.executeQuery(ls_sql);
    		////System.out.println("El rst"+ rst);
    		//Funciones funciones=new Funciones();
    		//System.out.println("El ls_sql...." + ls_sql);
    		int contador=0;
    		
			while ( rs.next() ) { 
				
				contador=contador+1;
				BDocumentoVO = new BDocumento();
				BDocumentoVO.setContador(contador);
				BDocumentoVO.setId_det_upload(rs.getInt("id_det_upload"));
				BDocumentoVO.setNombre_archivo(rs.getString("nombre_archivo"));
				
				lista.add(BDocumentoVO);
				
			}	
            
			rs.close();
			//conn.close();

            
        } catch (SQLException sqle) {
        	System.out.println("Error en el metodo Lista Proyecto Investigacion Upload..");
			//mensaje=sqle.toString();  
			sqle.printStackTrace();
        }
        return lista;
	}
	
	public String obtenerMaxSecuencia(String codigo_documento)throws Exception {
		// TODO Auto-generated method stub
		Statement us = null;
		ResultSet rs = null;
		ObtieneConexion obt = new ObtieneConexion();
		Connection cnx = null;
		String resultado = "";
		try {

			cnx = obt.getConnection();
			StringBuffer ls_sql = new StringBuffer(
					"select coalesce(max(secuencia_movimiento),0)+1 as resultado from tramite.movimientos_doc where codigo_documento = "+ codigo_documento);

			us = cnx.createStatement();
			String query = ls_sql.toString();
			System.out.println("query: " + query);
			rs = us.executeQuery(query);

			if (rs.next()) {
				resultado = rs.getString("resultado");
			}

			rs.close();

			obt.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {
			e.printStackTrace();
			obt.cerrar_conexion(cnx, rs);
		}
		return resultado;
	}
	
	
	public Collection listarEstados() throws Exception {
		// TODO Auto-generated method stub
		Collection listaEstados = new ArrayList();

		PreparedStatement us = null;
		ResultSet rs = null;
		ObtieneConexion obt = new ObtieneConexion();		
		Connection cnx = obt.getConnection();
		BMesaPartes BMesaPartesVO = null;	
       
        try {
        	
        	
        	StringBuffer ls_sql = new StringBuffer("select * from tramite.estados_doc order by codigo_estado");
			String query = ls_sql.toString();
				us = cnx.prepareStatement(query);
			    rs = us.executeQuery();
	
			int contador=0;
		    
		while ( rs.next() ) { 
			
			Hashtable tabla= new Hashtable();
			tabla.put("codigo", rs.getString("codigo_estado"));
			tabla.put("descripcion", rs.getString("estado_expediente"));
			
			listaEstados.add(tabla);

		}	
 
		obt.cerrar_conexion(cnx, rs);
			
        } catch (Exception e) {
        	obt.cerrar_conexion(cnx, rs);
             e.printStackTrace();
        }
        return listaEstados;
	}
	
	public Collection listarSecuencias(String codigo_documento) throws Exception {
		// TODO Auto-generated method stub
		//Collection listarSecs = new ArrayList();

		PreparedStatement us = null;
		ResultSet rs = null;
		ObtieneConexion obt = new ObtieneConexion();		
		Collection listaHistorial = new ArrayList();
		Connection cnx = obt.getConnection();
		BMesaPartes BMesaPartesVO = null;	
       
        try {	
        	
        	
        	StringBuffer ls_sql = new StringBuffer("select md.codigo_documento as codigo_documento," +
        			"md.codigo_oficina,md.fecha_movimiento as fecha_movimiento,md.hora_movimiento as" +
        			" hora_movimiento," +
        			" (select estado_expediente from  tramite.estados_doc where codigo_estado = md.estado_movimiento) as estado_movimiento," +
        			"md.codigo_inicia,md.secuencia_movimiento as" +
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
				" de.destino_documento=of_a.codigo_oficina and dc.codigo_documento = " + "'" +codigo_documento+ "'");
        	
				
				
				ls_sql.append(" order by codigo_documento desc,secuencia_movimiento desc, hora_movimiento desc, fecha_movimiento desc");
				
				String query = ls_sql.toString();
				us = cnx.prepareStatement(query);
			    rs = us.executeQuery();
	
			//int contador=0;
		    Funciones funciones=new Funciones();	
		while ( rs.next() ) { 
			
			BMesaPartesVO = new BMesaPartes();
			
			
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
			
			listaHistorial.add(BMesaPartesVO);

		}	
 
		obt.cerrar_conexion(cnx, rs);
			
        } catch (Exception e) {
        	obt.cerrar_conexion(cnx, rs);
             e.printStackTrace();
        }
        return listaHistorial;
	}

	public int registrar() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public Collection listarDocs(String codigo_oficina,
			String codigo_expediente, String codigo_documento, int valor) throws Exception {
		// TODO Auto-generated method stub
		Collection listarDocs = new ArrayList();
		System.out.println("Entre ... listarDocs");
		Statement us = null;
		ResultSet rs = null;
		ObtieneConexion obt = new ObtieneConexion();
		Connection cnx = null;
		DocumentosInternosService service = new DocumentosInternosServiceImplement();
		String codigo_temporal="";
		String codigo_documento_bucle="";
		int estado_temporal=0;
		try {

			cnx = obt.getConnection();
			StringBuffer ls_sql = new StringBuffer(
					"select distinct de.desc_origen, md.codigo_expediente, md.codigo_documento, md.codigo_recepcion,"
							+ "md.secuencia_movimiento,dc.numero_documento,dc.naturaleza_documento, dc.observa_documento, dc.asunto_documento " +
							" from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,"
							+ "tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr where "
							+ "tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and"
							+ " dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and "
							+ "de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and"
							+ " de.destino_documento=of_a.codigo_oficina and md.codigo_fondo like '01'");
			/*StringBuffer ls_sql = new StringBuffer(
					"select distinct de.desc_origen, md.codigo_expediente, md.codigo_recepcion, md.codigo_documento, "
							+"md.secuencia_movimiento, dc.numero_documento,dc.naturaleza_documento, dc.observa_documento, dc.asunto_documento " 
							+"from tramite.tipo_documento tp "
							+"inner join tramite.documento dc on tp.codigo_tipo=dc.codigo_tipo "
							+"inner join tramite.movimientos_doc md on dc.codigo_documento=md.codigo_documento "
							+"inner join tramite.doc_entrada de on dc.codigo_documento=de.codigo_documento "
							+"inner join tramite.personal pr on de.destinatario=pr.codigo_personal "
							+"inner join tramite.oficinas of on md.codigo_oficina=of.codigo_oficina "
							+"inner join tramite.oficinas of_a on de.destino_documento=of_a.codigo_oficina "
							+"where md.codigo_fondo like '01'");*/
			

			if (codigo_oficina != null && !codigo_oficina.equals("")
					&& !codigo_oficina.equals("null")) {
				ls_sql.append(" and md.codigo_oficina = '" + codigo_oficina
						+ "' ");
				System.out.println("el ls_codigo_oficina: " + codigo_oficina);
			}
			if (codigo_documento != null && !codigo_documento.equals("")
					&& !codigo_documento.equals("null")) {
				ls_sql.append(" and md.codigo_documento = '" + codigo_documento
						+ "' ");
				System.out.println("el codigo_documento: " + codigo_documento);
			}

			if (codigo_expediente != null && !codigo_expediente.equals("")
					&& !codigo_expediente.equals("null")) {
				ls_sql.append(" and md.codigo_expediente = '"
						+ codigo_expediente + "' ");
				
			}
			
			ls_sql.append(" order by md.codigo_documento desc limit 30 offset 0;");
			us = cnx.createStatement();
			System.out.println(ls_sql);
			rs = us.executeQuery(ls_sql.toString());
			Hashtable doc = null;
			int i = 0;
			while (rs.next()) {
				
				doc = new Hashtable();

				doc.put("procedencia",rs.getString("desc_origen"));
				doc.put("codigo_documento", rs.getString("codigo_documento"));
				doc.put("numero_documento", rs.getString("numero_documento"));
				doc.put("asunto_documento", rs.getString("asunto_documento"));
				doc.put("observa_documento", rs.getString("observa_documento"));
				doc.put("naturaleza_documento", rs.getString("naturaleza_documento"));
				doc.put("codigo_expediente", rs.getString("codigo_expediente"));
				
				doc.put("registrar", "<a href='javascript:registrar("+i+");'>Registrar</a>");
				doc.put("mantener", "<a href='javascript:mantener("+i+")'>Mantener</a>");
				
				/**
				 * INSERCION DE LINK FIRMA DIGITAL
				 * */
				 
				BInfoListaReiterativo infoLista = new BInfoListaReiterativo();
				
				infoLista = service.getCodigoDocumentoInternoReiterativo(rs.getString("codigo_documento").trim(),codigo_oficina,rs.getString("secuencia_movimiento").trim());
				estado_temporal = infoLista.getEstado_documento_interno();
				
				if(estado_temporal == 0){
					//preparar respuesta
					doc.put("doc_rspta",
							"<A id='A1' title='Preparar Respuesta' href='PuenteDocumentoInterno.do?cdi="+rs.getString("codigo_documento").trim()+"&cei="+rs.getString("codigo_expediente").trim()+"&tipenv=R&sec="+rs.getString("secuencia_movimiento").trim()+"&cri="+rs.getString("codigo_recepcion").trim()+"' >"+
						"<img src='img/send_doc.png' height='20' alt='Preparar Respuesta' border='0'></A>");
				}else{
					if(estado_temporal == 1){
						//modificar respuesta
						doc.put("doc_rspta",
								"<A id='A1' title='Modificar Respuesta' href='PuenteDocumentoInterno.do?cdi="+rs.getString("codigo_documento").trim()+"&cei="+rs.getString("codigo_expediente").trim()+"&tipenv=R&sec="+rs.getString("secuencia_movimiento").trim()+"&cri="+rs.getString("codigo_recepcion").trim()+"' >"+
						"<img src='img/edit_doc.png' height='20' alt='Modificar Respuesta' border='0'></A>");
					}else{
						if(estado_temporal == 5){
							//firmar
							if(valor==0){//indica que no tiene firma
								doc.put("doc_rspta", "<A id='A1' title='Ir a Bandeja documentos por firmar' href='FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=firma' >"+
										"<img src='img/send-bandeja-icon.png' height='20'  border='0'></A>");

							}else{
								doc.put("doc_rspta",
										"<A id='A1' title='Firmar' href='PuenteDocumentoInterno.do?cdi="+rs.getString("codigo_documento").trim()+"&cei="+rs.getString("codigo_expediente").trim()+"&tipenv=R&sec="+rs.getString("secuencia_movimiento").trim()+"&cri="+rs.getString("codigo_recepcion").trim()+"' >"+
								"<img src='img/firma_doc.png' height='20' alt='Firmar Respuesta' border='0'></A>");
							}
							
						}else{
							if(estado_temporal == 3){
								//firmar
								doc.put("doc_rspta","<FONT color='#00CC00'>FIRMADO</FONT>");
								
							}
						}
						
					}
				}
				codigo_documento_bucle = rs.getString("codigo_documento");

				if(!codigo_documento_bucle.equals(codigo_temporal)){
					
					//System.out.println("-->"+i+" >>"+rs.getString("codigo_documento")+", "+rs.getString("secuencia_movimiento"));
					
					listarDocs.add(doc);
					i++;
				}
				codigo_temporal=codigo_documento_bucle;

			}

			rs.close();

			obt.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {
			e.printStackTrace();
			obt.cerrar_conexion(cnx, rs);
		}
		return listarDocs;
	}

	private String buscarProcedencia(String codigo_documento) {
		// TODO Auto-generated method stub
		Statement us = null;
		ResultSet rs = null;
		ObtieneConexion obt = new ObtieneConexion();
		Connection cnx = null;
		String resultado = "";
		try {

			cnx = obt.getConnection();
			StringBuffer ls_sql = new StringBuffer(
					"select nombre_corto from tramite.movimientos_doc md inner join tramite.oficinas ofi on md.oficina_origen = ofi.codigo_oficina "
							+ " where secuencia_movimiento = 1 and codigo_documento = "
							+ codigo_documento);

			us = cnx.createStatement();
			String query = ls_sql.toString();
			
			rs = us.executeQuery(query);

			if (rs.next()) {
				resultado = rs.getString("nombre_corto");
			}

			rs.close();

			obt.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {
			e.printStackTrace();
			obt.cerrar_conexion(cnx, rs);
		}
		return resultado;
	}
}
