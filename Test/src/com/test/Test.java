package com.test;

public class Test {
//	   *
//	   ***
//	  *****
//	 *******
//	  *****
//	   ***
//	    *
//	打印出这种对称图形的程序
	public static void f1(int H,int W) {
		for (int i = 0; i < (H+1)/2; i++) {
			for (int j = 0; j < W/2-i; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j < (i+1)*2; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public static void f2(int H,int W) {
		for (int i = 1; i <= H/2; i++) {
			for (int j = 1; j <=i; j++) {
				System.out.print(" ");
			}
			for (int k = 1; k <=W-2*i; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		int H=7,W=7;
		f1(H,W);
		f2(H,W);
	}

}
