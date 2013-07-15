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
public class Page<T> implements Serializable {

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
	 * 当前页第一条数据在List中的位置,默认0
	 */
	private int start;

	/**
	 * 当前页最后一条数据在List中的位置
	 */
	private int end;

	/**
	 * 当前页中存放的记录
	 */

	private List<T> data;

	/**
	 * 总记录数
	 */
	private long totalCount;

	/**
	 * 排序字段
	 */
	private String orderFiled;

	/**
	 * 排序方向，DESC/ASC
	 */
	private String orderDirection;

	/**
	 * 当前页--用于查询的参数
	 */
	private int pageNo;

	/**
	 * 用于存放当前页的resultSet
	 */
	private ResultSet rs;

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	/**
	 * 构造方法，只构造空值
	 */
	public Page() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}

	/**
	 * 构造方法，只构造空值
	 */
	public Page(int pageSize) {
		this(0, 0, pageSize, new ArrayList());
	}

	public Page(HttpServletRequest request) {
		String start2 = request.getParameter("start");// 当前页第一条数据的行数
		String limit = request.getParameter("limit");// 每页条数
		String sort = request.getParameter("sort");// 排序字段
		String dir = request.getParameter("dir");// 排序方式

		start = Integer.parseInt(start2);
		pageSize = Integer.parseInt(limit);
		pageNo = start / pageSize + 1;
		end = start + pageSize;
		if (!BlankUtil.isBlank(sort)) {
			orderFiled = sort;
			orderDirection = dir;
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
	public Page(int start, long totalSize, int pageSize, List<T> data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
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
	 * @param rs
	 *            包含本页查询结果的结果集
	 */
	public Page(int start, long totalSize, int pageSize, ResultSet rs) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.rs = rs;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrderFiled() {
		return orderFiled;
	}

	public void setOrderFiled(String orderFiled) {
		this.orderFiled = orderFiled;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * 取数据库中包含的总记录数
	 * 
	 * @return 总记录数
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 取总页数
	 * 
	 * @return 总页数
	 */
	public long getTotalPageCount() {
		return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount
				/ pageSize + 1;
	}

	/**
	 * 取每页数据容量
	 * 
	 * @return 每页数据容量
	 */
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 设置当前页中的纪录
	 */
	public void setResult(List<T> data) {
		this.data = data;
	}

	/**
	 * 获取当前页中的记录
	 * 
	 * @return 当前页中的记录
	 */
	public List<T> getResult() {
		return data;
	}

	/**
	 * 取当前页码,页码默认1
	 * 
	 * @return 当前页码
	 */
	public int getCurrentPageNo() {
		return start / pageSize + 1;
	}

	/**
	 * 是否有下页
	 * 
	 * @return 有下页返回true，否则返回false
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}

	/**
	 * 是否有上页
	 * 
	 * @return 有上页返回true，否则返回false
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * 数据是否为空
	 * 
	 * @return 为空返回true, 否则返回false
	 */
	public boolean isEmpty() {
		return data == null || data.isEmpty();
	}

	/**
	 * 获取本页第一条数据在整个结果中的位置
	 * 
	 * @return 位置序号（从0开始）
	 */
	public int getStartIndex() {
		return (getCurrentPageNo() - 1) * pageSize;
	}

	/**
	 * 获取本页最后一条数据在整个结果中的位置
	 * 
	 * @return 位置序号（从0开始）
	 */
	public int getEndIndex() {
		int endIndex = getCurrentPageNo() * pageSize - 1;
		return endIndex >= totalCount ? (int) totalCount - 1 : endIndex;
	}

	/**
	 * 获取任一页第一条数据在整个结果中的位置（每页数据容量使用默认）
	 * 
	 * @param pageNo
	 *            页码，从1开始
	 * @return 位置序号（从0开始）
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在整个结果中的位置（每页数据容量使用指定）
	 * 
	 * @param pageNo
	 *            页码，从1开始
	 * @param pageSize
	 *            页面数据容量
	 * @return 位置序号（从0开始）
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", start=" + start + ", end="
				+ end + "]";
	}
	
}
