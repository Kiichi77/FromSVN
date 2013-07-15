package cn.com.kxcomm.contractmanage.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.common.util.WordTools;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDAO;
import cn.com.kxcomm.contractmanage.dao.ContractQuotationsDAO;
import cn.com.kxcomm.contractmanage.dao.ModelDataDAO;
import cn.com.kxcomm.contractmanage.dao.QuotationsDataDAO;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbContractQuotations;
import cn.com.kxcomm.contractmanage.service.IContractQuotationsService;
import cn.com.kxcomm.contractmanage.vo.ContractDocVariableVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.web.util.FilUtil2;
import cn.com.kxcomm.contractmanage.web.util.Parameters;

@Service("icontractQuotationsService")
public class ContractQuotationsService  extends
CommonService<TbContractQuotations> implements IContractQuotationsService {

	private static final Logger log = Logger.getLogger(ContractQuotationsService.class);
	
	@Autowired(required=true)
	private ContractQuotationsDAO contractQuotationsDAO;
	@Autowired(required=true)
	private QuotationsDataDAO quotationsDataDAO;
	@Autowired(required=true)
	private ModelDataDAO modelDataDAO;
	@Autowired(required=true)
	private ContractDAO contractDAO;
	@Autowired(required=true)
	private ContractDocService contractDocService;
	@Autowired(required=true)
	private ContractDocVariableService contractDocVariableService;
	
	//查询所有报价总表
	@Override
	public List<ContractQuotationsVo> queryAllQuotations(int contractId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" select tt.project_name,tt.name,tt.quantity,sum(tt.totalPrice) price,tt.cname,tcq.quotations_id,tt.id,tt.ordered ");
		hql.append(" from tb_contract_quotations tcq, ");
		hql.append(" (select d.quotations_id,d.project_name,d.name,d.quantity,c.name as cname,c.id,d.ordered,g.is_repeat,");
		hql.append(" case when g.is_repeat =0 then sum(a.total_price)*d.quantity else sum(a.total_price) end totalPrice ");
		hql.append(" from tb_configmodel_data a,tb_quotations_data d,tb_config_models c ,tb_data_record e,tb_product_type f,tb_product_category g");
		hql.append(" where  a.config_model_id = c.id ");
		hql.append(" and c.id = d.config_models_id ");
		hql.append(" and a.data_record_id = e.id ");
		hql.append(" and e.product_type_id = f.id ");
		hql.append(" and f.category_id = g.id ");
		hql.append(" group by d.id,g.is_repeat ");
		hql.append(" ) tt ");
		hql.append(" where tcq.quotations_id = tt.quotations_id ");
		hql.append(" and tcq.contract_id= ? ");
		hql.append(" group by tt.project_name,tt.name,tt.quantity,tt.cname,tcq.quotations_id,tt.id,tt.ordered ");
		List list = contractDAO.findByPage(hql.toString(), contractId);
		Object[] obj = new Object[list.size()];
		List<ContractQuotationsVo> detailvoList = new ArrayList<ContractQuotationsVo>();
		int ordered=0,quantity=0,remainingQuantity=0;
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			ContractQuotationsVo detailVo = new ContractQuotationsVo();
			detailVo.setProjectName(""+obj[0]);
			detailVo.setQuotationName(""+obj[1]);
			detailVo.setQuantity(""+(obj[2]!=null?obj[2]:0));
			detailVo.setTotalPrice(""+(obj[3]!=null?""+obj[3]:0));
			detailVo.setConfigModelName(""+obj[4]);
			detailVo.setQuotationsId(""+obj[5]);
			detailVo.setConfigModelId(""+obj[6]);
			detailVo.setOrdered(""+(obj[7]!=null?obj[7]:0));
			if(!BlankUtil.isBlank(detailVo.getQuantity())){
				quantity = Integer.parseInt(detailVo.getQuantity());
			}
			if(!BlankUtil.isBlank(detailVo.getOrdered())){
				ordered = Integer.parseInt(detailVo.getOrdered());
			}
			remainingQuantity = quantity-ordered;
			detailVo.setRemainingQuantity(""+remainingQuantity);
			detailVo.setUnit("台");
			detailvoList.add(detailVo);
		}
		return detailvoList;
	}

	@Override
	public CommonDAO<TbContractQuotations> getBindDao() {
		return contractQuotationsDAO;
	}

	/**
	 * 
	* 方法用途和描述:根据合同ID查询下面的配置单,执行合同是查询配置单
	* @return
	* @author luj 新增日期：2013-3-1
	* @since ContractManage
	 */
	public List<ContractQuotationsVo> queryAllConfigModels(int conId){
		StringBuffer sql =  new StringBuffer();		
		sql.append("select b.projectName,a.quotations.title,b.quantity,c.totalPrice*b.quantity,c.name,a.quotations.id,c.id,b.ordered,c.totalPrice from TbContractQuotations a,TbQuotationsData b,TbConfigModels c where a.quotations.id = b.quotations.id and b.configModels.id = c.id and a.contract.id=?");
		PageInfo<TbContract> page = new PageInfo<TbContract>();
		List list = contractDAO.pageInfoQuery(sql.toString(),page, conId);
		Object[] obj = new Object[list.size()];
		List<ContractQuotationsVo> detailvoList = new ArrayList<ContractQuotationsVo>();
		int ordered=0,quantity=0,remainingQuantity=0;
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			ContractQuotationsVo detailVo = new ContractQuotationsVo();
			detailVo.setProjectName(""+obj[0]);
			detailVo.setQuotationName(""+obj[1]);
			detailVo.setQuantity(""+obj[2]);
			detailVo.setTotalPrice(""+obj[3]);
			detailVo.setConfigModelName(""+obj[4]);
			detailVo.setQuotationsId(""+obj[5]);
			detailVo.setConfigModelId(""+obj[6]);
			detailVo.setOrdered(""+obj[7]);
			if(!BlankUtil.isBlank(detailVo.getQuantity())){
				quantity = Integer.parseInt(detailVo.getQuantity());//总数
			}
			if(!BlankUtil.isBlank(detailVo.getOrdered())){
				ordered = Integer.parseInt(detailVo.getOrdered()); //下单数
			}
			remainingQuantity = quantity-ordered;
			detailVo.setRemainingQuantity(""+remainingQuantity); //采购数
			detailVo.setUnitPrice(""+(obj[8]!=null?obj[8]:0)); //单价
			detailVo.setUnit("台");
			detailvoList.add(detailVo);
		}
		return detailvoList;
	}
	
	/**
	 * 
	* 方法用途和描述:保存加个总表表单数据,修改报价总表
	* @return
	* @author lizl 新增日期：2012-12-21
	* @since ContractManage
	 */
	@Override
	public String saveAndUpdateQuotation(List<ContractQuotationsVo> volist) {
		String end = "ok";
		int quantity = 1;  //数量
		String projectName = null; //项目名
		String name = null;  //报价总表名
		int quotationsId = 0; //报价表id
		int configModelsId = 0;  //机型配置单id
		String hql = "update TbQuotationsData set projectName=?,name=?,quantity=? where quotations.id=? and configModels.id=? ";
		try {
			System.out.println("volist:"+volist.size()+",volist:"+Json.toJson(volist));
			for(int i = 0;i < volist.size(); i++) {
				projectName = volist.get(i).getProjectName();//设置项目名称
				name = volist.get(i).getQuotationName();//设置报价表名称
				String qua = volist.get(i).getQuantity() ;  //设置数量
				if(!BlankUtil.isBlank(qua)){
					quantity = Integer.parseInt(qua);
				}
				String quoId = volist.get(i).getQuotationsId(); //报价表id
				if(!BlankUtil.isBlank(quoId)){
					quotationsId = Integer.parseInt(quoId);
				}
				String cmId = volist.get(i).getConfigModelId(); //机型配置id
				if(!BlankUtil.isBlank(cmId)){
					configModelsId = Integer.parseInt(cmId);
				}
				quotationsDataDAO.executeByHQL(hql,projectName,name,quantity,quotationsId,configModelsId);
			}
		} catch (Exception e) {
			end = "error";
			e.printStackTrace();
		}
		return end;
	}

	/**
	 * 
	 * 导出合同报价表总价excel
	 * 
	 * @param contract
	 *            合同对象
	 * @author zhangjh 新增日期：2012-12-28
	 * @since ContractManage
	 */
	@Override
	public TbContract exportContractTotalPrice(TbContract contract) {
		Parameters para = Parameters.getInstance();
		if(contract==null){
			log.info("contract is null.");
			return contract;
		}
		
		String sheetName = "价格总表";
		String firstLineString="合同附件2 价格总表";
		List<String> list =new ArrayList<String>();
		String filePath=para.exportExcelPath;
		String fileName=contract.getQuotationsExcel();
		
		List<ContractQuotationsVo> contractList = queryAllQuotations(contract.getId());
		//把总价格四舍五入
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(0);
		for (ContractQuotationsVo t : contractList) {
			StringBuffer text = new StringBuffer(); 
			double totalprice = t.getTotalPrice()!=null?Double.parseDouble(t.getTotalPrice()):0d;
			text.append(t.getProjectName()+","+t.getQuotationName()+",台,"+t.getQuantity()+","+nf.format(totalprice).replace(",", "")+",  ");
			list.add(text.toString());
		}
		
		 if(null == fileName || "".equals(fileName)) {
			 fileName =""+System.currentTimeMillis();
		 }
		 if(fileName.indexOf(".xlsx") < 0) {
			 fileName = fileName+".xlsx";
		 }	
		 FilUtil2.createPriceListExcel(sheetName, firstLineString, list, filePath, fileName);
		 contract.setQuotationsExcel(fileName);
		 this.contractDAO.merge(contract);
		 return contract;
	}

	@Override
	public boolean shengchengContractFile(Integer contractId) {
		if(contractId==null)
			return false;
		try{
			Parameters para = Parameters.getInstance();
			WordTools tools = WordTools.getInstance();
			List<TbContractDocVariable> docVariable = null;
			TbContractDocVariable var = null;
			TbContractDoc contractDoc = null;
			String end = para.exportWordPath;
			String docTemp = para.templatesWordPath;
			//查询该合同下的所有合同文件
			List<TbContractDoc> idsd = contractDocService.queryConFileById(contractId);
			long fileName = 0;
			//导出DOC文件
			System.out.println("****************************DocStart********************     List<TbContractDoc>:"+idsd.size());
			for(int i = 0;i < idsd.size(); i++) {
				contractDoc = idsd.get(i);
				if(null != contractDoc) {
					String endPath = contractDoc.getDoc().getPath();
					fileName = System.currentTimeMillis();
					//判断文件是否是图片，不是图片才进行下面操作
					if(endPath.endsWith("docx")){
						int id = idsd.get(i).getId();
						List<ContractDocVariableVo> volist = contractDocVariableService.queryAllContractDocVariable(id,true);
						docVariable = new ArrayList<TbContractDocVariable>();
						for(int j = 0;j < volist.size(); j++) {
							var = new TbContractDocVariable();
							var.setVariableName(volist.get(j).getVariableName());
							var.setContent(volist.get(j).getContent());
							docVariable.add(var);
						}
						System.out.println(docTemp+endPath+"       "+end+fileName+".docx");
						tools.export(docTemp+endPath,docVariable,end+fileName+".docx");
						contractDoc.setExportPath(fileName+".docx");
					}else if(endPath.endsWith("jpg")){
						//把图片移到下载的文件夹
						tools.moveFile(docTemp+endPath,end+fileName+".jpg");
						contractDoc.setExportPath(fileName+".jpg");
						LOGGER.info("文件是图片"+endPath);
					}
					this.contractDocService.updateContractDoc(contractDoc);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public Integer getContractByQuotationId(Integer quotationId) {
		String hql = "select count(*) from TbContractQuotations where quotations.id = ?";
		Integer  rs = this.contractQuotationsDAO.findTotalCount(hql, quotationId);
		return rs;
	}
}
