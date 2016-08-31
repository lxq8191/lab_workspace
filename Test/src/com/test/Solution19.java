package com.test;

public class Solution19 {
	class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	
	 public ListNode removeNthFromEnd(ListNode head, int n) {
		if(head==null) return null;
		ListNode p = head;
		ListNode q = head;
		for (int i = 0; i < n; i++) {
			q = q.next;
		}
		if(q==null){
			return head.next;
		}
		while((q.next)!= null){
			p = p.next;
			q = q.next;
		}
		p.next = p.next.next;
		return head;
	 }
	
}
