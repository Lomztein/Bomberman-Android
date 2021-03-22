const express = require("express")
const app = express()
const fs = require("fs")

app.use(express.static("public"))
app.use(express.json())

var highscores = []

readHighscoreDatabase();

app.post("/highscores", (req, res) => {
    console.log("Posting highscore");
    let json = req.body;
    let highscore = {
        author: json.author,
        score: json.score
    } // Just to make some sense of certainty that format is correct
    highscores.push(highscore);
	
	updateHighscoreDatabase();
	
	res.send("success");
})

app.get("/highscores", (req,res) => {
    console.log("Getting highscores")
    res.send(highscores);
})

app.listen("8080", "0.0.0.0", () => {
    console.log("Highscore server is up!")
})

function updateHighscoreDatabase() {
	const data = JSON.stringify(highscores);
	
	fs.writeFileSync("./highscore_db.json", data, "utf8");
	console.log("database file has been updated");
}

function readHighscoreDatabase() {
	const data = fs.readFileSync("./highscore_db.json", "utf8");
	
	if(data) {
		highscores = JSON.parse(data);
		console.log("database file has been parsed");
	}
	else {
		console.log("the database file is empty");
	}
}