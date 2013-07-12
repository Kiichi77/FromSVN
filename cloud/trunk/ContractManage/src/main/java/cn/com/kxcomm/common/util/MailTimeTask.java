package cn.com.kxcomm.common.util;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.contractmanage.dao.MailTimeTaskDAO;
import cn.com.kxcomm.contractmanage.entity.TbMailTimeTask;

public class MailTimeTask extends java.util.TimerTask{
	private String mainmail;
	private String sendto;
	private String subject;
	private String content;
	private TbMailTimeTask mailtask;
	@Autowired(required=true)
	private MailTimeTaskDAO mailTimeTaskDAO;
	public MailTimeTask(TbMailTimeTask tbMailTimeTask,String sendto,String subject,String content){
		mailtask = tbMailTimeTask;
		this.sendto = sendto;
		this.subject = subject;
		this.content = content;
	}

	@Override
	public void run() {

		TimerTask task = new TimerTask()   {   
			public void run()   {
				new SpringMailSender().simpleSend(sendto, subject, content);
				//发送完邮件清掉数据.
				mailTimeTaskDAO.delete(mailtask);
			}   
		};   
	}


	public static void main(String[] args){

	}
}
