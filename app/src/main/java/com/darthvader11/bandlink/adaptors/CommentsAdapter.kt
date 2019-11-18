package com.darthvader11.bandlink.adaptors


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.models.Comment
import com.darthvader11.bandlink.showToast
import kotlinx.android.synthetic.main.comment_item.view.*


class CommentsAdapter(val context: Context, private val comments: List<Comment>) :
    RecyclerView.Adapter<CommentsAdapter.MyViewHolder>() {

    companion object{
        val TAG: String = CommentsAdapter::class.java.simpleName
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val comment = comments[position]
        holder.setData(comment, position)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false)
        return MyViewHolder(view)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var currentComment: Comment? = null
        var currentPosition: Int = 0

        init {
            itemView.setOnClickListener {
                currentComment?.let {

                    context.showToast(currentComment!!.title + " clicked!")
                }

            }

            itemView.imgShare.setOnClickListener {

                currentComment?.let {

                    val intent = Intent()
                    val message: String = "My hobby is " + currentComment!!.title
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT, message)
                    intent.type = "text/plain"
                    context.startActivity(Intent.createChooser(intent, "Share to: "))
                }

            }
        }

        fun setData(comment: Comment?, pos: Int) {
            comment?.let {
                itemView.txtTitle.text = comment.title
            }
            this.currentComment = comment
            this.currentPosition = pos
        }
    }
}