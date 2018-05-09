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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;


public class MainActivity extends AppCompatActivity {

    private TextView testTextView;
    private static List<player> players;
    private static List<game> games;
    private static List<course> courses;

    //TextViews

    private TextView scoreTW1;
    private TextView scoreTW2;
    private TextView scoreTW3;
    private TextView scoreTW4;
    private TextView scoreTW5;
    private TextView scoreTW6;
    private TextView scoreTW7;
    private TextView scoreTW8;
    private TextView scoreTW9;
    private TextView scoreTW10;
    private TextView scoreTW11;
    private TextView scoreTW12;
    private TextView scoreTW13;
    private TextView scoreTW14;
    private TextView scoreTW15;
    private TextView scoreTW16;
    private TextView scoreTW17;
    private TextView scoreTW18;



    private static Map<Integer, Integer> idPlusMap = new HashMap<Integer, Integer>();
    private static Map<Integer, Integer> idMinusMap = new HashMap<Integer, Integer>();
    private static Map<Integer, Integer> idTWMap = new HashMap<Integer, Integer>();

    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testTextView = findViewById(R.id.testTextView);
        //map all the plus buttons.
        idPlusMap.put(R.id.buttonPlus1,1);
        idPlusMap.put(R.id.buttonPlus2,2);
        idPlusMap.put(R.id.buttonPlus3,3);
        idPlusMap.put(R.id.buttonPlus4,4);
        idPlusMap.put(R.id.buttonPlus5,5);
        idPlusMap.put(R.id.buttonPlus6,6);
        idPlusMap.put(R.id.buttonPlus7,7);
        idPlusMap.put(R.id.buttonPlus8,8);
        idPlusMap.put(R.id.buttonPlus9,9);
        idPlusMap.put(R.id.buttonPlus10,10);
        idPlusMap.put(R.id.buttonPlus11,11);
        idPlusMap.put(R.id.buttonPlus12,12);
        idPlusMap.put(R.id.buttonPlus13,13);
        idPlusMap.put(R.id.buttonPlus14,14);
        idPlusMap.put(R.id.buttonPlus15,15);
        idPlusMap.put(R.id.buttonPlus16,16);
        idPlusMap.put(R.id.buttonPlus17,17);
        idPlusMap.put(R.id.buttonPlus18,18);
        //map all the minus buttons.
        idMinusMap.put(R.id.buttonMinus1,1);
        idMinusMap.put(R.id.buttonMinus2,2);
        idMinusMap.put(R.id.buttonMinus3,3);
        idMinusMap.put(R.id.buttonMinus4,4);
        idMinusMap.put(R.id.buttonMinus5,5);
        idMinusMap.put(R.id.buttonMinus6,6);
        idMinusMap.put(R.id.buttonMinus7,7);
        idMinusMap.put(R.id.buttonMinus8,8);
        idMinusMap.put(R.id.buttonMinus9,9);
        idMinusMap.put(R.id.buttonMinus10,10);
        idMinusMap.put(R.id.buttonMinus11,11);
        idMinusMap.put(R.id.buttonMinus12,12);
        idMinusMap.put(R.id.buttonMinus13,13);
        idMinusMap.put(R.id.buttonMinus14,14);
        idMinusMap.put(R.id.buttonMinus15,15);
        idMinusMap.put(R.id.buttonMinus16,16);
        idMinusMap.put(R.id.buttonMinus17,17);
        idMinusMap.put(R.id.buttonMinus18,18);
        //all the text views
        idTWMap.put(1,R.id.Score1TW);
        idTWMap.put(2,R.id.Score2TW);
        idTWMap.put(3,R.id.Score3TW);
        idTWMap.put(4,R.id.Score4TW);
        idTWMap.put(5,R.id.Score5TW);
        idTWMap.put(6,R.id.Score6TW);
        idTWMap.put(7,R.id.Score7TW);
        idTWMap.put(8,R.id.Score8TW);
        idTWMap.put(9,R.id.Score9TW);
        idTWMap.put(10,R.id.Score10TW);
        idTWMap.put(11,R.id.Score11TW);
        idTWMap.put(12,R.id.Score12TW);
        idTWMap.put(13,R.id.Score13TW);
        idTWMap.put(14,R.id.Score14TW);
        idTWMap.put(15,R.id.Score15TW);
        idTWMap.put(16,R.id.Score16TW);
        idTWMap.put(17,R.id.Score17TW);
        idTWMap.put(18,R.id.Score18TW);
        //set the basic value of all the fields to "3"
        for(Map.Entry<Integer, Integer> entry : idTWMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            TextView textView = findViewById(entry.getValue());
            textView.setText("3");

        }


        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        populateDb();

        fetchData();
    }
    public void onClickMinusButton(View v) {
        int n = idMinusMap.get(v.getId());
        TextView textView = findViewById(idTWMap.get(n));
        int i = Integer.parseInt((textView).getText().toString());
        i -= 1;
        textView.setText(String.valueOf(i));
    }

    public void onClickPlusButton(View v) {
        int n = idPlusMap.get(v.getId());
            TextView textView = findViewById(idTWMap.get(n));
            int i = Integer.parseInt(textView.getText().toString());
            i++;
            textView.setText(String.valueOf(i));
    }
    public void calculateTotal(View v) {
        ArrayList<Integer> score = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : idTWMap.entrySet()) {
            TextView textView = findViewById(entry.getValue());
            int i = Integer.parseInt((textView).getText().toString());
            score.add(i);
        }
        Double sum = new Double(0);
        for (int i : score) {
            sum = sum + i;
        }
        testTextView.setText(sum.toString());
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
        testTextView.setText(activeCourse.getName()+ " " + activeCourse.getParScore());

        StringBuilder sB = new StringBuilder();
        sB.append(String.format(Locale.ENGLISH,
                "%s, %s (%d)\n", dbCourse.name, dbCourse.holesNumber,dbCourse.parNumber));
    }

}



