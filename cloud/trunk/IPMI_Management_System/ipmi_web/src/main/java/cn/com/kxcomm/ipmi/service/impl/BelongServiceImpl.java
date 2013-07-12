package cn.com.kxcomm.ipmi.service.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.dao.BelongDAO;
import cn.com.kxcomm.ipmi.dao.CommonDAO;
import cn.com.kxcomm.ipmi.entity.TbBelong;
import cn.com.kxcomm.ipmi.entity.TbServerBelong;
import cn.com.kxcomm.ipmi.service.BelongService;
import cn.com.kxcomm.ipmi.vo.BelongEntity;

/**
 * 服务器所属集合表业务逻辑类
 * @author chenliang
 *
 */
@Service("belongService")
public class BelongServiceImpl extends CommonServiceImpl<TbBelong> implements BelongService {

	private final static Logger log = Logger.getLogger(BelongServiceImpl.class);

	@Autowired(required=true)
	public BelongDAO belongDAO;

	@Override
	public CommonDAO<TbBelong> getBindDao() {
		return belongDAO;
	}

	/**
	 * 重写分页查询
	 */
	@Override
	public Page<TbBelong> findByPage(TbBelong entity, Page<TbBelong> pageInfo) {
		StringBuffer hql = new StringBuffer(" from TbBelong tb where 1=1 ");
		if(!BlankUtil.isBlank(entity)){
			if(!BlankUtil.isBlank(entity.getName()) && !"".equals(entity.getName())){
				hql.append(" and tb.name=? ");
			}
			if(!BlankUtil.isBlank(entity.getLevel())  && !"".equals(entity.getLevel())){
				if(-1==entity.getLevel().intValue()){
					entity.setLevel(null);
				}else{
					hql.append(" and tb.level=? ");
				}
			}
			return belongDAO.findByPage(pageInfo, hql.toString(), entity.getName(),entity.getLevel());
		}else{
			return belongDAO.findByPage(pageInfo, hql.toString());
		}
	}


	/**
	 * 根据菜单级别查询上一级菜单
	 * params: level 需要添加的这级菜单的级别
	 */
	@Override
	public List<TbBelong> findParentBylevel(short level) {
		log.debug("findParentBylevel level:"+level);
		//如果级别为数据中心，则没有上一级菜单
		if(level>1){ 
			//转为上一级菜单的级别
			level = (short) (level-1); 
			String hql = "from TbBelong where level = ?";
			List<TbBelong> belongList = belongDAO.find(hql, level);
			log.debug("belongList:"+belongList.size());
			return belongList;
		}
		return new ArrayList<TbBelong>();
	}


	/**
	 * 
	 * 方法用途和描述:根据父级菜单的id查询该级下的第一级子菜单。
	 * @return
	 * @author chenl 新增日期：2012-8-10
	 * @since ipmi_web
	 */
	@Override
	public List<BelongEntity> findMenu(Long parentId) {
		List<TbBelong> belongList = null;
		StringBuffer hql = new StringBuffer(" from TbBelong  where 1=1 ");
		//parentId为空则查询第一级菜单的数据
		if(BlankUtil.isBlank(parentId) || parentId==0){
			hql.append(" and parId is null");
			belongList = belongDAO.find(hql.toString());
		}else{
			hql.append(" and parId = ?");
			belongList = belongDAO.find(hql.toString(), parentId);
		}
		log.debug("belongList size:"+belongList.size());
		List<BelongEntity> belongEntityList = new ArrayList<BelongEntity>();
		if(!BlankUtil.isBlank(belongList)){
			for (int i = 0; i < belongList.size(); i++) {
				TbBelong belong = belongList.get(i);
				BelongEntity entity = new BelongEntity();
				entity.setChecked(false);
				entity.setId(belong.getId()+"");
				if(belong.getLevel()!=BusinessConstants.SERVER_CHASSIS){
					entity.setLeaf(false);
				}else{
					entity.setLeaf(true);
				}
				entity.setLevel(belong.getLevel());
				entity.setText(belong.getName());
				belongEntityList.add(entity);
			}
		}
		log.debug("belongEntityList size:"+belongEntityList.size());
		return belongEntityList;
	}

	/**
	 * 
	 * 方法用途和描述:根据父级菜单的id查询该级下的第一级子菜单。
	 * @return
	 * @author chenl 新增日期：2012-8-10
	 * @since ipmi_web
	 */
	@Override
	public List<BelongEntity> findMenuWithServer(Long parentId) {
		List<TbBelong> belongList = null;
		StringBuffer hql = new StringBuffer(" from TbBelong  where 1=1 ");
		//parentId为空则查询第一级菜单的数据
		if(BlankUtil.isBlank(parentId) || parentId==0){
			hql.append(" and parId is null");
			belongList = belongDAO.find(hql.toString());
		}else{
			hql.append(" and parId = ?");
			belongList = belongDAO.find(hql.toString(), parentId);
		}
		log.debug("belongList size:"+belongList.size());
		List<BelongEntity> belongEntityList = new ArrayList<BelongEntity>();
		if(!BlankUtil.isBlank(belongList)){
			for (int i = 0; i < belongList.size(); i++) {
				TbBelong belong = belongList.get(i);
				BelongEntity entity = new BelongEntity();
				entity.setChecked(false);
				entity.setId(belong.getId()+"");
				entity.setLeaf(false);
				//				if(belong.getLevel()!=BusinessConstants.SERVER_CHASSIS){
				//					entity.setLeaf(false);
				//				}else{
				//					entity.setLeaf(true);
				//				}
				entity.setLevel(belong.getLevel());
				entity.setText(belong.getName());
				belongEntityList.add(entity);
			}
		}
		log.debug("belongEntityList size:"+belongEntityList.size());
		TbBelong belong = belongDAO.findById(parentId);
		//如果传回来的parid是5开头的就把服务器查询出来
		if(null != belong && 5 == belong.getLevel()){
			List list = belongDAO.getServerByBelong(parentId);
			for(int i=0;i<list.size();i++){
				TbServerBelong t= (TbServerBelong)list.get(i);
				BelongEntity entity = new BelongEntity();
				entity.setChecked(false);
				entity.setId(t.getTbServer().getId()+"i");
				entity.setLeaf(true);
				Short a=6;
				entity.setLevel(a);
				entity.setText(t.getTbServer().getServerName());
				belongEntityList.add(entity);
			}
		}
		return belongEntityList;
	}
	
//	@Override
//	public List<BelongEntity> test(Long parentId){
//		List<TbBelong> belongList = null;
//		StringBuffer hql = new StringBuffer(" from TbBelong  where 1=1 ");
//		//parentId为空则查询第一级菜单的数据
//		if(BlankUtil.isBlank(parentId) || parentId==0){
//			hql.append(" and parId is null");
//			belongList = belongDAO.find(hql.toString());
//		}else{
//			hql.append(" and parId = ?");
//			belongList = belongDAO.find(hql.toString(), parentId);
//		}
//		log.debug("belongList size:"+belongList.size());
//		List<BelongEntity> checkedList = getMenu(parentId);
//		List<BelongEntity> belongEntityList = new ArrayList<BelongEntity>();
//		if(!BlankUtil.isBlank(belongList)){
//			for (int i = 0; i < belongList.size(); i++) {
//				TbBelong belong = belongList.get(i);
//				BelongEntity entity = new BelongEntity();
//				entity.setChecked(false);
//				entity.setId(belong.getId()+"");
//				entity.setLeaf(false);
//				entity.setLevel(belong.getLevel());
//				entity.setText(belong.getName());
//				belongEntityList.add(entity);
//			}
//		}
//		log.debug("belongEntityList size:"+belongEntityList.size());
//		TbBelong belong = belongDAO.findById(parentId);
//		//如果传回来的parid是5开头的就把服务器查询出来
//		if(null != belong && 5 == belong.getLevel()){
//			List list = belongDAO.getServerByBelong(parentId);
//			for(int i=0;i<list.size();i++){
//				TbServerBelong t= (TbServerBelong)list.get(i);
//				BelongEntity entity = new BelongEntity();
//				entity.setChecked(false);
//				entity.setId(t.getTbServer().getId()+"i");
//				entity.setLeaf(true);
//				Short a=6;
//				entity.setLevel(a);
//				entity.setText(t.getTbServer().getServerName());
//				belongEntityList.add(entity);
//			}
//		}
//		return belongEntityList;
////		List<BelongEntity> checkedList = getMenu(id);
////		List<BelongEntity> allBelongList = testMenu(null, checkedList);
////		return allBelongList;
//	}
//	
//	/**
//	 * 
//	* 方法用途和描述: 测试树形结构
//	* @param id
//	* @param checkedList
//	* @return
//	* @author chenl 新增日期：2012-9-7
//	* @since ipmi_web
//	 */
//	private List<BelongEntity> testMenu(Long id,List<BelongEntity> checkedList){
//		StringBuffer hql = new StringBuffer();
//		hql.append("select id,level,name,par_id from tb_belong");
//		if(id==null){
//			hql.append(" where par_id is null ");
//		}else {
//			hql.append(" where par_id = ?");
//		}
//		log.info("hql.tostring:"+hql.toString());
//		List list = belongDAO.findByHql(hql.toString(), null, id);
//		List<BelongEntity> belongList = new ArrayList<BelongEntity>();
//		Object[] obj = new Object[list.size()];
//		for (int i = 0; i < list.size(); i++) {
//			obj = (Object[]) list.get(i);
//			BelongEntity entity = new BelongEntity();
//			entity.setId(BlankUtil.isNull(obj[0]));
//			entity.setLevel((Short) obj[1]);
//			entity.setText(BlankUtil.isNull(obj[3]));
//			entity.setLeaf(false);
//			entity.setExpanded(true);
//			if(checkedList.size()<=0){
//				entity.setChecked(false);
//			}else{
//				for (int j = 0; j < checkedList.size(); j++) {
//					BelongEntity checkedEntity = checkedList.get(i);
//					if(entity.getId().equals(checkedEntity.getId())){
//						entity.setChecked(true);
//						break;
//					}else{
//						entity.setChecked(false);
//					}
//				}
//			}
//			//如果传回来的parid是5开头的就把服务器查询出来
//			if(BusinessConstants.SERVER_CHASSIS==entity.getLevel().intValue()){
//				List<BelongEntity> serverBelongList = new ArrayList<BelongEntity>();
//				List list1 = belongDAO.getServerByBelong(Long.parseLong(entity.getId()));
//				for(int j=0;j<list1.size();j++){
//					TbServerBelong t= (TbServerBelong)list1.get(j);
//					BelongEntity entity1 = new BelongEntity();
//					entity1.setId(t.getTbServer().getId()+"");
//					entity1.setLeaf(true);
//					Short a=6;
//					entity1.setLevel(a);
//					entity1.setText(t.getTbServer().getServerName());
//					if(checkedList.size()<=0){
//						entity.setChecked(false);
//					}else{
//						for (int k = 0; k < checkedList.size(); k++) {
//							BelongEntity checkedEntity = checkedList.get(i);
//							if(entity1.getId().equals(checkedEntity.getId())){
//								entity1.setChecked(true);
//								break;
//							}else{
//								entity1.setChecked(false);
//							}
//						}
//					}
//					serverBelongList.add(entity1);
//				}
//				entity.setChildren(serverBelongList);
//			}else{
//				entity.setChildren(testMenu(Long.parseLong(entity.getId()), checkedList));
//			}
//			belongList.add(entity);
//		}
//		return belongList;
//	}
//	
//	/**
//	 * 
//	* 方法用途和描述: 判断是否有关联服务器
//	* @return
//	* @author chenl 新增日期：2012-9-4
//	* @since ipmi_web
//	 */
//	private List<BelongEntity> getMenu(Long id){
//		StringBuffer hql = new StringBuffer();
//		hql.append(" select id,level,name,par_id from tb_belong where id = ? ");
//		hql.append(" or par_id in (select id from tb_belong where id = ? ) ");
//		hql.append(" or par_id in (select id from tb_belong where par_id in (select id from tb_belong where id = ?)) ");
//		hql.append(" or par_id in (select id from tb_belong where par_id in ");
//		hql.append(" (select id from tb_belong where par_id in ");
//		hql.append(" (select id from tb_belong where id = ?)))");
//		hql.append(" or par_id in (select id from tb_belong where par_id in ");
//		hql.append(" (select id from tb_belong where par_id in ");
//		hql.append(" (select id from tb_belong where par_id in ");
//		hql.append(" (select id from tb_belong where id = ? ))))");
//		hql.append(" or id in (select par_id from tb_belong where id = ? ) ");
//		hql.append(" or id in (select par_id from tb_belong where id in (select par_id from tb_belong where id = ?)) ");
//		hql.append(" or id in (select par_id from tb_belong where id in ");
//		hql.append(" (select par_id from tb_belong where id in ");
//		hql.append(" (select par_id from tb_belong where id = ?))) ");
//		hql.append(" or id in (select par_id from tb_belong where id in ");
//		hql.append(" (select par_id from tb_belong where par_id in ");
//		hql.append(" (select par_id from tb_belong where id in ");
//		hql.append(" (select par_id from tb_belong where id = ? )))) ");
//		Object[] obj = new Object[9];
//		for (int i = 0; i < obj.length; i++) {
//			obj[i] = id;
//		}
//		List list = belongDAO.findByHql(hql.toString(),null, obj);
//		List<BelongEntity> belongList = new ArrayList<BelongEntity>();
//		if(!BlankUtil.isBlank(list) && list.size()>0){
//			Object[] object = new Object[list.size()];
//			for (int i = 0; i < list.size(); i++) {
//				object = (Object[]) list.get(i);
//				BelongEntity entity = new BelongEntity();
//				entity.setId(""+parserLong((BigInteger) object[0]));
//				entity.setLevel((Short) object[1]);
//				entity.setParName(BlankUtil.isNull(object[3]));
//				belongList.add(entity);
//			}
//		}
//		return belongList;
//	}

	/**
	 * 查询最大id
	 */
	@Override
	public Long findMaxId() {
		String hql = "select max(id) from TbBelong tb";
		Long maxId = (long) belongDAO.findTotalCount(hql);
		log.info("maxId:"+(maxId+1));
		return maxId+1;
	}

	/**
	 * 根据等级，查询该等级下所有的集合列表
	 */
	@Override
	public Page<BelongEntity> findListByLevel(TbBelong belong,Page<TbBelong> pageInfo) {
		Page<BelongEntity> pageList = new Page<BelongEntity>();
		StringBuffer hql = new StringBuffer();
		hql.append("select t1.level as tlevel,t2.name as parname,t1.name as tname,t1.id as tid " +
		" from (select t.level,t.name ,t.id,t.par_id from tb_belong t where 1=1  ");
		if(BlankUtil.isBlank(belong.getLevel()) || "".equals(belong.getLevel())){
			hql.append(" and t.level = ? ");
			belong.setLevel(BusinessConstants.SERVER_DATACENTER);
		}else{
			if(-1==belong.getLevel().intValue()){
				belong.setLevel(null);
			}else{
				hql.append(" and t.level = ? ");
			}
		}
		if(!BlankUtil.isBlank(belong.getName())){
			hql.append(" and t.name like '%"+belong.getName()+"%'");
		}
		hql.append(" ) t1 left join tb_belong t2  on t1.par_id = t2.id ");
		//分页查询
		String countHql = "select count(*) as count from ("+hql.toString()+") cc";
		int count = belongDAO.findCount(countHql, belong.getLevel());
		log.debug("count:"+count);
		List list = belongDAO.findByHql(hql.toString(),pageInfo, belong.getLevel());
		Object[] obj = new Object[list.size()];
		List<BelongEntity> belongList = new ArrayList<BelongEntity>();
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			BelongEntity entity = new BelongEntity();
			entity.setLevel(Short.parseShort(obj[0].toString()));
			entity.setParName(BlankUtil.isNull(obj[1]));
			entity.setText(BlankUtil.isNull(obj[2]));
			entity.setId(obj[3].toString());
			belongList.add(entity);
		}
		pageList.setTotalCount(count);
		pageList.setResult(belongList);
		return pageList;
	}

	/**
	 * 查找菜单
	 */
	@Override
	public List<BelongEntity> getAllBelongMenu(Long belongId) {
		List<BelongEntity> belongList = new ArrayList<BelongEntity>();
		belongList = belongMenu(belongId);
		return belongList;
	}
	
	/**
	 * 
	* 方法用途和描述: 查询所有位置列表 
	* @param belonglist
	* @param parId
	* @return
	* @author chenl 新增日期：2012-8-29
	* @since ipmi_web
	 */
	private List<BelongEntity> belongMenu(Long parentId){
		List<TbBelong> belongList = null;
		StringBuffer hql = new StringBuffer(" from TbBelong  where 1=1 ");
		//parentId为空则查询第一级菜单的数据
		if(BlankUtil.isBlank(parentId) || parentId==0){
			hql.append(" and parId is null");
			belongList = belongDAO.find(hql.toString());
		}else{
			hql.append(" and parId = ?");
			belongList = belongDAO.find(hql.toString(), parentId);
		}
		log.debug("belongList size:"+belongList.size());
		List<BelongEntity> belongEntityList = new ArrayList<BelongEntity>();
		if(!BlankUtil.isBlank(belongList)){
			for (int i = 0; i < belongList.size(); i++) {
				TbBelong belong = belongList.get(i);
				BelongEntity entity = new BelongEntity();
				entity.setChecked(false);
				entity.setId(belong.getId()+"");
				entity.setLeaf(false);
				//				if(belong.getLevel()!=BusinessConstants.SERVER_CHASSIS){
				//					entity.setLeaf(false);
				//				}else{
				//					entity.setLeaf(true);
				//				}
				entity.setLevel(belong.getLevel());
				entity.setText(belong.getName());
				belongEntityList.add(entity);
			}
		}
		log.debug("belongEntityList size:"+belongEntityList.size());

		//如果传回来的parid是5开头的就把服务器查询出来
//		if(parentId.toString().startsWith("5")){
//			List list = belongDAO.getServerByBelong(parentId);
//			for(int i=0;i<list.size();i++){
//				TbServerBelong t= (TbServerBelong)list.get(i);
//				BelongEntity entity = new BelongEntity();
//				entity.setChecked(false);
//				entity.setId(t.getTbServer().getId());
//				entity.setLeaf(true);
//				Short a=6;
//				entity.setLevel(a);
//				entity.setText(t.getTbServer().getServerName());
//				belongEntityList.add(entity);
//			}
//		}
		return belongEntityList;
	}
	
	
	/**
	 * 重写删除
	 */
	@Override
	public boolean deleteAlls(Serializable[] pks) {
		boolean flat = false;
		log.debug("pks:"+pks);
		//判断该位置是否有关联有服务器。
		for (Serializable id : pks) {
			Long pkid = (Long) id;
			//如果关联服务器则不能删除
			if(isbelongService(pkid)){
				flat = false;
			}else{
				//没有关联服务器，则删除该位置及该位置下所有的位置
				flat = deleteBelongs(pkid);
			}
		}
		return flat;
	}

	/**
	 * 
	* 方法用途和描述: 判断是否有关联服务器
	* @return
	* @author chenl 新增日期：2012-9-4
	* @since ipmi_web
	 */
	private boolean isbelongService(Serializable id){
		StringBuffer hql = new StringBuffer();
		hql.append(" select server_id,belong_id from tb_server_belong where belong_id in (");
		hql.append(" select id from tb_belong where level=5 and (id = ? ");
		hql.append(" or par_id in (select id from tb_belong where id = ? ) ");
		hql.append(" or par_id in (select id from tb_belong where par_id in (select id from tb_belong where id = ?)) ");
		hql.append(" or par_id in (select id from tb_belong where par_id in ");
		hql.append(" (select id from tb_belong where par_id in ");
		hql.append(" (select id from tb_belong where id = ?)))");
		hql.append(" or par_id in (select id from tb_belong where par_id in ");
		hql.append(" (select id from tb_belong where par_id in ");
		hql.append(" (select id from tb_belong where par_id in ");
		hql.append(" (select id from tb_belong where id = ? )))))");
		hql.append(" ) ");
		Object[] obj = new Object[5];
		for (int i = 0; i < obj.length; i++) {
			obj[i] = id;
		}
		List list = belongDAO.findByHql(hql.toString(),null, obj);
		if(!BlankUtil.isBlank(list) && list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	* 方法用途和描述:删除该位置以及该位置下的所有服务器位置
	* @return
	* @author chenl 新增日期：2012-9-4
	* @since ipmi_web
	 */
	private boolean deleteBelongs(Long pk){
		log.info("delete Pk:"+pk);
		//mysql不支持删除本表操作的子连接关联,所以创建临时表
		//创建临时表
		StringBuffer createHql = new StringBuffer();
		createHql.append(" create table tmp as select id as col1 from tb_belong where id = ?");
		createHql.append(" or par_id in (select tb1.id from tb_belong tb1 where tb1.id = ? ) ");
		createHql.append(" or par_id in (select tb3.id from tb_belong tb3 where tb3.par_id in ");
		createHql.append(" (select tb2.id from tb_belong tb2 where tb2.id = ? )) ");
		createHql.append(" or par_id in (select tb6.id from tb_belong tb6 where tb6.par_id in ");
		createHql.append(" (select tb5.id from tb_belong tb5 where tb5.par_id in ");
		createHql.append(" (select tb4.id from tb_belong tb4 where tb4.id = ? ))) ");
		createHql.append(" or par_id in (select tb10.id from tb_belong tb10 where tb10.par_id in ");
		createHql.append(" (select tb9.id from tb_belong tb9 where tb9.par_id in ");
		createHql.append(" (select tb8.id from tb_belong tb8 where tb8.par_id in ");
		createHql.append(" (select tb7.id from tb_belong tb7 where tb7.id = ? )))) ");
		Long[] obj = new Long[5];
		for (int i = 0; i < obj.length; i++) {
			obj[i] = pk;
		}
		log.info("create table sql:"+createHql.toString());
		int result = belongDAO.executeSql(createHql.toString(),obj);
		log.debug("create result:"+result);
		//删除临时表中的数据
		StringBuffer deleteHql = new StringBuffer();
		deleteHql.append(" delete from tb_belong where id in (select col1 from tmp) ");
		result += belongDAO.executeSql(deleteHql.toString());
		log.debug("delete result:"+result);
		//删除临时表
		StringBuffer dropHql = new StringBuffer();
		dropHql.append(" drop table tmp ");
		result += belongDAO.executeSql(dropHql.toString());
		log.debug("drop result:"+result);
		if(result>0){
			return true;
		}
		return false;
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

}
