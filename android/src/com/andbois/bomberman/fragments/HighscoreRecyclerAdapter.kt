package com.andbois.bomberman.fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.andbois.bomberman.highscore.HighscoreViewModel
import com.andbois.bomberman.R

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class HighscoreRecyclerAdapter(

        private val values: HighscoreViewModel)
    : RecyclerView.Adapter<HighscoreRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_highscore, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values.get(position);
        holder.author.text = item.author
        holder.score.text = item.score.toString()
    }

    override fun getItemCount(): Int {
        return values.count();
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author: TextView = view.findViewById(R.id.item_number)
        val score: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + author.text + "': " + score.text;
        }
    }
}