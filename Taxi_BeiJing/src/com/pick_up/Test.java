package com.pick_up;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class Test {

	public static void main(String[] args) throws Exception {
		double lon1 = 121.5565;
		double lat1 =31.2525;
		double lon,lat;
		int key;
		int count = 0;
		File fmax=new File("F:"+File.separator+"point_test.txt");
		RandomAccessFile Fmax=null;
		Fmax=new RandomAccessFile(fmax,"rw");
		String b = new String(Fmax.readLine());
		while (b != null) {
			String[] sourceStrArray = b.split(",");
			lon = Double.parseDouble(sourceStrArray[2]);
			lat = Double.parseDouble(sourceStrArray[3]);
			key = Integer.parseInt(sourceStrArray[sourceStrArray.length - 1]);
			if(key == 2201){
				if(getDistance(lon1,lat1,lon,lat)<500){
					count++;
				}
			}
			b = Fmax.readLine();
		}
		System.out.println(count);

	}
	
	private static double getDistance(double lon3,double lat3,double lon,double lat)
	{
		double lat1 = (Math.PI/180)*lat3;  
	    double lat2 = (Math.PI/180)*lat;	          
	    double lon1 = (Math.PI/180)*lon3;  
	    double lon2 = (Math.PI/180)*lon;  
	          
	    //地球半径  
	    double R1 = 6371;  
	          
	    //两点间距离 km，如果想要米的话，结果*1000就可以了  
	    double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R1;  	          
	    return d*1000;
	}
	

}
