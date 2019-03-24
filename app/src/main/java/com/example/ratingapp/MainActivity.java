package com.example.ratingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String SOURCE = "MainActivity";
    public static ArrayList<Course> courses;
    Button addCourseBtn;
    ArrayAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        if (savedInstanceState != null)
        {

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void addCourse(View view)
    {
        showInputDialog();
    }

    private void init() {
        addCourseBtn = findViewById(R.id.addCourse);
        courses = new ArrayList<>();
        adapter = new ArrayAdapter(this, R.layout.activity_main_listview, courses);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rateCourseIntent((Course) listView.getItemAtPosition(position));
            }
        });
    }

    private void rateCourseIntent(Course course)
    {
        Intent intent = new Intent(this, RateCourse.class);
        intent.putExtra(SOURCE + "-Course", course);
        startActivity(intent);
    }

    protected void showInputDialog()
    {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.nameEdit);
        final EditText emailText = (EditText) promptView.findViewById(R.id.emailEdit);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        if (!editText.getText().toString().equals("") && isValidEmail(emailText.getText().toString()))
                        {
                            courses.add(new Course(editText.getText().toString(), emailText.getText().toString()));
                            refreshArrayAdapter();
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void refreshArrayAdapter()
    {
        listView.setAdapter(adapter);
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
