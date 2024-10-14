[pseudocode_and_explanation[1] (2).pdf](https://github.com/user-attachments/files/17357727/pseudocode_and_explanation.1.2.pdf)DSA Group 1 - PhoneBook

Eben Erasmus - 224005642 ( UI() and DoublyNode() and combined everything)

Oluwafemi Ogundare - 224013696 ( binarySearch() and getTail() )

Stefan Weitz - 224064673 ( deleteAtGivenNumber() and findPosition() )

Ethan Gerber - 224005634 ( getMiddle() and UpdateRecord() )

Lisa Chikovore - 224097539 ( getSize() and insertAtTheEnd() )

Sean Marimo - 223137219 ( nsertionSortSurname() and sortedInserted() )

TO RUN MAKE SURE JCONNECTOR IS ADDED TO EXTRANAL LIABARIES!

Main class -  
Loads all the records from a MySQL database into a linked list, performs a insertion sort on the linked list and opens the UI.

UI class -
Displays all the records and have buttons to add, search, edit and remove records from the linked list.

DoublyLinkedList class -
Stores all the the methods of the linked list which gets called in UI and Main classes.

DoublyNode class - 
The code for the node creation and gets called in DoublyLinkList class-

Main in Pseudocode:

    START
        DoublyLinkedList intList

        intList <- insertionSurname()

        UI(intList)
    END

    loadContacts(intList)
    {
        define database URL, username, and password
        TRY
            - Open connection using the URL, username, and password
            - Create a SQL query to select phone numbers, first names, and last names from the 'records' table

            - Prepare a statement to execute the SQL query
            - Execute the query and store the result

        WHILE (rs != null) THEN
            phoneNumber = rs <- phoneNumber
            firstName = rs <- firstName
            lastName = rs <- lastName

            intList <- insertAtEnd(phoneNumber, firstName, lastName)

            rs next
        ENDWHILE
        ENDTRY
        CATCH (Exception)
            DISPLAY error
        ENDCATCH
    }
        
          

UI in Pseudocode:

    MODULE UI
    {
        // initialize the UI with a DoublyLinkedList of contacts
        UI(intList)
        {
            // Create a Frame for the application
            SET ui TO new JFrame("PhoneBook")
            SET ui to exit on close
            MAXIMIZE ui window
            SET size of ui to 1000x1000

            // Create main panel and set layout
            SET panel TO new JPanel()
            SET layout of panel to null
            ADD panel to ui

            // Create a panel to hold contact items
            SET panelWithItems TO new JPanel()
            SET layout of panelWithItems to GridLayout with 0 rows and 1 column

            // Create a scroll pane to hold the contact items panel
            SET scrollPane TO new JScrollPane(panelWithItems)
            SET bounds of scrollPane to (10, 100, 1900, 900)
            ADD scrollPane to panel

            // Create and set up search bar
            SET searchBar TO new JTextField(20)
            SET bounds of searchBar to (10, 10, 165, 25)
            ADD searchBar to panel

            // Create and set up search button
            SET searchContact TO new JButton("Search Contact")
            SET bounds of searchContact to (180, 10, 165, 25)
            ADD searchContact to panel

            // Create and set up add contact button
            SET addContact TO new JButton("Add Contact")
            SET bounds of addContact to (360, 10, 165, 25)
        
            // Add action listener for add contact button
            ADD action listener to addContact
                // Create a new JFrame for adding a contact
                SET addContactWindow TO new JFrame("Add Contact")
                SET addContactWindow to dispose on close
                SET size of addContactWindow to 1000x1000

                // Create a panel for adding contact details
                SET addContactPanel TO new JPanel()
                SET layout of addContactPanel to null
                ADD addContactPanel to addContactWindow

                // Create and add labels and fields for phone number, first name, and last name
                ADD label "Phone Number: " to addContactPanel
                SET phoneField TO new JTextField(20)
                ADD phoneField to addContactPanel

                ADD label "First Name: " to addContactPanel
                SET firstNameField TO new JTextField(20)
                ADD firstNameField to addContactPanel

                ADD label "Last Name: " to addContactPanel
                SET lastNameField TO new JTextField(20)
                ADD lastNameField to addContactPanel

                // Create and add button to save the contact
                SET addButton TO new JButton("Add")
                ADD action listener to addButton
                    // Get input from fields and insert into the list
                    SET phoneNumber TO phoneField <- getText()
                    SET firstName TO firstNameField <- getText()
                    SET lastName TO lastNameField <- getText()
                    CALL intList <- insertAtEnd(phoneNumber, firstName, lastName)
                    DISPOSE addContactWindow
                    CALL loadProducts(panelWithItems, intList, null)
                    CALL intList <- insertionSortSurname()

                ADD addButton to addContactPanel
                SET addContactWindow visible to true

            ADD action listener to searchContact
                // Get the query from the search bar and load products based on the query
                SET query TO searchBar <- getText()
                CALL loadProducts(panelWithItems, intList, query)

            // Load initial list of products
            CALL loadProducts(panelWithItems, intList, null)

            // Set ui window visible
            SET ui visible to true
        }



        // Load the contact into the panel
        loadProducts(panelWithItems, intList, query)
            CLEAR panelWithItems
            SET layout of panelWithItems to BoxLayout with Y_AXIS

            // Check if a query is provided
            IF (query != null AND query != NULL) THEN
                // Perform binary search to find the position of the contact
                position = intList <- binarySearch(query)
                IF (position != -1) THEN
                    SET temp TO intList <- head
                    FOR (int i = 0; i < position; i++) THEN
                        temp = temp <- next
                        // Extract contact details
                        phoneNumber = temp <- data
                        firstName = temp <- firstName
                        lastName = temp <- lastName
                        // Create and add product panel for the contact
                        productPanel = createRecordPanel(panelWithItems, intList, phoneNumber, firstName, lastName, null)
                        ADD productPanel to panelWithItems
                    END FOR
            ELSE
                // Load all contacts if no query is provided
                DoubyNode temp = intList <- head
                WHILE (temp != null) THEN
                    phoneNumber = temp <- data
                    firstName = temp <- firstName
                    lastName = temp <- lastName
                    // Create and add product panel for the contact
                    productPanel = createRecordPanel(panelWithItems, intList, phoneNumber, firstName, lastName, null)
                    ADD productPanel to panelWithItems
                    SET temp TO temp.next
                END WHILE
                ENDIF
            END IF

            // Refresh the panel
            CALL panelWithItems <- revalidate()
            CALL panelWithItems <- repaint()



        // create a record panel for a contact
        createRecordPanel(panelWithItems, intList, phoneNumber, firstName, lastName, query)
            SET productPanel TO new JPanel()
            SET layout of productPanel to BoxLayout with X_AXIS
            SET border of productPanel to empty border
            SET maximum size of productPanel to (1500, 50)
            SET alignment of productPanel to left

            // Add contact details to the panel
            ADD label "Phone Number: " + phoneNumber to productPanel
            ADD label "First Name: " + firstName to productPanel
            ADD label "Last Name: " + lastName to productPanel

            // Create edit button with action listener
            SET editContact TO new JButton("Edit")
            ADD action listener to editContact
                // Create a window for editing contact details
                SET editWindow TO new JFrame("Edit Contact")
                SET editWindow to dispose on close
                SET size of editWindow to 250x250

                // Create a panel for editing contact details
                SET editPanel TO new JPanel()
                SET layout of editPanel to null
                ADD editPanel to editWindow

                // Add fields for new phone number
                ADD label "New Phone Number: " to editPanel
                SET phoneNewNumberField TO new JTextField(20)
                ADD phoneNewNumberField to editPanel

                // Create and add save button
                SET saveButton TO new JButton("Save")
                ADD action listener to saveButton
                    // Update the contact's phone number
                    newPhoneNumber = phoneNewNumberField <- getText()
                    CALL intList <- updateRecord(phoneNumber, newPhoneNumber)
                    DISPOSE editWindow
                    CALL loadProducts(panelWithItems, intList, null)
                ADD saveButton to editPanel

                SET editWindow visible to true
            ADD editContact to productPanel

            // Create delete button with hover effect
            SET deleteContact TO new JButton("Delete")
            ADD mouse listener to deleteContact
                ON mouse enter
                    CHANGE background color of deleteContact to RED
                ON mouse exit
                    CHANGE background color of deleteContact to LIGHT_GRAY

            ADD action listener to deleteContact
                // Delete the contact from the list and refresh the view
                CALL intList <- deleteAtGivenNumber(phoneNumber)
                CALL loadProducts(panelWithItems, intList, null)

            ADD deleteContact to productPanel

            RETURN productPanel
    }



DoublyLinkedList in Psuedocode:

    MODULE DoublyLinkedList
    {
        Doubly Node

        //The method to get size of the LinkedList
        getSize()
        {
            Count=0
      
            //Assigning head to temp so as to be able to move throughout the list 
            DoubyNode Temp = head

            //Using a loop to move throughout the list 
            WHILE (temp !=  null)
                Count++

                //Increment temp 
                Temp = Temp <- next
            ENDWHILE 

            RETURN count 
        }

    
    
        //Creating the method to get data and insert it at end
        insertAtEnd(data, firstName, lastName)
        {
            DoublyNode newNode
            newNode <- data =data
            newNode <- firstName = firstName
            newNode <- lastName = lastName
            //Checking if it is an empty list
            IF (head == null) then
                //If so insert the new node to be the head node
                head = newNode
                RETURN
            ENDIF

            //If not so then
            DoublyNode

            //Assign whatever value in head to temp so as to transverse through the list
            temp = head

            //Use a loop to transverse through the list as long as temp.next is not equal to null
            WHILE(temp.next !=null)

                //Increment temp
                Temp = temp <- next
            ENDWHILE

            //If we get to the end the we insert the contact 
            Temp <- next = newNode 
            newNode <- prev = temp
        }



        DeleteAtGivenNumber(number)
        {
            DoublyNode temp = head

            WHILE (temp != NULL AND temp <- data != number) THEN
                temp = temp <- next  // Traverse the list to find the node with the given number
            ENDWHILE
            
            IF (temp == NULL) THEN
                RETURN  // If the list is empty, nothing to delete
            ENDIF

            IF (temp == head) THEN
                head = Current <- next  // If the node to delete is the head, update the head pointer
            ELSE IF (temp <- next == NULL) THEN
                Current <- prev <- next = NULL  // If it's the last node, update the previous node's next to NULL
            ELSE
                // Update the pointers to remove the current node from the list
                Current <- prev <- next = Current <- next  
                Current <- next <- prev = Current <- prev
            ENDIF
            ENDIF
        }
  


        insertionSortSurname()
        {
            DoublyNode sorted = null
            DoublyNode current = head

            WHILE (current != NULL) THEN
                DoublyNode next = current <- next
                current <- prev = NULL;
                current <- next = NULL;
                sorted = sortedInsert(sorted, newNode)
                current = next
            ENDWHILE
            head = sorted
        }

        

        sortedInsert(sorted, newNode)
        {

            // Handle empty list or newNode comes before the head
            IF ( sorted == null AND newNode <- data == head <- data) THEN
    	          newNode.next = sorted
 	              IF (sorted != null) THEN
      		          sorted.prev = newNode
                ENDIF
                sorted = newNode
            ELSE
            // Iterate through the list to find the insertion position
            DoublyNode current = sorted
            WHILE (current <- next != null AND current <- next <- data == newNode.data)
    	          current = current <- next
            ENDWHILE

            // Insert newNode after current
            newNode <- next = current <- next
            IF (current <- next != null) THEN
    	          current <- next <- prev = newNode
            ENDIF
            current <- next = newNode
            newNode <- prev = current
            ENDIF
            
            // Return the updated head
            return sorted
        }
        


        binarySearch (target) 
        {
            // Check if the list is empty
            IF (head == null) THEN
                return -1 // Target not found in an empty list
             ENDIF   
            //Initialise low and high pointers
            
            DoublyNode low = head
            DoublyNode high = getTail()
            
            //Continue searching until the search range is empty
            WHILE (low != null AND high != null AND low != high <- next) THEN
                // Get the middle node from the getMiddle() function
                middle = getMiddle(low, high)
                
                //Compare the middle element with the target
                IF (middle <- data == target) then
                    return findPosition(middle) // Target found
                //Adjust the search range based on the comparison
                ELSE IF (middle <- data > target) then
                    high = middle <- next //search right half
                ELSE
                    low = middle <- prev //search left half
                ENDIF
            ENDWHILE
            //Target not found
            return -1 //not found
        }



        getTail()
        {
            //Start from the head
            DoublyNode temp = head
            //Traverse the list until the end is reached
            WHILE (temp.next !=null)
                temp = temp <- next
            ENDWHILE
            //The last node is the tail
            return temp
        }



        GetMiddle(low, high)
        {
            //It is the name of the function.
            
            IF (low == null) THEN
                return null
            ENDIF
            //If the node low is null, then the function will return null because there is no list to process, or the list is empty.

            DoublyNode Slow = low
            DoublyNode Fast = low
            //Initializes slow and fast so that we ensure both start at the beginning of the linked list.

            WHILE (Fast != High AND Fast.next != High)
                Slow = Slow <- next
                Fast = Fast <- next <- next
            ENDWHILE
            // This is where Slow and Fast move through the list until one of the following conditions is met: Fast is not equal to high and the next node of fast is not equal to high which allows the               function to go until prompted to stop.
            //Slow = Slow.next is where the slow node moves to the next slow node. Also, where Fast.next.next means moving two nodes.
            //This continues until either fast reaches the high node or the next node of fast is high which means the function cannot go forward.

            return slow
            // Once the loop ends, Slow will be pointing to the middle of the list between low and high. The function will then return slow, which represents the middle node.}
        }



        FindPosition(node) 
        {
            position = 0  // Initialize position counter
            DoublyNode temp = head  // Start from the head of the list

            WHILE (temp != node) THEN
                position++  // Increment position as we traverse the list
                temp = temp <- next  // Move to the next node
            ENDWHILE

            RETURN position  // Return the position once the node is found
        }



        UpdateRecord()
        {
            //Name of the function

            IF(head == null) THEN
                DISPLAY "Phonebook is empty"
                return
            ENDIF
            // If the head is empty it will display the phonebook is empty.

            DoublyNode currentNode = head
            //This creates a currentNode variable to represent the node currently being checked.

            WHILE(currentNode = head) THEN
                IF(currentNode <- data == phoneNumber) THEN
                    currentNode <- data = (T)newPhoneNUmber
                    return
                ENDIF
            ENDWHILE
            //  If the data of the current node matches the phone number we are looking for, it proceeds to the next step.
            // The data of the currentNode is updated to the new phone number. 
            //Then it returns the value.

            currentNode = currentNode <- next
            // If the current node does not match the phone number, it moves to the next node in the list and repeats the process.
        }
    }



DoublyNode in Psuedocode:
    
    MODULE DoublyNode
    {
        data
        firstName
        lastName
        next
        prev

        DoublyNode(data, firstName, lastName)
        {
            data = data
            firstName = firstName
            lastName = lastName
            next = null
            prev = null
        }
    }



SQL:

    CREATE DATABASE PhoneBook;

    USE PhoneBook;

    CREATE TABLE records
    (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    phoneNumber VARCHAR(20),
    firstName VARCHAR(50),
    lastName VARCHAR(50)
    );

    INSERT INTO records (phoneNumber, firstName, lastName)
    VALUES
        ('+15123343456', 'Grace', 'Green'),
        ('+17028998877', 'Victor', 'Vance'),
        ('+18025554433', 'Yara', 'Young'),
        ('+19235554411', 'Rachel', 'Robinson'),
        ('+14442223334', 'Sam', 'Smith'),
        ('+15145556677', 'Mia', 'Miller'),
        ('+18013339922', 'Frank', 'Foster'),
        ('+14119991111', 'Paul', 'Parker'),
        ('+17889992233', 'Karen', 'King'),
        ('+16508884455', 'Wendy', 'Wilson'),
        ('+17772223344', 'David', 'Davis'),
        ('+18334445566', 'Olivia', 'Owen'),
        ('+19112229988', 'Ivy', 'Irwin'),
        ('+15559993322', 'Jack', 'Jones'),
        ('+16778889900', 'Zane', 'Zimmerman'),
        ('+14887776655', 'Charlie', 'Clark'),
        ('+19884441133', 'Alice', 'Anderson'),
        ('+13334442288', 'Noah', 'Nelson'),
        ('+14441119966', 'Xander', 'Xavier'),
        ('+18112229977', 'Bob', 'Brown'),
        ('+19998882211', 'Tina', 'Taylor'),
        ('+12228889933', 'Quinn', 'Quincy'),
        ('+14117775533', 'Liam', 'Lewis'),
        ('+15553339977', 'Eve', 'Evans'),
        ('+17775551122', 'Henry', 'Harris');



Flowcharts:

[getTail flowchart.pdf](https://github.com/user-attachments/files/17357666/getTail.flowchart.pdf)

[binarySearch flowchart.pdf](https://github.com/user-attachments/files/17357669/binarySearch.flowchart.pdf)

[Untitled Diagram.drawio (11).pdf](https://github.com/user-attachments/files/17357682/Untitled.Diagram.drawio.11.pdf)

[GetMiddle Function Pseudocode Explained (1).pdf](https://github.com/user-attachments/files/17357698/GetMiddle.Function.Pseudocode.Explained.1.pdf)

[Getting the size of the position to insert the contact from the user documentation (1).pdf](https://github.com/user-attachments/files/17357699/Getting.the.size.of.the.position.to.insert.the.contact.from.the.user.documentation.1.pdf)

[Update Record function explained (1).pdf](https://github.com/user-attachments/files/17357708/Update.Record.function.explained.1.pdf)

[Stefan  (1).pdf](https://github.com/user-attachments/files/17357720/Stefan.1.pdf)

[pseudocode_and_explanation[1] (2).pdf](https://github.com/user-attachments/files/17357740/pseudocode_and_explanation.1.2.pdf)
