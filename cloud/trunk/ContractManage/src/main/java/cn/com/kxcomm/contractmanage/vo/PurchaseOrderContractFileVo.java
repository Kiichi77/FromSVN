package cn.com.kxcomm.contractmanage.vo;

public class PurchaseOrderContractFileVo {
  private String id;
  
  private String title;  //订购合同文件名称
  
  private String pathName; //文件路径
  
  private String create; //创建者
  
  private String createtime; //创建时间
  
  private String suppliers; //供应商
  
  private String contract; //合同

  private String projectName;  //项目名称
  
  private String contractMoney; //总价
  
  private String isPay; //付款状态
  
  private String totalpayment;   //实际付款
  
  private String totalOverPayMoney;  //可对冲金额
  
  private String overPayId;    //可对冲金额id
  
  private String purchaseCompany; //采购公司
  
  private String purchaseCompanyId; //采购公司Id
  
  private String paymentType;  //付款方式
  
  private String checkNo;   //支票号
  
  private String paymentTime;// 付款时间
  
  private String taxpayerNumber; //纳税人识别号
  
  private String openBank;//开户行
  
  private String accounts;//帐号
  
  private String suppliersId;
  
  
  
public String getSuppliersId() {
	return suppliersId;
}

public void setSuppliersId(String suppliersId) {
	this.suppliersId = suppliersId;
}

public String getCheckNo() {
	return checkNo;
}

public void setCheckNo(String checkNo) {
	this.checkNo = checkNo;
}

public String getPaymentTime() {
	return paymentTime;
}

public void setPaymentTime(String paymentTime) {
	this.paymentTime = paymentTime;
}

public String getPurchaseCompanyId() {
	return purchaseCompanyId;
}

public void setPurchaseCompanyId(String purchaseCompanyId) {
	this.purchaseCompanyId = purchaseCompanyId;
}

public String getOverPayId() {
	return overPayId;
}

public void setOverPayId(String overPayId) {
	this.overPayId = overPayId;
}

public String getTotalpayment() {
	return totalpayment;
}

public void setTotalpayment(String totalpayment) {
	this.totalpayment = totalpayment;
}

public String getTotalOverPayMoney() {
	return totalOverPayMoney;
}

public void setTotalOverPayMoney(String totalOverPayMoney) {
	this.totalOverPayMoney = totalOverPayMoney;
}

public String getProjectName() {
	return projectName;
}

public void setProjectName(String projectName) {
	this.projectName = projectName;
}

public String getContractMoney() {
	return contractMoney;
}

public void setContractMoney(String contractMoney) {
	this.contractMoney = contractMoney;
}

public String getIsPay() {
	return isPay;
}

public void setIsPay(String isPay) {
	this.isPay = isPay;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getPathName() {
	return pathName;
}

public void setPathName(String pathName) {
	this.pathName = pathName;
}

public String getCreate() {
	return create;
}

public void setCreate(String create) {
	this.create = create;
}

public String getCreatetime() {
	return createtime;
}

public void setCreatetime(String createtime) {
	this.createtime = createtime;
}

public String getSuppliers() {
	return suppliers;
}

public void setSuppliers(String suppliers) {
	this.suppliers = suppliers;
}

public String getContract() {
	return contract;
}

public void setContract(String contract) {
	this.contract = contract;
}


public String getPurchaseCompany() {
	return purchaseCompany;
}

public void setPurchaseCompany(String purchaseCompany) {
	this.purchaseCompany = purchaseCompany;
}


public String getPaymentType() {
	return paymentType;
}

public void setPaymentType(String paymentType) {
	this.paymentType = paymentType;
}



public String getTaxpayerNumber() {
	return taxpayerNumber;
}

public void setTaxpayerNumber(String taxpayerNumber) {
	this.taxpayerNumber = taxpayerNumber;
}

public String getOpenBank() {
	return openBank;
}

public void setOpenBank(String openBank) {
	this.openBank = openBank;
}

public String getAccounts() {
	return accounts;
}

public void setAccounts(String accounts) {
	this.accounts = accounts;
}

@Override
public String toString() {
	return "PurchaseOrderContractFileVo [id=" + id + ", title=" + title
			+ ", pathName=" + pathName + ", create=" + create + ", createtime="
			+ createtime + ", suppliers=" + suppliers + ", contract="
			+ contract + ", projectName=" + projectName + ", contractMoney="
			+ contractMoney + ", isPay=" + isPay + ", totalpayment="
			+ totalpayment + ", totalOverPayMoney=" + totalOverPayMoney
			+ ", overPayId=" + overPayId + ", purchaseCompany="
			+ purchaseCompany + ", purchaseCompanyId=" + purchaseCompanyId
			+ ", paymentType=" + paymentType + ", checkNo=" + checkNo
			+ ", paymentTime=" + paymentTime + ", taxpayerNumber="
			+ taxpayerNumber + ", openBank=" + openBank + ", accounts="
			+ accounts + ", suppliersId=" + suppliersId + "]";
}




}
