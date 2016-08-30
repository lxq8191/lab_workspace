package dbscan;

import java.io.*;

import java.util.*;

public class Dbscan {
	private static List<Point> pointsList = new ArrayList<Point>();// �洢���е�ļ���

	private static List<List<Point>> resultList = new ArrayList<List<Point>>();// �洢DBSCAN�㷨���صĽ����

	private static int e = 50;// e�뾶

	private static int minp = 3;// �ܶ���ֵ

	/**
	 * 
	 * ��ȡ�ı��еĵ����е㲢�洢��pointsList��
	 * 
	 * @throws IOException
	 */

	private static void display() throws Exception {

		int index = 1;
		FileWriter file = new FileWriter("D:" + File.separator
				+ "output_points.txt");

		for (Iterator<List<Point>> it = resultList.iterator(); it.hasNext();) {

			List<Point> lst = it.next();

			if (lst.isEmpty()) {

				continue;

			}
			// System.out.println("-----��"+index+"������-----");
			for (Iterator<Point> it1 = lst.iterator(); it1.hasNext();) {

				Point p = it1.next();
				file.write(p.getCarId() + "," + p.getTime() + "," + p.getX()
						+ "," + p.getY());
				file.write("," + p.getRate() + "," + p.getState() + "," + index);
				file.write("\n");
				// System.out.println(p.print());

			}

			index++;

		}
		file.close();

	}

	// �ҳ����п���ֱ��ľ���

	private static void applyDbscan() {

		try {

			pointsList = Utility.getPointsList();

			for (Iterator<Point> it = pointsList.iterator(); it.hasNext();) {

				Point p = it.next();

				if (!p.isClassed()) {

					List<Point> tmpLst = new ArrayList<Point>();

					if ((tmpLst = Utility.isKeyPoint(pointsList, p, e, minp)) != null) {

						// Ϊ���о�����ϵĵ�����ʾ

						Utility.setListClassed(tmpLst);

						resultList.add(tmpLst);

					}

				}

			}

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	// �����п���ֱ��ľ�����кϲ������ҳ���ӿɴ�ĵ㲢���кϲ�

	private static List<List<Point>> getResult() {

		applyDbscan();// �ҵ�����ֱ��ľ���

		int length = resultList.size();

		for (int i = 0; i < length; ++i) {

			for (int j = i + 1; j < length; ++j) {

				if (Utility.mergeList(resultList.get(i), resultList.get(j))) {

					resultList.get(j).clear();

				}

			}

		}

		return resultList;

	}

	/**
	 * 
	 * ����������
	 * 
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {

		long start = System.currentTimeMillis();
		System.out.println("main start!");

		getResult();

		display();

		System.out.println("main end!");

		long end = System.currentTimeMillis();

		System.out.println("time = " + (end - start) / 1000 / 60 + " mins");

		// System.out.println(Utility.getDistance(new Point(0,0), new
		// Point(0,2)));

	}
}
