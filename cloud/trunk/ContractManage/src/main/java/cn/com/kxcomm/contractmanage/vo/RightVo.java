package cn.com.kxcomm.contractmanage.vo;

/**
 * 
 * 权限对象
 * 
 * @author zhangjh 新增日期：2013-3-21
 * @since ContractManage
 */
public class RightVo {
	private Integer id;
	private String url;
	private Integer rightLevel;
	private Integer parid;
	private String parName;
	private String rightName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getRightLevel() {
		return rightLevel;
	}
	public void setRightLevel(Integer rightLevel) {
		this.rightLevel = rightLevel;
	}
	public Integer getParid() {
		return parid;
	}
	public void setParid(Integer parid) {
		this.parid = parid;
	}
	public String getParName() {
		return parName;
	}
	public void setParName(String parName) {
		this.parName = parName;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	@Override
	public String toString() {
		return "RightVo [id=" + id + ", url=" + url + ", rightLevel="
				+ rightLevel + ", parid=" + parid + ", parName=" + parName
				+ ", rightName=" + rightName + "]";
	} 
	
}
