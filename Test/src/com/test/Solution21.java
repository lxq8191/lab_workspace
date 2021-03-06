package com.test;


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l2==null) 
			return l1;
		if(l1==null) 
			return l2;
		ListNode head = null;
        ListNode end = null;
        while(l1!=null&&l2!=null){
        	int temp1 = l1.val;
        	int temp2 = l2.val;
        	if(temp1<=temp2){
        		 if(head == null){
                     head = l1; 
                     end = head;
                 } 
        		 else{
        			 end.next = l1;
        			 end = end.next;
        		 }
        		 l1 = l1.next;
        	}
        	else{
        		if(head == null){
                    head = l2; 
                    end = head;
                } 
       		 else{
       			 end.next = l2;
       			 end = end.next;
       		 }
       		 l2 = l2.next;
        	}
        	if(l1!=null){
        		end.next = l1;
        	}
        	if(l2!=null){
        		end.next = l2;
        	}
        }
    	return head;
    }
}
