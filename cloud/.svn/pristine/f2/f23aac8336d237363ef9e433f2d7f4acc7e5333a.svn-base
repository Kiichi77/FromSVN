package cn.com.kxcomm.common.util;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 功能描述:分页公共类
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author dengcd 新增日期：2008-10-9
 * @author 你的姓名 修改日期：2008-10-9
 * @since wapportal_manager version(2.0)
 */
@SuppressWarnings("unchecked")
public class PageInfo<T> implements Serializable {

	private static final long serialVersionUID = 2728437513842150372L;

	/**
	 * 每页数据容量的默认：15
	 */
	private static final int DEFAULT_PAGE_SIZE = 15;

	/**
	 * 每页数据容量
	 */
	private int pageSize;

	/**
	 * 当前页
	 */
	private int currentPage;

	/**
	 * 当前页中存放的记录
	 */
	private List<T> rows;

	/**
	 * 总记录数
	 */
	private long total;
	
	/**
	 * 返回信息
	 */
	private String msg;

	/**
	 * 构造方法，只构造空值
	 */
	public PageInfo() {
		this(0,0, DEFAULT_PAGE_SIZE, new ArrayList());
	}

	/**
	 * 构造方法，只构造空值
	 */
	public PageInfo(int pageSize) {
		this(0,0, pageSize, new ArrayList());
	}

	public PageInfo(HttpServletRequest request) {
		String page2 = request.getParameter("page");// 当前页
		String rows2 = request.getParameter("rows");// 每页条数

		if(!BlankUtil.isBlank(page2)){
			currentPage = Integer.parseInt(page2);
		}else{
			currentPage = 1;
		}
		if(!BlankUtil.isBlank(rows2)){
			pageSize = Integer.parseInt(rows2);
		}else{
			pageSize = DEFAULT_PAGE_SIZE;
		}
	}

	/**
	 * 默认构造方法,用于返回查询得结果集
	 * 
	 * @param start
	 *            本页数据在数据库中的起始位置
	 * @param totalSize
	 *            数据库中总记录条数
	 * @param pageSize
	 *            本页容量
	 * @param data
	 *            本页包含的数据
	 */
	public PageInfo(int rows,long totalSize, int pageSize, List<T> data) {
		this.pageSize = pageSize;
		this.currentPage = rows;
		this.total = totalSize;
		this.rows = data;
	}

	/**
	 * 数据是否为空
	 * 
	 * @return 为空返回true, 否则返回false
	 */
	public boolean isEmpty() {
		return rows == null || rows.isEmpty();
	}


	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageInfo [pageSize=" + pageSize + ", currentPage="
				+ currentPage + ", rows=" + rows + ", total=" + total
				+ ", msg=" + msg + "]";
	}

}
