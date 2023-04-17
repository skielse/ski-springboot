package com.ski.bootstart.util;

/**
 * @author wangzijie
 * @date 2023/4/17
 */
class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}

class CircularLinkedList {
    private Node head;
    private Node tail;

    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // 在链表末尾添加元素
    public void add(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        tail.next = head;
    }

    // 在指定位置插入元素
    public void insert(int value, int position) {
        Node newNode = new Node(value);
        if (position == 0) {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        } else {
            Node current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (current == tail) {
                tail = newNode;
            }
        }
    }

    // 从链表中删除指定位置的元素
    public void remove(int position) {
        if (head == null) {
            return;
        }
        if (position == 0) {
            head = head.next;
            tail.next = head;
        } else {
            Node current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            if (current.next == head) {
                tail = current;
            }
        }
    }

    // 打印链表中的元素
    public void print() {
        if (head == null) {
            return;
        }
        Node current = head;
        do {
            System.out.print(current.value + " ");
            current = current.next;
        } while (current != head);
    }
}
