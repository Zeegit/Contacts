package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private boolean isPerson;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdit;
    // Список полей объекта
    private String[] fieldList;

    /*public Contact(String name, String phoneNumber, boolean isPerson) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setPerson(isPerson);
        setTimeCreated(LocalDateTime.now() /*.withSecond(0).withNano(0));
    }*/

    public Contact() {
        setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        // fieldList = new String[] {"name", "number"};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        // return phoneNumber;
        return hasNumber() ? phoneNumber : "[no number]";
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isValidNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = null;
            System.out.println("Wrong number format!");
        }

    }

    private boolean isValidNumber(String phoneNumber) {
        String p =
                "\\d+[ -]\\w{2,}[ -]?\\w{2,}([ -]\\w{2,})?" +
                "|[+]?\\d+[ -]\\w+" +
                "|[+]?(\\d+[ -])?\\(\\w+\\)([ -]\\d+)?([ -]\\d+)?([ -]\\w+)?|\\d+";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    private boolean hasNumber() {
        return !(phoneNumber == null || phoneNumber.isEmpty());
    }

    public boolean isPerson() {
        return isPerson;
    }

    public void setPerson(boolean person) {
        isPerson = person;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
        setTimeLastEdit(timeCreated);
    }

    public LocalDateTime getTimeLastEdit() {
        return timeLastEdit;
    }

    public void setTimeLastEdit(LocalDateTime timeLastEdit) {
        this.timeLastEdit = timeLastEdit;
    }

    public void setTimeLastEditNow() {
        setTimeLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
    }

    public abstract String info();

    public void editField(String fieldName, String fieldValue) {
        switch (fieldName) {
            case "name":
                setName(fieldValue);
                break;
            case "number":
                setPhoneNumber(fieldValue);
                break;
            default:
                break;
        }
        setTimeLastEditNow();
    }

    public String[] getFieldList() {
        return fieldList;
    }

    public String getFieldListAsString() {
        StringBuilder b = new StringBuilder();

        for(String s: fieldList) {
            b.append(", ");
            b.append(s);
        }
        b.replace(0,2,"");
        return b.toString();
    }

    protected void setFieldList(String[] fieldList) {
        /*String[] tmp = new String[this.fieldList.length + fieldList.length];

        for (int i = 0; i < this.fieldList.length; i++) { tmp[i] = this.fieldList[i]; }
        for (int j = 0, k = this.fieldList.length; j < fieldList.length; j++, k++) { tmp[k] = fieldList[j]; }

        this.fieldList = tmp;*/
        this.fieldList = fieldList;
    }

    public boolean contains(String searchText) {
        String fullText = getFullText();
        Pattern pattern = Pattern.compile(searchText, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(fullText);
        return matcher.find();

    }

    protected abstract String getFullText();
}
