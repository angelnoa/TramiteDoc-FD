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
<HEAD><TITLE>Busqueda de Procedencia</TITLE>
<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />

<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />

<!-- LINK href="css/estilos.css" type="text/css" rel="stylesheet">
<LINK href="css/estilo1.css" type="text/css" rel="stylesheet"-->

<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/modelo.css" type="text/css" />
<STYLE type=text/css>

#scroll { 
     width:400px; 
     /*height:300px; */
     background-color:#ffffff; 
     overflow:auto; 
}


.oficinas th, .oficinas td{
	margin:0;
	padding:0;
}

.oficinas table{
	font-size: small;
    font-family:verdana, arial, helvetica, sans-serif;
	line-height:120%;
	text-align:center;
}

.oficinas caption{
	text-align:left;
	font-size:100%;
	padding:10px 0;
	color: #666;
	font-weight:bold; 
}

.oficinas th {
    font-weight: bold;
    color: #666;
    padding: 7px;
    text-align: center;
    background: #EEE;
    border-top: 1px solid #999;
    border-bottom: 1px solid #999;
    border-right: 1px dashed #e0e0e0;
}
 
.oficinas th:first-child {
    border-left: 1px dashed #e0e0e0;
}
 
.oficinas td {
    padding: 8px;
    font-size: 95%;
	border-bottom: 1px solid #e0e0e0;
    border-right: 1px dashed #e0e0e0;
}
 
.oficinas td:first-child{ 
    border-left: 1px dashed #e0e0e0;
}

 
.oficinas .odd {
    background: #f3f3f3;
}

.oficinas tbody tr:hover { 
 	background:#FFDD36;
 	cursor:pointer;
}

#div_frame_listas tbody tr:hover{
	background:#376FA6;
	color:#FFF;
 	cursor:pointer;

}

#div_frame_listas a:hover{
	/*background:#376FA6;*/
	color:#ff0000;
	font-weight:bolder;
 	cursor:pointer;

}
</STYLE>
<script src="js/funciones.js" type="text/javascript"></script>
<SCRIPT LANGUAGE=javascript src="js/scriptaculous.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
<script src="js/funciones.js" type="text/javascript"></script>


<SCRIPT type=text/javascript>
$(function() {
	  $("#dialog-message-modifica").dialog({
		autoOpen: false,
	    modal: true,
	    buttons: {
	      Ok: function() {
	        $( this ).dialog( "close" );
	        var value = $("#texto_modifica").val();
		    var ls_operacion = $("#sel_opcion_modal").val();
		    var ls_accion = $("#accion").val();
		    var operacion = 'procedenciaa';
		    var codigo = $("#codigo_persona_modifica").val();
		    var ejecuta = 'modificanombre';
	        $("#div_frame_listas").load("obtenerLista.do",{cambia:operacion,ls_texto:value,operacion:ls_operacion,accion:ls_accion,ls_ejecuta:ejecuta,ls_codigo:codigo});
	      },
	      Cancel: function() {
	          $( this ).dialog( "close" );
	        }
	    }
	  });
	  
	  $("#dialog-message-elimina").dialog({
			autoOpen: false,
		    modal: true,
		    buttons: {
		      Ok: function() {
		        $( this ).dialog( "close" );
		        var value = $("#sle_texto").val();
			    var ls_operacion = $("#sel_opcion").val();
			    var ls_accion = $("#accion").val();
			    var operacion = 'procedenciaa';
			    var codigo = $("#codigo_persona_elimina").val();
			    var ejecuta = 'eliminanombre';
		        $("#div_frame_listas").load("obtenerLista.do",{cambia:operacion,ls_texto:value,operacion:ls_operacion,accion:ls_accion,ls_ejecuta:ejecuta,ls_codigo:codigo});
		      },
		      Cancel: function() {
		          $( this ).dialog( "close" );
		        }
		    }
		  });
		  
		  $("#dialog-message-agrega").dialog({
				autoOpen: false,
			    modal: true,
			    buttons: {
			      Ok: function() {
			        var value = $("#texto_agrega").val();
			        
			        if (Validarcampovacio(value) == false){              
		                    alert("Ingresar descripcion");
		                    document.form_datos.texto_agrega.focus();
		                    return false;           
		            }else{
				        $( this ).dialog( "close" );
				        var ls_operacion = $("#sel_opcion_modal_agregar").val();
					    var ls_accion = $("#accion").val();
					    var operacion = 'procedenciaa';
					    var ejecuta = 'agreganombre';
				        $("#div_frame_listas").load("obtenerLista.do",{cambia:operacion,ls_texto:value,operacion:ls_operacion,accion:ls_accion,ls_ejecuta:ejecuta,ls_codigo:0});
		            }
				    
			      },
			      Cancel: function() {
			          $( this ).dialog( "close" );
			        }
			    }
			  });
	});
	
$(document).ready(function(){
	$("#regresar").button();
	
});

$(function() {
    $( document ).tooltip();
});
  
function mostrar_lista(){
	
	$(document).ready(function(){
				
	    var value = $("#sle_texto").val();
	    var ls_operacion = $("#sel_opcion").val();
	    var ls_accion = $("#accion").val();

	    var operacion = 'procedenciaa';
	    //llama al servlet con el parametro seleccionado
	    $("#div_frame_listas").load("obtenerLista.do",{cambia:operacion,ls_texto:value,operacion:ls_operacion,accion:ls_accion});

});

}

function modifica_lista(v1){
	var codigo = v1;
	var operacion = "seteaModifica";
	document.getElementById("codigo_persona_modifica").value=codigo;
	$("#dialog-message-modifica").load("obtenerLista.do",{cambia:operacion,ls_codigo:codigo});
	$("#dialog-message-modifica").dialog("open");
	
}

function agrega_a_lista(){
	$("#dialog-message-agrega").dialog("open");
}

function elimina_de_lista(v1){
	var codigo = v1;
	document.getElementById("codigo_persona_elimina").value=codigo;
	$("#dialog-message-elimina").dialog("open");
}

function ver_frame(){
	
	 var ls_operacion = document.getElementById("sel_opcion").value;
	 var ls_texto = document.getElementById("sle_texto").value;
	 var ls_accion = document.getElementById("accion").value;
	// var ls_accion = <c:out value='${accion}'/>;

	  if(ls_texto!= ""){
		 // alert(ls_texto);
	   var strURL = "VerFrameListas.do?tipo=procedencia";
		 strURL += "&operacion="+ls_operacion+"&sle_texto="+ls_texto.toUpperCase()+"&accion="+ls_accion;
		// alert(strURL);
		 document.getElementById("ifra_frame_listas").Document.location=strURL;
		 
		return;
	  }
	  
	}
function ver_frame_numero(){
	var ls_operacion= "";
	var ls_texto_nat = document.getElementById("sle_natural").value;
	var ls_texto_jur = document.getElementById("sle_juridica").value; 
	
	 if(ls_texto_nat!= ""){
	 
	 ls_operacion = "N";
	 
	 var strURL = "VerFrameListas.do?tipo=procedencia";
	 strURL += "&operacion="+ls_operacion+"&sle_texto="+ls_texto_nat.toUpperCase();
	
 	 document.getElementById("ifra_frame_listas").Document.location=strURL;
	return;
	
	 }else if(ls_texto_jur != ""){
	 	ls_operacion = "J";
	
	 var strURL = "VerFrameListas.do?tipo=procedencia";
	 strURL += "&operacion="+ls_operacion+"&sle_texto="+ls_texto_jur.toUpperCase();
	
 	 document.getElementById("ifra_frame_listas").Document.location=strURL;
	return;
	 
	 }
	 

	}
	
function listar_todos(){
	
	
	// var strURL = "VerFrameListasGlobal.do?tipo=maestanex";
	//window.showModalDialog("ValidaPaginas.do?tipo=instcontactos", window, "dialogHeight:500px; dialogWidth:800px; center:yes; help:no; resizable:no; status:no;scrollbar:no");       
	/*var strURL = "ValidaPaginas.do?tipo=instcontactos";
	
 	 location=strURL;
	return;*/
	
	
	 var ls_accion = document.getElementById("accion").value;
	// var ls_accion = <c:out value='${accion}'/>;
	
	   var strURL = "VerFrameListas.do?tipo=procedencia";
		 strURL += "&accion="+ls_accion;
		
		 document.getElementById("ifra_frame_listas").Document.location=strURL;
		// alert(strURL);
		return;
	}
	
	function agregar(){
	
	ls_accion = document.getElementById("accion").value;
	
	 document.location.href="ValidaPaginas.do?tipo=nuevaprocedencia&operacion=N"+"&accion="+ls_accion;
	return;
	}
	
	function regresar(){
	
	 document.location.href="ValidaPaginas.do?tipo=regresar&pagina=docentrada";
	 return;
	}
	
	function SeleccionarCampo(campo, valor){
		//alert("campo -"+campo+", valor -"+valor);
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
</HEAD>
<BODY bgColor="#F0F5FB"   bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">
<div class="derivaconta">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
<form id="form_datos" name="form_datos" method="post" action="" >
 <INPUT  type="hidden" id="accion" name="accion" value="<c:out value='${accion}'/>">
  	<INPUT  type="hidden" id="codigo_persona_modifica" name="codigo_persona_modifica">
 	<INPUT  type="hidden" id="codigo_persona_elimina" name="codigo_persona_elimina">
<br/>
<br/>
  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 height="100%">
   
    <TBODY>
      
      
      <TR> 
        <TD height="100%" align="center" valign="top" class="tablaArbitraje"  style="WIDTH: 100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <!--DWLayoutTable-->
           
            
            <tr> 
              <td height="273" colspan="5" valign="top" width="100%"> 
                <table width="100%" cellpadding="0" cellspacing="0" >
                
                  <tr valign="middle"> 
                    <td height="40" colspan="3" align="left" bgcolor="265ca6" class="Columna11"  > 
                      &nbsp;&nbsp; <font color="#FFFFFF" size="2" ><strong>B&Uacute;SQUEDA</strong></font> 
                    </td>
                  </tr>
                  <tr valign="middle"> 
                    <td height="50" colspan="3" align="left"  > <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <!--DWLayoutTable-->
                        <tr> 
                          <td width="246" height="28" align="center" valign="middle"><strong>B&uacute;squeda 
                            por :</strong> </td>
                          <td width="188">&nbsp;</td>
                          <td width="107">&nbsp;</td>
                          <td width="100">&nbsp;</td>
                          <td width="120">&nbsp;</td>
                          <td width="218">&nbsp;</td>
                        </tr>
                        <tr> 
                          <td height="29" align="center" valign="middle"> <select name="sel_opcion" id="sel_opcion" size="1"  class="input-medium">
                              <option value="0">--Selec--</option>
                              <option value="N">Persona Natural</option>
                              <option value="J">Persona Juridica</option>
                            </select></td>
                          <td align="center" valign="middle"> 
                          <input type="text" name="sle_texto" id="sle_texto" size="40" class="input" onkeyup="mostrar_lista();"/> 
                          </td>
                          <td align="center" valign="middle"> <input type="button" name="btn_agregar" id="btn_agregar" class="button" value="Agregar" onClick="agrega_a_lista()" title="Agregar Nueva Persona"  ><!-- td align="center" valign="middle"> <input type="button" name="btn_buscar" id="btn_buscar" class="boton" value="Buscar" onClick="ver_frame()" alt="buscar"  --></td>
                          <td align="center" valign="middle"> <!-- input type="button" name="btn_listar_todos" id="btn_listar_todos" class="boton" value="Listar Todos" onClick="listar_todos()" alt="Listar Todos"  --></td>
                          <td height="28" align="center"> <A id="regresar" HREF="javascript:regresar()" title="Regresar al Modulo de Registro"><img src="img/01regresar.png" width="16px" height="16px" align="top"/> Regresar</a><!-- input type="button" name="btn_agregar" id="btn_agregar" class="boton" value="Agregar" onClick="agregar()" alt="Agregar"  --></td>
                          <td valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
                        </tr>
                        <tr> 
                          <td height="10" colspan="6" valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
                        </tr>
                      </table></td>
                  </tr>
                  <tr valign="middle"> 
                    <td width="100%" align="center" valign="top"> </td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr> 
                    <td align="center" valign="top" height="220"> 
                    <div id="div_frame_listas" class="oficinas" style="width: 800px; height: 500px; overflow:auto;">
                    </div>  
                    </td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr align="center" valign="middle"> 
                    <td height="28" align="center"   > </td>
                  </tr>
                 
                </table></td>
            </tr>
          </table></TD>
      </TR>
       
    </TBODY>
  </TABLE>
			<SCRIPT language="javascript">
					  SeleccionarCampo("sel_opcion","<c:out value='${tipo_default}'/>");
			</SCRIPT>
	<div id="dialog-message-modifica" title="Modificar"></div>
	<div id="dialog-message-elimina" title="Eliminar">
	<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>Se eliminara el registro de manera permanente. Esta seguro?</p></div>
	<div id="dialog-message-agrega" title="Agregar">
	<p><select name='sel_opcion_modal_agregar' id='sel_opcion_modal_agregar' class='input-medium' size='1'>
		<option value='N' selected >Persona Natural</option>
		<option value='J'>Persona Juridica</option>
		</select>
		<input type='text' name='texto_agrega' id='texto_agrega'  style='width: 250px;' class='input' />
	</p>
	</div>
</FORM>
</div>
</BODY></HTML>
<% } %>