package tramitedoc.concytec.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.PasswordAuthentication;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.mail.Address;
import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
 

public class ValidaSessionAction extends Action {
	protected  Log log = LogFactory.getLog(ValidaSessionAction.class);
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	    throws Exception { 
	if (!request.isRequestedSessionIdValid()) {
	    request.setAttribute("timeout", "true");
	    return mapping.getInputForward();
	}
	return mapping.findForward("success");
    }

    public Connection getConnection(HttpServletRequest request, String key) {
	HttpSession session = request.getSession(false);
	DataSource dataSource = getDataSource(request, key);
	Connection cnx = null;
	if (dataSource != null) {
	    try {
		cnx = dataSource.getConnection();
		session.setAttribute("conexion", cnx);
	    } catch (SQLException e) {
		e.printStackTrace();
		session.setAttribute("MsjeError", "Error al obtener conexión con la base de datos");
		return null;
	    }
	} else {
	    session.setAttribute("MsjeError", "Error al obtener DataSource " + key);
	    return null;
	}
	return cnx;
    }

    public void closeConnection(Connection cnx) {
	if (cnx != null) {
	    try {
		//if (!cnx.isClosed())
			System.out.println("Cerrando conexion struts");
		    cnx.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    public void commitConnection(Connection cnx) {
	if (cnx != null) {
	    try {
		if (!cnx.isClosed())
		    cnx.commit();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    public void rollbackConnection(Connection cnx) {
	if (cnx != null) {
	    try {
		if (!cnx.isClosed())
		    cnx.rollback();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }
    
   public void enviarCorreoElectronico(HashMap<String, String> myHashMap,String ls_codigo_documento,Session session) {
		log.info("Enviando correo electronico ...");
		try {
			 Message msg = new MimeMessage(session);
			 msg.setFrom(new InternetAddress(IConstante.correoTramiteDocumentario));			
			 msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(myHashMap.get("email"),false));
			 String asunto=myHashMap.get("asunto_documento");
			 if(asunto.length()>40)
				{asunto=asunto.substring(0, 40);//cortamos el tamanio de caracteres
				}
			 
			 
			 msg.setSubject("STD - CONCYTEC: Registro Nº " +ls_codigo_documento+"\tAsunto:" +asunto);
			 msg.setText("\n\n"+myHashMap.get("remitente")+", "+
		    		 "le ha enviado un documento mediante el Sistema de Tramite Documentario:\nNº de Registro: " +ls_codigo_documento +
			 		 "\nAsunto:"+myHashMap.get("asunto_documento")+
		    		 "\n\nSistema de Tramite Documentario\nCONCYTEC");
		     msg.setHeader("X-Mailer", "O'Reilly SimpleSender");
		     Transport.send(msg);

		}
		catch (AddressException ae) {
		     ae.printStackTrace(System.out);
		   } catch (MessagingException me) {
		     me.printStackTrace(System.out);
		   }
		   log.info("El email ha sido enviado con exito..");
	}
   
/*   public void enviarCorreoElectronico(ArrayList<String> myListaCorreos,
			String ls_codigo_documento, Session sessionMail,
			HashMap<String, String> myHashMap,String tipoCorreo) {
		
	  log.info("enviando correos masivos ....");
	  
	   InternetAddress[] direcciones;
	   if(myListaCorreos.size()>0)
	   {direcciones = new InternetAddress [myListaCorreos.size()];}
	   else
		direcciones = new InternetAddress [1];	 
		
		log.info("tamianio lista: "+myListaCorreos.size());
		try {
			//aqui lleno el array direcciones con los correos en general
			for (int i = 0; i < myListaCorreos.size(); i++) {				 
				 log.info("mail"+i+": " +myListaCorreos.get(i));			
				direcciones[i]=new InternetAddress(myListaCorreos.get(i)); 
				 log.info("mail direcciones["+i+"]: " +direcciones[i]);
		   }
			log.info("size direcciones:" +direcciones.length);			
			
			
			 Message msg = new MimeMessage(sessionMail);
			 msg.setFrom(new InternetAddress(IConstante.correoTramiteDocumentario));
			 //supuestamente solo entro UN UNICO DESTINATARIO
			 if(tipoCorreo.equals(IConstante.correoTO_Principal)) 
				 {msg.addRecipients(Message.RecipientType.TO,direcciones);}
			 else 
				 {msg.addRecipients(Message.RecipientType.CC,direcciones);}
			 
			

					String asunto=myHashMap.get("asunto_documento");
					if(asunto.length()>40)
					{asunto=asunto.substring(0, 40);//cortamos el tamanio de caracteres
					}
					 msg.setSubject("STD - CONCYTEC: Registro Nº " +ls_codigo_documento+"\tAsunto:" +asunto);
					 msg.setText("\n\n"+myHashMap.get("remitente")+", "+
				    		 "le ha enviado un documento mediante el Sistema de Tramite Documentario:\nNº de Registro: " +ls_codigo_documento +
					 		 "\nAsunto:"+myHashMap.get("asunto_documento")+
					 		 "\n\nSistema de Tramite Documentario\nCONCYTEC");
				     msg.setHeader("X-Mailer", "O'Reilly SimpleSender");
				     Transport.send(msg);
			


		}
		catch (AddressException ae) {
		     ae.printStackTrace(System.out);
		   }
		catch (MessagingException me) {
		     me.printStackTrace(System.out);
		   }
		   log.info("El email ha sido enviado con exito..");		
		
	}
*/
   public void enviarCorreoElectronico(ArrayList<String> myListaCorreos,
			String ls_codigo_documento, Session sessionMail,
			HashMap<String, String> myHashMap,String tipoCorreo)  {

       try {
		Properties props = new Properties();
		   log.info("Envio por GMAIL");
		   props.setProperty("mail.transport.protocol", "smtp");
		   props.setProperty("mail.host", "smtp.gmail.com");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.socketFactory.fallback", "false");
		   props.setProperty("mail.smtp.quitwait", "false");
		   final String emailServidor = IConstante.correoTramiteDocumentario;
		   final String emailServidorPass = "dsic2012%";
		   Session session = Session.getInstance(props,
		           new Authenticator() {

		               @Override
		               protected PasswordAuthentication getPasswordAuthentication() {
		                   return new PasswordAuthentication(emailServidor, emailServidorPass);
		               }
		           });
		   MimeMessage message = new MimeMessage(session);
		   message.setSender(new InternetAddress(emailServidor));
		   String asunto=myHashMap.get("asunto_documento");
		   message.setSubject(asunto); //ASUNTO DEL CORREO
		   message.setContent("\n\n"+myHashMap.get("remitente")+", "+
		    		 "le ha enviado un documento mediante el Sistema de Tramite Documentario:\nNº de Registro: " +ls_codigo_documento +
			 		 "\nAsunto:"+myHashMap.get("asunto_documento")+
			 		 "\n\nSistema de Tramite Documentario\nCONCYTEC", "text/plain"); //MENSAJE

		   int iSize=myListaCorreos.size();
		   
		   System.out.println("Cantidad de Correos > "+iSize);
		   Address[] addresses= new Address[iSize];//SETEO EL ADDRES CON LA CANTIDAD DE DESTINOS
		   
		   for (int i=0; i<iSize; i++) {
		       System.out.println("Correo > "+myListaCorreos.get(i)); 
		       addresses[i]=new InternetAddress(myListaCorreos.get(i)); //PONGO CORREOS DESTINOS
		   }
		   
		   System.out.println("Tamaño address > "+addresses.length);
		   
		   if(tipoCorreo.equals(IConstante.correoTO_Principal)) 
			 {message.setRecipients(Message.RecipientType.TO, addresses);}
		   else 
			 {message.setRecipients(Message.RecipientType.CC, addresses);}
		   //message.setRecipients(Message.RecipientType.TO, addresses); //ENVIO COMO PRINCIPAL
		   
		   Transport.send(message);
		   
	} catch (AddressException ae) {
		// TODO Auto-generated catch block
		ae.printStackTrace(System.out);
	} catch (MessagingException ee) {
		// TODO Auto-generated catch block
		ee.printStackTrace(System.out);
	}
	log.info("El email ha sido enviado con exito..");	
   }
   
   
   public void enviarCorreoElectronicoExterno(String fecha_correo,String correo_externo,String ls_numero_expediente, 
		   String cadenaAleatoria, Session sessionMail, String tipoCorreo, HashMap<String, String> myHashMap)  {

      try {
		Properties props = new Properties();
		   log.info("Envio por GMAIL");
		   props.setProperty("mail.transport.protocol", "smtp");
		   props.setProperty("mail.host", "smtp.gmail.com");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.socketFactory.fallback", "false");
		   props.setProperty("mail.smtp.quitwait", "false");
		   final String emailServidor = IConstante.correoTramiteDocumentario;
		   final String emailServidorPass = "dsic2012%";
		   Session session = Session.getInstance(props,
		           new Authenticator() {

		               @Override
		               protected PasswordAuthentication getPasswordAuthentication() {
		                   return new PasswordAuthentication(emailServidor, emailServidorPass);
		               }
		           });
		   MimeMessage message = new MimeMessage(session);
		   message.setSender(new InternetAddress(emailServidor));
		   String asunto=myHashMap.get("asunto_documento");
		   message.setSubject(asunto); //ASUNTO DEL CORREO
		   message.setContent("\n\n"+"Estimado usuario:"+" "+
		    		 "\nUsted podrá acceder al Sistema de Trámite en línea para visualizar el avance de su trámite."
		    		 + "\n\nIngresar a: http://tramites.concytec.gob.pe/appConsultaenLinea/" +
			 		 "\nFecha: "+fecha_correo+
		    		 "\nNº de Expediente: "+ls_numero_expediente+
		    		 "\nContraseña: "+cadenaAleatoria+
		    		 "\n\nSaludos Cordiales,"+
			 		 "\nArea de Trámite Documentario\nCONCYTEC", "text/plain"); //MENSAJE

		   int iSize=1;
		   
		   System.out.println("Cantidad de Correos > "+iSize);
		   Address[] addresses= new Address[iSize];//SETEO EL ADDRES CON LA CANTIDAD DE DESTINOS
		   
		   for (int i=0; i<iSize; i++) {
		       System.out.println("Correo > "+correo_externo); 
		       addresses[i]=new InternetAddress(correo_externo); //PONGO CORREOS DESTINOS
		   }
		   
		   System.out.println("Tamaño address > "+addresses.length);
		   
		   if(tipoCorreo.equals(IConstante.correoTO_Principal)) 
			 {message.setRecipients(Message.RecipientType.TO, addresses);}
		   else 
			 {message.setRecipients(Message.RecipientType.CC, addresses);}
		   
		   //message.setRecipients(Message.RecipientType.TO, addresses); //ENVIO COMO PRINCIPAL
		   
		   Transport.send(message);
		   
	} catch (AddressException ae) {
		// TODO Auto-generated catch block
		ae.printStackTrace(System.out);
	} catch (MessagingException ee) {
		// TODO Auto-generated catch block
		ee.printStackTrace(System.out);
	}
	log.info("El email ha sido enviado con exito..");	
  }
    
  /* public static void enviarEmail(String asuntoMensaje, String mensaje, String destinatario) throws Exception {

       Properties props = new Properties();
       props.setProperty("mail.transport.protocol", "smtp");
       props.setProperty("mail.host", "smtp.gmail.com");
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.port", "465");
       props.put("mail.smtp.socketFactory.port", "465");
       props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
       props.put("mail.smtp.socketFactory.fallback", "false");
       props.setProperty("mail.smtp.quitwait", "false");
       final String emailServidor = "concytec2011@gmail.com";
       final String emailServidorPass = "dsci2011";
       Session session = Session.getInstance(props,
               new Authenticator() {

                   @Override
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(emailServidor, emailServidorPass);
                   }
               });
       MimeMessage message = new MimeMessage(session);
       message.setSender(new InternetAddress(emailServidor));
       message.setSubject(asuntoMensaje);
       message.setContent(mensaje, "text/plain");


       message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
       Transport.send(message);

   }*/
}
