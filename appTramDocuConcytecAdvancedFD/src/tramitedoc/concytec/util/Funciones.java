package tramitedoc.concytec.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.*;
import java.text.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;






import tramitedoc.concytec.action.AActionMantDerivar;
import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BInfoListaObservacion;
import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.bean.BPojo;


public class Funciones {
	protected  Log log = LogFactory.getLog(Funciones.class);
		
	public String convertir_fecha(String fecha) {
		int li_retorno;
		li_retorno = 0;
		String ls_new_fecha="";
		
		String ls_ano = null, ls_mes = null, ls_dia = null;

		// log.info("Fecha: " + fecha );

		try{
		ls_ano = fecha.substring(6, 10);
		ls_mes = fecha.substring(3, 5);
		ls_dia = fecha.substring(0, 2);
		/*
		 * log.info("ls_ano: " + ls_ano ); log.info("ls_mes: " +
		 * ls_mes ); log.info("ls_dia: " + ls_dia );
		 */

		ls_new_fecha = fecha.substring(6, 10) + fecha.substring(3, 5)
				+ fecha.substring(0, 2);

		// log.info("ls_new_fecha: " + ls_new_fecha );

		}catch(Exception e){
			
		}
		return ls_new_fecha;

	}

	public double ImporteDolares(double tipocambio, double IMPORTS) {

		double ls_cantidad = 0.0;

		ls_cantidad = tipocambio * IMPORTS;

		// log.info("ls_cantidad: " + ls_cantidad );

		return ls_cantidad;

	}

	public double ImporteHN100(double IMPORTS) {

		double ls_cantidad = 0.0;

		ls_cantidad = IMPORTS / 240;

		// log.info("ls_cantidad: " + ls_cantidad );

		return ls_cantidad;

	}

	public double ImporteHE125(double ImporteHN100) {

		double ls_cantidad = 0.0;

		ls_cantidad = ImporteHN100 * 1.25;

		// log.info("ls_cantidad: " + ls_cantidad );

		return ls_cantidad;

	}

	public double ImporteHE150(double ImporteHN100) {

		double ls_cantidad = 0.0;

		ls_cantidad = ImporteHN100 * 1.5;

		// log.info("ls_cantidad: " + ls_cantidad );

		return ls_cantidad;

	}

	public double ImporteHE200(double ImporteHN100) {

		double ls_cantidad = 0.0;

		ls_cantidad = ImporteHN100 * 2;

		// log.info("ls_cantidad: " + ls_cantidad );

		return ls_cantidad;

	}

	public int Horas(int NMinutos) {

		int ls_cantidad = 0;

		ls_cantidad = NMinutos / 60;

		// log.info("ls_cantidad: " + ls_cantidad );

		return ls_cantidad;

	}

	public double ImporteBruto(double ImporteSoles, double Horas,
			double ImporteHN100) {

		double ls_cantidad = 0.0;

		if (Horas == 240) {

			ls_cantidad = ImporteSoles;
			// log.info("ls_cantidad: " + ls_cantidad );

		} else if (Horas < 240) {

			ls_cantidad = ImporteHN100 * Horas;
			// log.info("ls_cantidad: " + ls_cantidad );

		}

		return ls_cantidad;

	}

	public String of_nombre_oficina(Connection cnx, String cod_oficina)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		ObtieneConexion  x= new ObtieneConexion();
		Connection	con= x.getConnection();
		String ls_descripcion_oficina = "";

		StringBuffer ls_sql = new StringBuffer("select descripcion_oficina from tramite.oficinas where codigo_oficina = '" + cod_oficina + "'");

		us = con.createStatement();
		rs = us.executeQuery(ls_sql.toString());
		
		while (rs.next()) {
			ls_descripcion_oficina = rs.getString("descripcion_oficina");
		}
		
		x.cerrar_conexion(con, rs);

		ls_descripcion_oficina=ls_descripcion_oficina==null?  "---":ls_descripcion_oficina;
		
		return ls_descripcion_oficina.equals("")? "---": ls_descripcion_oficina;
	}

	public String of_nombre_corto_oficina(Connection cnx, String cod_oficina)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_descripcion_oficina = "";
		ObtieneConexion  x= new ObtieneConexion();
		Connection	con= x.getConnection();
		
		StringBuffer ls_sql = new StringBuffer("select siglas_oficina from tramite.oficinas where codigo_oficina = "+cod_oficina);

		us = con.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql.toString());
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_descripcion_oficina = rs.getString("siglas_oficina");
			// log.info("El ls_descripcion_oficina: " +
			// ls_descripcion_oficina);
		}
		x.cerrar_conexion(con, rs);

		return ls_descripcion_oficina;
	}

	public int esUnicoenOficina(Connection cnx, String oficina, String persona) throws SQLException {
		
		int cantidad=0;
		Statement us = null;
		ResultSet rs = null;
		
		ObtieneConexion  x= new ObtieneConexion();
		Connection	con= x.getConnection();

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		StringBuffer ls_sql = new StringBuffer("select count(tp.codigo_personal) from tramite.personal tp, tramite.usuarios tu"+
				" where tu.usuario = tp.c_usuario and tp.codigo_oficina LIKE '"+oficina+"' and tu.estado like 'A'");

		us = con.createStatement();
		rs = us.executeQuery(ls_sql.toString());
		
		if (rs.next()) {
			cantidad=rs.getInt(1);
		}
		x.cerrar_conexion(con, rs);				
		return cantidad;
	}
	
	
	public String of_codigo_oficina(Connection cnx, String usuario)
			throws SQLException {

    	ObtieneConexion  x= new ObtieneConexion();
		Connection	con= x.getConnection();
		Statement us  = null;
		ResultSet rs = null;
		String ls_codigo_oficina="";

		String ls_sql = "select us.*,pe.* from tramite.usuarios us, tramite.personal pe where"
				+ " us.usuario=pe.c_usuario and pe.c_usuario="
				+ "'"
				+ usuario
				+ "'";

		us = con.createStatement();
		log.info("El us" + us);
		rs = us.executeQuery(ls_sql);
		log.info("El rs" + rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_codigo_oficina = rs.getString("codigo_oficina");
			// log.info("El ls_codigo_oficina: " + ls_codigo_oficina);
		}
		rs.cancelRowUpdates();
		x.cerrar_conexion(con,rs);
    	us.close();
    	
		return ls_codigo_oficina;
	}
	
	public String of_codigo_grupo(Connection cnx, String codigo_oficina)
			throws SQLException {

    	ObtieneConexion  x= new ObtieneConexion();
		Connection	con= x.getConnection();
		Statement us  = null;
		ResultSet rs = null;
		String grupo_oficina="";

		String ls_sql = "select  ofi.grupo_oficina  "
				+ "from  tramite.oficinas ofi  "
				+ "where ofi.codigo_oficina="+codigo_oficina;


		us = con.createStatement();
		log.info("El us" + us);
		rs = us.executeQuery(ls_sql);
		log.info("El rs" + rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			grupo_oficina = rs.getString("grupo_oficina");
			// log.info("El ls_codigo_oficina: " + ls_codigo_oficina);
		}
		rs.cancelRowUpdates();
		x.cerrar_conexion(con,rs);
    	us.close();
    	
		return grupo_oficina;
	}

	/**
	 * 
	 * @param cnx
	 * @param usuario
	 * @throws SQLException
	 */
		public String of_codigo_oficina_instituciones(Connection cnx, String usuario)	throws SQLException {
	
		ObtieneConexion  x= new ObtieneConexion();
		Connection	con= x.getConnection();
		Statement us  = null;
		ResultSet rs = null;
		String codigo_institucion="";
		
		String ls_sql = "select us.*,pe.*, ofi.institucion "+
			" from tramite.usuarios us, tramite.personal pe, tramite.oficinas ofi "+
			" where us.usuario=pe.c_usuario "+ 
			" and pe.codigo_oficina=ofi.codigo_oficina "+
			" and pe.c_usuario='"+usuario+"' "+
			" and pe.validacion=1; "; 
		log.info("El ls_sql-->"+ls_sql);
		us = con.createStatement();
		log.info("El us" + us);
		rs = us.executeQuery(ls_sql);
		log.info("El rs" + rs);
		
		// log.info("Si el ls_sql Doc: " + ls_sql);
		
		while (rs.next()) {
		
			codigo_institucion = rs.getString("institucion");
			// log.info("El ls_codigo_oficina: " + ls_codigo_oficina);
		}
		rs.cancelRowUpdates();
		x.cerrar_conexion(con,rs);
		us.close();
		
		return codigo_institucion;
		}
		
		public String getNombreInstitucion(String ls_codigo_institucion)	throws SQLException {
			
			ObtieneConexion  x= new ObtieneConexion();
			Connection	con= x.getConnection();
			Statement us  = null;
			ResultSet rs = null;
			String nombreInstitucion="---";
			
			StringBuffer sql = new StringBuffer("select descripcion_corta from tramite.tbl_elemento_catalogo ");
            sql.append(" where id_catalogo='tipo_sede' and id_elemento= "+ls_codigo_institucion);
			
			us = con.createStatement();
			log.info("El us" + us);
			rs = us.executeQuery(sql.toString());
			log.info("El rs" + rs);
			
			// log.info("Si el ls_sql Doc: " + ls_sql);
			
			while (rs.next()) {
				nombreInstitucion = rs.getString("descripcion_corta");
				// log.info("El ls_codigo_oficina: " + ls_codigo_oficina);
			}
			rs.cancelRowUpdates();
			x.cerrar_conexion(con,rs);
			us.close();
			
			return nombreInstitucion;
			}
		
		public void of_usuario_inicia_session(Connection cnx, String usuario)
				throws SQLException {
	
			Statement us = null;
			ResultSet rs = null;
			String ls_codigo_oficina = "";
			String ls_sql = "";
			ObtieneConexion x = new ObtieneConexion();
			Connection con = x.getConnection();
	
			// String ls_sql = "select * from tramite.oficinas where
			// codigo_oficina=" + "'" + cod_oficina + "'";
			ls_sql = "update tramite.usuarios set userconectados = 'S' where usuario="
					+ "'" + usuario + "'";
			us = con.createStatement();
			us.executeUpdate(ls_sql);

	}

	public void of_update_numero_referencia(Connection cnx, String codigo_documento)
	throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_codigo_oficina = "";
		String ls_sql = "";
		ObtieneConexion x = new ObtieneConexion();
		Connection con = x.getConnection();
		
		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		ls_sql = "update tramite.movimientos_doc set numero_referencia = '0' where codigo_documento="
				+ "'" + codigo_documento + "'";
		
		 log.info("El ls_sql num refe----"+  ls_sql);
		us = con.createStatement();
		us.executeUpdate(ls_sql);
		
		}
	
	public void of_usuario_cierra_session(Connection cnx, String usuario)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_codigo_oficina = "";
		String ls_sql = "";
		ObtieneConexion x = new ObtieneConexion();
		Connection con = x.getConnection();

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		ls_sql = "update tramite.parametro set valor = valor + 1";
		us = con.createStatement();
		us.executeUpdate(ls_sql);

	}

	public String of_codigo_persona(Connection cnx, String usuario)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_codigo_personal = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select us.*,pe.* from tramite.usuarios us, tramite.personal pe where"
				+ " us.usuario=pe.c_usuario and pe.c_usuario="
				+ "'"
				+ usuario
				+ "'";

		us = cnx.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_codigo_personal = rs.getString("codigo_personal");
			// log.info("El ls_codigo_personal: " +
			// ls_codigo_personal);
		}
		rs.close();

		return ls_codigo_personal;
	}

	public int parametro(Connection cnx) {
		int li_retorno;
		li_retorno = 0;
		// obtenermos el valor maximo...
		Statement us = null;
		ObtieneConexion x = new ObtieneConexion();
		Connection cn = x.getConnection();
		String ls_sql = "";
		try {
			ls_sql = "update tramite.parametro set valor = valor + 1";
			us = cn.createStatement();
			us.executeUpdate(ls_sql);
			ls_sql = "Select valor from tramite.parametro";
			ResultSet rs = us.executeQuery(ls_sql);
			while (rs.next()) {
				log.info("dentro del whyle.......");
				li_retorno = Integer.valueOf(rs.getString("valor")).intValue();
				// log.info("ls_valor :" + rs.getString("valor"));
			}
			x.cerrar_conexion(cn, rs);
			us.close();
		} catch (Exception e) {
			log.info("error en el Servlet : " + e.getMessage());
			x.cerrar_conexion(cn);
		}
		return li_retorno;

	}
	
	public int parametro() {
		int li_retorno;
		li_retorno = 0;
		// obtenermos el valor maximo...
		Statement us = null;
		ObtieneConexion x = new ObtieneConexion();
		Connection cn = x.getConnection();
		String ls_sql = "";
		try {
			ls_sql = "update tramite.parametro set valor = valor + 1";
			us = cn.createStatement();
			us.executeUpdate(ls_sql);
			ls_sql = "Select valor from tramite.parametro";
			ResultSet rs = us.executeQuery(ls_sql);
			while (rs.next()) {
				log.info("dentro del whyle.......");
				li_retorno = Integer.valueOf(rs.getString("valor")).intValue();
				// log.info("ls_valor :" + rs.getString("valor"));
			}
			x.cerrar_conexion(cn, rs);
			us.close();
		} catch (Exception e) {
			log.info("error en el Servlet : " + e.getMessage());
			x.cerrar_conexion(cn);
		}
		return li_retorno;

	}

	public String of_nombre_estado(Connection cnx, String codigo_estado)
			throws SQLException {


    	ObtieneConexion  x= new ObtieneConexion();
		Connection	con= x.getConnection();
		Statement us  = null;
		ResultSet rs = null;
		
		String ls_estado_expediente = "";

		if(codigo_estado.trim().equals("A")){
			codigo_estado="10";
		}
		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select estado_expediente  from tramite.estados_doc where codigo_estado="+codigo_estado;

		us = con.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_estado_expediente = rs.getString("estado_expediente");
			// log.info("El ls_estado_expediente: " +
			// ls_estado_expediente);
		}
		x.cerrar_conexion(con, rs);
		us.close();

		return ls_estado_expediente;
	}

	public String of_numero_expediente(Connection cnx,
			String correlativorecepcion) throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_codigo_expediente = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select codigo_expediente from tramite.movimientos_doc where codigo_recepcion="
				+ "'" + correlativorecepcion + "'";

		us = cnx.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_codigo_expediente = rs.getString("codigo_expediente");
			// log.info("El ls_codigo_expediente: " +
			// ls_codigo_expediente);
		}
		rs.close();

		return ls_codigo_expediente;
	}
	public String of_fecha_expediente(Connection cnx,
			String correlativorecepcion) throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_codigo_expediente = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select fecha_movimiento from tramite.movimientos_doc where codigo_recepcion="
				+ "'" + correlativorecepcion + "'";

		us = cnx.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_codigo_expediente = rs.getString("fecha_movimiento");
			// log.info("El ls_codigo_expediente: " +
			// ls_codigo_expediente);
		}
		rs.close();

		return ls_codigo_expediente;
	}
	public String of_numero_registro(Connection cnx) throws SQLException {

		// obtenermos el valor maximo...
		log.info("Obtener numero de registro......");

		int li_retorno;
		int li_parcial = 0;
		int li_expediente = 0;
		int li_secuencia;
		int li_secuencia_oficina;
		String codigo_documento = "";
		String ls_extraer_year = "";
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		Date d = new Date();
		String yearactual = year.format(d);

		// String naturaleza_documento = "E";
		String ls_correlativo = null;
		li_retorno = 1;
		Statement us = null;
		ResultSet rs = null;

		String ls_sql = "";
		String ls_valor = "";

		ls_sql = "Select max(codigo_documento) as c_valor from tramite.documento";
		// log.info("ls_sql............ " + ls_sql );
		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		// log.info("El resultset es: " + rs);

		if (rs == null) {
			log.info("rs es nulo .......");
			li_retorno = 1;
		} else {
			// log.info("rs NO es nulo ......." );
			while (rs.next()) {
				// log.info("dentro del whyle......." );
				if (rs.getString("c_valor") == null) {
					// log.info("ta vacio nulo ......." );
					li_retorno = 1;
				} else {
					// log.info("ls_valor :" +
					// rs.getString("c_valor"));

					ls_valor = rs.getString("c_valor");
					ls_extraer_year = rs.getString("c_valor").substring(4);
					// log.info("ls_extraer_year :" +
					// ls_extraer_year);
					li_retorno = Integer.valueOf(ls_valor).intValue() + 1;
				}

			}
			

		}

		// codigo_documento = yearactual+ li_retorno;
		codigo_documento = String.valueOf(li_retorno);
		rs.close();
		// log.info("El nuevo codigo documento es" +
		// codigo_documento);
		return codigo_documento;
	}

	public String of_numero_documento(Connection cnx, String numero_documento)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_valor = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select count(*) as c_valor from tramite.documento where numero_documento = "
				+ "'" + numero_documento + "'";

		us = cnx.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_valor = rs.getString("c_valor");
			log.info("El ls_valor: " + ls_valor);
		}
		rs.close();

		return ls_valor;
	}

	public String of_codigo_documento_valida(Connection cnx,
			String codigo_documento) throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_valor = "";
		String ls_valor_1 = "";
		String ls_valor_2 = "";
		String ls_sql = "";
		int ls_suma = 0;

		// log.info("En la tabla documento....RRR");
		ls_sql = "select count(*) as c_valor from tramite.documento where codigo_documento = "
				+ "'" + codigo_documento + "'";
		// log.info("El ls_sql: " + ls_sql);
		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);

		while (rs.next()) {

			ls_valor = rs.getString("c_valor");
			// log.info("El ls_valor: " + ls_valor);
		}
		rs.close();

		// log.info("En la tabla doc_entrada....XXX");

		ls_sql = "select count(*) as c_valor from tramite.doc_entrada where codigo_documento = "
				+ "'" + codigo_documento + "'";
		// log.info("El ls_sql 2: " + ls_sql);
		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);

		while (rs.next()) {

			ls_valor_1 = rs.getString("c_valor");
			// log.info("El ls_valor_1: " + ls_valor_1);
		}
		rs.close();

		// log.info("En la tabla movimientos_doc....XXX");

		ls_sql = "select count(*) as c_valor from tramite.movimientos_doc where codigo_documento = "
				+ "'" + codigo_documento + "'";
		// log.info("El ls_sql 3: " + ls_sql);
		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);

		while (rs.next()) {

			ls_valor_2 = rs.getString("c_valor");
			// log.info("El ls_valor_2: " + ls_valor_2);
		}
		rs.close();

		ls_suma = Integer.parseInt(ls_valor) + Integer.parseInt(ls_valor_1)
				+ Integer.parseInt(ls_valor_2);

		return String.valueOf(ls_suma);
	}

	public void of_codigo_documento_delete(Connection cnx,
			String codigo_documento) throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_valor = "";
		String ls_valor_1 = "";
		String ls_valor_2 = "";
		String ls_sql = "";
		int ls_suma = 0;

		ObtieneConexion x = new ObtieneConexion();
		Connection cn = x.getConnection();

		// log.info("En la tabla documento....RRR");
		ls_sql = "delete from tramite.documento WHERE codigo_documento =" + "'"
				+ codigo_documento + "'";
		// log.info("El ls_sql: " + ls_sql);
		us = cn.createStatement();
		us.executeUpdate(ls_sql);

		// log.info("En la tabla doc_entrada....XXX");

		ls_sql = "delete from tramite.doc_entrada WHERE codigo_documento ="
				+ "'" + codigo_documento + "'";
		// log.info("El ls_sql 2: " + ls_sql);
		us = cn.createStatement();
		us.executeUpdate(ls_sql);

		// log.info("En la tabla movimientos_doc....XXX");

		ls_sql = "delete from tramite.movimientos_doc WHERE codigo_documento ="
				+ "'" + codigo_documento + "'";
		// log.info("El ls_sql 3: " + ls_sql);
		us = cn.createStatement();
		us.executeUpdate(ls_sql);

		x.cerrar_conexion(cn);
		us.close();

	}

	public String of_medio_documento(Connection cnx, String codigo_medio)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_descripcion = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select * from tramite.medio where codigo_medio = "
				+ "'" + codigo_medio + "'";

		us = cnx.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_descripcion = rs.getString("descripcion");
			//log.info("El ls_descripcion medio..: " + ls_descripcion);
		}
		rs.close();

		return ls_descripcion;
	}

	public String of_validar_clave(Connection cnx, String password)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_valor = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select count(clave) as valor from tramite.usuarios where clave = "
				+ "'" + password + "'";

		us = cnx.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_valor = rs.getString("valor");
			// log.info("El ls_valor medio..: " + ls_valor);
		}
		rs.close();

		return ls_valor;
	}

	public String formatAcentos(String cadena) {
		cadena = cadena.replace("Ã¡", "á");
		cadena = cadena.replace("Ã©", "é");
		cadena = cadena.replace("Ã³", "ó");
		cadena = cadena.replace("Ãº", "ú");
		cadena = cadena.replace("Ã", "í");

		cadena = cadena.replace("Âº", "°");
		cadena = cadena.replace("í‘", "Ñ");
		cadena = cadena.replace("í±", "ñ");

		cadena = cadena.replace("â€“", "–");
		cadena = cadena.replace("â€™", "'");
		cadena = cadena.replace("Ã‰", "É");
		cadena = cadena.replace("Ã“", "Ó");
		cadena = cadena.replace("Ãš", "Ú");
		/*
		 * String cadena = new String("secar");
		 * log.info(cadena.replace('e','a'); //sacar
		 */

		return cadena;
	}

	public int parametroDocumentosUpload() {
		int li_retorno;
		li_retorno = 0;
		// obtenermos el valor maximo...
		Statement us = null;
		ObtieneConexion x = new ObtieneConexion();
		Connection cn = x.getConnection();
		String ls_sql = "";
		try {
			ls_sql = "update tramite.tbl_upload_parametro set valor = valor + 1";
			us = cn.createStatement();
			us.executeUpdate(ls_sql);
			ls_sql = "Select valor from tramite.tbl_upload_parametro";
			ResultSet rs = us.executeQuery(ls_sql);
			while (rs.next()) {
				log.info("dentro del whyle.......");
				li_retorno = Integer.valueOf(rs.getString("valor")).intValue();
				log.info("ls_valor :" + rs.getString("valor"));
			}
			
			x.cerrar_conexion(cn,rs);
			us.close();
		} catch (Exception e) {
			x.cerrar_conexion(cn);
			log.info("error en el Servlet : " + e.getMessage());
		}
		return li_retorno;

	}

	public String of_numero_doc_upload(Connection cnx, String codigo_documento)
			throws SQLException {


    	ObtieneConexion  x= new ObtieneConexion();
		Connection	con= x.getConnection();
		Statement us  = null;
		ResultSet rs = null;
		
		int ls_valor = 0;

		try {

			// String ls_sql = "select * from tramite.oficinas where
			// codigo_oficina=" + "'" + cod_oficina + "'";
			String ls_sql = "select count(id_det_upload) as valor from tramite.tbl_det_upload where codigo_documento = "+codigo_documento;

			us = con.createStatement();
			rs = us.executeQuery(ls_sql);

			// log.info("Si el of_numero_doc_upload CCCC ls_sql Doc: "
			// + ls_sql);

			while (rs.next()) {
				ls_valor = rs.getInt("valor");
				// log.info("El ls_valor medio..: " + ls_valor);
			}
			x.cerrar_conexion(con, rs);
			us.close();
		} catch (Exception e) {
			log.info("error en el Servlet : " + e.getMessage());
			x.cerrar_conexion(con);
		}
		return String.valueOf(ls_valor);
	}

	public String of_nombre_doc_upload(Connection cnx, String codigo_documento,
			String codigo_expediente, String secuencia_movimiento)
			throws SQLException {

		ObtieneConexion  x= new ObtieneConexion();
		Connection	con= x.getConnection();
		Statement us  = null;
		ResultSet rs = null;
		String ls_valor = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select nombre_archivo as valor from tramite.tbl_det_upload where"
				+ " codigo_documento = "
				+ "'"
				+ codigo_documento
				+ "'"
				+ " and codigo_expediente = "
				+ "'"
				+ codigo_expediente
				+ "'"
				+ " and secuencia_movimiento = "
				+ "'"
				+ secuencia_movimiento
				+ "'";

		us = con.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_valor = rs.getString("valor");
			log.info("El ls_valor medio..: " + ls_valor);
		}
		x.cerrar_conexion(con, rs);
		us.close();

		return ls_valor;
	}

	public String of_numero_secuencia(Connection cnx,
			String correlativorecepcion) throws SQLException {

		ObtieneConexion  x= new ObtieneConexion();
		Connection	con= x.getConnection();
		Statement us  = null;
		ResultSet rs = null;
		
		String ls_secuencia_movimiento = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select secuencia_movimiento from tramite.movimientos_doc where codigo_recepcion="
				+ "'" + correlativorecepcion + "'";

		us = con.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_secuencia_movimiento = rs.getString("secuencia_movimiento");
			// log.info("El ls_codigo_expediente: " +
			// ls_codigo_expediente);
		}
		x.cerrar_conexion(con, rs);
		us.close();

		return ls_secuencia_movimiento;
	}

	public String of_generar_nombre_upload(Connection cnx,
			int codigo_documento, int codigo_expediente,
			int secuencia_movimiento) throws SQLException {

		// obtenermos el valor maximo...
		log.info("Generar nombre para documentos......");

		int li_retorno;
		int li_parcial = 0;
		int li_expediente = 0;
		int li_secuencia;
		int li_secuencia_oficina;
		// String codigo_documento= "" ;
		Statement us = null;
		ResultSet rs = null;
		ObtieneConexion x = new ObtieneConexion();
		Connection cn = x.getConnection();
		String ls_sql = "";

		String ls_extraer_year = "";
		String ls_nombre_upload = "";
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		Date d = new Date();
		String yearactual = year.format(d);

		// String naturaleza_documento = "E";
		String ls_correlativo = null;
		li_retorno = 1;

		String ls_valor = "";

		ls_sql = "update tramite.tbl_parametro_nombre set valor = valor + 1";
		us = cn.createStatement();
		us.executeUpdate(ls_sql);
		
		ls_sql = "Select valor from tramite.tbl_parametro_nombre";
		rs = us.executeQuery(ls_sql);
		while (rs.next()) {
			// log.info("dentro del whyle......." );
			li_retorno = Integer.valueOf(rs.getString("valor")).intValue();
			// log.info("ls_valor parametro nombre:" +
			// rs.getString("valor"));
		}

		ls_nombre_upload = String.valueOf(codigo_documento)
				+ String.valueOf(codigo_expediente)
				+ String.valueOf(secuencia_movimiento) + li_retorno;

		// log.info("El nombre upload parametro nombre es.....RRRR" +
		// ls_nombre_upload);
		x.cerrar_conexion(cn, rs);
		us.close();
		
		return ls_nombre_upload;

	}

	public String cab_max_upload(Connection cnx) throws SQLException {

		// obtenermos el valor maximo...
		log.info("Obtener maximo valor cabecera upload......");

		int li_retorno;
		int li_parcial = 0;
		int li_expediente = 0;
		int li_secuencia;
		int li_secuencia_oficina;
		String codigo_documento = "";
		String ls_extraer_year = "";
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		Date d = new Date();
		String yearactual = year.format(d);
		String ls_cab_upload = "";
		// String naturaleza_documento = "E";
		String ls_correlativo = null;
		li_retorno = 1;
		Statement us = null;
		ResultSet rs = null;
		ObtieneConexion x = new ObtieneConexion();
		Connection con = x.getConnection();

		String ls_sql = "";
		String ls_valor = "";

		ls_sql = "select max(codigo_documento) as c_valor from tramite.documento";
		// log.info("ls_sql............ " + ls_sql );
		us = con.createStatement();
		rs = us.executeQuery(ls_sql);
		// log.info("El resultset es: " + rs);

		if (rs == null) {
			// log.info("rs es nulo ......." );
			li_retorno = 1;
		} else {
			// log.info("rs NO es nulo ......." );
			while (rs.next()) {
				// log.info("dentro del whyle......." );
				if (rs.getString("c_valor") == null) {
					// log.info("ta vacio nulo ......." );
					li_retorno = 1;
				} else {
					// log.info("ls_valor :" +
					// rs.getString("c_valor"));
					ls_valor = rs.getString("c_valor");
					li_retorno = Integer.valueOf(ls_valor).intValue() + 1;
					// log.info("li_retorno :" + li_retorno);
				}

			}

		}

		// codigo_documento = yearactual+ li_retorno;
		ls_cab_upload = String.valueOf(li_retorno);
		x.cerrar_conexion(con, rs);
		us.close();
		// log.info("El valor cabecera es..." + ls_cab_upload);
		return ls_cab_upload;
	}

	public String of_generar_nombre_upload(Connection cnx,
			int ls_cabecera_upload) throws SQLException {

		// obtenermos el valor maximo...
		// log.info("Generar nombre para documentos......" );

		int li_retorno;
		int li_parcial = 0;
		int li_expediente = 0;
		int li_secuencia;
		int li_secuencia_oficina;
		// String codigo_documento= "" ;
		Statement us = null;
		ResultSet rs = null;
		ObtieneConexion x = new ObtieneConexion();
		Connection cn = x.getConnection();
		String ls_sql = "";

		String ls_extraer_year = "";
		String ls_nombre_upload = "";
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		Date d = new Date();
		String yearactual = year.format(d);

		// String naturaleza_documento = "E";
		String ls_correlativo = null;
		li_retorno = 1;

		String ls_valor = "";

		ls_sql = "update tramite.tbl_parametro_nombre set valor = valor + 1";
		us = cn.createStatement();
		us.executeUpdate(ls_sql);
		ls_sql = "Select valor from tramite.tbl_parametro_nombre";
		rs = us.executeQuery(ls_sql);
		while (rs.next()) {
			// log.info("dentro del whyle......." );
			li_retorno = Integer.valueOf(rs.getString("valor")).intValue();
			// log.info("ls_valor parametro nombre:" +
			// rs.getString("valor"));
		}

		ls_nombre_upload = String.valueOf(ls_cabecera_upload) + li_retorno;

		// log.info("El nombre upload parametro Generar upload nombre
		// es.....RRRR" + ls_nombre_upload);
		x.cerrar_conexion(cn, rs);
		us.close();
		return ls_nombre_upload;

	}

	public String of_nombre_persona(String codigo_persona)
			throws SQLException {

		ObtieneConexion 	x= new ObtieneConexion();
		Connection			con= x.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String ls_nombre_persona = "--------------";

		if(codigo_persona!=null && !codigo_persona.trim().equals("")){
			String ls_sql = "select nombre_personal from tramite.personal where codigo_personal="+ codigo_persona;

		pstmt = con.prepareStatement(ls_sql);
		rs=pstmt.executeQuery();
		
		   if (rs.next()) {
			ls_nombre_persona = rs.getString("nombre_personal");			
		   }
		   rs.close();
		}
		x.cerrar_conexion(con,rs);
    	pstmt.close();

		return ls_nombre_persona;
	}
	
	public String of_nombre_persona(Connection cnx, String codigo_persona)
	throws SQLException {
	
	Statement us = null;
	ResultSet rs = null;
	String ls_nombre_persona = "--------------";
	
	if(codigo_persona!=null && !codigo_persona.trim().equals("")){
		String ls_sql = "select nombre_personal from tramite.personal where codigo_personal="
			+ "'" + codigo_persona + "'";
	
	   us = cnx.createStatement();
	   rs = us.executeQuery(ls_sql);
	   if (rs.next()) {
		ls_nombre_persona = rs.getString("nombre_personal");			
	   }
	   rs.close();
	}
	
	
	return ls_nombre_persona;
	}

	public String of_codigo_oficina_persona(Connection cnx,
			String codigo_oficina) throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_codigo_personal = "";

		String ls_sql = "select codigo_personal from tramite.personal p,tramite.usuarios u where p.codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and p.es_responsable ='S' and u.estado='A' and p.c_usuario=u.usuario";

		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		while (rs.next()) {

			ls_codigo_personal = rs.getString("codigo_personal");
			// log.info("El ls_descripcion_oficina: " +
			// ls_descripcion_oficina);
		}
		rs.close();

		return ls_codigo_personal;
	}

	public String of_nombre_motivo(Connection cnx, String codigo_motivo)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_descripcion_motivo = "";
		ObtieneConexion x= new ObtieneConexion();
    	Connection	con = x.getConnection();

		if (codigo_motivo == null) {
			ls_descripcion_motivo = "---";
			return ls_descripcion_motivo;
		} else {

			
			String ls_sql = "select descripcion_motivo from tramite.motivo where codigo_motivo= '" + codigo_motivo + "' ";
			us = con.createStatement();
			rs = us.executeQuery(ls_sql);
			while (rs.next()) {
				ls_descripcion_motivo = rs.getString("descripcion_motivo");
			}
			x.cerrar_conexion(con, rs);

			ls_descripcion_motivo=ls_descripcion_motivo==null?  "---":ls_descripcion_motivo;

			return ls_descripcion_motivo.equals("")? "---":ls_descripcion_motivo;
		}

	}

	public String of_oficina_deriva(Connection cnx, String codigo_recepcion,
			String codigo_documento) throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_oficina_deriva = "";
		String ls_cadena = "";
		String ls_sql = "select oficina_deriva from tramite.movimientos_doc where codigo_recepcion="
				+ "'"
				+ codigo_recepcion
				+ "'"
				+ " and codigo_documento="
				+ "'"
				+ codigo_documento + "'";

		log.info("El ls_sql DERIVA...: " + ls_sql);

		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		while (rs.next()) {

			ls_oficina_deriva = rs.getString("oficina_deriva");
			ls_cadena = ls_cadena + ls_oficina_deriva + "-";
			// log.info("El ls_cadena: " + ls_cadena);
		}
		rs.close();

		return ls_cadena;

	}

	public String of_max_hoja_envio(Connection cnx, String codigo_documento)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_as_valor = "";
		String ls_cadena = "";
		String ls_sql = "select max(codigo_recepcion) as as_valor from tramite.movimientos_doc where "
				+ " codigo_documento=" + "'" + codigo_documento + "'";

		// log.info("El ls_sql DERIVA...: " + ls_sql);

		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		while (rs.next()) {

			ls_as_valor = rs.getString("as_valor");

			// log.info("El ls_cadena: " + ls_cadena);
		}
		rs.close();

		return ls_as_valor;

	}

	public String of_max_secuencia_envio(Connection cnx, String codigo_documento)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_as_valor = "";
		String ls_cadena = "";
		String ls_sql = "select max(secuencia_movimiento) as as_valor from tramite.movimientos_doc where "
				+ " codigo_documento=" + "'" + codigo_documento + "'";

		// log.info("El ls_sql DERIVA...: " + ls_sql);

		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		while (rs.next()) {

			ls_as_valor = rs.getString("as_valor");

			 log.info("El ls_cadena: " + ls_cadena);
		}
		rs.close();

		return ls_as_valor;

	}

	public String of_valida_letras(String cadena) throws SQLException {

		int pos = 0;
		log.info("Entry en of_valida_letras");
		log.info("cadena antes de convertir-: "+cadena);
		String car_esp[]={"á","é","í","ó","ú","Á","É","Í","Ó","Ú","Ñ","ñ"," "};
		String car_valido[]={"a","e","i","o","u","A","E","I","O","U","N","n","_"};
		String car_suprimir[]={"°","-","'","\"","?","¿","%","/","º","{","}","[","]","ª","&",")","(","=","$","~",","};
		
		try {
			byte[] arr = cadena.getBytes("LATIN1");
			cadena = new String(arr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<car_esp.length;i++){
			
			
			if(cadena.indexOf(car_esp[i])>0){
				log.info("Se ha modificado " + car_esp[i]+" por "+car_valido[i]);
				cadena = cadena.replace(car_esp[i], car_valido[i]);
			}
		}
		
		for(int i=0;i<car_suprimir.length;i++){
			if(cadena.indexOf(car_suprimir[i])>0){
				log.info("Se ha borrado " + car_suprimir[i]);
				cadena = cadena.replace(car_suprimir[i], "");
			}
		}
		
		
		
		log.info("La cadena convertida es....." + cadena);
		
		return cadena;
	}

	public String of_observacion_derivacion(Connection cnx,
			String codigo_documento) throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_as_valor = "";
		String ls_cadena = "";
		/*
		 * String ls_sql = "select max(secuencia_movimiento) as as_valor from
		 * tramite.movimientos_doc where " + " codigo_documento="+"'" +
		 * codigo_documento + "'";
		 */
		String ls_sql = "select observa_movimiento as as_valor from tramite.movimientos_doc where"
				+ " codigo_documento="
				+ "'"
				+ codigo_documento
				+ "'"
				+ " and secuencia_movimiento=(select max(secuencia_movimiento) from"
				+ " tramite.movimientos_doc where codigo_documento="
				+ "'"
				+ codigo_documento + "'" + ")";

		// log.info("El ls_sql DERIVA...: " + ls_sql);

		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		while (rs.next()) {

			ls_as_valor = rs.getString("as_valor");

			// log.info("El ls_cadena: " + ls_cadena);
		}
		rs.close();

		return ls_as_valor;

	}

	public String of_observacion_registro(Connection cnx,
			String codigo_documento) throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_as_valor = "";
		String ls_cadena = "";
		/*
		 * String ls_sql = "select max(secuencia_movimiento) as as_valor from
		 * tramite.movimientos_doc where " + " codigo_documento="+"'" +
		 * codigo_documento + "'";
		 */
		String ls_sql = "select observa_registro as as_valor from tramite.movimientos_doc where"
				+ " codigo_documento="
				+ "'"
				+ codigo_documento
				+ "'"
				+ " and secuencia_movimiento=(select max(secuencia_movimiento) from"
				+ " tramite.movimientos_doc where codigo_documento="
				+ "'"
				+ codigo_documento + "'" + ")";

		// log.info("El ls_sql DERIVA...: " + ls_sql);

		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		while (rs.next()) {

			ls_as_valor = rs.getString("as_valor");

			// log.info("El ls_cadena: " + ls_cadena);
		}
		rs.close();

		return ls_as_valor;

	}

	public String of_cargo_persona(Connection cnx, String usuario)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_cargo_personal = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select us.*,pe.* from tramite.usuarios us, tramite.personal pe where"
				+ " us.usuario=pe.c_usuario and pe.c_usuario="
				+ "'"
				+ usuario
				+ "'";

		us = cnx.createStatement();
		log.info("El us" + us);
		rs = us.executeQuery(ls_sql);
		log.info("El rs" + rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_cargo_personal = rs.getString("cargo_personal");
			// log.info("El ls_codigo_oficina: " + ls_codigo_oficina);
		}
		rs.close();

		return ls_cargo_personal;
	}

	public String of_nombre_motivo_max(Connection cnx, String codigo_documento)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_descripcion_motivo = "";

		// log.info("El codigo_motivo dentro..: " + codigo_motivo);

		if (codigo_documento == null) {
			// log.info("Si el codigo del motivo es NULLLLLLLLLLLL ");
			ls_descripcion_motivo = "---";
			return ls_descripcion_motivo;
		} else {

			// log.info("Si es diferente de null... ");
			/*
			 * String ls_sql = "select descripcion_motivo from tramite.motivo
			 * where codigo_motivo=" + "'" + codigo_documento + "'"+"";
			 */
			String ls_sql = "select md.codigo_motivo,mv.descripcion_motivo as descripcion_motivo from tramite.movimientos_doc md,"
					+ "tramite.motivo mv where md.codigo_motivo=mv.codigo_motivo and md.codigo_documento="
					+ "'"
					+ codigo_documento
					+ "'"
					+ " and md.secuencia_movimiento=(select max(md.secuencia_movimiento) from"
					+ " tramite.movimientos_doc md where md.codigo_documento="
					+ "'" + codigo_documento + "'" + ")";

			// log.info("El ls_sql Motivo..: " + ls_sql);
			us = cnx.createStatement();
			rs = us.executeQuery(ls_sql);
			while (rs.next()) {

				ls_descripcion_motivo = rs.getString("descripcion_motivo");
				// log.info("El ls_descripcion_motivo: " +
				// ls_descripcion_motivo);
			}
			rs.close();

			return ls_descripcion_motivo;
		}

	}

	public String of_contador_upload_doc(Connection cnx, int codigo_documento)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_valor = "";
		log.info("Dentro del contador..: ");
		log.info("El codigo_documento dentro..: " + codigo_documento);

		/*
		 * if(codigo_documento==null){ //log.info("Si el codigo del
		 * motivo es NULLLLLLLLLLLL "); ls_descripcion_motivo="---"; return
		 * ls_descripcion_motivo; }else{
		 */

		// log.info("Si es diferente de null... ");
		/*
		 * String ls_sql = "select descripcion_motivo from tramite.motivo where
		 * codigo_motivo=" + "'" + codigo_documento + "'"+"";
		 */
		String ls_sql = "select count(*) as valor from tramite.tbl_det_upload  "
				+ "where codigo_documento="
				+ "'"
				+ String.valueOf(codigo_documento)
				+ "'"
				+ " and flag = 'A' and tipo = 'P'";

		log.info("El ls_sql Upload..: " + ls_sql);
		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		while (rs.next()) {

			ls_valor = rs.getString("valor");
			// log.info("El ls_descripcion_motivo: " +
			// ls_descripcion_motivo);
		}
		rs.close();

		return ls_valor;
	}

	public String of_nombre_documento_dirigido(Connection cnx,
			String codigo_documento) throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_valor = "";

		try {

			// String ls_sql = "select * from tramite.oficinas where
			// codigo_oficina=" + "'" + cod_oficina + "'";
			String ls_sql = "select dirigido as valor from tramite.documento where"
					+ " codigo_documento = " + "'" + codigo_documento + "'";

			us = cnx.createStatement();
			// log.info("El us"+ us);
			rs = us.executeQuery(ls_sql);
			// log.info("El rs"+ rs);

			// log.info("Si el of_numero_doc_upload CCCC ls_sql Doc: "
			// + ls_sql);

			while (rs.next()) {

				ls_valor = rs.getString("valor");
				// log.info("El ls_valor medio..: " + ls_valor);
			}
			rs.close();
		} catch (Exception e) {
			log.info("error en el Servlet : " + e.getMessage());
		}
		return ls_valor;
	}

	public String of_solicitud_documento(Connection cnx, String codigo_solicitud)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_descripcion = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select * from tramite.solicitud where codigo_solicitud = "
				+ "'" + codigo_solicitud + "'";

		us = cnx.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_descripcion = rs.getString("nombre_solicitud");
			//log.info("El ls_descripcion Solicitud..: "
					//+ ls_descripcion);
		}
		rs.close();

		return ls_descripcion;
	}
	
	
		public String of_codigo_persona_oficina(Connection cnx, String codigo_oficina) throws SQLException {
			
			Statement us = null;
			ResultSet rs = null;
			String ls_nombre_persona = "";
			
			ObtieneConexion x = new ObtieneConexion();
			Connection con = x.getConnection();
			
			// String ls_sql = "select * from tramite.oficinas where
			// codigo_oficina=" + "'" + cod_oficina + "'";
			String ls_sql = "select codigo_persona, nombre_persona,tipo from tramite.persona where" +
					" codigo_oficina="+ "'"+ codigo_oficina+ "'";
			
			us = con.createStatement();
			//log.info("El us" + us);
			rs = us.executeQuery(ls_sql);
			//log.info("El rs" + rs);
			
			// log.info("Si el ls_sql Doc: " + ls_sql);
			
			while (rs.next()) {
			
				ls_nombre_persona = rs.getString("nombre_persona");
				// log.info("El ls_codigo_oficina: " + ls_codigo_oficina);
			}
			x.cerrar_conexion(cnx, rs);
			us.close();
			
			return ls_nombre_persona;
			}
		
			public int of_max_secuencia_codigo(Connection cnx, String codigo_documento, String secuencia_movimiento)
			throws SQLException {
		
			Statement us = null;
			ResultSet rs = null;
			String ls_max_secuencia_movimiento = "";
		
			String ls_sql = "select max (secuencia_movimiento) as secuencia_movimiento from tramite.movimientos_doc where" +
					" codigo_documento="+ "'" + codigo_documento + "'"
					+ " and secuencia_movimiento>"+ "'" + secuencia_movimiento + "'";
		
			us = cnx.createStatement();
			// log.info("El us"+ us);
			rs = us.executeQuery(ls_sql);
			// log.info("El rs"+ rs);
		
			// log.info("Si el ls_sql Doc: " + ls_sql);
		
			while (rs.next()) {
		
				ls_max_secuencia_movimiento = rs.getString("secuencia_movimiento");
				// log.info("El ls_descripcion_oficina: " +
				// ls_descripcion_oficina);
			}
			rs.close();
		
			return Integer.valueOf(ls_max_secuencia_movimiento.trim());
		}
	
			
			public int of_count_estado_codigo(Connection cnx, String codigo_documento, String secuencia_movimiento)
			throws SQLException {
		
			Statement us = null;
			ResultSet rs = null;
			String ls_max_secuencia_movimiento = "";
		
			/*String ls_sql = "select count (codigo_documento) as secuencia_movimiento from tramite.movimientos_doc where" +
					" codigo_documento="+ "'" + codigo_documento + "'" +
					" and estado_movimiento="+ "'" + estado_movimiento + "'";*/
		
			String ls_sql = "select count (codigo_documento) as secuencia_movimiento from tramite.movimientos_doc where" +
			" codigo_documento="+ "'" + codigo_documento + "'" +
			" and secuencia_movimiento>"+ "'" + secuencia_movimiento + "'" +
			" and estado_movimiento='3'";
			
			us = cnx.createStatement();
			// log.info("El us"+ us);
			rs = us.executeQuery(ls_sql);
			// log.info("El rs"+ rs);
		
			 log.info("Si el of_count_estado_codigo Doc: " + ls_sql);
		
			while (rs.next()) {
		
				ls_max_secuencia_movimiento = rs.getString("secuencia_movimiento");
				// log.info("El ls_descripcion_oficina: " +
				// ls_descripcion_oficina);
			}
			rs.close();
		
			return Integer.valueOf(ls_max_secuencia_movimiento.trim());
		}
		
		public BInfoListaObservacion of_contador_estado_codigo(Connection cnx, String codigo_documento, String secuencia_movimiento,String hora_movimiento,
					String ultimo_usuario,String fecha_movimiento,String tipo_envio,int oficina_origen,String valor_codigo_oficina, int codigo_recepcion)
			throws SQLException {
		
			ObtieneConexion  x= new ObtieneConexion();
			Connection	con= x.getConnection();
			Statement us  = null;
			ResultSet rs = null;
			
			
			BInfoListaObservacion informacionresultado = new BInfoListaObservacion();
			Collection ListaMesaPartes = new ArrayList();
					
			String ls_sql = "select * from tramite.movimientos_doc where" +
			" codigo_documento="+ "'" + codigo_documento + "'" +
			" and secuencia_movimiento<"+ "'" + secuencia_movimiento + "'" +
			" and codigo_oficina = "+oficina_origen+
			" and fecha_movimiento ='"+fecha_movimiento+"'"+
			" and hora_movimiento='"+hora_movimiento+"' "+
			" and ultimo_usuario = '"+ultimo_usuario+"' "+
			//" and codigo_recepcion = "+codigo_recepcion+
			" order by secuencia_movimiento";
			
			us = con.createStatement();
			rs = us.executeQuery(ls_sql);
		
			log.info("sql---> "+ls_sql);
			while (rs.next()) {
				BInfoListaObservacion informacion = new BInfoListaObservacion();
				informacion.setSecuencia_inicial(rs.getInt("secuencia_movimiento"));
				informacion.setEstado_codigo(Integer.valueOf(rs.getString("estado_movimiento").trim()));
				informacion.setCodigo_oficina(rs.getInt("codigo_oficina"));
				informacion.setCodigo_oficina_origen(rs.getInt("oficina_origen"));
				informacion.setFecha_movimiento(rs.getString("fecha_movimiento"));
				informacion.setHora_movimiento(rs.getString("hora_movimiento"));
				informacion.setTipo_envio(rs.getString("tipo_envio"));
				informacion.setUltimo_usuario(rs.getString("ultimo_usuario")); //con esto me aseguro que esta en estado por recibir

				ListaMesaPartes.add(informacion);
			}
			
			
				if(ListaMesaPartes!=null && ListaMesaPartes.size()>0){
	  				Iterator it=ListaMesaPartes.iterator();
	  				while(it.hasNext()){
	  					BInfoListaObservacion temp=(BInfoListaObservacion)it.next();
	  					informacionresultado.setSi_observa(true);
	  					informacionresultado.setEstado_codigo(temp.getEstado_codigo());
	  					informacionresultado.setSecuencia_inicial(temp.getSecuencia_inicial());
	  					informacionresultado.setCodigo_oficina(temp.getCodigo_oficina());
	  					informacionresultado.setCodigo_oficina_origen(temp.getCodigo_oficina_origen());
	  					informacionresultado.setFecha_movimiento(temp.getFecha_movimiento());
	  					informacionresultado.setHora_movimiento(temp.getHora_movimiento());
	  					informacionresultado.setTipo_envio(temp.getTipo_envio());
	  					informacionresultado.setUltimo_usuario(temp.getUltimo_usuario());
	  				}
	  						  				
	  			}else{
	  				informacionresultado.setSi_observa(false);
	  			}
			
			x.cerrar_conexion(con, rs);
			us.close();
			//return Integer.valueOf(ls_max_secuencia_movimiento.trim());
			
			return informacionresultado;
		}
		
		public int of_codigo_recepcion(Connection cnx,String codigo_documento,String xxxx)throws SQLException {
			int resultado=0;
	        Connection conn = null;
			PreparedStatement pstmt = null;
			ObtieneConexion obt=new ObtieneConexion();
			ResultSet rs = null;

			String ls_sql = "select codigo_recepcion  from tramite.movimientos_doc " +
			" where codigo_documento=?  and secuencia_movimiento=? ";


			conn = obt.getConnection();
			log.info("query de actualizacion:   " +ls_sql);
			pstmt = conn.prepareStatement(ls_sql);

			pstmt.setInt(1, Integer.parseInt(codigo_documento));
			pstmt.setInt(2, Integer.parseInt(xxxx));
							
			rs=pstmt.executeQuery();

			while (rs.next()) {

				resultado = rs.getInt("codigo_recepcion");

			}
			obt.cerrar_conexion(conn, rs);
			pstmt.close();

			return resultado;
		}
		
		public BPojo of_codigo_recepcion_of_id_cab_upload(Connection cnx,String codigo_documento,String xxxx)throws SQLException {
			
			BPojo resultado = new BPojo();
			//int resultadoz=0;
	        Connection conn = null;
			PreparedStatement pstmt = null;
			ObtieneConexion obt=new ObtieneConexion();
			ResultSet rs = null;

			String ls_sql = "select codigo_recepcion  from tramite.movimientos_doc " +
			" where codigo_documento=?  and secuencia_movimiento=? ";


			conn = obt.getConnection();
			log.info("query de actualizacion:   " +ls_sql);
			pstmt = conn.prepareStatement(ls_sql);

			pstmt.setInt(1, Integer.parseInt(codigo_documento));
			pstmt.setInt(2, Integer.parseInt(xxxx));
							
			rs=pstmt.executeQuery();

			while (rs.next()) {

				resultado.setCodigo_recepcion(rs.getInt("codigo_recepcion"));

			}
			
			ls_sql = "select id_cab_upload  from tramite.tbl_cab_upload " +
			" where codigo_documento=?  and secuencia_movimiento=? ";


			log.info("query de actualizacion:   " +ls_sql);
			pstmt = conn.prepareStatement(ls_sql);

			pstmt.setInt(1, Integer.parseInt(codigo_documento));
			pstmt.setInt(2, Integer.parseInt(xxxx));
							
			rs=pstmt.executeQuery();

			while (rs.next()) {

				resultado.setId_cab_upload(rs.getInt("id_cab_upload"));

			}
			
			obt.cerrar_conexion(conn, rs);
			pstmt.close();

			return resultado;
		}
		
		public String of_nombre_director_oficina(Connection cnx, String codigo_oficina)
			throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String ls_nombre_personal = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select codigo_personal,nombre_personal from tramite.personal p, tramite.usuarios u where p.codigo_oficina="
			+ "'"+ codigo_oficina+ "' and p.es_responsable='S' and p.c_usuario=u.usuario and u.estado='A'";

		us = cnx.createStatement();
		 log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_nombre_personal = rs.getString("nombre_personal");
			// log.info("El ls_codigo_personal: " +
			// ls_codigo_personal);
		}
		rs.close();

		return ls_nombre_personal;
	}
	
		
		public String of_valida_documento_recibidos(Connection cnx, String codigo_documento,
				String secuencia_movimiento)
		throws SQLException {

					Statement us = null;
					ResultSet rs = null;
					String ls_as_codigo_documento = "";
				
					// String ls_sql = "select * from tramite.oficinas where
					// codigo_oficina=" + "'" + cod_oficina + "'";
					String ls_sql = "select count(codigo_documento) as  as_codigo_documento from  tramite.movimientos_doc where codigo_documento="
						+ "'"+ codigo_documento+ "'"+ " and secuencia_movimiento>"+ "'"+ secuencia_movimiento+ "' and estado_movimiento='3'";
				
					us = cnx.createStatement();
					// log.info("El us"+ us);
					rs = us.executeQuery(ls_sql);
					// log.info("El rs"+ rs);
				
					// log.info("Si el ls_sql Doc: " + ls_sql);
				
					while (rs.next()) {
				
						ls_as_codigo_documento = rs.getString("as_codigo_documento");
						// log.info("El ls_codigo_personal: " +
						// ls_codigo_personal);
					}
					rs.close();
				
					return ls_as_codigo_documento;
				}
		
		
		public String of_max_secuencia_documento(Connection cnx, String codigo_documento)
		throws SQLException {
	
			/* log.info("Dentro del Secuencia Documento...!!! ");
			 log.info("El codigo_documento...!!! " + codigo_documento);*/
			ObtieneConexion  x= new ObtieneConexion();
			Connection	con= x.getConnection();
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			
		String ls_max_secuencia_movimiento = "";
	
		String ls_sql = "select max (secuencia_movimiento) as secuencia_movimiento from tramite.movimientos_doc where codigo_documento=? ";
	
		pstmt = con.prepareStatement(ls_sql);
   	 	pstmt.setInt(1, Integer.parseInt(codigo_documento));
   	 	rs=pstmt.executeQuery();
	
		while (rs.next()) {
	
			ls_max_secuencia_movimiento = rs.getString("secuencia_movimiento");
			// log.info("El ls_max_secuencia_movimiento: " + ls_max_secuencia_movimiento);
		}
		x.cerrar_conexion(con, rs);
		pstmt.close();
		return ls_max_secuencia_movimiento;
	}

		public String of_max_secuencia_documento_estado_derivado(Connection cnx, String codigo_documento)
		throws SQLException {
	
			log.info("Dentro del of_max_secuencia_documento_estado_derivado ");
			 /*log.info("El codigo_documento...!!! " + codigo_documento);*/
			ObtieneConexion  x= new ObtieneConexion();
			Connection	con= x.getConnection();
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			
		String ls_max_secuencia_movimiento = "";
	
		String ls_sql = "select max (secuencia_movimiento) as secuencia_movimiento from tramite.movimientos_doc where estado_movimiento='5' and codigo_documento=? ";
	
		pstmt = con.prepareStatement(ls_sql);
   	 	pstmt.setInt(1, Integer.parseInt(codigo_documento));
   	 	rs=pstmt.executeQuery();
	
		while (rs.next()) {
			ls_max_secuencia_movimiento = rs.getString("secuencia_movimiento");
			// log.info("El ls_max_secuencia_movimiento: " + ls_max_secuencia_movimiento);
		}
		x.cerrar_conexion(con, rs);
		pstmt.close();
		return ls_max_secuencia_movimiento;
	}
		
		
		
	 public String of_max_oficina_origen_documento(Connection cnx, String codigo_documento, String secuencia_movimiento)
		throws SQLException {
	
		Statement us = null;
		ResultSet rs = null;
		String ls_max_oficina_origen = "";
	
		String ls_sql = "select oficina_origen from tramite.movimientos_doc where" +
				" codigo_documento="+ "'" + codigo_documento + "' and " +
				" secuencia_movimiento="+ "'" + secuencia_movimiento + "'";
	
		us = cnx.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);
	
		// log.info("Si el ls_sql Doc: " + ls_sql);
	
		while (rs.next()) {
	
			ls_max_oficina_origen = rs.getString("oficina_origen");
			// log.info("El ls_descripcion_oficina: " +
			// ls_descripcion_oficina);
		}
		rs.close();
	
		return ls_max_oficina_origen.trim();
	}

	 public String of_max_oficina_deriva_documento(Connection cnx, String codigo_documento, String secuencia_movimiento)
		throws SQLException {
	
		Statement us = null;
		ResultSet rs = null;
		String ls_max_oficina_deriva = "";
	
		String ls_sql = "select oficina_deriva from tramite.movimientos_doc where" +
		" codigo_documento="+ "'" + codigo_documento + "' and " +
		" secuencia_movimiento="+ "'" + secuencia_movimiento + "'";
	
		us = cnx.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);
	
		// log.info("Si el ls_sql Doc: " + ls_sql);
	
		while (rs.next()) {
	
			ls_max_oficina_deriva = rs.getString("oficina_deriva");
			// log.info("El ls_descripcion_oficina: " +
			// ls_descripcion_oficina);
		}
		rs.close();
	
		return ls_max_oficina_deriva.trim();
	}
	 
	 public String of_max_estado_movimiento_documento(Connection cnx, String codigo_documento, String secuencia_movimiento)
		throws SQLException {
	
		Statement us = null;
		ResultSet rs = null;
		String ls_max_estado_movimiento = "";
	
		String ls_sql = "select estado_movimiento from tramite.movimientos_doc where" +
		" codigo_documento="+ "'" + codigo_documento + "' and " +
		" secuencia_movimiento="+ "'" + secuencia_movimiento + "'";
	
		us = cnx.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);
	
		// log.info("Si el ls_sql Doc: " + ls_sql);
	
		while (rs.next()) {
	
			ls_max_estado_movimiento = rs.getString("estado_movimiento");
			// log.info("El ls_descripcion_oficina: " +
			// ls_descripcion_oficina);
		}
		rs.close();
	
		return ls_max_estado_movimiento.trim();
	}

	public boolean validaNombreDelArchivo(String fileName) {
		// TODO Auto-generated method stub
		boolean resultado = true;
		String caracteres_no_validos[]={"%","`","~","º","´"};
		
		for(int i=0;i<caracteres_no_validos.length && resultado;i++){
			if(fileName.contains(caracteres_no_validos[i])){
				resultado=false;
			}
		}
		
		return resultado;
	}

	public String convertir_comillas(String parametro) {
		// TODO Auto-generated method stub
		
		String comillas = "\""; 
		String cambio = "COMILLA_DOBLE";
		String comillas_simple="'";
		String cambio_simple="COMILLA_SIMPLE";
		String asunto_documento =parametro;
		
		if(asunto_documento!=null){
			asunto_documento=asunto_documento.replaceAll(comillas, cambio);
			asunto_documento=asunto_documento.replaceAll(comillas_simple, cambio_simple);
		}else{
			asunto_documento="";
		}
		
		//log.info("Nuevo asunto:"+asunto_documento);
		return asunto_documento;
	}

	public String convertir_comillas_simples(String observacion) {
		String cadena="";
      if(observacion!=null)
      {
    	  cadena=observacion.replace("'", "''");
      }
		return cadena;
	}

	public String validar_nulo(String codigo_oficina) {
		// TODO Auto-generated method stub
		if(codigo_oficina==null || codigo_oficina.equals(""))
			return "0";
		else
			return codigo_oficina;
	}

	public boolean esFechaValida(String sle_fecha_inicio) {
		// TODO Auto-generated method stub
		boolean resultado = true;
		if(sle_fecha_inicio==null) 
			return false;
		
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date fecha= formato.parse(sle_fecha_inicio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			resultado = false;
		}
		
		return resultado;
	}
	
	public String cambiarFormato(String sle_fecha_inicio) {
		// TODO Auto-generated method stub
		String resultado = "";
		if(sle_fecha_inicio==null) 
			return "";
		
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formato_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha=null;
		try {
			fecha= formato.parse(sle_fecha_inicio);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
		}
		if(fecha!=null)
			resultado= formato_yyyy_MM_dd.format(fecha);
		
		return resultado;
	}

	public String transNulo(String codigo_oficina) {
		// TODO Auto-generated method stub
		return codigo_oficina==null?"":codigo_oficina;
	}

	public boolean esNulo(String codigo_oficina) {
		// TODO Auto-generated method stub
		return codigo_oficina ==null || codigo_oficina.trim().equals("");
	}
	
	public String convertirFecha(Date fecha){
		String resultado ="";
		if(fecha!=null){
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		resultado = formato.format(fecha);
		}
		return resultado;
	}

	public Date convertirFechaDate(String fecha_envio) {
		// TODO Auto-generated method stub
		Date fecha = null;
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			fecha= formato.parse(fecha_envio);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
		}
		return fecha;
	}

	public String of_nombre_estado_seguimiento(Connection cnx, String codigo_estado) throws SQLException {
		// TODO Auto-generated method stub
		return of_nombre_estado(cnx, codigo_estado);
	}

	public String of_validar_clave_expediente(Connection cnx, String password) throws SQLException {
		// TODO Auto-generated method stub
		Statement us = null;
		ResultSet rs = null;
		String ls_valor = "";

		// String ls_sql = "select * from tramite.oficinas where
		// codigo_oficina=" + "'" + cod_oficina + "'";
		String ls_sql = "select count(clave) as valor from tramite.documento_expediente_clave where clave = "
				+ "'" + password + "'";

		us = cnx.createStatement();
		// log.info("El us"+ us);
		rs = us.executeQuery(ls_sql);
		// log.info("El rs"+ rs);

		// log.info("Si el ls_sql Doc: " + ls_sql);

		while (rs.next()) {

			ls_valor = rs.getString("valor");
			// log.info("El ls_valor medio..: " + ls_valor);
		}
		rs.close();

		return ls_valor;
	}
/*
 * Creado por Moises Pelaez
 * Para obtener los el codigo_personal en algunso casos DIRECCIONES de sus secretaria y en lo demas del
 * el responsable, excepto el de OGA que es para ambos casos.
 * Fecha:27-10-2011
 * 
 * **/
	public ArrayList<Integer> of_codigo_oficina_persona_correo(Connection cnx, String codigo_oficina) throws SQLException {
		
		ArrayList<Integer>  myListCodigos= new ArrayList<Integer>();
		Statement us = null;
		ResultSet rs = null;
		int ls_codigo_personal;
		String ls_sql="";
		int cod_oficina=Integer.parseInt(codigo_oficina);
		switch (cod_oficina) {
		case 2://SG
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and cargo_personal =10";			
			break;
		case 4://OGA
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and cargo_personal =10 ";
			ls_sql=ls_sql + "UNION " +"select codigo_personal from tramite.personal where codigo_oficina="
			+ "'" + codigo_oficina + "'" + " and es_responsable ='S'";
			log.info("sql_OGA=" +ls_sql);
			
			break;
		case 5://DSIC
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and cargo_personal =10";			
			break;
			
		case 6://OAJ
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and cargo_personal =10";			
			break;
			
		case 7://OPP
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and cargo_personal =10";			
			break;
			
		case 8://FONDECYT
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and cargo_personal =10";			
			break;
			
		case 9://OCI
			ls_sql = "select tp.codigo_personal from tramite.personal tp, tramite.usuarios tu where tp.codigo_oficina ="
				+ "'" + codigo_oficina + "'" + " and tp.cargo_personal =10 and tu.estado LIKE 'A' and tp.c_usuario = tu.usuario";			
			break;
			
		case 10://DPP
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and cargo_personal =10";			
			break;
			
		case 11://DAG
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and cargo_personal =10";			
			break;
		
		case 12://DAG
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and cargo_personal =10";			
			break;
			
		case 13://DPIT
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and cargo_personal =10";			
			break;
		
		case 14://DPIT
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and cargo_personal =10";			
			break;	
			

		default:
			ls_sql = "select codigo_personal from tramite.personal where codigo_oficina="
				+ "'" + codigo_oficina + "'" + " and es_responsable ='S'";
			break;
			
		}

		

		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		while (rs.next()) {
			ls_codigo_personal = rs.getInt("codigo_personal");
			myListCodigos.add(ls_codigo_personal);
			//log.info("codigo_personal= " +ls_codigo_personal);
			log.info("El cod personal: " +ls_codigo_personal);
		}
		rs.close();

		return myListCodigos;
	}
	
	/**
	 * Nuevo
	 */
	public String of_usuario_destino(Connection cnx, String codigo_personal)
	throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		String usuario_destino = "";

		String ls_sql = "select c_usuario from tramite.personal where codigo_personal="+codigo_personal;

		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		log.info("El rs" + rs);


		while (rs.next()) {

			usuario_destino = rs.getString("c_usuario");
			// log.info("El ls_codigo_oficina: " + ls_codigo_oficina);
		}
		rs.close();

		return usuario_destino;
	}
	
	public int of_grupo_oficina(Connection cnx, String codigo_oficina)
	throws SQLException {

		Statement us = null;
		ResultSet rs = null;
		int grupo_oficina=0;

		StringBuffer ls_sql = new StringBuffer("select grupo_oficina  from tramite.oficinas where codigo_oficina ="+codigo_oficina); 

		us = cnx.createStatement();
		rs = us.executeQuery(ls_sql.toString());
		log.info("El rs" + rs);


		while (rs.next()) {

			grupo_oficina = rs.getInt("grupo_oficina");
			// log.info("El ls_codigo_oficina: " + ls_codigo_oficina);
		}
		rs.close();

		return grupo_oficina;
	}
	
	public String iniciales(String firmadopor){
	String iniciales="";
	String temp="";
	String nombrecompleto[];
	firmadopor=firmadopor.trim();
	
	nombrecompleto= firmadopor.split(" ");
	System.out.println(firmadopor);
    for(int i=0; i<nombrecompleto.length; i++){
    	temp = nombrecompleto[i].trim();
    	if(!temp.equals("")){
    	//System.out.println(" --> "+temp);
    	//System.out.println("-> "+temp.charAt(0));
    	iniciales=iniciales+temp.charAt(0);
    	}
    }
    
	return iniciales.trim().toUpperCase();
	}
	
	public String getFechaActualyHoraActualTexto(){
		Date date = new Date();
		  //Caso 1: obtener la hora y salida por pantalla con formato:
		  DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		  System.out.println("Hora: "+hourFormat.format(date));
		  //Caso 2: obtener la fecha y salida por pantalla con formato:
		  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  System.out.println("Fecha: "+dateFormat.format(date));
		  //Caso 3: obtenerhora y fecha y salida por pantalla con formato:
		  DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		  System.out.println("Hora y fecha: "+hourdateFormat.format(date));
		    
		  String horayfecha=dateFormat.format(date).toString()+" "+hourFormat.format(date).toString();
		  horayfecha=horayfecha.replace("/", "");
		  horayfecha=horayfecha.replace(":", "");
		  horayfecha=horayfecha.replace(" ", "_");
		  
		  return horayfecha+"_";
	}
	
	public String getAnioActualTexto(){
		Date date = new Date();
		  
		  //Caso 2: obtener la fecha y salida por pantalla con formato:
		  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  System.out.println("Fecha: "+dateFormat.format(date));
		 		    
		  String horayfecha=dateFormat.format(date).toString();
		  horayfecha=horayfecha.replace("/", "");
		  
		  return "/"+horayfecha.substring(4, 8);
	}
	
	
}
