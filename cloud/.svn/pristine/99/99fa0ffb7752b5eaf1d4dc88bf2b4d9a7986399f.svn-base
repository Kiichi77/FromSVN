package cn.com.kxcomm.woyun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.woyun.service.SecurityGroupService;
import cn.com.kxcomm.woyun.vo.GroupVO;
import cn.com.kxcomm.woyun.vo.IpRangeVO;
import cn.com.kxcomm.woyun.vo.RuleVO;
import cn.com.kxcomm.woyun.vo.SecurityGroupVO;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.model.SecurityGroup;
import cn.com.woyun.nova.model.SecurityGroup.Rule;
import cn.com.woyun.nova.model.SecurityGroupRuleForCreate;
import cn.com.woyun.nova.service.SecurityGroupApi;

/**
 * 
* 功能描述:安全组实现类
* @author chenliang 新增日期：2012-11-30
* @since WoYun_Web
 */
@Service("securityGroupService")
public class SecurityGroupServiceImpl extends
		CommonServiceImpl<SecurityGroupVO> implements SecurityGroupService {

	private SecurityGroupApi securityGroupApi = SecurityGroupApi.getInstance();
	
	@Override
	public List<SecurityGroupVO> listSecurityGroup(Access access) throws Exception {
		List<SecurityGroup> securityGroup = securityGroupApi.listSecurityGroup(access);
		List<SecurityGroupVO> listSecurityGroupVo = new ArrayList<SecurityGroupVO>();
		for (SecurityGroup securityGroup2 : securityGroup) {
			SecurityGroupVO vo = new SecurityGroupVO();
			vo.setNumber(securityGroup2.getId());
			vo.setName(securityGroup2.getName());
			vo.setDescription(securityGroup2.getDescription());
			listSecurityGroupVo.add(vo);
		}
		return listSecurityGroupVo;
	}

	@Override
	public List<SecurityGroupVO> showSecurityGroupByServerId(Access access,
			String serverId) throws Exception {
		List<SecurityGroup> securityGroupList = securityGroupApi.showSecurityGroupByServerId(access, serverId);
		List<SecurityGroupVO> volist = new ArrayList<SecurityGroupVO>();
		for (SecurityGroup securityGroup : securityGroupList) {
			SecurityGroupVO vo = new SecurityGroupVO();
			vo.setDescription(securityGroup.getDescription());
			vo.setName(securityGroup.getName());
			vo.setNumber(securityGroup.getId());
			List<Rule> ruleList = securityGroup.getRules();
			if(!BlankUtil.isBlank(ruleList)){
				List<RuleVO> ruleVoList = new ArrayList<RuleVO>();
				for (Rule rule : ruleList) {
					RuleVO rulevo = new RuleVO();
					rulevo.setFormPort(rule.getFromPort());
					rulevo.setId(rule.getId());
					rulevo.setIpProtocol(rule.getIpProtocol());
					rulevo.setParentGroupId(rule.getParentGroupId());
					rulevo.setToPort(rule.getToPort());
					
					GroupVO groupvo = new GroupVO();
					groupvo.setName(rule.getGroup().getName());
					groupvo.setTenantId(rule.getGroup().getTenantId());
					rulevo.setGroupvo(groupvo);
					
					IpRangeVO iprangvo = new IpRangeVO();
					iprangvo.setCidr(rule.getIpRange().getCidr());
					rulevo.setIprangvo(iprangvo);
					ruleVoList.add(rulevo);
				}
				vo.setRuleList(ruleVoList);
			}
			volist.add(vo);
		}
		return volist;
	}

	@Override
	public boolean createSecurityGroup(Access access,
			SecurityGroupVO securityGroupVo) throws Exception {
		SecurityGroup securityGroup = null;
		if(!BlankUtil.isBlank(securityGroupVo)){
			securityGroup = securityGroupApi.createSecurityGroup(access, securityGroupVo.getName(), securityGroupVo.getDescription());
		}
		return securityGroup==null?false:true;
	}

	@Override
	public boolean deleteSecurityGroup(Access access, Integer id)
			throws Exception {
		securityGroupApi.deleteSecurityGroup(access, id);
		return true;
	}

	@Override
	public SecurityGroupVO showSecurityGroup(Access access, Integer id)
			throws Exception {
		SecurityGroup securityGroup = securityGroupApi.showSecurityGroup(access, id);
		SecurityGroupVO securityGroupVO = new SecurityGroupVO();
		securityGroupVO.setName(securityGroup.getName());
		securityGroupVO.setDescription(securityGroup.getDescription());
		securityGroupVO.setNumber(securityGroup.getId());
		List<RuleVO> ruleVoList = new ArrayList<RuleVO>();
		for (Rule rule : securityGroup.getRules()) {
			RuleVO vo =new RuleVO();
			vo.setFormPort(rule.getFromPort());
			vo.setIpProtocol(rule.getIpProtocol());
			vo.setId(rule.getId());
			vo.setParentGroupId(rule.getParentGroupId());
			vo.setToPort(rule.getToPort());
			GroupVO groupvo = new GroupVO();
			groupvo.setName(rule.getGroup().getName());
			groupvo.setTenantId(rule.getGroup().getTenantId());
			vo.setGroupvo(groupvo);
			IpRangeVO iprangvo = new IpRangeVO();
			iprangvo.setCidr(rule.getIpRange().getCidr());
			vo.setIprangvo(iprangvo);
			ruleVoList.add(vo);
		}
		securityGroupVO.setRuleList(ruleVoList);
		return securityGroupVO;
	}

	@Override
	public void createSecurityGroupRule(Access access,
			SecurityGroupRuleForCreate ruleForCreate)
			throws Exception {
		this.securityGroupApi.createSecurityGroupRule(access, ruleForCreate, true);
	}

	@Override
	public void deleteSecurityGroupRule(Access access, Integer id)
			throws Exception {
		this.securityGroupApi.deleteSecurityGroupRule(access, id);
	}

}
