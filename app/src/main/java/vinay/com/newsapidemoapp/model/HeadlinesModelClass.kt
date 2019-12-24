package vinay.com.newsapidemoapp.model


data class HeadlinesModelClass(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)


