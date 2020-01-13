package contacts;

public class Organization extends Contact {
    private String address;

    public Organization() {
        setPerson(false);
        setFieldList(new String[] {"name", "address", "number"});
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void editField(String fieldName, String fieldValue) {
        super.editField(fieldName, fieldValue);
        switch (fieldName) {
            case "address":
                setAddress(fieldValue);
                break;
            default:
                break;
        }
        setTimeLastEditNow();
    }

    @Override
    protected String getFullText() {
        return getName() + " " + getAddress() + getPhoneNumber();
    }

    @Override
    public String info() {
        return getName();
    }

    @Override
    public String toString() {
        return
                "Organization name: " + getName() + "\n" +
                        "Address: " + getAddress() + "\n" +
                        "Number: " + getPhoneNumber() + "\n" +
                        "Time created: " + getTimeCreated() + "\n" +
                        "Time last edit: " + getTimeLastEdit();
    }
}
