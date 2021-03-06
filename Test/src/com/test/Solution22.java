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
		  for (int i = 1; i < n; i++) {//循环n-1次，再添加n-1对括号
			  result = addParenthesis(result);
		}
		  return result;
	  }
	public List<String> addParenthesis(List<String> list) {
		List<String> al = new ArrayList<String>();
        int size = list.size();
        String temp;//临时存放字符串
        for(int i = 0; i < size;i++){
            String str = list.get(i);
            temp = "";
            //对取出的字符串循环插入(),不重复即插入list
            for(int j = 0;j < str.length();j++){
                temp = str.substring(0,j) + "()" + str.substring(j);
                if(tempSet.add(temp)){//判断是否已有
                    al.add(temp);
                }
            }
        }
        return al;
	}
}
