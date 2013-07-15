package cn.com.kxcomm.contractmanage.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.SuppliersDAO;
import cn.com.kxcomm.contractmanage.entity.TbSuppliers;
import cn.com.kxcomm.contractmanage.service.ISuppliersService;
import cn.com.kxcomm.contractmanage.service.ITaskService;
import cn.com.kxcomm.contractmanage.status.S_Suppliers;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
import cn.com.kxcomm.contractmanage.web.util.TaskName;

@Service("suppliersService")
public class SuppliersService extends CommonService<TbSuppliers> implements ISuppliersService{

	private static final Logger LOG = Logger.getLogger(SuppliersService.class);
    
	@Autowired(required=true)
	private SuppliersDAO suppliersDAO;
	@Autowired(required=true)
	private ITaskService taskService;
	
	
	public SuppliersDAO getSuppliersDAO() {
		return suppliersDAO;
	}
	public void setSuppliersDAO(SuppliersDAO suppliersDAO) {
		this.suppliersDAO = suppliersDAO;
	}
	@Override
	public CommonDAO<TbSuppliers> getBindDao() {
		return suppliersDAO;
	}
	
	//分页查询所有的供应商
	@Override
	public PageInfo<TbSuppliers> pageQuerySuppliers(PageInfo<TbSuppliers> pageInfo,TbSuppliers suppliers) {
		String sql = " from TbSuppliers s ";
		if(null!=suppliers.getId()){
			sql+= "where s.id = ? ";
		}
		List<TbSuppliers> list=suppliersDAO.pageInfoQuery(sql, pageInfo,suppliers.getId());
		int count = 0 ;
		if(null!=suppliers.getId()){
			suppliersDAO.findTotalCount("select count(*) from TbSuppliers where id = ? ",suppliers.getId());
		}else{
			suppliersDAO.findTotalCount("select count(*) from TbSuppliers ");
		}
		
		PageInfo<TbSuppliers> page = new PageInfo<TbSuppliers>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),list);
        return page;
	}
	
	//查询所有的供应商
	@Override
		public PageInfo<TbSuppliers> listSuppliers(PageInfo<TbSuppliers> pageInfo) {
			String sql = " from TbSuppliers s where s.state =? ";
			List<TbSuppliers> list=suppliersDAO.pageInfoQuery(sql, pageInfo,S_Suppliers.SUPPLIERSCODE_PASS);
			int count = suppliersDAO.findTotalCount("select count(*) from TbSuppliers where state=? ",S_Suppliers.SUPPLIERSCODE_PASS);
			PageInfo<TbSuppliers> page = new PageInfo<TbSuppliers>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),list);
            return page;
		}
	/**
	* 
	* 方法用途和描述: 根据ID查询某个客户信息
	* @return
	* @author luj 新增日期：2013-3-5
	* @since ContractManage
	*/
	@Override
	public TbSuppliers querySuppliersById(String id) {
		TbSuppliers suppliers = this.suppliersDAO.findById(Integer.parseInt(id));
		return suppliers;
	}
	
	/**
	 * 提交审核 
	 * @throws Exception 
	 */
	@Override
	public String submitChecked(CheckedVo checkedVo) throws Exception {
		int Id = 0; //合同id
		Long snpnorid = 0l; //创建人员
		Long personInChargeId = 0l; //负责人
		String param = "";//参数
		Integer projectId = null;
		if(!BlankUtil.isBlank(checkedVo.getId())){
			Id = Integer.parseInt(checkedVo.getId());
		}else{
			throw new NullPointerException("ContractId is null");
		}
		if(!BlankUtil.isBlank(checkedVo.getSnpnorId())){
			snpnorid = Long.parseLong(checkedVo.getSnpnorId());
		}else{
			throw new NullPointerException("SnpnorId is null");
		}
		if(!BlankUtil.isBlank(checkedVo.getUser_id())){
			personInChargeId = Long.parseLong(checkedVo.getUser_id());
		}
		if(!BlankUtil.isBlank(checkedVo.getProjectId())){
			projectId = Integer.parseInt(checkedVo.getProjectId());
		}
		param="?suppliersId="+Id;
		
		//更新供应商状态
		String hql = "update TbSuppliers set state=? where id=? ";
		suppliersDAO.executeByHQL(hql, S_Suppliers.SUPPLIERSCODE_CHECKING,Id);
		//向任务表中添加任务
		taskService.startTask(null, snpnorid, TaskName.audit_suppliers, projectId, personInChargeId, 
				BusinessConstants.audit_suppliers, checkedVo.getDescribe(), param,BusinessConstants.suppliers_pkid+Id);
		
		//完成审核不通过的待办任务
		TaskVo taskVo = new TaskVo();
		taskVo.setParamPkid(BusinessConstants.unSuppliers_pkid+Id);
		taskService.finishTask(taskVo);
		
		return "ok";
	}
	
	/**
	 * 审核 
	 * @throws Exception 
	 */
	@Override
	public void checkSuppliers(CheckedVo checkedVo) {
		if(!BlankUtil.isBlank(checkedVo)){
			String hql = "update TbSuppliers set state=?,checkResult=? where id=? ";
			int pkid = 0;
			if(!BlankUtil.isBlank(checkedVo.getId())){
				pkid = Integer.parseInt(checkedVo.getId());
			}
			if("0".equals(checkedVo.getResult())){ //通过
				suppliersDAO.executeByHQL(hql, S_Suppliers.SUPPLIERSCODE_PASS,checkedVo.getDescribe(),pkid);
			}else if("1".equals(checkedVo.getResult())){
				suppliersDAO.executeByHQL(hql, S_Suppliers.SUPPLIERSCODE_FAIL,checkedVo.getDescribe(),pkid);
			}
			//完成审核不通过的待办任务
			TaskVo taskVo = new TaskVo();
			taskVo.setParamPkid(BusinessConstants.suppliers_pkid+pkid);
			taskService.finishTask(taskVo);
		}
	}
}
