package com.pick_up;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//将包含点较多的前1000个聚簇提取出来
public class Get_Point_1000 {

	public static int num = 1000;// 需要的点的个数
	
	// 读取每一行最后一个数据聚簇号，并得到他出现的次数
	public static List<Map.Entry<Integer, Integer>> fre_cluster() throws Exception {
		Map<Integer, Integer> map_cluster = new HashMap<Integer, Integer>();
		int key = 0;

		File fmax = new File("F:" + File.separator + "outmax.txt");
		RandomAccessFile Fmax = null;
		Fmax = new RandomAccessFile(fmax, "rw");
		String a = new String(Fmax.readLine());
		while (a != null) {
			// 把获取的聚簇号设为key值，value为出现的次数			
			String[] sourceStrArray = a.split(",");
			key = Integer.parseInt(sourceStrArray[sourceStrArray.length - 1]);
			//如果是离群点就跳出循环
			if(key == -2){
				break;
			}
			if (map_cluster.containsKey(key)) {
				map_cluster.put(key, map_cluster.get(key) + 1);
			} else {
				map_cluster.put(key, 1);
			}
			a = Fmax.readLine();
		}
		Fmax.close();

		// 对map_cluster按value值进行排序，放在list_Data中
		List<Map.Entry<Integer, Integer>> list_Data = new ArrayList<Map.Entry<Integer, Integer>>(
				map_cluster.entrySet());
		Collections.sort(list_Data, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				if (o2.getValue() != null && o1.getValue() != null && o2.getValue().compareTo(o1.getValue()) > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		});

		return list_Data;
	}

	public static void getPoint(List<Map.Entry<Integer, Integer>> list_Data) throws Exception {

		// 将List中的数据转为map,并拿到前num个所需要的值
		Map<Integer, Integer> map_temp = new HashMap<Integer, Integer>();
		int i = 0;
		for (Map.Entry<Integer, Integer> l : list_Data) {
			map_temp.put(l.getKey(), l.getValue());
			i++;
			if (i >= num)
				break;
		}

		//提取前1000个聚簇，并写入到文件中
		int key = 0;		

		File fmax = new File("F:" + File.separator + "outmax.txt");
		RandomAccessFile Fm = null;
		Fm = new RandomAccessFile(fmax, "rw");
		
		File file=new File("F:"+File.separator+"point_test.txt");
		RandomAccessFile Fmax = null;
		Fmax = new RandomAccessFile(file,"rw");
		
		String b = new String(Fm.readLine());
		while (b != null) {
			String[] sourceStrArray = b.split(",");
			key = Integer.parseInt(sourceStrArray[sourceStrArray.length - 1]);
			// 如果前num个点包含读到的值则执行if
			if (map_temp.containsKey(key)) {
				Fmax.writeBytes(b);
				Fmax.writeBytes("\n");
			} else {
				b = Fm.readLine();
			}
			b = Fm.readLine();
		}
		Fm.close();
		Fmax.close();
	}


	public static void main(String[] args) throws Exception {
		getPoint(fre_cluster());
		System.out.println("main is end");
	}

}
