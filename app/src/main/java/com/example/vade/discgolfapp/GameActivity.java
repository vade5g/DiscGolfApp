package com.example.vade.discgolfapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.vade.discgolfapp.db.AppDatabase;
import com.example.vade.discgolfapp.db.Course;
import com.example.vade.discgolfapp.db.Player;
import com.example.vade.discgolfapp.db.utils.DatabaseInitializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class GameActivity extends AppCompatActivity {
    private TextView testTextView;
    private static List<player> players;
    private static List<game> games;
    private static List<course> courses;

    private static Map<Integer, Integer> idPlusMap = new HashMap<Integer, Integer>();
    private static Map<Integer, Integer> idMinusMap = new HashMap<Integer, Integer>();
    private static Map<Integer, Integer> idTWMap = new HashMap<Integer, Integer>();

    private AppDatabase mDb;

    public int totalScore;
    public player activePlayer;
    public course activeCourse;
    public List<Integer> activeScore = new ArrayList<>();
    private EditText courseNameET;


    private Spinner spinnerPlayer;
    private Spinner spinnerCourse;

    private ViewSwitcher switcher;
    private static final int REFRESH_SCREEN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        setContentView(R.layout.activity_game);
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

        //Spinners for selecting course and Player
        spinnerPlayer = findViewById(R.id.spinner1);
        spinnerCourse = findViewById(R.id.spinner2);

        //PlayersSpinner
        List<Player> players = mDb.playerModel().loadAllUsers();
        ArrayList<String> myStringArray2 = new ArrayList<String>();
        for (Player player : players) {
            myStringArray2.add(player.name);
        }
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> playerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, myStringArray2);
        //set the spinners adapter to the previously created one.
        spinnerPlayer.setAdapter(playerAdapter);

        //Courses Spinner
        List<Course> coursesList = mDb.courseModel().findAllCoursesSync();
        ArrayList<String> myStringArray1 = new ArrayList<String>();
        for (Course course : coursesList) {
            myStringArray1.add(course.name);
        }
        ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, myStringArray1);
        //set the spinners adapter to the previously created one.
        spinnerCourse.setAdapter(coursesAdapter);

        //set the basic value of all the fields to "3"
        for(Map.Entry<Integer, Integer> entry : idTWMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            TextView textView = findViewById(entry.getValue());
            textView.setText("3");

        }

        switcher = (ViewSwitcher) findViewById(R.id.profileSwitcher);



        fetchData();
    }
    public void nextView(View view) {
        switcher.showNext();
    }

    public void previousView(View view){
        switcher.showPrevious();
    }

    public void nextActivity(View v) {
        startActivity(new Intent(getApplicationContext(),
                GameActivity.class));
    }
    public void menuActivity(View v) {
        startActivity(new Intent(getApplicationContext(),
                MenuActivity.class));
    }
    public void calculateTotal(View v) {
        activeScore.clear();
        for(Map.Entry<Integer, Integer> entry : idTWMap.entrySet()) {
            TextView textView = findViewById(entry.getValue());
            int i = Integer.parseInt((textView).getText().toString());
            activeScore.add(i);
        }
        Integer sum = new Integer(0);
        for (int i : activeScore) {
            sum = sum + i;
        }
        testTextView.setText(sum.toString());
        totalScore = sum;
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


    private void populateDb() {
        DatabaseInitializer.populateSync(mDb);
    }

    private void fetchData() {
        // Note: this kind of logic should not be in an activity.
        /*StringBuilder sb = new StringBuilder();
        List<Player> proPlayers = mDb.playerModel().findYoungerThan(3099);
        for (Player proPlayer : proPlayers) {
            sb.append(String.format(Locale.US,
                    "%s, %s (%d)\n", proPlayer.name, proPlayer.gamesPlayed, proPlayer.bestScore));
        }
        */
        //Getting course by name from db and creating an object of it.
        Course dbCourse = mDb.courseModel().findCourseByName("Tali");
        Scanner scanner = new Scanner(dbCourse.holes);
        List<Integer> listHoles = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            listHoles.add(scanner.nextInt());
        }
        activeCourse = new course(dbCourse.id,dbCourse.name,dbCourse.parNumber,dbCourse.holesNumber,listHoles);

        //Getting player by the name from db and creating an object of it.
        Player dbPlayer = mDb.playerModel().findPlayerByName("Vade");

        activePlayer = new player(dbPlayer.id,dbPlayer.name);
        //Set best Score for the player from the DB
        activePlayer.setBestScore(dbPlayer.bestScore);

        /*
        String testString = activeCourse.getHoles().toString();
        testTextView.setText(activeCourse.getName()+ " " + activeCourse.getParScore());

        StringBuilder sB = new StringBuilder();
        sB.append(String.format(Locale.ENGLISH,
                "%s, %s (%d)\n", dbCourse.name, dbCourse.holesNumber,dbCourse.parNumber));
         */
    }
    public void saveGame(View v){
        String id = "ID"+mDb.gameModel().findAllGamesSync().size()+1;
        calculateTotal(v);
        Course saveCourse = mDb.courseModel().findCourseByName(spinnerCourse.getSelectedItem().toString());
        Player savePlayer = mDb.playerModel().findPlayerByName(spinnerPlayer.getSelectedItem().toString());
        DatabaseInitializer.addGame(mDb,id,savePlayer,saveCourse,totalScore,activeScore.toString());
        //game savedGame = new game(id,savePlayer,saveCourse,totalScore,activeScore);
        savePlayer.gamesPlayed++;
        if (savePlayer.bestScore > totalScore || savePlayer.bestScore == 0) {
            savePlayer.bestScore = totalScore;
        }
        mDb.playerModel().updateUser(savePlayer);
        testTextView.setText(id+", "+savePlayer.name+", "+saveCourse.name+", "+totalScore+", "+activeScore.toString());
    }
}




