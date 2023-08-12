package com.daedrii.tasklist.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.daedrii.tasklist.model.Task
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskItemInstrumentedTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ShouldRenderTaskItemProperly(){

        val task = Task(0, "Test", "Teste")

        composeTestRule.setContent {
            TaskItem(task = task, navController = rememberNavController() )
        }

        composeTestRule.onNodeWithText(task.taskTitle).assertExists()
        composeTestRule.onNodeWithText(task.taskDescription.toString()).assertExists()
        composeTestRule.onNodeWithContentDescription("Delete Button").assertExists()
        composeTestRule.onNodeWithTag("PriorityCard").assertExists()
    }

}