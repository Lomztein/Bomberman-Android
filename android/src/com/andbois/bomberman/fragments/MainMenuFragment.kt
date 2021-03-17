package com.andbois.bomberman.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.andbois.bomberman.LaunchGame
import com.andbois.bomberman.R
import kotlin.system.exitProcess

class MainMenuFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onStart() {
        super.onStart()
        view?.findViewById<Button>(R.id.btn_startGame)?.setOnClickListener { launchGame() }
        view?.findViewById<Button>(R.id.btn_exitGame)?.setOnClickListener { exitGame() }
    }

    public fun launchGame () {
        var launch = Intent(activity, LaunchGame::class.java)
        startActivity(launch);
    }

    public fun exitGame () {
        activity?.finishAffinity();
        exitProcess(0);
    }
}