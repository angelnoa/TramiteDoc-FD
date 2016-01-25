package tramitedoc.concytec.service;
/*
 * Autor Moises Pelaez S
 * mpelaezs@gmail.com
 * fecha:19-02-2012
 * 
 * */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




//import sun.util.calendar.LocalGregorianCalendar.Date;
//import tramitedoc.concytec.action.AActionAdjuntarDocumentoInterno;
import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BCorrelativos;
import tramitedoc.concytec.bean.BDatosFirmante;
import tramitedoc.concytec.bean.BFondo;
import tramitedoc.concytec.bean.BInfo;
import tramitedoc.concytec.bean.BInfoDocumento;
import tramitedoc.concytec.bean.BInfoDocumentoProyecto;
import tramitedoc.concytec.bean.BInfoInv;
import tramitedoc.concytec.bean.BInfoListaReiterativo;
import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.bean.BOficinas;
import tramitedoc.concytec.bean.BPersonal;
import tramitedoc.concytec.bean.BTipoDocumento;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.util.Constantes;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.ObtieneConexion;

public class DocumentosInternosServiceImplement implements
		DocumentosInternosService {
	protected  Log log = LogFactory.getLog(DocumentosInternosServiceImplement.class);
	@Override
	public ArrayList<EstandarBean> getTipoDocumentosInternosLista()
			throws Exception {
		ArrayList<EstandarBean> resultado= new ArrayList<EstandarBean>();
		Statement us  = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		EstandarBean item;
		
		 try {	
     		
				String ls_sql = "SELECT codigo_tipo_documento_interno, nombre   FROM tramite.tipo_documento_interno_oficial";
				
				conn = obt.getConnection();
				us = conn.createStatement();
				rst = us.executeQuery(ls_sql);    		
				log.info("sql :"+ls_sql);
	    		while(rst.next()){
	    			item= new EstandarBean();
	    			item.setIdBean(rst.getInt("codigo_tipo_documento_interno"));
	    			item.setNombreBean(rst.getString("nombre"));
	    			resultado.add(item);
	    		}
	            obt.cerrar_conexion(conn);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return resultado;
	}

	@Override
	public ArrayList<EstandarBean> getTipoDocumentosInternosListaTemp(int tipo)
			throws Exception {
		ArrayList<EstandarBean> resultado= new ArrayList<EstandarBean>();
		Statement us  = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		EstandarBean item;
		
		 try {	
     		
				String ls_sql = "SELECT codigo_tipo_documento_interno, nombre   FROM tramite.tipo_documento_interno_oficial WHERE codigo_tipo_documento_interno IN (1,2,3,4,5,8)";
				if(tipo == 1)
					ls_sql = "SELECT codigo_tipo_documento_interno, nombre   FROM tramite.tipo_documento_interno_oficial WHERE codigo_tipo_documento_interno IN (3,4,5)";
									
				conn = obt.getConnection();
				us = conn.createStatement();
				rst = us.executeQuery(ls_sql);    		
				log.info("sql :"+ls_sql);
	    		while(rst.next()){
	    			item= new EstandarBean();
	    			item.setIdBean(rst.getInt("codigo_tipo_documento_interno"));
	    			item.setNombreBean(rst.getString("nombre"));
	    			resultado.add(item);
	    		}
	            obt.cerrar_conexion(conn);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return resultado;
	}
	
	@Override
	public ArrayList<EstandarBean> getEstadoDocumentoInternoLista()
			throws Exception {
		ArrayList<EstandarBean> resultado= new ArrayList<EstandarBean>();
		Statement us  = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		EstandarBean item;
		
		 try {	
     		
				String ls_sql = "SELECT  codigo_estado_documento, nombre FROM tramite.estadodocumentointerno";
				
				conn = obt.getConnection();
				us = conn.createStatement();
				rst = us.executeQuery(ls_sql);    		
				log.info("sql :"+ls_sql);
				while(rst.next()){
	    			item= new EstandarBean();
	    			item.setIdBean(rst.getInt("codigo_estado_documento"));
	    			item.setNombreBean(rst.getString("nombre"));
	    			resultado.add(item);
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return resultado;
	}
	
	public ArrayList<EstandarBean> getEstadoDocumentoInternoListaSecretarias()
	throws Exception {
			ArrayList<EstandarBean> resultado= new ArrayList<EstandarBean>();
			Statement us  = null;
			ResultSet rst = null;		
			Connection conn = null;
			ObtieneConexion obt=new ObtieneConexion();
			EstandarBean item;

			try {	
		
		String ls_sql = "SELECT  codigo_estado_documento, nombre FROM tramite.estadodocumentointerno WHERE codigo_estado_documento NOT IN (3)";
		
		conn = obt.getConnection();
		us = conn.createStatement();
		rst = us.executeQuery(ls_sql);    		
		log.info("sql :"+ls_sql);
		while(rst.next()){
			item= new EstandarBean();
			item.setIdBean(rst.getInt("codigo_estado_documento"));
			item.setNombreBean(rst.getString("nombre"));
			resultado.add(item);
		}
        obt.cerrar_conexion(conn,rst);		
        
    } catch (SQLException sqle) {
    	obt.cerrar_conexion(conn);
		sqle.printStackTrace();
    }

return resultado;
}

	@Override
	public void saveDocumentoInterno(DocumentoInternoBean documentoInterno, BInfoDocumento bInfoDocuEliminado)
			throws Exception {
		log.info("Entrando a saveDocumentoInterno...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		log.info("codigo_documento_interno:" +documentoInterno.getCodigo_tipo_documento_interno());
		log.info("Asunto():" +documentoInterno.getAsunto());
		Calendar c = new GregorianCalendar(); 
		int tipo = documentoInterno.getCodigo_tipo_documento_interno();
		
		
		
		
		try {	
     		
			if(tipo==6){
				//ccp
				String insertQuery = "INSERT INTO tramite.documento_tipo_documento_interno(codigo_tipo_documento_interno, codigo_documento, asunto, descripcion,  dirigido_a, firmado_por, fecha_creacion, fecha_modificacion,codigo_estado_documento,codigo_oficina_pertenece,codigo_oficina,numero_doc_temporal, anio_doc,referencia,nombre_doc_adjuntos,codigos_oficinas_destinos_copias,personas,abreviatura_grado_profesion,nombre_archivo,flag,motivo,actividad,meta,especifica,valor,observacion,nota,numerofilas,numero_doc,codigo_expediente,codigo_recepcion,secuencia_movimiento,tipo_envio)    VALUES (?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(insertQuery);
				pstmt.setInt(1, documentoInterno.getCodigo_tipo_documento_interno());
				pstmt.setInt(2, documentoInterno.getCodigo_documento());
				pstmt.setString(3, documentoInterno.getAsunto());
				pstmt.setString(4, documentoInterno.getDescripcion());
				pstmt.setString(5, documentoInterno.getDirigido_a());
				pstmt.setString(6, documentoInterno.getFirmado_por());
				pstmt.setTimestamp(7, new  Timestamp(System.currentTimeMillis()));
				pstmt.setTimestamp(8, new  Timestamp(System.currentTimeMillis()));
				pstmt.setInt(9,documentoInterno.getCodigo_estado_documento());
				pstmt.setInt(10,documentoInterno.getCodigo_oficina_pertenece());
				pstmt.setInt(11,documentoInterno.getCodigo_oficina());
				pstmt.setInt(12,0);
				pstmt.setInt(13,c.get(Calendar.YEAR));
				pstmt.setString(14, documentoInterno.getReferencia());
				pstmt.setString(15, documentoInterno.getNombre_doc_adjuntos());
				pstmt.setString(16, documentoInterno.getCodigos_oficinas_destinos_copias());
				pstmt.setInt(17, documentoInterno.getPersonas());
				pstmt.setString(18, documentoInterno.getAbreviatura_grado_profesion());
				pstmt.setString(19, documentoInterno.getNombre_arhivo());
				
				if(documentoInterno.isChecked()){
					pstmt.setInt(20, 1);
				}
				else{
					pstmt.setInt(20, 0);
				}
				
				pstmt.setString(21, documentoInterno.getMotivo());
				pstmt.setString(22, documentoInterno.getActividad());
				pstmt.setString(23, documentoInterno.getMeta());
				pstmt.setString(24, documentoInterno.getEspecifica());
				pstmt.setString(25, documentoInterno.getValor());
				pstmt.setString(26, documentoInterno.getObservacion());
				pstmt.setString(27, documentoInterno.getNota());
				pstmt.setInt(28, documentoInterno.getNumerofilas());
				pstmt.setInt(29, documentoInterno.getNumero_doc());
			
				pstmt.setInt(30, documentoInterno.getCodigo_expediente());
				pstmt.setInt(31, documentoInterno.getCodigo_recepcion());
				pstmt.setInt(32, documentoInterno.getSecuencia_movimiento());
				pstmt.setString(33, documentoInterno.getTipo_envio());
				
			}else{
				if(tipo==7){
					//tdr
					
					String insertQuery = "INSERT INTO tramite.documento_tipo_documento_interno(codigo_tipo_documento_interno, codigo_documento, asunto, descripcion,  dirigido_a, firmado_por, fecha_creacion, fecha_modificacion,codigo_estado_documento,codigo_oficina_pertenece,codigo_oficina,numero_doc_temporal, anio_doc,referencia,nombre_doc_adjuntos,codigos_oficinas_destinos_copias,personas,abreviatura_grado_profesion,nombre_archivo,flag,objetivo,justificacion,finalidad,actividad,entregable,plazo,valor,financiamiento,modalidad,responsable_coordinacion,responsable_conformidad, numero_doc,codigo_expediente,codigo_recepcion,secuencia_movimiento,tipo_envio)    VALUES (?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
					
					conn = obt.getConnection();
					pstmt = conn.prepareStatement(insertQuery);
					pstmt.setInt(1, documentoInterno.getCodigo_tipo_documento_interno());
					pstmt.setInt(2, documentoInterno.getCodigo_documento());
					pstmt.setString(3, documentoInterno.getAsunto());
					pstmt.setString(4, documentoInterno.getDescripcion());
					pstmt.setString(5, documentoInterno.getDirigido_a());
					pstmt.setString(6, documentoInterno.getFirmado_por());
					pstmt.setTimestamp(7, new  Timestamp(System.currentTimeMillis()));
					pstmt.setTimestamp(8, new  Timestamp(System.currentTimeMillis()));
					pstmt.setInt(9,documentoInterno.getCodigo_estado_documento());
					pstmt.setInt(10,documentoInterno.getCodigo_oficina_pertenece());
					pstmt.setInt(11,documentoInterno.getCodigo_oficina());
					pstmt.setInt(12,0);
					pstmt.setInt(13,c.get(Calendar.YEAR));
					pstmt.setString(14, documentoInterno.getReferencia());
					pstmt.setString(15, documentoInterno.getNombre_doc_adjuntos());
					pstmt.setString(16, documentoInterno.getCodigos_oficinas_destinos_copias());
					pstmt.setInt(17, documentoInterno.getPersonas());
					pstmt.setString(18, documentoInterno.getAbreviatura_grado_profesion());
					pstmt.setString(19, documentoInterno.getNombre_arhivo());
										
					if(documentoInterno.isChecked()){
						pstmt.setInt(20, 1);
					}
					else{
						pstmt.setInt(20, 0);
					}
					pstmt.setString(21, documentoInterno.getObjetivo());
					pstmt.setString(22, documentoInterno.getJustificacion());
					pstmt.setString(23, documentoInterno.getFinalidad());
					pstmt.setString(24, documentoInterno.getActividad());
					pstmt.setString(25, documentoInterno.getEntregable());
					pstmt.setString(26, documentoInterno.getPlazo());
					pstmt.setString(27, documentoInterno.getValor());
					pstmt.setString(28, documentoInterno.getFinanciamiento());
					pstmt.setString(29, documentoInterno.getModalidad());
					pstmt.setString(30, documentoInterno.getResponsable_coordinacion());
					pstmt.setString(31, documentoInterno.getResponsable_conformidad());
					pstmt.setInt(32, documentoInterno.getNumero_doc());
					
					pstmt.setInt(33, documentoInterno.getCodigo_expediente());
					pstmt.setInt(34, documentoInterno.getCodigo_recepcion());
					pstmt.setInt(35, documentoInterno.getSecuencia_movimiento());
					pstmt.setString(36, documentoInterno.getTipo_envio());
					
				}else{
					//otro
					String insertQuery = "INSERT INTO tramite.documento_tipo_documento_interno(codigo_tipo_documento_interno, codigo_documento, asunto, descripcion,  dirigido_a, firmado_por, fecha_creacion, fecha_modificacion,codigo_estado_documento,codigo_oficina_pertenece,codigo_oficina,numero_doc_temporal,anio_doc,referencia,nombre_doc_adjuntos,codigos_oficinas_destinos_copias,personas,abreviatura_grado_profesion,nombre_archivo,numero_doc,flag,codigo_expediente,codigo_recepcion,secuencia_movimiento,tipo_envio,codigos_oficinas_firmar,codigos_oficinas_visto_bueno)    VALUES (?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
					
					conn = obt.getConnection();
					pstmt = conn.prepareStatement(insertQuery);
					pstmt.setInt(1, documentoInterno.getCodigo_tipo_documento_interno());
					pstmt.setInt(2, documentoInterno.getCodigo_documento());
					pstmt.setString(3, documentoInterno.getAsunto());
					pstmt.setString(4, documentoInterno.getDescripcion());
					pstmt.setString(5, documentoInterno.getDirigido_a());
					pstmt.setString(6, documentoInterno.getFirmado_por());
					pstmt.setTimestamp(7, new  Timestamp(System.currentTimeMillis()));
					pstmt.setTimestamp(8, new  Timestamp(System.currentTimeMillis()));
					pstmt.setInt(9,documentoInterno.getCodigo_estado_documento());
					pstmt.setInt(10,documentoInterno.getCodigo_oficina_pertenece());
					pstmt.setInt(11,documentoInterno.getCodigo_oficina());
					pstmt.setInt(12,0);
					pstmt.setInt(13,c.get(Calendar.YEAR));
					pstmt.setString(14, documentoInterno.getReferencia());
					pstmt.setString(15, documentoInterno.getNombre_doc_adjuntos());
					pstmt.setString(16, documentoInterno.getCodigos_oficinas_destinos_copias());
					pstmt.setInt(17, documentoInterno.getPersonas());
					pstmt.setString(18, documentoInterno.getAbreviatura_grado_profesion());
					pstmt.setString(19, documentoInterno.getNombre_arhivo());
					pstmt.setInt(20, documentoInterno.getNumero_doc());
					
					if(documentoInterno.isChecked()){
						pstmt.setInt(21, 1);
					}
					else{
						pstmt.setInt(21, 0);
					}
					
					pstmt.setInt(22, documentoInterno.getCodigo_expediente());
					pstmt.setInt(23, documentoInterno.getCodigo_recepcion());
					pstmt.setInt(24, documentoInterno.getSecuencia_movimiento());
					pstmt.setString(25, documentoInterno.getTipo_envio());
					
					pstmt.setString(26, documentoInterno.getCodigos_firmantes_destino());
					pstmt.setString(27, documentoInterno.getCodigos_visto_bueno_destino());
				
				}
			}
			
			pstmt.executeUpdate();
			
			if(bInfoDocuEliminado.getNumero_doc()>0){
			
				String insertQueryx = "UPDATE  tramite.documento_tipo_documento_interno SET codigo_estado_documento = 8  WHERE codigo_documento_interno=? ";
				//conn = obt.getConnection();
				pstmt = conn.prepareStatement(insertQueryx);
				pstmt.setInt(1, bInfoDocuEliminado.getCodigo_documento_interno());
				pstmt.executeUpdate();
			}
			
            obt.cerrar_conexion(conn);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
        
		
	}

	@Override
	public Collection getLista_oficinas() throws Exception {
		Collection ListaOficinas = new ArrayList();
		
		BOficinas BOficinasVO = null;
		Statement us  = null;
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		EstandarBean item;
		
		 try {	
     		
				String ls_sql = " select codigo_oficina,descripcion_oficina,nombre_corto,siglas_oficina from tramite.oficinas where institucion=2 and es_activo='A' order by orden ";
				
				conn = obt.getConnection();
				us = conn.createStatement();
				rst = us.executeQuery(ls_sql);    		
				log.info("sql :"+ls_sql);
				while(rst.next()){
					BOficinasVO = new BOficinas();					
					BOficinasVO.setCodigo_oficina(rst.getString("codigo_oficina"));
					BOficinasVO.setDescripcion_oficina(rst.getString("siglas_oficina")+" - "+rst.getString("descripcion_oficina"));
					BOficinasVO.setNombre_corto(rst.getString("nombre_corto"));
					BOficinasVO.setSiglas_oficina(rst.getString("siglas_oficina"));	
					BOficinasVO.setOficina(rst.getString("descripcion_oficina"));
					
					ListaOficinas.add(BOficinasVO);
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return ListaOficinas;
	}

	@Override
	public List<String> lista_Personas() throws Exception {
		List<String> ListaPersonas = new ArrayList<String>();
		
		
		Statement us  = null;
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		EstandarBean item;
		
		 try {	
     		
				String ls_sql = " SELECT codigo_persona, nombre_persona, tipo, codigo_oficina   FROM tramite.persona; ";
				
				conn = obt.getConnection();
				us = conn.createStatement();
				rst = us.executeQuery(ls_sql);    		
				log.info("sql :"+ls_sql);
				while(rst.next()){
					ListaPersonas.add(rst.getString("nombre_persona"));
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return ListaPersonas;
	}

	@Override
	public int of_codigo_oficina(String usuario_actual) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		int codigo_oficina=0;
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		EstandarBean item;
		
		 try {	
     		
				String ls_sql = "select us.*,pe.* from tramite.usuarios us, tramite.personal pe where"
				+ " us.usuario=pe.c_usuario and pe.c_usuario=?";
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setString(1, usuario_actual);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					codigo_oficina= rst.getInt("codigo_oficina");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return codigo_oficina;
	}

	@Override
	public String of_nombre_director_oficina(int codigo_oficina)
			throws Exception {
		PreparedStatement pstmt = null;
		String nombre_director_oficina="";
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		EstandarBean item;
		
		 try {	
     		
			 String ls_sql = "select codigo_personal,nombre_personal from tramite.personal where codigo_oficina=? and es_responsable='S'";
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setInt(1, codigo_oficina);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					nombre_director_oficina= rst.getString("nombre_personal");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return nombre_director_oficina;
	}

	
	public ArrayList<DocumentoInternoBean> getlistaDocumentosInternos(
			Integer codigo_oficina_pertenece) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ArrayList<DocumentoInternoBean> myLista = new ArrayList<DocumentoInternoBean>();
		DocumentoInternoBean item;
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		StringBuffer ls_sql = new StringBuffer("SELECT codigo_documento_interno, codigo_tipo_documento_interno, codigo_documento, asunto, descripcion,   dirigido_a, firmado_por, fecha_creacion, fecha_modificacion,     codigo_estado_documento, codigo_oficina, codigo_oficina_pertenece, numero_doc, anio_doc,personas,nombre_archivo FROM tramite.documento_tipo_documento_interno  WHERE codigo_oficina_pertenece =?  and codigo_estado_documento NOT IN (7,8) ORDER BY 1 desc ");
		
		 try {	
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1,codigo_oficina_pertenece);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				while(rst.next()){
					item = new DocumentoInternoBean();
					item.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
					item.setCodigo_tipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
					item.setAsunto(rst.getString("asunto"));
					item.setDirigido_a(rst.getString("dirigido_a"));
					item.setCodigo_estado_documento(rst.getInt("codigo_estado_documento"));
					item.setFecha_creacion(rst.getTimestamp("fecha_creacion"));
					item.setCodigo_oficina(rst.getInt("codigo_oficina"));
					
					String link="ModificarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' alt='modificar' width='24' height='24' border='0'> </a>";
					
					
					String verPDF="VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					verPDF="<a  href='"+verPDF+"'>"+"<img src='img/pdf.gif' alt='ver documento PDF' width='24' height='24' border='0'> </a>";
					
				
				
					
					item.setLink(verPDF);
					item.setNombre_arhivo(rst.getString("nombre_archivo"));
					
					
					// es es diferente de FIRMADO O  CERRADO solo veran el PDF 
					
				if(item.getCodigo_estado_documento()!=3 ){ //4 FIRMADO
					
					item.setLink(link +" "+verPDF);
				}	
					
					myLista.add(item);
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return myLista;
	}

	@Override
	public String getNombreSelloOficina(Integer codigo_oficina_pertenece) throws Exception {
		PreparedStatement pstmt = null;
		String nombre_sello_oficina="";
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		
		 try {	
     		
			 String ls_sql = "SELECT  nombre_imagen_sello  FROM tramite.oficinas where codigo_oficina=?";
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setInt(1, codigo_oficina_pertenece);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql+codigo_oficina_pertenece);
				if(rst.next()){
					nombre_sello_oficina= rst.getString("nombre_imagen_sello");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return nombre_sello_oficina.trim();
	}

	@Override
	public int getSiguienteNumeroDocumento(Integer codigo_oficina_pertenece,
			Integer codigo_tipo_documento_interno) throws Exception {
		PreparedStatement pstmt = null;
		int numSiguiente=0;
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		
		 try {	
     		
			 StringBuffer ls_sql = new StringBuffer("SELECT MAX(numero_doc)  as numero_doc   FROM tramite.documento_tipo_documento_interno WHERE codigo_oficina_pertenece=? AND codigo_tipo_documento_interno=? AND codigo_estado_documento NOT IN (7,8)");//AND anio_doc=2012
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1,codigo_oficina_pertenece);
				pstmt.setInt(2,codigo_tipo_documento_interno);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					numSiguiente= rst.getInt("numero_doc");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
	        numSiguiente++;
		return numSiguiente;
	}
	
	public BInfoDocumento getNumeroDocumentoEliminado(Integer codigo_oficina_pertenece,
			Integer codigo_tipo_documento_interno) throws Exception {
		
		BInfoDocumento bInfoDocuEliminado = new BInfoDocumento();	
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		log.info("Entre -getNumeroDocumentoEliminado");
		 try {	
     		
			 StringBuffer ls_sql = new StringBuffer("SELECT MAX(numero_doc) as numero_doc, codigo_documento_interno  FROM tramite.documento_tipo_documento_interno WHERE codigo_oficina_pertenece=? AND codigo_tipo_documento_interno=? AND codigo_estado_documento=7  GROUP BY codigo_documento_interno ");//AND anio_doc=2012
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1,codigo_oficina_pertenece);
				pstmt.setInt(2,codigo_tipo_documento_interno);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					bInfoDocuEliminado.setNumero_doc(rst.getInt("numero_doc"));
					bInfoDocuEliminado.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
	    		}
	            obt.cerrar_conexion(conn,rst);	
	            

	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
	        
            	        
		return bInfoDocuEliminado;
	}
	
	public int getSiguienteNumeroDocumento(Integer codigo_oficina_pertenece,
			Integer codigo_tipo_documento_interno, boolean esproyecto) throws Exception {
		
		log.info("Entre -getSiguienteNumeroDocumento proyectos");
		PreparedStatement pstmt = null;
		int numSiguiente=0;
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		 try {	
     		
			 StringBuffer ls_sql = new StringBuffer("SELECT MAX(numero_doc) as numero_doc   FROM tramite.documento_tipo_documento_interno WHERE codigo_oficina_pertenece=? AND codigo_tipo_documento_interno=? AND codigo_estado_documento=1 AND flag = 0; ");//AND anio_doc=2012
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1,codigo_oficina_pertenece);
				pstmt.setInt(2,codigo_tipo_documento_interno);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					numSiguiente= rst.getInt("numero_doc");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
	        numSiguiente++;
		return numSiguiente;
	}
	
	public BInfoDocumento getNumeroDocumentoEliminado(Integer codigo_oficina_pertenece,
			Integer codigo_tipo_documento_interno, boolean esproyecto) throws Exception {
		
		log.info("Entre -getNumeroDocumentoEliminado proyectos");
		PreparedStatement pstmt = null;
		BInfoDocumento bInfoDocuEliminado = new BInfoDocumento();	
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		 try {	
     		
			 StringBuffer ls_sql = new StringBuffer("SELECT MAX(numero_doc) as numero_doc, codigo_documento_interno   FROM tramite.documento_tipo_documento_interno WHERE codigo_oficina_pertenece=? AND codigo_tipo_documento_interno=? AND codigo_estado_documento=7 AND flag = 0  GROUP BY codigo_documento_interno; ");//AND anio_doc=2012
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1,codigo_oficina_pertenece);
				pstmt.setInt(2,codigo_tipo_documento_interno);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					bInfoDocuEliminado.setNumero_doc(rst.getInt("numero_doc"));
					bInfoDocuEliminado.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
	        
		return bInfoDocuEliminado;
	}

	@Override
	public String getNombreOficialAnio(int anio) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String nombre="";
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		
		 try {	
     		
			 String ls_sql = "SELECT nombre FROM tramite.nombre_oficial_anio WHERE anio=?";

				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setInt(1, anio);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					nombre= rst.getString("nombre");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return nombre;
	}

	@Override
	public String getNombreOficina(Integer codigo_oficina_pertenece) throws Exception {
	PreparedStatement pstmt = null;
	String siglas_oficina="";
	ResultSet rst = null;
	
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	
	
	 try {	
			
		 String ls_sql = "select descripcion_oficina from tramite.oficinas where codigo_oficina = ?";
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql);
			pstmt.setInt(1, codigo_oficina_pertenece);
			rst = pstmt.executeQuery();	
			log.info("sql :"+ls_sql);
			if(rst.next()){
				siglas_oficina= rst.getString("descripcion_oficina");
			}
	        obt.cerrar_conexion(conn,rst);		
	        
	    } catch (SQLException sqle) {
	    	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
	    }
	return siglas_oficina.trim();
	}
	
	@Override
	public String getSiglasOficina(Integer codigo_oficina_pertenece)
			throws Exception {
		PreparedStatement pstmt = null;
		String siglas_oficina="";
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		
		 try {	
     		
			 String ls_sql = "SELECT  siglas_oficina_pdf   FROM tramite.oficinas where codigo_oficina=?";
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setInt(1, codigo_oficina_pertenece);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					siglas_oficina= rst.getString("siglas_oficina_pdf");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return siglas_oficina.trim();
	}

	@Override
	public String getCargo(Integer personas) throws Exception {
		PreparedStatement pstmt = null;
		String cargo_persona="";
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		
		 try {	
     		
			 String ls_sql = "SELECT codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable,B.nombrecargo as nombrecargo   FROM tramite.personal A, tramite.cargo B"+
			 	" WHERE  A.cargo_personal=B.codigo_cargo " +
			 	" AND codigo_personal=?";
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setInt(1, personas);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					cargo_persona= rst.getString("nombrecargo");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return cargo_persona;
	}
	
	public String getNombre_Personal(Integer personas) throws Exception {
		PreparedStatement pstmt = null;
		String cargo_persona="";
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		
		 try {	
     		
			 String ls_sql = "SELECT codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable,B.nombrecargo as nombrecargo   FROM tramite.personal A, tramite.cargo B"+
			 	" WHERE  A.cargo_personal=B.codigo_cargo " +
			 	" AND codigo_personal=?";
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setInt(1, personas);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					cargo_persona= rst.getString("nombre_personal");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return cargo_persona;
	}

	//DAAA
	@Override
	public DocumentoInternoBean getDocumentoInterno(int codigo_documento_interno, String tipo_documento)
			throws Exception {
		PreparedStatement pstmt = null;		
		ResultSet rst = null;
		DocumentoInternoBean documentoInterno= null;
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		int valor_flag=0;
		
		 try {	
     		log.info("entre aesta funcion >>+>>>");
			 if(tipo_documento.equals("graba") || tipo_documento.equals("adjunta") || tipo_documento.equals("todo") || tipo_documento.equals("normal")){
			 
				String ls_sql = "SELECT codigo_documento_interno, codigo_tipo_documento_interno, codigo_documento, asunto, descripcion, dirigido_a, firmado_por, fecha_creacion, fecha_modificacion, codigo_estado_documento, codigo_oficina, codigo_oficina_pertenece, numero_doc, anio_doc, referencia, nombre_doc_adjuntos, codigos_oficinas_destinos_copias,personas,abreviatura_grado_profesion,nombre_archivo,flag,codigo_expediente,codigo_recepcion,secuencia_movimiento,tipo_envio,  codigos_oficinas_firmar, codigos_oficinas_visto_bueno   FROM tramite.documento_tipo_documento_interno WHERE codigo_documento_interno=?";
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setInt(1, codigo_documento_interno);
				log.info("sql :"+pstmt);
				rst = pstmt.executeQuery();	
				
				if(rst.next()){
					documentoInterno = new DocumentoInternoBean();
					documentoInterno.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
					documentoInterno.setCodigo_tipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
					documentoInterno.setCodigo_documento(rst.getInt("codigo_documento"));
					documentoInterno.setAsunto(rst.getString("asunto"));
					documentoInterno.setDescripcion(rst.getString("descripcion"));
					documentoInterno.setDirigido_a(rst.getString("dirigido_a"));
					documentoInterno.setFirmado_por(rst.getString("firmado_por"));					
					documentoInterno.setFecha_creacion(rst.getDate("fecha_creacion"));
					documentoInterno.setCodigo_estado_documento(rst.getInt("codigo_estado_documento"));
					documentoInterno.setCodigo_oficina(rst.getInt("codigo_oficina"));
					documentoInterno.setCodigo_oficina_pertenece(rst.getInt("codigo_oficina_pertenece"));			
					documentoInterno.setNumero_doc(rst.getInt("numero_doc"));
					documentoInterno.setAnio_doc(rst.getInt("anio_doc"));
					documentoInterno.setReferencia(rst.getString("referencia"));
					documentoInterno.setNombre_doc_adjuntos(rst.getString("nombre_doc_adjuntos"));
					documentoInterno.setCodigos_oficinas_destinos_copias(rst.getString("codigos_oficinas_destinos_copias"));								
					documentoInterno.setPersonas(rst.getInt("personas"));
					documentoInterno.setAbreviatura_grado_profesion(rst.getString("abreviatura_grado_profesion"));
					documentoInterno.setNombre_arhivo(rst.getString("nombre_archivo"));
					valor_flag=rst.getInt("flag");
					if(valor_flag==1){
						documentoInterno.setChecked(true);
					}
					else{
						documentoInterno.setChecked(false);	
					}
					
					documentoInterno.setCodigo_expediente(rst.getInt("codigo_expediente"));
					documentoInterno.setCodigo_recepcion(rst.getInt("codigo_recepcion"));
					documentoInterno.setSecuencia_movimiento(rst.getInt("secuencia_movimiento"));
					documentoInterno.setTipo_envio(rst.getString("tipo_envio"));
					
					log.info("codigos_oficinas_firmar ----"+rst.getString("codigos_oficinas_firmar"));
					documentoInterno.setCodigos_firmantes_destino(rst.getString("codigos_oficinas_firmar"));
					documentoInterno.setCodigos_visto_bueno_destino(rst.getString("codigos_oficinas_visto_bueno"));
										
					log.info("personas en Documento: " +documentoInterno.getPersonas());
					
	    		}
			 }
			 if(tipo_documento.equals("ccp")){
				 //ccp
				 String ls_sql = "SELECT codigo_documento_interno, codigo_tipo_documento_interno, codigo_documento, asunto, descripcion, dirigido_a, firmado_por, fecha_creacion, fecha_modificacion, codigo_estado_documento, codigo_oficina, codigo_oficina_pertenece, numero_doc, anio_doc, referencia, nombre_doc_adjuntos, codigos_oficinas_destinos_copias,personas,abreviatura_grado_profesion,nombre_archivo,flag,motivo,actividad,meta,especifica,valor,observacion,nota,numerofilas,codigo_expediente,codigo_recepcion,secuencia_movimiento,tipo_envio   FROM tramite.documento_tipo_documento_interno WHERE codigo_documento_interno=?";
					
					
					conn = obt.getConnection();
					pstmt = conn.prepareStatement(ls_sql);
					pstmt.setInt(1, codigo_documento_interno);
					rst = pstmt.executeQuery();	
					log.info("sql :"+ls_sql);
					if(rst.next()){
						documentoInterno = new DocumentoInternoBean();
						documentoInterno.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
						documentoInterno.setCodigo_tipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
						documentoInterno.setCodigo_documento(rst.getInt("codigo_documento"));
						documentoInterno.setAsunto(rst.getString("asunto"));
						documentoInterno.setDescripcion(rst.getString("descripcion"));
						documentoInterno.setDirigido_a(rst.getString("dirigido_a"));
						documentoInterno.setFirmado_por(rst.getString("firmado_por"));					
						documentoInterno.setFecha_creacion(rst.getDate("fecha_creacion"));
						documentoInterno.setCodigo_estado_documento(rst.getInt("codigo_estado_documento"));
						documentoInterno.setCodigo_oficina(rst.getInt("codigo_oficina"));
						documentoInterno.setCodigo_oficina_pertenece(rst.getInt("codigo_oficina_pertenece"));			
						documentoInterno.setNumero_doc(rst.getInt("numero_doc"));
						documentoInterno.setAnio_doc(rst.getInt("anio_doc"));
						documentoInterno.setReferencia(rst.getString("referencia"));
						documentoInterno.setNombre_doc_adjuntos(rst.getString("nombre_doc_adjuntos"));
						documentoInterno.setCodigos_oficinas_destinos_copias(rst.getString("codigos_oficinas_destinos_copias"));								
						documentoInterno.setPersonas(rst.getInt("personas"));
						documentoInterno.setAbreviatura_grado_profesion(rst.getString("abreviatura_grado_profesion"));
						documentoInterno.setNombre_arhivo(rst.getString("nombre_archivo"));
						valor_flag=rst.getInt("flag");
						if(valor_flag==1){
							documentoInterno.setChecked(true);
						}
						else{
							documentoInterno.setChecked(false);	
						}
						documentoInterno.setMotivo(rst.getString("motivo"));
						documentoInterno.setActividad(rst.getString("actividad"));
						documentoInterno.setMeta(rst.getString("meta"));
						documentoInterno.setEspecifica(rst.getString("especifica"));
						documentoInterno.setValor(rst.getString("valor"));
						documentoInterno.setObservacion(rst.getString("observacion"));
						documentoInterno.setNota(rst.getString("nota"));
						documentoInterno.setCodigo_tipo_requerimiento(rst.getInt("numerofilas"));
						
						documentoInterno.setCodigo_expediente(rst.getInt("codigo_expediente"));
						documentoInterno.setCodigo_recepcion(rst.getInt("codigo_recepcion"));
						documentoInterno.setSecuencia_movimiento(rst.getInt("secuencia_movimiento"));
						documentoInterno.setTipo_envio(rst.getString("tipo_envio"));
						
						log.info("personas en Documento: " +documentoInterno.getPersonas());
						
		    		}
			 }
			 if(tipo_documento.equals("tdr")){
				 //tdr

					String ls_sql = "SELECT codigo_documento_interno, codigo_tipo_documento_interno, codigo_documento, asunto, descripcion, dirigido_a, firmado_por, fecha_creacion, fecha_modificacion, codigo_estado_documento, codigo_oficina, codigo_oficina_pertenece, numero_doc, anio_doc, referencia, nombre_doc_adjuntos, codigos_oficinas_destinos_copias,personas,abreviatura_grado_profesion,nombre_archivo,flag,objetivo,justificacion,finalidad,actividad,entregable,plazo,valor,financiamiento,modalidad,responsable_coordinacion,responsable_conformidad,codigo_expediente,codigo_recepcion,secuencia_movimiento,tipo_envio   FROM tramite.documento_tipo_documento_interno WHERE codigo_documento_interno=?";
					
					
					conn = obt.getConnection();
					pstmt = conn.prepareStatement(ls_sql);
					pstmt.setInt(1, codigo_documento_interno);
					rst = pstmt.executeQuery();	
					log.info("sql :"+ls_sql);
					if(rst.next()){
						documentoInterno = new DocumentoInternoBean();
						documentoInterno.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
						documentoInterno.setCodigo_tipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
						documentoInterno.setCodigo_documento(rst.getInt("codigo_documento"));
						documentoInterno.setAsunto(rst.getString("asunto"));
						documentoInterno.setDescripcion(rst.getString("descripcion"));
						documentoInterno.setDirigido_a(rst.getString("dirigido_a"));
						documentoInterno.setFirmado_por(rst.getString("firmado_por"));					
						documentoInterno.setFecha_creacion(rst.getDate("fecha_creacion"));
						documentoInterno.setCodigo_estado_documento(rst.getInt("codigo_estado_documento"));
						documentoInterno.setCodigo_oficina(rst.getInt("codigo_oficina"));
						documentoInterno.setCodigo_oficina_pertenece(rst.getInt("codigo_oficina_pertenece"));			
						documentoInterno.setNumero_doc(rst.getInt("numero_doc"));
						documentoInterno.setAnio_doc(rst.getInt("anio_doc"));
						documentoInterno.setReferencia(rst.getString("referencia"));
						documentoInterno.setNombre_doc_adjuntos(rst.getString("nombre_doc_adjuntos"));
						documentoInterno.setCodigos_oficinas_destinos_copias(rst.getString("codigos_oficinas_destinos_copias"));								
						documentoInterno.setPersonas(rst.getInt("personas"));
						documentoInterno.setAbreviatura_grado_profesion(rst.getString("abreviatura_grado_profesion"));
						documentoInterno.setNombre_arhivo(rst.getString("nombre_archivo"));
						valor_flag=rst.getInt("flag");
						if(valor_flag==1){
							documentoInterno.setChecked(true);
						}
						else{
							documentoInterno.setChecked(false);	
						}
						
						documentoInterno.setObjetivo(rst.getString("objetivo"));
						documentoInterno.setJustificacion(rst.getString("justificacion"));
						documentoInterno.setFinalidad(rst.getString("finalidad"));
						documentoInterno.setActividad(rst.getString("actividad"));
						documentoInterno.setEntregable(rst.getString("entregable"));
						documentoInterno.setPlazo(rst.getString("plazo"));
						documentoInterno.setValor(rst.getString("valor"));
						documentoInterno.setFinanciamiento(rst.getString("financiamiento"));
						documentoInterno.setModalidad(rst.getString("modalidad"));
						documentoInterno.setResponsable_coordinacion(rst.getString("responsable_coordinacion"));
						documentoInterno.setResponsable_conformidad(rst.getString("responsable_conformidad"));
						
						documentoInterno.setCodigo_expediente(rst.getInt("codigo_expediente"));
						documentoInterno.setCodigo_recepcion(rst.getInt("codigo_recepcion"));
						documentoInterno.setSecuencia_movimiento(rst.getInt("secuencia_movimiento"));
						documentoInterno.setTipo_envio(rst.getString("tipo_envio"));
																	
						log.info("personas en Documento: " +documentoInterno.getPersonas());
						
		    		}
				 
				 
			 }
				
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return documentoInterno;
	}

	@Override
	public void updateDocumentoInterno(DocumentoInternoBean documentoInterno)
			throws Exception {
		
		log.info("Entrando a updateDocumentoInterno...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		log.info("codigo_documento_interno:" +documentoInterno.getCodigo_tipo_documento_interno());
		log.info("Asunto():" +documentoInterno.getAsunto());
		log.info("Codigo estado doc >>>>"+ documentoInterno.getCodigo_estado_documento());
		Calendar c = new GregorianCalendar(); 
		int valor=0;
		int tipo = documentoInterno.getCodigo_tipo_documento_interno();
		
		if(documentoInterno.isChecked()){
			valor=1;
		}
		
		try {	
     	
			if(tipo==6){
				//ccp
				StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
				//uptateQuery.append(" SET codigo_tipo_documento_interno=?,"); 
				uptateQuery.append(" SET asunto=?, descripcion=?, dirigido_a=?, firmado_por=?,"); 
				uptateQuery.append("fecha_modificacion=?, codigo_estado_documento=?,"); 
				uptateQuery.append("codigo_oficina=?, codigo_oficina_pertenece=?,");
				
				/*if(documentoInterno.getCodigo_estado_documento()==3){
					uptateQuery.append("numero_doc=?,");
				}else{
					uptateQuery.append("numero_doc_temporal=? ,");
				}*/
				uptateQuery.append("numero_doc=?,");
				uptateQuery.append("anio_doc=?,"); 
				uptateQuery.append("referencia=?, nombre_doc_adjuntos=?,codigos_oficinas_destinos_copias=?,personas=?,abreviatura_grado_profesion=?,flag=?,");
				uptateQuery.append(" motivo=?, actividad=?, meta=?, especifica=?, valor=?, observacion=?, nota=?, numerofilas=?, nombre_archivo=?");
				uptateQuery.append(" WHERE codigo_documento_interno=?");

				
				conn = obt.getConnection();
				log.info("query de actualizacion:   " +uptateQuery.toString());
				pstmt = conn.prepareStatement(uptateQuery.toString());
				//pstmt.setInt(1, documentoInterno.getCodigo_tipo_documento_interno());
				pstmt.setString(1, documentoInterno.getAsunto());
				pstmt.setString(2, documentoInterno.getDescripcion());
				pstmt.setString(3, documentoInterno.getDirigido_a());
				pstmt.setString(4, documentoInterno.getFirmado_por());		
				pstmt.setTimestamp(5, new  Timestamp(System.currentTimeMillis()));			
				
				pstmt.setInt(6,documentoInterno.getCodigo_estado_documento());
				pstmt.setInt(7,documentoInterno.getCodigo_oficina());
				pstmt.setInt(8,documentoInterno.getCodigo_oficina_pertenece());
				
				
				pstmt.setInt(9,documentoInterno.getNumero_doc());
				pstmt.setInt(10,c.get(Calendar.YEAR));
				pstmt.setString(11, documentoInterno.getReferencia());
				pstmt.setString(12, documentoInterno.getNombre_doc_adjuntos());
				pstmt.setString(13, documentoInterno.getCodigos_oficinas_destinos_copias());
				pstmt.setInt(14, documentoInterno.getPersonas());
				pstmt.setString(15, documentoInterno.getAbreviatura_grado_profesion());
				pstmt.setInt(16, valor);
				pstmt.setString(17, documentoInterno.getMotivo());
				pstmt.setString(18, documentoInterno.getActividad());
				pstmt.setString(19, documentoInterno.getMeta());
				pstmt.setString(20, documentoInterno.getEspecifica());
				pstmt.setString(21, documentoInterno.getValor());
				pstmt.setString(22, documentoInterno.getObservacion());
				pstmt.setString(23, documentoInterno.getNota());
				pstmt.setInt(24, documentoInterno.getNumerofilas());
				pstmt.setString(25, documentoInterno.getNombre_arhivo());
				pstmt.setInt(26, documentoInterno.getCodigo_documento_interno());
				
			}else{
				if(tipo==7){
				
				//tdr
				
				StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
				//uptateQuery.append(" SET codigo_tipo_documento_interno=?,"); 
				uptateQuery.append(" SET asunto=?, descripcion=?, dirigido_a=?, firmado_por=?,"); 
				uptateQuery.append("fecha_modificacion=?, codigo_estado_documento=?,"); 
				uptateQuery.append("codigo_oficina=?, codigo_oficina_pertenece=?,");
				
				/*if(documentoInterno.getCodigo_estado_documento()==3){
					uptateQuery.append("numero_doc=?,");
				}else{
					uptateQuery.append("numero_doc_temporal=? ,");
				}*/
				uptateQuery.append("numero_doc=?,");				
				uptateQuery.append("anio_doc=?,"); 
				uptateQuery.append("referencia=?, nombre_doc_adjuntos=?,codigos_oficinas_destinos_copias=?,personas=?,abreviatura_grado_profesion=?,flag=?,");
				uptateQuery.append("objetivo=?, justificacion=?, finalidad=?, actividad=?, entregable=?, plazo=?, valor=?,");
				uptateQuery.append("financiamiento=?, modalidad=?, responsable_coordinacion=?, responsable_conformidad=?, nombre_archivo=?");
				uptateQuery.append(" WHERE codigo_documento_interno=?");

				
				conn = obt.getConnection();
				log.info("query de actualizacion:   " +uptateQuery.toString());
				pstmt = conn.prepareStatement(uptateQuery.toString());
				//pstmt.setInt(1, documentoInterno.getCodigo_tipo_documento_interno());
				pstmt.setString(1, documentoInterno.getAsunto());
				pstmt.setString(2, documentoInterno.getDescripcion());
				pstmt.setString(3, documentoInterno.getDirigido_a());
				pstmt.setString(4, documentoInterno.getFirmado_por());		
				pstmt.setTimestamp(5, new  Timestamp(System.currentTimeMillis()));			
				
				pstmt.setInt(6,documentoInterno.getCodigo_estado_documento());
				pstmt.setInt(7,documentoInterno.getCodigo_oficina());
				pstmt.setInt(8,documentoInterno.getCodigo_oficina_pertenece());
				
				
				pstmt.setInt(9,documentoInterno.getNumero_doc());
				pstmt.setInt(10,c.get(Calendar.YEAR));
				pstmt.setString(11, documentoInterno.getReferencia());
				pstmt.setString(12, documentoInterno.getNombre_doc_adjuntos());
				pstmt.setString(13, documentoInterno.getCodigos_oficinas_destinos_copias());
				pstmt.setInt(14, documentoInterno.getPersonas());
				pstmt.setString(15, documentoInterno.getAbreviatura_grado_profesion());
				pstmt.setInt(16, valor);
				
				pstmt.setString(17, documentoInterno.getObjetivo());
				pstmt.setString(18, documentoInterno.getJustificacion());
				pstmt.setString(19, documentoInterno.getFinalidad());
				pstmt.setString(20, documentoInterno.getActividad());
				pstmt.setString(21, documentoInterno.getEntregable());
				pstmt.setString(22, documentoInterno.getPlazo());
				pstmt.setString(23, documentoInterno.getValor());
				pstmt.setString(24, documentoInterno.getFinanciamiento());
				pstmt.setString(25, documentoInterno.getModalidad());
				pstmt.setString(26, documentoInterno.getResponsable_coordinacion());
				pstmt.setString(27, documentoInterno.getResponsable_conformidad());
				pstmt.setString(28, documentoInterno.getNombre_arhivo());
				
				pstmt.setInt(29, documentoInterno.getCodigo_documento_interno());
				
				
				}else{
					StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
					//uptateQuery.append(" SET codigo_tipo_documento_interno=?,"); 
					uptateQuery.append(" SET asunto=?, descripcion=?, dirigido_a=?, firmado_por=?,"); 
					uptateQuery.append("fecha_modificacion=?, codigo_estado_documento=?,"); 
					uptateQuery.append("codigo_oficina=?, codigo_oficina_pertenece=?,");
					
					/*if(documentoInterno.getCodigo_estado_documento()==3){
						uptateQuery.append("numero_doc=?,");
					}else{
						uptateQuery.append("numero_doc_temporal=? ,");
					}*/
					uptateQuery.append("numero_doc=?,");
					uptateQuery.append("anio_doc=?,"); 
					uptateQuery.append("referencia=?, nombre_doc_adjuntos=?,codigos_oficinas_destinos_copias=?,personas=?,abreviatura_grado_profesion=?,flag=?, nombre_archivo=?");
					uptateQuery.append(" , codigos_oficinas_firmar= ?, codigos_oficinas_visto_bueno=?  ");
					
					uptateQuery.append(" WHERE codigo_documento_interno=?");

					
					conn = obt.getConnection();
					log.info("query de actualizacion:   " +uptateQuery.toString());
					pstmt = conn.prepareStatement(uptateQuery.toString());
					//pstmt.setInt(1, documentoInterno.getCodigo_tipo_documento_interno());
					pstmt.setString(1, documentoInterno.getAsunto());
					pstmt.setString(2, documentoInterno.getDescripcion());
					pstmt.setString(3, documentoInterno.getDirigido_a());
					pstmt.setString(4, documentoInterno.getFirmado_por());		
					pstmt.setTimestamp(5, new  Timestamp(System.currentTimeMillis()));			
					
					pstmt.setInt(6,documentoInterno.getCodigo_estado_documento());
					pstmt.setInt(7,documentoInterno.getCodigo_oficina());
					pstmt.setInt(8,documentoInterno.getCodigo_oficina_pertenece());
					
					
					pstmt.setInt(9,documentoInterno.getNumero_doc());
					pstmt.setInt(10,c.get(Calendar.YEAR));
					pstmt.setString(11, documentoInterno.getReferencia());
					pstmt.setString(12, documentoInterno.getNombre_doc_adjuntos());
					pstmt.setString(13, documentoInterno.getCodigos_oficinas_destinos_copias());
					pstmt.setInt(14, documentoInterno.getPersonas());
					pstmt.setString(15, documentoInterno.getAbreviatura_grado_profesion());
					pstmt.setInt(16, valor);
					pstmt.setString(17, documentoInterno.getNombre_arhivo());
					
					pstmt.setString(18, documentoInterno.getCodigos_firmantes_destino());
					pstmt.setString(19, documentoInterno.getCodigos_visto_bueno_destino());
					pstmt.setInt(20, documentoInterno.getCodigo_documento_interno());
				}
			}
			
			pstmt.executeUpdate();
            obt.cerrar_conexion(conn);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
        
		
		
	}

	@Override
	public DocumentoInternoBean getDocumentoInterno(String parameter,int parseInt)  {
		PreparedStatement pstmt = null;		
		ResultSet rst = null;
		DocumentoInternoBean documentoInterno= null;
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		log.info("Tome getDocumentoInterno(String parameter,int parseInt)");
		
		 try {	
     		
			 String ls_sql = "SELECT codigo_documento_interno, codigo_tipo_documento_interno, codigo_documento,   asunto, descripcion, dirigido_a, firmado_por, fecha_creacion,       fecha_modificacion, codigo_estado_documento, codigo_oficina, codigo_oficina_pertenece, numero_doc, anio_doc, referencia, nombre_doc_adjuntos, codigos_oficinas_destinos_copias,personas,abreviatura_grado_profesion,nombre_archivo   FROM tramite.documento_tipo_documento_interno WHERE nombre_archivo=? AND codigo_oficina_pertenece=?";
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setString(1,parameter);
				pstmt.setInt(2, parseInt);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					documentoInterno = new DocumentoInternoBean();
					documentoInterno.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
					documentoInterno.setCodigo_tipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
					documentoInterno.setAsunto(rst.getString("asunto"));
					documentoInterno.setDescripcion(rst.getString("descripcion"));
					documentoInterno.setDirigido_a(rst.getString("dirigido_a"));
					documentoInterno.setFirmado_por(rst.getString("firmado_por"));					
					documentoInterno.setFecha_creacion(rst.getDate("fecha_creacion"));
					documentoInterno.setCodigo_estado_documento(rst.getInt("codigo_estado_documento"));
					documentoInterno.setCodigo_oficina(rst.getInt("codigo_oficina"));
					documentoInterno.setCodigo_oficina_pertenece(rst.getInt("codigo_oficina_pertenece"));			
					documentoInterno.setNumero_doc(rst.getInt("numero_doc"));
					documentoInterno.setAnio_doc(rst.getInt("anio_doc"));
					documentoInterno.setReferencia(rst.getString("referencia"));
					documentoInterno.setNombre_doc_adjuntos(rst.getString("nombre_doc_adjuntos"));
					documentoInterno.setCodigos_oficinas_destinos_copias(rst.getString("codigos_oficinas_destinos_copias"));								
					documentoInterno.setPersonas(rst.getInt("personas"));
					documentoInterno.setAbreviatura_grado_profesion(rst.getString("abreviatura_grado_profesion"));
					documentoInterno.setNombre_arhivo(rst.getString("nombre_archivo"));
					
					log.info("personas en Documento: " +documentoInterno.getPersonas());
					
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return documentoInterno;
}

	@Override
	public BUsuario obtenerResponsabilidad(BUsuario userSystem)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("usuario=: " +userSystem.getUsuario());
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		int codigo_oficina=0;
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		EstandarBean item;
		
		 try {	
     		
				String ls_sql = "select us.*,pe.* from tramite.usuarios us, tramite.personal pe where"
				+ " us.usuario=pe.c_usuario and pe.c_usuario=?";
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setString(1, userSystem.getUsuario());
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					userSystem.setResponsable(rst.getString("es_responsable"));
					userSystem.setFlag(rst.getString("flag"));
	    		}
				
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
	        
		return userSystem;
		
	}

	@Override
	public ArrayList<DocumentoInternoBean> getlistaDocumentosInternos(
			int bandeja, int codigo_oficina_pertenece, int tipo) throws Exception {
		PreparedStatement pstmt = null;
		ArrayList<DocumentoInternoBean> myLista = new ArrayList<DocumentoInternoBean>();
		DocumentoInternoBean item;
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		StringBuilder ls_sql_alternativo = new StringBuilder("");
		
		String valor=null;
		
		StringBuilder ls_sql = new StringBuilder("SELECT codigo_documento_interno, codigo_tipo_documento_interno, codigo_documento, asunto, descripcion,   dirigido_a, firmado_por, fecha_creacion, fecha_modificacion, codigo_estado_documento, codigo_oficina, codigo_oficina_pertenece, numero_doc, anio_doc,personas,nombre_archivo,(select count (tbldet.id_det_upload) from tramite.tbl_det_upload  tbldet where tbldet.id_codigo_documento_interno= codigo_documento_interno and tbldet.flag ='A') as contador  FROM tramite.documento_tipo_documento_interno");
		if(tipo==1){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento IN (1) AND codigo_tipo_documento_interno NOT IN (6,7) AND flag = 1 ");
		}if(tipo==2){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1");
		}if(tipo==3){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=3 AND flag = 1 ");
		}if(tipo==4){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento IN (1) AND codigo_tipo_documento_interno = 6 AND flag = 1");
		}if(tipo==5){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND codigo_tipo_documento_interno= 6  AND flag = 1");
		}if(tipo==6){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento IN (1) AND codigo_tipo_documento_interno = 7 AND flag = 1");
		}if(tipo==7){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND codigo_tipo_documento_interno= 7 AND flag = 1 ");
		}if(tipo==8){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=3  AND codigo_tipo_documento_interno= 7  AND flag = 1");
		}
		
		if(tipo==9){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}if(tipo==10){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}if(tipo==11){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}
		
		if(tipo==900){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio IN ('N','D','R')  AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}
		
		if(tipo==800){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio IN ('N','D','R')  AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}
		
		if(tipo==12){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}if(tipo==13){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}if(tipo==14){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}
		
		if(tipo==901){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio IN ('N','D','R')  AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}
		
		if(tipo==801){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio IN ('N','D','R')  AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}
		
		if(tipo==15){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}if(tipo==16){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}if(tipo==17){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}
		
		if(tipo==18){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}if(tipo==19){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}if(tipo==20){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}
		
		if(tipo==21){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}if(tipo==22){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}if(tipo==23){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}
		
		if(tipo==24){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}if(tipo==25){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}if(tipo==26){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}
		
		if(tipo==27){//LISTA DE PROYECTOS CREADOS PARA ASOCIAR O EDITAR (DOCUMENTOS NORMALES) MIS PROYECTOS
			ls_sql.append(" WHERE codigo_oficina_pertenece =?  ");
			ls_sql.append(" AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND codigo_estado_documento=1  AND flag = 0  AND codigo_documento_interno NOT IN ");
			ls_sql.append(" (SELECT tdoc.codigo_documento_interno FROM tramite.documento_tipo_documento_interno tdoc, tramite.tbl_cab_upinternos tcup ");
			ls_sql.append(" WHERE tdoc.codigo_documento_interno = tcup.codigos_documento_interno_proyecto AND tdoc.codigo_oficina_pertenece = ? AND tcup.estado IN ('A','V')) ");
		}
		if(tipo==28){//LISTA DE PROYECTOS CREADOS PARA ASOCIAR O EDITAR (DOCUMENTOS CCP) MIS PROYECTOS
			ls_sql.append(" WHERE codigo_oficina_pertenece =?  ");
			ls_sql.append(" AND codigo_tipo_documento_interno IN (6) AND codigo_estado_documento=1  AND flag = 0  AND codigo_documento_interno NOT IN ");
			ls_sql.append(" (SELECT tdoc.codigo_documento_interno FROM tramite.documento_tipo_documento_interno tdoc, tramite.tbl_cab_upinternos tcup ");
			ls_sql.append(" WHERE tdoc.codigo_documento_interno = tcup.codigos_documento_interno_proyecto AND tdoc.codigo_oficina_pertenece = ? AND tcup.estado IN ('A','V')) ");
		}
		if(tipo==29){//LISTA DE PROYECTOS CREADOS PARA ASOCIAR O EDITAR (DOCUMENTOS TDR) MIS PROYECTOS
			ls_sql.append(" WHERE codigo_oficina_pertenece =?  ");
			ls_sql.append(" AND codigo_tipo_documento_interno IN (7) AND codigo_estado_documento=1  AND flag = 0  AND codigo_documento_interno NOT IN ");
			ls_sql.append(" (SELECT tdoc.codigo_documento_interno FROM tramite.documento_tipo_documento_interno tdoc, tramite.tbl_cab_upinternos tcup ");
			ls_sql.append(" WHERE tdoc.codigo_documento_interno = tcup.codigos_documento_interno_proyecto AND tdoc.codigo_oficina_pertenece = ? AND tcup.estado IN ('A','V')) ");
		}
		if(tipo==30){ //LISTA DE PROYECTOS QUE AUN NO HAN SIDO ASOCIADOS BANDEJA DOCUMENTOS NORMALES (ADJUNTAR DOCUMENTO)
			ls_sql.append(" WHERE codigo_oficina_pertenece =?  ");
			ls_sql.append(" AND codigo_tipo_documento_interno IN (1,2,3,4,5,6,7,8) AND codigo_estado_documento=1  AND flag = 0  AND codigo_documento_interno NOT IN ");
			ls_sql.append(" (SELECT tdoc.codigo_documento_interno FROM tramite.documento_tipo_documento_interno tdoc, tramite.tbl_cab_upinternos tcup ");
			ls_sql.append(" WHERE tdoc.codigo_documento_interno = tcup.codigos_documento_interno_proyecto AND tdoc.codigo_oficina_pertenece = ? AND tcup.estado IN ('A','V')) ");
		}
		
		if(tipo==31){ //LISTA DE PROYECTOS RECIBIDOS EN LA BANDEJA INTERNA (DOCUMENTOS NORMALES) PROYECTOS RECIBIDOS
			ls_sql_alternativo.append("SELECT a.codigo_documento_interno, a.codigo_tipo_documento_interno, a.codigo_documento,a.asunto, a.descripcion, a.dirigido_a, firmado_por,"); 
			ls_sql_alternativo.append(" a.fecha_creacion, a.fecha_modificacion, a.codigo_estado_documento, a.codigo_oficina, a.codigo_oficina_pertenece, a.numero_doc, a.anio_doc,a.personas,a.nombre_archivo,");
			ls_sql_alternativo.append(" (select codigo_documento from tramite.documento_tipo_documento_interno	where codigo_documento_interno=b.codigo_documento_interno) as codigo_documento_origen, b.codigo_documento_interno as cod_doc_int_origen ");
			ls_sql_alternativo.append(" FROM tramite.documento_tipo_documento_interno a, tramite.tbl_cab_upinternos b	WHERE a.codigo_tipo_documento_interno IN (1,2,3,4,5,8) ");
			ls_sql_alternativo.append(" AND a.codigo_documento_interno IN "); 
			ls_sql_alternativo.append(" (SELECT tcu.codigos_documento_interno_proyecto	FROM tramite.documento_tipo_documento_interno td, tramite.tbl_cab_upinternos tcu, "); 
			ls_sql_alternativo.append(" tramite.tbl_det_upinternos tdu  WHERE td.codigo_documento > 0 	AND td.codigo_estado_documento = 3	AND td.codigo_documento_interno = tcu.codigo_documento_interno "); 
			ls_sql_alternativo.append(" AND tdu.estado_usuario_origen like 'C'	AND tdu.estado_usuario_fin like 'A'  AND tcu.id_cab_upinternos = tdu.id_cab_upinternos "); 
			ls_sql_alternativo.append(" AND td.codigo_oficina = ? )  AND a.codigo_documento_interno = b.codigos_documento_interno_proyecto	AND b.estado IN ('A','V')");
			
			ls_sql = ls_sql_alternativo;
			
		}
		if(tipo==32){ //LISTA DE PROYECTOS RECIBIDOS EN LA BANDEJA INTERNA (DOCUMENTOS CCP) PROYECTOS RECIBIDOS
			ls_sql_alternativo.append("SELECT a.codigo_documento_interno, a.codigo_tipo_documento_interno, a.codigo_documento,a.asunto, a.descripcion, a.dirigido_a, firmado_por,"); 
			ls_sql_alternativo.append(" a.fecha_creacion, a.fecha_modificacion, a.codigo_estado_documento, a.codigo_oficina, a.codigo_oficina_pertenece, a.numero_doc, a.anio_doc,a.personas,a.nombre_archivo,");
			ls_sql_alternativo.append(" (select codigo_documento from tramite.documento_tipo_documento_interno	where codigo_documento_interno=b.codigo_documento_interno) as codigo_documento_origen, b.codigo_documento_interno as cod_doc_int_origen ");
			ls_sql_alternativo.append(" FROM tramite.documento_tipo_documento_interno a, tramite.tbl_cab_upinternos b	WHERE a.codigo_tipo_documento_interno IN (6) ");
			ls_sql_alternativo.append(" AND a.codigo_documento_interno IN "); 
			ls_sql_alternativo.append(" (SELECT tcu.codigos_documento_interno_proyecto	FROM tramite.documento_tipo_documento_interno td, tramite.tbl_cab_upinternos tcu, "); 
			ls_sql_alternativo.append(" tramite.tbl_det_upinternos tdu  WHERE td.codigo_documento > 0 	AND td.codigo_estado_documento = 3	AND td.codigo_documento_interno = tcu.codigo_documento_interno "); 
			ls_sql_alternativo.append(" AND tdu.estado_usuario_origen like 'C'	AND tdu.estado_usuario_fin like 'A'  AND tcu.id_cab_upinternos = tdu.id_cab_upinternos "); 
			ls_sql_alternativo.append(" AND td.codigo_oficina = ? )  AND a.codigo_documento_interno = b.codigos_documento_interno_proyecto	AND b.estado IN ('A','V')");
			
			ls_sql = ls_sql_alternativo;
		}
		if(tipo==33){ //LISTA DE PROYECTOS RECIBIDOS EN LA BANDEJA INTERNA (DOCUMENTOS TDR) PROYECTOS RECIBIDOS
			ls_sql_alternativo.append("SELECT a.codigo_documento_interno, a.codigo_tipo_documento_interno, a.codigo_documento,a.asunto, a.descripcion, a.dirigido_a, firmado_por,"); 
			ls_sql_alternativo.append(" a.fecha_creacion, a.fecha_modificacion, a.codigo_estado_documento, a.codigo_oficina, a.codigo_oficina_pertenece, a.numero_doc, a.anio_doc,a.personas,a.nombre_archivo,");
			ls_sql_alternativo.append(" (select codigo_documento from tramite.documento_tipo_documento_interno	where codigo_documento_interno=b.codigo_documento_interno) as codigo_documento_origen, b.codigo_documento_interno as cod_doc_int_origen ");
			ls_sql_alternativo.append(" FROM tramite.documento_tipo_documento_interno a, tramite.tbl_cab_upinternos b	WHERE a.codigo_tipo_documento_interno IN (7) ");
			ls_sql_alternativo.append(" AND a.codigo_documento_interno IN "); 
			ls_sql_alternativo.append(" (SELECT tcu.codigos_documento_interno_proyecto	FROM tramite.documento_tipo_documento_interno td, tramite.tbl_cab_upinternos tcu, "); 
			ls_sql_alternativo.append(" tramite.tbl_det_upinternos tdu  WHERE td.codigo_documento > 0 	AND td.codigo_estado_documento = 3	AND td.codigo_documento_interno = tcu.codigo_documento_interno "); 
			ls_sql_alternativo.append(" AND tdu.estado_usuario_origen like 'C'	AND tdu.estado_usuario_fin like 'A'  AND tcu.id_cab_upinternos = tdu.id_cab_upinternos "); 
			ls_sql_alternativo.append(" AND td.codigo_oficina = ? )  AND a.codigo_documento_interno = b.codigos_documento_interno_proyecto	AND b.estado IN ('A','V')");
			
			ls_sql = ls_sql_alternativo;
		}
		
		if(tipo==34){ //LISTA DE PROYECTOS QUE AUN NO HAN SIDO ASOCIADOS BANDEJA DOCUMENTOS CCP (ADJUNTAR DOCUMENTO)
			ls_sql.append(" WHERE codigo_oficina_pertenece =?  ");
			ls_sql.append(" AND codigo_tipo_documento_interno IN (7) AND codigo_estado_documento=1  AND flag = 0  AND codigo_documento_interno NOT IN ");
			ls_sql.append(" (SELECT tdoc.codigo_documento_interno FROM tramite.documento_tipo_documento_interno tdoc, tramite.tbl_cab_upinternos tcup ");
			ls_sql.append(" WHERE tdoc.codigo_documento_interno = tcup.codigos_documento_interno_proyecto AND tdoc.codigo_oficina_pertenece = ? AND tcup.estado IN ('A','V')) ");
		}

		ls_sql.append(" ORDER BY 1 desc ");
		
		StringBuilder ls_sql_alternativo_bandeja_firma_normal = new StringBuilder("");
		if(tipo==35){
			ls_sql_alternativo_bandeja_firma_normal.append("SELECT tdi.codigo_documento_interno, tdi.codigo_tipo_documento_interno, tdi.codigo_documento, tdi.asunto, tdi.descripcion,tdi.dirigido_a, tdi.firmado_por, tdi.fecha_creacion, tdi.fecha_modificacion, tdi.codigo_estado_documento, tdi.codigo_oficina,tdi.codigo_oficina_pertenece, tdi.numero_doc, tdi.anio_doc,tdi.personas,tdi.nombre_archivo,(select count (tbldet.id_det_upload) from tramite.tbl_det_upload  tbldet where tbldet.id_codigo_documento_interno= tdi.codigo_documento_interno and tbldet.flag ='A') as contador");  
			ls_sql_alternativo_bandeja_firma_normal.append(",lif.id_log_documento_interno_firma ");
			ls_sql_alternativo_bandeja_firma_normal.append(", (select count (cup.id_cab_upinternos) from tramite.tbl_cab_upinternos cup where cup.codigo_documento_interno= tdi.codigo_documento_interno and cup.estado ='P' and cup.nombre_archivo=tdi.nombre_archivo and cup.ciclo=0) as contadorprincipal ");
			
			ls_sql_alternativo_bandeja_firma_normal.append(", (select count (cup.id_cab_upinternos) from tramite.tbl_cab_upinternos cup "); 
			ls_sql_alternativo_bandeja_firma_normal.append(" left join tramite.tbl_det_upinternos dup on cup.id_cab_upinternos=dup.id_cab_upinternos ");
			ls_sql_alternativo_bandeja_firma_normal.append(" where cup.codigo_documento_interno= tdi.codigo_documento_interno  and cup.estado ='V' "); 
			ls_sql_alternativo_bandeja_firma_normal.append(" and cup.ciclo=0 and dup.codigo_oficina > 0  and dup.estado_usuario_fin = 'A' ) as contadorvistobueno  ");
			
			ls_sql_alternativo_bandeja_firma_normal.append(" FROM tramite.documento_tipo_documento_interno tdi  left join tramite.tbl_log_documento_interno_firma lif  on tdi.codigo_documento_interno=lif.codigo_documento_interno ");
			ls_sql_alternativo_bandeja_firma_normal.append(" WHERE tdi.codigo_oficina_pertenece =?  AND tdi.codigo_estado_documento=5  AND tdi.tipo_envio IN ('N','D','R') ");  
			ls_sql_alternativo_bandeja_firma_normal.append(" AND tdi.codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1  ORDER BY 1 desc ");
		}
		
		if(tipo==36){
			ls_sql_alternativo_bandeja_firma_normal.append("select tdi.codigo_documento_interno, tdi.codigo_tipo_documento_interno, tdi.codigo_documento, tdi.asunto, tdi.descripcion,tdi.dirigido_a, tdi.firmado_por, tdi.fecha_creacion, tdi.fecha_modificacion, tdi.codigo_estado_documento, tdi.codigo_oficina,tdi.codigo_oficina_pertenece, tdi.numero_doc, tdi.anio_doc,tdi.personas,tdi.nombre_archivo ");
			ls_sql_alternativo_bandeja_firma_normal.append(" from tramite.tbl_det_upinternos dup ");
			ls_sql_alternativo_bandeja_firma_normal.append(" left join tramite.tbl_cab_upinternos cup on dup.id_cab_upinternos=cup.id_cab_upinternos ");
			
			ls_sql_alternativo_bandeja_firma_normal.append(" left join tramite.documento_tipo_documento_interno tdi on tdi.codigo_documento_interno = cup.codigo_documento_interno ");
			ls_sql_alternativo_bandeja_firma_normal.append(" where dup.codigo_oficina= ?  and dup.estado_usuario_origen='C' and dup.estado_usuario_fin='A'  AND tdi.codigo_estado_documento=5 ");  
			ls_sql_alternativo_bandeja_firma_normal.append(" and cup.estado='P'  ORDER BY 1 desc ");
		}
		
		if(tipo==37){
			ls_sql_alternativo_bandeja_firma_normal.append("select distinct tdi.codigo_documento_interno, tdi.codigo_tipo_documento_interno, tdi.codigo_documento, tdi.asunto, tdi.descripcion,tdi.dirigido_a, tdi.firmado_por, tdi.fecha_creacion, tdi.fecha_modificacion, tdi.codigo_estado_documento, tdi.codigo_oficina,tdi.codigo_oficina_pertenece, tdi.numero_doc, tdi.anio_doc,tdi.personas,tdi.nombre_archivo ");
			ls_sql_alternativo_bandeja_firma_normal.append(" from tramite.tbl_det_upinternos dup ");
			ls_sql_alternativo_bandeja_firma_normal.append(" left join tramite.tbl_cab_upinternos cup on dup.id_cab_upinternos=cup.id_cab_upinternos ");
			
			ls_sql_alternativo_bandeja_firma_normal.append(" left join tramite.documento_tipo_documento_interno tdi on tdi.codigo_documento_interno = cup.codigo_documento_interno ");
			ls_sql_alternativo_bandeja_firma_normal.append(" where dup.codigo_oficina= ?  and dup.estado_usuario_origen='C' and dup.estado_usuario_fin='A'  AND tdi.codigo_estado_documento=5 ");  
			ls_sql_alternativo_bandeja_firma_normal.append(" and cup.estado in ('V','A')  ORDER BY 1 desc ");
		}
		

		 try {	
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				
				if(tipo==35 || tipo==36 || tipo==37){
					pstmt = conn.prepareStatement(ls_sql_alternativo_bandeja_firma_normal.toString());
				}
				
				pstmt.setInt(1,codigo_oficina_pertenece);
				if((tipo>=27 && tipo<=30) || tipo==34){pstmt.setInt(2,codigo_oficina_pertenece);}
				
				rst = pstmt.executeQuery();	
				log.info("sql :--->"+pstmt);
				while(rst.next()){
					item = new DocumentoInternoBean();
					item.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
					item.setCodigo_tipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
					item.setCodigo_documento((rst.getInt("codigo_documento")==0)? null:rst.getInt("codigo_documento"));
					item.setAsunto(rst.getString("asunto"));
					item.setDirigido_a(rst.getString("dirigido_a"));
					item.setCodigo_estado_documento(rst.getInt("codigo_estado_documento"));
					item.setFecha_creacion(rst.getTimestamp("fecha_creacion"));
					item.setCodigo_oficina(rst.getInt("codigo_oficina"));
					item.setCodigo_oficina_pertenece(rst.getInt("codigo_oficina_pertenece"));
					item.setFirmado_por(rst.getString("firmado_por"));
					item.setAnio_doc(rst.getInt("anio_doc"));
					//-------------------------
					String link2="";
					if(tipo==31){
						item.setCodigo_documento(rst.getInt("codigo_documento_origen"));
						
						//link2="ModificarDocumentoInterno.do?cod_doc_interno="+rst.getInt("cod_doc_int_origen")+"&cod_proy="+item.getCodigo_documento_interno()+"&numreg="+item.getCodigo_documento()+"&tipo_pi=2";
						link2="javascript:window.parent.modificarIframeMisProyectos("+rst.getInt("cod_doc_int_origen")+","+item.getCodigo_documento_interno()+","+item.getCodigo_documento()+");";
						item.setLink2("<a  href='"+link2+"' title='Modificar con el mismo Num. Registro'>"+item.getCodigo_documento()+"</a>");
												
					}
					
					
					
					String link="javascript:window.parent.modificarIframe("+item.getCodigo_documento_interno()+");";
					link="<a  href='"+link+"' >"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";
					
					if(tipo==4 || tipo==5 || (tipo>=15 && tipo<=20) || tipo==28){
						
						//link="ModificarDocumentoInternoEspecialC.do?cod_doc_interno="+item.getCodigo_documento_interno();
						link="javascript:window.parent.modificarIframe("+item.getCodigo_documento_interno()+");";
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";
					}
					
					if(tipo==6 || tipo==7 || (tipo>=21 && tipo<=26) || tipo==29){
						link="ModificarDocumentoInternoEspecialT.do?cod_doc_interno="+item.getCodigo_documento_interno();
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";
					}
					
					if(tipo==31){ //cod_doc_int_origen
						link="javascript:window.parent.modificarIframeProyectos("+item.getCodigo_documento_interno()+");";
						link="<a  href='"+link+"' >"+"<img src='img/edit_doc.png' title='Modificar con nuevo Num. Registro' width='20' height='20' border='0'> </a>";
						
						/*link="ModificarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno()+"&tipo_pi=1";
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";*/
					}
					if(tipo==32){
						link="javascript:window.parent.modificarIframeProyectos("+item.getCodigo_documento_interno()+");";
						link="<a  href='"+link+"' >"+"<img src='img/edit_doc.png' title='Modificar con nuevo Num. Registro' width='20' height='20' border='0'> </a>";
						
						/*link="ModificarDocumentoInternoEspecialC.do?cod_doc_interno="+item.getCodigo_documento_interno()+"&tipo_pi=1";
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";*/
					}
					if(tipo==33){
						link="ModificarDocumentoInternoEspecialT.do?cod_doc_interno="+item.getCodigo_documento_interno()+"&tipo_pi=1";
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";
					}
										
					String verPDF="VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					verPDF="<a  href='"+verPDF+"'>"+"<img src='img/pdf.gif' title='Ver documento PDF' width='20' height='20' border='0'/> </a>";
				
					//String enviarAdjunto="MoverArchivoAAdjunto.do?cod_doc_interno="+item.getCodigo_documento_interno();					
					//enviarAdjunto="<a  href='javascript:AdjuntarDocumento("+item.getCodigo_documento_interno()+")';>"+"<img src='img/docadjuntos.gif' alt='Adjuntar Documento' width='24' height='24' border='0' /> </a>";
					
					/*String firmarDocumento="FirmarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					firmarDocumento="<a  href='"+firmarDocumento+"'>"+"<img src='img/ver.gif' alt='firmar' width='24' height='24' border='0'> </a>";*/
					valor = (item.getCodigo_documento()==null)? "0":"1";
					
					String firmarDocumento="javascript:LlamarApplet("+item.getCodigo_documento_interno()+","+item.getCodigo_tipo_documento_interno()+","+valor+",1);";
					firmarDocumento="<a href='"+firmarDocumento+"'>"+"<img src='img/03firmar.png' title='Firmar' width='36' height='36' border='0'> </a>";
					
					/***
					 * CODIGO NUEVO
					 */
					String uploadDocumento="javascript:AgregarDocEscaneados("+item.getCodigo_documento_interno()+");";
					uploadDocumento="<a href='"+uploadDocumento+"'>"+"<img src='img/upload.png' title='Subir Archivo Firmado' width='20' height='20' border='0'> </a>";
					
					
					/**
					 * FIN
					 */
					
					String enviarAADocumento="ActionSeleccion.do?rb_seleccion=I&cod_doc_interno="+item.getCodigo_documento_interno()+"&oper=re";					
					//enviarAADocumento="<a  href='javascript:fn_editar_copia("+item.getCodigo_documento_interno()+")';><img src='img/nuevo.gif' alt='registrar' width='24' height='24' border='0'></a>";
					enviarAADocumento="<a  href='"+enviarAADocumento+"'>"+"<img src='img/nuevo.gif' alt='registrar' width='20' height='20' border='0'> </a>";
					
					String enviarAAdjunto="<a  href='javascript:AgregarArchivoInterno("+item.getCodigo_documento_interno()+")';>"+"<img src='img/docadjuntos.gif' alt='Adjuntar Documento' width='20' height='20' border='0' /> </a>";
					
					enviarAAdjunto="MantDocumento.do?operacion=RI&codigo="+item.getCodigo_documento_interno();
					enviarAAdjunto="<a  href='"+enviarAAdjunto+"'>"+"<img src='img/adjuntar.png' alt='adjuntar' width='20' height='20' border='0'> </a>";
					
					/*String eliminarDocumento="MantenimientoDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					eliminarDocumento="<a  href='"+eliminarDocumento+"'>"+"<img src='img/delete.png' title='Eliminar' width='24' height='24' border='0'> </a>";
					*/
					String eliminarDocumento="<a  href='javascript:deletedocument("+item.getCodigo_documento_interno()+")'>"+"<img src='img/delete.png' title='Eliminar' width='20' height='20' border='0'> </a>";
					String cerrarProyecto="<a  href='javascript:cerrardocument("+item.getCodigo_documento_interno()+")'>"+"<img src='img/delete.png' title='Eliminar' width='20' height='20' border='0'> </a>";
					
					String verPDFsfirmado ="VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno()+"&interno=SI";
					verPDFsfirmado="<a href='"+verPDFsfirmado+"'>"+"<img src='img/pdf.gif' title='Ver Archivo Firmado' width='20' height='20' border='0'> </a>";
					
					String eliminarPDFfirmado="javascript:deletedocumentinterno("+item.getCodigo_documento_interno()+");";
					eliminarPDFfirmado="<a href='"+eliminarPDFfirmado+"'>"+"<img src='img/delete.png' title='Eliminar Archivo Firmado' width='20' height='20' border='0'> </a>";
					
					String enviarPDFfirmado="javascript:LlamarApplet("+item.getCodigo_documento_interno()+","+item.getCodigo_tipo_documento_interno()+","+valor+",0);";
					enviarPDFfirmado="<a href='"+enviarPDFfirmado+"'>"+"<img src='img/send_doc.png' title='Enviar Registro' width='20' height='20' border='0'> </a>";

					
					item.setNombre_arhivo("<a  href='VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno()+"'  title='Ver Documento PDF' >"+rst.getString("nombre_archivo")+"</a>");
					item.setNota(rst.getString("nombre_archivo"));
					item.setLink(link+" "+verPDF+" "+eliminarDocumento);
								
					//item.setLink(link+" "+verPDF+" "+firmarDocumento);
					
					
					if (bandeja==7){
						//proyecto recibido
						//item.setLink(link+" "+verPDF+" "+cerrarProyecto);
						item.setLink(link+" "+verPDF);
					}
					
					
					
					link="ModificarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					link="<a  href='"+link+"'>"+"<img src='img/edit_doc.png' title='Modificar' width='20' height='20' border='0'> </a>";
												
					if (bandeja==1){
						item.setLink(verPDF+" "+enviarAAdjunto);
					}
					if (bandeja==2){
						enviarAAdjunto="AdjuntarDocumentoInterno.do?operacion=RI&codigo="+item.getCodigo_documento_interno();
						enviarAAdjunto="<a  href='"+enviarAAdjunto+"'>"+"<img src='img/adjuntar.png' title='Adjuntar Proyecto' width='20' height='20' border='0'> </a>";
						item.setLink(verPDF+" "+enviarAAdjunto);
					}
					if (bandeja==3){
						enviarAAdjunto="AdjuntarDocumentoInternoEspecialC.do?operacion=RI&codigo="+item.getCodigo_documento_interno();
						enviarAAdjunto="<a  href='"+enviarAAdjunto+"'>"+"<img src='img/adjuntar.png' title='Adjuntar Proyecto' width='20' height='20' border='0'> </a>";
						item.setLink(verPDF+" "+enviarAAdjunto);
					}
					if (bandeja==4){
						enviarAAdjunto="AdjuntarDocumentoInternoEspecialT.do?operacion=RI&codigo="+item.getCodigo_documento_interno();
						enviarAAdjunto="<a  href='"+enviarAAdjunto+"'>"+"<img src='img/adjuntar.png' title='Adjuntar Proyecto' width='20' height='20' border='0'> </a>";
						item.setLink(verPDF+" "+enviarAAdjunto);
					}
					if (bandeja==5){
						
						verPDF="mostrarlistasdebandejadocumentosinternos.do?cambia=upload&codigo_documento_interno="+item.getCodigo_documento_interno();
						verPDF="<a  class='showPopup' href='"+verPDF+"' title='Lista de Documentos - "+rst.getString("nombre_archivo")+"'>"+"<img src='img/copy_f2.png' title='Ver Documentos Adjuntos' width='20' height='20' border='0'> </a>";
						item.setLink(link+" "+verPDF+" "+eliminarDocumento+" "+firmarDocumento);
					}
					/**BANDEJA FIRMA MASIVA***/
					if (bandeja==9){
						String firmaMasiva = "<input  type='checkbox' name='selectedItems' value='"+item.getCodigo_documento_interno()+"' onclick=ModificarEstado('"+item.getCodigo_documento_interno()+"') >";
						firmarDocumento = "<img src='img/03firmargray.png' width='36' height='36' border='0'>";	
						verPDF="mostrarlistasdebandejadocumentosinternos.do?cambia=upload&codigo_documento_interno="+item.getCodigo_documento_interno();
						verPDF="<a  class='showPopup' href='"+verPDF+"' title='Lista de Documentos - "+rst.getString("nombre_archivo")+"'>"+"<img src='img/copy_f2.png' title='Ver Documentos Adjuntos' width='20' height='20' border='0'> </a>";
						item.setLink(link+" "+verPDF+" "+eliminarDocumento+" "+firmarDocumento);
						
						item.setLink3(firmaMasiva);
					}
					
					if (bandeja==6){
						item.setLink(verPDF);
					}
					
					
					/**
					 * NUEVO SOLO PARA TRAMITE SIN FIRMA DIGITAL
					 */
					if (bandeja==8){//INDICA QUE NO TIENE FIRMA 
						if(rst.getInt("contador")==0){ //SUBIR FILE FIRMADO
							item.setLink(link+" "+verPDF+" "+eliminarDocumento+" "+uploadDocumento);
						}else{//CUANDO YA SUBIO EL FILE
							item.setLink(verPDFsfirmado+" "+eliminarPDFfirmado+" "+enviarPDFfirmado);
						}
					}
										
					/**
					 * FIN
					 */
					if (bandeja==10){ //bandeja proyectos
						if(rst.getInt("contadorprincipal")==1  || rst.getInt("contadorvistobueno")>0){
						
							link="mostrarlistasdebandejadocumentosinternos.do?cambia=uploadprincipal&codigo_documento_interno="+item.getCodigo_documento_interno()+"&estado=ver";
							link="<a  class='showPopup' href='"+link+"' title='Lista de Documentos - "+rst.getString("nombre_archivo")+"'>"+"<img src='img/im-user-black.png' title='Ver Estado de documento principal' width='20' height='20' border='0'> </a>";
							
							verPDF="mostrarlistasdebandejadocumentosinternos.do?cambia=upload&codigo_documento_interno="+item.getCodigo_documento_interno()+"&estado=ver";
							verPDF="<a  class='showPopup' href='"+verPDF+"' title='Lista de Documentos - "+rst.getString("nombre_archivo")+"'>"+"<img src='img/copy_f2.png' title='Ver Estado de documentos adjuntos' width='20' height='20' border='0'> </a>";
							
							cerrarProyecto="<a  href='javascript:retornaabandeja("+item.getCodigo_documento_interno()+")'>"+"<img src='img/07observar.png' title='Regresar a Bandeja de Documentos' width='20' height='20' border='0'> </a>";
							
							item.setLink(link+" "+verPDF+" "+cerrarProyecto);
							item.setNombre_arhivo("<a  href='VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno()+"'  title='Ver Documento PDF' >"+rst.getString("nombre_archivo").substring(9, rst.getString("nombre_archivo").length())+"</a>");
						
							myLista.add(item);
						}
					}else{
						
						if (bandeja==11){
							firmarDocumento="javascript:firmaSolicitada("+item.getCodigo_documento_interno()+","+item.getCodigo_tipo_documento_interno()+",1);";
							firmarDocumento="<a href='"+firmarDocumento+"'>"+"<img src='img/03firmar.png' title='Firmar' width='20' height='20' border='0'> </a>";
							
							verPDF="mostrarlistasdebandejadocumentosinternos.do?cambia=upload&codigo_documento_interno="+item.getCodigo_documento_interno()+"&estado=verfirmarequerida";
							verPDF="<a  class='showPopup' href='"+verPDF+"' title='Lista de Documentos - "+rst.getString("nombre_archivo")+"'>"+"<img src='img/copy_f2.png' title='Ver Estado de documentos adjuntos' width='20' height='20' border='0'> </a>";
							
							cerrarProyecto="<a  href='javascript:retornaabandeja("+item.getCodigo_documento_interno()+")'>"+"<img src='img/07observar.png' title='Observar documento' width='20' height='20' border='0'> </a>";
							
							item.setLink(firmarDocumento+" "+verPDF+" "+cerrarProyecto);
							item.setNombre_arhivo("<a  href='VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno()+"'  title='Ver Documento PDF' >"+rst.getString("nombre_archivo").substring(9, rst.getString("nombre_archivo").length())+"</a>");
						}
						if (bandeja==12){
							firmarDocumento="javascript:firmaSolicitada("+item.getCodigo_documento_interno()+","+item.getCodigo_tipo_documento_interno()+",1);";
							firmarDocumento="<a href='"+firmarDocumento+"'>"+"<img src='img/03firmar.png' title='Firmar' width='20' height='20' border='0'> </a>";
							
							verPDF="mostrarlistasdebandejadocumentosinternos.do?cambia=upload&codigo_documento_interno="+item.getCodigo_documento_interno()+"&estado=vervbrequerida";
							verPDF="<a  class='showPopup' href='"+verPDF+"' title='Lista de Documentos - "+rst.getString("nombre_archivo")+"'>"+"<img src='img/copy_f2.png' title='Ver Estado de documentos adjuntos' width='20' height='20' border='0'> </a>";
							
							cerrarProyecto="<a  href='javascript:retornaabandeja("+item.getCodigo_documento_interno()+")'>"+"<img src='img/07observar.png' title='Observar documento' width='20' height='20' border='0'> </a>";
							
							item.setLink(verPDF+" "+cerrarProyecto);
							item.setNombre_arhivo("<a  href='VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno()+"'  title='Ver Documento PDF' >"+rst.getString("nombre_archivo").substring(9, rst.getString("nombre_archivo").length())+"</a>");
						}
						
						myLista.add(item);
					}
					
					
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return myLista;
	}
	
	@Override
	public ArrayList<DocumentoInternoBean> getlistaDocumentosInternos(
			int bandeja, int codigo_oficina_pertenece, int tipo, int firmaprevia) throws Exception {
		PreparedStatement pstmt = null;
		ArrayList<DocumentoInternoBean> myLista = new ArrayList<DocumentoInternoBean>();
		DocumentoInternoBean item;
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		StringBuilder ls_sql_alternativo = new StringBuilder("");
		StringBuilder ls_sql_alternativo_bandeja_firma_normal = new StringBuilder("");
		
		String valor=null;
		String valorDeIdLogDocumentoInterno="";
		
		//System.out.println("Entre a firma masiva 05122013");
		
		StringBuilder ls_sql = new StringBuilder("SELECT codigo_documento_interno, codigo_tipo_documento_interno, codigo_documento, asunto, descripcion,   dirigido_a, firmado_por, fecha_creacion, fecha_modificacion, codigo_estado_documento, codigo_oficina, codigo_oficina_pertenece, numero_doc, anio_doc,personas,nombre_archivo,(select count (tbldet.id_det_upload) from tramite.tbl_det_upload  tbldet where tbldet.id_codigo_documento_interno= codigo_documento_interno and tbldet.flag ='A') as contador  ");
		ls_sql.append(" FROM tramite.documento_tipo_documento_interno ");
		
		if(tipo==1){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento IN (1) AND codigo_tipo_documento_interno NOT IN (6,7) AND flag = 1 ");
		}if(tipo==2){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1");
		}if(tipo==3){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=3 AND flag = 1 ");
		}if(tipo==4){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento IN (1) AND codigo_tipo_documento_interno = 6 AND flag = 1");
		}if(tipo==5){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND codigo_tipo_documento_interno= 6  AND flag = 1");
		}if(tipo==6){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento IN (1) AND codigo_tipo_documento_interno = 7 AND flag = 1");
		}if(tipo==7){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND codigo_tipo_documento_interno= 7 AND flag = 1 ");
		}if(tipo==8){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=3  AND codigo_tipo_documento_interno= 7  AND flag = 1");
		}
		
		if(tipo==9){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}if(tipo==10){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}if(tipo==11){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}
		
		if(tipo==900){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio IN ('N','D','R')  AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}
		
		if(tipo==800){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio IN ('N','D','R')  AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}
		
		if(tipo==12){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}if(tipo==13){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}if(tipo==14){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}
		
		if(tipo==901){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio IN ('N','D','R')  AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1 ");
		}
		
		if(tipo==801){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio IN ('N','D','R')  AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}
		
		if(tipo==15){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}if(tipo==16){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}if(tipo==17){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}
		
		if(tipo==18){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}if(tipo==19){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}if(tipo==20){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (6) AND flag = 1 ");
		}
		
		if(tipo==21){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}if(tipo==22){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}if(tipo==23){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=1  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}
		
		if(tipo==24){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'N' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}if(tipo==25){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'D' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}if(tipo==26){
			ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=5  AND tipo_envio like 'R' AND codigo_tipo_documento_interno IN (7) AND flag = 1 ");
		}
		
		if(tipo==27){//LISTA DE PROYECTOS CREADOS PARA ASOCIAR O EDITAR (DOCUMENTOS NORMALES) MIS PROYECTOS
			ls_sql.append(" WHERE codigo_oficina_pertenece =?  ");
			ls_sql.append(" AND codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND codigo_estado_documento=1  AND flag = 0  AND codigo_documento_interno NOT IN ");
			ls_sql.append(" (SELECT tdoc.codigo_documento_interno FROM tramite.documento_tipo_documento_interno tdoc, tramite.tbl_cab_upinternos tcup ");
			ls_sql.append(" WHERE tdoc.codigo_documento_interno = tcup.codigos_documento_interno_proyecto AND tdoc.codigo_oficina_pertenece = ? AND tcup.estado IN ('A','V')) ");
		}
		if(tipo==28){//LISTA DE PROYECTOS CREADOS PARA ASOCIAR O EDITAR (DOCUMENTOS CCP) MIS PROYECTOS
			ls_sql.append(" WHERE codigo_oficina_pertenece =?  ");
			ls_sql.append(" AND codigo_tipo_documento_interno IN (6) AND codigo_estado_documento=1  AND flag = 0  AND codigo_documento_interno NOT IN ");
			ls_sql.append(" (SELECT tdoc.codigo_documento_interno FROM tramite.documento_tipo_documento_interno tdoc, tramite.tbl_cab_upinternos tcup ");
			ls_sql.append(" WHERE tdoc.codigo_documento_interno = tcup.codigos_documento_interno_proyecto AND tdoc.codigo_oficina_pertenece = ? AND tcup.estado IN ('A','V')) ");
		}
		if(tipo==29){//LISTA DE PROYECTOS CREADOS PARA ASOCIAR O EDITAR (DOCUMENTOS TDR) MIS PROYECTOS
			ls_sql.append(" WHERE codigo_oficina_pertenece =?  ");
			ls_sql.append(" AND codigo_tipo_documento_interno IN (7) AND codigo_estado_documento=1  AND flag = 0  AND codigo_documento_interno NOT IN ");
			ls_sql.append(" (SELECT tdoc.codigo_documento_interno FROM tramite.documento_tipo_documento_interno tdoc, tramite.tbl_cab_upinternos tcup ");
			ls_sql.append(" WHERE tdoc.codigo_documento_interno = tcup.codigos_documento_interno_proyecto AND tdoc.codigo_oficina_pertenece = ? AND tcup.estado IN ('A','V')) ");
		}
		if(tipo==30){ //LISTA DE PROYECTOS QUE AUN NO HAN SIDO ASOCIADOS BANDEJA DOCUMENTOS NORMALES (ADJUNTAR DOCUMENTO)
			ls_sql.append(" WHERE codigo_oficina_pertenece =?  ");
			ls_sql.append(" AND codigo_tipo_documento_interno IN (1,2,3,4,5,6,7,8) AND codigo_estado_documento=1  AND flag = 0  AND codigo_documento_interno NOT IN ");
			ls_sql.append(" (SELECT tdoc.codigo_documento_interno FROM tramite.documento_tipo_documento_interno tdoc, tramite.tbl_cab_upinternos tcup ");
			ls_sql.append(" WHERE tdoc.codigo_documento_interno = tcup.codigos_documento_interno_proyecto AND tdoc.codigo_oficina_pertenece = ? AND tcup.estado IN ('A','V')) ");
		}
		
		if(tipo==31){ //LISTA DE PROYECTOS RECIBIDOS EN LA BANDEJA INTERNA (DOCUMENTOS NORMALES) PROYECTOS RECIBIDOS
			ls_sql_alternativo.append("SELECT a.codigo_documento_interno, a.codigo_tipo_documento_interno, a.codigo_documento,a.asunto, a.descripcion, a.dirigido_a, firmado_por,"); 
			ls_sql_alternativo.append(" a.fecha_creacion, a.fecha_modificacion, a.codigo_estado_documento, a.codigo_oficina, a.codigo_oficina_pertenece, a.numero_doc, a.anio_doc,a.personas,a.nombre_archivo,");
			ls_sql_alternativo.append(" (select codigo_documento from tramite.documento_tipo_documento_interno	where codigo_documento_interno=b.codigo_documento_interno) as codigo_documento_origen, b.codigo_documento_interno as cod_doc_int_origen ");
			ls_sql_alternativo.append(" FROM tramite.documento_tipo_documento_interno a, tramite.tbl_cab_upinternos b	WHERE a.codigo_tipo_documento_interno IN (1,2,3,4,5,8) ");
			ls_sql_alternativo.append(" AND a.codigo_documento_interno IN "); 
			ls_sql_alternativo.append(" (SELECT tcu.codigos_documento_interno_proyecto	FROM tramite.documento_tipo_documento_interno td, tramite.tbl_cab_upinternos tcu, "); 
			ls_sql_alternativo.append(" tramite.tbl_det_upinternos tdu  WHERE td.codigo_documento > 0 	AND td.codigo_estado_documento = 3	AND td.codigo_documento_interno = tcu.codigo_documento_interno "); 
			ls_sql_alternativo.append(" AND tdu.estado_usuario_origen like 'C'	AND tdu.estado_usuario_fin like 'A'  AND tcu.id_cab_upinternos = tdu.id_cab_upinternos "); 
			ls_sql_alternativo.append(" AND td.codigo_oficina = ? )  AND a.codigo_documento_interno = b.codigos_documento_interno_proyecto	AND b.estado IN ('A','V')");
			
			ls_sql = ls_sql_alternativo;
			
		}
		if(tipo==32){ //LISTA DE PROYECTOS RECIBIDOS EN LA BANDEJA INTERNA (DOCUMENTOS CCP) PROYECTOS RECIBIDOS
			ls_sql_alternativo.append("SELECT a.codigo_documento_interno, a.codigo_tipo_documento_interno, a.codigo_documento,a.asunto, a.descripcion, a.dirigido_a, firmado_por,"); 
			ls_sql_alternativo.append(" a.fecha_creacion, a.fecha_modificacion, a.codigo_estado_documento, a.codigo_oficina, a.codigo_oficina_pertenece, a.numero_doc, a.anio_doc,a.personas,a.nombre_archivo,");
			ls_sql_alternativo.append(" (select codigo_documento from tramite.documento_tipo_documento_interno	where codigo_documento_interno=b.codigo_documento_interno) as codigo_documento_origen, b.codigo_documento_interno as cod_doc_int_origen ");
			ls_sql_alternativo.append(" FROM tramite.documento_tipo_documento_interno a, tramite.tbl_cab_upinternos b	WHERE a.codigo_tipo_documento_interno IN (6) ");
			ls_sql_alternativo.append(" AND a.codigo_documento_interno IN "); 
			ls_sql_alternativo.append(" (SELECT tcu.codigos_documento_interno_proyecto	FROM tramite.documento_tipo_documento_interno td, tramite.tbl_cab_upinternos tcu, "); 
			ls_sql_alternativo.append(" tramite.tbl_det_upinternos tdu  WHERE td.codigo_documento > 0 	AND td.codigo_estado_documento = 3	AND td.codigo_documento_interno = tcu.codigo_documento_interno "); 
			ls_sql_alternativo.append(" AND tdu.estado_usuario_origen like 'C'	AND tdu.estado_usuario_fin like 'A'  AND tcu.id_cab_upinternos = tdu.id_cab_upinternos "); 
			ls_sql_alternativo.append(" AND td.codigo_oficina = ? )  AND a.codigo_documento_interno = b.codigos_documento_interno_proyecto	AND b.estado IN ('A','V')");
			
			ls_sql = ls_sql_alternativo;
		}
		if(tipo==33){ //LISTA DE PROYECTOS RECIBIDOS EN LA BANDEJA INTERNA (DOCUMENTOS TDR) PROYECTOS RECIBIDOS
			ls_sql_alternativo.append("SELECT a.codigo_documento_interno, a.codigo_tipo_documento_interno, a.codigo_documento,a.asunto, a.descripcion, a.dirigido_a, firmado_por,"); 
			ls_sql_alternativo.append(" a.fecha_creacion, a.fecha_modificacion, a.codigo_estado_documento, a.codigo_oficina, a.codigo_oficina_pertenece, a.numero_doc, a.anio_doc,a.personas,a.nombre_archivo,");
			ls_sql_alternativo.append(" (select codigo_documento from tramite.documento_tipo_documento_interno	where codigo_documento_interno=b.codigo_documento_interno) as codigo_documento_origen, b.codigo_documento_interno as cod_doc_int_origen ");
			ls_sql_alternativo.append(" FROM tramite.documento_tipo_documento_interno a, tramite.tbl_cab_upinternos b	WHERE a.codigo_tipo_documento_interno IN (7) ");
			ls_sql_alternativo.append(" AND a.codigo_documento_interno IN "); 
			ls_sql_alternativo.append(" (SELECT tcu.codigos_documento_interno_proyecto	FROM tramite.documento_tipo_documento_interno td, tramite.tbl_cab_upinternos tcu, "); 
			ls_sql_alternativo.append(" tramite.tbl_det_upinternos tdu  WHERE td.codigo_documento > 0 	AND td.codigo_estado_documento = 3	AND td.codigo_documento_interno = tcu.codigo_documento_interno "); 
			ls_sql_alternativo.append(" AND tdu.estado_usuario_origen like 'C'	AND tdu.estado_usuario_fin like 'A'  AND tcu.id_cab_upinternos = tdu.id_cab_upinternos "); 
			ls_sql_alternativo.append(" AND td.codigo_oficina = ? )  AND a.codigo_documento_interno = b.codigos_documento_interno_proyecto	AND b.estado IN ('A','V')");
			
			ls_sql = ls_sql_alternativo;
		}
		
		if(tipo==34){ //LISTA DE PROYECTOS QUE AUN NO HAN SIDO ASOCIADOS BANDEJA DOCUMENTOS CCP (ADJUNTAR DOCUMENTO)
			ls_sql.append(" WHERE codigo_oficina_pertenece =?  ");
			ls_sql.append(" AND codigo_tipo_documento_interno IN (7) AND codigo_estado_documento=1  AND flag = 0  AND codigo_documento_interno NOT IN ");
			ls_sql.append(" (SELECT tdoc.codigo_documento_interno FROM tramite.documento_tipo_documento_interno tdoc, tramite.tbl_cab_upinternos tcup ");
			ls_sql.append(" WHERE tdoc.codigo_documento_interno = tcup.codigos_documento_interno_proyecto AND tdoc.codigo_oficina_pertenece = ? AND tcup.estado IN ('A','V')) ");
		}
		
		ls_sql.append(" ORDER BY 1 desc ");

		if(tipo==900){
			
			ls_sql_alternativo_bandeja_firma_normal.append("SELECT tdi.codigo_documento_interno, tdi.codigo_tipo_documento_interno, tdi.codigo_documento, tdi.asunto, tdi.descripcion,tdi.dirigido_a, tdi.firmado_por, tdi.fecha_creacion, tdi.fecha_modificacion, tdi.codigo_estado_documento, tdi.codigo_oficina,tdi.codigo_oficina_pertenece, tdi.numero_doc, tdi.anio_doc,tdi.personas,tdi.nombre_archivo,tdi.nombre_doc_adjuntos,(select count (tbldet.id_det_upload) from tramite.tbl_det_upload  tbldet where tbldet.id_codigo_documento_interno= tdi.codigo_documento_interno and tbldet.flag ='A') as contador");  
			ls_sql_alternativo_bandeja_firma_normal.append(",lif.id_log_documento_interno_firma ");
			ls_sql_alternativo_bandeja_firma_normal.append(", (select count (cup.id_cab_upinternos) from tramite.tbl_cab_upinternos cup where cup.codigo_documento_interno= tdi.codigo_documento_interno and cup.estado ='P' and cup.nombre_archivo=tdi.nombre_archivo and cup.ciclo=0) as contadorprincipal ");
			
			ls_sql_alternativo_bandeja_firma_normal.append(" FROM tramite.documento_tipo_documento_interno tdi  left join tramite.tbl_log_documento_interno_firma lif  on tdi.codigo_documento_interno=lif.codigo_documento_interno ");
			ls_sql_alternativo_bandeja_firma_normal.append(" WHERE tdi.codigo_oficina_pertenece =?  AND tdi.codigo_estado_documento=5  AND tdi.tipo_envio IN ('N','D','R') ");  
			ls_sql_alternativo_bandeja_firma_normal.append(" AND tdi.codigo_tipo_documento_interno IN (1,2,3,4,5,8) AND flag = 1  ORDER BY 1 desc ");
			
			
		}
		

		 try {	
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				if(tipo==900){
					pstmt = conn.prepareStatement(ls_sql_alternativo_bandeja_firma_normal.toString());
				}
				
				pstmt.setInt(1,codigo_oficina_pertenece);
				if((tipo>=27 && tipo<=30) || tipo==34){pstmt.setInt(2,codigo_oficina_pertenece);}
				
				rst = pstmt.executeQuery();	
				log.info("sql :"+pstmt);
				
				while(rst.next()){
					item = new DocumentoInternoBean();
					item.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
					item.setCodigo_tipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
					item.setCodigo_documento((rst.getInt("codigo_documento")==0)? null:rst.getInt("codigo_documento"));
					item.setAsunto(rst.getString("asunto"));
					item.setDirigido_a(rst.getString("dirigido_a"));
					item.setCodigo_estado_documento(rst.getInt("codigo_estado_documento"));
					item.setFecha_creacion(rst.getTimestamp("fecha_creacion"));
					item.setCodigo_oficina(rst.getInt("codigo_oficina"));
					item.setCodigo_oficina_pertenece(rst.getInt("codigo_oficina_pertenece"));
					item.setFirmado_por(rst.getString("firmado_por"));
					item.setAnio_doc(rst.getInt("anio_doc"));
					
					
					//-------------------------
					String link2="";
					if(tipo==31){
						item.setCodigo_documento(rst.getInt("codigo_documento_origen"));
						
						//link2="ModificarDocumentoInterno.do?cod_doc_interno="+rst.getInt("cod_doc_int_origen")+"&cod_proy="+item.getCodigo_documento_interno()+"&numreg="+item.getCodigo_documento()+"&tipo_pi=2";
						link2="javascript:window.parent.modificarIframeMisProyectos("+rst.getInt("cod_doc_int_origen")+","+item.getCodigo_documento_interno()+","+item.getCodigo_documento()+");";
						item.setLink2("<a  href='"+link2+"' title='Modificar con el mismo Num. Registro'>"+item.getCodigo_documento()+"</a>");
												
					}
					
					
					
					String link="javascript:window.parent.modificarIframe("+item.getCodigo_documento_interno()+");";
					link="<a  href='"+link+"' >"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";
					
					if(tipo==4 || tipo==5 || (tipo>=15 && tipo<=20) || tipo==28){
						
						//link="ModificarDocumentoInternoEspecialC.do?cod_doc_interno="+item.getCodigo_documento_interno();
						link="javascript:window.parent.modificarIframe("+item.getCodigo_documento_interno()+");";
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";
					}
					
					if(tipo==6 || tipo==7 || (tipo>=21 && tipo<=26) || tipo==29){
						link="ModificarDocumentoInternoEspecialT.do?cod_doc_interno="+item.getCodigo_documento_interno();
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";
					}
					
					if(tipo==31){ //cod_doc_int_origen
						link="javascript:window.parent.modificarIframeProyectos("+item.getCodigo_documento_interno()+");";
						link="<a  href='"+link+"' >"+"<img src='img/edit_doc.png' title='Modificar con nuevo Num. Registro' width='20' height='20' border='0'> </a>";
						
						/*link="ModificarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno()+"&tipo_pi=1";
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";*/
					}
					if(tipo==32){
						link="javascript:window.parent.modificarIframeProyectos("+item.getCodigo_documento_interno()+");";
						link="<a  href='"+link+"' >"+"<img src='img/edit_doc.png' title='Modificar con nuevo Num. Registro' width='20' height='20' border='0'> </a>";
						
						/*link="ModificarDocumentoInternoEspecialC.do?cod_doc_interno="+item.getCodigo_documento_interno()+"&tipo_pi=1";
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";*/
					}
					if(tipo==33){
						link="ModificarDocumentoInternoEspecialT.do?cod_doc_interno="+item.getCodigo_documento_interno()+"&tipo_pi=1";
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";
					}
										
					String verPDF="VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					verPDF="<a  href='"+verPDF+"'>"+"<img src='img/pdf.gif' title='Ver documento PDF' width='20' height='20' border='0'/> </a>";
				
					//String enviarAdjunto="MoverArchivoAAdjunto.do?cod_doc_interno="+item.getCodigo_documento_interno();					
					//enviarAdjunto="<a  href='javascript:AdjuntarDocumento("+item.getCodigo_documento_interno()+")';>"+"<img src='img/docadjuntos.gif' alt='Adjuntar Documento' width='24' height='24' border='0' /> </a>";
					
					/*String firmarDocumento="FirmarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					firmarDocumento="<a  href='"+firmarDocumento+"'>"+"<img src='img/ver.gif' alt='firmar' width='24' height='24' border='0'> </a>";*/
					valor = (item.getCodigo_documento()==null)? "0":"1";
					
					String firmarDocumento="javascript:LlamarApplet("+item.getCodigo_documento_interno()+","+item.getCodigo_tipo_documento_interno()+","+valor+",1);";
					firmarDocumento="<a href='"+firmarDocumento+"'>"+"<img src='img/03firmar.png' title='Firmar' width='36' height='36' border='0'> </a>";
					
					/***
					 * CODIGO NUEVO
					 */
					String uploadDocumento="javascript:AgregarDocEscaneados("+item.getCodigo_documento_interno()+");";
					uploadDocumento="<a href='"+uploadDocumento+"'>"+"<img src='img/upload.png' title='Subir Archivo Firmado' width='20' height='20' border='0'> </a>";
					
					
					/**
					 * FIN
					 */
					
					String enviarAADocumento="ActionSeleccion.do?rb_seleccion=I&cod_doc_interno="+item.getCodigo_documento_interno()+"&oper=re";					
					//enviarAADocumento="<a  href='javascript:fn_editar_copia("+item.getCodigo_documento_interno()+")';><img src='img/nuevo.gif' alt='registrar' width='24' height='24' border='0'></a>";
					enviarAADocumento="<a  href='"+enviarAADocumento+"'>"+"<img src='img/nuevo.gif' alt='registrar' width='20' height='20' border='0'> </a>";
					
					String enviarAAdjunto="<a  href='javascript:AgregarArchivoInterno("+item.getCodigo_documento_interno()+")';>"+"<img src='img/docadjuntos.gif' alt='Adjuntar Documento' width='20' height='20' border='0' /> </a>";
					
					enviarAAdjunto="MantDocumento.do?operacion=RI&codigo="+item.getCodigo_documento_interno();
					enviarAAdjunto="<a  href='"+enviarAAdjunto+"'>"+"<img src='img/adjuntar.png' alt='adjuntar' width='20' height='20' border='0'> </a>";
					
					/*String eliminarDocumento="MantenimientoDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					eliminarDocumento="<a  href='"+eliminarDocumento+"'>"+"<img src='img/delete.png' title='Eliminar' width='24' height='24' border='0'> </a>";
					*/
					String eliminarDocumento="<a  href='javascript:deletedocument("+item.getCodigo_documento_interno()+")'>"+"<img src='img/delete.png' title='Eliminar' width='20' height='20' border='0'> </a>";
					String cerrarProyecto="<a  href='javascript:cerrardocument("+item.getCodigo_documento_interno()+")'>"+"<img src='img/delete.png' title='Eliminar' width='20' height='20' border='0'> </a>";
					
					String verPDFsfirmado ="VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno()+"&interno=SI";
					verPDFsfirmado="<a href='"+verPDFsfirmado+"'>"+"<img src='img/pdf.gif' title='Ver Archivo Firmado' width='20' height='20' border='0'> </a>";
					
					String eliminarPDFfirmado="javascript:deletedocumentinterno("+item.getCodigo_documento_interno()+");";
					eliminarPDFfirmado="<a href='"+eliminarPDFfirmado+"'>"+"<img src='img/delete.png' title='Eliminar Archivo Firmado' width='20' height='20' border='0'> </a>";
					
					String enviarPDFfirmado="javascript:LlamarApplet("+item.getCodigo_documento_interno()+","+item.getCodigo_tipo_documento_interno()+","+valor+",0);";
					enviarPDFfirmado="<a href='"+enviarPDFfirmado+"'>"+"<img src='img/send_doc.png' title='Enviar Registro' width='20' height='20' border='0'> </a>";

					
					item.setNombre_arhivo("<a  href='VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno()+"'  title='Ver Documento PDF' >"+rst.getString("nombre_archivo")+"</a>");
					item.setNota(rst.getString("nombre_archivo"));
					item.setLink(link+" "+verPDF+" "+eliminarDocumento);
								
					//item.setLink(link+" "+verPDF+" "+firmarDocumento);
					
					
					if (bandeja==7){
						//proyecto recibido
						//item.setLink(link+" "+verPDF+" "+cerrarProyecto);
						item.setLink(link+" "+verPDF);
					}
					
					
					
					link="ModificarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					link="<a  href='"+link+"'>"+"<img src='img/edit_doc.png' title='Modificar' width='20' height='20' border='0'> </a>";
												
					if (bandeja==1){
						item.setLink(verPDF+" "+enviarAAdjunto);
					}
					if (bandeja==2){
						enviarAAdjunto="AdjuntarDocumentoInterno.do?operacion=RI&codigo="+item.getCodigo_documento_interno();
						enviarAAdjunto="<a  href='"+enviarAAdjunto+"'>"+"<img src='img/adjuntar.png' title='Adjuntar Proyecto' width='20' height='20' border='0'> </a>";
						item.setLink(verPDF+" "+enviarAAdjunto);
					}
					if (bandeja==3){
						enviarAAdjunto="AdjuntarDocumentoInternoEspecialC.do?operacion=RI&codigo="+item.getCodigo_documento_interno();
						enviarAAdjunto="<a  href='"+enviarAAdjunto+"'>"+"<img src='img/adjuntar.png' title='Adjuntar Proyecto' width='20' height='20' border='0'> </a>";
						item.setLink(verPDF+" "+enviarAAdjunto);
					}
					if (bandeja==4){
						enviarAAdjunto="AdjuntarDocumentoInternoEspecialT.do?operacion=RI&codigo="+item.getCodigo_documento_interno();
						enviarAAdjunto="<a  href='"+enviarAAdjunto+"'>"+"<img src='img/adjuntar.png' title='Adjuntar Proyecto' width='20' height='20' border='0'> </a>";
						item.setLink(verPDF+" "+enviarAAdjunto);
					}
					
					valorDeIdLogDocumentoInterno = rst.getString("id_log_documento_interno_firma");
					valorDeIdLogDocumentoInterno=(valorDeIdLogDocumentoInterno==null)? "":valorDeIdLogDocumentoInterno;
					
					/***DESPUES DE LA FIRMA PREVIA ***/
					if (bandeja==5){

						verPDF="mostrarlistasdebandejadocumentosinternos.do?cambia=upload&codigo_documento_interno="+item.getCodigo_documento_interno();
						verPDF="<a  class='showPopup' href='"+verPDF+"' title='Lista de Documentos - "+rst.getString("nombre_archivo")+"'>"+"<img src='img/copy_f2.png' title='Ver Documentos Adjuntos' width='20' height='20' border='0'> </a>";
						
						if(firmaprevia==0){ //bandeja firma
							if(!valorDeIdLogDocumentoInterno.equals("")){
								firmarDocumento="javascript:LlamarApplet("+item.getCodigo_documento_interno()+","+item.getCodigo_tipo_documento_interno()+","+valor+",2);";
								firmarDocumento="<a href='"+firmarDocumento+"'>"+"<img src='img/forward-icon.png' title='Enviar' width='32' height='32' border='0'> </a>";
								
								item.setNombre_arhivo("<a  href='VerArchivoPDFDocumentoInterno.do?filename="+rst.getString("nombre_archivo").substring(9,rst.getString("nombre_archivo").length())+"&codigo_estado=4&codigo_oficina_pertenece="+rst.getInt("codigo_oficina_pertenece")+"'  title='Ver Documento PDF' >"+rst.getString("nombre_archivo").substring(9,rst.getString("nombre_archivo").length())+"</a>");
							}
						}
						
						if(rst.getString("nombre_doc_adjuntos")!=null && rst.getString("nombre_doc_adjuntos").length()>0 && !rst.getString("nombre_doc_adjuntos").trim().equals("")){
							item.setLink(link+" "+verPDF+" "+eliminarDocumento+" "+firmarDocumento);
						}else{
							verPDF="<img src='img/copy_f3.png' width='20' height='20' border='0'>";	
							item.setLink(link+" "+verPDF+" "+eliminarDocumento+" "+firmarDocumento);
						}
						
					}
					
					/**BANDEJA FIRMA MASIVA***/
					if (bandeja==9){
						link="javascript:window.parent.modificarIframe("+item.getCodigo_documento_interno()+");";
						link="<a  href='"+link+"'>"+"<img src='img/edit_doc.png' title='Modificar' width='20' height='20' border='0'> </a>";
						
						String firmaMasiva = "<input  type='checkbox' name='selectedItems' value='"+item.getCodigo_documento_interno()+"' onclick=ModificarEstado('"+item.getCodigo_documento_interno()+"') >";
						firmarDocumento = "<img src='img/03firmargray.png' width='36' height='36' border='0'>";	
						//verPDF="mostrarlistasdebandejadocumentosinternos.do?cambia=upload&codigo_documento_interno="+item.getCodigo_documento_interno();
						//verPDF="<a  class='showPopup' href='"+verPDF+"' title='Lista de Documentos - "+rst.getString("nombre_archivo")+"'>"+"<img src='img/copy_f2.png' title='Ver Documentos Adjuntos' width='20' height='20' border='0'> </a>";
						
						verPDF="javascript:window.parent.mostrarlistasdebandejadocumentosinternos("+item.getCodigo_documento_interno()+");";
						verPDF="<a  href='"+verPDF+"'>"+"<img src='img/copy_f2.png' title='Ver Documentos Adjuntos' width='20' height='20' border='0'> </a>";
						
						if(rst.getString("nombre_doc_adjuntos")!=null && rst.getString("nombre_doc_adjuntos").length()>0 && !rst.getString("nombre_doc_adjuntos").trim().equals("")){
							item.setLink(link+" "+verPDF+" "+eliminarDocumento+" "+firmarDocumento);
						}else{
							verPDF="<img src='img/copy_f3.png' width='20' height='20' border='0'>";	
							item.setLink(link+" "+verPDF+" "+eliminarDocumento+" "+firmarDocumento);
						}
												
						item.setLink3(firmaMasiva);
					}
					
					if (bandeja==6){
						
						verPDF="mostrarlistasdebandejadocumentosinternos.do?cambia=upload&codigo_documento_interno="+item.getCodigo_documento_interno();
						verPDF="<a  class='showPopup' href='"+verPDF+"' title='Lista de Documentos - "+rst.getString("nombre_archivo")+"'>"+"<img src='img/copy_f2.png' title='Ver Documentos Adjuntos' width='20' height='20' border='0'> </a>";
						
						if(firmaprevia==0){ //bandeja firma
							if(!valorDeIdLogDocumentoInterno.equals("")){
								link ="";
								firmarDocumento="javascript:LlamarApplet("+item.getCodigo_documento_interno()+","+item.getCodigo_tipo_documento_interno()+","+valor+",2);";
								firmarDocumento="<a href='"+firmarDocumento+"'>"+"<img src='img/forward-icon.png' title='Enviar' width='32' height='32' border='0'> </a>";
								
								item.setNombre_arhivo("<a  href='VerArchivoPDFDocumentoInterno.do?filename="+rst.getString("nombre_archivo").substring(9,rst.getString("nombre_archivo").length())+"&codigo_estado=4&codigo_oficina_pertenece="+rst.getInt("codigo_oficina_pertenece")+"'  title='Ver Documento PDF' >"+rst.getString("nombre_archivo").substring(9,rst.getString("nombre_archivo").length())+"</a>");
							}else{
								firmarDocumento = "<img src='img/03firmargray.png' width='36' height='36' border='0'>";	
								//item.setNombre_arhivo("<a  href='VerArchivoPDFDocumentoInterno.do?filename="+rst.getString("nombre_archivo").substring(9,rst.getString("nombre_archivo").length())+"&codigo_estado=3&codigo_oficina_pertenece="+rst.getInt("codigo_oficina_pertenece")+"'  title='Ver Documento PDF' >"+rst.getString("nombre_archivo").substring(9,rst.getString("nombre_archivo").length())+"</a>");
								item.setNombre_arhivo("<a  href='VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno()+"'  title='Ver Documento PDF' >"+rst.getString("nombre_archivo")+"</a>");
							}
						}
						
						if(rst.getString("nombre_doc_adjuntos")!=null && rst.getString("nombre_doc_adjuntos").length()>0 && !rst.getString("nombre_doc_adjuntos").trim().equals("")){
							item.setLink(link+" "+verPDF+" "+eliminarDocumento+" "+firmarDocumento);
						}else{
							verPDF="<img src='img/copy_f3.png' width='20' height='20' border='0'>";	
							item.setLink(link+" "+verPDF+" "+eliminarDocumento+" "+firmarDocumento);
						}
						
						//item.setLink(verPDF);
					}
					
					
					/**
					 * NUEVO SOLO PARA TRAMITE SIN FIRMA DIGITAL
					 */
					if (bandeja==8){//INDICA QUE NO TIENE FIRMA 
						if(rst.getInt("contador")==0){ //SUBIR FILE FIRMADO
							item.setLink(link+" "+verPDF+" "+eliminarDocumento+" "+uploadDocumento);
						}else{//CUANDO YA SUBIO EL FILE
							item.setLink(verPDFsfirmado+" "+eliminarPDFfirmado+" "+enviarPDFfirmado);
						}
					}
										
					/**
					 * FIN
					 */
					
					
					if(firmaprevia==1){ //bandeja firma masiva
						if(valorDeIdLogDocumentoInterno.equals("")){
							if(rst.getInt("contadorprincipal")==0){
								myLista.add(item);
							}
						}
					}else{ //bandeja de firma normal
							//myLista.add(item);
							if(rst.getInt("contadorprincipal")==0){
								myLista.add(item);
							}
							
					}
					
					
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return myLista;
	}
	
	@Override
	public ArrayList<DocumentoInternoBean> getlistaDocumentosInternos(
			int bandeja, int codigo_oficina_pertenece, int fIRMADO, int codigo_tipo_documento_interno, int codigo_oficina,  String nom_documento, int nrodoc) throws Exception {
		PreparedStatement pstmt = null;
		ArrayList<DocumentoInternoBean> myLista = new ArrayList<DocumentoInternoBean>();
		DocumentoInternoBean item;
		ResultSet rst = null;
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		
		log.info("-----antes del sql------");
		log.info("-----codigo_tipo_documento_interno------"+codigo_tipo_documento_interno);
		log.info("-----codigo_oficina_destino------"+codigo_oficina);
		log.info("-----seguimos con el sql------");
		
		StringBuffer ls_sql = new StringBuffer("SELECT codigo_documento_interno, codigo_tipo_documento_interno, codigo_documento, asunto, descripcion,   dirigido_a, firmado_por, fecha_creacion, fecha_modificacion,     codigo_estado_documento, codigo_oficina, codigo_oficina_pertenece, numero_doc, anio_doc,personas,nombre_archivo FROM tramite.documento_tipo_documento_interno");
		ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=? ");
		
		if(codigo_tipo_documento_interno>0){
			ls_sql.append(" AND codigo_tipo_documento_interno =?");
		}
		if(codigo_tipo_documento_interno==0){
			ls_sql.append(" AND codigo_tipo_documento_interno IN (1,2,3,4,5,8)");
		}
		if(codigo_oficina>0){
			ls_sql.append(" AND codigo_oficina =?  ");
		}
		if(!nom_documento.equals("")){
			ls_sql.append(" AND nombre_archivo LIKE '%"+nom_documento+"%' ");
		}
		if(nrodoc>0){
			ls_sql.append("AND codigo_documento_interno =? ");
		}
		
		ls_sql.append(" ORDER BY 1 desc ");
				
		 try {	
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1,codigo_oficina_pertenece);
				pstmt.setInt(2,fIRMADO);
				int temp = 3;
				
				if(codigo_tipo_documento_interno>0){
					//int xtipo = Integer.parseInt(ls_tipo);
					pstmt.setInt(temp,codigo_tipo_documento_interno);
					temp+=1;
				}
				if(codigo_oficina>0){
					pstmt.setInt(temp,codigo_oficina);
					temp+=1;
				}
				if(nrodoc>0){
					pstmt.setInt(temp,nrodoc);
					
				}
								
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				while(rst.next()){
					item = new DocumentoInternoBean();
					item.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
					item.setCodigo_tipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
					item.setAsunto(rst.getString("asunto"));
					item.setDirigido_a(rst.getString("dirigido_a"));
					item.setCodigo_estado_documento(rst.getInt("codigo_estado_documento"));
					item.setFecha_creacion(rst.getTimestamp("fecha_creacion"));
					item.setCodigo_oficina(rst.getInt("codigo_oficina"));
					
					String link="ModificarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='24' height='24' border='0'> </a>";
					
					if(codigo_tipo_documento_interno==6){
						link="ModificarDocumentoInternoEspecialC.do?cod_doc_interno="+item.getCodigo_documento_interno();
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='24' height='24' border='0'> </a>";
					}
					
					if(codigo_tipo_documento_interno==7){
						link="ModificarDocumentoInternoEspecialT.do?cod_doc_interno="+item.getCodigo_documento_interno();
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='24' height='24' border='0'> </a>";
					}
					
					String verPDF="VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					verPDF="<a  href='"+verPDF+"'>"+"<img src='img/pdf.gif' title='Ver documento PDF' width='24' height='24' border='0'/> </a>";
				
					String enviarAdjunto="MoverArchivoAAdjunto.do?cod_doc_interno="+item.getCodigo_documento_interno();					
					enviarAdjunto="<a  href='javascript:AdjuntarDocumento("+item.getCodigo_documento_interno()+")';>"+"<img src='img/docadjuntos.gif' alt='Adjuntar Documento' width='24' height='24' border='0' /> </a>";
					
					String firmarDocumento="FirmarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					firmarDocumento="<a  href='"+firmarDocumento+"'>"+"<img src='img/ver.gif' title='Firmar' width='24' height='24' border='0'> </a>";
					
					/*String eliminarDocumento="MantenimientoDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					eliminarDocumento="<a  href='"+eliminarDocumento+"'>"+"<img src='img/delete.png' title='Eliminar' width='24' height='24' border='0'> </a>";
					*/
					String eliminarDocumento="<a  href='deletedocument("+item.getCodigo_documento_interno()+")'>"+"<img src='img/delete.png' title='Eliminar' width='24' height='24' border='0'> </a>";					
					
					
					item.setNombre_arhivo(rst.getString("nombre_archivo"));
					
				
					
				//	item.setLink(verPDF +" "+enviarAdjunto);
				//	item.setLink(link+" "+verPDF+" "+firmarDocumento);
					item.setLink(link+" "+verPDF+" "+eliminarDocumento);
					
					if (bandeja==1){
						item.setLink(verPDF);
					}
					
					myLista.add(item);
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return myLista;
	}

	/**
	 * NUEVO
	 */
	@Override
	public void modificaTablaDocumentosInternos(int codigo_documento_interno,int codigo_registro, int secuencia_movimiento_destino, String tipo_envio) throws Exception {    	
		log.info("Entrando a modificaTablaDocumentosInternos...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		
		try {	
     		
			StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
			//uptateQuery.append(" SET codigo_tipo_documento_interno=?,"); 
			uptateQuery.append(" SET codigo_documento=?, "); 
			uptateQuery.append(" secuencia_movimiento_destino=? ");
			
			if(tipo_envio.equals("R")){
				uptateQuery.append(", secuencia_movimiento=1 ");
			}
			
			uptateQuery.append(" WHERE codigo_documento_interno=?");

			
			conn = obt.getConnection();
			log.info("query de actualizacion 1:   " +uptateQuery.toString());
			pstmt = conn.prepareStatement(uptateQuery.toString());
			//pstmt.setInt(1, documentoInterno.getCodigo_tipo_documento_interno());
			pstmt.setInt(1, codigo_registro);
			pstmt.setInt(2, secuencia_movimiento_destino);
			pstmt.setInt(3, codigo_documento_interno);
			
			pstmt.executeUpdate();
			
			/*
			uptateQuery  = new StringBuffer("update tramite.tbl_det_upinternos set estado_usuario_origen = 'C', estado_usuario_fin = 'C' ");
			uptateQuery.append(" where id_det_upinternos = (select  b.id_det_upinternos  from  tramite.tbl_cab_upinternos a, tramite.tbl_det_upinternos b "); 
			uptateQuery.append(" where a.id_cab_upinternos = b.id_cab_upinternos and a.codigo_documento_interno = ? )");
			
			conn = obt.getConnection();
			log.info("query de actualizacion 2:   " +uptateQuery.toString());
			pstmt = conn.prepareStatement(uptateQuery.toString());
			pstmt.setInt(1, codigo_documento_interno);
			
			pstmt.executeUpdate();*/

            obt.cerrar_conexion(conn);
            
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
        

		}
	
	/***
	 * NUEVO
	 */
	
	@Override
	public BInfoDocumento esRegistroDocumentoInterno(String codigo_documento, String codigo_oficina_user, String secuencia, String ls_tipenv) throws Exception {    	
		log.info("Entrando a esRegistroDocumentoInterno...");
		PreparedStatement pstmt = null;
		//String siglas_oficina="";
		ResultSet rst = null;
		BInfoDocumento BInfoDocumentoVO = new BInfoDocumento();
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		log.info("ls_tipenv -> "+ls_tipenv);
		
		 try {	
     		
			 String ls_sql = "SELECT  codigo_documento_interno  FROM tramite.documento_tipo_documento_interno  where codigo_documento=? and flag = 1 and codigo_estado_documento NOT IN (7,8)";
			 if(!codigo_oficina_user.equals("")){
				 ls_sql = "SELECT  codigo_documento_interno  FROM tramite.documento_tipo_documento_interno  where codigo_documento=? and codigo_oficina_pertenece=? and codigo_estado_documento NOT IN (7,8)";
			 }
			 if(!secuencia.equals("")){
				 ls_sql = "SELECT  codigo_documento_interno, codigo_estado_documento, tipo_envio, codigo_tipo_documento_interno  FROM tramite.documento_tipo_documento_interno  where codigo_documento=? and codigo_oficina_pertenece=? and secuencia_movimiento=?  and codigo_estado_documento NOT IN (7,8)";
			 }
			 
			 if(ls_tipenv.equals("R")){
				 
				 ls_sql = "SELECT  codigo_documento_interno, codigo_estado_documento, tipo_envio, codigo_tipo_documento_interno  " +
				 		" FROM tramite.documento_tipo_documento_interno  " +
				 		" where codigo_documento=? and codigo_oficina_pertenece=? and secuencia_movimiento=? and tipo_envio like 'R' and codigo_estado_documento NOT IN (3,7,8)";
			 }
			 if(ls_tipenv.equals("D")){
				 
				 ls_sql = "SELECT  codigo_documento_interno, codigo_estado_documento, tipo_envio, codigo_tipo_documento_interno  " +
				 		" FROM tramite.documento_tipo_documento_interno  " +
				 		" where codigo_documento=? and codigo_oficina_pertenece=? and secuencia_movimiento=? and tipo_envio IN ('D','N') and codigo_estado_documento NOT IN (7,8)";
			 }
				
				log.info("Query -> "+ls_sql);
			 	conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setString(1, codigo_documento);
				if(!codigo_oficina_user.equals("")){
					pstmt.setString(2, codigo_oficina_user);
				}
				 if(!secuencia.equals("")){
					 pstmt.setString(3, secuencia);
				 }
				rst = pstmt.executeQuery();	
				//log.info("sql :"+ls_sql);
				if(rst.next()){
					BInfoDocumentoVO.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
					BInfoDocumentoVO.setEstado_documento_interno(rst.getInt("codigo_estado_documento"));
					BInfoDocumentoVO.setTipo_envio(rst.getString("tipo_envio"));
					BInfoDocumentoVO.setCodigo_tipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
					BInfoDocumentoVO.setInterno(true);
					log.info("Codigo doc int encontrado >>>"+BInfoDocumentoVO.getCodigo_documento_interno());
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return BInfoDocumentoVO;

		}
	
	@Override
	public boolean esRegistroDocumentoInterno(String codigo_documento, String codigo_oficina_user, String secuencia) throws Exception {    	
		log.info("Entrando a esRegistroDocumentoInterno...boolean");
		PreparedStatement pstmt = null;
		//String siglas_oficina="";
		ResultSet rst = null;
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		boolean resultado=false;
		 try {	
     		
			 String  ls_sql = "SELECT  *  " +
				 		" FROM tramite.documento_tipo_documento_interno  " +
				 		" where codigo_documento=? and codigo_oficina_pertenece=? and secuencia_movimiento=? and tipo_envio IN ('D','N','R') and codigo_estado_documento IN (3)";
				
				//log.info("Query -> "+ls_sql);
			 	conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setString(1, codigo_documento);
				pstmt.setString(2, codigo_oficina_user);
				pstmt.setString(3, secuencia);
				
				log.info("sql :"+pstmt);
				
				rst = pstmt.executeQuery();	
				
				if(rst.next()){
					resultado=true;
					log.info("Codigo doc int encontrado >>>");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return resultado;

		}
	
	@Override
	public boolean esRegistroDocumentoInternoObservado(String codigo_documento, String codigo_oficina_user, String secuencia_destino) throws Exception {    	
		log.info("Entrando a esRegistroDocumentoInternoObservado...boolean");
		PreparedStatement pstmt = null;
		//String siglas_oficina="";
		ResultSet rst = null;
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		boolean resultado=false;
		 try {	
     		
			 String  ls_sql = "SELECT  *  " +
				 		" FROM tramite.documento_tipo_documento_interno  " +
				 		" where codigo_documento=? and codigo_oficina_pertenece=? and secuencia_movimiento_destino=? and tipo_envio IN ('D','N','R') and codigo_estado_documento IN (9,10)";
				
				//log.info("Query -> "+ls_sql);
			 	conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setString(1, codigo_documento);
				pstmt.setString(2, codigo_oficina_user);
				pstmt.setString(3, secuencia_destino);
				
				log.info("sql :"+pstmt);
				
				rst = pstmt.executeQuery();	
				
				if(rst.next()){
					resultado=true;
					log.info("Codigo doc int encontrado >>>");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return resultado;

		}
	
	@Override
	public void deleteDocumentoInterno(int codigo_documento_interno) throws Exception {    	
		log.info("Entrando a deleteDocumentoInterno...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		/***
		 * TENGO QUE DISTINGUIR SI ES PROYECTO
		 * 
		 * SI ES PROYECTO DEBO DE CERRAR EL ACCESO
		 */
		try {	
     		
			StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
			//uptateQuery.append(" SET codigo_tipo_documento_interno=?,"); 
			uptateQuery.append(" SET codigo_estado_documento=7"); 
			uptateQuery.append(" WHERE codigo_documento_interno=?");

			
			conn = obt.getConnection();
			log.info("query de actualizacion:   " +uptateQuery.toString());
			pstmt = conn.prepareStatement(uptateQuery.toString());
			//pstmt.setInt(1, documentoInterno.getCodigo_tipo_documento_interno());
			pstmt.setInt(1, codigo_documento_interno);
						
			pstmt.executeUpdate();
            obt.cerrar_conexion(conn);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
        

		}
	
	@Override
	public int of_tipo_documento_interno(int codigo_documento_interno)throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		int tipo_documento=0;
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		
		 try {	
     		
				StringBuffer ls_sql = new StringBuffer("select codigo_tipo_documento_interno from tramite.documento_tipo_documento_interno where codigo_documento_interno = ?");
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1, codigo_documento_interno);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					tipo_documento = rst.getInt("codigo_tipo_documento_interno");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
	        
		return tipo_documento;
		
	}
	
	@Override
	public int getSiguienteNumeroDocumentoBorrador(Integer codigo_oficina_pertenece,
			Integer codigo_tipo_documento_interno) throws Exception {
		PreparedStatement pstmt = null;
		int numSiguiente=0;
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		
		 try {	
     		
			 StringBuffer ls_sql = new StringBuffer("SELECT MAX(numero_doc_temporal) as numero_doc_temporal  FROM tramite.documento_tipo_documento_interno WHERE codigo_oficina_pertenece=? AND codigo_tipo_documento_interno=? ");//AND anio_doc=2012
				
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1,codigo_oficina_pertenece);
				pstmt.setInt(2,codigo_tipo_documento_interno);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				if(rst.next()){
					numSiguiente= rst.getInt("numero_doc_temporal");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
	        numSiguiente=numSiguiente+1;
		return numSiguiente;
	}
	
	@Override
	public Collection getPersonal(Integer codigo_oficina) throws Exception {
		
		Collection ListaPersonasOficinas = new ArrayList();
		BPersonal BPersonalVO = null;
		
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		
		 try {	
			 String ls_sql = "SELECT pe.nombre_personal,pe.codigo_personal,of.codigo_oficina," +
 			"us.estado,pe.es_responsable FROM tramite.oficinas of,tramite.personal pe," +
			"tramite.usuarios us WHERE pe.codigo_oficina =of.codigo_oficina AND" +
			" pe.c_usuario=us.usuario and us.estado = 'A' " +
			"and pe.codigo_oficina = ? " +
			" order by pe.es_responsable desc,pe.nombre_personal asc";
			
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql);
				pstmt.setInt(1, codigo_oficina);
				rst = pstmt.executeQuery();	
				log.info("sql :"+ls_sql);
				while(rst.next()){
					BPersonalVO = new BPersonal();
					
					BPersonalVO.setNombre_personal(rst.getString("nombre_personal"));
					BPersonalVO.setCodigo_personal(rst.getString("codigo_personal"));
					BPersonalVO.setCodigo_oficina(rst.getString("codigo_oficina"));
					System.out.println(BPersonalVO.getNombre_personal());
					
					ListaPersonasOficinas.add(BPersonalVO);
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		return ListaPersonasOficinas;
	}
	
	
	public int getCodigoDocumentoInterno(String nombreFile,int codigo_oficina_pertenece)throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		int resultado=0;
		
		 try {	
     		
			 StringBuffer ls_sql = new StringBuffer("select codigo_documento_interno from tramite.documento_tipo_documento_interno where nombre_archivo = ? and codigo_oficina_pertenece = ? ");

			 	conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setString(1, nombreFile);
				pstmt.setInt(2, codigo_oficina_pertenece);
				rst = pstmt.executeQuery();  		
				log.info("sql :"+ls_sql);
				while(rst.next()){
					resultado = rst.getInt("codigo_documento_interno");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return resultado;
	}
	
	public int getCodigoDocumentoInterno(String codigo_documento, String oficina, String secuencia)throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		int resultado=0;
		
		 try {	
     		
			 StringBuffer ls_sql = new StringBuffer("select codigo_documento_interno from tramite.documento_tipo_documento_interno where codigo_documento = ?  AND codigo_oficina_pertenece = ?  AND  secuencia_movimiento=? ");

			 	conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setString(1, codigo_documento);
				pstmt.setString(2, oficina);
				pstmt.setString(3, secuencia);
				rst = pstmt.executeQuery();  		
				//log.info("sql :"+ls_sql);
				while(rst.next()){
					resultado = rst.getInt("codigo_documento_interno");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return resultado;
	}
	
public void saveDocumentoPrincipalFirmantes(int codigoDelDocumentoNuevo,BArchivo archivoenlista, String[] firmantes_destino)throws Exception{
		
		log.info("Entrando a saveDocumentoPrincipalFirmantes...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		ResultSet rst = null;
		
		int resultado = 0;
		try {	
			
			String insertQuery = "INSERT INTO tramite.tbl_cab_upinternos(codigo_documento_interno, nombre_archivo, nombre_archivo_cifrado, fecha_creacion, estado,codigos_documento_interno_proyecto) VALUES (?,?,?,?,?,?);";
			
					conn = obt.getConnection();
					pstmt = conn.prepareStatement(insertQuery);
					pstmt.setInt(1, codigoDelDocumentoNuevo);
					pstmt.setString(2, archivoenlista.getNombre_archivo());
					pstmt.setString(3, archivoenlista.getNombre_archivo_cifrado());
					pstmt.setTimestamp(4,new  Timestamp(System.currentTimeMillis()) );
					pstmt.setString(5,"P");
					pstmt.setInt(6, archivoenlista.getId_archivo_proyecto());
					
			pstmt.executeUpdate();
			
			
			
			String selectQuery = "SELECT last_value as valor FROM tramite.seq_tbl_cab_upinternos;";
		    	 			 			
			pstmt = conn.prepareStatement(selectQuery);
			log.info("sql :"+pstmt);
			rst = pstmt.executeQuery();	
			
			if(rst.next()){
				resultado = rst.getInt("valor");
    		}

			int codigo_oficina=0;
			if(firmantes_destino!=null && firmantes_destino.length>0 ){
	  			log.info("Enter CODIGOS DE OFICINAS FIRMANTES********************");
		  		 for (int   i = 0;   i < firmantes_destino.length;   i++) {
		  			codigo_oficina = Integer.parseInt(firmantes_destino[i]);
		  			
		  			insertQuery = "INSERT INTO tramite.tbl_det_upinternos(id_cab_upinternos, codigo_oficina, ruta, visado, firmado, fecha_modificacion,estado_usuario_origen,estado_usuario_fin,usuario)  VALUES (?,?,?,?,?,?,?,?,?);";
					//conn = obt.getConnection();
					pstmt = conn.prepareStatement(insertQuery);
					pstmt.setInt(1, resultado );
					pstmt.setInt(2, codigo_oficina);
					pstmt.setString(3, archivoenlista.getRuta());
					
					if(archivoenlista.isIsvisado())
						pstmt.setInt(4, 1 );
					else{
						pstmt.setInt(4, 0 );
					}
					
					if(archivoenlista.isIsfirmado())
						pstmt.setInt(5, 1 );
					else{
						pstmt.setInt(5, 0 );
					}
					pstmt.setTimestamp(6,new  Timestamp(System.currentTimeMillis()) );
					
					pstmt.setString(7, archivoenlista.getEstado_usuario_origen());
					pstmt.setString(8, archivoenlista.getEstado_usuario_fin());
					pstmt.setString(9, archivoenlista.getUsuario());
					
					pstmt.executeUpdate();
		  			

		  		 }
	  		
	  		}
			
			
			
					
			obt.cerrar_conexion(conn,rst);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
        
		
		
	}
	
	public void saveDocumentosAdjuntos(int codigoDelDocumentoNuevo,BArchivo archivoenlista)throws Exception{
		
		log.info("Entrando a saveDocumentosAdjuntos...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		ResultSet rst = null;
		
		int resultado = 0;
		try {	
     		
			if(archivoenlista.getId_archivo_proyecto() > 0){
				archivoenlista.setRuta(Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre());
			}
			
			String insertQuery = "INSERT INTO tramite.tbl_cab_upinternos(codigo_documento_interno, nombre_archivo, nombre_archivo_cifrado, fecha_creacion, estado,codigos_documento_interno_proyecto) VALUES (?,?,?,?,?,?);";
			
					conn = obt.getConnection();
					pstmt = conn.prepareStatement(insertQuery);
					pstmt.setInt(1, codigoDelDocumentoNuevo);
					pstmt.setString(2, archivoenlista.getNombre_archivo());
					pstmt.setString(3, archivoenlista.getNombre_archivo_cifrado());
					pstmt.setTimestamp(4,new  Timestamp(System.currentTimeMillis()) );
					
					if(archivoenlista.isIsvisado())
						pstmt.setString(5,"V");
					else{
						pstmt.setString(5, "A");
					}
					
					pstmt.setInt(6, archivoenlista.getId_archivo_proyecto());
					
			pstmt.executeUpdate();
			
			String selectQuery = "select id_cab_upinternos  from tramite.tbl_cab_upinternos where codigo_documento_interno = ?  and  nombre_archivo  = ? and estado IN ('A','V')";
			//conn = obt.getConnection();
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setInt(1, codigoDelDocumentoNuevo);
			pstmt.setString(2, archivoenlista.getNombre_archivo());
			rst = pstmt.executeQuery();	
			log.info("sql :"+selectQuery);
			if(rst.next()){
				resultado = rst.getInt("id_cab_upinternos");
    		}
			
			
			insertQuery = "INSERT INTO tramite.tbl_det_upinternos(id_cab_upinternos, usuario, ruta, visado, firmado, fecha_modificacion,estado_usuario_origen,estado_usuario_fin,usuario_destino)  VALUES (?,?,?,?,?,?,?,?,?);";
			//conn = obt.getConnection();
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1, resultado );
			pstmt.setString(2, archivoenlista.getUsuario());
			pstmt.setString(3, archivoenlista.getRuta());
			
			if(archivoenlista.isIsvisado())
				pstmt.setInt(4, 1 );
			else{
				pstmt.setInt(4, 0 );
			}
			
			if(archivoenlista.isIsfirmado())
				pstmt.setInt(5, 1 );
			else{
				pstmt.setInt(5, 0 );
			}
			pstmt.setTimestamp(6,new  Timestamp(System.currentTimeMillis()) );
			
			if(archivoenlista.getId_archivo_proyecto() > 0){
			pstmt.setString(7, "A");
			pstmt.setString(8, "C");
			pstmt.setString(9, "none");
			}
			else{
				pstmt.setString(7, "C");
				pstmt.setString(8, "C");
				pstmt.setString(9, "none");
			}
			
			pstmt.executeUpdate();
			
			
			/*StringBuffer uptateQuery2  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
			if(archivoenlista.getId_archivo_proyecto() > 0){
				uptateQuery2.append(" SET codigos_documento_interno_proyecto = ?, estado_usuario_origen = 'A', estado_usuario_fin = 'C'"); 
			}else{
				uptateQuery2.append(" SET codigos_documento_interno_proyecto = ?, estado_usuario_origen = 'C', estado_usuario_fin = 'C'");
			}
			uptateQuery2.append(" WHERE codigo_documento_interno = ?"); 

			conn = obt.getConnection();
			log.info("query de actualizacion:   " +uptateQuery2.toString());
			pstmt = conn.prepareStatement(uptateQuery2.toString());
			
			pstmt.setInt(1, archivoenlista.getId_archivo_proyecto());
			pstmt.setInt(2, codigoDelDocumentoNuevo);
			
			pstmt.executeUpdate();*/
			
			
			obt.cerrar_conexion(conn,rst);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
        
		
		
	}
	
public void saveDocumentoAdjuntoVistoBueno(int codigoDelDocumentoNuevo,BArchivo archivoenlista, String[] visto_bueno_destino)throws Exception{
		
		log.info("Entrando a saveDocumentoAdjuntoVistoBueno...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		ResultSet rst = null;
		
		int resultado = archivoenlista.getId_archivo();
		try {	
			
			String insertQuery;
			int codigo_oficina=0;
			if(visto_bueno_destino!=null && visto_bueno_destino.length>0 ){
	  			log.info("Enter CODIGOS DE OFICINAS VISTO BUENO********************");
		  		 for (int   i = 0;   i < visto_bueno_destino.length;   i++) {
		  			codigo_oficina = Integer.parseInt(visto_bueno_destino[i]);
		  			
		  			insertQuery = "INSERT INTO tramite.tbl_det_upinternos(id_cab_upinternos, codigo_oficina, ruta, visado, firmado, fecha_modificacion,estado_usuario_origen,estado_usuario_fin,usuario)  VALUES (?,?,?,?,?,?,?,?,?);";
					conn = obt.getConnection();
					pstmt = conn.prepareStatement(insertQuery);
					pstmt.setInt(1, resultado );
					pstmt.setInt(2, codigo_oficina);
					pstmt.setString(3, archivoenlista.getRuta());
					
					if(archivoenlista.isIsvisado())
						pstmt.setInt(4, 1 );
					else{
						pstmt.setInt(4, 0 );
					}
					
					if(archivoenlista.isIsfirmado())
						pstmt.setInt(5, 1 );
					else{
						pstmt.setInt(5, 0 );
					}
					pstmt.setTimestamp(6,new  Timestamp(System.currentTimeMillis()) );
					
					pstmt.setString(7, archivoenlista.getEstado_usuario_origen());
					pstmt.setString(8, archivoenlista.getEstado_usuario_fin());
					pstmt.setString(9, archivoenlista.getUsuario());
					
					pstmt.executeUpdate();
		  			

		  		 }
	  		
	  		}
				
			obt.cerrar_conexion(conn,rst);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
        
		
		
	}

	public ArrayList<BArchivo> getListaArchivosAdjuntos(int codigo_documento_interno) throws Exception{
		ArrayList<BArchivo> resultado= new ArrayList<BArchivo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		BArchivo item;
		int valor=0;
		log.info("Entre getListaArchivosAdjuntos");
		 try {	
     		
				String ls_sql = "SELECT tcu.id_cab_upinternos, tcu.estado, tcu.nombre_archivo, tcu.nombre_archivo_cifrado, tcu.codigos_documento_interno_proyecto ,tdu.id_det_upinternos, tdu.usuario, tdu.ruta, tdu.visado, tdu.firmado, tdu.estado_usuario_origen,tdu.estado_usuario_fin " +
						" FROM tramite.tbl_cab_upinternos tcu, tramite.tbl_det_upinternos tdu  WHERE codigo_documento_interno = ? "+
						" AND tcu.id_cab_upinternos = tdu.id_cab_upinternos AND tcu.estado IN ('A','V') AND tdu.codigo_oficina=0";
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1, codigo_documento_interno);

				rst = pstmt.executeQuery();  		
				log.info("sql :"+ls_sql);
	    		while(rst.next()){
	    			item= new BArchivo();
	    			item.setId_archivo(rst.getInt("id_cab_upinternos"));
	    			item.setEstado(rst.getString("estado"));
	    			item.setNombre_archivo(rst.getString("nombre_archivo"));
	    			item.setNombre_archivo_cifrado(rst.getString("nombre_archivo_cifrado"));
	    			item.setId_detalle_archivo(rst.getInt("id_det_upinternos"));
	    			item.setUsuario(rst.getString("usuario"));
	    			item.setRuta(rst.getString("ruta"));
	    			
	    			valor = rst.getInt("visado");
	    			if(valor == 1){
	    				item.setIsvisado(true);
	    				item.setIschecked(true);
	    			}else{
	    				item.setIsvisado(false);
	    				item.setIschecked(false);
	    			}
	    			
	    			valor = rst.getInt("firmado");
	    			if(valor == 1){
	    				item.setIsfirmado(true);
	    			}else{
	    				item.setIsfirmado(false);
	    			}
	    			
	    			item.setId_archivo_proyecto(rst.getInt("codigos_documento_interno_proyecto"));
	    			item.setEstado_usuario_origen(rst.getString("estado_usuario_origen"));
	    			item.setEstado_usuario_fin(rst.getString("estado_usuario_fin"));
	    			
	    			resultado.add(item);
	    		}
	            obt.cerrar_conexion(conn,rst);	
	            pstmt.close();
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return resultado;
	}
	
	public ArrayList<BArchivo> getArchivoPrincipal(int codigo_documento_interno,int ciclo) throws Exception{
		ArrayList<BArchivo> resultado= new ArrayList<BArchivo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		BArchivo item;

		log.info("Entre getArchivoPrincipal");
		 try {	
     		
				StringBuilder ls_sql = new StringBuilder("SELECT tcu.id_cab_upinternos, tcu.estado, tcu.nombre_archivo, tcu.nombre_archivo_cifrado ");
				ls_sql.append(" FROM tramite.tbl_cab_upinternos tcu  WHERE tcu.codigo_documento_interno =?  ");
				ls_sql.append(" AND tcu.estado ='P' AND tcu.ciclo=?  ");
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1, codigo_documento_interno);
				pstmt.setInt(2, ciclo);

				rst = pstmt.executeQuery();  		
				log.info("sql :"+pstmt);
				
	    		while(rst.next()){
	    			item= new BArchivo();
	    			item.setId_archivo(rst.getInt("id_cab_upinternos"));
	    			item.setEstado(rst.getString("estado"));
	    			item.setNombre_archivo(rst.getString("nombre_archivo_cifrado"));
	    			item.setNombre_archivo_cifrado(rst.getString("nombre_archivo_cifrado"));
	    			
	    			resultado.add(item);
	    		}
	            obt.cerrar_conexion(conn,rst);	
	            pstmt.close();
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return resultado;
	}

	
	public boolean siHayArchivosAdjuntosxCambiarNombres(int codigo_documento_interno) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		boolean valor=false;
		log.info("Entre getListaArchivosAdjuntosxCambiarNombres");
		
		 try {	
     		
				StringBuffer ls_sql = new StringBuffer("SELECT tcu.id_cab_upinternos, tcu.estado, tcu.nombre_archivo, tcu.nombre_archivo_cifrado, tcu.codigos_documento_interno_proyecto ,");
				ls_sql.append(" tdu.id_det_upinternos, tdu.usuario, tdu.ruta, tdu.visado, tdu.firmado, tdu.estado_usuario_origen,tdu.estado_usuario_fin , tdi.codigo_tipo_documento_interno ");
				ls_sql.append(" FROM tramite.tbl_cab_upinternos tcu, tramite.tbl_det_upinternos tdu ,tramite.documento_tipo_documento_interno tdi "); 
				ls_sql.append(" WHERE tcu.codigo_documento_interno = ? "); 
				ls_sql.append(" AND tcu.id_cab_upinternos = tdu.id_cab_upinternos AND tcu.estado IN ('A','V') ");
				ls_sql.append(" and tcu.codigos_documento_interno_proyecto=tdi.codigo_documento_interno ");
				ls_sql.append(" and tdi.codigo_tipo_documento_interno IN (6,7)");
				
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1, codigo_documento_interno);

				log.info("sql :"+pstmt);
				rst = pstmt.executeQuery();  		
				
	    		while(rst.next()){
	    			valor=true;
	    		}
	            obt.cerrar_conexion(conn,rst);
	            pstmt.close();
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return valor;
	}
	
	
public void updateDocumentosAdjuntos(int codigo_documento,BArchivo archivoenlista)throws Exception{
		
		log.info("Entrando a updateDocumentosAdjuntos...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		//Calendar c = new GregorianCalendar(); 
		int valor=0;
		int id_detalle_documento = archivoenlista.getId_detalle_archivo();
		int id_archivo = archivoenlista.getId_archivo();
		String estado = "A";
		//int tipo = documentoInterno.getCodigo_tipo_documento_interno();
	
	try{	
		StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.tbl_det_upinternos");
		uptateQuery.append(" SET visado =?, "); 
		uptateQuery.append(" fecha_modificacion = ? "); 
		uptateQuery.append("WHERE id_det_upinternos = ?"); 

		conn = obt.getConnection();
		log.info("query de actualizacion:   " +uptateQuery.toString());
		pstmt = conn.prepareStatement(uptateQuery.toString());
		
		if(archivoenlista.isIsvisado()){
			valor = 1;
		}
		pstmt.setInt(1, valor);
		pstmt.setTimestamp(2, new  Timestamp(System.currentTimeMillis()));
		pstmt.setInt(3, id_detalle_documento);
		
		pstmt.executeUpdate();
		
		/****/
		
		if(valor == 1){
			estado = "V";
		}
			
			
			StringBuffer uptateQuery2  = new StringBuffer("UPDATE tramite.tbl_cab_upinternos");
			uptateQuery2.append(" SET estado = ?"); 
			uptateQuery2.append(" WHERE id_cab_upinternos = ?"); 

			//conn = obt.getConnection();
			log.info("query de actualizacion:   " +uptateQuery2.toString());
			pstmt = conn.prepareStatement(uptateQuery2.toString());
			
			pstmt.setString(1, estado);
			pstmt.setInt(2, id_archivo);
			
			pstmt.executeUpdate();
		

        obt.cerrar_conexion(conn);		
        
    } catch (SQLException sqle) {
    	obt.cerrar_conexion(conn);
		sqle.printStackTrace();
    }
		
	}

	public void updateNombresDocumentosAdjuntos(BArchivo archivoenlista)throws Exception{
		
		log.info("Entrando a updateNombresDocumentosAdjuntos...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();

		int id_detalle_documento = archivoenlista.getId_archivo();
	
		try{	
			StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.tbl_cab_upinternos");
			uptateQuery.append(" SET nombre_archivo =?, "); 
			uptateQuery.append(" nombre_archivo_cifrado = ? "); 
			uptateQuery.append("WHERE id_cab_upinternos = ?"); 
		
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(uptateQuery.toString());

			pstmt.setString(1, archivoenlista.getNombre_archivo().substring(5,archivoenlista.getNombre_archivo().length()));
			pstmt.setString(2, archivoenlista.getNombre_archivo().substring(5,archivoenlista.getNombre_archivo().length()));
			pstmt.setInt(3, id_detalle_documento);
			
			log.info("query de actualizacion:   " +pstmt);
			pstmt.executeUpdate();
			
		    obt.cerrar_conexion(conn);		
		    
		} catch (SQLException sqle) {
			obt.cerrar_conexion(conn);
			sqle.printStackTrace();
		}
		
	}

	public void deleteDocumentosAdjuntos(int codigo_documento,BArchivo archivoenlista)throws Exception{
		log.info("Entrando a deleteDocumentosAdjuntos...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		int id_archivo = archivoenlista.getId_archivo();

	try{	
		StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.tbl_cab_upinternos");
		uptateQuery.append(" SET estado = 'E' "); 
		uptateQuery.append("WHERE id_cab_upinternos = ?"); 

		conn = obt.getConnection();
		log.info("query de actualizacion:   " +uptateQuery.toString());
		pstmt = conn.prepareStatement(uptateQuery.toString());
		
		pstmt.setInt(1, id_archivo);
		
		pstmt.executeUpdate();
        obt.cerrar_conexion(conn);		
        
    } catch (SQLException sqle) {
    	obt.cerrar_conexion(conn);
		sqle.printStackTrace();
    }
		
	}
	
	public int getEstadoDocumentoInterno(int codigoDocumentoInterno)throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		int resultado=0;
		
		 try {	
     		
			 StringBuffer ls_sql = new StringBuffer("select codigo_estado_documento from tramite.documento_tipo_documento_interno where codigo_documento_interno = ? ");

			 	conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql.toString());
				pstmt.setInt(1, codigoDocumentoInterno);
				rst = pstmt.executeQuery();  		
				log.info("sql :"+ls_sql);
				while(rst.next()){
					resultado = rst.getInt("codigo_estado_documento");
	    		}
	            obt.cerrar_conexion(conn,rst);		
	            
	        } catch (SQLException sqle) {
	        	obt.cerrar_conexion(conn);
				sqle.printStackTrace();
	        }
		
		return resultado;
	}
	
	public void updateEstadoRegistro(int codigo_documento_interno, int numero_doc_pdf, String nombreArchivo)throws Exception{

		log.info("Entrando a updateEstadoRegistro...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		int valor = 3;
		
	try{	
		StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
		uptateQuery.append(" SET numero_doc =?, "); 
		uptateQuery.append(" nombre_archivo = ?, "); 
		uptateQuery.append(" codigo_estado_documento = ?, "); 
		uptateQuery.append(" fecha_modificacion = ? ");
		uptateQuery.append("WHERE codigo_documento_interno = ?"); 

		conn = obt.getConnection();
		log.info("query de actualizacion:   " +uptateQuery.toString());
		pstmt = conn.prepareStatement(uptateQuery.toString());
		
		pstmt.setInt(1, numero_doc_pdf);
		pstmt.setString(2, nombreArchivo);
		pstmt.setInt(3, valor);
		pstmt.setTimestamp(4, new  Timestamp(System.currentTimeMillis()));
		pstmt.setInt(5, codigo_documento_interno);
		pstmt.executeUpdate();
		
        obt.cerrar_conexion(conn);		
        
    } catch (SQLException sqle) {
    	obt.cerrar_conexion(conn);
		sqle.printStackTrace();
    }
		
	}
	
	
public void updateRutaDocumentosAdjuntos(BArchivo archivoenlista)throws Exception{
		
		log.info("Entrando a updateRutaDocumentosAdjuntos...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();


	try{	
		StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.tbl_det_upinternos");
		uptateQuery.append(" SET ruta =?, "); 
		uptateQuery.append(" fecha_modificacion = ? "); 
		uptateQuery.append("WHERE id_det_upinternos = ?"); 

		conn = obt.getConnection();
		log.info("query de actualizacion:   " +uptateQuery.toString());
		pstmt = conn.prepareStatement(uptateQuery.toString());
		
//		if(archivoenlista.getEstado().equals("V")){
			pstmt.setString(1, Constantes.CarpetaArchivosDesarrolloenTramiteVisados.getNombre());
/*		}else{
			pstmt.setString(1, Constantes.CarpetaArchivosDesarrolloenFirmadaDigital.getNombre());
		}*/
		
		pstmt.setTimestamp(2, new  Timestamp(System.currentTimeMillis()));
		pstmt.setInt(3, archivoenlista.getId_detalle_archivo());
		
		pstmt.executeUpdate();
		obt.cerrar_conexion(conn);		
        
    } catch (SQLException sqle) {
    	obt.cerrar_conexion(conn);
		sqle.printStackTrace();
    }
		
	}

public void updateEstadoUsuariosTblDetUpinternos(String codigo_documento_proyecto_editar)throws Exception{
	
	log.info("Entrando a updateEstadoUsuariosTblDetUpinternos...");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ObtieneConexion obt=new ObtieneConexion();
	
	codigo_documento_proyecto_editar =(codigo_documento_proyecto_editar==null)? "":codigo_documento_proyecto_editar;
	
	
	try{	
		StringBuffer uptateQuery  = new StringBuffer("update tramite.tbl_det_upinternos  set estado_usuario_fin='C'  where id_cab_upinternos =(select cu.id_cab_upinternos from tramite.tbl_cab_upinternos cu where cu.codigos_documento_interno_proyecto= ? );"); 
		
		if(!codigo_documento_proyecto_editar.equals("")){
			conn = obt.getConnection();
			log.info("query de actualizacion:   " +uptateQuery.toString());
			pstmt = conn.prepareStatement(uptateQuery.toString());
			
			pstmt.setString(1, codigo_documento_proyecto_editar);
			
			pstmt.executeUpdate();
			obt.cerrar_conexion(conn);	
		}
				
	    
	} catch (SQLException sqle) {
		obt.cerrar_conexion(conn);
		sqle.printStackTrace();
	}
	
}


public BDatosFirmante getDatosFirmante(String usuario_actual) throws Exception{
	PreparedStatement pstmt = null;
	ResultSet rst = null;

	
	ObtieneConexion obt=new ObtieneConexion();
	Connection conn  = obt.getConnection();
	BDatosFirmante resultado= new BDatosFirmante();
	
	String nnn = usuario_actual;
	nnn = (nnn==null)? "nulo":nnn;
	
	System.out.println("Entre a RECUPERAR DATOS DEL FIRMANTE!!!");
	try {	
		
		 StringBuilder ls_sql = new StringBuilder("select tp.codigo_personal, tp.nombre_personal, tc.nombrecargo, tof.descripcion_oficina, tof.nombre_corto, tp.tipo_firma, tp.nombre_sello_visto, tp.ruta_sello, tp.nombre_pfx, tp.ruta_pfx, tof.nombre_sello, tu.dni, tu.abrev ");
		 ls_sql.append("from tramite.personal tp ");
		 ls_sql.append("inner join tramite.usuarios tu  ");
		 ls_sql.append("on tu.usuario = tp.c_usuario ");
		 ls_sql.append("inner join tramite.cargo tc ");
		 ls_sql.append("on tp.cargo_personal = tc.codigo_cargo ");
		 ls_sql.append("inner join tramite.oficinas tof ");
		 ls_sql.append("on tp.codigo_oficina = tof.codigo_oficina ");
		 ls_sql.append("where tu.usuario=? and tu.estado = 'A'");
						
			
			pstmt = conn.prepareStatement(ls_sql.toString());
			pstmt.setString(1, usuario_actual);
			
			//log.info("sql :"+pstmt);
			rst = pstmt.executeQuery();	
			
			if(rst.next()){
				resultado.setFirmado_por(rst.getString("nombre_personal"));
				resultado.setCargo(rst.getString("nombrecargo"));
				resultado.setTipo_firma(rst.getInt("tipo_firma"));
				resultado.setNombre_sello_visto(rst.getString("nombre_sello_visto"));
				resultado.setRuta_sello(rst.getString("ruta_sello"));
				resultado.setNombre_pfx(rst.getString("nombre_pfx"));
				resultado.setRuta_pfx(rst.getString("ruta_pfx"));
				resultado.setNombre_sello(rst.getString("nombre_sello"));
				resultado.setAbreviatura(rst.getString("abrev"));
				resultado.setDni(rst.getString("dni"));
				resultado.setOficina(rst.getString("descripcion_oficina"));
				resultado.setOficinaPermisoFirma(1);
				resultado.setNombre_corto(rst.getString("nombre_corto"));
				log.info("Firmado por -> "+resultado.getFirmado_por());
				log.info("Tipo de firma -> "+resultado.getTipo_firma());
			}
			pstmt.close();
		    obt.cerrar_conexion(conn,rst);		
		    
		} catch (SQLException sqle) {
			obt.cerrar_conexion(conn);
			sqle.printStackTrace();
		}
	return resultado;
}

public BDatosFirmante getDatosFirmantexCodigo(String codigo_persona_noborrar) throws Exception{
	PreparedStatement pstmt = null;
	ResultSet rst = null;

	ObtieneConexion obt=new ObtieneConexion();
	Connection conn = obt.getConnection();
	
	BDatosFirmante resultado= new BDatosFirmante();
	String esresponsable="";
	String esmaximaoficina="";
	/*String nnn = usuario_actual;
	nnn = (nnn==null)? "nulo":nnn;*/
	
	System.out.println("Entre a RECUPERAR DATOS DEL FIRMANTE xCodigo!!!");
	try {	
		
		System.out.println("Entre aqui1!!");
		 StringBuffer ls_sql = new StringBuffer("select tp.codigo_personal, tp.nombre_personal, tc.nombrecargo, tof.descripcion_oficina, tp.tipo_firma, tp.nombre_sello_visto, tp.cargo_personal,"); 
		 ls_sql.append(" tp.ruta_sello, tp.nombre_pfx, tp.ruta_pfx, tof.nombre_sello, tu.dni, tu.abrev, tof.codigo_oficina,tp.es_responsable,");
		 ls_sql.append(" (select nombre_corto from tramite.grupo_oficina where tof.grupo_oficina=codigo_grupo and tof.orden=orden) as esoficinamaxima");
		 ls_sql.append(" ,tp.c_usuario ");
		 ls_sql.append(" from tramite.personal tp, tramite.usuarios tu , tramite.cargo tc, tramite.oficinas tof ");
		 ls_sql.append(" where tp.c_usuario = tu.usuario ");
		 ls_sql.append(" and tp.cargo_personal = tc.codigo_cargo "); 
		 ls_sql.append(" and tp.codigo_oficina = tof.codigo_oficina "); 
		 ls_sql.append(" and tu.estado = 'A' and tp.codigo_personal = ?");
		 System.out.println("Entre aqui!!");
			
		
			pstmt = conn.prepareStatement(ls_sql.toString());
			pstmt.setString(1, codigo_persona_noborrar);
			rst = pstmt.executeQuery();	
			log.info("sql :"+ls_sql);
			if(rst.next()){
				resultado.setFirmado_por(rst.getString("nombre_personal"));
				log.info(resultado.getFirmado_por());
				resultado.setCargo(rst.getString("nombrecargo"));
				resultado.setTipo_firma(rst.getInt("tipo_firma"));
				resultado.setNombre_sello_visto(rst.getString("nombre_sello_visto"));
				resultado.setRuta_sello(rst.getString("ruta_sello"));
				resultado.setNombre_pfx(rst.getString("nombre_pfx"));
				resultado.setRuta_pfx(rst.getString("ruta_pfx"));
				resultado.setNombre_sello(rst.getString("nombre_sello"));
				resultado.setAbreviatura(rst.getString("abrev"));
				resultado.setDni(rst.getString("dni"));
				resultado.setOficina(rst.getString("descripcion_oficina"));
				
				esresponsable=rst.getString("es_responsable");
				esresponsable = (esresponsable==null)? "N":esresponsable.trim();
				if(esresponsable.equals("S")){
					resultado.setEsresponsable(true);
				}
				
				esmaximaoficina=rst.getString("esoficinamaxima");
				//log.info("esoficinamaxima "+rst.getString("esoficinamaxima"));
				esmaximaoficina = (esmaximaoficina==null)? "":esmaximaoficina.trim();
				if(!esmaximaoficina.equals("")){
					resultado.setEsoficinamaxima(true);
				}

				if(rst.getInt("cargo_personal")==10 || rst.getInt("cargo_personal")==13){
					resultado.setEssecretaria(true);
				}
				
				resultado.setUsuario("c_usuario");
				
			}
		    obt.cerrar_conexion(conn,rst);		
		    
		} catch (SQLException sqle) {
			obt.cerrar_conexion(conn);
			sqle.printStackTrace();
		}
	return resultado;
}

public BInfoListaReiterativo getCodigoDocumentoInternoReiterativo(String codigo_documento, String oficina, String secuencia)throws Exception{
	PreparedStatement pstmt = null;
	ResultSet rst = null;		
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	BInfoListaReiterativo resultado= new BInfoListaReiterativo();
	
	 try {	
 		
		 StringBuffer ls_sql = new StringBuffer("select codigo_documento_interno, codigo_estado_documento  from tramite.documento_tipo_documento_interno where codigo_documento = ?  AND codigo_oficina_pertenece = ?  AND  secuencia_movimiento=?  AND  tipo_envio = 'R' AND codigo_estado_documento NOT IN (3,7,8) ");

		 	conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			pstmt.setString(1, codigo_documento);
			pstmt.setString(2, oficina);
			pstmt.setString(3, secuencia);
			rst = pstmt.executeQuery();  		
			//log.info("sql :"+ls_sql);
			while(rst.next()){
				resultado.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
				resultado.setEstado_documento_interno(rst.getInt("codigo_estado_documento"));
    		}
            obt.cerrar_conexion(conn,rst);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	
	return resultado;
}


public BInfoListaReiterativo getCodigoDocumentoInternoDerivado(String codigo_documento, String oficina, String secuencia, String oficina_origen)throws Exception{
	PreparedStatement pstmt = null;
	ResultSet rst = null;		
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	BInfoListaReiterativo resultado= new BInfoListaReiterativo();
	 try {	
 		
		 //VERIFICO SI EXISTE RESPUESTA A ESTE REGISTRO
		 StringBuffer ls_sql = new StringBuffer("select codigo_documento_interno, codigo_estado_documento  from tramite.documento_tipo_documento_interno where codigo_documento = ?  AND codigo_oficina_pertenece = ?  AND  secuencia_movimiento=?  AND  tipo_envio = 'D' AND codigo_estado_documento NOT IN (3,7,8) ");

		 	conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			pstmt.setString(1, codigo_documento);
			pstmt.setString(2, oficina);
			pstmt.setString(3, secuencia);
			rst = pstmt.executeQuery();  		
			//log.info("sql :"+ls_sql);
			while(rst.next()){
				resultado.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
				resultado.setEstado_documento_interno(rst.getInt("codigo_estado_documento"));
    		}
			
			// SI NO HAY RESPUESTA VERIFICO SI EXISTEN PROYECTOS
			//if(resultado.getEstado_documento_interno()==0){
				
			StringBuffer ls_sql2 = new StringBuffer("SELECT  count(b.codigos_documento_interno_proyecto) as codigo_docu ");
			ls_sql2.append(" FROM tramite.documento_tipo_documento_interno a, tramite.tbl_cab_upinternos b, tramite.tbl_det_upinternos c ");
			ls_sql2.append(" WHERE a.codigo_tipo_documento_interno IN (1,2,3,4,5,8)	AND a.codigo_documento_interno = b.codigo_documento_interno ");
			ls_sql2.append(" AND a.codigo_documento_interno IN (SELECT  codigo_documento_interno  FROM tramite.documento_tipo_documento_interno "); 
			ls_sql2.append(" where codigo_documento= ?  and codigo_oficina_pertenece= ?  and  secuencia_movimiento_destino= ?  AND codigo_oficina = ? ");
			ls_sql2.append(" and codigo_estado_documento IN (3) ) and b.id_cab_upinternos = c.id_cab_upinternos  AND b.estado NOT IN ('E') ");
			ls_sql2.append(" and c.estado_usuario_origen like 'C'  and c.estado_usuario_fin like 'A'"); 

			pstmt = conn.prepareStatement(ls_sql2.toString());
			//log.info(codigo_documento+","+oficina_origen+","+secuencia+","+oficina);
			pstmt.setString(1, codigo_documento);
			pstmt.setString(2, oficina_origen);
			pstmt.setString(3, secuencia);
			pstmt.setString(4, oficina);
			rst = pstmt.executeQuery();  		
			//log.info("sql :"+ls_sql2);
			while(rst.next()){
				resultado.setCodigo_documento_interno_origen(rst.getInt("codigo_docu"));
    		}

            obt.cerrar_conexion(conn,rst);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	
	return resultado;
}

public BInfoListaReiterativo getCodigoDocumentoInternoDerivadoObservado(String codigo_documento, String oficina, String secuencia)throws Exception{
	PreparedStatement pstmt = null;
	ResultSet rst = null;		
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	BInfoListaReiterativo resultado= new BInfoListaReiterativo();
	 try {	
 		
		 StringBuffer ls_sql = new StringBuffer("select codigo_documento_interno, codigo_estado_documento  from tramite.documento_tipo_documento_interno where codigo_documento = ?  AND codigo_oficina_pertenece = ?  AND  secuencia_movimiento_destino=? ");

		 	conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			pstmt.setString(1, codigo_documento);
			pstmt.setString(2, oficina);
			pstmt.setString(3, secuencia);
			rst = pstmt.executeQuery();  		
			//log.info("sql :"+ls_sql);
			while(rst.next()){
				resultado.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
				resultado.setEstado_documento_interno(rst.getInt("codigo_estado_documento"));
    		}

            obt.cerrar_conexion(conn,rst);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	
	return resultado;
}

public ArrayList<DocumentoInternoBean> getlistaDocumentosInternosFirmados(int codigo_tipo_documento_interno, int codigo_documento, int codigo_documento_interno_busqueda,Date fecha_inicio, Date fecha_fin, String dirigido_a, int codigo_oficina_pertenece) throws Exception{
	PreparedStatement pstmt = null;
	ArrayList<DocumentoInternoBean> myLista = new ArrayList<DocumentoInternoBean>();
	DocumentoInternoBean item;
	ResultSet rst = null;
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	
	log.info("-----antes del sql------");
	log.info("-----codigo_tipo_documento_interno------"+codigo_tipo_documento_interno);
	log.info("-----codigo_oficina------"+codigo_oficina_pertenece);
	log.info("-----codigo_documento_interno------"+codigo_documento_interno_busqueda);
	log.info("-----seguimos con el sql------");
	
	StringBuffer ls_sql = new StringBuffer("SELECT codigo_documento_interno, codigo_tipo_documento_interno, codigo_documento, asunto, descripcion,   dirigido_a, firmado_por, fecha_creacion, fecha_modificacion, codigo_estado_documento, codigo_oficina, codigo_oficina_pertenece, numero_doc, anio_doc,personas,nombre_archivo FROM tramite.documento_tipo_documento_interno");
	ls_sql.append(" WHERE codigo_oficina_pertenece =? AND codigo_estado_documento=3 ");
	
	if(codigo_tipo_documento_interno>0){
		ls_sql.append(" AND codigo_tipo_documento_interno =?");
	}
	if(codigo_tipo_documento_interno==0){
		ls_sql.append(" AND codigo_tipo_documento_interno IN (1,2,3,4,5,6,7,8)");
	}
	
	if(codigo_documento > 0){
		ls_sql.append(" AND codigo_documento =?  ");
	}
	if(codigo_documento_interno_busqueda > 0){
		ls_sql.append(" AND numero_doc = ?  ");
	}
	if(fecha_inicio != null  && !fecha_inicio.equals("")){
		ls_sql.append(" AND fecha_modificacion >= '"+fecha_inicio+"' ");
	}
	if(fecha_fin!= null && !fecha_fin.equals("")){
		ls_sql.append(" AND fecha_modificacion <= '"+fecha_fin+"' ");
	}
	if(!dirigido_a.equals("")){
		ls_sql.append(" AND dirigido_a LIKE '%"+dirigido_a+"%' ");
	}
	
	ls_sql.append(" ORDER BY 13 desc ");
			
	 try {	
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			pstmt.setInt(1,codigo_oficina_pertenece);
			int temp = 2;
			
			if(codigo_tipo_documento_interno>0){
				pstmt.setInt(temp,codigo_tipo_documento_interno);
				temp+=1;
			}
			if(codigo_documento>0){
				pstmt.setInt(temp,codigo_documento);
				temp+=1;
			}
			if(codigo_documento_interno_busqueda>0){
				pstmt.setInt(temp,codigo_documento_interno_busqueda);
				
			}

							
			rst = pstmt.executeQuery();	
			log.info("sql :"+ls_sql);
			while(rst.next()){
				item = new DocumentoInternoBean();
				item.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
				item.setCodigo_tipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
				item.setAsunto(rst.getString("asunto"));
				item.setDirigido_a(rst.getString("dirigido_a"));
				item.setCodigo_estado_documento(rst.getInt("codigo_estado_documento"));
				item.setFecha_modificacion(rst.getTimestamp("fecha_modificacion"));
				item.setCodigo_oficina(rst.getInt("codigo_oficina"));
				
				item.setCodigo_documento(rst.getInt("codigo_documento"));
				item.setNumero_doc(rst.getInt("numero_doc"));
				
				String link="ModificarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
				link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='24' height='24' border='0'> </a>";
				
				if(codigo_tipo_documento_interno==6){
					link="ModificarDocumentoInternoEspecialC.do?cod_doc_interno="+item.getCodigo_documento_interno();
					link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='24' height='24' border='0'> </a>";
				}
				
				if(codigo_tipo_documento_interno==7){
					link="ModificarDocumentoInternoEspecialT.do?cod_doc_interno="+item.getCodigo_documento_interno();
					link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='24' height='24' border='0'> </a>";
				}
				
				String verPDF="VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
				verPDF="<a  href='"+verPDF+"'>"+"<img src='img/pdf.gif' title='Ver documento PDF' width='24' height='24' border='0'/> </a>";
			
				String enviarAdjunto="MoverArchivoAAdjunto.do?cod_doc_interno="+item.getCodigo_documento_interno();					
				enviarAdjunto="<a  href='javascript:AdjuntarDocumento("+item.getCodigo_documento_interno()+")';>"+"<img src='img/docadjuntos.gif' alt='Adjuntar Documento' width='24' height='24' border='0' /> </a>";
				
				String firmarDocumento="FirmarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
				firmarDocumento="<a  href='"+firmarDocumento+"'>"+"<img src='img/ver.gif' title='Firmar' width='24' height='24' border='0'> </a>";
				
				/*String eliminarDocumento="MantenimientoDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
				eliminarDocumento="<a  href='"+eliminarDocumento+"'>"+"<img src='img/delete.png' title='Eliminar' width='24' height='24' border='0'> </a>";
				*/
				String eliminarDocumento="<a  href='deletedocument("+item.getCodigo_documento_interno()+")'>"+"<img src='img/delete.png' title='Eliminar' width='24' height='24' border='0'> </a>";					
				
				
				item.setNombre_arhivo(rst.getString("nombre_archivo"));
				
			
				
			//	item.setLink(verPDF +" "+enviarAdjunto);
			//	item.setLink(link+" "+verPDF+" "+firmarDocumento);
				item.setLink(link+" "+verPDF+" "+eliminarDocumento);
				
				item.setLink(verPDF);
								
				myLista.add(item);
    		}
            obt.cerrar_conexion(conn,rst);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	
	return myLista;
}

public ArrayList<DocumentoInternoBean> getlistaArchivosBusqueda(int codigo_oficina, int codigo_oficina_pertenece, String nombre_archivo,Date fecha_inicio, Date fecha_fin) throws Exception{
	PreparedStatement pstmt = null;
	ArrayList<DocumentoInternoBean> myLista = new ArrayList<DocumentoInternoBean>();
	DocumentoInternoBean item;
	String tokens[]=null;
	ResultSet rst = null;
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	
	log.info("-----antes del sql------");
	log.info("-----codigo_oficina busqueda------"+codigo_oficina);
	log.info("-----codigo_oficina------"+codigo_oficina_pertenece);
	log.info("-----nombre_archivo------"+nombre_archivo);
	
	log.info("-----seguimos con el sql------");
	
	StringBuilder ls_sql = new StringBuilder("select distinct du.id_det_upload,du.codigo_documento,du.codigo_expediente,du.nombre_archivo,du.fecha_creacion,");
	ls_sql.append("md.oficina_origen,md.oficina_deriva, md.ultimo_usuario,du.fecha_creacion,du.estado_documento,doc.asunto_documento  ");
	ls_sql.append(" from tramite.tbl_det_upload du left join tramite.movimientos_doc md on du.codigo_recepcion = md.codigo_recepcion ");
	ls_sql.append(" left join tramite.documento doc on md.codigo_documento = doc.codigo_documento ");
	ls_sql.append(" where ");
	
	if(codigo_oficina==codigo_oficina_pertenece){
		ls_sql.append(" du.flag='I' and  md.oficina_deriva = ?  " );
	}else{
		ls_sql.append(" md.oficina_origen = ?  and du.flag='I'");
		
		if(codigo_oficina > 0){
			ls_sql.append(" and md.oficina_deriva = ? ");
		}
	}
		
	if(nombre_archivo != null && !nombre_archivo.trim().equals("") && !nombre_archivo.equals("null")){
		nombre_archivo = nombre_archivo.trim();
		tokens=nombre_archivo.split(" ");
		for(int i=0;i<tokens.length;i++){
			ls_sql.append(" and du.nombre_archivo ilike  '%"+ tokens[i] +"%' ");	
		} 
	}
	
	if(fecha_inicio != null  && !fecha_inicio.equals("")){
		ls_sql.append(" AND fecha_creacion >= '"+fecha_inicio+"' ");
	}
	if(fecha_fin!= null && !fecha_fin.equals("")){
		ls_sql.append(" AND fecha_creacion <= '"+fecha_fin+"' ");
	}
	
	//ls_sql.append(" and md.tipo_envio in ('P','R') ");
	
	ls_sql.append(" ORDER BY 2 desc ");
			
	 try {	
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			pstmt.setInt(1,codigo_oficina_pertenece);
			if(codigo_oficina!=codigo_oficina_pertenece){
				int temp = 2;
				if(codigo_oficina > 0){
					pstmt.setInt(temp,codigo_oficina);
					temp+=1;
				}
			}
			log.info("sql :"+pstmt);
			
			rst = pstmt.executeQuery();	
			
			while(rst.next()){
				
				item = new DocumentoInternoBean();
				
				item.setCodigo_documento(rst.getInt("codigo_documento"));
				item.setNombre_arhivo(rst.getString("nombre_archivo").trim());
				item.setCodigo_oficina_pertenece(rst.getInt("oficina_origen"));
				item.setCodigo_oficina(rst.getInt("oficina_deriva"));
				item.setFecha_creacion(rst.getTimestamp("fecha_creacion"));
				item.setAsunto(rst.getString("asunto_documento"));
				

				String verPDF="ListaDocumentos.do?iddetupload="+rst.getInt("id_det_upload")+"&tipo=uploaddoc&nombre_archivo="+item.getNombre_arhivo()+"&fileroute="+rst.getString("estado_documento").trim();
				verPDF="<a  href='"+verPDF+"'>"+"<img src='img/downloads-d-icon.png' title='Descargar archivo' width='24' height='24' border='0'/> </a>";
				
				
				item.setLink(verPDF);
								
				myLista.add(item);
    		}
            obt.cerrar_conexion(conn,rst);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	
	return myLista;
}

public ArrayList<BInfoInv> getlistaDocumentosInternosFirmados() throws Exception{
	PreparedStatement pstmt = null;
	ArrayList<BInfoInv> myLista = new ArrayList<BInfoInv>();

	ResultSet rst = null;
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	
	
	StringBuffer ls_sql = new StringBuffer("select id_investigador, apellido_paterno,apellido_materno,nombres,email from directorio_cti.tbl_investigador ");
							ls_sql.append(" where flag = 'C' order by apellido_paterno, apellido_materno  asc;");
				
	 try {	
			conn = obt.getConnectionDirectorio();
			pstmt = conn.prepareStatement(ls_sql.toString());
			rst = pstmt.executeQuery();	
			
			log.info("sql :"+ls_sql);
			while(rst.next()){
				BInfoInv item = new BInfoInv();
				item.setId_inv(rst.getInt("id_investigador"));
				item.setApepat(rst.getString("apellido_paterno"));
				item.setApemat(rst.getString("apellido_materno"));
				item.setNombreinv(rst.getString("nombres"));
				item.setEmail(rst.getString("email"));
				
				myLista.add(item);
    		}
			/***
			 * REALIZAMOS LA VERIFICACION
			 *
			double resultado=0;
			if(myLista!=null && myLista.size()>0){
  				Iterator it=myLista.iterator();
  				while(it.hasNext()){
  					BInfoInv archivoenlista=(BInfoInv)it.next();
  					archivoenlista.getId_inv();
  					
  					StringBuffer ls_sql2 = new StringBuffer("");
  					ls_sql2.append("select (CASE WHEN cantlaboral>0 THEN 1 ");
  					ls_sql2.append(" ELSE 0 ");
  					ls_sql2.append(" END)+(CASE WHEN cantacadem>0 THEN 1 ");
  					ls_sql2.append(" ELSE 0 ");
  					ls_sql2.append(" END)+(CASE WHEN cantform >0 THEN 1 ");
  					ls_sql2.append(" ELSE 0 ");
  					ls_sql2.append(" END)+(CASE WHEN cantidioma >0 THEN 1 ");
  					ls_sql2.append(" ELSE 0 ");
  					ls_sql2.append(" END)+(CASE WHEN cantarea >0 THEN 1 ");
  					ls_sql2.append(" ELSE 0 ");
  					ls_sql2.append(" END)+(CASE WHEN cantproy >0 THEN 1 ");
  					ls_sql2.append(" ELSE 0 ");
  					ls_sql2.append(" END)+(CASE WHEN cantpub >0 THEN 1 ");
  					ls_sql2.append(" ELSE 0 ");
  					ls_sql2.append(" END)+(CASE WHEN cantdist >0 THEN 1 ");
  					ls_sql2.append(" ELSE 0  ");
  					ls_sql2.append(" END) ");
  					ls_sql2.append(" as contlab ");
  					ls_sql2.append(" from (select count(*) as cantlaboral from directorio_cti.tbl_investigador inv  ");
  					ls_sql2.append(" join  directorio_cti.tbl_datos_laborales dl on inv.id_investigador=dl.id_investigador ");
  					ls_sql2.append(" where inv.id_investigador="+archivoenlista.getId_inv()+") lab, (select count(*) as cantacadem from directorio_cti.tbl_investigador inv "); 
  					ls_sql2.append(" join  directorio_cti.tbl_formacion_academica da on inv.id_investigador=da.id_investigador ");
  					ls_sql2.append(" where inv.id_investigador="+archivoenlista.getId_inv()+") gra, (select count(*) as cantform from directorio_cti.tbl_investigador inv "); 
  					ls_sql2.append(" join  directorio_cti.tbl_formacion_continua fc on inv.id_investigador=fc.id_investigador ");
  					ls_sql2.append(" where inv.id_investigador="+archivoenlista.getId_inv()+") form, (select count(*) as cantidioma from directorio_cti.tbl_investigador inv "); 
  					ls_sql2.append(" join  directorio_cti.tbl_conocimiento_idioma ci on inv.id_investigador=ci.id_investigador ");
  					ls_sql2.append(" where inv.id_investigador="+archivoenlista.getId_inv()+")idio,( select count(*) as cantarea from directorio_cti.tbl_investigador inv "); 
  					ls_sql2.append(" join  directorio_cti.tbl_areas_ocde_investigador ca on inv.id_investigador=ca.id_investigador ");
  					ls_sql2.append(" where inv.id_investigador="+archivoenlista.getId_inv()+") area, (select count(*) as cantproy from directorio_cti.tbl_investigador inv "); 
  					ls_sql2.append(" join  directorio_cti.tbl_proyecto pr on inv.id_investigador=pr.id_investigador   ");
  					ls_sql2.append(" where inv.id_investigador="+archivoenlista.getId_inv()+" and pr.id_institucion_laboral<>0) proy,  ");
  					ls_sql2.append(" (select count(*) as cantpub from directorio_cti.tbl_investigador inv  ");
  					ls_sql2.append(" join  directorio_cti.tbl_produccion_bibliografica pb on inv.id_investigador=pb.id_investigador ");
  					ls_sql2.append(" where inv.id_investigador="+archivoenlista.getId_inv()+") prod, (select count(*) as cantdist from directorio_cti.tbl_investigador inv "); 
  					ls_sql2.append(" join  directorio_cti.tbl_distinciones_premios dp on inv.id_investigador=dp.id_investigador ");
  					ls_sql2.append(" where inv.id_investigador="+archivoenlista.getId_inv()+") prem");
  					
  					pstmt = conn.prepareStatement(ls_sql2.toString());
  					rst = pstmt.executeQuery();	
  					while(rst.next()){
  						resultado=rst.getInt("contlab");
  					}
  					
  					archivoenlista.setPorcentaje((resultado>0)? (resultado/8)*100 : 0);
  					
  				}
			}
            obt.cerrar_conexion(conn,rst);		
            pstmt.close();
            log.info("Generando la lista");
            
            Collections.sort(myLista, new Comparator(){
            	 public int compare(Object o1, Object o2) {  
            		 BInfoInv e1 = (BInfoInv) o1;  
            		 BInfoInv e2 = (BInfoInv) o2;  
                     double codigo1 = e1.getPorcentaje();  
                     double codigo2 = e2.getPorcentaje();
       
                     if (codigo1 < codigo2) {  
                         return 1;  
                     } else if (codigo1 < codigo2) {  
                         return -1;  
                     } else {  
                         return 0;  
                     }  
                 } 
            });
        */    
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	
	return myLista;
}

public String of_nombre_archivo_sello_oficina(String codigo_oficina) throws Exception {
PreparedStatement pstmt = null;
String nombre_sello="";
ResultSet rst = null;

Connection conn = null;
ObtieneConexion obt=new ObtieneConexion();

try {	
	
 String ls_sql = "select nombre_sello  from tramite.oficinas where codigo_oficina ="+codigo_oficina;
	
	
	conn = obt.getConnection();
	pstmt = conn.prepareStatement(ls_sql);
	rst = pstmt.executeQuery();	
	log.info("sql :"+ls_sql);
	if(rst.next()){
		nombre_sello= rst.getString("nombre_sello");
	}
    obt.cerrar_conexion(conn,rst);		
    
} catch (SQLException sqle) {
	obt.cerrar_conexion(conn);
	sqle.printStackTrace();
}
return nombre_sello;
}

public void delete_of_nombre_archivo_sello_oficina(String codigo_oficina) throws Exception {
	PreparedStatement pstmt = null;
	ResultSet rst = null;

	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();

	try {	
		
	 String ls_sql = "update tramite.oficinas set nombre_sello = null where codigo_oficina ="+codigo_oficina;

		conn = obt.getConnection();
		pstmt = conn.prepareStatement(ls_sql);
		pstmt.executeUpdate();	
		log.info("sql :"+ls_sql);
		
	    obt.cerrar_conexion(conn,rst);		
	    
	} catch (SQLException sqle) {
		obt.cerrar_conexion(conn);
		sqle.printStackTrace();
	}
	}

public ArrayList<BInfoDocumentoProyecto> lista_derivacion_documentos_buscar(int oficina,String codigo_documento) throws Exception {
	
    log.info("ENTRO A  lista_derivacion_documentos_buscar");
    ArrayList<BInfoDocumentoProyecto> myLista = new ArrayList<BInfoDocumentoProyecto>();

	PreparedStatement us  = null;
	ResultSet rs = null;
	Connection cnx = null;
	ObtieneConexion obt=new ObtieneConexion();

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
    				"de.destino_documento=of_a.codigo_oficina and md.estado_movimiento = '3'" +
    				" and md.codigo_fondo like '01' and md.codigo_oficina like " + "'" + oficina + "'");

    				if(codigo_documento != null && !codigo_documento.equals("")){
   		    		 ls_sql.append(" and md.codigo_documento like '"+ codigo_documento +"' ");

   					}
    				
    	ls_sql.append(" and md.codigo_documento not in (select codigo_documento from " +
    				"tramite.movimientos_doc where estado_movimiento = '3' and" +
    				" codigo_fondo like '01' and codigo_oficina like " + "'" + oficina + 
    				"'" + " and flag ='I') order by md.codigo_documento desc limit 30 ");
    		 
    	String query = ls_sql.toString();
		
		log.info("Si el ls_sql Busca Documento General..: " + ls_sql);

		cnx = obt.getConnection();
		us = cnx.prepareStatement(query);
		rs = us.executeQuery();
		
		 String ls_ant_codigo_recepcion="";
    	 String ls_ant_codigo_documento="";
    	 String ls_ant_codigo_expediente="";
    	 String ls_ant_secuencia_movimiento="";
    	 String ls_naturaleza_documento="";
    	 
    	 String ls_codigo_recepcion="";
    	 String ls_codigo_documento="";
    	 String ls_codigo_documento_cont="";
    	 String ls_codigo_expediente="";
    	 String ls_codigo_expediente_cont="";
    	 String ls_secuencia_movimiento="";
    	 
    	 int contador=0;

    	 
		while ( rs.next() ) { 
			contador=contador+1;
		    BInfoDocumentoProyecto resultado= new BInfoDocumentoProyecto();
	
				ls_codigo_recepcion=rs.getString("codigo_recepcion").trim();
				ls_codigo_documento=rs.getString("codigo_documento").trim();
				ls_codigo_expediente=rs.getString("codigo_expediente").trim();
				ls_secuencia_movimiento=rs.getString("secuencia_movimiento").trim();
			
			if(contador==1){

			resultado.setCodigo_documento(rs.getString("codigo_documento").trim());
			resultado.setCodigo_expediente(rs.getString("codigo_expediente").trim());
			resultado.setCodigo_recepcion(rs.getString("codigo_recepcion").trim());
			resultado.setSecuencia_movimiento(rs.getString("secuencia_movimiento").trim());
			resultado.setOficina_origen(rs.getString("oficina_origen").trim());
			
			myLista.add(resultado);
			
			ls_ant_codigo_recepcion=ls_codigo_recepcion;
			ls_ant_codigo_documento=ls_codigo_documento;
			ls_ant_codigo_expediente=ls_codigo_expediente;
			ls_ant_secuencia_movimiento=ls_secuencia_movimiento;

			}
			else if(contador>1){
					
					if(ls_ant_codigo_documento.equals(ls_codigo_documento)){
						
					}else {
						
						resultado.setCodigo_documento(rs.getString("codigo_documento").trim());
						resultado.setCodigo_expediente(rs.getString("codigo_expediente").trim());
						resultado.setCodigo_recepcion(rs.getString("codigo_recepcion").trim());
						resultado.setSecuencia_movimiento(rs.getString("secuencia_movimiento").trim());
						resultado.setOficina_origen(rs.getString("oficina_origen").trim());
						
						myLista.add(resultado);
							
					}
					ls_ant_codigo_recepcion=ls_codigo_recepcion;
					ls_ant_codigo_documento=ls_codigo_documento;
					ls_ant_codigo_expediente=ls_codigo_expediente;
					ls_ant_secuencia_movimiento=ls_secuencia_movimiento;
	
					}
		}	
        
		obt.cerrar_conexion(cnx,rs);
    	
        
    } catch (SQLException e) {
    	obt.cerrar_conexion(cnx,rs);
        e.printStackTrace();
    }
    return myLista;
                
}

@Override
public void updateDocumentoInternoObservado(DocumentoInternoBean documentoInterno, String es_observado)
		throws Exception {
	
	log.info("Entrando a updateDocumentoInternoObservado...");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ObtieneConexion obt=new ObtieneConexion();
	log.info("codigo_documento_interno:" +documentoInterno.getCodigo_tipo_documento_interno());
	log.info("Asunto():" +documentoInterno.getAsunto());
	log.info("Codigo estado doc >>>>"+ documentoInterno.getCodigo_estado_documento());
	Calendar c = new GregorianCalendar(); 
	int valor=0;
	int tipo = documentoInterno.getCodigo_tipo_documento_interno();
	
	if(documentoInterno.isChecked()){
		valor=1;
	}
	
	try {	
 	
		if(tipo==6){
			//ccp
			StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
			//uptateQuery.append(" SET codigo_tipo_documento_interno=?,"); 
			uptateQuery.append(" SET asunto=?, descripcion=?, dirigido_a=?, firmado_por=?,"); 
			uptateQuery.append("fecha_modificacion=?, codigo_estado_documento=?,"); 
			uptateQuery.append("codigo_oficina=?, codigo_oficina_pertenece=?,");
			
			/*if(documentoInterno.getCodigo_estado_documento()==3){
				uptateQuery.append("numero_doc=?,");
			}else{
				uptateQuery.append("numero_doc_temporal=? ,");
			}*/
			uptateQuery.append("numero_doc=?,");
			uptateQuery.append("anio_doc=?,"); 
			uptateQuery.append("referencia=?, nombre_doc_adjuntos=?,codigos_oficinas_destinos_copias=?,personas=?,abreviatura_grado_profesion=?,flag=?,");
			uptateQuery.append(" motivo=?, actividad=?, meta=?, especifica=?, valor=?, observacion=?, nota=?, numerofilas=?, nombre_archivo=?");
			uptateQuery.append(" WHERE codigo_documento_interno=?");

			
			conn = obt.getConnection();
			log.info("query de actualizacion:   " +uptateQuery.toString());
			pstmt = conn.prepareStatement(uptateQuery.toString());
			//pstmt.setInt(1, documentoInterno.getCodigo_tipo_documento_interno());
			pstmt.setString(1, documentoInterno.getAsunto());
			pstmt.setString(2, documentoInterno.getDescripcion());
			pstmt.setString(3, documentoInterno.getDirigido_a());
			pstmt.setString(4, documentoInterno.getFirmado_por());		
			pstmt.setTimestamp(5, new  Timestamp(System.currentTimeMillis()));			
			
			pstmt.setInt(6,documentoInterno.getCodigo_estado_documento());
			pstmt.setInt(7,documentoInterno.getCodigo_oficina());
			pstmt.setInt(8,documentoInterno.getCodigo_oficina_pertenece());
			
			
			pstmt.setInt(9,documentoInterno.getNumero_doc());
			pstmt.setInt(10,c.get(Calendar.YEAR));
			pstmt.setString(11, documentoInterno.getReferencia());
			pstmt.setString(12, documentoInterno.getNombre_doc_adjuntos());
			pstmt.setString(13, documentoInterno.getCodigos_oficinas_destinos_copias());
			pstmt.setInt(14, documentoInterno.getPersonas());
			pstmt.setString(15, documentoInterno.getAbreviatura_grado_profesion());
			pstmt.setInt(16, valor);
			pstmt.setString(17, documentoInterno.getMotivo());
			pstmt.setString(18, documentoInterno.getActividad());
			pstmt.setString(19, documentoInterno.getMeta());
			pstmt.setString(20, documentoInterno.getEspecifica());
			pstmt.setString(21, documentoInterno.getValor());
			pstmt.setString(22, documentoInterno.getObservacion());
			pstmt.setString(23, documentoInterno.getNota());
			pstmt.setInt(24, documentoInterno.getNumerofilas());
			pstmt.setString(25, documentoInterno.getNombre_arhivo());
			pstmt.setInt(26, documentoInterno.getCodigo_documento_interno());
			
		}else{
			if(tipo==7){
			
			//tdr
			
			StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
			//uptateQuery.append(" SET codigo_tipo_documento_interno=?,"); 
			uptateQuery.append(" SET asunto=?, descripcion=?, dirigido_a=?, firmado_por=?,"); 
			uptateQuery.append("fecha_modificacion=?, codigo_estado_documento=?,"); 
			uptateQuery.append("codigo_oficina=?, codigo_oficina_pertenece=?,");
			
			/*if(documentoInterno.getCodigo_estado_documento()==3){
				uptateQuery.append("numero_doc=?,");
			}else{
				uptateQuery.append("numero_doc_temporal=? ,");
			}*/
			uptateQuery.append("numero_doc=?,");				
			uptateQuery.append("anio_doc=?,"); 
			uptateQuery.append("referencia=?, nombre_doc_adjuntos=?,codigos_oficinas_destinos_copias=?,personas=?,abreviatura_grado_profesion=?,flag=?,");
			uptateQuery.append("objetivo=?, justificacion=?, finalidad=?, actividad=?, entregable=?, plazo=?, valor=?,");
			uptateQuery.append("financiamiento=?, modalidad=?, responsable_coordinacion=?, responsable_conformidad=?, nombre_archivo=?");
			uptateQuery.append(" WHERE codigo_documento_interno=?");

			
			conn = obt.getConnection();
			log.info("query de actualizacion:   " +uptateQuery.toString());
			pstmt = conn.prepareStatement(uptateQuery.toString());
			//pstmt.setInt(1, documentoInterno.getCodigo_tipo_documento_interno());
			pstmt.setString(1, documentoInterno.getAsunto());
			pstmt.setString(2, documentoInterno.getDescripcion());
			pstmt.setString(3, documentoInterno.getDirigido_a());
			pstmt.setString(4, documentoInterno.getFirmado_por());		
			pstmt.setTimestamp(5, new  Timestamp(System.currentTimeMillis()));			
			
			pstmt.setInt(6,documentoInterno.getCodigo_estado_documento());
			pstmt.setInt(7,documentoInterno.getCodigo_oficina());
			pstmt.setInt(8,documentoInterno.getCodigo_oficina_pertenece());
			
			
			pstmt.setInt(9,documentoInterno.getNumero_doc());
			pstmt.setInt(10,c.get(Calendar.YEAR));
			pstmt.setString(11, documentoInterno.getReferencia());
			pstmt.setString(12, documentoInterno.getNombre_doc_adjuntos());
			pstmt.setString(13, documentoInterno.getCodigos_oficinas_destinos_copias());
			pstmt.setInt(14, documentoInterno.getPersonas());
			pstmt.setString(15, documentoInterno.getAbreviatura_grado_profesion());
			pstmt.setInt(16, valor);
			
			pstmt.setString(17, documentoInterno.getObjetivo());
			pstmt.setString(18, documentoInterno.getJustificacion());
			pstmt.setString(19, documentoInterno.getFinalidad());
			pstmt.setString(20, documentoInterno.getActividad());
			pstmt.setString(21, documentoInterno.getEntregable());
			pstmt.setString(22, documentoInterno.getPlazo());
			pstmt.setString(23, documentoInterno.getValor());
			pstmt.setString(24, documentoInterno.getFinanciamiento());
			pstmt.setString(25, documentoInterno.getModalidad());
			pstmt.setString(26, documentoInterno.getResponsable_coordinacion());
			pstmt.setString(27, documentoInterno.getResponsable_conformidad());
			pstmt.setString(28, documentoInterno.getNombre_arhivo());
			
			pstmt.setInt(29, documentoInterno.getCodigo_documento_interno());
			
			
			}else{
				StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
				//uptateQuery.append(" SET codigo_tipo_documento_interno=?,"); 
				uptateQuery.append(" SET asunto=?, descripcion=?, dirigido_a=?, firmado_por=?,"); 
				uptateQuery.append("fecha_modificacion=?, codigo_estado_documento=?,"); 
				//uptateQuery.append("codigo_oficina=?, codigo_oficina_pertenece=?,");
				
				/*if(documentoInterno.getCodigo_estado_documento()==3){
					uptateQuery.append("numero_doc=?,");
				}else{
					uptateQuery.append("numero_doc_temporal=? ,");
				}*/
				//uptateQuery.append("numero_doc=?,");
				//uptateQuery.append("anio_doc=?,"); 
				uptateQuery.append("referencia=?, ");
				//uptateQuery.append("nombre_doc_adjuntos=?,codigos_oficinas_destinos_copias=?,");
				uptateQuery.append("personas=?,abreviatura_grado_profesion=?");
				//uptateQuery.append(",flag=?, nombre_archivo=?");

				uptateQuery.append(" WHERE codigo_documento_interno=?");

				
				conn = obt.getConnection();
				log.info("query de actualizacion:   " +uptateQuery.toString());
				pstmt = conn.prepareStatement(uptateQuery.toString());
				//pstmt.setInt(1, documentoInterno.getCodigo_tipo_documento_interno());
				pstmt.setString(1, documentoInterno.getAsunto());
				pstmt.setString(2, documentoInterno.getDescripcion());
				pstmt.setString(3, documentoInterno.getDirigido_a());
				pstmt.setString(4, documentoInterno.getFirmado_por());		
				pstmt.setTimestamp(5, new  Timestamp(System.currentTimeMillis()));			
				
				if(documentoInterno.getCodigo_estado_documento()==1){
					pstmt.setInt(6,9);
				}else if(documentoInterno.getCodigo_estado_documento()==5){
					pstmt.setInt(6,10);
				}
				
				
				/*pstmt.setInt(7,documentoInterno.getCodigo_oficina());
				pstmt.setInt(8,documentoInterno.getCodigo_oficina_pertenece());
				
				
				pstmt.setInt(9,documentoInterno.getNumero_doc());
				pstmt.setInt(10,c.get(Calendar.YEAR));*/
				pstmt.setString(7, documentoInterno.getReferencia());
				/*pstmt.setString(12, documentoInterno.getNombre_doc_adjuntos());
				pstmt.setString(13, documentoInterno.getCodigos_oficinas_destinos_copias());*/
				pstmt.setInt(8, documentoInterno.getPersonas());
				pstmt.setString(9, documentoInterno.getAbreviatura_grado_profesion());
				/*pstmt.setInt(16, valor);
				pstmt.setString(17, documentoInterno.getNombre_arhivo());*/
				pstmt.setInt(10, documentoInterno.getCodigo_documento_interno());
			}
		}
		
		pstmt.executeUpdate();
        obt.cerrar_conexion(conn);		
        
    } catch (SQLException sqle) {
    	obt.cerrar_conexion(conn);
		sqle.printStackTrace();
    }
    
	
	
}


public BInfoDocumento getDatosAdicionales(String codigo_documento, String secuencia) throws Exception {    	
	log.info("Entrando a getDatosAdicionales...");
	PreparedStatement pstmt = null;
	ResultSet rst = null;
	BInfoDocumento BInfoDocumentoVO = new BInfoDocumento();
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	
	 try {	
 		
		 String ls_sql = "SELECT  codigo_motivo, observa_movimiento  FROM tramite.movimientos_doc  where codigo_documento=? and  secuencia_movimiento =?";

			log.info("Query -> "+ls_sql);
		 	conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql);
			pstmt.setString(1, codigo_documento);
			pstmt.setString(2, secuencia);

			rst = pstmt.executeQuery();	
			//log.info("sql :"+ls_sql);
			if(rst.next()){
				BInfoDocumentoVO.setCodigo_motivo(rst.getInt("codigo_motivo"));
				BInfoDocumentoVO.setObservacion(rst.getString("observa_movimiento"));
				
				//log.info("Codigo doc int encontrado >>>"+BInfoDocumentoVO.getCodigo_documento_interno());
    		}
            obt.cerrar_conexion(conn,rst);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	return BInfoDocumentoVO;

	}

	public void updateEstadoRegistroObservado(int codigo_documento_interno, int codigo_estado_documento)throws Exception{
	
		log.info("Entrando a updateEstadoRegistroObservado...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ObtieneConexion obt=new ObtieneConexion();
		int valor = 3;
		
	try{	
		StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
		uptateQuery.append(" SET codigo_estado_documento =3"); 
		uptateQuery.append(" WHERE codigo_documento_interno = ?"); 
	
		conn = obt.getConnection();
		log.info("query de actualizacion:   " +uptateQuery.toString());
		pstmt = conn.prepareStatement(uptateQuery.toString());
		
		pstmt.setInt(1, codigo_documento_interno);
		pstmt.executeUpdate();
		
	    obt.cerrar_conexion(conn);		
	    
	} catch (SQLException sqle) {
		obt.cerrar_conexion(conn);
		sqle.printStackTrace();
	}
	
}

public int getcodigo_movimiento_destino(int codigo_documento_interno) throws Exception{
			log.info("Entrando a getcodigo_movimiento_destino...");
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rst = null;
			ObtieneConexion obt=new ObtieneConexion();
			int valor = 0;
			
		try{	
			StringBuffer uptateQuery  = new StringBuffer("SELECT  secuencia_movimiento_destino  FROM tramite.documento_tipo_documento_interno where codigo_documento_interno =?");
			
		 	conn = obt.getConnection();
			pstmt = conn.prepareStatement(uptateQuery.toString());
			pstmt.setInt(1, codigo_documento_interno);

			rst = pstmt.executeQuery();	
			log.info("Query -> "+uptateQuery.toString());
			if(rst.next()){
				valor = rst.getInt("secuencia_movimiento_destino");
    		}
            obt.cerrar_conexion(conn,rst);		
		    
		} catch (SQLException sqle) {
			obt.cerrar_conexion(conn);
			sqle.printStackTrace();
		}
		return valor;
}

public int getcodigo_recepcion(int codigo_documento_interno, int secuencia_movimiento_destino) throws Exception{
	
	log.info("Entrando a getcodigo_recepcion...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		ObtieneConexion obt=new ObtieneConexion();
		int valor = 0;
		
	try{	
		StringBuffer uptateQuery  = new StringBuffer("select codigo_recepcion  from tramite.movimientos_doc ");
		uptateQuery.append(" where codigo_documento=?  and secuencia_movimiento=?");
		
	 	conn = obt.getConnection();
		pstmt = conn.prepareStatement(uptateQuery.toString());
		pstmt.setInt(1, codigo_documento_interno);
		pstmt.setInt(2, secuencia_movimiento_destino);
	
		rst = pstmt.executeQuery();	
		log.info("Query -> "+uptateQuery.toString());
		if(rst.next()){
			valor = rst.getInt("codigo_recepcion");
		}
	    obt.cerrar_conexion(conn,rst);		
	    
	} catch (SQLException sqle) {
		obt.cerrar_conexion(conn);
		sqle.printStackTrace();
	}
	return valor;

}
		
public String of_ingresar_doc_observardo(int codigo_documento_interno, int secuencia_movimiento, int secuencia_movimiento_destino, String observacion, String codigo_motivo, int codigo_recepcion_destino)throws Exception{
	log.info("dentro de of_ingresar_doc_observardo......."+codigo_documento_interno+","+secuencia_movimiento+","+secuencia_movimiento_destino+","+observacion+","+codigo_motivo+","+codigo_recepcion_destino);
	String codigo_fondo="01";
	Statement us  = null;
	ResultSet rs = null;
	
	String ls_sql = "";
	String ls_mensaje = "NO";
	String ls_estado_movimiento="2";
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	
	SimpleDateFormat fechaConsulta = new SimpleDateFormat ("yyyyMMdd");
	
	try{
		
			ls_sql = "UPDATE tramite.movimientos_doc SET estado_movimiento="+"'"+5+"', flag = ''"
			+ " where codigo_fondo="+ "'01'" +
			" and codigo_documento="+"'"+codigo_documento_interno+"'"
			+ " and secuencia_movimiento="+"'"+secuencia_movimiento+"'"+"";
			
			log.info(ls_sql);
		    	 us = con.createStatement();
		    	 us.executeUpdate(ls_sql);
		    	 
		   log.info("codigo_recepcion_destino -> "+codigo_recepcion_destino);
		   
		   //PODRIA PONERSE EL USUARIO QUE LO MODIFICO
		   
		   /// HAY QUE OBSERVAR SI EL ESTADO RECIBIR O TRAMITE - > LA SECUENCIA_MOVIMIENTO CAMBIA
		   ls_sql = "UPDATE tramite.tbl_det_upload set flag = 'I', codigo_recepcion ="+codigo_recepcion_destino+", "+
		   			" fecha_modificacion=" + "current_timestamp "+
		   			//" where  codigo_documento="+codigo_documento_interno+" and secuencia_movimiento="+secuencia_movimiento+" ";
		   			" where  codigo_documento="+codigo_documento_interno+" and secuencia_movimiento="+secuencia_movimiento_destino+" ";
		   
		   log.info(ls_sql);
		   us=con.createStatement();
		   us.executeUpdate(ls_sql);
		
		   /***
		    * NECESITO VERIFICAR SI DESTINO ESTABA EN RECIBIR O TRAMITE
		    */
		   int cantidad_movimientos=0;
		   
		   ls_sql = "SELECT count(codigo_documento) as cantidad_movimientos  from tramite.recorrido_doc  " +
		   		"where codigo_documento="+codigo_documento_interno+" and secuencia_movimiento="+secuencia_movimiento_destino;
		   
			
			us=con.createStatement();
			rs = us.executeQuery(ls_sql);
		   log.info("Cantidad de movimientos ls_sql -->"+ls_sql);
			while ( rs.next() ) { 
		           // //log.info("dentro del whyle......." );
		            if (rs.getString("cantidad_movimientos") == null )
		            {
		    			////log.info("ta vacio nulo ......." );
		            	cantidad_movimientos = 0;
		    	    }
		            else
		    		{
		            	////log.info("ls_valor :" + rs.getString("c_valor"));
		            	cantidad_movimientos = Integer.valueOf(rs.getString("cantidad_movimientos")).intValue();
		    		}

	        }
		
		log.info("------cantidad_movimientos "+cantidad_movimientos);	
		if(cantidad_movimientos==1){//ES POR RECIBIR
			ls_estado_movimiento = "2";
		}
		if(cantidad_movimientos==2){//ES EN TRAMITE
			ls_estado_movimiento = "3";
		}	
		   ls_sql = "UPDATE tramite.movimientos_doc SET estado_movimiento="+ls_estado_movimiento;
		
		   if(!observacion.equals("")){
			   ls_sql=ls_sql+" ,observa_movimiento='"+observacion+"' ";
		   }
		   if(!codigo_motivo.equals("")){
			   ls_sql=ls_sql+" ,codigo_motivo="+codigo_motivo+" ";
		   }
		   
		ls_sql=ls_sql+" where  codigo_documento="+codigo_documento_interno+" "
			+ " and secuencia_movimiento="+secuencia_movimiento_destino;	   
		   
		us = con.createStatement();
		us.executeUpdate(ls_sql);	

	 
	ls_mensaje="SI";
	log.info("FIN DE observa... ");
	
	x.cerrar_conexion(con);
					
	}	
	catch(Exception e){
		x.cerrar_conexion(con);
		log.info("error en el Servlet : " + e.getMessage() );	
	}
	return ls_mensaje;
}	

public ArrayList<DocumentoInternoBean> getListaProyectosRecibidosCodigo(String codigo_documento, String oficina, String secuencia, String oficina_origen)throws Exception{
	PreparedStatement pstmt = null;
	ResultSet rst = null;		
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	ArrayList<DocumentoInternoBean> myLista = new ArrayList<DocumentoInternoBean>();
	DocumentoInternoBean item;
	int tipo_documento_interno = 0;
	String link2="";
	String link ="";
	String verPDF="";
	
	 try {	
 		
				StringBuffer ls_sql2 = new StringBuffer("SELECT a.codigo_documento_interno, a.codigo_tipo_documento_interno, a.codigo_documento,a.asunto, a.descripcion, a.dirigido_a, firmado_por,");
				ls_sql2.append(" a.fecha_creacion, a.fecha_modificacion, a.codigo_estado_documento, a.codigo_oficina, a.codigo_oficina_pertenece, a.numero_doc, a.anio_doc,a.personas,a.nombre_archivo,");
				ls_sql2.append(" (select codigo_documento from tramite.documento_tipo_documento_interno	where codigo_documento_interno=b.codigo_documento_interno) as codigo_documento_origen,");
				ls_sql2.append(" b.codigo_documento_interno as cod_doc_int_origen "); 
				ls_sql2.append(" FROM tramite.documento_tipo_documento_interno a, tramite.tbl_cab_upinternos b	WHERE a.codigo_tipo_documento_interno IN (1,2,3,4,5,6,7,8) "); 
				ls_sql2.append(" AND a.codigo_documento_interno IN "); 
				ls_sql2.append(" (SELECT tcu.codigos_documento_interno_proyecto	FROM tramite.documento_tipo_documento_interno td, tramite.tbl_cab_upinternos tcu,"); 
				ls_sql2.append(" tramite.tbl_det_upinternos tdu  WHERE td.codigo_documento = ? 	AND td.codigo_estado_documento = 3  AND td.codigo_documento_interno = tcu.codigo_documento_interno "); 
				ls_sql2.append(" AND tdu.estado_usuario_origen like 'C'	AND tdu.estado_usuario_fin like 'A'  AND tcu.id_cab_upinternos = tdu.id_cab_upinternos "); 
				ls_sql2.append(" AND td.codigo_oficina = ?  AND td.codigo_oficina_pertenece=?  AND td.secuencia_movimiento_destino= ? )  AND a.codigo_documento_interno = b.codigos_documento_interno_proyecto	AND b.estado IN ('A','V')"); 


			 	conn = obt.getConnection();
				pstmt = conn.prepareStatement(ls_sql2.toString());
				pstmt.setString(1, codigo_documento);
				pstmt.setString(2, oficina);
				pstmt.setString(3, oficina_origen);
				pstmt.setString(4, secuencia);
				rst = pstmt.executeQuery();  		
				while(rst.next()){
					item = new DocumentoInternoBean();
					item.setCodigo_documento_interno(rst.getInt("codigo_documento_interno"));
					item.setCodigo_tipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
					item.setCodigo_documento(rst.getInt("codigo_documento_origen"));
					item.setAsunto(rst.getString("asunto"));
					item.setDirigido_a(rst.getString("dirigido_a"));
					item.setCodigo_estado_documento(rst.getInt("codigo_estado_documento"));
					item.setFecha_creacion(rst.getTimestamp("fecha_creacion"));
					item.setCodigo_oficina(rst.getInt("codigo_oficina"));
					//item.setCodigo_documento(rst.getInt("codigo_documento"));

					tipo_documento_interno = item.getCodigo_tipo_documento_interno();
					
					if(tipo_documento_interno == 6){
						link2="ModificarDocumentoInternoEspecialC.do?cod_doc_interno="+rst.getInt("cod_doc_int_origen")+"&cod_proy="+item.getCodigo_documento_interno()+"&numreg="+item.getCodigo_documento()+"&tipo_pi=2";
						item.setLink2("<a  href='"+link2+"' title='Modificar con el mismo Num. Registro' >"+item.getCodigo_documento()+"</a>");
						
						link="ModificarDocumentoInternoEspecialC.do?cod_doc_interno="+item.getCodigo_documento_interno()+"&tipo_pi=1";
						link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";
					}else{
						if(tipo_documento_interno == 7){
							link2="ModificarDocumentoInternoEspecialT.do?cod_doc_interno="+rst.getInt("cod_doc_int_origen")+"&cod_proy="+item.getCodigo_documento_interno()+"&numreg="+item.getCodigo_documento()+"&tipo_pi=2";
							item.setLink2("<a  href='"+link2+"' title='Modificar con el mismo Num. Registro'>"+item.getCodigo_documento()+"</a>");
							
							link="ModificarDocumentoInternoEspecialT.do?cod_doc_interno="+item.getCodigo_documento_interno()+"&tipo_pi=1";
							link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";
						}else{
							link2="ModificarDocumentoInterno.do?cod_doc_interno="+rst.getInt("cod_doc_int_origen")+"&cod_proy="+item.getCodigo_documento_interno()+"&numreg="+item.getCodigo_documento()+"&tipo_pi=2";
							item.setLink2("<a  href='"+link2+"' title='Modificar con el mismo Num. Registro'>"+item.getCodigo_documento()+"</a>");
							
							link="ModificarDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno()+"&tipo_pi=1";
							link="<a  href='"+link+"'>"+"<img src='img/modifica.gif' title='Modificar' width='20' height='20' border='0'> </a>";
						}	
					}
					
							
					verPDF="VerArchivoPDFDocumentoInterno.do?cod_doc_interno="+item.getCodigo_documento_interno();
					verPDF="<a  href='"+verPDF+"'>"+"<img src='img/pdf.gif' title='Ver documento PDF' width='20' height='20' border='0'/> </a>";
					
										
					String cerrarProyecto="<a  href='javascript:cerrardocument("+item.getCodigo_documento_interno()+")'>"+"<img src='img/delete.png' title='Cerrar Proyecto' width='20' height='20' border='0'> </a>";
										
					item.setNombre_arhivo(rst.getString("nombre_archivo"));
			
					item.setLink(link+" "+verPDF+" "+cerrarProyecto);

					myLista.add(item);
	    		}
					
				obt.cerrar_conexion(conn,rst);		
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	
	return myLista;
}

	public ArrayList<EstandarBean> getTipoRequerimientoInternoLista()
	throws Exception {
	ArrayList<EstandarBean> resultado= new ArrayList<EstandarBean>();
	Statement us  = null;
	ResultSet rst = null;		
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	EstandarBean item;
	
	try {	
		
		String ls_sql = "SELECT  codigo_tipo_requerimiento, nombre FROM tramite.tipo_requerimiento";
		
		conn = obt.getConnection();
		us = conn.createStatement();
		rst = us.executeQuery(ls_sql);    		
		log.info("sql :"+ls_sql);
		while(rst.next()){
			item= new EstandarBean();
			item.setIdBean(rst.getInt("codigo_tipo_requerimiento"));
			item.setNombreBean(rst.getString("nombre"));
			resultado.add(item);
		}
	    obt.cerrar_conexion(conn,rst);		
	    
	} catch (SQLException sqle) {
		obt.cerrar_conexion(conn);
		sqle.printStackTrace();
	}
	
	return resultado;
	}

	public String getCargo_of_CodigoCargo(String cargo_personal)throws Exception{
		String resultado= "";
		Statement us  = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		
		try {	
			
			String ls_sql = "SELECT  nombrecargo FROM tramite.cargo where codigo_cargo = '"+cargo_personal+"' ";
			
			conn = obt.getConnection();
			us = conn.createStatement();
			rst = us.executeQuery(ls_sql);    		
			log.info("sql :"+ls_sql);
			while(rst.next()){
				resultado = (rst.getString("nombrecargo"));
			}
		    obt.cerrar_conexion(conn,rst);		
		    
		} catch (SQLException sqle) {
			obt.cerrar_conexion(conn);
			sqle.printStackTrace();
		}
		
		return resultado;
	}
	
	public BInfo getInfo(String pcodigo_documento, String psecuencia_movimiento)throws Exception{
		BInfo binfo = new BInfo();
		PreparedStatement pstmt = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();
		int a =Integer.parseInt(pcodigo_documento);
		int b =Integer.parseInt(psecuencia_movimiento);
		try {	
			
			StringBuffer ls_sql =  new StringBuffer("select  doc_resp, (select nombre_archivo from tramite.tbl_det_upload  where codigo_documento = ?  and secuencia_movimiento = ?  and id_det_upload = (select  max(id_det_upload) from tramite.tbl_det_upload where codigo_documento = ?  and secuencia_movimiento = ? )) as nombre_archivo ");
			ls_sql.append("from tramite.movimientos_doc where codigo_documento = ? 	and secuencia_movimiento = ? ");
			
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			//log.info(a);
			//log.info(b);
			log.info("sql getInfo: "+ls_sql);
			pstmt.setInt(1, a);
			pstmt.setInt(2, b);
			pstmt.setInt(3, a);
			pstmt.setInt(4, b);
			pstmt.setInt(5, a);
			pstmt.setInt(6, b);
			rst = pstmt.executeQuery();	
			while(rst.next()){
				//System.out.println("dentro "+rst.getString("nombre_archivo"));
				binfo.setDoc_rpta(rst.getString("doc_resp"));
				binfo.setNombrearchivo(rst.getString("nombre_archivo"));
			}
		    obt.cerrar_conexion(conn,rst);	
		    pstmt.close();
		    
		} catch (SQLException sqle) {
			obt.cerrar_conexion(conn);
			sqle.printStackTrace();
		}
		
		return binfo;
	}
	
	public BInfo getInfoSobreDocumento(int codigo_documento_interno)throws Exception{
		BInfo binfo = new BInfo();
		PreparedStatement pstmt = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		try {	
			
			StringBuffer ls_sql =  new StringBuffer("select  nombre_archivo, codigo_tipo_documento_interno ");
			ls_sql.append("from tramite.documento_tipo_documento_interno where codigo_documento_interno = ? ");
			
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());

			log.info("sql getInfo: "+ls_sql);
			pstmt.setInt(1, codigo_documento_interno);
			rst = pstmt.executeQuery();	
			while(rst.next()){
				//System.out.println("dentro "+rst.getString("nombre_archivo"));
				binfo.setNombrearchivo(rst.getString("nombre_archivo"));
				binfo.setTipo_documento_interno(rst.getInt("codigo_tipo_documento_interno"));
			}
		    obt.cerrar_conexion(conn,rst);	
		    pstmt.close();
		    
		} catch (SQLException sqle) {
			obt.cerrar_conexion(conn);
			sqle.printStackTrace();
		}
		
		return binfo;
	}
	
	public BInfo getInfoSobreDocumentoFirmadoManualmente(int codigo_documento_interno)throws Exception{
		BInfo binfo = new BInfo();
		PreparedStatement pstmt = null;
		ResultSet rst = null;		
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		try {	
			
			StringBuffer ls_sql =  new StringBuffer("select id_det_upload,nombre_archivo  from tramite.tbl_det_upload  where id_codigo_documento_interno= ? ");
			ls_sql.append("  and flag = 'A';");
			
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			pstmt.setInt(1, codigo_documento_interno);
			
			log.info("sql getInfo: "+pstmt);
			rst = pstmt.executeQuery();	
			while(rst.next()){
				//System.out.println("dentro "+rst.getString("nombre_archivo"));
				binfo.setId_det_upload(rst.getInt("id_det_upload"));
				binfo.setNombrearchivo(rst.getString("nombre_archivo"));
			}
		    obt.cerrar_conexion(conn,rst);	
		    pstmt.close();
		    
		} catch (SQLException sqle) {
			obt.cerrar_conexion(conn);
			sqle.printStackTrace();
		}
		
		return binfo;
	}
	
	public void deleteFirmadoManual(int id_detalle_documento)throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = null;
		ObtieneConexion obt=new ObtieneConexion();

		try {	
			
			StringBuffer ls_sql =  new StringBuffer("delete from tramite.tbl_det_upload  where id_det_upload= ? ");
			
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			pstmt.setInt(1, id_detalle_documento);
			
			log.info("sql getInfo: "+pstmt);
			pstmt.executeUpdate();	
			
		    obt.cerrar_conexion(conn);	
		    pstmt.close();
		    
		} catch (SQLException sqle) {
			obt.cerrar_conexion(conn);
			sqle.printStackTrace();
		}
		
	}
	
	public void insertLogDocumentoInternoFirma(DocumentoInternoBean documentoenlista)throws Exception{
			
			log.info("Entrando a insertLogDocumentoInternoFirma...");
			Connection conn = null;
			PreparedStatement pstmt = null;
			ObtieneConexion obt=new ObtieneConexion();
			
			int codigo_documento=0;
			codigo_documento=(documentoenlista.getCodigo_documento()==null)? codigo_documento:documentoenlista.getCodigo_documento();

			try{	
				StringBuffer uptateQuery  = new StringBuffer("INSERT INTO tramite.tbl_log_documento_interno_firma(codigo_documento_interno, codigo_documento, firmado_por, codigo_oficina_pertenece, anio_doc) ");
				uptateQuery.append("  VALUES (?,?,?,?,?);"); 
			
				conn = obt.getConnection();
				pstmt = conn.prepareStatement(uptateQuery.toString());
	
				pstmt.setInt(1, documentoenlista.getCodigo_documento_interno());
				pstmt.setInt(2, codigo_documento);
				pstmt.setString(3, documentoenlista.getFirmado_por());
				pstmt.setInt(4, documentoenlista.getCodigo_oficina_pertenece());
				pstmt.setInt(5, documentoenlista.getAnio_doc());
				
				log.info("query de actualizacion:   " +pstmt);
				pstmt.executeUpdate();
				
			    obt.cerrar_conexion(conn);		
			    
			} catch (SQLException sqle) {
				obt.cerrar_conexion(conn);
				sqle.printStackTrace();
			}
			
		}
	

		public void updateEstadoDocumento(String cod_doc_interno, int codigo_estado_documento)throws Exception{
			log.info("Entrando a updateEstadoRegistro...");
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rst = null;
			ObtieneConexion obt=new ObtieneConexion();
			
			
		try{	
			StringBuffer uptateQuery  = new StringBuffer("UPDATE tramite.documento_tipo_documento_interno");
			uptateQuery.append(" SET  codigo_estado_documento = ? "); 
			uptateQuery.append("WHERE codigo_documento_interno = ?"); 

			conn = obt.getConnection();
			pstmt = conn.prepareStatement(uptateQuery.toString());
			
			pstmt.setInt(1, codigo_estado_documento);
			pstmt.setInt(2, Integer.parseInt(cod_doc_interno));
			
			log.info("query de actualizacion:   " +pstmt);
			pstmt.executeUpdate();
			
			/**
			 * TAMBIEN DEBO CAMBIAR DE ESTADO LOS REGISTROS EN TABLAS CABUPLOAD Y DETUPLOAD
			 */
			
			StringBuilder uptateQuery2 = new StringBuilder("UPDATE tramite.tbl_cab_upinternos "); 
			uptateQuery2.append(" set estado='E' ");
			uptateQuery2.append(" WHERE codigo_documento_interno =?  AND estado ='P' AND  ciclo=0 ");
			pstmt = conn.prepareStatement(uptateQuery2.toString());

			pstmt.setInt(1, Integer.parseInt(cod_doc_interno));
			
			log.info("query de actualizacion:   " +pstmt);
			pstmt.executeUpdate();
			
			
			StringBuilder uptateQuery3 = new StringBuilder("UPDATE tramite.tbl_cab_upinternos "); 
			uptateQuery3.append(" set  ciclo=0  ");
			uptateQuery3.append(" WHERE codigo_documento_interno =?  AND estado in ('A','V') ");
			pstmt = conn.prepareStatement(uptateQuery3.toString());

			pstmt.setInt(1, Integer.parseInt(cod_doc_interno));
			
			log.info("query de actualizacion:   " +pstmt);
			pstmt.executeUpdate();
			
			
			StringBuilder selectQuery = new StringBuilder("SELECT tdu.id_det_upinternos ");
			selectQuery.append(" FROM tramite.tbl_det_upinternos tdu "); 
			selectQuery.append(" left join  tramite.tbl_cab_upinternos tcu  on tdu.id_cab_upinternos=tcu.id_cab_upinternos "); 
			selectQuery.append(" WHERE tcu.codigo_documento_interno=?  and tdu.codigo_oficina<>0 "); 
			selectQuery.append(" and tcu.estado in ('V','A');");
			pstmt = conn.prepareStatement(selectQuery.toString());

			pstmt.setInt(1, Integer.parseInt(cod_doc_interno));
			
			rst=pstmt.executeQuery();
			while(rst.next()){
				StringBuilder uptateQuery4 = new StringBuilder("update tramite.tbl_det_upinternos  set  estado_usuario_origen='C', estado_usuario_fin='E' ");
				uptateQuery4.append(" where id_det_upinternos=? ");
				pstmt = conn.prepareStatement(uptateQuery4.toString());

				pstmt.setInt(1, rst.getInt("id_det_upinternos"));
				log.info("query de actualizacion:   " +pstmt);
				pstmt.executeUpdate();
				
			}

	        obt.cerrar_conexion(conn,rst);		
	        pstmt.close();
	        
	    } catch (SQLException sqle) {
	    	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
	    }
			
		}
		
		public void updateEstadoDocumentoIndividual(String cod_doc_interno, String estado_input, int codigo_oficina, String observacion, String tipo_adjunto)throws Exception{
			log.info("Entrando a updateEstadoDocumentoIndividual...");
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rst = null;
			ObtieneConexion obt=new ObtieneConexion();
			
			
		try{	
			conn = obt.getConnection();
			
			StringBuilder selectQuery = new StringBuilder("select du.id_det_upinternos from tramite.tbl_cab_upinternos cu ");
			selectQuery.append(" left join  tramite.tbl_det_upinternos du on cu.id_cab_upinternos=du.id_cab_upinternos ");
			
			if(tipo_adjunto.equals("P")){
				selectQuery.append(" where  cu.estado='P' ");
			}else{
				selectQuery.append(" where  cu.estado in ('A','V') ");
			}
			selectQuery.append(" and cu.codigo_documento_interno=?  and du.codigo_oficina=? ");
			
			pstmt = conn.prepareStatement(selectQuery.toString());
			pstmt.setInt(1, Integer.parseInt(cod_doc_interno));
			pstmt.setInt(2, codigo_oficina);
			log.info("sql :"+pstmt);
			rst = pstmt.executeQuery();  		
			
    		while(rst.next()){
    			StringBuilder uptateQuery = new StringBuilder("update tramite.tbl_det_upinternos  set  estado_usuario_fin=?, ");
    			
    			if(estado_input.equals("O")){
    				uptateQuery.append(" observacion=?  ");
    			}else{
    				uptateQuery.append(" firmado=?  ");
    			}
    			uptateQuery.append(" where id_det_upinternos= ?");
    			pstmt = conn.prepareStatement(uptateQuery.toString());
    			
    			pstmt.setString(1, estado_input);
    			if(estado_input.equals("O")){
    				pstmt.setString(2, observacion);
    			}else{
    				pstmt.setInt(2, 1);
    			}
    			
    			pstmt.setInt(3, rst.getInt("id_det_upinternos"));
    			log.info("query de actualizacion:   " +pstmt);
    			pstmt.executeUpdate();
    		}

	        obt.cerrar_conexion(conn,rst);		
	        pstmt.close();
	        
	    } catch (SQLException sqle) {
	    	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
	    }
			
		}
		
		public ArrayList<BArchivo> of_mostrar_estados_documento(int id_cab_upinternos,String tipo_lista) throws Exception{
			ArrayList<BArchivo> resultado= new ArrayList<BArchivo>();
			PreparedStatement pstmt = null;
			ResultSet rst = null;		
			Connection conn = null;
			ObtieneConexion obt=new ObtieneConexion();
			BArchivo item;
			int valor=0;
			log.info("Entre of_mostrar_estados_documento");
			 try {	
	     		
					StringBuilder ls_sql = new StringBuilder("SELECT tdu.*,ofi.siglas_oficina  FROM tramite.tbl_det_upinternos tdu ");  
					ls_sql.append(" left join  tramite.tbl_cab_upinternos tcu  on tdu.id_cab_upinternos=tcu.id_cab_upinternos "); 
					ls_sql.append(" left join tramite.oficinas ofi on tdu.codigo_oficina=ofi.codigo_oficina ");
					ls_sql.append(" WHERE tdu.id_cab_upinternos=?  and tdu.codigo_oficina<>0 ");
					
					if(tipo_lista.equals("P")){
						ls_sql.append(" and tcu.estado in ('P');");
					}else{
						ls_sql.append(" and tcu.estado in ('V','A')  and tdu.estado_usuario_fin in ('C','A');");
					}
					
					conn = obt.getConnection();
					pstmt = conn.prepareStatement(ls_sql.toString());
					pstmt.setInt(1, id_cab_upinternos);
					
					log.info("sql :"+pstmt);
					rst = pstmt.executeQuery();  		
					
		    		while(rst.next()){
		    			item= new BArchivo();
		    			item.setId_archivo(rst.getInt("id_cab_upinternos"));
		    			//item.setEstado(rst.getString("estado"));
		    			item.setNombre_archivo(rst.getString("siglas_oficina"));
		    			//item.setNombre_archivo_cifrado(rst.getString("nombre_archivo_cifrado"));
		    			item.setId_detalle_archivo(rst.getInt("id_det_upinternos"));
		    			item.setUsuario(rst.getString("usuario"));
		    			item.setRuta(rst.getString("ruta"));
		    			
		    			valor = rst.getInt("visado");
		    			if(valor == 1){
		    				item.setIsvisado(true);
		    				item.setIschecked(true);
		    			}else{
		    				item.setIsvisado(false);
		    				item.setIschecked(false);
		    			}
		    			
		    			valor = rst.getInt("firmado");
		    			if(valor == 1){
		    				item.setIsfirmado(true);
		    			}else{
		    				item.setIsfirmado(false);
		    			}
		    			
		    			//item.setId_archivo_proyecto(rst.getInt("codigos_documento_interno_proyecto"));
		    			item.setEstado_usuario_origen(rst.getString("estado_usuario_origen"));
		    			item.setEstado_usuario_fin(rst.getString("estado_usuario_fin"));
		    			
		    			resultado.add(item);
		    		}
		            obt.cerrar_conexion(conn,rst);	
		            pstmt.close();
		            
		        } catch (SQLException sqle) {
		        	obt.cerrar_conexion(conn);
					sqle.printStackTrace();
		        }
			
			return resultado;
		}
		
		public void actualizaCiclo(int codigo_documento_interno,String estado) throws Exception{
			log.info("Entrando a actualizaCiclo...");
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rst = null;
			ObtieneConexion obt=new ObtieneConexion();
			boolean todosFirmados = true;
			int id_cab_upinternos=0;
			
		try{	
			
			StringBuilder selectQuery = new StringBuilder("SELECT tdu.id_det_upinternos,tdu.id_cab_upinternos,tdu.firmado ");
			selectQuery.append(" FROM tramite.tbl_cab_upinternos tcu "); 
			selectQuery.append(" left join  tramite.tbl_det_upinternos tdu  on tdu.id_cab_upinternos=tcu.id_cab_upinternos "); 

			selectQuery.append(" WHERE tcu.codigo_documento_interno=? ");  
			
			if(estado.equals("P")){
				selectQuery.append(" and tcu.estado in ('P') ");
			}else{
				selectQuery.append(" and tcu.estado in ('V','A') ");
			}
			selectQuery.append(" and tdu.codigo_oficina<>0  order by tdu.id_cab_upinternos asc"); 
			
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(selectQuery.toString());

			pstmt.setInt(1, codigo_documento_interno);
			log.info("query de actualizacion:   " +pstmt);
			rst=pstmt.executeQuery();
			while(rst.next()){
				if(rst.getInt("firmado")==0){
					todosFirmados = false;
				}
			}
			
			if(todosFirmados){
				rst=pstmt.executeQuery();
				while(rst.next()){
					
					if(id_cab_upinternos != rst.getInt("id_cab_upinternos")){
							id_cab_upinternos = rst.getInt("id_cab_upinternos");
							StringBuilder uptateQuery3 = new StringBuilder("UPDATE tramite.tbl_cab_upinternos "); 
							uptateQuery3.append(" set  ciclo=1  ");
							uptateQuery3.append(" WHERE id_cab_upinternos =? ");
							pstmt = conn.prepareStatement(uptateQuery3.toString());
		
							pstmt.setInt(1, id_cab_upinternos);
							
							log.info("query de actualizacion:   " +pstmt);
							pstmt.executeUpdate();
					
					}
				}
				
			}
			
			
	        obt.cerrar_conexion(conn,rst);		
	        pstmt.close();
	        
	    } catch (SQLException sqle) {
	    	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
	    }
			
		}
		
		public int getInstitucion(int codigo_oficina) throws Exception {
			
			log.info("Entre -getInstitucion");
			PreparedStatement pstmt = null;
			int numSiguiente=0;
			ResultSet rst = null;
			
			Connection conn = null;
			ObtieneConexion obt=new ObtieneConexion();

			 try {	
	     		
				 StringBuffer ls_sql = new StringBuffer("SELECT institucion   FROM tramite.oficinas WHERE codigo_oficina=? ");//AND anio_doc=2012
					
					
					conn = obt.getConnection();
					pstmt = conn.prepareStatement(ls_sql.toString());
					pstmt.setInt(1,codigo_oficina);
					rst = pstmt.executeQuery();	
					log.info("sql :"+ls_sql);
					if(rst.next()){
						numSiguiente= rst.getInt("institucion");
		    		}
		            obt.cerrar_conexion(conn,rst);		
		            
		        } catch (SQLException sqle) {
		        	obt.cerrar_conexion(conn);
					sqle.printStackTrace();
		        }
		       
			return numSiguiente;
		}
		
public String getAccesosaOficinas(String codigo_personal) throws Exception {
			
			log.info("Entre -getAccesosaOficinas");
			PreparedStatement pstmt = null;
			String resultado=null;
			ResultSet rst = null;
			
			ObtieneConexion obt=new ObtieneConexion();
			Connection conn = obt.getConnection();
			

			 try {	
	     		
				 StringBuffer ls_sql = new StringBuffer("SELECT acceso_oficinas  FROM tramite.personal WHERE codigo_personal=? ");//AND anio_doc=2012
					
					
					
					pstmt = conn.prepareStatement(ls_sql.toString());
					pstmt.setString(1,codigo_personal);
					rst = pstmt.executeQuery();	
					log.info("sql :"+pstmt);
					if(rst.next()){
						resultado = rst.getString("acceso_oficinas");
		    		}
		            obt.cerrar_conexion(conn,rst);		
		            
		        } catch (SQLException sqle) {
		        	obt.cerrar_conexion(conn);
					sqle.printStackTrace();
		        }
		       
			return resultado;
		}

public String getAccesosaPersonas(String tempaccesosoficina, boolean ve_todos_usuarios, String usuario) throws Exception {
	
	log.info("Entre -getAccesosaPersonas");
	PreparedStatement pstmt = null;
	String resultado="";
	ResultSet rst = null;
	
	ObtieneConexion obt=new ObtieneConexion();
	Connection conn = obt.getConnection();
	

	 try {	
 		
		 StringBuffer ls_sql = new StringBuffer("select tp.codigo_personal from tramite.personal tp, tramite.usuarios tu where tp.codigo_oficina in ("+tempaccesosoficina+")  and tu.estado LIKE 'A' and tp.c_usuario = tu.usuario  ");//AND anio_doc=2012
		 if (!ve_todos_usuarios){
			 ls_sql.append(" and  tp.codigo_personal = "+usuario+" ");
		 }
			
			
			pstmt = conn.prepareStatement(ls_sql.toString());
			//pstmt.setString(1,codigo_personal);
			rst = pstmt.executeQuery();	
			log.info("sql :"+pstmt);
			while(rst.next()){
				resultado = rst.getString("codigo_personal")+","+resultado;
    		}
            obt.cerrar_conexion(conn,rst);		
            log.info("resultado ->"+resultado);
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
       
	return resultado;
}

public String getCodigosPersonalesResponsables(String accesoaoficinas) throws Exception {
	
	log.info("Entre -getCodigosPersonalesResponsables");
	PreparedStatement pstmt = null;
	String resultado="";
	ResultSet rst = null;
	
	ObtieneConexion obt=new ObtieneConexion();
	Connection conn = obt.getConnection();
	

	 try {	
 		
		 StringBuffer ls_sql = new StringBuffer("select tp.codigo_personal from tramite.personal tp, tramite.usuarios tu where tp.codigo_oficina in ("+accesoaoficinas+")  and tu.estado LIKE 'A' and tp.c_usuario = tu.usuario  and tp.cargo_personal in (2,3,1,10,13,11) ");//AND anio_doc=2012
			
			
			
			pstmt = conn.prepareStatement(ls_sql.toString());
			//pstmt.setString(1,codigo_personal);
			rst = pstmt.executeQuery();	
			log.info("sql :"+pstmt);
			while(rst.next()){
				resultado = rst.getString("codigo_personal")+","+resultado;
    		}
            obt.cerrar_conexion(conn,rst);		
            log.info("resultado ->"+resultado);
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
       
	return resultado;
}

public String getRutaDetalleUpload(String codigo_oficina) throws Exception {
	String resultado=null;
	log.info("Entre -getRutaDetalleUpload");
	PreparedStatement pstmt = null;
	ResultSet rst = null;
	
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();

	 try {	
 		
		 StringBuffer ls_sql = new StringBuffer("select o.descripcion_oficina, g.nombre_grupo  ");
		 ls_sql.append(" from tramite.oficinas o ");
		 ls_sql.append(" left join tramite.grupo_oficina g on g.codigo_grupo=o.grupo_oficina ");
		 ls_sql.append(" where o.institucion in (1,2) and o.es_activo='A' and  o.codigo_oficina="+codigo_oficina);
		 ls_sql.append(" order by o.orden;");//AND anio_doc=2012
			
			
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			//pstmt.setString(1,codigo_personal);
			rst = pstmt.executeQuery();	
			log.info("sql :"+pstmt);
			while(rst.next()){
				resultado = "/"+rst.getString("nombre_grupo")+"/"+rst.getString("descripcion_oficina");
    		}
            obt.cerrar_conexion(conn,rst);		
            log.info("resultado ->"+resultado);
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	
	
	return resultado;
}

public BArchivo getDetalleFile(String id_detalle_upload)throws Exception{
	PreparedStatement pstmt = null;
	ResultSet rst = null;		
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	BArchivo item= null;

	log.info("Entre getDetalleFile");
	 try {	
 		
			StringBuilder ls_sql = new StringBuilder("SELECT id_det_upload, ruta_archivo, nombre_archivo_detalle,nombre_archivo ");
			ls_sql.append(" FROM tramite.tbl_det_upload   WHERE id_det_upload =?  ");
			
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			pstmt.setString(1, id_detalle_upload);

			rst = pstmt.executeQuery();  		
			log.info("sql :"+pstmt);
			
    		while(rst.next()){
    			item= new BArchivo();
    			item.setId_archivo(rst.getInt("id_det_upload"));
    			item.setRutaDetalleUpload(rst.getString("ruta_archivo"));
    			item.setNombre_archivo(rst.getString("nombre_archivo_detalle"));
    		}
            obt.cerrar_conexion(conn,rst);	
            pstmt.close();
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	
	return item;
}


public int getGrupoOficina(int codigo_oficina_nuevo)throws Exception{
	PreparedStatement pstmt = null;
	ResultSet rst = null;		
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	int resultado= 0;

	log.info("Entre getGrupoOficina");
	 try {	
 		
			StringBuilder ls_sql = new StringBuilder("SELECT grupo_oficina ");
			ls_sql.append(" FROM tramite.oficinas   WHERE codigo_oficina =?  ");
			
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			pstmt.setInt(1, codigo_oficina_nuevo);

			rst = pstmt.executeQuery();  		
			log.info("sql :"+pstmt);
			
    		while(rst.next()){
    			resultado = rst.getInt("grupo_oficina");
    		}
    		
            obt.cerrar_conexion(conn,rst);	
            pstmt.close();
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	
	return resultado;
}

@Override
public String getRutaDocumentoUpload(int id_det) throws Exception {
	PreparedStatement pstmt = null;
	ResultSet rst = null;		
	Connection conn = null;
	ObtieneConexion obt=new ObtieneConexion();
	String resultado= "";

	log.info("Entre getGrupoOficina");
	 try {	
 		
			StringBuilder ls_sql = new StringBuilder("select ruta_archivo from tramite.tbl_det_upload where id_det_upload="+id_det );
			
			
			conn = obt.getConnection();
			pstmt = conn.prepareStatement(ls_sql.toString());
			//pstmt.setInt(1, codigo_oficina_nuevo);

			rst = pstmt.executeQuery();  		
			log.info("sql :"+pstmt);
			
    		while(rst.next()){
    			resultado = rst.getString("ruta_archivo");
    		}
    		
            obt.cerrar_conexion(conn,rst);	
            pstmt.close();
            
        } catch (SQLException sqle) {
        	obt.cerrar_conexion(conn);
			sqle.printStackTrace();
        }
	
	return resultado;
}



}


