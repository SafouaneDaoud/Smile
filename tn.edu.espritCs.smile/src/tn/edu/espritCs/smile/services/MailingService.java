package tn.edu.espritCs.smile.services;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import tn.edu.espritCs.smile.dao.UserDao;
import tn.edu.espritCs.smile.gui.LoginPage;
import tn.edu.espritCs.smile.technical.Mail;

public class MailingService {

	URL url;
	Properties properties;
	String mailPart;
	Session session;
	Message message;

	public void getMailProperties() {
		this.properties = new Properties();
		this.properties.put("mail.smtp.auth", "true");
		this.properties.put("mail.smtp.starttls.enable", "true");
		this.properties.put("mail.smtp.host", "smtp.gmail.com");
		this.properties.put("mail.smtp.port", "587");
	}

	public void getMailMessage(String pieceJointe, final Mail mail) {
		try {
			this.session = Session.getInstance(this.properties,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(mail
									.getMailAddressSender(), "00001984");
						}
					});
			this.message = new MimeMessage(this.session);
			InternetAddress recipient = new InternetAddress(
					mail.getMailAddressRecipient());
			this.message.setRecipient(Message.RecipientType.TO, recipient);
			this.message.setSubject(mail.getMailSubject());

			/**
			 * Partie 1: Le texte
			 */
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(mail.getMailObject());

			/**
			 * Le fichier joint
			 */
			MimeBodyPart mbp2 = null;
			if (pieceJointe != "") {
				mbp2 = new MimeBodyPart();
				System.out.println("piece jointe");
				String file = pieceJointe;
				mbp2.attachFile(file);
			}

			// On regroupe les deux dans le message
			MimeMultipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			if (mbp2 != null)
				mp.addBodyPart(mbp2);
			this.message.setContent(mp);
		} catch (IOException ex) {
			Logger.getLogger(MailingService.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(MailingService.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	private void SendMessage() {
		try {
			Transport.send(this.message);
		} catch (MessagingException ex) {
			Logger.getLogger(MailingService.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public void sendMail(String mailSubject, String mailBody) {
		try {
			Mail mail = new Mail();
			UserDao userDao = new UserDao();
			mail.setMailAddressRecipient(userDao.getAllAdminsEmailAdresses());
			mail.setMailAddressSender(LoginPage.currentUser.getEmailUser());
			mail.setMailSubject(mailSubject);
			String[] lines = mailBody.split("\\n");
			String msg = "";
			for (String s : lines) {
				msg = msg + s;
			}
			mail.setMailObject(msg);
			
			MailingService ms = new MailingService();
			ms.getMailProperties();

			ms.getMailMessage("", mail);// Send mail without any attatched files
			ms.SendMessage();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
