public class DoublyNode<T>
{
    T data;
    String firstName;
    String lastName;
    DoublyNode next;
    DoublyNode prev;

    public DoublyNode(T data, String firstName, String lastName)
    {
        this.data = data;
        this.firstName = firstName;
        this.lastName = lastName;
        this.next = null;
        this.prev = null;
    }
}
