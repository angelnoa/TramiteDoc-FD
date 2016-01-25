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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tramitedoc.concytec.bean.BCargo;
import tramitedoc.concytec.bean.BListaGeneral;
import tramitedoc.concytec.bean.BOficinas;
import tramitedoc.concytec.bean.BPersonal;
import tramitedoc.concytec.bean.BSolicitud;
import tramitedoc.concytec.bean.BTipoDocumento;
import tramitedoc.concytec.bean.BTipoUsuario;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.dao.interfaces.IAdministradorDAO;
import tramitedoc.concytec.util.Constantes;
import tramitedoc.concytec.util.IConstante;
import tramitedoc.concytec.util.ObtieneConexion;


/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SqlAdministradorDAO implements IAdministradorDAO{
	
	protected  Log log = LogFactory.getLog(SqlAdministradorDAO.class);
	
	/**************Metodo para la lista de usuarios***********/

public Collection  of_lista_tipo_usuarios(Connection cnx) throws Exception {
		
        log.info("ENTRO A  Lista de Usuarios");
		Collection ListaTipoUsuarios = new ArrayList();
		
		BTipoUsuario userVO = null;
		Statement us  = null;
		ResultSet rs = null;
       
        try {	
        	
        	String ls_sql = "select * from tramite.tipo_usuario  order by orden";
        	
	    	us = cnx.createStatement();
    		rs = us.executeQuery(ls_sql);
    		
    		while ( rs.next() ) { 
    			userVO=new BTipoUsuario();
    			userVO.setCodigo(rs.getString("codigo"));
    			userVO.setNombre(rs.getString("nombre"));
				ListaTipoUsuarios.add(userVO);
			}
            
			rs.close();
			
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return ListaTipoUsuarios;
	                
	}


public Collection  of_lista_tipo_sede(Connection cnx,int usuario_sistema) throws Exception {
	
    log.info("ENTRO A  of_lista_tipo_sede");
	Collection ListaTipoSedes = new ArrayList();
	
	BListaGeneral userVO = null;
	Statement us  = null;
	ResultSet rs = null;
   
    try {	
    	
    	StringBuffer ls_sql = new StringBuffer("select e.id_elemento, e.descripcion_corta ");
    	ls_sql.append(" from tramite.tbl_catalogo c ");
    	ls_sql.append(" left join tramite.tbl_elemento_catalogo e on c.id_catalogo=e.id_catalogo ");
    	ls_sql.append(" where c.id_catalogo='"+ IConstante.TIPO_SEDE +"' ");
    	
    	if(usuario_sistema!=1){
    		ls_sql.append(" and e.id_elemento="+usuario_sistema);
    	}
    	
    	
    	us = cnx.createStatement();
		rs = us.executeQuery(ls_sql.toString());
		
		while ( rs.next() ) { 
			userVO=new BListaGeneral();
			userVO.setCodigo(rs.getString("id_elemento"));
			userVO.setDescripcion(rs.getString("descripcion_corta"));
			ListaTipoSedes.add(userVO);
		}
        
		rs.close();
		
        
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return ListaTipoSedes;
                
}

	 public Collection  of_lista_usuarios(Connection cnx) throws Exception {
			
	        log.info("ENTRO A  Lista de Usuarios");
			Collection ListaUsuarios = new ArrayList();
			
			BUsuario userVO = null;
			Statement us  = null;
			ResultSet rs = null;
	       
	        try {	
	        	
	        	String ls_sql = "select * from tramite.usuarios order by usuario asc";
	        	
		    	us = cnx.createStatement();
	    		rs = us.executeQuery(ls_sql);
	    		
	    		while ( rs.next() ) { 
	    			
	    			Hashtable objUsuarios = new Hashtable();
					String seleccionar = "<label style='cursor: hand;' align='center' " 
					+ "onclick=\"fn_editar(" + "'" + rs.getString("usuario") + "', " + "'"
			        + rs.getString("clave") + "'"+ "," 
			        + "'"+ rs.getString("estado") + "'"+ "," 
			        + "'"+ rs.getString("flag") + "'"+ "," 
			        + "'"+ rs.getString("nombres") + "'"+ "," 
			        + "'"+ rs.getString("apellidos") + "'"+ "," 
			        + "'"+ rs.getString("email") + "'"
			        + ");\" " + ">ver</label>";
					
					objUsuarios.put("ACTION", seleccionar);
					objUsuarios.put("usuario", rs.getString("usuario"));
					objUsuarios.put("clave", rs.getString("clave"));
					objUsuarios.put("estado", rs.getString("estado"));
					objUsuarios.put("flag", rs.getString("flag"));
					objUsuarios.put("nombres", rs.getString("nombres"));
					objUsuarios.put("apellidos", rs.getString("apellidos"));
					objUsuarios.put("email", rs.getString("email"));					
					ListaUsuarios.add(objUsuarios);
					
				}
	            
				rs.close();
				
	            
	        } catch (SQLException e) {
	             e.printStackTrace();
	        }
	        return ListaUsuarios;
		                
		}
	 
	 public Collection of_lista_usuarios(Connection cnx,
			String codigoUsuarioBuscar) {
		// TODO Auto-generated method stub
		 log.info("ENTRO A  Lista de Usuarios");
			Collection ListaUsuarios = new ArrayList();
			
			BUsuario userVO = null;
			PreparedStatement us  = null;
			ResultSet rs = null;
	       
	        try {	
	        	
	        	String ls_sql = "select * from tramite.usuarios " +
	        			"where usuario = ? or nombres ilike '%'||?||'%' or apellidos ilike '%'||?||'%';";
	        	
		    	us = cnx.prepareStatement(ls_sql);
		    	us.setString(1, codigoUsuarioBuscar);
		    	us.setString(2, codigoUsuarioBuscar);
		    	us.setString(3, codigoUsuarioBuscar);
	    		rs = us.executeQuery();
	    		
	    		while ( rs.next() ) { 
	    			
	    			Hashtable objUsuarios = new Hashtable();
					String seleccionar = "<label style='cursor: hand;' align='center' " 
					+ "onclick=\"fn_editar(" + "'" + rs.getString("usuario") + "', " + "'"
			        + rs.getString("clave") + "'"+ "," 
			        + "'"+ rs.getString("estado") + "'"+ "," 
			        + "'"+ rs.getString("flag") + "'"+ "," 
			        + "'"+ rs.getString("nombres") + "'"+ "," 
			        + "'"+ rs.getString("apellidos") + "'"+ "," 
			        + "'"+ rs.getString("email") + "'"
			        + ");\" " + ">ver</label>";
					
					objUsuarios.put("ACTION", seleccionar);
					objUsuarios.put("usuario", rs.getString("usuario"));
					objUsuarios.put("clave", rs.getString("clave"));
					objUsuarios.put("estado", rs.getString("estado"));
					objUsuarios.put("flag", rs.getString("flag"));
					objUsuarios.put("nombres", rs.getString("nombres"));
					objUsuarios.put("apellidos", rs.getString("apellidos"));
					objUsuarios.put("email", rs.getString("email"));
					
					ListaUsuarios.add(objUsuarios);
					
				}
	            
				rs.close();
				
	            
	        } catch (SQLException e) {
	             e.printStackTrace();
	        }
	        return ListaUsuarios;
	}
	 
	 public Collection of_lista_usuarios(Connection cnx, String codigoUsuarioBuscar, int sede) {
			// TODO Auto-generated method stub
			 log.info("ENTRO A  busqueda Lista de Usuarios ");
				Collection ListaUsuarios = new ArrayList();
				
				BUsuario userVO = null;
				PreparedStatement us  = null;
				ResultSet rs = null;
		       
		        try {	
		        	
		        	StringBuilder ls_sql = new StringBuilder("select u.*, tu.nombre as tipo_usuario, e.descripcion_corta as tipo_sede ");
		        	ls_sql.append(" ,(case  u.estado when 'A' then 'Activo' when 'I' then 'Inactivo' else 'None' end ) as tipo_estado ");
		        	ls_sql.append(" from tramite.usuarios  u ");
		        	ls_sql.append(" left join tramite.tipo_usuario  tu  on u.flag=tu.codigo ");
		        	ls_sql.append(" left join tramite.tbl_elemento_catalogo  e  on u.sede=e.id_elemento  and  e.id_catalogo='tipo_sede' ");
		        	
		        	ls_sql.append(" where (u.usuario = ? or u.nombres ilike '%'||?||'%' or u.apellidos ilike '%'||?||'%' )");
		        	
		        	if(sede!=1){
		        		ls_sql.append(" and u.sede = ? ");
		        	}
		        	
		        	log.info("--> "+ls_sql);
		        	log.info("--> "+sede);
			    	us = cnx.prepareStatement(ls_sql.toString());
			    	us.setString(1, codigoUsuarioBuscar);
			    	us.setString(2, codigoUsuarioBuscar);
			    	us.setString(3, codigoUsuarioBuscar);
			    	if(sede!=1){
			    		us.setInt(4, sede);
			    	}
			    	
		    		rs = us.executeQuery();
		    		
		    		while ( rs.next() ) { 
		    			
		    			Hashtable objUsuarios = new Hashtable();
						String seleccionar = "<label style='cursor: hand;' align='center' " 
						+ "onclick=\"fn_editar(" + "'" + rs.getString("usuario") + "', " + "'"
				        + rs.getString("clave") + "'"+ "," 
				        + "'"+ rs.getString("estado") + "'"+ "," 
				        + "'"+ rs.getString("flag") + "'"+ "," 
				        + "'"+ rs.getString("nombres") + "'"+ "," 
				        + "'"+ rs.getString("apellidos") + "'"+ "," 
				        + "'"+ rs.getString("email") + "'"+ "," 
						+ "'"+ rs.getInt("sede") + "'"
				        + ");\" " + ">ver</label>";
						
						objUsuarios.put("ACTION", seleccionar);
						objUsuarios.put("usuario", rs.getString("usuario"));
						objUsuarios.put("clave", rs.getString("clave"));
						objUsuarios.put("estado", rs.getString("estado"));
						objUsuarios.put("flag", rs.getString("flag"));
						objUsuarios.put("nombres", rs.getString("nombres"));
						objUsuarios.put("apellidos", rs.getString("apellidos"));
						objUsuarios.put("email", rs.getString("email"));
						objUsuarios.put("sede", String.valueOf(rs.getInt("sede")));
						
						objUsuarios.put("tipo_usuario", rs.getString("tipo_usuario"));
						objUsuarios.put("tipo_sede", rs.getString("tipo_sede"));
						objUsuarios.put("tipo_estado", rs.getString("tipo_estado"));
						
						
						ListaUsuarios.add(objUsuarios);
						
					}
		            
					rs.close();
					
		            
		        } catch (SQLException e) {
		             e.printStackTrace();
		        }
		        return ListaUsuarios;
		}
	 
	 
	 public Collection of_lista_busca_personal(Connection cnx, String codigoUsuarioBuscar, int sede) {
			
	       log.info("ENTRO A  of_lista_busca_personal");
			Collection ListaPersonal = new ArrayList();
			
			//BUsuario userVO = null;
			Statement us  = null;
			ResultSet rs = null;

	       try {	
	       	
	       	StringBuffer ls_sql = new StringBuffer("select us.usuario,us.nombres,us.apellidos,us.email,us.flag,pe.validacion,pe.codigo_personal,pe.c_usuario,");
	       	ls_sql.append(" pe.codigo_oficina,pe.cargo_personal,pe.es_responsable,pe.tipo_firma,ca.codigo_cargo,ca.nombrecargo,of.codigo_oficina,");
	       	ls_sql.append(" of.nombre_corto ");
	       	ls_sql.append(" ,(case  us.estado when 'A' then 'ACTIVO' when 'I' then 'INACTIVO' else 'NONE' end ) as tipo_estado ");
	       	
	       	ls_sql.append(" ,(select tu.nombre from tramite.tipo_usuario  tu  ");
	       	ls_sql.append("	where us.flag=tu.codigo ) as tipo_usuario ");
	       	
	       	
	       	ls_sql.append(" ,(select descripcion_corta from tramite.tbl_elemento_catalogo ");
	       	ls_sql.append("	where id_catalogo='tipo_sede' and id_elemento=of.institucion) as nombre_sede ");
	       	
	       	ls_sql.append(" from tramite.usuarios us,tramite.personal pe,tramite.cargo ca,tramite.oficinas of ");
	       	ls_sql.append(" where us.usuario=pe.c_usuario and pe.cargo_personal=ca.codigo_cargo and of.codigo_oficina=pe.codigo_oficina ");
	       	
	       	if(sede!=1){
	       		ls_sql.append(" and us.sede = "+sede);
	       	}
	       	
	       	ls_sql.append(" and ( us.usuario = '"+codigoUsuarioBuscar+"' or us.nombres ilike '%'||'"+codigoUsuarioBuscar+"'||'%' or us.apellidos ilike '%'||'"+codigoUsuarioBuscar+"'||'%' )");
	       	
	       	ls_sql.append(" order by 1 asc");
	       	
	       	us = cnx.createStatement();
	       	System.out.println(ls_sql);
	   		rs = us.executeQuery(ls_sql.toString());
	   		
	   		while ( rs.next() ) { 
		        
		        String tipo_firma_ls = String.valueOf(rs.getInt("tipo_firma"));
		        tipo_firma_ls = (tipo_firma_ls==null)? "0":tipo_firma_ls;
		        
	   			Hashtable objPersonal = new Hashtable();
	   			
	   			
					String seleccionar = "<label style='cursor: hand;' align='center' " 
					+ "onclick=\"fn_editar(" + "'" + rs.getString("codigo_personal") + "'"+ "," + "'"
			        + rs.getString("c_usuario") + "'"+ "," 
			        + "'"+ rs.getString("nombres")+" "+rs.getString("apellidos")+ "'"+ "," 
			        + "'"+ rs.getString("cargo_personal") + "'"+ "," 
			        + "'"+ rs.getString("codigo_oficina") + "'"+ "," 
			        + "'"+ rs.getString("es_responsable") + "'"+ ","
			        + "'"+ tipo_firma_ls + "'" + ","
			        + "'"+ rs.getString("validacion") + "'"
			        + ");\" " + ">ver</label>";
					
					//log.info("El seleccionar es.."+ seleccionar);
					objPersonal.put("ACTION", seleccionar);
					objPersonal.put("codigo_personal", rs.getString("codigo_personal"));
					objPersonal.put("c_usuario", rs.getString("c_usuario"));
					objPersonal.put("nombre_personal", rs.getString("nombres")+" "+rs.getString("apellidos"));
					objPersonal.put("cargo_personal", rs.getString("nombrecargo"));
					objPersonal.put("oficina", rs.getString("nombre_corto"));
					objPersonal.put("es_responsable", rs.getString("es_responsable"));
					
					objPersonal.put("nombre_sede", (rs.getString("nombre_sede")==null)? "---":rs.getString("nombre_sede"));
					objPersonal.put("tipo_estado", rs.getString("tipo_estado"));
					objPersonal.put("email", rs.getString("email"));
					objPersonal.put("tipo_usuario", rs.getString("tipo_usuario"));
					
					ListaPersonal.add(objPersonal);
					
				}
	           
				rs.close();
				us.close();
	           
	       } catch (SQLException e) {
	            e.printStackTrace();
	       }
	       return ListaPersonal;
		                
		
	 }

	 
	 public Collection of_lista_usuarios(Connection cnx,
				int sede) {
			// TODO Auto-generated method stub
			 log.info("ENTRO A  Lista de Usuarios x sede");
				Collection ListaUsuarios = new ArrayList();
				
				BUsuario userVO = null;
				PreparedStatement us  = null;
				ResultSet rs = null;
		       
		        try {	
		        	
		        	StringBuilder ls_sql = new StringBuilder("select u.*, tu.nombre as tipo_usuario, e.descripcion_corta as tipo_sede ");
		        	ls_sql.append(" ,(case  u.estado when 'A' then 'Activo' when 'I' then 'Inactivo' else 'None' end ) as tipo_estado ");
		        	ls_sql.append(" from tramite.usuarios  u ");
		        	ls_sql.append(" left join tramite.tipo_usuario  tu  on u.flag=tu.codigo ");
		        	ls_sql.append(" left join tramite.tbl_elemento_catalogo  e  on u.sede=e.id_elemento  and  e.id_catalogo='tipo_sede' ");
		        	
		        	if(sede!=1){
		        		ls_sql.append(" where u.sede = ? ");
		        	}
		        			        			
		        	ls_sql.append(" order by  u.usuario; ");
		        	
			    	us = cnx.prepareStatement(ls_sql.toString());
			    	
			    	if(sede!=1){
			    		us.setInt(1, sede);
			    	}
			    	
		    		rs = us.executeQuery();
		    		
		    		while ( rs.next() ) { 
		    			
		    			Hashtable objUsuarios = new Hashtable();
						String seleccionar = "<label style='cursor: hand;' align='center' " 
						+ "onclick=\"fn_editar(" + "'" + rs.getString("usuario") + "', " + "'"
				        + rs.getString("clave") + "'"+ "," 
				        + "'"+ rs.getString("estado") + "'"+ "," 
				        + "'"+ rs.getString("flag") + "'"+ "," 
				        + "'"+ rs.getString("nombres") + "'"+ "," 
				        + "'"+ rs.getString("apellidos") + "'"+ "," 
				        + "'"+ rs.getString("email") + "'"+ "," 
				        + "'"+ rs.getString("sede") + "'"
				        + ");\" " + ">Editar</label>";
						
						objUsuarios.put("ACTION", seleccionar);
						objUsuarios.put("usuario", rs.getString("usuario"));
						objUsuarios.put("clave", rs.getString("clave"));
						objUsuarios.put("estado", rs.getString("estado"));
						objUsuarios.put("flag", rs.getString("flag"));
						objUsuarios.put("nombres", rs.getString("nombres"));
						objUsuarios.put("apellidos", rs.getString("apellidos"));
						objUsuarios.put("email", rs.getString("email"));
						objUsuarios.put("sede", (rs.getString("sede")!=null)? rs.getString("sede"): "");
						
						objUsuarios.put("tipo_usuario", rs.getString("tipo_usuario"));
						objUsuarios.put("tipo_sede", (rs.getString("tipo_sede")!=null)? rs.getString("tipo_sede"): "NONE");
						objUsuarios.put("tipo_estado", rs.getString("tipo_estado"));
						
						ListaUsuarios.add(objUsuarios);
						
					}
		            
					rs.close();
					
		            
		        } catch (SQLException e) {
		             e.printStackTrace();
		        }
		        return ListaUsuarios;
		}
	 
	public BUsuario UsuarioCons(Connection cnx,String usuario)
	   {
	   	
				//ResultSet result = null;
				BUsuario objUsuarios = null;
				Statement us  = null;
				ResultSet rs = null;
				
				try
				{
		    	
					String ls_sql = "select * from tramite.usuarios WHERE  usuario =" + "'" + usuario + "'" + " ";
        	
			    	us = cnx.createStatement();
		    		rs = us.executeQuery(ls_sql);
    		
					 log.info("SENTENCIA SQL : " + rs);			
					while ( rs.next() ) { 
	      			
						objUsuarios = new BUsuario();
						
						objUsuarios.setUsuario(rs.getString("usuario"));
						objUsuarios.setClave(rs.getString("clave"));
						objUsuarios.setEstado(rs.getString("estado"));
						objUsuarios.setFlag(rs.getString("flag"));
						objUsuarios.setNombres(rs.getString("nombres"));
						objUsuarios.setApellidos(rs.getString("apellidos"));
						objUsuarios.setEmail(rs.getString("email"));
						objUsuarios.setDni(rs.getString("dni"));
						objUsuarios.setAbreviatura(rs.getString("abrev"));
						
						objUsuarios.setSede(String.valueOf(rs.getInt("sede")));
						//objUsuarios.setTipo_firma("");
					}	
	      		
				}
				catch (SQLException se)
				{
					System.err.println("Se ha producido un error de BD.");  
					System.err.println(se.getMessage());  
				}
	  	
				return objUsuarios;
		
	          }

	 /**************Metodo para el ingreso de usuarios***********/
	 
	public void UsuarioIng(Connection cnx,String usuario,String clave,String estado,String flag,String nombres,String apellidos,String email,String dni,String abrev,String sede)  {
			
		ObtieneConexion 	x= new ObtieneConexion();
		Connection			con= x.getConnection();
		Statement us  = null;
	  	int value = Integer.parseInt(sede);
	  	
			try
			{
				
				String ls_sql = "insert into tramite.usuarios(usuario,clave,estado,perfil,flag,nombres,apellidos,email,dni,abrev,sede)" +
						" values(" + "'" + usuario + "'" + "," + "'" + clave + "'" + "," + "'" +
						estado + "'"+ "," + "'1'"+ "," + "'" +flag + "'" + "," + "'" +
						nombres + "'" + "," + "'" +
						apellidos + "'" + "," + "'" +
						email + "','" + 
						dni  + "','" + 
						abrev  + "'," +	value +")";
				
				 log.info("UsuarioIng "+ls_sql);
		    	 us = con.createStatement();
		    	 us.executeUpdate(ls_sql);
				
				
			}
			catch (SQLException se)
			{
				System.err.println("Se ha producido un error UsuarioIng de BD.");  
				System.err.println(se.getMessage());  
			}
		   }
				
		
	public boolean esUsuarioNuevo(String usuario) {
		boolean esUsuarioNuevo=true;
		// TODO Auto-generated method stub
		ObtieneConexion 	x= new ObtieneConexion();
		Connection			con= x.getConnection();
		Statement us=null;
		String ls_sql = "select usuario from tramite.usuarios where usuario='"+usuario+"'";
		
		try
		{
			us = con.createStatement();
    		
		ResultSet rs = us.executeQuery(ls_sql);
		if(rs.next()){
			esUsuarioNuevo=false;
		}
		}catch (SQLException se)
		{
			System.err.println("Se ha producido un error de BD.");  
			System.err.println(se.getMessage());  
		}
		return esUsuarioNuevo;
	}

	public int UsuarioMod(Connection cnx,String usuario,String clave,String estado,String flag,String nombres,String apellidos,String email,String dni,String abrev, String sede)  {
		
		String ls_sql = "";
		Statement us  = null;
		ObtieneConexion 	x= new ObtieneConexion();
		Connection			con= x.getConnection();
		int value = Integer.parseInt(sede);
		int valor=-1;
		try
		{
			
						
			ls_sql = "UPDATE tramite.usuarios SET usuario=" + "'" + usuario + "'" ;
			
			if(!clave.equals("")){
				ls_sql = ls_sql+"," + " clave=" + "'" + clave + "'";
			}
			
			ls_sql = ls_sql+"," + " estado=" + "'" + estado + "'"+
			"," + " flag=" + "'" + flag + "'" +  
			"," + " nombres=" + "'" + nombres + "'" + 
			"," + " apellidos=" + "'" + apellidos + "'" +   
			"," + " email=" + "'" + email + "'" + 
			"," + " dni=" + "'" + dni + "'" + 
			"," + " abrev=" + "'" + abrev + "'"+
			", sede=" + value +
			"   WHERE usuario =" + "'" + usuario + "'";
			
			log.info("El ls_sql usuario modificar es.." + ls_sql);
			
	    		us = con.createStatement();
	    		valor= us.executeUpdate(ls_sql);
			
			
		}
		catch (SQLException se)
		{
			System.err.println("Se ha producido un error de BD.");  
			System.err.println(se.getMessage());  
		}
		
		return valor;
		}
	
	

	public void UsuarioElim(Connection cnx,String usuario)  {
		
		String ls_sql = "";
		Statement us  = null;
		ObtieneConexion 	x= new ObtieneConexion();
		Connection			con= x.getConnection();
		
		try
		{
			
	
			ls_sql = "delete from tramite.usuarios WHERE usuario =" + "'" + usuario + "'";
			
			log.info("El ls_sql usuario eliminar es.." + ls_sql);
			
	    		us = con.createStatement();
	    		us.executeUpdate(ls_sql);
	    		
			
			
			
		}
		catch (SQLException se)
		{
			System.err.println("Se ha producido un error de BD.");  
			System.err.println(se.getMessage());  
		}
		}
  	
	
/***************************Oficinas************************************/

public Collection  of_lista_oficinas(Connection cnx) throws Exception {
	
    log.info("ENTRO A  Lista de Oficinas");
	Collection ListaOficinas = new ArrayList();
	
	BUsuario userVO = null;
	Statement us  = null;
	ResultSet rs = null;
   
    try {	
    	
    	String ls_sql = "select * from tramite.oficinas order by codigo_oficina asc";
    	
    	us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		
		while ( rs.next() ) { 
			
			Hashtable objOficinas = new Hashtable();
			String seleccionar = "<label style='cursor: hand;' align='center' " 
			+ "onclick=\"fn_editar(" + "'" + rs.getString("codigo_oficina") + "', " + "'"
	        + rs.getString("descripcion_oficina") + "'"+ "," 
	        + "'"+ rs.getString("nombre_corto") + "'"+ "," 
	        + "'"+ rs.getString("siglas_oficina") + "'"+ "," 
	        + "'"+ rs.getString("es_activo") + "'"
	        + ");\" " + ">ver</label>";
			
			objOficinas.put("ACTION", seleccionar);
			objOficinas.put("codigo_oficina", rs.getString("codigo_oficina"));
			objOficinas.put("descripcion_oficina", rs.getString("descripcion_oficina"));
			objOficinas.put("nombre_corto", rs.getString("nombre_corto"));
			objOficinas.put("siglas_oficina", rs.getString("siglas_oficina"));
			objOficinas.put("es_activo", rs.getString("es_activo"));
			
			ListaOficinas.add(objOficinas);
			
		}
        
		rs.close();
		
        
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return ListaOficinas;
                
}

public Collection  of_lista_oficinas(Connection cnx, int sede) throws Exception {
	
    log.info("ENTRO A  Lista de Oficinas");
	Collection ListaOficinas = new ArrayList();
	
	BUsuario userVO = null;
	Statement us  = null;
	ResultSet rs = null;
   
    try {	
    	
    	StringBuffer ls_sql = new StringBuffer("select  o.*, e.descripcion_corta as tipo_sede  from tramite.oficinas o ");
    	ls_sql.append(" left join tramite.tbl_elemento_catalogo  e  on o.institucion=e.id_elemento  and  e.id_catalogo='tipo_sede' ");
    	
    	if(sede!=1){
    		ls_sql.append(" where o.institucion=" + sede);
    	}
    	
    	ls_sql.append(" order by o.institucion, o.descripcion_oficina asc ");
    	
    	us = cnx.createStatement();
    	
    	System.out.println("-->"+ls_sql);
		rs = us.executeQuery(ls_sql.toString());
		
		while ( rs.next() ) { 
			
			Hashtable objOficinas = new Hashtable();
			String seleccionar = "<label style='cursor: hand;' align='center' " 
			+ "onclick=\"fn_editar(" + "'" + rs.getString("codigo_oficina") + "', " + "'"
	        + rs.getString("descripcion_oficina") + "'"+ "," 
	        + "'"+ rs.getString("nombre_corto") + "'"+ "," 
	        + "'"+ rs.getString("siglas_oficina") + "'"+ "," 
	        + "'"+ rs.getString("es_activo") + "'"
	        + ");\" " + ">ver</label>";
			
			objOficinas.put("ACTION", seleccionar);
			objOficinas.put("codigo_oficina", rs.getString("codigo_oficina"));
			objOficinas.put("descripcion_oficina", rs.getString("descripcion_oficina"));
			objOficinas.put("nombre_corto", rs.getString("nombre_corto"));
			objOficinas.put("siglas_oficina", rs.getString("siglas_oficina"));
			objOficinas.put("es_activo", rs.getString("es_activo"));
			
			objOficinas.put("tipo_sede", rs.getString("tipo_sede"));
			
			
			ListaOficinas.add(objOficinas);
			
		}
        
		rs.close();
		
        
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return ListaOficinas;
                
}


public Collection  of_lista_oficinas_x_sede(String sede) throws Exception {
	
    log.info("ENTRO A  of_lista_oficinas_x_sede");
	Collection ListaOficinas = new ArrayList();
	
	Statement us  = null;
	ResultSet rs = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	BListaGeneral lista_general;
    try {	
    	
    	StringBuffer ls_sql = new StringBuffer("select codigo_oficina,descripcion_oficina from tramite.oficinas ");
    	
    	if(!sede.equals("1")){
    		ls_sql.append(" where institucion=" + sede);
    	}
    	
    	ls_sql.append(" order by institucion");
    	log.info("JM Sql  "+ls_sql);	
    	us = con.createStatement();
		rs=us.executeQuery(ls_sql.toString());
		
		while ( rs.next() ) { 
			lista_general= new BListaGeneral();
			lista_general.setCodigo(String.valueOf(rs.getInt("codigo_oficina")));
			lista_general.setDescripcion(rs.getString("descripcion_oficina"));
			ListaOficinas.add(lista_general);
		}
        
		rs.close();
		
        
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return ListaOficinas;
                
}

public Collection  of_lista_oficinas_x_sede_x_group(String sede) throws Exception {
	
    log.info("ENTRO A  of_lista_oficinas_x_sede_x_group");
	Collection ListaOficinas = new ArrayList();
	
	Statement us  = null;
	ResultSet rs = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	BListaGeneral lista_general;
    try {	
    	
    	StringBuffer ls_sql = new StringBuffer("select codigo_oficina,descripcion_oficina from tramite.oficinas ");
    	ls_sql.append(" where coalesce(padre, 0) = 0  and es_activo = 'A' ");
    	
    	if(!sede.equals("1")){
    		ls_sql.append(" and institucion=" + sede);
    	}
    	
    	ls_sql.append(" order by institucion, orden;");
    	
    	us = con.createStatement();
		rs=us.executeQuery(ls_sql.toString());
		
		while ( rs.next() ) { 
			lista_general= new BListaGeneral();
			lista_general.setCodigo(rs.getString(1));
			lista_general.setDescripcion(rs.getString(2));
			ListaOficinas.add(lista_general);
		}
        
		rs.close();
		
        
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return ListaOficinas;
                
}

public Collection  of_lista_oficinas_x_sede_x_group_(String sede) throws Exception {
	
    log.info("ENTRO A  of_lista_oficinas_x_sede_x_group");
	Collection ListaOficinas = new ArrayList();
	
	Statement us  = null;
	ResultSet rs = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	BListaGeneral lista_general;
    try {	
    	
    	StringBuffer ls_sql = new StringBuffer("select codigo_oficina,descripcion_oficina from tramite.oficinas ");
    	ls_sql.append(" where  es_activo = 'A' ");
    	
    	if(!sede.equals("1")){
    		ls_sql.append(" and institucion=" + sede);
    	}
    	
    	ls_sql.append(" order by institucion, orden;");
    	
    	us = con.createStatement();
		rs=us.executeQuery(ls_sql.toString());
		
		while ( rs.next() ) { 
			lista_general= new BListaGeneral();
			lista_general.setCodigo(rs.getString(1));
			lista_general.setDescripcion(rs.getString(2));
			ListaOficinas.add(lista_general);
		}
        
		rs.close();
		
        
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return ListaOficinas;
                
}

public Collection  of_lista_oficinas_x_sede_x_sub(String codigo_oficina) throws Exception {
	
    log.info("ENTRO A  of_lista_oficinas_x_sede_x_sub");
	Collection ListaOficinas = new ArrayList();
	
	Statement us  = null;
	ResultSet rs = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	BListaGeneral lista_general;
    try {	
    	
    	StringBuffer ls_sql = new StringBuffer("select codigo_oficina,descripcion_oficina from tramite.oficinas ");
    	ls_sql.append(" where coalesce(padre, 0) = "+codigo_oficina+"  and es_activo = 'A' ");
    	
    	ls_sql.append(" order by institucion, orden asc ");
    	
    	us = con.createStatement();
		rs=us.executeQuery(ls_sql.toString());
		
		while ( rs.next() ) { 
			lista_general= new BListaGeneral();
			lista_general.setCodigo(rs.getString(1));
			lista_general.setDescripcion(rs.getString(2));
			ListaOficinas.add(lista_general);
		}
        
		rs.close();
		
        
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return ListaOficinas;
                
}

public BOficinas OficinaCons(Connection cnx,String codigo_oficina)
{
	
		ResultSet result = null;
		BOficinas objOficinas = null;
		Statement us  = null;
		ResultSet rs = null;
		
		try
		{
    	
			String ls_sql = "select codigo_oficina,descripcion_oficina,es_activo,institucion,padre  from  tramite.oficinas where " +
					"  codigo_oficina =" + "'" + codigo_oficina + "'" + " ";
	
	    	us = cnx.createStatement();
    		rs = us.executeQuery(ls_sql);
	
			 log.info("SENTENCIA SQL : " + rs);			
			while ( rs.next() ) { 
  			
				objOficinas = new BOficinas();
				
				objOficinas.setCodigo_oficina(rs.getString("codigo_oficina"));
				objOficinas.setDescripcion_oficina(rs.getString("descripcion_oficina"));
				objOficinas.setEs_activo(rs.getString("es_activo"));
				
				objOficinas.setSede(rs.getInt("institucion"));
				objOficinas.setPadre(rs.getInt("padre"));
				
			}	
  		
		}
		catch (SQLException se)
		{
			System.err.println("Se ha producido un error de BD.");  
			System.err.println(se.getMessage());  
		}
	
		return objOficinas;

      }



public int OficinaIng(Connection cnx,String nombrecorto,String descripcion_oficina,String siglas_oficina, String es_activo,int grupo_oficina
		, String sede, String oficina)  {
	
	ObtieneConexion 	x= new ObtieneConexion();
	Connection	con= x.getConnection();
	Statement us  = null;
	ResultSet rs = null;
	int    li_retorno=0;
	int orden = 1;
	try{
	
		StringBuilder ls_sql  =  new StringBuilder("select max(codigo_oficina) as c_valor from tramite.oficinas");
		us = con.createStatement();
    	rs = us.executeQuery(ls_sql.toString());
		while ( rs.next() ) { 
			li_retorno = Integer.valueOf(rs.getString("c_valor")).intValue() + 1;
  		}
    	     	 
    	        	
    	 StringBuilder ls_sql_2  =  new StringBuilder("insert into tramite.oficinas(codigo_oficina,codigo_fondo,nombre_corto,fondo,oficina,");
    	 ls_sql_2.append(" descripcion_oficina,siglas_oficina,es_activo,grupo_oficina,institucion,padre)");
    	 ls_sql_2.append(" values(" + li_retorno + ",'01','" +	nombrecorto + "','01'," +li_retorno + ",'" +descripcion_oficina + "',");
    	 ls_sql_2.append("'" +siglas_oficina + "','" +es_activo + "',"+grupo_oficina+","+sede+","+oficina+")");

    	 us = con.createStatement();
    	 us.executeUpdate(ls_sql_2.toString());
		
		
	}
	catch (SQLException se)
	{
		System.err.println("Se ha producido un error de BD.");  
		System.err.println(se.getMessage());  
	}
	
	return li_retorno;
}


public void OficinaIng(Connection cnx,String nombrecorto,String descripcion_oficina,String siglas_oficina,	String es_activo)  {
	
			ObtieneConexion 	x= new ObtieneConexion();
			Connection			con= x.getConnection();
			Statement us  = null;
			ResultSet rs = null;
			String ls_sql = "";
			int    li_retorno=0;
			
			try
			{
			
				ls_sql = "Select max(codigo_oficina) as c_valor from tramite.oficinas";
    			log.info("ls_sql............   " + ls_sql );
    			us = cnx.createStatement();
		    	 rs = us.executeQuery(ls_sql);
		    	 log.info("El rs es ..: " + rs);
				
		    	 if (rs == null) 
		    		{
		    			log.info("rs es nulo ......." );
		    			li_retorno = 1;
		    	    }
		    		else
		    		{
		    			log.info("rs NO es nulo ......." );
			    		while ( rs.next() ) { 
			    				            log.info("dentro del whyle......." );
			    				            if (rs.getString("c_valor") == null )
			    				            {
			    				    			log.info("ta vacio nulo ......." );
			    				    			li_retorno = 1;
			    				    	    }
			    				            else
			    				    		{
			    				            	log.info("ls_valor :" + rs.getString("c_valor"));
			    				            	li_retorno = Integer.valueOf(rs.getString("c_valor")).intValue() + 1;
			    				    		}
			    				           
			    		}
			        }
		    	 
		    	
		    	 ls_sql = "insert into tramite.oficinas(codigo_oficina,codigo_fondo,nombre_corto,fondo,oficina," +
		    	 		"descripcion_oficina,siglas_oficina,es_activo)" +
				" values(" + "'" + li_retorno + "'" + "," + "'01'" + "," + "'" +
				nombrecorto + "'"+ "," + "'01'"+ "," + "'" +li_retorno + "'"+ "," + "'" +descripcion_oficina + "'"
				+ "," + "'" +siglas_oficina + "'"+ "," + "'" +es_activo + "'"+ ")";
		
		    	 us = con.createStatement();
		    	 us.executeUpdate(ls_sql);
				
				
			}
			catch (SQLException se)
			{
				System.err.println("Se ha producido un error de BD.");  
				System.err.println(se.getMessage());  
			}
   }
		
public void OficinaMod(Connection cnx,String codigo_oficina,String nombre_corto,String descripcion_oficina,String siglas_oficina,String es_activo,String nombre_final,String ruta,String contentype){

	String ls_sql = "";
	Statement us  = null;
	ObtieneConexion x= new ObtieneConexion();
	Connection	con= x.getConnection();

	try
	{
		

		ls_sql = "UPDATE tramite.oficinas SET nombre_corto=" + "'" + nombre_corto + "'" +
		"," + " descripcion_oficina=" + "'" + descripcion_oficina + "'"+
		"," + " siglas_oficina=" + "'" + siglas_oficina + "'"+
		"," + " es_activo=" + "'" + es_activo + "'"+
		","+"nombre_sello='"+nombre_final+"' "+
		","+"contentype='"+contentype+"' "
		+ "WHERE codigo_oficina =" + "'" + codigo_oficina + "'";
		
		log.info("El ls_sql oficinas modificar es.." + ls_sql);
		
			us = con.createStatement();
			us.executeUpdate(ls_sql);
		
		
	}
	catch (SQLException se)
	{
		System.err.println("Se ha producido un error de BD.");  
		System.err.println(se.getMessage());  
	}	
}
public void OficinaMod(Connection cnx,String codigo_oficina,String nombre_corto,String descripcion_oficina,String siglas_oficina,String es_activo)  {

String ls_sql = "";
Statement us  = null;
ObtieneConexion 	x= new ObtieneConexion();
Connection			con= x.getConnection();

try
{
	

	ls_sql = "UPDATE tramite.oficinas SET nombre_corto=" + "'" + nombre_corto + "'" +
	"," + " descripcion_oficina=" + "'" + descripcion_oficina + "'"+
	"," + " siglas_oficina=" + "'" + siglas_oficina + "'"+
	"," + " es_activo=" + "'" + es_activo + "'" +  " " 
	+ "WHERE codigo_oficina =" + "'" + codigo_oficina + "'";
	
	log.info("El ls_sql oficinas modificar es.." + ls_sql);
	
		us = con.createStatement();
		us.executeUpdate(ls_sql);
	
	
}
catch (SQLException se)
{
	System.err.println("Se ha producido un error de BD.");  
	System.err.println(se.getMessage());  
}
}

public void OficinaElim(Connection cnx,String codigo_oficina)  {

String ls_sql = "";
Statement us  = null;
ObtieneConexion 	x= new ObtieneConexion();
Connection			con= x.getConnection();

try
{
	

	ls_sql = "delete from tramite.oficinas WHERE codigo_oficina =" + "'" + codigo_oficina + "'";
	
	log.info("El ls_sql oficinas eliminar es.." + ls_sql);
	
		us = con.createStatement();
		us.executeUpdate(ls_sql);
		
	
	
	
}
catch (SQLException se)
{
	System.err.println("Se ha producido un error de BD.");  
	System.err.println(se.getMessage());  
}
}

/**************Mantenimiento de Personal***********/

public Collection  of_lista_usuarios(String usuario) throws Exception {
	
    log.info("ENTRO A  Lista de Usuarios");
	Collection ListaUsuarios = new ArrayList();
	
	BUsuario userVO = null;
	Statement us  = null;
	ResultSet rs = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			cnx= x.getConnection();
    try {	
    	
    	String ls_sql = "select * from tramite.usuarios where usuario = " + "'" + usuario + "'"+" order by usuario asc";
    	
    	us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		
		while ( rs.next() ) { 
			
			userVO = new BUsuario();
			
			userVO.setUsuario(rs.getString("usuario"));
			userVO.setNombres(rs.getString("nombres")+" "+rs.getString("apellidos"));
			userVO.setApellidos(rs.getString("apellidos"));
			userVO.setSede(String.valueOf(rs.getInt("sede")));
			//userVO.setNombresapellidos(rs.getString("nombres")+" "+rs.getString("apellidos"));
			
			String ls_usuario=rs.getString("usuario");
			String nombres=rs.getString("nombres");
			String apellidos=rs.getString("apellidos");
			
			 log.info("ls_usuario es "+ ls_usuario);
			 log.info("nombres es "+ nombres);
			 log.info("apellidos es "+ apellidos);
			 
			 ListaUsuarios.add(userVO);

		}	
        
		x.cerrar_conexion(cnx, rs);
		us.close();
        
    } catch (SQLException e) {
         e.printStackTrace();
         x.cerrar_conexion(cnx);
    }
    return ListaUsuarios;
                
}

public Collection  of_lista_usuarios_nuevo(String usuario) throws Exception {
	
    log.info("ENTRO A  Lista de Usuarios");
	Collection listaUsuarios = new ArrayList();
	
	BUsuario userVO = null;
	Statement us  = null;
	ResultSet rs = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			cnx= x.getConnection();
    try {	
    	
    	String ls_sql = "select usuario,nombres,apellidos  from tramite.usuarios where usuario = '"+ usuario+"'  order by usuario asc";
    	
    	us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		
		while ( rs.next() ) { 
			
			userVO = new BUsuario();
			
			userVO.setUsuario(rs.getString("usuario"));
			userVO.setNombres(rs.getString("nombres")+" "+rs.getString("apellidos"));
			userVO.setApellidos(rs.getString("apellidos"));
			//userVO.setNombresapellidos(rs.getString("nombres")+" "+rs.getString("apellidos"));
		 
			listaUsuarios.add(userVO);

		}	
       x.cerrar_conexion(cnx, rs);
       us.close();
		
        
    } catch (SQLException e) {
         e.printStackTrace();
         x.cerrar_conexion(cnx);
    }
    return listaUsuarios;
                
}



public Collection  of_lista_personal(Connection cnx) throws Exception {
		
       log.info("ENTRO A  Lista de Personal");
		Collection ListaPersonal = new ArrayList();
		
		BUsuario userVO = null;
		Statement us  = null;
		ResultSet rs = null;
       try {	
       	
       	String ls_sql = "select us.usuario,us.nombres,us.apellidos,us.email,pe.validacion,pe.codigo_personal,pe.c_usuario," +
       			"pe.codigo_oficina,pe.cargo_personal,pe.es_responsable,pe.tipo_firma,ca.codigo_cargo,ca.nombrecargo,of.codigo_oficina," +
       			"of.nombre_corto from tramite.usuarios us,tramite.personal pe,tramite.cargo ca," +
       			"tramite.oficinas of where us.usuario=pe.c_usuario and pe.cargo_personal=ca.codigo_cargo and" +
       			" of.codigo_oficina=pe.codigo_oficina order by codigo_personal asc";
       	
	    us = cnx.createStatement();
   		rs = us.executeQuery(ls_sql);
   		
   		while ( rs.next() ) { 
	        
	        String tipo_firma_ls = String.valueOf(rs.getInt("tipo_firma"));
	        tipo_firma_ls = (tipo_firma_ls==null)? "0":tipo_firma_ls;
	        
   			Hashtable objPersonal = new Hashtable();
   			
   			
				String seleccionar = "<label style='cursor: hand;' align='center' " 
				+ "onclick=\"fn_editar(" + "'" + rs.getString("codigo_personal") + "'"+ "," + "'"
		        + rs.getString("c_usuario") + "'"+ "," 
		        + "'"+ rs.getString("nombres")+" "+rs.getString("apellidos")+ "'"+ "," 
		        + "'"+ rs.getString("cargo_personal") + "'"+ "," 
		        + "'"+ rs.getString("codigo_oficina") + "'"+ "," 
		        + "'"+ rs.getString("es_responsable") + "'"+ ","
		        + "'"+ tipo_firma_ls + "'" + ","
		        + "'"+ rs.getString("validacion") + "'"
		        + ");\" " + ">ver</label>";
				
				//log.info("El seleccionar es.."+ seleccionar);
				objPersonal.put("ACTION", seleccionar);
				objPersonal.put("codigo_personal", rs.getString("codigo_personal"));
				objPersonal.put("c_usuario", rs.getString("c_usuario"));
				objPersonal.put("nombre_personal", rs.getString("nombres")+" "+rs.getString("apellidos"));
				objPersonal.put("cargo_personal", rs.getString("nombrecargo"));
				objPersonal.put("oficina", rs.getString("nombre_corto"));
				objPersonal.put("es_responsable", rs.getString("es_responsable"));
				
				
				ListaPersonal.add(objPersonal);
				
			}
           
			rs.close();
			us.close();
           
       } catch (SQLException e) {
            e.printStackTrace();
       }
       return ListaPersonal;
	                
	}


public Collection  of_lista_personal(Connection cnx, int sede) throws Exception {
		
       log.info("ENTRO A  Lista de Personal");
		Collection ListaPersonal = new ArrayList();
		
		BUsuario userVO = null;
		Statement us  = null;
		ResultSet rs = null;

       try {	
       	
       	StringBuffer ls_sql = new StringBuffer("select us.usuario,us.sede,us.nombres,us.apellidos,us.email,us.flag,pe.validacion,pe.codigo_personal,pe.c_usuario,");
       	ls_sql.append(" pe.codigo_oficina,pe.cargo_personal,pe.es_responsable,pe.tipo_firma,ca.codigo_cargo,ca.nombrecargo,of.codigo_oficina,");
       	ls_sql.append(" of.nombre_corto, of.descripcion_oficina, of.siglas_oficina ");
       	ls_sql.append(" ,(case  us.estado when 'A' then 'ACTIVO' when 'I' then 'INACTIVO' else 'NONE' end ) as tipo_estado ");
       	
       	ls_sql.append(" ,(select tu.nombre from tramite.tipo_usuario  tu  ");
       	ls_sql.append("	where us.flag=tu.codigo ) as tipo_usuario ");
       	
       	ls_sql.append(" ,(select descripcion_corta from tramite.tbl_elemento_catalogo ");
       	ls_sql.append("	where id_catalogo='tipo_sede' and id_elemento=of.institucion) as nombre_sede ");
       	
       	ls_sql.append(" from tramite.usuarios us,tramite.personal pe,tramite.cargo ca,tramite.oficinas of ");
       	ls_sql.append(" where us.usuario=pe.c_usuario and pe.cargo_personal=ca.codigo_cargo and of.codigo_oficina=pe.codigo_oficina ");
       	
       	if(sede!=1){
       		ls_sql.append(" and us.sede = "+sede);
       	}
       	
       	ls_sql.append(" order by 1 asc");
       	log.info("LISTAR PERSONAL ->  "+ls_sql);
	    us = cnx.createStatement();
   		rs = us.executeQuery(ls_sql.toString());
   		
   		while ( rs.next() ) { 
	        
	        String tipo_firma_ls = String.valueOf(rs.getInt("tipo_firma"));
	        tipo_firma_ls = (tipo_firma_ls==null)? "0":tipo_firma_ls;
	        
   			Hashtable objPersonal = new Hashtable();
   			
   			
				String seleccionar = "<label style='cursor: hand;' align='center' " 
				+ "onclick=\"fn_editar(" + "'" + rs.getString("codigo_personal") + "'"+ "," + "'"
		        + rs.getString("c_usuario") + "'"+ "," 
		        + "'"+ rs.getString("nombres")+" "+rs.getString("apellidos")+ "'"+ "," 
		        + "'"+ rs.getString("cargo_personal") + "'"+ "," 
		        + "'"+ rs.getString("codigo_oficina") + "'"+ "," 
		        + "'"+ rs.getString("es_responsable") + "'"+ ","
		        + "'"+ tipo_firma_ls + "'" + ","
		        + "'"+ rs.getString("validacion") + "'"
		        + ");\" " + ">ver</label>";
				
				//log.info("El seleccionar es.."+ seleccionar);
				objPersonal.put("ACTION", seleccionar);
				objPersonal.put("codigo_personal", rs.getString("codigo_personal"));
				objPersonal.put("c_usuario", rs.getString("c_usuario"));
				objPersonal.put("nombre_personal", rs.getString("nombres")+" "+rs.getString("apellidos"));
				objPersonal.put("cargo_personal", rs.getString("nombrecargo"));
				objPersonal.put("oficina", rs.getString("descripcion_oficina"));
				objPersonal.put("es_responsable", rs.getString("es_responsable"));
				if(rs.getInt("sede")==1){
				objPersonal.put("nombre_sede","GENERAL");	
				}else{
				objPersonal.put("nombre_sede", (rs.getInt("sede")==2)? "CONCYTEC":"FONDECYT");
				}
				objPersonal.put("tipo_estado", rs.getString("tipo_estado"));
				objPersonal.put("email", rs.getString("email"));
				objPersonal.put("tipo_usuario", rs.getString("tipo_usuario"));
				
				ListaPersonal.add(objPersonal);
				
			}
           
			rs.close();
			us.close();
           
       } catch (SQLException e) {
            e.printStackTrace();
       }
       return ListaPersonal;
	                
	}

public BPersonal PersonalCons(Connection cnx,String codigo_personal)
  {
  	
			ResultSet result = null;
			BPersonal objPersonal = null;
			Statement us  = null;
			ResultSet rs = null;
			String valor=null;
			try
			{
	    	
				
				StringBuffer ls_sql = new StringBuffer("select p.*, u.sede , o.padre  from tramite.personal p ");
				ls_sql.append(" LEFT JOIN  tramite.usuarios u  on u.usuario=p.c_usuario ");
				ls_sql.append(" LEFT JOIN tramite.oficinas o  on o.codigo_oficina=p.codigo_oficina ");
				ls_sql.append(" WHERE  p.codigo_personal =" + "'" + codigo_personal + "'"+" ");
				
		    	us = cnx.createStatement();
	    		rs = us.executeQuery(ls_sql.toString());
		
				log.info("SENTENCIA SQL JM: " + ls_sql);			
				while ( rs.next() ) { 
     			
					objPersonal = new BPersonal();
					
					objPersonal.setCodigo_personal(rs.getString("codigo_personal"));
					objPersonal.setNombre_personal(rs.getString("nombre_personal"));
					objPersonal.setC_usuario(rs.getString("c_usuario"));
					objPersonal.setCargo_personal(rs.getString("cargo_personal"));
					objPersonal.setCodigo_oficina(rs.getString("codigo_oficina"));
					
					objPersonal.setSede(String.valueOf(rs.getInt("sede")));
					
					valor=String.valueOf(rs.getInt("validacion"));
					System.out.println("---->"+valor);
					objPersonal.setValidacion(valor==null? "0": valor);
					
					objPersonal.setSede(String.valueOf(rs.getInt("sede")));
					objPersonal.setPadre(rs.getInt("padre"));
				}	
     		
			}
			catch (SQLException se)
			{
				System.err.println("Se ha producido un error de BD.");  
				System.err.println(se.getMessage());  
			}
 	
			return objPersonal;
	
         }

/**************Metodo para el ingreso de Personal***********/

public void PersonalIng(Connection cnx,String personas,String c_usuario,String cargo_personal,String codigo_oficina,String es_responsable, String validacion)  {
		
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	Statement us  = null;
	String ls_sql="";
	String ls_valor="";
	int li_retorno=0;
	ResultSet rs = null;
 		
				try
				{
			
					ls_sql = "Select max(codigo_personal) as c_valor from tramite.personal";
	    			log.info("ls_sql............   " + ls_sql );
	    			us = cnx.createStatement();
			    	 rs = us.executeQuery(ls_sql);
			    	 log.info("El rs es ..: " + rs);
					
			    	 if (rs == null) 
			    		{
			    			log.info("rs es nulo ......." );
			    			li_retorno = 1;
			    	    }
			    		else
			    		{
			    			log.info("rs NO es nulo ......." );
				    		while ( rs.next() ) { 
				    				            log.info("dentro del whyle......." );
				    				            if (rs.getString("c_valor") == null )
				    				            {
				    				    			log.info("ta vacio nulo ......." );
				    				    			li_retorno = 1;
				    				    	    }
				    				            else
				    				    		{
				    				            	log.info("ls_valor :" + rs.getString("c_valor"));
				    				            	li_retorno = Integer.valueOf(rs.getString("c_valor")).intValue() + 1;
				    				    		}
				    				           
				    		}
				        }
			 		
			 		
			 ls_sql = "insert into tramite.personal(codigo_personal,codigo_fondo,nombre_personal,c_usuario,cargo_personal,codigo_oficina,es_responsable,validacion)" +
					" values(" + "'" + li_retorno + "'"+ "," + "'01'" + "," + "'" + personas + "'" + "," + "'" +
					c_usuario + "'"+ "," + "'" +cargo_personal + "'" + "," + "'" +
					codigo_oficina + "'" + "," + "'" +
					es_responsable + "'" + ","+validacion+")";
			 
			 //log.info("El ls_sql :" + ls_sql);
			 
	    	 us = con.createStatement();
	    	 us.executeUpdate(ls_sql);
					
					
				}
				catch (SQLException se)
				{
					System.err.println("Se ha producido un error de BD.");  
					System.err.println(se.getMessage());  
				}
	   }
			

public void PersonalMod(Connection cnx,String codigo_personal,String nombreapellido,String c_usuario,String cargo_personal,String codigo_oficina,String es_responsable,boolean existeFirma,boolean existePfx,String nombre_imagen,String ruta_imagen, String nombre_pfx, String ruta_pfx, int tipo_firma, String validacion) {
	
	String ls_sql = "";
	Statement us  = null;
	ObtieneConexion x= new ObtieneConexion();
	Connection	con= x.getConnection();
	
	try
	{
		

		ls_sql = "UPDATE tramite.personal SET nombre_personal=" + "'" + nombreapellido + "'" +
		"," + " c_usuario=" + "'" + c_usuario + "'"+
		"," + " cargo_personal=" + "" + cargo_personal + ""+
		"," + " codigo_oficina=" + "" + codigo_oficina + ""+
		"," + " es_responsable=" + "'" + es_responsable + "'"+
		" , tipo_firma="+tipo_firma+" , validacion = "+validacion;
		if(existeFirma){
			ls_sql=ls_sql+" , nombre_sello_visto='"+nombre_imagen+"', ruta_sello='"+ruta_imagen+"'";
		}
		if(existePfx){
			ls_sql=ls_sql+" , nombre_pfx='"+nombre_pfx+"', ruta_pfx='"+ruta_pfx+"'";
		}
		
		ls_sql=ls_sql+" WHERE codigo_personal =" + codigo_personal;
		
		log.info("El ls_sql personal modificar es.." + ls_sql);
		
   		us = con.createStatement();
   		us.executeUpdate(ls_sql);
		
		
	}
	catch (SQLException se)
	{
		System.err.println("Se ha producido un error de BD.");  
		System.err.println(se.getMessage());  
	}
	}

public void PersonalElim(Connection cnx,String codigo_personal)  {
	
	String ls_sql = "";
	Statement us  = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	
	try
	{
		

		ls_sql = "delete from tramite.personal WHERE codigo_personal =" + "'" + codigo_personal + "'";
		
		//log.info("El ls_sql personal eliminar es.." + ls_sql);
		
   		us = con.createStatement();
   		us.executeUpdate(ls_sql);
   		
		
		
		
	}
	catch (SQLException se)
	{
		System.err.println("Se ha producido un error de BD.");  
		System.err.println(se.getMessage());  
	}
	}


/**************Mantenimiento de Cargos***********/

public Collection  of_lista_cargos(Connection cnx) throws Exception {
	
    log.info("ENTRO A  Lista de Cargos");
	Collection ListaCargos = new ArrayList();
	
	BUsuario userVO = null;
	Statement us  = null;
	ResultSet rs = null;
   
    try {	
    	
    	String ls_sql = "select * from tramite.cargo order by codigo_cargo asc";
    	
    	us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		
		while ( rs.next() ) { 
			
			Hashtable objCargos = new Hashtable();
			String seleccionar = "<label style='cursor: hand;' align='center' " 
			+ "onclick=\"fn_editar(" + "'" + rs.getString("codigo_cargo") + "', " + "'"
	        + rs.getString("nombrecargo") + "'"
	        + ");\" " + ">ver</label>";
			
			objCargos.put("ACTION", seleccionar);
			objCargos.put("codigo_cargo", rs.getString("codigo_cargo"));
			objCargos.put("nombrecargo", rs.getString("nombrecargo"));
			
			ListaCargos.add(objCargos);
			
		}
        
		rs.close();
		
        
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return ListaCargos;
                
}


public BCargo CargoCons(Connection cnx,String codigo_cargo)
{
	
			ResultSet result = null;
			BCargo objBCargo = null;
			Statement us  = null;
			ResultSet rs = null;
			
			try
			{
	    	
				
				String ls_sql = "select * from tramite.cargo WHERE  codigo_cargo =" + "'" + codigo_cargo + "'"+" ";
				
		    	us = cnx.createStatement();
	    		rs = us.executeQuery(ls_sql);
		
				 log.info("SENTENCIA SQL : " + rs);			
				while ( rs.next() ) { 
   			
					objBCargo = new BCargo();
					
					objBCargo.setCodigo_cargo(rs.getString("codigo_cargo"));
					objBCargo.setNombrecargo(rs.getString("nombrecargo"));
					
		  	
				}	
   		
			}
			catch (SQLException se)
			{
				System.err.println("Se ha producido un error de BD.");  
				System.err.println(se.getMessage());  
			}
	
			return objBCargo;
	
       }


public void CargoIng(Connection cnx,String nombrecargo) {
		
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	Statement us  = null;
	String ls_sql="";
	String ls_valor="";
	int li_retorno=0;
	ResultSet rs = null;
		
				try
				{
			
					ls_sql = "Select max(codigo_cargo) as c_valor from tramite.cargo";
	    			log.info("ls_sql............   " + ls_sql );
	    			us = cnx.createStatement();
			    	 rs = us.executeQuery(ls_sql);
			    	 log.info("El rs es ..: " + rs);
					
			    	 if (rs == null) 
			    		{
			    			log.info("rs es nulo ......." );
			    			li_retorno = 1;
			    	    }
			    		else
			    		{
			    			log.info("rs NO es nulo ......." );
				    		while ( rs.next() ) { 
				    				            log.info("dentro del whyle......." );
				    				            if (rs.getString("c_valor") == null )
				    				            {
				    				    			log.info("ta vacio nulo ......." );
				    				    			li_retorno = 1;
				    				    	    }
				    				            else
				    				    		{
				    				            	log.info("ls_valor :" + rs.getString("c_valor"));
				    				            	li_retorno = Integer.valueOf(rs.getString("c_valor")).intValue() + 1;
				    				    		}
				    				           
				    		}
				        }
			 		
			 		
			 ls_sql = "insert into tramite.cargo(codigo_cargo,nombrecargo)" +
					" values(" + "'" + li_retorno + "'" + "," + "'" + nombrecargo + "'" + ")";
			 
			 log.info("El ls_sql :" + ls_sql);
			 
	    	 us = con.createStatement();
	    	 us.executeUpdate(ls_sql);
					
					
				}
				catch (SQLException se)
				{
					System.err.println("Se ha producido un error de BD.");  
					System.err.println(se.getMessage());  
				}
	   }
			

public void CargoMod(Connection cnx,String codigo_cargo,String nombrecargo)  {
	
	String ls_sql = "";
	Statement us  = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	
	try
	{
		

		ls_sql = "UPDATE tramite.cargo SET nombrecargo=" + "'" + nombrecargo + "'"+  " " 
		+ "WHERE codigo_cargo =" + "'" + codigo_cargo + "'";
		
		log.info("El ls_sql cargo modificar es.." + ls_sql);
		
 		us = con.createStatement();
 		us.executeUpdate(ls_sql);
		
		
	}
	catch (SQLException se)
	{
		System.err.println("Se ha producido un error de BD.");  
		System.err.println(se.getMessage());  
	}
	}

public void CargoElim(Connection cnx,String codigo_cargo)  {
	
	String ls_sql = "";
	Statement us  = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	
	try
	{
		

		ls_sql = "delete from tramite.cargo WHERE codigo_cargo =" + "'" + codigo_cargo + "'";
		
		log.info("El ls_sql cargo eliminar es.." + ls_sql);
		
 		us = con.createStatement();
 		us.executeUpdate(ls_sql);
 		
		x.cerrar_conexion(cnx);
		
		
	}
	catch (SQLException se)
	{
		System.err.println("Se ha producido un error de BD.");  
		System.err.println(se.getMessage());  
	}
	}


/**************Mantenimiento de Tipo Documento***********/

public Collection  of_lista_tipodocumento(Connection cnx) throws Exception {
	
    log.info("ENTRO A  Lista de Tipo Documento");
	Collection ListaTipoDocumento = new ArrayList();
	
	BUsuario userVO = null;
	Statement us  = null;
	ResultSet rs = null;
   
    try {	
    	
    	String ls_sql = "select * from tramite.tipo_documento order by codigo_tipo asc";
    	
    	us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		
		while ( rs.next() ) { 
			
			Hashtable objTipoDoc = new Hashtable();
			String seleccionar = "<label style='cursor: hand;' align='center' " 
			+ "onclick=\"fn_editar(" + "'" + rs.getString("codigo_tipo") + "', " + "'"
	        + rs.getString("descripcion_tipo") + "'"
	        + ");\" " + ">ver</label>";
			
			objTipoDoc.put("ACTION", seleccionar);
			objTipoDoc.put("codigo_tipo", rs.getString("codigo_tipo"));
			objTipoDoc.put("descripcion_tipo", rs.getString("descripcion_tipo"));
			
			ListaTipoDocumento.add(objTipoDoc);
			
		}
        
		rs.close();
		
        
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return ListaTipoDocumento;
                
}


public BTipoDocumento TipoDocumentoCons(Connection cnx,String codigo_tipo)
{
	
			ResultSet result = null;
			BTipoDocumento objBTipoDocumento = null;
			Statement us  = null;
			ResultSet rs = null;
			
			try
			{
	    	
				
				String ls_sql = "select * from tramite.tipo_documento WHERE  codigo_tipo =" + "'" + codigo_tipo + "'"+" ";
				
		    	us = cnx.createStatement();
	    		rs = us.executeQuery(ls_sql);
		
				 log.info("SENTENCIA SQL : " + rs);			
				while ( rs.next() ) { 
   			
					objBTipoDocumento = new BTipoDocumento();
					
					objBTipoDocumento.setCodigo_tipo(rs.getString("codigo_tipo"));
					objBTipoDocumento.setDescripcion_tipo(rs.getString("descripcion_tipo"));
					
		  	
				}	
   		
			}
			catch (SQLException se)
			{
				System.err.println("Se ha producido un error de BD.");  
				System.err.println(se.getMessage());  
			}
	
			return objBTipoDocumento;
	
       }


public void TipoDocumentoIng(Connection cnx,String descripcion_tipo){
		
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	Statement us  = null;
	String ls_sql="";
	String ls_valor="";
	int li_retorno=0;
	ResultSet rs = null;
		
				try
				{
			
					ls_sql = "Select max(codigo_tipo) as c_valor from tramite.tipo_documento";
	    			log.info("ls_sql............   " + ls_sql );
	    			us = cnx.createStatement();
			    	 rs = us.executeQuery(ls_sql);
			    	 log.info("El rs es ..: " + rs);
					
			    	 if (rs == null) 
			    		{
			    			log.info("rs es nulo ......." );
			    			li_retorno = 1;
			    	    }
			    		else
			    		{
			    			log.info("rs NO es nulo ......." );
				    		while ( rs.next() ) { 
				    				            log.info("dentro del whyle......." );
				    				            if (rs.getString("c_valor") == null )
				    				            {
				    				    			log.info("ta vacio nulo ......." );
				    				    			li_retorno = 1;
				    				    	    }
				    				            else
				    				    		{
				    				            	log.info("ls_valor :" + rs.getString("c_valor"));
				    				            	li_retorno = Integer.valueOf(rs.getString("c_valor")).intValue() + 1;
				    				    		}
				    				           
				    		}
				        }
			 		
			 		
			 ls_sql = "insert into tramite.tipo_documento(codigo_tipo,descripcion_tipo)" +
					" values(" + "'" + li_retorno + "'" + "," + "'" + descripcion_tipo + "'" + ")";
			 
			 log.info("El ls_sql :" + ls_sql);
			 
	    	 us = con.createStatement();
	    	 us.executeUpdate(ls_sql);
					
					
				}
				catch (SQLException se)
				{
					System.err.println("Se ha producido un error de BD.");  
					System.err.println(se.getMessage());  
				}
	   }
			

public void TipoDocumentoMod(Connection cnx,String codigo_tipo,String descripcion_tipo)  {
	
	String ls_sql = "";
	Statement us  = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	
	try
	{
		

		ls_sql = "UPDATE tramite.tipo_documento SET descripcion_tipo=" + "'" + descripcion_tipo + "'"+  " " 
		+ "WHERE codigo_tipo =" + "'" + codigo_tipo + "'";
		
		log.info("El ls_sql tipo documento modificar es.." + ls_sql);
		
 		us = con.createStatement();
 		us.executeUpdate(ls_sql);
		
		
	}
	catch (SQLException se)
	{
		System.err.println("Se ha producido un error de BD.");  
		System.err.println(se.getMessage());  
	}
	}

public void TipoDocumentoElim(Connection cnx,String codigo_tipo)  {
	
	String ls_sql = "";
	Statement us  = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	
	try
	{
		

		ls_sql = "delete from tramite.tipo_documento WHERE codigo_tipo =" + "'" + codigo_tipo + "'";
		
		log.info("El ls_sql tipo documento eliminar es.." + ls_sql);
		
 		us = con.createStatement();
 		us.executeUpdate(ls_sql);
 		
		
		
		
	}
	catch (SQLException se)
	{
		System.err.println("Se ha producido un error de BD.");  
		System.err.println(se.getMessage());  
	}
	}


/**************Mantenimiento de Solicitud***********/

public Collection  of_lista_solicitud(Connection cnx) throws Exception {
	
    log.info("ENTRO A  Lista de Solicitud");
	Collection ListaSolicitud = new ArrayList();
	
	BUsuario userVO = null;
	Statement us  = null;
	ResultSet rs = null;
   
    try {	
    	
    	String ls_sql = "select * from tramite.solicitud order by codigo_solicitud asc";
    	
    	us = cnx.createStatement();
		rs = us.executeQuery(ls_sql);
		
		while ( rs.next() ) { 
			
			Hashtable objSolicitud = new Hashtable();
			String seleccionar = "<label style='cursor: hand;' align='center' " 
			+ "onclick=\"fn_editar(" + "'" + rs.getString("codigo_solicitud") + "', " + "'"
	        + rs.getString("nombre_solicitud") + "'"
	        + ");\" " + ">ver</label>";
			
			objSolicitud.put("ACTION", seleccionar);
			objSolicitud.put("codigo_solicitud", rs.getString("codigo_solicitud"));
			objSolicitud.put("nombre_solicitud", rs.getString("nombre_solicitud"));
			
			ListaSolicitud.add(objSolicitud);
			
		}
        
		rs.close();
		
        
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return ListaSolicitud;
                
}


public BSolicitud SolicitudCons(Connection cnx,String codigo_solicitud)
{
	
			ResultSet result = null;
			BSolicitud objBSolicitud = null;
			Statement us  = null;
			ResultSet rs = null;
			
			try
			{
	    	
				
				String ls_sql = "select * from tramite.solicitud WHERE  codigo_solicitud =" + "'" + codigo_solicitud + "'"+" ";
				
		    	us = cnx.createStatement();
	    		rs = us.executeQuery(ls_sql);
		
				 log.info("SENTENCIA SQL : " + rs);			
				while ( rs.next() ) { 
   			
					objBSolicitud = new BSolicitud();
					
					objBSolicitud.setCodigo_solicitud(rs.getString("codigo_solicitud"));
					objBSolicitud.setNombre_solicitud(rs.getString("nombre_solicitud"));
					
		  	
				}	
   		
			}
			catch (SQLException se)
			{
				System.err.println("Se ha producido un error de BD.");  
				System.err.println(se.getMessage());  
			}
	
			return objBSolicitud;
	
       }


public void SolicitudIng(Connection cnx,String nombre_solicitud){
		
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	Statement us  = null;
	String ls_sql="";
	String ls_valor="";
	int li_retorno=0;
	ResultSet rs = null;
		
				try
				{
			
					ls_sql = "Select max(codigo_solicitud) as c_valor from tramite.solicitud";
	    			log.info("ls_sql............   " + ls_sql );
	    			us = cnx.createStatement();
			    	 rs = us.executeQuery(ls_sql);
			    	 log.info("El rs es ..: " + rs);
					
			    	 if (rs == null) 
			    		{
			    			log.info("rs es nulo ......." );
			    			li_retorno = 1;
			    	    }
			    		else
			    		{
			    			log.info("rs NO es nulo ......." );
				    		while ( rs.next() ) { 
				    				            log.info("dentro del whyle......." );
				    				            if (rs.getString("c_valor") == null )
				    				            {
				    				    			log.info("ta vacio nulo ......." );
				    				    			li_retorno = 1;
				    				    	    }
				    				            else
				    				    		{
				    				            	log.info("ls_valor :" + rs.getString("c_valor"));
				    				            	li_retorno = Integer.valueOf(rs.getString("c_valor")).intValue() + 1;
				    				    		}
				    				           
				    		}
				        }
			 		
			 		
			 ls_sql = "insert into tramite.solicitud(codigo_solicitud,nombre_solicitud)" +
					" values(" + "'" + li_retorno + "'" + "," + "'" + nombre_solicitud + "'" + ")";
			 
			 log.info("El ls_sql :" + ls_sql);
			 
	    	 us = con.createStatement();
	    	 us.executeUpdate(ls_sql);
					
					
				}
				catch (SQLException se)
				{
					System.err.println("Se ha producido un error de BD.");  
					System.err.println(se.getMessage());  
				}
	   }
			

public void SolicitudMod(Connection cnx,String codigo_solicitud,String nombre_solicitud)  {
	
	String ls_sql = "";
	Statement us  = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	
	try
	{
		

		ls_sql = "UPDATE tramite.solicitud SET nombre_solicitud=" + "'" + nombre_solicitud + "'"+  " " 
		+ "WHERE codigo_solicitud =" + "'" + codigo_solicitud + "'";
		
		log.info("El ls_sql solicitud modificar es.." + ls_sql);
		
 		us = con.createStatement();
 		us.executeUpdate(ls_sql);
		
		
	}
	catch (SQLException se)
	{
		System.err.println("Se ha producido un error de BD.");  
		System.err.println(se.getMessage());  
	}
	}

public void SolicitudElim(Connection cnx,String codigo_solicitud)  {
	
	String ls_sql = "";
	Statement us  = null;
	ObtieneConexion 	x= new ObtieneConexion();
	Connection			con= x.getConnection();
	
	try
	{
		

		ls_sql = "delete from tramite.solicitud WHERE codigo_solicitud =" + "'" + codigo_solicitud + "'";
		
		log.info("El ls_sql solicitud eliminar es.." + ls_sql);
		
 		us = con.createStatement();
 		us.executeUpdate(ls_sql);
 		
		
		
		
	}
	catch (SQLException se)
	{
		System.err.println("Se ha producido un error de BD.");  
		System.err.println(se.getMessage());  
	}
	}


/**********grupo oficina***************/

public int GrupoOficinaIng(String descripcion_oficina, String nombrecorto, int sede){
	ObtieneConexion x= new ObtieneConexion();
	Connection	con= x.getConnection();
	Statement us  = null;
	ResultSet rs = null;
	int resultado=0;
	int orden = 0;
	try{
	
		
    	StringBuilder ls_sql_orden  =  new StringBuilder("Select max(orden) as c_valor from tramite.grupo_oficina");
 		us = con.createStatement();
     	rs = us.executeQuery(ls_sql_orden.toString());
 	    		while ( rs.next() ) { 
		    	    	orden = Integer.valueOf(rs.getString("c_valor")).intValue();
	    		}
 	    orden=orden+100;
 	    
 	    
 	 
 	   StringBuilder ls_sql2 = new StringBuilder("SELECT nextval('tramite.seq_id_grupo_oficina');");
   	 	us = con.createStatement();
		rs = us.executeQuery(ls_sql2.toString());    		
 		while(rs.next()){
 			resultado=(rs.getInt("nextval"));
 		}
	 		 
 	    
    	 StringBuilder ls_sql_3  =  new StringBuilder("INSERT INTO tramite.grupo_oficina(codigo_grupo, nombre_grupo, orden, nombre_corto, estado, sede)");
    	 ls_sql_3.append(" VALUES ("+resultado+", '" +descripcion_oficina + "', "+orden+", '" +	nombrecorto + "', 1, "+sede+");");

    	 us = con.createStatement();
    	 us.executeUpdate(ls_sql_3.toString());
    	 
	 } catch (SQLException e) {
         e.printStackTrace();
    }
    	 
	return resultado;
}

	public void setOrdenOficina(int codigo_oficina, int sede){
		ObtieneConexion x= new ObtieneConexion();
		Connection	con= x.getConnection();
		Statement us  = null;
		ResultSet rs = null;
		int resultado=0;
		int orden = 0;
		try{
			
			StringBuilder ls_sql_orden  =  new StringBuilder("select max(orden) as c_valor from tramite.oficinas where grupo_oficina="+sede);
	 		us = con.createStatement();
	     	rs = us.executeQuery(ls_sql_orden.toString());
    		while ( rs.next() ) { 
	    	    	orden = Integer.valueOf(rs.getString("c_valor")).intValue();
    		}
    		orden+=4;
    		
    		StringBuilder ls_sql_ofi  =  new StringBuilder("update tramite.oficinas set orden = "+ orden +" where codigo_oficina = "+codigo_oficina);
	 		us = con.createStatement();
	     	us.executeUpdate(ls_sql_ofi.toString());
			
		} catch (SQLException e) {
	         e.printStackTrace();
	    }
	}
	
	public void setOrdenOficina(int codigo_oficina, int sede, int grupo_oficina, int padre){
		log.info("cod Oficina"+codigo_oficina);
		log.info("cod Oficina"+sede);
		log.info("cod Oficina"+grupo_oficina);
		log.info("cod Oficina"+padre);
		ObtieneConexion x= new ObtieneConexion();
		Connection	con= x.getConnection();
		Statement us  = null;
		ResultSet rs = null;
		int resultado=0;
		int orden = 0;
		try{
			
			StringBuilder ls_sql_orden  =  new StringBuilder("select orden  from tramite.grupo_oficina where codigo_grupo="+grupo_oficina);
	 		us = con.createStatement();
	     	rs = us.executeQuery(ls_sql_orden.toString());
    		while ( rs.next() ) { 
    			resultado = rs.getInt("orden");
    		}
    		
    		if(padre==0)
    			orden=resultado;
    		else{
    			//agrego de acuerdo a consulta
    			StringBuilder ls_sql_bo  =  new StringBuilder("select max(orden) as c_valor from tramite.oficinas where grupo_oficina="+sede);
    	 		us = con.createStatement();
    	     	rs = us.executeQuery(ls_sql_bo.toString());
    	     	orden=resultado;
        		while ( rs.next() ) { 
    	    	    	orden = Integer.valueOf(rs.getString("c_valor")).intValue();
        		}
        		orden+=10;
    			
    			
    		}
    		
    		StringBuilder ls_sql_ofi  =  new StringBuilder("update tramite.oficinas set orden = "+ orden +" where codigo_oficina = "+codigo_oficina);
	 		us = con.createStatement();
	     	us.executeUpdate(ls_sql_ofi.toString());
			
		} catch (SQLException e) {
	         e.printStackTrace();
	    }
	}
	
	public void IngresaPersona(int codigo_oficina_nuevo, String nombre_corto, String siglas_oficina, int oficina, int sede){
		ObtieneConexion x= new ObtieneConexion();
		Connection	con= x.getConnection();
		Statement us  = null;
		ResultSet rs = null;
		String resultado=null;
		int orden = 0;
		
		try{
			
			StringBuffer ls_sql = new StringBuffer("select e.id_elemento, e.descripcion_corta ");
	    	ls_sql.append(" from tramite.tbl_catalogo c ");
	    	ls_sql.append(" left join tramite.tbl_elemento_catalogo e on c.id_catalogo=e.id_catalogo ");
	    	ls_sql.append(" where c.id_catalogo='"+ IConstante.TIPO_SEDE +"' ");
	    	ls_sql.append(" and e.id_elemento="+sede);
	    	
	 		us = con.createStatement();
	     	rs = us.executeQuery(ls_sql.toString());
    		while ( rs.next() ) { 
    			resultado = rs.getString("descripcion_corta");
    		}
    		
    		
    		resultado=resultado+" - "+siglas_oficina.replace('*', ' ').trim();
    		
    		
    		StringBuilder ls_sql_ofi  =  new StringBuilder("INSERT INTO tramite.persona(nombre_persona, tipo, codigo_oficina) ");
    		ls_sql_ofi.append(" VALUES ('"+ resultado +"', 'J', "+ codigo_oficina_nuevo +");");
	 		us = con.createStatement();
	     	us.executeUpdate(ls_sql_ofi.toString());
			
		} catch (SQLException e) {
	         e.printStackTrace();
	    }
	}


}
