import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UI
{
    public UI(DoublyLinkedList<String> intList)
    {
        JFrame ui = new JFrame("PhoneBook");
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ui.setSize(1000, 1000);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        ui.add(panel);

        JPanel panelWithItems = new JPanel();
        panelWithItems.setLayout(new GridLayout(0, 1, 0, 5));

        JScrollPane scrollPane = new JScrollPane(panelWithItems);
        scrollPane.setBounds(10, 100, 1900, 900);
        panel.add(scrollPane);


        JTextField searchBar = new JTextField(20);
        searchBar.setBounds(10, 10, 165, 25);
        panel.add(searchBar);

        JButton searchContact = new JButton("Search Contact");
        searchContact.setBounds(180, 10, 165, 25);
        panel.add(searchContact);

        JButton addContact = new JButton("Add Contact");
        addContact.setBounds(360, 10, 165, 25);
        addContact.addActionListener(e -> {
            JFrame addContactWindow = new JFrame("Add Contact");
            addContactWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addContactWindow.setSize(1000, 1000);

            JPanel addContactPanel = new JPanel();
            addContactPanel.setLayout(null);
            addContactWindow.add(addContactPanel);

            JLabel phoneLabel = new JLabel("Phone Number: ");
            phoneLabel.setBounds(10, 10, 165, 25);
            addContactPanel.add(phoneLabel);

            JTextField phoneField = new JTextField(20);
            phoneField.setBounds(10, 40, 165, 25);
            addContactPanel.add(phoneField);

            JLabel firstNameLabel = new JLabel("First Name: ");
            firstNameLabel.setBounds(10, 70, 165, 25);
            addContactPanel.add(firstNameLabel);

            JTextField firstNameField = new JTextField(20);
            firstNameField.setBounds(10, 100, 165, 25);
            addContactPanel.add(firstNameField);

            JLabel lastNameLabel = new JLabel("Last Name: ");
            lastNameLabel.setBounds(10, 130, 165, 25);
            addContactPanel.add(lastNameLabel);

            JTextField lastNameField = new JTextField(20);
            lastNameField.setBounds(10, 160, 165, 25);
            addContactPanel.add(lastNameField);

            JButton addButton = new JButton("Add");
            addButton.setBounds(10, 190, 165, 25);
            addButton.addActionListener(event -> {
                String phoneNumber = phoneField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                intList.insertAtEnd(phoneNumber, firstName, lastName);
                addContactWindow.dispose();
                loadProducts(panelWithItems, intList, null);
                intList.insertionSortSurname();
            });
            addContactPanel.add(addButton);

            addContactWindow.setVisible(true);
        });
        panel.add(addContact);

        loadProducts(panelWithItems, intList, null);

        searchContact.addActionListener(e -> {
            String query = searchBar.getText();
            loadProducts(panelWithItems, intList, query);
        });

        ui.setVisible(true);
    }

    private void loadProducts(JPanel panelWithItems, DoublyLinkedList<String> intList, String query)
    {
        panelWithItems.removeAll();
        panelWithItems.setLayout(new BoxLayout(panelWithItems, BoxLayout.Y_AXIS));

        if (query != null && !query.trim().isEmpty())
        {
            int position = intList.binarySearch(query);
            if (position != -1)
            {
                DoublyNode<String> temp = intList.head;
                for (int i = 0; i < position; i++)
                {
                    temp = temp.next;

                    String phoneNumber = temp.data;
                    String firstName = temp.firstName;
                    String lastName = temp.lastName;

                    JPanel productPanel = createRecordPanel(panelWithItems, intList, phoneNumber, firstName, lastName, null);
                    panelWithItems.add(productPanel);
                }
            }
        }
        else
        {
            DoublyNode<String> temp = intList.head;
            while (temp != null) {
                String phoneNumber = temp.data;
                String firstName = temp.firstName;
                String lastName = temp.lastName;

                JPanel productPanel = createRecordPanel(panelWithItems, intList, phoneNumber, firstName, lastName, null);
                panelWithItems.add(productPanel);

                temp = temp.next;
            }
        }

        panelWithItems.revalidate();
        panelWithItems.repaint();
    }

    public JPanel createRecordPanel(JPanel panelWithItems, DoublyLinkedList intList, String phoneNumber, String firstName, String lastName, String query)
    {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.X_AXIS));
        productPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        productPanel.setMaximumSize(new Dimension(1500, 50));
        productPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel phoneNumberLabel = new JLabel(" Phone Number: " + phoneNumber);
        productPanel.add(phoneNumberLabel);

        JLabel firstNameLabel = new JLabel(" First Name: " + firstName);
        productPanel.add(firstNameLabel);

        JLabel lastNameTextLabel = new JLabel(" Last Name: " + lastName);
        productPanel.add(lastNameTextLabel);

        productPanel.add(Box.createHorizontalGlue());

        JButton editContact = new JButton("Edit");
        editContact.setBackground(Color.LIGHT_GRAY);
        editContact.addActionListener(e ->
        {
            JFrame editWindow = new JFrame("Edit Contact");
            editWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            editWindow.setSize(250, 250);

            JPanel editPanel = new JPanel();
            editPanel.setLayout(null);
            editWindow.add(editPanel);

            JLabel phoneNewNumberLabel = new JLabel("New Phone Number: ");
            phoneNewNumberLabel.setBounds(10, 10, 165, 25);
            editPanel.add(phoneNewNumberLabel);

            JTextField phoneNewNumberField = new JTextField(20);
            phoneNewNumberField.setBounds(10, 40, 165, 25);
            editPanel.add(phoneNewNumberField);

            JButton saveButton = new JButton("Save");
            saveButton.setBounds(10, 70, 165, 25);
            saveButton.addActionListener(event -> {
                String newPhoneNumber = phoneNewNumberField.getText();
                intList.updateRecord(phoneNumber, newPhoneNumber);
                editWindow.dispose();
                loadProducts(panelWithItems, intList, null);
            });
            editPanel.add(saveButton);

            editWindow.setVisible(true);
        });

        productPanel.add(editContact);

        productPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        JButton deleteContact = new JButton("Delete");
        deleteContact.setBackground(Color.LIGHT_GRAY);
        deleteContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteContact.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteContact.setBackground(Color.LIGHT_GRAY);
            }
        });
        deleteContact.addActionListener(e ->
        {
            intList.deleteAtGivenNumber(phoneNumber);
            loadProducts(panelWithItems, intList, null);
        });
        productPanel.add(deleteContact);

        return productPanel;
    }
}