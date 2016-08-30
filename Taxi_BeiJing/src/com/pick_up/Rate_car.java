package com.pick_up;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

//计算空车达到速率
public class Rate_car {
//	private static List<Map.Entry<Integer, Integer>> list_Data = new ArrayList<Map.Entry<Integer, Integer>>();
	private static Map<Integer, int[]> map_cluster = new HashMap<Integer, int[]>();
	
	public static void main(String[] args) throws Exception {
		int timeslot = 0;
		int clusterid = 0, clusterid_temp = 0;
		int time = 0;
		int[] arr = new int[3]; 
		
		DecimalFormat df = new DecimalFormat("0.0000000");
		
		File fmax=new File("F:"+File.separator+"point_include_01state_sort.txt");
		RandomAccessFile Fmax=null;
		Fmax=new RandomAccessFile(fmax,"r");
		
		FileWriter f=new FileWriter("F:"+File.separator+"rate_car.txt");
		
		String aa= new String(Fmax.readLine());
		while(aa!=null){
			
			String[] sourceStr = aa.split(",");
			time = Integer.parseInt(sourceStr[1]);
			timeslot = Integer.parseInt(sourceStr[1])/3600;
			clusterid = Integer.parseInt(sourceStr[5]);
			if (clusterid_temp == 0) {	//当开始读第一行时
				clusterid_temp = clusterid;
				arr[0] = arr[0] + 1;
				arr[1] = time;
				arr[2] = time;
				map_cluster.put(timeslot, arr);
			} else { 
				if (clusterid_temp == clusterid) {	// 当读到的聚簇号没有变时
						if(map_cluster.containsKey(timeslot)){
							arr = new int[3];//增加这句话，使得每次重新开辟一个数组，这样能解决一个聚簇每个时间段值都一样的错误
							arr[0] = map_cluster.get(timeslot)[0] + 1;
							if(map_cluster.get(timeslot)[1] >= time){
								arr[1] = time;
							}
							else{
								arr[1] = map_cluster.get(timeslot)[1];
							}
							if(map_cluster.get(timeslot)[2] < time){
								arr[2] = time;
							}
							else{
								arr[2] = map_cluster.get(timeslot)[2];
							}
							map_cluster.put(timeslot, arr);
						}
						else{
							arr = new int[3]; 
							arr[0] = 1;
							arr[1] = time;
							arr[2] = time;
							map_cluster.put(timeslot, arr);
						}
				} else { // 当读到的聚簇号变了
					f.write(clusterid_temp+"");
					f.write(",");
					int[] array1 = new int[3]; 
					for (int i = 0; i < 24; i++) {
						if(!map_cluster.containsKey(i)){
							map_cluster.put(i, array1);
						}
					}
					for (Integer key1 : map_cluster.keySet()) {
//						System.out.print(key1 + " ");
						double rate = 0.0;
						if((map_cluster.get(key1)[2]-map_cluster.get(key1)[1])==0){
							rate = Double.parseDouble(df.format(map_cluster.get(key1)[0]/60.0));
						}else{
							rate = Double.parseDouble(df.format(60.0 * map_cluster.get(key1)[0]/(map_cluster.get(key1)[2]-map_cluster.get(key1)[1])));
						}
						f.write(String.valueOf(rate));
						f.write(",");
//						for (int j = 0; j < map_cluster.get(key1).length; j++) {
//							System.out.print(map_cluster.get(key1)[j] + ",");
//						}
					}
					f.write("\n");
					clusterid_temp = clusterid;
					map_cluster.clear();
					//因为当读到聚簇号变化时，处理的是当前行之前的数据，所以当前行并没有进行处理，故将当前行在map_cluster清空之后放入
					arr = new int[3];
					arr[0] = 1;
					arr[1] = time;
					arr[2] = time;
					map_cluster.put(timeslot, arr);
				}
			} 
			aa = Fmax.readLine();
		}
		f.write(clusterid_temp+"");
		f.write(",");
		arr = new int[3]; 
		arr[0] = arr[0] + 1;
		arr[1] = time;
		arr[2] = time;
		map_cluster.put(timeslot, arr);
		int[] array = new int[3]; 
		for (int i = 0; i < 24; i++) {
			if(!map_cluster.containsKey(i)){
				map_cluster.put(i, array);
			}
		}
		for (Integer key1 : map_cluster.keySet()) {
//			System.out.print(key1 + " ");
			double rate = 0.0;
			if((map_cluster.get(key1)[2]-map_cluster.get(key1)[1])==0){
				rate = Double.parseDouble(df.format(map_cluster.get(key1)[0]/60.0));
			}else{
				rate = Double.parseDouble(df.format(60.0 * map_cluster.get(key1)[0]/(map_cluster.get(key1)[2]-map_cluster.get(key1)[1])));
			}
			f.write(String.valueOf(rate));
			f.write(",");
//			for (int j = 0; j < map_cluster.get(key1).length; j++) {
//				System.out.print(map_cluster.get(key1)[j] + ",");
//			}
//			System.out.println();
		}
		f.write("\n");
			
		Fmax.close();
		f.close();
	}
	
	

}
