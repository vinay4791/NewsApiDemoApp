package vinay.com.newsapidemoapp.db

import vinay.com.newsapidemoapp.model.Article
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DaoAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)

    @Query("SELECT * FROM article_table")
    fun fetchAllTasks(): LiveData<List<Article>>

    @Query("SELECT * FROM article_table WHERE title =:title")
    fun fetchArticle(title : String): LiveData<Article>

    @Update
    fun updateArticle(article: Article)

    @Delete
    fun deleteArticle(article: Article)
}