package tramitedoc.concytec.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import tramitedoc.concytec.dao.*;
import tramitedoc.concytec.form.*;
import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * AUTOR : Machuca Ovalle FECHA : 03-04-2006 VERSION : 1.0 DESCRIPCIÓN : Accion
 * de Logeo
 */

public class AActionMantPrecerrar extends ValidaSessionAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("DENTRO DEL MANT-PRE-CERRAR...");

		HttpSession session = request.getSession(true);
		Funciones f = new Funciones();

		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		ICierreDAO daoICierreDAO = new SqlCierreDAO();

		FFormMantPrecerrar oForm = (FFormMantPrecerrar) form;
		System.out.println("Antes del FFormMantPreliquidar...");

		System.out.println(oForm.getFecha_envio());
		System.out.println(oForm.getDestinatario());
		System.out.println(oForm.getRemitente());

		request.setAttribute("lista_couriers", daoICierreDAO.lista_couriers());
		request.setAttribute("lista_tipo_servicios",
				daoICierreDAO.lista_tipo_servicios());

		if (oForm.getTipo() != null && oForm.getTipo().equals("guardar")) {
			
			String mensaje = "";
			
			
			
				System.out.println("Guardando");
				if (ValidaCampos(oForm)) {
					int resultado = daoICierreDAO.registrar_preCierre(oForm);
					
					if (resultado == 0) {
						mensaje = "El registro no se ha completado correctamente";
					} else {
						mensaje = "Se ha registrado correctamente";
						FFormMantPrecerrar fPre = new FFormMantPrecerrar();
						String fecha_envio = f.convertirFecha(new Date());
						fPre.setFecha_envio(fecha_envio);
						session.setAttribute("FFormMantPrecerrar", fPre);
					}
					
				}else{
					mensaje= "Ingrese todos los datos";
				}
						
			request.setAttribute("mensaje_usuario", mensaje);
			
		} else {
			FFormMantPrecerrar fPre = new FFormMantPrecerrar();
			String fecha_envio = f.convertirFecha(new Date());
			fPre.setFecha_envio(fecha_envio);
			session.setAttribute("FFormMantPrecerrar", fPre);
		}

		
		
		return (mapping.findForward("exito"));
	}

	private boolean ValidaCampos(FFormMantPrecerrar oForm) {
		// TODO Auto-generated method stub
		boolean permitir = true;
		if(oForm.getDestinatario()<=0){
			permitir = false;
		}
		return permitir;
	}
}
