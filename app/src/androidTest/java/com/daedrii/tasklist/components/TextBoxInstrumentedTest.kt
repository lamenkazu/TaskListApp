package com.daedrii.tasklist.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextBoxInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ShouldRenderTextBoxCorrectly(){
        val value = "Texto de Teste"

        composeTestRule.setContent{
            TextBox(
                value = value,
                onValueChange = {  },
                modifier = Modifier.fillMaxWidth(),
                label = "Test",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )
        }
        composeTestRule.onNodeWithText(value).assertIsDisplayed()
    }

    @Test
    fun ShouldInputTextInBoxCorrectly(){
        val value = ""
        var capturedValue = ""
        val onValueChange: (String) -> Unit = {
            capturedValue = it
        }

        composeTestRule.setContent{
            TextBox(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                label = "Test",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )
        }

        val txtUpdate = "Texto Inserido"
        composeTestRule.onNodeWithText(value).performTextInput(txtUpdate)
        assert(capturedValue == value + txtUpdate)

    }
}