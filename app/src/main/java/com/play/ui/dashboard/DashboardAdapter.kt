package com.play.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.play.R
import com.play.data.model.Story
import kotlinx.android.synthetic.main.item_view.view.*

class DashboardAdapter(
    private val mStories: ArrayList<Story>,
    private val itemClickListener: StoryClickListener
) : RecyclerView.Adapter<DashboardAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: Story) {
            itemView.title.text = user.title
            itemView.type.text = user.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view, parent,
                false
            )
        )

    override fun getItemCount(): Int = mStories.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val story = mStories[position];
        holder.bind(story)
        holder.itemView.setOnClickListener(View.OnClickListener {
            itemClickListener.onCellClickListener(story);
        })
    }

    fun addItems(stories: List<Story>) {
        if (mStories.size > 0) {
            mStories.addAll(mStories.size - 1, stories)
        } else {
            mStories.addAll(0, stories)
        }
        notifyDataSetChanged()
    }

    interface StoryClickListener {
        fun onCellClickListener(story: Story)
    }
}