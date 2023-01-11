package com.m3.todoapp.database.dao

import androidx.room.*
import com.m3.todoapp.database.model.Task

@Dao
interface TasksDao {

    @Insert
    fun addTask( task: Task)
    @Update
    fun updateTask( task: Task)
    @Delete
    fun deleteTask( task: Task)

    @Query("SELECT * FROM Task")
    fun getAllTask(): List<Task>

    @Query("SELECT * FROM Task where title like :word or description like :word")
    fun searchForTask(word:String): List<Task>

    @Query("SELECT * FROM Task where title like :word")
    fun getItemTask(word:String): Task





}