package com.pick_up;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class Possion {
	private static Map<Integer,double[]> map_aih = new HashMap<Integer, double[]>();//���ÿ���۴ص�������
	private static Map<Integer,double[]> map_car = new HashMap<Integer, double[]>();//���ÿ���۴ص㳵����������
	private static Map<Integer,double[]> map_people = new HashMap<Integer, double[]>();//���ÿ���۴ص��˵���������
	private static Map<Integer,double[]> map_waittime = new HashMap<Integer, double[]>();//���ÿ���۴ص�ÿ��ʱ��εĵȴ�ʱ��
	private static int[] array = {10,20,30,40,50,60};//���ƽ����һ��Сʱ��ȡ�õ���������ʱ��
	
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
		//��������������ȴ洢��map_aih��
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
		
		//��������Ŀճ��������ʴ洢��map_car��
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
		
		//����������˵������ʴ洢��map_people��
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
					//Ŀǰ���ֵ������ǣ��������̫С����ʹ����õĵȴ�ʱ��ϴ�
					//�˵������복���������̫�󣬻�ʹ�ú�ʱ�����ӣ����Բμ���13���۴��е�2,3����ʱ���
					if(map_car.get(key1)[i] > map_people.get(key1)[i]){//�������ʴ����˵�����
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
					}else{//�˵����ʴ��ڳ�������
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
