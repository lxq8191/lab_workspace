package com.taxi;


import java.util.ArrayList;

public class Point
{
	private int number;
	private int time;
	private int size;
	private ArrayList<Integer> Neighbors;
	private double x;
	private double y;
	private boolean visted;
	private int clusterid;
	
	public Point(double a, double b,int c,int d)
	{
		x = a;
		y = b;
		number=c;
		time=d;
		visted = false;
		clusterid = -1;
		Neighbors=new ArrayList<Integer>();
		
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
	public int getnumber()
	{
		return number;
	}
	public int gettime()
	{
		return time;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public boolean getVisted()
	{
		return visted;
	}
	public int getclusterid()
	{
		return clusterid;
	}
	public void vist()
	{
		visted = true;
	}
	public void setclusterid(int a)
	{
		clusterid = a;
	}
}
