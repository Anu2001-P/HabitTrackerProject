package com.example.habittracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_items")
data class HabitItems (


    @ColumnInfo(name = "Name")
    var habitName: String,

    @ColumnInfo(name = "Time")
    var habitTime: String,

    @ColumnInfo(name = "Duration")
    var habitDuration : String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
