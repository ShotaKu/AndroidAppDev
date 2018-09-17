# Lecture note
## How to use google map api?

1. Get google android API key
2. Create new activity as google map activity
3. Then place API key on res > value > google_map_api.xml
```xml
<string name="google_maps_key" 
templateMergeStrategy="preserve" 
translatable="false">Here</string>
```
4. Try run the app

## How to put pin on map?
We have function `mMap.addMarker(LatLong)` and the function need LatLong class for input. You can create it by using `LatLng(Double,Double)`.

```kotlin
mMap.addMarker(MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"))
```

