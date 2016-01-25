<%@ include file="taglibs.jsp" %>
<% 
//Verifica que exista una sesion
String nombreusuario = String.valueOf(session.getAttribute("nombreusuario"));
//System.out.println("el nombre de usuario es.."+nombreusuario);
if (nombreusuario==null || nombreusuario.equals("null")){
%> 
<SCRIPT language="Javascript">
parent.document.location ="pag_expiracion.jsp";
</SCRIPT>
<% 
}
else {
%>

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD><TITLE>Pagina Nueva Procedencia</TITLE>
<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />
<link rel="stylesheet" href="css/modelo.css" type="text/css" />

<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<SCRIPT language="javascript">
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

function cerrar(){
       
       window.history.back();
}
function ingreso(){
        
		ls_accion = document.getElementById("accion").value;
		
			if (Validarcampovacio(document.form_datos.descripcion.value) == false)
              {              
                    alert("Ingresar Descripcion de la Procedencia");
                    document.form_datos.descripcion.focus();
                    return false;           
              } 
			    if(document.form_datos.tipopersona.value=="0"){
		    alert("Ingresar Tipo de Persona");
	 		document.form_datos.tipopersona.focus();
	 		return false;
   			}          
        var ls_operacion = "N";
	document.form_datos.action='MantProcedencia.do'+'?operacion='+ls_operacion+'&accion='+ls_accion;
	document.form_datos.submit();
		
}

function modificar(){
        
		ls_accion = document.getElementById("accion").value;
		
			if (Validarcampovacio(document.form_datos.descripcion.value) == false)
              {              
                    alert("Ingresar Descripcion de la Procedencia");
                    document.form_datos.descripcion.focus();
                    return false;           
              } 
			    if(document.form_datos.tipopersona.value=="0"){
		    alert("Ingresar Tipo de Persona");
	 		document.form_datos.tipopersona.focus();
	 		return false;
   			}          
        var ls_operacion = "M";
	document.form_datos.action='MantProcedencia.do'+'?operacion='+ls_operacion+'&accion='+ls_accion;
	document.form_datos.submit();
		
}

function eliminar(){
        
		ls_accion = document.getElementById("accion").value;
		
        var ls_operacion = "E";
	document.form_datos.action='MantProcedencia.do'+'?operacion='+ls_operacion+'&accion='+ls_accion;
	document.form_datos.submit();
		
}

</SCRIPT>
</HEAD>

<BODY bgColor="#F0F5FB" leftMargin="0"  topMargin="0" rightMargin="0" bottomMargin="0" marginwidth="0" marginheight="0">
<div class="derivaconta">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
<FORM  id="form_datos" name="form_datos" method="post" action="">
 <INPUT  type="hidden" id="accion" name="accion" value="<c:out value='${accion}'/>">
  <TABLE width="100%"  height="100%" border="0" cellpadding="0" cellspacing="0" class="TextFieldOn2">
    <TR> 
      <TD width="100%" height="247" valign="top"  > <CENTER>
          <TABLE width="100%" cellpadding="0" cellspacing="0">
          
            <TR> 
              <TD width="100%" height="49" valign="middle" bgcolor="265ca6" class="Columna11" >&nbsp; 
                <font color="#FFFFFF" size="2">
				<c:choose>
        <c:when test='${operacion ==  "N"}'>
				REGISTRAR PROCEDENCIA
			</c:when>
        <c:when test='${operacion ==  "M"}'>	
				MODIFICAR PROCEDENCIA
		</c:when>
		 <c:otherwise>
		 ELIMINAR PROCEDENCIA
		  </c:otherwise>
		  </c:choose>      		
				</font> </TD>
            </TR>
          </TABLE>
          <TABLE width="100%" border="0"  >
            <!--DWLayoutTable-->
            <TR> 
              <TD height="31" colspan="3" align="left" valign="middle" bgcolor="#F0F5FB" class="titulolistado">&nbsp;</TD>
            </TR>
            <TR> 
              <TD width="9" height="31" >&nbsp;</TD>
              <TD width="270" align="left" valign="middle"  >Descripci&oacute;n 
                de la Procedenc&iacute;a:</TD>
              <TD width="697" align="left" valign="middle" >
			  <c:choose>
			   <c:when test='${operacion ==  "N"}'>
			   <INPUT  id="descripcion" size="80" type="text" name="descripcion"  class="textfield06" >
			  <script language="JavaScript" type="text/javascript">if(document.getElementById) document.getElementById('descripcion').focus();</script> 
			  </c:when>
        <c:when test='${operacion ==  "M"}'>
		<INPUT id="codigo" type="hidden" maxLength="6" size="6" name="codigo" value="<c:out value='${codigo}'/>">
		 <INPUT  id="descripcion" size="80" type="text" name="descripcion"  class="textfield06" value="<c:out value='${descripcion}'/>" >
		 </c:when>
		 
		   <c:otherwise>
		<INPUT id="codigo" type="hidden" maxLength="6" size="6"  readonly name="codigo" value="<c:out value='${codigo}'/>">
		 <INPUT  id="descripcion" size="80" type="text" name="descripcion" readonly class="textfield06" value="<c:out value='${descripcion}'/>" >
		  </c:otherwise>
		  </c:choose>   
			  </TD>
            </TR>
            <TR> 
              <TD height="31" >&nbsp;</TD>
              <TD align="left" valign="middle"  >Tipo Procedenc&iacute;a:</TD>
              <TD align="left" valign="middle" >
			   <c:choose>
			   <c:when test='${operacion ==  "N"}'>
			   <SELECT id="tipopersona" name="tipopersona"  class="textfield05" style="width:100" value=''>
                  <option value="0">--Selec--</option>
                  <option value="N">Natural</option>
                  <option value="J">Juridica</option>
                </SELECT>
				</c:when>
				<c:when test='${operacion ==  "M"}'>
				<SELECT id="tipopersona" name="tipopersona"  class="textfield05" style="width:100" value=''>
						  <option value="0">--Selec--</option>
						  <option value="N">Natural</option>
						  <option value="J">Juridica</option>
						</SELECT>
				</c:when>
				  <c:otherwise>
				<SELECT id="tipopersona" name="tipopersona"  class="textfield05" style="width:100"  >
						  <option value="0">--Selec--</option>
						  <option value="N">Natural</option>
						  <option value="J">Juridica</option>
						</SELECT>
				  </c:otherwise>
			 
		  </c:choose> 	
				 </TD>
            </TR>
            <TR> 
              <TD height="20" >&nbsp;</TD>
              <TD >&nbsp;</TD>
              <TD >&nbsp;</TD>
            </TR>
            <TR> 
              <TD height="69" colspan="3" valign="top"> <table width="100%" border="0">
                  <!--DWLayoutTable-->
                  <tr> 
                    <td height="31" colspan="6" width="100%" align="left" valign="middle" bgcolor="#F0F5FB" class="titulolistado"><!--DWLayoutEmptyCell-->&nbsp;</td>
                  <tr> 
				   <c:if test='${operacion ==  "N" }' > 
                    <TD  width="50%" height="31" align="right" valign="middle">
                      <INPUT name="cmb_buscar" type="button" value="Grabar" onClick="javascript:ingreso()" class="boton" />					  </TD>
                    <TD  width="50%" align="left" valign="middle"> <INPUT name="cmd_salir" onClick="javascript:cerrar();" type="button" id="cmd_salir" value="Cerrar" class="boton">					</TD>
					</c:if>
                  <c:if test='${operacion ==  "M" }' > 
				  <TD  width="50%" height="31" align="right" valign="middle">
                      <INPUT name="cmb_buscar" type="button" value="Modificar" onClick="javascript:modificar()" class="boton" />					  </TD>
                    <TD  width="50%" align="left" valign="middle"> <INPUT name="cmd_salir" onClick="javascript:cerrar();" type="button" id="cmd_salir" value="Cerrar" class="boton">					</TD>
					</c:if>
                    <c:if test='${operacion ==  "E" }' > 
					 <TD  width="50%" height="31" align="right" valign="middle">
                      <INPUT name="cmb_buscar" type="button" value="Eliminar" onClick="javascript:eliminar()" class="boton" />					  </TD>
                    <TD  width="50%" align="left" valign="middle"> <INPUT name="cmd_salir" onClick="javascript:cerrar();" type="button" id="cmd_salir" value="Cerrar" class="boton">					</TD>
				    </c:if>
                </table></TD>
            </TR>
          </TABLE>
		   <c:if test="${operacion ==  'M' || operacion ==  'E'}" > 
                <SCRIPT language="javascript">
					  SeleccionarCampo("tipopersona","<c:out value='${tipodoc}'/>");
			    </SCRIPT> 
                </c:if>	
        </CENTER></TD>
    </TR>
  </TABLE>
</FORM>
</div>
</BODY></HTML>
<% } %>