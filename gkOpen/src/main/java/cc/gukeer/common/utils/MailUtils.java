package cc.gukeer.common.utils;

import cc.gukeer.common.security.MD5Utils;
import cc.gukeer.open.common.ProjectConfig;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.service.OpenUserService;
import cc.gukeer.open.service.impl.OpenUserServiceImpl;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
public class MailUtils {

	public  void sendMail(OpenUser openUser, HttpServletRequest request,String content,String category)throws AddressException, MessagingException {
		final Properties props = PropertiesUtil.getProperties("/email.properties");

		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};

		Session mailSession = Session.getInstance(props, authenticator);
		mailSession.setDebug(true);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress form = new InternetAddress(
				props.getProperty("mail.user"));
		message.setFrom(form);

		// 设置收件人
		InternetAddress to = new InternetAddress(openUser.getUsername());
		message.setRecipient(Message.RecipientType.TO, to);
		message.setSubject(category);
		message.setContent(content,"text/html;charset=UTF-8");

		// 发送邮件
		Transport.send(message);
	}
}
