 
# Lydia - Technical Test

## Subject:

Build an app that fetch data from this service : https://api.randomuser.me (use https://randomuser.me/api/1.0/?seed=lydia&results=10&page=1 to get 10 contacts for each api call, and increase page param to load more results).

The app must display result in a list of first names and last names, and the email under it.

The app must handle connectivity issue, and display the last results received if it can't retrieve one at launch.

Touching an item of the list should make appear a details page listing every attributes.

The app must be in Kotlin, any third-party libraries are allowed but you'll have to justify why you use them.

Evaluate the time it should take before starting, and give it with your work, with the time it really took.

## Suggested Solution:

#### Libraries that will be used:

- Retrofit & Gson to fetch and return remote data from an API

- LeakCanary to detect any leak

- ktx for extensions lambdas coroutines etc

- ViewModels, livedata, ViewModelProvider and all lifecycle components needed to propose a proper MVVM architecture

- Navigation Component to handle nav between Activity

- Glide to Load image on the Details Activity

- Dagger to inject dependencies between coomponent

- AppCompat, Fragment, Snackbar,

- Room & Paging to handle structured cache of users, for the offline mode. We prefer Room over SharedPreferences because we will store lareg (supposedly) amount of structured datas and not little key/value set of data (such as cookies token etc)

This app will have a SplashScreen, a ListActivity and a DetailActivity

Here is how we plan to do it:

1. Layout our directories using gitkeep, install Dagger, Retrofit/Gson, UI libraries, ktx & lifecycle libraries and create RemoteUserDataSource using Retrofit. ETA: 4h

2. Install Glide and Navigation; Create our Activities (and associated ViewModels) and design them. connect data Repository and ViewModels. ETA; 3h

3. Room and Cache; install Room, create a internet conenctivity change listener and add switch logic between Local/Remote in corresponding Repository. ETA: 3h

4. Improve UI: 2h

5. ensure everything is unit tested, test using TestCanary; add Scenarios & instrumented tests using Espresso.: 4h

6. Prepare and test for fake prod release: 2h


## Results


1. Took ~4h
2. Took 4h: Dagger took me more time than I expected
3. Took 6h: Again Dagger, I lost time trying to pass an application instance to my DatabaseModule.
4. Took ~2h
5. did not had enough time left
6. did not had enough time left
