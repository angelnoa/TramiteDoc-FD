package tramitedoc.concytec.dao.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BDatosFirmante;
import tramitedoc.concytec.bean.BInfo;
import tramitedoc.concytec.bean.BInfoDocumento;
import tramitedoc.concytec.bean.BInfoInv;
import tramitedoc.concytec.bean.BInfoListaReiterativo;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.bean.BInfoDocumentoProyecto;


public interface IControlBandejasService {
	
	public Collection lista_recepcion_documentos_normal_bandejas(String oficina,int tipo) throws Exception;
	
	/*public ArrayList<EstandarBean> getTipoDocumentosInternosListaTemp(int tipo) throws Exception;

	public ArrayList<EstandarBean> getEstadoDocumentoInternoLista()throws Exception;
	
	public ArrayList<EstandarBean> getEstadoDocumentoInternoListaSecretarias()throws Exception;

	public void saveDocumentoInterno(DocumentoInternoBean documentoInterno, BInfoDocumento bInfoDocuEliminado) throws Exception;*/
	
}
