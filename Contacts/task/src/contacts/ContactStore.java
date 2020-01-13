package contacts;

public class ContactStore extends ContactFactory {
    @Override
    Contact createContact(String type) {
        if (type.equals("person")) {
            return new People();
        } else if (type.equals("organization")) {
            return new Organization();
        } else {
            return null;
        }
    }
}
