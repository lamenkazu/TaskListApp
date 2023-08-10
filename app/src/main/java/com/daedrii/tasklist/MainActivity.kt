package com.daedrii.tasklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daedrii.tasklist.ui.theme.TaskListTheme
import com.daedrii.tasklist.view.addTask
import com.daedrii.tasklist.view.TaskList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskListTheme {

                val navController = rememberNavController()

                NavHost(navController, startDestination = "taskList" ){
                    composable(route = "taskList"){
                        TaskList(navController)
                    }

                    composable(route = "addTask"){
                        addTask(navController)
                    }
                }

            }
        }
    }
}