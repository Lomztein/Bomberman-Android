const express = require("express")
const app = express()

app.use(express.static("public"))
app.use(express.json())

var highscores = []

app.post("/highscores", (req, res) => {
    console.log("Posting highscore");
    let json = req.body;
    let highscore = {
        author: json.author,
        score: json.score
    } // Just to make some sense of certainty that format is correct
    highscores.push(highscore);
})

app.get("/highscores", (req,res) => {
    console.log("Getting highscores")
    res.send(highscores);
})

app.listen("8080", "0.0.0.0", () => {
    console.log("Highscore server is up!")
})
