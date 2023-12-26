package com.example.habittracker

import androidx.lifecycle.LiveData

class HabitRepository(private val db : HabitDatabase) {
    suspend fun insert(items: HabitItems) = db.getHabitDoa().insert(items)
    suspend fun delete(items: HabitItems) = db.getHabitDoa().delete(items)

    fun searchDatabase(searchQuery: String): LiveData<List<HabitItems>> {
        return db.getHabitDoa().searchDatabase(searchQuery)
    }



    fun getAllItems() = db.getHabitDoa().getItems()
}