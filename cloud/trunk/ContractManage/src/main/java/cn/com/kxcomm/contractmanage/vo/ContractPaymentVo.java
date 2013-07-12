package cn.com.kxcomm.contractmanage.vo;

/**
 * 合同收款对象
 * 
 * @author zhangjh 新增日期：2013-3-15
 * @since ContractManage
 */
public class ContractPaymentVo {
	
	@Override
	public String toString() {
		return "ContractPaymentVo [projectName=" + projectName
				+ ", contractNo=" + contractNo + ", contractName="
				+ contractName + ", buyerCompany=" + buyerCompany
				+ ", contractMoney=" + contractMoney + ", sellerName="
				+ sellerName + ", contractStatus=" + contractStatus
				+ ", receiveMoney=" + receiveMoney + ", unReceiveMoney="
				+ unReceiveMoney + ", contractPaymentId=" + contractPaymentId
				+ ", contractId=" + contractId + "]";
	}
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 买方公司名称
	 */
	private String buyerCompany;
	/**
	 * 合同总额
	 */
	private Double contractMoney;
	/**
	 * 销售人员名称
	 */
	private String sellerName;
	/**
	 * 合同状态
	 */
	private Integer contractStatus;
	/**
	 * 已到账金额
	 */
	private Double receiveMoney;
	/**
	 * 未到账金额
	 */
	private Double unReceiveMoney;
	private Integer contractPaymentId;
	private String contractId;
	public Integer getContractPaymentId() {
		return contractPaymentId;
	}
	public void setContractPaymentId(Integer contractPaymentId) {
		this.contractPaymentId = contractPaymentId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getBuyerCompany() {
		return buyerCompany;
	}
	public void setBuyerCompany(String buyerCompany) {
		this.buyerCompany = buyerCompany;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Integer getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(Integer contractStatus) {
		this.contractStatus = contractStatus;
	}
	public Double getContractMoney() {
		return contractMoney;
	}
	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
	}
	public Double getReceiveMoney() {
		return receiveMoney;
	}
	public void setReceiveMoney(Double receiveMoney) {
		this.receiveMoney = receiveMoney;
	}
	public Double getUnReceiveMoney() {
		return unReceiveMoney;
	}
	public void setUnReceiveMoney(Double unReceiveMoney) {
		this.unReceiveMoney = unReceiveMoney;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
}
