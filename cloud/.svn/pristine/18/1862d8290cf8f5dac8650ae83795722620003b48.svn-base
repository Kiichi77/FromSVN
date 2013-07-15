package cn.com.kxcomm.common.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class SpringMailSender {
	private JavaMailSenderImpl mailSender; 
	public  SpringMailSender(){
		mailSender = new JavaMailSenderImpl(); 
		// 设置参数 
		mailSender.setHost("smtp.mxhichina.com"); 
		mailSender.setUsername("zhaobaodong@kxcomm.com.cn");  
		mailSender.setPassword("2237705aa");  
	}
	
	/**
	 * 
	 * @param from 发送者邮箱
	 * @param sendto 收件人邮箱
	 * @param subject 主题
	 * @param content 正文
	 */
	public void simpleSend(String sendto,String subject,String content) {  
		// 构建简单邮件对象，见名知意  
		SimpleMailMessage smm = new SimpleMailMessage();  
		// 设定邮件参数  
		smm.setFrom(mailSender.getUsername());  
		smm.setTo(sendto);  
		smm.setSubject(subject);  
		smm.setText(content); 
		// 发送邮件
		mailSender.send(smm);  
		} 
	
}
