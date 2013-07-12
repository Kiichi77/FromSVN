package cn.com.kxcomm.base.vo;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

public class RightEntity implements Serializable {
	
	private Integer rightId;

	private Integer parentId;

	private String rightName;

	private String url;

	private Date createTime;

	private Short level;

	public Integer getRightId() {
		return rightId;
	}

	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String toString() {
		return new ToStringBuilder(this).append("url", this.url)
				.append("createTime", this.createTime)
				.append("rightName", this.rightName)
				.append("rightId", this.rightId).append("level", this.level)
				.append("parentId", this.parentId).toString();
	}
}