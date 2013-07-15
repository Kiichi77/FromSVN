package cn.com.kxcomm.common.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cn.com.kxcomm.ipmitool.entity.SerInfo;


/**
 * iprang的业务逻辑
 * @author DongGod
 *
 */
public class IprangeService {
	private String ip;
	private String user;
	private String pwd;
	private List<SerInfo> listser;
	public IprangeService(List<SerInfo> list){
		listser=list;
	}
	public IprangeService(String ipp,String userr,String pwdd){
		ip=ipp;
		user=userr;
		pwd=pwdd;
	}
	
	/**
	 * 255条线程同时PING,缩短检查时间
	 * @return
	 */
	public List<SerInfo> iprangezhiipping(){
		ExecutorService pool = Executors.newFixedThreadPool(255);
		List<Future> list = new ArrayList<Future>();  
		for(int i=1;i<=255;i++){
			PingIpThread a = new PingIpThread(ip+"."+i);
			Future f = pool.submit(a);
			list.add(f);
		}
		List<SerInfo> listser=new ArrayList();
		for(Future ff : list){
			try {
				if(!ff.get().equals("")){
					SerInfo ser = new SerInfo();
					ser.setHost(ff.get().toString());
					ser.setPasswd(pwd);
					ser.setUser(user);
					listser.add(ser);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		pool.shutdown();
		return listser;
	}
	
	/**
	 * 单线程后台执将PING通的主机检测IPMI功能,成功的加到LIST里,呵呵;
	 * @return
	 */
	public List<SerInfo> iprangezhiipmicheck(){
		ExecutorService onethread = Executors.newSingleThreadExecutor();
		CheckisnodeornotThread a = new CheckisnodeornotThread(listser);
		List<SerInfo> listserr=null;
		Future future = onethread.submit(a);
		try {
			listserr = (List<SerInfo>)future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		onethread.shutdown();
		return listserr;
	}
}
