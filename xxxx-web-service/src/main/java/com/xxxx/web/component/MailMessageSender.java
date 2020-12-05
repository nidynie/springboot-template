//package com.meiliyaya.web.component;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.mail.*;
//import java.util.Properties;
//
//@Slf4j
//public class MailMessageSender {
//
//    private String userName;
//
//    private String password;
//
//    private String host;
//
//    private boolean auth;
//
//    private Session session;
//
//    private Transport transport;
//
//
//    public MailMessageSender(String userName, String password, String host, boolean auth) {
//        this.userName = userName;
//        this.password = password;
//        this.host = host;
//        this.auth = auth;
//
//        init();
//    }
//
//    public void init() {
//        Properties properties = new Properties();
//        properties.setProperty("mail.host", host);
//        properties.setProperty("mail.transport.protocol", "smtp");
//        if (auth) {
//            properties.setProperty("mail.smtp.auth", "true");
//        }
//        session = Session.getInstance(properties);
//
//
//    }
//
//    public void sendMail(Message message) throws MessagingException {
//        try {
//            transport = session.getTransport();
//        } catch (NoSuchProviderException e) {
//            log.info("transport初始化过程出错,错误信息：{}", e.getMessage(), e);
//        }
//        transport.connect(userName, password);
//        transport.send(message, message.getAllRecipients());
//        this.close();
//    }
//
//
//    public void close() {
//        if (transport != null && transport.isConnected()) {
//            try {
//                transport.close();
//            } catch (MessagingException e) {
//                log.info("关闭邮件连接出错,错误信息：{}", e.getMessage(), e);
//            }
//
//        }
//    }
//
//
//    public Session getSession() {
//        return session;
//    }
//
//
//}
