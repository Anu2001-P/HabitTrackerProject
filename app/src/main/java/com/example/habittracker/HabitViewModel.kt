package com.example.habittracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HabitViewModel (private val repository: HabitRepository): ViewModel(){
    fun insert(items: HabitItems) = GlobalScope.launch{
        repository.insert(items)
    }
    fun delete(items: HabitItems) = GlobalScope.launch {
        repository.delete(items)
    }

    fun searchDatabase(searchQuery: String): LiveData<List<HabitItems>> {
        return repository.searchDatabase(searchQuery)
    }


    fun getItems() = repository.getAllItems()
}
