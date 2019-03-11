package com.example.ratingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Course> courses;
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
    }

    private void init()
    {
        addCourseBtn = findViewById(R.id.addCourse);
        courses = new ArrayList<>();
        courses.add(new Course("Android App"));
        courses.add(new Course("IOS"));
        courses.add(new Course("Sværdslugning"));
        adapter = new ArrayAdapter(this, R.layout.activity_main_listview, courses);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
