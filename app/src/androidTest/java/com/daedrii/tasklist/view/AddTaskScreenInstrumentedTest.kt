package com.daedrii.tasklist.view

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddTaskScreenInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ShouldNotAllowToSendTaskToDatabaseWhenTitleIsEmpty(){
        composeTestRule.setContent {
            AddTask(navController = rememberNavController())
        }
        composeTestRule.onNodeWithTag("Button Send").performClick()

        GlobalScope.launch {
            delay(1000) // Pausa a execução por 1 segundo sem bloquear a thread
            composeTestRule.onNodeWithText("Sua tarefa deve ter um titulo").assertExists()
        }

    }

    @Test
    fun ShouldRenderAddTaskScreenProperly(){
        composeTestRule.setContent {
            AddTask(navController = rememberNavController())
        }

        composeTestRule.onNodeWithContentDescription("Back Button").assertExists()

        composeTestRule.onNodeWithTag("PageTitleNewTask").assertExists()
        composeTestRule.onNodeWithTag("taskTitle").assertExists()
        composeTestRule.onNodeWithTag("taskDescription").assertExists()


        composeTestRule.onNodeWithTag("labelPriority").assertExists()
        composeTestRule.onNodeWithTag("radioLowPrio").assertExists()
        composeTestRule.onNodeWithTag("radioMidPrio").assertExists()
        composeTestRule.onNodeWithTag("radioHighPrio").assertExists()

        composeTestRule.onNodeWithTag("Button Send").assertExists()

    }

}