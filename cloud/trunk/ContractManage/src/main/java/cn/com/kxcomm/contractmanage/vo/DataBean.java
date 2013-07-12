package cn.com.kxcomm.contractmanage.vo;

import java.io.Serializable;

public class DataBean implements Serializable {

	private String id; // id
	private String typeId; //类型id
	private String name; // 名称
	private String url; // 请求地址
	private String param; // 参数
	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "DataBean [id=" + id + ", typeId=" + typeId + ", name=" + name
				+ ", url=" + url + ", param=" + param + "]";
	}

}
