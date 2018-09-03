## Lecture note
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
implementation 'com.android.support:appcompat-v7:26.1.0'
// to be 
implementation 'com.android.support:recyclerview-v7:26.1.0'
implementation 'com.android.support:cardview-v7:26.1.0'
```