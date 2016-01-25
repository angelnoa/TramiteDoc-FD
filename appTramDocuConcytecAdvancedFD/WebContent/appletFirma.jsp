<%@ include file="taglibs.jsp" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>	
	<script type="text/javascript" language="javascript">
	    function listenMessage(evt) {
	    	var resultado;
	        try{
	            var obj = $.parseJSON(evt.data);
	            resultado = obj.resultado;
	        } catch (err) { resultado = "1" }
	        
	        var id = $("#nomarch").val();
	        var chr = id.substr(0, 3);
	        if (chr == "chr") {
	            $.ajax({
	            	type: "GET",
	                url: "ResultadoAJAXFD.do",
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	                dataType: "text",
	                data: { tipo : "escribeR", nomarch: id, resultado: resultado },
	                success: function (result) { }
	            });
	            window.close();
	        } else {
	            setTimeout("window.close();", 100);
	            window.opener.getResponseChild(resultado);
	        }            
	    }
		
		if (window.addEventListener) {
			window.addEventListener("message", listenMessage, false);
		} else {
			window.attachEvent("onmessage", listenMessage);
		}
		
		function detectExtension() {
			setTimeout(foo, 3000);
		}
		
		function foo(){
			var exten = "https://chrome.google.com/webstore/detail/signnet-tab/ehabollbfgjdlnganbhlbbojkcdpenbg";
			var testUrl = "chrome-extension://ehabollbfgjdlnganbhlbbojkcdpenbg/redir.htm";    	
			try {
				if (typeof (chrome) !== 'undefined') {
				    $.ajax({
				        url: testUrl,
				        timeout: 100,
				        type: 'HEAD',
				        error: function(){
				        	window.open(exten);
				        }
				    });
				}
			} catch (err) {}
		}
	</script>
<body onload="detectExtension()">
	<center>
		<div>
			<iframe frameborder="0" id="ContentIframe" width="620px" height="370px" src="<c:out value='${srcIframe}'/>"></iframe>
			<input type="hidden" id="nomarch" value="<c:out value='${archID}'/>" />
		</div>
	</center>
</body>
</html>