package com.test;

public class Solution112 {
	public class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
		 }
	public boolean DFS(TreeNode node,int sum,int curSum){
		if(node==null) return false;
		if(node.left==null&&node.left==null){
			return curSum + node.val == sum;
		}
		return DFS(node.left,sum,curSum + node.val) || DFS(node.right,sum,curSum + node.val);
		
	}
	public boolean hasPathSum(TreeNode root, int sum) {
		return DFS(root,sum,0);
    }
}
