package org.tensorflow.lite.examples.detection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    CheckBox cat, dog, person;
    Button submit;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    Set<String> set = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        cat = (CheckBox) findViewById(R.id.cat);
        dog = (CheckBox) findViewById(R.id.dog);
        person = (CheckBox) findViewById(R.id.person);

        submit = (Button) findViewById(R.id.submit);

        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        editor = sharedPreferences.edit();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set.clear();;
                display(v);
            }
        });
    }

    public void display(View v) {

        if (cat.isChecked())
            //  colors = colors + " Cat ";
            set.add("cat");
        if (dog.isChecked())
            //   colors = colors + " Dog ";
            set.add("dog");
        if (person.isChecked())
            //  colors = colors + " Person ";
            set.add("person");


        editor.putStringSet("item_detect", set);
        editor.apply();

        Toast.makeText(this, "Selected :" + set,
                Toast.LENGTH_SHORT).show();

        Intent i = new Intent(Settings.this, DetectorActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }


}