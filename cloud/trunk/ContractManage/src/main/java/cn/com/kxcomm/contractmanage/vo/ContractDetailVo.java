package cn.com.kxcomm.contractmanage.vo;

import java.io.Serializable;

/**
 * 
* 功能描述:合同详情内容vo
* @author chenliang 新增日期：2012-12-20
* @since ContractManage
 */
public class ContractDetailVo implements Serializable {

	private String id;
	private String sourceId; // 数据源id
	private String sourceName; // 数据源名称
	private String configId; // 配置单id
	private String configName; // 配置单名称
	private String totalPrice; // 总价
	private String quotationName;//报价总表名称
	
	public String getQuotationName() {
		return quotationName;
	}

	public void setQuotationName(String quotationName) {
		this.quotationName = quotationName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "ContractDetailVo [id=" + id + ", sourceId=" + sourceId
				+ ", sourceName=" + sourceName + ", configId=" + configId
				+ ", configName=" + configName + ", totalPrice=" + totalPrice
				+ "]";
	}

}
