package utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class Correo {
	
	public String enviarCorreo(String correo, String mensaje){
		
		String correoOrigen = "shopmedicaldis@gmail.com";
		String clave="shopDIS15";
		
		try {
			
			Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", correoOrigen);
            props.setProperty("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			
            Session session = Session.getDefaultInstance(props);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.addRecipient(
            Message.RecipientType.TO,new InternetAddress(correo));
            
            message.setSubject("Recuperar Contraseña");
            message.setText(mensaje);
            
            Transport t = session.getTransport("smtp");
            t.connect(correoOrigen,clave);
            t.sendMessage(message, message.getAllRecipients());
            
            t.close();
            
            return "enviado";
            
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return "error";
		}
		
	
	}

}
