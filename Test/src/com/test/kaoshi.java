package com.test;

public class kaoshi {
	
	public static void main(String[] args) {
		double num = 100000.0;
		double sum = 0;
		for (int i = 50000; i < num&&sum<0.001; i+=1000,sum=0) {
			sum = 1.0 - i/num;
			sum = Math.pow(sum, 6);
			System.out.println(sum+","+i);
		}
	}

}
