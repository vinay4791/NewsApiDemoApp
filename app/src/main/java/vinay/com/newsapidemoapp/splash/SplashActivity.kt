package vinay.com.newsapidemoapp.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import vinay.com.newsapidemoapp.R
import vinay.com.newsapidemoapp.headlines.HeadlinesActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            HeadlinesActivity.start(this)
            finish()
        }, 1000)
    }
}

