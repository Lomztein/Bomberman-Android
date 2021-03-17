package com.andbois.bomberman.highscore

import kotlin.collections.ArrayList

// Acts as DAO
class HighscoreRepository {

    var url = "http://lomztein.net:3000/highscores"

    public fun fetchHighscores () : ArrayList<Highscore> {
        //TODO: Make HTTP request and parse JSON response.
        var result: ArrayList<Highscore> = ArrayList<Highscore>();
        result.add(Highscore("John Doe", 666));
        result.add(Highscore("Jane Doe", 667));
        result.add(Highscore("Lomzie", 9001));
        return result;
    }
}