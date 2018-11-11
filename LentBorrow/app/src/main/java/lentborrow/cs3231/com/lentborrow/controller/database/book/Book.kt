package lentborrow.cs3231.com.lentborrow.controller.database.book

class Book(){
    var id = "";
    var category = ""
    var imageURL = ""
    var isBorrowed = false
    var isUsedForRequest = false
    var lentBy = ""
    var locate = ""
    var name = ""
    var requester = "";
    var tradeType = "";


    constructor(id:String, category: String, imageURL:String, isBorrowed:Boolean, isUsedForRequest: Boolean
                , lentBy:String, locate:String, name:String, reqester:String, tradeType:String):this(){
        this.id = id;
        this.category = category;
        this.imageURL = imageURL;
        this.isBorrowed = isBorrowed
        this.isUsedForRequest = isUsedForRequest
        this.lentBy = lentBy
        this.locate = locate
        this.name = name;
        this.requester = requester;
        this.tradeType = tradeType;
    }
}