package tramitedoc.concytec.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import tramitedoc.concytec.bean.BCourier;
import tramitedoc.concytec.bean.BDocumento;
import tramitedoc.concytec.bean.BPaquete;
import tramitedoc.concytec.bean.BPersona;
import tramitedoc.concytec.bean.BTipoServicio;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.dao.interfaces.IBusquedasDAO;
import tramitedoc.concytec.dao.interfaces.ICierreDAO;
import tramitedoc.concytec.form.FFormMantCerrar;
import tramitedoc.concytec.form.FFormMantPrecerrar;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.ObtieneConexion;

public class SqlCierreDAO implements ICierreDAO {

	public int actualizarEstadoDocumentos(int codigo_cierre,int estado) throws Exception {
		// TODO Auto-generated method stub

		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		int resultado = 0;
		try {

			String ls_sql = "select * from tramite.paquete_documento where codigo_paquete = "+codigo_cierre;
			
			st = cnx.createStatement();			
			rs = st.executeQuery(ls_sql);
			
			while(rs.next()){
				String cod_doc =rs.getString("codigo_documento");
				String sec_mov = rs.getString("secuencia_movimiento");
				ls_sql="update tramite.movimientos_doc set estado_movimiento = " +estado+
						" where codigo_documento = "+cod_doc +" and secuencia_movimiento = "+sec_mov;
				ps=cnx.prepareStatement(ls_sql);
				resultado +=ps.executeUpdate();
				
				ls_sql="insert into tramite.recorrido_doc " +
						"select * from tramite.movimientos_doc where codigo_documento = "+cod_doc+
						" and secuencia_movimiento ="+sec_mov+" and estado_movimiento = "+ estado;
				ps=cnx.prepareStatement(ls_sql);
				ps.executeUpdate();
				
			}

			x.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {

			x.cerrar_conexion(cnx, rs);
			e.printStackTrace();
		}
		
		return resultado;

	}
	public int cerrar_documentos(FFormMantCerrar oForm) throws Exception {
		// TODO Auto-generated method stub
		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		int resultado = 0;
		Funciones f=new Funciones();
		try {

			String ls_sql = "update tramite.paquete_cierre set fecha_recepcion = ?::date," +
					" codigo_guia_courier_recep = ? where codigo_cierre = ?";
			
			
			ps = cnx.prepareStatement(ls_sql);
			ps.setString(1, f.cambiarFormato(oForm.getFecha_recepcion()));
			ps.setString(2,oForm.getCodigo_guia_courier_recep());
			ps.setInt(3, oForm.getCodigo_cierre());
			
			ps.executeUpdate();

			x.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {

			x.cerrar_conexion(cnx, rs);
			e.printStackTrace();
		}
		
		return resultado;
	}
	public BPaquete buscar_paquete(int id_paquete) throws Exception {
		

		BPaquete paq = null;
		Statement us = null;
		ResultSet rs = null;

		IBusquedasDAO ibu=new SqlBusquedasDAO();
		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		Funciones f= new Funciones();
		try {

			String ls_sql = " select c.* from  tramite.paquete_cierre c " +
					"where c.codigo_cierre = "+id_paquete+ " order by codigo_cierre desc";

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			// //System.out.println("El rs"+ rs);
			if (rs.next()) {

				paq = new BPaquete();
				String fecha_formateada = f.convertirFecha(rs.getDate("fecha_envio"));
				paq.setFecha_envio(fecha_formateada);
				paq.setCourier(ibu.buscar_courier(rs.getInt("courier")));
				paq.setDestinatario(ibu.buscar_persona(rs.getInt("destinatario")));
				paq.setRemitente(ibu.buscar_usuario(rs.getString("remitente")));
				paq.setFecha_recepcion(rs.getDate("fecha_recepcion"));
				paq.setCodigo_guia_courier(rs.getString("codigo_guia_courier"));
				paq.setTipo_servicio(ibu.buscar_tipo_servicio(rs.getInt("tipo_servicio")));
				paq.setObjeto_envio(rs.getString("objeto_envio"));
				paq.setDocumentos(buscarDocumentos(id_paquete));
			}

			x.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {

			x.cerrar_conexion(cnx, rs);
		}
		
		return paq;

	}
	public Collection buscarDocumentos(int id_paquete) {
		// TODO Auto-generated method stub
		
		Statement us = null;
		ResultSet rs = null;
		Collection documentos = new ArrayList();
		
		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		Funciones f= new Funciones();
		try {

			String ls_sql = "select codigo_documento,secuencia_movimiento from tramite.paquete_documento  where codigo_paquete = "+id_paquete;

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			// //System.out.println("El rs"+ rs);
			BDocumento doc=null;
			while(rs.next()) {
				doc=new BDocumento();
				doc.setCodigo_documento(rs.getString("codigo_documento"));
				doc.setSecuencia_movimiento(rs.getString("secuencia_movimiento"));
				documentos.add(doc);
			}

			x.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {

			x.cerrar_conexion(cnx, rs);
		}
		
		return documentos;

	}
	public Collection pendientesCierre() throws Exception {
		// TODO Auto-generated method stub
		Collection documentos = new ArrayList();

		BPaquete paq = null;
		Statement us = null;
		ResultSet rs = null;

		IBusquedasDAO ibu=new SqlBusquedasDAO();
		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		Funciones f=new Funciones();
		try {

			String ls_sql = " select c.codigo_cierre,c.fecha_envio,pe.nombre_persona as destinatario,c.objeto_envio, " +
					"se.descripcion as tipo_servicio, cr.codigo_courier || ' - ' ||cr.nombre_courier as courier, c.codigo_guia_courier," +
					" u.nombres || ' ' || u.apellidos as remitente, c.codigo_guia_courier_recep,c.fecha_recepcion " +
					"from  tramite.paquete_cierre c left join tramite.courier cr on cr.id_courier = c.courier " +
					"left join tramite.persona pe on pe.codigo_persona = c.destinatario " +
					"left join tramite.usuarios u on u.usuario = c.remitente " +
					"inner join tramite.personal p  on p.c_usuario = u.usuario " +
					"inner join tramite.oficinas of on of.codigo_oficina = p.codigo_oficina " +
					"left join tramite.tipo_servicio se on se.codigo_tipo_servicio = c.tipo_servicio " +
					"where c.fecha_recepcion is null order by codigo_cierre desc;";

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			// //System.out.println("El rs"+ rs);
			while (rs.next()) {

				paq = new BPaquete();
				String var_ini = "<label style='cursor: hand;' align='center' " 
					+ "onclick=\"ver_detalle("+ rs.getInt("codigo_cierre")+");\" >";
				String var_fin = "</label>";
				String fecha_formateada = f.convertirFecha(rs.getDate("fecha_envio"));
				paq.setFecha_envio(var_ini+fecha_formateada+var_fin);
				String crs[]= (rs.getString("courier")==null?"":rs.getString("courier")).split(" - ");
				paq.setCourier(new BCourier(0, crs[0], crs[1]));
				paq.setDestinatario(new BPersona(rs.getString("destinatario")));
				paq.setRemitente(new BUsuario(rs.getString("remitente")));
				paq.setFecha_recepcion(rs.getDate("fecha_recepcion"));
				paq.setCodigo_guia_courier(rs.getString("codigo_guia_courier"));
				paq.setTipo_servicio(new BTipoServicio(0,rs.getString("tipo_servicio")));
				paq.setDocumentos(buscarDocumentos(rs.getInt("codigo_cierre")));			

				documentos.add(paq);
			}

			x.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {

			x.cerrar_conexion(cnx, rs);
		}
		
		return documentos;

	}

	public int registrar_preCierre(FFormMantPrecerrar oForm) throws Exception {
		// TODO Auto-generated method stub
		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		Funciones f=new Funciones();
		int resultado = 0;
		try {

			String ls_sql = "select nextval('tramite.seq_cierre')";
			st = cnx.createStatement();
			rs = st.executeQuery(ls_sql);

			if (rs.next()) {
				int id_cierre = rs.getInt(1);
				ls_sql = "insert into tramite.paquete_cierre (codigo_cierre,fecha_envio,destinatario,remitente,objeto_envio,"
						+ "tipo_servicio,courier ,  codigo_guia_courier) values "
						+ "(?,?::date,?,?,?,?,?,?);";

				ps = cnx.prepareStatement(ls_sql);
				ps.setInt(1, id_cierre);
				String fecha_envio_formato = f.cambiarFormato(oForm.getFecha_envio());
				ps.setString(2, fecha_envio_formato);
				ps.setInt(3, oForm.getDestinatario());
				ps.setString(4, oForm.getRemitente());
				ps.setString(5, oForm.getObjeto_envio());
				ps.setInt(6, oForm.getTipo_servicio());
				ps.setInt(7, oForm.getCodigo_courier());
				ps.setString(8, oForm.getCodigo_guia_courier());
				if(oForm.getPaquete()!=null){
					Iterator it = oForm.getPaquete().iterator();
					while (it.hasNext()) {
						BDocumento doc = (BDocumento) it.next();
						if (doc.isSeleccionado()) {
							registrar_paquete_documento(id_cierre, doc);
							actualizarEstadoDocumentos(id_cierre, 6);
						}
					}
				}
				
				resultado = ps.executeUpdate();
			}
			x.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {

			x.cerrar_conexion(cnx, rs);
			e.printStackTrace();
		}
		
		return resultado;
	}

	private int registrar_paquete_documento(int id_cierre, BDocumento doc)
			throws Exception {

		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();

		Statement st = null;
		PreparedStatement ps = null;
		int resultado = 0;

		try {
			String ls_sql = "insert into tramite.paquete_documento (codigo_paquete,codigo_documento,secuencia_movimiento) values(?,?::integer,?::integer);";

			ps = cnx.prepareStatement(ls_sql);
			ps.setInt(1, id_cierre);
			ps.setString(2, doc.getCodigo_documento());
			ps.setString(3,doc.getSecuencia_movimiento());
			resultado = ps.executeUpdate();
			
			x.cerrar_conexion(cnx);
		} catch (SQLException e) {

			x.cerrar_conexion(cnx);
			e.printStackTrace();
		}
		
		return resultado;

	}

	public Collection lista_couriers() throws Exception {
		// TODO Auto-generated method stub
		
		Collection couriers = new ArrayList();

		BCourier cou = null;
		Statement us = null;
		ResultSet rs = null;

		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		try {

			String ls_sql = " select c.* from  tramite.courier c";

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			// //System.out.println("El rs"+ rs);
			while (rs.next()) {

				cou = new BCourier();
				cou.setCodigo_courier(rs.getString("codigo_courier"));
				cou.setId_courier(rs.getInt("id_courier"));
				cou.setNombre_courier(rs.getString("nombre_courier"));

				couriers.add(cou);
			}

			x.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {

			x.cerrar_conexion(cnx, rs);
			e.printStackTrace();
		}
		
		return couriers;
	}

	public Collection lista_tipo_servicios() throws Exception {
		// TODO Auto-generated method stub
		Collection tipo_servicios = new ArrayList();
		
		
		Collection tipoServs = new ArrayList();

		BTipoServicio tipServ = null;
		Statement us = null;
		ResultSet rs = null;

		ObtieneConexion x = new ObtieneConexion();
		Connection cnx = x.getConnection();
		try {

			String ls_sql = " select * from  tramite.tipo_servicio ";

			us = cnx.createStatement();
			// //System.out.println("El us"+ us);
			rs = us.executeQuery(ls_sql);
			// //System.out.println("El rs"+ rs);
			while (rs.next()) {

				tipServ = new BTipoServicio();
				tipServ.setCodigo_tipo_servicio(rs.getInt("codigo_tipo_servicio"));
				tipServ.setDescripcion(rs.getString("descripcion"));

				tipoServs.add(tipServ);
			}

			x.cerrar_conexion(cnx, rs);

		} catch (SQLException e) {

			x.cerrar_conexion(cnx, rs);
			e.printStackTrace();
		}
		
		return tipoServs;
	}

}
