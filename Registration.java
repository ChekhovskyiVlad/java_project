package individual1;

import java.io.Console;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class Registration extends Account {

    void printEmail(Scanner in) {
        System.out.print("Enter your email: ");
        String email = in.nextLine();
        setEmail(email);
    }

    boolean proveEmail() {
        return getEmail() != null && getEmail().endsWith("@gmail.com");
    }

    void printPassword() {
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
        try (FileOutputStream fos = new FileOutputStream("users.bin", true)) {
            String data = getEmail() + ":" + getPassword() + ":" + getIdMember() + "\n";
            fos.write(data.getBytes());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
