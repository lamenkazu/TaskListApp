package com.daedrii.tasklist.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.daedrii.tasklist.R
import com.daedrii.tasklist.components.TaskItem
import com.daedrii.tasklist.model.Task
import com.daedrii.tasklist.model.TaskDAO
import com.daedrii.tasklist.ui.theme.Black
import com.daedrii.tasklist.ui.theme.Pink40
import com.daedrii.tasklist.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(navController: NavController) {
    
    Scaffold(
        containerColor = Black,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          navController.navigate("addTask")
                },
                containerColor = Pink40,
                modifier = Modifier.testTag("addTaskButton")
            ) {
                Icon(imageVector = ImageVector.vectorResource(
                    id = R.drawable.ic_add),
                    contentDescription = "Ícone Adicionar Tarefa"
                )
            }
        }
    ) {
        it.toString()
        val taskDAO = TaskDAO(LocalContext.current)
        val taskList = mutableListOf<Task>()
        //taskList.add(Task(0, "Title Test", "Task Description"))
        taskList.addAll(taskDAO.getAll())
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(80.dp)
            ){
                Text(
                    text = "Lista de Tarefas",
                    color = White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.testTag("TaskListPageTitle")

                )
            }

            LazyColumn{
                itemsIndexed(taskList){position, _ ->
                    TaskItem(taskList[position], navController)
                }
            }
        }
    }

}
@Composable
@Preview
private fun TaskListScreenPreview(){
    val navController = rememberNavController()
    TaskList(navController)
}