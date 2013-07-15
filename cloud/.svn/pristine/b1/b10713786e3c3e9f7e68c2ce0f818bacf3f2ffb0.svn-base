package cn.com.kxcomm.task.util;

import java.util.Date;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 功能描述:发送邮件
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author chenliang 新增日期：2011-11-1
 * @author 你的姓名 修改日期：2011-11-1
 * @since BusinessMsgMonitor
 */
public class SendMail {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendMail.class);
	public static SendMail instance = null;
	private SendMail(){
	}
	
	public static SendMail getSingleInstance(){
		if(instance == null)
			instance = new SendMail();
		
		return instance;
	}

	/**
	 * 
	 * 方法用途和描述: 发送邮件
	 * 
	 * @param from
	 *            发送邮件箱
	 * @param to
	 *            收件箱
	 * @param subject
	 *            主题
	 * @param body
	 *            正文
	 * @param stmp
	 *            邮件服务器
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @author chenliang 新增日期：2011-11-1
	 * @author 你的姓名 修改日期：2011-11-1
	 * @since BusinessMsgMonitor
	 */
	public void sendMail(String from, String[] to, String subject,
			String body, String smtp, String username, String password) {
		try {
			Properties pro = System.getProperties();
			// 设置STMP邮件服务器
			pro.put("mail.smtp.host", smtp);
			pro.put("mail.smtp.auth", "true");
			// 传入用户名和密码
			Session session = Session.getDefaultInstance(pro,
					new PasswordAuthenticator(username, password));
			// 创建新邮件
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] sendTo = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				LOGGER.debug("Send address："+to[i]);
				sendTo[i] = new InternetAddress(to[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, sendTo);
			msg.setSubject(subject);
			msg.setText(body);
			msg.setSentDate(new Date());
			// 发送
			LOGGER.info("Start send mail.");
			Transport.send(msg);
			LOGGER.info("Mail sent successfully.");
		} catch (AddressException e) {
			e.printStackTrace();
			LOGGER.error("send Mail Address error:",e);
		} catch (MessagingException e) {
			e.printStackTrace();
			LOGGER.error("send Mail Messaging error:",e);
		}
	}

	public static void main(String[] args) {
		SendMail aa = SendMail.getSingleInstance();
		aa.sendMail("zhangjianhua@kxcomm.com.cn",new String[]{"zhangjianhua@kxcomm.com.cn"}, "测试标题", "测试内容","mail.kxcomm.com.cn", "zhangjianhua@kxcomm.com.cn", "yuky817689");
	}
}

class PasswordAuthenticator extends Authenticator {

	private String username;
	private String password;

	public PasswordAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}