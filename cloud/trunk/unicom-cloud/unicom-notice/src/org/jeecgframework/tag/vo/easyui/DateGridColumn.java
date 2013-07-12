package org.jeecgframework.tag.vo.easyui;
/**
 * 
 * 类描述：列表字段模型
 * 
 * @author: jeecg
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class DateGridColumn {
	protected String title;//表格列名
	protected String field;//数据库对应字段
	protected Integer width;//宽度
	protected String rowspan;//跨列
	protected String colspan;//跨行
	protected String align;//对齐方式
	protected boolean sortable;//是否排序
	protected boolean checkbox;//是否显示复选框
	protected String formatter;//格式化函数
	protected boolean hidden;//是否隐藏
	protected String treefield;//
	protected boolean image;//是否是图片
	protected boolean query;//是否查询 
	protected String url;//自定义链接
	protected String funname="openwindow";//自定义函数名称
	protected String arg;
	public boolean isQuery() {
		return query;
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public void setQuery(boolean query) {
		this.query = query;
	}

	public boolean isImage() {
		return image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public String getTreefield() {
		return treefield;
	}

	public void setTreefield(String treefield) {
		this.treefield = treefield;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getTitle() {
		return title;
	}

	public String getField() {
		return field;
	}

	public Integer getWidth() {
		return width;
	}

	public String getRowspan() {
		return rowspan;
	}

	public String getColspan() {
		return colspan;
	}

	public String getAlign() {
		return align;
	}

	public boolean isSortable() {
		return sortable;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public String getFormatter() {
		return formatter;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFunname() {
		return funname;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}

}
