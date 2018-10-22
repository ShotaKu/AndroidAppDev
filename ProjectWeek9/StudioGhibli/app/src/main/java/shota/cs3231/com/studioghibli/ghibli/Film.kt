package shota.cs3231.com.studioghibli.ghibli

import java.util.*

class Film(var id: String = ""
           , var title: String = ""
           , var description: String = ""
           , var director: String = ""
           , var producer: String = ""
           , var release_date: String = ""
           , var rt_score: Int = 0
           , var people: Array<String> = arrayOf<String>()
           , var species: Array<String> = arrayOf<String>()
           , var vehicles: Array<String> = arrayOf<String>()
           , var url: String = ""){

    constructor():this("","","","","","",0,arrayOf<String>(),arrayOf<String>(),arrayOf<String>(),""){

    }
    fun getDate(): Date {
        return Date(this.release_date);
    }
}