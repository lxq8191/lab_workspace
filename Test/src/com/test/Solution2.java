package com.test;

public class Solution2 {
	static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; }
	}
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if(l1==null) return l2;
		if(l2==null) return l1;
		
		ListNode ret = l1;
		ListNode pre = new ListNode(0);
		pre.next = l1;
		
		int flag = 0;
		while(l1!=null&&l2!=null){
			l1.val = l1.val + l2.val + flag;
			flag = l1.val/10;
			l1.val = l1.val % 10;
			pre = l1;
			l1 = l1.next;
			l2 = l2.next;
		}
		if(l2!=null){
			pre.next = l2;
			l1 = l2;
		}
		while (l1 != null) {
            l1.val += flag;
            flag = l1.val / 10;
            l1.val = l1.val % 10;
            pre = l1;
            l1 = l1.next;
        }
        
        if (flag > 0) {
            ListNode node = new ListNode(1);
            pre.next = node;
        }
        
		return ret;
    }
	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		ListNode r =  addTwoNumbers(l1,l2);
		while(r!=null){
			System.out.println(r.val);
			r = r.next;
		}
	}
}
