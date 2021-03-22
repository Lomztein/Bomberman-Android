package com.andbois.bomberman.highscore

import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.collections.ArrayList

// Acts as DAO
class HighscoreRepository {

    var url = "http://192.168.0.11:8080/highscores"
    var client: OkHttpClient = OkHttpClient()

    val highscoreCache: ArrayList<Highscore> = ArrayList<Highscore>()

    public fun fetchHighscores () : ArrayList<Highscore> {
        runBlocking {
            val deferred = GlobalScope.async { sendHighscoreRequest() }
            deferred.await()
        }

        insertHighscore("author" + highscoreCache.count().toString(), highscoreCache.count())

        return highscoreCache
    }

    public fun insertHighscore(author: String, score: Int) {
        runBlocking {
            val deferred = GlobalScope.async { submitHighscoreRequest(author, score) }
            deferred.await()
        }
    }

    private suspend fun submitHighscoreRequest(author: String, score: Int) {
        var test = "{ \"author\": \"$author\", \"score\": $score }"
        var requestBody = test.toRequestBody("application/json; charset=utf-8".toMediaType())

        var request = Request.Builder().method("POST", requestBody).url(url).build()
        val response = client.newCall(request).execute()

        if(response.isSuccessful) {
            val data = response.body?.string()
        }
        else {
            println("highscore POST failure " + response.message)
        }
    }

    private suspend fun sendHighscoreRequest() {
        var request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()

        if(response.isSuccessful) {
            val data = response.body?.string()

            highscoreCache.clear()
            highscoreCache.addAll(Gson().fromJson(data, Array<Highscore>::class.java).toList())
        }
        else {
            println("highscore GET failure " + response.message)
        }
    }
}