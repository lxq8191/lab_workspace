package com.dbscan.beijing;

import java.io.File;
import java.util.ArrayList;
import java.io.RandomAccessFile;

public class dbscan 
{
	private static ArrayList<Point> Dataset = new ArrayList<>();
	private static double eps = 50;
	private static int minpts = 3;
	private static int count = 0;
	
//	private int R1 = 500;
//	private int R2 = 500;
	
	
	public static ArrayList<Point> run()
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
	
	@SuppressWarnings("resource")
	private static  void createData() throws Exception
	{
		double lon,lat;
		int car_id,state;
		double temp_lo,temp_la;
		String time;
		System.out.println("createData is begin");
//		File fmax=new File("F:"+File.separator+".txt");
		File fmax = new File("src\\com\\dbscan\\beijing\\points.txt");
		RandomAccessFile F=null;
		F=new RandomAccessFile(fmax,"r");
		
		String currentLine = new String(F.readLine());
		while(currentLine!=null){
			String[] soure = currentLine.split(",");
			lon = Double.parseDouble(soure[3])/100000;
			lat = Double.parseDouble(soure[2])/100000;
			car_id = Integer.parseInt(soure[0]);
			time = soure[1];
			state = Integer.parseInt(soure[6]);
			Dataset.add(new Point(lon,lat,car_id,time,state));
			currentLine = F.readLine();
		}
		F.close();	
		System.out.println("createData is end  data.size="+Dataset.size());
//		for(int m=0;m<Dataset.size();m++)
//		{
//			for(int n=m+1;n<Dataset.size();n++)
//			{
//				if(getDistance(Dataset.get(m), Dataset.get(n))<=eps)
//				{					
//					Dataset.get(m).addNeighbors(n);
//					Dataset.get(n).addNeighbors(m);
//				}
//			}
//			Dataset.get(m).setsize(); 
//			System.out.println(Dataset.get(m).getsize()+";"+m);	
//		}
		for(int m=0;m<Dataset.size();m++)
		{
			temp_lo = Dataset.get(m).getX();
			temp_la = Dataset.get(m).getY();
			for(int n=m+1;n<Dataset.size();n++)
			{				
				if(((temp_lo-0.00118)<=Dataset.get(n).getX()&&Dataset.get(n).getX()<=(temp_lo+0.00118))&&
						((temp_la-0.00090)<=Dataset.get(n).getY()&&Dataset.get(n).getY()<=(temp_la+0.00090))){
					Dataset.get(m).addNeighbors(n);
					Dataset.get(n).addNeighbors(m);
				}			    
			}
			Dataset.get(m).setsize(); 
//			System.out.println(Dataset.get(m).getsize()+";"+count++);			
		}
		
		System.out.println("data is OK!");
	}
	
	private static void expandCluster(Point p,  int c)
	{
		//System.out.println("expandCluster is begin");
		p.setclusterid(c);
		//System.out.println("c="+c);
		for(int i = 0; i < p.getNeighbors().size(); i++)//遍历p的邻居节点
		{
			if(!Dataset.get(p.getNeighbors().get(i)).getVisted())
			{
				Dataset.get(p.getNeighbors().get(i)).vist();
				if(Dataset.get(p.getNeighbors().get(i)).getsize() >= minpts&&getDistance(p, Dataset.get(p.getNeighbors().get(i)))<500)
				{
					for(Integer j=0;j<Dataset.get(p.getNeighbors().get(i)).getsize();j++)//遍历p的第i个邻居节点的邻居节点
					{
//						int m=0;
//						for(;m<p.getNeighbors().size();m++)
//						{
//							if(Dataset.get(p.getNeighbors().get(i)).getNeighbors().get(j)==p.getNeighbors().get(m))
//								break;
//						}
//						if(m==p.getNeighbors().size())
//							p.addNeighbors(Dataset.get(p.getNeighbors().get(i)).getNeighbors().get(j));
//						
						if(!p.getNeighbors().contains(Dataset.get(p.getNeighbors().get(i)).getNeighbors().get(j))){
							p.addNeighbors(Dataset.get(p.getNeighbors().get(i)).getNeighbors().get(j));
						}
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
	
	public static void main(String[] args)throws Exception
	{
		long start=System.currentTimeMillis()/1000/60;
		System.out.println("main is begin");
		createData();
		run();
		ArrayList<Point> output = Dataset;
		File fmax=new File("F:"+File.separator+"cluster_clustering_bjweekend11.txt");
		RandomAccessFile Fmax=null;
		Fmax=new RandomAccessFile(fmax,"rw");
		ArrayList<Integer> a=new ArrayList<Integer>();
		
		int cmax=1;	
		for(int c=1;c<=cmax;c++)//输出结果
		{
			for(int p=0;p<output.size();p++)
			{
				if((output.get(p)).getclusterid()==c)
				{
					a.add(p);
					Fmax.writeBytes(String.valueOf((output.get(p)).getNumber()));
					Fmax.writeBytes(",");
					Fmax.writeBytes((output.get(p)).getTime());
					Fmax.writeBytes(",");
					Fmax.writeBytes(String.valueOf((output.get(p)).getX()));
					Fmax.writeBytes(",");					
					Fmax.writeBytes(String.valueOf((output.get(p)).getY()));
					Fmax.writeBytes(",");
					Fmax.writeBytes(String.valueOf((output.get(p)).getState()));
					Fmax.writeBytes(",");
					Fmax.writeBytes(String.valueOf((output.get(p)).getclusterid()));
					Fmax.writeBytes("\n");
				}
				else if((output.get(p)).getclusterid()>c)
					cmax=output.get(p).getclusterid();
			}
			a.clear();
		}
//		for(int pp=0;pp<output.size();pp++)
//		{
//			if((output.get(pp)).getclusterid()==-2)
//			{
//				Fmax.writeBytes(String.valueOf((output.get(pp)).getNumber()));
//				Fmax.writeBytes(",");
//				Fmax.writeBytes((output.get(pp)).getTime());
//				Fmax.writeBytes(",");
//				Fmax.writeBytes(String.valueOf((output.get(pp)).getX()));
//				Fmax.writeBytes(",");					
//				Fmax.writeBytes(String.valueOf((output.get(pp)).getY()));
//				Fmax.writeBytes(",");
//				Fmax.writeBytes(String.valueOf((output.get(pp)).getState()));
//				Fmax.writeBytes(",");
//				Fmax.writeBytes(String.valueOf((output.get(pp)).getclusterid()));
//				Fmax.writeBytes("\n");
//			}
//		}
		Fmax.close();
		long end=System.currentTimeMillis()/1000/60;
		System.out.println("time = "+(end-start)+"mins");
		System.out.println("main is end");

	}
	
}