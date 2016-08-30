package com.graph;
import java.util.Stack;

/**
 * ������Ȩ�޻�ͼ<br/>
 * Ѱ����㵽�յ������·��
 */
public class GrfAllEdge {
	// ͼ�Ķ�������
	private int total;
	// �����������Ϣ
	private String[] nodes;
	// ͼ���ڽӾ���
	private int[][] matirx;

	public GrfAllEdge(int total, String[] nodes) {
		this.total = total;
		this.nodes = nodes;
		this.matirx = new int[total][total];
	}

	private void printStack(Stack<Integer> stack, int k) {
		for (Integer i : stack) {
			System.out.print(this.nodes[i] + ",");
		}
		System.out.print(this.nodes[k] + ",");
	}

	/**
	 * Ѱ����㵽�յ������·��
	 * 
	 * @param underTop
	 *            ������ջ�����±ߵ�Ԫ��
	 * @param goal
	 *            Ŀ��
	 * @param stack
	 */
	private void dfsStack(int underTop, int goal, Stack<Integer> stack) {
		// System.out.print("\nջԪ��:");
		// this.printStack(stack);

		if (stack.isEmpty()) {
			return;
		}

		// ����ջ��Ԫ�أ���������
		int k = stack.peek().intValue();
		// ������ջ�����±ߵ�Ԫ��
		int uk = underTop;

		if (k == goal) {
			System.out.print("\n������յ㲻����ͬ");
			return;
		}

		// ��ջ�����ڽӵ����εݹ���ã�������ȱ���
		for (int i = 0; i < this.total; i++) {
			// �бߣ����Ҳ������ϵ����µ���������
			if (this.matirx[k][i] == 1 && k != i) {
				// �ų���·
				if (stack.contains(i)) {
					// ��ĳ����A����ȷ������ڽӵ�Bʱ������������ͼ�����Դ���B��A��·�����ڻ�·�У�����Ҫ�ų��������
					// �ϸ���룬�������Ҳ��һ����
					if (i != uk) {
						System.out.print("\n�л�:");
						this.printStack(stack, i);
					}
					continue;
				}

				// ��ӡ·��
				if (i == goal) {
					System.out.print("\n·��:");
					this.printStack(stack, i);
					continue;
				}

				// ��ȱ���
				stack.push(i);
				dfsStack(k, goal, stack);
			}
		}

		stack.pop();
	}

	private void printMatrix() {
		System.out.println("----------------- matrix -----------------");
		System.out.println("---0-1-2-3-4-5-6-7-8--");
		System.out.println("---A-B-C-D-E-F-G-H-I--");
		for (int i = 0; i < this.total; i++) {
			System.out.print(" " + this.nodes[i] + "|");
			for (int j = 0; j < this.total; j++) {
				System.out.print(this.matirx[i][j] + "-");
			}
			System.out.print("\n");
		}
		System.out.println("----------------- matrix -----------------");
	}

	// ����[i][i]λ�ô���Ԫ��ֵΪ0��0��ʾͼ�еĶ���iδ�����ʣ�1��ʾͼ�еĶ���i�ѱ�����
	private void resetVisited() {
		for (int i = 0; i < this.total; i++) {
			this.matirx[i][i] = 0;
		}
	}

	// ��ʼ��ͼ����
	// 0---1---2---3---4---5---6---7---8---
	// A---B---C---D---E---F---G---H---I---
	private void initGrf() {
		// A-B, A-D, A-E
		this.matirx[0][1] = 1;
		this.matirx[1][0] = 1;
		this.matirx[0][3] = 1;
		this.matirx[3][0] = 1;
		this.matirx[0][4] = 1;
		this.matirx[4][0] = 1;
		// B-C
		this.matirx[1][2] = 1;
		this.matirx[2][1] = 1;
		// C-F
		this.matirx[2][5] = 1;
		this.matirx[5][2] = 1;
		// D-E, D-G
		this.matirx[3][4] = 1;
		this.matirx[4][3] = 1;
		this.matirx[3][6] = 1;
		this.matirx[6][3] = 1;
		// E-F, E-H
		this.matirx[4][5] = 1;
		this.matirx[5][4] = 1;
		this.matirx[4][7] = 1;
		this.matirx[7][4] = 1;
		// F-H, F-I
		this.matirx[5][7] = 1;
		this.matirx[7][5] = 1;
		this.matirx[5][8] = 1;
		this.matirx[8][5] = 1;
		// G-H
		this.matirx[6][7] = 1;
		this.matirx[7][6] = 1;
		// H-I
		this.matirx[7][8] = 1;
		this.matirx[8][7] = 1;
	}

	// ��ʼ��ͼ����
	// 0---1---2---3---4---5---6---7---8---
	// A---B---C---D---E---F---G---H---I---
	private void initGrf2() {
		// A-B, A-D, A-E
		this.matirx[0][1] = 1;
		this.matirx[1][0] = 1;
		this.matirx[0][3] = 1;
		this.matirx[3][0] = 1;
		this.matirx[0][4] = 1;
		this.matirx[4][0] = 1;
		// B-C
		this.matirx[1][2] = 1;
		this.matirx[2][1] = 1;
		// C-F
		this.matirx[2][5] = 1;
		this.matirx[5][2] = 1;
		// D-E
		this.matirx[3][4] = 1;
		this.matirx[4][3] = 1;
		// E-F, E-H
		this.matirx[4][5] = 1;
		this.matirx[5][4] = 1;
		this.matirx[4][7] = 1;
		this.matirx[7][4] = 1;
		// F-H, F-I
		this.matirx[5][7] = 1;
		this.matirx[7][5] = 1;
		this.matirx[5][8] = 1;
		this.matirx[8][5] = 1;
		// G-H
		this.matirx[6][7] = 1;
		this.matirx[7][6] = 1;
		// H-I
		this.matirx[7][8] = 1;
		this.matirx[8][7] = 1;
	}

	// ��ʼ��ͼ����
	// 0---1---2---3---4---5---6---7---8---
	// A---B---C---D---E---F---G---H---I---
	private void initGrf3() {
		// A-D, A-E
		this.matirx[0][3] = 1;
		this.matirx[3][0] = 1;
		this.matirx[0][4] = 1;
		this.matirx[4][0] = 1;
		// B-C
		this.matirx[1][2] = 1;
		this.matirx[2][1] = 1;
		// C-F
		this.matirx[2][5] = 1;
		this.matirx[5][2] = 1;
		// E-H, E-I
		this.matirx[4][7] = 1;
		this.matirx[7][4] = 1;
		this.matirx[4][8] = 1;
		this.matirx[8][4] = 1;
		// F-I
		this.matirx[5][8] = 1;
		this.matirx[8][5] = 1;
		// G-H
		this.matirx[6][7] = 1;
		this.matirx[7][6] = 1;
	}

	public static void main(String[] args) {
		String[] nodes = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
		GrfAllEdge grf = new GrfAllEdge(9, nodes);
		grf.initGrf();
		grf.printMatrix();

		System.out.print("\n------ Ѱ����㵽�յ������·����ʼ ------");
		grf.resetVisited();
		int origin = 0;
		int goal = 8;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(origin);
		grf.dfsStack(-1, goal, stack);
		System.out.print("\n------ Ѱ����㵽�յ������·������ ------");
	}

}
