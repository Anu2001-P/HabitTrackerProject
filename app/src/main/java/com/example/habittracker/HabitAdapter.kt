package com.example.habittracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitAdapter (
    var list : List<HabitItems>,
    val habitItemClickInterface:HabitItemsClickInterface
): RecyclerView.Adapter<HabitAdapter.HabitViewHolder>(){


    inner class HabitViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val name  = itemView.findViewById<TextView>(R.id.Itemname)
        val time = itemView.findViewById<TextView>(R.id.Itemquan)
        val dur = itemView.findViewById<TextView>(R.id.Itemrate)
        val delete = itemView.findViewById<ImageView>(R.id.delete)



    }
    interface HabitItemsClickInterface{
        fun onItemsClick(habitItems: HabitItems)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {


        holder.name.text = list.get(position).habitName

        holder.time.text = "Time: "+list.get(position).habitTime.toString()

        holder.dur.text = "Duration "+list.get(position).habitDuration.toString()

        holder.delete.setOnClickListener {
            habitItemClickInterface.onItemsClick(list.get(position))
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun setData(newList : List<HabitItems>){
        list = newList
        notifyDataSetChanged()
    }
}

