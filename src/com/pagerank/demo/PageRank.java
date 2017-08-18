package com.pagerank.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PageRank {

	//����ȫ�ֱ��� alpha ����ֵ
	public static final double ALPHA=0.85;
	public static final double DISTANCE=0.000001;
	
	public static void main(String[] args) {

		System.out.println("ALPHA��ֵΪ��"+ALPHA);
		//��ʼ������q
		List<Double> q=new ArrayList<Double>();
		q.add(new Double(2.14335103032906));
		q.add(new Double(0.4690253246490811));
		q.add(new Double(0.152093449701467));  
		q.add(new Double(2.751926907462932));
		System.out.println("��ʼ������qΪ��");
		printVec(q);//�������
		System.out.println("��ʼ����GΪ��");
		printMatrix(getG(ALPHA));
		List<Double> pagerank=calPageRank(q,ALPHA);
		System.out.println("���PageRank��");
		printVec(pagerank);
		System.out.println();
	}

	public static List<Double> calPageRank(List<Double> q1, double a) {
		List<List<Double>> g=getG(a);//��ȡ��ʼ������
		List<Double> q=null;
		while(true){
			q=vectorMulMatrix(g,q1);//��������������õ�����
			//������������֮��ľ���
			double dis=calDistance(q,q1);
			System.out.println("��������֮��ľ���Ϊ��"+dis);
			if (dis<=DISTANCE) {
				System.out.println("q1:");
				printVec(q1);
				System.out.println("q");
				printVec(q);
				break;
			}
			q1=q;
		}
		return q;
	}

	public static double calDistance(List<Double> q, List<Double> q1) {
		// TODO Auto-generated method stub
		double distance=0;
		if (q.size()!=q1.size()) {
			return -1;
		}
		for(int i=0;i<q.size();i++){
			distance+=Math.pow(q.get(i).doubleValue()-q1.get(i).doubleValue(), 2);
		}
		return Math.sqrt(distance);
	}

	public static List<Double> vectorMulMatrix(List<List<Double>> m, List<Double> v) {
		if (m==null||v==null||m.size()<=0||m.get(0).size()!=v.size()) {
			return null;
		}
		List<Double> list=new ArrayList<>();
		for(int i=0;i<m.size();i++){
			double sum=0;
			for(int j=0;j<m.get(i).size();j++){
				double temp=m.get(i).get(j).doubleValue()
						*v.get(j).doubleValue();
				sum+=temp;
			}
			list.add(sum);
		}
		return list;
	}

	/**
	 * ��ӡ�������
	 * @param m
	 */
	public static void printMatrix(List<List<Double>> m) {
		// TODO Auto-generated method stub
		for(int i=0;i<m.size();i++){
			for(int j=0;j<m.get(i).size();j++){
				System.out.print(m.get(i).get(j)+"\t");
			}
			System.out.println();
		}
	}

	/**
	 * �����ȡ��ʼ�ľ���G
	 * @param a  Ϊalpha��ֵ
	 * @return  ��ʼ����
	 */
	public static List<List<Double>> getG(double a) {
		int n=getS().size();//��ʼS������ͼ����
		//���˾���
		List<List<Double>> as=numberMulMatrix(getS(),a);
		List<List<Double>> nu=numberMulMatrix(getU(),(1-a)/n);
		//��������֮��
		List<List<Double>> g=addMatrix(as,nu);
		return g;
	}
	public static List<List<Double>> addMatrix(List<List<Double>> list1, List<List<Double>> list2) {
		List<List<Double>> list=new ArrayList<>();
		
		if (list1.size()!=list2.size()||list1.size()<=0||list2.size()<=0) {
			return null;
		}
		for(int i=0;i<list1.size();i++){
			list.add(new ArrayList<>());
			for(int j=0;j<list1.get(i).size();j++){
				double temp=list1.get(i).get(j).doubleValue()+
							list2.get(i).get(j).doubleValue();
				list.get(i).add(new Double(temp));
			}
		}
		return list;
	}

	/**
	 * ����һ�������Ծ���
	 * @param s ����s
	 * @param a  double ���͵���
	 * @return һ���µľ���
	 */
	private static List<List<Double>> numberMulMatrix(List<List<Double>> s, double a) {
		List<List<Double>> list=new ArrayList<>();
		for(int i=0;i<s.size();i++){
			list.add(new ArrayList<>());
			for(int j=0;j<s.get(i).size();j++){
				double temp=a*s.get(i).get(j).doubleValue();
				list.get(i).add(new Double(temp));
			}
		}
		return list;
	}

	/**
	 * ��ʼȫΪ1 ��n�׾���
	 * @return
	 */


	private static List<List<Double>> getU() {
		// TODO Auto-generated method stub
		List<Double> row1=new ArrayList<>();
		row1.add(new Double(1));
		row1.add(new Double(1));
		row1.add(new Double(1));
		row1.add(new Double(1));
		List<Double> row2=new ArrayList<>();
		row2.add(new Double(1));
		row2.add(new Double(1));
		row2.add(new Double(1));
		row2.add(new Double(1));
		List<Double> row3=new ArrayList<>();
		row3.add(new Double(1));
		row3.add(new Double(1));
		row3.add(new Double(1));
		row3.add(new Double(1));
		List<Double> row4=new ArrayList<>();
		row4.add(new Double(1));
		row4.add(new Double(1));
		row4.add(new Double(1));
		row4.add(new Double(1));
		
		List<List<Double>> u=new ArrayList<>();
		u.add(row1);
		u.add(row2);
		u.add(row3);
		u.add(row4);
		
		return u;
	}

	private static List<List<Double>> getS() {
		List<Double> row1=new ArrayList<>();
		row1.add(new Double(0));
		row1.add(new Double(0));
		row1.add(new Double(0));
		row1.add(new Double(0));
		List<Double> row2=new ArrayList<>();
		row2.add(new Double(1/3.0));
		row2.add(new Double(0));
		row2.add(new Double(0));
		row2.add(new Double(1));
		List<Double> row3=new ArrayList<>();
		row3.add(new Double(1/3.0));
		row3.add(new Double(1/2.0));
		row3.add(new Double(0));
		row3.add(new Double(0));
		List<Double> row4=new ArrayList<>();
		row4.add(new Double(1/3.0));
		row4.add(new Double(1/2.0));
		row4.add(new Double(1));
		row4.add(new Double(0));
		
		List<List<Double>> s=new ArrayList<>();
		s.add(row1);
		s.add(row2);
		s.add(row3);
		s.add(row4);
		
		return s;
	}

	/**
	 * ��ӡ���һά����
	 * @param q
	 */
	public static void printVec(List<Double> v) {
		// TODO Auto-generated method stub
		for(int i=0;i<v.size();i++){
			System.out.println(v.get(i)+",");
		}
		System.out.println();
	}

	/**
	 * ��ȡһ����ʼ���������
	 * @param n   ����q��ά��
	 * @return  һ�����������q,ÿһά����һ��0-5֮��������
	 */
	public static List<Double> getInitQ(int n){
		Random random=new Random();
		List<Double> q=new ArrayList<>();
		for(int i=0;i<n;i++){
			q.add(new Double(5*random.nextDouble()));
		}
		return q;
	}
	
	
	
	
}
