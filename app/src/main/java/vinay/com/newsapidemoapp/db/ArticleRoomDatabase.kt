package vinay.com.newsapidemoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vinay.com.newsapidemoapp.model.Article

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Article::class), version = 1, exportSchema = false)
@TypeConverters(SourceConverter::class)
public abstract class ArticleRoomDatabase : RoomDatabase() {

    abstract fun daoAccess(): DaoAccess

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ArticleRoomDatabase? = null

        fun getDatabase(context: Context): ArticleRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleRoomDatabase::class.java,
                    "article_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}