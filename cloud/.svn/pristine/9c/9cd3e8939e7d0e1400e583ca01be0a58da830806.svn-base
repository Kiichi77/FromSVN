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
 * @Description: 发布公告
 * @author zhangdaihao
 * @date 2013-05-24 14:23:25
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_unicom_notice", schema = "")
@SuppressWarnings("serial")
public class UnicomNoticeEntity implements java.io.Serializable {
	/**公告主键*/
	private java.lang.String id;
	/**发布时间*/
	private java.util.Date createTime;
	/**公告标题*/
	private java.lang.String title;
	/**公告内容*/
	private java.lang.String content;
	/**下载地址*/
	private java.lang.String download;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公告主键
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
	 *@param: java.lang.String  公告主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发布时间
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发布时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公告标题
	 */
	@Column(name ="TITLE",nullable=true,length=255)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公告标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公告内容
	 */
	@Column(name ="CONTENT",nullable=true,length=255)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公告内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下载地址
	 */
	@Column(name ="DOWNLOAD",nullable=true,length=255)
	public java.lang.String getDownload(){
		return this.download;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下载地址
	 */
	public void setDownload(java.lang.String download){
		this.download = download;
	}
}
