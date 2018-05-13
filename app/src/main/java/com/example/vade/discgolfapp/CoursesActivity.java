package com.example.vade.discgolfapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.vade.discgolfapp.db.AppDatabase;
import com.example.vade.discgolfapp.db.Course;

import java.util.ArrayList;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {

    private AppDatabase mDb;
    private ListView listView;
    private EditText courseET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        mDb = AppDatabase.getStoredDatabase(getApplicationContext());
        listView = findViewById(R.id.coursesList);
        courseET = findViewById(R.id.courseNameET);

        List<Course> coursesList = mDb.courseModel().findAllCoursesSync();
        ArrayList<String> myStringArray1 = new ArrayList<String>();

        for (Course course : coursesList) {
            myStringArray1.add(course.name);
        }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,myStringArray1);
        listView.setAdapter(listAdapter);
    }

    public void menuActivity(View v) {
        startActivity(new Intent(getApplicationContext(),
                MenuActivity.class));
    }
    public void addNew(View v) {
        startActivity(new Intent(getApplicationContext(),
                MainActivity.class));
    }
    public void searchCourse(View v) {
        String name = courseET.getText().toString();
        List<Course> coursesList = mDb.courseModel().findCoursesByName(name);
        ArrayList<String> myStringArray1 = new ArrayList<String>();

        for (Course course : coursesList) {
            myStringArray1.add(course.name);
        }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,myStringArray1);
        listView.setAdapter(listAdapter);

    }

}



