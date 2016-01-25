package tramitedoc.concytec.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tramitedoc.concytec.bean.BParametro;
import tramitedoc.concytec.dao.interfaces.IParametro;
import tramitedoc.concytec.util.ObtieneConexion;

public class SqlParametroDAO implements IParametro {

	@Override
	public List<BParametro> listar() {
		ObtieneConexion x = new ObtieneConexion();
		Connection cn = x.getConnection();		
        ArrayList<BParametro> parametros = new ArrayList<BParametro>();
        
        try {

            String sql = "select codigo_parametro, simbolo, descripcion, valor from tramite.parametros_google;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BParametro parametro = new BParametro();
                
                parametro.setCodigo_parametro(rs.getInt(1));
                parametro.setSimbolo(rs.getString(2));
                parametro.setDescripcion(rs.getString(3));
                parametro.setValor(rs.getString(4));                
                parametros.add(parametro);
                
            }
            
            ps.close();
            x.cerrar_conexion(cn,rs);
            
        } catch (SQLException ex) {
            System.out.println("ERROR LISTAR PARAMETROS: " + ex.getMessage());
        } 
        return parametros;		
	}

	@Override
	public int modificar(BParametro parametro) {
		
		 int estado = 0;
		 
		 ObtieneConexion x = new ObtieneConexion();
		 Connection cn = x.getConnection();		 
	     
	     
	        try {
	            String sql = "update tramite.parametros_google "
	            			+ "set simbolo = ?, "
	            			+ "descripcion = ?, "
	            			+ "valor = ? "
	            			+ "where codigo_parametro = ?;";
	            PreparedStatement ps = cn.prepareStatement(sql);
	            ps.setString(1, parametro.getSimbolo());
	            ps.setString(2, parametro.getDescripcion());
	            ps.setString(3, parametro.getValor());
	            ps.setInt(4, parametro.getCodigo_parametro());
	            int rpta = ps.executeUpdate();
	            
	            if(rpta > 0) {
	            	estado = 1;
	            }else{
	            	estado = 0;
	            }
	            
	            x.cerrar_conexion(cn);

	        } catch (SQLException ex) {
	        	estado = 0;
	            System.out.println("ERROR MODIFICAR PARAMETROS: " + ex.getMessage());
	        } 
	        return estado;
	}

}
