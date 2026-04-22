package individual1;

import java.io.Console;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Registration extends Account {

    void printEmail(Scanner in) {
        System.out.print("Enter your email: ");
        String email = in.nextLine();
        setEmail(email);

        try (FileOutputStream fos = new FileOutputStream("users.bin")) {
            byte[] buffer = email.getBytes();

            fos.write(buffer, 0, buffer.length);
            System.out.println("The file has been written");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    boolean proveEmail() {
        return getEmail() != null && getEmail().contains("@gmail.com");
    }

    /* треба було використати нативну бібліотеку, по джава не дає доступ до символів в реальному часі */
    void printPassword() {
        Console console = System.console();

        if (console != null) {
            char[] password = console.readPassword("Enter your password: ");
            setPassword(new String(password));
        } else {
            System.out.println("Console is not available.");
        }
    }

    // запис в файл
}
