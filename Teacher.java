package individual1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Account {

    private ArrayList<Course> courses = new ArrayList<>();

    void printInfoTeacher() {
        Scanner in = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("""
                    1. Create a course
                    2. Assign grade to a student
                    3. Add a task to the course
                    4. Enroll a student to the course
                    5. Show my courses
                    6. Exit
                    """);

            System.out.print("Choose option: ");
            choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    createCourse(in);
                    break;

                case 2:
                    assignGradeToStudent(in);
                    break;

                case 3:
                    createTask(in);
                    break;

                case 4:
                    enrollStudentToCourse(in);
                    break;

                case 5:
                    showCourses();
                    break;

                case 6:
                    System.out.println("Exit...");
                    return;

                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    void createCourse(Scanner in) {
        Course course = new Course();

        System.out.print("Enter course title: ");
        String title = in.nextLine();

        course.setTitle(title);

        course.setStudentIds(new String[0]);
        course.setTasks(new String[0]);

        courses.add(course);

        try (FileOutputStream fos = new FileOutputStream("courses.txt", true)) {

            String teachers = String.join(",", course.getTeacherIds());
            String students = "";
            String tasks = "";

            String data = course.getTitle() + ":" + teachers + ":" + students + ":" + tasks + "\n";

            fos.write(data.getBytes());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Course created successfully!");
    }

    void assignGradeToStudent(Scanner in) {

        String title;
        System.out.print("Enter your course: ");
        title = in.nextLine();

        String idStudent;
        System.out.print("Enter the IDstudent that you want to add: ");
        idStudent = in.nextLine();

        try (FileInputStream fin = new FileInputStream("users.bin")) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String parts[] = line.split(":");

            }
        } catch (IOException ex) {
            ex.getMessage();
        }

        System.out.print("""
            Enter students that you wish to add: 
            To stop enter "Enter"
        """);

    }

    void createTask(Scanner in) {

    }

    void enrollStudentToCourse(Scanner in) {
        // TODO
    }

    void showCourses() {
        // TODO
    }
}
