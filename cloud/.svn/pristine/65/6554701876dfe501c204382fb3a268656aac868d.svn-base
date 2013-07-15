package cn.com.kxcomm.contractmanage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 工作职务
 * 
 * @author zhangjh 新增日期：2013-4-2
 * @since ContractManage
 */
@Entity
@Table(name = "tb_job_dutiesr")
public class TbJobduties implements Serializable{
	private static final long serialVersionUID = 7581030248499814669L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;

	/**
	 * 职务
	 */
	@Column(name = "job_dutiesr", unique = false, updatable = true, nullable = true, insertable = true,length=100)
	private String jobDutiesrName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobDutiesrName() {
		return jobDutiesrName;
	}

	public void setJobDutiesrName(String jobDutiesrName) {
		this.jobDutiesrName = jobDutiesrName;
	}

}
