package cn.com.kxcomm.ipmi.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbBelong entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_belong")
public class TbBelong implements java.io.Serializable {

	// Fields

	private Long id;
	private Long parId;
	private String name;
	private Short level;
	private Set<TbServerBelong> tbServerBelongs = new HashSet<TbServerBelong>(0);

	// Constructors

	/** default constructor */
	public TbBelong() {
	}

	/** minimal constructor */
	public TbBelong(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TbBelong(Long id, Long parId, String name, Short level,
			Set<TbServerBelong> tbServerBelongs,
			Set<TbServerBelong> tbServerBelongs_1) {
		this.id = id;
		this.parId = parId;
		this.name = name;
		this.level = level;
		this.tbServerBelongs = tbServerBelongs;
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

	@Column(name = "par_id")
	public Long getParId() {
		return this.parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}

	@Column(name = "name", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "level")
	public Short getLevel() {
		return this.level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbBelong")
	public Set<TbServerBelong> getTbServerBelongs() {
		return this.tbServerBelongs;
	}

	public void setTbServerBelongs(Set<TbServerBelong> tbServerBelongs) {
		this.tbServerBelongs = tbServerBelongs;
	}


}