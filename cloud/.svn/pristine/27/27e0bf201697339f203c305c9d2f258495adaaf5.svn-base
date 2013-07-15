package cn.com.kxcomm.contractmanage.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 到货证明
 * 
 * @author zhangjh 新增日期：2013-3-7
 * @since ContractManage
 */
@Entity
@Table(name = "tb_arrival_proved")
public class TbArrivalProved implements java.io.Serializable{
	private static final long serialVersionUID = 5712184754337744504L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_id", nullable = false)
	private TbContract contract;
	
	
	/**
	 * 到货证明
	 */
	@Column(name = "goods_proved_file", unique = false, updatable = true, nullable = true, insertable = true,length=100)
	private String goodsProvedfile;
	
	/**
	 * 初验证明
	 */
	@Column(name = "begin_proved_file", unique = false, updatable = true, nullable = true, insertable = true,length=100)
	private String beginProvedfile;
	
	/**
	 * 终验证明
	 */
	@Column(name = "final_proved_file", unique = false, updatable = true, nullable = true, insertable = true,length=100)
	private String finalProvedfile;
	
	/**
	 *到货时间
	 */
	@Column(name = "goods_time", unique = false, updatable = true, nullable = true, insertable = true)
	private Date goodstime;

	/**
	 *初验时间
	 */
	@Column(name = "begin_time", unique = false, updatable = true, nullable = true, insertable = true)
	private Date begintime;

	/**
	 *终验时间
	 */
	@Column(name = "final_time", unique = false, updatable = true, nullable = true, insertable = true)
	private Date finaltime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbContract getContract() {
		return contract;
	}

	public void setContract(TbContract contract) {
		this.contract = contract;
	}

	public String getGoodsProvedfile() {
		return goodsProvedfile;
	}

	public void setGoodsProvedfile(String goodsProvedfile) {
		this.goodsProvedfile = goodsProvedfile;
	}

	public String getBeginProvedfile() {
		return beginProvedfile;
	}

	public void setBeginProvedfile(String beginProvedfile) {
		this.beginProvedfile = beginProvedfile;
	}

	public String getFinalProvedfile() {
		return finalProvedfile;
	}

	public void setFinalProvedfile(String finalProvedfile) {
		this.finalProvedfile = finalProvedfile;
	}

	public Date getGoodstime() {
		return goodstime;
	}

	public void setGoodstime(Date goodstime) {
		this.goodstime = goodstime;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getFinaltime() {
		return finaltime;
	}

	public void setFinaltime(Date finaltime) {
		this.finaltime = finaltime;
	}


	
}
