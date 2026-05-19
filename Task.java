package individual1;

public class Task {

    private String title;
    private String[] description;
    private String[] exercise;

    protected void setTitle(String title) {
        this.title = title;
    }

    protected String getTitle() {
        return title;
    }

    protected void setDescription(String[] description) {
        this.description = description;
    }

    protected String[] getDescription() {
        return description;
    }

    protected String[] getExercise() {
        return exercise;
    }

    protected void setExercise(String[] exercise) {
        this.exercise = exercise;
    }

}
