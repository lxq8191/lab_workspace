package com.taxi;

import java.io.File;

//import java.lang.Math;
import java.util.ArrayList;

//import two.Point;
import java.io.RandomAccessFile;


public class dbscan 
{
	private static ArrayList<Point> Dataset;
	private static double eps;
	private int minpts;
	public  dbscan(ArrayList<Point> d, double e, int m) 
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
	
	public ArrayList run()
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
	private static int getNO(StringBuffer a)
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
		File ff = new File("F:\\Taxi");
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
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(53)=='1')
						{
							data.add(new Point(Double.valueOf(aa.substring(24, 32)),
									Double.valueOf(aa.substring(35, 43)),getnumber(aa),gettime(aa)));
							b=aa.charAt(53);
							//System.out.println(aa.length());
						}
					}
					else if(b=='1')
					{
						if(aa.charAt(53)=='0')
						{
							data.add(new Point(Double.valueOf(aa.substring(24, 32)),
									Double.valueOf(aa.substring(35, 43)),getnumber(aa),gettime(aa)));
							b=aa.charAt(53);
							//System.out.println(aa.length());
						}
					}
					
				}
			}
			else if (getNO(aa)==4)
			{
				char b='0';
				//data.add(new Point(Double.valueOf(aa.substring(25, 33)),
						//Double.valueOf(aa.substring(36, 44)),getnumber(aa),gettime(aa)));
				b=aa.charAt(54);
				String thisline=null;
				for(;(thisline=F.readLine())!=null;)
				{
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(54)=='1')
						{
							data.add(new Point(Double.valueOf(aa.substring(25, 33)),
									Double.valueOf(aa.substring(36, 44)),getnumber(aa),gettime(aa)));
							b=aa.charAt(54);
							//System.out.println(aa.length());
						}
					}
					else if(b=='1')
					{
						if(aa.charAt(54)=='0')
						{
							data.add(new Point(Double.valueOf(aa.substring(25, 33)),
									Double.valueOf(aa.substring(36, 44)),getnumber(aa),gettime(aa)));
							b=aa.charAt(54);
							//System.out.println(aa.length());
						}
					}
					
				}
			}
			else if (getNO(aa)==5)
			{
				char b='0';
				//data.add(new Point(Double.valueOf(aa.substring(26, 34)),
						//Double.valueOf(aa.substring(37, 45)),getnumber(aa),gettime(aa)));
				b=aa.charAt(55);
				String thisline=null;
				for(;(thisline=F.readLine())!=null;)
				{
					aa= new StringBuffer(thisline);
					if(aa.length()==0)
						break;
					else if(b!='0'&&b!='1')
						break;
					else if(b=='0')
					{
						if(aa.charAt(55)=='1')
						{
							data.add(new Point(Double.valueOf(aa.substring(26, 34)),
									Double.valueOf(aa.substring(37, 45)),getnumber(aa),gettime(aa)));
							b=aa.charAt(55);
							//System.out.println(aa.length());
						}
					}
					else if(b=='1')
					{
						if(aa.charAt(55)=='0')
						{
							data.add(new Point(Double.valueOf(aa.substring(26, 34)),
									Double.valueOf(aa.substring(37, 45)),getnumber(aa),gettime(aa)));
							b=aa.charAt(55);
							//System.out.println(aa.length());
						}
					}
					
				}
			}
		}
		System.out.println("createData is end  data.size="+data.size());
		for(int m=0;m<data.size();m++)
		{
			for(int n=m+1;n<data.size();n++)
			{
				if(getDistance(data.get(m), data.get(n))<=250)
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
		for(int i = 0; i < p.getNeighbors().size(); i++)
		{
			if(!Dataset.get(p.getNeighbors().get(i)).getVisted())
			{
				Dataset.get(p.getNeighbors().get(i)).vist();
				//ArrayList<Integer> TmpNeighbors = Dataset.get(p.getNeighbors().get(i)).getNeighbors();
				if(Dataset.get(p.getNeighbors().get(i)).getsize() >= minpts&&getDistance(p, Dataset.get(p.getNeighbors().get(i)))<1500)
				{
					for(Integer j=0;j<Dataset.get(p.getNeighbors().get(i)).getsize();j++)
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
			if(Dataset.get(p.getNeighbors().get(i)).getclusterid()==-1&&getDistance(p, Dataset.get(p.getNeighbors().get(i)))<=1000)
			{
				Dataset.get(p.getNeighbors().get(i)).setclusterid(c);
				//System.out.println("c="+c);
			}
		}
		//System.out.println("expandCluster is end");
	}
//	private ArrayList regionQuery(Point p)
//	{
//		ArrayList<Integer> region = new ArrayList<Integer>();
//		for(int i = 0; i < Dataset.size(); i++)
//		{
//			//System.out.println(getDistance(p, Dataset.get(i)));
//			if(getDistance(p, Dataset.get(i)) <= eps)
//			{
//				region.add(i);
//			}
//		}
//		return region;
//	}
	private static double getDistance(Point a, Point b)
	{
		double p,q,p1,q1,R,s; 
		R=6378137;
		double aa=a.getY()* Math.PI / 180.0;
		double bb=b.getY()* Math.PI / 180.0;
		p=aa-bb;
		q=(a.getX()-b.getX())* Math.PI / 180.0;
		p1=Math.sin(p / 2.0);
		q1=Math.sin(q / 2.0);
		s=2*R* Math.asin(Math.sqrt(p1 * p1 + Math.cos(p)* Math.cos(q) * q1 * q1));
		return s;
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
	private static Point getstart(ArrayList<Point> a)
	{
		return a.get(0);
	}
	private static Point getend(ArrayList<Point> a)
	{
		Point end;
		end=a.get(98);
//		for(int i=2;i<a.size();i++)
//		{
//			if(getDistance(a.get(0),a.get(i))>getDistance(a.get(0),end))
//			{
//				end=a.get(i);
//			}
//		}
		return end;
	}
	public static void main(String[] args)throws Exception
	{
		long start=System.currentTimeMillis()/1000/60;
		System.out.println(start);
		System.out.println("main is begin");
		dbscan d = new dbscan(createData(), 250.0, 3);
		ArrayList<Point> output = d.run();
		//ArrayList<Point> ff=ConfirmSite(output);
		File fmax=new File("F:"+File.separator+"outmax.txt");
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
//					System.out.println("Number:"+(output.get(p)).getnumber()+"\tTime:"+
//						(output.get(p)).gettime()+"\tX: " + (output.get(p)).getX() + "\tY: " +
//							(output.get(p)).getY() +"\tClusterID: " + (output.get(p)).getclusterid());
				else if((output.get(p)).getclusterid()>c)
					cmax=output.get(p).getclusterid();
			}
//			miniNO=a.get(0);
//			thisS=0.0;
//			miniS=0.0;
//			if(a.size()>=3)
//			{
//				for(int q1=1;q1<a.size();q1++)
//				{
//					miniS=miniS+getDistance(output.get(a.get(0)),output.get(a.get(q1)));
//				}
//				for(int q=1;q<a.size();q++)
//				{
//					for(int qq=0;qq<a.size()&&qq!=q;qq++)
//					{
//						thisS=thisS+getDistance(output.get(a.get(q)),output.get(a.get(qq)));
//					}
//					if(thisS<miniS)
//						miniNO=a.get(q);
//				}
//			}
//			System.out.println("Number:"+(output.get(miniNO)).getnumber()+"\tTime:"+
//			(output.get(miniNO)).gettime()+"\tX: " + (output.get(miniNO)).getX() + "\tY: " +
//				(output.get(miniNO)).getY() +"\tClusterID: " + (output.get(miniNO)).getclusterid());
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
//				System.out.println("Number:"+(output.get(pp)).getnumber()+"\tTime:"+
//						(output.get(pp)).gettime()+"\tX: " + (output.get(pp)).getX() + "\tY: " +
//							(output.get(pp)).getY() +"\tClusterID: " + (output.get(pp)).getclusterid());
		}
		Fmax.close();
		//System.out.println(ff.size());
//		File f=new File("D:"+File.separator+"out.txt");
//		RandomAccessFile F=null;
//		F=new RandomAccessFile(f,"rw");
//		//System.out.println(ff.size());
//		for(int i=0;i<ff.size();i++)
//		{
//			F.writeBytes(String.valueOf((ff.get(i)).getX()));
//			F.writeBytes("\t\t");
//			F.writeBytes(String.valueOf((ff.get(i)).getY()));
//			F.writeBytes("\t\t");
//			F.writeBytes(String.valueOf((ff.get(i)).getclusterid()));
//			F.writeBytes("\r\n");
//		}
//		F.close();
//		for(int i=0;i<ff.size();i++)
//		{
//			for(int ii=0;ii<ff.size()&&ii!=i;ii++)
//			{
//				System.out.println(getDistance(ff.get(i),ff.get(ii)));
//			}
//		}
//		System.out.println(getend(ff).getX()+"    "+getend(ff).getY());
//		getroute aa=new getroute(ff,5000.0,getstart(ff),getend(ff));
//		
//		if(aa.check()==0)
//			System.out.println("不能到达终点");
//		else
//		{
//			aa.run(aa.start);
//			aa.printf(aa.end);
//		}
//		for(int ab=0;ab<ff.size();ab++)
//		{
//			System.out.println(getDistance(ff.get(ab), getend(ff)));
//		}
		long end=System.currentTimeMillis()/1000/60;
		System.out.println(end-start);
		System.out.println("main is end");

	}
	
}