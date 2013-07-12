package cn.com.kxcomm.contractmanage.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the tb_task_url database table.
 * 
 */
@Entity
@Table(name = "tb_task_url")
public class TbTaskUrl implements Serializable {
	private static final long serialVersionUID = -5860975181698105471L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name = "id", updatable = false, nullable = false, insertable = false, length = 32)
	private Long id;

	@Column(name = "url", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String url;

	@Column(name = "user_name", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String userName;
	
	@Column(name = "role_id", unique = false, updatable = true, nullable = true, insertable = true)
	private Long role_id;

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public TbTaskUrl() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}