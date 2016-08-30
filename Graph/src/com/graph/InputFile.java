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
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
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
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
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
