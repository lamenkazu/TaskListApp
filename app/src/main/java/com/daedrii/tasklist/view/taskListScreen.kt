package com.daedrii.tasklist.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.daedrii.tasklist.R
import com.daedrii.tasklist.ui.theme.Black
import com.daedrii.tasklist.ui.theme.Pink40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun taskList(navController: NavController) {
    
    Scaffold(
        containerColor = Black,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          navController.navigate("addTask")
                },
                containerColor = Pink40
            ) {
                Icon(imageVector = ImageVector.vectorResource(
                    id = R.drawable.ic_add),
                    contentDescription = "Ícone Adicionar Tarefa"
                )
            }
        }
    ) {
        it
    }

}