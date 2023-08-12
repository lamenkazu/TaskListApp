package com.daedrii.tasklist.model

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class TaskDAOInstrumentedTest {


    private val testTestTaskId = 10000
    private val testTestTaskTitle = "Test Title"
    private val testTestTaskDescription  = "Test Description"
    private val testTestTaskPrio = Task.Priority.HIGH
    private val testTask = Task(
                                testTestTaskId,
                                testTestTaskTitle,
                                testTestTaskDescription,
                                testTestTaskPrio
                            )

    private val context: Context =  InstrumentationRegistry.getInstrumentation().targetContext
    private val testTaskDAO = TaskDAO(context)

    @Test
    fun test1_ShouldInsertTaskInSQLiteDatabaseSuccessfully(){

        assert(testTaskDAO.insert(testTask))
    }

    @Test
    fun test3_ShouldDeleteTaskInSQLiteDatabaseSuccessfully() {

        assert(testTaskDAO.delete(testTask))
    }

    @Test
    fun test2_ShouldGetAllTasksInDatabaseSuccessfully(){

        val taskList = mutableListOf<Task>()

        if(testTaskDAO.getAll().isNotEmpty()){

            assert(taskList.addAll(testTaskDAO.getAll()))
        }

    }

}