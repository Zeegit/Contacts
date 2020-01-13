package contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactBook implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient Scanner scanner; // = new Scanner(System.in);
    private List<Contact> book = new ArrayList<>();

    void dialog() {
        scanner = new Scanner(System.in);
        String action;

        do {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            action = scanner.nextLine();
            switch (action) {
                case "add":
                    actionAdd();
                    break;
                case "list":
                    actionList();
                    break;
                case "search":
                    actionSearch();
                    break;
                case "count":
                    actionCount();
                    break;
                case "exit":
                    break;
                default:
                    break;
            }
        } while (!"exit".equals(action));
    }

    private void actionList() {
        for (int i = 0; i < book.size(); i++) {
            System.out.println((i + 1) + ". " + book.get(i).info());
        }
        System.out.println();
        System.out.print("[list] Enter action ([number], back): ");
        String action = scanner.nextLine();
        if (action.matches("\\d+")) {
            int recno = Integer.parseInt(action) - 1;
            System.out.println(book.get(recno));
            System.out.println();
            dialogEdit(recno);
        }

    }

    private void dialogEdit(int recNo) {
        System.out.print("[record] Enter action (edit, delete, menu): ");
        String action = scanner.nextLine();
        switch (action) {
            case "edit":
                actionEdit(recNo);
                break;
            case "delete":
                actionDelete(recNo);
                break;
            default:
                break;
        }
        System.out.println();
    }

    private void actionSearch() {
        ArrayList<Contact> text = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        String action;
        do {
            text.clear();
            index.clear();
            System.out.print("Enter search query: ");
            String searchText = scanner.nextLine();
            for (int i = 0; i < book.size(); i++) {
                if (book.get(i).contains(searchText)) {
                    text.add(book.get(i));
                    index.add(i);
                }
            }

            for (int i = 0; i < text.size(); i++) {
                System.out.println((i + 1) + ". " + text.get(i).info());
            }
            System.out.println();
            System.out.print("[search] Enter action ([number], back, again): ");
            action = scanner.nextLine();
            if (action.matches("\\d+")) {
                int recno = index.get(Integer.parseInt(action) - 1);
                System.out.println(book.get(recno));
                System.out.println();
                dialogEdit(recno);
            }
        } while (action.equals("again"));
        System.out.println();
    }

    private void actionEdit(int recNo) {
        Contact contact = book.get(recNo);
        System.out.printf("Select a field (%s): ", contact.getFieldListAsString());
        String fieldName = scanner.nextLine();
        System.out.print("Enter " + fieldName + ": ");
        String fieldValue = scanner.nextLine();
        contact.editField(fieldName, fieldValue);
        System.out.println("Saved\n");
        System.out.print(book.get(recNo));

    }

    private void actionDelete(int recNo) {
        book.remove(recNo);
    }

    private void actionAdd() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();

        ContactStore contactstore = new ContactStore();
        Contact contact = contactstore.makeContact(type);
        book.add(contact);
        System.out.println("The record added.\n");
    }

    private void actionCount() {
        System.out.printf("The Phone Book has %d records\n\n", book.size());
    }
}
