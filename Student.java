package individual1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Student extends Account {

    private short[] grades;
    public String[] enrolledCourses;

    public Student(Account currentUser) {
        setStatus(currentUser.getStatus());
        setName(currentUser.getName());
        setLastName(currentUser.getLastName());
        setEmail(currentUser.getEmail());
        setPassword(currentUser.getPassword());
        setIdMember(currentUser.getIdMember());
    }

    void printInfoStudent() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. See all grades
                    2. Do tasks
                    3. Calculate your average grade
                    4. Update your data
                    5. Exit
                    """);

            System.out.print("Choose option: ");
            short choice = in.nextShort();
            in.nextLine();

            switch (choice) {
                case 1 ->
                    System.out.println("Grades function is not ready yet.");
                case 2 ->
                    doTasks(in);
                case 3 ->
                    System.out.println("Average grade function is not ready yet.");
                case 4 ->
                    System.out.println("Update data function is not ready yet.");
                case 5 -> {
                    System.out.println("Exit...");
                    return;
                }
                default ->
                    System.out.println("Wrong choice.");
            }
        }
    }

    void doTasks(Scanner in) {
        String studentId = getIdMember();

        String studentCourses = findStudentCourses(studentId);

        if (studentCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }

        String[] courses = studentCourses.split(",");

        boolean hasTasks = false;

        try (Scanner fileScanner = new Scanner(new FileInputStream("tasks.txt"))) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                String[] parts = line.split(":");

                if (parts.length >= 4) {
                    String taskCourseTitle = parts[0];
                    String taskTitle = parts[1];
                    String description = parts[2];
                    String exercise = parts[3];

                    if (studentHasCourse(courses, taskCourseTitle)) {
                        hasTasks = true;

                        System.out.println();
                        System.out.println("Course: " + taskCourseTitle);
                        System.out.println("Task: " + taskTitle);
                        System.out.println("Description: " + description);
                        System.out.println("Exercise: " + exercise);

                        System.out.print("Do you want to answer this task? yes/no: ");
                        String answerChoice = in.nextLine();

                        if (answerChoice.equalsIgnoreCase("yes")) {
                            writeAnswer(in, taskCourseTitle, taskTitle);
                        }
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("Error reading tasks.txt: " + ex.getMessage());
            return;
        }

        if (!hasTasks) {
            System.out.println("There are no tasks for your courses.");
        }
    }

    String findStudentCourses(String studentId) {
        try (Scanner fileScanner = new Scanner(new FileInputStream("users.txt"))) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                String[] parts = line.split(":");

                // parts[5] = student ID
                // parts[6] = courses
                if (parts.length >= 7 && parts[5].equals(studentId)) {
                    return parts[6];
                }
            }

        } catch (IOException ex) {
            System.out.println("Error reading users.txt: " + ex.getMessage());
        }

        return "";
    }

    boolean studentHasCourse(String[] courses, String taskCourseTitle) {
        for (String course : courses) {
            if (course.equalsIgnoreCase(taskCourseTitle)) {
                return true;
            }
        }

        return false;
    }

    void writeAnswer(Scanner in, String courseTitle, String taskTitle) {
        System.out.print("Enter your answer: ");
        String answer = in.nextLine();

        Submission submission = new Submission(
                courseTitle,
                taskTitle,
                getIdMember(),
                answer
        );

        submission.save();

        System.out.println("Answer submitted successfully.");
    }
}
