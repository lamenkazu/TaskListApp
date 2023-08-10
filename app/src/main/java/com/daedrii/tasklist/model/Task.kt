package com.daedrii.tasklist.model

data class Task(
    val taskTitle: String?,
    val taskDescription: String?,
    val taskPriority: Priority = Priority.NONE
){
    enum class Priority{
        NONE,
        LOW,
        MID,
        HIGH
    }
}