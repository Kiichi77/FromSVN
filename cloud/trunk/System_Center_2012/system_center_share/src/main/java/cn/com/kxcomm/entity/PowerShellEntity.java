package cn.com.kxcomm.entity;

import java.io.Serializable;

/**
 * 
 * 功能描述:脚本实体
 * 
 * @author chenliang 新增日期：2013-6-24
 * @since system_center_sdk
 */
public class PowerShellEntity implements Serializable {

	private Long id;
	private String fileName; // 文件名
	private String powerShellName;// 名称
	private Long shellTypeId; // 脚本类型id
	private Long variableId; // 变量id
	private String remark; // 备注

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPowerShellName() {
		return powerShellName;
	}

	public void setPowerShellName(String powerShellName) {
		this.powerShellName = powerShellName;
	}

	public Long getShellTypeId() {
		return shellTypeId;
	}

	public void setShellTypeId(Long shellTypeId) {
		this.shellTypeId = shellTypeId;
	}

	public Long getVariableId() {
		return variableId;
	}

	public void setVariableId(Long variableId) {
		this.variableId = variableId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
