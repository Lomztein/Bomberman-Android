package com.andbois.bomberman

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun launchGame (view: View) {
        var launch = Intent(this, LaunchGame::class.java)
        startActivity(launch);
    }
}