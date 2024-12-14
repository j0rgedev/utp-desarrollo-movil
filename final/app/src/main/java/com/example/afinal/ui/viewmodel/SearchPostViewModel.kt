package com.example.afinal.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.data.repository.PostRepository
import com.example.afinal.data.remote.model.Post
import kotlinx.coroutines.launch

class SearchPostViewModel : ViewModel() {
    private val repository = PostRepository()

    sealed class PostUiState {
        object Initial : PostUiState()
        object Loading : PostUiState()
        data class Success(val post: Post) : PostUiState()
        data class Error(val message: String) : PostUiState()
        object NotFound : PostUiState()
    }

    var uiState by mutableStateOf<PostUiState>(PostUiState.Initial)
        private set

    fun fetchPostById(id: Int) {
        if (id <= 0) {
            uiState = PostUiState.Error("ID de post inválido")
            return
        }

        viewModelScope.launch {
            uiState = PostUiState.Loading

            try {
                val response = repository.getPostById(id)

                uiState = when {
                    response == null -> PostUiState.NotFound
                    response is Post -> PostUiState.Success(response)
                    else -> PostUiState.Error("Error desconocido al obtener el post")
                }
            } catch (e: Exception) {
                uiState = if (e.message?.contains("404") == true) {
                    PostUiState.NotFound
                } else {
                    PostUiState.Error(
                        e.localizedMessage ?: "Error al buscar el post"
                    )
                }
            }
        }
    }
}
