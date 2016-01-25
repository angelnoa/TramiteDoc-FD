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

public class AActionMantCerrar extends ValidaSessionAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("DENTRO DEL MANT-CERRAR...");

		HttpSession session = request.getSession(true);

		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		ICierreDAO daoICierreDAO = new SqlCierreDAO();
		Funciones f = new Funciones();

		FFormMantCerrar oForm = (FFormMantCerrar) form;

		String codigoOficinaDelUSuario = String.valueOf(session
				.getAttribute("codigo_oficina"));

		if (codigoOficinaDelUSuario != null
				&& codigoOficinaDelUSuario.equals("1")) {

			// MantCerrar
			if (oForm.getTipo() != null
					&& oForm.getTipo().equals("ver_detalle")) {

				BPaquete paquete = daoICierreDAO.buscar_paquete(oForm
						.getCodigo_cierre());

				oForm.setFecha_envio(paquete.getFecha_envio());
				oForm.setTipo_servicio(paquete.getTipo_servicio()
						.getDescripcion());
				oForm.setNombre_destinatario(paquete.getDestinatario()
						.getNombre_persona());
				oForm.setNombre_courier(paquete.getCourier()
						.getNombre_courier());
				oForm.setNombre_remitente(paquete.getRemitente().toString());
				oForm.setCodigo_guia_courier(paquete.getCodigo_guia_courier());
				oForm.setObjeto_envio(paquete.getObjeto_envio());
				oForm.setPaquete(paquete.getDocumentos());
				String fecha = f.convertirFecha(new Date());
				oForm.setFecha_recepcion(fecha);
				oForm.setCodigo_guia_courier_recep("");

			} else if (oForm.getTipo() != null
					&& oForm.getTipo().equals("guardar")) {

				System.out.println("Cerrando el paquete de documentos");

				int resultado = daoICierreDAO.cerrar_documentos(oForm);
				daoICierreDAO.actualizarEstadoDocumentos(
						oForm.getCodigo_cierre(), 7);

				oForm.setCodigo_guia_courier_recep("");
				session.setAttribute("listado_pendientes",
						daoICierreDAO.pendientesCierre());

			} else {

				session.setAttribute("listado_pendientes",
						daoICierreDAO.pendientesCierre());

			}
		}

		return (mapping.findForward("exito"));

	}
}
