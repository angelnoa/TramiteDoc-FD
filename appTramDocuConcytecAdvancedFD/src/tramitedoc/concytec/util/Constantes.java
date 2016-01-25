package tramitedoc.concytec.util;



public enum Constantes {
 CarpetaArchivosProduccion("/documents/docTramite"),
 CarpetaArchivosDesarrollo("/documents/docTramite"),
 CarpetaArchivosDesarrolloTemporal("/documents/docTramiteTemporales"),
 CarpetaArchivosDesarrolloTemporalOrigen("/documents/documentosxFirmar/Firmados/"),
 
 CarpetaArchivosDesarrolloTemporalDestino("/documents/docTramiteFirmados/"),
 
 CarpetaArchivosDesarrolloTemporalImagen("/documents/docTramiteFirmados/SellosDigitales/"),
 CarpetaArchivosDesarrolloenTramiteVisadosBackup("/documents/docTramiteFirmadosBackup/BackupVisados/"),
 CarpetaArchivosDesarrolloenTramiteVisadosTemporalBackup("/documents/docTramiteFirmadosBackup/BackupVisadosTemporal/"),
 CarpetaArchivosDesarrolloenTramiteFirmadosBackup("/documents/docTramiteFirmadosBackup/BackupFirmados/"),
 CarpetaArchivosDesarrolloenTramiteFirmadosTemporalBackup("/documents/docTramiteFirmadosBackup/BackupFirmadosTemporal/"),
 CarpetaArchivosDesarrolloenFirmadaDigitalBackup("/documents/docTramiteFirmadosBackup/"),
 CarpetaArchivosDesarrolloenTramiteVisados("/documents/docTramiteFirmados/Visados/"),
 CarpetaArchivosDesarrolloenTramiteFirmados("/documents/docTramiteFirmados/Firmados/"),
 CarpetaArchivosDesarrolloenFirmadaDigital("/documents/docTramiteFirmados/"),
 CarpetaArchivosDesarrolloSellos("/documents/docTramiteFirmados/SellosDigitales/"),
 CarpetaArchivosDesarrolloPfx("/documents/docTramiteFirmados/PfxDigitales/"),
 CarpetaArchivosLocal("D:\\documents\\docTramite"),
 CorreoTramiteDocumentario("tramite.documentario@concytec.gob.pe"),
 DocumentoCreadoExito("Documento Creado con Éxito"),
 DocumentoModificadoExito("Documento Modificado con Éxito"),
 DocumentoFirmadoExito("Documento Firmado con Éxito"),
 CarpetaArchivosMembretesDesarrollo("/documents/docTramite/membretes_oficinas"),
 CarpetaArchivosMembretesProduccion("/documents/docTramite/membretes_oficinas"),
 CarpetaArchivosMembretesLocal("D:\\documents\\docTramite\\membretes_oficinas\\"),
 CorreoTO("TO"),
 CorreoCC("CC"),
 CorreoBCC("CCP"), 
 NumeroCaracteresPassword(5),
 TipoDeSede("tipo_sede");//copia oculta
 String nombre;
 int numeroCaractes;
 Constantes(String ruta) { this.nombre = ruta; }
 Constantes(int numero) { this.numeroCaractes = numero; }
 public String getNombre( ) { return nombre; }
 public int getNumeroCaractes( ) { return numeroCaractes; }

};

