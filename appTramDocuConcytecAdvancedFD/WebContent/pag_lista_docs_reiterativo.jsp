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
<title>Busqueda de expedientes</title>
</head>

<body>
<table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
  <!--DWLayoutTable-->
  <tr>
    <td width="10" height="10" style="height: 10px;"></td>
    <td width="117" align="left"></td>
    <td width="235"></td>
    <td width="300"></td>
    <td width="324" colspan="5"></td>
  </tr>
  <tr>
    <td height="20" style="width: 10px;">&nbsp;</td>
    <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td style="width: 10px;" class="labelsubtitle"><span class="label" style="width: 90px;">Oficina:</span></td>
    <td align="left" valign="middle" style="width: 300px;"><label>
      <select name="select">
      </select>
      </label>
      <a  href="javascript:buscar_persona()"></a> </td>
    <td colspan="5"></td>
  </tr>
  <tr>
    <td height="20" style="width: 10px;">&nbsp;</td>
    <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td style= "align=&quot;center&quot;" class="labelsubtitle"><span class="label" >Nro de registro:</span></td>
    <td align="left" valign="middle" style="width: 300px;"><label></label>
        <a  href="javascript:buscar_persona()"></a>
        <label>
        <input type="text" name="textfield" />
      </label></td>
    <td colspan="5"></td>
  </tr>
  <tr>
    <td height="20" style="width: 10px;">&nbsp;</td>
    <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td style= align="center" class="labelsubtitle"><span class="label" >Nro de expediente:</span></td>
    <td align="left" valign="middle" style="width: 300px;"><label></label>
      <a  href="javascript:buscar_persona()"></a> <label>
      <input type="text" name="textfield" />
      </label></td>
    <td colspan="5"></td>
  </tr>
  <tr>
    <td height="20" style="width: 10px;">&nbsp;</td>
    <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;</td>
    <td align="left" valign="middle" style="width: 300px;"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td colspan="5"></td>
  </tr>
  
  <tr>
    <td height="2" colspan="9" align="center"><hr /></td>
  </tr>
  <tr>
    <td height="31" colspan="9" align="center"><input name="btn" type="submit" value="Buscar"></td>
  </tr>
</table>
<table  border="1" align="center" cellpadding="0"  width="100%">
  <tr align="center" >
    <td width="1%"    align="center" class="TextFieldOn36">No</td>
    <td width="10%"    align="center" valign="middle" class="TextFieldOn36" >Nro Registro </td>
    <td width="8%"   align="center" class="TextFieldOn36">Nro Documento </td>
    <td width="17%"    align="center" valign="middle" class="TextFieldOn36">Nro Expediente </td>
    <td width="16%"    align="center" valign="middle" class="TextFieldOn36">Procedencia</td>
    <td width="39%"  class="TextFieldOn36"> Observaci&oacute;n </td>
    <td width="9%" align="center"  class="TextFieldOn36">Accion</td>
  </tr>
  
        <tr  class="tablepar" align="center"  >
          <td align="center"  class="caja7">1</td>
          <td align="center" class="caja7">&nbsp;</td>
          <td align="center" class="caja7">&nbsp;</td>
          <td align="center" class="caja7">&nbsp;</td>
          <td align="center" class="caja7">&nbsp;</td>
          <td class="caja7"><c:out value='${pr.observa_movimiento}'/></td>
          <td align="center" class="caja7"><a href="pag_registro_reiterativo.html">Registrar</a>/<a href="pag_mantenimiento_reiterativo.jsp">Mantener</a></td>
        </tr>
      
  <input  type="hidden" id="valor_fila" name="valor_fila" value="" />
</table>
</body>
</html>
