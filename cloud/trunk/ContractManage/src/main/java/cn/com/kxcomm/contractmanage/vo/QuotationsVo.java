package cn.com.kxcomm.contractmanage.vo;

public class QuotationsVo {
    private String id;
    
    private String dataSourceName;
    private String dataSourceType;
    
    private String title;
    
    private String status;
    
    private String createtime;
    
    private String downUrl;
    
    private String creatorName;
    
    private String projectName;
    
    private String contractQuotationId; //合同与报价表中间表id
    
    private String projectId; //项目id

    private String afterDiscountNowPrice; //折扣后现场价
    private String afterDiscountPrice; //折扣后价格
    private String catalogTotalPrice;  //目录合价
    private String totalPrice;  //合价
    private String quantity; //数量
    
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDataSourceType() {
		return dataSourceType;
	}

	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}

	public String getAfterDiscountNowPrice() {
		return afterDiscountNowPrice;
	}

	public void setAfterDiscountNowPrice(String afterDiscountNowPrice) {
		this.afterDiscountNowPrice = afterDiscountNowPrice;
	}

	public String getAfterDiscountPrice() {
		return afterDiscountPrice;
	}

	public void setAfterDiscountPrice(String afterDiscountPrice) {
		this.afterDiscountPrice = afterDiscountPrice;
	}

	public String getCatalogTotalPrice() {
		return catalogTotalPrice;
	}

	public void setCatalogTotalPrice(String catalogTotalPrice) {
		this.catalogTotalPrice = catalogTotalPrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getContractQuotationId() {
		return contractQuotationId;
	}

	public void setContractQuotationId(String contractQuotationId) {
		this.contractQuotationId = contractQuotationId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	
	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	@Override
	public String toString() {
		return "QuotationsVo [id=" + id + ", dataSourceName=" + dataSourceName
				+ ", title=" + title + ", status=" + status + ", createtime="
				+ createtime + ", downUrl=" + downUrl + ", creatorName="
				+ creatorName + ", projectName=" + projectName + "]";
	}

    
}
