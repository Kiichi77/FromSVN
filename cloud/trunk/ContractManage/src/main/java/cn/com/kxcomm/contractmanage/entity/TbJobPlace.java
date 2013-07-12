package cn.com.kxcomm.contractmanage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * 工作地点
 * 
 * @author zhangjh 新增日期：2013-4-2
 * @since ContractManage
 */
@Entity
@Table(name = "tb_job_place")
public class TbJobPlace  implements Serializable{
	private static final long serialVersionUID = -5414571152728867977L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	/**
	 * 工作地点
	 */
	@Column(name = "job_place", unique = false, updatable = true, nullable = true, insertable = true,length=100)
	private String jobPlaceName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobPlaceName() {
		return jobPlaceName;
	}

	public void setJobPlaceName(String jobPlaceName) {
		this.jobPlaceName = jobPlaceName;
	}

}
