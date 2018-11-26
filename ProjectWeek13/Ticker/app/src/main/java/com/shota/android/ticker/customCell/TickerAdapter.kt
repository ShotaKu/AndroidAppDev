package com.shota.android.ticker.customCell

import android.content.res.Resources
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat.getColor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.shota.android.ticker.R
import com.shota.android.ticker.controller.Ticker
import kotlinx.android.synthetic.main.customcell_ticker.view.*


class TickerAdapter(val tickerList: ArrayList<Ticker>):RecyclerView.Adapter<TickerAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return tickerList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customcell_ticker,parent,false)
/*        v.setOnClickListener{
            var message = v.subtitle.text +":"+ v.title.text
            Toast.makeText(it,message, Toast.LENGTH_LONG).show()
        }*/
        val newView = ViewHolder(v)
        return newView
    }

    override fun onBindViewHolder(viewHolder: TickerAdapter.ViewHolder, p1: Int) {
        val name = tickerList[p1].currencyPair;
        val doller =  "$" +String.format("%1$,.2f", tickerList[p1].getLast());
        val in24h = String.format("%1$,.2f",tickerList[p1].getBaseVolume()) + " USBT in 24h"
        val change = tickerList[p1].getPercentChenge()

        var pm = ""
        if(change<0){
            viewHolder.change.setTextColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.minus))
            pm = "-"
        }
        else if(0<change){
            viewHolder.change.setTextColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.plus))
            pm = "+"
        }
        else{
            viewHolder.change.setTextColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.abc_tint_default))
            pm = ""
        }


        viewHolder.name.text = name;
        viewHolder.doller.text = doller;
        viewHolder.change.text = pm + String.format("%1$,.2f",change)
        viewHolder.in24h.text = in24h;
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val name = v.name_ticker
        val change = v.change_ticker
        val in24h = v.in24h_ticker
        val doller = v.dollar_ticker
    }
}