package cn.com.kxcomm.contractmanage.entity;

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
 * 产品分类
 * 
 * @author zhangjh 新增日期：2012-12-4
 * @since ContractManage
 */
@Entity
@Table(name = "tb_product_type")
public class TbProductType implements java.io.Serializable{

	private static final long serialVersionUID = 5938686262313436091L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 产品分类名称
	 */
	@Column(name = "name", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String name;
	
	/**
	 * 产品总类
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	private TbProductCategory category;
	
	@OneToMany(mappedBy="dataSource")
	@Cascade({CascadeType.ALL})
	private Set<TbDataRecord> dataRecordMembers;
	
	public Set<TbDataRecord> getDataRecordMembers() {
		return dataRecordMembers;
	}
	public void setDataRecordMembers(Set<TbDataRecord> dataRecordMembers) {
		this.dataRecordMembers = dataRecordMembers;
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
	public TbProductCategory getCategory() {
		return category;
	}
	public void setCategory(TbProductCategory category) {
		this.category = category;
	}
}
