package com.bp;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class BpDeepTest2{
    public static void main(String[] args) throws Exception{
        //��ʼ��������Ļ�������
        //��һ��������һ���������飬��ʾ������Ĳ�����ÿ��ڵ���������{3,10,10,10,10,2}��ʾ�������3���ڵ㣬�������2���ڵ㣬�м���4�������㣬ÿ��10���ڵ�
        //�ڶ���������ѧϰ�����������������Ƕ���ϵ��
        BpDeep bp = new BpDeep(new int[]{2,10,10,10,10,10,1}, 0.01, 0.1);

        
        //�����������ݣ���Ӧ�����4����ά��������
        double[][] data = new double[48][2];
        //����Ŀ�����ݣ���Ӧ4���������ݵķ���
        double[][] target = new double[48][1];
        
        File ff = new File("E:"+File.separator+"result1.txt");
		RandomAccessFile FF=null;
		FF=new RandomAccessFile(ff,"r");
		String str = new String(FF.readLine());
		int m = 0;//��
		while(str!=null){
			int n = 0;//��
			String[] source = str.split(",");
			data[m][n] = Double.parseDouble(source[0]);
			target[m][n] = Double.parseDouble(source[2]);
			n++;
			data[m][n] = Double.parseDouble(source[1]);
			m++;
			str = FF.readLine();
		}
		FF.close();
		
//		for (int i = 0; i < data.length; i++) {
//			for (int j = 0; j < data[i].length; j++) {
//				System.out.print(data[i][j]+",");
//			}
//			System.out.println();
//		}
//		for (int i = 0; i < target.length; i++) {
//			System.out.print(target[i][0]+",");
//		}
        

        //����ѵ��5000��
        for(int n=0;n<5000;n++)
            for(int i=0;i<data.length;i++)
                bp.train(data[i], target[i]);

        //����ѵ�������������������
        for(int j=0;j<data.length;j++){
            double[] result = bp.computeOut(data[j]);
            System.out.println(Arrays.toString(data[j])+":"+Arrays.toString(result));
        }

        //����ѵ�������Ԥ��һ�������ݵķ���
        double[] x = new double[]{0.26,2};
        double[] result = bp.computeOut(x);
        System.out.println(Arrays.toString(x)+":"+Arrays.toString(result));
    }
}