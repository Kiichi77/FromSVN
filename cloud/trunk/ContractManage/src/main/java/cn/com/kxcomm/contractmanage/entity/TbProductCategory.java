package cn.com.kxcomm.contractmanage.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 产品总类
 * 
 * @author zhangjh 新增日期：2012-12-4
 * @since ContractManage
 */
@Entity
@Table(name = "tb_product_category")
public class TbProductCategory implements java.io.Serializable{
	private static final long serialVersionUID = -3636703611940984331L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 产品总类名称
	 */
	@Column(name = "name", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String name;
	
	/**
	 * 标记是否乘以数量(0或空:乘以数量，1：不乘以数量)
	 */
	@Column(name = "is_repeat", unique = false, updatable = true, nullable = true, insertable = true)
	private Integer isRepeat;
	
	@OneToMany(mappedBy="category")
	@Cascade({CascadeType.ALL})
	private Set<TbProductType> productTypeMembers;
	
	public Set<TbProductType> getProductTypeMembers() {
		return productTypeMembers;
	}
	public void setProductTypeMembers(Set<TbProductType> productTypeMembers) {
		this.productTypeMembers = productTypeMembers;
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
	public Integer getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(Integer isRepeat) {
		this.isRepeat = isRepeat;
	}
	
	
	@Override
	public String toString() {
		return "TbProductCategory [id=" + id + ", name=" + name + "]";
	}
}
