package com.techstack.corebanking.service;



import com.techstack.corebanking.dto.EmailRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;


@Service
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username: noreply@skybanksl.com}")
    private String fromEmail;
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendPlainText(EmailRequest request) {

        System.out.println("Recipeint is :" + request.getRecipient());
        System.out.println("Subject is :" + request.getSubject());
        System.out.println("Body is :" + request.getMsgBody());


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("bayee3k@yahoo.com");
        message.setSubject("Test");
        message.setText("Testing email sending");
        mailSender.send(message);
        return "Mail Sent Successfully";
    }

    public String sendHtml(EmailRequest request) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo("bayee3k@yahoo.com");
        helper.setSubject("Testing");
        helper.setText("Sending test email", true); // true means this is HTML
        mailSender.send(message);
        return "Mail Sent Successfully";
    }


    public void sendEmail(EmailRequest request, MultipartFile attachment) throws Exception {

        configureTrustAllSSL();
        MimeMessage message = mailSender.createMimeMessage();
        // 'true' enables multipart mode for attachments
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(request.getRecipient());
        helper.setSubject(request.getSubject());
        helper.setText(request.getMsgBody());

        if (attachment != null && !attachment.isEmpty()) {
            helper.addAttachment(attachment.getOriginalFilename(), attachment);
        }

        mailSender.send(message);
    }

    /**
     * Configures SSL to trust all certificates and bypass hostname verification.
     * This allows the application to connect to servers with untrusted or self-signed certificates.
     * WARNING: This approach is insecure for production use.
     */
    private static void configureTrustAllSSL() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing - trust all clients
                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing - trust all servers
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
    }
}
