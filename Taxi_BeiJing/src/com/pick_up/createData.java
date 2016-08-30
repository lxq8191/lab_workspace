package com.pick_up;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

//将数据写成最终数据库表的形式
public class createData {
	private static Map<Integer,double[]> map_aih = new HashMap<Integer, double[]>();//存放每个聚簇的吸引度
	private static Map<Integer,double[]> map_car = new HashMap<Integer, double[]>();//存放每个聚簇点车到来的速率
	private static Map<Integer,double[]> map_people = new HashMap<Integer, double[]>();//存放每个聚簇点人到来的速率
	private static Map<Integer,double[]> map_xy = new HashMap<Integer, double[]>();//存放每个聚簇点的经纬度
	
	public static void waitTime() throws Exception{
		//将计算出的吸引度存储到map_aih中
		double[] arr = new double[24];
		int clusterid = 0;
		File ff = new File("E:"+File.separator+"attraction_1000.txt");
		RandomAccessFile FF=null;
		FF=new RandomAccessFile(ff,"r");
		String str = new String(FF.readLine());
		while(str!=null){
			arr = new double[24];
			String[] source = str.split(",");
			clusterid = Integer.parseInt(source[0]);
			for (int i = 1; i < source.length; i++) {
				arr[i-1] = Double.parseDouble(source[i]);
			}
			map_aih.put(clusterid, arr);
			str = FF.readLine();
		}
		FF.close();
		
		//将计算出的空车到达速率存储到map_car中
		double[] arr1 = new double[24];
		int clusterid1 = 0;
		File fmax=new File("E:"+File.separator+"rate_car.txt");
		RandomAccessFile Fmax=null;
		Fmax=new RandomAccessFile(fmax,"r");
		String aa = Fmax.readLine();
		while(aa!=null){
			arr1 = new double[24];
			String[] sourceStr = aa.split(",");
			clusterid1 = Integer.parseInt(sourceStr[0]);
			for (int i = 1; i < sourceStr.length; i++) {
				arr1[i-1] = Double.parseDouble(sourceStr[i]);
			}
			map_car.put(clusterid1, arr1);
			aa = Fmax.readLine();
		}
		Fmax.close();
		
		//将计算出的人到达速率存储到map_people中
		double[] arr2 = new double[24];
		int clusterid2 = 0;
		File f=new File("E:"+File.separator+"rate_people.txt");
		RandomAccessFile F=null;
		F=new RandomAccessFile(f,"r");
		String bb = F.readLine();
		while(bb!=null){
			arr2 = new double[24];
			String[] soureStrArr = bb.split(",");
			clusterid2 = Integer.parseInt(soureStrArr[0]);
			for (int i = 1; i < soureStrArr.length; i++) {
				arr2[i-1] = Double.parseDouble(soureStrArr[i]);
			}
			map_people.put(clusterid2, arr2);
			bb = F.readLine();
		}
		F.close();

		double[] xy = new double[2];
		int id = 0;
		File fff=new File("E:"+File.separator+"XY_1000.txt");
		RandomAccessFile FFF=null;
		FFF=new RandomAccessFile(fff,"r");
		String ss = FFF.readLine();
		while(ss!=null){
			xy = new double[2];
			String[] soureStrArray = ss.split(",");
			id = Integer.parseInt(soureStrArray[2]);
			for (int i = 0; i < 2; i++) {
				xy[i] = Double.parseDouble(soureStrArray[i]);
			}
			map_xy.put(id, xy);
			ss = FFF.readLine();
		}
		FFF.close();
		
		File file=new File("E:"+File.separator+"output_result.txt");
		RandomAccessFile Fr=null;
		Fr=new RandomAccessFile(file,"rw");
		int n = 0;
		for(Integer key1 : map_people.keySet()){
			for (int i = 0; i < 24; i++) {
				Fr.writeBytes(++n+","+map_xy.get(key1)[0]+","+map_xy.get(key1)[1]+","+i
						+","+map_car.get(key1)[i]+","+map_people.get(key1)[i]+","
							+map_aih.get(key1)[i]+"\n");
			}
		}
		Fr.close();
	}
	
	public static void main(String[] args) throws Exception {
		waitTime();
	}

}
