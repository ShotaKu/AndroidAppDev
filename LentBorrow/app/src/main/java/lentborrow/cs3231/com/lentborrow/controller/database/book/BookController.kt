package lentborrow.cs3231.com.lentborrow.controller.database.book

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import lentborrow.cs3231.com.lentborrow.controller.database.DatabaseController
import lentborrow.cs3231.com.lentborrow.controller.database.user.UserController
import java.lang.Boolean.parseBoolean

class BookController(): DatabaseController(){
    fun create(book: Book): Book {
        return Book();
    }
    fun getBooksByName(name:String, successCallback: (snapShot: ArrayList<DataSnapshot>) -> Unit   // Unit = void
                       , failedCallback:(error: DatabaseError) -> Unit){

        find("Book",
                { s: DataSnapshot -> searchBookByName(name,s) }
                ,successCallback
                ,failedCallback)
    }

    fun getBooksByID(id:String, successCallback: (snapShot: ArrayList<DataSnapshot>) -> Unit   // Unit = void
                     , failedCallback:(error: DatabaseError) -> Unit){

        find("Book",
                { s: DataSnapshot -> searchBookByID(id,s) }
                ,successCallback
                ,failedCallback)
    }
    fun getBooksByOwnerID(id:String, successCallback: (snapShot: ArrayList<DataSnapshot>) -> Unit   // Unit = void
                          , failedCallback:(error: DatabaseError) -> Unit){
        val uCon = UserController();
        uCon.
    }

    final fun snapShotBookAdapter(snapShot: DataSnapshot):Book{
        val id = snapShot.key.toString();
        val category = snapShot.child("category").value.toString()
        val imageURL = snapShot.child("image").value.toString()
        val isBorrowed = parseBoolean(snapShot.child("isBorrowed").value.toString())
        val isUsedForRequest = parseBoolean(snapShot.child("isUsedForRequest").value.toString())
        val lentBy = snapShot.child("lentBy").value.toString()
        val locate = snapShot.child("locate").value.toString()
        val name = snapShot.child("name").value.toString();
        val requester = snapShot.child("requester").value.toString()
        val tradeType  =snapShot.child("tradeType").value.toString()
        return Book(id, category, imageURL, isBorrowed, isUsedForRequest
                , lentBy, locate, name, requester, tradeType)
    }

    private fun searchBookByName(keyName: String, snapShot: DataSnapshot):Boolean{
        var result:Boolean = false;

        val name = snapShot.child("name").value.toString();
        val reg = Regex(keyName)
        if(reg.containsMatchIn(name)){
            result = true;
        }

        return result;
    }

    private fun searchBookByID(keyID: String, snapShot: DataSnapshot):Boolean{
        var result:Boolean = false;

        val id = snapShot.key.toString();
        if(id == keyID){
            result = true;
        }

        return result;
    }

}