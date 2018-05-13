/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.vade.discgolfapp.db.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.Calendar;
import java.util.Date;

import com.example.vade.discgolfapp.db.AppDatabase;
import com.example.vade.discgolfapp.db.Course;
import com.example.vade.discgolfapp.db.Game;
import com.example.vade.discgolfapp.db.Player;

public class DatabaseInitializer {

    // Simulate a blocking operation delaying each Game insertion with a delay:
    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    public static void addGame(final AppDatabase db, final String id,
                                final Player player, final Course course, final int totalScore, final String score) {
        Game game = new Game();
        game.id = id;
        game.courseId = course.id;
        game.playerId = player.id;
        game.totalScore = totalScore;
        game.score = score;
        db.gameModel().insertGame(game);
    }

    public static Course addCourse(final AppDatabase db, final String id, final String name, final int holesNumber,
                                    final int parNumber, final String holes) {
        Course course = new Course();
        course.id = id;
        course.name = name;
        course.holesNumber = holesNumber;
        course.parNumber = parNumber;
        course.holes = holes;
        db.courseModel().insertCourse(course);
        return course;
    }

    public static Player addPlayer(final AppDatabase db, final String id, final String name,
                                    final int bestScore, final int gamesPlayed) {
        Player player = new Player(id,name);
        player.gamesPlayed = gamesPlayed;
        player.bestScore = bestScore;
        db.playerModel().insertUser(player);
        return player;
    }

    private static void populateWithTestData(AppDatabase db) {
        //db.gameModel().deleteAll();
        //db.playerModel().deleteAll();
        //db.courseModel().deleteAll();

        Player player1 = addPlayer(db, "1", "Vade", 75, 1);
        Player player2 = addPlayer(db, "2", "Topi", 54, 4);


        Course course1 = addCourse(db, "1", "Tali", 18, 54, "5 3 3 3 3 3 3 3 4 4 3 3 3 3 4 3 3 3");
        Course course2 = addCourse(db, "2", "Meikku",18, 54, "3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3");
        Course course3 = addCourse(db, "3", "Puolari",18, 54, "3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3");
        Course course4 = addCourse(db, "4", "Kivikko",18, 54, "3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3");
        addCourse(db, "5", "Jatemaki",18, 54, "3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3");

        try {


            addGame(db, "1", player1, course1, 75, "3 4 3 5 3 6 3 4 5 3 6 4 5 4 3 6 5 3");
            Thread.sleep(DELAY_MILLIS);
            addGame(db, "2", player2, course1, 65, "3 5 4 3 4 3 4 3 4 3 4 3 4 3 4 5 3 4");
            Thread.sleep(DELAY_MILLIS);
            addGame(db, "3", player2, course2, 54, "3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3");
            Thread.sleep(DELAY_MILLIS);
            addGame(db, "4", player2, course3, 65, "4 4 3 3 3 3 3 4 3 4 3 4 3 4 3 4 3 4");
            Thread.sleep(DELAY_MILLIS);
            addGame(db, "5", player2, course4, 79, "5,5,4,5,4,5,6,4,5,4,6,4,5,4,6,4,6,4");
            Log.d("DB", "Added Games");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}
