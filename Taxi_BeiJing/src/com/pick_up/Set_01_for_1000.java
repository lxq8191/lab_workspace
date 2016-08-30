package com.pick_up;

import java.io.File;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.ArrayList;

//��ǰ1000���۴��е�ÿ��������ؿ�״̬
public class Set_01_for_1000 {
	private static ArrayList<Point> Dataset = new ArrayList<>();
	
	private static int getNO(String a)//�õ����⳵ID
	{
		int m=0;
		for(;m<7;m++)
		{
			if(a.charAt(m)==',')
				break;
		}
		return m;
	}
	
	private static int gettime(String a)//����ʱ��
	{
		return(Integer.valueOf(a.substring(12+getNO(a), 14+getNO(a)))*3600+
				Integer.valueOf(a.substring(15+getNO(a), 17+getNO(a)))*60+
				Integer.valueOf(a.substring(18+getNO(a), 20+getNO(a))));
	}
	
	public static String getPrettyNumber(String number) {//ȥ��double��������������õ�0
	    return BigDecimal.valueOf(Double.parseDouble(number))  
	            .stripTrailingZeros().toPlainString();  
	}  
	
	//��ȡǰ1000���۴��еĵ㣬�������Dataset��
	@SuppressWarnings("resource")
	public static void createData() throws Exception{
		double lon;
		double lat;
		int num;
		File file1 = new File("F:" + File.separator + "point_test.txt");
		RandomAccessFile Fmax = null;
		Fmax = new RandomAccessFile(file1,"rw");
		String a = new String(Fmax.readLine());
		while(a!=null){
			String[] soure = a.split(",");
			lon = Double.parseDouble(soure[2]);//����
			lat = Double.parseDouble(soure[3]);//γ��
			num = Integer.parseInt(soure[4]);//�۴غ�
			Dataset.add(new Point(lon,lat,Integer.parseInt(soure[0]),Integer.parseInt(soure[1]),num));
			a = Fmax.readLine();
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		System.out.println("main begin!");
		long start = System.currentTimeMillis();
		createData();
		ArrayList<Point> data = new ArrayList<>();
		File ff = new File("F:\\Taxi");//�ļ�·��
		File[] files = ff.listFiles();
		for(int i=0;i<files.length;i++)
		{
			
			//System.out.println(i);
			RandomAccessFile F=null;
			//RandomAccessFile F[]=new RandomAccessFile[100];
			F=new RandomAccessFile(files[i],"r");
			String aa= new String(F.readLine());
			while(aa!=null){
				String[] soureStr = aa.split(",");
				for(int j = 0;j < Dataset.size();j++){
					if(Dataset.get(j).getnumber()==Integer.parseInt(soureStr[0])
							&&Dataset.get(j).gettime()==gettime(aa)
								&&Dataset.get(j).getX()==Double.parseDouble(getPrettyNumber(soureStr[2]))
									&&Dataset.get(j).getY()==Double.parseDouble(getPrettyNumber(soureStr[3]))){
						//���⳵id��ʱ�䣬��γ��ƥ��֮�󣬽��������ӵ�data�У��������ؿ�״̬
						data.add(new Point(Dataset.get(j).getX(),Dataset.get(j).getY(),
								Dataset.get(j).getnumber(),Dataset.get(j).gettime(),Integer.parseInt(soureStr[6]),Dataset.get(j).getclusterid()));
					}
				}
				aa = F.readLine();
			}
		}
		File fmax=new File("F:"+File.separator+"point_include_01state.txt");
		RandomAccessFile Fmax=null;
		Fmax=new RandomAccessFile(fmax,"rw");
		for (int p = 0; p < data.size(); p++) {
			Fmax.writeBytes(String.valueOf((data.get(p)).getnumber()));
			Fmax.writeBytes(",");
			//F.writeBytes("\tTime:");
			Fmax.writeBytes(String.valueOf((data.get(p)).gettime()));
			Fmax.writeBytes(",");
			//F.writeBytes("\tX:");
			Fmax.writeBytes(String.valueOf((data.get(p)).getX()));
			Fmax.writeBytes(",");
			//F.writeBytes("\tY:");
			Fmax.writeBytes(String.valueOf((data.get(p)).getY()));
			Fmax.writeBytes(",");
			Fmax.writeBytes(String.valueOf((data.get(p)).getState()));
			Fmax.writeBytes(",");
			//F.writeBytes("\tClusterID:");
			Fmax.writeBytes(String.valueOf((data.get(p)).getclusterid()));
			Fmax.writeBytes("\r\n");
		}
		Fmax.close();
		System.out.println("main end!");
		long end = System.currentTimeMillis();
		System.out.println("time = "+(end - start)/1000/60 +" mins");
	}
}
