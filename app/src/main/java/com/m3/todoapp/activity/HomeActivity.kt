package com.m3.todoapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.m3.islami2.base.BaseActivity
import com.m3.todoapp.Conestent
import com.m3.todoapp.DetailsFragment
import com.m3.todoapp.TaskAdapter
import com.m3.todoapp.database.TasksDataBase
import com.m3.todoapp.database.model.Task
import com.m3.todoapp.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {
    lateinit var adapter: TaskAdapter

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.add.setOnClickListener {

            startaddActivity()

        }
        initRecyclerView()
        swipTODelete()
//        binding.add.setOnLongClickListener {
//            makeToast(message = "hi")
//            true
//        }
        adapter.onItemClickListener=object :TaskAdapter.OnItemClickListener{
            override fun OnItemClick(tasks: Task, position: Int) {
                showDetailsDialogue(position, tasks)

            }
        }
        adapter.onItemLongClickListener=object :TaskAdapter.OnItemLongClickListener{
            override fun OnItemLongClick(tasks: Task, position: Int) {
                startEditActivity(tasks)
            }


        }



    }


    private fun showDetailsDialogue(position: Int, task: Task) {
        val dialogue= DetailsFragment(position, task)
        dialogue.show(supportFragmentManager,"")
    }
    private fun initRecyclerView() {
        adapter = TaskAdapter(listOf())
        binding.recyclerview.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        val task = (TasksDataBase.getInstance(applicationContext).tasksDao().getAllTask())
        adapter.changeData(task)
    }

    private fun startaddActivity() {
        intent = Intent(this, AddTaskActivity::class.java)
        startActivity(intent)
    }

    private fun startEditActivity(tasks: Task) {
        val intent = Intent(this, UpdateActivity::class.java)
       intent.putExtra(Conestent.Extra_Tasks,tasks.title)
       intent.putExtra(Conestent.Extra_Id,tasks.id)
        startActivity(intent)

    }




    private fun swipTODelete() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
            return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position= viewHolder.adapterPosition
                val item =adapter.tasks.get(position)
                var counter=false


                showDialoge(message="you want delete task",
                    posActionName ="undo",

                    posAction = { dialog, which->
                        dialog.dismiss()
                        counter=true
                        val newtask = TasksDataBase.getInstance(applicationContext).tasksDao().getAllTask()
                        adapter.changeData(newtask)


                    }
                )

                Handler(Looper.getMainLooper()).postDelayed({

                    if(!counter){
                        recreate()
                        TasksDataBase.getInstance(applicationContext).tasksDao().deleteTask(item)
                        val newtask = TasksDataBase.getInstance(applicationContext).tasksDao().getAllTask()
                        adapter.changeData(newtask)


                    }}, 3000)
            }
//                if(!counter){
//                    TasksDataBase.getInstance(applicationContext).tasksDao().deleteTask(item)
//                    val newtask = TasksDataBase.getInstance(applicationContext).tasksDao().getAllTask()
//                    adapter.changeData(newtask)
//
//                }
                //adapter.tasks.drop(position)

                //TasksDataBase.getInstance(applicationContext).tasksDao().deleteTask(item)

//                val newtask = TasksDataBase.getInstance(applicationContext).tasksDao().getAllTask()
//                adapter.changeData(newtask)






        }).attachToRecyclerView(binding.recyclerview)
    }
}

