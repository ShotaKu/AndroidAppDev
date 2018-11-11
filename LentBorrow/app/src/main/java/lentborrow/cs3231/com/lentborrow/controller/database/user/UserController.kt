package lentborrow.cs3231.com.lentborrow.controller.database.user

import android.provider.ContactsContract
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import lentborrow.cs3231.com.lentborrow.controller.database.DatabaseController

class UserController(): DatabaseController(){
    fun create(user:User):User{
        val userID = this.pushObject("User",user.getDatabaseForm());
        user.userID = userID;
        return user;
    }
    fun getUserByEmail(email:String, successCallback: (snapShot: DataSnapshot) -> Unit   // Unit = void
    , failedCallback:(error: DatabaseError) -> Unit){

        finds("User",
                { s:DataSnapshot -> searchUserByEmail(email,s) }
                ,successCallback
                ,failedCallback)
    }

    fun getUserByID(id:String, successCallback: (snapShot: User) -> Unit   // Unit = void
                    , failedCallback:(error: DatabaseError) -> Unit){
        finds("User",
                { s:DataSnapshot -> searchUserByID(id,s) }
                ,fun(s:DataSnapshot){

                }
                ,failedCallback)
    }

    fun searchUserByEmail(email: String, snapShot: DataSnapshot):Boolean{
        var result:Boolean = false;

        val mail = snapShot.child("email").value.toString();
        if(email == mail){
            result = true;
        }

        return result;
    }

    fun dataSnapshotAdapter(snapShot: DataSnapshot):User{
        return User();
    }

    fun searchUserByID(id: String, snapShot: DataSnapshot):Boolean{
        var result:Boolean = false;

        val uID = snapShot.key.toString();
        if(id == uID){
            result = true;
        }

        return result;
    }

}