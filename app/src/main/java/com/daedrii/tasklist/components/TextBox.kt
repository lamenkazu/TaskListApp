package com.daedrii.tasklist.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.daedrii.tasklist.ui.theme.Black
import com.daedrii.tasklist.ui.theme.Pink40
import com.daedrii.tasklist.ui.theme.Pink80
import com.daedrii.tasklist.ui.theme.ShapeTextBox
import com.daedrii.tasklist.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType
){

    OutlinedTextField(
        value = value,
        onValueChange,
        modifier,
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        label = {
            Text(text = label)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Black,
            focusedBorderColor = Pink40,
            focusedLabelColor = Pink40,
            containerColor = White,
            cursorColor = Pink40
        ),
        shape = ShapeTextBox.small
    )

}

@Composable
@Preview
fun TextBoxPreview(){
    TextBox(
        value = "Erick",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        label = "Descrição",
        maxLines = 1,
        keyboardType = KeyboardType.Text
    )
}