package jeecg.test.entity.unicom;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 云平台资源管理
 * @author zhangdaihao
 * @date 2013-05-24 14:47:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_unicomgd_cloud_resources", schema = "")
@SuppressWarnings("serial")
public class UnicomgdCloudResourcesEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**映射私网IP地址*/
	private java.lang.String privateIpAddress;
	/**新公网IP*/
	private java.lang.String publicIpAddress;
	/**映射私网端口号*/
	private java.lang.String privatePort;
	/**映射公网IP地址*/
	private java.lang.String publicAddress;
	/**映射公网端口号*/
	private java.lang.String publicPort;
	/**是否能上网*/
	private java.lang.String internet;
	/**所属网元*/
	private java.lang.String networkElements;
	/**使用情况*/
	private java.lang.String content;
	/**使用时间*/
	private java.util.Date createTime;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=255)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  映射私网IP地址
	 */
	@Column(name ="PRIVATE_IP_ADDRESS",nullable=true,length=255)
	public java.lang.String getPrivateIpAddress(){
		return this.privateIpAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  映射私网IP地址
	 */
	public void setPrivateIpAddress(java.lang.String privateIpAddress){
		this.privateIpAddress = privateIpAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  新公网IP
	 */
	@Column(name ="PUBLIC_IP_ADDRESS",nullable=true,length=255)
	public java.lang.String getPublicIpAddress(){
		return this.publicIpAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  新公网IP
	 */
	public void setPublicIpAddress(java.lang.String publicIpAddress){
		this.publicIpAddress = publicIpAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  映射私网端口号
	 */
	@Column(name ="PRIVATE_PORT",nullable=true,length=255)
	public java.lang.String getPrivatePort(){
		return this.privatePort;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  映射私网端口号
	 */
	public void setPrivatePort(java.lang.String privatePort){
		this.privatePort = privatePort;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  映射公网IP地址
	 */
	@Column(name ="PUBLIC_ADDRESS",nullable=true,length=255)
	public java.lang.String getPublicAddress(){
		return this.publicAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  映射公网IP地址
	 */
	public void setPublicAddress(java.lang.String publicAddress){
		this.publicAddress = publicAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  映射公网端口号
	 */
	@Column(name ="PUBLIC_PORT",nullable=true,length=255)
	public java.lang.String getPublicPort(){
		return this.publicPort;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  映射公网端口号
	 */
	public void setPublicPort(java.lang.String publicPort){
		this.publicPort = publicPort;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否能上网
	 */
	@Column(name ="INTERNET",nullable=true,length=255)
	public java.lang.String getInternet(){
		return this.internet;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否能上网
	 */
	public void setInternet(java.lang.String internet){
		this.internet = internet;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属网元
	 */
	@Column(name ="NETWORK_ELEMENTS",nullable=true,length=255)
	public java.lang.String getNetworkElements(){
		return this.networkElements;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属网元
	 */
	public void setNetworkElements(java.lang.String networkElements){
		this.networkElements = networkElements;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用情况
	 */
	@Column(name ="CONTENT",nullable=true,length=255)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用情况
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  使用时间
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  使用时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
}
