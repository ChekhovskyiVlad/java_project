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
                    5. Show my courses
                    6. Exit
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
                    showCourses();
                case 6 -> {
                    System.out.println("Exit...");
                    return;
                }
                default ->
                    System.out.println("Wrong choice");
            }
        }
    }

    void createCourse(Scanner in) {
        Course course = new Course();

        System.out.print("Enter course title: ");
        String title = in.nextLine();

        course.setTitle(title);
        course.setTeacherId(getIdMember());

        courses.add(course);
        course.save();

        System.out.println("Course created successfully!");
    }

    void enrollStudentToExistingCourse(Scanner in) {
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

    void createTask(Scanner in) {
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

    boolean studentExists(String studentId) {
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

    void addCourseToStudent(String studentId, String courseTitle) {
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

    void assignGradeToStudent(Scanner in) {
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

    void showCourses() {
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
}
