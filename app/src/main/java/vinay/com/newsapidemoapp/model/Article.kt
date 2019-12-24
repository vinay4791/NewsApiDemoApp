package vinay.com.newsapidemoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class Article(
    val source: Source,
    val author: String,
    @PrimaryKey @ColumnInfo  val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)