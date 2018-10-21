package lentborrow.cs3231.com.lentborrow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import lentborrow.cs3231.com.lentborrow.customCell.Book

class SearchActivity : AppCompatActivity() {
    var list:RecyclerView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    private fun search(key:String){

    }

    private fun showResult(books:ArrayList<Book>){

        if(list != null){

        }
    }
}
