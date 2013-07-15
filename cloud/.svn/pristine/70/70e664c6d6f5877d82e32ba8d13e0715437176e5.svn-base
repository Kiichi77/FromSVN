package cn.com.kxcomm.ipmi.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.dao.CommonDAO;
import cn.com.kxcomm.ipmi.dao.PolicyServerDAO;
import cn.com.kxcomm.ipmi.entity.TbPolicy;
import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.service.PolicyServerService;

/**
 * 策略分配业务逻辑处理类
 * @author chenliang
 *
 */
@Service("policyServerService")
public class PolicyServerServiceImpl extends CommonServiceImpl<TbPolicySever> implements
PolicyServerService {

	private static final Logger log = Logger.getLogger(PolicyServerServiceImpl.class);

	@Autowired(required=true)
	public PolicyServerDAO policyServerDAO;

	@Override
	public CommonDAO<TbPolicySever> getBindDao() {
		return policyServerDAO;
	}

	/**
	 * 策略分配分页查询
	 * params:entity 分页实体类，用于存放条件值
	 * 		  pageInfo 分页对象
	 * return page 分页集合
	 */
	@Override
	public Page<TbPolicySever> findByPage(TbPolicySever entity, Page<TbPolicySever> pageInfo) {
		StringBuffer hql = new StringBuffer(" from TbPolicySever tps where 1=1 ");
		if(!BlankUtil.isBlank(entity)){
			if(!BlankUtil.isBlank(entity.getTbPolicy())){
				if(!BlankUtil.isBlank(entity.getTbPolicy().getPolicyName())){
					hql.append(" and tps.tbPolicy.policyName like ? ");
					entity.getTbPolicy().setPolicyName("%"+entity.getTbPolicy().getPolicyName()+"%");
				}
			}
			if(!BlankUtil.isBlank(entity.getTbServer())){
				if(!BlankUtil.isBlank(entity.getTbServer().getServerIpIn())){
					hql.append(" and tps.tbServer.serverIpIn like ? ");
					entity.getTbServer().setServerIpIn("%"+entity.getTbServer().getServerIpIn()+"%");
				}
			}
			if(!BlankUtil.isBlank(entity.getBiningState())&&entity.getBiningState()!=0){
				hql.append(" and tps.biningState =? ");
			}
			if(BlankUtil.isBlank(entity.getTbPolicy().getPolicyName())||entity.getTbPolicy().getPolicyName().equals(""))
				entity.getTbPolicy().setPolicyName(null);
			if(BlankUtil.isBlank(entity.getTbServer().getServerIpIn())||entity.getTbServer().getServerIpIn().equals(""))
				entity.getTbServer().setServerIpIn(null);
			if(BlankUtil.isBlank(entity.getBiningState())||entity.getBiningState().equals(""))
				entity.setBiningState(null);
			if(!BlankUtil.isBlank(entity)){
				if(entity.getBiningState()!=null){
					if(entity.getBiningState()==0)
						entity.setBiningState(null);
				}
			}
			return policyServerDAO.findByPage(pageInfo, hql.toString(), entity.getTbPolicy().getPolicyName(),entity.getTbServer().getServerIpIn(),entity.getBiningState());
		}else{
			return policyServerDAO.findByPage(pageInfo,hql.toString());
		}
	}

	/**
	 * 实际操作中,通过ip模糊搜索出来的服务器绝对不会多,使用hibernate的的batch操作即可
	 */
	public void littleBatchSave(List<TbPolicySever> list,String policyid){
		Session session=policyServerDAO.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		if(list.size()>0){
			for(int i = 0 ; i<list.size();i++){
				TbPolicySever  t = list.get(i);
				session.save(t);
				if(i%30==0)
				{
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			session.close();
		}
	}

	/**
	 * 获取该服务器空闲的policyid
	 * @param serverid
	 * @return
	 */
	public Integer getFreePolicyId(TbServer server) {
		String hql="select policyidInServer from TbPolicySever where bining_server_id = ? order by policyid_in_server asc";
		List<Integer> policyids=policyServerDAO.find(hql, server.getId());
		Integer policyid = 0;
		//下面这段是避免policyid为1-5和13,出自张建华张工之口
		if(policyids!=null&&policyids.size()>=1)
		policyid=policyids.get(policyids.size()-1);
		log.info("policyid max:"+policyid);
        /**
         * 赵宝东新加:中途有策略删除了的话,也能把它空余了的ID纠出来的判断
         */
		for(int i=0;i<policyids.size();i++){	
		   if(i+6<policyids.get(i)){
			   policyid=i+5;
			   break;
		   }
		}
		if(policyid==null||policyid<6){
			log.info("==null||<6:"+policyid);
			policyid=6;
		}else if(policyid==12){
			policyid=14;
		}else policyid=policyid+1;
		return policyid;
	} 



	/**
	 * 
	 * 方法用途和描述:BigInteger类型转为Long类型
	 * @param params
	 * @return
	 * @author chenl 新增日期：2012-8-15
	 * @since ipmi_web
	 */
	private Long parserLong(BigInteger params){
		Long result = 0L;
		if(!BlankUtil.isBlank(params)){
			result = Long.parseLong(params.toString());
		}
		return result;
	}

	/**
	 * 
	 * 方法用途和描述:TinyInt类型转为short类型
	 * @param params
	 * @return
	 * @author chenl 新增日期：2012-8-15
	 * @since ipmi_web
	 */
	private Short parserShort(Byte params){
		Short result = 0;
		if(!BlankUtil.isBlank(params)){
			result = params.shortValue();
		}
		return result;
	}
	@Override
	public List<TbPolicySever>  findByPolicyId(Long id) {
		String hql =  " select tps.id,tps.bining_server_type,tps.bining_state,tps.policyid_in_server, "+
		" ts.id,ts.idrac_ip,ts.idrac_user,ts.idrac_password, " +
		" tp.id,tp.policy_type"+
		" from tb_policy_sever tps,tb_server ts,tb_policy tp "+
		" where tps.bining_server_id = ts.id "+
		" and tps.bining_id = tp.id "+
		" and tp.id = ? ";
		List list = policyServerDAO.findbyHql(hql,id);
		Object[] obj = new Object[list.size()];
		List<TbPolicySever> policySeverList = new ArrayList<TbPolicySever>();
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);

			TbPolicySever policySever = new TbPolicySever();
			policySever.setId(parserLong((BigInteger) obj[0]));
			policySever.setBiningServerType((Integer) obj[1]);
			policySever.setBiningState((Integer) obj[2]);
			policySever.setPolicyidInServer((Integer)  obj[3]);

			TbServer server = new TbServer();
			server.setId(parserLong((BigInteger) obj[4]));
			server.setIdracIp(BlankUtil.isNull(obj[5]));
			server.setIdracUser(BlankUtil.isNull(obj[6]));
			server.setIdracPassword(BlankUtil.isNull(obj[7]));
			policySever.setTbServer(server);

			TbPolicy policy = new TbPolicy();
			policy.setId(parserLong((BigInteger) obj[8]));
			policy.setPolicyType((Short) obj[9]);
			policySever.setTbPolicy(policy);

			policySeverList.add(policySever);
		}
		return policySeverList;
	}

	@Override
	public TbPolicySever findByPolicyIdandServerId(Long pid, Long sid) {
		String hql="from TbPolicySever where tbPolicy.id = ? and tbServer.id= ?";
		TbPolicySever tbPolicySever=(TbPolicySever)policyServerDAO.findUnique(hql, pid,sid);
		return tbPolicySever;
	}



}
