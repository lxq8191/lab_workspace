package weka;

import java.io.File;  
import java.io.IOException;  

import weka.classifiers.functions.MultilayerPerceptron;  
import weka.core.Attribute;  
import weka.core.FastVector;  
import weka.core.Instance;  
import weka.core.Instances;  
import weka.core.converters.ArffLoader;  


public class Test2 {
	 public static void main(String[] args) throws IOException {  
		//���ڲ��ԣ������鱣��һЩ���ݣ������ݿ���ȡ������ͬ���  
		       //��ά�����һ�б�ʾ���µ�ʵ�����ݣ��ڶ������ϸ��µ����ݣ����ڸ����Ե������ݵ�Ԥ���
		double[][] a = {{-0.93,-0.995},{-0.93,-0.93},{-0.93,-0.93},{-0.95,-0.93},{-0.93,-0.95},  
		                           {-0.95,-0.93},{-0.93,-0.95},{-0.93,-0.93},{-0.95,-0.93},{-0.9,-0.95},  
		                          {-0.92,-0.9},{-0.575,-0.92},{-0.23,-0.575}};  
		         
		//����ѵ��������  
		File inputFile = new File("G:\\train.arff");//���ļ���Դ�������ķ������ӣ��������غ�·���滻��  
		ArffLoader atf = new ArffLoader();  
		try {  
			    atf.setFile(inputFile);  
		} catch (IOException e1) {  
		        e1.printStackTrace();  
			}  
		Instances instancesTrain = atf.getDataSet();   
		instancesTrain.setClassIndex(0);//����ѵ�����ݼ��������ԣ������ĸ������н���Ԥ�⣨���Ե��±��0��ʼ��  
			          
		//������Լ�����  
		 	        FastVector attrs = new FastVector();  
		 	              
			        Attribute ratio = new Attribute(null, 0);//�������ԣ�����Ϊ�������ƺ����Ժţ������ԺŲ���Ӱ��FastVector�����Ե�˳��  
		 	        Attribute preratio = new Attribute("PRE",2);  
		 	              
		 	        attrs.addElement(ratio);//��FastVector��������ԣ�������FastVector�е�˳������ӵ��Ⱥ�˳��ȷ����  
			        attrs.addElement(preratio);  
		 	              
			        Instances instancesTest = new Instances("bp",attrs,attrs.size());//����ʵ�����������ݼ�������Ϊ���ƣ�FastVector���͵����Լ����Լ����Լ��Ĵ�С�������ݼ���������  
		 	              
		 	        instancesTest.setClass(ratio);//�������ݼ��������ԣ������ĸ������н���Ԥ��  
			          
		 	        for(int k=0;k<13;k++){  
		 	            Instance ins = new Instance(attrs.size());//����ʵ������һ������  
			            ins.setDataset(instancesTest);//���ø������ݶ�Ӧ�����ݼ��������ݼ������Խ��ж�Ӧ  
		 	            ins.setValue(ratio, a[k][0]);//��������ÿ�����Ե�ֵ  
			            ins.setValue(preratio, a[k][1]);   
			            instancesTest.add(ins);//������������ӵ����ݼ���  
			        }  
		 	          
			        MultilayerPerceptron m_classifier = new MultilayerPerceptron();//�����㷨ʵ����Ҫʹ���������㷨��ֻ�ð��໻����Ӧ�ļ���  
		 	          
			        try {  
		 	            m_classifier.buildClassifier(instancesTrain); //����ѵ��  
		 	        } catch (Exception e) {  
			            e.printStackTrace();  
			        }  
			          
			        for(int i = 0;i<13;i++){//���Է�����  
		 	            //instancesTest.instance(i)��õ�����ģ��Ԥ��Ľ��ֵ��instancesTest.instance(i).classValue()��õ��ǲ��Լ������Ե�ֵ  
			            //�˴��ǰ�Ԥ��ֵ��ʵ��ֵͬʱ��������жԱ�  
			            try {  
			                System.out.println(m_classifier.classifyInstance(instancesTest.instance(i))+",,,"+instancesTest.instance(i).classValue());  
			            } catch (Exception e) {  
		                e.printStackTrace();  
			            }  
			        }  
			        System.out.println("bp success!");  
			    }  
		  
			}  
