package pl.mopsior.reminders.presentation.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pl.mopsior.reminders.presentation.data.entities.TodoEntity

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY createdAt ASC")
    fun getAllTodos(): Flow<List<TodoEntity>>
//    przez Flow dane będą aktualizowane po zmianie w DB

    @Query("""
        SELECT * FROM todos
        WHERE isCompleted = 0 OR (isCompleted = 1 AND completedAt >= :startOfToday)
        ORDER BY 
          CASE WHEN isCompleted = 1 THEN 1 ELSE 0 END ASC,
          CASE WHEN isCompleted = 1 THEN completedAt ELSE NULL END DESC,
          createdAt DESC
    """)
    fun getRecentTodos(startOfToday: Long): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todos WHERE id = :id LIMIT 1")
    suspend fun getTodoById(id: Long): TodoEntity?
//    ? w TodoEntity to może zwrócić null gdy nie ma takiego zadań
//    suspend pozwala na zatrzymanie funckji do czasu aż DB zwróci wynik. Chyba taki await

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TodoEntity): Long
//    @Insert automatycznie tworzy zapytanie insert do DB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todos: TodoEntity)
//   przez vararg można przekazać wiele zadań na raz (dobre do synchro z z tele)

    @Update
    suspend fun update(todo: TodoEntity)

    @Delete
    suspend fun delete(todo: TodoEntity)

    // Usuń wiele zadań po liście id
    @Query("DELETE FROM todos WHERE id IN (:ids)")
    suspend fun deleteByIds(ids: List<Long>): Int

}