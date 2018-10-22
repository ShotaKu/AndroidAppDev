package shota.cs3231.com.studioghibli.ghibli
import com.google.gson.Gson

class FilmIndex(){
    var index:Array<Film> = arrayOf()
        get() = field;

    constructor(json:String):this(){
        val gson = Gson();
        index = gson.fromJson(json, Array<Film>::class.java);
    }

    fun getArrayListIndex():ArrayList<Film>{
        return index.toCollection(ArrayList());
    }
}