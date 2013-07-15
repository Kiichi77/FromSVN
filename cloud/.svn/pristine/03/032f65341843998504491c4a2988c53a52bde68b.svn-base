package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;

/**
 * activiti流程图业务调用接口
 * @author DongGod
 *
 */
public interface IProcessUserService extends ICommonService<TbProcessUser> {

	public List<TbProcessUser> queryAllByUserId(Long id,int start,int end);

	PageInfo<TbProcessUser> listTbProcessUser(PageInfo<TbProcessUser> pageInfo,
			Long projectId);
}
