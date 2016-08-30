package com.dbscan.beijing;

public class Distance_test {
	
	public static void main(String[] args) {

//		116.30142,39.98105
//		116.29965,39.9809

		String str1="116.34899,39.9637";
		String[] s1 = str1.split(",");
		String str2="116.34899,39.9637";
		String[] s2 = str2.split(",");

		double a1 = Double.parseDouble(s1[1]);
		double a2 = Double.parseDouble(s2[1]);
		double b1 = Double.parseDouble(s1[0]);		
		double b2 = Double.parseDouble(s2[0]);
		double lat1 = (Math.PI/180)*a1;  
	    double lat2 = (Math.PI/180)*a2;	          
	    double lon1 = (Math.PI/180)*b1;  
	    double lon2 = (Math.PI/180)*b2;  
	          
	    //地球半径  
	    double R1 = 6371;  
	          
	    //两点间距离 km，如果想要米的话，结果*1000就可以了  
	    double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R1;  	          
	    System.out.println(d*1000);
	}
	

}
