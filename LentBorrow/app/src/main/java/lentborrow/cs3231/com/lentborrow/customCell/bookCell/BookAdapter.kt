package lentborrow.cs3231.com.lentborrow.customCell.bookCell

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.book_customcell.view.*
import kotlinx.android.synthetic.main.request_customcell.view.*
import lentborrow.cs3231.com.lentborrow.R
import lentborrow.cs3231.com.lentborrow.customCell.Book

class BookAdapter(val requests: ArrayList<Book>): RecyclerView.Adapter<BookAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return requests.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.book_customcell,parent,false)
/*        v.setOnClickListener{
            var message = v.subtitle.text +":"+ v.title.text
            Toast.makeText(it,message, Toast.LENGTH_LONG).show()
        }*/
        val newView = ViewHolder(v)
        return newView
    }

    override fun onBindViewHolder(viewHolder: BookAdapter.ViewHolder, p1: Int) {
        var name = requests[p1].name
        var id = requests[p1].tradeType;
        viewHolder.studentName.text = name;
        viewHolder.id.text = id;
        viewHolder.itemView.setOnClickListener {
            //Toast.makeText(this, requests[p1].id+": "+requests[p1].name, Toast.LENGTH_SHORT).show()
            //TODO create toast pop up when click card view
            Toast.makeText(it.context,id+":"+name, Toast.LENGTH_LONG).show()
        }
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val studentName = v.bookName_bookCell
        val id = v.tradeType_bookCell
    }
}