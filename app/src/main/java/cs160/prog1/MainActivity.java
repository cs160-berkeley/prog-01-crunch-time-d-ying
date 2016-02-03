package cs160.prog1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    ExerciseTable exercises = new ExerciseTable();
    String exercise = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeSpinner();
    }

    private void initializeSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.exercise_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercise_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        exercise = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Not implemented since some item will always be selected
    }

    public void sendMessage(View view) {
        EditText input = (EditText) findViewById(R.id.input);
        String inputText = input.getText().toString();
        if (inputText.equals("")) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
        } else {
            float calories = exercises.getCalories(exercise, Integer.parseInt(inputText));

            TextView convertedValue = (TextView) findViewById(R.id.converted_value);
            String convertedValueText = String.format("%.1f calories", calories);
            convertedValue.setText(convertedValueText);

            TextView equivalents = (TextView) findViewById(R.id.equivalents);
            equivalents.setText(getEquivalents(exercise, calories));
        }
    }

    public String getEquivalents(String name, float calories) {
        String result = "Which is equivalent to:";
        Exercise[] equivalents = exercises.getEquivalent(name, calories);
        for (Exercise equivalent : equivalents) {
            String ex = equivalent.getName();
            String type = "minutes of ";
            if (ex.charAt(ex.length()-1) == 's' && !ex.equals("Jumping Jacks")) {
                type = "";
            }
            result += "\n" + String.format("%.1f %s%s", equivalent.getReps_min(), type, ex);
        }
        return result;
    }
}
