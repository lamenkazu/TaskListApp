package com.daedrii.tasklist.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SendButtonInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ShouldDisplaySendButtonTextProperly(){

        val buttonText = "Enviar"


        composeTestRule.setContent {
            SendButton(
                onClick = { },
                modifier = Modifier ,
                text = buttonText,
                shape = CircleShape
            )
        }

        composeTestRule.onNodeWithText(buttonText).assertIsDisplayed()


    }

    @Test
    fun ShouldPerformClickOfButtonProperly(){
        var clicked = false

        composeTestRule.setContent {
            SendButton(
                onClick = { clicked = true  },
                modifier = Modifier ,
                text = "",
                shape = CircleShape
            )
        }
        composeTestRule.onNode(hasClickAction()).performClick()

        // Verifique se a ação esperada ocorreu (no caso, a variável "clicked" foi alterada)
        assert(clicked)
    }

}