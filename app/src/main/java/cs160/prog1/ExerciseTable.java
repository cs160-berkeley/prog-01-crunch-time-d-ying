package cs160.prog1;

import java.util.ArrayList;
import java.util.HashMap;

public class ExerciseTable {

    HashMap<String, Float> exercises = new HashMap<>();

    public ExerciseTable() {
        exercises.put("Pushups", 350f);
        exercises.put("Situps", 200f);
        exercises.put("Squats", 225f);
        exercises.put("Leg-Lifts", 25f);
        exercises.put("Planking", 25f);
        exercises.put("Jumping Jacks", 10f);
        exercises.put("Pullups", 100f);
        exercises.put("Cycling", 12f);
        exercises.put("Walking", 20f);
        exercises.put("Jogging", 12f);
        exercises.put("Swimming", 13f);
        exercises.put("Stair-Climbing", 15f);
    }

    public float getCalories(String exercise, int reps_min) {
        return 100*(reps_min/exercises.get(exercise));
    }

    public Exercise[] getEquivalent(String exercise, float calories) {
        ArrayList<Exercise> equivalents = new ArrayList<>();
        for (HashMap.Entry<String, Float> entry : exercises.entrySet()) {
            if (!entry.getKey().equals(exercise)) {
                float value = entry.getValue() * (calories / 100);
                Exercise equivalent = new Exercise(entry.getKey(), value);
                equivalents.add(equivalent);
            }
        }
        return equivalents.toArray(new Exercise[equivalents.size()]);
    }
}
