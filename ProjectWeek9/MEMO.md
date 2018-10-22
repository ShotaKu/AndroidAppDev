## Lecture note week 9
### Choose API for excercise
We will make an application that read JSON file and format it to be custom object class. You can choose an API from [toddmotto/public-apis repository](https://github.com/toddmotto/public-apis). However you should choose a API which following conditions.
  1. Auth is not needed API
  2. Support HTTPS request.
  3. Has both **Object** and **array** inside in the JSON when request

 - [Json Tips](https://githab.com/ShotaKu/AndroidAppDev/Week)
### Create object for recive data
In my case, I choose Studio Ghibli API for example.
 - [StudioGhibli API](https://ghibliapi.herokuapp.com/)

So, I create Film class for getting data from API. You should make class which has fields same with JSON object. 
 - Film Class
    - [Film class](https://github.com/ShotaKu/AndroidAppDev)
    - [Film Json](https://ghibliapi.herokuapp.com/films/2baf70d1-42bb-4437-b551-e5fed5a87abe)
 - FilmIndex Class
     - [FilmIndex](https://github.com/ShotaKu/AndroidAppDev)
     - [Films Json](https://ghibliapi.herokuapp.com/films)

You can see that my class has same fields like JSON.
### How to parce JSON to Custom class
Let's think about after you recive JSON data successfully. You have to parce the JSON String data to custom class object. 
 1. import json library of [google/gson](https://github.com/google/gson) by add following code at **build.gradle(Module:app)**

    ```Gradle
    implementation 'com.google.code.gson:gson:2.8.5'
    ```
 2. Sync gradle for making the library available in project.
 3. Using the library by following library guid. In my [FilmIndex class](https://github.com/ShotaKu/AndroidAppDev) has example of it.
    ```Kotlin
        constructor(json:String):this(){
            val gson = Gson();
            index = gson.fromJson(json, Array<Film>::class.java);
        }
    ```
This constructor will make array of Film from input ```json:String```. You can see how to use it on

### How to get JSON from API?
1. import Http networking library from [kittinunf/Fuel Repository](https://github.com/kittinunf/Fuel) by add following code at **build.gradle(Module:app)**
        ```Gradle
        api 'com.github.kittinunf.fuel:fuel-android:1.15.1'
        ```
2. Sync gradle for making the library available in project.
3. Using the library by following library guid.
        ```Kotlin
        var index:FilmIndex = FilmIndex();
        FuelManager.instance.basePath = "https://ghibliapi.herokuapp.com/"
        "/films".httpGet().responseString { request, response, result ->
            //make a GET to https://httpbin.org/get and do something with response
            val (data, error) = result
            if (error == null) {
                //do something when succes
                //films will return all films of Studio ghibli. 
                index = FilmIndex(data!!);

                //Apply it to recyclerView
                val rView = recyclerView //ID of recycler view
                rView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

                var adapter = FilmAdapter(index.getArrayListIndex());
                rView.adapter = adapter;
            } else {
                //error handling. You can show toast
            }
        }
        ```

