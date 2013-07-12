package cn.com.kxcomm.woyun.vo;

import java.io.Serializable;
import java.util.List;

public class SecurityGroupVO implements Serializable {

	private Integer number; // id
	private String name; // 名称

	private String description; // 描述
	private List<RuleVO> ruleList; // 规则

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RuleVO> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<RuleVO> ruleList) {
		this.ruleList = ruleList;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SecurityGroupVO [number=" + number + ", name=" + name
				+ ", description=" + description + ", ruleList=" + ruleList
				+ "]";
	}

}
