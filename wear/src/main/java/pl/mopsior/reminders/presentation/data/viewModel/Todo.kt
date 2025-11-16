package pl.mopsior.reminders.presentation.data.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pl.mopsior.reminders.presentation.data.entities.TodoEntity
import pl.mopsior.reminders.presentation.data.repository.TodoRepository

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

//  StateFlow automatycznie powiadamia UI o zmianach
    val allTodos: StateFlow<List<TodoEntity>> = repository.allTodos
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList() // początkowa wartość kiedy apka sie ładuje
        )

    fun addTodo(title: String) {
        if (title.trim().isBlank()) return
        Log.i("TodoViewModel", "Adding todo with title: $title")

        viewModelScope.launch {
            val newTodo = TodoEntity(
                title = title.trim(),
                isCompleted = false,
                createdAt = System.currentTimeMillis()
            )

//          wstawia do db
            repository.insert(newTodo)
        }
    }

    fun toggleTodoCompleted(todo: TodoEntity) {
        viewModelScope.launch {
            val updatedTodo = todo.copy(isCompleted = !todo.isCompleted)
            repository.update(updatedTodo)
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }
}