package cn.com.kxcomm.contractmanage.vo;

/**
 * 
 * 功能描述:合同订单vo
 * 
 * @author lujia 新增日期：2013-2-27
 * @since ContractManage
 */
public class ContractOrderVo {
	private String contractOrderId;		//销售订单ID
	private String orderNo;   //订单编号
	private String contractName;  //合同名称
	private String contractNo;//合同编号
	private String contractType;//合同类型 1:正式合同，2：临时合同
	private String projectId; //项目id
	private String projectName;  //项目名称
	private String note; //备注
	private String consignee;//收货人
	private String consigneePhone;//收货人联系号码
	private String createtime;  //创建时间
	private String creator;   //创建者
	private String status;   //状态  :0不同意，1同意
	private String caudit; //审核者
	private String purchase; //采购人员
	private String purchaseCompany; //采购方（公司） 
	private String roderNums; //选中机型配置的执行数量,数字用“，”隔开.
	private String quotionIds;	//选中报价表ID,数字用“，”隔开.
	private String configModelId;	//选中机型配置的ID,数字用“，”隔开.
	private String orderednum;	//选中已经采购的数量,数字用“，”隔开.
	//全通用单元数据，只有两个属性，拼接格式如下：'configModeId=orderQuantity|~configModeId=orderQuantity,configModeId=orderQuantity'
	private String currentUnitData;
	private String receiveInfo;//收货地址
	private String startTime;//查询-开始时间
	private String endTime; //查询- 结束时间
	private String keyword;//查询-模糊查询值

	public String getReceiveInfo() {
		return receiveInfo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public void setReceiveInfo(String receiveInfo) {
		this.receiveInfo = receiveInfo;
	}

	public String getCurrentUnitData() {
		return currentUnitData;
	}

	public void setCurrentUnitData(String currentUnitData) {
		this.currentUnitData = currentUnitData;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrderednum() {
		return this.orderednum;
	}

	public void setOrderednum(String orderednum) {
		this.orderednum = orderednum;
	}

	public String getConfigModelId() {
		return this.configModelId;
	}

	public void setConfigModelId(String configModelId) {
		this.configModelId = configModelId;
	}

	public String getQuotionIds() {
		return this.quotionIds;
	}

	public void setQuotionIds(String quotionIds) {
		this.quotionIds = quotionIds;
	}

	public String getRoderNums() {
		return this.roderNums;
	}

	public void setRoderNums(String roderNums) {
		this.roderNums = roderNums;
	}

	public String getPurchaseCompany() {
		return this.purchaseCompany;
	}

	public void setPurchaseCompany(String purchaseCompany) {
		this.purchaseCompany = purchaseCompany;
	}

	public String getCaudit() {
		return caudit;
	}

	public void setCaudit(String caudit) {
		this.caudit = caudit;
	}

	public String getPurchase() {
		return purchase;
	}

	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}

	public String getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	@Override
	public String toString() {
		return "ContractOrderVo [contractOrderId=" + contractOrderId
				+ ", orderNo=" + orderNo + ", contractName=" + contractName
				+ ", contractNo=" + contractNo + ", contractType="
				+ contractType + ", projectId=" + projectId + ", projectName="
				+ projectName + ", note=" + note + ", consignee=" + consignee
				+ ", consigneePhone=" + consigneePhone + ", createtime="
				+ createtime + ", creator=" + creator + ", status=" + status
				+ ", caudit=" + caudit + ", purchase=" + purchase
				+ ", purchaseCompany=" + purchaseCompany + ", roderNums="
				+ roderNums + ", quotionIds=" + quotionIds + ", configModelId="
				+ configModelId + ", orderednum=" + orderednum
				+ ", currentUnitData=" + currentUnitData + ", receiveInfo="
				+ receiveInfo + ", startTime=" + startTime + ", endTime="
				+ endTime + ", keyword=" + keyword + "]";
	}

}
