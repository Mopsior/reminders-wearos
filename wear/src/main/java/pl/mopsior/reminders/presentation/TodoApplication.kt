package pl.mopsior.reminders.presentation

import android.app.Application
import pl.mopsior.reminders.presentation.data.db.TodoDatabase
import pl.mopsior.reminders.presentation.data.repository.TodoRepository

// tworzona przy starcie, żyje przez całą aplikację
class TodoApplication : Application() {

//  lazy - tworzy obiekt przy pierwszym użyciu
    val database by lazy {
        TodoDatabase.getDatabase(this)
    }

    val repository by lazy {
        TodoRepository(database.todoDao())
    }

    override fun onCreate() {
        super.onCreate()
    }
}