package individual1;

import java.io.*;

public class Task {

    private String title;
    private String[] description;
    private String[] exercise;

    public void save(String courseTitle) {
        try (FileOutputStream fos = new FileOutputStream("tasks.txt", true)) {
            String descriptionText = String.join(",", description);
            String exerciseText = String.join(",", exercise);

            String data = courseTitle + ":"
                    + title + ":"
                    + descriptionText + ":"
                    + exerciseText + "\n";

            fos.write(data.getBytes());

        } catch (IOException ex) {
            System.out.println("Error writing task: " + ex.getMessage());
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public String[] getExercise() {
        return exercise;
    }

    public void setExercise(String[] exercise) {
        this.exercise = exercise;
    }
}
