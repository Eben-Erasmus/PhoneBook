public class DoublyLinkedList<T>
{
    DoublyNode<T> head;

    public int getSize()
    {
        int count = 0;
        DoublyNode<T> temp = head;
        while (temp != null)
        {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public void insertAtEnd(T data, String firstName, String lastName)
    {
        DoublyNode<T> newNode = new DoublyNode<>(data, firstName, lastName);
        if(head == null)
        {
            head = newNode;
            return;
        }
        DoublyNode<T> temp = head;
        while (temp.next != null)
        {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.prev = temp;
    }

    public void deleteAtGivenNumber(String number)
    {
        DoublyNode<T> temp = head;

        while (temp != null && !temp.data.equals(number))
        {
            temp = temp.next;
        }

        if (temp == null)
        {
            System.out.println("Number not found");
            return;
        }

        //First node
        if (temp == head)
        {
            head = temp.next;
        }
        //Last node
        else if (temp.next == null)
        {
            temp.prev.next = null;
        }
        //Any other node
        else
        {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }

    public void insertionSortSurname()
    {
        DoublyNode<T> sorted = null;
        DoublyNode<T> current = head;

        while (current != null)
        {
            DoublyNode<T> next = current.next;
            current.prev = null;
            current.next = null;
            sorted = sortedInsert(sorted, current);
            current = next;
        }
        head = sorted;
    }

    public DoublyNode<T> sortedInsert(DoublyNode<T> sorted, DoublyNode<T> newNode)
    {
        if (sorted == null || ((String) sorted.lastName).compareTo((String) newNode.lastName) >= 0)
        {
            newNode.next = sorted;
            if (sorted != null)
            {
                sorted.prev = newNode;
            }
            sorted = newNode;
        }
        else
        {
            DoublyNode<T> current = sorted;
            while (current.next != null && ((String) current.next.lastName).compareTo((String) newNode.lastName) < 0)
            {
                current = current.next;
            }
            newNode.next = current.next;
            if (current.next != null)
            {
                current.next.prev = newNode;
            }
            current.next = newNode;
            newNode.prev = current;
        }
        return sorted;
    }

    public void print()
    {
        DoublyNode<T> temp = head;
        while (temp != null)
        {
            System.out.println(temp.data + " " + temp.firstName + " " + temp.lastName);
            temp = temp.next;
        }
    }

    public int binarySearch(String target)
    {
        if (head == null)
        {
            return -1;
        }

        DoublyNode<T> low = head;
        DoublyNode<T> high = getTail();

        while (low != null && high != null && low != high.next)
        {
            DoublyNode<T> middle = getMiddle(low, high);

            if (middle.lastName.equals(target))
            {
                return findPosition(middle);
            }
            else if (((String) middle.lastName).compareTo(target) < 0)
            {
                low = middle.next;
            }
            else
            {
                high = middle.prev;
            }
        }
        return -1;
    }

    public DoublyNode<T> getTail()
    {
        DoublyNode<T> temp = head;
        while (temp.next != null)
        {
            temp = temp.next;
        }
        return temp;
    }

    public DoublyNode<T> getMiddle(DoublyNode<T> low, DoublyNode<T> high)
    {
        if (low == null)
        {
            return null;
        }

        DoublyNode<T> slow = low;
        DoublyNode<T> fast = low;

        while (fast != high && fast.next != high)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public int findPosition(DoublyNode<T> node)
    {
        int position = 0;
        DoublyNode<T> temp = head;
        while (temp != node)
        {
            position++;
            temp = temp.next;
        }
        return position;
    }

    public void updateRecord(String phoneNumber, String newPhoneNumber)
    {
        if (head == null) {
            System.out.println("Phonebook is empty.");
            return;
        }

        DoublyNode<T> currentNode = head;

        while (currentNode != null) {
            if (currentNode.data == phoneNumber) {
                currentNode.data = (T) newPhoneNumber;

                return;
            }
            currentNode = currentNode.next;
        }
    }
}