package com.dbscan.beijing;

import java.util.ArrayList;

public class Point
{
	private int number;
	private String time;
	private int size;
	private int state;
	private ArrayList<Integer> Neighbors;
	private double x;
	private double y;
	private boolean visted;
	private int clusterid;
	
	
	//经度，纬度，车的ID，时间，状态
	public Point(double a, double b,int c,String d,int e)
	{
		x = a;
		y = b;
		number=c;
		time=d;
		state = e;
		visted = false;
		clusterid = -1;
		Neighbors=new ArrayList<Integer>();
		
	}
		
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}	
	
	public void addNeighbors(Integer a)
	{
		Neighbors.add(a);
	}
	
	public ArrayList<Integer> getNeighbors()
	{
		return Neighbors;
	}
	
	public void setsize()
	{
		size=Neighbors.size();
	}
	
	public int getsize()
	{
		return size;
	}
	
	public boolean getVisted()
	{
		return visted;
	}
	
	public void vist()
	{
		visted = true;
	}
	
	public int getclusterid()
	{
		return clusterid;
	}
	
	public void setclusterid(int a)
	{
		clusterid = a;
	}
}
