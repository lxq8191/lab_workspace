package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time_test {
	public static void main(String[] args) throws Exception {
		String inuput_dt="1970-01-01 0:0:0";
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date dt= sdf.parse(inuput_dt);//ת���ɹ���Date����
	    Long time  = dt.getTime();//����Ǿ���1970��1��1��0��0��0��ĺ�����
	    System.out.println(time);
	    Long time2 = System.currentTimeMillis();//���������ͬ,��ȡϵͳ��ǰʱ�������
	    System.out.println(time2);
	    System.out.println(time2-time);//�������ʱ���뵱ǰʱ���ʱ������Ǻ�����

	    int year=0; 
	    int month=0; 
	    int day=0; 
	    Calendar c=Calendar.getInstance();//���ϵͳ��ǰ���� 
	    year=c.get(Calendar.YEAR); 
	    month=c.get(Calendar.MONTH)+1;//ϵͳ���ڴ�0��ʼ���� 
	    day=c.get(Calendar.DAY_OF_MONTH);  
	    System.out.println("��ǰϵͳʱ�䣺"+year+"��"+month+"��"+day+"��");//

	}
	
}
