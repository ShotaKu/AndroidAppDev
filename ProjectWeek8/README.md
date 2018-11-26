## Lecture note
### How to change activity pages?
1. Make text plane and button.
    - The button will be called **Button A**
    - The edit text will be called **Edit text A**
2. Make following fucntion
```kotlin
fun buttonClicked(view: View){
    //Edit text A which created in 1st step
    var edittext = findViewByID<EditText>(R.id.editText);   
    var message = editText.text.toString();

    if(message != ""){
        //Load activity information
        var intent = Intent(this, SecondActivity::class.java).apply {
            //Set value which want to send to the activity.
            putExtra("Message",message);
        }
        //{} will make no need to make like this
        //instent.putExtra("Message",message)
        //instent.putExtra("Message",message)
        //instent.putExtra("Message",message)
        startActivity(intent);
    }else{
        //If message is empty
    }
    
}
```
3. Attach function at **Button A**
4. Create new activity at java folder
    + Right click at java file
    + New > activity > empty activity
5. Add code for getting message which sent from first activity
    + Name it as **SecondActivity**
```kotlin
//In SecondActivity class.
var message = intent.getStringExtra("MESSAGE");
textView.text = message;
```

6. Done enjoy.

## Example source code
- [Week8/TwoPage](https://github.com/ShotaKu/AndroidAppDev/tree/master/ProjectWeek8/TwoPage)
- [Week8/StudentListVer2](https://github.com/ShotaKu/AndroidAppDev/tree/master/ProjectWeek8/StudentListVer2)
