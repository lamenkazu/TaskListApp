package com.daedrii.tasklist.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class TaskDAO(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        private const val DB_VERSION = 1
        private const val DB_NAME = "tasks_db"
        private const val TABLE_TASKS = "tasks"
        private const val KEY_ID = "id"
        private const val KEY_TITLE = "title"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_PRIORITY = "priority"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sqlQuerry = "CREATE TABLE $TABLE_TASKS (" +
                "$KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$KEY_TITLE TEXT, " +
                "$KEY_DESCRIPTION TEXT," +
                "$KEY_PRIORITY INTEGER" +
                ")"
        db?.execSQL(sqlQuerry)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")
        onCreate(db)
    }

    fun insert(task: Task): Boolean{
        val db = writableDatabase
        val values = ContentValues()

        values.put(KEY_ID, task.taskId)
        values.put(KEY_TITLE, task.taskTitle)
        values.put(KEY_DESCRIPTION, task.taskDescription)
        values.put(KEY_PRIORITY, task.taskPriority.ordinal)

        return db.insert(TABLE_TASKS, null, values) != -1L
    }

    fun delete(task: Task): Boolean{
        val db = writableDatabase
        val whereClause = "$KEY_ID = ? AND $KEY_TITLE = ? AND $KEY_DESCRIPTION = ? AND $KEY_PRIORITY = ?"
        val whereArgs = arrayOf(task.taskId.toString(), task.taskTitle, task.taskDescription, task.taskPriority.ordinal.toString())

        Log.d("Delete Task","${task.taskTitle} ${task.taskDescription} ${task.taskPriority.toString()} ")

        return db.delete(TABLE_TASKS, whereClause, whereArgs) != 0
    }


    @SuppressLint("Range")
    fun getAll(): ArrayList<Task>{

        val db = readableDatabase
        val selectQuerry = "SELECT * FROM $TABLE_TASKS"
        val productList = ArrayList<Task>()

        val cursor: Cursor? = db.rawQuery(selectQuerry, null)

        cursor?.apply {
            if(moveToFirst()){
                do{
                    val id = getInt((getColumnIndex(KEY_ID)))
                    val title = getString(getColumnIndex(KEY_TITLE))
                    val description = getString(getColumnIndex(KEY_DESCRIPTION))
                    val priorityOrdinal = getInt(getColumnIndex(KEY_PRIORITY))
                    val priority = Task.Priority.values()[priorityOrdinal]
                    productList.add(Task(id, title, description, priority))

                }while(moveToNext())
            }
            close()
        }

        return productList
    }
}