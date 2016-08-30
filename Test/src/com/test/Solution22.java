package com.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution22 {
	Set<String> tempSet = new HashSet<String>();
	  public List<String> generateParenthesis(int n) {
		  List<String> result = new ArrayList<String>();
		  if(n==0){
			  return result;
		  }
		  result.add("()");
		  for (int i = 1; i < n; i++) {//ѭ��n-1�Σ������n-1������
			  result = addParenthesis(result);
		}
		  return result;
	  }
	public List<String> addParenthesis(List<String> list) {
		List<String> al = new ArrayList<String>();
        int size = list.size();
        String temp;//��ʱ����ַ���
        for(int i = 0; i < size;i++){
            String str = list.get(i);
            temp = "";
            //��ȡ�����ַ���ѭ������(),���ظ�������list
            for(int j = 0;j < str.length();j++){
                temp = str.substring(0,j) + "()" + str.substring(j);
                if(tempSet.add(temp)){//�ж��Ƿ�����
                    al.add(temp);
                }
            }
        }
        return al;
	}
}
