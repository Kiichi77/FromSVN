package cn.com.kxcomm.ipmi.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.dao.CommonDAO;
import cn.com.kxcomm.ipmi.dao.PolicyDAO;
import cn.com.kxcomm.ipmi.dao.PolicyServerDAO;
import cn.com.kxcomm.ipmi.entity.TbPolicy;
import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.service.PolicyService;
import cn.com.kxcomm.ipmi.vo.PolicyServerEntity;

/**
 * 策略业务逻辑处理类
 * @author chenliang
 *
 */
@Service("policyService")
public class PolicyServiceImpl extends CommonServiceImpl<TbPolicy> implements
PolicyService {

	private static final Logger log = Logger.getLogger(PolicyServiceImpl.class);

	@Autowired(required=true)
	public PolicyDAO policyDAO;

	@Autowired(required=true)
	public PolicyServerDAO policyServerDAO;



	@Override
	public CommonDAO<TbPolicy> getBindDao() {
		return policyDAO;
	}

	/**
	 * 功耗控制策略分页查询
	 * params:entity 分页实体类，用于存放条件值
	 * 		  pageInfo 分页对象
	 * return page 分页集合
	 */
	@Override
	public Page<TbPolicy> findByPage(TbPolicy entity, Page<TbPolicy> pageInfo) {
		StringBuffer hql = new StringBuffer(" from TbPolicy tp where 1=1 ");
		if(!BlankUtil.isBlank(entity)){
			if(!BlankUtil.isBlank(entity.getPolicyType())&&entity.getPolicyType()!=0){
				hql.append(" and tp.policyType=? ");
			}
			if(!BlankUtil.isBlank(entity.getPolicyName())){
				hql.append(" and tp.policyName like ? ");
				entity.setPolicyName("%"+entity.getPolicyName()+"%");
			}
			if(BlankUtil.isBlank(entity.getPolicyName())||entity.getPolicyName().equals(""))
				entity.setPolicyName(null);
			if(BlankUtil.isBlank(entity.getPolicyType())||entity.getPolicyType().equals(""))
				entity.setPolicyType(null);
			if(!BlankUtil.isBlank(entity)){
				if(!BlankUtil.isBlank(entity.getPolicyType())&&entity.getPolicyType()==0){
					entity.setPolicyType(null);
				}}

			return policyDAO.findByPage(pageInfo, hql.toString(),entity.getPolicyType(),entity.getPolicyName());
		}else{
			return policyDAO.findByPage(pageInfo,hql.toString());
		}
	}

	/***
	 * 设置策略与服务器之间的关系
	 */
	@Override
	public boolean saveOrUpdateSetPolicy(PolicyServerEntity policyServerEntity) {
		if(!BlankUtil.isBlank(policyServerEntity)){
			//策略id
			Long policyId = policyServerEntity.getPolicyId();
			//根据策略id查询策略与服务器是否存在数据
			List<TbPolicySever> policyServerList = getPolicyServerListByPolicyId(policyId);
			log.debug("==========policyServerList========="+policyServerList.toString()+",isBlank"+BlankUtil.isBlank(policyServerList));
			//如果策略id查询策略与服务器中存在数据，即为修改.
			if(policyServerList.size()>0){
				//修改之前,先删除策略id在策略与服务器表中已经存的在数据.
				int result = deletePolicyServerListByPolicyId(policyId);
				if(result>0){
					//重新设置策略与服务器之间的关系.
					setPolicy(policyServerEntity);
				}else{
					log.debug("策略与服务器群组之间关系删除失败。");
				}
			}else{ //如果根据策略id查询策略与服务器中不存在数据，即为新增关系。
				setPolicy(policyServerEntity);
			}
		}else{
			log.info("policyServerEntity is null.");
		}
		return true;
	}

	/**
	 * 
	 * 方法用途和描述: 设置策略与服务器之间的关系
	 * @param policyServerEntity
	 * @return
	 * @author chenl 新增日期：2012-8-9
	 * @since ipmi_web
	 */
	public void setPolicy(PolicyServerEntity policyServerEntity){
		Long[] serverId = policyServerEntity.getServerId();
		if(!BlankUtil.isBlank(serverId)){
			for (int i = 0; i < serverId.length; i++) {
				TbPolicySever policyServer = new TbPolicySever();
				policyServer.setBiningType(policyServerEntity.getBiningType());
				policyServer.setBiningServerType(policyServerEntity.getBiningServerType());
				policyServer.setBiningState(policyServerEntity.getBiningState());
				TbPolicy policy = new TbPolicy();
				policy.setId(policyServerEntity.getPolicyId());
				policyServer.setTbPolicy(policy);
				TbServer server = new TbServer();
				server.setId(serverId[i]);
				policyServer.setTbServer(server);
				policyServerDAO.saveOrUpdate(policyServer);
			}
			log.info("Set strategy and the relationship between the server successful.");
		}else{
			log.info("serverId is null,Don't need to set up.");
		}
	}

	/**
	 * 
	 * 方法用途和描述: 根据策略id查找策略与服务器关系表的数据
	 * @param policyId
	 * @return
	 * @author chenl 新增日期：2012-8-9
	 * @since ipmi_web
	 */
	private List<TbPolicySever> getPolicyServerListByPolicyId(Long policyId){
		log.debug("getPolicyServerListByPolicyId policyId:"+policyId);
		String hql = "from TbPolicySever where tbPolicy.id=?";
		List<TbPolicySever> policyServerList = policyServerDAO.find(hql, policyId);
		return policyServerList;
	}

	/**
	 * 
	 * 方法用途和描述: 根据策略id删除策略与服务器关系表的数据
	 * @param policyId
	 * @return
	 * @author chenl 新增日期：2012-8-9
	 * @since ipmi_web
	 */
	private int deletePolicyServerListByPolicyId(Long policyId){
		log.debug("deletePolicyServerListByPolicyId policyId:"+policyId);
		String hql = "delete TbPolicySever where tbPolicy.id=?";
		int result = policyServerDAO.executeByHQL(hql, policyId);
		return result;
	}



	/**
	 * 调用张建华接口
	 * 修改策略
	 * params:policyServerlist 根据策略id查找的策略id与服务器策略id与服务器之间的原来的关系
	 * tbpolicy 获取新的阀值信息
	 */
	@Override
	public boolean updateSetPolicy(List<TbPolicySever> policyServerList,TbPolicy tbpolicy) {
		return true;
	}
}
