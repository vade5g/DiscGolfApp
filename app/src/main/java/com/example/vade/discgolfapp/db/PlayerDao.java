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

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface PlayerDao {
    @Query("select * from Player")
    List<Player> loadAllUsers();

    @Query("SELECT * FROM Player WHERE bestScore < :score")
    List<Player> findYoungerThan(int score);

    @Query("select * from Player where id = :id")
    Player loadUserById(int id);

    @Query("select * from Player where name = :firstName and gamesPlayed = :played")
    List<Player> findUserByNameAndLastName(String firstName, String played);


    @Query("select * from Player where name = :name")
    Player findPlayerByName(String name);

    @Insert(onConflict = IGNORE)
    void insertUser(Player player);

    @Delete
    void deleteUser(Player player);

    @Query("delete from Player where name like :badName like :badName")
    int deleteUsersByName(String badName);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceUsers(Player... players);

    @Delete
    void deleteUsers(Player player1, Player player2);

    @Query("SELECT * FROM Player WHERE :age == :age") // TODO: Fix this!
    List<Player> findUsersYoungerThan(int age);

    @Query("SELECT * FROM Player WHERE gamesPlayed < :played")
    List<Player> findUsersThatHavePlayedLessThan(int played);

    @Query("DELETE FROM Player")
    void deleteAll();
}