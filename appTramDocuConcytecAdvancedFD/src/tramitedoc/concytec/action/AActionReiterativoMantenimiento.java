package tramitedoc.concytec.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.dao.interfaces.IReiterativoDAO;
import tramitedoc.concytec.dao.sql.SqlReiterativoDAO;
import tramitedoc.concytec.form.FFormReiterativo;
import tramitedoc.concytec.form.FFormReiterativoMantenimiento;
import tramitedoc.concytec.form.FFormReiterativoRegistro;
import tramitedoc.concytec.service.ReiterativoService;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.ValidaSessionAction;

public class AActionReiterativoMantenimiento extends ValidaSessionAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		FFormReiterativoMantenimiento mant = (FFormReiterativoMantenimiento)form;
		IReiterativoDAO rDao = new SqlReiterativoDAO();
		Funciones f=new Funciones();
		ReiterativoService service = new ReiterativoService();
		Collection listaDocsDetalles = null;
		String  mensaje_usuario= null;
		
		if(mant.getOpcion().equals("anular")){
			rDao.anularMovimiento(mant.getCodigo_documento(),mant.getSecuencia_movimiento());
		}else if(mant.getOpcion().equals("editar")){
			
		}
		listaDocsDetalles = service.listarSecuenciasMantenimiento(mant.getCodigo_documento());
		session.setAttribute("listadoSecuenciasMantenimiento", listaDocsDetalles);
		return (mapping.findForward("mantenimiento"));
	}
}
