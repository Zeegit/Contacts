package contacts;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filename = "phonebook1.db";
        ContactBook cb = new ContactBook();

        /*try {
            cb = (ContactBook) SerializationUtils.deserialize(filename);
            System.out.printf("open %s\n\n", filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.printf("File \"%s\" is not found\n", filename);
            //e.printStackTrace();
        }*/

        cb.dialog();

        /*try {
            SerializationUtils.serialize(cb, filename);
        } catch (IOException e) {
             e.printStackTrace();
        }*/



    }
}
