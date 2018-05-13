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
import com.example.vade.discgolfapp.db.Game;

import java.util.ArrayList;
import java.util.List;

public class GamesActivity extends AppCompatActivity {

    private ListView gamesListView;
    private AppDatabase mDb;
    private EditText gameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        gamesListView = findViewById(R.id.gamesListView);
        //gameET = findViewById(R.id.courseNameET);

        List<Game> gamesList = mDb.gameModel().findAllGamesSync();
        ArrayList<String> myStringArray3 = new ArrayList<String>();

        for (Game game : gamesList) {
            myStringArray3.add(mDb.courseModel().findCourseById(game.courseId).name+", "+mDb.playerModel().findPlayerById(game.playerId).name+", "+game.totalScore);
        }
        ArrayAdapter<String> listAdapter3 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,myStringArray3);
        gamesListView.setAdapter(listAdapter3);
    }

    public void menuActivity(View v) {
        startActivity(new Intent(getApplicationContext(),
                MenuActivity.class));
    }

    //Search games by parameter to be implemented
    /*public void searchCourse(View v) {
        String name = courseET.getText().toString();
        List<Course> coursesList = mDb.courseModel().findCoursesByName(name);
        ArrayList<String> myStringArray1 = new ArrayList<String>();

        for (Course course : coursesList) {
            myStringArray1.add(course.name);
        }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,myStringArray1);
        listView.setAdapter(listAdapter);

    }
    */
}

