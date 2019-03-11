package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        // TODO
        head = new Node(null);
        tail = head;
        head.next = tail;
        head.prev = tail;
        tail.next = head;
        tail.prev = head;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (size == 0){
            head.val = c;
            head.next = head;

            tail.val = c;
            tail.next = head;

            size = 1;

        } else {
            Node new_node = new Node(c, tail, head);
            tail.next = new_node;
            head.prev = new_node;
            tail = new_node;
            size += 1;
        }

    }

    public void addFirst(Chicken c) {
        // TODO
        if (size == 0){
            head.val = c;
            head.next = tail;
            head.prev = tail;

            tail.val = c;
            tail.next = head;
            tail.prev = head;

            size = 1;

        } else {
            Node new_node = new Node(c, tail, head);
            head.prev = new_node;
            tail.next = new_node;
            head = new_node;
            size += 1;
        }

    }

    public Chicken get(int index) {

        // Check for exceptions
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }


        Node current_node = head;
        for(int i = 0; i < index; i++){
            current_node = current_node.next;
        }
        return current_node.val;

    }

    public Chicken remove(int index) {
        // Check for exceptions
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        // Handle the edge cases
        if(index == 0){
            return removeFirst();
        }
        if(index == (size)){
            return removeLast();
        }

        //
        Node current_node = head;
        for(int i = 0; i < index; i++){
            current_node = current_node.next;
        }
        try {
            return current_node.val;
        } finally {
            // Remove the element
            current_node.prev.next = current_node.next;
            current_node.next.prev = current_node.prev;
            size -= 1;


        }

    }

    public Chicken removeFirst() {

        // Make sure the array is not already empty
        if (size == 0){
            throw new IndexOutOfBoundsException();
        }

        // Clear out head and tail if the array has only one element left
        if (size == 1){
            try {
                return head.val;
            } finally {
                head.next = null;
                head.prev = null;
                tail.next = null;
                tail.prev = null;
                tail.val = null;
                head.val = null;
                size -= 1;
            }
        }
        // if size > 1

        try{
            return head.val;
        } finally{
            tail.next = head.next;
            head.next.prev = tail;
            head = tail.next;
            size -= 1;
        }

    }

    public Chicken removeLast() {
        // Make sure the array is not already empty
        if (size == 0){
            throw new IndexOutOfBoundsException();
        }

        // Clear out head and tail if the array has only one element left
        if (size == 1){
            try {
                return head.val;
            } finally {
                head.next = null;
                head.prev = null;
                tail.next = null;
                tail.prev = null;
                tail.val = null;
                head.val = null;
                size -= 1;
            }
        }

        try{
            return head.val;
        } finally{
            head.prev = tail.prev;
            tail.prev.next = head;
            size -= 1;
        }

    }
}