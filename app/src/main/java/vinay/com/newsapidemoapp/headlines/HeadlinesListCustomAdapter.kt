package vinay.com.newsapidemoapp.headlines

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import vinay.com.newsapidemoapp.R
import vinay.com.newsapidemoapp.model.Article

class HeadlinesListCustomAdapter(
    val data: List<Article>,
    val mContext: Context,
    val onHeadlinesListCustomAdapterItemClickListener: OnHeadlinesListCustomAdapterItemClickListener
) :
    RecyclerView.Adapter<HeadlinesListCustomAdapter.CustomViewHolder>() {

    interface OnHeadlinesListCustomAdapterItemClickListener {
        fun onHeadlinesListCustomAdapterItemClick(title: String)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeadlinesListCustomAdapter.CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.headlines_item_row, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(
        holder: HeadlinesListCustomAdapter.CustomViewHolder,
        position: Int
    ) {
        holder.bind(data[position], onHeadlinesListCustomAdapterItemClickListener, mContext);
    }

    class CustomViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val sourceTextView: TextView = itemView.findViewById(R.id.source_tv)
        val dateTextView: TextView = itemView.findViewById(R.id.date_tv)
        val headlinesTextView: TextView = itemView.findViewById(R.id.headlines_tv)
        val headlinesImageView: ImageView = itemView.findViewById(R.id.headlines_iv)

        fun bind(
            article: Article,
            onHeadlinesListCustomAdapterItemClickListener: OnHeadlinesListCustomAdapterItemClickListener,
            mContext: Context
        ) {
            sourceTextView.text = article.source.name
            dateTextView.text = article.publishedAt
            headlinesTextView.text = article.title

            val requestOptions = RequestOptions().fitCenter()
            Glide.with(mContext).load(article.urlToImage).apply(requestOptions)
                .into(headlinesImageView)

            itemView.setOnClickListener {
                onHeadlinesListCustomAdapterItemClickListener.onHeadlinesListCustomAdapterItemClick(
                    article.title
                )
            }
        }

    }
}