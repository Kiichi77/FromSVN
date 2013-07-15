package cn.com.kxcomm.common.util;

import java.util.Timer;

/**
 * 
* 功能描述:定时器任务
* <p>版权所有：康讯通讯
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author lizl 新增日期：2010-7-27
* @author 你的姓名 修改日期：2010-7-27
* @since woyun
 */
public class TimerUtil {
	private Timer timer;
	
	private static TimerUtil timerUtil;
	
	private TimerUtil(){
		timer = new Timer();
	}
	
	
	public synchronized static TimerUtil getInstance(){
		if(timerUtil==null) timerUtil = new TimerUtil();
		return timerUtil;
	}
	
	/**
	* 方法用途和描述: 获取Timer，需要用到的地方统一在这里获取可以避免有单一系统有多个Timer实例
	* @return
	* @author lizl 新增日期：2010-7-27
	* @author 你的姓名 修改日期：2010-7-27
	* @since woyun
	 */
	public Timer getTimer(){
		return this.timer;
	}
	
	/**
	 * 
	* 方法用途和描述: 终止计时器，丢弃所有当前已安排的任务
	* @author lizl 新增日期：2010-7-27
	* @author 你的姓名 修改日期：2010-7-27
	* @since woyuns
	 */
	public void cancel(){
		this.timer.cancel();
	}
	
	/**
	 * 
	* 方法用途和描述: 从计时器的任务队列中移除所有已取消的任务。
	* @author lizl 新增日期：2010-7-27
	* @author 你的姓名 修改日期：2010-7-27
	* @since woyun
	 */
	public void purge() {
		this.timer.purge();
	}

}
