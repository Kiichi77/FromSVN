package cn.com.kxcomm.ipmi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 报警与服务器的关联表
 * 
 * @author zhangjh 新增日期：2012-9-19
 * @since ipmi_share
 */
@Entity
@Table(name = "tb_alert_server")
public class AlertServerEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1269608747348171201L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private int id;
	/**
	 * 服务器ID
	 */
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "server_id", nullable = false)
	private TbServer server;
	
	/**
	 * 报警ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_id", nullable = false)
	private AlertEntity alert;
	

	public TbServer getServer() {
		return server;
	}

	public void setServer(TbServer server) {
		this.server = server;
	}

	public AlertEntity getAlert() {
		return alert;
	}

	public void setAlert(AlertEntity alert) {
		this.alert = alert;
	}

}
