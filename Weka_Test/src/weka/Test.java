package weka;

import java.io.File;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class Test {
	public static void main(String[] args) throws Exception {
		Classifier m_classifier = new J48();
		// ѵ�������ļ�
		File inputFile = new File("D:/Program Files/Weka-3-6/data/cpu.with.vendor.arff");
		ArffLoader atf = new ArffLoader();
		atf.setFile(inputFile);
		// ����ѵ���ļ�
		Instances instancesTrain = atf.getDataSet();
		instancesTrain.setClassIndex(0);
		// ѵ��
		m_classifier.buildClassifier(instancesTrain);

		// ���������ļ�
		inputFile = new File("D:/Program Files/Weka-3-6/data/cpu.with.vendor.arff");
		atf.setFile(inputFile);
		// ��������ļ�
		Instances instancesTest = atf.getDataSet();
		// ���÷������������кţ���һ��Ϊ0�ţ���instancesTest.numAttributes()����ȡ����������
		instancesTest.setClassIndex(0);

		// ��������ʵ����
		double sum = instancesTest.numInstances();
		double right = 0.0f;
		// ���Է�����
		for (int i = 0; i < sum; i++) {
			// ���Ԥ��ֵ�ʹ�ֵ��ȣ����������еķ������ṩ����Ϊ��ȷ�𰸣�����������壩
			if (m_classifier.classifyInstance(instancesTest.instance(i)) == instancesTest.instance(i).classValue()) {
				// ��ȷֵ��1
				right++;
			}
		}
		System.out.println("J48 classification precision:" + (right / sum));
	}
}
