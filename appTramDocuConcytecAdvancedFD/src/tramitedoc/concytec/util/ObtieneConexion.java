/*
 * Created on 04/04/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tramitedoc.concytec.util;
import java.sql.*;
/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ObtieneConexion {
	
	private String	driver= "org.postgresql.Driver";
	
	/*PRODUCCION NUEVO  
	private String 	url= "jdbc:postgresql://192.168.5.153:5432/concytec";
	private String 	login= "usrtram";
	private String 	password= "utramdocu";
	*/

/*produccion	
	
	private String 	url= "jdbc:postgresql://192.168.5.224:5432/concytec";
	private String 	login= "usrtram";
	private String 	password= "utramdocu";
*/ 

/*DESARROLLO */
	private String 	url= "jdbc:postgresql://192.168.5.175:5432/concytec";
	private String 	login= "usrtram";
	private String 	password= "usrtram";
 
		
	/*	base de datos local	  
		

	private String 	url= "jdbc:postgresql://localhost:5432/concytec";
	private String 	login= "postgres";
	private String 	password= "root";
 */
	public Connection getConnection() {
		Connection	cn=null;
		
		try{	
			Class.forName(driver);
			cn= DriverManager.getConnection(url,login,password);
		} catch(SQLException e) { 
			System.out.println(e.toString()); 
		} catch(Exception e) { 
			System.out.println(e.toString());	
		}
		
		return cn;
	}
	
	public Connection getConnectionDirectorio() {
		Connection	cn=null;
		
		try{	
			Class.forName(driver);
			cn= DriverManager.getConnection("jdbc:postgresql://192.168.5.175:5432/PROY_CONCYTEC","usrdirectorio","dir77ac");
		} catch(SQLException e) { 
			System.out.println(e.toString()); 
		} catch(Exception e) { 
			System.out.println(e.toString());	
		}
		
		return cn;
	}

	public void cerrar_conexion(Connection cnx, ResultSet rs) {
		// TODO Auto-generated method stub
		cerrar_conexion(cnx);
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void cerrar_conexion(Connection cnx) {
		// TODO Auto-generated method stub
		if(cnx!=null)
			try {
				cnx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
