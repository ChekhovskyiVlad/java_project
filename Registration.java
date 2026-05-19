package individual1;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class Registration extends Account {

    void printStatus(Scanner in) {
        System.out.print("Are you a teacher? (Print `yes` or `no`): ");
        String status = in.nextLine();

        switch (status) {
            case "yes":
                setStatus("TEACHER");
                break;
            case "no":
                setStatus("STUDENT");
                break;
            default:
                System.out.println("Invalid answer");
                throw new AssertionError();
        }
    }

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
        String password;

        if (console != null) {
            password = new String(console.readPassword("Enter your password: "));
        } else {
            System.out.print("Enter your password: ");
            password = in.nextLine();
        }

        setPassword(password);
    }

    void generateMemberId() {
        setIdMember(UUID.randomUUID().toString());
    }

    void saveUser() {
        try (FileOutputStream fos = new FileOutputStream("users.txt", true)) {
            String data = getStatus() + ":"
                    + getName() + ":"
                    + getLastName() + ":"
                    + getEmail() + ":"
                    + getPassword() + ":"
                    + getIdMember() + "\n";

            fos.write(data.getBytes());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    boolean proveEmailExist() {
        try (Scanner fileScanner = new Scanner(new FileInputStream("users.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");

                if (parts.length >= 4) {
                    String emailFound = parts[3];

                    if (emailFound.equals(getEmail())) {
                        return true;
                    }
                }
            }
        } catch (IOException ex) {
            return false;
        }

        return false;
    }
}
