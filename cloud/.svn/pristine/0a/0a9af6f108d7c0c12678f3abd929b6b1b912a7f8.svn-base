package cn.com.kxcomm.contractmanage.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


/**
 * The persistent class for the tb_mail_time_task database table.
 * 
 */
@Entity
@Table(name="tb_mail_time_task")
public class TbMailTimeTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name = "id", updatable = false, nullable = false, insertable = false, length = 32)
	private int id;


	@Column(name = "date_time", unique = false, updatable = false, nullable = false, insertable = true)
	private Date dateTime;

	@Column(name = "process_id", unique = false, updatable = true, nullable = true, insertable = true)
	private int processId;

	@Column(name = "project_id", unique = false, updatable = true, nullable = true, insertable = true)
	private int projectId;

	@Column(name = "user_id", unique = false, updatable = true, nullable = true, insertable = true)
	private long userId;

	@Column(name = "subject", unique = false, updatable = true, nullable = true, insertable = true,length = 200)
	private String subject;

	@Column(name = "content", unique = false, updatable = true, nullable = true, insertable = true,length = 300)
	private String content;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TbMailTimeTask() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getProcessId() {
		return this.processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}