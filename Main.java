package individual1;

import java.util.Scanner;


/*
возможно нужно сделать отдельный файл который собирает это все, а мейн передает уже готовые конструкторы
 */
public class Main {

    public static void main(String[] args) {

        String text = """
                Input your choice:
                1. Sign Up
                2. Sign In
                """;

        System.out.print(text);

        Scanner in = new Scanner(System.in);
        short choice = in.nextShort();
        in.nextLine();

        Registration reg = new Registration();
        Login log = new Login();
        if (choice == 1) {
            reg.printName(in);
            reg.printLastname(in);
            reg.printEmail(in);

            if (reg.proveEmail()) {
                reg.printPassword();
                reg.generateMemberId();
                reg.saveUser();

                if (reg.proveEmailExist()) {
                    System.out.print("User with the same email already exist");
                    return;
                }
            } else {
                System.out.println("Email is invalid");
            }
        } else if (choice == 2) {
            boolean success = log.loginUser();

            if (success) {
                System.out.println("User was signed in");
            } else {
                System.out.println("User wasn't signed in");
            }
        } else {
            System.out.println("Invalid choice");
        }

        in.close();
    }
}
