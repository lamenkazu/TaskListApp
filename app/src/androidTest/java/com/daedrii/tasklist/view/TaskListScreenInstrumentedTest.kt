package com.daedrii.tasklist.view

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskListScreenInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldRenderTaskListScreenProperly(){
        composeTestRule.setContent {
            TaskList(navController = rememberNavController())
        }

        composeTestRule.onNodeWithTag("addTaskButton").assertExists()
        composeTestRule.onNodeWithTag("TaskListPageTitle").assertExists()

    }

}