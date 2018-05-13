package com.example.vade.discgolfapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vade.discgolfapp.db.AppDatabase;
import com.example.vade.discgolfapp.db.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayersActivity extends AppCompatActivity {

    private AppDatabase mDb;
    private ListView playerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        mDb = AppDatabase.getStoredDatabase(getApplicationContext());

        playerListView = findViewById(R.id.playersListView);

        List<Player> players = mDb.playerModel().loadAllUsers();
        ArrayList<String> myStringArray2 = new ArrayList<String>();
        for (Player player : players) {
            myStringArray2.add(player.name+", Games played:"+player.gamesPlayed+", BestScore:"+player.bestScore);
        }
        ArrayAdapter<String> listAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,myStringArray2);
        playerListView.setAdapter(listAdapter2);
    }

    public void menuActivity(View v) {
        startActivity(new Intent(getApplicationContext(),
                MenuActivity.class));
    }
    public void addNewPlayer(View v) {
        startActivity(new Intent(getApplicationContext(),
                AddPlayerActivity.class));
    }


}
