package com.m3.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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
        private val DATABASE_NAME="tasks-db"
        fun getInstance(context: Context): TasksDataBase {
            if (database == null) {             //context   //2sm class database  / 2sm database
                database= Room.databaseBuilder(context,TasksDataBase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()//delete if schema change or we cAN provide migration
                    .allowMainThreadQueries()//allow accessing db in ui thread//or use kotlin coroutines
                    .build()

            }
            return database!!
        }
    }
}




