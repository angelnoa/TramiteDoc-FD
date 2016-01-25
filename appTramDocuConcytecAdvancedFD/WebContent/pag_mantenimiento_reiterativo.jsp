<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
        <script src="js/funciones.js" type="text/javascript"></script>
		<script  src="js/prototype-1.4.0.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/scriptaculous.js"></script>
       
        <script type="text/javascript" src="js/ajaxtags.js"></script>
		
		<script language="JavaScript" src="js/avatec.js"></script>
		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librería para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
<title>Mantenimiento en reiterativo</title>
</head>

<body>
<table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
  <!--DWLayoutTable-->
  <tr>
    <td width="10" height="10" style="height: 10px;"></td>
    <td width="156" align="left"></td>
    <td width="71"></td>
    <td width="300"></td>
    <td width="18"></td>
    <td width="103"></td>
    <td width="10"></td>
    <td width="500"></td>
    <td width="10"></td>
  </tr>
  <tr>
    <td height="20" style="width: 10px;">&nbsp;</td>
    <td style="width: 90px;" class="label" align="left"> N&deg; Registro: </td>
    <td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;</td>
    <td align="left" valign="middle" style="width: 300px;"><input	name="codigo_documento">
      <a  href="javascript:buscar_persona()"></a> </td>
    <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td rowspan="3" align="left" valign="middle" class="label"><span class="label" style="width: 90px;"><span class="label" style="width: 90px;"><span class="label" style="width: 90px;"> Observaci&oacute;n Documento: </span></span></span></td>
    <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td rowspan="3" align="left" valign="middle"><span style="width: 300px;">
      <textarea name="textarea" style="width: 300px;height: 70px;"  styleclass="caja"></textarea>
    </span></td>
    <td style="width: 10px;">&nbsp;</td>
  </tr>
  <tr>
    <td height="20" style="width: 10px;">&nbsp;</td>
    <td style="width: 90px;" class="label" align="left"> N&deg; Doc.: </td>
    <td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;</td>
    <td align="left" valign="middle" style="width: 300px;"><html:text property="numero_documento" style="width: 260px;" styleClass="caja" />
        <input	name="numero_documento" /></td>
    <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td style="width: 10px;">&nbsp;</td>
  </tr>
  <tr>
    <td height="20" style="width: 10px;">&nbsp;</td>
    <td style="width: 90px;" class="label" align="left"> N&deg; Expediente: </td>
    <td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;</td>
    <td align="left" valign="middle" style="width: 300px;"><input name="codigo_expediente"/></td>
    <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td style="width: 10px;">&nbsp;</td>
  </tr>
  <tr>
    <td height="20" style="width: 10px;">&nbsp;</td>
    <td style="width: 90px;" class="label" align="left">Procedencia:</td>
    <td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;</td>
    <td align="left" valign="middle" style="width: 300px;"><input name="codigo_expediente2"/></td>
    <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td align="left" valign="middle" class="label">Editar archivos: </td>
    <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td align="left" valign="middle"><input type="button"  id="btnUpload" name="btnUpload"  value="Agregar Archivos" onclick="AgregarArchivos()" alt="Agregar Archivos" styleclass="boton" />
&nbsp;

  <img src="img/docadjuntos.gif" alt="Documentos Adjuntos" /> </td>
    <td style="width: 10px;">&nbsp;</td>
  </tr>
  <tr>
    <td height="20" style="width: 10px;">&nbsp;</td>
    <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;</td>
    <td align="left" valign="middle" style="width: 300px;"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td align="left" valign="middle"><c:choose></c:choose></td>
    <td style="width: 10px;">&nbsp;</td>
  </tr>
  <tr>
    <td height="2" colspan="9" align="center"><hr /></td>
  </tr>
  <tr>
    <td height="31" colspan="9" align="center"><!--DWLayoutEmptyCell-->&nbsp;</td>
  </tr>
</table>
<table  border="1" align="center" cellpadding="0"  width="100%">
  <tr align="center" >
    <td width="1%"    align="center" class="TextFieldOn36">No</td>
    <td width="10%"    align="center" valign="middle" class="TextFieldOn36" >Origen</td>
    <td width="8%"   align="center" class="TextFieldOn36">Destino</td>
    <td width="17%"    align="center" valign="middle" class="TextFieldOn36">Destinatario</td>
    <td width="16%"    align="center" valign="middle" class="TextFieldOn36">Estado</td>
    <td width="39%"  class="TextFieldOn36"> Observaci&oacute;n </td>
    <td width="9%" align="center"  class="TextFieldOn36">Operaci&oacute;n</td>
  </tr>
  
        <tr  class="tablepar" align="center"  >
          <td align="center"  class="caja7">1</td>
          <td align="center" class="caja7">DSIC</td>
          <td align="center" class="caja7">OGA</td>
          <td align="center" class="caja7">Randy Suclupe </td>
          <td align="center" class="caja7">Por Recibir </td>
          <td class="caja7"><c:out value='${pr.observa_movimiento}'/>
          Ninguna</td>
          <td align="center" class="caja7">Edici&oacute;n/Anulaci&oacute;n</td>
        </tr>
      
  <input  type="hidden" id="valor_fila" name="valor_fila" value="" />
</table>
</body>
</html>
