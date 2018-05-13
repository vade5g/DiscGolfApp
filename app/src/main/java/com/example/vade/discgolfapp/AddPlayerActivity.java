package com.example.vade.discgolfapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.vade.discgolfapp.db.AppDatabase;
import com.example.vade.discgolfapp.db.Player;

public class AddPlayerActivity extends AppCompatActivity {

    private EditText addPlayerET;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        mDb = AppDatabase.getStoredDatabase(getApplicationContext());
        addPlayerET = findViewById(R.id.newPlayerET);

    }
    public void savePlayer(View v) {
        if (addPlayerET.length() != 0) {
            String id = addPlayerET.getText().toString() + (mDb.playerModel().loadAllUsers().size());
            String playerName = addPlayerET.getText().toString();
            Player playerToBeSaved = new Player(id,playerName);
            mDb.playerModel().insertUser(playerToBeSaved);
            //testTextView.setText(id+ ", "+courseName+ ", " +18+ ", "+totalScore+ ", "+holesString);
            startActivity(new Intent(getApplicationContext(),
                    PlayersActivity.class));
        } else {
            return;
        }
    }


    public void menuActivity(View v) {
        startActivity(new Intent(getApplicationContext(),
                MenuActivity.class));
    }
    public void goToPlayers() {
        startActivity(new Intent(getApplicationContext(),
                PlayersActivity.class));
    }
}
