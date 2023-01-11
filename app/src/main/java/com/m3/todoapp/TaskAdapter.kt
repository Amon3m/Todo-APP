package com.m3.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.m3.todoapp.database.model.Task


class TaskAdapter (var tasks: List<Task>): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title :TextView=itemView.findViewById(R.id.title_item)
        val checkBox:CheckBox=itemView.findViewById(R.id.completed_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task=tasks.get(position)
        holder.title.setText(task.title)
        holder.checkBox.isChecked=task.iscomplited?:false
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener {
                onItemClickListener?.OnItemClick(task,position)
            }
        }

            if (onItemLongClickListener!=null){
                holder.itemView.setOnLongClickListener {
                   onItemLongClickListener?.OnItemLongClick(task,position)
                    true
                }

                }

        }

    var onItemLongClickListener:OnItemLongClickListener?=null
    interface OnItemLongClickListener{
        fun OnItemLongClick(tasks: Task,position: Int)

    }
    var onItemClickListener:OnItemClickListener?=null
    interface OnItemClickListener{
        fun OnItemClick(tasks: Task,position: Int)



    }


    override fun getItemCount(): Int {
        return tasks.size
    }

    fun changeData(tasks: List<Task>) {
    this.tasks=tasks


        notifyDataSetChanged()

    }

}