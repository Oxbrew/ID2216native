package com.darthvader11.bandlink.adaptors


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.models.Suggestion
import com.darthvader11.bandlink.showToast
import kotlinx.android.synthetic.main.suggestion_item.view.*


class SuggestionsAdapter(val context: Context, private val suggestions: List<Suggestion>) :
    RecyclerView.Adapter<SuggestionsAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val suggestion = suggestions[position]
        holder.setData(suggestion, position)
    }

    override fun getItemCount(): Int {
        return suggestions.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.suggestion_item, parent, false)
        return MyViewHolder(view)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var currentSuggestion: Suggestion? = null
        var currentPosition: Int = 0

        init {
            itemView.setOnClickListener {
                currentSuggestion?.let {

                    context.showToast(currentSuggestion!!.title + " clicked!")
                }

            }

        }

        fun setData(suggestion: Suggestion?, pos: Int) {
            suggestion?.let {
                itemView.txtProfile.text = suggestion.title
                itemView.txtSuggestion.text = suggestion.suggestion
                itemView.profilePic.setImageResource(suggestion.drb)
            }
            this.currentSuggestion = suggestion
            this.currentPosition = pos
        }
    }
}