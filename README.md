# MyBakingApp - Udacity Advanced Android project

# Project Overview
You will productionize an app, taking it from a functional state to a production-ready state. This will involve finding and handling error cases, adding accessibility features, allowing for localization, adding a widget, and adding a library.

# Why this Project?
As a working Android developer, you often have to create and implement apps where you are responsible for designing and planning the steps you need to take to create a production-ready app. Unlike Popular Movies where we gave you an implementation guide, it will be up to you to figure things out for the Baking App.

# What Will I Learn?
In this project you will:

- Use MediaPlayer/Exoplayer to display videos.

- Handle error cases in Android.

- Add a widget to your app experience.

- Leverage a third-party library in your app.

- Use Fragments to create a responsive design that works on phones and tablets.

# App Description
Your task is to create a Android Baking App that will allow Udacity’s resident baker-in-chief, Miriam, to share her recipes with the world. You will create an app that will allow a user to select a recipe and see video-guided steps for how to complete it.

# The recipe listing is located here.
https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json

The JSON file contains the recipes' instructions, ingredients, videos and images you will need to complete this project. Don’t assume that all steps of the recipe have a video. Some may have a video, an image, or no visual media at all.

One of the skills you will demonstrate in this project is how to handle unexpected input in your data -- professional developers often cannot expect polished JSON data when building an app.

# Layout 
https://d17h27t6h515a5.cloudfront.net/topher/2017/March/58dee986_bakingapp-mocks/bakingapp-mocks.pdf

# workflow + features implementation
- Activities available (in order of operation): widget -> main -> steps -> ingredients / step detail.
- Return Home: click on Toolbar up arrow '<-' MyBakingApp.

## Phone

<img src="https://user-images.githubusercontent.com/1282659/38772161-11906560-3ff6-11e8-9c52-f184b9571909.png" width="200"><img src="https://user-images.githubusercontent.com/1282659/39097449-0f2d4b6c-4622-11e8-8f2b-41302ddcbfed.png" width="200"><img src="https://user-images.githubusercontent.com/1282659/39097531-1fa42c44-4623-11e8-827e-cf684459d4ab.png" width="200"><img src="https://user-images.githubusercontent.com/1282659/39097455-16edbabc-4622-11e8-9381-bed2d0664af0.png" width="200"><img src="https://user-images.githubusercontent.com/1282659/39097454-1500f6e2-4622-11e8-8ac1-6ca43d91b5af.png" width="200">

#### Widget update
- each recipe selection invokes WidgetProvider.onUpdate()

<img src="https://user-images.githubusercontent.com/1282659/39097639-bdbb1dba-4624-11e8-9b49-b2d44f048f74.png" width="200"><img src="https://user-images.githubusercontent.com/1282659/39097527-1acdcfc2-4623-11e8-8280-09fe8001d5ea.png" width="200">

#### Rotation: SharedPreference persistence 
<img src="https://user-images.githubusercontent.com/1282659/39097449-0f2d4b6c-4622-11e8-8f2b-41302ddcbfed.png" width="200"><img src="https://user-images.githubusercontent.com/1282659/39097529-1c5a7c6e-4623-11e8-8a40-0087555ae128.png" height="200">

- selected step with highlight

<img src="https://user-images.githubusercontent.com/1282659/39097531-1fa42c44-4623-11e8-827e-cf684459d4ab.png" width="200"><img src="https://user-images.githubusercontent.com/1282659/39097530-1dfafbca-4623-11e8-96f7-55fd67f61ede.png" height="200">

- video position 
- auto-play/pause setting

<img src="https://user-images.githubusercontent.com/1282659/39097454-1500f6e2-4622-11e8-8ac1-6ca43d91b5af.png" width="200"><img src="https://user-images.githubusercontent.com/1282659/39097456-18ce3dd4-4622-11e8-99c6-412421ab8bea.png" height="200">

- scroll position 
- selected ingredient with highlight

<img src="https://user-images.githubusercontent.com/1282659/39097455-16edbabc-4622-11e8-9381-bed2d0664af0.png" width="200"><img src="https://user-images.githubusercontent.com/1282659/39097458-1ab0e138-4622-11e8-96bf-57438fab776b.png" height="200">

## Tablet
<img src="https://user-images.githubusercontent.com/1282659/38772205-d6d48f04-3ff6-11e8-8f46-1ab9daee555d.png" width="300"><img src="https://user-images.githubusercontent.com/1282659/39098226-1f92e84e-462d-11e8-9323-f15b3e8e1776.png" width="300"><img src="https://user-images.githubusercontent.com/1282659/38772168-243a3a9c-3ff6-11e8-8b31-8b6a37a01e04.png" width="300"><img src="https://user-images.githubusercontent.com/1282659/38772167-2429ecb4-3ff6-11e8-83ff-1db322fb2781.png" width="300">

#### Widget update
- each recipe selection invokes WidgetProvider.onUpdate()

<img src="https://user-images.githubusercontent.com/1282659/39098492-5653a978-4631-11e8-84f1-8edfb6a05200.png" width="300"><img src="https://user-images.githubusercontent.com/1282659/39098493-5669e3d2-4631-11e8-99d4-bf9ccb4c5ef3.png" width="300">

#### Rotation: SharedPreference persistence

<img src="https://user-images.githubusercontent.com/1282659/39098226-1f92e84e-462d-11e8-9323-f15b3e8e1776.png" width="300"><img src="https://user-images.githubusercontent.com/1282659/39098227-21600116-462d-11e8-8b4b-394644b97d45.png" height="300">

- video position 
- auto-play/pause setting

<img src="https://user-images.githubusercontent.com/1282659/39098496-591cca22-4631-11e8-8ddc-0a55d5cbdfa5.png" width="300"><img src="https://user-images.githubusercontent.com/1282659/39098497-592b61c2-4631-11e8-8b22-81489c61b9a4.png" height="300">

- scroll position 
- selected ingredient with highlight

<img src="https://user-images.githubusercontent.com/1282659/39098494-58df8bee-4631-11e8-8d4e-dfbd4bf0d5fb.png" width="300"><img src="https://user-images.githubusercontent.com/1282659/39098495-590d37e2-4631-11e8-8d19-ef8569ef9ecd.png" height="300">

# References

Widget base on Mark Murphy's example

https://github.com/commonsguy/cw-advandroid/tree/master/AppWidget/LoremWidget
 
Video player base on ExoPlayer 

https://github.com/google/ExoPlayer 

https://github.com/yusufcakmak/ExoPlayerSample

ButterKnife by Jake Wharton

http://jakewharton.github.io/butterknife/
