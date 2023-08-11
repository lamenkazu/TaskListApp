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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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

    Scaffold {
        Column {

            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                    contentDescription = null
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
                    fontSize = 32.sp
                )

                val taskTitle = taskTitle()

                val taskDescription = taskDescription()

                val taskPriority = taskPriority()

                val context = LocalContext.current // Obter o contexto atual
                val taskDAO = TaskDAO(context)
                SendButton(
                    onClick = {
                        if(taskTitle.isBlank()){
                            Toast.makeText(context, "Sua tarefa deve ter um titulo", Toast.LENGTH_SHORT).show()

                        }else{
                            taskDAO.insert(Task(taskDAO.getAll().size, taskTitle, taskDescription, taskPriority))
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp) ,
                    text = "Salvar",
                    shape = ShapeSendButton.small
                )

            }
        }
    }

}
@Composable
fun taskPriority(): Task.Priority {

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

    var actualPriority: Task.Priority by remember{
        mutableStateOf(Task.Priority.NONE)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,

        modifier = Modifier.fillMaxWidth()
    ) {

        Text(text = "Nível de Prioridade")

        RadioButton(
            selected = lowPrio,
            onClick = {
                lowPrio = !lowPrio
                midPrio = false
                highPrio = false
                noPrio = !lowPrio

                actualPriority = if(lowPrio){
                    Task.Priority.LOW
                }else{
                    Task.Priority.NONE
                }

            },
            colors = RadioButtonDefaults.colors(
                unselectedColor = GreenDisabled,
                selectedColor = GreenSelected
            )
        )

        RadioButton(
            selected = midPrio,
            onClick = {
                midPrio = !midPrio
                lowPrio = false
                highPrio = false
                noPrio = false

                actualPriority = if(midPrio){
                    Task.Priority.MID
                }else{
                    Task.Priority.NONE
                }

            },
            colors = RadioButtonDefaults.colors(
                unselectedColor = YellowDisabled,
                selectedColor = YellowSelected
            )
        )

        RadioButton(
            selected = highPrio,
            onClick = {
                highPrio = !highPrio
                midPrio = false
                lowPrio = false
                noPrio = false

                actualPriority = if(highPrio){
                    Task.Priority.HIGH
                }else{
                    Task.Priority.NONE
                }

            },
            colors = RadioButtonDefaults.colors(
                unselectedColor = RedDisabled,
                selectedColor = RedSelected
            )
        )

    }

    return actualPriority

}
@Composable
fun taskTitle(): String{

    var taskTitle by remember {
        mutableStateOf("")
    }

    TextBox(
        value = taskTitle,
        onValueChange = {
            taskTitle = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 20.dp, 20.dp, 0.dp),
        label = "Titulo da Tarefa",
        maxLines = 1,
        keyboardType = KeyboardType.Text
    )

    return taskTitle
}
@Composable
fun taskDescription(): String{

    var taskDescription by remember{
        mutableStateOf("")
    }

    TextBox(
        value = taskDescription,
        onValueChange = {
            taskDescription = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(20.dp, 10.dp, 20.dp, 0.dp),
        label = "Descrição da Tarefa",
        maxLines = 5,
        keyboardType = KeyboardType.Text
    )

    return taskDescription
}
