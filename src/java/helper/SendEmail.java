/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author T
 */
public class SendEmail {

    public SendEmail() {
    }

    private String from = "ckp2004vn@gmail.com";
    private final String password = "vrsr gadj ckwr nhoe";

    public Boolean sendEmail(String to, String link, String name,String OTP) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }

        };
        Session session = Session.getInstance(props,auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("Reset Password", "UTF-8");
            String content = "<h1>Hello " + name + "</h1>" + "<p> Mã OTP của bạn là :"+OTP+"</p>"
                    + "<p>Click the link to reset your password: <a href='" + link + "'>Reset Password</a>"
                    + "</p>";
            msg.setContent(content,"text/html;charset=UTF-8");
            Transport.send(msg);
            System.out.println("Success");
            return true;
        } catch (Exception ex) {
           ex.printStackTrace();
           return false;
        }
    }
}
