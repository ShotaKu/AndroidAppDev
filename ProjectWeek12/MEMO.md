# Lecture note week 12
# How to save/load value in local strage?
Some value such as user email address, password for login, and userID should save in local strage in application development. It is because it help to auto login after first boot. You can use `SharedPreferences` for the situation.

## Create class for manage local value
### Initialize `SharedPreferences`
`SharedPreferences` must have unique key for save or load value. I create a `LocalValueController(context: Context)` for manage local strage value in android machine. e.g. [LocalValueController.kt](https://github.com/ShotaKu/AndroidAppDev/blob/master/ProjectWeek12/Storage/app/src/main/java/com/shota/android/storage/localValue/LocalValueController.kt)

```kotlin
    final val localStrageID = "STORAGE"     //must unique, static and readonly field
    var local: SharedPreferences? = null;
    var editor: SharedPreferences.Editor? = null;

    init {
        // initialised SharedPreferences
        this.local = context.getSharedPreferences(localStrageID, 0)
        // initialised editor for save value.
        this.editor = local!!.edit();
    }
```

### Create save function
`SharedPreferences` can save value by `.putString(String,String)` function of `Editor`.

the save function is like...

```kotlin
    fun setString(key: String, value: String) {
        // put string with key(ID). You will
        editor!!.putString(key, value);
        // save change by commit() function.
        editor!!.commit();
    }
```

### Create load function 
`SharedPreferences` can load value by `.getString(String,String)` function.

the load function is like...

```kotlin
    fun getString(key: String): String {
        // return string that saved by key(ID). Return empty string(second input) if not found.
        return local!!.getString(key, "");
    }
```

## Testing created class

1. Create save function for save button.
    ```kotlin
    fun OnClickSave(view: View){
        val sText = SaveText.text.toString()
        if(sText != null && !sText.isEmpty())
            lvCon!!.setString("save",sText)
    }
    ```
2. Create load function for load button
    ```kotlin
    fun OnClickLoad(view: View){
        val txt = lvCon!!.getString("save");
            if(txt != null){
                LoadText.setText(txt);
            }else{
                LoadText.setText("No value to load!!")
            }
    }
    ```

3. Create 1 plainText, 2 button, 1 textView each view have following ID
    - textView = loadText
    - plainText = saveText
    - button 1 = saveButton
    - button 2 = loadButton

    Set onClick function save and load

4. Run application

    