package cn.com.woyun.keystone.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Users implements Iterable<User>, Serializable {

	@JsonProperty("users")
	private List<User> list;

	/**
	 * @return the list
	 */
	public List<User> getList() {
		return list;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Users [list=" + list + "]";
	}

	@Override
	public Iterator<User> iterator() {
		return list.iterator();
	}
	
}
