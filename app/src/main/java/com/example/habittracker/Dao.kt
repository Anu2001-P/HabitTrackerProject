package com.example.habittracker

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: HabitItems)

    @Delete
    suspend fun delete(item : HabitItems)


    @Query("SELECT * FROM habit_items ")
    fun getItems(): LiveData<List<HabitItems>>
    @Query("SELECT *FROM habit_items WHERE Name LIKE :searchQuery")
    fun searchDatabase(searchQuery: String) : LiveData<List<HabitItems>>





}