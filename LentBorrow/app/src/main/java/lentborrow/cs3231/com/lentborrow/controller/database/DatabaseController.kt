package lentborrow.cs3231.com.lentborrow.controller.database

import com.google.firebase.database.*


open class DatabaseController(){
    var database: FirebaseDatabase = FirebaseDatabase.getInstance();

    fun get(path:String, successCallback: (snapShot:DataSnapshot) -> Unit   // Unit = void
            , failedCallback:(error: DatabaseError) -> Unit){
        val myRef = database.getReference(path);
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //val value = dataSnapshot.getValue(String::class.java)
                //Log.d(TAG, "Value is: " + value!!)
                successCallback(dataSnapshot);
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                failedCallback(error);
            }
        })
    }

    fun setString(path:String,value:String){
        setObject(path,value);
    }

    fun setObject(path: String,value: Any) {
        val myRef = database.getReference(path)
        myRef.setValue(value);
    }
    fun pushObject(path:String,value: DatabaseForm):String{
        val myRef = database.getReference(path)
        val userPath:DatabaseReference = myRef.push()
        setObject(path+ "/" + userPath.key!!,value);
        return path + userPath.key!!
    }

//    myRef.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            // This method is called once with the initial value and again
//            // whenever data at this location is updated.
//            String value = dataSnapshot.getValue(String.class);
//            Log.d(TAG, "Value is: " + value);
//        }
//
//        @Override
//        public void onCancelled(DatabaseError error) {
//            // Failed to read value
//            Log.w(TAG, "Failed to read value.", error.toException());
//        }
//    });
}