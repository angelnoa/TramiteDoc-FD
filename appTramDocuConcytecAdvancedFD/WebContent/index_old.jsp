<%@ include file="taglibs.jsp" %>
<html>
<head>
<title>Sistema de Tramite Documentario Concytec--</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<LINK href="css/estilos.css" type="text/css" rel="stylesheet">
<LINK href="css/estilo1.css" type="text/css" rel="stylesheet">
 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />

</head>

<SCRIPT language="javascript">
	
	function ingreso(){
		form_datos.action="Login.do";
		form_datos.submit();	
	}
</SCRIPT>

<BODY   bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" background="img/background.gif">

<form id="form_datos" name="form_datos" method="post" action="" >

  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
    <!--DWLayoutTable-->
    <TBODY>
      <TR> 
        <TD height="38" colspan="3" valign="top" bgcolor="a8b5c8" ><jsp:include page="/headTramite.jsp"/></TD>
      </TR>
      <TR> 
        <TD width="20%" rowSpan=2 valign="top" ><!--DWLayoutEmptyCell-->&nbsp;</TD>
        <TD width="588"  height="51" valign="top" ><!--DWLayoutEmptyCell-->&nbsp;</TD>
        <TD width="20%" rowSpan=2 valign="top" ><!--DWLayoutEmptyCell-->&nbsp;</TD>
      </TR>
      <TR> 
        <TD height="325" valign="top" ><TABLE  id=Table3 cellSpacing=1 cellPadding=1 align="center" class="TextFieldOn2"  >
                  <!--DWLayoutTable-->
                  <TBODY>
                    <TR> 
                      <TD height="19" colSpan=4 valign="top" class="TextFieldOn6" background="img/fondoplomo8.jpg">&nbsp;Ingreso 
                        de Usuarios</TD>
                    </TR>
                    <TR> 
                      <TD width="18" height="250">&nbsp;</TD>
                      <TD width="268" align="center" valign="middle"> <TABLE width="100%" border=0 cellSpacing=5 style="WIDTH: 200px">
                          <!--DWLayoutTable-->
                          <TBODY>
                            <TR> 
                              <TD width="261" colSpan=3 vAlign=top > </TD>
                            </TR>
                            <TR> 
                              <TD 
                                					style="WIDTH: 150px; HEIGHT: 8px; TEXT-ALIGN: center" 
                                					vAlign=top colSpan=3> <TABLE id=TABLE1 
                                					style="WIDTH: 184px; HEIGHT: 99px" cellSpacing=0 
                                					cellPadding=0 border=0>
                                  <TBODY>
                                    <TR> 
                                      <TD  
                                							vAlign=top align="center"> 
                                        <TABLE style="WIDTH: 180px" cellSpacing=0 
                                							cellPadding=0 border=0>
                                          <!--DWLayoutTable-->
                                          <TBODY>
                                            <TR> 
                                              <TD width="180" height="20" align=left style="WIDTH: 169px; HEIGHT: 20px" class="TextFieldOn7">Usuario:</TD>
                                            </TR>
                                            <TR> 
                                              <TD height="20" align=left style="WIDTH: 169px; HEIGHT: 20px"> 
                                                <input type="text" maxlength="20" name="usuario" id="tx_usuario" class="TextFieldOff" > 
                                                <script language="JavaScript" type="text/javascript">if(document.getElementById) document.getElementById('tx_usuario').focus();</script>                                              </TD>
                                            </TR>
                                            <TR> 
                                              <TD height="20" align=left style="WIDTH: 169px; HEIGHT: 20px" class="TextFieldOn7">Password:</TD>
                                            </TR>
                                            <TR> 
                                              <TD height="20" align=left style="WIDTH: 169px; HEIGHT: 20px"> 
                                                <input type="password" name="password" id="tx_clave" class="TextFieldOff">                                              </TD>
                                            </TR>
                                            <TR> 
                                              <TD height="20" align="center" valign="top"><!--DWLayoutEmptyCell-->&nbsp;                                              </TD>
                                            </TR>
                                            <TR> 
                                              <TD height="30" align="left" valign="top" style="WIDTH: 169px; HEIGHT: 20px"> 
                                                <input  type="submit" class="boton"  name="button" value="Ingresar"  onClick="javascript:ingreso()">                                              </TD>
                                            </TR>
                                            <TR> 
                                              <TD height="15" valign="top" > </TD>
                                            </TR>
                                            <TR> <html:errors property="proceso"/> 
                                              <TD height="22" align="center" valign="middle" ><b> 
                                                <font color="#FF0000"> <html:errors property="login"/> 
                                                </font> </b> </TD>
                                            </TR>
                                            <TR> 
                                              <TD height="22" align="center" valign="middle"> 
                                                <b> <font color="#FF0000"> <html:errors property="password"/> 
                                                </font> </b> </TD>
                                            </TR>
                                          </TBODY>
                                        </TABLE></TD>
                                    </TR>
                                  </TBODY>
                                </TABLE></TD>
                            </TR>
                          </TBODY>
                        </TABLE></TD>
                      <TD width="1">&nbsp;</TD>
                      <TD width="142" valign="top"><img src="img/documentos.gif" width="143"></TD>
                    </TR>
                  </TBODY>
                </TABLE></TD>
      </TR>
      <TR align="center" valign="top"> 
        <TD height="58" >&nbsp;</TD>
        <TD valign="top" >Cualquier consulta sírvase comunicarse con Tramite Documentario 
          o envíe un correo electrónico a : informes@concytec.gob.pe </TD>
      <TD >&nbsp;</TD>
      </TR>
    </TBODY>
  </TABLE>

</FORM>

</BODY>
</HTML>
