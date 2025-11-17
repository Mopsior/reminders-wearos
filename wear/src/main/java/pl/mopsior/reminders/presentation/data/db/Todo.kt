package pl.mopsior.reminders.presentation.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import pl.mopsior.reminders.presentation.data.dao.TodoDao
import pl.mopsior.reminders.presentation.data.entities.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 2,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
//  Room automatycznie wygeneruje
    abstract fun todoDao(): TodoDao

//  zapewnia jedną instancję db w całej aplikacji
    companion object {

//      zapewnia widoczność zmiennej we wszystkich wątkach,
//      gwarantuje że zawsze będzie aktualna wartość
        @Volatile
        private var INSTANCE: TodoDatabase? = null

//      zwraca instancję db, context - kontekst aplikacji  (potrzebny dla db)
        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
//              tylko jeden wątek może wykonać ten kod na raz,
//              aby nie było paru instancji

                val instance = databaseBuilder(
                    context = context.applicationContext,
                    klass = TodoDatabase::class.java,
                    name = "todo_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()

                INSTANCE = instance
                instance
            }
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                super.onCreate(db)
//              TODO: dodać początkowe dane
            }
        }
    }
}