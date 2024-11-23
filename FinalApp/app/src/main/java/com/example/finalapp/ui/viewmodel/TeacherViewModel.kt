package com.example.finalapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalapp.data.local.dao.TeacherDao
import com.example.finalapp.data.local.entity.Teacher
import kotlinx.coroutines.launch

class TeacherViewModel(
    private val dao: TeacherDao
): ViewModel() {

    var teachers by mutableStateOf(listOf<Teacher>())
        private set

    init {
        viewModelScope.launch {
            dao.getAllTeachers().collect {
                teachers = it
            }
        }
    }

    fun add(teacher: Teacher) = viewModelScope.launch {
        dao.insertTeacher(teacher = teacher)
    }

    fun update(teacher: Teacher) = viewModelScope.launch {
        dao.updateTeacher(teacher = teacher)
    }

    fun delete(teacher: Teacher) = viewModelScope.launch {
        dao.deleteTeacher(teacher = teacher)
    }

}