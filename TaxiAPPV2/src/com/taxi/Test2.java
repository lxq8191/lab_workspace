package com.taxi;

public class Test2 {
	private static int i;
	private static int j;

	public Test2(int i,int j) {
		// TODO Auto-generated constructor stub
		this.i = i;
		this.j = j;
	}
	public static void kk(int x,int y){
		System.out.println(i+j);
	}
	public static void main(String[] args) {
		Test2 t = new Test2(2,3);
		System.out.println(i+j);
		kk(i,j);
	}

}
