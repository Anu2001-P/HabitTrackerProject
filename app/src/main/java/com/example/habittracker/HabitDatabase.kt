package com.example.habittracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HabitItems::class], version =1)

abstract class HabitDatabase : RoomDatabase() {

    abstract fun getHabitDoa(): Dao

    companion object {
        @Volatile
        private var INSTANCE: HabitDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) =
            INSTANCE ?: synchronized(LOCK) {

                INSTANCE ?: createDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HabitDatabase::class.java,
                "Habit.db"
            )
                .build()

    }
}