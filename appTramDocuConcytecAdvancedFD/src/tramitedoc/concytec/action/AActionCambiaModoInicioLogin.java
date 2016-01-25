package tramitedoc.concytec.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ajaxtags.servlets.BaseAjaxAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class AActionCambiaModoInicioLogin  extends BaseAjaxAction {
	protected  Log log = LogFactory.getLog(AActionCambiaModoInicioLogin.class);
	public String getXmlContent(ActionMapping actionMapping,
                                ActionForm actionForm,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
    	
    	response.setContentType("text/html;charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
  		request.setCharacterEncoding("UTF-8");
  		HttpSession session = request.getSession(false); 
  		//System.out.println("daaaa");  		        
		 		
  		
        PrintWriter out = response.getWriter();
        out.printf("cambio");
        out.close();
        
        return "";
    }
  

}