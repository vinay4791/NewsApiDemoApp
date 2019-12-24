package vinay.com.newsapidemoapp.db

import androidx.lifecycle.LiveData
import com.businessbooks.androidapp.api.ServiceManager
import com.businessbooks.androidapp.api.ServiceManagerProvider
import vinay.com.newsapidemoapp.model.Article

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ArticleRepository(private val daoAccess: DaoAccess) {

    var serviceManager: ServiceManager? = null

    init {
        serviceManager = ServiceManagerProvider.provideSearchRepository()
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allArticles: LiveData<List<Article>> = daoAccess.fetchAllTasks()



    fun fetchArticle(title: String) : LiveData<Article> {
        return daoAccess.fetchArticle(title)
    }

    fun insert(article: Article) {
        daoAccess.insertArticle(article)
    }
}