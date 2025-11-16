package pl.mopsior.reminders.presentation.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.mopsior.reminders.presentation.data.repository.TodoRepository

class TodoViewModelFactory(
    private val repository: TodoRepository
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // sprawdź czy modelClass to TodoViewModel
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            // @Suppress - wyłącz ostrzeżenie o unchecked cast
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}