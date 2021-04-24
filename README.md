  <h3 align="center">My Game List</h3>

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

This app contains CRUD functionality (Create, read, update & delete) using firebases realtime database for persistence, It uses the RAWG API which contains thousands of games and places them in a paged list. Each image for each card within the games database can be clicked which will return details about the game image clicked on. The card can be clicked to add that particular game to the reviews/favourites list of the App, here the details from the game is taken and added to firebase and then is read to the reviews/favourites list. In the Reviews section of the app you can update and delete a review, it also contains functionality that allows the image to be clicked to return details of that game similar to the functionality in the database area of the app.

<!-- APP IMAGES -->
## Images

  App homepage 
## ![](https://user-images.githubusercontent.com/78083043/115963126-4fb41e80-a516-11eb-8930-e663b05c8b7e.png)


Game database 
## ![](https://user-images.githubusercontent.com/78083043/115963268-7d996300-a516-11eb-9942-47ca18a96b44.png)


Detailed game view 
## ![](https://user-images.githubusercontent.com/78083043/115963314-b46f7900-a516-11eb-80f2-89499b638905.png)


Add Review
## ![](https://user-images.githubusercontent.com/78083043/115963382-f39dca00-a516-11eb-8bc9-70f7d58466fd.png)

Review List
## ![](https://user-images.githubusercontent.com/78083043/115963557-b84fcb00-a517-11eb-9677-0a0fc624f989.png)






