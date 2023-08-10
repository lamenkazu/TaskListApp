package com.daedrii.tasklist.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.daedrii.tasklist.ui.theme.Pink40
import com.daedrii.tasklist.ui.theme.White

@Composable
fun SendButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    shape: Shape
){
    
    Button(
        onClick, modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Pink40,
            contentColor = White
        ),
        shape = shape
    ) {
        Text(
            text,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
    
}