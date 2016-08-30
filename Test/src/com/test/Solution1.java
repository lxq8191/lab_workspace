package com.test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution1 {
	public static int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		int[] tempArr;
		tempArr = nums.clone();
		int temp = 0;
		int head = 0,tail = nums.length-1;
		Arrays.sort(tempArr);
		for(;head < tail&&tail > 0;){
			temp = tempArr[head]+tempArr[tail];
			if(temp==target){
				for (int i = 0; i < nums.length; i++) {
					if(nums[i]==tempArr[head]){
						result[0] = i;
						break;
					}
				}
				for (int i = 0; i < nums.length; i++) {
					if(nums[i]==tempArr[tail]){
						result[1] = i;
						if(result[0]!=result[1]){
							break;
						}
					}
				}
				break;
			}else if(temp>target){
				tail--;
			}else if(temp<target){
				head++;
			}
		}
		return result;
    }
	public static void main(String[] args) {
		int[] num = {0,4,3,0};
		int s = 0;
		System.out.println(Arrays.toString(twoSum(num,s)));
	}
}
