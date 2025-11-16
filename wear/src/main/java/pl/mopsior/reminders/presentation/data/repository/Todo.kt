package pl.mopsior.reminders.presentation.data.repository

import kotlinx.coroutines.flow.Flow
import pl.mopsior.reminders.presentation.data.dao.TodoDao
import pl.mopsior.reminders.presentation.data.entities.TodoEntity

// Warstwa między ViewModel a Dao
class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: Flow<List<TodoEntity>> = todoDao.getAllTodos()

    suspend fun getTodoById(id: Long): TodoEntity? {
        return todoDao.getTodoById(id)
    }

    suspend fun insert(todo: TodoEntity): Long {
        return todoDao.insert(todo)
    }

    suspend fun update(todo: TodoEntity) {
        todoDao.update(todo)
    }

    suspend fun delete(todo: TodoEntity) {
        todoDao.delete(todo)
    }

    suspend fun toggleCompleted(todoId: Long) {
        val todo = todoDao.getTodoById(todoId)

        todo?.let {
//          odwrócona kopia
            val updatedTodo = it.copy(isCompleted = !it.isCompleted)

            todoDao.update(updatedTodo)
        }
    }
}