import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main
{
    public static void main(String[] args)
    {
        DoublyLinkedList<String> intList = new DoublyLinkedList<>();

        loadContacts(intList);

        intList.insertionSortSurname();

        System.out.println("Size of Integer List: " + intList.getSize());

        new UI(intList);
    }

    private static void loadContacts(DoublyLinkedList<String> intList)
    {
        String url = "jdbc:mysql://windhoek.erasmus.na:3306/PhoneBook";
        String user = "intellij";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String selectSQL = "SELECT phoneNumber, firstName, lastName FROM records";

            PreparedStatement pstmt = conn.prepareStatement(selectSQL);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                String phoneNumber = rs.getString("phoneNumber");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                intList.insertAtEnd(phoneNumber, firstName, lastName);

                System.out.println("Loaded item: " + lastName);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}