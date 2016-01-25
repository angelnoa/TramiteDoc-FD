package tramitedoc.concytec.action;

import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class GenerateReportAction extends Action
{protected  Log log = LogFactory.getLog(GenerateReportAction.class);
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
	  ServletOutputStream servletOutputStream = response.getOutputStream();
	    InputStream reportStream = getServlet().getServletConfig()
	        .getServletContext().getResourceAsStream("/reportes/ReporteSTDExterno.jasper");
	    response.setContentType("application/pdf"); 
	   
	    String ls_mensaje_exp=(String)request.getSession().getAttribute("numero_expediente");
	    String mensaje_clave=(String)request.getSession().getAttribute("mensaje_clave");
	    String usuario=(String)request.getSession().getAttribute("nombreusuario");
    HashMap<String,String> parameterMap = new HashMap<String,String>();
    parameterMap.put("numero_expediente",ls_mensaje_exp);
    parameterMap.put("mensaje_clave",mensaje_clave);
    parameterMap.put("usuario",usuario);
    
   // Class.forName("com.mysql.jdbc.Driver");

  //  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightstats?user=root&password=password");

    JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,
    		parameterMap, new JREmptyDataSource());

    //connection.close();

    servletOutputStream.flush();
    servletOutputStream.close();

    return null;
  }
}
