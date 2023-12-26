package com.example.habittracker

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),HabitAdapter.HabitItemsClickInterface, SearchView.OnQueryTextListener{
    lateinit var addfab : FloatingActionButton
    lateinit var items: RecyclerView
    lateinit var list: List<HabitItems>
    lateinit var habitAdapter: HabitAdapter
    lateinit var habitViewModel: HabitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        items = findViewById(R.id.items)
        addfab = findViewById(R.id.fab)
        list = ArrayList<HabitItems>()
        habitAdapter = HabitAdapter(list,this)
        items.layoutManager = LinearLayoutManager(this)
        items.adapter = habitAdapter
        val habitRepo = HabitRepository(HabitDatabase(this))
        val factory = HabitViewModelFactory(habitRepo)
        habitViewModel =  ViewModelProvider(this,factory).get(HabitViewModel::class.java)
        habitViewModel.getItems().observe(this, Observer{
            habitAdapter.list = it
            habitAdapter.notifyDataSetChanged()
        })

        addfab.setOnClickListener{
            openDialog()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)

        val search =  menu?.findItem(R.id.search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        return true
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchDatabase(newText)
        }
        return true

    }
    private fun searchDatabase(query : String) {

        val searchQuery = "%$query%"

        habitViewModel.searchDatabase(searchQuery).observe(this, Observer { list ->
            list?.let {
                habitAdapter.setData(it)

            }
        })

    }

    fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.add_dialog)
        val cancel = dialog.findViewById<Button>(R.id.cancel_button)
        val add = dialog.findViewById<Button>(R.id.add_button)
        val habitNameEdit = dialog.findViewById<EditText>(R.id.EditItemName)
        val habitDurationEdit = dialog.findViewById<EditText>(R.id.EditItemRate)
        val habitTimeEdit = dialog.findViewById<EditText>(R.id.EditItemQuantity)
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        add.setOnClickListener{
            val habitName : String = habitNameEdit.text.toString()
            val habitDuration : String = habitDurationEdit.text.toString()
            val habitTime : String = habitTimeEdit.text.toString()
            if (habitName.isNotEmpty()&&habitDuration.isNotEmpty()&&habitTime.isNotEmpty()){

                val items = HabitItems(habitName,habitTime,habitDuration)
                habitViewModel.insert(items)
                Toast.makeText(applicationContext,"Habit Inserted", Toast.LENGTH_SHORT).show()
                habitAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            else{
                Toast.makeText(applicationContext,"PLease enter all fields", Toast.LENGTH_SHORT).show()
                habitAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }

        }
        dialog.show()


    }

    override fun onItemsClick(habitItems: HabitItems) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.dialogTitle)
        builder.setMessage(R.string.dialogMessage)

        builder.setNegativeButton("Yes") { dialogInterface, which ->

            habitViewModel.delete(habitItems)
            habitAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext, "Item Deleted", Toast.LENGTH_LONG).show()
        }
        builder.setPositiveButton("No") { dialogInterface, which ->

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()


    }



}





