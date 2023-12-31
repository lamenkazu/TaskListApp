package com.daedrii.tasklist.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.daedrii.tasklist.R
import com.daedrii.tasklist.components.SendButton
import com.daedrii.tasklist.components.TextBox
import com.daedrii.tasklist.model.Task
import com.daedrii.tasklist.model.TaskDAO
import com.daedrii.tasklist.ui.theme.GreenDisabled
import com.daedrii.tasklist.ui.theme.GreenSelected
import com.daedrii.tasklist.ui.theme.RedDisabled
import com.daedrii.tasklist.ui.theme.RedSelected
import com.daedrii.tasklist.ui.theme.ShapeSendButton
import com.daedrii.tasklist.ui.theme.YellowDisabled
import com.daedrii.tasklist.ui.theme.YellowSelected

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTask(navController: NavController){

    var taskTitle by remember {
        mutableStateOf("")
    }
    var taskDescription by remember{
        mutableStateOf("")
    }
    var taskPriority by remember{
        mutableStateOf(Task.Priority.NONE)
    }

    Scaffold {
        Column {

            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                    contentDescription = "Back Button"
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Inserir Nova Tarefa",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    modifier = Modifier.testTag("PageTitleNewTask")
                )

                TaskTitle(taskTitle){
                    taskTitle = it
                }
                TaskDescription(taskDescription) {
                    taskDescription = it
                }

                TaskPriority {
                    taskPriority = it
                }

                SendTask(taskTitle, taskDescription, taskPriority, navController)

            }
        }
    }

}

@Composable
private fun TaskTitle(taskTitle: String, onTaskTitleChange: (String) -> Unit){

    TextBox(
        value = taskTitle,
        onValueChange = {
            onTaskTitleChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 20.dp, 20.dp, 0.dp)
            .testTag("taskTitle"),
        label = "Titulo da Tarefa",
        maxLines = 1,
        keyboardType = KeyboardType.Text
    )
}
@Composable
private fun TaskDescription(taskDescription: String, onTaskDescriptionChange: (String) -> Unit){

    TextBox(
        value = taskDescription,
        onValueChange = {
            onTaskDescriptionChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(20.dp, 10.dp, 20.dp, 0.dp)
            .testTag("taskDescription"),
        label = "Descrição da Tarefa",
        maxLines = 5,
        keyboardType = KeyboardType.Text
    )

}

@Composable
private fun TaskPriority(onTaskPrioChange: (Task.Priority) -> Unit) {

    var noPrio by remember{
        mutableStateOf(false)
    }
    var lowPrio by remember{
        mutableStateOf(false)
    }
    var midPrio by remember{
        mutableStateOf(false)
    }
    var highPrio by remember{
        mutableStateOf(false)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,

        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Nível de Prioridade",
            modifier = Modifier.testTag("labelPriority")
        )

        RadioButton(
            selected = lowPrio,
            onClick = {
                lowPrio = !lowPrio
                midPrio = false
                highPrio = false
                noPrio = !lowPrio

                if(lowPrio){
                    onTaskPrioChange(Task.Priority.LOW)
                }else{
                    onTaskPrioChange(Task.Priority.NONE)
                }
            },
            colors = RadioButtonDefaults.colors(
                unselectedColor = GreenDisabled,
                selectedColor = GreenSelected
            ),
            modifier = Modifier.testTag("radioLowPrio")
        )

        RadioButton(
            selected = midPrio,
            onClick = {
                midPrio = !midPrio
                lowPrio = false
                highPrio = false
                noPrio = false

                if(midPrio){
                    onTaskPrioChange(Task.Priority.MID)
                }else{
                    onTaskPrioChange(Task.Priority.NONE)
                }

            },
            colors = RadioButtonDefaults.colors(
                unselectedColor = YellowDisabled,
                selectedColor = YellowSelected
            ),
            modifier = Modifier.testTag("radioMidPrio")
        )

        RadioButton(
            selected = highPrio,
            onClick = {
                highPrio = !highPrio
                midPrio = false
                lowPrio = false
                noPrio = false

                if(highPrio){
                    onTaskPrioChange(Task.Priority.HIGH)
                }else{
                    onTaskPrioChange(Task.Priority.NONE)
                }

            },
            colors = RadioButtonDefaults.colors(
                unselectedColor = RedDisabled,
                selectedColor = RedSelected
            ),
            modifier = Modifier.testTag("radioHighPrio")
        )

    }

}

@Composable
private fun SendTask(taskTitle: String, taskDescription: String, taskPriority: Task.Priority, navController: NavController) {
    val context = LocalContext.current // Obter o contexto atual
    val taskDAO = TaskDAO(context)
    SendButton(
        onClick = {
            if(taskTitle.isBlank()){
                Toast.makeText(context, "Sua tarefa deve ter um titulo", Toast.LENGTH_SHORT).show()

            }else{
                taskDAO.insert(Task(taskDAO.getAll().size, taskTitle, taskDescription, taskPriority))

                navController.navigate("addTask"){
                    popUpTo("addTask"){
                        inclusive = true
                    }
                }
                Toast.makeText(context, "Sua tarefa foi salva com sucesso", Toast.LENGTH_SHORT).show()

            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .testTag("Button Send"),
        text = "Salvar",
        shape = ShapeSendButton.small
    )
}

@Composable
@Preview
private fun AddTaskScreenPreview(){
    val navController = rememberNavController()
    AddTask(navController)
}
