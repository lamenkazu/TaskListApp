package com.daedrii.tasklist.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.daedrii.tasklist.R
import com.daedrii.tasklist.model.Task
import com.daedrii.tasklist.ui.theme.Black
import com.daedrii.tasklist.ui.theme.GreenSelected
import com.daedrii.tasklist.ui.theme.Purple80
import com.daedrii.tasklist.ui.theme.RedSelected
import com.daedrii.tasklist.ui.theme.ShapeButton
import com.daedrii.tasklist.ui.theme.YellowSelected

@Composable
fun TaskItem(task: Task) {

    val priorityColor = when(task.taskPriority){
        Task.Priority.NONE -> {
            Black
        }
        Task.Priority.LOW -> {
            GreenSelected
        }
        Task.Priority.MID -> {
            YellowSelected
        }
        Task.Priority.HIGH -> {
            RedSelected
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Purple80
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {

            val(txtTitle, txtDescription,
                cardPriority, btnDelete) = createRefs()

            Card(
                shape = ShapeButton.large,
                colors = CardDefaults.cardColors(
                    containerColor = priorityColor
                ),

                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(cardPriority) {
                        start.linkTo(parent.start, margin = 10.dp)
                        top.linkTo(parent.top, margin = 15.dp)
                    }
            ){

            }

            Text(
                text = task.taskTitle.toString(),
                modifier = Modifier
                    .constrainAs(txtTitle){
                        start.linkTo(cardPriority.end, margin = 10.dp)
                        top.linkTo(parent.top, margin = 10.dp)
                    },
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp
            )

            Text(
                text = task.taskDescription.toString(),
                modifier = Modifier
                    .width(280.dp)
                    .constrainAs(txtDescription){
                        start.linkTo(parent.start, margin = 10.dp)
                        top.linkTo(txtTitle.bottom, margin = 10.dp)
                    },
                fontSize = 18.sp
            )

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.constrainAs(btnDelete){
                    start.linkTo(parent.end)
                    bottom.linkTo(parent.top, margin = (-40).dp)
                }
            ) {

                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete_forever), contentDescription = null, tint = RedSelected)

            }

        }
    }

}

@Preview
@Composable
private fun TaskItemPreview(){
    TaskItem(Task("Tarefa Teste", "blaablablabla", Task.Priority.LOW))
}