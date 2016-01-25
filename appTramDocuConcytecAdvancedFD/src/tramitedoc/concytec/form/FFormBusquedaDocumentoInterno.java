package tramitedoc.concytec.form;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;


import tramitedoc.concytec.bean.DocumentoInternoBean;


/**
 * AUTOR		: Pelaez Sarmiento, Moises
 * FECHA		: 19-02-2012
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Mapeo de datos del recibidos del jsp  
 */
public class FFormBusquedaDocumentoInterno extends ValidatorForm {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 4152512310908945280L;
	protected  Log log = LogFactory.getLog(FFormBusquedaDocumentoInterno.class);
	/**
	 * 
	 */

	protected FormFile _file;
	protected FormFile _filesupload;
	protected Collection _archivos;
	
	
	public void setFile(FormFile file){ _file = file; }
    public FormFile getFile(){ return _file; }
         
    public void setFileupload(FormFile fileupload){ _filesupload = fileupload; }
    public FormFile getFileupload(){ return _filesupload; }
    
	public void setArchivos(Collection archivos) {_archivos = archivos;}
	public Collection getArchivos() {return _archivos;}
		
	protected DocumentoInternoBean _documentoInternoBean;	
	//private ArrayList<EstandarBean> tipoDocumentoInternosList = new ArrayList<EstandarBean>();
	
	public FFormBusquedaDocumentoInterno(){
		_documentoInternoBean = new DocumentoInternoBean();
		
		//_documentoInternoBean.setCopia_oficinas_destino(new String[0]);

	}
	public void setCodigo_estado_documento(Integer codigo_estado_documento) {
		_documentoInternoBean.setCodigo_estado_documento(codigo_estado_documento);
	}
	public Integer getCodigo_estado_documento() {
		return _documentoInternoBean.getCodigo_estado_documento();
	}
	public Integer getCodigo_tipo_documento_interno() {
		return _documentoInternoBean.getCodigo_tipo_documento_interno();
	}
	public void setCodigo_tipo_documento_interno(
			Integer codigo_tipo_documento_interno) {
		_documentoInternoBean.setCodigo_tipo_documento_interno(codigo_tipo_documento_interno);
	}
	public Integer getCodigo_documento() {
		return _documentoInternoBean.getCodigo_documento();
	}
	public void setCodigo_documento(Integer codigo_documento) {
		_documentoInternoBean.setCodigo_documento(codigo_documento);
	}
	
	public Integer getCodigo_expediente() {
		return _documentoInternoBean.getCodigo_expediente();
	}
	public void setCodigo_expediente(Integer codigo_expediente) {
		_documentoInternoBean.setCodigo_expediente(codigo_expediente);
	}
	
	public Integer getCodigo_recepcion() {
		return _documentoInternoBean.getCodigo_recepcion();
	}
	public void setCodigo_recepcion(Integer codigo_recepcion) {
		_documentoInternoBean.setCodigo_recepcion(codigo_recepcion);
	}
	
	public Integer getSecuencia_movimiento() {
		return _documentoInternoBean.getSecuencia_movimiento();
	}
	public void setSecuencia_movimiento(Integer secuencia_movimiento) {
		_documentoInternoBean.setSecuencia_movimiento(secuencia_movimiento);
	}
	
	public String getAsunto() {
		return _documentoInternoBean.getAsunto();
	}
	public void setAsunto(String asunto) {
		_documentoInternoBean.setAsunto(asunto);
	}
	public String getDescripcion() {
		return _documentoInternoBean.getDescripcion();
	}
	public void setDescripcion(String descripcion) {
		_documentoInternoBean.setDescripcion(descripcion);
	}
	
	public String getRadiobuttons() {
		return _documentoInternoBean.getRadiobuttons();
	}
	public void setRadiobuttons(String radiobuttons) {
		_documentoInternoBean.setRadiobuttons(radiobuttons);
	}
	
	public String getDirigido_a() {
		return _documentoInternoBean.getDirigido_a();
	}
	public void setDirigido_a(String dirigido_a) {
		_documentoInternoBean.setDirigido_a(dirigido_a);
	}
	public String getFirmado_por() {
		return _documentoInternoBean.getFirmado_por();
	}
	public void setFirmado_por(String firmado_por) {
		_documentoInternoBean.setFirmado_por(firmado_por);
	}
	public Date getFecha_creacion() {
		return _documentoInternoBean.getFecha_creacion();
	}
	public void setFecha_creacion(Date fecha_creacion) {
		_documentoInternoBean.setFecha_creacion(fecha_creacion);
	}
	public Date getFecha_modificacion() {
		return _documentoInternoBean.getFecha_modificacion();
	}
	public void setFecha_modificacion(Date fecha_modificacion) {
		_documentoInternoBean.setFecha_modificacion(fecha_modificacion);
	}
	
	public DocumentoInternoBean getDocumentoInterno() {
		return _documentoInternoBean;
	}
	public void setDocumentoInterno(DocumentoInternoBean documentoInternoBean) {
		_documentoInternoBean=documentoInternoBean;
	}
	
	public Integer getCodigo_oficina() {
		return _documentoInternoBean.getCodigo_oficina();
	}
	public void setCodigo_oficina(Integer codigo_oficina) {
		_documentoInternoBean.setCodigo_oficina(codigo_oficina);
	}
	public Integer getPersonas() {
		return _documentoInternoBean.getPersonas();
	}
	public void setPersonas(Integer personas) {
		_documentoInternoBean.setPersonas(personas);
	}
	
	public String getMotivo() {
		return _documentoInternoBean.getMotivo();
	}
	public void setMotivo(String motivo) {
		_documentoInternoBean.setMotivo(motivo);
	}
	
	public String getActividad() {
		return _documentoInternoBean.getActividad();
	}
	public void setActividad(String actividad) {
		_documentoInternoBean.setActividad(actividad);
	}
	
	public String getMeta() {
		return _documentoInternoBean.getMeta();
	}
	public void setMeta(String meta) {
		_documentoInternoBean.setMeta(meta);
	}
	
	public String getEspecifica() {
		return _documentoInternoBean.getEspecifica();
	}
	public void setEspecifica(String especifica) {
		_documentoInternoBean.setEspecifica(especifica);
	}
	
	public String getValor() {
		return _documentoInternoBean.getValor();
	}
	public void setValor(String valor) {
		_documentoInternoBean.setValor(valor);
	}
	
	public String getObservacion() {
		return _documentoInternoBean.getObservacion();
	}
	public void setObservacion(String observacion) {
		_documentoInternoBean.setObservacion(observacion);
	}
	
	public String getNota() {
		return _documentoInternoBean.getNota();
	}
	public void setNota(String nota) {
		_documentoInternoBean.setNota(nota);
	}
	
	public String getNombre_arhivo() {
		return _documentoInternoBean.getNombre_arhivo();
	}
	public void setNombre_arhivo(String nombre_arhivo) {
		_documentoInternoBean.setNombre_arhivo(nombre_arhivo);
	}
	
	public Integer getCodigo_oficina_pertenece() {
		return _documentoInternoBean.getCodigo_oficina_pertenece();
	}
	public void setCodigo_oficina_pertenece(Integer codigo_oficina_pertenece) {
		_documentoInternoBean.setCodigo_oficina_pertenece(codigo_oficina_pertenece);
	}
	
	public void setCodigo_documento_interno_busqueda(Integer codigo_documento_interno_busqueda) {
		_documentoInternoBean.setCodigo_documento_interno_busqueda(codigo_documento_interno_busqueda);
	}
	public Integer getCodigo_documento_interno_busqueda() {
		return _documentoInternoBean.getCodigo_documento_interno_busqueda();
	}
	
	public Integer getNumero_doc() {
		return _documentoInternoBean.getNumero_doc();
	}
	public void setNumero_doc(Integer numero_doc) {
		_documentoInternoBean.setNumero_doc(numero_doc);
	}
	public Integer getAnio_doc() {
		return _documentoInternoBean.getAnio_doc();
	}
	public void setAnio_doc(Integer anio_doc) {
		_documentoInternoBean.setAnio_doc(anio_doc);
	}
	
	public boolean getChecked() {
		return _documentoInternoBean.isChecked();
	}
	public void setChecked(boolean checked) {
		_documentoInternoBean.setChecked(checked);
	}
	
	
	
	
	 @Override 
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		 try {
		      request.setCharacterEncoding("UTF-8");    		      
		    }
		    catch (UnsupportedEncodingException ex) {
		    }

		return super.validate(mapping, request);
	}
	
	 
	 @Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		 
		 _documentoInternoBean.setCopia_oficinas_destino(new String[0]);
		 _documentoInternoBean.setChecked(false);
		 
		  try {
		      request.setCharacterEncoding("UTF-8");
		 
		      
		  }
		    catch (UnsupportedEncodingException ex) {
		    }
		super.reset(mapping, request);
	}
	 
	 
	 public void setNombreSelloOficina(String nombreSelloOficina) {
		 _documentoInternoBean.setNombreSelloOficina(nombreSelloOficina);
		}
		public String getNombreSelloOficina() {
			return _documentoInternoBean.getNombreSelloOficina();
		}
		
		public void setNombre_oficial_anio(String nombre_oficial_anio) {
			_documentoInternoBean.setNombre_oficial_anio(nombre_oficial_anio);
		}
		public String getNombre_oficial_anio() {
			return _documentoInternoBean.getNombre_oficial_anio();
		}
	
		
	/*	public void setCopia_oficinas_destino(ArrayList<String> copia_oficinas_destino) {
			this._documentoInternoBean.setCopia_oficinas_destino(copia_oficinas_destino);
		}
		public ArrayList<String> getCopia_oficinas_destino() {
			return _documentoInternoBean.getCopia_oficinas_destino();
		}*/
		
	/*	 private String stringMultibox[] = new String[0];

		    public String[] getStringMultibox() {
		        return (this.stringMultibox);
		    }

		    public void setStringMultibox(String stringMultibox[]) {
		        this.stringMultibox = stringMultibox;
		    }
		*/
		
	

		    public String[] getCopia_oficinas_destino() {
		        return (this._documentoInternoBean.getCopia_oficinas_destino());
		    }

		    public void setCopia_oficinas_destino(String stringMultibox[]) {
		    	this._documentoInternoBean.setCopia_oficinas_destino(stringMultibox);
		    }
			public void setCargo_persona_destinatario(
					String cargo_persona_destinatario) {
				this._documentoInternoBean.setCargo_persona_destinatario(cargo_persona_destinatario);
				
			}
			public void setReferencia(String referencia) {
				this._documentoInternoBean.setReferencia(referencia);
			}
			
			public String getReferencia() {
				return this._documentoInternoBean.getReferencia();
			}
			
			public void setNombre_doc_adjuntos(String nombre_doc_adjuntos) {
				this._documentoInternoBean.setNombre_doc_adjuntos(nombre_doc_adjuntos);
			}
			public String getNombre_doc_adjuntos() {
				return this._documentoInternoBean.getNombre_doc_adjuntos();
			}
			
			public void setCodigo_documento_interno(Integer codigo_documento_interno) {
				this._documentoInternoBean.setCodigo_documento_interno(codigo_documento_interno);
			}
			public Integer getCodigo_documento_interno() {
				return this._documentoInternoBean.getCodigo_documento_interno();
			}
			
			public void setAbreviatura_grado_profesion(
					String abreviatura_grado_profesion) {
				this._documentoInternoBean.setAbreviatura_grado_profesion(abreviatura_grado_profesion);
			}
			
			public String getAbreviatura_grado_profesion() {
				return _documentoInternoBean.getAbreviatura_grado_profesion();
			}
			
			public String getTipo_envio() {
				return _documentoInternoBean.getTipo_envio();
			}
			public void setTipo_envio(String tipo_envio) {
				_documentoInternoBean.setTipo_envio(tipo_envio);
			}	
	
}
