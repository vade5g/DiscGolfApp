package com.example.vade.discgolfapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vade.discgolfapp.db.AppDatabase;
import com.example.vade.discgolfapp.db.utils.DatabaseInitializer;

public class LoadingActivity extends AppCompatActivity {

    private AppDatabase mDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);


        mDb = AppDatabase.getStoredDatabase(getApplicationContext());

        //populateDb();
        DatabaseInitializer.populateAsync(mDb);

        Thread welcomeThread = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(3500);  //Delay of 3,5 seconds
                } catch (Exception e) {

                } finally {

                    Intent i = new Intent(LoadingActivity.this,
                            MenuActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();


    }
    private void populateDb() {
        DatabaseInitializer.populateAsync(mDb);
    }
}
