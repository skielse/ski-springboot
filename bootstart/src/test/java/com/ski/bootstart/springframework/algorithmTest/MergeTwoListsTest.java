package com.ski.bootstart.springframework.algorithmTest;

import com.ski.bootstart.algorithm.MergeTwoLists;
import com.ski.bootstart.algorithm.MergeTwoLists.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author wangzijie
 * @date 2024/1/19
 */
public class MergeTwoListsTest {

    @Test
    public void testMergeTwoLists_emptyLists() {
        ListNode l1 = null;
        ListNode l2 = null;
        ListNode merged = MergeTwoLists.mergeTwoLists(l1, l2);
        Assertions.assertNull(merged);
    }

    @Test
    public void testMergeTwoLists_oneListIsEmpty() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = null;
        ListNode merged = MergeTwoLists.mergeTwoLists(l1, l2);
        Assertions.assertEquals(1, merged.val);
        Assertions.assertNull(merged.next);
    }

    @Test
    public void testMergeTwoLists_bothListsAreNotEmptyAndFirstNodeLessThanSecondNode() {
        ListNode l1 = new ListNode(1);
        ListNode l1_next = new ListNode(2);
        ListNode l1_next_next = new ListNode(4);
        l1.next = l1_next;
        l1_next.next = l1_next_next;

        ListNode l2 = new ListNode(2);
        ListNode l2_next = new ListNode(3);
//        ListNode l2_next_next = new ListNode(1);
        l2.next = l2_next;
//        l2_next.next = l2_next_next;

        ListNode merged = MergeTwoLists.mergeTwoLists(l1, l2);
        Assertions.assertEquals(1, merged.val);
        Assertions.assertEquals(2, merged.next.val);
        Assertions.assertEquals(2, merged.next.next.val);
        Assertions.assertEquals(3, merged.next.next.next.val);
        Assertions.assertEquals(4, merged.next.next.next.next.val);
        Assertions.assertNull(merged.next.next.next.next.next);
    }

    @Test
    public void testMergeTwoLists_bothListsAreNotEmptyAndFirstNodeGreaterThanOrEqualToSecondNode() {
        ListNode l1 = new ListNode(4);
        ListNode l1_next = new ListNode(2);
        ListNode l1_next_next = new ListNode(1);
        l1.next = l1_next;
        l1_next.next = l1_next_next;

        ListNode l2 = new ListNode(3);
        ListNode l2_next = new ListNode(2);
        ListNode l2_next_next = new ListNode(1);
        l2.next = l2_next;
        l2_next.next = l2_next_next;

        ListNode merged = MergeTwoLists.mergeTwoLists(l1, l2);
        Assertions.assertEquals(1, merged.val);
        Assertions.assertEquals(2, merged.next.val);
        Assertions.assertEquals(2, merged.next.next.val);
        Assertions.assertEquals(3, merged.next.next.next.val);
        Assertions.assertEquals(4, merged.next.next.next.next.val);
        Assertions.assertNull(merged.next.next.next.next.next);
    }
}
