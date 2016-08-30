package com.pick_up;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class Possion {
	private static Map<Integer,double[]> map_aih = new HashMap<Integer, double[]>();//存放每个聚簇的吸引度
	private static Map<Integer,double[]> map_car = new HashMap<Integer, double[]>();//存放每个聚簇点车到来的速率
	private static Map<Integer,double[]> map_people = new HashMap<Integer, double[]>();//存放每个聚簇点人到来的速率
	private static Map<Integer,double[]> map_waittime = new HashMap<Integer, double[]>();//存放每个聚簇点每个时间段的等待时间
	private static int[] array = {10,20,30,40,50,60};//存放平均在一个小时内取得的六个测试时间
	
	private static int getPossionVariable(double lamda) {
		int x = 0;
//		double y = Math.random();
		double y = 0.5;
		double cdf = getPossionProbability(x, lamda);
		while (cdf < y) {
			x++;
			cdf += getPossionProbability(x, lamda);
		}
		return x;
	}

	private static double getPossionProbability(int k, double lamda) {
		double c = Math.exp(-lamda), sum = 1;
		for (int i = 1; i <= k; i++) {
			sum *= lamda / i;
		}
		return sum * c;
	}
	
	public static void waitTime() throws Exception{
		//将计算出的吸引度存储到map_aih中
		double[] arr = new double[24];
		int clusterid = 0;
		File ff = new File("E:"+File.separator+"attraction_test.txt");
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
		File fmax=new File("E:"+File.separator+"rate_car_test.txt");
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
		File f=new File("E:"+File.separator+"rate_people_test.txt");
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
//		for (Integer key2 : map_aih.keySet()) {
//			for (int i = 0; i < 24; i++) {
//				System.out.print(map_aih.get(key2)[i]+",");
//			}
//			System.out.println();
//		}
		
		File file=new File("E:"+File.separator+"output_test_result.txt");
		RandomAccessFile Fr=null;
		Fr=new RandomAccessFile(file,"rw");
		double result = 0.0;
		for(Integer key1 : map_aih.keySet()){
			for (int i = 0; i < 24; i++) {
					//目前出现的问题是：如果速率太小，会使得求得的等待时间较大
					//人的速率与车的速率相差太大，会使得候车时间增加，可以参见第13个聚簇中第2,3两个时间段
					if(map_car.get(key1)[i] > map_people.get(key1)[i]){//车的速率大于人的速率
						for (int j = 0; j < array.length; j++) {
							if(map_car.get(key1)[i] > 0.1){
								result = ((getPossionVariable(array[j]*map_car.get(key1)[i])+1)/map_car.get(key1)[i])-array[j];
								Fr.writeBytes(key1+","+i+","+array[j]+","+map_aih.get(key1)[i]+","+result);
								Fr.writeBytes("\n");
							}else{
								result = ((getPossionVariable(array[j]*map_car.get(key1)[i])+1)/map_car.get(key1)[i])-array[j];
								Fr.writeBytes(key1+","+i+","+array[j]+","+map_aih.get(key1)[i]+","+result/10);
								Fr.writeBytes("\n");
							}
							
						}
					}else{//人的速率大于车的速率
						for (int j = 0; j < array.length; j++) {
							if(map_car.get(key1)[i] > 0.1){
								result = ((getPossionVariable(array[j]*map_people.get(key1)[i])+1)/map_car.get(key1)[i])-array[j];
								Fr.writeBytes(key1+","+i+","+array[j]+","+map_aih.get(key1)[i]+","+result);
								Fr.writeBytes("\n");
							}else{
								result = ((getPossionVariable(array[j]*map_people.get(key1)[i])+1)/map_car.get(key1)[i])-array[j];
								Fr.writeBytes(key1+","+i+","+array[j]+","+map_aih.get(key1)[i]+","+result/10);
								Fr.writeBytes("\n");
							}
							
						}
				}
				
			}
		}
		Fr.close();
	}
	
	public static void main(String[] args) throws Exception {
//		System.out.println(getPossionVariable(1.8));
		waitTime();
	}

}
