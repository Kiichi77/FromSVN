package cn.com.kxcomm.contractmanage.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.AppConfig;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.FtpUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDocDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemplatesDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemplatesDocDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemplatesDocTypeDAO;
import cn.com.kxcomm.contractmanage.dao.ContractTemplatesRelationShipDAO;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplates;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDocType;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesRelationship;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesDocService;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVo;
import cn.com.kxcomm.contractmanage.web.util.FtpSync;

@Service("contractTemplatesDocService")
public class ContractTemplatesDocService extends CommonService<TbContractTemplatesDoc> implements IContractTemplatesDocService{
	
	private static final Logger log = Logger.getLogger(ContractTemplatesDocService.class);
	
	@Autowired(required=true)
	private ContractTemplatesDocDAO contractTemplatesDocDAO;
	@Autowired(required=true)
	private ContractTemplatesDAO contractTemplatesDAO;
	@Autowired(required=true)
	private ContractDocDAO contractDocDAO;
	@Autowired(required=true)
	private ContractTemplatesDocTypeDAO contractTemplatesDocTypeDAO;
	@Autowired(required=true)
	private ContractTemplatesRelationShipDAO contractTemplatesRelationShipDAO;
	
	@Override
	public CommonDAO<TbContractTemplatesDoc> getBindDao() {
		return contractTemplatesDocDAO;
	}

	//查询某一个模版下所有的文件
	@Override
	public List<ContractTemplatesDocVo> listContractTemplatesDoc(int id){
		StringBuffer hql = new StringBuffer();
		hql.append(" select a from ");
		hql.append(" TbContractTemplatesRelationship a");
		hql.append(" where a.contractTemplates.id=?");
		
		List<ContractTemplatesDocVo> listContractTemplatesDoc=new ArrayList<ContractTemplatesDocVo>();
		List list = contractTemplatesDocDAO.find(hql.toString(), id);
		for(int i=0;i<list.size();i++){
			TbContractTemplatesRelationship doc = (TbContractTemplatesRelationship)list.get(i);
			ContractTemplatesDocVo contractTemplatesDocVo=new ContractTemplatesDocVo();
			contractTemplatesDocVo.setId(""+doc.getId());
			contractTemplatesDocVo.setDocname(doc.getContractTemplatesDoc().getDocname());
			contractTemplatesDocVo.setCreatetime(""+doc.getContractTemplatesDoc().getCreatetime());
			contractTemplatesDocVo.setPath(doc.getContractTemplatesDoc().getPath());
			contractTemplatesDocVo.setbVariable(""+doc.getContractTemplatesDoc().getbVariable());
			Set set = doc.getContractTemplatesDoc().getContractTemplatesDocTypeMembers();
			Iterator iter = set.iterator();
			String typeName = "";
			int num=0;
			while (iter.hasNext()) {
				num++;
				TbContractTemplatesDocType dt = (TbContractTemplatesDocType) iter.next();
				typeName += dt.getDocType().getName();
				if(num!=set.size()){
					typeName+=",";
				}
			}
			contractTemplatesDocVo.setTypeName(typeName);
			listContractTemplatesDoc.add(contractTemplatesDocVo);
		}
		return listContractTemplatesDoc;
	}

		//根据id查询
		@Override
		public PageInfo<ContractTemplatesDocVo> getTemplatesDocByPk(int id)
				throws Exception {
			TbContractTemplatesDoc templatesDoc = contractTemplatesDocDAO.findById(id);
			List<ContractTemplatesDocVo> volist = new ArrayList<ContractTemplatesDocVo>();
			if(!BlankUtil.isBlank(templatesDoc)){
				ContractTemplatesDocVo vo = new ContractTemplatesDocVo();
				vo.setbVariable(""+templatesDoc.getbVariable());
				vo.setCreatetime(""+templatesDoc.getCreatetime());
				vo.setDocname(templatesDoc.getDocname());
				vo.setId(""+templatesDoc.getId());
				vo.setPath(templatesDoc.getPath());
				Set<TbContractTemplatesDocType> set = templatesDoc.getContractTemplatesDocTypeMembers();
				Iterator iter = set.iterator();
				String typeId = "";
				int i = 0 ;
				while (iter.hasNext()) {
					i++;
					TbContractTemplatesDocType docType = (TbContractTemplatesDocType) iter.next();
					typeId += docType.getDocType().getId();
					if(i!=set.size()){
						typeId+=",";
					}
				}
				vo.setTypeName(typeId);
				volist.add(vo);
			}
			PageInfo<ContractTemplatesDocVo> pageInfo = new PageInfo<ContractTemplatesDocVo>();
			pageInfo.setMsg("ok");
			pageInfo.setRows(volist);
			return pageInfo;
		}

		//修改
		@Override
		public void updateTemplatesDoc(ContractTemplatesDocVo docvo) throws Exception {
			String stringid = docvo.getId();
			int docvoid = 0;
			if(!BlankUtil.isBlank(stringid)){
				docvoid = Integer.parseInt(stringid);
			}
			//1.修改合同模板的内容
			StringBuffer hql = new StringBuffer();
			hql.append("update TbContractTemplatesDoc set docname=?,bVariable=? where id = ?");
			int result = contractTemplatesDocDAO.executeByHQL(hql.toString(), docvo.getDocname(),Integer.parseInt(docvo.getbVariable()),docvoid);
			LOGGER.info("修改合同模板内容成功!");
		}
		
		//根据模板ID查询相关模板文件
		//params tempId合同ID
		@Override
		public List<TbContractTemplatesDoc> queryConTempDocById(String tempId) {
			//select * from tb_contract_doc t1,tb_contract_templates_doc t2 
			//where t1.doc_id = t2.id and t1.contract_id = 9 and t2.b_variable = 1;
			
			StringBuffer hql = new StringBuffer();
			hql.append(" select t2 from TbContractDoc t1,TbContractTemplatesDoc t2 ");
			hql.append(" where t1.doc.id = t2.id ");
			hql.append(" and t1.contract.id = ? ");
			hql.append(" and t2.bVariable = 1 ");
			List list = contractTemplatesDocDAO.find(hql.toString(),Integer.parseInt(tempId));
			List<TbContractTemplatesDoc> templatesDocList = new ArrayList<TbContractTemplatesDoc>();
			for (int i = 0; i < list.size(); i++) {
				TbContractTemplatesDoc templatesDoc = (TbContractTemplatesDoc) list.get(0);
				templatesDocList.add(templatesDoc);
			}
			
//			TbContractDoc contractDoc = new TbContractDoc();
//			TbContract contract = new TbContract();
//			contract.setId(Integer.parseInt(tempId));
//			contractDoc.setContract(contract);
//			TbContractTemplatesDoc contractTemplatesDoc = new TbContractTemplatesDoc();
//			contractTemplatesDoc.setbVariable(1);
//			contractDoc.setDoc(contractTemplatesDoc);
//			List<TbContractDoc> doclist = this.contractDocDAO.findByExample(contractDoc);
//			for (TbContractDoc tbContractDoc : doclist) {
//				templatesDocList.add(tbContractDoc.getDoc());
//			}
			return templatesDocList;
		}

		/**
		 * 查询合同模板文件
		 */
		@Override
		public PageInfo<ContractTemplatesDocVo> queryAll(PageInfo<ContractTemplatesDocVo> pageInfo,ContractTemplatesDocVo vo,boolean flat) {
			Integer typeName = null;
			StringBuffer sb = new StringBuffer();
			sb.append(" select doc.id,doc.b_variable,doc.create_time,doc.doc_name,doc.path,group_concat(type.name) name ");
			sb.append(" from tb_contract_templates_doc doc,tb_contract_templates_doc_type doctype,tb_contract_doc_type type ");
			sb.append(" where doctype.doc_id = doc.id and doctype.doc_type_id = type.id ");
			if(!BlankUtil.isBlank(vo.getCreatetime())){
				sb.append(" and date_format(doc.create_time,'%Y-%m-%d')=date_format(?,'%Y-%m-%d')");
			}else{
				vo.setCreatetime(null);
			}
			if(!BlankUtil.isBlank(vo.getName())){
				sb.append(" and doc.doc_name like '%"+vo.getName()+"%'");
			}
			if(!BlankUtil.isBlank(vo.getTypeName())){
				if(!"0".equals(vo.getTypeName())){
					sb.append(" and type.id = ? ");
					typeName = Integer.parseInt(vo.getTypeName());
				}
			}
			sb.append(" group by id");
			  StringBuffer countSql = new StringBuffer();
			    countSql.append(" select count(*) as count from ("+sb.toString()+") tt ");
			    int count = contractTemplatesDocDAO.findCount(countSql.toString());
			List list = contractTemplatesDocDAO.findByPage(flat,sb.toString(), pageInfo, vo.getCreatetime(),typeName);
			List<ContractTemplatesDocVo> volist = new ArrayList<ContractTemplatesDocVo>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			Object[] obj = new Object[list.size()];
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[]) list.get(i);
				ContractTemplatesDocVo temDocvo = new ContractTemplatesDocVo();
				temDocvo.setId(""+obj[0]);
				temDocvo.setbVariable(""+obj[1]);
				temDocvo.setCreatetime(""+obj[2]);
				temDocvo.setDocname(""+obj[3]);
				temDocvo.setPath(""+obj[4]);
				temDocvo.setTypeName(""+obj[5]);
				volist.add(temDocvo);
			}
			pageInfo.setMsg("ok");
			pageInfo.setRows(volist);
			
			pageInfo.setTotal(count);
			return pageInfo;
		}

		/**
		 * 查询合同模板选中的合同文件
		 */
		@Override
		public PageInfo<ContractTemplatesDocVo> queryTemplateDoc(ContractTemplatesDocVo vo) {
			Integer typeName = null;
			StringBuffer sb = new StringBuffer();
			sb.append(" select  d.id did ,c.id cid ,c.b_variable ,c.create_time,c.doc_name,c.path ,group_concat(a.name) name");
			sb.append(" from  tb_contract_doc_type a , tb_contract_templates_doc_type b,tb_contract_templates_doc c left join tb_contract_templates_relationship d ");
			sb.append(" on c.id = d.contract_templates_doc_id ");
			if(!BlankUtil.isBlank(vo.getContractTemplatesId())){// 
				sb.append(" and d.contract_templates_id = ? ");
			}else{
				vo.setContractTemplatesId(null);
			}
			
			sb.append(" where a.id = b.doc_type_id ");
			sb.append(" and b.doc_id = c.id ");
			if(!BlankUtil.isBlank(vo.getTypeName())){
				if(!"0".equals(vo.getTypeName())){
					sb.append(" and a.id = ? ");
					typeName = Integer.parseInt(vo.getTypeName());
				}
			}else{
				vo.setTypeName(null);
			}
			if(!BlankUtil.isBlank(vo.getName())){
				sb.append(" and c.doc_name like '%"+vo.getName()+"%'");
			}
			sb.append(" group by cid ");
			System.out.println("look at this sql: "+sb.toString());
			System.out.println("vo.getContractTemplatesId()"+vo.getContractTemplatesId());
			HashMap<String, Object> map = this.contractTemplatesDocDAO.findByPageSql(sb.toString(),null,vo.getContractTemplatesId(),vo.getTypeName());
			List<ContractTemplatesDocVo> volist = new ArrayList<ContractTemplatesDocVo>();
			TbContractTemplates contractTemplates = null;
			if(!BlankUtil.isBlank(vo.getContractTemplatesId()) && !"0".equals(vo.getContractTemplatesId())) {
				contractTemplates = this.contractTemplatesDAO.findById(Integer.parseInt(vo.getContractTemplatesId()));
			} else {
				contractTemplates = new TbContractTemplates();
				contractTemplates.setStatus(0);
			}
			Set set = map.entrySet();
			Iterator it = set.iterator();
			ContractTemplatesDocVo templatesDocVo = null;
			while (it.hasNext()) {
				Entry entry = (Entry) it.next();
				templatesDocVo = (ContractTemplatesDocVo) entry.getValue();
				if(0 == contractTemplates.getStatus() || 2 == contractTemplates.getStatus() || 4 == contractTemplates.getStatus() || 6 == contractTemplates.getStatus()) {//模板已审核
					if(null != templatesDocVo.getTemplateDocTypeId() && !"null".equals(templatesDocVo.getTemplateDocTypeId()) && !"0".equals(templatesDocVo.getTemplateDocTypeId())) {
						volist.add(templatesDocVo);
					}
				} else {
					volist.add((ContractTemplatesDocVo) entry.getValue());
				}
			}
			
			PageInfo<ContractTemplatesDocVo> pageInfo = new PageInfo<ContractTemplatesDocVo>();
			pageInfo.setMsg("ok");
			pageInfo.setRows(volist); 
			pageInfo.setTotal(volist.size());
			return pageInfo;
		}

		@Override
		public void ftpUploadContractDocFileSync(String localPath,String fileName) {
			AppConfig config = AppConfig.getInstance();
			boolean isFtpSync = config.getString("isFtpSync").equals("true")?true:false;
			if(!isFtpSync)
				return ;
			
			FtpSync.ftpUploadSync(localPath, "contractDocFilePath", fileName);
		}

		@Override
		public void ftpDownloadSync(String localPath) {
			AppConfig config = AppConfig.getInstance();
			boolean isFtpSync = config.getString("isFtpSync").equals("true")?true:false;
			if(!isFtpSync)
				return ;
			String ftpHost=config.getString("ftpHost");
			String ftpUser=config.getString("ftpUser");
			String ftpPasswd=config.getString("ftpPasswd");
			String contractDocFilePath=config.getString("contractDocFilePath");
			try {
				FtpUtil.downFiles(ftpHost, 21, ftpUser, ftpPasswd, contractDocFilePath, localPath, ".docx", null, false);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public void ftpDeleteSync(String fileName) {
			AppConfig config = AppConfig.getInstance();
			boolean isFtpSync = config.getString("isFtpSync").equals("true")?true:false;
			if(!isFtpSync)
				return ;
			String ftpHost=config.getString("ftpHost");
			String ftpUser=config.getString("ftpUser");
			String ftpPasswd=config.getString("ftpPasswd");
			String contractDocFilePath=config.getString("contractDocFilePath");
			try {
				String[] files = new String[1];
				files[0] = fileName;
				FtpUtil.deleteFile(ftpHost, 21, ftpUser, ftpPasswd, contractDocFilePath, files);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

}
