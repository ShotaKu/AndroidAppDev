## Lecture note week 10
### How to read JSON from local file?

 - This lecture note is writen for summarize how to put json folder in local application file, read the file and parse it in data class object. This lecture note will use [btp-nav.json](https://github.com/ShotaKu/AndroidAppDev/blob/master/ProjectWeek10/BtpNavJson/app/src/main/assets/btp-nav.json) for example of json data

1. Put `.json` file in `assets` folder
    - Create `assets` file in app folder if folder not exist(ploject file path/app/src/main)

2. Read all data in json file as String by using `InputStream`.
    ```kotlin
    val iStream:InputStream = getAssets().open("btp-nav.json");
    val json:String = iStream.reader().readText();
    ```

3. Import gson by using Gradle see at [week 9 lecture note]( notehttps://github.com/ShotaKu/AndroidAppDev/blob/master/ProjectWeek9/MEMO.md)

4. Create data class for parse json object. e.g. [Data](https://github.com/ShotaKu/AndroidAppDev/blob/master/ProjectWeek10/BtpNavJson/app/src/main/java/com/shota/android/btpnavjson/Data.kt)

5. Parse json data to data class by using fromJson function.
    ```kotlin
    val gson = Gson()
    val datas = gson.fromJson(json, Array<Data>::class.java);
    ```

6. Using the ArrayList data for show recycler view.