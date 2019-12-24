package vinay.com.newsapidemoapp.headlines

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.businessbooks.androidapp.BaseActivity
import vinay.com.newsapidemoapp.R
import vinay.com.newsapidemoapp.model.Article
import vinay.com.newsapidemoapp.model.HeadlinesModelClass
import vinay.com.newsapidemoapp.newsdetails.NewsDetailsActivity

class HeadlinesActivity : BaseActivity(),
    HeadlinesListCustomAdapter.OnHeadlinesListCustomAdapterItemClickListener {

    private lateinit var headlinesViewModel: HeadlinesViewModel
    private var headlinesRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headlines)
        initialize()
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            var intent = Intent(context, HeadlinesActivity::class.java)
            context.startActivity(intent)
        }
    }

    fun initialize() {
        headlinesViewModel = ViewModelProviders.of(this).get(HeadlinesViewModel::class.java)
        headlinesRecyclerView = findViewById(R.id.headlines_recycler_view)

        headlinesViewModel.allArticles.observe(this, Observer {
            it?.let {
                processResponse(it)
            }
        })
        headlinesViewModel.getHeadlines()
    }

    fun processResponse(articles: List<Article>) {
        headlinesRecyclerView?.layoutManager = LinearLayoutManager(this)
        headlinesRecyclerView?.adapter =
            HeadlinesListCustomAdapter(articles, this, this)
    }

    override fun onHeadlinesListCustomAdapterItemClick(title: String) {
        NewsDetailsActivity.start(this, title)
    }
}
