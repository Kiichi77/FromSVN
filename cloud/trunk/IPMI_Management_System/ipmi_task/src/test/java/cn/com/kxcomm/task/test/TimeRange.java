package cn.com.kxcomm.task.test;

import java.util.Calendar;

public class TimeRange {
	public boolean inTimeRange(int startHour,int startMinute,int endHour,int endMinute){
		Calendar nowTime = Calendar.getInstance(); //现在的时间
		Calendar startTime = Calendar.getInstance();//开始的时间
		startTime.set(Calendar.HOUR_OF_DAY, startHour);
		startTime.set(Calendar.MINUTE, startMinute);
		startTime.set(Calendar.MILLISECOND, 0);
		Calendar endTime = Calendar.getInstance();//结束的时间
		if((startHour - endHour)>=12){
			endTime.add(Calendar.DATE,1);
		}
		endTime.set(Calendar.HOUR_OF_DAY, endHour);
		endTime.set(Calendar.MINUTE, endMinute);
		endTime.set(Calendar.MILLISECOND, 59);
		
		System.out.println("现在的时间:"+nowTime.get(Calendar.YEAR)+"-"+nowTime.get(Calendar.MONTH)+"-"+nowTime.get(Calendar.DAY_OF_MONTH)+" "+nowTime.get(Calendar.HOUR_OF_DAY)+":"+nowTime.get(Calendar.MINUTE));
		System.out.println("开始的时间:"+startTime.get(Calendar.YEAR)+"-"+startTime.get(Calendar.MONTH)+"-"+startTime.get(Calendar.DAY_OF_MONTH)+" "+startTime.get(Calendar.HOUR_OF_DAY)+":"+startTime.get(Calendar.MINUTE));
		System.out.println("结束的时间:"+endTime.get(Calendar.YEAR)+"-"+endTime.get(Calendar.MONTH)+"-"+endTime.get(Calendar.DAY_OF_MONTH)+" "+endTime.get(Calendar.HOUR_OF_DAY)+":"+endTime.get(Calendar.MINUTE));
		
		if (nowTime.compareTo(startTime) >= 0 && nowTime.compareTo(endTime) <= 0) {
			return true;
		}
		return false;
	}
	
	
	public boolean inTimeRangeUse(int startHour,int startMinute,int endHour,int endMinute){
		Calendar nowTime = Calendar.getInstance(); //现在的时间
		Calendar startTime = Calendar.getInstance();//开始的时间
		startTime.set(Calendar.HOUR_OF_DAY, startHour);
		startTime.set(Calendar.MINUTE, startMinute);
		startTime.set(Calendar.MILLISECOND, 0);
		Calendar endTime = Calendar.getInstance();//结束的时间
		if(endHour<startHour){
			endTime.add(Calendar.DATE,1);
		}
		if(endHour==startHour && startMinute>=endMinute){
			endTime.add(Calendar.DATE,1);
		}
		endTime.set(Calendar.HOUR_OF_DAY, endHour);
		endTime.set(Calendar.MINUTE, endMinute);
		endTime.set(Calendar.MILLISECOND, 59);
		
		System.out.println("现在的时间:"+nowTime.get(Calendar.YEAR)+"-"+nowTime.get(Calendar.MONTH)+"-"+nowTime.get(Calendar.DAY_OF_MONTH)+" "+nowTime.get(Calendar.HOUR_OF_DAY)+":"+nowTime.get(Calendar.MINUTE));
		System.out.println("开始的时间:"+startTime.get(Calendar.YEAR)+"-"+startTime.get(Calendar.MONTH)+"-"+startTime.get(Calendar.DAY_OF_MONTH)+" "+startTime.get(Calendar.HOUR_OF_DAY)+":"+startTime.get(Calendar.MINUTE));
		System.out.println("结束的时间:"+endTime.get(Calendar.YEAR)+"-"+endTime.get(Calendar.MONTH)+"-"+endTime.get(Calendar.DAY_OF_MONTH)+" "+endTime.get(Calendar.HOUR_OF_DAY)+":"+endTime.get(Calendar.MINUTE));
		
		if (nowTime.compareTo(startTime) >= 0 && nowTime.compareTo(endTime) <= 0) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args){
		TimeRange aa =new TimeRange();
		int startH = 23;
		int startM=59;
		int endH = 23;
		int endM = 59;
		boolean isflag = aa.inTimeRange(startH, startM, endH, endM);
		System.out.println("返回:"+isflag);
		isflag = aa.inTimeRangeUse(startH, startM, endH, endM);
		System.out.println("返回:"+isflag);
	}
}
