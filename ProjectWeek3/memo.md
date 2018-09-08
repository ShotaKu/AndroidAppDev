## Lecture note week 3
### How to make list in android apps?

1. Add code below build.gladle(Module app) at dependencies
    ```gradle
    dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation"org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support:recyclerview-v7:26.1.0'     //Add new here
    implementation 'com.android.support:cardview-v7:26.1.0'         //Add new here
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    }
    ```
    It is easy if you copy **implementation 'com.android.support:appcompat-v7:26.1.0'** 2 times and edit **appcompat** to **recyclerview** and **cardview**
    ```gradle
    //copy this part then...
    implementation 'com.android.support:appcompat-v7:26.1.0'
    // change to be 
    implementation 'com.android.support:recyclerview-v7:26.1.0'
    implementation 'com.android.support:cardview-v7:26.1.0'
    ```

2. Create list cell in new layer
    + Right click at app>res>layout folder
    + Create new layout file
        - **File name must be lower case for all characters**
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="match_parent">

    </android.support.constraint.ConstraintLayout>
    ```
    This is default empty layout. Make cell layout in this layout file.
    I create cell like [this file](
      AndroidAppDev/ProjectWeek3/app/src/main/res/layout/customcell.xml
     "customcell.xml").

    This is some tips for making list cells.

    1. Set contents as vertical layout group
        ```xml
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">     //orientation="vertical" will set children as vertical layout
        ```
    2. Set parent size as content size
        ```xml
        <android.support.v7.widget.CardView
            android:layout_width="match_parent"     //match_parent will set the content width as parent's size
            android:layout_height="wrap_content"    //wrap_content will set the content width as children's size
            android:layout_margin="5dp">
        ```
    3. Set margin size of content
        ```xml
        <android.support.v7.widget.CardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="5dp">        //Set margin space for 5dp
        ```
    4. Set padding size of content
        ```xml
        <TextView
            android:id="@+id/title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="10dp"      //Set padding space for 10dp
            android:text="Shopan Kuroda"
            android:textAppearance="@style/TextAppearance.AppCompat.Large" />
        ```
3. Create class for store data in cell
    + Create new .kt file at app>java>com.**Project_company_name**.**project_name**
    + Kotlin class format
    ```kotlin
    package com.shota.studentlistapp

    data class Student(val id: String,val name: String){

    }
    ```

### In progress (to be continue to adapter class)

<!-- 4. Crete Adapter class for useing recycler view in app
    + This class is created for overwrite recycler view functions as wanted behavior
    + You have too overwrite 3 functions:
        + getItemCount(): Int
        + onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
        + onBindViewHolder(viewHolder: StudentAdapter.ViewHolder, p1: Int)
    + Create class for each cell -->

    
