package com.pick_up;

import java.io.File;
import java.io.RandomAccessFile;

//�����������ؿ�״̬Ϊ1�ĵ���ȡ����
public class Get_1_point {
	public static void main(String[] args) throws Exception {
		File fmax=new File("F:"+File.separator+"point_include_01state_sort.txt");
		RandomAccessFile Fmax=null;
		Fmax=new RandomAccessFile(fmax,"r");
		
		File f=new File("F:"+File.separator+"point_include_1.txt");
		RandomAccessFile F=null;
		F=new RandomAccessFile(f,"rw");
		
		String a = Fmax.readLine();
		while(a!=null){
			String[] soureStr = a.split(",");
			if(Integer.parseInt(soureStr[soureStr.length-2])==1){
				F.writeBytes(a);
				F.writeBytes("\n");
			}
			a = Fmax.readLine();
		}
		Fmax.close();
		F.close();
	}
}
