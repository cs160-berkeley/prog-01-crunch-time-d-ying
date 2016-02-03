package cs160.prog1;

public class Exercise {

    private String name;
    private float reps_min;

    public Exercise(String name, float reps_min) {
        this.name = name;
        this.reps_min = reps_min;
    }

    public String getName() {
        return name;
    }

    public float getReps_min() {
        return reps_min;
    }
}
