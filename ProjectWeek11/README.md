# Lecture note week 11
# How to use firebase in Kotlin

## Connect to firebase
1. Create a project on firebase side (if didnt prepared)
    - Open firebase and signin or signup
    - Create new project and select region

2. Open/Create android studio project
    - Select Tool>Firebase from toolbar.
    - Choose function that you want to use
        - Use auth in this example
    - Click `Connect to firebase`
    - Give google login permission to android studio
    - Choose your firebase project that you want to connect.

3. Import library from Gradle
    - **Not recommend to setting up from firebase tool in Android studio because the library is too old!**
    - Add following code for import google maven library at root-level build.gradle
        ```Gradle
        buildscript {
            dependencies {
            // ...
                classpath 'com.google.gms:google-services:4.2.0' // Add this
            }
        }

        allprojects {
            repositories {
                google() // Add if not there
            }
        }
        ```
    - See [Android firebase reference](https://firebase.google.com/docs/android/setup?authuser=0) and pick library which you want to use.
        - This example will use auth library.
    - Add core code for import firebase library at app-level 
        ```Gradle
        apply plugin: 'com.android.application'

        android {

        }

        dependencies {
            implementation 'com.google.firebase:firebase-core:16.0.4' 
            //Core library for firebase

            implementation 'com.google.firebase:firebase-auth:16.0.5' 
            //Add library that you want to use too
        }

        // ADD THIS AT THE BOTTOM (DO NOT FORGET)
        apply plugin: 'com.google.gms.google-services'
        ```
    - Sync gradle to project.

# How to use firebase library
## Authentication 

### Login
Almost all function in firebase library is **parallel proceed function**. So, you need use it carefully

    1. Initialize firebase app in project
        - First of all, you need to initialize app before use all functions by following code

        ```kotlin
        FirebaseApp.initializeApp(this); 
        // No need if already initialize in other place.
        ```

    2. Get instance to firebase auth for connection

        ```kotlin
        val mAuth: FirebaseAuth? = FirebaseAuth.getInstance();
        ```

    3. Signup function is also parallel proceed function

        ```kotlin
        mAuth!!.signInWithEmailAndPassword(mEmail,mPassword)
                            .addOnCompleteListener(activity, OnCompleteListener<AuthResult>{ task ->
                                if(task.isSuccessful){
                                    afterLoginTask(activity)
                                }else{
                                    failedToLoginTask
                                }
                            })
        ```

## Realtime database
### Read 
1. Get instance of firebase realtime database by following code.

    ```kotlin
    val database = FirebaseDatabase.getInstance()
    ```

    Make sure that you already initialize firebase app in project. If not, initialize it before get instance.

    ```kotlin
    FirebaseApp.initializeApp(this); 
    val database = FirebaseDatabase.getInstance()
    ```

2. Set reference to directory in database which you want to use

    ```kotlin
    val myRef = database.getReference(email)
    ```

3. Read value on reference

    ```kotlin
    myRef.addValueEventListener(object : ValueEventListener {

        //Take snapshot of all data in reference
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val memoList:ArrayList<Memo> = ArrayList()
            for(data in dataSnapshot.children){
                // process each data in reference
                val title = data.key.toString();
                val content = data.value.toString()
                val memo = Memo(title, content);
                memoList.add(memo);
            }
            list.adapter = MemoListAdapter(memoList);
        }
        // If error
        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context, error.message.toString(),Toast.LENGTH_LONG);
        }
    })
    ```

- You can create class to manage database like following code. This code will get all memo in firebase.
    
    Implementation:
    ```kotlin
    fun getMemo(title: String, successCallback: (memo: Memo) -> Unit, failedCallback: (error: DatabaseError) -> Unit) {
        val ref = database.getReference(reference + "/" + title);
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val memo = Memo(dataSnapshot)
                successCallback(memo);
            }

            override fun onCancelled(error: DatabaseError) {
                failedCallback(error);
            }
        });
    }
    ```

    Usage:
    ```kotlin
        val list = rView_main
        list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val context = this.baseContext;

        dCon.getAllMemo({ memoList ->   //memoList is result that get from firebase.
            list.adapter = MemoListAdapter(memoList);
        },{ error ->    // get FirebaseError class if error occur
            Toast.makeText(context, error.message.toString(),Toast.LENGTH_LONG);
        })
    ```

### Set
1. Get instance of firebase realtime database by following code.

    ```kotlin
    val database = FirebaseDatabase.getInstance()
    ```

    Make sure that you already initialize firebase app in project. If not, initialize it before get instance.

    ```kotlin
    FirebaseApp.initializeApp(this); 
    val database = FirebaseDatabase.getInstance()
    ```

2. Set reference to directory in database which you want to use

    ```kotlin
    val myRef = database.getReference("Memo")
    ```

3. Set value on reference

    ```kotlin
    myRef.setValue("Hello,World!")
    ```

    You can add SuccessListener when write value too.
    ```kotlin
    mDatabase.child("users").child(userId).setValue(user)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Write was successful!
                // ...
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Write failed
                // ...
            }
        });
    ```

## Reference
 - [Real time database: read and write](https://firebase.google.com/docs/database/android/read-and-write)
 - [Add firebase on android project](https://firebase.google.com/docs/android/setup)
 - [Password authentication](https://firebase.google.com/docs/auth/android/password-auth)