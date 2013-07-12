package cn.com.kxcomm.contractmanage.vo;

public class PurchaseOrderRelationshipVo {
	private String id;
	private String purchaseOrderId;// 采购订单Id
	private String purchaseOrderNo;// 采购订单编号
	private String purchaseQuantity;// 采购数量
	private String configModels; // 配置单名称
	private String configModelsId; //配置单id
	private String companyName;//公司名称
	
	private String contractOrderRelationshipId;// 销售订单详情id
	private String status;// 状态
	private PurchaseOrderRelationshipDataVo[] dataVo;//全通用单元数组
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getConfigModelsId() {
		return configModelsId;
	}

	public void setConfigModelsId(String configModelsId) {
		this.configModelsId = configModelsId;
	}

	public String getContractOrderRelationshipId() {
		return contractOrderRelationshipId;
	}

	public void setContractOrderRelationshipId(String contractOrderRelationshipId) {
		this.contractOrderRelationshipId = contractOrderRelationshipId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PurchaseOrderRelationshipDataVo[] getDataVo() {
		return dataVo;
	}

	public void setDataVo(PurchaseOrderRelationshipDataVo[] dataVo) {
		this.dataVo = dataVo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public String getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(String purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public String getConfigModels() {
		return configModels;
	}

	public void setConfigModels(String configModels) {
		this.configModels = configModels;
	}

	@Override
	public String toString() {
		return "PurchaseOrderRelationshipVo [id=" + id + ", purchaseOrderId="
				+ purchaseOrderId + ", purchaseOrderNo=" + purchaseOrderNo
				+ ", purchaseQuantity=" + purchaseQuantity + ", configModels="
				+ configModels + "]";
	}

}
