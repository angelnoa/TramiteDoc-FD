package tramitedoc.concytec.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tramitedoc.concytec.bean.BUsuarioSel;
import tramitedoc.concytec.dao.interfaces.IUsuarioSelDAO;
import tramitedoc.concytec.util.ObtieneConexion;

public class SqlUsuarioSelDAO implements IUsuarioSelDAO{

	@Override
	public List<BUsuarioSel> getUsersByEmail(String email) {
		ObtieneConexion x = new ObtieneConexion();
		Connection cn = x.getConnection();		
        ArrayList<BUsuarioSel> usuarios = new ArrayList<BUsuarioSel>();
        
        try {

            StringBuffer sql = new StringBuffer("select distinct usu.usuario, usu.email, pr.codigo_personal,pr.nombre_personal, pr.codigo_oficina,ofi.siglas_oficina, ofi.descripcion_oficina,ofi.nombre_corto,ofi.orden,tup.nombre,pr.validacion, ca.nombrecargo, ofi.institucion "); 
            
            sql.append(" ,(select descripcion_corta from tramite.tbl_elemento_catalogo ");
            sql.append("	where id_catalogo='tipo_sede' and id_elemento=ofi.institucion) as nombre_sede ");
            
            sql.append(" from  tramite.oficinas ofi, tramite.personal pr ");
            sql.append(" left join  tramite.usuarios usu  on  usu.usuario=pr.c_usuario ");
            sql.append(" left join tramite.tipo_usuario tup on tup.codigo=usu.flag ");
            sql.append(" left join tramite.cargo  ca  on  ca.codigo_cargo = pr.cargo_personal ");
            sql.append(" where usu.estado='A' and ofi.codigo_oficina = pr.codigo_oficina ");
            sql.append(" and usu.email = '" + email + "' ");
            
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BUsuarioSel usuario = new BUsuarioSel();
                usuario.setUsuario(rs.getString(1));
                usuario.setNombre_corto(rs.getString(8));
                usuario.setNombre_cargo(rs.getString(12));
                usuario.setInstitucion(rs.getString(13));
                
                usuario.setNombre_institucion(rs.getString(14));
                
                usuarios.add(usuario);
            }
            
            
            StringBuffer sql2 = new StringBuffer("select distinct usu.usuario, usu.sede  "); 
            sql2.append(" ,(select descripcion_corta from tramite.tbl_elemento_catalogo ");
            sql2.append("	where id_catalogo='tipo_sede' and id_elemento=usu.sede) as nombre_sede ");
            
            sql2.append(" from  tramite.usuarios usu ");
            sql2.append(" where usu.estado='A' ");
            sql2.append(" and usu.flag = 'A' ");
            sql2.append(" and usu.email = '" + email + "' ");
            
           ps = cn.prepareStatement(sql2.toString());
           rs = ps.executeQuery();

            while (rs.next()) {
                BUsuarioSel usuario = new BUsuarioSel();
                usuario.setUsuario(rs.getString(1));
                /*usuario.setNombre_corto(rs.getString(8));
                usuario.setNombre_cargo(rs.getString(12));
                usuario.setInstitucion(rs.getString(13));*/
                
                //if(usuario.getUsuario().equals("admin")){
                	usuario.setNombre_corto("ADMINISTRADOR");
                	usuario.setInstitucion(rs.getString(2));
                	 usuario.setNombre_institucion(rs.getString(3));
                	 
                	usuarios.add(usuario);
                	//break;
                //}
                           
            }
            
            ps.close();
            x.cerrar_conexion(cn,rs);
            
            
        } catch (SQLException ex) {
            System.out.println("ERROR LISTAR USUARIOS: " + ex.getMessage());
        } 

        return usuarios;	
	}

}
