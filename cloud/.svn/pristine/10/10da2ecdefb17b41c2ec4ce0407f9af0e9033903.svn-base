package cn.com.kxcomm.contractmanage.service.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.FormulaUtil;
import cn.com.kxcomm.common.util.MoneyToChinese;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDocDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDocVariableDAO;
import cn.com.kxcomm.contractmanage.dao.ContractReceivePaymentDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemplatesDocDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemplatesDocVariableDAO;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbContractReceivePayment;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDocVariable;
import cn.com.kxcomm.contractmanage.service.IContractDocVariableService;
import cn.com.kxcomm.contractmanage.status.S_ContractReceivePayment;
import cn.com.kxcomm.contractmanage.vo.ContractDocVariableVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;

@Service("contractDocVariableService")
public class ContractDocVariableService extends
CommonService<TbContractDocVariable> implements IContractDocVariableService{
	
	private static final Logger LOG = Logger.getLogger(ContractDocVariableService.class); 

	@Autowired(required=true)
	private ContractDocVariableDAO contractDocVariableDAO;
	@Autowired(required=true)
	private ContractTemplatesDocVariableDAO contractTemplatesDocVariableDAO;
	@Autowired(required=true)
	private ContractDocDAO contractDocDAO;
	@Autowired(required=true)
	private ContractTemplatesDocDAO contractTemplatesDocDAO;
	@Autowired(required=true)
	private ContractReceivePaymentDAO contractReceivePaymentDAO;
	
	@Override
	public CommonDAO<TbContractDocVariable> getBindDao() {
		return contractDocVariableDAO;
	}
	
	private String totleMoneyRmb; //总金额大写
	private String perceRateRMBCaptial; //总金额大写
	private String PrelimipayRMBCapital;
	private String finalPayRMBCapital;
	
	//查询合同文件所有的变量
	public List<ContractDocVariableVo> queryAllContractDocVariable(int id,boolean isAll) {
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT v.id,v.variable_name,v.content,v.contract_doc_id,t3.description ");
		sql.append(" FROM tb_contract_doc_variable v,tb_contract_doc d,tb_contract_templates_doc_variable t3 ");
		sql.append(" where v.contract_doc_id=? ");
		sql.append(" AND v.contract_doc_id=d.id ");
		sql.append(" AND v.variable_name = t3.variable_name ");
		sql.append(" and d.doc_id = t3.templates_doc_id");
		List<ContractDocVariableVo> listContractDocVariable=new ArrayList<ContractDocVariableVo>();
		List list=contractDocVariableDAO.queryAllContractDocVariable(sql.toString(), id);
		Object[] obj = new Object[list.size()];
	    for(int i=0;i<list.size();i++){
	    	obj = (Object[]) list.get(i);
	    	ContractDocVariableVo contractDocVariableVo=new ContractDocVariableVo();
	    	contractDocVariableVo.setId(""+obj[0]);
	    	contractDocVariableVo.setVariableName(""+obj[1]);
	    	contractDocVariableVo.setContent(rounding(""+obj[2],""+obj[1])); //obj[2]
	    	contractDocVariableVo.setContractDocId(""+obj[3]);
	    	contractDocVariableVo.setDescription(obj[4]!=null?obj[4].toString():"");
	    	
	    	if(!isAll && FormulaUtil.checkVariableName(contractDocVariableVo.getVariableName())){
	    		this.LOGGER.info("判断变量名与配置文件中名字不一样:"+contractDocVariableVo.getVariableName());
	    		continue;
	    	}else{
	    		listContractDocVariable.add(contractDocVariableVo);
	    	}
       }
	    List<ContractDocVariableVo> resultlist = new ArrayList<ContractDocVariableVo>();
	    HashMap<String, String> map = FormulaUtil.getConfigTemplatesParam();
	    //设置正确的大写
	    for (int i = 0; i < listContractDocVariable.size(); i++) {
	    	ContractDocVariableVo vo = listContractDocVariable.get(i);
	    	if(vo.getVariableName().equals(map.get(BusinessConstants.totleMoneyRmb))){//#总价格大写
	    		vo.setContent(totleMoneyRmb);
	    	}else if(vo.getVariableName().equals(map.get(BusinessConstants.perceRateRMBCaptial))){ //#贷款百分比折算人民币大写
	    		vo.setContent(perceRateRMBCaptial);
			}else if(vo.getVariableName().equals(map.get(BusinessConstants.PrelimipayRMBCapital))){ //#初验付款百分比折算人民币大写
				vo.setContent(PrelimipayRMBCapital);
			}else if(vo.getVariableName().equals(map.get(BusinessConstants.finalPayRMBCapital))){//#终验付款百分比折算人民币大写
				vo.setContent(finalPayRMBCapital);
			}
	    	resultlist.add(vo);
		}
	    return resultlist;
	}
	
	/**
	 * 
	* 方法用途和描述: 四舍五入
	* @param variableName
	* @return
	* @author chenliang 新增日期：2013-6-22
	* @since ContractManage
	 */
	private String rounding(String content,String variableName){
		try {
			if(null==content || "".equals(content)){
				return content;
			}
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(0);
			HashMap<String, String> map = FormulaUtil.getConfigTemplatesParam();
			if(variableName.equals(map.get(BusinessConstants.totleMoneyNum))){ //总价格小写
				//获取总金额把￥和，去掉
				content = content.replace("￥", "").replace(",", "");
				//转为double类型
				double totleMoney = Double.parseDouble(content);
				//四舍五入
				String mm = nf.format(totleMoney);
				//转为大写
				double money = Double.parseDouble(mm.replace(",", ""));
				totleMoneyRmb = MoneyToChinese.toChineseCharacter(money);
				return "￥"+mm;
			}else if(variableName.equals(map.get(BusinessConstants.perceRateRMBlower))){ //#贷款百分比折算人民币小写
				
				content = content.replace("￥", "").replace(",", "");
				double perceRateRMBlower = Double.parseDouble(content);
				
				String mm = nf.format(perceRateRMBlower);
				double money = Double.parseDouble(mm.replace(",", ""));
				
				perceRateRMBCaptial = MoneyToChinese.toChineseCharacter(money);
				return "￥"+mm;
			}else if(variableName.equals(map.get(BusinessConstants.PrelimipayRMBlower))){ //#初验付款百分比折算人民币小写
				content = content.replace("￥", "").replace(",", "");
				double PrelimipayRMBlower = Double.parseDouble(content);
				
				String mm = nf.format(PrelimipayRMBlower);
				double money = Double.parseDouble(mm.replace(",", ""));
				
				PrelimipayRMBCapital = MoneyToChinese.toChineseCharacter(money);
				
				return "￥"+mm;
			}else if(variableName.equals(map.get(BusinessConstants.finalPayRMBlower))){ //#终验付款百分比折算人民币小写
				content = content.replace("￥", "").replace(",", "");
				double finalPayRMBlower = Double.parseDouble(content);
				
				String mm = nf.format(finalPayRMBlower);
				double money = Double.parseDouble(mm.replace(",", ""));
				
				finalPayRMBCapital = MoneyToChinese.toChineseCharacter(money);
				return "￥"+mm;
			}else{
				return content;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return content;
		}
	}
	
	/**
	 * 
	 * 方法用途和描述:修改变量内容
	 * @param contractDocVariables 合同变量值集合
	 * @param contractQuotationlist 合同报价总表集合
	 * @return
	 * @author lizl 新增日期：2012-12-21
	 * @throws Exception 
	 * @since ContractManage
	 */
	@Override
	public String saveUpdataContractDocVariable(List<ContractDocVariableVo> contractDocVariables,List<ContractQuotationsVo> contractQuotationlist,int contractId) throws Exception {
		String end = "ok";
		TbContractDocVariable contractDocVariable = null;
		TbContractDoc contractDoc = null;
		HashMap<String, String> map = FormulaUtil.getConfigTemplatesParam();
		HashMap<String, Object> variableMap = new HashMap<String, Object>();
		for(int i = 0;i < contractDocVariables.size(); i++) {
			contractDocVariable = new TbContractDocVariable();
			contractDoc = new TbContractDoc();
			contractDoc.setId(Integer.parseInt(contractDocVariables.get(i).getContractDocId()));
			contractDocVariable.setContent(contractDocVariables.get(i).getContent());
			contractDocVariable.setContractDoc(contractDoc);
			if(null != contractDocVariables.get(i).getId() && !"".equals(contractDocVariables.get(i).getId().trim())) {
				contractDocVariable.setId(Integer.parseInt(contractDocVariables.get(i).getId()));
			}
			contractDocVariable.setVariableName(contractDocVariables.get(i).getVariableName());
			this.contractDocVariableDAO.saveOrUpdate(contractDocVariable);
			if(map.get(BusinessConstants.PrelimiPay).equals(contractDocVariables.get(i).getVariableName())){ //初验付款贷款百分比
				variableMap.put(BusinessConstants.PrelimiPay, contractDocVariables.get(i));
			}else if(map.get(BusinessConstants.totalPriceLoans).equals(contractDocVariables.get(i).getVariableName())){ //#本合同总价贷款百分比
				variableMap.put(BusinessConstants.totalPriceLoans, contractDocVariables.get(i));
			}else if(map.get(BusinessConstants.finalPay).equals(contractDocVariables.get(i).getVariableName())){//#终验付款贷款百分比
				variableMap.put(BusinessConstants.finalPay, contractDocVariables.get(i));
			}
		}
		//保存或修改合同前期中期后期，变量的价格，获取百分比的值，计算总价的值
		if(!BlankUtil.isBlank(contractDocVariables) && contractDocVariables.size()>0){
			String contractDocId = contractDocVariables.get(0).getContractDocId();
			int docid = Integer.parseInt(contractDocId);
			calculateTotalPrice(variableMap,contractQuotationlist,docid,contractId);
		}
		return end;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据百分比计算合同的分期百分比价格
	* @param contractDocVariables
	* @param contractQuotationlist
	* @author chenliang 新增日期：2013-1-16
	 * @throws Exception 
	* @since ContractManage
	 */
	private void calculateTotalPrice(HashMap<String, Object> hashMap,List<ContractQuotationsVo> contractQuotationlist,int contractDocId,int contractId) throws Exception{
		//1、把合同中的价格保存到合同收款信息表中
		TbContractReceivePayment payment = new TbContractReceivePayment();
		TbContract contract = new TbContract();
		contract.setId(contractId);
		payment.setContract(contract);
		//2、把合同中的价格保存到合同文件变量值中
		List<ContractDocVariableVo> volist = new ArrayList<ContractDocVariableVo>();
		//计算总价格
		double totalprice = 0;
		if(!BlankUtil.isBlank(contractQuotationlist)){
			for (ContractQuotationsVo contractQuotationsVo : contractQuotationlist) {
				totalprice += Double.parseDouble(contractQuotationsVo.getTotalPrice());
			}
		}
		LOG.info("totalprice:"+totalprice);
		HashMap<String, String> map = FormulaUtil.getConfigTemplatesParam();
		//添加总价格小写变量的值
		ContractDocVariableVo paramvo = new ContractDocVariableVo();
		paramvo.setContractDocId(""+contractDocId);
		paramvo.setVariableName(map.get(BusinessConstants.totleMoneyNum).toString());
		NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
		paramvo.setContent("￥"+nf.format(totalprice));
		volist.add(paramvo);
		payment.setContractTotalPrice(totalprice); //收款项总价格设置
		//添加总价格大写变量的值
		paramvo = new ContractDocVariableVo();
		paramvo.setContractDocId(""+contractDocId);
		paramvo.setVariableName(map.get(BusinessConstants.totleMoneyRmb).toString());
		paramvo.setContent(MoneyToChinese.toChineseCharacter(totalprice));
		volist.add(paramvo);
		payment.setContractTotalPriceText(MoneyToChinese.toChineseCharacter(totalprice));//收款项总价格大写设置
		//获取合同前期百分比，计算初验合同应付款
		ContractDocVariableVo vo = (ContractDocVariableVo) hashMap.get(BusinessConstants.PrelimiPay);
		double prelimipay = countTotalPrice(vo,totalprice);
		payment.setContractBeginPercent(getPercent(vo.getContent())); //收款项合同前期百分比，计算初验合同应付款设置
		//初验付款百分比折算人民币小写
		paramvo = new ContractDocVariableVo();
		paramvo.setContractDocId(""+contractDocId);
		paramvo.setVariableName(map.get(BusinessConstants.PrelimipayRMBlower).toString());
		NumberFormat nf1 = NumberFormat.getNumberInstance();
        nf1.setMaximumFractionDigits(2);
		paramvo.setContent("￥"+nf1.format(prelimipay));
		volist.add(paramvo);
		payment.setContractBeginPrice(prelimipay); //收款项付款百分比折算人民币小写设置
		//初验付款百分比折算人民币大写
		paramvo = new ContractDocVariableVo();
		paramvo.setContractDocId(""+contractDocId);
		paramvo.setVariableName(map.get(BusinessConstants.PrelimipayRMBCapital).toString());
		paramvo.setContent(MoneyToChinese.toChineseCharacter(prelimipay));
		volist.add(paramvo);
		payment.setContractBeginPriceText(MoneyToChinese.toChineseCharacter(prelimipay));//收款项初验付款百分比折算人民币大写设置
		//本合同总价贷款百分比
		vo = (ContractDocVariableVo) hashMap.get(BusinessConstants.totalPriceLoans);
		double totalPriceLoans = countTotalPrice(vo, totalprice);
		payment.setContractGoodsPercent(getPercent(vo.getContent())); //收款项 合同总价贷款百分比
		//贷款百分比折算人民币小写
		paramvo = new ContractDocVariableVo();
		paramvo.setContractDocId(""+contractDocId);
		paramvo.setVariableName(map.get(BusinessConstants.perceRateRMBlower).toString());
		NumberFormat nf2 = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
		paramvo.setContent("￥"+nf2.format(totalPriceLoans));
		volist.add(paramvo);
		payment.setContractGoodsPrice(totalPriceLoans);//收款项 贷款百分比折算人民币小写
		//贷款百分比折算人民币大写
		paramvo = new ContractDocVariableVo();
		paramvo.setContractDocId(""+contractDocId);
		paramvo.setVariableName(map.get(BusinessConstants.perceRateRMBCaptial).toString());
		paramvo.setContent(MoneyToChinese.toChineseCharacter(totalPriceLoans));
		volist.add(paramvo);
		payment.setContractGoodsPriceText(MoneyToChinese.toChineseCharacter(totalPriceLoans));
		//终验付款贷款百分比
		vo = (ContractDocVariableVo) hashMap.get(BusinessConstants.finalPay);
		double finalPay = countTotalPrice(vo, totalprice);
		payment.setContractFinalPercent(getPercent(vo.getContent())); //收款项终验付款贷款百分比
		//#终验付款百分比折算人民币小写
		paramvo = new ContractDocVariableVo();
		paramvo.setContractDocId(""+contractDocId);
		paramvo.setVariableName(map.get(BusinessConstants.finalPayRMBlower).toString());
		NumberFormat nf3 = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
		paramvo.setContent("￥"+nf3.format(finalPay));
		volist.add(paramvo);
		payment.setContractFinalPrice(finalPay); //收款项终验付款百分比折算人民币小写
		//#终验付款百分比折算人民币大写
		paramvo = new ContractDocVariableVo();
		paramvo.setContractDocId(""+contractDocId);
		paramvo.setVariableName(map.get(BusinessConstants.finalPayRMBCapital).toString());
		paramvo.setContent(MoneyToChinese.toChineseCharacter(finalPay));
		volist.add(paramvo);
		payment.setContractFinalPriceText(MoneyToChinese.toChineseCharacter(finalPay));//收款项终验付款百分比折算人民币大写
		payment.setStatus(S_ContractReceivePayment.RECEIVE_GOODSPRICE);
		saveOrUpdateTotalPrice(volist);
		//添加或修改合同收款项
		saveOrUpdateReceivePayment(payment);
	}
	
	/**
	 * 
	* 方法用途和描述:修改或添加合同收款项
	* @param payment
	* @author chenliang 新增日期：2013-4-27
	* @since ContractManage
	 */
	private void saveOrUpdateReceivePayment(TbContractReceivePayment payment){
		//1、现根据合同id查找是否该数据存在
		String hql = "delete TbContractReceivePayment tb where tb.contract.id = ? ";
		int result = contractReceivePaymentDAO.executeByHQL(hql, payment.getContract().getId());
		//2、添加合同收款项
		contractReceivePaymentDAO.save(payment);
	}
	
	private double countTotalPrice(ContractDocVariableVo vo,double totalprice){
		String percent = vo.getContent();
		LOG.info("percent:"+percent);
		if(!BlankUtil.isBlank(percent)){
			double per = Double.parseDouble(percent.replace("%", ""))*0.01;
			totalprice = per*totalprice;
			LOG.info("percent totalprice:"+totalprice);
		}
		return totalprice;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取百分比
	* @param percent
	* @return
	* @author chenliang 新增日期：2013-4-15
	* @since ContractManage
	 */
	private Integer getPercent(String percent){
		int pc = 0;
		if(!BlankUtil.isBlank(percent)){
			pc = Integer.parseInt(percent.replace("%", ""));
		}
		return pc;
	}
	
	/**
	 * 
	* 方法用途和描述: 添加或修改合同中的价格
	* @param vo
	* @param totalprice
	* @author chenliang 新增日期：2013-1-16
	* @since ContractManage
	 */
	private void saveOrUpdateTotalPrice(List<ContractDocVariableVo> volist){
		TbContractDocVariable contractDocVariable = null;
		TbContractDoc contractDoc = null;
		for (ContractDocVariableVo vo : volist) {
			contractDocVariable = new TbContractDocVariable();
			contractDoc = new TbContractDoc();
			contractDoc.setId(Integer.parseInt(vo.getContractDocId()));
			contractDocVariable.setContent(vo.getContent());
			contractDocVariable.setContractDoc(contractDoc);
			contractDocVariable.setVariableName(vo.getVariableName());
			LOG.info("contractDocVariable:"+contractDocVariable.toString());
			//根据选中合同文件id和变量名查找
			TbContractDocVariable contractvar = queryByContractIdAndVarName(vo);
			if(!BlankUtil.isBlank(contractvar) && null!=contractvar.getId() && 0!=contractvar.getId()){
				contractDocVariable.setId(contractvar.getId());
				this.contractDocVariableDAO.merge(contractDocVariable);
			}else{
				this.contractDocVariableDAO.save(contractDocVariable);
			}
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据选中合同文件id和变量名查找
	* @return
	* @author chenliang 新增日期：2013-1-16
	* @since ContractManage
	 */
	private TbContractDocVariable queryByContractIdAndVarName(ContractDocVariableVo vo){
		String hql = " from TbContractDocVariable where variableName=? and contractDoc.id=?";
		List<TbContractDocVariable> variableList = contractDocDAO.find(hql, vo.getVariableName(),Integer.parseInt(vo.getContractDocId()));
		if(!BlankUtil.isBlank(variableList)){
			return variableList.get(0);
		}
		return null;
	}
	
	/**
	 * 
	 * 方法用途和描述:检查需要设置变量的文件是否设置了
	 * 
	 * @return
	 * @author luj 新增日期：2012-12-27
	 * @param templatesDocId,contractId
	 * @since ContractManage
	 */
	@Override
	public boolean checkVariable(int templatesDocId,int contractId)
	{
		System.out.println("templatesDocId:    "+templatesDocId+"    contractId:    "+contractId);
		boolean ck=false;
		StringBuffer hqlTemplatesDoc = new StringBuffer();
		hqlTemplatesDoc.append(" SELECT count(*) FROM TbContractTemplatesDocVariable v ");
		hqlTemplatesDoc.append(" WHERE v.templatesDoc.id=? ");
		int countTemplatesDoc=contractDocVariableDAO.findTotalCount(hqlTemplatesDoc.toString(), templatesDocId);
		StringBuffer hqlDoc=new StringBuffer();
		hqlDoc.append(" SELECT count(*) FROM TbContractDocVariable ");
		hqlDoc.append(" WHERE contractDoc.doc.id=? and contractDoc.contract.id=?");
		int countDoc=contractDocVariableDAO.findTotalCount(hqlDoc.toString(), templatesDocId,contractId);
		if(countTemplatesDoc!=countDoc)
		{
			return ck;
		}
		return true;
	}

	/**
	 * 保存合同变量值设置
	 * @param contractDocId 合同选中文件id
	 */
	@Override
	public void saveContractDocVariable(int contractDocId) {
		//1、根据合同选中文件id查找合同选中文件对象
		TbContractDoc contractDoc = contractDocDAO.findById(contractDocId);
		TbContractTemplatesDoc doc =contractDoc.getDoc();
		//2、根据合同模板文件id查找合同模板文件
		TbContractTemplatesDoc templatesDoc = contractTemplatesDocDAO.findById(doc.getId());
		Set variableSet =templatesDoc.getContractTemplatesDocVariableMembers();
		Iterator variableIter = variableSet.iterator();
		while (variableIter.hasNext()) {
			TbContractTemplatesDocVariable variable = (TbContractTemplatesDocVariable) variableIter.next();
			TbContractDocVariable docVariable = new TbContractDocVariable();
			TbContractDoc contractDocTemp = new TbContractDoc();
			contractDocTemp.setId(contractDocId);
			docVariable.setContractDoc(contractDoc);
			docVariable.setVariableName(variable.getVariableName());
			docVariable.setContent("");
			//保存合同变量值的内容
			contractDocVariableDAO.save(docVariable);
			LOG.info("Save ContractDocVariable successInfo.");
		}
	}

	@Override
	public List<ContractDocVariableVo> queryPrice(int contractDocId) {
		List<ContractDocVariableVo> volist = new ArrayList<ContractDocVariableVo>();
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT v.id,v.variable_name,v.content,v.contract_doc_id,t3.description ");
		sql.append(" FROM tb_contract_doc_variable v,tb_contract_templates_doc_variable t3 ");
		sql.append(" where v.contract_doc_id=? ");
		sql.append(" AND v.variable_name = t3.variable_name ");
		List list=contractDocVariableDAO.queryAllContractDocVariable(sql.toString(), contractDocId);
		Object[] obj = new Object[list.size()];
	    for(int i=0;i<list.size();i++){
	    	obj = (Object[]) list.get(i);
	    	ContractDocVariableVo contractDocVariableVo=new ContractDocVariableVo();
	    	contractDocVariableVo.setId(obj[0].toString());
	    	contractDocVariableVo.setVariableName(obj[1].toString());
	    	contractDocVariableVo.setContent(obj[2].toString());
	    	contractDocVariableVo.setContractDocId(obj[3].toString());
	    	contractDocVariableVo.setDescription(obj[4]!=null?obj[4].toString():"");
	    	if(FormulaUtil.checkVariableName(contractDocVariableVo.getVariableName())){
	    		volist.add(contractDocVariableVo);
	    	}else{
	    		continue;
	    	}
       }
	    return volist;
	}
	/**
	 * 根据合同ID查询合同金额
	 * @param contractDocId 合同选中文件id
	 */
	@Override
	public String totleMoneyByContractId(Integer contractId)
	{
		StringBuffer sb=new StringBuffer();
		sb.append(" from TbContractDocVariable cdv where cdv.contractDoc.contract.id=? ");
		List<TbContractDocVariable> docvarList = contractDocVariableDAO.find(sb.toString(), contractId);
		HashMap<String, String> map = FormulaUtil.getConfigTemplatesParam();
		for (int i = 0; i < docvarList.size(); i++) {
			TbContractDocVariable tbdocContractDocVariable = docvarList.get(i);
			if(!BlankUtil.isBlank(tbdocContractDocVariable.getVariableName())){
				if(tbdocContractDocVariable.getVariableName().equals(map.get(BusinessConstants.totleMoneyNum))){
					return tbdocContractDocVariable.getContent();
				}
			}
		}
		return "0";
		
	}
	
}
