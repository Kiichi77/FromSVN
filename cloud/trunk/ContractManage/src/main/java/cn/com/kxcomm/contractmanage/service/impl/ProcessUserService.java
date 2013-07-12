package cn.com.kxcomm.contractmanage.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ProcessUserDAO;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;
import cn.com.kxcomm.contractmanage.service.IProcessUserService;

@Service("processUserService")
public class ProcessUserService extends CommonService<TbProcessUser> implements IProcessUserService{
	private static final Logger LOG = Logger.getLogger(ProcessUserService.class);

	@Autowired(required=true)
	private ProcessUserDAO processUserDAO;
	@Override
	public CommonDAO<TbProcessUser> getBindDao() {
		
		return processUserDAO;
	}
	
	@Override
	public List<TbProcessUser> queryAllByUserId(Long userId,int start,int end) {
		String hql = "select a from TbProcessUser a where a.userId=?";
//		List<TbProcessUser> processUselist =
		Query query = processUserDAO.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setLong(0, userId);
		query.setFirstResult(start); 
		query.setMaxResults(end); 
		List<TbProcessUser> processUselist= (List<TbProcessUser>)query.list();
		return processUselist;
	}
	
	// 查询所有的机型配置
	@Override
	public PageInfo<TbProcessUser> listTbProcessUser(PageInfo<TbProcessUser> pageInfo,Long projectId)
	{
//		List<TbProcessUser> listConfigModelsVo=new ArrayList<TbProcessUser>();
		String hql = " from TbProcessUser tu where 1=1 ";
		String counthql = "select count(*) from TbProcessUser tu  where 1=1 ";
		if(projectId!=null){
			hql += " and  tu.projectId=?";
			counthql += " and tu.projectId=?";
		}
		List<TbProcessUser> list=processUserDAO.pageInfoQuery(hql,pageInfo,projectId);
		int count = processUserDAO.findTotalCount(counthql,projectId);
//		for (TbConfigModels configModels:list) {
//			ConfigModelsVo configModelsVo=new ConfigModelsVo();
//			configModelsVo.setId(configModels.getId().toString());
//			configModelsVo.setName(configModels.getName());
//			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");  
//			configModelsVo.setCreatetime(sdf.format(configModels.getCreatetime()));
//			configModelsVo.setStatus(configModels.getStatus().toString());
//			configModelsVo.setDataSourceName(configModels.getDataSource().getName());
//			configModelsVo.setDataSourceType(configModels.getDataSource().getDataSourceType().getTypeName());
//			configModelsVo.setCreatorName(configModels.getCreator().getUserName());
//			configModelsVo.setDataSourceId(""+configModels.getDataSource().getId());
//			listConfigModelsVo.add(configModelsVo);
//		}
		PageInfo<TbProcessUser> page = new PageInfo<TbProcessUser>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),list);
		 return page;
	}

}
