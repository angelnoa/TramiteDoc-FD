<%@ include file="taglibs.jsp" %>
<html>
<head>
<title>Portal Institucional de CONCYTEC</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<LINK href="css/estilos.css" type="text/css" rel="stylesheet">
<LINK href="css/estilo1.css" type="text/css" rel="stylesheet">
 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />

<style type="text/css">
<!--
.style1 {
	font-size: 14px;
	color: #006699;
	font-weight: bold;
}
-->
</style>
</head>
<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<SCRIPT language="javascript">
	
	function Buscar(){
	
	 if (Validarcampovacio(document.form_datos.anio.value) == false)
              {              
                    alert("Ingresar Año");
                    document.form_datos.anio.focus();
                    return false;           
              } 
	if (Validarcampovacio(document.form_datos.num_exp.value) == false)
              {              
                    alert("Ingresar Numero de Expediente");
                    document.form_datos.num_exp.focus();
                    return false;           
              } 		  
	if (Validarcampovacio(document.form_datos.password.value) == false)
              {              
                    alert("Ingresar Clave");
                    document.form_datos.password.focus();
                    return false;           
              } 
			  			  
	 var ls_anio = document.getElementById("anio").value;
	 var ls_num_exp = document.getElementById("num_exp").value;
	 var ls_password = document.getElementById("password").value;
	
	 
	  
	   var strURL = "VerFrameListas.do?tipo=busquedalinea";
		 strURL += "&anio="+ls_anio+"&num_exp="+ls_num_exp+"&password="+ls_password;
		
		 document.getElementById("ifra_frame_listas").Document.location=strURL;
		// alert(strURL);
		//return;
	
	}
	
	function autoYear() {
		  var time = new Date();
		  var year = time.getYear();
		 
		  if (year < 1900) {
			year = year + 1900;
		  }
		
		  var date = year - 25; /*change the '25' to the number of years in the past you want to show */
		  var future = year + 10; /*change the '10' to the number of years in the future you want to show */ 
		
		  document.writeln ("<select class='caja' id='anio' name='anio'><option value=\"\">------");
		  do {
			date++;
			document.write ("<option value=\"" +date+"\">" +date+ "");
		  }
		  while (date < future)
		  document.write ("</select>");
	}
	
	function SeleccionarCampo(campo, valor){
	  for(var i = 0; i < document.forms[0].elements.length; i++){
		if (document.forms[0].elements[i].name==campo){
		  for(var j=0; j< document.forms[0].elements[i].length; j++){      
			if (document.forms[0].elements[i].options[j].value==valor){
			  document.forms[0].elements[i].options[j].selected=true;
			}
		  }
		}    
	  }
	}
</SCRIPT>

<BODY   bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">

<form id="form_datos" name="form_datos" method="post" action="" >

	
  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
    <!--DWLayoutTable-->
    <TBODY>
      <TR> 
        <TD height="38" colspan="3" valign="top" bgcolor="a8b5c8">
		  <jsp:include page="/headTramite.jsp"/>          		</TD>
      </TR>
      <TR> 
        <TD width="147" height="30" valign="top" ><!--DWLayoutEmptyCell-->&nbsp;		</TD>
        <TD width="684" valign="top" ><!--DWLayoutEmptyCell-->&nbsp;</TD>
        <TD width="147" valign="top" ><!--DWLayoutEmptyCell-->&nbsp;</TD>
      </TR>
      <TR>
        <TD height="430" valign="top" ><!--DWLayoutEmptyCell-->&nbsp;</TD>
        <TD valign="top" ><table width="100%" border="0" cellpadding="0" cellspacing="0" >
          <!--DWLayoutTable-->
          <tr>
            <TD height="25" colspan="3" align="left" valign="middle" background="img/fondoplomo8.jpg" class="Arial16px666666" >&nbsp;&nbsp;Procedimientos Administrativos</TD>
      </tr>
          <tr>
            <TD width="9" height="30" >&nbsp;</TD>
            <TD width="567" align="left" valign="middle" class="alojamientoBold" >Búsqueda On Line de Trámite Documentario</TD>
          <TD width="10" >&nbsp;</TD>
          </tr>
          <tr>
            <TD height="112" >&nbsp;</TD>
            <TD valign="top"  class="Arial11px666666" ><div align="justify">Por medio de esta consulta, la ciudadanía puede visualizar el estado de sus expedientes presentados. Para ello deberá ingresar el Año y Número del Expediente, así como la Contraseña que le proporcionará el Departamento de Trámite Documentario, al momento de la entrega del expediente. <br>
                  <br>
              Dicha contraseña está conformada por números y letras y se consigna en el cargo de recepción del expediente presentado. Cualquier consulta favor comunicarse al correo tramite@concytec.gob.pe. <br>
                  <br>
              Se le recuerda el horario de atención en Mesa de Partes: Lunes a viernes, de 08:00 a 4:15:00 horas.  </div><br></TD>
          <TD >&nbsp;</TD>
          </tr>
          <tr>
            <TD height="84" >&nbsp;</TD>
            <TD valign="top" ><table width="100%" border="0" cellpadding="0" cellspacing="0" class="TextFieldOn13">
              <!--DWLayoutTable-->
              <tr>
                <TD height="22" colspan="4" align="center" valign="middle" background="img/fondoplomo8.jpg" class="alojamientoBold" >B&uacute;squeda de Expediente</TD>
            </tr>
              <tr>
                <TD width="25%" height="21" align="right" valign="middle" class="Arial10px000000" >Año:&nbsp;&nbsp;</TD>
		   <TD width="25%" align="left" valign="middle" ><script type="text/javascript">autoYear();</script></TD>
              <TD width="25%" align="right" valign="middle" class="Arial10px000000">N&deg; del Expediente:&nbsp;&nbsp;</TD>
              <TD width="25%" align="left" valign="middle" ><INPUT class="txt" id="num_exp"  size="15" name="num_exp" ></TD>
              </tr>
              <tr>
                <TD height="21" align="right" valign="middle" class="Arial10px000000">Ingrese su Contraseña:&nbsp;&nbsp;</TD>
                <TD align="left" valign="middle" ><INPUT class="txt" id="password"  size="15" name="password" type="password" ></TD>
                <TD >&nbsp;</TD>
                <TD >&nbsp;</TD>
              </tr>
              <tr>
                <TD height="30" colspan="4" align="center" valign="middle" >  <INPUT  class="boton" style="WIDTH: 80px; HEIGHT: 18px" id="INPUT5" onClick="javascript:Buscar();"   type="button" value="Buscar" name="cmb_grabar"></TD>
              </tr>
            </table>            </TD>
            <TD >&nbsp;</TD>
          </tr>
          <tr>
            <TD height="133" >&nbsp;</TD>
            <TD align="center" valign="middle" ><IFRAME id="ifra_frame_listas"  name="ifra_frame_listas" src="VerFrameListas.do?tipo=busquedalinea" align="middle"  frameBorder="0" width="100%"   scrolling="auto" height="100%"  marginheight="30%"  ></IFRAME></TD>
            <TD >&nbsp;</TD>
          </tr>    
        </table>        </TD>
        <TD valign="top" ><!--DWLayoutEmptyCell-->&nbsp;</TD>
      </TR>
	 			 <SCRIPT language="javascript">
					  SeleccionarCampo("anio","2011");
					  
			    </SCRIPT> 
    </TBODY>
  </TABLE>

</FORM>

</BODY>
</HTML>
