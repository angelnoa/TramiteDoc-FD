<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="taglibs.jsp" %>
<%@ page import="org.apache.struts.action.*, org.apache.struts.Globals" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PaginaTexto</title>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></script>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />


    <script type="text/javascript" src="jHtmlArea/scripts/jHtmlArea-0.7.5.js"></script>
    <link rel="Stylesheet" type="text/css" href="jHtmlArea/style/jHtmlArea.css" />
    <!-- <link rel="Stylesheet" type="text/css" href="jHtmlArea/style/jHtmlArea.Editor.css" /> -->
    
    <style type="text/css">
        /* body { background: #ccc;} */
        div.jHtmlArea .ToolBar ul li a.custom_disk_button 
        {
            background: url(images/disk.png) no-repeat;
            background-position: 0 0;
        }
        
        div.jHtmlArea { border: solid 1px #ccc; }
    </style>
</head>
<body>
    <script type="text/javascript">    
        // You can do this to perform a global override of any of the "default" options
        // jHtmlArea.fn.defaultOptions.css = "jHtmlArea.Editor.css";

        $(function() {
            //$("textarea").htmlarea(); // Initialize all TextArea's as jHtmlArea's with default values

            //$("#txtDefaultHtmlArea").htmlarea(); // Initialize jHtmlArea's with all default values

            $("#txtDefaultHtmlArea").htmlarea({
                // Override/Specify the Toolbar buttons to show
                toolbar: [
                    ["bold", "italic", "underline"],
                    ["orderedList", "unorderedList"],
                    ["indent", "outdent"],
                    ["justifyleft", "justifycenter", "justifyright"],
                    ["p", "h1", "h2", "h3", "h4", "h5", "h6"],
                    ["link", "unlink"]
                ],

                // Override any of the toolbarText values - these are the Alt Text / Tooltips shown
                // when the user hovers the mouse over the Toolbar Buttons
                // Here are a couple translated to German, thanks to Google Translate.
                toolbarText: $.extend({}, jHtmlArea.defaultOptions.toolbarText, {
                        "bold": "Negrita",
                        "italic": "Cursiva",
                        "underline": "Subrayado"
                    }),

                // Specify a specific CSS file to use for the Editor
                css: "style//jHtmlArea.Editor.css",

                // Do something once the editor has finished loading
                loaded: function() {
                    //// 'this' is equal to the jHtmlArea object
                    //alert("jHtmlArea has loaded!");
                    //this.showHTMLView(); // show the HTML view once the editor has finished loading
                }
            });
        });
    </script>
    <form name="formularioContacto" id="formularioContacto" action=""> 
    <textarea id="txtDefaultHtmlArea"  name="txtDefaultHtmlArea" cols="60" rows="15" style="background-color: lightyellow"><c:out value='${texto_enriquecido}'/></textarea>
   </form>
</body>
</html>