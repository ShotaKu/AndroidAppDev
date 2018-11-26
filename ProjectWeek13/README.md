# Lecture note week 13
## Create Ticker app from firebase of another user project
### Firebase setup

This setup is another way from week 11 set up.

1. Create new project in Android studio
2. Create new project in firebase
    1. Click `+ Add application` on `Project overview` tab
    2. Follow 4 steps which firebase 
        - Step 1: Fill information about android project
            + Package name = **(Must be same with)** your application packagen name 
            + App nickname = Any name as you like
            + Debug signing... = __""(empty)__
        - Step 2: Download `google-services.json` file and place it under `<Project path>/app/`
        - Step 3: Add SDK. See step 1 in [lecture note week 11](https://github.com/ShotaKu/AndroidAppDev/blob/master/ProjectWeek11/) and do same thing to your new project.
            - **Do not forgot to update new firebase-core version.** Latest is 16.0.4 (in 2018/11/26)
        - Step 4: Run app and make sure that firebase ditect connection

    - Option: If you dont know package name

        Open AndroidManifest.xml and you can check package name from it

        ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        package="com.shota.android.ticker">
        // ↑This is your package name
        ```

3. Test realtime database by using your firebase project
    - **Setup**
        1. Add realtime database SDK to gradle

            ```Gradle
            implementation 'com.google.firebase:firebase-database:16.0.4'
            ```

    - **Write to database**
        1. Write following code for add a message on realtimde database
            ```kotlin
            val database = FirebaseDatabase.getInstance();
            val myRef = database.getReference("message");

            myRef.setValue("Hello, World!");
            ```
            import package if necessary
        2. Check `realtime database` in `database tab`
    
    - **Read**
        1. Write following code for read value from realtimce database

            ```kotlin
            myRef.addValueEventListener(object:ValueEventListener {
                override fun onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.value.toString();
                    Log.d(TAG, "Value is: " + value);
                }

                override fun onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            })
            ```
            import package if necessary
### Use firebase project of other user
 - Edit `google-services.json`

    ```JSON
        "project_info": {
        "project_number": "971959668906",
        "firebase_url": "https://<Your firebase project name>.firebaseio.com",
        "project_id": "androidappdevweek13",
        "storage_bucket": "androidappdevweek13.appspot.com"
        },
    ```

    change firebase_url to be other person's URL

    This method can use in case the project security rule set as **public**.

    You can read and write like you tested in Firebase setup.

### Example code
- Read value and make ArrayList of object class

    ```Kotlin
    val ref = database.getReference("tickers")
    ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list:ArrayList<Ticker> = arrayListOf()  // init list
                // snapShot have many ticker object inside
                // make foreach loop for it.
                for(ticker in snapshot.children){
                    // read each child value
                    val last = ticker.child("last").value.toString()
                    val cPair = ticker.child("currencyPair").value.toString()
                    val bVolume = ticker.child("baseVolume").value.toString()
                    val pChange = ticker.child("percentChange").value.toString()
                    list.add(Ticker(last,cPair,bVolume,pChange))
                }
                //Apply to adapter
                rView.adapter = TickerAdapter(list)
            }

            override fun onCancelled(p0: DatabaseError) {
                // Show error Toast here
            }
        })
    ```

### Reference
 - [Firebase reference](https://firebase.google.com/docs/database/android/start/)