package com.test;

import java.util.HashMap;
import java.util.Map;

public class Solution171 {
	static Map<String, Integer> map = new HashMap<String, Integer>();
	static String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static int titleToNumber(String s) {
		for (int i = 1; i <= 26; i++) {
			map.put(str.charAt(i-1)+"", i);
		}
		int result = 0;
		for(int j = s.length()-1,k = 0;j >= 0;j--,k++){
			result = (int) (result + map.get(s.charAt(j)+"")*Math.pow(26, k));
		}
		return result;
        	
    }
	public static void main(String[] args) {
		System.out.println(titleToNumber("AB"));
	}
}
