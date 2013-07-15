package cn.com.kxcomm.contractmanage.web.util;

/**
 * 
 * 初始化数据<br/>
 * 1、生成所有报价表文件<br/>
 * 2、同步合同模版文件，用于生成合同使用<br/>
 * 3、同步采购订购合同文件
 * 
 * @author zhangjh 新增日期：2013-3-27
 * @since ContractManage
 */
public class LoginWorkingThread extends Thread {

	@Override
	public void run() {
		cn.com.kxcomm.common.util.InitExportQuotations initExportQuotations = cn.com.kxcomm.common.util.InitExportQuotations.getInstance();
		initExportQuotations.createExcel();
		cn.com.kxcomm.common.util.InitExportContract initExportContract = cn.com.kxcomm.common.util.InitExportContract.getInstance();
		initExportContract.syncContractDocuments();
	}

}
