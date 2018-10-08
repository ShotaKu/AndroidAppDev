## Lecture note
### How to change activity pages?
1. Make text plane and button.
    - The button will be called **Button A**
    - The edit text will be called **Edit text A**
2. Make following fucntion
```kotlin
fun buttonClicked(view: View){
    //var message = editText
    var edittext = findViewByID<EditText>(R.id.editText);
    var message = editText.text.toString();
    if(message != ""){
        var intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("Message",message);
        }
        //{} will make no need to make like this
        //instent.putExtra("Message",message)
        //instent.putExtra("Message",message)
        //instent.putExtra("Message",message)
    }
    startActivity(intent);
}
```
3. Attach function at **Button A**
4. Create new activity at java folder
    + Right click at java file
    + New > activity > empty activity
5. Add code to get message
```kotlin
var message = intent.getStringExtra("MESSAGE");
textView.text = message;
```
