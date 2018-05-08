package com.example.vade.discgolfapp;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vade.discgolfapp.db.AppDatabase;
import com.example.vade.discgolfapp.db.Course;
import com.example.vade.discgolfapp.db.Player;
import com.example.vade.discgolfapp.db.utils.DatabaseInitializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    private TextView testTextView;
    private static List<player> players;
    private static List<game> games;
    private static List<course> courses;

    private Button plusButton1;
    private Button minusButton1;
    private TextView scoreTW1;

    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testTextView = findViewById(R.id.testTextView);
        plusButton1 = findViewById(R.id.buttonPlus1);
        minusButton1 = findViewById(R.id.buttonMinus1);
        scoreTW1 = findViewById(R.id.Score1TW);
        scoreTW1.setText("3");

        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        populateDb();

        fetchData();
    }

    public void onClickPlusButton(View v) {
        int i = Integer.parseInt(scoreTW1.getText().toString());
        i++;
        scoreTW1.setText(String.valueOf(i));
    }
    public void onClickMinusButton(View v) {
        int i = Integer.parseInt(scoreTW1.getText().toString());
        i -= 1;
        scoreTW1.setText(String.valueOf(i));
    }

    private void populateDb() {
        DatabaseInitializer.populateSync(mDb);
    }
    private void fetchData() {
        // Note: this kind of logic should not be in an activity.
        StringBuilder sb = new StringBuilder();
        List<Player> proPlayers = mDb.playerModel().findYoungerThan(3099);
        for (Player proPlayer : proPlayers) {
            sb.append(String.format(Locale.US,
                    "%s, %s (%d)\n", proPlayer.name, proPlayer.gamesPlayed, proPlayer.bestScore));
        }

        //Getting course by name from db and creating an object of it.

        Course dbCourse = mDb.courseModel().findCourseByName("Tali");
        Scanner scanner = new Scanner(dbCourse.holes);
        List<Integer> listHoles = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            listHoles.add(scanner.nextInt());
        }
        course activeCourse = new course(dbCourse.name,dbCourse.parNumber,dbCourse.holesNumber,listHoles);

        String testString = activeCourse.getHoles().toString();
        testTextView.setText(activeCourse.getName()+ " " + activeCourse.getParScore() + " " + testString);

        StringBuilder sB = new StringBuilder();
        sB.append(String.format(Locale.ENGLISH,
                "%s, %s (%d)\n", dbCourse.name, dbCourse.holesNumber,dbCourse.parNumber));
    }
}



