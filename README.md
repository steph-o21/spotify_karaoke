# Spotify Karaoke 

## Curent Working Demo:
https://youtu.be/CEyYxzh0lBI

## Description
Spotify Karaoke is an android application that utilizes the Spotify API to allow users to link their Spotify accounts and sing along to the lyrics to any song they're listening to as they appear on the the screen. 

## Getting Started
- Spotify users should have their spotify account downloaded from the play store on the device they plan to use to download the app.
- Users will be able to sign in or sign up for the app and authorize the app to access their spotify
- Once authorized, their spotify will be linked to the app and they will be able to see the lyrics to the currently played songs
- Users will be able to pause, play, skip forward to the next song, or skip back to the beginning of the currently played song
- Resources used in the development of this project can be found at the bottom of the page 

#### Register / Sign In
- Authentication will be set up using firebase. 
- Users will be able to sign in or register for the app using their email and a created password
- Once signed in to the app, users will be able to sign in to their spotify and connect it to the application 
- This will not store or use user data other than to deliver the data needed for the app to deliver its functionality

#### Graphic Lyric Display
- This screen will be the karaoke screen that plays music and display lyrics for the current song being played by the user 
- The user will be able to play, pause, and skip to anywhere in the song being played 
- When the user ends the song or hits the back button, they will be taken back to the song selection screen where they can choose another song or log out of the application

#### Song Selection
- Search results will be displayed in a scrolling list below the search box and will show all available songs that contain the search key-words
- Once the user selects a song, they will be transitioned to the graphic lyric display screen

### Current App Screens
- Sign In Screen
- Graphic Lyric Display Screen
- Song Selection Screen

### Figma Prototype 
#### [UI/UX Implementation](https://www.figma.com/file/HyXK3TU3Dv6faWwYbkN3v8/Spotify-Karaoke-App)
- The prototype shows a mockup of the different screens the project aims to display
- Click play on the top right to navigate through

## Error Handling
Since the project is still in development mode, users will have to have access to be able to fully use the app and its functions

## Dependencies
Inside the 'app/build.gradle' file, make sure the following respositories and dependencies are present:

    repositories {
    mavenCentral()
    maven { url "https://jitpack.io" }
    }
    
    dependencies {

    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation fileTree(include: ['*.jar'], dir: 'app')
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    
    implementation platform('com.google.firebase:firebase-bom:28.3.0')
    implementation 'com.google.firebase:firebase-analytics'
    implementation 'com.google.firebase:firebase-auth'
    implementation 'com.google.firebase:firebase-firestore'
    implementation 'com.google.firebase:firebase-storage'
    implementation 'com.google.firebase:firebase-database'
    implementation 'com.android.support:multidex:1.0.3'

    implementation 'com.firebaseui:firebase-ui-auth:7.2.0'
    implementation 'com.firebaseui:firebase-ui-database:7.2.0'

    // spotify api dependencies
    implementation project(':spotify-app-remote-release-0.7.2')
    implementation 'com.spotify.android:auth:1.1.0'
    implementation 'com.github.kaaes:spotify-web-api-android:0.4.1'
    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.vectordrawable:vectordrawable:1.1.0'
    implementation 'androidx.navigation:navigation-fragment:2.3.5'
    implementation 'androidx.navigation:navigation-ui:2.3.5'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    }

## Resources 
Wanna create a similar app? Check out some of the resources that helped guide the development of this project:
#### [Android SDK QuickStart](https://developer.spotify.com/documentation/android/quick-start/)
#### [Spotify Web API Tutorial](https://developer.spotify.com/documentation/web-api/quick-start/)
#### [Spotify Web API for Android](https://github.com/kaaes/spotify-web-api-android)
#### [Spotify Web API Android Client - API Reference](https://kaaes.github.io/spotify-web-api-android/javadoc/index.html?kaaes/spotify/webapi/android/SpotifyService.html)
#### [Spotify API](https://developer.spotify.com/documentation/web-api/)
#### [LyricsGenius API](https://lyricsgenius.readthedocs.io/en/master/)
#### [Firebase.Firestore.Timestamp](https://firebase.google.com/docs/reference/unity/struct/firebase/firestore/timestamp)

