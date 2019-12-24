package vinay.com.newsapidemoapp.headlines

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.businessbooks.androidapp.BaseViewModel
import com.businessbooks.androidapp.api.ServiceManager
import com.businessbooks.androidapp.api.ServiceManagerProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vinay.com.newsapidemoapp.db.ArticleRepository
import vinay.com.newsapidemoapp.db.ArticleRoomDatabase
import vinay.com.newsapidemoapp.model.Article
import vinay.com.newsapidemoapp.model.HeadlinesModelClass

class HeadlinesViewModel(application: Application) : BaseViewModel(application) {

    var serviceManager: ServiceManager? = null
    // The ViewModel maintains a reference to the repository to get data.
    private val repository: ArticleRepository
    // LiveData gives us updated words when they change.
    val allArticles: LiveData<List<Article>>

    init {
        serviceManager = ServiceManagerProvider.provideSearchRepository()
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val wordsDao = ArticleRoomDatabase.getDatabase(application).daoAccess()
        repository = ArticleRepository(wordsDao)
        allArticles = repository.allArticles
    }

    fun insert(article: Article) {
        Observable.just(repository)
            .subscribeOn(Schedulers.io())
            .subscribe { db -> repository.insert(article) }
    }

    fun getHeadlines() {
        serviceManager!!.getHeadlinesList()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({ result ->
                for (article in result.body()!!.articles) {
                    insert(article)
                }
            }, { error ->

            })
    }
}