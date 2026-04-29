package individual1;

import java.io.Console;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

// сделать проверку имейла на существования уже юзера
public class Registration extends Account {

    void printName(Scanner in) {
        System.out.print("Enter your name: ");
        String name = in.nextLine();
        setName(name);
    }

    void printLastname(Scanner in) {
        System.out.print("Enter your last name: ");
        String lastName = in.nextLine();
        setLastName(lastName);
    }

    void printEmail(Scanner in) {
        System.out.print("Enter your email: ");
        String email = in.nextLine();
        setEmail(email);
    }

    boolean proveEmail() {
        return getEmail() != null && getEmail().endsWith("@gmail.com");
    }

    void printPassword(Scanner in) {
        Console console = System.console();

        if (console != null) {
            char[] password = console.readPassword("Enter your password: ");
            setPassword(new String(password));
        } else {
            System.out.println("Console is not available.");
        }
    }

    void generateMemberId() {
        setIdMember(UUID.randomUUID().toString()); // конвертируем в строку так как возвращает без нее как UUID
    }

    void saveUser() {
        try (FileOutputStream fos = new FileOutputStream("users.txt", true)) {
            // name:lastName:email:password:id:grades:courses
            String data = getName() + ":"
                    + getLastName() + ":"
                    + getEmail() + ":"
                    + getPassword() + ":"
                    + getIdMember() + ":"
                    + "" + ":"
                    + "" + "\n";

            fos.write(data.getBytes());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
