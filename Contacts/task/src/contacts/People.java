package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class People extends Contact {
    private String surname;
    private LocalDate birthDate;
    private String gender;

    /*public People(String name, String surname, String phoneNumber, LocalDate birthDate, String gender) {
        super(name, phoneNumber, true);
        setSurname(surname);
        setBirthDate(birthDate);
        setGender(gender);
    }*/

    public People() {
        setPerson(true);
        setFieldList(new String[]{"name", "surname", "birth date", "gender", "number"});
    }

    @Override
    public String info() {
        return getName() + " " + getSurname();
    }

    @Override
    public void editField(String fieldName, String fieldValue) {
        super.editField(fieldName, fieldValue);
        // name, surname, birth, gender, number

        switch (fieldName) {
            case "surname":
                setSurname(fieldValue);
                break;
            case "birth date":
                setBirthDate(fieldValue);
                break;
            case "gender":
                setGender(fieldValue);
                break;
            default:
                break;
        }
        setTimeLastEditNow();
    }

    @Override
    protected String getFullText() {
        return getName() + " " + getSurname() + " " + getBirthDate() + " " + getGender() + " " + getPhoneNumber();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return hasBirthDate() ? birthDate.toString() : "[no data]";
    }

    public void setBirthDate(String birthDate) {
        try {
            this.birthDate = LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
        }
    }

    public String getGender() {
        return hasGender() ? gender : "[no data]";
    }

    private boolean hasGender() {
        return !(gender == null);
    }

    public void setGender(String gender) {
        if (isValidGender(gender)) {
            this.gender = gender;
        } else {
            this.gender = null;
            System.out.println("Bad gender!");
        }
    }

    private boolean isValidGender(String gender) {
        return "M".equals(gender) || "F".equals(gender);
    }

    private boolean hasBirthDate() {
        return !(birthDate == null);
    }

    @Override
    public String toString() {
        return
                "Name: " + getName() + "\n" +
                        "Surname: " + getSurname() + "\n" +
                        "Birth date: " + getBirthDate() + "\n" +
                        "Gender: " + getGender() + "\n" +
                        "Number: " + getPhoneNumber() + "\n" +
                        "Time created: " + getTimeCreated() + "\n" +
                        "Time last edit: " + getTimeLastEdit();
    }
}
