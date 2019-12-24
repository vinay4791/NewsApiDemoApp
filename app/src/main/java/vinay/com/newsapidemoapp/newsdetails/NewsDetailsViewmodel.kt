package vinay.com.newsapidemoapp.newsdetails

import android.app.Application
import androidx.lifecycle.LiveData
import com.businessbooks.androidapp.BaseViewModel
import com.businessbooks.androidapp.api.ServiceManagerProvider
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import vinay.com.newsapidemoapp.db.ArticleRepository
import vinay.com.newsapidemoapp.db.ArticleRoomDatabase
import vinay.com.newsapidemoapp.model.Article

class NewsDetailsViewmodel(application: Application) : BaseViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: ArticleRepository
    lateinit var article: LiveData<Article>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val wordsDao = ArticleRoomDatabase.getDatabase(application).daoAccess()
        repository = ArticleRepository(wordsDao)

    }

    fun fetch(title: String) {
        article = repository.fetchArticle(title)
    }
}