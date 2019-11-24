package com.darthvader11.bandlink.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.models.Feed
import com.darthvader11.bandlink.showToast
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedAdapter (val context: Context, private val feedContent: List<Feed>):
        RecyclerView.Adapter<FeedAdapter.MyViewHolder>(){
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val feed = feedContent[position]
        holder.setData(feed, position)
    }

    override fun getItemCount(): Int {
        return feedContent.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_feed,parent, false)
        return MyViewHolder(view)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var currentPost: Feed? = null
        var currentPosition: Int = 0

        init {
            itemView.setOnClickListener {
                currentPost?.let {
                    context.showToast(currentPost!!.postInfo + " clicked!")
                }
            }
        }

        fun setData(feed: Feed?, pos: Int){
            feed?.let {
                itemView.feedTitle.text = feed.postTitle
                itemView.feedInfo.text = feed.postInfo
                itemView.feedPic.setImageResource(feed.postPic)
            }
            this.currentPost = feed
            this.currentPosition = pos
        }
    }
}