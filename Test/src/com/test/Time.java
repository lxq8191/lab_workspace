package com.test;

import java.io.ObjectInputStream.GetField;
import java.text.SimpleDateFormat;

public class Time {
	public static int[] GetWeek(String str) {
		int[] arr = new int[2];
		int y = 0, m = 0, d = 0;
		int hour = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String st = sdf.format(Long.parseLong(str));
		y = Integer.parseInt(st.substring(0, 4));
		m = Integer.parseInt(st.substring(5, 7));
		d = Integer.parseInt(st.substring(8, 10));
		hour = Integer.parseInt(st.substring(11, 13));
		System.out.println(y+","+m+","+d);
		if (m < 3) {
			m += 12;
			--y;
		}
		int w = (d + 1 + 2 * m + 3 * (m + 1) / 5 + y + (y >> 2) - y / 100 + y / 400) % 7;
		arr[0] = w;
		arr[1] = hour;
		return arr;
	}
	

	public static void main(String[] args) {
		String str = "1243354110000";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(Long.parseLong(str)));
		System.out.println(GetWeek(str)[0]+","+GetWeek(str)[1]);
	    

	}
	
}
