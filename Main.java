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
        Login currentUser = new Login();

        if (choice == 1) {
            reg.printStatus(in);
            reg.printName(in);
            reg.printLastname(in);
            reg.printEmail(in);
            reg.printPassword(in);

            if (reg.proveEmail()) {
                if (reg.proveEmailExist()) {
                    System.out.print("User with the same email already exist");
                    return;
                }
                reg.generateMemberId();
                reg.saveUser();

            } else {
                System.out.println("Email is invalid");
            }
        } else if (choice == 2) {
            boolean success = currentUser.loginUser();

            if (success) {

                System.out.println("Signed in");

                currentUser.printAllInfo();

                if (currentUser.getStatus().equals("TEACHER")) {

                    Teacher teacher = new Teacher(currentUser);

                    teacher.printInfoTeacher();

                } else if (currentUser.getStatus().equals("STUDENT")) {

                    Student student = new Student(currentUser);

                    student.printInfoStudent();
                }

            } else {
                System.out.println("Invalid email or password");
            }

        } else {
            System.out.println("Invalid choice");
        }

        in.close();
    }
}
