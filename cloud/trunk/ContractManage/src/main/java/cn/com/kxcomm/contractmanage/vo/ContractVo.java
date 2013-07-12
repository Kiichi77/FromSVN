package cn.com.kxcomm.contractmanage.vo;

public class ContractVo {
	private String id;

	private String quotationsTitle;  //报价表名称或id
	private String contractTemplatesId; //合同模板id
	private String contractTemplatesName; //合同模板名称

	private String title;  //合同名称

	private String createTime;  //开始时间
	private String endTime;  //截止时间
	
	private String status;
	private String downPath; // 合同文件下载路径

	private String contractNo; // 订单号，合同内部编号
	private String creatorName; // 创建者
	private String creatorId; // 创建者id
	private String projectName; // 项目名
	private String projectId; // 项目id
	private String quotationsId;		//报价表ID
	private String contractNumber; //合同编号
	private String contractTotalPrice;//合同总价
	private String contractGoodsPercent;//货款百分比
	private String contractGoodsPrice;//货款价格
	private String contractBeginPercent;//货款百分比
	private String contractBeginPrice;//货款价格
	private String contractFinalPercent;//货款百分比
	private String contractFinalPrice;//货款价格
	
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getQuotationsId() {
		return this.quotationsId;
	}

	public void setQuotationsId(String quotationsId) {
		this.quotationsId = quotationsId;
	}

	public String getContractTemplatesId() {
		return this.contractTemplatesId;
	}

	public void setContractTemplatesId(String contractTemplatesId) {
		this.contractTemplatesId = contractTemplatesId;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDownPath() {
		return downPath;
	}

	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuotationsTitle() {
		return quotationsTitle;
	}

	public void setQuotationsTitle(String quotationsTitle) {
		this.quotationsTitle = quotationsTitle;
	}

	public String getContractTemplatesName() {
		return contractTemplatesName;
	}

	public void setContractTemplatesName(String contractTemplatesName) {
		this.contractTemplatesName = contractTemplatesName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContractTotalPrice() {
		return contractTotalPrice;
	}

	public void setContractTotalPrice(String contractTotalPrice) {
		this.contractTotalPrice = contractTotalPrice;
	}

	public String getContractGoodsPercent() {
		return contractGoodsPercent;
	}

	public void setContractGoodsPercent(String contractGoodsPercent) {
		this.contractGoodsPercent = contractGoodsPercent;
	}

	public String getContractGoodsPrice() {
		return contractGoodsPrice;
	}

	public void setContractGoodsPrice(String contractGoodsPrice) {
		this.contractGoodsPrice = contractGoodsPrice;
	}

	public String getContractBeginPercent() {
		return contractBeginPercent;
	}

	public void setContractBeginPercent(String contractBeginPercent) {
		this.contractBeginPercent = contractBeginPercent;
	}

	public String getContractBeginPrice() {
		return contractBeginPrice;
	}

	public void setContractBeginPrice(String contractBeginPrice) {
		this.contractBeginPrice = contractBeginPrice;
	}

	public String getContractFinalPercent() {
		return contractFinalPercent;
	}

	public void setContractFinalPercent(String contractFinalPercent) {
		this.contractFinalPercent = contractFinalPercent;
	}

	public String getContractFinalPrice() {
		return contractFinalPrice;
	}

	public void setContractFinalPrice(String contractFinalPrice) {
		this.contractFinalPrice = contractFinalPrice;
	}

}
