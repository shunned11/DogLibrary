# DogLibrary
A simple library to fetch random dog images of various breeds from the internet

## Integrating into your project

This library is available in [JitPack.io](https://jitpack.io/private#shunned11/DogLibrary) repository.

To use it make sure that
Maven is mentioned in the `settings.gradle` file

```groovy
repositories {
  mavenCentral()
  google()
  maven { url "https://jitpack.io" }
}
```
Repository's url is added to the `build.gradle` file in your app:
```groovy
dependencies {
  implementation 'com.github.shunned11:DogLibrary:1.1'

    // Other dependencies your app might use
}
```

## Building
This project is built using [Gradle](https://gradle.org/):

1. Clone the repository: `git clone https://github.com/shunned11/DogLibrary.git`
2. Build: `./gradlew assemble`
3. Grab the `aar` file

## Usage

This library uses Retrofit, Suspend Functions and Glide to retrieve the images.

The DogImages object has one public read-only property ```currentIndex``` which can be used to know the index of the currentImage

The DogImages object has four helper methods to fetch dog images. Note that all helper methods except getPreviousImage are suspend functions and must be called from inside a coroutinescope or a suspend function

This function returns the url of one image as a list of strings. Size of the list is one
```kotlin
suspend fun getImage():List<String>
```


This function returns the url of `number` of images as a list of strings. Size of the list is `number`
```kotlin
suspend fun getImages(number: Int):List<String>
```


This function either returns the url of one new image if we are at the end of the list or the url of the next image based on the currentIndex as a list of string. Size of the list is one
```kotlin
suspend fun getNextImage():List<String>
```


This function returns the url of previous image unless we are at the previous image as a list of string. Size of the list is one
```kotlin
fun getPreviousImage(): List<String> 
```

The helper methods can be accessed from a coroutine scope or a suspend function by using

```kotlin
DogImages.getNextImage()
DogImages.getImages(number:Int)
DogImages.getImage()
```

## Sample Project
Please find a sample project to use this library [here](https://github.com/shunned11/SampleDogImagesApp)

