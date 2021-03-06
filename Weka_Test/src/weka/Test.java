package weka;

import java.io.File;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class Test {
	public static void main(String[] args) throws Exception {
		Classifier m_classifier = new J48();
		// 训练语料文件
		File inputFile = new File("D:/Program Files/Weka-3-6/data/cpu.with.vendor.arff");
		ArffLoader atf = new ArffLoader();
		atf.setFile(inputFile);
		// 读入训练文件
		Instances instancesTrain = atf.getDataSet();
		instancesTrain.setClassIndex(0);
		// 训练
		m_classifier.buildClassifier(instancesTrain);

		// 测试语料文件
		inputFile = new File("D:/Program Files/Weka-3-6/data/cpu.with.vendor.arff");
		atf.setFile(inputFile);
		// 读入测试文件
		Instances instancesTest = atf.getDataSet();
		// 设置分类属性所在行号（第一行为0号），instancesTest.numAttributes()可以取得属性总数
		instancesTest.setClassIndex(0);

		// 测试语料实例数
		double sum = instancesTest.numInstances();
		double right = 0.0f;
		// 测试分类结果
		for (int i = 0; i < sum; i++) {
			// 如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
			if (m_classifier.classifyInstance(instancesTest.instance(i)) == instancesTest.instance(i).classValue()) {
				// 正确值加1
				right++;
			}
		}
		System.out.println("J48 classification precision:" + (right / sum));
	}
}
