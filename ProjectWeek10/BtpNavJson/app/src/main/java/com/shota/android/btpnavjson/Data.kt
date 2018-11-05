package com.shota.android.btpnavjson

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Data(date: String, preTaxNav:Double, preTaxBid:Double, fundSize:Double,change:Double,percentChange:Double){
    var date = date
    var preTaxNav = preTaxNav
    var preTaxBid = preTaxBid
    var fundSize = fundSize
    var change = change
    var percentChange = percentChange

    fun getDate():Date{
        return Date(date);
    }
}