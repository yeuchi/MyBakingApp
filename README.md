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

# Implementation screenshots: 
widget -> main -> steps -> ingredients / step detail

## Phone

![widget](https://user-images.githubusercontent.com/1282659/38772161-11906560-3ff6-11e8-9c52-f184b9571909.png)
![mainactivity](https://user-images.githubusercontent.com/1282659/38772165-1c1f1044-3ff6-11e8-9df2-e7f71a674782.png)
![steps](https://user-images.githubusercontent.com/1282659/38772164-1c10a72a-3ff6-11e8-9e2a-89f3acf266f0.png)
![ingredients](https://user-images.githubusercontent.com/1282659/38772163-1bfe6786-3ff6-11e8-9e8e-8bee0a8a4a85.png)
![detail_step](https://user-images.githubusercontent.com/1282659/38772162-1bef594e-3ff6-11e8-9bf8-e4831d66d4a9.png)

## Tablet
![widget600](https://user-images.githubusercontent.com/1282659/38772205-d6d48f04-3ff6-11e8-8f46-1ab9daee555d.png)
![mainactivity600](https://user-images.githubusercontent.com/1282659/38772169-2449b4ea-3ff6-11e8-932c-d59ec9251e3c.png)
![ingredients600](https://user-images.githubusercontent.com/1282659/38772168-243a3a9c-3ff6-11e8-8b31-8b6a37a01e04.png)
![steps600](https://user-images.githubusercontent.com/1282659/38772167-2429ecb4-3ff6-11e8-83ff-1db322fb2781.png)

# References

Widget base on Mark Murphy's example

https://github.com/commonsguy/cw-advandroid/tree/master/AppWidget/LoremWidget
 
Video player base on ExoPlayer 

https://github.com/google/ExoPlayer 
https://github.com/yusufcakmak/ExoPlayerSample
