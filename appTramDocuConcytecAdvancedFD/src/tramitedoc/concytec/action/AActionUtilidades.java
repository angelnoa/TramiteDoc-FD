package tramitedoc.concytec.action;



import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.util.NameService;




public class AActionUtilidades extends Action{
	protected  Log log = LogFactory.getLog(AActionUtilidades.class);
	private List<String> names = new ArrayList<String>();
	public ActionForward execute(ActionMapping mapping,
		    ActionForm form,
		    HttpServletRequest request,
		    HttpServletResponse response)
		    throws Exception
		  {
		String prefix ="";
		prefix = request.getParameter("dirigido_a");
		names=(List<String>)request.getSession().getAttribute("names");
		
		System.out.println("names size" +names.size());
		
		NameService service = NameService.getInstance(names);
        List matching = service.findNames(prefix);
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        if (matching.size() > 0) {
            PrintWriter out = response.getWriter();
           
            response.setContentType("text/html");
            StringBuffer results = new StringBuffer("<ul>");
            Iterator iter = matching.iterator();
            while(iter.hasNext()) {            	
                String name = (String) iter.next();
                results.append("<li>" + name + "</li>");
                log.info(name);
            }
            
            results.append("</ul>");
            matching = null;
            service = null;
            out.println(results); 
            out.close();
        } else {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        
        return null;
		  }
}
