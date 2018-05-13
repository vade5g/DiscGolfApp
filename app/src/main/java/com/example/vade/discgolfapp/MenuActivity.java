package com.example.vade.discgolfapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.vade.discgolfapp.db.AppDatabase;
import com.example.vade.discgolfapp.db.utils.DatabaseInitializer;

public class MenuActivity extends AppCompatActivity {

    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void gameActivity(View v) {
        startActivity(new Intent(getApplicationContext(),
                GameActivity.class));
    }

    public void coursesActivity(View v) {
        startActivity(new Intent(getApplicationContext(),
                CoursesActivity.class));
    }
    public void playersActivity(View v) {
        startActivity(new Intent(getApplicationContext(),
                PlayersActivity.class));
    }

    public void gamesActivity(View v) {
        startActivity(new Intent(getApplicationContext(),
                GamesActivity.class));
    }

}
