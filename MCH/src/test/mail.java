package test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class mail {


    /**
     * @param args
     * to为发送列表，cc为抄送列表，bcc为密送列表，attach为附件，username为实用的用户名，title为邮件标题，txt为邮件内容
     */
    public static final String user = "1501917367@qq.com";//发送邮件邮箱
    public static final String pwd = "mdrneusrrovhhhai";//发送邮件邮箱密码
    public static final String smtp = "smtp.qq.com";//简单邮件传输协议
    static String[] to = {"xxx@163.com", "xxx@163.com"};
    static String[] cc = {"xxx@163.com", "xxx@163.com"};
    static String[] bcc = {"xxx@163.com", "xxx@163.com"};
    static String[] bcc1 = null;
    static final String attach = "E:\\work\\TestAuto_Integration\\zlr.txt";//附件位置
    static final String txt = "E:\\work\\TestAuto_Integration\\zlr.txt";//正文位置
    static String username = "xxx";
    static String title = "xxx";

    public static void main(String[] args) {
        mailzlr(new String[]{"3313220756@qq.com"}, null, null, null, "mal test?", "subtitle", "C:\\Users\\server\\MinecraftCommandHelper\\logs\\cache\\ver.json");
    }

    private static void mailzlr(String[] to, String[] cc, String[] bcc, String attach, String title, String title1, String txt) {
        StringBuilder txtFile = new StringBuilder();
        if (new File(txt).isFile()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(new File(txt)));

                String s;
                while ((s = br.readLine()) != null)
                    txtFile.append(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!txtFile.toString().equals(""))
            txt = txtFile.toString();

        List<File> FileList = new ArrayList<>();//附件列表
        if (attach != null) {
            File file = new File(attach);
            FileList.add(file);
        }
        String text = txt + "\n\n";
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtp);//这里默认使用163邮箱
            Session session;
            if (user != null && pwd != null) {
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.user", user);
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.password", pwd);
            } else {
                props.put("mail.smtp.auth", "false");
            }

            session = Session.getInstance(props, new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(user, pwd);
                        }
                    }
            );

            MimeMessage message = new MimeMessage(session);
            //if (false) {//是否需要收条
            //    message.addHeader("Disposition-Notification-To", "接收收条地址");
            //}
            message.setSubject(title1);//
            message.setFrom(new InternetAddress(user, title));
            String toList = getMailList(to);
            InternetAddress[] iaToList = new InternetAddress().parse(toList);
            message.setRecipients(Message.RecipientType.TO, iaToList); //收件人
            if (cc != null) {
                String toList1 = getMailList(cc);
                InternetAddress[] iaToList1 = new InternetAddress().parse(toList1);
                message.setRecipients(Message.RecipientType.CC, iaToList1); //抄送人
            }
            if (bcc != null) {
                String toList2 = getMailList(bcc);
                InternetAddress[] iaToList2 = new InternetAddress().parse(toList2);
                message.setRecipients(Message.RecipientType.BCC, iaToList2); //密送人
            }

            MimeMultipart multi = new MimeMultipart();
            BodyPart textBodyPart = new MimeBodyPart();

            textBodyPart.setText(text);

            multi.addBodyPart(textBodyPart);
            if (FileList != null) {
                for (File file : FileList) {
                    FileDataSource fds = new FileDataSource(file);
                    BodyPart fileBodyPart = new MimeBodyPart();
                    fileBodyPart.setDataHandler(new DataHandler(fds));
                    fileBodyPart.setFileName(MimeUtility.encodeText(file.getName()));//如果附件有中文通过转换没有问题了
                    multi.addBodyPart(fileBodyPart);
                }
            }
            Transport transport = session.getTransport();

            transport.connect(props.getProperty("mail.host"), props.getProperty("mail.name"), props.getProperty("mail.password"));
            message.setContent(multi);
            message.setSentDate(new Date());
            transport.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getMailList(String[] mailArray) {
        StringBuilder toList = new StringBuilder();
        int length = mailArray.length;
        if (mailArray != null && length < 2) {
            toList.append(mailArray[0].replaceAll("!", "@"));
        } else {
            for (int i = 0; i < length; i++) {
                toList.append(mailArray[i].replaceAll("!", "@"));
                if (i != (length - 1)) {
                    toList.append(",");
                }

            }
        }
        return toList.toString();

    }
}