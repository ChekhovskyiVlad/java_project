package individual1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("""
                Input your choice:
                1. Sign Up
                2. Sign In
                """);

        System.out.print("Choose option: ");
        int choice = in.nextInt();
        in.nextLine();

        if (choice == 1) {
            Registration reg = new Registration();

            reg.printName(in);
            reg.printLastname(in);
            reg.printEmail(in);

            if (!reg.proveEmail()) {
                System.out.println("Email is invalid");
                return;
            }

            if (reg.proveEmailExist()) {
                System.out.println("User with the same email already exists");
                return;
            }

            reg.printPassword(in);
            reg.generateMemberId();
            reg.saveUser();

            System.out.println("User registered successfully");

        } else if (choice == 2) {
            Login log = new Login();

            boolean success = log.loginUser(in);

            if (success) {
                System.out.println("User was signed in");

                System.out.println("""
                        Choose your role:
                        1. Student
                        2. Teacher
                        """);

                System.out.print("Choose option: ");
                int role = in.nextInt();
                in.nextLine();

                if (role == 1) {
                    Student student = new Student();

                    student.setName(log.getName());
                    student.setLastName(log.getLastName());
                    student.setEmail(log.getEmail());
                    student.setPassword(log.getPassword());
                    student.setIdMember(log.getIdMember());

                    student.setGrades(log.getGrades());
                    student.setEnrolledCourses(log.getEnrolledCourses());

                    student.printInfoStudent();

                } else if (role == 2) {
                    Teacher teacher = new Teacher();

                    teacher.setName(log.getName());
                    teacher.setLastName(log.getLastName());
                    teacher.setEmail(log.getEmail());
                    teacher.setPassword(log.getPassword());
                    teacher.setIdMember(log.getIdMember());

                    teacher.printPanel();

                } else {
                    System.out.println("Invalid role");
                }

            } else {
                System.out.println("User wasn't signed in");
            }

        } else {
            System.out.println("Invalid choice");
        }

        in.close();
    }
}
