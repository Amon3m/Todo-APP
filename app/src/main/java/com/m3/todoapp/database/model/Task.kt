package com.m3.todoapp.database.model

import android.accounts.AuthenticatorDescription
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Task(
    @ColumnInfo @PrimaryKey (autoGenerate = true)   var id:Int?=null,
    @ColumnInfo    var title:String?=null,
    @ColumnInfo    var description:String?=null,
    @ColumnInfo    var iscomplited:Boolean?=null

)
