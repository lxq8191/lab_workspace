package com.pick_up;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

//�����˵��������
public class Rate_people {
//	private static List<Map.Entry<Integer, Integer>> list_Data = new ArrayList<Map.Entry<Integer, Integer>>();
	private static Map<Integer, int[]> map_cluster = new HashMap<Integer, int[]>();//���ʱ�������Լ�ÿ��ʱ������
																				//int[3]�ֱ�������ʱ���ͨ���ĳ������������ȵ����ʱ�䣬��󵽴��ʱ��
	
	public static void main(String[] args) throws Exception {
		int timeslot = 0;
		int clusterid = 0, clusterid_temp = 0;
		int time = 0;
		int[] arr = new int[3]; 
		
		DecimalFormat df = new DecimalFormat("0.0000000");
		
		File fmax=new File("F:"+File.separator+"point_include_1.txt");
		RandomAccessFile Fmax=null;
		Fmax=new RandomAccessFile(fmax,"r");
		
		FileWriter f=new FileWriter("F:"+File.separator+"rate_people.txt");
		
		String aa= new String(Fmax.readLine());
		while(aa!=null){
			
			String[] sourceStr = aa.split(",");
			time = Integer.parseInt(sourceStr[1]);
			timeslot = Integer.parseInt(sourceStr[1])/3600;
			clusterid = Integer.parseInt(sourceStr[5]);
			
			if (clusterid_temp == 0) {	//����ʼ����һ��ʱ
				clusterid_temp = clusterid;
				arr[0] = arr[0] + 1;
				arr[1] = time;
				arr[2] = time;
				map_cluster.put(timeslot, arr);
			} else { 
				if (clusterid_temp == clusterid) {	// �������ľ۴غ�û�б�ʱ
						if(map_cluster.containsKey(timeslot)){
							arr = new int[3];//������仰��ʹ��ÿ�����¿���һ�����飬�����ܽ��һ���۴�ÿ��ʱ���ֵ��һ���Ĵ���
							arr[0] = map_cluster.get(timeslot)[0] + 1;
							if(map_cluster.get(timeslot)[1] > time){
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
				} else { // �������ľ۴غű���
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
					//��Ϊ�������۴غű仯ʱ��������ǵ�ǰ��֮ǰ�����ݣ����Ե�ǰ�в�û�н��д����ʽ���ǰ����map_cluster���֮�����
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
		int[] array = new int[3]; 
		for (int i = 0; i < 24; i++) {
			if(!map_cluster.containsKey(i)){
				map_cluster.put(i, array);
			}
		}
		for (Integer key1 : map_cluster.keySet()) {
//			System.out.print(key1 + " ");
			double rate = 0.0;
			if(map_cluster.get(key1)[0]==0){
				rate = 0.0;
			}
			else{
				if((map_cluster.get(key1)[2]-map_cluster.get(key1)[1])==0){
					rate = Double.parseDouble(df.format(map_cluster.get(key1)[0]/60.0));
						
				}else{
						rate = Double.parseDouble(df.format(60.0 * map_cluster.get(key1)[0]/(map_cluster.get(key1)[2]-map_cluster.get(key1)[1])));
					}
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
