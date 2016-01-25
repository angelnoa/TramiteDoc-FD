
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="net.sf.jasperreports.engine.fill.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.view.*" %>
<%@ page import="java.net.*" %> 
<%@ page import="tramitedoc.concytec.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="tramitedoc.concytec.bean.*"%>
<%@ page import="javax.print.*"%>
<jsp:directive.page import="net.sf.jasperreports.engine.JasperRunManager"/>
<%
ObtieneConexion x = null;
Connection conn = null;
ServletOutputStream ouputStream = null;
try{
	

	 x= new ObtieneConexion();
	 conn= x.getConnection();
	String ls_codigo_documento="";
	String ls_desc_origen="";
	String ls_numero_documento="";
	String ls_folios_documento="";
	String ls_asunto_documento="";
	String ls_fecha_rpta="";
	String ls_fecha_registro="";
	int anio=0;
	
	
	if (session.getAttribute("codigo_documento")!=null) { 
	   ls_codigo_documento = String.valueOf(session.getAttribute("codigo_documento"));
	   System.out.println("El ls_codigo_documento es ..." + ls_codigo_documento); 
	} 
	
	if (session.getAttribute("desc_origen")!=null) { 
	   ls_desc_origen = String.valueOf(session.getAttribute("desc_origen"));
	   System.out.println("El ls_desc_origen detf1 es..." + ls_desc_origen); 
	}
	if (session.getAttribute("numero_documento")!=null) { 
	   ls_numero_documento = String.valueOf(session.getAttribute("numero_documento"));
	   System.out.println("El ls_numero_documento detf1 es..." + ls_numero_documento); 
	}
	if (session.getAttribute("folios_documento")!=null) { 
	   ls_folios_documento = String.valueOf(session.getAttribute("folios_documento"));
	   System.out.println("El ls_folios_documento detf1 es..." + ls_folios_documento); 
	}
	if (session.getAttribute("asunto_documento")!=null) { 
	   ls_asunto_documento = String.valueOf(session.getAttribute("asunto_documento"));
	   System.out.println("El ls_asunto_documento detf1 es..." + ls_asunto_documento); 
	}
	if (session.getAttribute("fecha_rpta")!=null) { 
	   ls_fecha_rpta = String.valueOf(session.getAttribute("fecha_rpta"));
	   System.out.println("El ls_fecha_rpta detf1 es..." + ls_fecha_rpta); 
	}
	if (session.getAttribute("fecha_registro")!=null) { 
	   ls_fecha_registro = String.valueOf(session.getAttribute("fecha_registro"));
	   System.out.println("El fecha_registro detf1 es..." + ls_fecha_registro); 
	}
	if (session.getAttribute("anio")!=null) { 
		   ls_fecha_registro = String.valueOf(session.getAttribute("anio"));
		   System.out.println("el anio es ." + anio); 
		}
	/*String fileName = "/pages/reports/reporte_f1.jasper";
	File reportFile = new File(application.getRealPath("/pages/reports/reporte_f1.jasper"));*/
	
	

    String fileName = "/reportes/hojaruta_double.jasper";
	File reportFile = new File(application.getRealPath("/reportes/hojaruta_double.jasper"));
    Map parameters = new HashMap();
    		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
    			Un2t numero;
				/*int num;
				double decimal;
				decimal=bsoli.getTot_f1detalle();*/
				String numerotexto="";
				String dnumtexto="";
				
				//numerotexto=String.valueOf(decimal);
				
				numerotexto.substring(numerotexto.indexOf(".")+1,numerotexto.length());
			
				dnumtexto=numerotexto.substring(numerotexto.indexOf(".")+1,numerotexto.length());
				if(dnumtexto.length()==1){
					dnumtexto=dnumtexto+"0";
				}
				String res;
		       
     parameters.put("codigo_documento",ls_codigo_documento);
	 parameters.put("secuencia_movimiento","1");
	 parameters.put("desc_origen",ls_desc_origen);
	 parameters.put("numero_documento",ls_numero_documento);
	 parameters.put("folios_documento",ls_folios_documento);
	 parameters.put("asunto_documento",ls_asunto_documento);
	 parameters.put("fecha_rpta",ls_fecha_rpta);
	 parameters.put("fecha_registro",ls_fecha_registro);
	 parameters.put("iEntero",anio);
	 //../../resources/images/logoConcytec.PNG
	 parameters.put("ruta_logoConcy", "../../img/cabeceraconcytec.gif");    
    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
    
    response.setContentType("application/pdf");
   	fileName = fileName.substring(fileName.lastIndexOf('/')+1);
    StringBuffer contentDisposition = new StringBuffer();
   	contentDisposition.append("attachment;");
    contentDisposition.append("filename=\"");
   	contentDisposition.append("hojaruta_double.pdf");
   	contentDisposition.append("\"");  
    response.setHeader ("Content-Disposition", contentDisposition.toString());    
    response.setContentLength(bytes.length);    
    ouputStream = response.getOutputStream();
    ouputStream.write(bytes, 0, bytes.length);
        
    
}catch(Exception e){
    e.printStackTrace();
}finally{
	ouputStream.flush();
    ouputStream.close();
    try{
	    if (conn != null && !conn.isClosed()) {
			conn.close();
			x.cerrar_conexion(conn);
		}   
    }
	catch (Exception e2){
		System.out.println("No se cerro");
	}
}
%>