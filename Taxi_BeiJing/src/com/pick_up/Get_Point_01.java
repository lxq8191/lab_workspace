package com.pick_up;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

//将符合某一聚簇范围内的01转换点提取出来，这个程序目前没使用
public class Get_Point_01 {
	public static Map<Integer, double[]> map_Range = new HashMap<Integer, double[]>();
	public static Map<Integer, double[]> map_list = new HashMap<Integer,double[]>();
	
	//将前1000个聚簇中的点存入map_list中，double[]中存放经纬度，Integer存储聚簇号
	public static void getPointList() throws Exception{
		
		double lon;
		double lat;
		double num;
		int key = 0;
		File file1 = new File("F:" + File.separator + "point_test.txt");
		RandomAccessFile Fmax = null;
		Fmax = new RandomAccessFile(file1,"rw");
		String a = new String(Fmax.readLine());
		while(a!=null){
			String[] soure = a.split(",");
			lon = Double.parseDouble(soure[2]);//经度
			lat = Double.parseDouble(soure[3]);//纬度
			num = Double.parseDouble(soure[4]);//聚簇号
			double[] arr = new double[3];
			arr[0] = lon;
			arr[1] = lat;
			arr[2] = num;
			map_list.put(key,arr);
			key++;
			a = Fmax.readLine();
		}
		Fmax.close();
		System.out.println("end");
		Iterator<Entry<Integer, double[]>> it = map_list.entrySet().iterator();
		while(it.hasNext()){
			Entry<?, ?> ex = (Entry<?, ?>) it.next();	
    		System.out.println(ex.getKey()+","+ex.getValue());
    		System.out.println();
    	}	
	}
	
	private static int getNO(StringBuffer a)//得到出租车ID
	{
		int m=0;
		for(;m<7;m++)
		{
			if(a.charAt(m)==',')
				break;
		}
		return m;
	}
	
	private static int getnumber(StringBuffer a)
	{
		return(Integer.valueOf(a.substring(0, getNO(a))));
	}
	
	private static int gettime(StringBuffer a)
	{
		return(Integer.valueOf(a.substring(12+getNO(a), 14+getNO(a)))*3600+
				Integer.valueOf(a.substring(15+getNO(a), 17+getNO(a)))*60+
				Integer.valueOf(a.substring(18+getNO(a), 20+getNO(a))));
	}
	
	//查找符合经纬度范围的0/0转换点
	public static ArrayList<Point> changeZToO() throws Exception{
		double longitude;
		double latitude;
		File file = new File("F:\\Point_01.txt");
		RandomAccessFile Fm = null;
		Fm = new RandomAccessFile(file, "rw");
		
		
		
		System.out.println("changeZToO is begin");
		ArrayList<Point> data = new ArrayList<Point>();
		File ff = new File("F:\\Taxi1");//文件路径
		File[] files = ff.listFiles();
		for(int i=0;i<files.length;i++)
		{
			//System.out.println(i);
			RandomAccessFile F=null;
			//RandomAccessFile F[]=new RandomAccessFile[100];
			F=new RandomAccessFile(files[i],"r");
			StringBuffer aa;
			aa= new StringBuffer(F.readLine());
//			System.out.println(aa);
//			System.out.println(aa.length());
			//System.out.println(getNO(aa));
			if (getNO(aa)==3)
			{
				char b='0';
				b=aa.charAt(53);
				String thisline=null;
				for(;(thisline=F.readLine())!=null;)
				{
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(53)=='1')
						{
								longitude = Double.valueOf(aa.substring(24, 32));
					            latitude = Double.valueOf(aa.substring(35, 43));
					            double[] array = new double[2];
					            array[0] = longitude;
					            array[1] = latitude;
					            if(map_list.containsKey(array)){
					            	Fm.writeBytes(String.valueOf(getnumber(aa)));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(String.valueOf(gettime(aa)));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(aa.substring(24, 32));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(aa.substring(35, 43));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(String.valueOf(map_list.get(array)));
					            	Fm.writeBytes("\n");
					            	break;
					            }					            	
//							data.add(new Point(Double.valueOf(aa.substring(24, 32)),
//									Double.valueOf(aa.substring(35, 43)),getnumber(aa),gettime(aa)));
							b=aa.charAt(53);
						}
					}
					else if(b=='1'){
						b = aa.charAt(53);
					}
					
				}
			}
			else if (getNO(aa)==4)
			{
				char b='0';
				b=aa.charAt(54);
				String thisline=null;
				for(;(thisline=F.readLine())!=null;)
				{
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(54)=='1')
						{							
								longitude = Double.valueOf(aa.substring(25, 33));
					            latitude = Double.valueOf(aa.substring(36, 44));
					            double[] array = new double[2];
					            array[0] = longitude;
					            array[1] = latitude;
					            if(map_list.containsKey(array)){
					            	Fm.writeBytes(String.valueOf(getnumber(aa)));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(String.valueOf(gettime(aa)));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(aa.substring(25, 33));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(aa.substring(36, 44));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(String.valueOf(map_list.get(array)));
					            	Fm.writeBytes("\n");
					            	break;
					            }
//							data.add(new Point(Double.valueOf(aa.substring(25, 33)),
//									Double.valueOf(aa.substring(36, 44)),getnumber(aa),gettime(aa)));
							b=aa.charAt(54);
							
						}
					}
					else if(b=='1'){
						b = aa.charAt(54);
					}
					
				}
			}
			else if (getNO(aa)==5)
			{
				char b='0';
				b=aa.charAt(55);
				String thisline=null;
				for(;(thisline=F.readLine())!=null;)
				{
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(55)=='0')
						{
								longitude = Double.valueOf(aa.substring(26, 34));
					            latitude = Double.valueOf(aa.substring(37, 45));
					            double[] array = new double[2];
					            array[0] = longitude;
					            array[1] = latitude;
					            if(map_list.containsKey(array)){
					            	Fm.writeBytes(String.valueOf(getnumber(aa)));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(String.valueOf(gettime(aa)));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(aa.substring(26, 34));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(aa.substring(37, 45));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(String.valueOf(map_list.get(array)));
					            	Fm.writeBytes("\n");
					            	break;
					            }
//							data.add(new Point(Double.valueOf(aa.substring(26, 34)),
//									Double.valueOf(aa.substring(37, 45)),getnumber(aa),gettime(aa)));
							b=aa.charAt(55);
						}
					}
					else if(b=='1'){
						b = aa.charAt(55);
					}
				}
			}
		}
		System.out.println("changeZToO is end");
		Fm.close();
		return data;
	}
	
	public static void main(String[] args) throws Exception {
		getPointList();
		changeZToO();
	}

}
