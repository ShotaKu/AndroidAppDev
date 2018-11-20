package lentborrow.cs3231.com.lentborrow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.android.synthetic.main.activity_book_detail.*
import lentborrow.cs3231.com.lentborrow.controller.activity.ActivityMigrationController
import lentborrow.cs3231.com.lentborrow.controller.database.book.Book
import lentborrow.cs3231.com.lentborrow.controller.database.book.BookController
import lentborrow.cs3231.com.lentborrow.generic.MessageController
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView


class BookDetailActivity : AppCompatActivity() {

    var bCon = BookController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        var bookID = intent.getStringExtra("bookID");
        getBook(bookID)
    }
    fun getBook(bookID:String){
        var book = Book()

        bCon.getBookByID(bookID,{ book-> run {
            if(book != null)
                showBookDetail(book);
            else
                MessageController(this).showToast("Book detail broken");
        }},{ error ->
            MessageController(this).showToast("Book not found");
        })
    }
    fun showBookDetail(book: Book){
        bookName_detail.text = book.name;
        tradeType_detail.text =  book.tradeType;
        bookCategory_detail.text = "Category: " + book.category;
        tradeAt_detail.text = "Trade at " + book.locate;
        bookOwner_detail.text = "See book owner"
        userID = book.lentBy;
        downloadImage(book.imageURL);
    }

    var userID = "";

    fun toOwnerDetail(){
        val amCon = ActivityMigrationController()
        amCon.setUserDetail(this).pass("userID",userID).go();
    }

    fun downloadImage(imageURL:String){
        DownloadImageTask(bookImage_detail)
                .execute(imageURL);
    }

    private class DownloadImageTask(internal var bmImage: ImageView) : AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            val urldisplay = urls[0]
            var mIcon11: Bitmap? = null
            try {
                val `in` = java.net.URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error", e.message)
                e.printStackTrace()
            }

            return mIcon11
        }

        override fun onPostExecute(result: Bitmap) {
            bmImage.setImageBitmap(result)
        }
    }
}


