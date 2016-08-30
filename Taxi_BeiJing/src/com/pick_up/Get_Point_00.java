package com.pick_up;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//将符合某一聚簇范围内的00转换点提取出来，并且排除了连续停留在一个点上超过6分钟的车，这个程序目前没使用
public class Get_Point_00 {
	public static Map<Integer, double[]> map_Range = new HashMap<Integer, double[]>();
	public static Map<Integer, double[]> map_XY = new HashMap<Integer, double[]>();
	public static double time_span = 600;//时间间隔
	
	//计算两点间的距离
	private static double getDistance(double lon3,double lat3,double lon,double lat)
	{
		double lat1 = (Math.PI/180)*lat3;  
	    double lat2 = (Math.PI/180)*lat;	          
	    double lon1 = (Math.PI/180)*lon3;  
	    double lon2 = (Math.PI/180)*lon;  
	          
	    //地球半径  
	    double R1 = 6371;  
	          
	    //两点间距离 km，如果想要米的话，结果*1000就可以了  
	    double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R1;  	          
	    return d*1000;
	}
	
	//将聚簇中心点存放的map_XY中，key为聚簇号，value为经纬度
	private static void getXY() throws Exception{
		double lon;//经度
		double lat;//纬度
		int key = 0;
		File file = new File("F:" + File.separator + "test.txt");
		RandomAccessFile Fmax = null;
		Fmax = new RandomAccessFile(file,"rw");
		String b = new String(Fmax.readLine());
		while (b != null) {
			String[] sourceStrArray = b.split(",");
			lon = Double.parseDouble(sourceStrArray[0]);
			lat = Double.parseDouble(sourceStrArray[1]);
			key = Integer.parseInt(sourceStrArray[sourceStrArray.length - 1]);
			double[] arr_temp = new double[2];
			arr_temp[0] = lon;
			arr_temp[1] = lat;
			map_XY.put(key, arr_temp);
			b = Fmax.readLine();
		}
		Fmax.close();
	}
	
	private static Map<Integer, double[]> getRange() throws Exception{//获得每个聚簇经纬度范围
		//将范围存入map_Range，key是聚簇号，value:前两个数组的数存经度范围，第三四个数存纬度范围
		
		double lon;//经度
		double lat;//纬度
		int key = 0;
		int key_temp = 0;
		double max_lon = 0;
		double min_lon = 0;
		double max_lat = 0;
		double min_lat = 0;
		
		//读取前1000个聚簇
		File file = new File("F:" + File.separator + "point_test.txt");
		RandomAccessFile Fmax = null;
		Fmax = new RandomAccessFile(file,"rw");
		String b = new String(Fmax.readLine());
		while (b != null) {
			String[] sourceStrArray = b.split(",");
			lon = Double.parseDouble(sourceStrArray[2]);
			lat = Double.parseDouble(sourceStrArray[3]);
			key = Integer.parseInt(sourceStrArray[sourceStrArray.length - 1]);
			if (key_temp == 0) {
				key_temp = key;
				max_lon = min_lon = lon;
				max_lat = min_lat = lat;
			}else{
				if(key_temp==key){
					if(lon > max_lon){
						max_lon = lon;
					}
					if(lon < min_lon){
						min_lon = lon;
					}
					if(lat > max_lat){
						max_lat = lat;
					}
					if(lat < min_lat){
						min_lat = lat;
					}
				}
				else{
					double[] arr_temp = new double[4];
					arr_temp[0] = max_lon;
					arr_temp[1] = min_lon;
					arr_temp[2] = max_lat;
					arr_temp[3] = min_lat;					
					map_Range.put(key_temp, arr_temp);
					key_temp = key;
					
				}
			}
			b = Fmax.readLine();
		}
		//最后一个聚簇
		double[] arr_temp = new double[4];
		arr_temp[0] = max_lon;
		arr_temp[1] = min_lon;
		arr_temp[2] = max_lat;
		arr_temp[3] = min_lat;					
		map_Range.put(key_temp, arr_temp);
		Fmax.close();
		
		return map_Range;
	}
	
	private static int getNO(StringBuffer a)//得到出租车ID位数
	{
		int m=0;
		for(;m<7;m++)
		{
			if(a.charAt(m)==',')
				break;
		}
		return m;
	}
	
	private static int getnumber(StringBuffer a)//得到出租车ID
	{
		return(Integer.valueOf(a.substring(0, getNO(a))));
	}
	
	private static int gettime(StringBuffer a)//得到时间
	{
		return(Integer.valueOf(a.substring(12+getNO(a), 14+getNO(a)))*3600+
				Integer.valueOf(a.substring(15+getNO(a), 17+getNO(a)))*60+
				Integer.valueOf(a.substring(18+getNO(a), 20+getNO(a))));
	}
	
	//查找符合经纬度范围的0/0转换点
	public static ArrayList<Point> changeZToZ() throws Exception{
		double longitude;
		double latitude;
		double longi;
		double lati;
		double time = 0;
		File file = new File("F:\\Point_add.txt");
		RandomAccessFile Fm = null;
		Fm = new RandomAccessFile(file, "rw");
		
		System.out.println("createData is begin");
		ArrayList<Point> data = new ArrayList<Point>();
		File ff = new File("F:\\Taxi");//文件路径
		File[] files = ff.listFiles();
		for(int i=0;i<files.length;i++)
		{
			time = 0;
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
					StringBuffer bb = new StringBuffer(aa);
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(53)=='0')
						{
							//如果下一行aa和上一行bb，出租车ID相同，经纬度相同，时间间隔小于10分钟（600秒），则不加入
							if(getnumber(aa)==getnumber(bb)&&Double.parseDouble(aa.substring(24,32))==Double.parseDouble(bb.substring(24,32))
									&&Double.parseDouble(aa.substring(35,43))==Double.parseDouble(bb.substring(35,43))&&time < time_span){
								b=aa.charAt(53);
								time = time + gettime(aa)-gettime(bb);
							}else{
								time = 0;
								//遍历整个map_Range，查找经纬度在已有范围内的0/0转换点，若找到，则break，接着找下一行
								for (Integer key1 : map_Range.keySet()) {
									longitude = Double.valueOf(aa.substring(24, 32));//每个符合条件的0/0转换点的经纬度
						            latitude = Double.valueOf(aa.substring(35, 43));
						            longi = map_XY.get(key1)[0];//每个聚类中心点的经纬度
					            	lati = map_XY.get(key1)[1];			            
						            if(longitude < map_Range.get(key1)[0] && longitude > map_Range.get(key1)[1]
						            		&& latitude < map_Range.get(key1)[2] && latitude > map_Range.get(key1)[3]
						            				&& getDistance(longitude, latitude, longi, lati)<=500){
						            	//当读到的该点满足0/0转换，并且经纬度在一定范围，离某聚簇中心点的距离小于等于500米，则添加该点
						            	Fm.writeBytes(String.valueOf(getnumber(aa)));
						            	Fm.writeBytes(",");
						            	Fm.writeBytes(String.valueOf(gettime(aa)));
						            	Fm.writeBytes(",");
						            	Fm.writeBytes(aa.substring(24, 32));
						            	Fm.writeBytes(",");
						            	Fm.writeBytes(aa.substring(35, 43));
						            	Fm.writeBytes(",");
						            	Fm.writeBytes(String.valueOf(key1));
						            	Fm.writeBytes("\n");
						            	break;
						            }
								}
//								data.add(new Point(Double.valueOf(aa.substring(24, 32)),
//										Double.valueOf(aa.substring(35, 43)),getnumber(aa),gettime(aa)));
								b=aa.charAt(53);
							}
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
					StringBuffer bb = new StringBuffer(aa);
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(54)=='0')
						{
							if(getnumber(aa)==getnumber(bb)&&Double.parseDouble(aa.substring(25,33))==Double.parseDouble(bb.substring(25,33))
									&&Double.parseDouble(aa.substring(36,44))==Double.parseDouble(bb.substring(36,44))&&time < time_span){
								b=aa.charAt(54);
								time = time + gettime(aa)-gettime(bb);
							}else{
								time = 0;
							//遍历整个map_Range，查找经纬度在已有范围内的0/0转换点，若找到，则break，接着找下一行
							for (Integer key1 : map_Range.keySet()) {
								longitude = Double.valueOf(aa.substring(25, 33));
					            latitude = Double.valueOf(aa.substring(36, 44));
					            longi = map_XY.get(key1)[0];//每个聚类中心点的经纬度
				            	lati = map_XY.get(key1)[1];			            
					            if(longitude < map_Range.get(key1)[0] && longitude > map_Range.get(key1)[1]
					            		&& latitude < map_Range.get(key1)[2] && latitude > map_Range.get(key1)[3]
					            				&& getDistance(longitude, latitude, longi, lati)<=500){
					            	//当读到的该点满足0/0转换，并且经纬度在一定范围，离某聚簇中心点的距离小于等于500米，则添加该点
					            	Fm.writeBytes(String.valueOf(getnumber(aa)));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(String.valueOf(gettime(aa)));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(aa.substring(25, 33));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(aa.substring(36, 44));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(String.valueOf(key1));
					            	Fm.writeBytes("\n");
					            	break;
					            }
							}
//							data.add(new Point(Double.valueOf(aa.substring(25, 33)),
//									Double.valueOf(aa.substring(36, 44)),getnumber(aa),gettime(aa)));
							b=aa.charAt(54);
							}
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
					StringBuffer bb = new StringBuffer(aa);
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(55)=='0')
						{
							if(getnumber(aa)==getnumber(bb)&&Double.parseDouble(aa.substring(26,34))==Double.parseDouble(bb.substring(26,34))
									&&Double.parseDouble(aa.substring(37,45))==Double.parseDouble(bb.substring(37,45))&&time < time_span){
								b=aa.charAt(55);
								time = time + gettime(aa)-gettime(bb);
							}else{
								time = 0;
							//遍历整个map_Range，查找经纬度在已有范围内的0/0转换点，若找到，则break，接着找下一行
							for (Integer key1 : map_Range.keySet()) {
								longitude = Double.valueOf(aa.substring(26, 34));
					            latitude = Double.valueOf(aa.substring(37, 45));
					            longi = map_XY.get(key1)[0];//每个聚类中心点的经纬度
				            	lati = map_XY.get(key1)[1];			            
					            if(longitude < map_Range.get(key1)[0] && longitude > map_Range.get(key1)[1]
					            		&& latitude < map_Range.get(key1)[2] && latitude > map_Range.get(key1)[3]
					            				&& getDistance(longitude, latitude, longi, lati)<=500){
					            	//当读到的该点满足0/0转换，并且经纬度在一定范围，离某聚簇中心点的距离小于等于500米，则添加该点
					            	Fm.writeBytes(String.valueOf(getnumber(aa)));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(String.valueOf(gettime(aa)));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(aa.substring(26, 34));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(aa.substring(37, 45));
					            	Fm.writeBytes(",");
					            	Fm.writeBytes(String.valueOf(key1));
					            	Fm.writeBytes("\n");
					            	break;
					            }
							}
//							data.add(new Point(Double.valueOf(aa.substring(26, 34)),
//									Double.valueOf(aa.substring(37, 45)),getnumber(aa),gettime(aa)));
							b=aa.charAt(55);
						}
						}
					}
					else if(b=='1'){
						b = aa.charAt(55);
					}
				}
			}
		}
		System.out.println("createData is end");
		
		return data;
	}
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		getXY();
		getRange();
		changeZToZ();
		long end = System.currentTimeMillis();
		System.out.println("time = "+(end-start)/1000/60+" mins");
	}

}
