package com.andbois.bomberman

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.andbois.bomberman.fragments.CreditsFragment
import com.andbois.bomberman.fragments.HighscoreFragment
import com.andbois.bomberman.fragments.MainMenuFragment
import com.andbois.bomberman.highscore.HighscoreViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mainMenu : MainMenuFragment;
    lateinit var highscores : HighscoreFragment
    lateinit var credits : CreditsFragment

    var highscoresModel = viewModels<HighscoreViewModel>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainMenu = MainMenuFragment();
        highscores = HighscoreFragment(highscoresModel.value);
        credits = CreditsFragment();

        replaceFragment(mainMenu);
    }

    public fun openMainMenu (view: View) = replaceFragment(mainMenu);
    public fun openHighscores (view: View) = replaceFragment(highscores);
    public fun openCredits (view: View) = replaceFragment(credits);


    fun replaceFragment (fragment : Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragment)
            addToBackStack(null)
            commit();
        }
    }
}