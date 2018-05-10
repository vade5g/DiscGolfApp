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
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface CourseDao {

    @Query("select * from Course where id = :id")
    Course loadCourseById(int id);

    @Query("select * from Course where name= :name")
    Course findCourseByName(String name);

    /*@Query("SELECT * FROM Course " +
            "INNER JOIN Game ON Game.id = Course.id " +
            "INNER JOIN Player on Player.id = Game.id" +
            "WHERE Player.name LIKE :userName"
    )
    LiveData<List<Course>> findCoursesPlayedByName(String userName);
    */
    /*
    @Query("SELECT * FROM Course " +
            "INNER JOIN Game ON Game.book_id = Course.id " +
            "INNER JOIN Player on Player.id = Game.user_id " +
            "WHERE Player.name LIKE :userName " +
            "AND Game.endTime > :after "
    )
    LiveData<List<Course>> findBooksBorrowedByNameAfter(String userName, Date after);
    */
    @Query("SELECT * FROM Course " +
            "INNER JOIN Game ON Game.id = Course.id " +
            "INNER JOIN Player on Player.id = Game.id " +
            "WHERE Player.name LIKE :userName"
    )
    List<Course> findCoursesPlayedByNameSync(String userName);

    @Query("SELECT * FROM Course " +
            "INNER JOIN Game ON Game.id LIKE Course.id " +
            "WHERE Game.player_id LIKE :userId "
    )
    LiveData<List<Course>> findGamesPlayedByUser(String userId);
    /*
    @Query("SELECT * FROM Course " +
            "INNER JOIN Game ON Game.book_id LIKE Course.id " +
            "WHERE Game.user_id LIKE :userId " +
            "AND Game.endTime > :after "
    )
    LiveData<List<Course>> findBooksBorrowedByUserAfter(String userId, Date after);
    */
    /*
    @Query("SELECT * FROM Course " +
            "INNER JOIN Game ON Game.book_id LIKE Course.id " +
            "WHERE Game.user_id LIKE :userId "
    )
    List<Course> findBooksBorrowedByUserSync(String userId);
    */
    @Query("SELECT * FROM Course")
    LiveData<List<Course>> findAllBooks();


    @Query("SELECT * FROM Course")
    List<Course> findAllBooksSync();

    @Insert(onConflict = IGNORE)
    void insertBook(Course course);

    @Update(onConflict = REPLACE)
    void updateBook(Course course);

    @Query("DELETE FROM Course")
    void deleteAll();
}
