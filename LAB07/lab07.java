class lab07 {
    public static void main(String[] args) {
        List list = new SinglyLinkedList();
        list.add("B");
        list.add("C");
        list.add("B");
        list.add("D");
        System.out.println(list);
        List list2 = new SinglyLinkedList();
        list2.add("A");
        list2.add("B");
        list2.add("B");
        list2.add("C");
        list2.add("B");
        list2.add("C");
        list2.add("B");
        list2.add("D");
        System.out.println(list2);
        System.out.println(list.isSublist(list2));
    }
}