package com.m3.todoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.m3.todoapp.database.dao.TasksDao
import com.m3.todoapp.database.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TasksDataBase:RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    //static fun
    //signelton..constract one obj
    companion object {
        private var database: TasksDataBase? = null
        fun getInstance(): TasksDataBase {
            if (database == null) {

                ///
            }
            return database!!
        }
    }
}




