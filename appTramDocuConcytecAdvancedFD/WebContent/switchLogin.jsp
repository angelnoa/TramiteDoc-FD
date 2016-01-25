<% 
//Verifica que exista una sesion
String nombreusuario = String.valueOf(session.getAttribute("nombreusuario"));
String flag = String.valueOf(session.getAttribute("ls_flag"));

if (nombreusuario==null || nombreusuario.equals("null")|| !flag.equals("A") ){
%> 
<SCRIPT language="Javascript">
parent.document.location ="index_back.jsp";
</SCRIPT>
<% 
}else {
%>
<%@ include file="taglibs.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="tramitedoc.concytec.bean.*" %>
<%@ page import="tramitedoc.concytec.dao.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="language" content="es" />
<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />
<title>Acceso al Sistema de Tramite</title>
<!--TERMINA EL HEAD-->
</head>
<body topmargin="0" leftmargin="0">

  <%
  	SqlParametroDAO sqlParametroDAO = new SqlParametroDAO();	
	List<BParametro> parametros = sqlParametroDAO.listar();
	String Valor = "";
	String ParametroGoogle = "GLE";
	Integer Id = 0;
	for(BParametro p : parametros){
		if(p.getSimbolo().equalsIgnoreCase(ParametroGoogle)){
			Id = p.getCodigo_parametro();
			Valor = p.getValor();
			break;
		}
	}
  %>
	
	<!--CONTENT-->
	<form action="AActionParametro" method='post'>
	
	<table width="100%" cellspacing="7px." cellpadding="0">
				<tr>
					<td style="width:100%">
						<div class="TitleScreen">
							&nbsp;&nbsp;Modificación de Inicio de Sesión en el Sistema
						</div>
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							<tr>
								<td width="8" height="23" style="height: 10px;">&nbsp;
									
								</td>
								<td width="88" align="left">&nbsp;
									
								</td>
								<td width="8">&nbsp;
									
								</td>
								<td colspan="2">&nbsp;
									
								</td>
								<td width="13">&nbsp;
									
								</td>
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Actual Inicio de Sesión:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <%if(Valor.equalsIgnoreCase("1")){
							    		out.println("<input type='text' value='Inicio de Sesión con Google' disabled='' style='width:200px;'/>");
							    	}else{
							    		out.println("<input type='text' value='Inicio de Sesión Tradicional' disabled='' style='width:200px;'/>");
							    	} %>
							  	</td>
								<td style="width: 10px;">&nbsp;
									
								</td>
							</tr>
													
                           
							<tr>
								<td height="23" style="height: 10px;">&nbsp;
									
								</td>
								<td align="left">&nbsp;
									
								</td>
								<td>&nbsp;
									
								</td>
								<td colspan="2">&nbsp;
									
								</td>
								<td>&nbsp;
									
								</td>
							</tr>
							<tr>
								<td height="31" colspan="6" align="center">
									<table>
										<tr>
											<td>
												<input type="hidden" id="hdfIdParametro" name="hdfIdParametro" value="<%=Id%>">
											</td>
											<td>
												<input type='submit' value='Cambiar Inicio de Sesión' class="button">
											</td>
											<td>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="23" style="height: 10px;">&nbsp;
									
								</td>
								<td align="left">&nbsp;
									
								</td>
								<td>&nbsp;
									
								</td>
								<td colspan="2">&nbsp;
									
								</td>
								<td>&nbsp;
									
								</td>
							</tr>
					  </table>
                   
          		
				  </td>
				</tr>
				
			</table>
			
    </form>

</body>
</html>
<% 
}
%>