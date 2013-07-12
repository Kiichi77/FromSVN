package cn.com.kxcomm.base.vo;

public class QueryVO<T> {
	
	private Integer statrtNum;  // 开始行数
	private Integer pageSize;   // 页大小(显示多少行)
	private Integer endNum;    //  结束行数
	private int columnCount;
	
	private String sort;//排序字段
	private String dir;//排序方式 ASC/DESC
	
	
	public int getColumnCount() {
		return columnCount;
	}
	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}
	public Integer getStatrtNum() {
		return statrtNum;
	}
	public void setStatrtNum(Integer statrtNum) {
		this.statrtNum = statrtNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getEndNum() {
		return endNum;
	}
	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}


}
