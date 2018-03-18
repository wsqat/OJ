## 题目描述

打乱链表顺序

record-list

Given a singly linked list L: L 0→L 1→…→L n-1→L n,
reorder it to: L 0→L n →L 1→L n-1→L 2→L n-2→…

You must do this in-place without altering the nodes' values.

For example,
Given{1,2,3,4}, reorder it to{1,4,2,3}.

## 解题思路
快慢指针找到中间节点，将后面的链表反转（前插法），合并链表
- 快慢指针找到链表中点
- 拆分链表，中点之后逆序显示
- 依次插入到前半截链表中


## AC代码

```
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {
        if (head==null || head.next==null){
            return ;
        }
        
        //找到链表中点
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        //拆分链表，中点之后逆序显示
        ListNode after = slow.next;
        ListNode pre = null;
        slow.next = null;
        while (after!=null){
            ListNode temp = after.next;
            after.next = pre;
            pre = after;
            after = temp;
        }
        
        //依次插入到前半截链表中
        ListNode first = head;
        after = pre;
        while (first!=null&& after!=null){
            ListNode ftemp = first.next;
            ListNode atemp = after.next;
            first.next = after;
            first = ftemp;
            after.next = ftemp;
            after = atemp;
        }
    }
}
```