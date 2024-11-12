public class test {
    public static void main(String[] args)
    {
        SinglyLinkedList intList = new SinglyLinkedList();

        intList.insertAtEnd(1);
        intList.insertAtEnd(2);
        intList.insertAtEnd(3);

        System.out.println("Size of Integer List: " + intList.getSize());
    }
}
