package com.graph;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class InputFile {
	
	public static void readTxtFile(String filePath){
        try {
        		int count = 1;
                String encoding="UTF-8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//���ǵ������ʽ
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    
                    while((lineTxt = bufferedReader.readLine()) != null && count <= 10){
                        //System.out.println(lineTxt);
                    	count++;
                    	String[] arr =  lineTxt.split(",");
                    	sumNumber(arr);
                    	
                    	/*System.out.println(arr[0]);
                    	System.out.println(arr[1]);
                    	System.out.println(arr[2]);*/
                    	//System.out.println(java.util.Arrays.toString(arr));
                    	/*if(lineTxt.){
                    		
                    	}*/
                    }
                    read.close();
        }else{
            System.out.println("�Ҳ���ָ�����ļ�");
        }
        } catch (Exception e) {
            System.out.println("��ȡ�ļ����ݳ���");
            e.printStackTrace();
        }
     
    }
     
    private static void sumNumber(String[] arr) {
		// TODO Auto-generated method stub
    	
		
	}

	public static void main(String argv[]){
        String filePath = "F:\\dataset1\\gd_train_data.txt";
//      "res/";
        readTxtFile(filePath);
    }

}
