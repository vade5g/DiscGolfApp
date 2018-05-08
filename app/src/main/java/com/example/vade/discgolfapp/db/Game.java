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

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Course.class,
                parentColumns = "id",
                childColumns = "course_id"),

        @ForeignKey(entity = Player.class,
                parentColumns = "id",
                childColumns = "player_id")})
@TypeConverters(DateConverter.class)
public class Game {
    // Fields can be public or private with getters and setters.
    @PrimaryKey
    @NonNull
    public String id;

    public int totalScore;

    public String score;


    @ColumnInfo(name="course_id")
    public String courseId;

    @ColumnInfo(name="player_id")
    public String playerId;
}
