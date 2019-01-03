import mysql.DatabaseServer;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

public class Server {
		private static final String password = "CuckLORD123";
		private static final String DIVIDER = "\n-------------------------------------------\n";
		public static void main(String[] args)
		{
			ArrayList<String> addresses = DatabaseServer.getEmails();
			String sendFrom = "CUS1166Final@gmail.com";
			
			Properties props = System.getProperties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			Session session = Session.getInstance(props,
					  new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(sendFrom, password);
						}
					  });
			
			Timer mailTimer = new Timer();
			
			mailTimer.scheduleAtFixedRate(new TimerTask()
			{
				public void run() 
				{
					int sentProgress = 0;

					try
					{
						MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress(sendFrom));
						message.setSubject("Package Tracking: Your Order Update");
						
						message.setText("Hello user,"
								+ "\n"
								+ "		This is an automated email to inform you that"
								+ " your package information may have been recently updated."
								+ " Please launch the app and check on your current orders to"
								+ " view any updates on your order status.");
						
						System.out.println(DIVIDER + "Starting e-mail sending service... " + DIVIDER);
						for(String to : addresses)
						{
							if(to!=null&&to.contains("@")){
							message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
							Transport.send(message);
							System.out.println("Sent e-mail to " + to);
							sentProgress++;
							}
						}
					}
					catch(MessagingException e)
					{
						e.printStackTrace();
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
					System.out.println("\nFinished sending e-mails. Total sent: " + sentProgress);
				}
			}, 0, 10*60*1000);
			
		}
}
