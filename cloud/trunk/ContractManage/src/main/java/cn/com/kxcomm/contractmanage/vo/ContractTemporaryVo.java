package cn.com.kxcomm.contractmanage.vo;

/**
 * 
 * 临时合同VO类
 * 
 * @author lizl 新增日期：2013-04-15
 * @since ContractManage
 */
public class ContractTemporaryVo {
	
	private String id;

	private String title;  //合同名称

	private String createTime;  //开始时间
	
	private String status;		//状态
	
	private String contractNo; // 订单编号
	
	private String creatorName; // 创建者
	
	private String creatorId; // 创建者id
	
	private String quotationsId;		//报价表ID
	
	private String fileForPDF;		//长传的PDF文件
	
	private String endTime; // 结束时间
	
	private String usedNum;//使用数量
	
	private String projectId;
	
	private String name;
	
	private String projectName;
	
	private String quantity;
	
	private String price;

	public String getProjectId() {
		return projectId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(String usedNum) {
		this.usedNum = usedNum;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getCreatorName() {
		return this.creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatorId() {
		return this.creatorId;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getQuotationsId() {
		return this.quotationsId;
	}

	public void setQuotationsId(String quotationsId) {
		this.quotationsId = quotationsId;
	}

	public String getFileForPDF() {
		return this.fileForPDF;
	}

	public void setFileForPDF(String fileForPDF) {
		this.fileForPDF = fileForPDF;
	}

	@Override
	public String toString() {
		return "ContractTemporaryVo [id=" + this.id + ", title=" + this.title
				+ ", createTime=" + this.createTime + ", status=" + this.status
				+ ", contractNo=" + this.contractNo + ", creatorName="
				+ this.creatorName + ", creatorId=" + this.creatorId
				+ ", quotationsId=" + this.quotationsId + ", fileForPDF="
				+ this.fileForPDF + "]";
	}
}
