package com.test;

public class Solution231 {
	public static boolean isPowerOfTwo(int n) {
		if(n<=0) return false;
		if(n==1) return true;
		while(n!=1){
			if(n%2 != 0){
				return false;
			}else{
				n = n / 2;
			}
		}
		return true;
    }
	
	public static void main(String[] args) {
		System.out.println(isPowerOfTwo(128));
	}
}
