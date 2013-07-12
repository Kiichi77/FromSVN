package cn.com.kxcomm.contractmanage.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.FormulaUtil;
import cn.com.kxcomm.common.util.WordTools;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemplatesDocVariableDAO;
import cn.com.kxcomm.contractmanage.entity.TbContractDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDocVariable;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesDocVariableService;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVariableVo;

@Service("contractTemplatesDocVariableService")
public class ContractTemplatesDocVariableService extends CommonService<TbContractTemplatesDocVariable> implements IContractTemplatesDocVariableService{
	private static final Logger log = Logger.getLogger(ContractTemplatesDocVariableService.class);
	@Autowired(required=true)
	private ContractTemplatesDocVariableDAO contractTemplatesDocVariableDAO;
	@Autowired(required=true)
	private ContractTemplatesDocService contractTemplatesDocService;
	
	@Override
	public CommonDAO<TbContractTemplatesDocVariable> getBindDao() {
		return contractTemplatesDocVariableDAO;
	}
   
	public WordTools wordTools;
	
	//查询一个文件下所有的变量
	public List<ContractTemplatesDocVariableVo> listContractTemplatesDocVariable(int id)
	{
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT ctdv.id,ctdv.description,ctdv.variable_name,ctdv.templates_doc_id ");
		hql.append(" FROM tb_contract_templates_doc_variable ctdv,tb_contract_templates_doc ctd ");
	    hql.append(" WHERE ctdv.templates_doc_id=? ");
	    hql.append(" AND ctdv.templates_doc_id=ctd.id ");
	    List<ContractTemplatesDocVariableVo> listContractTemplatesDocVariable=new ArrayList<ContractTemplatesDocVariableVo>();
	    List list=contractTemplatesDocVariableDAO.findByPage(hql.toString(),id);
	    Object[] obj = new Object[list.size()];
	    for(int i=0;i<list.size();i++)
       {
	    	obj = (Object[]) list.get(i);
	    	ContractTemplatesDocVariableVo contractTemplatesDocVariableVo=new ContractTemplatesDocVariableVo();
	    	contractTemplatesDocVariableVo.setId(obj[0].toString());
	    	contractTemplatesDocVariableVo.setDescription(obj[1].toString());
	    	contractTemplatesDocVariableVo.setVariableName(obj[2].toString());
	    	contractTemplatesDocVariableVo.setTemplatesDocId(obj[3].toString());
	    	listContractTemplatesDocVariable.add(contractTemplatesDocVariableVo);
       }
	    return listContractTemplatesDocVariable;
	}
	/**
	 * 删除变量.
	 *
	 * @author luj 新增日期：2012-12-28
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public void deleteVariable(int id) throws SQLException
	{
		StringBuffer hql=new StringBuffer();
		hql.append(" DELETE t2 FROM tb_contract_templates_doc_variable t1,tb_contract_doc_variable t2,tb_contract_doc t3 ");
		hql.append(" where t1.id=? ");
		hql.append(" AND t2.contract_doc_id=t3.id ");
		hql.append(" and t1.variableName=t2.variableName ");
		hql.append(" AND t1.templates_doc_id=t3.doc_id ");
		//hql.append(" and t1.templatesDoc.id=t2.contractDoc.doc.id ");
		contractTemplatesDocVariableDAO.deleteBatch(hql.toString(),id);
		hql.delete(0, hql.length());
		hql.append(" DELETE FROM TbContractTemplatesDocVariable t1 ");
		hql.append(" WHERE t1.id=? ");
		contractTemplatesDocVariableDAO.executeByHQL(hql.toString(),id);
	}
	
	/**
	 * 保存、修改模版变量.
	 *
	 * @author luj 新增日期：2012-12-19
	 * @param jsonBeanList
	 * @return
	 */
	@Override
	public String saveAndUpdateVariable(String docPath,List<ContractTemplatesDocVariableVo> jsonBeanList) {
		String end = "ok";
		try {
			//判断变量名是否在文件中存在
			List<TbContractDocVariable> docVariable = new ArrayList<TbContractDocVariable>();
			for (ContractTemplatesDocVariableVo docvarVo : jsonBeanList) {
				TbContractDocVariable docvar = new TbContractDocVariable();
				docvar.setVariableName(docvarVo.getVariableName());
				docvar.setContent(docvarVo.getDescription());
				TbContractDoc doc =  new TbContractDoc();
				doc.setId(Integer.parseInt(docvarVo.getTemplatesDocId()));
				docvar.setContractDoc(doc);
				docVariable.add(docvar);
			}
			//调用检查变量名称是否一致
			WordTools tools = WordTools.getInstance();
			log.info("docPath:"+docPath+",    docVariable.size:"+docVariable.size());
			HashMap<String , TbContractDocVariable> table = tools.checkVariable(docPath, docVariable);
			log.info("table.size:"+table.size()+",docVariable.size:"+docVariable.size()+",jsonbeanList:"+jsonBeanList.size());
			//如果一致则进行保存操作
			if(table.size()==jsonBeanList.size()){
				//判断需要计算的价格的几个变量名是否与配置文件的变量名一致
//				if(checkTemplatesParam(jsonBeanList)){
					TbContractTemplatesDocVariable contractTemplatesDocVariable=null;
					TbContractTemplatesDoc contractTemplatesDoc=null;
					for(int i = 0;i < jsonBeanList.size(); i++) {
						contractTemplatesDocVariable = new TbContractTemplatesDocVariable();
						contractTemplatesDoc=new TbContractTemplatesDoc();
						if(!BlankUtil.isBlank(jsonBeanList.get(i).getId())) {
							// 修改
							contractTemplatesDocVariable.setId(Integer.parseInt(jsonBeanList.get(i).getId()));
							StringBuffer hql=new StringBuffer();
							hql.append(" UPDATE TbContractTemplatesDocVariable v ");
							hql.append(" SET v.description=?,v.variableName=?");
							hql.append(" where v.id=? ");
							contractTemplatesDocVariable.setDescription(jsonBeanList.get(i).getDescription());
							contractTemplatesDocVariable.setVariableName(jsonBeanList.get(i).getVariableName());
							contractTemplatesDocVariableDAO.executeByHQL(hql.toString(),contractTemplatesDocVariable.getDescription(),contractTemplatesDocVariable.getVariableName(),contractTemplatesDocVariable.getId());
						}else{
							//添加
							contractTemplatesDocVariable.setDescription(jsonBeanList.get(i).getDescription());
							contractTemplatesDocVariable.setVariableName(jsonBeanList.get(i).getVariableName());
							contractTemplatesDoc.setId(Integer.parseInt(jsonBeanList.get(i).getTemplatesDocId()));
							contractTemplatesDocVariable.setTemplatesDoc(contractTemplatesDoc);
							this.contractTemplatesDocVariableDAO.save(contractTemplatesDocVariable);
						}
					}
//				}else{
//					LOGGER.info("配置文件中设置的变量名称与页面设置的不一致!");
//					end = "no";
//				}
			}else{
				end="no";
				LOGGER.info("saveAndUpdateVariable variable is Not equal");
			}
		} catch (Exception e) {
			e.printStackTrace();
			end = "error";
			
		}
		return end;
	}
	
	/**
	 * 
	* 方法用途和描述:  验证页面设置的需要计算值的变量与配置文件设置的是否一致
	* @param jsonBeanList  变量集合
	* @return true 验证通过，false验证不通过 
	* @author chenliang 新增日期：2013-1-16
	* @since ContractManage
	 */
	private boolean checkTemplatesParam(List<ContractTemplatesDocVariableVo> jsonBeanList){
		HashMap<String, String> map = FormulaUtil.getConfigTemplatesParam();
		int num = 0;
		Set set = map.entrySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			for (ContractTemplatesDocVariableVo variablevo : jsonBeanList) {
				if(variablevo.getVariableName().equals(entry.getValue())){
					num++;
					break;
				}else{
					continue;
				}
			}
		}
		log.info("num:"+num+",map.size:"+map.size());
		//10为上面从配置文件中获取的10中变量，如果num==10，则表示10中变量都匹配
		if(num == map.size()){
			return true;
		}
		return false;
	}
	
}
