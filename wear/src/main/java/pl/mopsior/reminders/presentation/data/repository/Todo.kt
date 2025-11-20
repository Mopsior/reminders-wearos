package pl.mopsior.reminders.presentation.data.repository

import kotlinx.coroutines.flow.Flow
import pl.mopsior.reminders.presentation.data.dao.TodoDao
import pl.mopsior.reminders.presentation.data.entities.TodoEntity
import pl.mopsior.reminders.presentation.utils.DateUtils

// Warstwa między ViewModel a Dao
class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: Flow<List<TodoEntity>> = todoDao.getAllTodos()
    val recentTodos: Flow<List<TodoEntity>> = todoDao.getRecentTodos(DateUtils.getStartOfToday())

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

    suspend fun deleteByIds(ids: List<Long>): Int {
        return if (ids.isEmpty()) 0 else todoDao.deleteByIds(ids)
    }

    suspend fun toggleCompleted(todoId: Long) {
        val todo = todoDao.getTodoById(todoId)

        todo?.let {
//          odwrócona kopia
            val updatedTodo = it.copy(isCompleted = !it.isCompleted, completedAt = if (!it.isCompleted) System.currentTimeMillis() else null)

            todoDao.update(updatedTodo)
        }
    }
}