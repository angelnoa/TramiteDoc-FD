<%@ include file="taglibs.jsp" %>
<HTML><HEAD><TITLE></TITLE>
<LINK href="css/estilos.css" type="text/css" rel="stylesheet">
</HEAD>
<SCRIPT src="js/AC_RunActiveContent.js" 
type=text/javascript></SCRIPT>
<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<SCRIPT src="js/AC_ActiveX.js" type=text/javascript></SCRIPT>

<SCRIPT language=JavaScript type=text/JavaScript>
<!--
function Sistema_Tramite_Anterior(){
		var strURL="http://ap.concytec.gob.pe/tdocu/Tdoc/Informacion_TramitesTdoc.asp"
 		window.open(strURL,"","HEIGHT=480,WIDTH=730,scrollbars=yes")
		}
		
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

var submitted = false;
function submitCheck() {
                      if (submitted) {
                          alert("Ya ha pulsado el botón de Entrar. Por favor, espere...");
					 return false;
                      } else {
                          submitted = true;
                          return true;
                      }
             }

function menu(id,cont){
        var i=1; 

       
//		 alert(id);
                if(i==id)
                {
                        x = document.getElementById('s'+i);
                        //x.style.display = 'block';
						x.style.display = '';
//						alert('Abre s'+i);
						
						

                }
                else
                {
                        x = document.getElementById('s'+i);
//                        alert(x.style.display);
						x.style.display = 'none';
				//		alert('Cierra s'+i);
                }
                i++;
        
}


//-->		
</SCRIPT>

<SCRIPT src="js/urchin.js" type=text/javascript>
</SCRIPT>

<BODY  leftMargin=0 topMargin=0 rightMargin=0 bottommargin="0" marginheight="0" marginwidth="0" >

<TABLE  cellSpacing=0 cellPadding=0 width="100%"   height=""
align=center border=0>
  <TBODY>
  <TR>
    <TD>
      <SCRIPT language=JavaScript type=text/JavaScript>
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
//-->
</SCRIPT>

      <SCRIPT language=JavaScript>
<!--
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
};

function MM_showHideLayers2() {
  var i,p,v,obj,args=MM_showHideLayers2.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
};

function MM_goToURL() { 
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
};

function MM_showHideLayersALL(){
MM_showHideLayers2('menudominio','','hide');
MM_showHideLayers2('multidominio','','hide');
MM_showHideLayers2('promocionweb','','hide');
MM_showHideLayers2('ofertas','','hide');
MM_showHideLayers2('diseñoweb','','hide');
MM_showHideLayers2('reportes','','hide');

};
-->
</SCRIPT>

      <SCRIPT language=JavaScript type=text/JavaScript>
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</SCRIPT>

      <TABLE height=38 cellSpacing=0 cellPadding=0 align=center bgcolor="a8b5c8" border=0 width="100%">
        <!--DWLayoutTable-->
          <TBODY>
        <TR>
          <TD width=100% height="70" align=center vAlign=middle><jsp:include page="/headTramite.jsp"/></TD>
            </TR></TBODY>
      </TABLE>
      <!-- 750 -->
      <TABLE height=25 cellSpacing=0 cellPadding=0 width="100%" align=center 
      border=0>
        <TBODY>
        <TR>
          <TD bgColor=#ffffff><IMG height=1 
            src="index_php_archivos/pixel_blank.gif" width=1></TD></TR>
        <TR>
          <TD background=img/g_fondobarramenu.gif><!-- 750 -->
                <TABLE height=23 cellSpacing=0 cellPadding=0 width=100% border=0>
                  <!--DWLayoutTable-->
                  <TBODY>
              <TR>
                      <TD width=72 height="30" 
                align=middle valign="top" class=Arial12pxFFFFFF ><!--DWLayoutEmptyCell-->&nbsp; </TD>
                <TD width=2><IMG height=23 
                  src="img/g_separador_cajasmenu.gif" width=2></TD>
                      <TD class=Arial12pxFFFFFF 
                onmouseover="this.style.backgroundColor='0964EB';this.style.cursor='hand'; " 
                onmouseout="this.style.backgroundColor='';" 
                align=center width=114><A  class=topmenu
                  href="pag_mesa_partes.jsp" >Inicio</A> </TD>
				
                <TD width=2><IMG height=23 
                  src="img/g_separador_cajasmenu.gif" width=2></TD>
                <TD width=100 
                align=center valign="middle" class=Arial12pxFFFFFF 
                onmouseover="this.style.backgroundColor='0964EB';this.style.cursor='hand'; MM_showHideLayers2('diseñoweb','','show')" 
                onmouseout="this.style.backgroundColor=''; MM_showHideLayersALL();">
                  <DIV id=diseñoweb 
                  style="Z-INDEX: 1; VISIBILITY: hidden; WIDTH: 140px; POSITION: absolute; TOP: 90px; HEIGHT: 30px" 
                  name="diseñoweb">
                  <TABLE height=50 cellSpacing=0 cellPadding=4 width="100%" 
                  border=0 >
                    <TBODY>
                     
						
					 <TR>
                    <TR>
                      <TD bgcolor="#666666" height="15"><A  class="topmenu1" 
                        href="ActionSeleccion.do?rb_seleccion=E" target="_parent">Registro Doc. 
                        </A></TD></TR>
						
					 <TR>
                      <TD bgcolor="#666666" height="15" ><A class="topmenu1" 
                        href="ListaDocumentos.do?tipo=recepcion&operacion=RN&inicia=SI"  target="_parent">Recepci&oacute;n Doc.</A></TD>
						</TR>
				
					<TR>
                      <TD bgcolor="#666666" height="15" ><A class="topmenu1" 
                        href="ListaDocumentos.do?tipo=derivar&operacion=D&inicia=SI"  target="_parent">Derivaci&oacute;n Doc..</A></TD></TR>
						<TR>
                      <TD bgcolor="#666666" height="15" ><A class="topmenu1" 
                        href="ListaDocumentos.do?tipo=archivar&operacion=A&inicia=SI"  target="_parent">Archivar Doc.</A></TD></TR>
						<TR>
                      <TD bgcolor="#666666" height="15" ><A class="topmenu1" 
                        href="ListaDocumentos.do?tipo=anular&operacion=A&inicia=SI"  target="_parent">Anular Doc.</A></TD></TR>
						<c:choose>
				  <c:when test="${codigo_oficina ==  '1' }" >
						<TR>
                      <TD bgcolor="#666666" height="15" ><A class="topmenu1" 
                        href="ListaDocumentos.do?tipo=preliquidar&operacion=P"  target="_parent">Pre-Cerrar Doc.</A></TD>
                        
                        </TR>
                        
					<TR>
                      <TD bgcolor="#666666" height="15" ><A class="topmenu1" 
                        href="ListaDocumentos.do?tipo=liquidar&operacion=L"  target="_parent">Cerrar Doc.</A></TD></TR>
						</c:when>
						</c:choose>
                    <TR>
                      <TD bgcolor="#666666" height="15" ><A class="topmenu1" 
                        href="Reiterativo.do"  target="_parent">Reiterativo</A></TD></TR>
                 <!--     
                    <c:choose>
                    	<c:when test="${cargo_personal eq '11' || cargo_personal eq '10' || cargo_personal eq '13' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3'}">
                    	
                     <TR>
                      <TD bgcolor="#666666" height="15"><A  class="topmenu1" 
                        href="MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI" target="_parent">Crear Documento Interno 
                        </A></TD>
                        
                      </TR>
                      
                    	</c:when>
                    
                    </c:choose>  
                    
                     <c:choose>
                    	<c:when test="${cargo_personal eq '11' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3'}">
                    	
                     <TR>
                      <TD bgcolor="#666666" height="15"><A  class="topmenu1" 
                        href="MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI&tipo=firma" target="_parent">Documentos por Firmar 
                        </A></TD>
                        
                      </TR>
                      
                    	</c:when>
                    
                    </c:choose>    
			 -->
            <!-- Agregado Jazanero  -->
            		<c:choose>
            			<c:when test="${codigo_oficina eq '29' || codigo_oficina eq '30' || codigo_oficina eq '28' || codigo_oficina eq '27' || codigo_oficina eq '5' || codigo_oficina eq '8'}">
            				<c:choose>
                    			<c:when test="${cargo_personal eq '11' || cargo_personal eq '10' || cargo_personal eq '13' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3'}">
                    	
                     			<TR>
                      				<TD bgcolor="#666666" height="15"><A  class="topmenu1" 
                        			href="MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI" target="_parent">Crear Documento Interno	</A>
                        			</TD>
                      			</TR>
                      
                      			
	                    		</c:when>
                    		</c:choose>
                    		<c:choose>
                      				<c:when test="${cargo_personal eq '11' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3'}">
                      				<TR>
                      					<TD bgcolor="#666666" height="15"><A  class="topmenu1" 
                        				href="FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=firma" target="_parent">Documentos por Firmar 
                        				</A></TD>
                        
                      				</TR>
                      				</c:when>
                      				<c:otherwise>
                      				<TR>
                      					<TD bgcolor="#666666" height="15"><A  class="topmenu1" 
                       					 href="FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=vista" target="_parent">Documentos para Firma 
                        				</A></TD>
                      				</TR>
                      				</c:otherwise>
                      			</c:choose>  
             			</c:when>
            		</c:choose>
            		
			 <!--Agregado Jazanero -->        
						
                </TBODY></TABLE></DIV><A 
                  class=topmenu >Registro</A> </TD>
                <TD width=2><IMG height=23 
                  src="img/g_separador_cajasmenu.gif" width=2></TD>
                <TD width=110 
                align=center valign="middle" class=Arial12pxFFFFFF 
                onmouseover="this.style.backgroundColor='0964EB';this.style.cursor='hand'; MM_showHideLayers2('promocionweb','','show')" 
                onmouseout="this.style.backgroundColor=''; MM_showHideLayersALL();">
                  <DIV id=promocionweb 
                  style="Z-INDEX: 1; VISIBILITY: hidden; WIDTH: 150px; POSITION: absolute; TOP: 90px; HEIGHT: 30px" 
                  name="promocionweb">
                <TABLE height=30 cellSpacing=0 cellPadding=4 width="100%" 
                  border=0>
                    <TBODY>
                    <TR>
                      	<TD bgcolor="#666666"><A class=topmenu 
                        					href="ListaDocumentos.do?tipo=seguimiento&operacion=S&inicia=SI" target="_parent">B&uacute;squeda 
                        </A></TD>
                   </TR>
                    
                        
                </TBODY></TABLE></DIV><A 
                  class=topmenu >Seguimiento</A> </TD>
                <TD width=2><IMG height=23 
                  src="img/g_separador_cajasmenu.gif" width=2></TD>
				    <TD width=110 
                align=center valign="middle" class=Arial12pxFFFFFF 
                onmouseover="this.style.backgroundColor='0964EB';this.style.cursor='hand'; MM_showHideLayers2('reportes','','show')" 
                onmouseout="this.style.backgroundColor=''; MM_showHideLayersALL();">
                  <DIV id=reportes 
                  style="Z-INDEX: 1; VISIBILITY: hidden; WIDTH: 280px; POSITION: absolute; TOP: 90px; HEIGHT: 100px" 
                  name="reportes">
                <TABLE height=50 cellSpacing=0 cellPadding=4 width="100%" 
                  border=0>
                    <TBODY>
                    <TR>
                    <!-- 	
                      <TD bgcolor="#666666"><A class=topmenu 
                        href="ValidaPaginas.do?tipo=reportes&accion=general" target="_parent">Generales / Historial Documento
                        </A></TD></TR>
                     -->
					<TR>
                      <TD bgcolor="#666666"><A class=topmenu 
                        href="ValidaPaginas.do?tipo=reportes&accion=criterioestado" target="_parent">Estado / Fecha Respuesta Legal
                        </A></TD></TR>
                        <c:if test="${nombre_usuario.flag == 'S' || cargo_personal eq '11'}">
												<TR>
                      <TD bgcolor="#666666"><A class=topmenu 
                        href="ReportesGestion.do?tipo=mos_cons_tran" target="_parent">Consolidado de transacciones
                        </A></TD></TR>
						</c:if>
						 <c:if test="${nombre_usuario.flag == 'S' || cargo_personal eq '11' || cargo_personal eq '10' || cargo_personal eq '13' || cargo_personal eq '3'}">
							<!-- --><TR>
                      <TD bgcolor="#666666"><A class=topmenu 
                        href="ReportesGestion.do?tipo=rep_mos_doc" target="_parent">Consolidado pendientes por oficina</A></TD></TR> 
						</c:if>
						<c:choose>
            			<c:when test="${codigo_oficina eq '29' || codigo_oficina eq '30' || codigo_oficina eq '28' || codigo_oficina eq '27' || codigo_oficina eq '5' || codigo_oficina eq '8'}">
            				<c:choose>
                    			<c:when test="${cargo_personal eq '11' || cargo_personal eq '10' || cargo_personal eq '13' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3'}">
                    				<TR>
                      					<TD bgcolor="#666666"><A class=topmenu 
                        					href="ListaDocumentosFirmados.do?tipo=seguimiento&operacion=S&inicia=SI" target="_parent">Reporte Documentos Firmados 
                        				</A></TD>
                   					</TR>
                        		</c:when>
                        	</c:choose>
                        </c:when>
                      </c:choose>

                </TBODY></TABLE></DIV>
                  <A 
                  class=topmenu >Reportes</A> </TD>
				   <TD width=2><IMG height=23 
                  src="img/g_separador_cajasmenu.gif" width=2></TD>
				  
				  <c:choose>
				  <c:when test="${codigo_oficina ==  '1' || codigo_oficina ==  '2' || codigo_oficina ==  '14'}">
				  
                     <TD class=Arial12pxFFFFFF 
                onmouseover="this.style.backgroundColor='0964EB';this.style.cursor='hand'; " 
                onmouseout="this.style.backgroundColor='';" 
                align=center width=300>
				
				<A  class=topmenu href='javascript:Sistema_Tramite_Anterior()' >Sistema Tr&aacute;mite Anterior </A>							</TD>
				</c:when>
				<c:otherwise>
				<TD class=Arial12pxFFFFFF align=center width=300>
                </TD>
				</c:otherwise>
				</c:choose>
					
				  <TD width=4 valign="top"><IMG height=23 
                  src="img/g_separador_cajasmenu.gif" width=2></TD> 
				   <TD width=180 valign="middle" align="center"><span class="Arial12pxFFFFFF"><a class=topmenu 
                  href="AdmUsuario.do?tipo=inicio">Cambiar contrase&ntilde;a </a></span></TD> 
				   
                <TD width=2><IMG height=23 
                  src="img/g_separador_cajasmenu.gif" width=2></TD>
                <TD width=80 
                align=center valign="middle" class=Arial12pxFFFFFF 
                onmouseover="this.style.backgroundColor='0964EB';this.style.cursor='hand';MM_showHideLayersALL();" 
                onmouseout="this.style.backgroundColor='';MM_showHideLayersALL();"><A class=topmenu 
                  href="AdmUsuario.do?tipo=salir">Salir</A></TD>
				   </TR></TBODY></TABLE></TD></TR>
        <TR>
          <TD bgColor=#000000><IMG height=1 
            src="index_php_archivos/pixel_blank.gif" 
width=1></TD></TR></TBODY></TABLE>
        <TABLE height=25 cellSpacing=0 cellPadding=0 width="100%" align=center 
      bgColor=#cccccc border=0>
          <TBODY>
        <TR>
          <TD bgColor=#ffffff><IMG height=1 
            src="index_php_archivos/pixel_blank.gif" width=1></TD></TR>
       
        <TR>
              <TD height="24" background=img/am.jpg > 
                <TABLE cellSpacing=0 cellPadding=0 width=100% border=0 height="">
                <TBODY>
              <TR vAlign=top>
                      <TD 
                width=16 height="25" align=middle vAlign=top background=index_php_archivos/am.jpg class="Arial12px000000LinkMenuLocal">&nbsp;</TD>    
                      <TD class=Arial12px000000LinkMenuLocal vAlign=center 
                align=middle width=13 
                  background=index_php_archivos/am.jpg>&nbsp; </TD>
                    <TD class=Arial12px000000LinkMenuLocal vAlign=center align=left 
                width=223 background=img/am.jpg> 
                    &nbsp;&nbsp;&nbsp;Bienvenido(a):&nbsp;
                        <span class="style1">
                        <c:out value='${nombreusuario}'/></span>
</TD>
                
                <TD width="312" vAlign=top background=index_php_archivos/am.jpg><IMG 
                  height=2 src="index_php_archivos/pixel_blank.gif" width=2></TD>
               <TD class=Arial12px000000LinkMenuLocal vAlign=center align=left 
                width=175 background=img/am.jpg> 
                      </TD>
                <TD class=Arial12px000000LinkMenuLocal vAlign=center align=right width=157 
                background="img/am.jpg"><div align="left" > 
        <script language="JavaScript">
		 document.write( f_fecha_actual() ); 
		 
       </script>
      </div></TD>
		</TR></TBODY></TABLE></TD></TR></TABLE>
      <SCRIPT language=JavaScript type=text/JavaScript>
<!--

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

var iF_timer=null;
function stopScroll(){
clearTimeout(iF_timer)}
function Lvl_iFrameScroller(f,i,t,d,a){ //v1.0 4LevelWebs
 var r="frames['"+i+"']",ie=document.all,y,c=(f&&!i)?f:(f&&i)?r:i;
 var b=(!f)?r:f+"."+r,o=(eval("opener&&opener.parent."+b))?"opener.":'';
 var v=(f&&i)?y=o+"parent."+f+"."+c:y=o+"parent."+r;
 var e=(a==1)?'scrollLeft':'scrollTop',n =(a==1)?'pageXOffset':'pageYOffset';
 var x=(a==1)?'p,0':(a==2)?'10000,0':(a==3)?'0,10000':'0,p',s=(d==0)?(ie)?t:t+.9:(ie)?-t:-(t-.9);
 var p=(document.all)?eval(y+".document.body."+e+"")+s:eval(y+"."+n+"")+s;

var m =eval(y+".window.scrollTo("+x+")");
 iF_timer=setTimeout("Lvl_iFrameScroller('"+f+"','"+i+"',"+t+","+d+","+a+")",1)
}
//-->
</SCRIPT>
      </TD></TR></TBODY></TABLE>

</BODY></HTML>
