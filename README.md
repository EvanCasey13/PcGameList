  <h3 align="center">My PC Game List App</h3>

<!-- ABOUT THE PROJECT -->
## About The Project
App built based on my personal interest in games, that contains a list of thousands of games using the RAWG API and another list of favourite games and the proceeding review for each game on your list.

### Built With

* [Kotlin](https://kotlinlang.org/)
* [Android Studio](https://developer.android.com/studio)
* [RAWG API](https://rawg.io/apidocs)
* [Firebase](https://firebase.google.com/docs/database)

<!-- USAGE EXAMPLES -->
## Usage

This app contains CRUD functionality (Create, read, update & delete) using firebases realtime database for persistence, It uses the RAWG API which contains thousands of games and places them in a paged list. Each image for each card within the games database can be clicked which will return details about the game image clicked on. The card can be clicked to add that particular game to the reviews/favourites list of the App, here the details from the game is taken and added to firebase and then is read to the reviews/favourites list.

<h3 align="center">Homepage</h3>
[APPhomepage](https://user-images.githubusercontent.com/78083043/114903643-e98d1480-9e0e-11eb-8e6b-5bf9decfa848.png)

<h3 align="center">Game Database</h3>
[GameDatabase](https://user-images.githubusercontent.com/78083043/114903720-fc074e00-9e0e-11eb-9abd-f273ffa1ebb9.png)

<h3 align="center">Detailed View of a Game</h3>
[Game Detailed View](https://user-images.githubusercontent.com/78083043/114903832-193c1c80-9e0f-11eb-88ca-72f0dadbe827.png)

<h3 align="center">Adding Review for a Game</h3>
[GameReview](https://user-images.githubusercontent.com/78083043/114903950-3bce3580-9e0f-11eb-86cf-473fcfeaf221.png)






