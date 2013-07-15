package cn.com.kxcomm.ipmi.entity;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbRight entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_right")
public class TbRight implements java.io.Serializable {

	// Fields

	private Long id;
	private String url;
	private Integer name;
	private Integer rightLevel;
	private String matchOper;
	private Integer parid;
	private Short isend;
	private String rightName;
	private Set<TbRightRole> tbRightRoles = new HashSet<TbRightRole>(0);

	// Constructors

	/** default constructor */
	public TbRight() {
	}

	public TbRight(Long long1) {
		id=long1;
	}
	
	/** minimal constructor */
	public TbRight(String url, Integer name, Integer rightLevel,
			String matchOper, Integer parid, Short isend, String rightName) {
		this.url = url;
		this.name = name;
		this.rightLevel = rightLevel;
		this.matchOper = matchOper;
		this.parid = parid;
		this.isend = isend;
		this.rightName = rightName;
	}

	/** full constructor */
	public TbRight(String url, Integer name, Integer rightLevel,
			String matchOper, Integer parid, Short isend, String rightName,
			Set<TbRightRole> tbRightRoles) {
		this.url = url;
		this.name = name;
		this.rightLevel = rightLevel;
		this.matchOper = matchOper;
		this.parid = parid;
		this.isend = isend;
		this.rightName = rightName;
		this.tbRightRoles = tbRightRoles;
	}



	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "url", nullable = true, length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "name", nullable = true)
	public Integer getName() {
		return this.name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	@Column(name = "right_level", nullable = false)
	public Integer getRightLevel() {
		return this.rightLevel;
	}

	public void setRightLevel(Integer rightLevel) {
		this.rightLevel = rightLevel;
	}

	@Column(name = "match_oper", nullable = true, length = 150)
	public String getMatchOper() {
		return this.matchOper;
	}

	public void setMatchOper(String matchOper) {
		this.matchOper = matchOper;
	}

	@Column(name = "parid", nullable = true)
	public Integer getParid() {
		return this.parid;
	}

	public void setParid(Integer parid) {
		this.parid = parid;
	}

	@Column(name = "isend", nullable = true)
	public Short getIsend() {
		return this.isend;
	}

	public void setIsend(Short isend) {
		this.isend = isend;
	}

	@Column(name = "right_name", nullable = false, length = 50)
	public String getRightName() {
		return this.rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbRight")
	public Set<TbRightRole> getTbRightRoles() {
		return this.tbRightRoles;
	}

	public void setTbRightRoles(Set<TbRightRole> tbRightRoles) {
		this.tbRightRoles = tbRightRoles;
	}

}