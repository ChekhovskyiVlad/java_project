package individual1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {

    private String title;
    private String teacherId;
    private ArrayList<String> studentIds = new ArrayList<>();

    public void save() {
        try (FileOutputStream fos = new FileOutputStream("courses.txt", true)) {
            String students = String.join(",", studentIds);

            String data = title + ":" + teacherId + ":" + students + "\n";
            fos.write(data.getBytes());

        } catch (IOException ex) {
            System.out.println("Error writing course: " + ex.getMessage());
        }
    }

    public static ArrayList<Course> loadAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("courses.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");

                if (parts.length >= 2) {
                    Course course = new Course();

                    course.setTitle(parts[0]);
                    course.setTeacherId(parts[1]);

                    if (parts.length >= 3 && !parts[2].isEmpty()) {
                        String[] students = parts[2].split(",");

                        for (String studentId : students) {
                            course.addStudentId(studentId);
                        }
                    }

                    courses.add(course);
                }
            }
        } catch (IOException ex) {
            System.out.println("Courses file not found yet.");
        }

        return courses;
    }

    public static void rewriteAllCourses(ArrayList<Course> courses) {
        try (FileOutputStream fos = new FileOutputStream("courses.txt", false)) {
            for (Course course : courses) {
                String students = String.join(",", course.getStudentIds());

                String data = course.getTitle() + ":"
                        + course.getTeacherId() + ":"
                        + students + "\n";

                fos.write(data.getBytes());
            }
        } catch (IOException ex) {
            System.out.println("Error writing courses file: " + ex.getMessage());
        }
    }

    public static Course findByTitle(ArrayList<Course> courses, String title) {
        for (Course course : courses) {
            if (course.getTitle().equalsIgnoreCase(title)) {
                return course;
            }
        }

        return null;
    }

    public void addStudentId(String studentId) {
        if (!studentIds.contains(studentId)) {
            studentIds.add(studentId);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public ArrayList<String> getStudentIds() {
        return studentIds;
    }
}
