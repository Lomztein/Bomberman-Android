package com.andbois.bomberman.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andbois.bomberman.R
import com.andbois.bomberman.highscore.HighscoreViewModel

/**
 * A fragment representing a list of Items.
 */
class HighscoreFragment(val highscoreViewModel: HighscoreViewModel) : Fragment() {

    private var columnCount = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_highscore_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                highscoreViewModel.forceFetch()
                adapter = HighscoreRecyclerAdapter(highscoreViewModel)
            }
        }
        return view
    }
}