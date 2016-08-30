package com.bp;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class BpDeepTest2{
    public static void main(String[] args) throws Exception{
        //初始化神经网络的基本配置
        //第一个参数是一个整型数组，表示神经网络的层数和每层节点数，比如{3,10,10,10,10,2}表示输入层是3个节点，输出层是2个节点，中间有4层隐含层，每层10个节点
        //第二个参数是学习步长，第三个参数是动量系数
        BpDeep bp = new BpDeep(new int[]{2,10,10,10,10,10,1}, 0.01, 0.1);

        
        //设置样本数据，对应上面的4个二维坐标数据
        double[][] data = new double[48][2];
        //设置目标数据，对应4个坐标数据的分类
        double[][] target = new double[48][1];
        
        File ff = new File("E:"+File.separator+"result1.txt");
		RandomAccessFile FF=null;
		FF=new RandomAccessFile(ff,"r");
		String str = new String(FF.readLine());
		int m = 0;//行
		while(str!=null){
			int n = 0;//列
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
        

        //迭代训练5000次
        for(int n=0;n<5000;n++)
            for(int i=0;i<data.length;i++)
                bp.train(data[i], target[i]);

        //根据训练结果来检验样本数据
        for(int j=0;j<data.length;j++){
            double[] result = bp.computeOut(data[j]);
            System.out.println(Arrays.toString(data[j])+":"+Arrays.toString(result));
        }

        //根据训练结果来预测一条新数据的分类
        double[] x = new double[]{0.26,2};
        double[] result = bp.computeOut(x);
        System.out.println(Arrays.toString(x)+":"+Arrays.toString(result));
    }
}