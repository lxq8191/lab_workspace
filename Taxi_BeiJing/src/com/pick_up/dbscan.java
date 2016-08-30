package com.pick_up;

import java.io.File;

//import java.lang.Math;
import java.util.ArrayList;

//import two.Point;
import java.io.RandomAccessFile;

//dbscan聚类算法
public class dbscan 
{
	private static ArrayList<Point> Dataset;
	private static double eps = 50;
	private int minpts = 3;
	
//	private int R1 = 500;
//	private int R2 = 500;
	
	public  dbscan(double e, int m ,ArrayList<Point> d) 
	{
		//Dataset = (ArrayList<Point>) D.clone();
		eps = e;
		minpts = m;
//		for(int i=0;i<d.size();i++)
//		{
//			for(int n=0;n<d.size();n++)
//			{
//				if(i!=n&&getDistance(d.get(i), d.get(n))<=e)
//				{
//					d.get(i).addNeighbors(n);
//				}
//			}
//			d.get(i).setsize();
//			//System.out.println(data.get(m).getsize());
//		}
//		System.out.println("data is OK!"+d.size());
		//System.out.println("createData is end");
		Dataset = (ArrayList<Point>) d.clone();
	}
	
	public ArrayList<Point> run()
	{
		System.out.println("run is begin");
		int c = 0;
		for(int i = 0; i < Dataset.size(); i++)
		{
			if(!Dataset.get(i).getVisted())
			{
				Dataset.get(i).vist();//设置该点已经访问
				//ArrayList<Integer> Neighbors = Dataset.get(i).getNeighbors();
				//寻找近邻结点
				if(Dataset.get(i).getsize() >= minpts)//大于规定数目则生成一个聚簇
				{
					c = c + 1;//生成聚簇号
					//System.out.println("c="+c);
					expandCluster(Dataset.get(i),c);
				}
				else
				{
					Dataset.get(i).setclusterid(-2);//设离群点的聚簇号为-2
				}
			}
		}
		System.out.println("run is end");
		return Dataset;
	}
	private static int getnumber(StringBuffer a)
	{
		return(Integer.valueOf(a.substring(0, getNO(a))));
	}
	private static int gettime(StringBuffer a)
	{
		return(Integer.valueOf(a.substring(12+getNO(a), 14+getNO(a)))*3600+
				Integer.valueOf(a.substring(15+getNO(a), 17+getNO(a)))*60+
				Integer.valueOf(a.substring(18+getNO(a), 20+getNO(a))));
	}
	private static int getNO(StringBuffer a)//得到出租车ID
	{
		int m=0;
		for(;m<7;m++)
		{
			if(a.charAt(m)==',')
				break;
		}
		return m;
	}
	private static  ArrayList<Point> createData() throws Exception
	{
		System.out.println("createData is begin");
		ArrayList<Point> data = new ArrayList<Point>();
		File ff = new File("F:\\Taxi1");//文件路径
		File[] files = ff.listFiles();
		for(int i=0;i<files.length;i++)
		{
			//System.out.println(i);
			RandomAccessFile F=null;
			//RandomAccessFile F[]=new RandomAccessFile[100];
			F=new RandomAccessFile(files[i],"r");
			StringBuffer aa;
			aa= new StringBuffer(F.readLine());
//			System.out.println(aa);
//			System.out.println(aa.length());
			//System.out.println(getNO(aa));
			if (getNO(aa)==3)
			{
				char b='0';
				//data.add(new Point(Double.valueOf(aa.substring(24, 32)),
						//Double.valueOf(aa.substring(35, 43)),getnumber(aa),gettime(aa)));
				b=aa.charAt(53);
				String thisline=null;
				for(;(thisline=F.readLine())!=null;)
				{
					StringBuffer bb = new StringBuffer(aa);
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(53)=='1')
						{
							data.add(new Point(Double.valueOf(bb.substring(24, 32)),
									Double.valueOf(bb.substring(35, 43)),getnumber(bb),gettime(bb)));
							data.add(new Point(Double.valueOf(aa.substring(24, 32)),
									Double.valueOf(aa.substring(35, 43)),getnumber(aa),gettime(aa)));
							b=aa.charAt(53);
						}
					}
					else if(b=='1'){
						b = aa.charAt(53);
					}
					
				}
			}
			else if (getNO(aa)==4)
			{
				char b='0';
				b=aa.charAt(54);
				String thisline=null;
				for(;(thisline=F.readLine())!=null;)
				{
					StringBuffer bb = new StringBuffer(aa);
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(54)=='1')
						{
							data.add(new Point(Double.valueOf(bb.substring(25, 33)),
									Double.valueOf(bb.substring(36, 44)),getnumber(bb),gettime(bb)));
							data.add(new Point(Double.valueOf(aa.substring(25, 33)),
									Double.valueOf(aa.substring(36, 44)),getnumber(aa),gettime(aa)));
							b=aa.charAt(54);
							
						}
					}
					else if(b=='1'){
						b = aa.charAt(54);
					}
					
				}
			}
			else if (getNO(aa)==5)
			{
				char b='0';
				b=aa.charAt(55);
				String thisline=null;
				for(;(thisline=F.readLine())!=null;)
				{
					StringBuffer bb = new StringBuffer(aa);
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(55)=='1')
						{
							data.add(new Point(Double.valueOf(bb.substring(26, 34)),
									Double.valueOf(bb.substring(37, 45)),getnumber(bb),gettime(bb)));
							data.add(new Point(Double.valueOf(aa.substring(26, 34)),
									Double.valueOf(aa.substring(37, 45)),getnumber(aa),gettime(aa)));
							b=aa.charAt(55);
						}
					}
					else if(b=='1'){
						b = aa.charAt(55);
					}
				}
			}
		}
		System.out.println("createData is end  data.size="+data.size());
		for(int m=0;m<data.size();m++)
		{
			for(int n=m+1;n<data.size();n++)
			{
				if(getDistance(data.get(m), data.get(n))<=eps)
				{					
					data.get(m).addNeighbors(n);
					data.get(n).addNeighbors(m);
				}
			}
			data.get(m).setsize(); 
		}
		System.out.println("data is OK!");
		return data;
	}
	private void expandCluster(Point p,  int c)
	{
		//System.out.println("expandCluster is begin");
		p.setclusterid(c);
		//System.out.println("c="+c);
		for(int i = 0; i < p.getNeighbors().size(); i++)//遍历p的邻居节点
		{
			if(!Dataset.get(p.getNeighbors().get(i)).getVisted())
			{
				Dataset.get(p.getNeighbors().get(i)).vist();
				//ArrayList<Integer> TmpNeighbors = Dataset.get(p.getNeighbors().get(i)).getNeighbors();
				if(Dataset.get(p.getNeighbors().get(i)).getsize() >= minpts&&getDistance(p, Dataset.get(p.getNeighbors().get(i)))<500)
				{
					for(Integer j=0;j<Dataset.get(p.getNeighbors().get(i)).getsize();j++)//遍历p的第i个邻居节点的邻居节点
					{
						int m=0;
						for(;m<p.getNeighbors().size();m++)
						{
							if(Dataset.get(p.getNeighbors().get(i)).getNeighbors().get(j)==p.getNeighbors().get(m))
								break;
						}
						if(m==p.getNeighbors().size())
							p.addNeighbors(Dataset.get(p.getNeighbors().get(i)).getNeighbors().get(j));
					}
				}
			}
			if(Dataset.get(p.getNeighbors().get(i)).getclusterid()==-1&&getDistance(p, Dataset.get(p.getNeighbors().get(i)))<=500)
			{
				Dataset.get(p.getNeighbors().get(i)).setclusterid(c);
				//System.out.println("c="+c);
			}
		}
		//System.out.println("expandCluster is end");
	}
	private static double getDistance(Point a, Point b)
	{
		double lat1 = (Math.PI/180)*a.getY();  
	    double lat2 = (Math.PI/180)*b.getY();	          
	    double lon1 = (Math.PI/180)*a.getX();  
	    double lon2 = (Math.PI/180)*b.getX();  
	          
	    //地球半径  
	    double R1 = 6371;  
	          
	    //两点间距离 km，如果想要米的话，结果*1000就可以了  
	    double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R1;  	          
	    return d*1000;
	}
	private static  ArrayList<Point> ConfirmSite(ArrayList<Point> Data)
	{
		ArrayList<Point> b = new ArrayList<Point>();
		ArrayList<Integer> a=new ArrayList<Integer>();
		int cmax=1;
		int miniNO;
		double miniS,thisS;
		for(int c=1;c<=cmax;c++)
		{
			for(int p=0;p<Data.size();p++)
			{
				if((Data.get(p)).getclusterid()==c)
				{
					a.add(p);
				}
				else if((Data.get(p)).getclusterid()>c)
					cmax=(Data.get(p)).getclusterid();
			}
			miniNO=a.get(0);
			thisS=0.0;
			miniS=0.0;
			if(a.size()>=3)
			{
				for(int q1=1;q1<a.size();q1++)
				{
					miniS=miniS+getDistance(Data.get(a.get(0)),Data.get(a.get(q1)));
				}
				for(int q=1;q<a.size();q++)
				{
					for(int qq=0;qq<a.size()&&qq!=q;qq++)
					{
						thisS=thisS+getDistance(Data.get(a.get(q)),Data.get(a.get(qq)));
					}
					if(thisS<miniS)
						miniNO=a.get(q);
				}
			}
			b.add(Data.get(miniNO)); 
			a.clear();
		}
		return b;
	}
	public static void main(String[] args)throws Exception
	{
		long start=System.currentTimeMillis()/1000/60;
		System.out.println(start);
		System.out.println("main is begin");
		dbscan d = new dbscan(50.0, 3,createData());
		ArrayList<Point> output = d.run();
		//ArrayList<Point> ff=ConfirmSite(output);
		File fmax=new File("F:"+File.separator+"output_pick_up.txt");
		RandomAccessFile Fmax=null;
		Fmax=new RandomAccessFile(fmax,"rw");
		ArrayList<Integer> a=new ArrayList<Integer>();
		int miniNO;
		int cmax=1;
		double miniS,thisS;
		for(int c=1;c<=cmax;c++)//输出结果
		{
			for(int p=0;p<output.size();p++)
			{
				if((output.get(p)).getclusterid()==c)
				{
					a.add(p);
					//F.writeBytes("Number:");
					Fmax.writeBytes(String.valueOf((output.get(p)).getnumber()));
					Fmax.writeBytes("\t");
					//F.writeBytes("\tTime:");
					Fmax.writeBytes(String.valueOf((output.get(p)).gettime()));
					Fmax.writeBytes("\t");
					//F.writeBytes("\tX:");
					Fmax.writeBytes(String.valueOf((output.get(p)).getX()));
					Fmax.writeBytes("\t");
					//F.writeBytes("\tY:");
					Fmax.writeBytes(String.valueOf((output.get(p)).getY()));
					Fmax.writeBytes("\t");
					//F.writeBytes("\tClusterID:");
					Fmax.writeBytes(String.valueOf((output.get(p)).getclusterid()));
					Fmax.writeBytes("\r\n");
				}
				else if((output.get(p)).getclusterid()>c)
					cmax=output.get(p).getclusterid();
			}
			a.clear();
		}
		for(int pp=0;pp<output.size();pp++)
		{
			if((output.get(pp)).getclusterid()==-2)
			{
				//F.writeBytes("Number:");
				Fmax.writeBytes(String.valueOf((output.get(pp)).getnumber()));
				Fmax.writeBytes("\t");
				//F.writeBytes("\tTime:");
				Fmax.writeBytes(String.valueOf((output.get(pp)).gettime()));
				Fmax.writeBytes("\t");
				//F.writeBytes("\tX:");
				Fmax.writeBytes(String.valueOf((output.get(pp)).getX()));
				Fmax.writeBytes("\t");
				//F.writeBytes("\tY:");
				Fmax.writeBytes(String.valueOf((output.get(pp)).getY()));
				Fmax.writeBytes("\t");
				//F.writeBytes("\tClusterID:");
				Fmax.writeBytes(String.valueOf((output.get(pp)).getclusterid()));
				Fmax.writeBytes("\r\n");
			}
		}
		Fmax.close();
		long end=System.currentTimeMillis()/1000/60;
		System.out.println(end-start);
		System.out.println("main is end");

	}
	
}