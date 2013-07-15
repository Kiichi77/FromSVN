package cn.com.kxcomm.common.thread;

import java.util.List;
import java.util.concurrent.Callable;

import cn.com.kxcomm.ipmitool.SupportTool;
import cn.com.kxcomm.ipmitool.entity.SerInfo;

public class CheckisnodeornotThread implements Callable{
	private List<SerInfo> listser;
	private SupportTool a;
	public CheckisnodeornotThread(List<SerInfo> list){
		a = SupportTool.getSingleInstance();
		listser=list;
	}

	@Override
	public List<SerInfo> call() throws Exception {
		List<SerInfo> listserr  = a.checkSucessIpmi(listser);
		return listserr;
	}
}
