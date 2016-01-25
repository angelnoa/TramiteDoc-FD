<%-- 
    Document   : user
    Created on : Jan 7, 2009, 8:23:48 AM
    Author     : eswar@vaannila.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<%@ include file="taglibs.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Details</title>
        <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
    </head>
    <body>
        <display:table id="data" name="sessionScope.FFormMantSeguimiento.seguimientoList" requestURI="/ListaDocumentos.do?tipo=reporte_excel" pagesize="10" export="true">
            <display:column property="codigo_documento"  title="NUMERO REGISTRO" sortable="true"  />
            <display:column property="codigo_expediente" title="NUMERO EXPEDIENTE" sortable="true"  />
            <display:column property="numero_documento" title="NUMERO  DOCUMENTO" sortable="true" />
			<display:column property="fecha_registro" sortable="true"  title="FECHA REGISTRO"  />
			<display:column property="oficina_origen" sortable="true"  title="OFICINA ORIGEN"  />
			<display:column property="oficina_deriva" sortable="true"  title="OFICINA DESTINO"  />
			<display:column property="naturaleza_documento" sortable="true"  title="NATURALEZA" />
			<display:column property="estado_expediente" title="ESTADO EXPEDIENTE" sortable="true"  />
			<display:column property="asunto_documento" title="ASUNTO" sortable="true"  />	
            <display:setProperty name="export.excel.filename" value="ActorDetails.xls"/>
            <display:setProperty name="export.pdf.filename" value="ActorDetails.pdf"/>
            <display:setProperty name="export.pdf" value="true" />
        </display:table>
		
		
		<A  title="Ir a la pagina Anterior" class="caja8" target="_parent"  href='javascript:window.history.back()' > Ir a la P&aacute;gina Anterior </A>
    </body>
</html>
