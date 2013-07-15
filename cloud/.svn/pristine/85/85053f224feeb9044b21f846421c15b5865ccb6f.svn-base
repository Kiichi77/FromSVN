package cn.com.kxcomm.ipmi.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * 功能描述:服务器所属集合表菜单entity
 * 
 * @author chenl 新增日期：2012-8-10
 * @since ipmi_web
 */
public class BelongEntity implements Serializable {
	private String text; // 节点名称
	private boolean checked; // 是否选中
	private boolean leaf; // 是否叶子节点
	private String id; // 节点id
	private Short level; // 级别
	private boolean expanded; //是否展开
	private String parName; // 父级节点名称
	private List<BelongEntity> children;  //子菜单

	public boolean isExpanded() {
		return expanded;
	}
	
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public List<BelongEntity> getChildren() {
		return children;
	}

	public void setChildren(List<BelongEntity> children) {
		this.children = children;
	}

	public String getParName() {
		return parName;
	}

	public void setParName(String parName) {
		this.parName = parName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "BelongEntity [text=" + text + ", checked=" + checked
				+ ", leaf=" + leaf + ", id=" + id + ", level=" + level + "]";
	}

}
