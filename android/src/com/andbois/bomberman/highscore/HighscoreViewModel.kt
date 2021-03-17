package com.andbois.bomberman.highscore

import androidx.lifecycle.ViewModel

class HighscoreViewModel : ViewModel() {

    var repo : HighscoreRepository = HighscoreRepository()
    var cache : ArrayList<Highscore>? = null;

    public fun forceFetch () {
        cache = repo.fetchHighscores();
    }

    fun get (position : Int) : Highscore {
        if (cache == null) {
            forceFetch();
        }
        return cache?.get(position)!!;
    }

    fun count () : Int {
        if (cache == null) {
            forceFetch();
        }
        return cache?.size!!;
    }

}