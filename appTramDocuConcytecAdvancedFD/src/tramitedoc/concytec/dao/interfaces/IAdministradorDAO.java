/*
 * Created on 27/11/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tramitedoc.concytec.dao.interfaces;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.util.Constantes;
/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface  IAdministradorDAO {
    
	/********************************Maestros Usuarios**************************************/
	public abstract Collection of_lista_tipo_usuarios(Connection cnx) throws Exception;
	public abstract Collection of_lista_tipo_sede(Connection cnx, int usuario_sistema) throws Exception;
	public abstract Collection of_lista_usuarios(Connection cnx) throws Exception;
	public abstract Collection of_lista_usuarios_nuevo(String usuario) throws Exception;
	public BUsuario UsuarioCons(Connection cnx,String usuario);
	public void UsuarioIng(Connection cnx,String usuario,String clave,String estado,String flag,String nombres,String apellidos,String email,String dni,String abrev, String sede);
	public int UsuarioMod(Connection cnx,String usuario,String clave,String estado,String flag,String nombres,String apellidos,String email,String dni,String abrev, String sede);
	public void UsuarioElim(Connection cnx,String usuario);
	
/********************************Maestros Oficinas**************************************/
	
	public abstract Collection of_lista_oficinas(Connection cnx) throws Exception;
	public abstract Collection of_lista_oficinas(Connection cnx, int sede) throws Exception;
	public BOficinas OficinaCons(Connection cnx,String codigo_oficina);
	public void OficinaIng(Connection cnx,String nombrecorto,String descripcion_oficina,String siglas_oficina,String es_activo);
	public int OficinaIng(Connection cnx,String nombrecorto,String descripcion_oficina,String siglas_oficina, String es_activo
			,int grupo_oficina, String sede, String oficina);
	
	public void OficinaMod(Connection cnx,String codigo_oficina,String nombre_corto,String descripcion_oficina,String siglas_oficina,String es_activo,String nombre_final,String ruta,String contentype);
	public void OficinaMod(Connection cnx,String codigo_oficina,String nombre_corto,String descripcion_oficina,String siglas_oficina,String es_activo);
	public void OficinaElim(Connection cnx,String codigo_oficina);
	

/********************************Maestros Personal**************************************/
	
	public abstract Collection of_lista_usuarios(String usuario) throws Exception;
	public abstract Collection of_lista_personal(Connection cnx) throws Exception;
	public abstract Collection of_lista_personal(Connection cnx, int sede) throws Exception;
	public BPersonal PersonalCons(Connection cnx,String codigo_personal);
	public void PersonalIng(Connection cnx,String personas,String c_usuario,String cargo_personal,String codigo_oficina,String es_responsable, String validacion);
	public void PersonalMod(Connection cnx,String codigo_personal,String personas,String c_usuario,String cargo_personal,String codigo_oficina,String es_responsable,boolean existeFirma,boolean existePfx,String nombre_imagen,String ruta_imagen, String nombre_pfx, String ruta_pfx, int parseInt, String validacion);
	public void PersonalElim(Connection cnx,String codigo_personal);
	
/********************************Maestros Cargos**************************************/
	
	public abstract Collection of_lista_cargos(Connection cnx) throws Exception;
	public BCargo CargoCons(Connection cnx,String codigo_cargo);
	public void CargoIng(Connection cnx,String nombrecargo);
	public void CargoMod(Connection cnx,String codigo_cargo,String nombrecargo);
	public void CargoElim(Connection cnx,String codigo_cargo);
	
/********************************Maestros Tipo Documento**************************************/
	
	public abstract Collection of_lista_tipodocumento(Connection cnx) throws Exception;
	public BTipoDocumento TipoDocumentoCons(Connection cnx,String codigo_tipo);
	public void TipoDocumentoIng(Connection cnx,String descripcion_tipo);
	public void TipoDocumentoMod(Connection cnx,String codigo_tipo,String descripcion_tipo);
	public void TipoDocumentoElim(Connection cnx,String codigo_tipo);	

/********************************Maestros Solicitud**************************************/
	
	public abstract Collection of_lista_solicitud(Connection cnx) throws Exception;
	public BSolicitud SolicitudCons(Connection cnx,String codigo_solicitud);
	public void SolicitudIng(Connection cnx,String nombre_solicitud);
	public void SolicitudMod(Connection cnx,String codigo_solicitud,String nombre_solicitud);
	public void SolicitudElim(Connection cnx,String codigo_solicitud);
	public abstract Collection of_lista_busca_personal(Connection cnx, String codigoUsuarioBuscar, int sede) throws Exception;
	public abstract Collection of_lista_usuarios(Connection cnx, String codigoUsuarioBuscar, int sede) throws Exception;
	public abstract Collection of_lista_usuarios(Connection cnx, int sede) throws Exception;
	public abstract Collection of_lista_oficinas_x_sede(String pcodigo_institucion) throws Exception;
	public abstract Collection of_lista_oficinas_x_sede_x_group(String pcodigo_institucion) throws Exception;
	public abstract Collection of_lista_oficinas_x_sede_x_group_(String pcodigo_institucion) throws Exception;
	public abstract Collection  of_lista_oficinas_x_sede_x_sub(String codigo_oficina) throws Exception;
	public abstract boolean esUsuarioNuevo(String usuario);		
	
	/********************************Maestros Grupo Oficina**************************************/
	public int GrupoOficinaIng(String descripcion_oficina, String nombrecorto, int sede);
	public void setOrdenOficina(int codigo_oficina, int sede);
	public void setOrdenOficina(int codigo_oficina, int sede, int grupo_oficina, int padre);
	
	/********************************Maestros Persona**************************************/
	public void IngresaPersona(int codigo_oficina_nuevo, String nombre_corto, String siglas_oficina, int oficina, int sede);
}
