package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbDataRecord;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;
import cn.com.kxcomm.contractmanage.vo.DataBean;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;

/**
 * 
 * 数据源数据记录
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
public interface IDataRecordService  extends ICommonService<TbDataRecord>{

	//根据数据源id查询详情
	public List<DataBean> listDetailDataRecord(int dataSourceId);
	
	//根据类型id查询详情
	public PageInfo<DataRecordVo> getDetail(int typeId,int sourceId,PageInfo<DataRecordVo> pageinfo);
	
	//上传文件excel
	public int uploadDataSource(XSSFWorkbook workbook,TbDataSource dataSource) throws Exception;
	
}
