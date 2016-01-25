/*
 * Created on 27/11/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tramitedoc.concytec.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.dao.interfaces.ILoginDAO;
import tramitedoc.concytec.util.ObtieneConexion;

/**
 * @author Administrador
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SqlLoginDAO implements ILoginDAO {
	/**
	 * Autenticamos los credenciales del usuario y devolvemos un VO o null.
	 **/
	public BUsuario fjauthenticate(Connection cnx, String usuario, String clave) {
		BUsuario userVO = null;
		ResultSet rs = null;
		PreparedStatement us = null;
		ObtieneConexion x = new ObtieneConexion();
		Connection con = x.getConnection();
		try {

			/*StringBuffer sql = new StringBuffer("select distinct usu.usuario, usu.clave, usu.estado, usu.perfil, usu.flag, pr.codigo_oficina,ofi.siglas_oficina, ofi.descripcion_oficina,ofi.nombre_corto,ofi.orden,tup.nombre,pr.validacion, ca.nombrecargo "); 
            sql.append(" from  tramite.oficinas ofi, tramite.personal pr ");
            sql.append(" left join  tramite.usuarios usu  on  usu.usuario=pr.c_usuario ");
            sql.append(" left join tramite.tipo_usuario tup on tup.codigo=usu.flag ");
            sql.append(" left join tramite.cargo  ca  on  ca.codigo_cargo = pr.cargo_personal ");
            sql.append(" where ofi.codigo_oficina = pr.codigo_oficina ");
            sql.append(" and usu.estado='A' ");
            sql.append(" and usu.usuario = '"+usuario+"' ");
            sql.append(" and usu.clave = '"+clave+"' ");*/
			
			StringBuffer sql = new StringBuffer("select usu.* from tramite.usuarios usu  ");
            sql.append(" where  usu.estado='A' ");
            sql.append(" and usu.usuario = '"+usuario+"' ");
            sql.append(" and usu.clave = '"+clave+"' ");

			String query = sql.toString();
			System.out.println(query);
			us = con.prepareStatement(query);
			rs = us.executeQuery();

			if (rs.next()) {
				userVO = new BUsuario();
				userVO.setUsuario(rs.getString("usuario"));
				userVO.setClave(rs.getString("clave"));
				userVO.setEstado(rs.getString("estado"));
				userVO.setPerfil(rs.getString("perfil"));
				userVO.setFlag(rs.getString("flag"));
				userVO.setSede(String.valueOf(rs.getInt("sede")));
				//userVO.setNombre_corto(" ");
			}

			x.cerrar_conexion(con, rs);
			us.close();

		} catch (SQLException se) {
			
			System.err.println("Se ha producido un error de BD.");
			System.err.println(se.getMessage());
			x.cerrar_conexion(con, rs);
		}
		return userVO;
	}

	public int cambiarPassword(String usuario, String new_password) {
		// TODO Auto-generated method stub

		ResultSet rs = null;
		PreparedStatement us = null;
		ObtieneConexion x = new ObtieneConexion();
		Connection con = x.getConnection();
		int resultado = 0;
		try {	
			StringBuffer ls_sql = new StringBuffer(
					"update tramite.usuarios set clave = ? where usuario = ?");

			String query = ls_sql.toString();
			us = con.prepareStatement(query);
			us.setString(1, new_password);
			us.setString(2, usuario);
			resultado = us.executeUpdate();
			
			x.cerrar_conexion(con, rs);

		} catch (SQLException se) {
			System.err.println("Se ha producido un error de BD.");
			System.err.println(se.getMessage());
			x.cerrar_conexion(con, rs);
		}

		return resultado;
	}

}
