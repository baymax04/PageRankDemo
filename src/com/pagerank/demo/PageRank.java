package com.pagerank.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PageRank {

	//定义全局变量 alpha 和阈值
	public static final double ALPHA=0.85;
	public static final double DISTANCE=0.000001;
	
	public static void main(String[] args) {

		System.out.println("ALPHA的值为："+ALPHA);
		//初始化向量q
		List<Double> q=new ArrayList<Double>();
		q.add(new Double(2.14335103032906));
		q.add(new Double(0.4690253246490811));
		q.add(new Double(0.152093449701467));  
		q.add(new Double(2.751926907462932));
		System.out.println("初始的向量q为：");
		printVec(q);//输出向量
		System.out.println("初始矩阵G为：");
		printMatrix(getG(ALPHA));
		List<Double> pagerank=calPageRank(q,ALPHA);
		System.out.println("输出PageRank：");
		printVec(pagerank);
		System.out.println();
	}

	public static List<Double> calPageRank(List<Double> q1, double a) {
		List<List<Double>> g=getG(a);//获取初始化矩阵
		List<Double> q=null;
		while(true){
			q=vectorMulMatrix(g,q1);//矩阵乘以向量，得到向量
			//计算两个向量之间的距离
			double dis=calDistance(q,q1);
			System.out.println("两个向量之间的距离为："+dis);
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
	 * 打印输出矩阵
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
	 * 计算获取初始的矩阵G
	 * @param a  为alpha的值
	 * @return  初始矩阵
	 */
	public static List<List<Double>> getG(double a) {
		int n=getS().size();//初始S矩阵，由图所得
		//数乘矩阵
		List<List<Double>> as=numberMulMatrix(getS(),a);
		List<List<Double>> nu=numberMulMatrix(getU(),(1-a)/n);
		//两个矩阵之和
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
	 * 计算一个数乘以矩阵
	 * @param s 矩阵s
	 * @param a  double 类型的数
	 * @return 一个新的矩阵
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
	 * 初始全为1 的n阶矩阵
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
	 * 打印输出一维向量
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
	 * 获取一个初始的随机向量
	 * @param n   向量q的维度
	 * @return  一个随机的向量q,每一维都是一个0-5之间的随机数
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
