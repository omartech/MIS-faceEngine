/*
 * 
 */
package com.omar.hubino.util;

import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * The Class HTMLEmail.
 */
public class HTMLEmail {

	/** The mydomain. */
	String mydomain = null;

	/**
	 * Send email.
	 * 
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param subject
	 *            the subject
	 * @param body
	 *            the body
	 * @param contentType
	 *            the content type
	 * 
	 * @return the string
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public String sendEmail(String from, String to, String subject,
			String body, String contentType) throws Exception {

		try {
			/**
			 * Getting the smtp host mydomain IP Address from the
			 * ApplicationResource.properties File in WEB-INF folder
			 */
			ResourceBundle bundle = null;
			bundle = ResourceBundle
					.getBundle("com.ibss.hubino.backoffice.properties.MailServerConfig");

			mydomain = bundle.getString("mydomain");

			String userName = bundle.getString("userName");
			String password = bundle.getString("password");
			int SMTP_PORT = 25;

			Properties props = System.getProperties();
			props.put("mail.smtp.auth", "true");
			Session session = Session.getInstance(props, new SMTPAuthenticator(
					userName, password));
			// Session session = Session.getInstance(props, null);
			props.put("mail.smtp.host", mydomain);
//			props.put("mail.smtp.starttls.enable", "true");
			// props.put("mail.smtp.starttls.enable", true);
			 props.put("mail.smtp.port", SMTP_PORT);
			session.setDebug(true);

			// Create the message
			Message message = new MimeMessage(session);
			// Fill its headers
			message.setSubject(subject);
			message.setFrom(new InternetAddress(from));

			/* Message to send to a single eMail */
			// message.addRecipient(Message.RecipientType.TO, new
			// InternetAddress(to));
			/* Message to send to Multiple eMails */
			message.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse(to, false));
			// Create your new message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Set the HTML content, be sure it references the attachment
			String htmlText = body;

			// Set the content of the body part
			messageBodyPart.setContent(htmlText, contentType);

			// Create a related multi-part to combine the parts
			MimeMultipart multipart = new MimeMultipart("related");

			// Add body part to multipart
			multipart.addBodyPart(messageBodyPart);

			// Create part for the image
			// messageBodyPart = new MimeBodyPart();

			// Fetch the image and associate to part
			// DataSource fds = new FileDataSource(file);
			// messageBodyPart.setDataHandler(new DataHandler(fds));

			// Add a header to connect to the HTML
			// messageBodyPart.setHeader("Content-ID","<memememe>");

			// Add part to multi-part - Use this to add a attachment
			// multipart.addBodyPart(messageBodyPart);

			// Associate multi-part with message
			message.setContent(multipart);
			message.setSentDate(new Date());

			Transport.send(message);

		} catch (SendFailedException e) {
			e.printStackTrace();
			throw e;
		} catch (MessagingException mex) {
			mex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "sent";
	}

	/**
	 * The main method.
	 * 
	 * @param a
	 *            the arguments
	 */
	public static void main(String a[]) {
		HTMLEmail test = new HTMLEmail();
		try {

			test.sendEmail("sathees.ramasamy@yahoo.com",
					"sathees.ramasamy@gmail.com", "test from hubino",
					"test test test WIN", "text/html");

			// test.sendEmail("bala@hubino.com", "vrkbala@gmail.com",
			// "test from hubino", "Hubino has started to rock !!!!!!!!",
			// "root", "Hubino1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class SMTPAuthenticator extends Authenticator {
	String user;
	String pass;

	SMTPAuthenticator(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, pass);
	}
}
