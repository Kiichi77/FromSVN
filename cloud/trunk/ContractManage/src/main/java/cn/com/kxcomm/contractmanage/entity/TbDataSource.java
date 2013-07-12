package cn.com.kxcomm.contractmanage.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 数据源总表
 * 
 * @author zhangjh 新增日期：2012-12-4
 * @since ContractManage
 */
@Entity
@Table(name = "tb_data_source")
public class TbDataSource implements java.io.Serializable{
	private static final long serialVersionUID = 5766863237889519514L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 数据源名称
	 */
	@Column(name = "name", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String name;
	
	/**
	 * 状态
	 */
	@Column(name = "status", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer status;
	
	/**
	 * 描述
	 */
	@Column(name = "description", unique = false, updatable = true, nullable = false, insertable = true, length = 200)
	private String description;
	
	/**
	 * 创建时间
	 */
	@Column(name = "createtime", unique = false, updatable = false, nullable = false, insertable = true)
	private Date createtime;
	
	/**
	 * 数据源分类
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	private TbDataSourceType dataSourceType;
	
	public TbDataSourceType getDataSourceType() {
		return this.dataSourceType;
	}
	public void setDataSourceType(TbDataSourceType dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	
	@OneToMany(mappedBy="dataSource")
	@Cascade({CascadeType.ALL})
	private Set<TbDataRecord> dataRecordMembers;
	
	@OneToMany(mappedBy="dataSource")
	@Cascade({CascadeType.ALL})
	private Set<TbConfigModels> configModelsMembers;
	
	public Set<TbDataRecord> getDataRecordMembers() {
		return dataRecordMembers;
	}
	public void setDataRecordMembers(Set<TbDataRecord> dataRecordMembers) {
		this.dataRecordMembers = dataRecordMembers;
	}
	public Set<TbConfigModels> getConfigModelsMembers() {
		return configModelsMembers;
	}
	public void setConfigModelsMembers(Set<TbConfigModels> configModelsMembers) {
		this.configModelsMembers = configModelsMembers;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "TbDataSource [id=" + id + ", name=" + name + ", status="
				+ status + ", description=" + description + ", createtime="
				+ createtime + "]";
	}
	
}
