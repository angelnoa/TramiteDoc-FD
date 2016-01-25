package tramitedoc.concytec.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.form.FFormMantDocumento;

public class AActionMoverArchivoAADocumento extends Action {

	protected  Log log = LogFactory.getLog(AActionMoverArchivoAADocumento.class);
	public ActionForward execute(ActionMapping mapping,
		    ActionForm form,
		    HttpServletRequest request,
		    HttpServletResponse response)
		    throws Exception
		  {
		  
			log.info("Entry documento AActionMoverArchivoAADocumento ....");		   
		   FFormMantDocumento formM= (FFormMantDocumento)form;
		   log.info(formM.getAsunto_documento());
		// EmployeeSearchService service = new EmployeeSearchService();
		   
		    // Retrieve employee count.
		    //int employeeCount = service.getEmployeeCount();
		   
		    // Write employee count to HTTP response.
		    PrintWriter out = response.getWriter();
		  //  out.print(employeeCount);
		   
		    // Return null to inform the controller servlet
		    // that the HTTP response has been handled.
		    return null;
		  }

	
	
}
