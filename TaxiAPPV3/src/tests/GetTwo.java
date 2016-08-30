package tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class GetTwo 
{
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
	public static void main(String[] args)throws Exception
	{
		ArrayList<Point> data=new ArrayList<Point>();
		File f = new File("D:"+File.separatorChar+"outmax.txt");
		FileWriter ff=new FileWriter("E:"+File.separator+"test.txt");
		BufferedWriter FF=new BufferedWriter(ff);
		RandomAccessFile F=new RandomAccessFile(f, "r");
		String a=new String(F.readLine());
		int i=0;
		while(a!=null)
			{
				String b[]=a.split("\t");
				data.add(new Point(Double.valueOf(b[2]),Double.valueOf(b[3]),
						Integer.valueOf(b[0]),Integer.valueOf(b[1])));
				data.get(i).setclusterid(Integer.valueOf(b[4]));
				a=F.readLine();
				i++;
			}
		F.close();
		for(int j=0;j<data.size();j++)
		{
			if(data.get(j).getclusterid()==3)
			{
				FF.write(String.valueOf(data.get(j).getX()));
				FF.write(",");
				FF.write(String.valueOf(data.get(j).getY()));
				FF.write("\r\n");
			}
		}
		FF.close();
		
		//ArrayList<Point> b = new ArrayList<Point>();
/*		ArrayList<Integer> m=new ArrayList<Integer>();
		int cmax=568;
		int miniNO;
		double miniS,thisS;
		int p=0;
		for(int c=1;c<=cmax;c++)
		{
			for(;p<data.size();p++)
			{
				if((data.get(p)).getclusterid()==c)
				{
					m.add(p);
				}
				else if(p!=c)
					break;
			}
			miniNO=m.get(0);
			thisS=0.0;
			miniS=0.0;
			if(m.size()>=3)
			{
				for(int q1=1;q1<m.size();q1++)
				{
					miniS=miniS+getDistance(data.get(m.get(0)),data.get(m.get(q1)));
				}
				for(int q=1;q<m.size();q++)
				{
					for(int qq=0;qq<m.size()&&qq!=q;qq++)
					{
						thisS=thisS+getDistance(data.get(m.get(q)),data.get(m.get(qq)));
					}
					if(thisS<miniS)
						miniNO=m.get(q);
				}
			}
			FF.write(String.valueOf(data.get(miniNO).getX()));
			//FF.writeDouble(data.get(miniNO).getX());
			FF.write(",");
			FF.write(String.valueOf(data.get(miniNO).getY()));
			//FF.writeDouble(data.get(miniNO).getY());
			FF.write("\r\n");
			//b.add(data.get(miniNO)); 
			m.clear();
		}
		FF.close();
		*/
	}
}
