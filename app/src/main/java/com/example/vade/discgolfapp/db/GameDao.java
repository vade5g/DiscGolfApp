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

package com.example.vade.discgolfapp.db;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface GameDao {

    @Query("SELECT * From Game")
    LiveData<List<Game>> findAllGames();

    @Query("SELECT * From Game")
    List<Game> findAllGamesSync();

    @Insert(onConflict = IGNORE)
    void insertGame(Game game);

    @Query("DELETE FROM Game")
    void deleteAll();
}





/*
    @Query("SELECT Game.id, Course.name, Player.name From Game " +
        "INNER JOIN Course ON Game.book_id = Course.id " +
        "INNER JOIN Player ON Game.user_id = Player.id ")
    LiveData<List<LoanWithUserAndBook>> findAllWithUserAndBook();

    @Query("SELECT Game.id, Course.name as title, Player.name as name, Game.startTime, Game.endTime " +
            "FROM Course " +
            "INNER JOIN Game ON Game.book_id = Course.id " +
            "INNER JOIN Player on Player.id = Game.user_id " +
            "WHERE Player.name LIKE :userName " +
            "AND Game.endTime > :after "
    )
    LiveData<List<LoanWithUserAndBook>> findLoansByNameAfter(String userName, Date after);
    */