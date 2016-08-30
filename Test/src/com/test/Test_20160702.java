package com.test;

import org.omg.CORBA.IntHolder;

public class Test_20160702 {
	public static void main(String[] args) {
		Integer a = 127;
		Integer b = 127;
//		if(a.equals(b)){
			if(a==b){
			System.out.println("=");
		}else{
			System.out.println("no =");
		}
		IntHolder x = new IntHolder(30);
		triple(x);
	}
	
	public static void triple(IntHolder x){
		x.value = 3 * x.value;
		System.out.println(x.value);
	}

}
