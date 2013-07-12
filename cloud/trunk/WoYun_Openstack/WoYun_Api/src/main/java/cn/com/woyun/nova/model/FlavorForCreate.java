package cn.com.woyun.nova.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("flavor")
@JsonIgnoreProperties(ignoreUnknown=true)
public class FlavorForCreate implements Serializable {
	
	private Integer id;
	
	private String name;
	
	private Integer vcpus;
	
	private Integer ram;
	
	private Integer disk;
	
	@JsonProperty("OS-FLV-EXT-DATA:ephemeral")
	private Integer ephemeral;
	
	private Integer swap;
	
	@JsonProperty("rxtx_factor")
	private Integer rxtxFactor;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the vcpus
	 */
	public Integer getVcpus() {
		return vcpus;
	}

	/**
	 * @param vcpus the vcpus to set
	 */
	public void setVcpus(Integer vcpus) {
		this.vcpus = vcpus;
	}

	/**
	 * @return the ram
	 */
	public Integer getRam() {
		return ram;
	}

	/**
	 * @param ram the ram to set
	 */
	public void setRam(Integer ram) {
		this.ram = ram;
	}

	/**
	 * @return the disk
	 */
	public Integer getDisk() {
		return disk;
	}

	/**
	 * @param disk the disk to set
	 */
	public void setDisk(Integer disk) {
		this.disk = disk;
	}

	/**
	 * @return the ephemeral
	 */
	public Integer getEphemeral() {
		return ephemeral;
	}

	/**
	 * @param ephemeral the ephemeral to set
	 */
	public void setEphemeral(Integer ephemeral) {
		this.ephemeral = ephemeral;
	}

	/**
	 * @return the swap
	 */
	public Integer getSwap() {
		return swap;
	}

	/**
	 * @param swap the swap to set
	 */
	public void setSwap(Integer swap) {
		this.swap = swap;
	}

	/**
	 * @return the rxtxFactor
	 */
	public Integer getRxtxFactor() {
		return rxtxFactor;
	}

	/**
	 * @param rxtxFactor the rxtxFactor to set
	 */
	public void setRxtxFactor(Integer rxtxFactor) {
		this.rxtxFactor = rxtxFactor;
	}
	
}
