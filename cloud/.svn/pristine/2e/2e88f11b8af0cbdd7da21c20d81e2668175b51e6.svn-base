package cn.com.kxcomm.contractmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 源数据分类<br/>
 * 1:存储、2:硬件、3:软件、4:其他
 * @author YuKy1327
 *
 */
@Entity
@Table(name = "tb_data_source_type")
public class TbDataSourceType implements java.io.Serializable {
	private static final long serialVersionUID = -6386515622611244030L;

	/**
	 * (系统初始化时向数据库插数据)
	 * 1:存储、2:硬件、3:软件、4:其他
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="type_id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 源数据分类名称
	 */
	@Column(name = "type_name", unique = false, updatable = true, nullable = false, insertable = true, length = 50)
	private String typeName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
