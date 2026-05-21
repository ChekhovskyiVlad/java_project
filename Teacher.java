package individual1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Account {

    private ArrayList<Course> courses;

    public Teacher(Account currentUser) {
        setStatus(currentUser.getStatus());
        setName(currentUser.getName());
        setLastName(currentUser.getLastName());
        setEmail(currentUser.getEmail());
        setPassword(currentUser.getPassword());
        setIdMember(currentUser.getIdMember());

        courses = Course.loadAllCourses();
    }

    void printInfoTeacher() {
        Scanner in = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("""
                    1. Create a course
                    2. Assign grade to a student
                    3. Add a task to the course
                    4. Enroll a student to the course
                    5. Check answers of students
                    6. Show my courses
                    7. Exit
                    """);

            System.out.print("Choose option: ");
            choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1 ->
                    createCourse(in);
                case 2 ->
                    assignGradeToStudent(in);
                case 3 ->
                    createTask(in);
                case 4 ->
                    enrollStudentToExistingCourse(in);
                case 5 ->
                    checkSubmissions(in);
                case 6 ->
                    showCourses();
                case 7 -> {
                    System.out.print("Exit....");
                    return;
                }
                default ->
                    System.out.println("Wrong choice");
            }
        }
    }

    private void createCourse(Scanner in) {
        Course course = new Course();

        System.out.print("Enter course title: ");
        String title = in.nextLine();

        course.setTitle(title);
        course.setTeacherId(getIdMember());

        courses.add(course);
        course.save();

        System.out.println("Course created successfully!");
    }

    private void enrollStudentToExistingCourse(Scanner in) {
        if (courses.isEmpty()) {
            System.out.println("You have no courses.");
            return;
        }

        System.out.print("Enter course title: ");
        String title = in.nextLine();

        Course course = Course.findByTitle(courses, title);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter student ID: ");
        String studentId = in.nextLine().trim();

        if (studentId.isEmpty()) {
            System.out.println("Student ID cannot be empty.");
            return;
        }

        if (!studentExists(studentId)) {
            System.out.println("Student with this ID was not found.");
            return;
        }

        course.addStudentId(studentId);
        Course.rewriteAllCourses(courses);

        addCourseToStudent(studentId, course.getTitle());

        System.out.println("Student enrolled successfully.");
    }

    private void createTask(Scanner in) {
        System.out.print("Enter course title: ");
        String courseTitle = in.nextLine();

        Course course = Course.findByTitle(courses, courseTitle);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        Task task = new Task();

        System.out.print("Enter task title: ");
        task.setTitle(in.nextLine());

        System.out.print("Enter description: ");
        task.setDescription(in.nextLine().split(","));

        System.out.print("Enter exercise: ");
        task.setExercise(in.nextLine().split(","));

        task.save(course.getTitle());

        System.out.println("Task created successfully.");
    }

    private boolean studentExists(String studentId) {
        try (Scanner scanner = new Scanner(new FileInputStream("users.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");

                if (parts.length >= 6) {
                    String status = parts[0];
                    String id = parts[5];

                    if (status.equalsIgnoreCase("STUDENT") && id.equals(studentId)) {
                        return true;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading users file: " + ex.getMessage());
        }

        return false;
    }

    private void addCourseToStudent(String studentId, String courseTitle) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("users.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");

                if (parts.length >= 6 && parts[5].equals(studentId)) {
                    if (parts.length == 6) {
                        line += ":" + courseTitle;
                    } else {
                        String studentCourses = parts[6];

                        if (!studentCourses.contains(courseTitle)) {
                            line += "," + courseTitle;
                        }
                    }
                }

                lines.add(line);
            }
        } catch (IOException ex) {
            System.out.println("Read error: " + ex.getMessage());
        }

        try (FileOutputStream fos = new FileOutputStream("users.txt", false)) {
            for (String line : lines) {
                fos.write((line + "\n").getBytes());
            }
        } catch (IOException ex) {
            System.out.println("Write error: " + ex.getMessage());
        }
    }

    private void assignGradeToStudent(Scanner in) {
        System.out.print("Enter course title: ");
        String courseTitle = in.nextLine();

        Course course = Course.findByTitle(courses, courseTitle);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter student ID: ");
        String studentId = in.nextLine();

        if (!course.getStudentIds().contains(studentId)) {
            System.out.println("This student is not enrolled in this course.");
            return;
        }

        System.out.print("Enter grade: ");
        String grade = in.nextLine();

        System.out.println("Grade " + grade + " assigned to student " + studentId);
    }

    private void checkSubmissions(Scanner in) {

        ArrayList<String> updatedLines = new ArrayList<>();

        try (Scanner fileScanner
                = new Scanner(new FileInputStream("submission.txt"))) {

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();

                String[] parts = line.split(":");

                /*
            0 courseTitle
            1 taskTitle
            2 studentId
            3 answer
            4 status
            5 grade
            6 feedback
                 */
                if (parts.length >= 7) {

                    String courseTitle = parts[0];
                    String taskTitle = parts[1];
                    String studentId = parts[2];
                    String answer = parts[3];
                    String status = parts[4];
                    String grade = parts[5];
                    String feedback = parts[6];

                    System.out.println();
                    System.out.println("Course: " + courseTitle);
                    System.out.println("Task: " + taskTitle);
                    System.out.println("Student ID: " + studentId);
                    System.out.println("Answer: " + answer);
                    System.out.println("Status: " + status);
                    System.out.println("Grade: " + grade);
                    System.out.println("Feedback: " + feedback);

                    // teacher checks task
                    System.out.print("Do you want to grade this submission? yes/no: ");
                    String choice = in.nextLine();

                    if (choice.equalsIgnoreCase("yes")) {

                        System.out.print("Enter grade: ");
                        String newGrade = in.nextLine();

                        System.out.print("Enter feedback: ");
                        String newFeedback = in.nextLine();

                        // update values
                        parts[4] = "CHECKED";
                        parts[5] = newGrade;
                        parts[6] = newFeedback;

                        System.out.println("Submission checked.");
                    }

                    // create updated line
                    String updatedLine
                            = parts[0] + ":"
                            + parts[1] + ":"
                            + parts[2] + ":"
                            + parts[3] + ":"
                            + parts[4] + ":"
                            + parts[5] + ":"
                            + parts[6];

                    updatedLines.add(updatedLine);
                }
            }

        } catch (IOException ex) {
            System.out.println("Error reading submissions: "
                    + ex.getMessage());
            return;
        }

        // rewrite submission.txt
        try (FileOutputStream fos
                = new FileOutputStream("submission.txt", false)) {

            for (String line : updatedLines) {
                fos.write((line + "\n").getBytes());
            }

        } catch (IOException ex) {
            System.out.println("Error writing submissions: "
                    + ex.getMessage());
        }
    }

    private void showCourses() {
        if (courses.isEmpty()) {
            System.out.println("You have no courses.");
            return;
        }

        for (Course course : courses) {
            System.out.println("Course: " + course.getTitle());
            System.out.println("Teacher ID: " + course.getTeacherId());
            System.out.println("Students: " + course.getStudentIds());
        }
    }


/// нужно тогда еще написать функцию проверки таска
}
