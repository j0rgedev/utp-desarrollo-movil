package com.example.finalapp.utils

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import com.example.finalapp.data.local.entity.Teacher

val TeacherSaver: Saver<Teacher, Any> = listSaver(
    save = { listOf(it.code, it.names, it.fathersLastName, it.mothersLastName, it.sex, it.income, it.children) },
    restore = {
        Teacher(
            code = it[0] as Int,
            names = it[1] as String,
            fathersLastName = it[2] as String,
            mothersLastName = it[3] as String,
            sex = it[4] as String,
            income = it[5] as Double,
            children = it[6] as Int
        )
    }
)