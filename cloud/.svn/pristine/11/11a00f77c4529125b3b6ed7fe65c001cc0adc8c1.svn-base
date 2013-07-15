package cn.com.woyun.nova.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class QuotaSets implements Iterable<QuotaSet>, Serializable{
	private static final long serialVersionUID = 3030727372441256123L;
	@JsonProperty("quota_set")
	private List<QuotaSet> list;

	/**
	 * @return the list
	 */
	public List<QuotaSet> getList() {
		return list;
	}
	
	@Override
	public Iterator<QuotaSet> iterator() {
		return list.iterator();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuotaSets [list=" + list + "]";
	}
}
