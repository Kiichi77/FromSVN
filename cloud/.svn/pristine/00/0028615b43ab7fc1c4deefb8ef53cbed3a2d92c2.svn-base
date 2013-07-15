package cn.com.kxcomm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cn.com.kxcomm.ipmitool.SupportTool;
import cn.com.kxcomm.ipmitool.entity.SerInfo;

public class Range255ThreadTest {

	public static void  main(String[] args) throws InterruptedException, ExecutionException{
		//ping多线程
		Long hehe = System.currentTimeMillis();
		ExecutorService pool = Executors.newFixedThreadPool(255);
		List<Future> list = new ArrayList<Future>();  
		for(int i=1;i<=255;i++){
			CheckIpmiThread a = new CheckIpmiThread("192.168.1."+i,"test","123456");
			Future f = pool.submit(a);
			list.add(f);
		}
		List<SerInfo> listser=new ArrayList();
		for(Future ff : list){
			if(!ff.get().equals("")){
				System.out.println(ff.get().toString()+",ping is Ok~!");
				SerInfo ser = new SerInfo();
				ser.setHost(ff.get().toString());
				ser.setPasswd("123456");
				ser.setUser("test");
				listser.add(ser);
			}
		}
		Long hehe2 = System.currentTimeMillis();
		System.out.println((hehe2-hehe)/1000+"秒");
		//关闭ping的多线程
		pool.shutdown();

		//开始检测IPMI功能,单线程
		Long hehe3 = System.currentTimeMillis();
		ExecutorService onethread = Executors.newSingleThreadExecutor();
		CheckIpmiThread2 a = new CheckIpmiThread2(listser);
		Future future = onethread.submit(a);
		List<SerInfo> listserr = (List<SerInfo>)future.get();
		System.out.println("有ipmi功能的主机:");
		for(SerInfo ss:listserr){
			System.out.println(ss.toString());
		}
		Long hehe4 = System.currentTimeMillis();
		System.out.println((hehe4-hehe3)/1000+"秒");
		onethread.shutdown();
	}
}

@SuppressWarnings("all")
class CheckIpmiThread2 implements Callable  {
	private List<SerInfo> listser;
	private SupportTool a;
	public CheckIpmiThread2(List<SerInfo> list){
		a = SupportTool.getSingleInstance();
		listser=list;
	}

	@Override
	public List<SerInfo> call() throws Exception {
		List<SerInfo> listserr  = a.checkSucessIpmi(listser);
		return listserr;
	}

}