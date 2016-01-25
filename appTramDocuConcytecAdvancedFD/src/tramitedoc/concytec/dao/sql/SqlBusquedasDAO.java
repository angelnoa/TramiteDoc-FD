package tramitedoc.concytec.dao.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import tramitedoc.concytec.bean.BCourier;
import tramitedoc.concytec.bean.BDocumento;
import tramitedoc.concytec.bean.BPersona;
import tramitedoc.concytec.bean.BTipoServicio;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.dao.interfaces.IBusquedasDAO;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.ObtieneConexion;

public class SqlBusquedasDAO implements IBusquedasDAO {

	private String of_nombre_oficina(String cod_oficina) {
		// TODO Auto-generated method stub
		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		Statement us = null;
		ResultSet rs = null;

		String resultado =null;
		try {
			String ls_sql = "select descripcion_oficina from tramite.oficinas where codigo_oficina='" + cod_oficina + "'";

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			
			if(rs.next()){
				
				resultado = rs.getString("descripcion_oficina");
				
			}
			x.cerrar_conexion(cnx, rs);

		} catch (Exception e) {
			x.cerrar_conexion(cnx, rs);
		}
		
		return resultado;
	}
	
	public BTipoServicio buscar_tipo_servicio(int tipo_servicio) {
		// TODO Auto-generated method stub
		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		Statement us = null;
		ResultSet rs = null;

		BTipoServicio c=null;
		try {
			String ls_sql = "select c.* from  tramite.tipo_servicio c where codigo_tipo_servicio = "+tipo_servicio;

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			
			if(rs.next()){
				c=new BTipoServicio();
				c.setCodigo_tipo_servicio(rs.getInt("codigo_tipo_servicio"));
				c.setDescripcion(rs.getString("descripcion"));
				
			}
			x.cerrar_conexion(cnx, rs);

		} catch (Exception e) {
			x.cerrar_conexion(cnx, rs);
		}
		
		return c;
	}
	public BPersona buscar_persona(int id_persona) {
		// TODO Auto-generated method stub
		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		Statement us = null;
		ResultSet rs = null;

		BPersona c=null;
		try {
			String ls_sql = "select c.* from  tramite.persona c where codigo_persona = "+id_persona;

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			
			if(rs.next()){
				c=new BPersona();
				c.setCodigo_persona(""+rs.getInt("codigo_persona"));
				c.setNombre_persona(rs.getString("nombre_persona"));
				
			}
			x.cerrar_conexion(cnx, rs);

		} catch (Exception e) {
			x.cerrar_conexion(cnx, rs);
		}
		
		return c;

	}
	
	public BUsuario buscar_usuario(String codigo) {
		// TODO Auto-generated method stub
		

		
		Statement us = null;
		ResultSet rs = null;

		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		BUsuario user=null;
		try {

			String ls_sql = "";
			
				ls_sql = "select u.usuario,u.nombres,u.apellidos,of.codigo_oficina, of.nombre_corto, of.descripcion_oficina "
						+ "from tramite.usuarios u "
						+ " inner join tramite.personal p  on p.c_usuario = u.usuario "
						+ "inner join tramite.oficinas of on of.codigo_oficina = p.codigo_oficina "
						+ "where u.usuario ilike '" + codigo + "'";
			

			// //System.out.println("El s_sql"+ ls_sql);

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			// //System.out.println("El rs"+ rs);
			if (rs.next()) {

				user = new BUsuario();
				user.setNombres(rs.getString("nombres"));
				user.setApellidos(rs.getString("apellidos"));
				user.setCodigo_oficina(rs.getString("codigo_oficina"));
				user.setUsuario(rs.getString("usuario"));
				user.setDescripcion_oficina(rs.getString("descripcion_oficina"));

			}

			x.cerrar_conexion(cnx, rs);
		} catch (SQLException e) {

			x.cerrar_conexion(cnx, rs);
		}
		
		return user;

	}
	public BCourier buscar_courier(int id_courier) throws Exception {
		// TODO Auto-generated method stub

		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		Statement us = null;
		ResultSet rs = null;

		BCourier c=null;
		try {
			String ls_sql = "select c.* from  tramite.courier c where id_courier = "+id_courier;

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			
			if(rs.next()){
				c=new BCourier();
				c.setId_courier(id_courier);
				c.setNombre_courier(rs.getString("nombre_courier"));
				c.setCodigo_courier(rs.getString("codigo_courier"));
			}
			x.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {
			x.cerrar_conexion(cnx, rs);
		}
		
		return c;
	}

	public Collection documentos_pend_cerrar() throws Exception {
		// TODO Auto-generated method stub
		Collection documentos = new ArrayList();

		
		Statement us = null;
		ResultSet rs = null;

		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		try {

			String ls_sql = "  select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, "
					+ "md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, "
					+ "md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta, "
					+ "md.codigo_expediente,md.oficina_origen,md.oficina_deriva,md.correlativo_deriva,md.observa_registro,md.codigo_recepcion, "
					+ "tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento, "
					+ "dc.numero_documento_ant,de.origen_documento,de.destino_documento,de.desc_origen, "
					+ "de.tipo,de.remitente,de.destinatario,of.codigo_oficina,of.descripcion_oficina, "
					+ "of_a.descripcion_oficina as descripcion_destino,pr.codigo_personal,pr.nombre_personal "
					+ " from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc, "
					+ "tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr "
					+ " where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and "
					+ " dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento "
					+ " and de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and "
					+ "de.destino_documento=of_a.codigo_oficina and md.estado_movimiento ='8' and "
					+ " md.codigo_fondo like '01' order by codigo_documento desc;";

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			BDocumento doc = null;
			// //System.out.println("El rs"+ rs);
			Funciones f = new Funciones();
			while (rs.next()) {

				doc=new BDocumento();
				doc.setCodigo_documento(rs.getString("codigo_documento"));
				doc.setNumero_documento(rs.getString("numero_documento"));
				doc.setAsunto_documento(rs.getString("asunto_documento"));
				doc.setCodigo_oficina(of_nombre_oficina(rs.getString("oficina_origen")));
				doc.setRemitente(rs.getString("remitente"));
				doc.setSecuencia_movimiento(rs.getString("secuencia_movimiento"));
				/*
				doc = new Hashtable();
				
				doc.put("codigo_documento",rs.getString("codigo_documento"));
				doc.put("numero_documento",rs.getString("numero_documento"));
				doc.put("asunto_documento",rs.getString("asunto_documento"));
				
				doc.put("oficina", of_nombre_oficina(rs.getString("oficina_origen")));
				doc.put("remitente", rs.getString("remitente"));
				doc.put("secuencia_movimiento",rs.getString("secuencia_movimiento"));
				*/

				documentos.add(doc);
			}

			rs.close();
			if (cnx != null) {
				cnx.close();
			}
		} catch (SQLException e) {

			if (cnx != null) {
				cnx.close();
			}
		}
		
		return documentos;
	}

	
	public Collection buscar_usuarios(String codigo, String nombre)
			throws Exception {
		// TODO Auto-generated method stub
		Collection usuarios = new ArrayList();

		BUsuario user = null;
		Statement us = null;
		ResultSet rs = null;

		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		try {

			String ls_sql = "";
			if (codigo.equals("")) {
				ls_sql = "select u.usuario,u.nombres,u.apellidos,of.codigo_oficina, of.nombre_corto, of.descripcion_oficina "
						+ "from tramite.usuarios u "
						+ " inner join tramite.personal p  on p.c_usuario = u.usuario "
						+ "inner join tramite.oficinas of on of.codigo_oficina = p.codigo_oficina "
						+ " where u.nombres ilike '%"
						+ nombre
						+ "%' or u.apellidos ilike '%" + nombre + "%'";
			} else {
				ls_sql = "select u.usuario,u.nombres,u.apellidos,of.codigo_oficina, of.nombre_corto, of.descripcion_oficina "
						+ "from tramite.usuarios u "
						+ " inner join tramite.personal p  on p.c_usuario = u.usuario "
						+ "inner join tramite.oficinas of on of.codigo_oficina = p.codigo_oficina "
						+ "where u.usuario ilike '" + codigo + "%'";
			}

			// //System.out.println("El s_sql"+ ls_sql);

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			// //System.out.println("El rs"+ rs);
			while (rs.next()) {

				user = new BUsuario();
				user.setNombres(rs.getString("nombres"));
				user.setApellidos(rs.getString("apellidos"));
				user.setCodigo_oficina(rs.getString("codigo_oficina"));
				user.setUsuario(rs.getString("usuario"));
				user.setDescripcion_oficina(rs.getString("descripcion_oficina"));

				usuarios.add(user);
			}

			x.cerrar_conexion(cnx, rs);
		} catch (SQLException e) {

			x.cerrar_conexion(cnx, rs);
		}
		
		return usuarios;

	}

	public Collection buscar_instituciones(String codigo, String nombre)
			throws Exception {
		// TODO Auto-generated method stub
		Collection instituciones = new ArrayList();

		BUsuario BUsuarioVO = null;
		Statement us = null;
		ResultSet rs = null;

		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		try {

			String ls_sql = "";
			if (codigo.equals("")) {
				ls_sql = "select * from tramite.persona where  nombre_persona ilike '%"
						+ nombre + "%'";
			} else {
				ls_sql = "select * from tramite.persona where  codigo_persona = "
						+ codigo;
			}

			// //System.out.println("El s_sql"+ ls_sql);

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			// //System.out.println("El rs"+ rs);
			while (rs.next()) {

				BPersona persona = new BPersona();

				persona.setCodigo_persona(rs.getString("codigo_persona"));
				persona.setNombre_persona(rs.getString("nombre_persona"));
				instituciones.add(persona);
			}

			rs.close();
			if (cnx != null) {
				cnx.close();
			}
		} catch (SQLException e) {

			if (cnx != null) {
				cnx.close();
			}
		}
		
		return instituciones;

	}
}
