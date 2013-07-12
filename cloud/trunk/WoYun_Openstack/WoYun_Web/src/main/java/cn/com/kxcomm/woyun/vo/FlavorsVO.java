package cn.com.kxcomm.woyun.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import cn.com.woyun.nova.model.Link;

/**
 * 
 * 功能描述:模板vo
 * 
 * @author chenliang 新增日期：2012-11-30
 * @since WoYun_Web
 */
public class FlavorsVO implements Serializable {

	private String id;
	
	private String name;
	
	private String vcpus;
	
	private Integer ram;
	
	private String disk;
	
	private Integer ephemeral;

	public String getVcpus() {
		return this.vcpus;
	}

	public void setVcpus(String vcpus) {
		this.vcpus = vcpus;
	}

	public Integer getRam() {
		return this.ram;
	}

	public void setRam(Integer ram) {
		this.ram = ram;
	}

	public String getDisk() {
		return this.disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public Integer getEphemeral() {
		return this.ephemeral;
	}

	public void setEphemeral(Integer ephemeral) {
		this.ephemeral = ephemeral;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FlavorsVO [id=" + id + ", name=" + name + "]";
	}
	
}
