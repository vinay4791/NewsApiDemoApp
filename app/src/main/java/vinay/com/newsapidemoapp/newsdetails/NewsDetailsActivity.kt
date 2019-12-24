package vinay.com.newsapidemoapp.newsdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_news_details.*
import vinay.com.newsapidemoapp.R
import vinay.com.newsapidemoapp.model.Article

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var newsDetailsViewmodel: NewsDetailsViewmodel

    lateinit var newsDetailsTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        newsDetailsTitle = intent.getStringExtra("newsDetailsTitle")
        initialize()
    }

    fun initialize() {
        newsDetailsViewmodel = ViewModelProviders.of(this).get(NewsDetailsViewmodel::class.java)
        newsDetailsViewmodel.fetch(newsDetailsTitle)
        newsDetailsViewmodel.article.observe(this, Observer {
            it?.let {
                processData(it)
            }
        })
    }

    fun processData(article: Article) {
        description_tv.setText(article.description)
        source_tv.setText(article.source.name)
        date_tv.setText(article.publishedAt)
        new_details_headline_tv.setText(article.title)

        val requestOptions = RequestOptions().fitCenter()
        Glide.with(this).load(article.urlToImage).apply(requestOptions)
            .into(news_iv)
    }

    companion object {
        @JvmStatic
        fun start(context: Context, newsDetailsTitle: String) {
            var intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra("newsDetailsTitle", newsDetailsTitle)
            context.startActivity(intent)
        }
    }
}
