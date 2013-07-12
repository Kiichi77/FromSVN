package cn.com.kxcomm.entity;

import java.io.Serializable;

/**
 * 
 * 功能描述:变量实体
 * 
 * @author chenliang 新增日期：2013-6-24
 * @since system_center_sdk
 */
public class VariableEntity implements Serializable {

	private Long id;
	private String name; // 变量名
	private String value; // 变量值

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
