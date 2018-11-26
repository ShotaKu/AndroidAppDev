# Lecture note week 6
## How to use google map api

1. Get google android API key
2. Create new activity as google map activity
3. Then place API key on res > value > google_map_api.xml
```xml
<string name="google_maps_key" 
templateMergeStrategy="preserve" 
translatable="false">Here</string>
```
4. Try run the app

## How to put pin on map
We have function `mMap.addMarker(LatLong)` and the function need LatLong class for input. You can create it by using `LatLng(Double,Double)`.

```kotlin
mMap.addMarker(MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"))
```

## How to change image of pin
You can use `.icon` function for change image of pin. Also, you can set subtitle by using `.snippet(String)` function.
```kotlin
MarkerOptions().position(this.getLatLng())
                .title("Me")
                .snippet("Here is my location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.me))
```

## Create and using tread function
Thread function is normaly used for parallel processing. 
```kotlin
class MyThread: Thread {
        constructor():super() {
            // First action when this thread was initialized
        }

        override fun run() {
            while (true) {
                try {
                    // Process here
                    Thread.sleep(1000)  //Sleep this tread and return the process. 
                                        //(Sleep will give change to other thread to run.)
                } catch (ex: Exception) {
                    // Catch error if error happen when thread.
                }
            }
        }
    }
```
After you finish create your own tread, if you want to using it, you can initialize the thread by `var thread = MyThread()`. Then run it by `thread.start()`.
