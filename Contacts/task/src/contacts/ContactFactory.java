package contacts;

import java.util.Scanner;

abstract class ContactFactory {
    final Scanner scanner = new Scanner(System.in);

    abstract Contact createContact(String type);

    Contact makeContact(String type) {
        Contact contact = createContact(type);
        if (contact == null) {
            System.out.println("Sorry, we are not able to create this kind of contact\n");
            return null;
        }

        String[] fields = contact.getFieldList();
        for (String field : fields) {
            System.out.printf("Enter the %s: ", field);
            String fieldValue = scanner.nextLine();
            contact.editField(field, fieldValue);
        }
        return contact;
    }
}
